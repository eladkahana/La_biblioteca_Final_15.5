package comMain.controllers;

import comMain.entities.ReadersEntity;
import comMain.services.ReadersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
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


    @PostMapping("/LogIn/TryToConnect")
    public ResponseEntity<List<Object[]>> TryToConnect(@RequestParam("IP") String IP,
                                                @RequestParam("UserName") String UserName,
                                                @RequestParam("Password") String Password) {

        try {
            InetAddress ipAddress = InetAddress.getLocalHost();
            NetworkInterface networkInterface = NetworkInterface.getByInetAddress(ipAddress);
            byte[] macAddressBytes = networkInterface.getHardwareAddress();

            StringBuilder macAddress = new StringBuilder();
            for (int i = 0; i < macAddressBytes.length; i++) {
                macAddress.append(String.format("%02X%s", macAddressBytes[i], (i < macAddressBytes.length - 1) ? "-" : ""));
            }

            List<Object[]> userID = readersService.TryToConnect(IP, macAddress.toString(), UserName, Password);
            HttpHeaders headers = new HttpHeaders();
            headers.setAccessControlAllowOrigin("*"); // Allow requests from any domain
            return ResponseEntity.ok().headers(headers).body(userID);
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        } catch (SocketException e) {
            throw new RuntimeException(e);
        }
    }


    @PutMapping("/EditReader")
    public void EditReader(@RequestParam String IDno,
                                    @RequestParam String adress,
                                    @RequestParam String phoneNo,
                                    @RequestParam String firstName,
                                    @RequestParam String lastName,
                                    @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") String birthDate,
                                    @RequestParam String gender,
                                    @RequestParam String Email,
                          @RequestParam int ID) {


        readersService.EditReader(
                IDno,
                adress,
                phoneNo,
                firstName,
                lastName,
                birthDate,
                gender,
                Email,
                ID
        );
    }

}