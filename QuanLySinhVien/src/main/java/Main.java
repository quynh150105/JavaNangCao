import controller.LoginController;
import view.LoginView;

public class   Main {
    public static void  main(String[] args){
//        User user = new User("admin","123");
//
//        userDAO.getInstance().insert(user);

//        ArrayList<User> ds = userDAO.getInstance().getAll();
//        for(User u: ds){
//            System.out.println(u.getUsername());
//        }

//        Student student = Student.builder()
//                .name("quynh")
//                .gpa(3.2f)
//                .age(21)
//                .address("hanoi")
//                .build();
//
//        studentDAO.getInstance().insert(student);

//        Student findById = studentDAO.getInstance().selectById(1);
//        System.out.println("thong tin user duoc lay la: " + findById.getName());

        LoginView loginView = new LoginView();
        LoginController controller = new LoginController(loginView);
        controller.showLoginView();
    }
}
