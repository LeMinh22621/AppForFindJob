package Model.DTO;
public class ItemSkill
{
    private String ID_SKILL;
    private String NAME_SKILL;
    @Override
    public String toString()
    {
        return NAME_SKILL;
    }
    public String getID_SKILL() {
        return ID_SKILL;
    }

    public void setID_SKILL(String ID_SKILL) {
        this.ID_SKILL = ID_SKILL;
    }

    public String getNAME_SKILL() {
        return NAME_SKILL;
    }

    public void setNAME_SKILL(String NAME_SKILL) {
        this.NAME_SKILL = NAME_SKILL;
    }
}
