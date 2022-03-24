package com.example.freemoneynoscam.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class EmailDBManager {

    public String validateEmail(String email) {
        int atIndex;
        int periodIndex;
        int atCount = 0;

        for (int i = 0; i < email.length(); i++) {
            if (email.charAt(i) == '@') atCount++;
        }

        if (atCount > 1) return null;

        if (email.contains("@")) {
            atIndex = email.indexOf("@");
            if (email.contains(".")) {
                periodIndex = email.lastIndexOf(".");
                if (atIndex < periodIndex) {
                    return email;
                } else {
                    return null;
                }
            }
        }
        return null;
    }

    public boolean insertEmailIntoDB(String email) {
        String result = validateEmail(email);

        if (result != null) {
            try {
                String url = "jdbc:mysql://localhost:3306/free_money";
                Connection con = DriverManager.getConnection(url, "root", "password");
                Statement stmt = con.createStatement();
                String sqlString = "INSERT INTO emails (email) VALUES('" + email + "')";
                stmt.execute(sqlString);

            } catch (SQLException e) {
                e.printStackTrace();
            }
            return true;
        } else {
            return false;
        }

    }
}
