package dao;

import entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

public class StudentDAO {
    private final EntityManagerFactory emf;
    public StudentDAO() {
        emf = Persistence.createEntityManagerFactory("StudentPU");
    }
    // CREATE - Thêm sinh viên
    public void create(Student student) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(student);
            em.getTransaction().commit();
            System.out.println("✅ Thêm sinh viên thành công: " + student);
        } catch (Exception e) {
            if (em.getTransaction().isActive())
                em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    // READ ALL - Lấy danh sách tất cả
    public List<Student> findAll() {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT s FROM Student s",
                    Student.class).getResultList();
        } finally {
            em.close();
        }
    }

    // READ BY ID
    public Student findById(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Student.class, id);
        } finally {
            em.close();
        }
    }

    // UPDATE
    public void update(Student student) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(student);
            em.getTransaction().commit();
            System.out.println("✅ Cập nhật thành công: " + student);
        } catch (Exception e) {
            if (em.getTransaction().isActive())
                em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    // DELETE
    public void delete(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Student student = em.find(Student.class, id);
            if (student != null) {
                em.remove(student);
                em.getTransaction().commit();
                System.out.println("✅ Xóa sinh viên ID=" + id + " thành công!");
            } else {
                System.out.println("❌ Không tìm thấy sinh viên ID=" + id);
            }
        } catch (Exception e) {
            if (em.getTransaction().isActive())
                em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    // Đóng factory khi kết thúc chương trình
    public void close() {
        emf.close();
    }
}
