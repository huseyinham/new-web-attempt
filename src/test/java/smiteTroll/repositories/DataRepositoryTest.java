package smiteTroll.repositories;

import org.junit.Assert;
import org.junit.Test;
import smiteTroll.classes.God;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DataRepositoryTest {

    private DataRepository dataRepo = new DataRepository();
    private ConnectionCreator connectionCreator = new ConnectionCreator();
/*

    @Test
    public void testGodCanBeAddedToDB() {
        God god = new God("God", "magical");
        dataRepo.addNewGodToDB(god.getGodName(), god.getGodType());
        try {
            Connection con = null;
            PreparedStatement prepStmt = null;
            try {
                con = connectionCreator.getConnection();
                prepStmt = con.prepareStatement("SELECT * FROM god WHERE god_name = ?");
                prepStmt.setString(1, god.getGodName());
                ResultSet rs = prepStmt.executeQuery();
                while (rs.next()) {
                    Assert.assertEquals("God", rs.getString("god_name"));
                }
            } finally {
                prepStmt.close();
                con.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        teardown(god.getGodName());
    }

    private void teardown(String dbKey) {
        dataRepo.deleteGodFromDB(dbKey);
    }
*/
}
