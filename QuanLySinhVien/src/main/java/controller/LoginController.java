package controller;

import dao.userDAO;
import entity.User;
import view.LoginView;
import view.StudentView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginController {
    private LoginView loginView;
    private StudentView studentView;

    public LoginController(LoginView loginView){
        this.loginView = loginView;
        loginView.addLoginListener(new LoginListener());
    }

    public void showLoginView(){
        loginView.setVisible(true);
    }

    class LoginListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            User user = loginView.getUser();
            if (userDAO.getInstance().checkUser(user)) {
                // nếu đăng nhập thành công, mở màn hình quản lý sinh viên
                studentView = new StudentView();
                StudentController studentController = new StudentController(studentView);
                studentController.showStudent(); // showStudentView
                loginView.setVisible(false);
            } else {
                loginView.showMessage("username hoặc password không đúng.");
            }
        }
    }

}
