package csms.database.component;

import csms.database.theme.SystemTheme;
import csms.database.theme.ThemeColorChange;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JLabel;

public class Cell extends JButton {

    private Date date;
    private boolean title;
    private boolean isToDay;
    
    private boolean isHighlighted;

    public Cell() {
        setContentAreaFilled(false);
        setBorder(null);
        setHorizontalAlignment(JLabel.CENTER);
    }

    public void asTitle() {
        title = true;
    }

    public boolean isTitle() {
        return title;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void currentMonth(boolean act) {
        if(ThemeColorChange.getInstance().getMode() == ThemeColorChange.Mode.DARK){
            if (act) {
                setForeground(new Color(200, 200, 200));
            } else {
                setForeground(new Color(125, 125, 125));
            }
        } else{
            if (act) {
                setForeground(new Color(80, 80, 80));
            } else {
                setForeground(new Color(170, 170, 170));
            }
        }
    }

    public void setAsToDay() {
        isToDay = true;
        setForeground(Color.WHITE);
    }
    
    public void setAsHighlighted(){
        isHighlighted = true;
    }

    @Override
    protected void paintComponent(Graphics grphcs) {
        if (title) {
            grphcs.setColor(new Color(213, 213, 213));
            grphcs.drawLine(0, getHeight() - 1, getWidth(), getHeight() - 1);
        }
        if (isToDay) {
            Graphics2D g2 = (Graphics2D) grphcs;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(SystemTheme.mainColor);
            int x = getWidth() / 2 - 17;
            int y = getHeight() / 2 - 17;
            g2.fillRoundRect(x, y, 35, 35, 100, 100);
        }
        if (isHighlighted) {
            Graphics2D g2 = (Graphics2D) grphcs;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(Color.RED);
            int x = getWidth() / 2 - 17;
            int y = getHeight() / 2 - 17;
            g2.fillRoundRect(x, y, 10, 10, 100, 100);
        }
        super.paintComponent(grphcs);
    }

}
