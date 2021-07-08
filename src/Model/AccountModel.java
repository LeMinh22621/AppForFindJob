package Model;
import Model.DTO.Account;
import Model.Database.DBHelper;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;
public class AccountModel
{
    private Alert alert = new Alert(Alert.AlertType.INFORMATION);
    public boolean checkAccount(String username, String password)
    {
        boolean result = false;
        try
        {
            String query = "SELECT * FROM TB_ACCOUNT WHERE USERNAME = '" + username + "' and PASSWORD = '" + password + "' and STATUS = 'True'";
            ResultSet rs = DBHelper.getInstance().select(query);
            
            if(rs.next())
            {
                result = true;
                rs.close();
            }
            DBHelper.getInstance().close();
        }
        catch (SQLException | ClassNotFoundException ex)
        {
            Logger.getLogger(AccountModel.class.getName()).log(Level.SEVERE, null, ex);
        }
       return result;
    }
    public boolean checkEmail(String email, int accesser)
    {
        boolean result = false;
        try
        {
            String query = "SELECT * FROM TB_ACCOUNT WHERE EMAIL = '" + email + "' and ACCESSER = '" + accesser + "'";
            ResultSet rs = DBHelper.getInstance().select(query);
            
            if(rs.next())
            {
                result = true;
                rs.close();
            }
            DBHelper.getInstance().close();
        }
        catch (SQLException | ClassNotFoundException ex)
        {
            Logger.getLogger(AccountModel.class.getName()).log(Level.SEVERE, null, ex);
        }
       return result;
    }
    public Account getAccount(String username, String password)
    {
        Account account = new Account();
        try
        {
            String query = "SELECT * FROM TB_ACCOUNT WHERE USERNAME = '" + username + "' and PASSWORD = '" + password + "'";
            ResultSet rs = DBHelper.getInstance().select(query);
            if(rs.next())
            {
                account.setID_ACCOUNT( rs.getString("ID_ACCOUNT"));
                account.setUSERNAME(rs.getString("USERNAME"));
                account.setPASSWORD(rs.getString("PASSWORD"));
                account.setEMAIL(rs.getString("EMAIL"));
                account.setACCESSER(rs.getInt("ACCESSER"));
                account.setSTATUS(rs.getBoolean("STATUS"));
            }
            rs.close();
            DBHelper.getInstance().close();
        }
        catch (SQLException | ClassNotFoundException ex)
        {
            Logger.getLogger(AccountModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return account;
    }
    public void registerAccount(Account acc)
    {
        if(checkAccount(acc.getUSERNAME(), acc.getPASSWORD()))
        {
            alert.setContentText("this Username had registed!");
            alert.show();
            return;
        }
        else
        {
            String query = "INSERT INTO TB_ACCOUNT(ID_ACCOUNT, USERNAME, PASSWORD, EMAIL, ACCESSER, STATUS) VALUES ('" +
                    acc.getID_ACCOUNT() + "','" + acc.getUSERNAME() + "','" + acc.getPASSWORD() + "','" + acc.getEMAIL() + "','" + acc.getACCESSER() + "','" + acc.getSTATUS() + "')";
            try
            {
                if(DBHelper.getInstance().excuteDB(query))
                {
                    alert.setContentText("Register Success!");
                }
                else
                {
                    alert.setContentText("Register failed!");
                }
                alert.show();
            }
            catch (ClassNotFoundException | SQLException ex)
            {
                Logger.getLogger(AccountModel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    public int selectLastRowCol()
    {
        int id = 0;
        try
        {
            ResultSet rs = DBHelper.getInstance().selectLastRowCol("TB_ACCOUNT", "ID_ACCOUNT", "A");
            if(rs.next())
            {
                String idAccount = rs.getString("ID_ACCOUNT");
                String idA = idAccount.replaceAll("A", "");
                id = Integer.parseInt(idA);
            }
        }
        catch (SQLException | ClassNotFoundException ex)
        {
            Logger.getLogger(AccountModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
    }
}
