import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import Sweeper.Box;
import Sweeper.Coordinator;
import Sweeper.Game;
import Sweeper.Ranges;

import static Sweeper.GameState.PLAYED;

public class JavaSweeper extends JFrame {

    private Game game;
    private JPanel panel;
    private JLabel label;
    private final int COLS = 9;
    private final int ROWS = 9;
    private final int BOMBS = 6;

    private final int FLAGS = 9;
    private final int IMAGE_SIZE =  50;

    public static void main(String[] args) {
        new JavaSweeper().setVisible(true);

    }
    private JavaSweeper ()
    {
        game = new Game (COLS, ROWS, BOMBS, FLAGS);
        game.start();
        setImages();
        initLabel();
        initPanel();
        initFrame();
    }
    private void initLabel()
    {
        label = new JLabel("Zdraste))");
        add (label, BorderLayout.SOUTH);
    }
    private void initPanel () {
        panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                for (Coordinator coordinator : Ranges.getAllCoords()) {

                    g.drawImage((Image) game.getBox(coordinator).image,
                            coordinator.x * IMAGE_SIZE,
                            coordinator.y * IMAGE_SIZE,
                            this);
                }
            }
        };
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int x = e.getX() / IMAGE_SIZE;
                int y = e.getY() / IMAGE_SIZE;
                Coordinator coordinator = new Coordinator(x, y);
                if (e.getButton() == MouseEvent.BUTTON1)
                    game.pressLeftButton(coordinator);
                if (e.getButton() == MouseEvent.BUTTON3)
                    game.pressRightButton(coordinator);
                if (e.getButton() == MouseEvent.BUTTON2)
                    game.start();
                label.setText(getMessage());
                panel.repaint();

            }
        });


        panel.setPreferredSize(new Dimension(
                Ranges.getSize().x * IMAGE_SIZE,
                Ranges.getSize().y * IMAGE_SIZE));
        add(panel);
    }
       private String getMessage()
       {
switch (game.getGamestate())
{
    case PLAYED: return "Think wisely";
    case BOMBED: return "You failed, chaos will rise";
    case WINNER: return "Heretics have been purged";
    default: return "Goodbye";
}
    }


    private void initFrame ()
    {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle ("JAVA Sweeper");
        setLocationRelativeTo(null);
        setResizable (false);
        setVisible (true);
        setIconImage (getImage("icon"));
        pack ();
    }
    private void setImages ()
    {
        for (Box box : Box.values())
            box.image = getImage(box.name());
    }
    private Image getImage (String name)
    {
        String filename = "img/" + name.toLowerCase() + ".png";
        ImageIcon icon = new ImageIcon (getClass().getResource(filename));
        return icon.getImage();
    }
}
