package rocks.friedrich.jwinf.en.tasks.conditionals_excercises.find_the_way_to_the_lake;

import rocks.friedrich.jwinf.platform.Solver;

public class TaskSolver extends Solver<Robot>
{

    @Override
    public void easy(Robot robot)
    {
        for (int i = 0; i < 17; i++)
        {
            robot.forward();
            if (robot.obstacleInFront())
            {
                robot.turnLeft();
            }
        }
    }

    @Override
    public void medium(Robot robot)
    {
        for (int i = 0; i < 18; i++)
        {
            robot.forward();
            if (robot.obstacleInFront())
            {
                robot.turnRight();
                if (robot.obstacleInFront())
                {
                    robot.turnLeft();
                    robot.turnLeft();
                }
            }
        }
    }

    @Override
    public void hard(Robot robot)
    {
        for (int i = 0; i < 19; i++)
        {
            robot.forward();
            if (robot.obstacleInFront())
            {
                robot.turnRight();
                if (robot.obstacleInFront())
                {
                    robot.turnLeft();
                    robot.turnLeft();
                }
            } else
            {
                robot.turnLeft();
                if (!robot.obstacleInFront())
                {
                    robot.forward();
                } else
                {
                    robot.turnRight();
                }
            }
        }
    }

    @Override
    public void all(Robot robot)
    {
    }

    public static void main(String[] args)
    {
        new TaskSolver().solve();
    }
}
