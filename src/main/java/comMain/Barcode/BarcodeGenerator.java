package comMain.Barcode;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import javax.imageio.ImageIO;
import net.sourceforge.barbecue.Barcode;
import net.sourceforge.barbecue.BarcodeException;
import net.sourceforge.barbecue.BarcodeFactory;
import net.sourceforge.barbecue.BarcodeImageHandler;
import net.sourceforge.barbecue.output.OutputException;

public class BarcodeGenerator {
    public static byte[] generateBarcode(String input) throws IOException, BarcodeException, OutputException {
        // Create the barcode
        Barcode barcode = BarcodeFactory.createCode128(input);

        // Convert barcode to image
        BufferedImage barcodeImage = BarcodeImageHandler.getImage(barcode);

        // Convert image to byte array
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(barcodeImage, "png", baos);
        baos.flush();
        byte[] barcodeBytes = baos.toByteArray();
        baos.close();

        return barcodeBytes;
    }
}
