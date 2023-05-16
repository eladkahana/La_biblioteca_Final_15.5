package comMain.controllers;

import comMain.entities.ReadersEntity;
import comMain.services.ReadersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.sql.Date;
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
    public List<ReadersEntity> getAllReaders() {
        return readersService.getAllReaders();
    }

    @PutMapping("/addReader")
    public List<Object[]> addReader(@RequestParam String ID,
                                    @RequestParam String adress,
                                    @RequestParam String phoneNo,
                                    @RequestParam String firstName,
                                    @RequestParam String lastName,
                                    @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") String birthDate,
                                    @RequestParam String gender,
                                    @RequestParam String Email) {



        List<Object[]> result = readersService.addReader(
                ID,
                adress,
                phoneNo,
                firstName,
                lastName,
                birthDate,
                gender,
                Email
        );
        return result;
    }
}
