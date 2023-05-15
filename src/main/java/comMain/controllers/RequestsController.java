package comMain.controllers;

import comMain.entities.RequestsEntity;
import comMain.services.RequestsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

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


}
