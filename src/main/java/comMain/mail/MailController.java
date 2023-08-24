package comMain.mail;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import comMain.entities.ReadersEntity;
import comMain.entities.RequestsEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.List;
@CrossOrigin
@RestController
@RequestMapping("/emails")
public class MailController {


    @Autowired
    private MailTypes mailTypes;


    @PutMapping("/changeDays")
    public void changeDays( String newDays) {
        mailTypes.changeDays(newDays);
    }


    @PutMapping("/reminder")
    public void reminder() throws JsonProcessingException {
        mailTypes.reminder();
    }


    @PutMapping("/reaction")
    public void reaction(Integer requestID, String answer) {
        mailTypes.reaction(requestID,answer);
    }


}
