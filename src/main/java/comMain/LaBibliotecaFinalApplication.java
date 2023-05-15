package comMain;

import com.fasterxml.jackson.core.JsonProcessingException;
import comMain.entities.BookEntity;
import comMain.mail.MailTypes;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.awt.print.Printable;
import java.net.URI;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;




@SpringBootApplication
public class LaBibliotecaFinalApplication {



    public static void main(String[] args) throws SQLException, JsonProcessingException {

        SpringApplication.run(LaBibliotecaFinalApplication.class, args);
        MailTypes mt = new MailTypes();

//        String url = "http://localhost:8080/book";
//        RestTemplate restTemplate = new RestTemplate();
//        URI uri = UriComponentsBuilder.fromUriString(url).build().toUri();
//        ParameterizedTypeReference<List<BookEntity>> responseType = new ParameterizedTypeReference<List<BookEntity>>() {};
//        List<BookEntity> response = restTemplate.exchange(uri, HttpMethod.GET, null, responseType).getBody();
//        for (BookEntity book : response) {
//            bookListModel.addElement(book);
//        }
//
//
//
//        List ls = new ArrayList();
//        ls.add(-67);
//        mt.changeDays(ls);
////
//        mt.reminder();




        //mt.reaction(2,"תודה רבה! נשמח שתדרג אותנו בערוצים המובילים בטלגרם בניהם WEBUTNET, בנוסף ");
        //mt.reaction(2,"thanks");
    }

}
