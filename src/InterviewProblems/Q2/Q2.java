package InterviewProblems.Q2;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;

public class Q2 {
    private static void enCrypt(String input){
        try {
            String key = "QX5PNYo8NgSIn7v46JomDqbb";
            String iv = "Z62BZzhu3NkkfBrX";
            IvParameterSpec ivSpec = new IvParameterSpec(iv.getBytes(StandardCharsets.UTF_8));
            SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, ivSpec);

            byte[] encrypted = cipher.doFinal(input.getBytes(StandardCharsets.UTF_8));
            String encryptedTextHex = String.format("%x", new BigInteger(1, encrypted));
            System.out.println("Input:"+input);
            System.out.println("Encrypted text (Hex): " + encryptedTextHex);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        enCrypt("LUKE");
        enCrypt("0911123456");
        enCrypt("伊原力科技May4STech");

    }
}
