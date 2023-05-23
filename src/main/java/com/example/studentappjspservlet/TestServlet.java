package com.example.studentappjspservlet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@WebServlet(name = "TestServlet", value = "/TestServlet")
public class TestServlet extends HttpServlet {

    private DataSource dataSource;
    private DataSource getDataSource() throws NamingException {

        String jndi="java:comp/env/jdbc/servletjsp" ;
        Context context = new InitialContext();
        DataSource dataSource = (DataSource) context.lookup(jndi);
        return dataSource;
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out =response.getWriter();
        response.setContentType("text/plain");
        out.println("<h1> testing this servlet </h1>");
        Connection conn= null;
        Statement st=null;
        ResultSet rs=null;
        try {
            dataSource = getDataSource();
            conn = dataSource.getConnection();
            String query="select * from students";
            st=conn.createStatement();
            rs=st.executeQuery(query);
            while (rs.next()){
                String email =rs.getString("email");
                out.println(email);
            }
        }catch (Exception exc){
            System.out.println(exc.getMessage());

        }
        finally {
            try {
                assert conn != null;
                conn.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
