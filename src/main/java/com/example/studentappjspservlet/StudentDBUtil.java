package com.example.studentappjspservlet;

import javax.sql.DataSource;
import java.io.PrintWriter;
import java.sql.*;
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

    public void saveStudent(Student student) {

        Connection conn= null;
        Statement st=null;
        ResultSet rs=null;
        try {
            dataSource = getDataSource();
            conn = dataSource.getConnection();
            String query="insert into students (firstname, lastname, email) values (?,?,?)";
            PreparedStatement preparedStatement=conn.prepareStatement(query);
            preparedStatement.setString(1,student.getFirstName());
            preparedStatement.setString(2,student.getLastName());
            preparedStatement.setString(3,student.getEmail());
            preparedStatement.executeQuery();
            preparedStatement.close();

            System.out.println("im on save student method below preparestatment");

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

    }

    public void deleteStudent(int id) {

        Connection conn= null;
        Statement st=null;
        ResultSet rs=null;
        try {
            dataSource = getDataSource();
            conn = dataSource.getConnection();
            String query="delete from students where id = ?";
            PreparedStatement preparedStatement=conn.prepareStatement(query);
            preparedStatement.setInt(1,id);
            preparedStatement.executeUpdate();
            preparedStatement.close();


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


    }

    public void updateStudent(Student student) {

        Connection conn= null;
        Statement st=null;
        ResultSet rs=null;
        try {
            dataSource = getDataSource();
            conn = dataSource.getConnection();
            String query="update students set firstname = ? ,lastname = ? , email = ?  where id =?;";
            PreparedStatement preparedStatement=conn.prepareStatement(query);
            preparedStatement.setString(1,student.getFirstName());
            preparedStatement.setString(2,student.getLastName());
            preparedStatement.setString(3,student.getEmail());
            preparedStatement.setInt(4,student.getId());
            preparedStatement.executeUpdate();
            preparedStatement.close();

            System.out.println("im on save student method below preparestatment");

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

    }
}
