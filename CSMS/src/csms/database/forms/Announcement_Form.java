package csms.database.forms;

import csms.database.announce.swing.announcementpanels;
import csms.database.announce.swing.postannounce;
import csms.database.component.Form;
import csms.database.glasspane.GlassPanePopup;
import csms.database.properties.SystemProperties;
import csms.database.scrollbar.ScrollBarCustom;
import csms.databaseconnection.DBConnection;
import java.awt.Color;
import java.awt.Font;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import net.miginfocom.swing.MigLayout;

public class Announcement_Form extends Form {
    
    Connection dbconn = DBConnection.openConnection();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    SimpleDateFormat df = new SimpleDateFormat("MMMM dd, yyyy");
    SystemProperties pro = new SystemProperties();

    public Announcement_Form() {
        initComponents();
        init();
        todayupdate();
        upcomingupdate();
        historyupdate();
    }
    
    private void init(){
        todayscroll.getViewport().setOpaque(false);
        todayscroll.setVerticalScrollBar(new ScrollBarCustom());
        upscroll.getViewport().setOpaque(false);
        upscroll.setVerticalScrollBar(new ScrollBarCustom());
        lastscroll.getViewport().setOpaque(false);
        lastscroll.setVerticalScrollBar(new ScrollBarCustom());
        todaypanel.setLayout(new MigLayout("wrap", "push[center]push"));
        uppanel.setLayout(new MigLayout("wrap", "push[center]push"));
        lastpanel.setLayout(new MigLayout("wrap", "push[center]push"));
        pro.loadFromFile();
        if(pro.getPosition().equals("Student")){
            post.setVisible(false);
        }
    }
    
    private void todayupdate(){
        todaypanel.removeAll();
        pro.loadFromFile();
        try {
            String query = "SELECT * FROM announce WHERE timecreated=? OR (sgs=? OR sgs=?)";
            if(!pro.getPosition().equals("Student")){
                query = "SELECT * FROM announce WHERE timecreated=?";
            }
            PreparedStatement ps = dbconn.prepareStatement(query);
            ps.setString(1, sdf.format(new Date()));
            if(pro.getPosition().equals("Student")){
                ps.setNull(2, java.sql.Types.INTEGER);
                ps.setString(3, pro.getSgs());
            }
            ResultSet rs = ps.executeQuery();
            if(!rs.isBeforeFirst()){
                JLabel label = new JLabel();
                label.setFont(new Font("Candara", 0, 12));
                label.setForeground(new Color(10,10,10));
                label.setText("There's no announcement today");
                todaypanel.add(label);
            } else {
                while(rs.next()){
                    int p = rs.getInt("tea_id");
                    String pos = "Teacher";
                    String title = rs.getString("title");
                    String date = df.format(rs.getDate("timecreated"));
                    if(p == 0){
                        p = rs.getInt("admin_id");
                        pos = "Admin";
                    }
                    announcementpanels atp = new announcementpanels();
                    atp.updatetext(convertIcon(p, pos), title, date, convertName(p, pos));
                    todaypanel.add(atp);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Announcement_Form.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void upcomingupdate(){
        uppanel.removeAll();
        pro.loadFromFile();
        try {
            String query = "SELECT * FROM announce WHERE timecreated>? AND (sgs=? OR sgs=?)";
            if(!pro.getPosition().equals("Student")){
                query = "SELECT * FROM announce WHERE timecreated>?";
            }
            PreparedStatement ps = dbconn.prepareStatement(query);
            ps.setString(1, sdf.format(new Date()));
            if(pro.getPosition().equals("Student")){
                ps.setNull(2, java.sql.Types.INTEGER);
                ps.setString(3, pro.getSgs());
            }
            ResultSet rs = ps.executeQuery();
            if(!rs.isBeforeFirst()){
                JLabel label = new JLabel();
                label.setFont(new Font("Candara", 0, 12));
                label.setForeground(new Color(10,10,10));
                label.setText("There's no upcoming announcement");
                uppanel.add(label);
            } else {
                while(rs.next()){
                    int p = rs.getInt("tea_id");
                    String pos = "Teacher";
                    String title = rs.getString("title");
                    String date = df.format(rs.getDate("timecreated"));
                    if(p == 0){
                        p = rs.getInt("admin_id");
                        pos = "Admin";
                    }
                    announcementpanels atp = new announcementpanels();
                    atp.updatetext(convertIcon(p, pos), title, date, convertName(p, pos));
                    uppanel.add(atp);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Announcement_Form.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void historyupdate(){
        lastpanel.removeAll();
        pro.loadFromFile();
        try {
            String query = "SELECT * FROM announce WHERE timecreated<? AND (sgs=? OR sgs=?)";
            if(!pro.getPosition().equals("Student")){
                query = "SELECT * FROM announce WHERE timecreated<?";
            }
            PreparedStatement ps = dbconn.prepareStatement(query);
            ps.setString(1, sdf.format(new Date()));
            if(pro.getPosition().equals("Student")){
                ps.setNull(2, java.sql.Types.INTEGER);
                ps.setString(3, pro.getSgs());
            }
            ResultSet rs = ps.executeQuery();
            if(!rs.isBeforeFirst()){
                JLabel label = new JLabel();
                label.setFont(new Font("Candara", 0, 12));
                label.setForeground(new Color(10,10,10));
                label.setText("There's no recent announcement");
                lastpanel.add(label);
            } else {
                while(rs.next()){
                    int p = rs.getInt("tea_id");
                    String pos = "Teacher";
                    String title = rs.getString("title");
                    String date = df.format(rs.getDate("timecreated"));
                    if(p == 0){
                        p = rs.getInt("admin_id");
                        pos = "Admin";
                    }
                    announcementpanels atp = new announcementpanels();
                    atp.updatetext(convertIcon(p, pos), title, date, convertName(p, pos));
                    lastpanel.add(atp);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Announcement_Form.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private String convertName(int id, String type){
        String name = "";
        try {
            String query = "SELECT * FROM admin WHERE admin_id=?";
            if(type.equals("Teacher")){
                query = "SELECT * FROM teacher WHERE tea_id=?";
            }
            PreparedStatement ps = dbconn.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                name = rs.getString("first_name") + " " + rs.getString("last_name");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Announcement_Form.class.getName()).log(Level.SEVERE, null, ex);
        }
        return name;
    }
    
    private ImageIcon convertIcon(int id, String type){
        ImageIcon icon = null;
        try {
            String query = "SELECT * FROM admin WHERE admin_id=?";
            if(type.equals("Teacher")){
                query = "SELECT * FROM teacher WHERE tea_id=?";
            }
            PreparedStatement ps = dbconn.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                byte[] imgdata = rs.getBytes("img_avatar");
                if(imgdata == null){
                    icon = new ImageIcon(getClass().getResource("/csms/database/icon/avatarimg.png"));
                } else {
                    icon = new ImageIcon(imgdata);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Announcement_Form.class.getName()).log(Level.SEVERE, null, ex);
        }
        return icon;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        todayoutpanel = new csms.database.announce.swing.ColorPanelRound();
        lbtoday = new javax.swing.JLabel();
        todayscroll = new javax.swing.JScrollPane();
        todaypanel = new javax.swing.JPanel();
        upoutpanel = new csms.database.announce.swing.ColorPanelRound();
        lbup = new javax.swing.JLabel();
        upscroll = new javax.swing.JScrollPane();
        uppanel = new javax.swing.JPanel();
        lastoutpanel = new csms.database.announce.swing.ColorPanelRound();
        lblast = new javax.swing.JLabel();
        lastscroll = new javax.swing.JScrollPane();
        lastpanel = new javax.swing.JPanel();
        post = new csms.database.announce.swing.Button();

        lbtoday.setFont(new java.awt.Font("Bahnschrift", 0, 14)); // NOI18N
        lbtoday.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbtoday.setText("Todays announcement");

        todayscroll.setBorder(null);
        todayscroll.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        todayscroll.setMaximumSize(new java.awt.Dimension(32767, 125));
        todayscroll.setOpaque(false);

        todaypanel.setOpaque(false);

        javax.swing.GroupLayout todaypanelLayout = new javax.swing.GroupLayout(todaypanel);
        todaypanel.setLayout(todaypanelLayout);
        todaypanelLayout.setHorizontalGroup(
            todaypanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 826, Short.MAX_VALUE)
        );
        todaypanelLayout.setVerticalGroup(
            todaypanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 142, Short.MAX_VALUE)
        );

        todayscroll.setViewportView(todaypanel);

        javax.swing.GroupLayout todayoutpanelLayout = new javax.swing.GroupLayout(todayoutpanel);
        todayoutpanel.setLayout(todayoutpanelLayout);
        todayoutpanelLayout.setHorizontalGroup(
            todayoutpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(todayoutpanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(todayoutpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbtoday, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(todayscroll, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        todayoutpanelLayout.setVerticalGroup(
            todayoutpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(todayoutpanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbtoday)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(todayscroll, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        lbup.setFont(new java.awt.Font("Bahnschrift", 0, 14)); // NOI18N
        lbup.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbup.setText("Upcoming announcement");

        upscroll.setBorder(null);
        upscroll.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        upscroll.setMaximumSize(new java.awt.Dimension(32767, 125));
        upscroll.setOpaque(false);

        uppanel.setOpaque(false);

        javax.swing.GroupLayout uppanelLayout = new javax.swing.GroupLayout(uppanel);
        uppanel.setLayout(uppanelLayout);
        uppanelLayout.setHorizontalGroup(
            uppanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 826, Short.MAX_VALUE)
        );
        uppanelLayout.setVerticalGroup(
            uppanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 142, Short.MAX_VALUE)
        );

        upscroll.setViewportView(uppanel);

        javax.swing.GroupLayout upoutpanelLayout = new javax.swing.GroupLayout(upoutpanel);
        upoutpanel.setLayout(upoutpanelLayout);
        upoutpanelLayout.setHorizontalGroup(
            upoutpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(upoutpanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(upoutpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbup, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(upscroll, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        upoutpanelLayout.setVerticalGroup(
            upoutpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(upoutpanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbup)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(upscroll, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        lblast.setFont(new java.awt.Font("Bahnschrift", 0, 14)); // NOI18N
        lblast.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblast.setText("Last announcement");

        lastscroll.setBorder(null);
        lastscroll.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        lastscroll.setMaximumSize(new java.awt.Dimension(32767, 125));
        lastscroll.setOpaque(false);

        lastpanel.setOpaque(false);

        javax.swing.GroupLayout lastpanelLayout = new javax.swing.GroupLayout(lastpanel);
        lastpanel.setLayout(lastpanelLayout);
        lastpanelLayout.setHorizontalGroup(
            lastpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 826, Short.MAX_VALUE)
        );
        lastpanelLayout.setVerticalGroup(
            lastpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 142, Short.MAX_VALUE)
        );

        lastscroll.setViewportView(lastpanel);

        javax.swing.GroupLayout lastoutpanelLayout = new javax.swing.GroupLayout(lastoutpanel);
        lastoutpanel.setLayout(lastoutpanelLayout);
        lastoutpanelLayout.setHorizontalGroup(
            lastoutpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(lastoutpanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(lastoutpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblast, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lastscroll, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        lastoutpanelLayout.setVerticalGroup(
            lastoutpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(lastoutpanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblast)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lastscroll, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        post.setText("Post announcement");
        post.setFont(new java.awt.Font("Bahnschrift", 0, 12)); // NOI18N
        post.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                postActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(todayoutpanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(upoutpanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lastoutpanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(post, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(todayoutpanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(upoutpanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lastoutpanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(post, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void postActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_postActionPerformed
        GlassPanePopup.showPopup(new postannounce());
        todayupdate();
        upcomingupdate();
        historyupdate();
    }//GEN-LAST:event_postActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private csms.database.announce.swing.ColorPanelRound lastoutpanel;
    private javax.swing.JPanel lastpanel;
    private javax.swing.JScrollPane lastscroll;
    private javax.swing.JLabel lblast;
    private javax.swing.JLabel lbtoday;
    private javax.swing.JLabel lbup;
    private csms.database.announce.swing.Button post;
    private csms.database.announce.swing.ColorPanelRound todayoutpanel;
    private javax.swing.JPanel todaypanel;
    private javax.swing.JScrollPane todayscroll;
    private csms.database.announce.swing.ColorPanelRound upoutpanel;
    private javax.swing.JPanel uppanel;
    private javax.swing.JScrollPane upscroll;
    // End of variables declaration//GEN-END:variables
}
