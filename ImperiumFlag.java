package Sweeper;

class ImperiumFlag {
    private Matrix imperiumflagMap;
    private int countOfClosedBoxes;

            void start ()
            {
                imperiumflagMap = new Matrix(Box.CLOSED);
                countOfClosedBoxes = Ranges.getSize().x * Ranges.getSize().y;
            }
            Box get (Coordinator coordinator)
            {
                return imperiumflagMap.get (coordinator);
            }

    void setOpenedToBox(Coordinator coordinator)
    {
                imperiumflagMap.set(coordinator, Box.OPENED);
        countOfClosedBoxes --;
    }
    void toggleFlagedToBox (Coordinator coordinator)
    {
        switch (imperiumflagMap.get(coordinator))
        {
            case FLAGED : setClosedToBox (coordinator); break;
            case CLOSED : setFlagedToBox (coordinator); break;

        }
    }

    private void setClosedToBox(Coordinator coordinator)
    {
        imperiumflagMap.set(coordinator, Box.CLOSED);
    }

    private void setFlagedToBox(Coordinator coordinator)
    {
        imperiumflagMap.set(coordinator, Box.FLAGED);
    }

  int getCountOfClosedBoxes() {
                return countOfClosedBoxes;
    }

    void setBombedToBox(Coordinator coordinator) {
                imperiumflagMap.set (coordinator, Box.BOMBED);
    }

    void setOpenedToClosedBombBox(Coordinator coordinator) {
                if (imperiumflagMap.get (coordinator) == Box.CLOSED)
                    imperiumflagMap.set (coordinator, Box.OPENED);
    }

    void setNoBombToFlagedSafeBox(Coordinator coordinator) {
                if (imperiumflagMap.get(coordinator) == Box.FLAGED)
                    imperiumflagMap.set (coordinator, Box.NOBOMB);
    }



    private int getCountOfFlagedBoxesAround(Coordinator coordinator) {
        int count = 0;
        for (Coordinator around: Ranges.getCoordsAround(coordinator))
            if (imperiumflagMap.get(around) == Box.FLAGED)
                count++;
        return count;
    }
}
