package Controller.Seeker;

import Controller.Post.FXMLPostController;
import Model.DTO.ItemSelectedSkill;
import Model.DTO.ItemSkill;
import Model.DTO.SeekerProfile;
import Model.SeekerModel;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Callback;

public class FXMLSeekerController implements Initializable
{
    @FXML
    private TabPane tabPaneSeeker;
    @FXML
    private Tab tabDashboard; @FXML private AnchorPane anchorPaneDashboard; @FXML private ScrollPane scrollPaneDashboard;@FXML private GridPane gridPaneDashboard;
    @FXML
    private Tab tabMyProject;
    @FXML
    private Tab tabFeedBack;
    @FXML
    private Tab tabProfile;
    /*
            Profile
    */
    private SeekerModel seekerModel;
    @FXML
    private ImageView imgvAvatar;
    @FXML
    private TextField txtFirstName, txtLastName, txtAge, txtPhoneNumber, txtFacebook;
    @FXML
    private ComboBox cbbGender, cbbSkill;
    @FXML
    private TextArea txtADesciption;
    @FXML
    private Button btnLogout, btnOK, btnCancel, btnEdit;
    @FXML
    private ListView<ItemSelectedSkill> lvSkill;
    
    private ObservableList<ItemSelectedSkill> observableListSelectedSkill;
    private ObservableList<ItemSkill>observableListSkill;
    private SeekerProfile seekerProfile;
    @FXML
    private String idAccount;
    public void dataTransmission(String id)
    {
        idAccount = id;
        loadData();
    }
    private void loadData()
    {
        try
        {
            seekerModel = new SeekerModel();
            seekerProfile = seekerModel.getSeekerProfile(idAccount);
            observableListSkill = seekerModel.getListSkill();
            observableListSelectedSkill = seekerModel.getListSelectedSkill(idAccount);
            cbbSkill.getItems().setAll(observableListSkill);
            
            lvSkill.setItems(observableListSelectedSkill);
            
            txtFirstName.setText(seekerProfile.getFIRST_NAME());
            txtLastName.setText(seekerProfile.getLAST_NAME());
            txtAge.setText(""+seekerProfile.getAGE());
            txtPhoneNumber.setText(seekerProfile.getPHONE_NUMBER());
            txtADesciption.setText(seekerProfile.getSEEKER_DESCRIPTION());
            cbbGender.setPromptText((seekerProfile.getGENDER() == true )? "Male":"Female");
            txtFacebook.setText(seekerProfile.getFACEBOOK());
            
            imgvAvatar.setImage(new Image( (seekerProfile.getIMAGE() != null)? seekerProfile.getIMAGE():"\\RelateSource\\Image\\User.png"));
            
        }
        catch (SQLException | ClassNotFoundException ex)
        {
            Logger.getLogger(FXMLSeekerController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        tabPaneSeeker.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Tab>()
        {
            @Override
            public void changed(ObservableValue<? extends Tab> observable, Tab oldTab, Tab newTab)
            {
                if(newTab == tabDashboard)
                {
                    if(anchorPaneDashboard.getChildren().size() > 0)
                        anchorPaneDashboard.getChildren().remove(0);
                }
            }
        });
        
        lvSkill.setCellFactory(new Callback< ListView<ItemSelectedSkill>, ListCell<ItemSelectedSkill> >()
        {
            @Override
            public ListCell<ItemSelectedSkill> call(ListView<ItemSelectedSkill> p)
            {
                ListCell<ItemSelectedSkill> listCell = new ListCell<ItemSelectedSkill>()
                {
                    @Override
                    protected void updateItem(ItemSelectedSkill itemSlectedSkill, boolean empty)
                    {
                        super.updateItem(itemSlectedSkill, empty);
                        if(itemSlectedSkill != null)
                        {
                            setText(itemSlectedSkill.getSkill().getNAME_SKILL());
                            setId(itemSlectedSkill.getSkill().getID_SKILL());
                        }
                        else
                        {
                            setText("");
                            setId("");
                        }
                    };
                };
                return listCell;
            }
        });
        ObservableList<String> listGender = FXCollections.observableArrayList
        (
                "Male",
                "Female"
        );
        cbbGender.setItems(listGender);
        cbbGender.getSelectionModel().select(0);
        
        txtFirstName.setDisable(true);
        txtLastName.setDisable(true);
        txtPhoneNumber.setDisable(true);
        txtADesciption.setDisable(true);
        cbbGender.setDisable(true);
        cbbSkill.setDisable(true);
        txtAge.setDisable(true);
        lvSkill.setDisable(true);
        txtFacebook.setEditable(false);
        imgvAvatar.setDisable(true);
        
        btnOK.setVisible(false);
        btnCancel.setVisible(false);
    }
    @FXML
    public void btnEditEvent(ActionEvent e)
    {
        txtFirstName.setDisable(false);
        txtLastName.setDisable(false);
        txtPhoneNumber.setDisable(false);
        txtADesciption.setDisable(false);
        txtAge.setDisable(false);
        txtFacebook.setEditable(true);
        cbbGender.setDisable(false);
        cbbSkill.setDisable(false);
        lvSkill.setDisable(false);
        imgvAvatar.setDisable(false);
        
        btnOK.setVisible(true);
        btnCancel.setVisible(true);
        btnEdit.setVisible(false);
    }
    private boolean checkDataPforile()
    {   
        if(txtFirstName.getText().length() != 0 ||
           txtLastName.getText().length() != 0 ||
           Integer.parseInt( txtAge.getText()) > 0 ||
           txtPhoneNumber.getText().length() == 10 ||
           txtADesciption.getText().length() != 0)
            return true;
        
        return false;
    }
    @FXML
    public void btnOKEvent(ActionEvent e)
    {
        txtFirstName.setDisable(true);
        txtLastName.setDisable(true);
        txtPhoneNumber.setDisable(true);
        txtADesciption.setDisable(true);
        cbbGender.setDisable(true);
        txtAge.setDisable(true);
        cbbSkill.setDisable(true);
        lvSkill.setDisable(true);
        txtFacebook.setEditable(false);
        imgvAvatar.setDisable(true);
        SeekerProfile seekerProfilechanged = new SeekerProfile();
        
        if(checkDataPforile())
        {
            seekerProfilechanged.setID_ACCOUNT(idAccount);
            seekerProfilechanged.setFIRST_NAME(txtFirstName.getText());
            seekerProfilechanged.setLAST_NAME(txtLastName.getText());
            seekerProfilechanged.setAGE(Integer.parseInt(txtAge.getText()));
            seekerProfilechanged.setGENDER( (cbbGender.getSelectionModel().getSelectedItem().equals("Male"))?true:false);
            seekerProfilechanged.setPHONE_NUMBER( txtPhoneNumber.getText());
            seekerProfilechanged.setFACEBOOK(txtFacebook.getText());
            seekerProfilechanged.setSEEKER_DESCRIPTION(txtADesciption.getText());
            seekerProfilechanged.setIMAGE(imgvAvatar.getImage().getUrl());
            try
            {
                seekerModel.updateSeekerProfile(seekerProfilechanged);
            }
            catch (ClassNotFoundException | SQLException ex)
            {
                Logger.getLogger(FXMLSeekerController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        else
        {
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setContentText("Invalid Information!");
            a.show();
        }


        btnOK.setVisible(false);
        btnCancel.setVisible(false);
        btnEdit.setVisible(true);
        
        
    }
    @FXML
    public void btnCancelEvent(ActionEvent e)
    {
        
    }
    @FXML
    public void btnLogoutEvent(ActionEvent e)
    {
        try
        {
            Parent LoginView = FXMLLoader.load(getClass().getResource("/View/LogIn/FXMLLogIn.fxml"));
            Scene LoginScene = new Scene(LoginView);
            
            Stage LoginStage = (Stage)((Node)e.getSource()).getScene().getWindow();
            LoginStage.setScene(LoginScene);
            LoginStage.show();
        }
        catch (IOException ex)
        {
            Logger.getLogger(FXMLSeekerController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void cbbSkillEvent(ActionEvent e)
    {
        try
        {
            ItemSkill itemSkill = new ItemSkill();
            itemSkill = (ItemSkill)cbbSkill.getValue();
            ItemSelectedSkill itemSelectedSkill = new ItemSelectedSkill();
            itemSelectedSkill.setID_ACCOUNT(idAccount);
            itemSelectedSkill.setSkill(itemSkill);
            for(ItemSelectedSkill i : observableListSelectedSkill)
            {
                if(i.getSkill().getID_SKILL().equals(itemSelectedSkill.getSkill().getID_SKILL()))
                    return;
            }
            seekerModel.addItemSelectedSkill(itemSelectedSkill);
            observableListSelectedSkill.add(itemSelectedSkill);
        }
        catch (ClassNotFoundException | SQLException ex)
        {
            Logger.getLogger(FXMLSeekerController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void lvSkillEvent(MouseEvent e)
    {
        if(e.getButton().equals(MouseButton.PRIMARY))
            if(e.getClickCount() == 2)
            {
                try
                {
                    ItemSelectedSkill itemSelectedSkill = lvSkill.getSelectionModel().getSelectedItem();
                    observableListSelectedSkill.remove(itemSelectedSkill);
                    seekerModel.deleteItemSelectedSkill(itemSelectedSkill.getSkill().getID_SKILL());
                }
                catch (ClassNotFoundException | SQLException ex)
                {
                    Logger.getLogger(FXMLSeekerController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
    }
    public void txtFacebookEvent(MouseEvent event)
    {
        if(txtFacebook.editableProperty().getValue() == false && event.getButton().equals(MouseButton.PRIMARY) && event.getClickCount() == 2)
        {
            Application app = new Application()
            {
                @Override
                public void start(Stage stage) throws Exception {
                    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }
            };
            app.getHostServices().showDocument(txtFacebook.getText());
        }
    }
    public void imgvAvatarEvent(MouseEvent event)
    {
        if(event.getButton().equals(MouseButton.PRIMARY) && event.getClickCount() == 2)
        {
            FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().addAll
            (
                    new FileChooser.ExtensionFilter("All" , "*.*"),
                    new FileChooser.ExtensionFilter(".png" , "*.png"),
                    new FileChooser.ExtensionFilter(".jpg" , "*.jpg")
            );
            fileChooser.setInitialDirectory(new File("C:/Users/lehon/Documents/NetBeansProjects/AppForFindJob/src/RelateSource/Image"));
            fileChooser.setTitle("Choice Image");
            File file = fileChooser.showOpenDialog(null);
            if(file != null)
            {
                imgvAvatar.setImage( new Image(file.getPath().replace("C:\\Users\\lehon\\Documents\\NetBeansProjects\\AppForFindJob\\src", "")));
            }
        }
    }
    
    /*
        Dashboard
    */
    @FXML
    private ObservableList<FXMLPostController> listPost;
    
    public GridPane loadDashboard(int rows)
    {
        GridPane gridPane = new GridPane();
        FXMLLoader fxmlLoader = new FXMLLoader();
        
        for(int i = 0; i < rows; i++)
        {
            RowConstraints rowConstraints = new RowConstraints();
            rowConstraints.setPrefHeight(325);
            gridPane.getRowConstraints().add(rowConstraints);
            try
            {
                AnchorPane post = fxmlLoader.load(getClass().getResource("\\View\\Post\\FXMLLogIn.fxml"));
                gridPane.addRow(i, post);
            }
            catch (IOException ex)
            {
                Logger.getLogger(FXMLSeekerController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        ColumnConstraints columnConstraints = new ColumnConstraints();
        columnConstraints.setPercentWidth(696);
        gridPane.getColumnConstraints().add(columnConstraints);
        
        return gridPane;
    }
}
