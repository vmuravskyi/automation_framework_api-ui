package com.rest.examples.serializationanddeserialization.pojo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rest.examples.serializationanddeserialization.pojo.complex.Root;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import org.testng.annotations.Test;

public class ReadJsonFileToObjectTest {

    @Test
    public void readJsonToObject() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        List<Root> roots = Arrays.asList(
            objectMapper.readValue(new File("src/test/resources/filesToReadAsJson/complexJson.json"),
                Root[].class)
        );
        roots.forEach(System.out::println);

        // convert to stream
        String backToString = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(roots);
        System.out.println(backToString);

        // write to another .json
        // ease way
        objectMapper.writeValue(new File("src/test/resources/filesToReadAsJson/complexJsonBack.json"), roots);

        // using io streams
//        OutputStream fileOutputStream =
//            new FileOutputStream(new File("src/test/resources/filesToReadAsJson/complexJsonBack.json"));
//        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(
//            backToString.getBytes(StandardCharsets.UTF_8));
//        fileOutputStream.write(byteArrayInputStream.read());
//        fileOutputStream.close();
    }

}
