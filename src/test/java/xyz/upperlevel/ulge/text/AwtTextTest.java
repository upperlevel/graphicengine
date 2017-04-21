package xyz.upperlevel.ulge.text;

import javax.swing.*;
import java.awt.*;
import java.awt.font.TextAttribute;
import java.text.AttributedString;

/**
 * Created by loryruta on 17/04/17.
 */
public class AwtTextTest extends JPanel {
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        String s = "l g";

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        Font plainFont = new Font("Times New Roman", Font.PLAIN, 24);

        AttributedString as = new AttributedString(s);
        as.addAttribute(TextAttribute.FONT, plainFont);
        as.addAttribute(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON, 0, s.length());

        g2.drawString(as.getIterator(), 24, 70);
    }

    public static void main(String[] args) {
        JFrame f = new JFrame();
        f.getContentPane().add(new AwtTextTest());
        f.setSize(850, 250);
        f.setVisible(true);
    }
}
