import java.io.*;
import java.nio.file.Files;

public class FileManager {
    //Read file and return byte array
    public static byte[] readFile(String path) throws IOException{
        File file = new File(path);
        return Files.readAllBytes(file.toPath());
    }

    //Write byte array to file
    public static void writeFile(String path, byte[] data) throws IOException{
        File file = new File(path);
        Files.write(file.toPath(), data);
    }
    }