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
        robot.dropFirewood();
        robot.forward();
    }

    @Override
    public void medium(Robot robot)
    {
        robot.forward();
        robot.collectFirewood();
        for (int i = 0; i < 40; i++)
        {
            if (robot.platformAbove())
            {
                robot.jump();
            }
            else
            {
                if (robot.obstacleInFront())
                {
                    robot.turnAround();
                }
                robot.forward();
            }
        }
        robot.dropFirewood();
    }

    @Override
    public void hard(Robot robot)
    {
    }

    public static void main(String[] args)
    {
        new TaskSolver().solve("medium");
    }
}
