package smiteTroll;

import java.beans.Statement;

public class Gods {



    public Gods() {

    }

    public static void main (String args[]){
        Gods god = new Gods();
        god.getNewGod();
    }

    private void getNewGod() {
        Statement stmt = null;
        stmt = con.createStatement();
        String query = "SELECT * FROM smiteTroll, " + "ORDER BY RAND(), " + "LIMIT 1 ;";
    }



    //randomly select a smiteTroll
    //obtain all the items that can be used on that smiteTroll i.e. physical and neutral items for a physical smiteTroll
    //randomly choose 6 items out of those items available


}
