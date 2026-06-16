package csms.loadingscreen;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Insets;
import javax.swing.JProgressBar;
import javax.swing.plaf.basic.BasicProgressBarUI;

public class progressbar extends JProgressBar {
    
    public progressbar(){
        setUI (new BasicProgressBarUI(){
           @Override
           protected void paintString(Graphics grphcs, int i, int i1, int i2, int i3, int i4, Insets insets){
               grphcs.setColor(new Color(0,0,0));
               super.paintString(grphcs, i, i1, i2, i3, i4, insets);
           }
        });
    }
}
