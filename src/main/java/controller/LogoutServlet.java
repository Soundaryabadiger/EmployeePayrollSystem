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
import javax.servlet.http.HttpSession;

@WebServlet("/LogoutServlet")
public class LogoutServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session =
                request.getSession(false);

        if(session != null) {

            session.invalidate();
        }

        response.sendRedirect("login.jsp");
    }
}
