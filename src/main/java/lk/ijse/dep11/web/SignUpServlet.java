package lk.ijse.dep11.web;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.dbcp2.BasicDataSource;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet("/signup")
public class SignUpServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String confirmPassword = req.getParameter("confirm-password");
        String fullName = req.getParameter("full-name");

        if(password.equals(confirmPassword)){
            BasicDataSource connectionPool = (BasicDataSource) getServletContext().getAttribute("connectionPool");
            try(Connection connection = connectionPool.getConnection()){
                PreparedStatement stmExist = connection.prepareStatement("SELECT * FROM user WHERE username = ?");
                stmExist.setString(1,email);
                if(stmExist.executeQuery().next()){
                    req.setAttribute("duplicateEmail",true);
                    getServletContext().getRequestDispatcher("/signup.jsp").forward(req,resp);
                    return;
                }
                PreparedStatement stm = connection.prepareStatement("INSERT INTO user(username, password, full_name) VALUES (?,?,?)");
                stm.setString(1,email);
                stm.setString(2, DigestUtils.sha256Hex(password));
                stm.setString(3,fullName);
                stm.executeUpdate();

                resp.sendRedirect("/app/signin.jsp?created=true");

//                req.setAttribute("created" , true);
//                getServletContext().getRequestDispatcher("/signin.jsp").forward(req, resp);
            }catch (SQLException e){
                e.printStackTrace();
                req.setAttribute("error" , true);
                getServletContext().getRequestDispatcher("/signup.jsp").forward(req,resp);
            }

        }else {
            req.setAttribute("mismatch" , true);
            getServletContext().getRequestDispatcher("/signup.jsp").forward(req, resp);
        }
    }
}
