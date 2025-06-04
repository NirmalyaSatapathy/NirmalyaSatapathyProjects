package com.java.asset.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.java.asset.model.Login;
import com.java.asset.util.ConnectionHelper;
import com.java.asset.util.EncryptPassword;

public class LoginDaoImpl implements LoginDao {

    @Override
    public String adminLogin(Login login) {
        String sql = "SELECT * FROM Admin WHERE Username = ? AND PasswordHash = ?";
        try (Connection con = ConnectionHelper.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            String encryptedPass = EncryptPassword.getCode(login.getPassword());

            ps.setString(1, login.getName());
            ps.setString(2, encryptedPass);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return "Admin Login Successful";
            } else {
                return "Invalid Admin Credentials";
            }

        } catch (Exception e) {
            e.printStackTrace();
            return "Error during admin login: " + e.getMessage();
        }
    }

    @Override
    public String userLogin(Login login) {
        String sql = "SELECT * FROM Employee WHERE Username = ? AND PasswordHash = ?";
        try (Connection con = ConnectionHelper.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            String encryptedPass = EncryptPassword.getCode(login.getPassword());

            ps.setString(1, login.getName());
            ps.setString(2, encryptedPass);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return "User Login Successful";
            } else {
                return "Invalid User Credentials";
            }

        } catch (Exception e) {
            e.printStackTrace();
            return "Error during user login: " + e.getMessage();
        }
    }
}
