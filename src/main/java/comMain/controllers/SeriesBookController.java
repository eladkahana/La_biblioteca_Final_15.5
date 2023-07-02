package comMain.controllers;

import comMain.entities.AudienceEntity;
import comMain.entities.SeriesBookEntity;
import comMain.services.SeriesBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
@CrossOrigin
@Validated
@RestController
@RequestMapping("/seriesBook")
public class SeriesBookController {

    @Autowired
    private SeriesBookService seriesBookService;

    @PostMapping
    public String save(@Valid @RequestBody SeriesBookEntity vO) {
        return seriesBookService.save(vO).toString();
    }

    @DeleteMapping("/{id}")
    public void delete(@Valid @NotNull @PathVariable("id") Integer id) {
        seriesBookService.delete(id);
    }

    @PutMapping("/{id}")
    public void update(@Valid @NotNull @PathVariable("id") Integer id,
                       @Valid @RequestBody SeriesBookEntity vO) {
        seriesBookService.update(id, vO);
    }

    @GetMapping("/{id}")
    public SeriesBookEntity getById(@Valid @NotNull @PathVariable("id") Integer id) {
        return seriesBookService.getById(id);
    }


    @GetMapping
    public List<SeriesBookEntity> getAllSeries(){
        return seriesBookService.getAllSeries();
    }


}
