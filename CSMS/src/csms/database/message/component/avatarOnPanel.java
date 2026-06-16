package csms.database.message.component;

import csms.database.theme.ThemeColorChange;
import java.awt.Color;
import javax.swing.ImageIcon;

public class avatarOnPanel extends javax.swing.JPanel {

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public ImageIcon getImage() {
        return Image;
    }

    public void setImage(ImageIcon Image) {
        this.Image = Image;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    private String fullName;
    private ImageIcon Image;
    private String id;
    private String position;
    
    public avatarOnPanel() {
        initComponents();
        init();
    }
    
    private void init(){
        avatar.setImage(Image);
        namefull.setText(fullName);
        if(ThemeColorChange.getInstance().getMode() == ThemeColorChange.Mode.DARK){
            namefull.setForeground(new Color(230,230,230));
        } else{
            namefull.setForeground(new Color(80,80,80));
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        avatar = new csms.database.theme.ImageAvatar();
        namefull = new javax.swing.JLabel();

        setOpaque(false);

        javax.swing.GroupLayout avatarLayout = new javax.swing.GroupLayout(avatar);
        avatar.setLayout(avatarLayout);
        avatarLayout.setHorizontalGroup(
            avatarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        avatarLayout.setVerticalGroup(
            avatarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        namefull.setBackground(new java.awt.Color(153, 153, 153));
        namefull.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        namefull.setForeground(new java.awt.Color(80, 80, 80));
        namefull.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        namefull.setText("jLabel1");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(avatar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(namefull, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(avatar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(namefull)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public csms.database.theme.ImageAvatar avatar;
    public javax.swing.JLabel namefull;
    // End of variables declaration//GEN-END:variables
}
