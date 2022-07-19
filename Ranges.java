package Sweeper;

import java.util.ArrayList;
import java.util.Random;

public class Ranges {
    private static Coordinator size;
private static ArrayList<Coordinator> allCoords;
private static Random random = new Random();
    public static Coordinator getSize() {
        return size;
    }

   static void setSize (Coordinator _size)
    {
        size = _size;
        allCoords = new ArrayList<Coordinator>();
        for (int y = 0; y < size.y; y++)
            for (int x = 0; x < size.x; x++)
                allCoords.add(new Coordinator (x, y));
    }
    public static ArrayList<Coordinator> getAllCoords ()
    {
        return allCoords;
    }
    static boolean inRange (Coordinator coordinator)
    {
        return  coordinator.x >= 0 && coordinator.x < size.x &&
                coordinator.y >= 0 && coordinator.y < size.y;
    }
    static Coordinator getRandomCoordinator()
    {
        return new Coordinator (random.nextInt (size.x),
                                random.nextInt(size.y));
    }
    static ArrayList<Coordinator> getCoordsAround(Coordinator coordinator){

        Coordinator around;
        ArrayList<Coordinator> list = new ArrayList<Coordinator>();
        for (int x= coordinator.x - 1; x <= coordinator.x + 1; x++)
            for (int y = coordinator.y - 1; y <= coordinator.y + 1; y++)
                if (inRange(around = new Coordinator(x, y)))
                    if (!around.equals(coordinator))
                        list.add (around);
        return list;
    }
}
