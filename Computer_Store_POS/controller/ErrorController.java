package controller;

import au.edu.uts.ap.javafx.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.text.Text;
import model.Catalogue;

public class ErrorController extends Controller<Catalogue>{
    
    //closes the window
    @FXML public void closeButtonAction(ActionEvent event) {
    stage.close();
    }
    
    

}
