package login2.controller;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import java.net.URL;
import java.util.EnumMap;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.print.PrinterJob;
import javafx.scene.SnapshotParameters;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;

public class qrcode implements Initializable {
    @FXML
    private ImageView qr_image;
    String lien;

    public void setLien(String lien) {
        this.lien = lien;
    }
    public void setimage(){
            // set QR code parameters
            Map<EncodeHintType, Object> hints = new EnumMap<>(EncodeHintType.class);
            hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
            hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
            hints.put(EncodeHintType.MARGIN, 2);
           // create QR code image
       try {
        QRCodeWriter qrWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrWriter.encode(lien, BarcodeFormat.QR_CODE, 300, 300, hints);
        WritableImage qrcodeImage = SwingFXUtils.toFXImage(MatrixToImageWriter.toBufferedImage(bitMatrix), null);
        qr_image.setImage(qrcodeImage);
       } catch (WriterException e) {
        e.printStackTrace();
           }
    }
     @FXML
     void imprimer() {
       PrinterJob printerJob = PrinterJob.createPrinterJob();
        if (printerJob != null && printerJob.showPrintDialog(qr_image.getScene().getWindow())) {
            printerJob.printPage(qr_image);
            printerJob.endJob();
        }
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }
}
