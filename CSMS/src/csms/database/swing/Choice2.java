package csms.database.swing;

import csms.database.scrollbar.ScrollBarCustom;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;
import javax.swing.JComboBox;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;

public class Choice2 extends JComboBox{

    public Color getBg() {
        return bg;
    }

    public void setBg(Color bg) {
        this.bg = bg;
    }
    
    public String getHint() {
        return hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }
    
    private String hint = "";
    private Color bg = Color.WHITE;
    
    public Choice2() {
        setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
        setBackground(new Color(0, 0, 0, 0));
        setForeground(Color.decode("#7A8C8D"));
        setFont(new java.awt.Font("sansserif", 0, 12));
        ajustaScrollBar(this);
        //setSelectionColor(new Color(75, 175, 152));
    }
    
    private void ajustaScrollBar(JComboBox box){
        Object comp = box.getUI().getAccessibleChild(box, 0);
        if (comp instanceof JPopupMenu) {
            JPopupMenu popup = (JPopupMenu) comp;
            JScrollPane scrollPane = (JScrollPane) popup.getComponent(0);
            scrollPane.setVerticalScrollBar(new ScrollBarCustom());
            scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
            scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        }
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(bg);
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 5, 5);
        super.paintComponent(g);
    }
    
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if (getSelectedItem() == null) {
            int h = getHeight();
            ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
            Insets ins = getInsets();
            FontMetrics fm = g.getFontMetrics(new Font("century gothic", 0, 12));
            g.setColor(new Color(200, 200, 200));
            g.drawString(hint, ins.left + 10, h / 2 + fm.getAscent() / 2 - 2);
        }
    }
}
