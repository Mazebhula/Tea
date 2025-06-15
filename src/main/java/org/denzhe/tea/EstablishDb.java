package org.denzhe.tea;

import io.github.cdimascio.dotenv.Dotenv;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EstablishDb {
    public EstablishDb() throws SQLException {
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
    }
}
