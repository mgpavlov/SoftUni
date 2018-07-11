package p10MooD3;

public abstract class BaseCharacter implements Able{
    protected String username;
    protected String hashPassword;
    protected int level;

    public BaseCharacter(String username, int level) {
        this.username = username;
        this.hashPassword = null;
        this.level = level;
    }
}
