package controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import dao.EmployeeDAO;
import model.Employee;

@WebServlet("/AddEmployeeServlet")

@MultipartConfig

public class AddEmployeeServlet
extends HttpServlet {

    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        String name =
                request.getParameter("name");

        String email =
                request.getParameter("email");

        String department =
                request.getParameter("department");

        double salary =
                Double.parseDouble(
                request.getParameter("salary"));

        String joiningDate =
                request.getParameter("joiningDate");

        Part filePart =
                request.getPart("photo");

        String fileName = "default.jpg";

        if(filePart != null &&
        filePart.getSize() > 0 &&
        filePart.getSubmittedFileName() != null &&
        !filePart.getSubmittedFileName().equals("")) {

            fileName =
            Paths.get(
            filePart.getSubmittedFileName())
            .getFileName()
            .toString();

            String uploadPath =
            getServletContext()
            .getRealPath("")
            + "images";

            File uploadDir =
            new File(uploadPath);

            if(!uploadDir.exists()) {

                uploadDir.mkdir();
            }

            filePart.write(
            uploadPath +
            File.separator +
            fileName);
        }

        Employee emp = new Employee();

        emp.setName(name);

        emp.setEmail(email);

        emp.setDepartment(department);

        emp.setSalary(salary);

        emp.setJoiningDate(joiningDate);

        emp.setPhoto(fileName);

        EmployeeDAO dao =
                new EmployeeDAO();

        boolean status =
                dao.addEmployee(emp);

        if(status) {

            response.sendRedirect(
            "ViewEmployeesServlet");

        } else {

            response.getWriter()
            .println(
            "Failed to Add Employee");
        }
    }
}