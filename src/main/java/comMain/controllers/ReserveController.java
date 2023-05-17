package comMain.controllers;

import comMain.entities.ReserveEntity;
import comMain.services.ReserveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Validated
@RestController
@RequestMapping("/reserve")
public class ReserveController {

    @Autowired
    private ReserveService reserveService;

    @PostMapping
    public String save(@Valid @RequestBody ReserveEntity vO) {
        return reserveService.save(vO).toString();
    }

    @DeleteMapping("/{id}")
    public void delete(@Valid @NotNull @PathVariable("id") Integer id) {
        reserveService.delete(id);
    }

    @PutMapping("/{id}")
    public void update(@Valid @NotNull @PathVariable("id") Integer id,
                       @Valid @RequestBody ReserveEntity vO) {
        reserveService.update(id, vO);
    }

    @GetMapping("/{id}")
    public ReserveEntity getById(@Valid @NotNull @PathVariable("id") Integer id) {
        return reserveService.getById(id);
    }


    @GetMapping("/ReserveDaysLeft")
    public List<Object[]> ReserveDaysLeft(@RequestParam("arrStr") String arrStr){
        return reserveService.ReserveDaysLeft(arrStr);
    }

    @GetMapping
    public List<ReserveEntity> getAllReserves(){
        return reserveService.getAllReserves();
    }

    @GetMapping("/MonthlyResrvesAmount")
    public List<Object[]> MonthlyResrvesAmount(){
        return reserveService.MonthlyResrvesAmount();
    }


    @GetMapping("/todayResrvesAmount")
    public List<Object[]> todayResrvesAmount(){
        return reserveService.todayResrvesAmount();
    }

    @GetMapping("/displayReservesByDays")
    public List<Object[]> displayReservesByDays(){
        return reserveService.displayReservesByDays();
    }

    @GetMapping("/displayReservesByHours")
    public List<Object[]> displayReservesByHours(){
        return reserveService.displayReservesByHours();
    }

    @PutMapping("/AddReserve")
    public void AddReserve(@RequestParam String readerIDno,@RequestParam Integer BookID,@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") String dueTo){
        reserveService.AddReserve(readerIDno,BookID,dueTo);
    }

    @PutMapping("/AddRank")
    public void AddRank(@RequestParam  String IDno, @RequestParam  Integer copyID, @RequestParam  Integer rank){
        reserveService.AddRank(IDno,copyID,rank);
    }

    @PutMapping("/AddReturnBook")
    public void AddReturnBook(@RequestParam Integer copyID, @RequestParam String readerIDno){
        reserveService.AddReturnBook(copyID,readerIDno);
    }
}
