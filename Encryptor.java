import javax.crypto.Cipher; //It handles encryption/decrpytion
import javax.crypto.SecretKey; //It is the AES Key
import javax.crypto.spec.GCMParameterSpec; //It is used to specify the parameters for GCM mode, it contains IV and Tag length
import java.security.SecureRandom; //It is used to generate a random IV everytime

public class Encryptor{
    private static final int IV_SIZE = 12; //GCM standard IV size is 12 bytes
    private static final int TAG_LENGTH = 128; //GCM standard tag size is 128 bytes
    public static byte[] encrypt (byte[] data, SecretKey key) throws Exception{
        //Creating a IV
        byte[] iv = new byte[IV_SIZE];
        SecureRandom random = new SecureRandom();
        random.nextBytes(iv);//Generating a random IV
        /*
        Ex:
        [23,-76,34,11,87...]
        */

        //Creating a Cipher Text
        Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");

        //GCM Parameters
        GCMParameterSpec spec = new GCMParameterSpec(TAG_LENGTH, iv); //Creating a GCMParameterSpec with the IV and Tag length

        //Intializing the Cipher in Encrypt mode
        cipher.init(Cipher.ENCRYPT_MODE, key, spec); //Mode = Encrypt, Key = AES Key, Spec = IV + Tag

        //Encrypt the data
        byte[] encryptedData = cipher.doFinal(data); //This single line does the encryption, Plaintext XOR AES(Key, Counter) = CipherText, then final result = [CipherText + Tag]

        //Creating a result array
        byte[] result = new byte[iv.length + encryptedData.length]; //Creating a new result array, it stores IV + Encrypted Data

        //Copying the IV
        System.arraycopy(iv, 0, result, 0, iv.length); //Copying the IV to the beginning of the result array

        //Copying the Encrypted Data
        System.arraycopy(encryptedData, 0, result, iv.length, encryptedData.length); //Ciphertext + Tag after IV

        return result;
    }
}