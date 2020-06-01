import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

import java.awt.event.*;

public class Launcher {
    public static void main(String[] args) {
        new Window(50,20);
    }
}

class Window extends JFrame implements ActionListener{

    JPanel background = new JPanel();

    double[] perlinIndex;
    Tile[] tiles;

    Timer timer = new Timer(1,this);

    public Window(int PerlinScale, int TileScale) {


        int Width = PerlinScale*TileScale;
        int Height = PerlinScale*TileScale;
        background.setBounds(0,0,Width,Height);
        background.setLayout(null);

        add(background);

        setSize(Width,Height);
        setResizable(false);
        setTitle("Terrain Generation");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        timer.start();

        generatePerlinNoise(PerlinScale);
    }

    void generatePerlinNoise(int scale) {
        int areaIndex = 0;
        perlinIndex = new double[scale*scale];
        tiles = new Tile[scale*scale];

        for (int collum = 0; collum < scale; collum++) {
            for (int row = 0; row < scale; row++) {
                Random randomStartIndexs = new Random();

                int randomHeight = randomStartIndexs.nextInt(11);
                perlinIndex[areaIndex] = (double)randomHeight/10;

                try {
                    double madeHeight = perlinIndex[areaIndex-scale];
                    Random randomOperat = new Random();

                    int randomOperator = randomOperat.nextInt(2);
                    System.out.println(randomOperator);

                    double newHeight = 0;
                    
                    Random randHeight = new Random();
                    if (randomOperator == 0) newHeight = madeHeight - (double)(randHeight.nextInt(10));
                    else newHeight = madeHeight + (double)(randHeight.nextInt(8));

                    if (newHeight/10 < 0) {
                        newHeight *= -1;
                    }

                    if (newHeight < 0.01f) {
                        newHeight*=10;
                    }

                    System.out.println(newHeight/10);

                    perlinIndex[areaIndex] = newHeight/10;
                }catch (Exception e) {}
                
                tiles[areaIndex] = new Tile((float)perlinIndex[areaIndex],20, row, collum);
                background.add(tiles[areaIndex]);

                areaIndex++;
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }
}