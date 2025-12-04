/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myinputconfirmmvc;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

/**
 *
 * @author admin
 */

public class MyInputConfirmController {
    
    MyInputConfirmView view;

    public MyInputConfirmController(MyInputConfirmView view) {
        
        this.view = view;
        
        view.okButton.setOnAction(event -> buttonAction());
        
    }
    
    public void initBindings() {
    
    
        BooleanBinding bb = Bindings.or(view.tfd1.textProperty().isEmpty(), view.tfd1.textProperty().isNotEqualTo(view.tfd2.textProperty()));
        
        view.okButton.disableProperty().bind(bb);
    
    
    }
    
    private void buttonAction() {
        
        System.out.println("Cliccato!");
    }
    
}
