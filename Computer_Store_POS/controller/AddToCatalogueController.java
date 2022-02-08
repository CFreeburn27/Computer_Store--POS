package controller;

import au.edu.uts.ap.javafx.Controller;
import au.edu.uts.ap.javafx.ViewLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Catalogue;


public class AddToCatalogueController extends Controller<Catalogue> {
        //returns the Catalogue model using the getCatalogue method
       public Catalogue getCatalogue()
           {
               return model;
           }
     //Text fields that are linked to the fxml file   
     @FXML private TextField typeTf;
     @FXML private TextField nameTf;
     @FXML private TextField priceTf; 
 
     //The 3 get methods take the inputs from the text fields and convert them to the required properties
    private String getType() {
        return typeTf.getText();
    }
    private String getName() {
        return nameTf.getText();
    }
    private Double getPrice() {
            return Double.parseDouble(priceTf.getText());
    }
    
    //checks if the price entered for the new part is is between the correct parameters
    private boolean priceValid(){
    return priceTf.getText().matches("[0-9]+");
    }

    //Action event for when the button is selected it takes inputs from the text fields and calls the add part 
    //method from the catalogue model, only if the priceValid returns true
    @FXML
    public void handleAddPart(ActionEvent event) throws Exception {
        if (priceValid()){
            String type = getType();
            String name = getName();
            Double price = getPrice();
            getCatalogue().addPart(name , type, price);
    //else an error window will display if the valid price returns false        
        }else
            ViewLoader.showStage(model,"/view/error.fxml", "Incorrect Input",new Stage());
        }
       

}
