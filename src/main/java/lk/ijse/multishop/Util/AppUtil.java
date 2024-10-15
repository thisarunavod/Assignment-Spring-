package lk.ijse.multishop.Util;

import java.util.Base64;

public class AppUtil {
    public static String toBase64ProfilePic(byte[] itemPic){
        // base64 formatt ekata ape file format eka convert karaa  //
        return Base64.getEncoder().encodeToString(itemPic);

    }
}
