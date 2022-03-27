package com.example.freemoneynoscam.repositories;

import com.example.freemoneynoscam.model.Email;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmailRepository {

    private List<Email> emails = new ArrayList<>();
    private Connection con;
    private Statement stmt;
    private ResultSet rs;

    public EmailRepository() {
        connectToDb();
    }

    public String fetchSingleEmail() {
        return "example@hotmail.com";
    }

    public List<Email> fetchAllEmails() {
        try {
            stmt = con.createStatement();
            String sqlString = "SELECT * FROM emails";
            rs = stmt.executeQuery(sqlString);

            int id;
            String email;
            while (rs.next()) {
                id = rs.getInt("id");
                email = rs.getString("email");
                emails.add(new Email(id, email));
            }

            return emails;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void connectToDb() {
        try {
            String url = "jdbc:mysql://localhost:3306/free_money";
            con = DriverManager.getConnection(url, "root", "password");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

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
                stmt = con.createStatement();
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
