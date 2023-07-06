package comMain.controllers;

import comMain.entities.ReadersEntity;
import comMain.services.ReadersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.List;

@CrossOrigin
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
                                    @RequestParam String address,
                                    @RequestParam String phoneNo,
                                    @RequestParam String firstName,
                                    @RequestParam String lastName,
                                    @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") String birthDate,
                                    @RequestParam String gender,
                                    @RequestParam String Email) {


        List<Object[]> result = readersService.addReader(
                ID,
                address,
                phoneNo,
                firstName,
                lastName,
                birthDate,
                gender,
                Email
        );
        return result;
    }


    @PostMapping("/LogIn/TryToConnect")
    public ResponseEntity<List<Object[]>> TryToConnect(@Valid @RequestParam("IP") String IP,
                                                       @Valid @RequestParam("UserName") String UserName,
                                                       @Valid @RequestParam("Password") String Password) {



        List<Object[]> userID = readersService.TryToConnect(IP,  UserName, Password);
        HttpHeaders headers = new HttpHeaders();
        return ResponseEntity.ok().headers(headers).body(userID);
    }


    @PutMapping("/EditReader")
    public void EditReader(@RequestParam String IDno,
                                    @RequestParam String address,
                                    @RequestParam String phoneNo,
                                    @RequestParam String firstName,
                                    @RequestParam String lastName,
                                    @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") String birthDate,
                                    @RequestParam String gender,
                                    @RequestParam String Email,
                          @RequestParam int ID) {


        readersService.EditReader(
                IDno,
                address,
                phoneNo,
                firstName,
                lastName,
                birthDate,
                gender,
                Email,
                ID
        );
    }


    @PostMapping("/LogIn/AddPassword")
    public List<Object[]> AddPassword(@RequestParam() int uID,
                                       @RequestParam() String Password){
        return readersService.AddPassword(uID,Password);

    }

    @PostMapping("/LogIn/AddPasswordWeb")
    public ResponseEntity<Object> AddPasswordWeb(@RequestParam() int uID,
                                                 @RequestParam() String Password){
        readersService.AddPassword(uID, Password);
        HttpHeaders headers = new HttpHeaders();
        return ResponseEntity.ok().headers(headers).build();
    }


    @GetMapping("/getReadersForWeb")
    public List<Object[]> getReadersForWeb(){
        return readersService.getReadersForWeb();

    }

}