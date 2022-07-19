package Sweeper;

 class Matrix {
    private Box [] [] matrix;

    Matrix (Box defaultBox)
    {
        matrix = new Box[Ranges.getSize().x] [Ranges.getSize().y];
        for (Coordinator coordinator : Ranges.getAllCoords())
            matrix [coordinator.x] [coordinator.y] = defaultBox;
    }
    Box get (Coordinator coordinator){
        if (Ranges.inRange (coordinator))
        return matrix [coordinator.x] [coordinator.y];
        return null;
    }
    void set (Coordinator coordinator, Box box)
    {
        if (Ranges.inRange (coordinator))
        matrix [coordinator.x] [coordinator.y] = box;
    }
}
