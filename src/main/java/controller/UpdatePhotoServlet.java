package controller;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import util.DBConnection;

@WebServlet("/UpdatePhotoServlet")

@MultipartConfig

public class UpdatePhotoServlet
extends HttpServlet {

    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        int empId =
                Integer.parseInt(
                        request.getParameter("empId"));

        Part filePart =
                request.getPart("photo");

        String fileName =
                filePart.getSubmittedFileName();

        String uploadPath =
        getServletContext().getRealPath("")
        + "images";

        File uploadDir =
                new File(uploadPath);

        if(!uploadDir.exists()) {

            uploadDir.mkdir();
        }

        filePart.write(
        uploadPath + File.separator + fileName);

        try {

            Connection con =
                    DBConnection.getConnection();

            String query =
            "UPDATE employees SET photo=? WHERE emp_id=?";

            PreparedStatement ps =
                    con.prepareStatement(query);

            ps.setString(1, fileName);

            ps.setInt(2, empId);

            ps.executeUpdate();

        } catch(Exception e) {

            e.printStackTrace();
        }

        response.sendRedirect(
        "EmployeeProfileServlet?id=" + empId);
    }
}