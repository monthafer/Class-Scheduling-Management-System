package csms.database.forms;

import csms.database.component.Form;
import csms.database.component.SubjectPanel;
import csms.database.properties.SystemProperties;
import csms.database.theme.SystemTheme;
import csms.databaseconnection.DBConnection;
import java.awt.Color;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextArea;
import net.miginfocom.swing.MigLayout;

public class Subject_Form extends Form {
    
    private SubjectPanel subpanel;
    Connection dbconn = DBConnection.openConnection();
    String sgs = "";

    public Subject_Form() {
        initComponents();
        init();
        initUpdate();
    }
    
    private void init(){
        panelup.setBackground(SystemTheme.mainColor);
        scroll.setOpaque(false);
        scroll.setBackground(new Color(0, 0, 0, 0));
        scroll.getViewport().setOpaque(false);
        scroll.setBorder(null);
        scroll.setViewportBorder(null);
        Ddesc.setOpaque(false);
        Ddesc.setBackground(new Color(0, 0, 0, 0));
        Ddesc.setAlignmentX(JTextArea.CENTER_ALIGNMENT);
        
        descbg.setVisible(false);
        Dsubject.setVisible(false);
        Dteacher.setVisible(false);
        Ddays.setVisible(false);
        Dtime.setVisible(false);
        Ddesc.setVisible(false);
        Dlocation.setVisible(false);
        teaD.setVisible(false);
        daysD.setVisible(false);
        timeD.setVisible(false);
        descD.setVisible(false);
        locationD.setVisible(false);
    }
    
    //name, sem, desc, day1, day2, tea, hour1, min1, hour2, min2, loc_id
    
    private void initUpdate(){
        bgPanel.setLayout(new MigLayout());
        SystemProperties pro = new SystemProperties();
        pro.loadFromFile();
        sgs = pro.getSgs();
        try {
            Statement stmt = dbconn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM subject WHERE sgs_id=" + sgs);
            int x=0;
            while (rs.next()) {
                String name = rs.getString("name");
                String sem = String.valueOf(rs.getInt("sem"));
                String desc = rs.getString("desc");
                String day1 = rs.getString("day1");
                String day2 = rs.getString("day2");
                int hour1 = rs.getInt("hour1");
                int min1 = rs.getInt("min1");
                int hour2 = rs.getInt("hour2");
                int min2 = rs.getInt("min2");
                String loc = String.valueOf(rs.getInt("loc_id"));
                String tea = String.valueOf(rs.getInt("tea_id"));
                Statement st = dbconn.createStatement();
                ResultSet rs1 = st.executeQuery("SELECT first_name, last_name FROM teacher WHERE tea_id=" + tea);
                String tea_name=null;
                String location=null;
                if(rs1.next()){
                    tea_name = rs1.getString("first_name") + " " + rs1.getString("last_name");
                } else {
                    tea_name = "(No one is handling this subject)";
                }
                rs1.close();
                ResultSet rs2 = st.executeQuery("SELECT * FROM location WHERE loc_id=" + loc);
                if(rs2.next()){
                    location = rs2.getString("dep") + " room " + rs2.getString("room");
                }
                rs2.close();
                st.close();
                String minu;
                String minu2;
                String time;
                if (min1 == 0){
                    minu = ":00";
                } else {
                    minu = ":" + String.valueOf(min1);
                }
                if (min2 == 0) {
                    minu2 = ":00";
                } else {
                    minu2 = ":" + String.valueOf(min2);
                }
                if (hour1 > 12) {
                    if (hour2 > 12) {
                        time = String.valueOf(hour1 - 12) + minu + " PM - " + String.valueOf(hour2 - 12) + minu2 + " PM";
                    } else {
                        time = String.valueOf(hour1 - 12) + minu + " PM - " + String.valueOf(hour2) + minu2 + " AM";
                    }
                }
                else{
                    if (hour2 > 12) {
                        time = String.valueOf(hour1) + minu + " AM - " + String.valueOf(hour2 - 12) + minu2 + " PM";
                    } else {
                        time = String.valueOf(hour1) + minu + " AM - " + String.valueOf(hour2) + minu2 + " AM";
                    }
                }
                
                SubjectPanel panelsub = new SubjectPanel();

                if (day2 != null) {
                    panelsub.setDay(day1 + " & " + day2);
                } else {
                    panelsub.setDay(day1);
                }
                panelsub.setTeacher(tea_name);
                panelsub.setSubject(name + " " + sem);
                panelsub.setTime(time);
                panelsub.setSubdesc(desc);
                panelsub.setLoc(location);
                
                x++;

                if (x == 1 || x == 2) {
                    bgPanel.add(panelsub);
                } else if (x == 3) {
                    bgPanel.add(panelsub, "wrap");
                    x = 0;
                }
                panelsub.initEvent(this);
                
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(Subject_Form.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelup = new csms.database.swing.PanelRound();
        bgPanel = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        descbg = new csms.database.message.swing.Background();
        Dsubject = new javax.swing.JLabel();
        descD = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        teaD = new javax.swing.JLabel();
        Dteacher = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        daysD = new javax.swing.JLabel();
        Ddays = new javax.swing.JLabel();
        Dtime = new javax.swing.JLabel();
        timeD = new javax.swing.JLabel();
        Dlocation = new javax.swing.JLabel();
        locationD = new javax.swing.JLabel();
        scroll = new javax.swing.JScrollPane();
        Ddesc = new javax.swing.JTextArea();

        javax.swing.GroupLayout panelupLayout = new javax.swing.GroupLayout(panelup);
        panelup.setLayout(panelupLayout);
        panelupLayout.setHorizontalGroup(
            panelupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        panelupLayout.setVerticalGroup(
            panelupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 57, Short.MAX_VALUE)
        );

        bgPanel.setOpaque(false);

        javax.swing.GroupLayout bgPanelLayout = new javax.swing.GroupLayout(bgPanel);
        bgPanel.setLayout(bgPanelLayout);
        bgPanelLayout.setHorizontalGroup(
            bgPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 623, Short.MAX_VALUE)
        );
        bgPanelLayout.setVerticalGroup(
            bgPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 568, Short.MAX_VALUE)
        );

        jPanel3.setOpaque(false);

        descbg.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        descbg.setFocusable(false);

        Dsubject.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        Dsubject.setForeground(new java.awt.Color(230, 230, 230));
        Dsubject.setText("Subject");

        descD.setForeground(new java.awt.Color(80, 80, 80));
        descD.setText("Description:");

        jPanel1.setOpaque(false);

        teaD.setForeground(new java.awt.Color(80, 80, 80));
        teaD.setText("Teacher: ");

        Dteacher.setText("Monthafer Barani");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(teaD)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Dteacher, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(teaD)
                    .addComponent(Dteacher))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setOpaque(false);

        daysD.setForeground(new java.awt.Color(80, 80, 80));
        daysD.setText("Class days:");

        Ddays.setText("Mondays & Wednesdays");

        Dtime.setText("10:00am - 11:00am");

        timeD.setForeground(new java.awt.Color(80, 80, 80));
        timeD.setText("Class Time:");

        Dlocation.setText("PUC Room 8");

        locationD.setForeground(new java.awt.Color(80, 80, 80));
        locationD.setText("Class Location:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(locationD)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Dlocation, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(timeD)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Dtime, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(daysD)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Ddays, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(5, 5, 5))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(daysD)
                    .addComponent(Ddays))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(timeD)
                    .addComponent(Dtime))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(locationD)
                    .addComponent(Dlocation))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        scroll.setBackground(new java.awt.Color(255, 255, 255));
        scroll.setBorder(null);
        scroll.setFocusable(false);
        scroll.setOpaque(false);

        Ddesc.setEditable(false);
        Ddesc.setBackground(new java.awt.Color(255, 255, 255));
        Ddesc.setColumns(20);
        Ddesc.setLineWrap(true);
        Ddesc.setRows(5);
        Ddesc.setBorder(null);
        Ddesc.setFocusable(false);
        scroll.setViewportView(Ddesc);

        javax.swing.GroupLayout descbgLayout = new javax.swing.GroupLayout(descbg);
        descbg.setLayout(descbgLayout);
        descbgLayout.setHorizontalGroup(
            descbgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(descbgLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(descbgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(descbgLayout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addGroup(descbgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, descbgLayout.createSequentialGroup()
                                .addGroup(descbgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, descbgLayout.createSequentialGroup()
                                        .addComponent(Dsubject)
                                        .addGap(0, 0, Short.MAX_VALUE)))
                                .addGap(14, 14, 14))
                            .addComponent(descD, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, descbgLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(scroll, javax.swing.GroupLayout.DEFAULT_SIZE, 257, Short.MAX_VALUE)
                .addGap(29, 29, 29))
        );
        descbgLayout.setVerticalGroup(
            descbgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(descbgLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(Dsubject)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(descD)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scroll, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(descbg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(descbg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(92, 92, 92))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(bgPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(panelup, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelup, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(bgPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JLabel Ddays;
    public javax.swing.JTextArea Ddesc;
    public javax.swing.JLabel Dlocation;
    public javax.swing.JLabel Dsubject;
    public javax.swing.JLabel Dteacher;
    public javax.swing.JLabel Dtime;
    private javax.swing.JPanel bgPanel;
    public javax.swing.JLabel daysD;
    public javax.swing.JLabel descD;
    public csms.database.message.swing.Background descbg;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    public javax.swing.JLabel locationD;
    private csms.database.swing.PanelRound panelup;
    private javax.swing.JScrollPane scroll;
    public javax.swing.JLabel teaD;
    public javax.swing.JLabel timeD;
    // End of variables declaration//GEN-END:variables
}
