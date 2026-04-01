package controller;

import dao.studentDAO;
import entity.Student;
import view.StudentView;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class StudentController {
    private StudentView studentView;

    public StudentController(StudentView studentView){
        this.studentView = studentView;

        studentView.addAddStudentListener(new AddStudentListener());
        studentView.addEdiStudentListener(new EditStudentListener());
        studentView.addDeleteStudentListener(new DeleteStudentListener());
        studentView.addClearListener(new ClearStudentListener());
        studentView.addSortStudentGPAListener(new SortStudentGPAListener());
        studentView.addSortStudentNameListener(new SortStudentNameListener());
        studentView.addListStudentSelectionListener(new ListStudentSelectionListener());
    }

    public void showStudent(){
        ArrayList<Student> students = studentDAO.getInstance().getAll();
        studentView.setVisible(true);
        studentView.showListStudents(students);
    }

    class AddStudentListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            Student student = studentView.getStudentInfo();
            if (student != null) {
                studentDAO.getInstance().insert(student);
                studentView.showStudent(student);
                studentView.showListStudents(studentDAO.getInstance().getAll());
                studentView.showMessage("Thêm thành công!");
            }
        }
    }

    /**
     * Lớp EditStudentListener
     * chứa cài đặt cho sự kiện click button "Edit"
     *
     * @author viettuts.vn
     */
    class EditStudentListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            Student student = studentView.getStudentInfo();
            if (student != null) {
                studentDAO.getInstance().update(student);
                studentView.showStudent(student);
                studentView.showListStudents(studentDAO.getInstance().getAll());
                studentView.showMessage("Cập nhật thành công!");
            }
        }
    }

    class DeleteStudentListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            Student student = studentView.getStudentInfo();
            if (student != null) {
                studentDAO.getInstance().delete(student);
                studentView.clearStudentInfo();
                studentView.showListStudents(studentDAO.getInstance().getAll());
                studentView.showMessage("Xóa thành công!");
            }
        }
    }

    /**
     * Lớp ClearStudentListener
     * chứa cài đặt cho sự kiện click button "Clear"
     *
     * @author viettuts.vn
     */
    class ClearStudentListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            studentView.clearStudentInfo();
        }
    }

    /**
     * Lớp SortStudentGPAListener
     * chứa cài đặt cho sự kiện click button "Sort By GPA"
     *
     * @author viettuts.vn
     */
    class SortStudentGPAListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            studentDAO.getInstance().sortByGpa();
            studentView.showListStudents(studentDAO.getInstance().getAll());
        }
    }

    /**
     * Lớp SortStudentGPAListener
     * chứa cài đặt cho sự kiện click button "Sort By Name"
     *
     * @author viettuts.vn
     */
    class SortStudentNameListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            studentDAO.getInstance().sortByName();
            studentView.showListStudents(studentDAO.getInstance().getAll());
        }
    }

    /**
     * Lớp ListStudentSelectionListener
     * chứa cài đặt cho sự kiện chọn student trong bảng student
     *
     * @author viettuts.vn
     */
    class ListStudentSelectionListener implements ListSelectionListener {
        public void valueChanged(ListSelectionEvent e) {
            studentView.fillStudentFromSelectedRow();
        }
    }
}
