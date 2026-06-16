package csms.database.forms;

import csms.database.admin.swing.aboutus;
import csms.database.admin.swing.systemabout;
import csms.database.component.Form;
import csms.database.event.EventColorChange;
import csms.database.glasspane.GlassPanePopup;
import csms.database.mainAdmin;
import csms.database.mainFaculty;
import csms.database.mainViewer;
import csms.database.properties.SystemProperties;
import csms.database.swing.EventSwitchSelected;
import csms.database.theme.ThemeColorChange;
import csms.login.Continue;
import java.awt.Color;
import javax.swing.SwingUtilities;

public class Setting_Form extends Form {

    SystemProperties pro = new SystemProperties();
    
    public Setting_Form() {
        initComponents();
        ThemeColorChange.getInstance().addEventColorChange(new EventColorChange(){
            @Override
            public void colorChange(Color color){
                switchButton.setBackground(color);
                imageBackgroundOption1.getSwitch().setBackground(color);
            }
        });
        if(ThemeColorChange.getInstance().getMode() == ThemeColorChange.Mode.LIGHT){
            lbDark.setForeground(new Color(80,80,80));
            lbColor.setForeground(new Color(80,80,80));
            lbColor1.setForeground(new Color(80,80,80));
            lbColor2.setForeground(new Color(80,80,80));
            lbColor3.setForeground(new Color(80,80,80));
        }
    }

    @Override
    public void changeColor(Color color){
        lbDark.setForeground(color);
        lbColor.setForeground(color);
        lbColor1.setForeground(color);
        lbColor2.setForeground(color);
        lbColor3.setForeground(color);
        imageBackgroundOption1.changeColorLabel(color);
    }
    
    public void setEventColorChange(EventColorChange event) {
        colorOption1.setEvent(event);
    }
    
    public void setSelectedThemeColor(Color color) {
        colorOption1.setSelectedColor(color);
    }
    
     public void setDarkMode(boolean darkMode) {
        switchButton.setSelected(darkMode);
        switchButton.addEventSelected(new EventSwitchSelected() {
            @Override
            public void onSelected(boolean selected) {
                new SystemProperties().save("dark_mode", selected + "");
                if (selected) {
                    ThemeColorChange.getInstance().changeMode(ThemeColorChange.Mode.DARK);
                } else {
                    ThemeColorChange.getInstance().changeMode(ThemeColorChange.Mode.LIGHT);
                }
            }
        });
    }
     
    public void initBackgroundImage(String imageSelected) {
        imageBackgroundOption1.init(imageSelected);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lbDark = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        switchButton = new csms.database.swing.SwitchButton();
        imageBackgroundOption1 = new csms.database.component.ImageBackgroundOption();
        jPanel3 = new javax.swing.JPanel();
        lbColor = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        colorOption1 = new csms.database.component.ColorOption();
        button1 = new csms.database.swing.Button();
        jPanel5 = new javax.swing.JPanel();
        lbColor1 = new javax.swing.JLabel();
        lbColor2 = new javax.swing.JLabel();
        lbColor3 = new javax.swing.JLabel();

        jPanel1.setOpaque(false);

        lbDark.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lbDark.setForeground(new java.awt.Color(230, 230, 230));
        lbDark.setText("Dark mode");

        jLabel1.setForeground(new java.awt.Color(128, 128, 128));
        jLabel1.setText("Use dark mode to change the system's color mode");

        jPanel2.setOpaque(false);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(switchButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(switchButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(12, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbDark)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 599, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lbDark)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1))
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setOpaque(false);

        lbColor.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lbColor.setForeground(new java.awt.Color(230, 230, 230));
        lbColor.setText("Theme Color");

        jLabel4.setForeground(new java.awt.Color(128, 128, 128));
        jLabel4.setText("Select color to set theme color system.");

        jPanel4.setOpaque(false);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(colorOption1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(12, Short.MAX_VALUE)
                .addComponent(colorOption1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbColor)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(lbColor)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4))
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        button1.setBackground(new java.awt.Color(255, 153, 153));
        button1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        button1.setText("LOGOUT?");
        button1.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        button1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button1ActionPerformed(evt);
            }
        });

        jPanel5.setOpaque(false);

        lbColor1.setForeground(new java.awt.Color(230, 230, 230));
        lbColor1.setText("About this system!");
        lbColor1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lbColor1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbColor1MouseClicked(evt);
            }
        });

        lbColor2.setForeground(new java.awt.Color(230, 230, 230));
        lbColor2.setText("About us!");
        lbColor2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lbColor2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbColor2MouseClicked(evt);
            }
        });

        lbColor3.setForeground(new java.awt.Color(230, 230, 230));
        lbColor3.setText("Edit your profile...");
        lbColor3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lbColor3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbColor3MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbColor1)
                    .addComponent(lbColor2)
                    .addComponent(lbColor3))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbColor1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbColor2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbColor3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(imageBackgroundOption1, javax.swing.GroupLayout.DEFAULT_SIZE, 925, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(imageBackgroundOption1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(58, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void button1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button1ActionPerformed
        pro.loadFromFile();
        if (pro.getPosition().equals("Student")) {
            mainViewer frame = (mainViewer) SwingUtilities.getWindowAncestor(Setting_Form.this);
            frame.dispose();
        } else if (pro.getPosition().equals("Teacher")) {
            mainFaculty frame = (mainFaculty) SwingUtilities.getWindowAncestor(Setting_Form.this);
            frame.dispose();
        } else {
            mainAdmin frame = (mainAdmin) SwingUtilities.getWindowAncestor(Setting_Form.this);
            frame.dispose();
        }
        new Continue().setVisible(true);
    }//GEN-LAST:event_button1ActionPerformed

    private void lbColor1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbColor1MouseClicked
        GlassPanePopup.showPopup(new systemabout());
    }//GEN-LAST:event_lbColor1MouseClicked

    private void lbColor2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbColor2MouseClicked
        GlassPanePopup.showPopup(new aboutus());
    }//GEN-LAST:event_lbColor2MouseClicked

    private void lbColor3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbColor3MouseClicked
        
    }//GEN-LAST:event_lbColor3MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private csms.database.swing.Button button1;
    private csms.database.component.ColorOption colorOption1;
    private csms.database.component.ImageBackgroundOption imageBackgroundOption1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JLabel lbColor;
    private javax.swing.JLabel lbColor1;
    private javax.swing.JLabel lbColor2;
    private javax.swing.JLabel lbColor3;
    private javax.swing.JLabel lbDark;
    private csms.database.swing.SwitchButton switchButton;
    // End of variables declaration//GEN-END:variables
}
