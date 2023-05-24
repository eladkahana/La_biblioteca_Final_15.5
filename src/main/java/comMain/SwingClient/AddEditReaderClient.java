package comMain.SwingClient;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public class AddEditReaderClient {

    public static void EditRedaer(String IDno,
                                String adress,
                                String phoneNo,
                                String firstName,
                                String lastName,
                                java.sql.Date birthDate,
                                String gender,
                                String Email,
                                int ID) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/readers/EditReader";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        MultiValueMap<String, Object> parameters = new LinkedMultiValueMap<>();
        parameters.add("IDno", IDno);
        parameters.add("adress", adress);
        parameters.add("phoneNo", phoneNo);
        parameters.add("firstName", firstName);
        parameters.add("lastName", lastName);
        parameters.add("birthDate", birthDate.toString());
        parameters.add("gender", gender);
        parameters.add("Email", Email);
        parameters.add("ID", ID);

        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(parameters, headers);

        restTemplate.exchange(url, HttpMethod.PUT, requestEntity, new ParameterizedTypeReference<List<Object[]>>() {});
    }


    public static int addReader(String ID,
                                String adress,
                                String phoneNo,
                                String firstName,
                                String lastName,
                                java.sql.Date birthDate,
                                String gender,
                                String Email) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/readers/addReader";

        MultiValueMap<String, Object> parameters = new LinkedMultiValueMap<>();
        parameters.add("ID", ID);
        parameters.add("adress", adress);
        parameters.add("phoneNo", phoneNo);
        parameters.add("firstName", firstName);
        parameters.add("lastName", lastName);
        parameters.add("birthDate", birthDate.toString());
        parameters.add("gender", gender);
        parameters.add("Email", Email);


        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(parameters, headers);

        ResponseEntity<List<Object[]>> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, new ParameterizedTypeReference<List<Object[]>>() {
        });
        List<Object[]> response = responseEntity.getBody();

        int newID = (int) response.get(0)[0];

        return newID;
    }
}
