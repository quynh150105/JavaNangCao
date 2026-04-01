package dao;

import entity.Student;
import entity.User;
import utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class studentDAO implements DaoInterface<Student> {

    private studentDAO(){}

    public static studentDAO getInstance(){
        return new studentDAO();
    }


    @Override
    public int insert(Student student) {
        try{
            Connection c = JDBCUtils.getConnection();

            String sql = "INSERT  into student(name,age,address,gpa) VALUES (?,?,?,?)";

            PreparedStatement preparedStatement = c.prepareStatement(sql);

            preparedStatement.setString(1,student.getName());
            preparedStatement.setInt(2, student.getAge());
            preparedStatement.setString(3, student.getAddress());
            preparedStatement.setFloat(4,student.getGpa());

            int kq = preparedStatement.executeUpdate();

            System.out.println("them thanh cong " + kq + " user");

            JDBCUtils.closeConnection(c);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 201;
    }

    @Override
    public int update(Student student) {
        try{
            Connection c = JDBCUtils.getConnection();

            String sql = "UPDATE student SET "
                    + "name = ?, "
                    + "age = ?, "
                    + "address = ?, "
                    + "gpa = ? "
                    + "WHERE id = ?";

            PreparedStatement statement = c.prepareStatement(sql);

            statement.setString(1,student.getName());
            statement.setInt(2, student.getAge());
            statement.setString(3, student.getAddress());
            statement.setFloat(4,student.getGpa());
            statement.setInt(5,student.getId());


            int kq = statement.executeUpdate();

            System.out.println("so dong thay doi: " + kq);

            JDBCUtils.closeConnection(c);

        }
        catch (SQLException e){
            e.printStackTrace();
        }

        return 0;
    }

    @Override
    public int delete(Student student) {
        try{
            Connection c = JDBCUtils.getConnection();

            String sql = "DELETE from student "
                    + "WHERE id= ?";

            PreparedStatement statement = c.prepareStatement(sql);

            statement.setInt(1,student.getId());

            int kq = statement.executeUpdate();

            System.out.println("so dong thay doi: " + kq);

            JDBCUtils.closeConnection(c);

        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public ArrayList<Student> getAll() {
        ArrayList<Student> kq = new ArrayList<Student>();
        try {
            Connection c = JDBCUtils.getConnection();

            String sql = "SELECT * from student";

            PreparedStatement statement = c.prepareStatement(sql);

            ResultSet rs = statement.executeQuery(sql);

            while(rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int age = rs.getInt("age");
                String address = rs.getString("address");
                float gpa = rs.getFloat("gpa");
                Student student = Student.builder()
                        .id(id).name(name).age(age).address(address).gpa(gpa)
                        .build();
                kq.add(student);
            }
            JDBCUtils.closeConnection(c);
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return kq;
    }

    @Override
    public Student selectById(int id) {
        Student std = null;

        try{
            Connection con = JDBCUtils.getConnection();

            String sql = "SELECT * from student where id=?";

            PreparedStatement preparedStatement = con.prepareStatement(sql);

            preparedStatement.setInt(1,id);

            ResultSet rs = preparedStatement.executeQuery();

            while(rs.next()){
                String name = rs.getString("name");
                int age = rs.getInt("age");
                String address = rs.getString("address");
                float gpa = rs.getFloat("gpa");
                std = Student.builder()
                        .id(id)
                        .name(name)
                        .age(age)
                        .address(address)
                        .gpa(gpa)
                        .build();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return std;
    }

    public ArrayList<Student> sortByName(){
        ArrayList<Student> list = new ArrayList<>();
        try{
            Connection c = JDBCUtils.getConnection();

            String sql = "SELECT * FROM student order by name asc";

            PreparedStatement statement = c.prepareStatement(sql);

            ResultSet rs = statement.executeQuery();

            while(rs.next()){
                Student student = Student.builder()
                        .id(rs.getInt("id"))
                        .name(rs.getString("name"))
                        .age(rs.getInt("age"))
                        .address(rs.getString("address"))
                        .gpa(rs.getFloat("gpa"))
                        .build();
                list.add(student);
            }
            JDBCUtils.closeConnection(c);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public ArrayList<Student> sortByGpa(){
        ArrayList<Student> list = new ArrayList<>();

        try{
            Connection c = JDBCUtils.getConnection();

            String sql = "SELECT * FROM student order by gpa asc";

            PreparedStatement statement = c.prepareStatement(sql);

            ResultSet rs = statement.executeQuery();

            while(rs.next()){
                Student student = Student.builder()
                        .id(rs.getInt("id"))
                        .name(rs.getString("name"))
                        .age(rs.getInt("age"))
                        .address(rs.getString("address"))
                        .gpa(rs.getFloat("gpa"))
                        .build();
                list.add(student);
            }
            JDBCUtils.closeConnection(c);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return list;
    }


}
