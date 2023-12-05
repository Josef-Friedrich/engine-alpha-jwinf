package rocks.friedrich.jwinf.blockly_robot.logic.robot;

public class Action
{
    protected String name;

    protected ErrorMessages error;

    public Action(String name)
    {
        this.name = name;
    }

    public Action setError(ErrorMessages error)
    {
        this.error = error;
        return this;
    }

    public boolean hasError()
    {
        return error != null;
    }

    public String getName()
    {
        return name;
    }
}
