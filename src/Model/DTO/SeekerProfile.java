package Model.DTO;
public class SeekerProfile
{
    private String ID_ACCOUNT;

    public SeekerProfile()
    {
        ID_ACCOUNT = "";
        FIRST_NAME = "";
        LAST_NAME = "";
        AGE = 0;
        GENDER = true;
        IMAGE = "";
        CV = "";
        SEEKER_DESCRIPTION = "";
        PHONE_NUMBER = "";
        FACEBOOK = "";
    }

    public String getID_ACCOUNT() {
        return ID_ACCOUNT;
    }

    public void setID_ACCOUNT(String ID_ACCOUNT) {
        this.ID_ACCOUNT = ID_ACCOUNT;
    }

    public String getFIRST_NAME() {
        return FIRST_NAME;
    }

    public void setFIRST_NAME(String FIRST_NAME) {
        this.FIRST_NAME = FIRST_NAME;
    }

    public String getLAST_NAME() {
        return LAST_NAME;
    }

    public void setLAST_NAME(String LAST_NAME) {
        this.LAST_NAME = LAST_NAME;
    }

    public int getAGE() {
        return AGE;
    }

    public void setAGE(int AGE) {
        this.AGE = AGE;
    }

    public boolean getGENDER() {
        return GENDER;
    }

    public void setGENDER(boolean GENDER) {
        this.GENDER = GENDER;
    }

    public String getCV() {
        return CV;
    }

    public void setCV(String CV) {
        this.CV = CV;
    }

    public String getIMAGE() {
        return IMAGE;
    }

    public void setIMAGE(String IMAGE) {
        this.IMAGE = IMAGE;
    }

    public String getPHONE_NUMBER() {
        return PHONE_NUMBER;
    }

    public void setPHONE_NUMBER(String PHONE_NUMBER) {
        this.PHONE_NUMBER = PHONE_NUMBER;
    }

    public String getSEEKER_DESCRIPTION() {
        return SEEKER_DESCRIPTION;
    }

    public void setSEEKER_DESCRIPTION(String SEEKER_DESCRIPTION) {
        this.SEEKER_DESCRIPTION = SEEKER_DESCRIPTION;
    }

    public String getFACEBOOK() {
        return FACEBOOK;
    }

    public void setFACEBOOK(String FACEBOOK) {
        this.FACEBOOK = FACEBOOK;
    }
    private String FIRST_NAME;
    private String LAST_NAME;
    private int AGE;
    private boolean GENDER;
    private String CV;
    private String IMAGE;
    private String PHONE_NUMBER;
    private String SEEKER_DESCRIPTION;
    private String FACEBOOK;
}
