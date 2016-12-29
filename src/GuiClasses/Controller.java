package GuiClasses;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.net.URL;
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
    private CheckBox LithographsCheckBox;

    @FXML
    private CheckBox otherCheckBox;

    @FXML
    private Button addArtistButtonID;

    @FXML
    private Button deleteArtistButtonID;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }


}
