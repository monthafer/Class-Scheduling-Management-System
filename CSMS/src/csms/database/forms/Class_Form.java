package csms.database.forms;

import csms.database.admin.table.TableCustom;
import csms.database.component.Form;
import csms.database.glasspane.GlassPanePopup;
import csms.database.swing.subjectinfopanel;
import csms.database.swing.subjectpane;
import csms.databaseconnection.DBConnection;
import java.awt.Color;
import java.awt.Font;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableModel;
import net.miginfocom.swing.MigLayout;

public class Class_Form extends Form {
    
    Connection dbconn = DBConnection.openConnection();

    public Class_Form() {
        initComponents();
        init();
        searchid();
    }
    
    private void init(){
        TableCustom.apply(locscroll, TableCustom.TableType.MULTI_LINE);
        subjectpanel.setLayout(new MigLayout("wrap, h 448, w 282", "push[center]push"));
        JLabel label = new JLabel();
        label.setFont(new Font("Candara", 0, 12));
        label.setForeground(new Color(10, 10, 10));
        label.setText("Select a room to show the subjects");
        subjectpanel.add(label);
    }
    
    private void searchid(){
        DefaultTableModel dt = (DefaultTableModel) loctable.getModel();
        dt.setRowCount(0);
        try {
            String query = "SELECT * FROM location WHERE loc_id LIKE ? OR dep LIKE ? OR room LIKE ? OR room_desc LIKE ?";
            PreparedStatement ps = dbconn.prepareStatement(query);
            ps.setString(1, "%" + search.getText() + "%");
            ps.setString(2, "%" + search.getText() + "%");
            ps.setString(3, "%" + search.getText() + "%");
            ps.setString(4, "%" + search.getText() + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                Object[] o = {rs.getInt("loc_id"), rs.getString("dep"), rs.getString("room"), rs.getString("room_desc")};
                dt.addRow(o);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Class_Form.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void searchdep(){
        DefaultTableModel dt = (DefaultTableModel) loctable.getModel();
        dt.setRowCount(0);
        try {
            String query = "SELECT * FROM location WHERE loc_id LIKE ? OR dep LIKE ? OR room LIKE ? OR room_desc LIKE ? ORDER BY dep,room";
            PreparedStatement ps = dbconn.prepareStatement(query);
            ps.setString(1, "%" + search.getText() + "%");
            ps.setString(2, "%" + search.getText() + "%");
            ps.setString(3, "%" + search.getText() + "%");
            ps.setString(4, "%" + search.getText() + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                Object[] o = {rs.getInt("loc_id"), rs.getString("dep"), rs.getString("room"), rs.getString("room_desc")};
                dt.addRow(o);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Class_Form.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void searchdes(){
        DefaultTableModel dt = (DefaultTableModel) loctable.getModel();
        dt.setRowCount(0);
        try {
            String query = "SELECT * FROM location WHERE loc_id LIKE ? OR dep LIKE ? OR room LIKE ? OR room_desc LIKE ? ORDER BY room_desc,dep,room";
            PreparedStatement ps = dbconn.prepareStatement(query);
            ps.setString(1, "%" + search.getText() + "%");
            ps.setString(2, "%" + search.getText() + "%");
            ps.setString(3, "%" + search.getText() + "%");
            ps.setString(4, "%" + search.getText() + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                Object[] o = {rs.getInt("loc_id"), rs.getString("dep"), rs.getString("room"), rs.getString("room_desc")};
                dt.addRow(o);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Class_Form.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void choicehe(){
        if (sort.getSelectedIndex() == 0 || sort.getSelectedIndex() == 1) {
            searchid();
        } else if (sort.getSelectedIndex() == 2) {
            searchdep();
        } else if (sort.getSelectedIndex() == 3) {
            searchdes();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        locscroll = new javax.swing.JScrollPane();
        loctable = new javax.swing.JTable();
        subjectpanel = new csms.database.component.PlainPanelRound();
        search = new csms.database.swing.MyTextField();
        sort = new csms.database.swing.ChoiceBox();

        loctable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Location ID", "Department", "Room", "Room Description"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        loctable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                loctableMouseClicked(evt);
            }
        });
        locscroll.setViewportView(loctable);
        if (loctable.getColumnModel().getColumnCount() > 0) {
            loctable.getColumnModel().getColumn(0).setPreferredWidth(5);
            loctable.getColumnModel().getColumn(2).setPreferredWidth(5);
        }

        subjectpanel.setRoundBottomLeft(30);
        subjectpanel.setRoundBottomRight(30);
        subjectpanel.setRoundTopLeft(30);
        subjectpanel.setRoundTopRight(30);

        javax.swing.GroupLayout subjectpanelLayout = new javax.swing.GroupLayout(subjectpanel);
        subjectpanel.setLayout(subjectpanelLayout);
        subjectpanelLayout.setHorizontalGroup(
            subjectpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 282, Short.MAX_VALUE)
        );
        subjectpanelLayout.setVerticalGroup(
            subjectpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        search.setColor(new java.awt.Color(255, 255, 255));
        search.setHint("Search...");
        search.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                searchKeyReleased(evt);
            }
        });

        sort.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "<default>", "Location ID", "Department", "Room Description" }));
        sort.setColor(new java.awt.Color(255, 255, 255));
        sort.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sortActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(locscroll)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(search, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(sort, javax.swing.GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(subjectpanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(subjectpanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(search, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(sort, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(locscroll, javax.swing.GroupLayout.PREFERRED_SIZE, 417, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void searchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchKeyReleased
        choicehe();
    }//GEN-LAST:event_searchKeyReleased

    private void sortActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sortActionPerformed
        choicehe();
    }//GEN-LAST:event_sortActionPerformed

    private void loctableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_loctableMouseClicked
        subjectpanel.removeAll();
        subjectpanel.repaint();
        subjectpanel.revalidate();
        int b = loctable.getSelectedRow();
        String locid = String.valueOf(loctable.getValueAt(b, 0));
        try {
            String query = "SELECT * FROM subject WHERE loc_id=?";
            PreparedStatement ps = dbconn.prepareStatement(query);
            ps.setString(1, locid);
            ResultSet rs = ps.executeQuery();
            if(!rs.isBeforeFirst()){
                JLabel label = new JLabel();
                label.setFont(new Font("Candara", 0, 12));
                label.setForeground(new Color(10,10,10));
                label.setText("There's no subject in this room");
                subjectpanel.add(label);
            } else {
                while(rs.next()){
                    String n = rs.getString("name").toUpperCase();
                    String ns = rs.getString("name") + " " + rs.getString("sem");
                    int hs = rs.getInt("hour1");
                    int ms = rs.getInt("min1");
                    int he = rs.getInt("hour2");
                    int me = rs.getInt("min2");
                    int tea = rs.getInt("tea_id");
                    int sg = rs.getInt("sgs_id");
                    String d1 = rs.getString("day1");
                    String d2 = rs.getString("day2");
                    String d23 = rs.getString("day2");
                    String des = rs.getString("desc");
                    if(d2 == null || d2.equals("null")){
                        d2 = "null";
                    }
                    subjectpane pan = new subjectpane();
                    pan.setId(locid);
                    pan.updatetext(n, hs, ms, he, me, d1, d2);
                    pan.addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                            subjectinfopanel subp = new subjectinfopanel();
                            String dh1;
                            if (d23 == null || d23.equals("null")) {
                                dh1 = "null";
                            } else {
                                dh1 = d23;
                            }
                            subp.updatetext(ns, hs, ms, he, me, tea, sg, d1, dh1, des);
                            GlassPanePopup.showPopup(subp);
                        }
                    });
                    subjectpanel.add(pan);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Class_Form.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_loctableMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane locscroll;
    private javax.swing.JTable loctable;
    private csms.database.swing.MyTextField search;
    private csms.database.swing.ChoiceBox sort;
    private csms.database.component.PlainPanelRound subjectpanel;
    // End of variables declaration//GEN-END:variables
}
