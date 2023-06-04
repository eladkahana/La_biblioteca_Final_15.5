package comMain.SwingClient;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public abstract class BookManagerClient {
    public static int createCopy(int bookID) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://localhost:8080/book/createCopy";

        MultiValueMap<String, Object> parameters = new LinkedMultiValueMap<>();
        parameters.add("bookID", bookID);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(parameters, headers);

        ResponseEntity<List<Object[]>> response = restTemplate.exchange(url, HttpMethod.POST, requestEntity, new ParameterizedTypeReference<List<Object[]>>() {});
        int newID = (int) response.getBody().get(0)[0];

        return newID;
    }


    public static void deleteCopy(int copyID) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://localhost:8080/book/DeleteBook";

        MultiValueMap<String, Object> parameters = new LinkedMultiValueMap<>();
        parameters.add("copyID", copyID);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(parameters, headers);

        restTemplate.exchange(url, HttpMethod.PUT, requestEntity, new ParameterizedTypeReference<List<Object[]>>() {});

    }





}
