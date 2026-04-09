package dao;

import model.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {
    // Thông tin kết nối
    private static final String URL =
            "jdbc:mysql://localhost:3306/quanlysinhvien";
    private static final String USER = "root";
    // ← thay bằng user của
    private static final String PASSWORD = "12345678";  // ← thay bằng password
   // của bạn
    // Helper method lấy Connection
    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
    // ====================== CREATE ======================
    public void insertStudent(Student student) {
        String sql = "INSERT INTO students (name, age, email) VALUES (?, ?,?)";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql,
                     Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, student.getName());
            pstmt.setInt(2, student.getAge());
            pstmt.setString(3, student.getEmail());
            int rows = pstmt.executeUpdate();
            if (rows > 0) {
                try (ResultSet rs = pstmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        student.setId(rs.getLong(1));
                    }
                }
                System.out.println("✅ Thêm sinh viên thành công: " +
                        student);
            }
        } catch (SQLException e) {
            System.err.println("❌ Lỗi khi thêm: " + e.getMessage());
        }
    }
    // ====================== READ ALL ======================
    public List<Student> getAllStudents() {
        List<Student> list = new ArrayList<>();
        String sql = "SELECT * FROM students ORDER BY id";
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Student s = new Student();
                s.setId(rs.getLong("id"));
                s.setName(rs.getString("name"));
                s.setAge(rs.getInt("age"));
                s.setEmail(rs.getString("email"));
                list.add(s);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    // ====================== READ BY ID ======================
    public Student getStudentById(Long id) {
        String sql = "SELECT * FROM students WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setLong(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Student s = new Student();
                    s.setId(rs.getLong("id"));
                    s.setName(rs.getString("name"));
                    s.setAge(rs.getInt("age"));
                    s.setEmail(rs.getString("email"));
                    return s;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    // ====================== UPDATE ======================
    public void updateStudent(Student student) {
        String sql = "UPDATE students SET name = ?, age = ?, email = ? WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, student.getName());
            pstmt.setInt(2, student.getAge());
            pstmt.setString(3, student.getEmail());
            pstmt.setLong(4, student.getId());
            int rows = pstmt.executeUpdate();
            if (rows > 0) {
                System.out.println("✅ Cập nhật thành công: " + student);
            } else {
                System.out.println("❌ Không tìm thấy sinh viên ID = " +
                        student.getId());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    // ====================== DELETE ======================
    public void deleteStudent(Long id) {
        String sql = "DELETE FROM students WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setLong(1, id);
            int rows = pstmt.executeUpdate();
            if (rows > 0) {
                System.out.println("✅ Xóa thành công sinh viên ID = " + id);
            } else {
                System.out.println("❌ Không tìm thấy sinh viên ID = " + id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    // ====================== TRANSACTION EXAMPLE ======================
    public void transferExample() {
        String sql1 = "UPDATE students SET age = age + 1 WHERE id = 1";
        String sql2 = "UPDATE students SET age = age - 1 WHERE id = 2";
        try (Connection conn = getConnection()) {
            conn.setAutoCommit(false);
// Bắt đầu transaction
            try (Statement stmt = conn.createStatement()) {
                stmt.executeUpdate(sql1);
                stmt.executeUpdate(sql2);
                conn.commit();
// Thành công → commit
                System.out.println("✅ Transaction thành công!");
            } catch (SQLException e) {
                conn.rollback();
// Lỗi → rollback
                System.err.println("❌ Transaction bị rollback: " +
                        e.getMessage());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
