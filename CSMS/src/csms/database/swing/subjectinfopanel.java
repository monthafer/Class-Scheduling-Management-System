package csms.database.swing;

import csms.databaseconnection.DBConnection;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class subjectinfopanel extends javax.swing.JPanel {
    
    Connection dbconn = DBConnection.openConnection();

    public subjectinfopanel() {
        initComponents();
        init();
    }
    
    public void updatetext(String Subject, int hourstart, int minutestart, int hourend, int minuteend, int teacherid, int sgs, String day1, String day2, String description){
        subject.setText(Subject);
        time.setText(convertTime(hourstart, minutestart) + " - " + convertTime(hourend, minuteend));
        teacher.setText(convertTeacher(teacherid));
        this.sgs.setText(convertSgs(sgs));
        desc.setText(description);
        if(!day2.equals("null")){
            day.setText("Every " + day1 + " and " + day2);
        } else {
            day.setText("Every " + day1);
        }
    }
    
    private String convertTeacher(int id){
        if(id == 0){
            return "*(No one is currently handling this subject)*";
        } else {
            String name = "";
            try {
                String query = "SELECT * FROM teacher WHERE tea_id=?";
                PreparedStatement ps = dbconn.prepareStatement(query);
                ps.setInt(1, id);
                ResultSet rs = ps.executeQuery();
                if(rs.next()){
                    name = rs.getString("first_name") + " " + rs.getString("last_name");
                }
            } catch (SQLException ex) {
                Logger.getLogger(subjectinfopanel.class.getName()).log(Level.SEVERE, null, ex);
            }
            return name;
        }
    }
    
    private String convertSgs(int id){
        if(id == 0){
            return "*(This is a rare bug, i think)*";
        } else {
            String name = "";
            try {
                String query = "SELECT * FROM sgs WHERE sgs_id=?";
                PreparedStatement ps = dbconn.prepareStatement(query);
                ps.setInt(1, id);
                ResultSet rs = ps.executeQuery();
                if(rs.next()){
                    name = rs.getString("strand") + " " + rs.getString("grade") + "-" + rs.getString("section");
                }
            } catch (SQLException ex) {
                Logger.getLogger(subjectinfopanel.class.getName()).log(Level.SEVERE, null, ex);
            }
            return name;
        }
    }
    
    private void init(){
        scrolldesc.setViewportBorder(null);
        scrolldesc.setOpaque(false);
        scrolldesc.getViewport().setOpaque(false);
    }
    
    private String convertTime(int hour, int minute){
        String min = null;
        if(minute == 0){
            min = "00";
        } else {
            min = String.valueOf(minute);
        }
        if(hour>12){
            return String.valueOf(hour - 12) + ":" + min + " PM";
        } else {
            return String.valueOf(hour) + ":" + min + " AM";
        }
    }
    
    private int roundTopLeft = 30;
    private int roundTopRight = 30;
    private int roundBottomLeft = 30;
    private int roundBottomRight = 30;
    
    @Override
    protected void paintComponent(Graphics grphcs) {
        Graphics2D g2 = (Graphics2D) grphcs.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(Color.WHITE);
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

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        subject = new javax.swing.JLabel();
        lbteacher = new javax.swing.JLabel();
        lbdescription = new javax.swing.JLabel();
        teacher = new javax.swing.JLabel();
        scrolldesc = new javax.swing.JScrollPane();
        desc = new javax.swing.JTextArea();
        lbclassdays = new javax.swing.JLabel();
        lbclasstime = new javax.swing.JLabel();
        day = new javax.swing.JLabel();
        time = new javax.swing.JLabel();
        lbout = new javax.swing.JLabel();
        sgs = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setOpaque(false);

        subject.setFont(new java.awt.Font("Bahnschrift", 1, 18)); // NOI18N
        subject.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        subject.setText("jLabel1");

        lbteacher.setFont(new java.awt.Font("Bahnschrift", 0, 12)); // NOI18N
        lbteacher.setText("Teacher:");

        lbdescription.setFont(new java.awt.Font("Bahnschrift", 0, 12)); // NOI18N
        lbdescription.setText("Description:");

        teacher.setFont(new java.awt.Font("Bahnschrift", 0, 12)); // NOI18N
        teacher.setText("Teacher's name");

        scrolldesc.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        desc.setColumns(20);
        desc.setFont(new java.awt.Font("Bahnschrift", 0, 12)); // NOI18N
        desc.setLineWrap(true);
        desc.setRows(5);
        desc.setBorder(null);
        scrolldesc.setViewportView(desc);

        lbclassdays.setFont(new java.awt.Font("Bahnschrift", 0, 12)); // NOI18N
        lbclassdays.setText("Class days:");

        lbclasstime.setFont(new java.awt.Font("Bahnschrift", 0, 12)); // NOI18N
        lbclasstime.setText("Class Time:");

        day.setFont(new java.awt.Font("Bahnschrift", 0, 12)); // NOI18N
        day.setText("Class Dayss");

        time.setFont(new java.awt.Font("Bahnschrift", 0, 12)); // NOI18N
        time.setText("CLass times");

        lbout.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        lbout.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbout.setText("(Tap outside the box to exit)");

        sgs.setFont(new java.awt.Font("Bahnschrift", 0, 12)); // NOI18N
        sgs.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        sgs.setText("TVL-ICT 12-1");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lbclasstime)
                                .addGap(18, 18, 18)
                                .addComponent(time))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lbclassdays)
                                .addGap(18, 18, 18)
                                .addComponent(day))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lbdescription)
                                .addGap(18, 18, 18)
                                .addComponent(scrolldesc, javax.swing.GroupLayout.PREFERRED_SIZE, 319, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lbteacher)
                                .addGap(18, 18, 18)
                                .addComponent(teacher)))
                        .addGap(0, 46, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(sgs, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(subject, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbout, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(subject)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sgs)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbteacher)
                    .addComponent(teacher))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbdescription)
                    .addComponent(scrolldesc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbclassdays)
                    .addComponent(day))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbclasstime)
                    .addComponent(time))
                .addGap(24, 24, 24)
                .addComponent(lbout)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel day;
    private javax.swing.JTextArea desc;
    private javax.swing.JLabel lbclassdays;
    private javax.swing.JLabel lbclasstime;
    private javax.swing.JLabel lbdescription;
    private javax.swing.JLabel lbout;
    private javax.swing.JLabel lbteacher;
    private javax.swing.JScrollPane scrolldesc;
    private javax.swing.JLabel sgs;
    private javax.swing.JLabel subject;
    private javax.swing.JLabel teacher;
    private javax.swing.JLabel time;
    // End of variables declaration//GEN-END:variables
}
