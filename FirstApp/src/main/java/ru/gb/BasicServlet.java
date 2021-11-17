package ru.gb;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.security.auth.login.LoginException;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.util.ArrayList;


@WebServlet (name = "BasicServlet", urlPatterns = "/basic_servlet")
public class BasicServlet implements Servlet {
    private static Logger logger = LoggerFactory.getLogger(BasicServlet.class);
    private transient ServletConfig config;


    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        // Сохраняем полученную от сервера конфигурацию
        this.config = config;
    }

    @Override
    public ServletConfig getServletConfig() {
        return config;
    }

    // Метод вызывается для каждого нового HTTP запроса к данному сервлету
    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        logger.info("New request");
        ArrayList<Product> list = new ArrayList();
        for (int i = 0; i < 10; i++) {
            list.add(new Product(i, "product"+ i, i*100* Math.random()));
        }
        // получаем объект типа BufferedWriter и пишем в него ответ на пришедший запрос
        for (int i = 0; i < list.size(); i++) {
            servletResponse.getWriter().println(list.get(i).getTitle());
        }
    }

    @Override
    public String getServletInfo() {
        return "BasicServlet";
    }


    // При завершении работы веб приложения, контейнер вызывает этот метод для всех сервлетов из этого приложения
    @Override
    public void destroy() {
        logger.info("Servlet {} Destroyed", getServletInfo());
    }
}
