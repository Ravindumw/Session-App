package lk.ijse.dep11.web.filter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter({"/index.jsp","/page1.jsp","/page2.jsp","/page3.jsp"})
public class SecurityFilter extends HttpFilter {

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpSession session = req.getSession(false);
        if(session != null){
            chain.doFilter(req, res);
        }else {
            res.sendRedirect(req.getContextPath() + "/signin.jsp");
        }
    }
}
