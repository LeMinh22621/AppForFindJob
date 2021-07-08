package Model.DTO;

public class Account
{
    private String ID_ACCOUNT;
    private String USERNAME;
    private String PASSWORD;
    private String EMAIL;
    private int ACCESSER;
    private boolean STATUS;

    public String getID_ACCOUNT() {
        return ID_ACCOUNT;
    }

    public String getUSERNAME() {
        return USERNAME;
    }

    public String getPASSWORD() {
        return PASSWORD;
    }

    public String getEMAIL() {
        return EMAIL;
    }

    public int getACCESSER() {
        return ACCESSER;
    }

    public boolean getSTATUS() {
        return STATUS;
    }

    public void setID_ACCOUNT(String ID_ACCOUNT) {
        this.ID_ACCOUNT = ID_ACCOUNT;
    }

    public void setUSERNAME(String USERNAME) {
        this.USERNAME = USERNAME;
    }

    public void setPASSWORD(String PASSWORD) {
        this.PASSWORD = PASSWORD;
    }

    public void setEMAIL(String EMAIL) {
        this.EMAIL = EMAIL;
    }

    public void setACCESSER(int ACCESSER) {
        this.ACCESSER = ACCESSER;
    }

    public void setSTATUS(boolean STATUS) {
        this.STATUS = STATUS;
    }

}
