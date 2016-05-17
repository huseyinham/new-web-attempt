package smiteTroll.Repositories;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionCreator {

    public Connection getConnection() throws SQLException, ClassNotFoundException, IOException {
        ApplicationProperties properties = new ApplicationProperties();
        Class.forName(properties.getDriver());
        return DriverManager.getConnection(properties.getUrl(), properties.getUsername(), properties.getPassword());
    }
}