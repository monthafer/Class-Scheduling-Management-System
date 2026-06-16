package csms.database.forms;

import csms.database.component.Form;
import csms.database.component.TimePanel;
import csms.database.properties.SystemProperties;
import csms.database.scrollbar.ScrollBarCustom;
import csms.database.theme.ThemeColorChange;
import csms.databaseconnection.DBConnection;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class Timetable_Form extends Form {

    Connection dbconn = DBConnection.openConnection();
    private JPanel[] dayPanels = new JPanel[7];
    SystemProperties pro = new SystemProperties();

    public Timetable_Form() {
        initComponents();
        init();
        if(pro.getPosition().equals("Student")){
            initStud();
        } else {
            initTea();
        }
        
    }
    
    private void init(){
        pro.loadFromFile();
        scroll.setOpaque(false);
        scroll.setBackground(new Color(0, 0, 0, 0));
        scroll.getViewport().setOpaque(false);
        scroll.setBorder(null);
        scroll.setViewportBorder(null);
        scroll.setVerticalScrollBar(new ScrollBarCustom());
        mainPanel.setOpaque(false);
        
        mainPanel.setLayout(new GridLayout(1,5));
        for (int i = 0; i < 5; i++) {
            JPanel dayPanel = new JPanel(new GridLayout(7, 1));
            
            if (ThemeColorChange.getInstance().getMode() == ThemeColorChange.Mode.DARK) {
                dayPanel.setBackground(new Color(0,0,0,20));
                dayPanel.setForeground(new Color(0,0,0,20));
                dayPanel.setBorder(BorderFactory.createTitledBorder((new LineBorder(new Color(255,255,255), 1, true)),
                    getDayName(i).toUpperCase(), TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.CENTER, new Font("Century Gothic", 0, 12), Color.WHITE));
            } else {
                dayPanel.setForeground(new Color(255,255,255,20));
                dayPanel.setBackground(new Color(255,255,255,20));
                dayPanel.setBorder(BorderFactory.createTitledBorder((new LineBorder(new Color(0, 0, 0), 1, true)),
                    getDayName(i).toUpperCase(), TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.CENTER, new Font("Century Gothic", 0, 12), Color.BLACK));
            }
            dayPanels[i] = dayPanel;
            mainPanel.add(dayPanel);
        }
    }
    
    private void initTea(){
        try {
            Statement stmt = dbconn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM subject WHERE tea_id=" + pro.getId());
            while (rs.next()) {
                // Create a new JTextArea for each timetable entry and add it to the appropriate day panel
                String day2 = rs.getString("day2");
                TimePanel panel = new TimePanel();
                panel.subname.setText(rs.getString("name").toUpperCase());
                panel.subsgs.setText(convertSgs(rs.getInt("sgs_id")));
                panel.subteacher.setText(convertTeacher(rs.getInt("tea_id")));
                panel.subtime.setText(convertTime(rs.getInt("hour1"), rs.getInt("min1"), rs.getInt("hour2"), rs.getInt("min2")));
                dayPanels[getDayIndex(rs.getString("day1"))].add(panel);
                if(day2 != null){
                    TimePanel panel2 = new TimePanel();
                    panel2.subname.setText(rs.getString("name").toUpperCase());
                    panel2.subsgs.setText(convertSgs(rs.getInt("sgs_id")));
                    panel2.subteacher.setText(convertTeacher(rs.getInt("tea_id")));
                    panel2.subtime.setText(convertTime(rs.getInt("hour1"), rs.getInt("min1"), rs.getInt("hour2"), rs.getInt("min2")));
                    dayPanels[getDayIndex(day2)].add(panel2, "");
                }
            }
            rs.close();
            stmt.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    private void initStud(){
        try {
            Statement stmt = dbconn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM subject WHERE sgs_id=" + pro.getSgs());
            while (rs.next()) {
                // Create a new JTextArea for each timetable entry and add it to the appropriate day panel
                String day2 = rs.getString("day2");
                TimePanel panel = new TimePanel();
                panel.subname.setText(rs.getString("name").toUpperCase());
                panel.subsgs.setText(convertSgs(rs.getInt("sgs_id")));
                panel.subteacher.setText(convertTeacher(rs.getInt("tea_id")));
                panel.subtime.setText(convertTime(rs.getInt("hour1"), rs.getInt("min1"), rs.getInt("hour2"), rs.getInt("min2")));
                dayPanels[getDayIndex(rs.getString("day1"))].add(panel);
                if(day2 != null){
                    TimePanel panel2 = new TimePanel();
                    panel2.subname.setText(rs.getString("name").toUpperCase());
                    panel2.subsgs.setText(convertSgs(rs.getInt("sgs_id")));
                    panel2.subteacher.setText(convertTeacher(rs.getInt("tea_id")));
                    panel2.subtime.setText(convertTime(rs.getInt("hour1"), rs.getInt("min1"), rs.getInt("hour2"), rs.getInt("min2")));
                    dayPanels[getDayIndex(day2)].add(panel2, "");
                }
            }
            rs.close();
            stmt.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    private String convertTime(int hour1, int min1, int hour2, int min2){
        String hour_start, hour_end, start, end, min_start, min_end;
        if (hour1 > 12) {
            hour_start = String.valueOf(hour1 - 12);
            start = "PM";
        } else {
            hour_start = String.valueOf(hour1);
            start = "AM";
        }
        
        if (hour2 > 12) {
            hour_end = String.valueOf(hour2 - 12);
            end = "PM";
        } else {
            hour_end = String.valueOf(hour2);
            end = "AM";
        }
        
        if (min1 == 0){
            min_start = "00";
        } else if (min1 < 10){
            min_start = "0" + String.valueOf(min1);
        } else {
            min_start = String.valueOf(min1);
        }
        
        if (min2 == 0){
            min_end = "00";
        } else if (min2 < 10){
            min_end = "0" + String.valueOf(min2);
        } else {
            min_end = String.valueOf(min2);
        }
        
        return hour_start + ":" + min_start + start + " - " + hour_end + ":" + min_end + end;
    }
    
    private String convertTeacher(int teaid){
        try {
            Statement st = dbconn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM teacher WHERE tea_id=" + teaid);
            String tea = null;
            if(rs.next()){
                tea = rs.getString("first_name") + " "  + rs.getString("last_name");
            } 
            return tea;
        } catch (SQLException ex) {
            Logger.getLogger(Timetable_Form.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    private String convertSgs(int sgsid){
        try {
            Statement st = dbconn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM sgs WHERE sgs_id=" + sgsid);
            String sgs = null;
            if(rs.next()){
                sgs = rs.getString("strand") + " " + String.valueOf(rs.getInt("grade")) + "-" + rs.getString("section");
            } 
            return sgs;
        } catch (SQLException ex) {
            Logger.getLogger(Timetable_Form.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    private int getDayIndex(String day) {
      switch(day.toLowerCase()) {
         case "monday": return 0;
         case "tuesday": return 1;
         case "wednesday": return 2;
         case "thursday": return 3;
         case "friday": return 4;
         case "saturday": return 5;
         case "sunday": return 6;
         default: return -1;
      }
   }
  
   private String getDayName(int index) {
      switch(index) {
         case 0: return "Monday";
         case 1: return "Tuesday";
         case 2: return "Wednesday";
         case 3: return "Thursday";
         case 4: return "Friday";
         case 5: return "Saturday";
         case 6: return "Sunday";
         default: return "Invalid Day";
      }
   }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        scroll = new javax.swing.JScrollPane();
        mainPanel = new javax.swing.JPanel();

        scroll.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 836, Short.MAX_VALUE)
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 531, Short.MAX_VALUE)
        );

        scroll.setViewportView(mainPanel);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scroll)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scroll, javax.swing.GroupLayout.DEFAULT_SIZE, 518, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel mainPanel;
    private javax.swing.JScrollPane scroll;
    // End of variables declaration//GEN-END:variables
}
