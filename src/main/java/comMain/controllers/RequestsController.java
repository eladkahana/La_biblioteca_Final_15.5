package comMain.controllers;

import comMain.entities.RequestsEntity;
import comMain.services.RequestsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Validated
@RestController
@RequestMapping("/requests")
public class RequestsController {

    @Autowired
    private RequestsService requestsService;

    @PostMapping
    public String save(@Valid @RequestBody RequestsEntity vO) {
        return requestsService.save(vO).toString();
    }

    @DeleteMapping("/{id}")
    public void delete(@Valid @NotNull @PathVariable("id") Integer id) {
        requestsService.delete(id);
    }

    @PutMapping("/{id}")
    public void update(@Valid @NotNull @PathVariable("id") Integer id,
                       @Valid @RequestBody RequestsEntity vO) {
        requestsService.update(id, vO);
    }

    @GetMapping("/{id}")
    public RequestsEntity getById(@Valid @NotNull @PathVariable("id") Integer id) {
        return requestsService.getById(id);
    }

    @GetMapping("/getCheckedRequests")
    public List<RequestsEntity> getCheckedRequests(){
        return requestsService.getCheckedRequests();
    }

    @GetMapping("/getUnCheckedRequests")
    public List<RequestsEntity> getUnCheckedRequests(){
        return requestsService.getUnCheckedRequests();
    }

    @PutMapping("/requestChecked")
    public void requestChecked(@RequestParam int requestID){
        requestsService.requestChecked(requestID);
    }


    @PostMapping(value = "/AddRequest", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addRequest(@RequestParam int readerID, @RequestParam String content, @RequestParam String topic) {

        List<Object[]> request = requestsService.AddRequest(readerID, content, topic);
        HttpHeaders headers = new HttpHeaders();
        headers.setAccessControlAllowOrigin("*"); // Allow requests from any domain
        return ResponseEntity.ok().headers(headers).body(request);
    }





}
