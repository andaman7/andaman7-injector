package biz.manex.andaman7.injector.webservice.REST;

import biz.manex.andaman7.injector.dto.*;
import org.apache.http.HttpResponse;

import java.util.*;

/**
 * This class contains methods to interact with the EHR service of Andaman7.
 *
 * @author Pierre-Yves Derbaix (pierreyves.derbaix@gmail.com)
 * Copyright A7 Software (http://www.manex.biz)
 * Date: 24/01/2015.
 */
public class AndamanEhrService extends CustomRestService {

    /**
     * Unique instance of the REST service.
     */
    private static Map<String, AndamanEhrService> instances =
            new HashMap<String, AndamanEhrService>();


    private AndamanEhrService(String urlServer, String apiKey, String login,
            String password) {
        super(urlServer, apiKey, login, password);
    }

    /**
     * Returns the unique instance of the EHR service.
     *
     * @param urlServer the URL of the distant server
     * @param apiKey the API key
     * @param login the login needed to authenticate
     * @param password the password needed to authenticate
     * @return the unique instance of the EHR service
     */
    public static AndamanEhrService getInstance(String urlServer, String apiKey,
            String login, String password) {

        AndamanEhrService instance = AndamanEhrService.instances.get(urlServer +
                "#" + login);

        if(instance == null) {
            instance = new AndamanEhrService(urlServer, apiKey, login,
                    password);
            AndamanEhrService.instances.put(urlServer + "#" + login, instance);
        }

        return instance;
    }

    /**
     * Injects some AMIs into the EHR of a registrar.
     *
     * @param sourceDeviceId the UUID of the source device
     * @param sourceRegistrarId the UUID of the source registrar
     * @param destinationRegistrar the destination {@link RegistrarDTO}
     * @param amis the AMIs to inject into the destination registrar's EHR
     * @return the HTTP response to the request
     */
    public HttpResponse sendAmiBasesToRegistrar(String sourceDeviceId,
            String sourceRegistrarId, AndamanUserDTO destinationRegistrar,
            HashMap<String, String> amis) {

        String destinationRegistrarId = destinationRegistrar.getUuid();
        String[] destinationRegistrars = new String[] {destinationRegistrarId};

        // Build an AmiBaseDTO for each AMI
        HashSet<AmiBaseDTO> amiBaseDTOs = new HashSet<AmiBaseDTO>();

        for(HashMap.Entry<String, String> entry : amis.entrySet()) {
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
                amiContainerDTOs);
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

    /**
     * Returns the medical data in queue for the specified device.
     *
     * @param deviceId the UUID of the device to retrieve medical data from
     * @return the HTTP response to the request
     */
    public HttpResponse getMedicalDataInQueue(String deviceId) {

        try {
            return this.restTemplate.get("devices/medical-records?device-uuid="+
                    deviceId + "&brand=android", login, password);

        } catch (Exception e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }

        return null;
    }

    public HttpResponse acknowledgeMedicalData(String deviceId, String[] medicalRecordIds) {

        try {
            String body = this.jsonMapper.writeValueAsString(medicalRecordIds);
            return this.restTemplate.post("registrars/medical-records/acknowledge?device-uuid=" + deviceId, body);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Builds an {@link AmiBaseDTO}.
     *
     * @param destinationRegistrarId the UUID of the destination registrar
     * @param tamiId the identifier of the TAMI
     * @param value the value of the AMI
     * @param sourceDeviceId the UUID of the source device
     * @return the built {@link AmiBaseDTO}
     */
    private AmiBaseDTO buildAmiBaseDTO(String destinationRegistrarId,
            String tamiId, String value, String sourceDeviceId) {

        AmiBaseDTO amiBaseDTO = new AmiBaseDTO();
        amiBaseDTO.setAmiContainerId(destinationRegistrarId);
        amiBaseDTO.setQualifiers(new HashSet<AmiQualDTO>());
        //amiBaseDTO.setUuidMulti("");
        //amiBaseDTO.setRegistrarCreatorId("");
        amiBaseDTO.setTamiId(tamiId);
        amiBaseDTO.setTamiVersion("1");
        amiBaseDTO.setValue(value);
        amiBaseDTO.setCreationDate(new Date());
        amiBaseDTO.setCreatorId(sourceDeviceId);

        return amiBaseDTO;
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
    private AmiContainerDTO buildAmiContainerDTO(
            HashSet<AmiBaseDTO> amiBaseDTOs, AndamanUserDTO destinationRegistrar,
            HashMap<String, String> contextMap) {

        AmiContainerDTO amiContainerDTO = new AmiContainerDTO();
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

    /**
     * Builds a {@link biz.manex.andaman7.injector.dto.RegistrarSyncContentDTO}.
     *
     * @param sourceRegistrarId the UUID of the source registrar
     * @param sourceDeviceId the UUID of the source device
     * @param destinationRegistrars the list of destination registrar's UUIDs
     * @param amiContainerDTOs the list of AmiContainerDTOs
     * @return the built RegistrarSyncContentDTO
     */
    private RegistrarSyncContentDTO buildRegistrarSyncContentDTO(
            String sourceRegistrarId, String sourceDeviceId,
            String[] destinationRegistrars, AmiContainerDTO[] amiContainerDTOs)
    {

        RegistrarSyncContentDTO syncContentDTO = new RegistrarSyncContentDTO();
        syncContentDTO.setSourceDeviceId(sourceDeviceId);
        syncContentDTO.setSourceRegistrarId(sourceRegistrarId);
        syncContentDTO.setDestinationRegistrars(
                Arrays.asList(destinationRegistrars));
        syncContentDTO.setAmiContainerDTOs(Arrays.asList(amiContainerDTOs));
        //syncContentDTO.setFileUuidToFileContent(new HashMap<String, String>());
        //syncContentDTO.setFileUuidToFileContentString("");
        //syncContentDTO.setLastUploadDateFromDevice();
        syncContentDTO.setCreationDate(new Date());
        syncContentDTO.setCreatorId(sourceDeviceId);
        syncContentDTO.setUuid(UUID.randomUUID().toString());

        return syncContentDTO;
    }
}
