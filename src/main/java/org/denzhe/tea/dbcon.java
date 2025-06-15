package org.denzhe.tea;

import io.github.cdimascio.dotenv.Dotenv;

import java.sql.*;
import java.util.ArrayList;

public class dbcon {
    ArrayList<String> users= new ArrayList<>();
    public ArrayList<String> getUsers() throws SQLException {
        Dotenv dotenv = Dotenv.configure().load();
        Connection con = DriverManager.getConnection(
                "jdbc:sqlserver://roxx.database.windows.net:1433;database=roxx;user=%s;password=%s;encrypt=true;" +
                        "trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;",
                        dotenv.get("user"), dotenv.get("password"));
        Statement st = con.createStatement();
        String qry = "select name from users";
        ResultSet ds = st.executeQuery(qry);
        while (ds.next()) {
            users.add(ds.getString("name"));
        }
        con.close();
        return users;
    }
}
