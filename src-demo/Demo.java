import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Random;

/**
 * Created by Анатолій on 21.10.2016.
 */
public class Demo {
    private final static SecureRandom random = new SecureRandom();
    private static String genSessId() {

        return Base64.getEncoder().encodeToString(random.generateSeed(36));
    }
    public static void main(String[] args) throws NoSuchAlgorithmException {
         MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] bytes = digest.digest("hello!".getBytes());
        Random randomLong = new Random();
        SecureRandom random = new SecureRandom();
        random.nextBytes(bytes);
        for (byte b:bytes) {
            System.out.format("(byte)0x%02x, ",b );

        }
        for (int i=0;i<10;i++){
            System.out.println(genSessId());
            System.out.println(genSessId().length());
        }



    }
}
