import eims.util.MD5BU;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Test {
    public static void main(String[] args) {
        String s = "a";
        String a = MD5BU.md5(s);
        System.out.println(a);
        //ICy5YqxZB1uWSwcVLSNLcA==

    }


}
