package csms.database.announce.swing;

import javax.swing.ImageIcon;

public class announcementpanels extends javax.swing.JPanel {

    public announcementpanels() {
        initComponents();
    }
    
    public void updatetext(ImageIcon avatar, String title, String date, String teacher){
        this.avatar.setImage(avatar);
        this.title.setText(title);
        this.date.setText(date);
        this.teacher.setText(teacher);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        avatar = new csms.database.theme.ImageAvatar();
        title = new javax.swing.JLabel();
        teacher = new javax.swing.JLabel();
        date = new javax.swing.JLabel();

        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        setMaximumSize(new java.awt.Dimension(826, 32767));
        setOpaque(false);

        avatar.setImage(new javax.swing.ImageIcon(getClass().getResource("/csms/database/icon/avatarimg.png"))); // NOI18N

        title.setFont(new java.awt.Font("Bahnschrift", 1, 18)); // NOI18N
        title.setText("title");

        teacher.setFont(new java.awt.Font("Bahnschrift", 0, 12)); // NOI18N
        teacher.setText("teacher");

        date.setFont(new java.awt.Font("Bahnschrift", 0, 12)); // NOI18N
        date.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        date.setText("May 27, 2091");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(avatar, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(title)
                    .addComponent(teacher))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 532, Short.MAX_VALUE)
                .addComponent(date, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(avatar, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(title)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(teacher)))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(date, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private csms.database.theme.ImageAvatar avatar;
    private javax.swing.JLabel date;
    private javax.swing.JLabel teacher;
    private javax.swing.JLabel title;
    // End of variables declaration//GEN-END:variables
}
