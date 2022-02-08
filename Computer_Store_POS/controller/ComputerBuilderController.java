package controller;
import au.edu.uts.ap.javafx.Controller;
import au.edu.uts.ap.javafx.ViewLoader;
import javafx.event.*;
import javafx.fxml.FXML;
import javafx.stage.*;
import model.ComputerBuilder;

public class ComputerBuilderController extends Controller<ComputerBuilder> 
{
    
    @FXML
    public void initialize() {

    }
    
   //returns ComputerBuilder model using the getComputerBuilder Method 
   public final ComputerBuilder getComputerBuilder()
           {
               return model;
           }
    
   
       //opens the catalogue window when button is selected
       @FXML private void handleOpenCatalogue(ActionEvent event) throws Exception {
       ViewLoader.showStage(model.getCatalogue(), "/view/catalogue.fxml", "Catalogue", new Stage());
       
    }
       //opens the build window when button is selected
       @FXML private void handleOpenBuild(ActionEvent event) throws Exception {
       ViewLoader.showStage(model.getBuild(), "/view/build.fxml", "Current Build", new Stage());
    }
       //exists the program when button is selected
        @FXML public void handleExit(ActionEvent event) {
                System.exit(0);
        }
}