package rocks.friedrich.jwinf.platform.logic.robot;

import java.util.ArrayList;
import java.util.List;

public class Route
{
    List<Movement> movements;

    public Route(List<Movement> movements)
    {
        this.movements = movements;
    }

    public Route()
    {
        movements = new ArrayList<Movement>();
    }

    public void add(Movement movement)
    {
        movements.add(movement);
    }

    public String[] toArray()
    {
        String[] result = new String[movements.size()];
        for (int i = 0; i < movements.size(); i++)
        {
            var mov = movements.get(i);
            String prefix = "";
            if (!mov.successful)
            {
                prefix = "!";
            }
            result[i] = prefix + mov.name;
        }
        return result;
    }

    public void printRoute()
    {
        System.out.println("\"" + String.join("\", \"", toArray()) + "\"");
    }
}
