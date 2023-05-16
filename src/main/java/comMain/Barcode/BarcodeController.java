package comMain.Barcode;

import net.sourceforge.barbecue.BarcodeException;
import net.sourceforge.barbecue.output.OutputException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
class BarcodeController {
    @GetMapping("/barcode/{input}")
    public ResponseEntity<byte[]> generateBarcode(@PathVariable String input) {
        try {
            byte[] barcodeBytes = BarcodeGenerator.generateBarcode(input);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_PNG);
            return new ResponseEntity<>(barcodeBytes, headers, HttpStatus.OK);
        } catch (IOException e) {
            // Handle exception if barcode generation fails
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
