package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.EmployeeDAO;

@WebServlet("/ApplyLeaveServlet")

public class ApplyLeaveServlet
extends HttpServlet {

    protected void doPost(
    HttpServletRequest request,

    HttpServletResponse response)

    throws ServletException, IOException {

        int empId =
        Integer.parseInt(
        request.getParameter("empId"));

        String leaveDate =
        request.getParameter("leaveDate");

        String reason =
        request.getParameter("reason");

        EmployeeDAO dao =
        new EmployeeDAO();

        dao.applyLeave(
        empId,
        leaveDate,
        reason);

        response.sendRedirect(
        "EmployeeProfileServlet?id=" + empId);
    }
}