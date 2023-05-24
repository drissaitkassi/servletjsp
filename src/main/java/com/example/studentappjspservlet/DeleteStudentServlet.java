package com.example.studentappjspservlet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "DeleteStudentServlet", value = "/DeleteStudentServlet")
public class DeleteStudentServlet extends HttpServlet {

    private DataSource dataSource;
    private  StudentDBUtil studentDBUtil;

    @Override
    public void init() throws ServletException {
        super.init();
        try {
            dataSource =getDataSource();
            studentDBUtil= new StudentDBUtil(dataSource);
        } catch (NamingException e) {
            throw new RuntimeException(e);
        }
    }
    private DataSource getDataSource() throws NamingException, NamingException {

        String jndi="java:comp/env/jdbc/servletjsp" ;
        Context context = new InitialContext();
        DataSource dataSource = (DataSource) context.lookup(jndi);
        return dataSource;
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out=response.getWriter();
        String id=request.getParameter("id");
        studentDBUtil.deleteStudent(Integer.parseInt(String.valueOf(id)));
        System.out.println(id);
        response.sendRedirect("StudentControllerServlet");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
