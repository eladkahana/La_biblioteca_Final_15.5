package comMain;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.net.ssl.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.*;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;


@SpringBootApplication

public class LaBibliotecaFinalApplication {



    public static void main(String[] args) throws IOException, KeyStoreException, CertificateException, NoSuchAlgorithmException, UnrecoverableKeyException, KeyManagementException {

        SpringApplication.run(LaBibliotecaFinalApplication.class, args);


        String keystoreFile = "D:\\new\\La_biblioteca_Final_5.5-master\\src\\main\\resources\\https.jks";
        String keystorePassword = "password";
        String alias = "https";

        KeyStore keyStore = KeyStore.getInstance("JKS");
        FileInputStream fis = new FileInputStream(keystoreFile);
        keyStore.load(fis, keystorePassword.toCharArray());

        // Create a KeyManagerFactory and initialize it with the loaded keystore
        KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
        keyManagerFactory.init(keyStore, keystorePassword.toCharArray());

        // Create and configure the SSLContext
        // Disable SSL certificate validation
        SSLContext sslContext = SSLContext.getInstance("TLS");
        sslContext.init(null, new TrustManager[] { new X509TrustManager() {
            public void checkClientTrusted(X509Certificate[] certs, String authType) {}
            public void checkServerTrusted(X509Certificate[] certs, String authType) {}
            public X509Certificate[] getAcceptedIssuers() { return null; }
        }}, new SecureRandom());
        HttpsURLConnection.setDefaultSSLSocketFactory(sslContext.getSocketFactory());
        HttpsURLConnection.setDefaultHostnameVerifier((hostname, session) -> true);
    }

}
