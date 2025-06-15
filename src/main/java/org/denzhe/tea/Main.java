package org.denzhe.tea;

import java.sql.SQLException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws SQLException {
        new UpdateDb().addUsers("denje");
        ArrayList<String> con = new dbcon().getUsers();
        System.out.println(con.toString());
    }
}
