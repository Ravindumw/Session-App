package lk.ijse.dep11.web;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/signup")
public class SignUpServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String confirmPassword = req.getParameter("confirm-password");
        String fullName = req.getParameter("full-name");

        if(password.equals(confirmPassword)){
            getServletContext().getRequestDispatcher("/signin.jsp").forward(req, resp);
        }else {
            getServletContext().getRequestDispatcher("/signup.jsp").forward(req, resp);
        }
    }
}
