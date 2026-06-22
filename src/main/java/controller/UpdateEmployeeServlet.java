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
import model.Employee;

@WebServlet("/UpdateEmployeeServlet")
public class UpdateEmployeeServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        int empId =
                Integer.parseInt(request.getParameter("empId"));

        String name = request.getParameter("name");

        String email = request.getParameter("email");

        String department =
                request.getParameter("department");

        double salary =
                Double.parseDouble(
                        request.getParameter("salary"));

        String joiningDate =
                request.getParameter("joiningDate");

        Employee emp = new Employee();

        emp.setEmpId(empId);
        emp.setName(name);
        emp.setEmail(email);
        emp.setDepartment(department);
        emp.setSalary(salary);
        emp.setJoiningDate(joiningDate);

        EmployeeDAO dao = new EmployeeDAO();

        boolean status = dao.updateEmployee(emp);

        if(status) {

            response.sendRedirect(
                    "ViewEmployeesServlet");

        } else {

            response.getWriter()
            .println("Update Failed");
        }
    }
}
