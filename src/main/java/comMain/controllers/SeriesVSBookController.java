package comMain.controllers;

import comMain.entities.SeriesVsBookEntity;
import comMain.services.SeriesVSBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Validated
@RestController
@RequestMapping("/seriesVSBook")
public class SeriesVSBookController {

    @Autowired
    private SeriesVSBookService seriesVSBookService;

    @PostMapping
    public String save(@Valid @RequestBody SeriesVsBookEntity vO) {
        return seriesVSBookService.save(vO).toString();
    }

    @DeleteMapping("/{id}")
    public void delete(@Valid @NotNull @PathVariable("id") Integer id) {
        seriesVSBookService.delete(id);
    }

    @PutMapping("/{id}")
    public void update(@Valid @NotNull @PathVariable("id") Integer id,
                       @Valid @RequestBody SeriesVsBookEntity vO) {
        seriesVSBookService.update(id, vO);
    }

    @GetMapping("/{id}")
    public SeriesVsBookEntity getById(@Valid @NotNull @PathVariable("id") Integer id) {
        return seriesVSBookService.getById(id);
    }


}
