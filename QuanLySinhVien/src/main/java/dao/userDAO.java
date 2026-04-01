package dao;

import entity.User;
import utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class userDAO implements DaoInterface<User>{

    private userDAO(){}

    public static userDAO getInstance(){
        return new userDAO();
    }

    public boolean checkUser(User user){
        if(user != null){
            if("admin".equals(user.getUsername()) && "123".equals(user.getPassword())){
                return true;
            }
        }
        return false;
    }


    @Override
    public int insert(User user) {
        try{
            Connection c = JDBCUtils.getConnection();

            String sql = "INSERT  into user(username,password) VALUES (?,?)";

            PreparedStatement preparedStatement = c.prepareStatement(sql);

            preparedStatement.setString(1,user.getUsername());
            preparedStatement.setString(2, user.getPassword());

            int kq = preparedStatement.executeUpdate();

            System.out.println("them thanh cong " + kq + " user");

            JDBCUtils.closeConnection(c);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 201;
    }

    @Override
    public int update(User user) {
        try{
            Connection c = JDBCUtils.getConnection();

            String sql = "UPDATE user SET "
                    + "password= ? "
                    + "hoten= ? "
                    + "WHERE username=?";

            PreparedStatement statement = c.prepareStatement(sql);

            statement.setString(1, user.getPassword());
            statement.setString(3,user.getUsername());


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
    public int delete(User user) {
        try{
            Connection c = JDBCUtils.getConnection();

            String sql = "DELETE from user "
                    + "WHERE username= ?";

            PreparedStatement statement = c.prepareStatement(sql);

            statement.setString(1,user.getUsername());

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
    public ArrayList<User> getAll() {
        ArrayList<User> kq = new ArrayList<User>();
        try {
            Connection c = JDBCUtils.getConnection();

            String sql = "SELECT * from user";

            PreparedStatement statement = c.prepareStatement(sql);

            ResultSet rs = statement.executeQuery(sql);

            while(rs.next()){
                // getString("col_name in mysql")
                String username = rs.getString("username");
                String password = rs.getString("password");
                User user = new User(username,password);
                kq.add(user);
            }
            JDBCUtils.closeConnection(c);
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return kq;
    }

    @Override
    public User selectById(int id) {
        return null;
    }
}
