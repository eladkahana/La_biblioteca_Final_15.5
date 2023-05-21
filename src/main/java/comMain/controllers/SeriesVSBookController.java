package comMain.controllers;

import comMain.entities.SeriesVsBookEntity;
import comMain.services.SeriesVSBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

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


    @PutMapping("/setBookToSeries")
    public void setBookToSeries(@RequestParam String BookSeries, @RequestParam String ISBN,@RequestParam int BookIndexInSeries){
        seriesVSBookService.setBookToSeries(BookSeries,ISBN,BookIndexInSeries);
    };



    @DeleteMapping ("/deleteBookFromSeries")
    public void deleteBookFromSeries(@RequestParam int bookID){
        seriesVSBookService.deleteBookFromSeries(bookID);
    };

    @GetMapping("/getSeriesByBook")
    public List<Object[]> getSeriesByBook(@RequestParam int bookID){
        return seriesVSBookService.getSeriesByBook(bookID);
    };


}
