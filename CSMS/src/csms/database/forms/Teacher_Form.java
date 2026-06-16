package csms.database.forms;

import csms.database.component.Form;
import csms.database.component.TeacherPanel;
import csms.database.properties.SystemProperties;
import csms.database.scrollbar.ScrollBarCustom;
import csms.database.theme.SystemTheme;
import csms.database.theme.ThemeColorChange;
import csms.databaseconnection.DBConnection;
import java.awt.Color;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import net.miginfocom.swing.MigLayout;

public class Teacher_Form extends Form {

    Connection dbconn = DBConnection.openConnection();
    SystemProperties pro = new SystemProperties();
    
    public Teacher_Form() {
        initComponents();
        initDark();
        panelup.setBackground(SystemTheme.mainColor);
        initUpdate();
    }
    
    private void initDark(){
        if(ThemeColorChange.getInstance().getMode() == ThemeColorChange.Mode.DARK){
            txtBio.setForeground(new Color(230,230,230));
            txtEmail.setForeground(new Color(230,230,230));
            txtAddress.setForeground(new Color(230,230,230));
            txtContact.setForeground(new Color(230,230,230));
            txtDay.setForeground(new Color(230,230,230));
            txtFb.setForeground(new Color(230,230,230));
            txtHour.setForeground(new Color(230,230,230));
            txtName.setForeground(new Color(125,125,125));
            biotxt.setForeground(new Color(230,230,230));
            addresstxt.setForeground(new Color(230,230,230));
            daytxt.setForeground(new Color(230,230,230));
            emailtxt.setForeground(new Color(230,230,230));
            fbtxt.setForeground(new Color(230,230,230));
            hourtxt.setForeground(new Color(230,230,230));
            numtxt.setForeground(new Color(230,230,230));
            bottom.setForeground(new Color(200,200,200));
        } else{
            txtBio.setForeground(new Color(80,80,80));
            txtEmail.setForeground(new Color(80,80,80));
            txtAddress.setForeground(new Color(80,80,80));
            txtContact.setForeground(new Color(80,80,80));
            txtDay.setForeground(new Color(80,80,80));
            txtFb.setForeground(new Color(80,80,80));
            txtHour.setForeground(new Color(80,80,80));
            txtName.setForeground(new Color(130,130,130));
            biotxt.setForeground(new Color(80,80,80));
            addresstxt.setForeground(new Color(80,80,80));
            daytxt.setForeground(new Color(80,80,80));
            emailtxt.setForeground(new Color(80,80,80));
            fbtxt.setForeground(new Color(80,80,80));
            hourtxt.setForeground(new Color(80,80,80));
            numtxt.setForeground(new Color(80,80,80));
            bottom.setForeground(new Color(51,51,51));
        }
        scroll.setOpaque(false);
        scroll.setBackground(new Color(0, 0, 0, 0));
        scroll.getViewport().setOpaque(false);
        scroll.setBorder(null);
        scroll.setViewportBorder(null);
        scroll.setVerticalScrollBar(new ScrollBarCustom());
        pro.loadFromFile();
        if(pro.getPosition().equals("Administrator")){
            bottom.setVisible(true);
        } else {
            bottom.setVisible(false);
        }
        
    }
    
    private void initUpdate(){
        choosepanel.setLayout(new MigLayout("wrap", "push[center]push"));
        try {
            Statement stmt = dbconn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM teacher");
            while(rs.next()){
                String name = rs.getString("first_name") + " " + rs.getString("last_name");
                String bio = rs.getString("tea_desc");
                String email = rs.getString("email");
                String contact = rs.getString("contact_no");
                String facebook = rs.getString("facebook");
                String address = rs.getString("address");
                String hours = rs.getString("consult_hours");
                String days = rs.getString("consult_days");
                byte[] imagedata = rs.getBytes("img_avatar");
                ImageIcon format;
                if (imagedata != null) {
                    format = new ImageIcon(imagedata);
                } else {
                    format = new ImageIcon(getClass().getResource("/csms/database/icon/avatarimg.png"));
                }
                
                TeacherPanel teacher = new TeacherPanel();
                teacher.setTeacher(name);
                teacher.setEmail(email);
                teacher.setAddress(address);
                teacher.setAvatar(format);
                teacher.setBio(bio);
                teacher.setConsultDay(days);
                teacher.setContact(contact);
                teacher.setConsultHour(hours);
                teacher.setFacebook(facebook);
                teacher.initEvent(this);
                choosepanel.add(teacher);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Teacher_Form.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bgpanel = new javax.swing.JPanel();
        txtName = new javax.swing.JLabel();
        emailtxt = new javax.swing.JLabel();
        numtxt = new javax.swing.JLabel();
        hourtxt = new javax.swing.JLabel();
        daytxt = new javax.swing.JLabel();
        avatar = new csms.database.theme.ImageAvatar();
        biotxt = new javax.swing.JLabel();
        fbtxt = new javax.swing.JLabel();
        addresstxt = new javax.swing.JLabel();
        txtEmail = new javax.swing.JLabel();
        txtContact = new javax.swing.JLabel();
        txtHour = new javax.swing.JLabel();
        txtDay = new javax.swing.JLabel();
        txtFb = new javax.swing.JLabel();
        txtAddress = new javax.swing.JLabel();
        txtBio = new javax.swing.JLabel();
        panelup = new csms.database.swing.PanelRound();
        scroll = new javax.swing.JScrollPane();
        choosepanel = new javax.swing.JPanel();
        bottom = new javax.swing.JLabel();

        bgpanel.setOpaque(false);

        txtName.setBackground(new java.awt.Color(130, 130, 130));
        txtName.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        txtName.setForeground(new java.awt.Color(130, 130, 130));
        txtName.setText("Monthafer Barani");

        emailtxt.setText("Email:");

        numtxt.setText("Contact Number:");

        hourtxt.setText("Consultation Hours:");

        daytxt.setText("Consultation Days:");

        avatar.setImage(new javax.swing.ImageIcon(getClass().getResource("/csms/database/icon/avatarimg.png"))); // NOI18N

        biotxt.setText("Bio:");

        fbtxt.setText("Facebook Account:");

        addresstxt.setText("Address:");

        txtEmail.setText("barani.ma111@shs.msumain.edu.ph");

        txtContact.setText("09639619732");

        txtHour.setText("1:00pm - 4:00pm");

        txtDay.setText("Everyday");

        txtFb.setText("Monthafer Barani");

        txtAddress.setText("MSU - Fisheries Village, Marawi City");

        txtBio.setText("Your friendly neighborhood ICT student!");

        javax.swing.GroupLayout bgpanelLayout = new javax.swing.GroupLayout(bgpanel);
        bgpanel.setLayout(bgpanelLayout);
        bgpanelLayout.setHorizontalGroup(
            bgpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bgpanelLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(bgpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtName)
                    .addGroup(bgpanelLayout.createSequentialGroup()
                        .addComponent(daytxt)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtDay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(bgpanelLayout.createSequentialGroup()
                        .addComponent(hourtxt)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtHour, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(bgpanelLayout.createSequentialGroup()
                        .addComponent(numtxt)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtContact, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(bgpanelLayout.createSequentialGroup()
                        .addComponent(emailtxt)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(bgpanelLayout.createSequentialGroup()
                        .addComponent(biotxt)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtBio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(bgpanelLayout.createSequentialGroup()
                        .addComponent(addresstxt)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtAddress, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(bgpanelLayout.createSequentialGroup()
                        .addComponent(fbtxt)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtFb, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 78, Short.MAX_VALUE)
                .addComponent(avatar, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        bgpanelLayout.setVerticalGroup(
            bgpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bgpanelLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(bgpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(avatar, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(bgpanelLayout.createSequentialGroup()
                        .addComponent(txtName)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(bgpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(biotxt)
                            .addComponent(txtBio))
                        .addGap(61, 61, 61)
                        .addGroup(bgpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(emailtxt)
                            .addComponent(txtEmail))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(bgpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(numtxt)
                            .addComponent(txtContact))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(bgpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(hourtxt)
                            .addComponent(txtHour))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(bgpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(daytxt)
                            .addComponent(txtDay))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(bgpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(fbtxt)
                            .addComponent(txtFb))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(bgpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(addresstxt)
                            .addComponent(txtAddress))))
                .addContainerGap(132, Short.MAX_VALUE))
        );

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

        scroll.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        choosepanel.setOpaque(false);

        javax.swing.GroupLayout choosepanelLayout = new javax.swing.GroupLayout(choosepanel);
        choosepanel.setLayout(choosepanelLayout);
        choosepanelLayout.setHorizontalGroup(
            choosepanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 273, Short.MAX_VALUE)
        );
        choosepanelLayout.setVerticalGroup(
            choosepanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 382, Short.MAX_VALUE)
        );

        scroll.setViewportView(choosepanel);

        bottom.setFont(new java.awt.Font("Segoe UI", 0, 9)); // NOI18N
        bottom.setForeground(new java.awt.Color(51, 51, 51));
        bottom.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        bottom.setText("*You can manage teachers in the 'Admin' tab.");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelup, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(bgpanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(scroll)
                            .addComponent(bottom, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelup, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(bgpanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(bottom)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(scroll, javax.swing.GroupLayout.PREFERRED_SIZE, 365, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(12, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel addresstxt;
    public csms.database.theme.ImageAvatar avatar;
    private javax.swing.JPanel bgpanel;
    private javax.swing.JLabel biotxt;
    private javax.swing.JLabel bottom;
    private javax.swing.JPanel choosepanel;
    private javax.swing.JLabel daytxt;
    private javax.swing.JLabel emailtxt;
    private javax.swing.JLabel fbtxt;
    private javax.swing.JLabel hourtxt;
    private javax.swing.JLabel numtxt;
    private csms.database.swing.PanelRound panelup;
    private javax.swing.JScrollPane scroll;
    public javax.swing.JLabel txtAddress;
    public javax.swing.JLabel txtBio;
    public javax.swing.JLabel txtContact;
    public javax.swing.JLabel txtDay;
    public javax.swing.JLabel txtEmail;
    public javax.swing.JLabel txtFb;
    public javax.swing.JLabel txtHour;
    public javax.swing.JLabel txtName;
    // End of variables declaration//GEN-END:variables
}
