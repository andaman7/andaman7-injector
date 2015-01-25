package biz.manex.andaman7.injector;

import biz.manex.andaman7.injector.dto.RegistrarDTO;
import biz.manex.andaman7.injector.dto.RegistrarSyncContentDTO;
import biz.manex.andaman7.injector.webservice.REST.AndamanContextService;
import biz.manex.andaman7.injector.webservice.REST.AndamanEhrService;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.codehaus.jackson.map.ObjectMapper;

import java.util.HashMap;


/**
 * @author Pierre-Yves Derbaix (pierreyves.derbaix@gmail.com)
 * Copyright A7 Software (http://www.manex.biz)
 * Date: 19/01/2015.
 */
public class Main {

    // Server URLs
    public static String ENDPOINT = "http://localhost:8080/api/";
    public static String CONTEXT_SERVICE_URL = ENDPOINT + "context/v1/";
    public static String EHR_SERVICE_URL = ENDPOINT + "ehr/v1/";

    // Authentication data
    public static String API_KEY = "2C949434-20FF-4636-BA96-B7C0CAD42612";
    public static String LAB_LOGIN = "pyderbaix@student.ulg.ac.be";
    public static String LAB_PASSWORD = "aaaaaa";
    public static String PATIENT_LOGIN = "pierreyves.derbaix@gmail.com";
    public static String PATIENT_PASSWORD = "aaaaaa";

    // UUIDs of registrars and devices
    public static String LAB_ID = "7e698f06-f58a-446e-9488-f9824050c2e7";
    public static String LAB_DEVICE_ID = "B4EE0D40-A35B-4DD6-AADC-4C3029219590";
    public static String PATIENT_ID = "9eeec626-5b24-4b55-b588-4ec05c39c96a";
    public static String PATIENT_DEVICE_ID = "B08286E0-A9FB-4D85-A673-B6A29F61ED97";

    // AMIs
    public static HashMap<String, String> AMIS;
    static {
        AMIS = new HashMap<String, String>();
        AMIS.put("vaccineName", "Influenza");
    }

    public static void main(String[] args) {

        try {
            // Initialization of the REST services
            AndamanContextService contextService =
                    new AndamanContextService(CONTEXT_SERVICE_URL, API_KEY);
            AndamanEhrService ehrService =
                    new AndamanEhrService(EHR_SERVICE_URL, API_KEY);

            // Sending invitation to the patient
            String[] newCommunityMembers = new String[] { PATIENT_ID };
            RegistrarDTO[] registrars = contextService.sendCommunityRequest(
                    LAB_DEVICE_ID, newCommunityMembers, LAB_LOGIN,
                    LAB_PASSWORD);

            for(RegistrarDTO registrar : registrars) {
                System.out.println("Name : " + registrar.getFirstName() + " " +
                        registrar.getLastName());

                // Send medical data to the server
                HttpResponse response =
                        ehrService.sendAmiBasesToRegistrar(LAB_DEVICE_ID,
                                LAB_ID, registrar, registrar.getUuid(), AMIS,
                                LAB_LOGIN, LAB_PASSWORD);

                System.out.println("Status code : " + response.getStatusLine
                        ().getStatusCode());

                // Simulate patient acceptation and retrieval of the data
                contextService.setCommunityAcceptance(LAB_ID, true,
                        PATIENT_LOGIN, PATIENT_PASSWORD);
                response = ehrService.getMedicalRecordsInQueue(
                        PATIENT_DEVICE_ID, PATIENT_LOGIN, PATIENT_PASSWORD);

                ObjectMapper mapper = new ObjectMapper();
                RegistrarSyncContentDTO[] registrarSyncContentDTOs =
                        mapper.readValue(
                                EntityUtils.toString(response.getEntity()),
                                RegistrarSyncContentDTO[].class);
                System.out.println("Retrieved medical data : \n" +
                        mapper.writerWithDefaultPrettyPrinter().
                                writeValueAsString(registrarSyncContentDTOs));
            }

        } catch (Exception e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
    }
}
