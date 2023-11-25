package rocks.friedrich.jwinf.en.tasks.conditionals_excercises.find_the_way_to_the_lake;

import rocks.friedrich.jwinf.platform.Solver;

public class TaskSolver extends Solver<Robot>
{
    public TaskSolver()
    {
        super("19-DE-12-stay-on-the-road");
    }

    @Override
    public void easy(Robot robot)
    {
        for (int i = 0; i < 17; i++)
        {
            robot.forward();
            if (robot.obstacleInFront())
            {
                System.out.println("turn");
                robot.turnLeft();
            }
        }
    }

    @Override
    public void medium(Robot robot)
    {
        for (int i = 0; i < 20; i++)
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
        for (int i = 0; i < 20; i++)
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
