package Model.Database;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;

public class DBHelper implements DBHelperInterface
{
    private static DBHelper Instance;
    public static DBHelper getInstance() throws ClassNotFoundException, SQLException
    {
            if(Instance == null)
            {
                Instance = new DBHelper();
            }
            return Instance;
    }
    private Connection conn;
    private ResultSet rs;
    private PreparedStatement stmt;
    
    private DBHelper()
    {
        initialzeConnection();
    }
    private void initialzeConnection()
    {
        try
        {
            Class.forName(JDBC_DRIVER);
        }
        catch(ClassNotFoundException e)
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText(e.getMessage());
            alert.show();
        }
    }
    public void close()
    {
        try
        {
            conn.close();
        }
        catch (SQLException ex)
        {
            Logger.getLogger(DBHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @Override
    public ResultSet select(String query)
    {
        try
        {
            conn = DriverManager.getConnection(DB_URL, "", "");
            stmt = conn.prepareStatement(query);
            rs = stmt.executeQuery();
        }
        catch (SQLException ex)
        {
            Logger.getLogger(DBHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }
    public ResultSet selectLastRowCol(String tableName, String columnName, String specialCharacter)
    {
        try
        {
            String query = "SELECT TOP 1 " + columnName 
                    + " FROM " + tableName
                    + " WHERE " + columnName + " LIKE '" + specialCharacter + "%'"
                    + " ORDER BY " + columnName + " DESC";
            conn = DriverManager.getConnection(DB_URL, "", "");
            stmt = conn.prepareStatement(query);
            rs = stmt.executeQuery();
        }
        catch (SQLException ex)
        {
            Logger.getLogger(DBHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }

    @Override
    public boolean excuteDB(String query)
    {
        try
        {
            conn = DriverManager.getConnection(DB_URL, "", "");
            stmt = conn.prepareStatement(query);
            stmt.execute();
            stmt.close();
            conn.close();
            return true;
        }
        catch (SQLException ex)
        {
            Logger.getLogger(DBHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}
