package comMain.controllers;

import comMain.entities.ReadersEntity;
import comMain.services.ReadersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Validated
@RestController
@RequestMapping("/readers")
public class ReadersController {

    @Autowired
    private ReadersService readersService;

    @PostMapping
    public String save(@Valid @RequestBody ReadersEntity vO) {
        return readersService.save(vO).toString();
    }

    @DeleteMapping("/{id}")
    public void delete(@Valid @NotNull @PathVariable("id") Integer id) {
        readersService.delete(id);
    }

    @PutMapping("/{id}")
    public void update(@Valid @NotNull @PathVariable("id") Integer id,
                       @Valid @RequestBody ReadersEntity vO) {
        readersService.update(id, vO);
    }

    @GetMapping("/{id}")
    public ReadersEntity getById(@Valid @NotNull @PathVariable("id") Integer id) {
        return readersService.getById(id);
    }

    @GetMapping
    public List<ReadersEntity> getAllReaders(){
        return readersService.getAllReaders();
    }


}
