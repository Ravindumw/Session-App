package lk.ijse.dep11.web;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.dbcp2.BasicDataSource;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet("/signin")
public class SignInServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String mode = req.getParameter("mode");

        BasicDataSource pool = (BasicDataSource) getServletContext().getAttribute("connectionPool");
        try(Connection connection = pool.getConnection()){
            PreparedStatement stm = connection.prepareStatement("SELECT full_name,password FROM user WHERE username = ?");
            stm.setString(1,email);
            ResultSet rst = stm.executeQuery();
            if(rst.next()){
                if(rst.getString("password").equals(DigestUtils.sha256Hex(password))){
                    HttpSession session = req.getSession();
                    session.setAttribute("fullName", rst.getString("full_name"));
                    session.setAttribute("mode" , mode == null ? "dark" : mode);
                    resp.sendRedirect(req.getContextPath());
                }else {
                    req.setAttribute("denied",true);
                    getServletContext().getRequestDispatcher("/signin.jsp").forward(req,resp);
                }
            }else {
                req.setAttribute("denied",true);
                getServletContext().getRequestDispatcher("/signin.jsp").forward(req,resp);
            }
        }catch (SQLException e){
            e.printStackTrace();
            req.setAttribute("error",true);
            getServletContext().getRequestDispatcher("/signin.jsp").forward(req,resp);
        }
    }
}
