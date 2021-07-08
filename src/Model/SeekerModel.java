package Model;

import Model.DTO.ItemSelectedSkill;
import Model.DTO.ItemSkill;
import Model.DTO.SeekerProfile;
import Model.Database.DBHelper;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

public class SeekerModel
{
    private Alert alert = new Alert(Alert.AlertType.INFORMATION);
    public SeekerProfile getSeekerProfile(String idAccount) throws SQLException, ClassNotFoundException
    {
        SeekerProfile seekerProfile = new SeekerProfile();
        String query = "SELECT * FROM TB_SEEKER_PROFILE WHERE ID_ACCOUNT = '" + idAccount + "'";
        ResultSet rs = DBHelper.getInstance().select(query);
        if(rs.next())
        {
            seekerProfile.setID_ACCOUNT(idAccount);
            seekerProfile.setFIRST_NAME(rs.getString("FIRST_NAME"));
            seekerProfile.setLAST_NAME(rs.getString("LAST_NAME"));
            seekerProfile.setAGE(rs.getInt("AGE"));
            seekerProfile.setGENDER(rs.getBoolean("GENDER"));
            seekerProfile.setCV(rs.getString("CV"));
            seekerProfile.setIMAGE(rs.getString("IMAGE"));
            seekerProfile.setPHONE_NUMBER(rs.getString("PHONE_NUMBER"));
            seekerProfile.setSEEKER_DESCRIPTION(rs.getString("SEEKER_DESCRIPTION"));
            seekerProfile.setFACEBOOK(rs.getString("FACEBOOK"));
        }
        rs.close();
        return seekerProfile;
    }
    public ObservableList<ItemSkill> getListSkill()
    {
        List<ItemSkill> list = new ArrayList();
        try
        {
            String query = "SELECT * FROM TB_SKILL";
            ResultSet rs = DBHelper.getInstance().select(query);
            while(rs.next())
            {
                ItemSkill item = new ItemSkill();
                item.setID_SKILL(rs.getString("ID_SKILL"));
                item.setNAME_SKILL(rs.getString("NAME_SKILL"));
                
                list.add(item);
            }
            rs.close();
            DBHelper.getInstance().close();
        }
        catch (ClassNotFoundException | SQLException ex)
        {
            Logger.getLogger(SeekerModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        ObservableList<ItemSkill> observableList = FXCollections.observableList(list);
        return observableList;
    }
    public ObservableList<ItemSelectedSkill> getListSelectedSkill(String idAccount)
    {
        List<ItemSelectedSkill> list = new ArrayList<>();
        try
        {
            String query = "SELECT * FROM TB_SELECTED_SKILL WHERE ID_ACCOUNT = '" + idAccount + "'";
            ResultSet rs = DBHelper.getInstance().select(query);
            while(rs.next())
            {
                ItemSelectedSkill itemSelectedSkill = new ItemSelectedSkill();
                
                ItemSkill itemSkill = new ItemSkill();
                String idSkill = rs.getString("ID_SKILL");
                String query1 = "SELECT * FROM TB_SKILL WHERE ID_SKILL = '" + idSkill + "'";
                ResultSet rsSkill = DBHelper.getInstance().select(query1);
                if(rsSkill.next())
                {
                    itemSkill.setID_SKILL(idSkill);
                    itemSkill.setNAME_SKILL(rsSkill.getString("NAME_SKILL"));
                }
                rsSkill.close();
                
                itemSelectedSkill.setID_ACCOUNT(rs.getString("ID_ACCOUNT"));
                itemSelectedSkill.setSkill(itemSkill);
                
                list.add(itemSelectedSkill);
            }
            rs.close();
            DBHelper.getInstance().close();
        }
        catch (ClassNotFoundException | SQLException ex)
        {
            Logger.getLogger(SeekerModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        ObservableList<ItemSelectedSkill> observableList = FXCollections.observableList(list);
        return observableList;
    }
    public void addItemSelectedSkill(ItemSelectedSkill itemSelectedSkill) throws ClassNotFoundException, SQLException
    {
        String query = "INSERT INTO TB_SELECTED_SKILL(ID_ACCOUNT, ID_SKILL) VALUES ('" + 
                itemSelectedSkill.getID_ACCOUNT() + "','" + itemSelectedSkill.getSkill().getID_SKILL() + "')";
        DBHelper.getInstance().excuteDB(query);
    }
    public void deleteItemSelectedSkill(String id) throws ClassNotFoundException, SQLException
    {
        String query = "DELETE TB_SELECTED_SKILL WHERE ID_SKILL = '" + id + "'";
        DBHelper.getInstance().excuteDB(query);
    }
    public void updateSeekerProfile(SeekerProfile seekerProfile) throws ClassNotFoundException, SQLException
    {
        String query = "UPDATE TB_SEEKER_PROFILE SET FIRST_NAME = '" + seekerProfile.getFIRST_NAME() +
                "', LAST_NAME = '" + seekerProfile.getLAST_NAME() +
                "', AGE = '" + seekerProfile.getAGE() +
                "', GENDER = '" + seekerProfile.getGENDER() + 
                "', IMAGE = '" + seekerProfile.getIMAGE() +
                "', PHONE_NUMBER = '" + seekerProfile.getPHONE_NUMBER() + 
                "', SEEKER_DESCRIPTION = '" + seekerProfile.getSEEKER_DESCRIPTION() + 
                "', FACEBOOK = '" + seekerProfile.getFACEBOOK() +
                "' WHERE ID_ACCOUNT = '" + seekerProfile.getID_ACCOUNT() + "'"; 
        
        DBHelper.getInstance().excuteDB(query);
    }
}
