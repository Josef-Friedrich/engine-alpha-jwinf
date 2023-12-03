package rocks.friedrich.jwinf.en.tasks.conditionals_excercises.heat_the_castle;

import rocks.friedrich.jwinf.platform.Solver;

/**
 * https://jwinf.de/task/1159
 */
public class TaskSolver extends Solver<Robot>
{
    @Override
    public void easy(Robot robot)
    {
        if (!robot.platformInFront())
        {
            robot.constructPlatformInFront();
        }
        robot.forward();
        robot.forward();
        robot.collectFirewood();
        robot.forward();
        robot.dropFirewood();
    }

    @Override
    public void medium(Robot robot)
    {
    }

    @Override
    public void hard(Robot robot)
    {
    }

    public static void main(String[] args)
    {
        new TaskSolver().solve("easy");
    }
}
