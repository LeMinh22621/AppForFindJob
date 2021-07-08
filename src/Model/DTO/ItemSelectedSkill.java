package Model.DTO;
public class ItemSelectedSkill
{
    private String ID_ACCOUNT;
    private ItemSkill Skill;

    public ItemSkill getSkill() {
        return Skill;
    }

    public void setSkill(ItemSkill Skill) {
        this.Skill = Skill;
    }

    public String getID_ACCOUNT() {
        return ID_ACCOUNT;
    }

    public void setID_ACCOUNT(String ID_ACCOUNT) {
        this.ID_ACCOUNT = ID_ACCOUNT;
    }
    
    @Override
    public String toString()
    {
        return Skill.getNAME_SKILL();
    }
}
