package org.denzhe.tea;

import io.github.cdimascio.dotenv.Dotenv;

import java.sql.*;

public class UpdateDb {
    public void addUsers(String user) throws SQLException {
        Dotenv dotenv = Dotenv.configure().load();
        Connection con = DriverManager.getConnection(
                "jdbc:sqlserver://roxx.database.windows.net:1433;database=roxx;user=%s;password=%s;encrypt=true;" +
                        "trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;",
                dotenv.get("user"), dotenv.get("password"));

        String createTableSQL = "IF NOT EXISTS (SELECT * FROM sys.tables WHERE name = 'users') "
                + "CREATE TABLE users ("
                + "id INT IDENTITY(1,1) PRIMARY KEY, "
                + "name NVARCHAR(255) NOT NULL, "
                + ")";
        PreparedStatement createTableStmt = con.prepareStatement(createTableSQL);
        createTableStmt.executeUpdate();
        createTableStmt.close();
        PreparedStatement st = con.prepareStatement("insert into users (name) values (?)");
        st.setString(1,user);
        st.executeUpdate();

    }
}
