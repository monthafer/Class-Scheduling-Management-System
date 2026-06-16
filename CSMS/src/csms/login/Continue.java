package csms.login;

import csms.database.mainAdmin;
import csms.database.mainFaculty;
import csms.database.mainViewer;
import csms.database.properties.SystemProperties;
import csms.database.theme.ThemeColorChange;
import csms.databaseconnection.DBConnection;
import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Continue extends javax.swing.JFrame {

    SystemProperties pro = new SystemProperties();
    Connection dbconn = DBConnection.openConnection();
    
    public Continue() {
        pro.loadFromFile();
        if(pro.getId() == null || pro.getId().equals("") || pro.getId().isEmpty()){
            dispose();
            new main().setVisible(true);
        }
        initComponents();
        init();
    }
    
    private void init(){
        setShape(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 25, 25));
        setBackground(new Color(255,255,255));
        ThemeColorChange.getInstance().initBackground(bgPanel);
        Color co = new Color(pro.getColor().getRed(), pro.getColor().getGreen(), pro.getColor().getBlue(), 150);
        
        panelRound.setColor(co.brighter());
        panelRound.setOpaque(false);
        cmdClose.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                System.exit(0);
            }
        });
        login.setBackground(co.brighter());
        login.setOpaque(false);
        cont.setEffectColor(pro.getColor().brighter());
        cont.setText("Continue as " + convertName(Integer.parseInt(pro.getId())));
        imgavatar.setImage(convertIcon(Integer.parseInt(pro.getId())));
        if(!pro.isDarkMode()){
            bgPanel.setBackground(Color.WHITE);
            cont.setBackground(Color.WHITE);
            cont.setForeground(Color.BLACK);
            lbLogin.setForeground(Color.BLACK);
            lbCsms.setForeground(Color.BLACK);
        }
        if (pro.getBackgroundImage() != null) {
            ThemeColorChange.getInstance().changeBackgroundImage(pro.getBackgroundImage());
        }
        try {
            BufferedImage image = ImageIO.read(getClass().getResourceAsStream("/csms/database/icon/logocsms.png"));
            Image icon = Toolkit.getDefaultToolkit().createImage(image.getSource());
            setIconImage(icon);
        } catch (IOException ex) {
            Logger.getLogger(Continue.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private String convertName(int userid){
        try {
            String name = null;
            PreparedStatement ps;
            if (pro.getPosition().equals("Student")){
                ps = dbconn.prepareStatement("SELECT * FROM student WHERE stud_id=?");
            } else if(pro.getPosition().equals("Teacher")){
                ps = dbconn.prepareStatement("SELECT * FROM teacher WHERE tea_id=?");
            } else{
                ps = dbconn.prepareStatement("SELECT * FROM admin WHERE admin_id=?");
            }
            ps.setInt(1, userid);
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                name = rs.getString("first_name"); // + " " + rs.getString("last_name")
            }
            return name;
        } catch (SQLException ex) {
            Logger.getLogger(Continue.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    private ImageIcon convertIcon(int userid){
        try {
            ImageIcon icon = null;
            PreparedStatement ps;
            if (pro.getPosition().equals("Student")){
                ps = dbconn.prepareStatement("SELECT * FROM student WHERE stud_id=?");
            } else if(pro.getPosition().equals("Teacher")){
                ps = dbconn.prepareStatement("SELECT * FROM teacher WHERE tea_id=?");
            } else{
                ps = dbconn.prepareStatement("SELECT * FROM admin WHERE admin_id=?");
            }
            ps.setInt(1, userid);
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                byte[] imagedata = rs.getBytes("img_avatar");
                if (imagedata != null){
                    icon = new ImageIcon(imagedata);
                } else {
                    icon = new ImageIcon(getClass().getResource("/csms/database/icon/avatarimg.png"));
                }
            }
            return icon;
        } catch (SQLException ex) {
            Logger.getLogger(Continue.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bgPanel = new csms.database.component.PanelBackground();
        panelRound = new csms.login.component.panelRound();
        cont = new csms.login.swing.Button();
        lbCont = new javax.swing.JLabel();
        imgavatar = new csms.database.theme.ImageAvatar();
        cmdClose = new csms.database.component.Button();
        jLabel1 = new javax.swing.JLabel();
        lbLogin = new javax.swing.JLabel();
        login = new csms.login.swing.Button();
        lbCsms = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        bgPanel.setBackground(new java.awt.Color(34, 34, 34));

        panelRound.setRoundBottomLeft(500);
        panelRound.setRoundBottomRight(500);
        panelRound.setRoundTopLeft(25);
        panelRound.setRoundTopRight(25);

        cont.setBackground(new java.awt.Color(34, 34, 34));
        cont.setForeground(new java.awt.Color(255, 255, 255));
        cont.setText("Continue as Monthafer");
        cont.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        cont.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                contActionPerformed(evt);
            }
        });

        lbCont.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        lbCont.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbCont.setText("Continue on where you left from...");

        javax.swing.GroupLayout panelRoundLayout = new javax.swing.GroupLayout(panelRound);
        panelRound.setLayout(panelRoundLayout);
        panelRoundLayout.setHorizontalGroup(
            panelRoundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRoundLayout.createSequentialGroup()
                .addGroup(panelRoundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelRoundLayout.createSequentialGroup()
                        .addGap(162, 162, 162)
                        .addComponent(cont, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelRoundLayout.createSequentialGroup()
                        .addGap(138, 138, 138)
                        .addComponent(lbCont, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelRoundLayout.createSequentialGroup()
                        .addGap(214, 214, 214)
                        .addComponent(imgavatar, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelRoundLayout.setVerticalGroup(
            panelRoundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRoundLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(imgavatar, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbCont)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cont, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(25, Short.MAX_VALUE))
        );

        cmdClose.setBackground(new java.awt.Color(221, 0, 0));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/csms/loadingscreen/smalcal.gif"))); // NOI18N

        lbLogin.setBackground(new java.awt.Color(255, 255, 255));
        lbLogin.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        lbLogin.setForeground(new java.awt.Color(255, 255, 255));
        lbLogin.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbLogin.setText("or log in/sign up to other account:");

        login.setText("LOG IN / REGISTER");
        login.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        login.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginActionPerformed(evt);
            }
        });

        lbCsms.setFont(new java.awt.Font("Century Gothic", 0, 8)); // NOI18N
        lbCsms.setForeground(new java.awt.Color(255, 255, 255));
        lbCsms.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbCsms.setText("CLASS SCHEDULING MANAGEMENT SYSTEM 2023");

        javax.swing.GroupLayout bgPanelLayout = new javax.swing.GroupLayout(bgPanel);
        bgPanel.setLayout(bgPanelLayout);
        bgPanelLayout.setHorizontalGroup(
            bgPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelRound, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(bgPanelLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabel1)
                .addGap(90, 90, 90)
                .addComponent(lbCsms, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(110, 110, 110)
                .addComponent(cmdClose, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 6, Short.MAX_VALUE))
            .addGroup(bgPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbLogin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bgPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(login, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(181, 181, 181))
        );
        bgPanelLayout.setVerticalGroup(
            bgPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bgPanelLayout.createSequentialGroup()
                .addGroup(bgPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(bgPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(bgPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(cmdClose, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbCsms))))
                .addGap(1, 1, 1)
                .addComponent(panelRound, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lbLogin)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(login, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(33, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bgPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bgPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void contActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_contActionPerformed
        dispose();
        if (pro.getPosition().equals("Student")) {
            new csms.loadingscreen.mainload(null, true).setVisible(true);
            new mainViewer().setVisible(true);
        } else if (pro.getPosition().equals("Teacher")) {
            new csms.loadingscreen.mainload(null, true).setVisible(true);
            new mainFaculty().setVisible(true);
        } else {
            new csms.loadingscreen.mainload(null, true).setVisible(true);
            new mainAdmin().setVisible(true);
        }
    }//GEN-LAST:event_contActionPerformed

    private void loginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginActionPerformed
        dispose();
        new main().setVisible(true);
    }//GEN-LAST:event_loginActionPerformed
    
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Continue.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Continue.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Continue.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Continue.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Continue().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private csms.database.component.PanelBackground bgPanel;
    private csms.database.component.Button cmdClose;
    private csms.login.swing.Button cont;
    private csms.database.theme.ImageAvatar imgavatar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel lbCont;
    private javax.swing.JLabel lbCsms;
    private javax.swing.JLabel lbLogin;
    private csms.login.swing.Button login;
    private csms.login.component.panelRound panelRound;
    // End of variables declaration//GEN-END:variables
}
