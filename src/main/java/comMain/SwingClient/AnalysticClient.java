package comMain.SwingClient;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import comMain.entities.BookEntity;
import comMain.entities.ReadersEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;


public abstract class  AnalysticClient {


    private static RestTemplate restTemplate;
    public static String MonthlyResrvesAmount() throws JsonProcessingException {
        restTemplate = new RestTemplate();
        String url = "https://localhost:8080/reserve/MonthlyResrvesAmount";
        URI uri = UriComponentsBuilder.fromUriString(url).build().toUri();

        String response = restTemplate.getForObject(uri, String.class);

// Use Jackson library to deserialize the JSON string into a list of MyTable objects
        ObjectMapper objectMapper = new ObjectMapper();
        List<Object[]> myTableList = objectMapper.readValue(response, new TypeReference<List<Object[]>>(){});


        return myTableList.get(0)[0].toString();
    }

    public static String todayResrvesAmount() throws JsonProcessingException {
        restTemplate = new RestTemplate();
        String url = "https://localhost:8080/reserve/todayResrvesAmount";
        URI uri = UriComponentsBuilder.fromUriString(url).build().toUri();

        String response = restTemplate.getForObject(uri, String.class);

// Use Jackson library to deserialize the JSON string into a list of MyTable objects
        ObjectMapper objectMapper = new ObjectMapper();
        List<Object[]> myTableList = objectMapper.readValue(response, new TypeReference<List<Object[]>>(){});


        return myTableList.get(0)[0].toString();
    }

    public static List<Object[]> displayReservesByDays() throws JsonProcessingException {
        restTemplate = new RestTemplate();
        String url = "https://localhost:8080/reserve/displayReservesByDays";
        URI uri = UriComponentsBuilder.fromUriString(url).build().toUri();

        String response = restTemplate.getForObject(uri, String.class);

// Use Jackson library to deserialize the JSON string into a list of MyTable objects
        ObjectMapper objectMapper = new ObjectMapper();
        List<Object[]> myTableList = objectMapper.readValue(response, new TypeReference<List<Object[]>>(){});


        return myTableList;
    }

    public static List<Object[]> displayReservesByHours() throws JsonProcessingException {
        restTemplate = new RestTemplate();
        String url = "https://localhost:8080/reserve/displayReservesByHours";
        URI uri = UriComponentsBuilder.fromUriString(url).build().toUri();


        String response = restTemplate.getForObject(uri, String.class);

// Use Jackson library to deserialize the JSON string into a list of MyTable objects
        ObjectMapper objectMapper = new ObjectMapper();
        List<Object[]> myTableList = objectMapper.readValue(response, new TypeReference<List<Object[]>>(){});


        return myTableList;
    }

    public static String MostReservedBook() throws JsonProcessingException {
        restTemplate = new RestTemplate();
        String url = "https://localhost:8080/book/MostReservedBook";
        URI uri = UriComponentsBuilder.fromUriString(url).build().toUri();

        String response = restTemplate.getForObject(uri, String.class);

// Use Jackson library to deserialize the JSON string into a list of MyTable objects
        ObjectMapper objectMapper = new ObjectMapper();
        List<Object[]> myTableList = objectMapper.readValue(response, new TypeReference<List<Object[]>>(){});

        Integer  OriginalId = (Integer) myTableList.get(0)[0];

        url = "https://localhost:8080/book/" + OriginalId;
        uri = UriComponentsBuilder.fromUriString(url).build().toUri();

        BookEntity book = restTemplate.getForObject(uri, BookEntity.class);


        return book.getTitle();
    }


}
