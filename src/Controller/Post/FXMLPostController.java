package Controller.Post;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
public class FXMLPostController implements Initializable
{
    @FXML
    private TextArea txtAContents;
    @FXML
    private Label lbSkill, lbAddress, lbTitle;
    @FXML
    private Button btnApply;
    
    public void initialize(URL url, ResourceBundle rb)
    {
        
    }    
}
