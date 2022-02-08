package controller;

import au.edu.uts.ap.javafx.Controller;
import au.edu.uts.ap.javafx.ViewLoader;
import java.text.DecimalFormat;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Build;
import model.Catalogue;
import model.Part;

public class BuildController extends Controller<Build> {
    
    //links the price coloumn so the format can be modified
    @FXML private TableColumn<Part, String> priceClm;
    //links to fxml file so the table view can be observed when updated
    @FXML private TableView<Part> buildTv;
    //links to fxml file so the button is disabled until an item is selected
    @FXML private Button removeBtn;
    //links the total text field to the fxml file so it be observed
    @FXML private Text totalTxt;
    //creates the totalProp as a SimpleStringProperty
    private final StringProperty totalProp = new SimpleStringProperty();
    
    //formats the total price of the build into a 2 decimal format
    public final String getTotalPrice(){
    DecimalFormat format = new DecimalFormat("0.00");
        totalProp.set("Total: $" + format.format(getBuild().totalPrice()));
        return totalProp.get();
    }
    
    //returns the build method using the get build method
    public final Build getBuild()
    {
        return model;
    }
    //closes the window when button is selected
    @FXML public void closeButtonAction(ActionEvent event) {
    stage.close();
    }
    //calls the remove part from build method on the selected part
    private void removePart(){
    getBuild().remove(getSelectedPart());
    }
    
    //updates the tableview when a part is selected and returns the value that is selected
    private ObservableList<Part> getSelectedPart()
    {
    return buildTv.getSelectionModel().getSelectedItems();
    }
    
    //calls the remove part method when button is selected
    @FXML private void handleRemoveFromBuild(ActionEvent event) throws Exception        
    {
        removePart();
    }
    
    @FXML
    private void initialize() {   
    //converts the price column of the table view into a 2 decimal string format    
    priceClm.setCellValueFactory(cellData -> cellData.getValue().priceProperty().asString("$%.2f"));
    
    //observes the tableview so when a part is selected the remove button will be enabled
    buildTv.getSelectionModel().selectedItemProperty().addListener((removePart) -> 
    removeBtn.setDisable(removePart == null));
    
    //allows for multiple parts to be selected in the table view
    buildTv.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    
    //sets the total text to the SimpleStringProperty retured from the getTotalPrice method
    totalTxt.setText(getTotalPrice());
   
    //observers the totalTxt branch in fxml so that when the total text is updated staright away
    getBuild().getParts().addListener((ListChangeListener.Change<? extends Part> c) -> {
    totalTxt.setText(getTotalPrice());
    
    });
    
   
   
}
    //opens the check build window
     @FXML private void handleCheckBuild(ActionEvent event) throws Exception
    {
        ViewLoader.showStage(getBuild(),"/view/buildcheck.fxml","Build Validity Status",new Stage());
    }



}
