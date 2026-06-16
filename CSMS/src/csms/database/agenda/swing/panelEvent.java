package csms.database.agenda.swing;

import csms.database.theme.SystemTheme;

public class panelEvent extends javax.swing.JPanel {

    public panelEvent() {
        initComponents();
        setBackground(SystemTheme.mainColor);
        dot.setBackground(SystemTheme.mainColor);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        agendaDate = new javax.swing.JLabel();
        agendaName = new javax.swing.JLabel();
        dot = new csms.database.swing.Button();

        agendaDate.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        agendaDate.setText("jLabel1");

        agendaName.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        agendaName.setText("Pre-Defense");

        dot.setIcon(new javax.swing.ImageIcon(getClass().getResource("/csms/database/icon/dots.png"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(agendaName)
                    .addComponent(agendaDate))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 73, Short.MAX_VALUE)
                .addComponent(dot, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(dot, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(agendaName)
                        .addGap(1, 1, 1)
                        .addComponent(agendaDate)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JLabel agendaDate;
    public javax.swing.JLabel agendaName;
    public csms.database.swing.Button dot;
    // End of variables declaration//GEN-END:variables
}
