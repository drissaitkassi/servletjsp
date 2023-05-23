package com.example.studentappjspservlet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "StudentControllerServlet", value = "/StudentControllerServlet")
public class StudentControllerServlet extends HttpServlet {

    private StudentDBUtil studentDBUtil;
    private DataSource dataSource;

    public StudentControllerServlet() {
        super();
    }

    private DataSource getDataSource() throws NamingException, NamingException {

        String jndi="java:comp/env/jdbc/servletjsp" ;
        Context context = new InitialContext();
        DataSource dataSource = (DataSource) context.lookup(jndi);
        return dataSource;
    }

    @Override
    public void init() throws ServletException {
        super.init();

        try {
            dataSource=getDataSource();
            studentDBUtil =new StudentDBUtil(dataSource);

        } catch (NamingException e) {
            throw new RuntimeException(e);
        }

    }

    private void listStudents(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        List<Student>students=studentDBUtil.getStudents();
        System.out.println(students);
        request.setAttribute("STUDENTS",students);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/list-students.jsp");
        dispatcher.forward(request, response);
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            listStudents(request,response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
