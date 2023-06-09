package comMain.SwingClient;

import comMain.entities.RequestsEntity;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public abstract class ReservationClient {
    public static void addReserve(String readerIDno,Integer BookID, Date dueTo){
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://localhost:8080/reserve/AddReserve";


        MultiValueMap<String, Object> parameters = new LinkedMultiValueMap<>();
        parameters.add("readerIDno", readerIDno);
        parameters.add("BookID", BookID);
        parameters.add("dueTo", dueTo.toString());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(parameters, headers);

        restTemplate.exchange(url, HttpMethod.PUT, requestEntity, new ParameterizedTypeReference<List<Object[]>>() {});

    }


    public static void addExtension(int readerIDno,Date newDate){
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://localhost:8080/reserve/AddExtension";


        MultiValueMap<String, Object> parameters = new LinkedMultiValueMap<>();
        parameters.add("oldReserveID", readerIDno);
        parameters.add("newDate", newDate.toString());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(parameters, headers);

        restTemplate.exchange(url, HttpMethod.PUT, requestEntity, new ParameterizedTypeReference<List<Object[]>>() {});

    }

    public static void addRank(String IDno, Integer copyID, Integer rank){
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://localhost:8080/reserve/AddRank";


        MultiValueMap<String, Object> parameters = new LinkedMultiValueMap<>();
        parameters.add("IDno", IDno);
        parameters.add("copyID", copyID);
        parameters.add("rank", rank);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(parameters, headers);

        restTemplate.exchange(url, HttpMethod.PUT, requestEntity, new ParameterizedTypeReference<List<Object[]>>() {});

    }


    public static void addReturnBook(Integer copyID, String readerIDno){
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://localhost:8080/reserve/AddReturnBook";


        MultiValueMap<String, Object> parameters = new LinkedMultiValueMap<>();
        parameters.add("copyID", copyID);
        parameters.add("readerIDno", readerIDno);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(parameters, headers);

        restTemplate.exchange(url, HttpMethod.PUT, requestEntity, new ParameterizedTypeReference<List<Object[]>>() {});

    }


    public static void reminder() {
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://localhost:8080/emails/reminder";
        restTemplate.exchange(url, HttpMethod.PUT, null, new ParameterizedTypeReference<List<Object[]>>() {});
    }


    public static void changeDays(List<String> days){
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://localhost:8080/emails/changeDays";
        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();

        String DaysInString = "";
        for (String day : days) {
            DaysInString += day;
            DaysInString += ",";
        }

        parameters.add("newDays", DaysInString);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(parameters, headers);

        restTemplate.exchange(url, HttpMethod.PUT, requestEntity, new ParameterizedTypeReference<String>() {});
    }

}
