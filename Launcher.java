import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

import java.awt.Color;
import java.awt.event.*;

public class Launcher {
    public static void main(String[] args) {
        new Window(100,8);
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
        background.setBackground(Color.BLUE);

        add(background);

        setSize(Width,Height);
        setResizable(false);
        setTitle("Terrain Generation");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        timer.start();

        generatePerlinNoise(PerlinScale,TileScale);
    }

    void generatePerlinNoise(int scale, int tileScale) {
        int areaIndex = 0;
        perlinIndex = new double[scale*scale];
        tiles = new Tile[scale*scale];

        for (int collum = 0; collum < scale; collum++) { // VERTICAL
            for (int row = 0; row < scale; row++) { // HORIZONTAL
                Random randomStartIndexs = new Random();

                int randomHeight = randomStartIndexs.nextInt(8);
                perlinIndex[areaIndex] = (double)randomHeight/10;

                try {
                    double madeHeight = perlinIndex[areaIndex-scale];
                    Random randomOperat = new Random();

                    int randomOperator = randomOperat.nextInt(2);
                    System.out.println(randomOperator);

                    double newHeight = 0;
                    
                    Random randHeight = new Random();
                    if (randomOperator == 0) newHeight = madeHeight - (double)(randHeight.nextInt(8));
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
                
                tiles[areaIndex] = new Tile((float)perlinIndex[areaIndex],tileScale, row, collum);
                background.add(tiles[areaIndex]);

                areaIndex++;
            }
        }

        flatten(.3f, 1,.2f,0.0f);
    }

    void flatten(double frequency, int loop, double maxHeight, double minHeight) {

        int areaIndex = 0;

        for (int layer = 0; layer < loop; layer++) {

            for (int collum = 0; collum < tiles.length; collum++) {
                    
                Random randomHeight = new Random();
                Random randomOp = new Random();

                int randomOperator = randomOp.nextInt(2);
                int heightShift = randomHeight.nextInt(3);

                double newHeight;

                if (randomOperator == 1) newHeight = perlinIndex[areaIndex] - (double)(heightShift/10)+frequency;
                else newHeight = perlinIndex[areaIndex] + (double)(heightShift/10)+frequency;

                System.out.println(newHeight + " " + perlinIndex[areaIndex]);

                if (perlinIndex[areaIndex] <= maxHeight && perlinIndex[areaIndex] >= minHeight) {
                    tiles[areaIndex].changePerlinIndex((float)newHeight);
                }

                areaIndex++;
            }

            areaIndex = 0;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();

        for (int i = 0; i < tiles.length; i++) {
            if (tiles[i] != null) {
                tiles[i].update();
            }
        }
    }
}