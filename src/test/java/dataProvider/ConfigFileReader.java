package dataProvider;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConfigFileReader {

    private Properties properties;
    private final String propertyFilePath= "config//config.properties";


    public ConfigFileReader(){
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(propertyFilePath));
            properties = new Properties();
            try {
                properties.load(reader);
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("config.properties not found at " + propertyFilePath);
        }
    }

    public String getBrowser(){
        String browser = properties.getProperty("browser");
        if(browser!= null) return browser;
        else throw new RuntimeException("Can not read browser from the cofniguration file");
    }

    public String getDriverPath(){
        String driverPath = properties.getProperty("driverPath");
        if(driverPath!= null) return driverPath;
        else throw new RuntimeException("driverPath not specified in the Configuration.properties file.");
    }

    public String getDriverPathFirefox(){
        String driverPath = properties.getProperty("driverPathFirefox");
        if(driverPath!= null) return driverPath;
        else throw new RuntimeException("driverPath for Firefox not specified in the Configuration.properties file.");
    }

    public long getImplicitlyWait() {
        String implicitWaitDuration = properties.getProperty("implicitWaitDuration");
        if(implicitWaitDuration != null) return Long.parseLong(implicitWaitDuration);
        else throw new RuntimeException("Implicit wait duration not specified in the Configuration properties file");
    }

    public String getApplicationUrl() {
        String url = properties.getProperty("url");
        if(url != null) return url;
        else throw new RuntimeException("url not specified in the Configuration.properties file.");
    }

}