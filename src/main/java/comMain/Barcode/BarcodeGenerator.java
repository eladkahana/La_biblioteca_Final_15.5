package comMain.Barcode;

import org.krysalis.barcode4j.impl.code39.Code39Bean;
import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider;


import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;

import java.io.IOException;


public class BarcodeGenerator {
    public static byte[] generateBarcode(String input) throws IOException {
        // Create the barcode
        Code39Bean bean = new Code39Bean();

        final int dpi = 500;

        // Set up the canvas provider for monochrome PNG output
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        BitmapCanvasProvider canvas = new BitmapCanvasProvider(
                baos, "image/png", dpi, BufferedImage.TYPE_BYTE_GRAY, false, 0);

        // Generate the barcode
        bean.generateBarcode(canvas, input);

        // Signal end of generation
        canvas.finish();

        return baos.toByteArray();
    }
}
