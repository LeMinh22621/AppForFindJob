package Controller.LogIn;

import Model.AccountModel;
import Model.DTO.Account;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

public class FXMLSignupController implements Initializable
{
    private Alert alert = new Alert(Alert.AlertType.INFORMATION);
    private AccountModel accountModel;
    @FXML
    private TextField txtUsername;
    @FXML
    private PasswordField pwdPassword;
    @FXML
    private PasswordField pwdConfirmPassword;
    @FXML
    private TextField txtEmail;
    @FXML
    private RadioButton rbEmployer;
    @FXML
    private RadioButton rbSeeker;
    @FXML
    private Button btnRegister;
    @FXML
    private Button btnCancel;
    private ToggleGroup tG;
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        tG = new ToggleGroup();
        rbSeeker.setToggleGroup(tG);
        rbSeeker.setSelected(true);
        rbEmployer.setToggleGroup(tG);
    }    
    
    @FXML
    public void btnCancelEvent(ActionEvent event) throws IOException
    {
        Parent LoginView = FXMLLoader.load(getClass().getResource("/View/LogIn/FXMLLogIn.fxml"));
        Scene LoginScene = new Scene(LoginView);
        
        Stage LoginStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        LoginStage.setScene(LoginScene);
        LoginStage.show();
    }
    
    private boolean checkSignupData()
    {
        if(txtUsername.getText().length() == 0 || txtUsername.getText().length() > 20)
        {
            alert.setContentText("Enter Username (length <= 20)");
        }
        else if(pwdPassword.getText().length() == 0 || pwdPassword.getText().length() > 20)
        {
            alert.setContentText("Enter Password (length <= 20)");
        }
        else if(!pwdPassword.getText().equals(pwdConfirmPassword.getText()))
        {
            alert.setContentText("Confirm Password is not correct");
        }
        else if(txtEmail.getText().length() == 0 || ((!txtEmail.getText().contains("@")) || (!txtEmail.getText().contains("."))))
        {
            alert.setContentText("Enter your email must to have '@' and '.'");
        }
        else
        {
            return true;
        }
        alert.show();
        return false;    
    }
    public void btnRegisterEvent(ActionEvent event) throws IOException
    {
        accountModel = new AccountModel();
          if(checkSignupData())
          {
              String idAccount = "A" + (accountModel.selectLastRowCol() + 1);
              Account acc = new Account();
              acc.setID_ACCOUNT(idAccount);
              acc.setUSERNAME(txtUsername.getText());
              acc.setPASSWORD(pwdPassword.getText());
              acc.setEMAIL(txtEmail.getText());
              acc.setACCESSER( (rbSeeker.isSelected() == true)?0:1 );
              acc.setSTATUS(true);
              accountModel.registerAccount(acc);
          }
//        Parent LoginView = FXMLLoader.load(getClass().getResource("/View/LogIn/FXMLLogIn.fxml"));
//        Scene LoginScene = new Scene(LoginView);
//        Stage LoginStage = (Stage)((Node)event.getSource()).getScene().getWindow();
//        LoginStage.setScene(LoginScene);
//        LoginStage.show();
    }
}
