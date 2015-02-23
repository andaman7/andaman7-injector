package biz.manex.andaman7.injector.webservice.REST;

import biz.manex.andaman7.injector.models.AMI;
import biz.manex.andaman7.injector.models.AMIContainer;
import biz.manex.andaman7.injector.models.Qualifier;
import biz.manex.andaman7.server.api.dto.ehrSynchro.RegistrarSyncContentDTO;
import biz.manex.andaman7.server.api.dto.ehrSynchro.ehr.AmiBaseDTO;
import biz.manex.andaman7.server.api.dto.ehrSynchro.ehr.AmiContainerDTO;
import biz.manex.andaman7.server.api.dto.ehrSynchro.ehr.AmiQualDTO;
import biz.manex.andaman7.server.api.dto.registrar.AndamanUserDTO;
import biz.manex.andaman7.server.api.dto.registrar.RegistrarDTO;
import java.io.IOException;
import org.apache.http.HttpResponse;

import java.util.*;

/**
 * Contains methods to interact with the EHR service of Andaman7.
 *
 * @author Pierre-Yves Derbaix (pierreyves.derbaix@gmail.com)<br/>
 *         Copyright A7 Software (http://www.manex.biz)<br/>
 *         Date: 24/01/2015.
 */
public class AndamanEhrService extends CustomRestService {

    /**
     * Unique instance of the REST service.
     */
    private static final Map<String, AndamanEhrService> INSTANCES =
            new HashMap<String, AndamanEhrService>();


    /**
     * Builds an EHR service connection to the server.
     *
     * @param urlServer the URL of the server
     * @param apiKey the API key
     * @param login the login used for the authentication
     * @param password the password used for the authentication
     */
    private AndamanEhrService(String urlServer, String apiKey, String login, String password) {
        super(urlServer, apiKey, login, password);
    }

    /**
     * Returns the unique instance of the EHR service corresponding to the URL
     * server and the login.
     *
     * @param urlServer the URL of the distant server
     * @param apiKey the API key
     * @param login the login needed to authenticate
     * @param password the password needed to authenticate
     * @return the unique instance of the EHR service
     */
    public static AndamanEhrService getInstance(String urlServer, String apiKey, String login, String password) {

        AndamanEhrService instance = INSTANCES.get(String.format("%s#%s#%s", urlServer, login, password));

        if(instance == null) {
            
            instance = new AndamanEhrService(urlServer, apiKey, login, password);
            INSTANCES.put(String.format("%s#%s#%s", urlServer, login, password), instance);
        }

        return instance;
    }

    /**
     * Injects some AMIs into the EHR of a registrar.
     *
     * @param sourceRegistrar the source registrar
     * @param destinationRegistrar the destination {@link biz.manex.andaman7.server.api.dto.registrar.RegistrarDTO}
     * @param amiContainers the AMI containers where to inject AMIs
     * @param contextId the ID of the context
     * @param tamiVersion the version of the XML file describing the TAMIs
     * @throws java.io.IOException
     */
    public void sendAmiBasesToRegistrar(RegistrarDTO sourceRegistrar, AndamanUserDTO destinationRegistrar,
            List<AMIContainer> amiContainers, String contextId, int tamiVersion) throws IOException {

        String sourceDeviceId = sourceRegistrar.getDevices().get(0).getUuid();

        String destinationRegistrarId = destinationRegistrar.getUuid();
        String[] destinationRegistrars = { destinationRegistrarId };

        List<AmiContainerDTO> amiContainerDTOs = new ArrayList<AmiContainerDTO>();

        // Build AmiContainerDTOs for each AMI container
        for(AMIContainer amiContainer : amiContainers) {

            List<AMI> amis = amiContainer.getAmis();

            // Build an AmiBaseDTO for each AMI
            HashSet<AmiBaseDTO> amiBaseDTOs = new HashSet<AmiBaseDTO>();

            for (AMI ami : amis) {
                
                AmiBaseDTO amiBaseDTO = buildAmiBaseDTO(amiContainer.getUuid(),
                        destinationRegistrarId, ami, tamiVersion, sourceDeviceId);
                amiBaseDTOs.add(amiBaseDTO);
            }

            // Build the context map
            HashMap<String, String> contextMap = new HashMap<String, String>();
            contextMap.put(contextId, amiContainer.getUuid());

            // Build the AmiContainerDTO(s)
            AmiContainerDTO amiContainerDTO = buildAmiContainerDTO(amiBaseDTOs,
                    destinationRegistrar, contextMap);
            amiContainerDTOs.add(amiContainerDTO);
        }

        // Build the RegistrarSyncContentDTO(s)
        RegistrarSyncContentDTO syncContentDTO = buildRegistrarSyncContentDTO(sourceRegistrar,
                destinationRegistrars, amiContainerDTOs.toArray(new AmiContainerDTO[amiContainerDTOs.size()]));

        RegistrarSyncContentDTO[] syncContentDTOs = { syncContentDTO };

        // Send the request to the server
        String body = jsonMapper.writeValueAsString(syncContentDTOs);
        restTemplate.put("registrars/medical-records", body, true);

    }

    /**
     * Returns the medical data in queue for the specified device.
     *
     * @param deviceId the UUID of the device to retrieve medical data from
     * @return the HTTP response to the request
     * @throws java.io.IOException
     */
    public HttpResponse getMedicalDataInQueue(String deviceId) throws IOException {
        return restTemplate.get("devices/medical-records?device-uuid=" + deviceId + "&brand=android", true);
    }

    /**
     * Acknowledges some medical data to allow the server to delete them.
     *
     * @param deviceId the UUID of the device from which the data have been
     *                 retrieved
     * @param medicalRecordIds a list of UUIDs of the retrieved medical data
     * @return the HTTP response to the request
     * @throws java.io.IOException
     */
    public HttpResponse acknowledgeMedicalData(String deviceId, String[] medicalRecordIds) throws IOException {

            String body = jsonMapper.writeValueAsString(medicalRecordIds);
            return restTemplate.post("registrars/medical-records/acknowledge?device-uuid=" + deviceId, body, true);
    }

    /**
     * Builds an {@link AmiBaseDTO}.
     *
     * @param amiContainerId the UUID of the AMI container in which the AMI will
     *                       be inserted
     * @param registrarCreatorId the UUID of the registrar that created the AMI
     * @param ami the AMI
     * @param tamiVersion the version of the XML file describing the TAMIs
     * @param sourceDeviceId the UUID of the source device
     * @return the built {@link AmiBaseDTO}
     */
    private AmiBaseDTO buildAmiBaseDTO(String amiContainerId, String registrarCreatorId, AMI ami, int tamiVersion, String sourceDeviceId) {

        AmiBaseDTO amiBaseDTO = new AmiBaseDTO();
        
        amiBaseDTO.setUuid(UUID.randomUUID().toString());
        amiBaseDTO.setAmiContainerId(amiContainerId);

        HashSet<AmiQualDTO> amiQualDTOs = new HashSet<AmiQualDTO>();

        for(Qualifier qualifier : ami.getQualifiers()) {
            AmiQualDTO amiQualDTO = buildAmiQualDTO(qualifier, registrarCreatorId, tamiVersion, sourceDeviceId);
            amiQualDTOs.add(amiQualDTO);
        }

        amiBaseDTO.setQualifiers(amiQualDTOs);
        //amiBaseDTO.setUuidMulti("");
        amiBaseDTO.setRegistrarCreatorId(registrarCreatorId);
        amiBaseDTO.setTamiId(ami.getType().getKey());
        amiBaseDTO.setTamiVersion(String.valueOf(tamiVersion));
        amiBaseDTO.setValue(ami.getValue());
        amiBaseDTO.setCreationDate(new Date());
        amiBaseDTO.setCreatorId(sourceDeviceId);

        return amiBaseDTO;
    }

    private AmiQualDTO buildAmiQualDTO(Qualifier qualifier, String registrarCreatorId, int tamiVersion, String sourceDeviceId) {

        AmiQualDTO amiQualDTO = new AmiQualDTO();

        amiQualDTO.setUuid(UUID.randomUUID().toString());
        amiQualDTO.setCreatorId(sourceDeviceId);
        amiQualDTO.setCreationDate(new Date());
        amiQualDTO.setRegistrarCreatorId(registrarCreatorId);
        amiQualDTO.setTamiVersion(String.valueOf(tamiVersion));
        amiQualDTO.setTamiId(qualifier.getType().getKey());
        amiQualDTO.setValue(qualifier.getValue());

        return amiQualDTO;
    }

    /**
     * Builds an {@link AmiContainerDTO}.
     *
     * @param amiBaseDTOs the AmiBaseDTOs to add into the AmiContainerDTO
     * @param destinationRegistrar the destination RegistrarDTO
     * @param contextMap the contextmap that maps the source device with the
     *                   destination registrar
     * @return the built AmiContainerDTO
     */
    private AmiContainerDTO buildAmiContainerDTO(HashSet<AmiBaseDTO> amiBaseDTOs, AndamanUserDTO destinationRegistrar,
            HashMap<String, String> contextMap) {

        AmiContainerDTO amiContainerDTO = new AmiContainerDTO();
        
        amiContainerDTO.setAmiBases(amiBaseDTOs);
        amiContainerDTO.setRegistrarUUID(destinationRegistrar.getUuid());
        amiContainerDTO.setContextMap(contextMap);
        amiContainerDTO.setUuid(destinationRegistrar.getUuid());
        amiContainerDTO.setCreatorId(destinationRegistrar.getCreatorId());
        amiContainerDTO.setCreationDate(destinationRegistrar.getCreationDate());
        amiContainerDTO.setModificatorId(destinationRegistrar.getModificatorId());
        amiContainerDTO.setModificationDate(destinationRegistrar.getModificationDate());
        amiContainerDTO.setInvalidatorId(destinationRegistrar.getInvalidatorId());
        amiContainerDTO.setInvalidationDate(destinationRegistrar.getInvalidationDate());

        return amiContainerDTO;
    }

    /**
     * Builds a {@link biz.manex.andaman7.server.api.dto.ehrSynchro.RegistrarSyncContentDTO}.
     *
     * @param sourceRegistrar the source registrar
     * @param destinationRegistrars the list of destination registrar's UUIDs
     * @param amiContainerDTOs the list of AmiContainerDTOs
     * @return the built RegistrarSyncContentDTO
     */
    private RegistrarSyncContentDTO buildRegistrarSyncContentDTO(RegistrarDTO sourceRegistrar,
            String[] destinationRegistrars, AmiContainerDTO[] amiContainerDTOs) {

        String sourceRegistrarId = sourceRegistrar.getUuid();
        String sourceDeviceId = sourceRegistrar.getDevices().get(0).getUuid();

        RegistrarSyncContentDTO syncContentDTO = new RegistrarSyncContentDTO();
        
        syncContentDTO.setSourceDeviceId(sourceDeviceId);
        syncContentDTO.setSourceRegistrarId(sourceRegistrarId);
        syncContentDTO.setDestinationRegistrars(Arrays.asList(destinationRegistrars));
        syncContentDTO.setAmiContainerDTOs(Arrays.asList(amiContainerDTOs));
        syncContentDTO.setFileUuidToFileContent(new HashMap<String, String>());
        //syncContentDTO.setFileUuidToFileContentString("");
        //syncContentDTO.setLastUploadDateFromDevice();
        syncContentDTO.setCreationDate(new Date());
        syncContentDTO.setCreatorId(sourceDeviceId);
        syncContentDTO.setUuid(UUID.randomUUID().toString());

        return syncContentDTO;
    }
}
