package fileoperations;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class MyClass {


    public static void main(String[] args) {

        String dirPath = "folder" + File.separator + "anotherFolder";
        File dir = new File(dirPath);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        File file = new File(dir + File.separator + "MyFile.txt");

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

//        writeToFile(file, "first file");
//        System.out.println(readFile(file));

        writeFileWithFileOutputStream(file, "My string");
        readFileWithFileInputStream(file);
//        file.delete();


    }

    private static void readFileWithFileInputStream(File file) {
        try (FileInputStream fis = new FileInputStream(file)) {
            String result = new String(fis.readAllBytes());
            System.out.println(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void writeFileWithFileOutputStream(File file, String input) {
        try (FileOutputStream oes = new FileOutputStream(file)) {
            oes.write(input.getBytes(StandardCharsets.US_ASCII));
            oes.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String readFile(File file) {
        try (FileReader fr = new FileReader(file)) {
            int i;
            StringBuilder sb = new StringBuilder();
            while ((i = fr.read()) != -1) {
                sb.append((char) i);
            }
            return sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static void writeToFile(File file, String input) {
        try (FileWriter fw = new FileWriter(file)) {
            fw.write(input);
            fw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
