package smiteTroll.repositories;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ApplicationProperties {

    private Properties prop;

    public ApplicationProperties() {
        try {
            prop = new Properties();
            InputStream in = getClass().getResourceAsStream("/jdbc.properties");
            prop.load(in);
            in.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String getDriver() {
        return prop.getProperty("jdbc.driver");
    }

    public String getUrl() {
        return prop.getProperty("jdbc.url");
    }

    public String getUsername() {
        return prop.getProperty("jdbc.username");
    }

    public String getPassword() {
        return prop.getProperty("jdbc.password");
    }
}
