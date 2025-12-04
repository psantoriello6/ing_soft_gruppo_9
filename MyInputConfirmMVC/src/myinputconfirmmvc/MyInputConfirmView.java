/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myinputconfirmmvc;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 *
 * @author admin
 */
public class MyInputConfirmView extends VBox {
    Label lbl1;
    Label lbl2;
    TextField tfd1;
    TextField tfd2;
    Button okButton;
    
    public MyInputConfirmView() {
        lbl1 = new Label();
        lbl2 = new Label();
        tfd1 = new TextField();
        tfd2 = new TextField();
        okButton = new Button();
        
        lbl1.setText("Input:");
        lbl2.setText("Confirm Input:");
        okButton.setText("OK");
           HBox raw1 = new HBox();
        raw1.getChildren().addAll(lbl1, tfd1);
        raw1.setSpacing(10);
        raw1.setAlignment(Pos.CENTER);
        
        //raw2
        
        HBox raw2 = new HBox();
        raw2.getChildren().addAll(lbl2, tfd2);
        raw2.setSpacing(10);
        raw2.setAlignment(Pos.CENTER);
        
       //container
        
        this.setAlignment(Pos.CENTER);
        this.getChildren().addAll(raw1,raw2,okButton);
        this.setSpacing(20);
    }
      
}
