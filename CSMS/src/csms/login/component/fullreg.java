package csms.login.component;

import csms.databaseconnection.DBConnection;
import csms.login.main;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import jnafilechooser.api.JnaFileChooser;
import net.miginfocom.swing.MigLayout;

public class fullreg extends javax.swing.JPanel {

    private PanelLoginandRegister loginAndRegister;
    Connection dbconn = DBConnection.openConnection();
    private MigLayout layout;
    String dir, contact, address, fbaccount, bio, conHour;
    int adviser, grade;

    public fullreg() {
        initComponents();
        administrator.setVisible(false);
        teacher.setVisible(false);
        student.setVisible(false);
        setFocusCycleRoot(true);
        setVisible(false);
        setOpaque(false);
        setFocusable(true);
        addMouseListener(new MouseAdapter() {
        });
        backbutton();
        ActionListener eventRegister = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        };
        ActionListener eventLogin = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {

            }
        };
        loginAndRegister = new PanelLoginandRegister(eventRegister, eventLogin);
        init();
    }

    private void selectedItems(){
        
    }
    
    private void backbutton() {
        back.setBackground(new Color(7, 175, 90));
        back.setForeground(new Color(250, 250, 250));
        back.setText("BACK");
        next.setBackground(new Color(7, 175, 90));
        next.setForeground(new Color(250, 250, 250));
        administrator.setVisible(false);
        teacher.setVisible(false);
        student.setVisible(false);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bg = new javax.swing.JLayeredPane();
        next = new csms.login.swing.Button();
        back = new csms.login.swing.Button();
        administrator = new javax.swing.JPanel();
        lbfrf2 = new javax.swing.JLabel();
        lbimgavatar1 = new javax.swing.JLabel();
        lbfrfdesc2 = new javax.swing.JLabel();
        adminDir = new javax.swing.JLabel();
        adminChooseImage = new csms.database.swing.Button();
        adminContact = new csms.login.swing.MyTextField();
        adminFb = new csms.login.swing.MyTextField();
        adminJob = new csms.login.swing.MyTextField();
        adminJobLoc = new csms.login.swing.MyTextField();
        adminJobInfo = new csms.login.swing.MyTextField();
        adminBio = new csms.login.swing.MyTextField();
        jLabel1 = new javax.swing.JLabel();
        student = new javax.swing.JPanel();
        lbfrf = new javax.swing.JLabel();
        lbimgavatar = new javax.swing.JLabel();
        lbfrfdesc = new javax.swing.JLabel();
        studDir = new javax.swing.JLabel();
        studChooseImage = new csms.database.swing.Button();
        studSection = new csms.login.swing.Choice();
        studGrade = new csms.login.swing.Choice();
        studStrand = new csms.login.swing.Choice();
        studContact = new csms.login.swing.MyTextField();
        studAddress = new csms.login.swing.MyTextField();
        studFb = new csms.login.swing.MyTextField();
        studBio = new csms.login.swing.MyTextField();
        jLabel2 = new javax.swing.JLabel();
        teacher = new javax.swing.JPanel();
        lbfrf1 = new javax.swing.JLabel();
        lbconsulthours = new javax.swing.JLabel();
        lbfrfdesc1 = new javax.swing.JLabel();
        teaDir = new javax.swing.JLabel();
        lbimgavatar2 = new javax.swing.JLabel();
        lbimgavatar3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        teaChooseImage = new csms.database.swing.Button();
        teaAdvisor = new csms.login.swing.Choice();
        teaTime2 = new csms.login.swing.Choice();
        teaTime1 = new csms.login.swing.Choice();
        teaConsultDays = new csms.login.swing.MyTextField();
        teaContact = new csms.login.swing.MyTextField();
        teaAddress = new csms.login.swing.MyTextField();
        teaFb = new csms.login.swing.MyTextField();
        teaBio = new csms.login.swing.MyTextField();
        jLabel3 = new javax.swing.JLabel();

        bg.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        next.setText("NEXT");
        next.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nextActionPerformed(evt);
            }
        });
        bg.add(next, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 90, 60, 30));

        back.setText("BACK");
        back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backActionPerformed(evt);
            }
        });
        bg.add(back, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 90, 60, 30));

        administrator.setBackground(new java.awt.Color(255, 255, 255));
        administrator.setOpaque(false);
        administrator.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbfrf2.setBackground(new java.awt.Color(130, 130, 130));
        lbfrf2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lbfrf2.setForeground(new java.awt.Color(130, 130, 130));
        lbfrf2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbfrf2.setText("Full Registration Form");
        administrator.add(lbfrf2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 30, 400, 30));

        lbimgavatar1.setForeground(new java.awt.Color(80, 80, 80));
        lbimgavatar1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbimgavatar1.setText("Image Avatar");
        administrator.add(lbimgavatar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 430, 80, -1));

        lbfrfdesc2.setForeground(new java.awt.Color(80, 80, 80));
        lbfrfdesc2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbfrfdesc2.setText("Please fill-up your full information below");
        administrator.add(lbfrfdesc2, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 60, 220, -1));

        adminDir.setText(" ");
        administrator.add(adminDir, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 430, 230, -1));

        adminChooseImage.setBackground(new java.awt.Color(204, 204, 204));
        adminChooseImage.setText("Choose Image...");
        adminChooseImage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                adminChooseImageActionPerformed(evt);
            }
        });
        administrator.add(adminChooseImage, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 425, -1, -1));

        adminContact.setHint("Contact Number");
        administrator.add(adminContact, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 110, 240, -1));

        adminFb.setHint("Facebook Account (optional)");
        administrator.add(adminFb, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 160, 240, -1));

        adminJob.setHint("Occupation");
        administrator.add(adminJob, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 210, 240, -1));

        adminJobLoc.setHint("Place of Occupation");
        administrator.add(adminJobLoc, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 260, 240, -1));

        adminJobInfo.setHint("Occupation info");
        administrator.add(adminJobInfo, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 310, 240, -1));

        adminBio.setHint("Your Bio");
        administrator.add(adminBio, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 360, 240, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/csms/login/icon/fullregbg.PNG"))); // NOI18N
        administrator.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        bg.add(administrator, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 50, -1, -1));

        student.setBackground(new java.awt.Color(255, 255, 255));
        student.setOpaque(false);
        student.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbfrf.setBackground(new java.awt.Color(130, 130, 130));
        lbfrf.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lbfrf.setForeground(new java.awt.Color(130, 130, 130));
        lbfrf.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbfrf.setText("Full Registration Form");
        student.add(lbfrf, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 30, 400, 30));

        lbimgavatar.setForeground(new java.awt.Color(80, 80, 80));
        lbimgavatar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbimgavatar.setText("Image Avatar");
        student.add(lbimgavatar, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 380, 80, -1));

        lbfrfdesc.setForeground(new java.awt.Color(80, 80, 80));
        lbfrfdesc.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbfrfdesc.setText("Please fill-up your full information below");
        student.add(lbfrfdesc, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 60, 220, -1));

        studDir.setText(" ");
        student.add(studDir, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 380, 230, -1));

        studChooseImage.setBackground(new java.awt.Color(204, 204, 204));
        studChooseImage.setText("Choose Image...");
        studChooseImage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                studChooseImageActionPerformed(evt);
            }
        });
        student.add(studChooseImage, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 375, -1, -1));

        studSection.setSelectedItem(null);
        studSection.setHint("Section");
        student.add(studSection, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 310, 130, -1));

        studGrade.setSelectedItem(null);
        studGrade.setHint("Grade");
        student.add(studGrade, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 310, 130, -1));

        studStrand.setSelectedItem(null);
        studStrand.setHint("Strand");
        student.add(studStrand, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 310, 130, -1));

        studContact.setHint("Contact Number");
        student.add(studContact, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 110, 240, -1));

        studAddress.setHint("Address");
        student.add(studAddress, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 160, 240, -1));

        studFb.setHint("Facebook Account (optional)");
        student.add(studFb, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 210, 240, -1));

        studBio.setHint("Your Bio");
        student.add(studBio, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 260, 240, -1));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/csms/login/icon/fullregbg.PNG"))); // NOI18N
        student.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        bg.add(student, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 50, -1, -1));

        teacher.setBackground(new java.awt.Color(255, 255, 255));
        teacher.setOpaque(false);
        teacher.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbfrf1.setBackground(new java.awt.Color(130, 130, 130));
        lbfrf1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lbfrf1.setForeground(new java.awt.Color(130, 130, 130));
        lbfrf1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbfrf1.setText("Full Registration Form");
        teacher.add(lbfrf1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 30, 400, 30));

        lbconsulthours.setForeground(new java.awt.Color(80, 80, 80));
        lbconsulthours.setText("Consultation Hours");
        teacher.add(lbconsulthours, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 365, 110, -1));

        lbfrfdesc1.setForeground(new java.awt.Color(80, 80, 80));
        lbfrfdesc1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbfrfdesc1.setText("Please fill-up your full information below");
        teacher.add(lbfrfdesc1, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 60, 220, -1));

        teaDir.setText(" ");
        teacher.add(teaDir, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 450, 230, -1));

        lbimgavatar2.setForeground(new java.awt.Color(80, 80, 80));
        lbimgavatar2.setText("Image Avatar");
        teacher.add(lbimgavatar2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 450, 80, -1));

        lbimgavatar3.setForeground(new java.awt.Color(80, 80, 80));
        lbimgavatar3.setText("Consultation Days");
        teacher.add(lbimgavatar3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 410, 110, -1));

        jLabel4.setText("-");
        teacher.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 365, -1, -1));

        teaChooseImage.setBackground(new java.awt.Color(204, 204, 204));
        teaChooseImage.setText("Choose Image...");
        teaChooseImage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                teaChooseImageActionPerformed(evt);
            }
        });
        teacher.add(teaChooseImage, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 445, -1, -1));

        teaAdvisor.setHint("Advisory Class");
        teacher.add(teaAdvisor, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 300, 240, 40));

        teaTime2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "7:00 am", "8:00 am", "9:00 am", "10:00 am", "11:00 am", "12:00 pm", "1:00 pm", "2:00 pm", "3:00 pm", "4:00 pm", "5:00 pm", "6:00 pm", "7:00 pm", "8:00 pm", "9:00 pm" }));
        teaTime2.setSelectedItem(null);
        teaTime2.setHint("To");
        teacher.add(teaTime2, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 355, 100, 40));

        teaTime1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "7:00 am", "8:00 am", "9:00 am", "10:00 am", "11:00 am", "12:00 pm", "1:00 pm", "2:00 pm", "3:00 pm", "4:00 pm", "5:00 pm", "6:00 pm", "7:00 pm", "8:00 pm", "9:00 pm" }));
        teaTime1.setSelectedItem(null);
        teaTime1.setHint("From");
        teacher.add(teaTime1, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 355, 100, 40));

        teaConsultDays.setHint("eg. Monday & Wednesday");
        teacher.add(teaConsultDays, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 405, 220, 30));

        teaContact.setHint("Contact Number");
        teacher.add(teaContact, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 100, 240, -1));

        teaAddress.setHint("Address");
        teacher.add(teaAddress, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 150, 240, -1));

        teaFb.setHint("Facebook Account (optional)");
        teacher.add(teaFb, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 200, 240, -1));

        teaBio.setHint("Your Bio");
        teacher.add(teaBio, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 250, 240, -1));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/csms/login/icon/fullregbg.PNG"))); // NOI18N
        jLabel3.setOpaque(true);
        teacher.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        bg.add(teacher, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 50, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void backActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backActionPerformed
        setVisible(false);
        administrator.setVisible(false);
        teacher.setVisible(false);
        student.setVisible(false);
    }//GEN-LAST:event_backActionPerformed

    private void nextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nextActionPerformed
        /*String gra = String.valueOf(studGrade.getSelectedItem());
        String sec = String.valueOf(studSection.getSelectedItem());
        String stra = String.valueOf(studStrand.getSelectedItem());
        Statement stmt;
        if (administrator.isVisible()) {
            contact = (String) adminContact.getText().trim();
            fbaccount = (String) adminFb.getText().trim();
            bio = (String) adminBio.getText();
        } else if (teacher.isVisible()){
            contact = (String) teaContact.getText().trim();
            address = (String) teaAddress.getText().trim();
            fbaccount = (String) teaFb.getText().trim();
            bio = (String) teaBio.getText().trim();
            int adv = (Integer) teaAdvisor.getSelectedIndex() - 1;
            adviser = String.valueOf(adv);
            conHour = (String.valueOf(teaTime1.getSelectedItem()) + " - " + String.valueOf(teaTime2.getSelectedItem()));
            System.out.println(adviser);
        } else if (student.isVisible()){
            contact = (String) studContact.getText().trim();
            address = (String) studAddress.getText().trim();
            fbaccount = (String) studFb.getText().trim();
            bio = (String) studBio.getText().trim();
        }
        try {
            stmt = dbconn.createStatement();
            ResultSet res = stmt.executeQuery("SELECT sgs_id FROM sgs WHERE strand='" + stra + "' AND grade=" + gra + " AND section='" + sec + "'");
            while (res.next()) {
                grade = (String) res.getString("sgs_id");
            }
            res.close();
        } catch (SQLException ex) {
            Logger.getLogger(fullreg.class.getName()).log(Level.SEVERE, null, ex);
        }*/
    }//GEN-LAST:event_nextActionPerformed

    private void adminChooseImageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adminChooseImageActionPerformed
        main main = new main();
        JnaFileChooser ch = new JnaFileChooser();
        ch.addFilter("png, jpg and jpeg", "png", "jpeg", "jpg");
        boolean action = ch.showOpenDialog(main);
        if (action){
            System.out.println(ch.getSelectedFile());
            dir = String.valueOf(ch.getSelectedFile());
            adminDir.setText(dir);
        }
    }//GEN-LAST:event_adminChooseImageActionPerformed

    private void studChooseImageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_studChooseImageActionPerformed
        main main = new main();
        JnaFileChooser ch = new JnaFileChooser();
        ch.addFilter("png, jpg and jpeg", "png", "jpeg", "jpg");
        boolean action = ch.showOpenDialog(main);
        if (action){
            System.out.println(ch.getSelectedFile());
            dir = String.valueOf(ch.getSelectedFile());
            studDir.setText(dir);
        }
    }//GEN-LAST:event_studChooseImageActionPerformed

    private void teaChooseImageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_teaChooseImageActionPerformed
        main main = new main();
        JnaFileChooser ch = new JnaFileChooser();
        ch.addFilter("png, jpg and jpeg", "png", "jpeg", "jpg");
        boolean action = ch.showOpenDialog(main);
        if (action){
            System.out.println(ch.getSelectedFile());
            dir = String.valueOf(ch.getSelectedFile());
            teaDir.setText(dir);
        }
    }//GEN-LAST:event_teaChooseImageActionPerformed

    @Override
    protected void paintComponent(Graphics grphcs) {
        Graphics2D g2 = (Graphics2D) grphcs;
        g2.setColor(new Color(255, 255, 255));
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
        g2.fillRect(0, 0, getWidth(), getHeight());
        g2.setComposite(AlphaComposite.SrcOver);
        super.paintComponent(grphcs);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private csms.login.swing.MyTextField adminBio;
    private csms.database.swing.Button adminChooseImage;
    private csms.login.swing.MyTextField adminContact;
    private javax.swing.JLabel adminDir;
    private csms.login.swing.MyTextField adminFb;
    private csms.login.swing.MyTextField adminJob;
    private csms.login.swing.MyTextField adminJobInfo;
    private csms.login.swing.MyTextField adminJobLoc;
    private javax.swing.JPanel administrator;
    private csms.login.swing.Button back;
    private javax.swing.JLayeredPane bg;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel lbconsulthours;
    private javax.swing.JLabel lbfrf;
    private javax.swing.JLabel lbfrf1;
    private javax.swing.JLabel lbfrf2;
    private javax.swing.JLabel lbfrfdesc;
    private javax.swing.JLabel lbfrfdesc1;
    private javax.swing.JLabel lbfrfdesc2;
    private javax.swing.JLabel lbimgavatar;
    private javax.swing.JLabel lbimgavatar1;
    private javax.swing.JLabel lbimgavatar2;
    private javax.swing.JLabel lbimgavatar3;
    private csms.login.swing.Button next;
    private csms.login.swing.MyTextField studAddress;
    private csms.login.swing.MyTextField studBio;
    private csms.database.swing.Button studChooseImage;
    private csms.login.swing.MyTextField studContact;
    private javax.swing.JLabel studDir;
    private csms.login.swing.MyTextField studFb;
    private csms.login.swing.Choice studGrade;
    private csms.login.swing.Choice studSection;
    private csms.login.swing.Choice studStrand;
    private javax.swing.JPanel student;
    private csms.login.swing.MyTextField teaAddress;
    private csms.login.swing.Choice teaAdvisor;
    private csms.login.swing.MyTextField teaBio;
    private csms.database.swing.Button teaChooseImage;
    private csms.login.swing.MyTextField teaConsultDays;
    private csms.login.swing.MyTextField teaContact;
    private javax.swing.JLabel teaDir;
    private csms.login.swing.MyTextField teaFb;
    private csms.login.swing.Choice teaTime1;
    private csms.login.swing.Choice teaTime2;
    private javax.swing.JPanel teacher;
    // End of variables declaration//GEN-END:variables

    public void student() {
        student.setVisible(true);
    }

    public void teacher() {
        teacher.setVisible(true);
    }

    public void administrator() {
        administrator.setVisible(true);
    }
    
    //letting info out
    public String getContact(){
        if (administrator.isVisible()) {
            contact = (String) adminContact.getText().trim();
        } else if (teacher.isVisible()){
            contact = (String) teaContact.getText().trim();
        } else if (student.isVisible()){
            contact = (String) studContact.getText().trim();
        }
        return contact;
    }
    public String getAddress(){
        if (teacher.isVisible()){
            address = (String) teaAddress.getText().trim();
        } else if (student.isVisible()){
            address = (String) studAddress.getText().trim();
        }
        return address;
    }
    public String getFb(){
        if (administrator.isVisible()) {
            fbaccount = (String) adminFb.getText().trim();
        } else if (teacher.isVisible()){
            fbaccount = (String) teaFb.getText().trim();
        } else if (student.isVisible()){
            fbaccount = (String) studFb.getText().trim();
        }
        return fbaccount;
    }
    public String getBio(){
        if (administrator.isVisible()) {
            bio = (String) adminBio.getText();
        } else if (teacher.isVisible()){
            bio = (String) teaBio.getText().trim();
        } else if (student.isVisible()){
            bio = (String) studBio.getText().trim();
        }
        return bio;
    }
    public String getDir(){
        return dir;
    }
    //stud
    public int getStudGrade(){
        String gra = String.valueOf(studGrade.getSelectedItem());
        String sec = String.valueOf(studSection.getSelectedItem());
        String stra = String.valueOf(studStrand.getSelectedItem());
        try {
            Statement stmt;
            stmt = dbconn.createStatement();
            ResultSet res = stmt.executeQuery("SELECT sgs_id FROM sgs WHERE strand='" + stra + "' AND grade=" + gra + " AND section='" + sec + "'");
            while (res.next()) {
                grade = res.getInt("sgs_id");
            }
            res.close();
        } catch (SQLException ex) {
            Logger.getLogger(fullreg.class.getName()).log(Level.SEVERE, null, ex);
        }
        return grade;
    }
    //tea
    public int getAdvisoryClass(){
        int adv = (Integer) teaAdvisor.getSelectedIndex() - 1;
        adviser = adv;
        return adviser;
    }
    public String getConsultHours(){
        conHour = (String.valueOf(teaTime1.getSelectedItem()) + " - " + String.valueOf(teaTime2.getSelectedItem()));
        return conHour;
    }
    public String getConsultDays(){
        return teaConsultDays.getText().trim();
    }
    //admin
    public String getAdminJob(){
        return adminJob.getText().trim();
    }
    public String getAdminJobLoc(){
        return adminJobLoc.getText().trim();
    }
    public String getAdminJobInfo(){
        return adminJobInfo.getText().trim();
    }
    //nextButton
    public void addEventButtonNext(ActionListener event){
        next.addActionListener(event);
    }
    

    private void init() {
        studStrand.setSelectedItem(null);
        studSection.setSelectedItem(null);
        try {
            Statement stmt = dbconn.createStatement();
            ResultSet rs1 = stmt.executeQuery("SELECT DISTINCT strand FROM sgs");
            while (rs1.next()){
                String strand = rs1.getString("strand");
                studStrand.addItem(strand);
            }
            rs1.close();
            
            ResultSet rs4 = stmt.executeQuery("SELECT DISTINCT grade FROM sgs");
            while (rs4.next()){
                String grade = rs4.getString("grade");
                studGrade.addItem(String.valueOf(grade));
            }
            rs4.close();
            
            studSection.getSelectedIndex();
            
            String firstStrand = (String) studStrand.getSelectedItem();
            String firstGrade = (String) studGrade.getSelectedItem();
            ResultSet rs2 = stmt.executeQuery("SELECT DISTINCT section FROM sgs WHERE strand='" + firstStrand + "' AND grade=" + firstGrade);
            while (rs2.next()){
                String section = rs2.getString("section");
                studSection.addItem(section);
            }
            rs2.close();
            
            teaAdvisor.addItem("<none>");
            ResultSet rs6 = stmt.executeQuery("SELECT grade, section, strand FROM sgs");
            while (rs6.next()){
                String grade = rs6.getString("grade");
                String section = rs6.getString("section");
                String strand = rs6.getString("strand");
                String sgs = (strand + " " + grade + "-" + section);
                teaAdvisor.addItem(sgs);
            }
            rs6.close();
            
            studStrand.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    String selectedStrand = (String) studStrand.getSelectedItem();
                    String selectedGrade = (String) studGrade.getSelectedItem();
                    studSection.removeAllItems();
                    try {
                        Statement stmt2 = dbconn.createStatement();
                        ResultSet rs3 = stmt2.executeQuery("SELECT DISTINCT section FROM sgs WHERE strand='" + selectedStrand + "' AND grade=" + selectedGrade);
                        while(rs3.next()){
                            String section = rs3.getString("section");
                            studSection.addItem(section);
                        }
                        rs3.close();
                        stmt2.close();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
            });
            
            studGrade.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    String selectedStrand = (String) studStrand.getSelectedItem();
                    String selectedGrade = (String) studGrade.getSelectedItem();
                    studSection.removeAllItems();
                    try {
                        Statement stmt3 = dbconn.createStatement();
                        ResultSet rs5 = stmt3.executeQuery("SELECT DISTINCT section FROM sgs WHERE strand='" + selectedStrand + "' AND grade=" + selectedGrade);
                        while(rs5.next()){
                            String section = rs5.getString("section");
                            studSection.addItem(section);
                        }
                        rs5.close();
                        stmt3.close();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
            });
        } catch (SQLException ex) {
            Logger.getLogger(fullreg.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
