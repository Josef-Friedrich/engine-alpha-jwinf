package rocks.friedrich.jwinf.en.tasks.loops_excercises.securing_the_road;

import rocks.friedrich.jwinf.blockly_robot.Solver;

/**
 * https://jwinf.de/task/1140
 */
public class TaskSolver extends Solver<Robot>
{
    @Override
    public void easy(Robot robot)
    {
        robot.forward();
        robot.forward();
        for (int i = 0; i < 8; i++)
        {
            robot.dropCone();
            robot.forward();
        }
    }

    @Override
    public void medium(Robot robot)
    {
        for (int i = 0; i < 4; i++)
        {
            robot.forward();
            robot.turnLeft();
            robot.forward();
            robot.dropCone();
            robot.turnRight();
            robot.forward();
            robot.turnRight();
            robot.forward();
            robot.turnLeft();
            robot.forward();
        }
    }

    @Override
    public void hard(Robot robot)
    {
        robot.forward();
        robot.forward();
        for (int i = 0; i < 4; i++)
        {
            robot.dropCone();
            robot.forward();
            robot.dropCone();
            robot.turnLeft();
            robot.forward();
            robot.dropCone();
            robot.turnRight();
            robot.forward();
            robot.turnRight();
            robot.forward();
            robot.turnLeft();
        }
    }

    public static void main(String[] args)
    {
        new TaskSolver().solve();
    }
}
