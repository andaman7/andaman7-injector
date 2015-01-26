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

            // Login as laboratory to get registrar and device UUIDs
            RegistrarDTO labRegistrar = contextService.login(LAB_LOGIN,
                    LAB_PASSWORD);
            String labId = labRegistrar.getUuid();

            if(labRegistrar.getDevices().isEmpty()) {
                System.err.println("A device is needed.");
                System.exit(1);
            }

            String labDeviceId = labRegistrar.getDevices().get(0).getUuid();

            // Search for the patient
            RegistrarDTO[] patients = contextService.searchUsers("derbaix",
                    LAB_LOGIN, LAB_PASSWORD);

            if(patients.length == 0) {
                System.err.println("No patients were found.");
                System.exit(1);
            }

            RegistrarDTO patient = patients[0];
            String patientId = patient.getUuid();

            // Send medical data to the server
            HttpResponse response = ehrService.sendAmiBasesToRegistrar(
                    labDeviceId, labId, patient, patientId, AMIS, LAB_LOGIN,
                    LAB_PASSWORD);

            System.out.println("Status code : " + response.getStatusLine().
                    getStatusCode());

            // Simulate retrieval of the data by the patient
            response = ehrService.getMedicalDataInQueue(
                    PATIENT_DEVICE_ID, PATIENT_LOGIN, PATIENT_PASSWORD);

            ObjectMapper mapper = new ObjectMapper();
            RegistrarSyncContentDTO[] registrarSyncContentDTOs =
                    mapper.readValue(
                            EntityUtils.toString(response.getEntity()),
                            RegistrarSyncContentDTO[].class);
            System.out.println("Retrieved medical data : \n" +
                    mapper.writerWithDefaultPrettyPrinter().
                            writeValueAsString(registrarSyncContentDTOs));

        } catch (Exception e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
    }
}
