package GuiClasses;

import GalleryClasses.*;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import javax.naming.Name;
import javax.swing.table.*;
import javax.swing.text.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Controller implements Initializable {
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

    public ArtistCollection artistsData = new ArtistCollection();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List<String> sp = new ArrayList<String>();
        sp.add("lethographs");

        artistsData.add(new Artist("louay" , "aboalzahab" , true , sp ,  0, 100));
        sp = new ArrayList<String>();
        sp.add("photographs");
        sp.add("lethographs");
        sp.add("other");
        artistsData.add(new Artist("zaza" , "allazaza" , true , sp ,  0, 100));
        //Artist table
        firstNameCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));//cellData -> cellData.getValue().firtNameProperty().concat(new SimpleStringProperty(" ").concat( cellData.getValue().lastNameProperty() )) );
        lastNameCol.setCellValueFactory(new PropertyValueFactory<Artist, String>("lastName"));
        specCol.setCellValueFactory(new PropertyValueFactory<>("specializations"));
        minPriceCol.setCellValueFactory(new PropertyValueFactory<Artist, Integer>("minPrice"));
        maxPriceCol.setCellValueFactory(new PropertyValueFactory<Artist, Integer>("maxPrice"));
        aliveCol.setCellValueFactory(cellData -> new SimpleBooleanProperty(cellData.getValue().isAlive()) );

        artistTableView.setItems(artistsData.getArtists());

        //Artwork Table


        //Costumer Table

    }


    public void addBtnClicked(MouseEvent mouseEvent) {
        List<String> sp = new ArrayList<String>();
        if(photographCheckBoxID.isSelected()) sp.add("photographs");
        if(lithographsCheckBoxID.isSelected())sp.add("lethographs");
        if(othersCheckBoxID.isSelected()) sp.add("other");

        try {
            Integer minPr = Integer.parseInt(minPriceID.getText());
            Integer maxPr = Integer.parseInt(maxPriceID.getText());
            artistsData.add(new Artist(artistFirstNameID.getText() , artistLastNameID.getText() , isAliveRadioButtonID.isSelected() ,sp , minPr , maxPr));
            artistFirstNameID.setText(null);
            artistLastNameID.setText(null);
            minPriceID.setText("");
            maxPriceID.setText("");
            isAliveRadioButtonID.setSelected(false);
            lithographsCheckBoxID.setSelected(false);
            photographCheckBoxID.setSelected(false);
            othersCheckBoxID.setSelected(false);
        } catch (NumberFormatException e){System.out.println("some fields are empty"); } catch (Exception e){e.printStackTrace();}

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
//               artistsData.remove(ar);
//               tmp.setLastName("homara");
//               artistsData.add(tmp);
//        }
    }


}
