package Controller.LogIn;

import Controller.Seeker.FXMLSeekerController;
import Model.AccountModel;
import Model.DTO.Account;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class FXMLLogInController implements Initializable
{
    Account account;
    AccountModel accountModel;
    @FXML
    private Button btnLogin;
    @FXML
    private Button btnSignup;
    @FXML
    private TextField txtUsername;
    @FXML
    private PasswordField pwdPassword;
    
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        
    }    
    
    public void btnLoginEvent(ActionEvent event)
    {
        accountModel = new AccountModel();
        String username = txtUsername.getText();
        String password = pwdPassword.getText();
        String mess = "";
        if(username.length() == 0 || password.length() == 0)
        {
            mess = "Empty username or password";
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText(mess);
            alert.show();
        }
        else
        {
            if(accountModel.checkAccount(username, password))
            {
                try
                {
                    account = accountModel.getAccount(username, password);

                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/View/Seeker/FXMLSeeker.fxml"));
                    Parent SignupView = (Parent)fxmlLoader.load();
                    Scene SignupScene = new Scene(SignupView);
                    
                    FXMLSeekerController seekerController = fxmlLoader.getController();
                    seekerController.dataTransmission(account.getID_ACCOUNT());
                    
                    Stage SignupStage = (Stage)((Node)event.getSource()).getScene().getWindow();
                    SignupStage.setScene(SignupScene);
                    SignupStage.show();
                }
                catch (IOException ex)
                {
                    Logger.getLogger(FXMLLogInController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            else
            {
                mess = "Wrong username or password!";
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText(mess);
                alert.show();
            }
        }
        
    }
    public void btnSignupEvent(ActionEvent event)
    {
        try
        {
            Parent SignupView = FXMLLoader.load(getClass().getResource("/View/LogIn/FXMLSignup.fxml"));
            Scene SignupScene = new Scene(SignupView);
            
            Stage SignupStage = (Stage)((Node)event.getSource()).getScene().getWindow();
            SignupStage.setScene(SignupScene);
            SignupStage.show();
        }
        catch (IOException ex)
        {
            Logger.getLogger(FXMLLogInController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
