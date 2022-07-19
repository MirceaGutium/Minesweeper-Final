package Sweeper;

 class Bomb {
    private Matrix bombMap;
    private int totalBombs;

    Bomb (int totalBombs)
    {
        this.totalBombs = totalBombs;
        fixBombsCount();
    }
    void start ()
    {
        bombMap = new Matrix (Box.ZERO);
        for (int j = 0; j < totalBombs; j++)
        placeBomb ();
    }
    Box get (Coordinator coordinator)
    {
        return bombMap.get(coordinator);
    }

    private void fixBombsCount ()
    {
        int maxBombs = Ranges.getSize().x * Ranges.getSize().y / 2;
        if (totalBombs > maxBombs)
            totalBombs = maxBombs;
    }
    private void placeBomb ()
    {
        while (true) {
            Coordinator coordinator = Ranges.getRandomCoordinator();
            if (Box.BOMB == bombMap.get(coordinator))
                continue;
            bombMap.set (coordinator, Box.BOMB);
            incNumbersAroundBomb (coordinator);
            break;
        }
    }
    private void incNumbersAroundBomb (Coordinator coordinator){
        for (Coordinator around : Ranges.getCoordsAround(coordinator))
            if (Box.BOMB != bombMap.get (around))
            bombMap.set (around, bombMap.get(around).getNextNumberBox());
    }

        int getTotalBombs() {
        return totalBombs;
     }
 }
