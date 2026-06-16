package csms.database.admin.swing;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;

public class aboutus extends javax.swing.JPanel {

    public aboutus() {
        initComponents();
        desc.setBackground(new Color(0,0,0,0));
        desc.setSelectionColor(new Color(48,170,63,200));
        desc.setOpaque(false);
        
        txtinfo2.setBackground(new Color(0,0,0,0));
        txtinfo2.setSelectionColor(new Color(48,170,63,200));
        txtinfo2.setOpaque(false);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        desc = new javax.swing.JTextPane();
        lboutside = new javax.swing.JLabel();
        lbdesc = new javax.swing.JLabel();
        txtname = new javax.swing.JLabel();
        avatar = new csms.database.theme.ImageAvatar();
        txtposition = new javax.swing.JLabel();
        txtinfo1 = new javax.swing.JLabel();
        barani = new csms.database.theme.ImageAvatar();
        hosni = new csms.database.theme.ImageAvatar();
        basher = new csms.database.theme.ImageAvatar();
        neera = new csms.database.theme.ImageAvatar();
        txtinfo2 = new javax.swing.JTextPane();

        setBackground(new java.awt.Color(255, 255, 255));

        desc.setEditable(false);
        desc.setForeground(new java.awt.Color(133, 133, 133));
        desc.setText("Starting off the introduction with the brain of the team, Mr. Barani. Barani is a perfectionist who is overly fixated on a perfectly planned and concise schedule in which why he was part of the idealists in this project. As the \"brain\" of the team, he mainly worked and coded everything in the project while collaborating with his teams' ideas.");

        lboutside.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        lboutside.setForeground(new java.awt.Color(153, 153, 153));
        lboutside.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lboutside.setText("(Tap outside the box to exit)");

        lbdesc.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        lbdesc.setForeground(new java.awt.Color(80, 80, 80));
        lbdesc.setText("Description:");

        txtname.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        txtname.setForeground(new java.awt.Color(80, 80, 80));
        txtname.setText("Barani, Monthafer A.");

        avatar.setImage(new javax.swing.ImageIcon(getClass().getResource("/csms/database/icon/barani.jpg"))); // NOI18N

        txtposition.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        txtposition.setForeground(new java.awt.Color(133, 133, 133));
        txtposition.setText("Main Programmer");

        txtinfo1.setForeground(new java.awt.Color(133, 133, 133));
        txtinfo1.setText("Born on July 15, 2004");

        barani.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 255, 153), 3));
        barani.setImage(new javax.swing.ImageIcon(getClass().getResource("/csms/database/icon/barani.jpg"))); // NOI18N
        barani.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                baraniMouseClicked(evt);
            }
        });

        hosni.setImage(new javax.swing.ImageIcon(getClass().getResource("/csms/database/icon/hosni.jpg"))); // NOI18N
        hosni.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                hosniMouseClicked(evt);
            }
        });

        basher.setImage(new javax.swing.ImageIcon(getClass().getResource("/csms/database/icon/basger.jpg"))); // NOI18N
        basher.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                basherMouseClicked(evt);
            }
        });

        neera.setImage(new javax.swing.ImageIcon(getClass().getResource("/csms/database/icon/neera.jpg"))); // NOI18N
        neera.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                neeraMouseClicked(evt);
            }
        });

        txtinfo2.setEditable(false);
        txtinfo2.setForeground(new java.awt.Color(133, 133, 133));
        txtinfo2.setText("Likes: Going to seaside alone with gloomy weather");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(barani, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(hosni, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(basher, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(neera, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtposition, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtname)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(txtinfo1, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtinfo2, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addComponent(avatar, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(70, 70, 70)
                .addComponent(lbdesc))
            .addGroup(layout.createSequentialGroup()
                .addGap(74, 74, 74)
                .addComponent(desc, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(lboutside, javax.swing.GroupLayout.PREFERRED_SIZE, 588, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(barani, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(hosni, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(basher, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(neera, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(txtposition))
                            .addComponent(txtname))
                        .addGap(16, 16, 16)
                        .addComponent(txtinfo1)
                        .addGap(1, 1, 1)
                        .addComponent(txtinfo2, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(avatar, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addComponent(lbdesc)
                .addGap(15, 15, 15)
                .addComponent(desc, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(lboutside))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void baraniMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_baraniMouseClicked
        txtname.setText("Barani, Monthafer A.");
        txtposition.setText("Main Programmer");
        txtinfo1.setText("Born on July 15, 2004");
        txtinfo2.setText("Likes: Going to seaside alone with gloomy weather");
        desc.setText("Starting off the introduction with the brain of the team, Mr. Barani. Barani is a perfectionist who is overly fixated on a perfectly planned and concise schedule in which why he was part of the idealists in this project. As the \"brain\" of the team, he mainly worked and coded everything in the project while collaborating with his teams' ideas.");
        barani.setBorder(BorderFactory.createLineBorder(new Color(153, 255, 153), 3));
        hosni.setBorder(null);
        basher.setBorder(null);
        neera.setBorder(null);
        avatar.setImage(new ImageIcon(getClass().getResource("/csms/database/icon/barani.jpg")));
    }//GEN-LAST:event_baraniMouseClicked

    private void hosniMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_hosniMouseClicked
        txtname.setText("Benito, Hosni M.");
        txtposition.setText("Main Researcher");
        txtinfo1.setText("Born on March 8, 2005");
        txtinfo2.setText("Likes to chisims and complain - Hates Everyone (⁠≧⁠▽⁠≦⁠) - INTJ-T - Pisces ");
        desc.setText("Then we have Mr. Benito serving as the spine of the team. He is also a perfectionist but at the same the worst procrastinator in the planet and yet loves to complain about everything so it's no wonder he came up with the idea of the project to complain about the school's system. He is the assigned leader of the team (that feels hasn't contributed much) and is also the technical writer for the necessary parts of the project.");
        barani.setBorder(null);
        hosni.setBorder(BorderFactory.createLineBorder(new Color(153, 255, 153), 3));
        basher.setBorder(null);
        neera.setBorder(null);
        avatar.setImage(new ImageIcon(getClass().getResource("/csms/database/icon/hosni.jpg")));
    }//GEN-LAST:event_hosniMouseClicked

    private void basherMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_basherMouseClicked
        txtname.setText("Abdulbasher, Abdulrahim R.");
        txtposition.setText("Researcher");
        txtinfo1.setText("Born on December 30, 2003");
        txtinfo2.setText("Likes: EAT!");
        desc.setText("I've run out of important organs so let's just say he's the egg in the team. Mr. Basher is the complete opposite of the previous two when it comes to perfecting things as he's always that \"atleast I tried\" person and usually doesn't give a damn about anything. He needs the project to fix his schedule. That's it. Jk, Mr. Basher provided the team with an eggshell since his place has always been the meeting place to brainstorm ideas.");
        barani.setBorder(null);
        hosni.setBorder(null);
        basher.setBorder(BorderFactory.createLineBorder(new Color(153, 255, 153), 3));
        neera.setBorder(null);
        avatar.setImage(new ImageIcon(getClass().getResource("/csms/database/icon/basger.jpg")));
    }//GEN-LAST:event_basherMouseClicked

    private void neeraMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_neeraMouseClicked
        txtname.setText("Hadji Azis, Rania P.");
        txtposition.setText("Researcher");
        txtinfo1.setText("Born on May 24, 2005");
        txtinfo2.setText("Likes to eat in every situation");
        desc.setText("Ending the introduction with the rich 'tita'. Ms. Rania is also the type to not give a damn but not at this project because nobody would want a season 2 in SHS. As she's the 'unica ija' of the team, she contributes much on materialistic and monetary help. #RichTitaMoments");
        barani.setBorder(null);
        hosni.setBorder(null);
        basher.setBorder(null);
        neera.setBorder(BorderFactory.createLineBorder(new Color(153, 255, 153), 3));
        avatar.setImage(new ImageIcon(getClass().getResource("/csms/database/icon/neera.jpg")));
    }//GEN-LAST:event_neeraMouseClicked

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
    private csms.database.theme.ImageAvatar avatar;
    private csms.database.theme.ImageAvatar barani;
    private csms.database.theme.ImageAvatar basher;
    public javax.swing.JTextPane desc;
    private csms.database.theme.ImageAvatar hosni;
    public javax.swing.JLabel lbdesc;
    private javax.swing.JLabel lboutside;
    private csms.database.theme.ImageAvatar neera;
    private javax.swing.JLabel txtinfo1;
    public javax.swing.JTextPane txtinfo2;
    public javax.swing.JLabel txtname;
    private javax.swing.JLabel txtposition;
    // End of variables declaration//GEN-END:variables
}
