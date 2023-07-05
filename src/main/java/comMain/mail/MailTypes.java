package comMain.mail;



//import com.DTOs.ReadersDTO;
//import com.controllers.ReadersController;
//import com.entities.ReadersEntity;
//import com.repositories.ReadersRepository;
//import com.services.ReadersService;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import comMain.controllers.BookController;
import comMain.controllers.ReserveController;
import comMain.entities.ReadersEntity;
import comMain.entities.RequestsEntity;
import comMain.repositories.BookRepository;
import comMain.services.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;

import org.springframework.stereotype.Service;
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
@Service
@RequiredArgsConstructor
public class MailTypes {

    private String DaysInString = "2,-2,-10";
    @Autowired
    private final RequestsService requestsService;
    @Autowired
    private final ReserveService reserveService;
    @Autowired
    private final BookService bookService;

    @Autowired
    private final ReadersService readersService;

    @Autowired
    private final ResponseService responseService;

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
    public void reminder() throws  JsonProcessingException {




        List<Object[]> myTableList = reserveService.ReserveDaysLeft(this.DaysInString);



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




            List<Object[]> bookSuggest = bookService.SuggestBooks((int) item[5]);



            if(bookSuggest != null) {

                content.append("<br><br><br>בנוסף אנו ממליצים לך להשאיל את הספר: <br>");
                content.append("'" + bookSuggest.get(0)[0] + "'");
                content.append("<br>חושבים שתהנה לקרוא אותו :) <br>");
                content.append("<br> תודה רבה ויום טוב!<br>");
                content.append("<br> ספריית - La Biblioteca<br>");
            }

            Email email = new Email((String) item[1], "תזכורת להחזרת ספר", content.toString());
            email.setImage((byte[]) (item[4]));

            email.sendEmail();
        }


    }


    /**
     * send email of responding a contact from readers
     *
     * @param requestID - the reader who sent a request
     * @param answer  - the content of the reaction
     */
    public void reaction(@RequestParam("requestID") Integer requestID, @RequestParam("answer") String answer) {



        RequestsEntity request = requestsService.getById(requestID);




        ReadersEntity reader = readersService.getById(request.getReaderId());



        Email email = new Email(reader.getEmail(), "מענה לפנייתך - מערכת ספרייה", answer);
        if(email.sendEmail()) {

            responseService.addResponse(requestID, answer);

        }

    }
}
