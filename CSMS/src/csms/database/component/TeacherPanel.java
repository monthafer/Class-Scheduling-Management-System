package csms.database.component;

import csms.database.forms.Teacher_Form;
import csms.database.theme.SystemTheme;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TeacherPanel extends JPanel {

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getConsultHour() {
        return consultHour;
    }

    public void setConsultHour(String consultHour) {
        this.consultHour = consultHour;
    }

    public String getConsultDay() {
        return consultDay;
    }

    public void setConsultDay(String consultDay) {
        this.consultDay = consultDay;
    }

    public ImageIcon getAvatar() {
        return avatar;
    }

    public void setAvatar(ImageIcon avatar) {
        this.avatar = avatar;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
        trName.setText(teacher);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
        trDesc.setText(email);
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
    
    private String bio;
    private String contact;
    private String facebook;
    private String address;
    private String consultHour;
    private String consultDay;
    private ImageIcon avatar;
    private String teacher;
    private String email;

    private int roundTopLeft = 5;
    private int roundTopRight = 30;
    private int roundBottomLeft = 30;
    private int roundBottomRight = 5;
    
    csms.database.swing.Button dot = new csms.database.swing.Button();
    JLabel trName = new JLabel();
    JLabel trDesc = new JLabel();

    public TeacherPanel() {
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
    
    private void init(){
        setBackground(SystemTheme.mainColor.brighter());
        setRoundBottomLeft(30);
        setRoundBottomRight(5);
        setRoundTopLeft(5);
        setRoundTopRight(30);
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        
        dot.setBackground(new Color(0,0,0,0));
        dot.setIcon(new javax.swing.ImageIcon(getClass().getResource("/csms/database/icon/dots.png")));
        this.add(dot, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 0, 50, 50));
        
        trName.setFont(new java.awt.Font("Segoe UI", 1, 16));
        trName.setForeground(Color.WHITE);
        trName.setText(teacher);
        this.add(trName, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 5, 200, 20));
        
        trDesc.setForeground(new java.awt.Color(80, 80, 80));
        trDesc.setText(email);
        this.add(trDesc, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 25, -1, -1));
    }

    public void initEvent (Teacher_Form teacherform){
        dot.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae){
                teacherform.txtAddress.setText(address);
                teacherform.txtBio.setText(bio);
                teacherform.txtContact.setText(contact);
                teacherform.txtDay.setText(consultDay);
                teacherform.txtEmail.setText(email);
                teacherform.txtHour.setText(consultHour);
                teacherform.txtName.setText(teacher);
                teacherform.avatar.setImage(avatar);
                if (facebook == null || "".equals(facebook)){
                    teacherform.txtFb.setText("none");
                } else {
                    teacherform.txtFb.setText(facebook);
                }
            }
        });
    }
}
