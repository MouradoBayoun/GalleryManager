package GuiClasses;

import GalleryClasses.*;
import javafx.application.Platform;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.CheckBoxListCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javax.naming.Name;
import javax.swing.table.*;
import javax.swing.text.*;
import java.awt.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    //Artist Tab
    @FXML
    private ChoiceBox<String > specializationChoiceBox;

    @FXML
    private TextField artistFirstNameID;

    @FXML
    private TextField artistLastNameID;

    @FXML
    private TextField minPriceID;

    @FXML
    private TextField maxPriceID;

    @FXML
    private RadioButton isAliveRadioButtonID;

    @FXML
    private CheckBox photographCheckBoxID;

    @FXML
    private CheckBox lithographsCheckBoxID;

    @FXML
    private CheckBox othersCheckBoxID;

    @FXML
    private Button addArtistButtonID;

    @FXML
    private Button deleteArtistButtonID;


    //tableveiw content
    @FXML
    private TableView<Artist> artistTableView;
    @FXML
    private TableColumn<Artist , String> firstNameCol;
     @FXML
    private TableColumn<Artist , String> lastNameCol;
     @FXML
    private TableColumn<Artist , String> specCol;
    @FXML
    private TableColumn<Artist , Integer> minPriceCol;
    @FXML
    private TableColumn<Artist , Integer> maxPriceCol;
    @FXML
    private TableColumn<Artist , Boolean> aliveCol;

    //Costumer Tab__________
    @FXML
    private TextField customerFirstNameID;

    @FXML
    private TextField customerLastNameID;

    @FXML
    private TextField customerPhoneID;

    @FXML
    private TextField customerAddressID;
    @FXML
    private Button deleteCustomertButtonID;
    @FXML
    private Button addCustomerButtonID;
    @FXML
    private ChoiceBox<String> customerPreferdArtistChoice;
    @FXML
    private Button customerPreferdArtistBtn;
    @FXML
    private ChoiceBox<String> customerPreferdArtworkChoice;
    @FXML
    private Button customerPreferdArtworkBtn;

    //customer tableiew
    @FXML
    private TableView<Customer> customerTableView;
    @FXML
    private TableColumn<Customer, String > costumerfirstNameCol;
    @FXML
    private TableColumn<Customer, String> costumerlastNameCol;
    @FXML
    private TableColumn<Customer, String> customerphoneNumberCol;
    @FXML
    private TableColumn<Customer, String> customerAddressCol;
    @FXML
    private TableColumn<Customer, String > artistPreferencesCol;
    @FXML
    private TableColumn<Customer ,String> artWorkPreferencesCol;




    //private ArtistCollection artistsData = new ArtistCollection();
    //private CustomerCollection customerData = new CustomerCollection();
    private InventoryCollection inventoryCollectiondata = new InventoryCollection();

    public void showAlert(String message) {
        Stage window = new Stage();

        //Block events to other windows
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Error");
        window.setMinWidth(250);

        Label label = new Label();
        label.setText(message);
        Button closeButton = new Button("OK");
        closeButton.setOnAction(e -> window.close());

        VBox layout = new VBox(10);
        layout.getChildren().addAll(label, closeButton);
        layout.setAlignment(Pos.CENTER);

        //Display window and wait for it to be closed before returning
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {


        // filling some data
        List<String> sp = new ArrayList<String>();
        sp.add("lethographs");

        inventoryCollectiondata.artists.add(new Artist("louay" , "aboalzahab" , true , sp ,  0, 100));
        sp = new ArrayList<String>();
        sp.add("photographs");
        sp.add("lethographs");
        sp.add("other");
        inventoryCollectiondata.artists.add(new Artist("zaza" , "allazaza" , true , sp ,  0, 100));

        inventoryCollectiondata.customers.add(new Customer("louay" , "abo" , "0957766300" , "yafour" , inventoryCollectiondata.artists.getArtists() , new LinkedList<ArtWork>()) );
        inventoryCollectiondata.customers.add(new Customer("omar" , "malass" , "0980766300" , "Qura" , new LinkedList<Artist>() , new LinkedList<ArtWork>()) );
        //Artist table
        firstNameCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));//cellData -> cellData.getValue().firtNameProperty().concat(new SimpleStringProperty(" ").concat( cellData.getValue().lastNameProperty() )) );
        lastNameCol.setCellValueFactory(new PropertyValueFactory<Artist, String>("lastName"));
        specCol.setCellValueFactory(new PropertyValueFactory<>("specializations"));
        minPriceCol.setCellValueFactory(new PropertyValueFactory<Artist, Integer>("minPrice"));
        maxPriceCol.setCellValueFactory(new PropertyValueFactory<Artist, Integer>("maxPrice"));
        aliveCol.setCellValueFactory(cellData -> new SimpleBooleanProperty(cellData.getValue().isAlive()) );

        artistTableView.setItems(inventoryCollectiondata.artists.getArtists());

        //Artwork Table


        //Costumer Table
        costumerfirstNameCol.setCellValueFactory(new PropertyValueFactory<Customer, String>("firstName"));
        costumerlastNameCol.setCellValueFactory(new PropertyValueFactory<Customer, String>("lastName"));
        customerphoneNumberCol.setCellValueFactory(new PropertyValueFactory<Customer, String>("phoneNumber"));
        customerAddressCol.setCellValueFactory(new PropertyValueFactory<Customer, String>("address"));
        artistPreferencesCol.setCellValueFactory(new PropertyValueFactory<Customer, String>("artistPreferences"));

        customerTableView.setItems(inventoryCollectiondata.customers.getCostumers());

        for (Artist i:
                inventoryCollectiondata.artists.getArtists()) {
            customerPreferdArtistChoice.getItems().add(i.getFirstName() + " " + i.getLastName());
        }
        for(ArtWork i: inventoryCollectiondata.artwoksdata.getArtWorksList()){
            customerPreferdArtworkChoice.getItems().add(i.getTitle());
        }


    }

//Artists Actions--------

    public void addBtnClicked(MouseEvent mouseEvent) { // adding an artist
        List<String> sp = new ArrayList<String>();
        if(photographCheckBoxID.isSelected()) sp.add("photographs");
        if(lithographsCheckBoxID.isSelected())sp.add("lethographs");
        if(othersCheckBoxID.isSelected()) sp.add("other");

        try {
            Integer minPr = Integer.parseInt(minPriceID.getText());
            Integer maxPr = Integer.parseInt(maxPriceID.getText());
            String firstName = artistFirstNameID.getText();
            String lastName = artistLastNameID.getText();
            inventoryCollectiondata.artists.add(new Artist( firstName , lastName , isAliveRadioButtonID.isSelected() ,sp , minPr , maxPr));
            customerPreferdArtistChoice.getItems().add(firstName + " " + lastName);
            artistFirstNameID.setText("");
            artistLastNameID.setText("");
            minPriceID.setText("");
            maxPriceID.setText("");
            isAliveRadioButtonID.setSelected(false);
            lithographsCheckBoxID.setSelected(false);
            photographCheckBoxID.setSelected(false);
            othersCheckBoxID.setSelected(false);
        } catch (NumberFormatException e){ showAlert("some data is not entered"); System.out.println("some fields are empty"); } catch (Exception e){e.printStackTrace();}

    }

    public void deleteBtnClicked(MouseEvent mouseEvent) {
        ObservableList<Artist> productSelected, allProducts;
        allProducts = artistTableView.getItems();
        productSelected = artistTableView.getSelectionModel().getSelectedItems();
        productSelected.forEach(allProducts::remove);
//        Artist tmp;
//        for (Artist ar:
//             productSelected) {
//               tmp = new Artist(ar);
//               inventoryCollectiondata.artists.remove(ar);
//               tmp.setLastName("homara");
//               inventoryCollectiondata.artists.add(tmp);
//        }
    }

    //Customer Actions

    List<Artist> prefArtist = new LinkedList<Artist>();
    List<ArtWork> prefArtwork = new LinkedList<ArtWork>();

    public void addCustBtnClicked(MouseEvent mouseEvent) {
        String firstName = customerFirstNameID.getText();
        String lastName = customerLastNameID.getText();
        String address = customerAddressID.getText();
        String number = customerPhoneID.getText();
        if( addtoPrefArtistList() || addtoPrefArtworkList() || firstName.equals("") || lastName.equals("") || address.equals("") || number.equals("")){
            showAlert("some data is not entered");
        }else {
            inventoryCollectiondata.customers.add(new Customer(firstName, lastName, number, address, prefArtist, prefArtwork));
            prefArtist = new LinkedList<Artist>();
            prefArtwork = new LinkedList<ArtWork>();
            customerFirstNameID.setText("");
            customerLastNameID.setText("");
            customerAddressID.setText("");
            customerPhoneID.setText("");
        }

    }


    public void deleteCustBtnClicked(MouseEvent mouseEvent) {
        ObservableList<Customer> selected, all;
        all = customerTableView.getItems();
        selected = customerTableView.getSelectionModel().getSelectedItems();
        selected.forEach(all::remove);
    }

    public void addPrefArtistBtn(MouseEvent mouseEvent) {
        addtoPrefArtistList();
    }

    public void addPrefArtworkBtn(MouseEvent mouseEvent) {
    }

    public boolean addtoPrefArtistList(){
       if(customerPreferdArtistChoice.getValue() == null)
           return !(prefArtist.isEmpty());
        String artistName = customerPreferdArtistChoice.getValue();
        int idx = artistName.indexOf(" ");
        String artistFirstName = artistName.substring(0 , idx);
        String artistLastName = artistName.substring(idx+1 , artistName.length());

        prefArtist.add(inventoryCollectiondata.artists.findArtist(artistFirstName , artistLastName));
        customerPreferdArtistChoice.getSelectionModel().clearSelection();
        return true;
    }

    public boolean addtoPrefArtworkList(){
        if(customerPreferdArtworkChoice.getValue() == null)
            return !(prefArtwork.isEmpty());
        String title = customerPreferdArtworkChoice.getValue();
        prefArtwork.add(inventoryCollectiondata.artwoksdata.findArtwork(title));
        customerPreferdArtworkChoice.getSelectionModel().clearSelection();
        return true;
    }




}
