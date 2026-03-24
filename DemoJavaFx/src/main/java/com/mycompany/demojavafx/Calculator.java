package com.mycompany.demojavafx;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;

import javafx.event.EventHandler;

import java.awt.*;

public class Calculator extends Application {

    private TextField tfDisplay;

    private Button[] btns;
    private String[] btnLabels = {  // Labels of 16 buttons
            "7", "8", "9", "+",
            "4", "5", "6", "-",
            "1", "2", "3", "x",
            "C", "0", "=", "/"
    };

    private int result = 0;
    private String inStr = "0";
    private char lastOperator = ' ';

    EventHandler handler = evt -> {
        String currentBtnLabel = ((Button)evt.getSource()).getText();
        switch (currentBtnLabel){
            case "0": case "1": case "2": case "3": case "4":
            case "5": case "6": case "7": case "8": case "9":
            if (inStr.equals("0")){
                inStr = currentBtnLabel;
            }
            else {
                inStr += currentBtnLabel;
            }
            tfDisplay.setText(inStr);
            if(lastOperator == '='){
                result = 0;
                lastOperator = ' ';
            }
            break;

            case "+":
                compute();
                lastOperator = '+';
                break;
            case "-":
                compute();
                lastOperator = '-';
                break;
            case "*":
                compute();
                lastOperator = '*';
                break;
            case "/":
                compute();
                lastOperator = '/';
                break;
            case "=":
                compute();
                lastOperator = '=';
                break;
            // Clear button
            case "C":
                result = 0;
                inStr = "0";
                lastOperator = ' ';
                tfDisplay.setText("0");
                break;
        }
    };
    private void compute() {
        int inNum = Integer.parseInt(inStr);
        inStr = "0";
        if (lastOperator == ' ') {
            result = inNum;
        } else if (lastOperator == '+') {
            result += inNum;
        } else if (lastOperator == '-') {
            result -= inNum;
        } else if (lastOperator == '*') {
            result *= inNum;
        } else if (lastOperator == '/') {
            result /= inNum;
        } else if (lastOperator == '=') {
            // Keep the result for the next operation
        }
        tfDisplay.setText(result + "");
    }



    @Override
    public void start(Stage primaryStage) throws Exception {
        // Setup the Display TextField
        tfDisplay = new TextField("0");
        tfDisplay.setEditable(false);
        tfDisplay.setAlignment(Pos.CENTER_RIGHT);

        // Setup a GridPane for 4x4 Buttons
        int numCols = 4;
        int numRows = 4;
        GridPane paneButton = new GridPane();
        paneButton.setPadding(new Insets(15, 0, 15, 0));  // top, right, bottom, left
        paneButton.setVgap(5);  // Vertical gap between nodes
        paneButton.setHgap(5);  // Horizontal gap between nodes
        // Setup 4 columns of equal width, fill parent
        ColumnConstraints[] columns = new ColumnConstraints[numCols];
        for (int i = 0; i < numCols; ++i) {
            columns[i] = new ColumnConstraints();
            columns[i].setHgrow(Priority.ALWAYS) ;  // Allow column to grow
            columns[i].setFillWidth(true);  // Ask nodes to fill space for column
            paneButton.getColumnConstraints().add(columns[i]);
        }

        // Setup 16 Buttons and add to GridPane; and event handler
        btns = new Button[16];
        for (int i = 0; i < btns.length; ++i) {
            btns[i] = new Button(btnLabels[i]);
            btns[i].setOnAction(handler);  // Register event handler
            btns[i].setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);  // full-width
            paneButton.add(btns[i], i % numCols, i / numCols);  // control, col, row
        }

        // Setup up the scene graph rooted at a BorderPane (of 5 zones)
        BorderPane root = new BorderPane();
        root.setPadding(new javafx.geometry.Insets(15, 15, 15, 15));  // top, right, bottom, left
        root.setTop(tfDisplay);     // Top zone contains the TextField
        root.setCenter(paneButton); // Center zone contains the GridPane of Buttons

        // Set up scene and stage
        primaryStage.setScene(new Scene(root, 300, 300));
        primaryStage.setTitle("JavaFX Calculator");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
