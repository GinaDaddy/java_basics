package com.brian;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Use of properties.
 * https://mkyong.com/java/java-properties-file-examples/
 */
public class PropertyUseDemo {

    public static void main(String[] args) throws IOException {
        try (InputStream input = PropertyUseDemo.class.getClassLoader().getResourceAsStream("myProperties.properties");) {
            Properties prop = new Properties();
            prop.load(input);
            prop.forEach((key, value) -> System.out.println("Key: " + key + ", Value: " + value));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
