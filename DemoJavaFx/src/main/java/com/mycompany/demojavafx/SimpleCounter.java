package com.mycompany.demojavafx;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class SimpleCounter extends Application {
    private TextField tfCount;
    private Button btnCount;
    private int count = 0;

    @Override
    public void start(Stage stage) throws Exception {
        tfCount = new TextField();
        tfCount.setText("0");
        tfCount.setEditable(false);
        btnCount = new Button();
        btnCount.setText("count");
        btnCount.setOnAction(
                evt -> tfCount.setText(count++ + "")
        );

        // create Scene
        FlowPane flowPane = new FlowPane();
        flowPane.setPadding(new Insets(15,12,15,12)); // top left right bottom
        flowPane.setVgap(10); // vertical gap between nodes in pixels
        flowPane.setHgap(10); // horizontal gap ...
        flowPane.setAlignment(Pos.CENTER); // alignment
        flowPane.getChildren().addAll(new Label("Count: "), tfCount, btnCount);

        // setup scene and stage
        stage.setScene(new Scene(flowPane,400,80));
        stage.setTitle("Demo Counter");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
