/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myinputconfirmmvc;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author admin
 */
public class MyInputConfirm extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        
        MyInputConfirmView view = new MyInputConfirmView();
        MyInputConfirmController controller = new MyInputConfirmController(view);
        controller.initBindings();
        
        Scene scene = new Scene(view, 300, 250);
        primaryStage.setTitle("My Input Confirm");
        primaryStage.setScene(scene);
        primaryStage.show();
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
