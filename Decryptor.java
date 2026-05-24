import javax.crypto.Cipher; // Import the Cipher class for encryption and decryption
import javax.crypto.SecretKey; // Import the SecretKey class for secret key management
import javax.crypto.spec.GCMParameterSpec; // Import the GCMParameterSpec class for GCM mode parameters

public class Decryptor{
    private static final int IV_SIZE = 12; // Define the size of the initialization vector (IV) for GCM mode
    private static final int TAG_LENGTH = 128; // Define the length of the authentication tag for GCM mode

    public static byte[] decrypt(byte[] encryptedData, SecretKey key) throws Exception{
        //Extract IV from the encrypted data
        byte[] iv = new byte[IV_SIZE]; // Create a byte array to hold the IV
        System.arraycopy(encryptedData, 0, iv, 0, IV_SIZE); // Copy the IV from the encrypted data, copies first 12 bytes into iv

        //Extract the Ciphertext from the encrypted data
        byte[] cipherText = new byte[encryptedData.length - IV_SIZE]; // Create a byte array to hold the ciphertext, size is total length minus IV size
        System.arraycopy(encryptedData, IV_SIZE, cipherText, 0, cipherText.length);// Copy the ciphertext from the encrypted data, starting from the position after the IV

        //Initialize Cipher for decryption
        Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");// Create a Cipher instance for AES in GCM mode with no padding
        GCMParameterSpec spec = new GCMParameterSpec(TAG_LENGTH, iv);// Create a GCMParameterSpec with the specified tag length and IV
        cipher.init(Cipher.DECRYPT_MODE, key, spec); // Initialize the Cipher in decryption mode with the secret key and GCM parameters

        //Decrypt and verify tag
        return cipher.doFinal(cipherText); // Perform the decryption and return the plaintext, this will also verify the authentication tag
    }
}