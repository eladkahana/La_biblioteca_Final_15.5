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
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/emails")
public class MailTypes {

    private String DaysInString;

    /**
     * function that update the days of reminding
     *
     * @param newDays - The days in relation to the book return date
     *                when it is necessary to send a reminder
     */
    public void changeDays(List<Object> newDays) {
        this.DaysInString = "";
        for (Object day : newDays) {
            this.DaysInString += day.toString();
            this.DaysInString += ",";
        }


    }

    /**
     * send email of reminding to return the book
     *
     * @throws SQLException
     * @throws JsonProcessingException
     */
    @PostMapping("/reminder")
    public void reminder() throws SQLException, JsonProcessingException {


//        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPAPU");
//        EntityManager em = emf.createEntityManager();
//        StoredProcedureQuery sp = em.createStoredProcedureQuery("dbo.reservedaysleft");
//        sp.registerStoredProcedureParameter("arrStr", String.class, ParameterMode.IN);
//        sp.setParameter("arrStr", this.DaysInString);
//        sp.execute();


        String url = "http://localhost:8080/reserve/ReserveDaysLeft";
        RestTemplate restTemplate = new RestTemplate();
        String daysInString = DaysInString; // Set the value of the query parameter
        URI uri = UriComponentsBuilder.fromUriString(url).queryParam("arrStr", daysInString).build().toUri();


        String response = restTemplate.getForObject(uri, String.class);

// Use Jackson library to deserialize the JSON string into a list of MyTable objects
        ObjectMapper objectMapper = new ObjectMapper();
        List<Object[]> myTableList = objectMapper.readValue(response, new TypeReference<List<Object[]>>() {
        });

        for (Object[] item : myTableList) {
            System.out.println(item[0]);
        }

        // write the content of the email

        for (Object[] item : myTableList) {
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
            content.append("<br> תודה רבה ויום טוב!<br>");
            content.append("<br> ספריית - La Biblioteca<br>");

            Email email = new Email((String) item[1], "תזכורת להחזרת ספר", content.toString());
            email.setImage((byte[]) item[4]);
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
