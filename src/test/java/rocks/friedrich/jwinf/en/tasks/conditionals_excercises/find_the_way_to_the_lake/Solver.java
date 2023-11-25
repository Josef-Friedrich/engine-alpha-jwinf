package rocks.friedrich.jwinf.en.tasks.conditionals_excercises.find_the_way_to_the_lake;

public class Solver extends TaskSolver
{
    @Override
    public void easy(Robot r)
    {
        r.forward();
    }

    public static void main(String[] args)
    {
        new Solver().solve("easy");
    }
}
