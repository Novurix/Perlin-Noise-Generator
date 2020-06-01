import javax.swing.JPanel;

import java.awt.Color;

public class Tile extends JPanel {
    public Tile(float perlinIndex, int scale, int x, int y) {
        setBounds(x*scale,y*scale,scale,scale);

        float rgb = 255*perlinIndex;
        if (rgb > 255) {
            rgb = 250;
        }if (rgb < 0) {
            rgb = 0;
        }

        System.out.println(rgb);
        setLayout(null);
        setBackground(new Color((int)rgb,(int)rgb,(int)rgb));
    }
}