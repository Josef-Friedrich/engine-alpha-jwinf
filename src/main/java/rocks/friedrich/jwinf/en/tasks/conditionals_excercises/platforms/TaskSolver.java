package rocks.friedrich.jwinf.en.tasks.conditionals_excercises.platforms;

import rocks.friedrich.jwinf.platform.Solver;

/**
 * https://jwinf.de/task/1160
 */
public class TaskSolver extends Solver<Robot>
{
    @Override
    public void easy(Robot robot)
    {
        robot.forward();
        robot.collectFirewood();
        for (int i = 0; i < 15; i++)
        {
            robot.forward();
            if (robot.platformAbove())
            {
                robot.jump();
            }
        }
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
