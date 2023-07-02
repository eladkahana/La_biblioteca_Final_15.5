package comMain.controllers;

import comMain.entities.AudienceVsBookEntity;
import comMain.services.AudienceVSBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
@CrossOrigin
@Validated
@RestController
@RequestMapping("/audienceVSBook")
public class AudienceVSBookController {

    @Autowired
    private AudienceVSBookService audienceVSBookService;

    @PostMapping
    public String save(@Valid @RequestBody AudienceVsBookEntity vO) {
        return audienceVSBookService.save(vO).toString();
    }

    @DeleteMapping("/{id}")
    public void delete(@Valid @NotNull @PathVariable("id") Integer id) {
        audienceVSBookService.delete(id);
    }

    @PutMapping("/{id}")
    public void update(@Valid @NotNull @PathVariable("id") Integer id,
                       @Valid @RequestBody AudienceVsBookEntity vO) {
        audienceVSBookService.update(id, vO);
    }

    @GetMapping("/{id}")
    public AudienceVsBookEntity getById(@Valid @NotNull @PathVariable("id") Integer id) {
        return audienceVSBookService.getById(id);
    }



    @PutMapping("/setAudienceToBook")
    public void setAudienceToBook(@RequestParam String team, @RequestParam String ISBN){
        audienceVSBookService.setAudienceToBook(team,ISBN);
    };

    @DeleteMapping("/deleteAudienceFromBook")
    public void deleteAudienceFromBook(@RequestParam int bookID){
        audienceVSBookService.deleteAudienceFromBook(bookID);
    };


    @GetMapping("/getAllAudiencesByBook")
    public List<AudienceVsBookEntity> getAllAudiencesByBook(@RequestParam int bookID){
        return audienceVSBookService.getAllAudiencesByBook(bookID);
    };


}
