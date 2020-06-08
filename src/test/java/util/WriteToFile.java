package util;

import testclasses.TestBase;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class WriteToFile extends TestBase {

    public void writeStr(String str) {
        try {
            FileWriter fw = new FileWriter("src/main/resources/productinfo.txt",true);
            fw.write(str);
            fw.write("\n");
            fw.close();
            logger.info("Successfully wrote to the file.");
        } catch (IOException e) {
            logger.error("An error occurred while writing to the file", e);
        }
    }
}

