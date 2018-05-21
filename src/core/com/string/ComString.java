/*
 * Class 
 * @filename UtilArray 
 * @encoding UTF-8
 * @author Liquid Edge Solutions  * 
 * @copyright Copyright Liquid Edge Solutions. All rights reserved. * 
 * @programmer Ryno van Zyl * 
 * @date 25 Aug 2017 * 
 */
package core.com.string;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

/**
 *
 * @author Ryno
 */
public class ComString {
    //--------------------------------------------------------------------------
    public static String ucwords(String sentence) {
        StringBuilder sb = new StringBuilder();

        for (CharSequence word: sentence.split(" "))
            sb.append(Character.toUpperCase(word.charAt(0))).append(word.subSequence(1, word.length())).append(" ");

        return sb.toString().trim();
    }
    //--------------------------------------------------------------------------
    public static String ucfirst(String sentence) {
        return String.valueOf(Character.toUpperCase(sentence.charAt(0))).concat(sentence.substring(1));
    }
    //--------------------------------------------------------------------------
    public static String format_currency(String value) {
        return format_currency(Double.parseDouble(value));
    }
    //--------------------------------------------------------------------------
    public static String format_currency(int value) {
        return format_currency(Double.parseDouble(String.valueOf(value)));
    }
    //--------------------------------------------------------------------------
    public static String format_currency(float value) {
        return format_currency(Double.parseDouble(String.valueOf(value)));
    }
    //--------------------------------------------------------------------------
    public static String format_currency(Double value) {
        DecimalFormat formatter = new DecimalFormat("R ###,###,##0.00");
        return formatter.format(value);
    }
    //--------------------------------------------------------------------------
    public static String pad(String str, int size, char padChar) {
        StringBuilder padded = new StringBuilder(str);
        while (padded.length() < size) {
            padded.append(padChar);
        }
        return padded.toString();
    }
    //--------------------------------------------------------------------------
    public static String encrypt(String plainText){
	String b64encoded = Base64.getEncoder().encodeToString(plainText.getBytes());

        // Reverse the string
        String reverse = new StringBuffer(b64encoded).reverse().toString();

        StringBuilder tmp = new StringBuilder();
        final int OFFSET = 4;
        for (int i = 0; i < reverse.length(); i++) {
           tmp.append((char)(reverse.charAt(i) + OFFSET));
        }
        return tmp.toString();
    }
    //--------------------------------------------------------------------------
    public static String decrypt(String str){
	StringBuilder tmp = new StringBuilder();
        final int OFFSET = 4;
        for (int i = 0; i < str.length(); i++) {
           tmp.append((char)(str.charAt(i) - OFFSET));
        }

        String reversed = new StringBuffer(tmp.toString()).reverse().toString();
        return new String(Base64.getDecoder().decode(reversed));
    }
    //--------------------------------------------------------------------------
    private static String md5(String str) {
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(StandardCharsets.UTF_8.encode(str));
            return String.format("%032x", new BigInteger(1, md5.digest()));
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(ComString.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    //--------------------------------------------------------------------------
}
