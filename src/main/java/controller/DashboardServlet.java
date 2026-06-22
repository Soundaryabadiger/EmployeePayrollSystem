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
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.EmployeeDAO;

@WebServlet("/DashboardServlet")

public class DashboardServlet
extends HttpServlet {

    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        EmployeeDAO dao = new EmployeeDAO();

        int totalEmployees =
                dao.getTotalEmployees();

        int presentToday =
                dao.getPresentToday();

        int absentToday =
                dao.getAbsentToday();

        double totalPayroll =
                dao.getTotalPayroll();

        request.setAttribute(
                "totalEmployees",
                totalEmployees);

        request.setAttribute(
                "presentToday",
                presentToday);

        request.setAttribute(
                "absentToday",
                absentToday);

        request.setAttribute(
                "totalPayroll",
                totalPayroll);
        
        Map<String, Integer> departmentCounts =
        		dao.getDepartmentCounts();

        		request.setAttribute(
        		"departmentCounts",
        		departmentCounts);

        request.getRequestDispatcher(
        "dashboard.jsp")
        .forward(request, response);
    }
}
