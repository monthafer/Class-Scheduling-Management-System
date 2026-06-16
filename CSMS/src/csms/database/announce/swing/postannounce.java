package csms.database.announce.swing;

import csms.database.glasspane.GlassPanePopup;
import csms.database.properties.SystemProperties;
import csms.databaseconnection.DBConnection;
import java.sql.*;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class postannounce extends javax.swing.JPanel {
    
    Connection dbconn = DBConnection.openConnection();
    SystemProperties pro = new SystemProperties();

    public postannounce() {
        initComponents();
        init();
    }
    
    private void init(){
        stud.removeAllItems();
        stud.addItem("<all students>");
        try {
            String query = "SELECT * FROM sgs";
            PreparedStatement ps = dbconn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                String sg = rs.getString("strand") + " " + rs.getString("grade") + "-" + rs.getString("section");
                stud.addItem(sg);
            }
        } catch (SQLException ex) {
            Logger.getLogger(postannounce.class.getName()).log(Level.SEVERE, null, ex);
        }
        stud.requestFocus();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbtitle = new javax.swing.JLabel();
        title = new csms.database.swing.MyTextField();
        txt = new javax.swing.JTextPane();
        lbdesc = new javax.swing.JLabel();
        stud = new csms.database.swing.ChoiceBox();
        lbstud = new javax.swing.JLabel();
        lbdate = new javax.swing.JLabel();
        month = new csms.database.swing.ChoiceBox();
        day = new csms.database.swing.ChoiceBox();
        lbtitle1 = new javax.swing.JLabel();
        done = new csms.database.announce.swing.Button();
        lbtitle2 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));

        lbtitle.setFont(new java.awt.Font("Bahnschrift", 0, 12)); // NOI18N
        lbtitle.setText("Title:");

        title.setColor(new java.awt.Color(255, 255, 255));
        title.setHint("Enter title...");

        txt.setForeground(new java.awt.Color(133, 133, 133));
        txt.setMaximumSize(new java.awt.Dimension(339, 2147483647));
        txt.setMinimumSize(new java.awt.Dimension(339, 20));

        lbdesc.setFont(new java.awt.Font("Bahnschrift", 0, 12)); // NOI18N
        lbdesc.setText("Description:");

        stud.setColor(new java.awt.Color(255, 255, 255));

        lbstud.setFont(new java.awt.Font("Bahnschrift", 0, 12)); // NOI18N
        lbstud.setText("Student Audience:");

        lbdate.setFont(new java.awt.Font("Bahnschrift", 0, 12)); // NOI18N
        lbdate.setText("Date:");

        month.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December" }));
        month.setSelectedIndex(-1);
        month.setColor(new java.awt.Color(255, 255, 255));
        month.setHint("Month");

        day.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));
        day.setSelectedIndex(-1);
        day.setColor(new java.awt.Color(255, 255, 255));
        day.setHint("Day");

        lbtitle1.setFont(new java.awt.Font("Bahnschrift", 0, 14)); // NOI18N
        lbtitle1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbtitle1.setText("Add a new announcement");

        done.setText("Done");
        done.setFont(new java.awt.Font("Bahnschrift", 0, 12)); // NOI18N
        done.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                doneActionPerformed(evt);
            }
        });

        lbtitle2.setFont(new java.awt.Font("Bahnschrift", 0, 11)); // NOI18N
        lbtitle2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbtitle2.setText("For comboboxes, use up and down arrow keys.");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbtitle1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(done, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(48, 48, 48))
            .addGroup(layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lbstud)
                        .addGap(18, 18, 18)
                        .addComponent(stud, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(lbdesc)
                            .addGap(18, 18, 18)
                            .addComponent(txt, javax.swing.GroupLayout.DEFAULT_SIZE, 339, Short.MAX_VALUE))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(lbtitle)
                            .addGap(18, 18, 18)
                            .addComponent(title, javax.swing.GroupLayout.PREFERRED_SIZE, 377, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lbdate)
                        .addGap(18, 18, 18)
                        .addComponent(month, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(day, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(189, 189, 189)))
                .addContainerGap(40, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbtitle2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(lbtitle1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbtitle2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbtitle)
                    .addComponent(title, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbdesc))
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbstud)
                    .addComponent(stud, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbdate)
                    .addComponent(month, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(day, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(done, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void doneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_doneActionPerformed
        if (!title.getText().trim().equals("") && !txt.getText().trim().equals("") 
                && stud.getSelectedIndex() != -1 && day.getSelectedIndex() != -1) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(new Date());
            String datte = String.valueOf(cal.get(Calendar.YEAR)) + "-" + String.valueOf(month.getSelectedIndex() + 1) + "-" + String.valueOf(day.getSelectedItem());
            pro.loadFromFile();
            try {
                String query = "INSERT INTO announce(title,tea_id,timecreated,description,sgs) VALUES (?,?,?,?,?)";
                if (pro.getPosition().equals("Administrator")) {
                    query = "INSERT INTO announce(title,admin_id,timecreated,description,sgs) VALUES (?,?,?,?,?)";
                }
                PreparedStatement ps = dbconn.prepareStatement(query);
                ps.setString(1, title.getText());
                ps.setString(2, pro.getId());
                ps.setString(3, datte);
                ps.setString(4, txt.getText());
                if (stud.getSelectedIndex() == 0 || stud.getSelectedIndex() == -1) {
                    ps.setNull(5, java.sql.Types.INTEGER);
                } else {
                    ps.setString(5, String.valueOf(stud.getSelectedIndex() + 1));
                }
                ps.executeUpdate();
                JOptionPane.showMessageDialog(this, "Announcement Added!");
                GlassPanePopup.closePopupLast();
            } catch (SQLException ex) {
                Logger.getLogger(postannounce.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_doneActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private csms.database.swing.ChoiceBox day;
    private csms.database.announce.swing.Button done;
    private javax.swing.JLabel lbdate;
    private javax.swing.JLabel lbdesc;
    private javax.swing.JLabel lbstud;
    private javax.swing.JLabel lbtitle;
    private javax.swing.JLabel lbtitle1;
    private javax.swing.JLabel lbtitle2;
    private csms.database.swing.ChoiceBox month;
    private csms.database.swing.ChoiceBox stud;
    private csms.database.swing.MyTextField title;
    public javax.swing.JTextPane txt;
    // End of variables declaration//GEN-END:variables
}
