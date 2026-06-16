package csms.database.component;

import csms.database.forms.Subject_Form;
import csms.database.theme.SystemTheme;
import csms.databaseconnection.DBConnection;
import java.sql.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class SubjectPanel extends JPanel {

    public String getLoc() {
        return loc;
    }

    public void setLoc(String loc) {
        this.loc = loc;
    }

    public String getSubdesc() {
        return subdesc;
    }

    public void setSubdesc(String subdesc) {
        this.subdesc = subdesc;
    }

    public int getRoundTopLeft() {
        return roundTopLeft;
    }

    public void setRoundTopLeft(int roundTopLeft) {
        this.roundTopLeft = roundTopLeft;
        repaint();
    }

    public int getRoundTopRight() {
        return roundTopRight;
    }

    public void setRoundTopRight(int roundTopRight) {
        this.roundTopRight = roundTopRight;
        repaint();
    }

    public int getRoundBottomLeft() {
        return roundBottomLeft;
    }

    public void setRoundBottomLeft(int roundBottomLeft) {
        this.roundBottomLeft = roundBottomLeft;
        repaint();
    }

    public int getRoundBottomRight() {
        return roundBottomRight;
    }

    public void setRoundBottomRight(int roundBottomRight) {
        this.roundBottomRight = roundBottomRight;
        repaint();
    }
    
    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
        txtSubject.setText(subject);
    }
    
    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
        txtDay.setText(day);
    }
    
    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
        txtTime.setText(time);
    }
    
    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
        txtTeacher.setText(teacher);
    }
    
    public SubjectPanel(String subject, String teacher, String day, String time){
        this.subject = subject;
        this.day = day;
        this.time = time;
        this.teacher = teacher;
    }

    private int roundTopLeft =30;
    private int roundTopRight = 10;
    private int roundBottomLeft = 10;
    private int roundBottomRight = 30;
    private String loc;
    private String subject;
    private String day;
    private String time;
    private String teacher;
    private String subdesc;
    JLabel txtSubject = new JLabel();
    JLabel txtTime = new JLabel();
    JLabel txtTeacher = new JLabel();
    JLabel txtDay = new JLabel();
    csms.database.swing.Button dot = new csms.database.swing.Button();
    private SubjectDes desc;
    Connection dbconn = DBConnection.openConnection();
    
    public SubjectDes getDesc() {
        return desc;
    }

    public SubjectPanel() {
        setOpaque(false);
        init();
    }

    @Override
    protected void paintComponent(Graphics grphcs) {
        Graphics2D g2 = (Graphics2D) grphcs.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(SystemTheme.mainColor);
        Area area = new Area(createRoundTopLeft());
        if (roundTopRight > 0) {
            area.intersect(new Area(createRoundTopRight()));
        }
        if (roundBottomLeft > 0) {
            area.intersect(new Area(createRoundBottomLeft()));
        }
        if (roundBottomRight > 0) {
            area.intersect(new Area(createRoundBottomRight()));
        }
        g2.fill(area);
        g2.dispose();
        super.paintComponent(grphcs);
    }

    private Shape createRoundTopLeft() {
        int width = getWidth();
        int height = getHeight();
        int roundX = Math.min(width, roundTopLeft);
        int roundY = Math.min(height, roundTopLeft);
        Area area = new Area(new RoundRectangle2D.Double(0, 0, width, height, roundX, roundY));
        area.add(new Area(new Rectangle2D.Double(roundX / 2, 0, width - roundX / 2, height)));
        area.add(new Area(new Rectangle2D.Double(0, roundY / 2, width, height - roundY / 2)));
        return area;
    }

    private Shape createRoundTopRight() {
        int width = getWidth();
        int height = getHeight();
        int roundX = Math.min(width, roundTopRight);
        int roundY = Math.min(height, roundTopRight);
        Area area = new Area(new RoundRectangle2D.Double(0, 0, width, height, roundX, roundY));
        area.add(new Area(new Rectangle2D.Double(0, 0, width - roundX / 2, height)));
        area.add(new Area(new Rectangle2D.Double(0, roundY / 2, width, height - roundY / 2)));
        return area;
    }

    private Shape createRoundBottomLeft() {
        int width = getWidth();
        int height = getHeight();
        int roundX = Math.min(width, roundBottomLeft);
        int roundY = Math.min(height, roundBottomLeft);
        Area area = new Area(new RoundRectangle2D.Double(0, 0, width, height, roundX, roundY));
        area.add(new Area(new Rectangle2D.Double(roundX / 2, 0, width - roundX / 2, height)));
        area.add(new Area(new Rectangle2D.Double(0, 0, width, height - roundY / 2)));
        return area;
    }

    private Shape createRoundBottomRight() {
        int width = getWidth();
        int height = getHeight();
        int roundX = Math.min(width, roundBottomRight);
        int roundY = Math.min(height, roundBottomRight);
        Area area = new Area(new RoundRectangle2D.Double(0, 0, width, height, roundX, roundY));
        area.add(new Area(new Rectangle2D.Double(0, 0, width - roundX / 2, height)));
        area.add(new Area(new Rectangle2D.Double(0, 0, width, height - roundY / 2)));
        return area;
    }

    private void init() {
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        
        txtSubject.setFont(new Font("Segoe UI", 1, 18));
        txtSubject.setForeground(Color.WHITE);
        txtSubject.setText(subject);
        add(txtSubject, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 5, -1, -1));
        
        txtTime.setForeground(new Color(230,230,230));
        txtTime.setText(time);
        add(txtTime, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 75, -1, 20));
        
        txtTeacher.setForeground(new Color(230,230,230));
        txtTeacher.setText(teacher);
        add(txtTeacher, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 25, -1, 20));
        
        JLabel nextclass = new JLabel();
        nextclass.setText("Next Class:");
        add(nextclass, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 45, -1, 20));
        
        txtDay.setForeground(new Color(230,230,230));
        txtDay.setText(day);
        add(txtDay, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 60, -1, 20));
        
        dot.setBackground(new Color(0,0,0,0));
        dot.setIcon(new javax.swing.ImageIcon(getClass().getResource("/csms/database/icon/dots.png")));
        add(dot, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 5, -1, -1));
        
        JLabel line = new JLabel();
        line.setIcon(new javax.swing.ImageIcon(getClass().getResource("/csms/database/icon/line.png")));
        add(line, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, -1, -1));
    }
    
    public void initEvent(Subject_Form subjectform){
        dot.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                desc = new SubjectDes(0, subject, teacher, day, time);
                if(!subjectform.Dsubject.isVisible()){
                    subjectform.descbg.setVisible(true);
                    subjectform.Dsubject.setVisible(true);
                    subjectform.Dteacher.setVisible(true);
                    subjectform.Ddays.setVisible(true);
                    subjectform.Dtime.setVisible(true);
                    subjectform.teaD.setVisible(true);
                    subjectform.daysD.setVisible(true);
                    subjectform.timeD.setVisible(true);
                    subjectform.descD.setVisible(true);
                    subjectform.Ddesc.setVisible(true);
                    subjectform.locationD.setVisible(true);
                    subjectform.Dlocation.setVisible(true);
                }
                    subjectform.Dsubject.setText(subject);
                    subjectform.Dteacher.setText(teacher);
                    subjectform.Ddays.setText(day);
                    subjectform.Dtime.setText(time);
                    subjectform.Ddesc.setText(subdesc);
                    subjectform.Dlocation.setText(loc);
            }
        });
    }

}
