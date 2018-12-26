package com.venux; /**
 * Created by Administrator on 2018/12/26.
 */

import javax.servlet.*;
import java.io.IOException;

/**
 * TestServlet
 *
 * @author Alan Liu
 * @create 2018-12-26 17:03
 **/
public class ServletTest implements Servlet{

    @Override
    public void init(ServletConfig config) throws ServletException {
        System.out.println("init");
    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        System.out.println("service==");
        res.getWriter().println("red");
    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {

    }
}
