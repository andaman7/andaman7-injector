package biz.manex.andaman7.injector.webservice.REST;

import biz.manex.andaman7.injector.dto.*;
import org.apache.http.HttpResponse;

import java.util.*;

/**
 * @author Pierre-Yves Derbaix (pierreyves.derbaix@gmail.com)
 * Copyright A7 Software (http://www.manex.biz)
 * Date: 24/01/2015.
 */
public class AndamanEhrService extends CustomRestService {

    public AndamanEhrService(String urlServer, String apiKey) {
        super(urlServer, apiKey);
    }

    public HttpResponse sendAmiBasesToRegistrar(String sourceDeviceId,
            String sourceRegistrarId, RegistrarDTO destinationRegistrar,
            String  medicalRecordId, HashMap<String, String> amis, String login,
            String password) {

        String destinationRegistrarId = destinationRegistrar.getUuid();
        String[] destinationRegistrars = new String[] {destinationRegistrarId};

        // Build an AmiBaseDTO for each AMI
        HashSet<AmiBaseDTO> amiBaseDTOs = new HashSet<AmiBaseDTO>();
        Iterator it = amis.entrySet().iterator();

        while(it.hasNext()) {
            HashMap.Entry<String, String> entry =
                    (HashMap.Entry<String, String>) it.next();

            AmiBaseDTO amiBaseDTO = buildAmiBaseDTO(destinationRegistrarId,
                    entry.getKey(), entry.getValue(), sourceDeviceId);
            amiBaseDTOs.add(amiBaseDTO);
        }

        // Build the context map
        HashMap<String, String> contextMap = new HashMap<String, String>();
        contextMap.put(sourceDeviceId, destinationRegistrar.getUuid());

        // Build the AmiContainerDTO(s)
        AmiContainerDTO amiContainerDTO = buildAmiContainerDTO(amiBaseDTOs,
                destinationRegistrar, contextMap);
        AmiContainerDTO[] amiContainerDTOs =
                new AmiContainerDTO[] { amiContainerDTO };

        // Build the RegistrarSyncContentDTO(s)
        RegistrarSyncContentDTO syncContentDTO = buildRegistrarSyncContentDTO(
                sourceRegistrarId, sourceDeviceId, destinationRegistrars,
                medicalRecordId, amiContainerDTOs);
        RegistrarSyncContentDTO[] syncContentDTOs = new
                RegistrarSyncContentDTO[] { syncContentDTO };

        // Send the request to the server
        try {
            String body = this.jsonMapper.writeValueAsString(syncContentDTOs);
            return this.restTemplate.put("registrars/medical-records", body,
                    login, password);

        } catch (Exception e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }

        return null;
    }

    public HttpResponse getMedicalRecordsInQueue(String deviceId, String login,
            String password) {

        try {
            return this.restTemplate.get("devices/medical-records?device-uuid="+
                    deviceId + "&brand=android", login, password);

        } catch (Exception e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }

        return null;
    }

    private AmiBaseDTO buildAmiBaseDTO(String destinationRegistrarId,
            String tamiId, String value, String sourceDeviceId) {

        AmiBaseDTO amiBaseDTO = new AmiBaseDTO();
        amiBaseDTO.setAmiContainerId(destinationRegistrarId);
        amiBaseDTO.setQualifiers(new HashSet<AmiQualDTO>());
        //amiBaseDTO.setUuidMulti("");
        //amiBaseDTO.setRegistrarCreatorId("");
        //amiBaseDTO.setSynchroState("");
        amiBaseDTO.setTamiId(tamiId);
        amiBaseDTO.setTamiVersion("1");
        amiBaseDTO.setValue(value);
        amiBaseDTO.setCreationDate(new Date());
        amiBaseDTO.setCreatorId(sourceDeviceId);

        return amiBaseDTO;
    }

    private AmiContainerDTO buildAmiContainerDTO(
            HashSet<AmiBaseDTO> amiBaseDTOs, RegistrarDTO destinationRegistrar,
            HashMap<String, String> contextMap) {

        AmiContainerDTO amiContainerDTO = new AmiContainerDTO();
        //amiContainerDTO.setSynchroState("");
        amiContainerDTO.setAmiBases(amiBaseDTOs);
        amiContainerDTO.setRegistrarUUID(destinationRegistrar.getUuid());
        amiContainerDTO.setContextMap(contextMap);
        amiContainerDTO.setUuid(destinationRegistrar.getUuid());
        amiContainerDTO.setCreatorId(destinationRegistrar.getCreatorId());
        amiContainerDTO.setCreationDate(destinationRegistrar.getCreationDate());
        amiContainerDTO.setModificatorId(
                destinationRegistrar.getModificatorId());
        amiContainerDTO.setModificationDate(
                destinationRegistrar.getModificationDate());
        amiContainerDTO.setInvalidatorId(
                destinationRegistrar.getInvalidatorId());
        amiContainerDTO.setInvalidationDate(
                destinationRegistrar.getInvalidationDate());

        return amiContainerDTO;
    }

    private RegistrarSyncContentDTO buildRegistrarSyncContentDTO(
            String sourceRegistrarId, String sourceDeviceId,
            String[] destinationRegistrars, String medicalRecordId,
            AmiContainerDTO[] amiContainerDTOs) {

        RegistrarSyncContentDTO syncContentDTO = new RegistrarSyncContentDTO();
        syncContentDTO.setSourceDeviceId(sourceDeviceId);
        syncContentDTO.setSourceRegistrarId(sourceRegistrarId);
        syncContentDTO.setDestinationRegistrars(
                Arrays.asList(destinationRegistrars));
        syncContentDTO.setMedicalRecordId(medicalRecordId);
        syncContentDTO.setAmiContainerDTOs(Arrays.asList(amiContainerDTOs));
        //syncContentDTO.setEhrsContent("");
        //syncContentDTO.setFileUuidToFileContent(new HashMap<String, String>());
        //syncContentDTO.setFileUuidToFileContentString("");
        //syncContentDTO.setLastUploadDateFromDevice();
        syncContentDTO.setCreationDate(new Date());
        syncContentDTO.setCreatorId(sourceDeviceId);
        syncContentDTO.setUuid(UUID.randomUUID().toString());

        return syncContentDTO;
    }
}
