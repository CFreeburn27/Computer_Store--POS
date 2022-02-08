package controller;

import au.edu.uts.ap.javafx.Controller;
import au.edu.uts.ap.javafx.ViewLoader;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Catalogue;
import model.Part;

public class CatalogueController extends Controller<Catalogue> {
    //enables the price column to be formatted
    @FXML private TableColumn<Part, String> priceClm;
    
    //enables the filtes to be observed and updated instantly when match found
    @FXML private TextField typeFilterTf; 
    @FXML private TextField minPriceFilterTf; 
    @FXML private TextField maxPriceFilterTf;
    
    //links the catalogue table view to be observerd
    @FXML private TableView<Part> catalogueTv;
    
    //enables the add to build and remove button to be disabled until a part is selected
    @FXML private Button removeBtn;
    @FXML private Button addToBuildBtn;
    @FXML private Button openBtn;
    
       //returns the catalogue model using the get catalogue model    
       public final Catalogue getCatalogue()
           {
               return model;
           } 

       //opens the add part to catalogue window when the button is selected
       @FXML private void handleOpenAddToCatalogue(ActionEvent event) throws Exception {
       ViewLoader.showStage(getCatalogue(), "/view/addtocatalogue.fxml", "Add New Part to Catalogue", new Stage());
    }
    //closes the window when the button is selected     
    @FXML public void closeButton(ActionEvent event) {
    stage.close();
}  
    //gets the type filter text from the textfield
    public String getType() {
        return typeFilterTf.getText();
        
    }
    //gets the min price filter text from the textfield
    public String getMinPrice(){
        return minPriceFilterTf.getText();
    }
    
    //gets the max price filter text from the textfield
    public String getMaxPrice(){
        return maxPriceFilterTf.getText();
    }
    
    @FXML
    private void initialize(){
        
        //allows for multiple parts to be selected
        catalogueTv.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        
        //disables the remove part button until a part has been selected
        catalogueTv.getSelectionModel().selectedItemProperty().addListener((removePart) -> 
        removeBtn.setDisable(removePart == null));
        
        //disables the add to build part button until a part has been selected
        catalogueTv.getSelectionModel().selectedItemProperty().addListener((addPart) -> 
        addToBuildBtn.setDisable(addPart == null));
        
        //allows for the type filter to be observed which allows the list be filtered instantly
        typeFilterTf.textProperty().addListener((filType) ->
        getCatalogue().filterList(getType(), getMinPrice(), getMaxPrice()));
        
        //allows for the min price filter to be observed which allows the list be filtered instantly
        minPriceFilterTf.textProperty().addListener((filMinPrice) ->
        getCatalogue().filterList(getType(), getMinPrice(), getMaxPrice()));
        
        //allows for the max price filter to be observed which allows the list be filtered instantly
        maxPriceFilterTf.textProperty().addListener((filMaxPrice) ->
        getCatalogue().filterList(getType(), getMinPrice(), getMaxPrice()));
        
        //converts the price column of the table view into a 2 decimal string format 
        priceClm.setCellValueFactory(cellData -> cellData.getValue().priceProperty().asString("$%.2f"));
        }  
    
           //removes the part that from the list through the get selected model
           private void removePart(){
           getCatalogue().remove(getSelectedPart());
             }
           
         //updates the tableview when a part is selected and returns the value that is selected
        private ObservableList<Part> getSelectedPart(){
           return catalogueTv.getSelectionModel().getSelectedItems();
         }
           
     //calls the remove part method when button is selected      
    @FXML private void handleRemoveFromCatalogue(ActionEvent event) throws Exception        
    {
        removePart();
    }
    
    //calls the addToBuild build function and adda the part that is returned from the get selected part method
    @FXML private void handleAddToBuild(ActionEvent event) throws Exception
    {
        getCatalogue().addToBuild(getSelectedPart());
    
    }
    
}



