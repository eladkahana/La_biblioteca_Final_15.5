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



    public static void main(String[] args){

        SpringApplication.run(LaBibliotecaFinalApplication.class, args);
    }

}
