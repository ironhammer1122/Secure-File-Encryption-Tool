import javax.crypto.SecretKey; //It has the symmetric key needed for both encryption and decryption.
import javax.crypto.spec.SecretKeySpec; //It constructs a secret key from byte array.
import java.security.MessageDigest; //It has algorithms for hashing such as SHA-1 or SHA-256, they take any size of data and convert it into a fixed size hash value.

/* 
Password (String)
        ↓
Convert to bytes
        ↓
SHA-256 hash (32 bytes)
        ↓
Wrap as AES key
        ↓
Return SecretKey
*/

public class KeyGeneratorUtil {
    public static SecretKey generateKey(String password) throws Exception{
        //Convert your password into bytes.
        byte[] passwordbytes = password.getBytes("UTF-8"); //UTF-8 is the standard encoding for text data, it can represent all characters in the Unicode character set.
        /*
        Example:
        password: "abc"
        'a' = 97 in ASCII
        'b' = 98 in ASCII
        'c' = 99 in ASCII
        passwordbytes = [97, 98, 99]
        */
        
        //Hash using SHA-256 to get a 32-byte key.
        MessageDigest digest = MessageDigest.getInstance("SHA-256");//Created an object that knows SHA-256 algorithm, it can process input and produce hash.
        byte[] keybytes = digest.digest(passwordbytes); //It takes the password bytes and produces a 32-byte hash.

        //Convert the hash into AES key.
        return new SecretKeySpec(keybytes, "AES"); //It takes the 32-byte hash and creates a SecretKey object that can be used for AES encryption and decryption.
    }
}
