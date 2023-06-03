package comMain.mail;



//import com.DTOs.ReadersDTO;
//import com.controllers.ReadersController;
//import com.entities.ReadersEntity;
//import com.repositories.ReadersRepository;
//import com.services.ReadersService;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import comMain.entities.ReadersEntity;
import comMain.entities.RequestsEntity;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/emails")
public class MailTypes {

    private String DaysInString = "2,-2,-10";

    /**
     * function that update the days of reminding
     *
     * @param newDays - The days in relation to the book return date
     *                when it is necessary to send a reminder
     */
    @PutMapping("/changeDays")
    public void changeDays(@RequestParam String newDays) {


        this.DaysInString = newDays;


    }

    /**
     * send email of reminding to return the book
     *
     * @throws JsonProcessingException
     */
    @PutMapping("/reminder")
    public void reminder() throws  JsonProcessingException {


        String url = "http://localhost:8080/reserve/ReserveDaysLeft";
        RestTemplate restTemplate = new RestTemplate();
        URI uri = UriComponentsBuilder.fromUriString(url).queryParam("arrStr", this.DaysInString).build().toUri();


        String response = restTemplate.getForObject(uri, String.class);

// Use Jackson library to deserialize the JSON string into a list of MyTable objects
        ObjectMapper objectMapper = new ObjectMapper();
        List<Object[]> myTableList = objectMapper.readValue(response, new TypeReference<List<Object[]>>() {
        });





        // write the content of the email

        for (Object[] item : myTableList) {
            System.out.println(item[0]);
            StringBuilder content = new StringBuilder();
            content.append("שלום ").append(item[0]).append(".<br>");
            if ((int) item[2] >= 0) {
                content.append("רצינו להזכיר לך שבעוד ")
                        .append(item[2])
                        .append(" ימים תצטרך להחזיר את הספר '")
                        .append(item[3])
                        .append("'");
            } else {
                content.append("רצינו להזכיר לך שאתה צריך להחזיר את הספר '")
                        .append(item[3])
                        .append("'");
            }



             url = "http://localhost:8080/book/SuggestBooks";
             restTemplate = new RestTemplate();
             uri = UriComponentsBuilder.fromUriString(url).queryParam("readerID", item[5]).build().toUri();


             response = restTemplate.getForObject(uri, String.class);

             objectMapper = new ObjectMapper();
            List<Object[]> bookSuggest = objectMapper.readValue(response, new TypeReference<List<Object[]>>() {
            });



            if(bookSuggest != null) {

                content.append("<br><br><br>בנוסף אנו ממליצים לך להשאיל את הספר: <br>");
                content.append("'" + bookSuggest.get(0)[4] + "'");
                content.append("<br>חושבים שתהנה לקרוא אותו :) <br>");
                content.append("<br> תודה רבה ויום טוב!<br>");
                content.append("<br> ספריית - La Biblioteca<br>");
            }

            Email email = new Email((String) item[1], "תזכורת להחזרת ספר", content.toString());
            email.setImage(((String) item[4]).getBytes(StandardCharsets.UTF_8));

            email.sendEmail();
        }


    }


    /**
     * send email of responding a contact from readers
     *
     * @param requestID - the reader who sent a request
     * @param answer  - the content of the reaction
     */
    @PutMapping("/reaction")
    public void reaction(@RequestParam("requestID") Integer requestID, @RequestParam("answer") String answer) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/requests/" + requestID;
        URI uri = UriComponentsBuilder.fromUriString(url).build().toUri();
        ResponseEntity<RequestsEntity> requestResult = restTemplate.exchange(uri, HttpMethod.GET, null, RequestsEntity.class);
        RequestsEntity request = requestResult.getBody();


         url = "http://localhost:8080/readers/" + request.getReaderId();
         uri = UriComponentsBuilder.fromUriString(url).build().toUri();
        ResponseEntity<ReadersEntity> readerResult = restTemplate.exchange(uri, HttpMethod.GET, null, ReadersEntity.class);
        ReadersEntity reader = readerResult.getBody();

        url = "http://localhost:8080/respones/addResponse";

        MultiValueMap<String, Object> parameters = new LinkedMultiValueMap<>();
        parameters.add("requestID", request.getId());
        parameters.add("content", answer);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(parameters, headers);

        Email email = new Email(reader.getEmail(), "מענה לפנייתך - מערכת ספרייה", answer);
        if(email.sendEmail()) {

            restTemplate.exchange(url, HttpMethod.PUT, requestEntity, new ParameterizedTypeReference<List<Object[]>>() {
            });
        }

    }
}
