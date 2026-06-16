package csms.database;

import csms.database.event.EventColorChange;
import csms.database.forms.*;
import csms.database.glasspane.GlassPanePopup;
import csms.database.menu.EventMenu;
import csms.database.properties.SystemProperties;
import csms.database.theme.SystemTheme;
import csms.database.theme.ThemeColor;
import csms.database.theme.ThemeColorChange;
import csms.login.Continue;
import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author Monthafer Barani
 */
public class mainFaculty extends javax.swing.JFrame {

    private Setting_Form settingForm;
    
    public mainFaculty() {
        initComponents();
        setBackground(new Color(0,0,0));
        GlassPanePopup.install(this);
        init();
        try {
            BufferedImage image = ImageIO.read(getClass().getResourceAsStream("/csms/database/icon/logocsms.png"));
            Image icon = Toolkit.getDefaultToolkit().createImage(image.getSource());
            setIconImage(icon);
        } catch (IOException ex) {
            Logger.getLogger(Continue.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void init(){
        header.initMoving(this);
        header.initEvent(this, panelBackground1);
        menu.addEventMenu(new EventMenu(){
            @Override
            public void selectedMenu(int index){
                if (index == 0){
                    mainBody.displayForm(new Home_Form(), "Home");
                }
                else if (index == 1){
                    mainBody.displayForm(new Calendar_Form(), "Calendar");
                }
                else if (index == 2){
                    mainBody.displayForm(new Timetable_Form(),"Timetable");
                }
                else if (index == 3){
                    mainBody.displayForm(new Student_Form(),"Student");
                }
                else if (index == 4){
                    mainBody.displayForm(new Agenda_Form(),"Agenda");
                }
                else if (index == 5){
                    mainBody.displayForm(new Announcement_Form(), "Announcement");
                }
                else if (index == 6){
                    mainBody.displayForm(new Class_Form(), "Room");
                }
                else if (index == 7){
                    mainBody.displayForm(new Message_Form(), "Message");
                }
                else if (index == 8){
                    mainBody.displayForm(settingForm, "Setting");
                }
            }
        });
        ThemeColorChange.getInstance().addThemes(new ThemeColor(new Color(34, 34, 34), Color.WHITE){
            @Override
            public void onColorChange(Color color){
                panelBackground1.setBackground(color);
            }
        });
        ThemeColorChange.getInstance().addThemes(new ThemeColor(Color.WHITE, new Color(80,80,80)){
            @Override
            public void onColorChange(Color color){
                mainBody.changeColor(color);
            }
        });
        ThemeColorChange.getInstance().initBackground(panelBackground1);
        SystemProperties pro = new SystemProperties();
        pro.loadFromFile();
        ThemeColorChange.getInstance().setMode(ThemeColorChange.Mode.DARK);
        if(!pro.isDarkMode()){
            ThemeColorChange.getInstance().initBackground(panelBackground1);
            ThemeColorChange.getInstance().setMode(ThemeColorChange.Mode.LIGHT);
            panelBackground1.setBackground(Color.WHITE);
            mainBody.changeColor(new Color(80,80,80));
        }
        if (pro.getBackgroundImage() != null) {
            ThemeColorChange.getInstance().changeBackgroundImage(pro.getBackgroundImage());
        }
        SystemTheme.mainColor = pro.getColor();
        
        settingForm = new Setting_Form();
        settingForm.setEventColorChange(new EventColorChange(){
           @Override 
           public void colorChange(Color color){
               SystemTheme.mainColor = color;
               ThemeColorChange.getInstance().ruenEventColorChange(color);
               repaint();
               pro.save("theme_color", color.getRGB()+ "");
           }
        });
        settingForm.setSelectedThemeColor(pro.getColor());
        settingForm.setDarkMode(pro.isDarkMode());
        settingForm.initBackgroundImage(pro.getBackgroundImage());
        mainBody.displayForm(new Home_Form(), "Home");
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelBackground1 = new csms.database.component.PanelBackground();
        header = new csms.database.component.Header();
        mainBody = new csms.database.component.MainBody();
        menu = new csms.database.menu.MenuTeacher();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        panelBackground1.setBackground(new java.awt.Color(34, 34, 34));

        javax.swing.GroupLayout panelBackground1Layout = new javax.swing.GroupLayout(panelBackground1);
        panelBackground1.setLayout(panelBackground1Layout);
        panelBackground1Layout.setHorizontalGroup(
            panelBackground1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(header, javax.swing.GroupLayout.DEFAULT_SIZE, 1200, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBackground1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(menu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(mainBody, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelBackground1Layout.setVerticalGroup(
            panelBackground1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBackground1Layout.createSequentialGroup()
                .addComponent(header, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelBackground1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(mainBody, javax.swing.GroupLayout.DEFAULT_SIZE, 600, Short.MAX_VALUE)
                    .addComponent(menu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelBackground1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelBackground1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
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
            java.util.logging.Logger.getLogger(mainFaculty.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(mainFaculty.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(mainFaculty.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(mainFaculty.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                //new csms.loadingscreen.mainload(null,true).setVisible(true);
                new mainFaculty().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private csms.database.component.Header header;
    private csms.database.component.MainBody mainBody;
    private csms.database.menu.MenuTeacher menu;
    private csms.database.component.PanelBackground panelBackground1;
    // End of variables declaration//GEN-END:variables
}
