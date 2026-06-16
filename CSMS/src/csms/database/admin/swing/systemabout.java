package csms.database.admin.swing;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;

public class systemabout extends javax.swing.JPanel {

    public systemabout() {
        initComponents();
        txt.setBackground(new Color(0,0,0,0));
        txt.setSelectionColor(new Color(48,170,63,200));
        txt.setOpaque(false);
        
        txt1.setBackground(new Color(0,0,0,0));
        txt1.setSelectionColor(new Color(48,170,63,200));
        txt1.setOpaque(false);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txt = new javax.swing.JTextPane();
        title = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txt1 = new javax.swing.JTextPane();

        setBackground(new java.awt.Color(255, 255, 255));

        txt.setEditable(false);
        txt.setForeground(new java.awt.Color(133, 133, 133));
        txt.setText("          The “Class Scheduling Management System” is a digitalized class scheduling system that allows both teachers and students to efficiently manage their time and class schedule. The proponents of this project from the Grade 12 TVL ICT of MSU – Marawi Senior High School developed the system on March 2022 to May 2023 to aid the occurring problems in regards with the school’s scheduling management system. The system is facilitated by one or more school administrator that can easily access and manage the system. The users are both the teacher and the students of the institution.");

        title.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        title.setForeground(new java.awt.Color(80, 80, 80));
        title.setText("About this system, Class Scheduling Management System (CSMS):");

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(153, 153, 153));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("(Tap outside the box to exit)");

        txt1.setEditable(false);
        txt1.setForeground(new java.awt.Color(133, 133, 133));
        txt1.setText("          This capstone project has effectively fulfilled its objectives by being able to provide a much more facilitative class scheduling management system for the said institution which was the aim of creating the project. Additionally, the project has also proved its benefits for the institution as it could help the teachers and the students to have a more well organized environment in the school that could also serve as their foundation in being more productive and aware of their schedules in school. Lastly, this project can be a stepping stone for the institution to eradicate all the issues relating to scheduling management.");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt1, javax.swing.GroupLayout.PREFERRED_SIZE, 493, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(title)
                    .addComponent(txt, javax.swing.GroupLayout.PREFERRED_SIZE, 493, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(57, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(35, Short.MAX_VALUE)
                .addComponent(title)
                .addGap(18, 18, 18)
                .addComponent(txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txt1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50)
                .addComponent(jLabel1)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    @Override
    protected void paintComponent(Graphics grphcs){
        Graphics2D g2 = (Graphics2D) grphcs.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getBackground());
        g2.fill(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 15, 15));
        g2.dispose();
        super.paintComponent(grphcs);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    public javax.swing.JLabel title;
    public javax.swing.JTextPane txt;
    public javax.swing.JTextPane txt1;
    // End of variables declaration//GEN-END:variables
}
