package lk.ijse.dep11.web.listener;

import org.apache.commons.dbcp2.BasicDataSource;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.annotation.WebServlet;
import java.sql.SQLException;

@WebListener
public class AppContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        BasicDataSource pool = new BasicDataSource();
        pool.setDriverClassName("com.mysql.cj.jdbc.Driver");
        pool.setUsername("root");
        pool.setPassword("Ravindu123");
        pool.setUrl("jdbc:mysql://localhost:3306/Session_App");
        pool.setInitialSize(10);
        pool.setMaxTotal(15);
        sce.getServletContext().setAttribute("connectionPool", pool);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        try{
            ((BasicDataSource)sce.getServletContext().getAttribute("connectionPool")).close();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}
