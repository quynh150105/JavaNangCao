import dao.StudentDAO;
import dao.StudentDAOHibernate;
import entity.Student;

public class Main {
    public static void main(String[] args) {
        StudentDAO dao = new StudentDAO();
        System.out.println("=== CHƯƠNG TRÌNH QUẢN LÝ SINH VIÊN - HIBERNATE FRAMEWORK ===\n");
// CREATE
                System.out.println("1. CREATE");
        Student s1 = new Student("Nguyễn Văn An", 20, "an.nguyen@gmail.com");
        Student s2 = new Student("Trần Thị Bình", 21, "binh.tran@gmail.com");
        dao.create(s1);
        dao.create(s2);
// READ ALL
        System.out.println("\n2. READ ALL");
        dao.findAll().forEach(System.out::println);
// READ BY ID
        System.out.println("\n3. READ BY ID");
        System.out.println(dao.findById(1L));
// UPDATE
        System.out.println("\n4. UPDATE");
        Student updateStu = dao.findById(1L);
        if (updateStu != null) {
            updateStu.setName("Nguyễn Văn An - Đã cập nhật");
            updateStu.setAge(23);
            dao.update(updateStu);
        }
// DELETE
        System.out.println("\n5. DELETE");
        dao.delete(2L);
// READ ALL sau khi thay đổi
        System.out.println("\n6. READ ALL sau UPDATE & DELETE");
        dao.findAll().forEach(System.out::println);
        dao.close();
        System.out.println("\n=== KẾT THÚC CHƯƠNG TRÌNH ===");
    }
}
