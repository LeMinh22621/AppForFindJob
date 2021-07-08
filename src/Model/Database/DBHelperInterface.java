package Model.Database;
import java.sql.ResultSet;
public interface DBHelperInterface
{
    static final String JDBC_DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    static final String DB_URL = "jdbc:sqlserver://DESKTOP-0QMTVFB\\LEHONGMINH;databaseName=AppForFindJob;integratedSecurity=true";
    
    public boolean excuteDB(String query);
    public ResultSet select(String query);
}