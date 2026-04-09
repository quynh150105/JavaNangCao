package dao;

import entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class StudentDAOHibernate {

    private final SessionFactory sessionFactory;

    public StudentDAOHibernate() {
        // Load cấu hình từ hibernate.cfg.xml
        sessionFactory = new Configuration()
                .configure()
                .buildSessionFactory();
    }

    // ================== CREATE ==================
    public void create(Student student) {
        Transaction tx = null;

        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();

            // Check email đã tồn tại chưa
            Long count = session.createQuery(
                            "SELECT COUNT(s) FROM Student s WHERE s.email = :email",
                            Long.class)
                    .setParameter("email", student.getEmail())
                    .uniqueResult();

            if (count != null && count > 0) {
                System.out.println("❌ Email đã tồn tại: " + student.getEmail());
                return;
            }

            session.persist(student);

            tx.commit();
            System.out.println("✅ Thêm thành công: " + student);

        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            System.out.println("❌ Lỗi khi thêm!");
            e.printStackTrace();
        }
    }

    // ================== READ ALL ==================
    public List<Student> findAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Student", Student.class)
                    .getResultList();
        }
    }

    // ================== READ BY ID ==================
    public Student findById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Student.class, id);
        }
    }

    // ================== UPDATE ==================
    public void update(Student student) {
        Transaction tx = null;

        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();

            session.merge(student);

            tx.commit();
            System.out.println("✅ Cập nhật thành công: " + student);

        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            System.out.println("❌ Lỗi khi cập nhật!");
            e.printStackTrace();
        }
    }

    // ================== DELETE ==================
    public void delete(Long id) {
        Transaction tx = null;

        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();

            Student student = session.get(Student.class, id);

            if (student != null) {
                session.remove(student);
                tx.commit();
                System.out.println("✅ Xóa thành công ID = " + id);
            } else {
                System.out.println("❌ Không tìm thấy ID = " + id);
            }

        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            System.out.println("❌ Lỗi khi xóa!");
            e.printStackTrace();
        }
    }

    // ================== CLOSE ==================
    public void close() {
        if (sessionFactory != null && !sessionFactory.isClosed()) {
            sessionFactory.close();
        }
    }
}