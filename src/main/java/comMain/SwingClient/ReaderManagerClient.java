package comMain.SwingClient;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public class ReaderManagerClient {

    public static void changePassword(int uID,
                                      String Password) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://localhost:8080/readers/LogIn/AddPassword";

        MultiValueMap<String, Object> parameters = new LinkedMultiValueMap<>();
        parameters.add("uID", uID);
        parameters.add("Password", Password);


        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(parameters, headers);

        ResponseEntity<List<Object[]>> responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, new ParameterizedTypeReference<List<Object[]>>() {
        });



    }
}
