package csms.database.agenda.swing;

import csms.database.theme.SystemTheme;
import csms.database.theme.ThemeColorChange;
import java.awt.Color;

public class titleMenu extends javax.swing.JPanel {

    public titleMenu() {
        initComponents();
        setOpaque(false);
        setBackground(new Color(0,0,0,0));
        init();
    }
    
    private void init(){
        panel1.setBackground(SystemTheme.mainColor);
        panel2.setBackground(SystemTheme.mainColor);
        txtmonth.setForeground(Color.WHITE);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel1 = new javax.swing.JPanel();
        txtmonth = new javax.swing.JLabel();
        panel2 = new javax.swing.JPanel();

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panel1.setBackground(new java.awt.Color(0, 0, 0));
        panel1.setPreferredSize(new java.awt.Dimension(100, 25));
        panel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtmonth.setFont(new java.awt.Font("SansSerif", 0, 11)); // NOI18N
        txtmonth.setForeground(new java.awt.Color(255, 255, 255));
        txtmonth.setText("Month:");
        txtmonth.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        panel1.add(txtmonth, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 6, -1, 10));

        add(panel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 5, 80, -1));

        panel2.setBackground(new java.awt.Color(0, 0, 0));
        panel2.setPreferredSize(new java.awt.Dimension(100, 25));

        javax.swing.GroupLayout panel2Layout = new javax.swing.GroupLayout(panel2);
        panel2.setLayout(panel2Layout);
        panel2Layout.setHorizontalGroup(
            panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 120, Short.MAX_VALUE)
        );
        panel2Layout.setVerticalGroup(
            panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 5, Short.MAX_VALUE)
        );

        add(panel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 25, 120, 5));
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel panel1;
    private javax.swing.JPanel panel2;
    private javax.swing.JLabel txtmonth;
    // End of variables declaration//GEN-END:variables
}
