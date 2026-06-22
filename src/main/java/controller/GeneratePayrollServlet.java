-e /**
 * Employee Payroll System
 * @author Soundarya Badiger
 * @version 1.0
 * @since 2025
 * KLE College of Engineering and Technology
 * Electronics and Communication Engineering, Bengaluru
 */
package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.EmployeeDAO;

@WebServlet("/GeneratePayrollServlet")

public class GeneratePayrollServlet
extends HttpServlet {

    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        int empId =
                Integer.parseInt(
                request.getParameter("empId"));

        EmployeeDAO dao =
                new EmployeeDAO();

        boolean result =
                dao.generatePayroll(empId);

        if(result) {

            response.sendRedirect(
            "EmployeeProfileServlet?id=" + empId);

        } else {

            response.getWriter().println(
            "Payroll already generated for this month.");
        }
    }
}
