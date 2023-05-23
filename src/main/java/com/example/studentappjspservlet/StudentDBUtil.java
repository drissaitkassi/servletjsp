package com.example.studentappjspservlet;

import javax.sql.DataSource;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class StudentDBUtil {

    private DataSource dataSource;

    public StudentDBUtil(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<Student> getStudents(){
        List<Student> students=new ArrayList<>();

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
                int id =rs.getInt("id");
                String firstName =rs.getString("firstname");
                String lastName =rs.getString("lastname");
                String email =rs.getString("email");
                Student eachStudent=new Student(id,firstName,lastName,email);
                students.add(eachStudent);
            }
        }catch (Exception exc){
            System.out.println(exc.getMessage());

        }
        finally {
            try {
                assert conn != null;
                //todo you need to close the connection
                conn.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return students;
    }

}
