package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.EmployeeDAO;
import model.Employee;

@WebServlet("/EditEmployeeServlet")
public class EditEmployeeServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        int empId =
                Integer.parseInt(request.getParameter("id"));

        EmployeeDAO dao = new EmployeeDAO();

        Employee emp = dao.getEmployeeById(empId);

        request.setAttribute("employee", emp);

        request.getRequestDispatcher("editEmployee.jsp")
                .forward(request, response);
    }
}