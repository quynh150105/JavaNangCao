package com.mycompany.demojavafx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class DemoJavaFx extends Application {
    private Button btnHello;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
            btnHello = new Button();
            btnHello.setText("Hello World");

            btnHello.setOnAction(
                    evt -> System.out.println("Hello World from btnHello")
            );

        StackPane root = new StackPane();
        root.getChildren().add(btnHello);

        Scene scene = new Scene(root, 300, 300);
        stage.setScene(scene);
        stage.setTitle("Hello");
        stage.show();

    }
}
