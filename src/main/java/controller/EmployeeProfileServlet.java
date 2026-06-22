-e /**
 * Employee Payroll System
 * @author Soundarya Badiger
 * @version 1.0
 * @since 2025
 * KLE College of Engineering and Technology
 * Electronics and Communication Engineering, Bengaluru
 */
package controller;


import model.Leave;
import java.util.List;
import java.io.IOException;
import model.Payroll;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.EmployeeDAO;
import model.Attendance;
import model.Employee;

@WebServlet("/EmployeeProfileServlet")
public class EmployeeProfileServlet
extends HttpServlet {

    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        int empId =
                Integer.parseInt(
                        request.getParameter("id"));

        EmployeeDAO dao = new EmployeeDAO();

        Employee emp =
                dao.getEmployeeById(empId);

        List<Attendance> attendanceList =
                dao.getAttendanceByEmpId(empId);
        
        List<Payroll> payrollList =
                dao.getPayrollByEmpId(empId);

        request.setAttribute("employee", emp);

        request.setAttribute(
                "attendanceList",
                attendanceList);
        
        request.setAttribute(
                "payrollList",
                payrollList);
        
        List<Leave> leaveList =
        		dao.getLeaveHistory(empId);

        		request.setAttribute(
        		"leaveList",
        		leaveList);

        request.getRequestDispatcher(
                "employeeProfile.jsp")
                .forward(request, response);
    }
}
