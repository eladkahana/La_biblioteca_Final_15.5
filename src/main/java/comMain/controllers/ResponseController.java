package comMain.controllers;

import comMain.entities.RequestsEntity;
import comMain.entities.ResponesEntity;
import comMain.services.ResponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@CrossOrigin
@Validated
@RestController
@RequestMapping("/respones")
public class ResponseController {

    @Autowired
    private ResponseService responesService;

    @PostMapping
    public String save(@Valid @RequestBody ResponesEntity vO) {
        return responesService.save(vO).toString();
    }

    @DeleteMapping("/{id}")
    public void delete(@Valid @NotNull @PathVariable("id") Integer id) {
        responesService.delete(id);
    }

    @PutMapping("/{id}")
    public void update(@Valid @NotNull @PathVariable("id") Integer id,
                       @Valid @RequestBody ResponesEntity vO) {
        responesService.update(id, vO);
    }

    @GetMapping("/{id}")
    public ResponesEntity getById(@Valid @NotNull @PathVariable("id") Integer id) {
        return responesService.getById(id);
    }


    @PutMapping("/addResponse")
    public void addResponse(@RequestParam Integer requestID, @RequestParam String content) {
        responesService.addResponse(requestID, content);
    }


    @GetMapping("/getResponse")
    public List<Object[]> getResponse() {
        return responesService.getResponse();
    }


}
