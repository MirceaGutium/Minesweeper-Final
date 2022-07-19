package Sweeper;

public class Game {

   private Bomb bomb;
   private ImperiumFlag imperiumflag;

    public GameState getGamestate()
    {
        return gamestate;
    }

    private GameState gamestate;

    public Game (int cols, int rows, int bombs, int ImperiumFlag)
    {
        Ranges.setSize(new Coordinator (cols, rows));
        bomb = new Bomb(bombs);
        imperiumflag = new ImperiumFlag();
    }

    public void start (){
        bomb.start();
        imperiumflag.start();
        gamestate = GameState.PLAYED;
    }
    public Box getBox (Coordinator coordinator)
    {
        if (imperiumflag.get(coordinator) == Box.OPENED)
            return bomb.get(coordinator);
        else
        return imperiumflag.get(coordinator);
    }

    public void pressLeftButton(Coordinator coordinator)
    {
        if (gameOver()) return;
        openBox (coordinator);
        checkWinner ();
    }
    private void checkWinner()
    {
        if (gamestate == GameState.PLAYED)
            if (imperiumflag.getCountOfClosedBoxes() == bomb.getTotalBombs())
                gamestate = GameState.WINNER;
    }

    private void openBox (Coordinator coordinator){
        switch (imperiumflag.get(coordinator)){
            case OPENED: setOpenedToClosedBoxesAroundNumber (coordinator); return;
            case FLAGED: return;
            case CLOSED:
                switch (bomb.get (coordinator)){
                    case ZERO: openBoxesAround(coordinator); return;
                    case BOMB: openBombs(coordinator); return;
                    default: imperiumflag.setOpenedToBox(coordinator); return;
                }
        }
    }
   private void setOpenedToClosedBoxesAroundNumber (Coordinator coordinator){

   }


    private void openBombs(Coordinator bombed)
    {
        gamestate = GameState.BOMBED;
        imperiumflag.setBombedToBox(bombed);
        for (Coordinator coordinator: Ranges.getAllCoords())
            if (bomb.get (coordinator) == Box.BOMB)
                imperiumflag.setOpenedToClosedBombBox (coordinator);
        else
            imperiumflag.setNoBombToFlagedSafeBox (coordinator);

    }

    private void openBoxesAround(Coordinator coordinator) {
        imperiumflag.setOpenedToBox(coordinator);
        for (Coordinator around : Ranges.getCoordsAround(coordinator))
            openBox(around);
    }

    public void pressRightButton(Coordinator coordinator)
    {
        if (gameOver()) return;
        imperiumflag.toggleFlagedToBox (coordinator);
    }

    private boolean gameOver() {
        if (gamestate == GameState.PLAYED)
            return false;
        start();
        return true;
    }
}
