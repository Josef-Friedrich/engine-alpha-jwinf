package rocks.friedrich.jwinf.platform.logic.robot;

public class Action
{
    public Action(String name)
    {
        this.name = name;
    }

    public String name;

    public ErrorMessages error;

    public Action setError(ErrorMessages error)
    {
        this.error = error;
        return this;
    }
}
