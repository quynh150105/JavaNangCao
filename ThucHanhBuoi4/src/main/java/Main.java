import dao.StudentDAO;
import model.Student;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        StudentDAO dao = new StudentDAO();
        Scanner sc = new Scanner(System.in);
        int choice;
        do {

            System.out.println("\n========== QUẢN LÝ SINH VIÊN - JDBC =========");
            System.out.println("1. Thêm sinh viên");
            System.out.println("2. Xem tất cả sinh viên");
            System.out.println("3. Tìm sinh viên theo ID");
            System.out.println("4. Cập nhật sinh viên");
            System.out.println("5. Xóa sinh viên");
            System.out.println("6. Demo Transaction");
            System.out.println("0. Thoát");
            System.out.print("Chọn chức năng: ");
            choice = sc.nextInt();
            sc.nextLine(); // consume newline
            switch (choice) {
                case 1:
                    System.out.print("Nhập tên: ");
                    String name = sc.nextLine();
                    System.out.print("Nhập tuổi: ");
                    int age = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Nhập email: ");
                    String email = sc.nextLine();
                    Student s = new Student(name, age, email);
                    dao.insertStudent(s);
                    break;
                case 2:
                    System.out.println("Danh sách sinh viên:");
                    dao.getAllStudents().forEach(System.out::println);
                    break;
                case 3:
                    System.out.print("Nhập ID: ");
                    Long id = sc.nextLong();
                    Student found = dao.getStudentById(id);
                    System.out.println(found != null ? found : "Không tìm thấy!");
                    break;
                case 4:
                    System.out.print("Nhập ID cần sửa: ");
                    Long updateId = sc.nextLong();
                    sc.nextLine();
                    Student updateStu = dao.getStudentById(updateId);
                    if (updateStu != null) {
                        System.out.print("Tên mới: ");
                        updateStu.setName(sc.nextLine());
                        System.out.print("Tuổi mới: ");
                        updateStu.setAge(sc.nextInt());
                        sc.nextLine();
                        System.out.print("Email mới: ");
                        updateStu.setEmail(sc.nextLine());
                        dao.updateStudent(updateStu);
                    }
                    break;
                case 5:
                    System.out.print("Nhập ID cần xóa: ");
                    Long deleteId = sc.nextLong();
                    dao.deleteStudent(deleteId);
                    break;
                case 6:
                    dao.transferExample();
                    break;
                case 0:
                    System.out.println("Tạm biệt!");
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ!");
            }
        } while (choice != 0);
        sc.close();
    }

}
