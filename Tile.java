import javax.swing.JPanel;

import java.awt.Color;

public class Tile extends JPanel {

    float perlinIndex;

    public Tile(float perlinIndex, int scale, int x, int y) {
        setBounds(x*scale,y*scale,scale,scale);

        this.perlinIndex = perlinIndex;
        setLayout(null);
    }

    public void changePerlinIndex(float perlinIndex) {
        this.perlinIndex = perlinIndex;
    }

    void update() {
        float rgb = 255*perlinIndex;
        if (rgb > 255) rgb = 255;
        if (rgb < 0) rgb = 0;

        int rgbValue = (int)rgb;
        setBackground(new Color(rgbValue,rgbValue,rgbValue));
    }
}