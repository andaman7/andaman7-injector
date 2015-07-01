package biz.manex.andaman7.injector.webservice;

import biz.manex.andaman7.injector.dtos.A7ItemDTO;
import biz.manex.andaman7.injector.dtos.A7ItemTypeEnum;
import biz.manex.andaman7.injector.exceptions.AndamanException;
import biz.manex.andaman7.injector.exceptions.AuthenticationException;
import biz.manex.andaman7.injector.models.AMI;
import biz.manex.andaman7.injector.models.AMIContainer;
import biz.manex.andaman7.injector.models.Qualifier;
import biz.manex.andaman7.injector.utils.XmlHelper;
import biz.manex.andaman7.server.api.pub.dto.device.DeviceDTO;
import biz.manex.andaman7.server.api.pub.dto.ehr.ResultSyncContentDTO;
import biz.manex.andaman7.server.api.pub.dto.ehr.SyncContentDTO;
import biz.manex.andaman7.server.api.pub.dto.invitations.InvitationDTO;
import biz.manex.andaman7.server.api.pub.dto.translation.TranslationDTO;
import biz.manex.andaman7.server.api.pub.dto.trusteduser.TrustedUserCreationDTO;
import biz.manex.andaman7.server.api.pub.dto.trusteduser.TrustedUserDTO;
import biz.manex.andaman7.server.api.pub.dto.trusteduser.TrustedUserModificationDTO;
import biz.manex.andaman7.server.api.pub.dto.user.AuthenticatedUserDTO;
import biz.manex.andaman7.server.api.pub.dto.user.UserDTO;
import com.fasterxml.jackson.core.type.TypeReference;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.*;
import org.apache.http.util.EntityUtils;

/**
 * The main controller that coordinates the views and performs all the business logic.
 *
 * @author Pierre-Yves Derbaix (pierreyves.derbaix@gmail.com)<br/>
 * Copyright A7 Software (http://www.a7-software.com)<br/>
 * Date: 05/06/2015.
 */
public class AndamanService extends CustomRestService {

    /**
     * Constants
     */
    private static final String EHR_KEY = "amiSet.ehr";
    private static final String NAMESPACE_ENTRY_KEY = "ami.namespaceEntry";
    private static final String NAMESPACE_VALUE_KEY = "qualifier.namespaceValue";

    /**
     * Unique instance of the REST service.
     */
    private static final Map<String, AndamanService> INSTANCES = new HashMap<>();


    /**
     * Builds a context service connection to the server.
     *
     * @param urlServer the URL of the server
     * @param apiKey the API key
     * @param login the login used for the authentication
     * @param password the password used for the authentication
     */
    public AndamanService(String urlServer, String apiKey, String login,
            String password) {
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
    public static AndamanService getInstance(String urlServer,
            String apiKey, String login, String password) {

        AndamanService instance = INSTANCES.get(String.format("%s#%s#%s", urlServer, login, password));

        if(instance == null) {

            instance = new AndamanService(urlServer, apiKey, login, password);
            INSTANCES.put(String.format("%s#%s#%s", urlServer, login, password), instance);
        }

        return instance;
    }

    /**
     * Returns data related to the authenticated registrar.
     *
     * @return the {@link biz.manex.andaman7.server.api.dto.registrar.RegistrarDTO}
     *         of the authenticated registrar
     * @throws java.io.IOException if there was an error with the connection to the server
     */
    public AuthenticatedUserDTO getAuthenticatedUser() throws IOException, AndamanException {

        HttpResponse response = restTemplate.get("users/me?_envelope=false", true);

        if(response.getStatusLine().getStatusCode() == HttpStatus.SC_FORBIDDEN)
            throw new AuthenticationException("Wrong credentials.");

        return jsonMapper.readValue(response.getEntity().getContent(), AuthenticatedUserDTO.class);
    }

    /**
     * Returns an XML file that describes the TAMIs according to the current
     * version of the file.
     *
     * @param currentXmlVersion the current version of the XML file
     * @return the XML document received from the server if a new version exists
     *         or null if no new version exists
     * @throws java.io.IOException if there was an error with the connection to the server
     * @throws org.xml.sax.SAXException if there was an error while parsing the XML document
     * @throws javax.xml.parsers.ParserConfigurationException
     */
    public Document getTamiXml(int currentXmlVersion) throws IOException, SAXException, ParserConfigurationException, AndamanException {
        HttpResponse response = restTemplate.get("dictionaries/tamis?currentVersion=" + currentXmlVersion, true);

        if(response.getStatusLine().getStatusCode() == HttpStatus.SC_FORBIDDEN)
            throw new AuthenticationException("Wrong credentials.");

        if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK)
            return XmlHelper.getDocument(response.getEntity().getContent());
        else
            return null;
    }

    /**
     * Returns an XML file that describes the GUI panels layouts according to
     * the current version of the file.
     *
     * @param currentXmlVersion the current version of the XML file
     * @return the XML document received from the server if a new version exists
     *         or null if no new version exists
     * @throws java.io.IOException if there was an error with the connection to the server
     * @throws org.xml.sax.SAXException if there was an error while parsing the XML document
     * @throws javax.xml.parsers.ParserConfigurationException
     */
    public Document getGuiXml(int currentXmlVersion) throws IOException, SAXException, ParserConfigurationException, AndamanException {
        HttpResponse response = restTemplate.get("dictionaries/gui?currentVersion=" + currentXmlVersion, true);

        if(response.getStatusLine().getStatusCode() == HttpStatus.SC_FORBIDDEN)
            throw new AuthenticationException("Wrong credentials.");

        if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK)
            return XmlHelper.getDocument(response.getEntity().getContent());
        else
            return null;
    }

    /**
     * Returns the {@link biz.manex.andaman7.server.api.dto.device.DeviceDTO} of
     * the logged registrar.
     *
     * @return a list of the devices of the logged registrar
     * @throws java.io.IOException if there was an error with the connection to the server
     */
    public List<DeviceDTO> getDevices() throws IOException, AndamanException {

        HttpResponse response = restTemplate.get("users/me/devices?_envelope=false", true);

        if(response.getStatusLine().getStatusCode() == HttpStatus.SC_FORBIDDEN)
            throw new AuthenticationException("Wrong credentials.");

        return jsonMapper.readValue(response.getEntity().getContent(), new TypeReference<List<DeviceDTO>>() {});
    }

    /**
     * Searches users based on a given keyword.
     *
     * @param keyword the keyword used to filter users
     * @return the list of {@link biz.manex.andaman7.server.api.dto.registrar.AndamanUserDTO}
     *         found based on the keyword
     * @throws java.io.IOException if there was an error with the connection to the server
     */
    public List<UserDTO> searchUsers(String keyword) throws IOException, AndamanException {

        List<UserDTO> users = new ArrayList<>();
        users.addAll(getUsersFromSearch("users?_envelope=false&administrative.firstName="+ keyword));
        users.addAll(getUsersFromSearch("users?_envelope=false&administrative.lastName="+ keyword));
        
        return users;
    }
    
    private List<UserDTO> getUsersFromSearch(String searchUri) throws IOException, AndamanException {
        HttpResponse firstNameResponse = restTemplate.get(searchUri, true);

        if(firstNameResponse.getStatusLine().getStatusCode() == HttpStatus.SC_FORBIDDEN)
            throw new AuthenticationException("Wrong credentials.");

        return jsonMapper.readValue(firstNameResponse.getEntity().getContent(), new TypeReference<List<UserDTO>>() {});
    }
    
    /**
     * Sends a community request to other registrars.
     *
     * @return the list of {@link biz.manex.andaman7.server.api.dto.registrar.RegistrarDTO}s
     * of the new community members
     * @throws java.io.IOException
     */
    public List<TrustedUserDTO> sendCommunityRequest(TrustedUserCreationDTO trustedUserDTO) throws IOException, AndamanException {

        String body = jsonMapper.writeValueAsString(trustedUserDTO);
        HttpResponse response = restTemplate.post("users/me/trusted-users?_envelope=false", body, true);

        return jsonMapper.readValue(response.getEntity().getContent(), new TypeReference<List<TrustedUserDTO>>(){});
    }

    /**
     * Returns all received invitations.
     *
     * @return the received invitations
     * @throws IOException if there was an error with the connection to the server
     */
    public List<InvitationDTO> getInvitations() throws IOException, AndamanException {

        HttpResponse response = restTemplate.get("users/me/trusted-users/invitations?_envelope=false", true);

        if(response.getStatusLine().getStatusCode() == HttpStatus.SC_FORBIDDEN)
            throw new AuthenticationException("Wrong credentials.");

        return jsonMapper.readValue(response.getEntity().getContent(), new TypeReference<List<InvitationDTO>>() {});
    }

    /**
     * Sets the acceptance level of an invitation.
     *
     * @param otherRegistrarUuid the UUID of the other registrar
     * @param trustedUser the acceptance level of the user
     * @throws IOException if there was an error with the connection to the server
     */
    public void setAcceptance(String otherRegistrarUuid, TrustedUserModificationDTO trustedUser) throws IOException, AndamanException {

        String body = jsonMapper.writeValueAsString(trustedUser);
        HttpResponse response = restTemplate.put(String.format("users/me/trusted-users/%s?_envelope=false", otherRegistrarUuid), body, true);

        if(response.getStatusLine().getStatusCode() == HttpStatus.SC_FORBIDDEN)
            throw new AuthenticationException("Wrong credentials.");
    }

    /**
     * Returns the community members of the logged registrar.
     *
     * @return the community members of the logged registrar
     * @throws java.io.IOException if there was an error with the connection to the server
     */
    public List<UserDTO> getTrustedUsers() throws IOException, AndamanException {

        HttpResponse response = restTemplate.get("users/me/trusted-users?_envelope=false", true);

        if(response.getStatusLine().getStatusCode() == HttpStatus.SC_FORBIDDEN)
            throw new AuthenticationException("Wrong credentials.");

        List<TrustedUserDTO> trustedUsers = jsonMapper.readValue(response.getEntity().getContent(), new TypeReference<List<TrustedUserDTO>>() {});
        List<UserDTO> userDTOs = new ArrayList<>();

        for (TrustedUserDTO trustedUser : trustedUsers)
            userDTOs.add(trustedUser.getUser());

        return userDTOs;
    }
    
    /**
     * Returns the translation strings used to translate the TAMIs and the GUI strings.
     *
     * @return the translation strings used to translate the TAMIs and the GUI strings
     * @throws java.io.IOException if there was an error with the connection to the server
     */
    public List<TranslationDTO> getTranslations() throws IOException, AndamanException {

        HttpResponse response = restTemplate.get("translations?_envelope=false");

        if(response.getStatusLine().getStatusCode() == HttpStatus.SC_FORBIDDEN)
            throw new AuthenticationException("Wrong credentials.");

        return jsonMapper.readValue(response.getEntity().getContent(), new TypeReference<List<TranslationDTO>>() {});
    }
    
    /**
     * Injects some AMIs into the EHR of a registrar.
     *
     * @param sourceUser
     * @param tamiVersion the version of the XML file describing the TAMIs
     * @throws java.io.IOException
     */
    public void sendAmiBasesToRegistrar(AuthenticatedUserDTO sourceUser, AMIContainer amiContainer, int tamiVersion) throws IOException, AndamanException {

        List<DeviceDTO> devices = getDevices();
        
        if(devices.isEmpty())
            return;
        
        String sourceDeviceId = devices.get(0).getId();
        String sourceUserId = sourceUser.getId();

        Map<String, String> contextMap = amiContainer.getContextMap();
        Map<String, AMI> amis = amiContainer.getAmis();
        
        List<A7ItemDTO> a7Items = new ArrayList<>();

        // Add an A7Item for the EHR itself
        A7ItemDTO ehr = new A7ItemDTO(amiContainer.getUuid());
        ehr.setCreatorRegistrarId(sourceUserId);
        ehr.setCreationDate(new Date());
        ehr.setType(A7ItemTypeEnum.AmiSet);
        ehr.setKey(EHR_KEY);
        ehr.setVersion(tamiVersion);

        a7Items.add(ehr);

        // Process the namespace entries
        for(Map.Entry<String, String> contextEntry : contextMap.entrySet()) {

            A7ItemDTO namespaceEntry = new A7ItemDTO(UUID.randomUUID().toString());
            namespaceEntry.setCreatorRegistrarId(sourceUserId);
            namespaceEntry.setCreationDate(new Date());
            namespaceEntry.setType(A7ItemTypeEnum.AMI);
            namespaceEntry.setKey(NAMESPACE_ENTRY_KEY);
            namespaceEntry.setValue(contextEntry.getKey());
            namespaceEntry.setVersion(tamiVersion);
            namespaceEntry.setParentId(amiContainer.getUuid());

            A7ItemDTO namespaceValue = new A7ItemDTO(UUID.randomUUID().toString());
            namespaceValue.setCreatorRegistrarId(sourceUserId);
            namespaceValue.setCreationDate(new Date());
            namespaceValue.setType(A7ItemTypeEnum.Qualifier);
            namespaceValue.setKey(NAMESPACE_VALUE_KEY);
            namespaceValue.setValue(contextEntry.getValue());
            namespaceValue.setVersion(tamiVersion);
            namespaceValue.setParentId(namespaceEntry.getId());

            a7Items.add(namespaceEntry);
            a7Items.add(namespaceValue);
        }

        // Process the AMIs
        for (AMI ami : amis.values()) {

            A7ItemDTO amiItem = new A7ItemDTO(ami.getId());
            amiItem.setCreatorRegistrarId(sourceUserId);
            amiItem.setCreationDate(new Date());
            amiItem.setType(A7ItemTypeEnum.AMI);
            amiItem.setKey(ami.getType().getKey());
            amiItem.setValue(ami.getValue());
            amiItem.setVersion(tamiVersion);
            amiItem.setParentId(amiContainer.getUuid());

            a7Items.add(amiItem);

            // Process the qualifiers
            for(Qualifier qualifier : ami.getQualifiers()) {

                A7ItemDTO qualifierItem = new A7ItemDTO(qualifier.getId());
                qualifierItem.setCreatorRegistrarId(sourceUserId);
                qualifierItem.setCreationDate(new Date());
                qualifierItem.setType(A7ItemTypeEnum.Qualifier);
                qualifierItem.setKey(qualifier.getType().getKey());
                qualifierItem.setValue(qualifier.getValue());
                qualifierItem.setVersion(tamiVersion);
                qualifierItem.setParentId(amiItem.getId());

                a7Items.add(qualifierItem);
            }
        }
        
        String medicalRecords = jsonMapper.writeValueAsString(a7Items);
        SyncContentDTO syncContentDTO = new SyncContentDTO();
        syncContentDTO.setSourceDeviceId(sourceDeviceId);
        syncContentDTO.setSourceUserId(sourceUser.getId());
        syncContentDTO.setA7Items(medicalRecords);
        
        // Send the request to the server
        String body = jsonMapper.writeValueAsString(syncContentDTO);
        
        System.out.println(medicalRecords);
        System.out.println();
        System.out.println(body);
        
        for(String destinationRegistrarId : amiContainer.getDestinationRegistrarsIds())
            restTemplate.post(String.format("users/%s/a7-items", destinationRegistrarId), body, true);
    }

    /**
     * Returns the medical data in queue for the specified device.
     *
     * @param userId 
     * @param deviceId the UUID of the device to retrieve medical data from
     * @return the HTTP response to the request
     * @throws java.io.IOException
     */
    public List<ResultSyncContentDTO> getMedicalDataInQueue(String userId, String deviceId) throws IOException, AndamanException {

        try {
            HttpResponse response = restTemplate.get(String.format("users/%s/a7-items?_envelope=false&_deviceId=%s", userId, deviceId), true);
            return jsonMapper.readValue(response.getEntity().getContent(), new TypeReference<List<ResultSyncContentDTO>>() {});

        } catch(NullPointerException e) {
            return new ArrayList<>();
        }
    }

    /**
     * Acknowledges some medical data to allow the server to delete them.
     *
     * @param medicalRecordIds a list of UUIDs of the retrieved medical data
     * @return the HTTP response to the request
     * @throws java.io.IOException
     */
    public HttpResponse acknowledgeMedicalData(String[] medicalRecordIds) throws IOException, AndamanException {

        String body = jsonMapper.writeValueAsString(medicalRecordIds);
        return restTemplate.put("users/%s/a7-items?_envelope=false", body, true);
    }
}
