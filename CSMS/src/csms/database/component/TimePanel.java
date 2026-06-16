package csms.database.component;

import csms.database.theme.SystemTheme;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import net.miginfocom.swing.MigLayout;

public class TimePanel extends JPanel {

    public String getSubject() {
        return subject;
    }
    public void setSubject(String subject) {
        this.subject = subject;
    }
    public String getTime() {
        return time;
    }
    public void setTime(String time) {
        this.time = time;
    }
    public String getTeacher() {
        return teacher;
    }
    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }
    public String getSgs() {
        return sgs;
    }
    public void setSgs(String sgs) {
        this.sgs = sgs;
    }
    
    private String subject;
    private String time;
    private String teacher;
    private String sgs;
    
    public JLabel subname;
    public JLabel subtime;
    public JLabel subteacher;
    public JLabel subsgs;

    public TimePanel() {
        setOpaque(false);
        setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
        setLayout(new MigLayout("wrap","", "push[]1[]5[]1[]push"));
        initComponent();
    }
    
    private void initComponent(){
        subname = new JLabel(subject);
        subname.setFont(new Font("century gothic", 1, 14));
        subname.setForeground(Color.BLACK);
        subname.setVerticalAlignment(JLabel.BOTTOM);
        add(subname);
        
        subtime = new JLabel(time);
        subtime.setFont(new Font("century gothic", 0, 10));
        subtime.setForeground(Color.BLACK);
        add(subtime);
        
        subteacher = new JLabel(teacher);
        subteacher.setFont(new Font("century gothic", 0, 12));
        subteacher.setForeground(Color.BLACK);
        add(subteacher);
        
        subsgs = new JLabel(sgs);
        subsgs.setFont(new Font("century gothic", 0, 10));
        subsgs.setForeground(Color.BLACK);
        add(subsgs);
    }

    @Override
    protected void paintComponent(Graphics grphcs) {
        Graphics2D g2 = (Graphics2D) grphcs.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(SystemTheme.mainColor.brighter());
        Area area = new Area(createRoundTopLeft());
        area.intersect(new Area(createRoundTopRight()));
        area.intersect(new Area(createRoundBottomLeft()));
        area.intersect(new Area(createRoundBottomRight()));
        g2.fill(area);
        g2.dispose();
        super.paintComponent(grphcs);
    }

    private Shape createRoundTopLeft() {
        int width = getWidth();
        int height = getHeight();
        int roundX = Math.min(width, 15);
        int roundY = Math.min(height, 15);
        Area area = new Area(new RoundRectangle2D.Double(0, 0, width, height, roundX, roundY));
        area.add(new Area(new Rectangle2D.Double(roundX / 2, 0, width - roundX / 2, height)));
        area.add(new Area(new Rectangle2D.Double(0, roundY / 2, width, height - roundY / 2)));
        return area;
    }
    private Shape createRoundTopRight() {
        int width = getWidth();
        int height = getHeight();
        int roundX = Math.min(width, 15);
        int roundY = Math.min(height, 15);
        Area area = new Area(new RoundRectangle2D.Double(0, 0, width, height, roundX, roundY));
        area.add(new Area(new Rectangle2D.Double(0, 0, width - roundX / 2, height)));
        area.add(new Area(new Rectangle2D.Double(0, roundY / 2, width, height - roundY / 2)));
        return area;
    }
    private Shape createRoundBottomLeft() {
        int width = getWidth();
        int height = getHeight();
        int roundX = Math.min(width, 15);
        int roundY = Math.min(height, 15);
        Area area = new Area(new RoundRectangle2D.Double(0, 0, width, height, roundX, roundY));
        area.add(new Area(new Rectangle2D.Double(roundX / 2, 0, width - roundX / 2, height)));
        area.add(new Area(new Rectangle2D.Double(0, 0, width, height - roundY / 2)));
        return area;
    }
    private Shape createRoundBottomRight() {
        int width = getWidth();
        int height = getHeight();
        int roundX = Math.min(width, 15);
        int roundY = Math.min(height, 15);
        Area area = new Area(new RoundRectangle2D.Double(0, 0, width, height, roundX, roundY));
        area.add(new Area(new Rectangle2D.Double(0, 0, width - roundX / 2, height)));
        area.add(new Area(new Rectangle2D.Double(0, 0, width, height - roundY / 2)));
        return area;
    }

}
