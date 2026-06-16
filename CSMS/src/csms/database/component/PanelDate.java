package csms.database.component;

import csms.database.forms.Calendar_Form;
import csms.database.properties.SystemProperties;
import csms.database.theme.ThemeColorChange;
import csms.databaseconnection.DBConnection;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PanelDate extends javax.swing.JLayeredPane {

    public String getAgendaName() {
        return agendaName;
    }

    public void setAgendaName(String agendaName) {
        this.agendaName = agendaName;
    }

    public String getAgendaDesc() {
        return agendaDesc;
    }

    public void setAgendaDesc(String agendaDesc) {
        this.agendaDesc = agendaDesc;
    }

    public String getAgendaDate() {
        return agendaDate;
    }

    public void setAgendaDate(String agendaDate) {
        this.agendaDate = agendaDate;
    }

    private int month;
    private int year;
    SystemProperties pro = new SystemProperties();
    Connection dbconn = DBConnection.openConnection();
    private String agendaName;
    private String agendaDesc;
    private String agendaDate;

    public PanelDate(int month, int year) {
        initComponents();
        this.month = month;
        this.year = year;
        init();
        //System.out.println(Calendar.DAY_OF_WEEK);
        //System.out.println(Calendar.MONTH);
        //System.out.println(Calendar.YEAR);
    }

    private void init() {
        mon.asTitle();
        tue.asTitle();
        wed.asTitle();
        thu.asTitle();
        fri.asTitle();
        sat.asTitle();
        sun.asTitle();
        setDate();
        if(ThemeColorChange.getInstance().getMode() == ThemeColorChange.Mode.DARK){
            mon.setForeground(new Color(230,230,230));
            tue.setForeground(new Color(230,230,230));
            wed.setForeground(new Color(230,230,230));
            thu.setForeground(new Color(230,230,230));
            fri.setForeground(new Color(230,230,230));
            sat.setForeground(new Color(230,230,230));
        } else{
            mon.setForeground(new Color(80,80,80));
            tue.setForeground(new Color(80,80,80));
            wed.setForeground(new Color(80,80,80));
            thu.setForeground(new Color(80,80,80));
            fri.setForeground(new Color(80,80,80));
            sat.setForeground(new Color(80,80,80));
        }
    }

    private void setDate() {
        pro.loadFromFile();
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month - 1);  //  month jan as 0 so start from 0
        calendar.set(Calendar.DATE, 1);
        int startDay = calendar.get(Calendar.DAY_OF_WEEK) - 1;  //  get day of week -1 to index
        calendar.add(Calendar.DATE, -startDay);
        ToDay toDay = getToDay();
        
        for (Component com : getComponents()) {
            Cell cell = (Cell) com;
            if (!cell.isTitle()) {
                cell.setText(calendar.get(Calendar.DATE) + "");
                cell.setDate(calendar.getTime());
                cell.currentMonth(calendar.get(Calendar.MONTH) == month - 1);
                if (toDay.isToDay(new ToDay(calendar.get(Calendar.DATE), calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.YEAR)))) {
                    cell.setAsToDay();
                }
                //highlight(cell, toDay);
                calendar.add(Calendar.DATE, 1); //  up 1 day
            }
        }
        //high(toDay, calendar);
    }

    private ToDay getToDay() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        return new ToDay(calendar.get(Calendar.DATE), calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.YEAR));
    }
    
    private void highlight(Cell cell, ToDay toDay){
        try {
            Statement stmt = dbconn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM agenda");
            while(rs.next()){
                Date date = rs.getDate("date");
                String name = rs.getString("name");
                String desc = rs.getString("description");
                String repeat = rs.getString("repeatable").trim();
                
                Calendar cal = Calendar.getInstance();
                cal.setTime(date);
                
                if(repeat.equals("yes")){
                    if (toDay.isToDay(new ToDay(cal.get(Calendar.DATE), cal.get(Calendar.MONTH) + 1))) {
                        cell.setAsHighlighted();
                        cell.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                Calendar_Form form = new Calendar_Form();
                                if (!form.agendaPanel.isVisible()) {
                                    form.agendaPanel.setVisible(true);
                                }
                                form.agendaName.setText(name);
                                form.desc.setText(desc);
                                form.date.setText("Every " + getMonth(cal.get(Calendar.MONTH) + 1) + " " + cal.get(Calendar.DATE));
                            }
                        });
                    }
                } else {
                    if (toDay.isToDay(new ToDay(cal.get(Calendar.DATE), cal.get(Calendar.MONTH) + 1, cal.get(Calendar.YEAR)))) {
                        cell.setAsHighlighted();
                        cell.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                Calendar_Form form = new Calendar_Form();
                                if (!form.agendaPanel.isVisible()) {
                                    form.agendaPanel.setVisible(true);
                                }
                                form.agendaName.setText(name);
                                form.desc.setText(desc);
                                form.date.setText(getMonth(cal.get(Calendar.MONTH) + 1) + " " + cal.get(Calendar.DATE) + ", " + cal.get(Calendar.YEAR));
                            }
                        });
                    }
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(PanelDate.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void sgsFinder(String sgs, String sgsname) throws SQLException{
        Statement statement123 = dbconn.createStatement();
        ResultSet resultset123 = statement123.executeQuery("SELECT * FROM sgs");
        sgsname = resultset123.getString("strand") + " " + String.valueOf(resultset123.getInt("grade")) + resultset123.getString("section");
    }
    
    private void high(ToDay toDay, Calendar calendar){
        try {
            pro.loadFromFile();
            Statement stmt = dbconn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM agenda");
            while(rs.next()){
                Date date = rs.getDate("date");
                String name = rs.getString("name");
                String desc = rs.getString("description");
                String repeat = rs.getString("repeatable").trim();
                String sgs = String.valueOf(rs.getInt("sgs_id"));
                String sgsname = "";
                if (sgs == null || sgs.equals("")){
                    sgsFinder(sgs, sgsname);
                }
                
                Calendar cal = Calendar.getInstance();
                cal.setTime(date);
                
                if(repeat.equals("yes")) {
                    ToDay Day = new ToDay(cal.get(Calendar.DATE), cal.get(Calendar.MONTH) + 1);
                    for (Component com : getComponents()) {
                        Cell cell = (Cell) com;
                        if (!cell.isTitle()) {
                            cell.setText(calendar.get(Calendar.DATE) + "");
                            cell.setDate(calendar.getTime());
                            cell.currentMonth(calendar.get(Calendar.MONTH) == month - 1);
                            if (toDay.isToDay(new ToDay(calendar.get(Calendar.DATE), calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.YEAR)))) {
                                cell.setAsToDay();
                            }
                            if (Day.isToDay(new ToDay(calendar.get(Calendar.DATE), calendar.get(Calendar.MONTH) + 1))) {
                                cell.setAsHighlighted();
                                cell.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        //code
                                        setAgendaDate("Every " + getMonth(cal.get(Calendar.MONTH) + 1) + " " + cal.get(Calendar.DATE));
                                    }
                                });
                            }
                            calendar.add(Calendar.DATE, 1); //  up 1 day
                        }
                    }
                } else {
                    ToDay Day = new ToDay(cal.get(Calendar.DATE), cal.get(Calendar.MONTH) + 1, cal.get(Calendar.YEAR));
                    for (Component com : getComponents()){
                        Cell cell = (Cell) com;
                        if (!cell.isTitle()) {
                            cell.setText(calendar.get(Calendar.DATE) + "");
                            cell.setDate(calendar.getTime());
                            cell.currentMonth(calendar.get(Calendar.MONTH) == month - 1);
                            if (toDay.isToDay(new ToDay(calendar.get(Calendar.DATE), calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.YEAR)))) {
                                cell.setAsToDay();
                            }
                            if (Day.isToDay(new ToDay(calendar.get(Calendar.DATE), calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.YEAR)))) {
                                cell.setAsHighlighted();
                                cell.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        // code
                                        setAgendaDate(getMonth(cal.get(Calendar.MONTH) + 1) + " " + cal.get(Calendar.DATE) + ", " + cal.get(Calendar.YEAR));
                                    }
                                });
                            }
                            calendar.add(Calendar.DATE, 1); //  up 1 day
                        }
                    }
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(PanelDate.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private String getMonth(int month) {
        switch (month) {
            case 1:
                return "January";
            case 2:
                return "February";
            case 3:
                return "March";
            case 4:
                return "April";
            case 5:
                return "May";
            case 6:
                return "June";
            case 7:
                return "July";
            case 8:
                return "September";
            case 9:
                return "October";
            case 10:
                return "November";
            case 11:
                return "December";
            default:
                return "Unknown";
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        sun = new csms.database.component.Cell();
        mon = new csms.database.component.Cell();
        tue = new csms.database.component.Cell();
        wed = new csms.database.component.Cell();
        thu = new csms.database.component.Cell();
        fri = new csms.database.component.Cell();
        sat = new csms.database.component.Cell();
        cell8 = new csms.database.component.Cell();
        cell9 = new csms.database.component.Cell();
        cell10 = new csms.database.component.Cell();
        cell11 = new csms.database.component.Cell();
        cell12 = new csms.database.component.Cell();
        cell13 = new csms.database.component.Cell();
        cell14 = new csms.database.component.Cell();
        cell15 = new csms.database.component.Cell();
        cell16 = new csms.database.component.Cell();
        cell17 = new csms.database.component.Cell();
        cell18 = new csms.database.component.Cell();
        cell19 = new csms.database.component.Cell();
        cell20 = new csms.database.component.Cell();
        cell21 = new csms.database.component.Cell();
        cell22 = new csms.database.component.Cell();
        cell23 = new csms.database.component.Cell();
        cell24 = new csms.database.component.Cell();
        cell25 = new csms.database.component.Cell();
        cell26 = new csms.database.component.Cell();
        cell27 = new csms.database.component.Cell();
        cell28 = new csms.database.component.Cell();
        cell29 = new csms.database.component.Cell();
        cell30 = new csms.database.component.Cell();
        cell31 = new csms.database.component.Cell();
        cell32 = new csms.database.component.Cell();
        cell33 = new csms.database.component.Cell();
        cell34 = new csms.database.component.Cell();
        cell35 = new csms.database.component.Cell();
        cell36 = new csms.database.component.Cell();
        cell37 = new csms.database.component.Cell();
        cell38 = new csms.database.component.Cell();
        cell39 = new csms.database.component.Cell();
        cell40 = new csms.database.component.Cell();
        cell41 = new csms.database.component.Cell();
        cell42 = new csms.database.component.Cell();
        cell43 = new csms.database.component.Cell();
        cell44 = new csms.database.component.Cell();
        cell45 = new csms.database.component.Cell();
        cell46 = new csms.database.component.Cell();
        cell47 = new csms.database.component.Cell();
        cell48 = new csms.database.component.Cell();
        cell49 = new csms.database.component.Cell();

        setLayout(new java.awt.GridLayout(7, 7));

        sun.setForeground(new java.awt.Color(204, 0, 0));
        sun.setText("Sun");
        add(sun);

        mon.setText("Mon");
        add(mon);

        tue.setText("Tue");
        add(tue);

        wed.setText("Wed");
        add(wed);

        thu.setText("Thu");
        add(thu);

        fri.setText("Fri");
        add(fri);

        sat.setText("Sat");
        add(sat);
        add(cell8);
        add(cell9);
        add(cell10);
        add(cell11);
        add(cell12);
        add(cell13);
        add(cell14);
        add(cell15);
        add(cell16);
        add(cell17);
        add(cell18);
        add(cell19);
        add(cell20);
        add(cell21);
        add(cell22);
        add(cell23);
        add(cell24);
        add(cell25);
        add(cell26);
        add(cell27);
        add(cell28);
        add(cell29);
        add(cell30);
        add(cell31);
        add(cell32);
        add(cell33);
        add(cell34);
        add(cell35);
        add(cell36);
        add(cell37);
        add(cell38);
        add(cell39);
        add(cell40);
        add(cell41);
        add(cell42);
        add(cell43);
        add(cell44);
        add(cell45);
        add(cell46);
        add(cell47);
        add(cell48);
        add(cell49);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private csms.database.component.Cell cell10;
    private csms.database.component.Cell cell11;
    private csms.database.component.Cell cell12;
    private csms.database.component.Cell cell13;
    private csms.database.component.Cell cell14;
    private csms.database.component.Cell cell15;
    private csms.database.component.Cell cell16;
    private csms.database.component.Cell cell17;
    private csms.database.component.Cell cell18;
    private csms.database.component.Cell cell19;
    private csms.database.component.Cell cell20;
    private csms.database.component.Cell cell21;
    private csms.database.component.Cell cell22;
    private csms.database.component.Cell cell23;
    private csms.database.component.Cell cell24;
    private csms.database.component.Cell cell25;
    private csms.database.component.Cell cell26;
    private csms.database.component.Cell cell27;
    private csms.database.component.Cell cell28;
    private csms.database.component.Cell cell29;
    private csms.database.component.Cell cell30;
    private csms.database.component.Cell cell31;
    private csms.database.component.Cell cell32;
    private csms.database.component.Cell cell33;
    private csms.database.component.Cell cell34;
    private csms.database.component.Cell cell35;
    private csms.database.component.Cell cell36;
    private csms.database.component.Cell cell37;
    private csms.database.component.Cell cell38;
    private csms.database.component.Cell cell39;
    private csms.database.component.Cell cell40;
    private csms.database.component.Cell cell41;
    private csms.database.component.Cell cell42;
    private csms.database.component.Cell cell43;
    private csms.database.component.Cell cell44;
    private csms.database.component.Cell cell45;
    private csms.database.component.Cell cell46;
    private csms.database.component.Cell cell47;
    private csms.database.component.Cell cell48;
    private csms.database.component.Cell cell49;
    private csms.database.component.Cell cell8;
    private csms.database.component.Cell cell9;
    private csms.database.component.Cell fri;
    private csms.database.component.Cell mon;
    private csms.database.component.Cell sat;
    private csms.database.component.Cell sun;
    private csms.database.component.Cell thu;
    private csms.database.component.Cell tue;
    private csms.database.component.Cell wed;
    // End of variables declaration//GEN-END:variables
}
