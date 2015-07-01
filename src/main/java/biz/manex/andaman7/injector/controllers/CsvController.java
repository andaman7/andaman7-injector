package biz.manex.andaman7.injector.controllers;

import biz.manex.andaman7.injector.dtos.users.ehrs.ResultSyncContentDTO;
import biz.manex.andaman7.injector.exceptions.AndamanException;
import biz.manex.andaman7.injector.models.AMI;
import biz.manex.andaman7.injector.models.AMIContainer;
import biz.manex.andaman7.injector.models.Qualifier;
import biz.manex.andaman7.injector.models.QualifierMapping;
import biz.manex.andaman7.injector.models.types.QualifierType;
import biz.manex.andaman7.injector.models.types.TAMI;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

import javax.xml.bind.DatatypeConverter;
import java.io.*;
import java.text.ParseException;
import java.util.*;

/**
 * The controller that handles (de)serialization in CSV format.
 *
 * @author Pierre-Yves Derbaix (pierreyves.derbaix@gmail.com)<br/>
 *         Copyright A7 Software (http://www.a7-software.com)<br/>
 *         Date : 07/03/2015.
 */
public class CsvController {

    /**
     * Get the CSV records from a file.
     *
     * @param file the file to get the records from
     * @return a set of CSV records
     * @throws IOException if the CSV file is not found
     */
    private Iterable<CSVRecord> getRecords(File file) throws IOException {

        Reader in = new FileReader(file);
        CSVParser csvParser = new CSVParser(in, CSVFormat.EXCEL.withDelimiter(';').withHeader());

        return csvParser.getRecords();
    }

    /**
     * Returns a map of AMIs from an CSV file.
     *
     * @param file the CSV file
     * @return data to send to the destination registrar
     * @throws java.io.IOException if the CSV file is not found
     * @throws AndamanException TODO
     * @throws ParseException TODO
     */
    public List<AMIContainer> getDataFromCsvFile(File file) throws IOException, AndamanException, ParseException {

        // Get the records from the CSV file
        Iterable<CSVRecord> records = getRecords(file);
        String separator = "\\|";

        // Process each record
        Map<String, AMIContainer> amiContainersToSync = new HashMap<String, AMIContainer>();
        List<QualifierMapping> qualifiersMappings = new ArrayList<QualifierMapping>();

        for (CSVRecord record : records)
            processRecord(record, separator, amiContainersToSync, qualifiersMappings);

        // Map all the qualifiers to their corresponding AMI
        mapQualifiers(qualifiersMappings);

        return new ArrayList<>(amiContainersToSync.values());
    }

    private void processRecord(CSVRecord record, String separator, Map<String,
            AMIContainer> amiContainersToSync, List<QualifierMapping> qualifiersMappings) throws AndamanException, ParseException {

        String recordEhrContextIds = record.get("ehrContextIds");
        String recordEhrIds = record.get("ehrIds");

        String[] ehrContextIds = recordEhrContextIds.split(separator);
        String[] ehrIds = recordEhrIds.split(separator);

        if(ehrContextIds.length != ehrIds.length)
            throw new AndamanException("Mismatch between the number of EHR context ids and the number of EHR ids.");

        String recordDestinationRegistrarIds = record.get("destinationRegistrarIds");
        String[] destinationRegistrarIds = recordDestinationRegistrarIds.split(separator);

        String amiId = record.get("amiId");
        String tamiId = record.get("tamiId");
        String value = record.get("value");

        String recordCreationDate = record.get("creationDate");
        Calendar calendar = DatatypeConverter.parseDateTime(recordCreationDate);
        Date creationDate = calendar.getTime();

        // Build an AMI container
        Map<String, String> contextMap;
        AMIContainer amiContainer;

        // If the AMI container already exists
        if(amiContainersToSync.containsKey(recordDestinationRegistrarIds)) {

            amiContainer = amiContainersToSync.get(recordDestinationRegistrarIds);
            contextMap = amiContainer.getContextMap();

            // Otherwise, create it
        } else {

            contextMap = new HashMap<String, String>();
            amiContainer = new AMIContainer(ehrIds[0], Arrays.asList(destinationRegistrarIds));
            amiContainersToSync.put(recordDestinationRegistrarIds, amiContainer);
        }

        String parentId = record.get("parentId");

        // Create the AMI
        if(parentId == null || parentId.isEmpty()) {

            TAMI tami = new TAMI(tamiId);
            AMI ami = new AMI(amiId, tami, value, creationDate);
            amiContainer.addAmi(ami);

            // Create the qualifier and add it to the mapping list
        } else {

            QualifierType qualifierType = new QualifierType(tamiId);
            Qualifier qualifier = new Qualifier(UUID.randomUUID().toString(), qualifierType, value);

            QualifierMapping qualifierMapping = new QualifierMapping(qualifier, parentId, amiContainer);
            qualifiersMappings.add(qualifierMapping);
        }

        // Update the context map
        for(int i = 0; i < ehrContextIds.length; i++) {

            String ehrContextId = ehrContextIds[i];
            String ehrId = ehrIds[i];

            contextMap.put(ehrContextId, ehrId);
        }
    }

    /**
     * Maps each qualifier to the corresponding AMI.
     *
     * @param qualifiersMappings the list of qualifiers mappings.
     */
    private void mapQualifiers(List<QualifierMapping> qualifiersMappings) {

        // Add the qualifiers into the right AMIs
        for(QualifierMapping qualifierMapping : qualifiersMappings) {

            String parentId = qualifierMapping.getParentId();
            AMIContainer amiContainer = qualifierMapping.getAmiContainer();

            Qualifier qualifier = qualifierMapping.getQualifier();
            AMI ami = amiContainer.getAmis().get(parentId);
            ami.getQualifiers().add(qualifier);
        }
    }

    /**
     * Write a flat representation of a list of AMI containers to the specified file.
     *
     * @param file the file in which to save the CSV representation of the AMI containers
     * @throws IOException if an error occured while manipulating the specified file.
     */
    public void generateCsvFile(ResultSyncContentDTO resultSyncContentDTO, File file) throws IOException {

        FileWriter writer = new FileWriter(file);
        CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.EXCEL.withDelimiter(';'));

        // Write the headers
        csvPrinter.printRecord(
                "ehrContextIds",
                "ehrIds",
                "destinationRegistrarIds",
                "amiId",
                "tamiId",
                "value",
                "creationDate",
                "parentId"
        );

        /*for(AmiContainerDTO amiContainer : amiContainers) {

            StringBuilder ehrContextIds = new StringBuilder();
            StringBuilder ehrIds = new StringBuilder();

            Map<String, String> contextMap = amiContainer.getContextMap();
            Set<Map.Entry<String, String>> entries = contextMap.entrySet();
            Iterator<Map.Entry<String, String>> it = entries.iterator();

            while(it.hasNext()) {
                Map.Entry<String, String> entry = it.next();

                ehrContextIds.append(entry.getKey());
                ehrIds.append(entry.getValue());

                if(it.hasNext()) {
                    ehrContextIds.append("|");
                    ehrIds.append("|");
                }
            }

            String destinationRegistrarIds = amiContainer.getRegistrarUUID();

            for(AmiBaseDTO amiBaseDTO : amiContainer.getAmiBases()) {
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

                // Write a record per AMI
                String amiId = amiBaseDTO.getUuid();
                String tamiId = amiBaseDTO.getTamiId();
                String value = amiBaseDTO.getValue();
                String creationDate = sdf.format(amiBaseDTO.getCreationDate());
                String parentId = "";

                csvPrinter.printRecord(
                        ehrContextIds.toString(),
                        ehrIds.toString(),
                        destinationRegistrarIds,
                        amiId,
                        tamiId,
                        value,
                        creationDate,
                        parentId
                );

                // Write a record per qualifier
                for(AmiQualDTO amiQualDTO : amiBaseDTO.getQualifiers()) {

                    amiId = amiQualDTO.getUuid();
                    tamiId = amiQualDTO.getTamiId();
                    value = amiQualDTO.getValue();
                    creationDate = sdf.format(amiQualDTO.getCreationDate());
                    parentId = amiBaseDTO.getUuid();

                    csvPrinter.printRecord(
                            ehrContextIds.toString(),
                            ehrIds.toString(),
                            destinationRegistrarIds,
                            amiId,
                            tamiId,
                            value,
                            creationDate,
                            parentId
                    );
                }
            }
        }

        csvPrinter.flush();
        csvPrinter.close();*/
    }
}
