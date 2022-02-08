package controller;

import au.edu.uts.ap.javafx.Controller;
import static java.awt.SystemColor.text;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.text.Text;
import model.Build;

public class CheckBuildController extends Controller<Build>{
    
   @FXML private Text checkTxt;
   
    
    @FXML public void closeButtonAction(ActionEvent event) {
    stage.close();
    }
    //returns the build model
    public final Build getBuild(){
        return model;
    }
    //returns the get parts model 
    public final Build getParts(){
        return model;
    }
    
    @FXML private void initialize(){
        
        //intiates a string for text to be added, depended on boolean  statements
        String checkString ="";
        if(getParts().isValid()){
        checkString="The build is functional.\n";
        }
        
        //checks if parts list contains a part with cpu
        if(getParts().hasPartOfType("cpu") == false){
        checkString = checkString + "The build is missing a CPU.\n";
        }
        
        //checks if parts list contains a part with motherboard
        if(getParts().hasPartOfType("motherboard") == false){
        checkString = checkString + "The build is missing a motherboard.\n";
        }
               
        //checks if parts list contains a part with memeory
        if(getParts().hasPartOfType("memory") == false){
        checkString = checkString + "The build is missing RAM.\n";
        }
        
        //checks if parts list contains a part with case
        if(getParts().hasPartOfType("case") == false){
        checkString = checkString + "The build is missing a case.\n";

        }
        
        //checks if parts list contains a part with storage
        if(getParts().hasPartOfType("storage") == false){
        checkString = checkString + "The build is missing storage.\n";
        }
        
        //sets the checktxt text in fxml to the updated string
        checkTxt.setText(checkString);
        
    }
    
}
