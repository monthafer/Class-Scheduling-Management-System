package csms.database.forms;

import csms.database.admin.swing.popup;
import csms.database.admin.table.TableCustom;
import csms.database.component.Form;
import csms.database.glasspane.GlassPanePopup;
import csms.database.theme.ThemeColorChange;
import csms.databaseconnection.DBConnection;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

public class Admin_Form extends Form {

    Connection dbconn = DBConnection.openConnection();
    SimpleDateFormat df = new SimpleDateFormat("MMMM dd, yyyy");
    private int q = -1;

    public Admin_Form() {
        initComponents();
        initdefault();
        initDarkMode();
    }
    
    private void initdefault(){
        hideall();
        studtablepanel.setVisible(true);
        editstud.setVisible(true);
        userbuttonpanel.setVisible(true);
        sortStudent();
        defaultStudent();
        initUsers();
        initAgenda();
        initLocation();
        initSgs();
        initSubject();
        
        txt.setBackground(new Color(0,0,0,0));
        txt.setSelectionColor(new Color(48,170,63,200));
        txt.setOpaque(false);
        
        TableCustom.apply(studtablescroll, TableCustom.TableType.MULTI_LINE);
        TableCustom.apply(teatablescroll, TableCustom.TableType.MULTI_LINE);
        TableCustom.apply(admintablescroll, TableCustom.TableType.MULTI_LINE);
        TableCustom.apply(loctablescroll, TableCustom.TableType.MULTI_LINE);
        TableCustom.apply(subtablescroll, TableCustom.TableType.MULTI_LINE);
        TableCustom.apply(sgstablescroll, TableCustom.TableType.MULTI_LINE);
        TableCustom.apply(agendatablescroll, TableCustom.TableType.MULTI_LINE);
        
        showtable.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(showtable.getSelectedIndex() == 0){
                    hideall();
                    studtablepanel.setVisible(true);
                    editstud.setVisible(true);
                    userbuttonpanel.setVisible(true);
                    txt.setText("▫ The SGS ID corresponds to the SGS ID in the SGS table. ▫ The SGS ID can be nulled by leaving its text field blank. ▫ Note that you cannot add or edit a student but you can remove them.");
                    defaultStudent();
                    sortStudent();
                } else if(showtable.getSelectedIndex() == 1){
                    hideall();
                    teatablepanel.setVisible(true);
                    edittea.setVisible(true);
                    userbuttonpanel.setVisible(true);
                    txt.setText("▫ Note that you cannot add or edit a teacher but you can remove them.");
                    defaultTeacher();
                    sortTeacher();
                } else if(showtable.getSelectedIndex() == 2){
                    hideall();
                    admintablepanel.setVisible(true);
                    editadmin.setVisible(true);
                    userbuttonpanel.setVisible(true);
                    txt.setText("▫ Note that you cannot add or edit an admin but you can remove them.");
                    defaultAdmin();
                    sortAdmin();
                } else if(showtable.getSelectedIndex() == 3){
                    hideall();
                    sgstablepanel.setVisible(true);
                    editsgs.setVisible(true);
                    sgsbuttonpanel.setVisible(true);
                    txt.setText("▫ The teacher adviser corresponds to the ID of the teacher in the teacher table. ▫The teacher adviser can be nulled by leaving its text field blank. ▫Note that the SGS ID should not be the same to other SGS to avoid error redundancy. ▫If a specific strand has only one section, put '1' on the section text field.");
                    defaultSgs();
                    sortSgs();
                } else if(showtable.getSelectedIndex() == 4){
                    hideall();
                    loctablepanel.setVisible(true);
                    editloc.setVisible(true);
                    locbuttonpanel.setVisible(true);
                    txt.setText("▫Note that the Location ID should not be the same to other Location to avoid error redundancy. ▫The description refers to the name of that room(eg. If the room 8 is Computer Room, put '8' in the room text field and 'Computer Room' in the description text field.) ▫The description can be nulled by leaving its text field blank.");
                    defaultLocation();
                    sortLocation();
                } else if(showtable.getSelectedIndex() == 5){
                    hideall();
                    agendatablepanel.setVisible(true);
                    editagenda.setVisible(true);
                    agendabuttonpanel.setVisible(true);
                    txt.setText("▫Agenda ID cannot be duplicated to avoid redundancy error. ▫All of the fields are required to be filled up.");
                    defaultAgenda();
                    sortAgenda();
                }
                else if(showtable.getSelectedIndex() == 6){
                    hideall();
                    subtablepanel.setVisible(true);
                    editsub.setVisible(true);
                    subbuttonpanel.setVisible(true);
                    txt.setText("▫Subject ID cannot be duplicated to avoid redundancy error. ▫The semester should be in Integer form(eg. 1,2,3,4). ▫The day2, Loc ID, SGS ID, and Teacher ID can be nulled by leaving it blank or selecting the <none> item for day2. ▫The hour and min start represent the starting hour and min of the class. That also goes to the hour and min end. ▫(eg. If the class is set on 10:00AM-1:30PM, set the hour start to 10, min start to 0, hour end to 13, and min end to 30. The hour is in 24 hour format)");
                    defaultSubject();
                    sortSubject();
                }
            }
        });
    }
    private void initDarkMode(){
        if(ThemeColorChange.getInstance().getMode() == ThemeColorChange.Mode.DARK){
            labelbottom.setForeground(Color.WHITE);
            labelbottom1.setForeground(Color.WHITE);
            labelbottom2.setForeground(Color.WHITE);
            lbemail.setForeground(Color.WHITE);
            lbemail1.setForeground(Color.WHITE);
            lbemail2.setForeground(Color.WHITE);
            lbemail3.setForeground(Color.WHITE);
            lbemail4.setForeground(Color.WHITE);
            lbemail5.setForeground(Color.WHITE);
            lbemail6.setForeground(Color.WHITE);
            lbfirst.setForeground(Color.WHITE);
            lbfirst1.setForeground(Color.WHITE);
            lbfirst2.setForeground(Color.WHITE);
            lbfirst3.setForeground(Color.WHITE);
            lbfirst4.setForeground(Color.WHITE);
            lbfirst5.setForeground(Color.WHITE);
            lbfirst6.setForeground(Color.WHITE);
            lblast.setForeground(Color.WHITE);
            lblast6.setForeground(Color.WHITE);
            lblast5.setForeground(Color.WHITE);
            lblast4.setForeground(Color.WHITE);
            lblast3.setForeground(Color.WHITE);
            lblast2.setForeground(Color.WHITE);
            lblast1.setForeground(Color.WHITE);
            lbnum.setForeground(Color.WHITE);
            lbnum1.setForeground(Color.WHITE);
            lbnum10.setForeground(Color.WHITE);
            lbnum11.setForeground(Color.WHITE);
            lbnum12.setForeground(Color.WHITE);
            lbnum13.setForeground(Color.WHITE);
            lbnum14.setForeground(Color.WHITE);
            lbnum15.setForeground(Color.WHITE);
            lbnum17.setForeground(Color.WHITE);
            lbnum2.setForeground(Color.WHITE);
            lbnum3.setForeground(Color.WHITE);
            lbnum4.setForeground(Color.WHITE);
            lbnum5.setForeground(Color.WHITE);
            lbnum6.setForeground(Color.WHITE);
            lbnum7.setForeground(Color.WHITE);
            lbnum8.setForeground(Color.WHITE);
            lbnum9.setForeground(Color.WHITE);
            lbsgs.setForeground(Color.WHITE);
            lbshowtable.setForeground(Color.WHITE);
            lbsortby.setForeground(Color.WHITE);
            lbstudid.setForeground(Color.WHITE);
            lbteaid.setForeground(Color.WHITE);
            lbteaid1.setForeground(Color.WHITE);
            lbteaid2.setForeground(Color.WHITE);
            lbteaid3.setForeground(Color.WHITE);
            lbteaid4.setForeground(Color.WHITE);
            lbteaid5.setForeground(Color.WHITE);
            lbtitle.setForeground(Color.WHITE);
            lbtitle1.setForeground(Color.WHITE);
            lbtitle2.setForeground(Color.WHITE);
            lbtitle3.setForeground(Color.WHITE);
            lbtitle4.setForeground(Color.WHITE);
            lbtitle5.setForeground(Color.WHITE);
            lbtitle6.setForeground(Color.WHITE);
        } else {
            labelbottom.setForeground(Color.BLACK);
            labelbottom1.setForeground(Color.BLACK);
            labelbottom2.setForeground(Color.BLACK);
            lbemail.setForeground(Color.BLACK);
            lbemail1.setForeground(Color.BLACK);
            lbemail2.setForeground(Color.BLACK);
            lbemail3.setForeground(Color.BLACK);
            lbemail4.setForeground(Color.BLACK);
            lbemail5.setForeground(Color.BLACK);
            lbemail6.setForeground(Color.BLACK);
            lbfirst.setForeground(Color.BLACK);
            lbfirst1.setForeground(Color.BLACK);
            lbfirst2.setForeground(Color.BLACK);
            lbfirst3.setForeground(Color.BLACK);
            lbfirst4.setForeground(Color.BLACK);
            lbfirst5.setForeground(Color.BLACK);
            lbfirst6.setForeground(Color.BLACK);
            lblast.setForeground(Color.BLACK);
            lblast6.setForeground(Color.BLACK);
            lblast5.setForeground(Color.BLACK);
            lblast4.setForeground(Color.BLACK);
            lblast3.setForeground(Color.BLACK);
            lblast2.setForeground(Color.BLACK);
            lblast1.setForeground(Color.BLACK);
            lbnum.setForeground(Color.BLACK);
            lbnum1.setForeground(Color.BLACK);
            lbnum10.setForeground(Color.BLACK);
            lbnum11.setForeground(Color.BLACK);
            lbnum12.setForeground(Color.BLACK);
            lbnum13.setForeground(Color.BLACK);
            lbnum14.setForeground(Color.BLACK);
            lbnum15.setForeground(Color.BLACK);
            lbnum17.setForeground(Color.BLACK);
            lbnum2.setForeground(Color.BLACK);
            lbnum3.setForeground(Color.BLACK);
            lbnum4.setForeground(Color.BLACK);
            lbnum5.setForeground(Color.BLACK);
            lbnum6.setForeground(Color.BLACK);
            lbnum7.setForeground(Color.BLACK);
            lbnum8.setForeground(Color.BLACK);
            lbnum9.setForeground(Color.BLACK);
            lbsgs.setForeground(Color.BLACK);
            lbshowtable.setForeground(Color.BLACK);
            lbsortby.setForeground(Color.BLACK);
            lbstudid.setForeground(Color.BLACK);
            lbteaid.setForeground(Color.BLACK);
            lbteaid1.setForeground(Color.BLACK);
            lbteaid2.setForeground(Color.BLACK);
            lbteaid3.setForeground(Color.BLACK);
            lbteaid4.setForeground(Color.BLACK);
            lbteaid5.setForeground(Color.BLACK);
            lbtitle.setForeground(Color.BLACK);
            lbtitle1.setForeground(Color.BLACK);
            lbtitle2.setForeground(Color.BLACK);
            lbtitle3.setForeground(Color.BLACK);
            lbtitle4.setForeground(Color.BLACK);
            lbtitle5.setForeground(Color.BLACK);
            lbtitle6.setForeground(Color.BLACK);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tableLayeredPane = new javax.swing.JLayeredPane();
        subtablepanel = new javax.swing.JPanel();
        subtablescroll = new javax.swing.JScrollPane();
        subtable = new javax.swing.JTable();
        sgstablepanel = new javax.swing.JPanel();
        sgstablescroll = new javax.swing.JScrollPane();
        sgstable = new javax.swing.JTable();
        loctablepanel = new javax.swing.JPanel();
        loctablescroll = new javax.swing.JScrollPane();
        loctable = new javax.swing.JTable();
        agendatablepanel = new javax.swing.JPanel();
        agendatablescroll = new javax.swing.JScrollPane();
        agendatable = new javax.swing.JTable();
        admintablepanel = new javax.swing.JPanel();
        admintablescroll = new javax.swing.JScrollPane();
        admintable = new javax.swing.JTable();
        teatablepanel = new javax.swing.JPanel();
        teatablescroll = new javax.swing.JScrollPane();
        teatable = new javax.swing.JTable();
        studtablepanel = new javax.swing.JPanel();
        studtablescroll = new javax.swing.JScrollPane();
        studtable = new javax.swing.JTable();
        editLayeredPane = new javax.swing.JLayeredPane();
        editsub = new javax.swing.JPanel();
        lbtitle6 = new javax.swing.JLabel();
        lbteaid5 = new javax.swing.JLabel();
        lbfirst6 = new javax.swing.JLabel();
        lblast6 = new javax.swing.JLabel();
        lbemail6 = new javax.swing.JLabel();
        lbnum8 = new javax.swing.JLabel();
        txtsubid = new javax.swing.JTextField();
        txtsubname = new javax.swing.JTextField();
        txtsubsem = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtsubdesc = new javax.swing.JTextArea();
        txtsubday1 = new csms.database.swing.Choice2();
        txtsubday2 = new csms.database.swing.Choice2();
        lbnum9 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        lbnum10 = new javax.swing.JLabel();
        txtsubhour1 = new javax.swing.JTextField();
        txtsubmin1 = new javax.swing.JTextField();
        lbnum11 = new javax.swing.JLabel();
        txtsubmin2 = new javax.swing.JTextField();
        lbnum12 = new javax.swing.JLabel();
        txtsubhour2 = new javax.swing.JTextField();
        lbnum13 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        lbnum14 = new javax.swing.JLabel();
        txtsublocid = new javax.swing.JTextField();
        txtsubsgsid = new javax.swing.JTextField();
        lbnum15 = new javax.swing.JLabel();
        txtsubteaid = new javax.swing.JTextField();
        lbnum17 = new javax.swing.JLabel();
        editsgs = new javax.swing.JPanel();
        lbtitle4 = new javax.swing.JLabel();
        lbteaid3 = new javax.swing.JLabel();
        lbfirst4 = new javax.swing.JLabel();
        lblast4 = new javax.swing.JLabel();
        lbemail4 = new javax.swing.JLabel();
        lbnum7 = new javax.swing.JLabel();
        txtsgsid = new javax.swing.JTextField();
        txtsgsstrand = new javax.swing.JTextField();
        txtsgsgrade = new javax.swing.JTextField();
        txtsgssection = new javax.swing.JTextField();
        txtsgsteaid = new javax.swing.JTextField();
        editloc = new javax.swing.JPanel();
        lbtitle5 = new javax.swing.JLabel();
        lbteaid4 = new javax.swing.JLabel();
        lbfirst5 = new javax.swing.JLabel();
        lblast5 = new javax.swing.JLabel();
        lbemail5 = new javax.swing.JLabel();
        txtlocid = new javax.swing.JTextField();
        txtlocname = new javax.swing.JTextField();
        txtlocroom = new javax.swing.JTextField();
        txtlocdesc = new javax.swing.JTextField();
        editagenda = new javax.swing.JPanel();
        lbtitle3 = new javax.swing.JLabel();
        lbteaid2 = new javax.swing.JLabel();
        lbfirst3 = new javax.swing.JLabel();
        lblast3 = new javax.swing.JLabel();
        lbemail3 = new javax.swing.JLabel();
        lbnum6 = new javax.swing.JLabel();
        txtagendaid = new javax.swing.JTextField();
        txtagendaname = new javax.swing.JTextField();
        txtagendarepeat = new csms.database.swing.Choice2();
        txtagendadatemonth = new csms.database.swing.Choice2();
        txtagendadateday = new csms.database.swing.Choice2();
        jScrollPane4 = new javax.swing.JScrollPane();
        txtagendadesc = new javax.swing.JTextArea();
        editadmin = new javax.swing.JPanel();
        lbtitle2 = new javax.swing.JLabel();
        lbteaid1 = new javax.swing.JLabel();
        lbfirst2 = new javax.swing.JLabel();
        lblast2 = new javax.swing.JLabel();
        lbemail2 = new javax.swing.JLabel();
        lbnum2 = new javax.swing.JLabel();
        txtadminid = new javax.swing.JTextField();
        txtadminfirst = new javax.swing.JTextField();
        txtadminlast = new javax.swing.JTextField();
        txtadminemail = new javax.swing.JTextField();
        txtadminnum = new javax.swing.JTextField();
        labelbottom2 = new javax.swing.JLabel();
        txtadminjob = new javax.swing.JTextField();
        lbnum3 = new javax.swing.JLabel();
        txtadminjobloc = new javax.swing.JTextField();
        lbnum4 = new javax.swing.JLabel();
        lbnum5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtadminjobdesc = new javax.swing.JTextArea();
        edittea = new javax.swing.JPanel();
        lbtitle1 = new javax.swing.JLabel();
        lbteaid = new javax.swing.JLabel();
        lbfirst1 = new javax.swing.JLabel();
        lblast1 = new javax.swing.JLabel();
        lbemail1 = new javax.swing.JLabel();
        lbnum1 = new javax.swing.JLabel();
        txtteaid = new javax.swing.JTextField();
        txtteafirst = new javax.swing.JTextField();
        txttealast = new javax.swing.JTextField();
        txtteaemail = new javax.swing.JTextField();
        txtteanum = new javax.swing.JTextField();
        labelbottom1 = new javax.swing.JLabel();
        editstud = new javax.swing.JPanel();
        lbtitle = new javax.swing.JLabel();
        lbstudid = new javax.swing.JLabel();
        lbfirst = new javax.swing.JLabel();
        lblast = new javax.swing.JLabel();
        lbemail = new javax.swing.JLabel();
        lbnum = new javax.swing.JLabel();
        lbsgs = new javax.swing.JLabel();
        txtstudid = new javax.swing.JTextField();
        txtstudfirst = new javax.swing.JTextField();
        txtstudlast = new javax.swing.JTextField();
        txtstudemail = new javax.swing.JTextField();
        txtstudnum = new javax.swing.JTextField();
        txtstudsgs = new javax.swing.JTextField();
        labelbottom = new javax.swing.JLabel();
        tablesortPanel = new javax.swing.JPanel();
        lbshowtable = new javax.swing.JLabel();
        showtable = new csms.database.swing.Choice2();
        sortby = new csms.database.swing.Choice2();
        lbsortby = new javax.swing.JLabel();
        buttonLayeredPane = new javax.swing.JLayeredPane();
        userbuttonpanel = new javax.swing.JPanel();
        removebutton = new csms.database.swing.Button();
        agendabuttonpanel = new javax.swing.JPanel();
        agendaremovebutton = new csms.database.swing.Button();
        agendaaddbutton = new csms.database.swing.Button();
        agendaupdatebutton = new csms.database.swing.Button();
        agendarefreshbutton = new csms.database.swing.Button();
        sgsbuttonpanel = new javax.swing.JPanel();
        sgsremovebutton = new csms.database.swing.Button();
        sgsaddbutton = new csms.database.swing.Button();
        sgsupdatebutton = new csms.database.swing.Button();
        sgsrefreshbutton = new csms.database.swing.Button();
        locbuttonpanel = new javax.swing.JPanel();
        locaddbutton = new csms.database.swing.Button();
        locupdatebutton = new csms.database.swing.Button();
        locremovebutton = new csms.database.swing.Button();
        locrefreshbutton = new csms.database.swing.Button();
        subbuttonpanel = new javax.swing.JPanel();
        subaddbutton = new csms.database.swing.Button();
        subupdatebutton = new csms.database.swing.Button();
        subremovebutton = new csms.database.swing.Button();
        subrefreshbutton = new csms.database.swing.Button();
        otherOptionPanel = new javax.swing.JPanel();
        txt = new javax.swing.JTextPane();

        tableLayeredPane.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        subtablepanel.setFocusable(false);
        subtablepanel.setOpaque(false);

        subtable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Subject", "Semester", "Description", "Day 1", "Day 2", "Hour 1", "Minute 1", "Hour 2", "Minute 2", "Loc ID", "SGS ID", "Teacher ID"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        subtablescroll.setViewportView(subtable);
        if (subtable.getColumnModel().getColumnCount() > 0) {
            subtable.getColumnModel().getColumn(0).setPreferredWidth(30);
            subtable.getColumnModel().getColumn(1).setPreferredWidth(60);
            subtable.getColumnModel().getColumn(2).setPreferredWidth(30);
            subtable.getColumnModel().getColumn(4).setPreferredWidth(50);
            subtable.getColumnModel().getColumn(5).setPreferredWidth(50);
            subtable.getColumnModel().getColumn(6).setPreferredWidth(30);
            subtable.getColumnModel().getColumn(7).setPreferredWidth(30);
            subtable.getColumnModel().getColumn(8).setPreferredWidth(30);
            subtable.getColumnModel().getColumn(9).setPreferredWidth(30);
            subtable.getColumnModel().getColumn(10).setPreferredWidth(30);
            subtable.getColumnModel().getColumn(11).setPreferredWidth(30);
            subtable.getColumnModel().getColumn(12).setPreferredWidth(30);
        }

        javax.swing.GroupLayout subtablepanelLayout = new javax.swing.GroupLayout(subtablepanel);
        subtablepanel.setLayout(subtablepanelLayout);
        subtablepanelLayout.setHorizontalGroup(
            subtablepanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(subtablescroll)
        );
        subtablepanelLayout.setVerticalGroup(
            subtablepanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(subtablescroll, javax.swing.GroupLayout.DEFAULT_SIZE, 350, Short.MAX_VALUE)
        );

        tableLayeredPane.add(subtablepanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 600, -1));

        sgstablepanel.setFocusable(false);
        sgstablepanel.setOpaque(false);

        sgstable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "SGS ID", "Strand", "Grade", "Section", "Teacher Adviser ID"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        sgstablescroll.setViewportView(sgstable);
        if (sgstable.getColumnModel().getColumnCount() > 0) {
            sgstable.getColumnModel().getColumn(0).setPreferredWidth(20);
            sgstable.getColumnModel().getColumn(4).setPreferredWidth(40);
        }

        javax.swing.GroupLayout sgstablepanelLayout = new javax.swing.GroupLayout(sgstablepanel);
        sgstablepanel.setLayout(sgstablepanelLayout);
        sgstablepanelLayout.setHorizontalGroup(
            sgstablepanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(sgstablescroll)
        );
        sgstablepanelLayout.setVerticalGroup(
            sgstablepanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(sgstablescroll, javax.swing.GroupLayout.DEFAULT_SIZE, 350, Short.MAX_VALUE)
        );

        tableLayeredPane.add(sgstablepanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 600, -1));

        loctablepanel.setFocusable(false);
        loctablepanel.setOpaque(false);

        loctable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Location ID", "Department Name", "Room Number", "Room Description"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        loctablescroll.setViewportView(loctable);
        if (loctable.getColumnModel().getColumnCount() > 0) {
            loctable.getColumnModel().getColumn(0).setPreferredWidth(10);
            loctable.getColumnModel().getColumn(1).setPreferredWidth(30);
            loctable.getColumnModel().getColumn(2).setPreferredWidth(10);
            loctable.getColumnModel().getColumn(3).setPreferredWidth(100);
        }

        javax.swing.GroupLayout loctablepanelLayout = new javax.swing.GroupLayout(loctablepanel);
        loctablepanel.setLayout(loctablepanelLayout);
        loctablepanelLayout.setHorizontalGroup(
            loctablepanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(loctablescroll)
        );
        loctablepanelLayout.setVerticalGroup(
            loctablepanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(loctablescroll, javax.swing.GroupLayout.DEFAULT_SIZE, 350, Short.MAX_VALUE)
        );

        tableLayeredPane.add(loctablepanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 600, -1));

        agendatablepanel.setFocusable(false);
        agendatablepanel.setOpaque(false);

        agendatable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Agenda ID", "Agenda Name", "Agenda Description", "Date", "Repeatable"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        agendatablescroll.setViewportView(agendatable);

        javax.swing.GroupLayout agendatablepanelLayout = new javax.swing.GroupLayout(agendatablepanel);
        agendatablepanel.setLayout(agendatablepanelLayout);
        agendatablepanelLayout.setHorizontalGroup(
            agendatablepanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(agendatablescroll)
        );
        agendatablepanelLayout.setVerticalGroup(
            agendatablepanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(agendatablescroll, javax.swing.GroupLayout.DEFAULT_SIZE, 350, Short.MAX_VALUE)
        );

        tableLayeredPane.add(agendatablepanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 600, -1));

        admintablepanel.setFocusable(false);
        admintablepanel.setOpaque(false);

        admintable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "First Name", "Last Name", "E-mail", "Contact Number", "Job", "Job Location", "Job Description"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        admintablescroll.setViewportView(admintable);
        if (admintable.getColumnModel().getColumnCount() > 0) {
            admintable.getColumnModel().getColumn(0).setPreferredWidth(30);
        }

        javax.swing.GroupLayout admintablepanelLayout = new javax.swing.GroupLayout(admintablepanel);
        admintablepanel.setLayout(admintablepanelLayout);
        admintablepanelLayout.setHorizontalGroup(
            admintablepanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(admintablescroll)
        );
        admintablepanelLayout.setVerticalGroup(
            admintablepanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(admintablescroll, javax.swing.GroupLayout.DEFAULT_SIZE, 350, Short.MAX_VALUE)
        );

        tableLayeredPane.add(admintablepanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 600, -1));

        teatablepanel.setFocusable(false);
        teatablepanel.setOpaque(false);

        teatable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID", "First Name", "Last Name", "E-mail", "Contact Number"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        teatablescroll.setViewportView(teatable);
        if (teatable.getColumnModel().getColumnCount() > 0) {
            teatable.getColumnModel().getColumn(0).setPreferredWidth(30);
        }

        javax.swing.GroupLayout teatablepanelLayout = new javax.swing.GroupLayout(teatablepanel);
        teatablepanel.setLayout(teatablepanelLayout);
        teatablepanelLayout.setHorizontalGroup(
            teatablepanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(teatablescroll, javax.swing.GroupLayout.DEFAULT_SIZE, 600, Short.MAX_VALUE)
        );
        teatablepanelLayout.setVerticalGroup(
            teatablepanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(teatablescroll, javax.swing.GroupLayout.DEFAULT_SIZE, 350, Short.MAX_VALUE)
        );

        tableLayeredPane.add(teatablepanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        studtablepanel.setFocusable(false);
        studtablepanel.setOpaque(false);

        studtable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID", "First Name", "Last Name", "E-mail", "Contact Number", "SGS ID"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        studtablescroll.setViewportView(studtable);
        if (studtable.getColumnModel().getColumnCount() > 0) {
            studtable.getColumnModel().getColumn(0).setPreferredWidth(30);
            studtable.getColumnModel().getColumn(5).setPreferredWidth(30);
        }

        javax.swing.GroupLayout studtablepanelLayout = new javax.swing.GroupLayout(studtablepanel);
        studtablepanel.setLayout(studtablepanelLayout);
        studtablepanelLayout.setHorizontalGroup(
            studtablepanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(studtablescroll)
        );
        studtablepanelLayout.setVerticalGroup(
            studtablepanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(studtablescroll, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );

        tableLayeredPane.add(studtablepanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 600, 350));

        editsub.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        editsub.setOpaque(false);

        lbtitle6.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        lbtitle6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbtitle6.setText("Subject Edit Menu");

        lbteaid5.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        lbteaid5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbteaid5.setText("Subject ID:");

        lbfirst6.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        lbfirst6.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbfirst6.setText("Subject:");

        lblast6.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        lblast6.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblast6.setText("Semester:");

        lbemail6.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        lbemail6.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbemail6.setText("Description:");

        lbnum8.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        lbnum8.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbnum8.setText("Day 1:");

        txtsubid.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        txtsubname.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        txtsubsem.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jScrollPane3.setBorder(null);

        txtsubdesc.setColumns(20);
        txtsubdesc.setLineWrap(true);
        txtsubdesc.setRows(5);
        txtsubdesc.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jScrollPane3.setViewportView(txtsubdesc);

        txtsubday1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Monday", "Tuesday", "Wednesday", "Thursday", "Friday" }));
        txtsubday1.setSelectedIndex(-1);

        txtsubday2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "<none>", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday" }));

        lbnum9.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        lbnum9.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbnum9.setText("Day 2:");

        jPanel1.setOpaque(false);

        lbnum10.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        lbnum10.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbnum10.setText("Hour Start:");

        txtsubhour1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        txtsubmin1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lbnum11.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        lbnum11.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbnum11.setText("Min Start:");

        txtsubmin2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lbnum12.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        lbnum12.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbnum12.setText("Min End:");

        txtsubhour2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lbnum13.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        lbnum13.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbnum13.setText("Hour End:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbnum13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbnum10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtsubhour1, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                        .addComponent(lbnum11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtsubmin1, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtsubhour2, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lbnum12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtsubmin2, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtsubhour1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbnum10)
                    .addComponent(txtsubmin1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbnum11))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtsubhour2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbnum13)
                    .addComponent(txtsubmin2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbnum12))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        jPanel2.setOpaque(false);

        lbnum14.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        lbnum14.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbnum14.setText("Loc ID:");

        txtsublocid.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        txtsubsgsid.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lbnum15.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        lbnum15.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbnum15.setText("SGS ID:");

        txtsubteaid.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lbnum17.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        lbnum17.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbnum17.setText("Teacher ID Adviser:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(lbnum14, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtsublocid, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lbnum15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtsubsgsid, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(lbnum17)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtsubteaid)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtsublocid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbnum14)
                    .addComponent(txtsubsgsid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbnum15))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtsubteaid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbnum17))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout editsubLayout = new javax.swing.GroupLayout(editsub);
        editsub.setLayout(editsubLayout);
        editsubLayout.setHorizontalGroup(
            editsubLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(editsubLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(editsubLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbtitle6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(editsubLayout.createSequentialGroup()
                        .addGroup(editsubLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblast6)
                            .addComponent(lbemail6)
                            .addComponent(lbfirst6)
                            .addComponent(lbteaid5)
                            .addComponent(lbnum8)
                            .addComponent(lbnum9))
                        .addGap(45, 45, 45)
                        .addGroup(editsubLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtsubday2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtsubday1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtsubid, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtsubname)
                            .addComponent(txtsubsem)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))))
                .addContainerGap())
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        editsubLayout.setVerticalGroup(
            editsubLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(editsubLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbtitle6)
                .addGap(43, 43, 43)
                .addGroup(editsubLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbteaid5)
                    .addComponent(txtsubid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(editsubLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtsubname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbfirst6))
                .addGap(18, 18, 18)
                .addGroup(editsubLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtsubsem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblast6))
                .addGap(18, 18, 18)
                .addGroup(editsubLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbemail6)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(editsubLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbnum8)
                    .addComponent(txtsubday1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(editsubLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbnum9)
                    .addComponent(txtsubday2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        editsgs.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        editsgs.setOpaque(false);

        lbtitle4.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        lbtitle4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbtitle4.setText("SGS Edit Menu");

        lbteaid3.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        lbteaid3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbteaid3.setText("SGS ID:");

        lbfirst4.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        lbfirst4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbfirst4.setText("Strand:");

        lblast4.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        lblast4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblast4.setText("Grade:");

        lbemail4.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        lbemail4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbemail4.setText("Section:");

        lbnum7.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        lbnum7.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbnum7.setText("Teacher Adviser:");

        txtsgsid.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        txtsgsstrand.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        txtsgsgrade.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        txtsgssection.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        txtsgsteaid.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout editsgsLayout = new javax.swing.GroupLayout(editsgs);
        editsgs.setLayout(editsgsLayout);
        editsgsLayout.setHorizontalGroup(
            editsgsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(editsgsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(editsgsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbtitle4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(editsgsLayout.createSequentialGroup()
                        .addGroup(editsgsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblast4)
                            .addComponent(lbemail4)
                            .addComponent(lbnum7)
                            .addComponent(lbfirst4)
                            .addComponent(lbteaid3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(editsgsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtsgssection, javax.swing.GroupLayout.DEFAULT_SIZE, 193, Short.MAX_VALUE)
                            .addComponent(txtsgsteaid, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtsgsstrand, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtsgsgrade, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtsgsid, javax.swing.GroupLayout.Alignment.LEADING))))
                .addContainerGap())
        );
        editsgsLayout.setVerticalGroup(
            editsgsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(editsgsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbtitle4)
                .addGap(43, 43, 43)
                .addGroup(editsgsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbteaid3)
                    .addComponent(txtsgsid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(editsgsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtsgsstrand, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbfirst4))
                .addGap(18, 18, 18)
                .addGroup(editsgsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtsgsgrade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblast4))
                .addGap(18, 18, 18)
                .addGroup(editsgsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtsgssection, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbemail4))
                .addGap(18, 18, 18)
                .addGroup(editsgsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtsgsteaid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbnum7))
                .addContainerGap(311, Short.MAX_VALUE))
        );

        editloc.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        editloc.setOpaque(false);

        lbtitle5.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        lbtitle5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbtitle5.setText("Location Edit Menu");

        lbteaid4.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        lbteaid4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbteaid4.setText("Location ID:");

        lbfirst5.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        lbfirst5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbfirst5.setText("Department:");

        lblast5.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        lblast5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblast5.setText("Room:");

        lbemail5.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        lbemail5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbemail5.setText("Description:");

        txtlocid.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        txtlocname.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        txtlocroom.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        txtlocdesc.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout editlocLayout = new javax.swing.GroupLayout(editloc);
        editloc.setLayout(editlocLayout);
        editlocLayout.setHorizontalGroup(
            editlocLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(editlocLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(editlocLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbtitle5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(editlocLayout.createSequentialGroup()
                        .addGroup(editlocLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblast5)
                            .addComponent(lbemail5)
                            .addComponent(lbfirst5)
                            .addComponent(lbteaid4))
                        .addGap(33, 33, 33)
                        .addGroup(editlocLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtlocdesc, javax.swing.GroupLayout.DEFAULT_SIZE, 189, Short.MAX_VALUE)
                            .addComponent(txtlocname, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtlocroom, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtlocid, javax.swing.GroupLayout.Alignment.LEADING))))
                .addContainerGap())
        );
        editlocLayout.setVerticalGroup(
            editlocLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(editlocLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbtitle5)
                .addGap(43, 43, 43)
                .addGroup(editlocLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbteaid4)
                    .addComponent(txtlocid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(editlocLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtlocname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbfirst5))
                .addGap(18, 18, 18)
                .addGroup(editlocLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtlocroom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblast5))
                .addGap(18, 18, 18)
                .addGroup(editlocLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtlocdesc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbemail5))
                .addContainerGap(347, Short.MAX_VALUE))
        );

        editagenda.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        editagenda.setOpaque(false);

        lbtitle3.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        lbtitle3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbtitle3.setText("Agenda Edit Menu");

        lbteaid2.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        lbteaid2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbteaid2.setText("Agenda ID:");

        lbfirst3.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        lbfirst3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbfirst3.setText("Agenda Name:");

        lblast3.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        lblast3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblast3.setText("Description:");

        lbemail3.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        lbemail3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbemail3.setText("Date:");

        lbnum6.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        lbnum6.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbnum6.setText("Repeatable:");

        txtagendaid.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        txtagendaname.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        txtagendarepeat.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "yes", "no" }));
        txtagendarepeat.setSelectedIndex(-1);

        txtagendadatemonth.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December" }));
        txtagendadatemonth.setSelectedIndex(-1);

        txtagendadateday.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));
        txtagendadateday.setSelectedIndex(-1);

        jScrollPane4.setBorder(null);

        txtagendadesc.setColumns(20);
        txtagendadesc.setLineWrap(true);
        txtagendadesc.setRows(5);
        txtagendadesc.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jScrollPane4.setViewportView(txtagendadesc);

        javax.swing.GroupLayout editagendaLayout = new javax.swing.GroupLayout(editagenda);
        editagenda.setLayout(editagendaLayout);
        editagendaLayout.setHorizontalGroup(
            editagendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(editagendaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(editagendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbtitle3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(editagendaLayout.createSequentialGroup()
                        .addGroup(editagendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblast3)
                            .addComponent(lbemail3)
                            .addComponent(lbnum6)
                            .addComponent(lbfirst3)
                            .addComponent(lbteaid2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(editagendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtagendaid, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 202, Short.MAX_VALUE)
                            .addComponent(txtagendaname, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 202, Short.MAX_VALUE)
                            .addComponent(txtagendarepeat, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 202, Short.MAX_VALUE)
                            .addGroup(editagendaLayout.createSequentialGroup()
                                .addComponent(txtagendadatemonth, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtagendadateday, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))))
                .addContainerGap())
        );
        editagendaLayout.setVerticalGroup(
            editagendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(editagendaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbtitle3)
                .addGap(43, 43, 43)
                .addGroup(editagendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbteaid2)
                    .addComponent(txtagendaid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(editagendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtagendaname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbfirst3))
                .addGap(18, 18, 18)
                .addGroup(editagendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblast3)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                .addGroup(editagendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbemail3)
                    .addComponent(txtagendadatemonth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtagendadateday, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(editagendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbnum6)
                    .addComponent(txtagendarepeat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(242, 242, 242))
        );

        editadmin.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        editadmin.setOpaque(false);

        lbtitle2.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        lbtitle2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbtitle2.setText("Administrator Info");

        lbteaid1.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        lbteaid1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbteaid1.setText("Admin ID:");

        lbfirst2.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        lbfirst2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbfirst2.setText("First Name:");

        lblast2.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        lblast2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblast2.setText("Last Name:");

        lbemail2.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        lbemail2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbemail2.setText("Email:");

        lbnum2.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        lbnum2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbnum2.setText("Contact Number:");

        txtadminid.setEditable(false);
        txtadminid.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        txtadminfirst.setEditable(false);
        txtadminfirst.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        txtadminlast.setEditable(false);
        txtadminlast.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        txtadminemail.setEditable(false);
        txtadminemail.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        txtadminnum.setEditable(false);
        txtadminnum.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        labelbottom2.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        labelbottom2.setText("*Note: You cannot edit the admin's info nor add an admin.");

        txtadminjob.setEditable(false);
        txtadminjob.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lbnum3.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        lbnum3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbnum3.setText("Job:");

        txtadminjobloc.setEditable(false);
        txtadminjobloc.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lbnum4.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        lbnum4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbnum4.setText("Job Location:");

        lbnum5.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        lbnum5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbnum5.setText("Job Description:");

        jScrollPane1.setBorder(null);

        txtadminjobdesc.setEditable(false);
        txtadminjobdesc.setColumns(20);
        txtadminjobdesc.setLineWrap(true);
        txtadminjobdesc.setRows(5);
        txtadminjobdesc.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtadminjobdesc.setFocusable(false);
        jScrollPane1.setViewportView(txtadminjobdesc);

        javax.swing.GroupLayout editadminLayout = new javax.swing.GroupLayout(editadmin);
        editadmin.setLayout(editadminLayout);
        editadminLayout.setHorizontalGroup(
            editadminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(editadminLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(editadminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbtitle2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(editadminLayout.createSequentialGroup()
                        .addGroup(editadminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblast2)
                            .addComponent(lbemail2)
                            .addComponent(lbnum2)
                            .addComponent(lbfirst2)
                            .addComponent(lbteaid1)
                            .addComponent(lbnum3)
                            .addComponent(lbnum4)
                            .addComponent(lbnum5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(editadminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtadminemail, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtadminnum)
                            .addComponent(txtadminid, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtadminfirst)
                            .addComponent(txtadminlast)
                            .addComponent(txtadminjob)
                            .addComponent(txtadminjobloc)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                    .addGroup(editadminLayout.createSequentialGroup()
                        .addComponent(labelbottom2)
                        .addGap(0, 38, Short.MAX_VALUE)))
                .addContainerGap())
        );
        editadminLayout.setVerticalGroup(
            editadminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(editadminLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbtitle2)
                .addGap(43, 43, 43)
                .addGroup(editadminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbteaid1)
                    .addComponent(txtadminid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(editadminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtadminfirst, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbfirst2))
                .addGap(18, 18, 18)
                .addGroup(editadminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtadminlast, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblast2))
                .addGap(18, 18, 18)
                .addGroup(editadminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtadminemail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbemail2))
                .addGap(18, 18, 18)
                .addGroup(editadminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtadminnum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbnum2))
                .addGap(18, 18, 18)
                .addGroup(editadminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtadminjob, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbnum3))
                .addGap(18, 18, 18)
                .addGroup(editadminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtadminjobloc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbnum4))
                .addGap(18, 18, 18)
                .addGroup(editadminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbnum5)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 119, Short.MAX_VALUE)
                .addComponent(labelbottom2)
                .addContainerGap())
        );

        edittea.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        edittea.setOpaque(false);

        lbtitle1.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        lbtitle1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbtitle1.setText("Teacher Info");

        lbteaid.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        lbteaid.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbteaid.setText("Teacher ID:");

        lbfirst1.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        lbfirst1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbfirst1.setText("First Name:");

        lblast1.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        lblast1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblast1.setText("Last Name:");

        lbemail1.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        lbemail1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbemail1.setText("Email:");

        lbnum1.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        lbnum1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbnum1.setText("Contact Number:");

        txtteaid.setEditable(false);
        txtteaid.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        txtteafirst.setEditable(false);
        txtteafirst.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        txttealast.setEditable(false);
        txttealast.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        txtteaemail.setEditable(false);
        txtteaemail.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        txtteanum.setEditable(false);
        txtteanum.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        labelbottom1.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        labelbottom1.setText("*Note: You cannot edit the teacher's info nor add a teacher.");

        javax.swing.GroupLayout editteaLayout = new javax.swing.GroupLayout(edittea);
        edittea.setLayout(editteaLayout);
        editteaLayout.setHorizontalGroup(
            editteaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(editteaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(editteaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbtitle1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(editteaLayout.createSequentialGroup()
                        .addGroup(editteaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblast1)
                            .addComponent(lbemail1)
                            .addComponent(lbnum1)
                            .addComponent(lbfirst1)
                            .addComponent(lbteaid))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(editteaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtteaemail)
                            .addComponent(txtteanum, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtteaid)
                            .addComponent(txtteafirst, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txttealast, javax.swing.GroupLayout.Alignment.LEADING)))
                    .addGroup(editteaLayout.createSequentialGroup()
                        .addComponent(labelbottom1)
                        .addGap(0, 36, Short.MAX_VALUE)))
                .addContainerGap())
        );
        editteaLayout.setVerticalGroup(
            editteaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(editteaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbtitle1)
                .addGap(43, 43, 43)
                .addGroup(editteaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbteaid)
                    .addComponent(txtteaid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(editteaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtteafirst, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbfirst1))
                .addGap(18, 18, 18)
                .addGroup(editteaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txttealast, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblast1))
                .addGap(18, 18, 18)
                .addGroup(editteaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtteaemail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbemail1))
                .addGap(18, 18, 18)
                .addGroup(editteaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtteanum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbnum1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 291, Short.MAX_VALUE)
                .addComponent(labelbottom1)
                .addContainerGap())
        );

        editstud.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        editstud.setOpaque(false);

        lbtitle.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        lbtitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbtitle.setText("Student Info");

        lbstudid.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        lbstudid.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbstudid.setText("Student ID:");

        lbfirst.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        lbfirst.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbfirst.setText("First Name:");

        lblast.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        lblast.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblast.setText("Last Name:");

        lbemail.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        lbemail.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbemail.setText("Email:");

        lbnum.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        lbnum.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbnum.setText("Contact Number:");

        lbsgs.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        lbsgs.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbsgs.setText("SGS ID:");

        txtstudid.setEditable(false);
        txtstudid.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        txtstudfirst.setEditable(false);
        txtstudfirst.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        txtstudlast.setEditable(false);
        txtstudlast.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        txtstudemail.setEditable(false);
        txtstudemail.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        txtstudnum.setEditable(false);
        txtstudnum.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        txtstudsgs.setEditable(false);
        txtstudsgs.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        labelbottom.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        labelbottom.setText("*Note: You cannot edit the student's info nor add a student.");

        javax.swing.GroupLayout editstudLayout = new javax.swing.GroupLayout(editstud);
        editstud.setLayout(editstudLayout);
        editstudLayout.setHorizontalGroup(
            editstudLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(editstudLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(editstudLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbtitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(editstudLayout.createSequentialGroup()
                        .addGroup(editstudLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblast)
                            .addComponent(lbemail)
                            .addComponent(lbnum)
                            .addComponent(lbfirst)
                            .addComponent(lbstudid)
                            .addComponent(lbsgs))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(editstudLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtstudemail)
                            .addComponent(txtstudnum, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtstudid)
                            .addComponent(txtstudfirst, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtstudlast, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtstudsgs, javax.swing.GroupLayout.Alignment.LEADING)))
                    .addGroup(editstudLayout.createSequentialGroup()
                        .addComponent(labelbottom)
                        .addGap(0, 34, Short.MAX_VALUE)))
                .addContainerGap())
        );
        editstudLayout.setVerticalGroup(
            editstudLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(editstudLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbtitle)
                .addGap(43, 43, 43)
                .addGroup(editstudLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbstudid)
                    .addComponent(txtstudid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(editstudLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtstudfirst, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbfirst))
                .addGap(18, 18, 18)
                .addGroup(editstudLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtstudlast, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblast))
                .addGap(18, 18, 18)
                .addGroup(editstudLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtstudemail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbemail))
                .addGap(18, 18, 18)
                .addGroup(editstudLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtstudnum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbnum))
                .addGap(18, 18, 18)
                .addGroup(editstudLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtstudsgs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbsgs))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 255, Short.MAX_VALUE)
                .addComponent(labelbottom)
                .addContainerGap())
        );

        editLayeredPane.setLayer(editsub, javax.swing.JLayeredPane.DEFAULT_LAYER);
        editLayeredPane.setLayer(editsgs, javax.swing.JLayeredPane.DEFAULT_LAYER);
        editLayeredPane.setLayer(editloc, javax.swing.JLayeredPane.DEFAULT_LAYER);
        editLayeredPane.setLayer(editagenda, javax.swing.JLayeredPane.DEFAULT_LAYER);
        editLayeredPane.setLayer(editadmin, javax.swing.JLayeredPane.DEFAULT_LAYER);
        editLayeredPane.setLayer(edittea, javax.swing.JLayeredPane.DEFAULT_LAYER);
        editLayeredPane.setLayer(editstud, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout editLayeredPaneLayout = new javax.swing.GroupLayout(editLayeredPane);
        editLayeredPane.setLayout(editLayeredPaneLayout);
        editLayeredPaneLayout.setHorizontalGroup(
            editLayeredPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(editsub, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(editadmin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(editloc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(editagenda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(editsgs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(edittea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(editstud, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        editLayeredPaneLayout.setVerticalGroup(
            editLayeredPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(editsub, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(editadmin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(editloc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(editagenda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(editsgs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(edittea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(editstud, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        tablesortPanel.setOpaque(false);

        lbshowtable.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        lbshowtable.setText("Show Table:");

        showtable.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Student", "Teacher", "Administrator", "SGS", "Location", "Agenda", "Subject" }));
        showtable.setHint("table");

        sortby.setHint("sort by");

        lbsortby.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        lbsortby.setText("Sort By:");

        buttonLayeredPane.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        userbuttonpanel.setOpaque(false);

        removebutton.setBackground(new java.awt.Color(255, 102, 102));
        removebutton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        removebutton.setText("Remove the selected student?");
        removebutton.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        removebutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removebuttonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout userbuttonpanelLayout = new javax.swing.GroupLayout(userbuttonpanel);
        userbuttonpanel.setLayout(userbuttonpanelLayout);
        userbuttonpanelLayout.setHorizontalGroup(
            userbuttonpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, userbuttonpanelLayout.createSequentialGroup()
                .addContainerGap(39, Short.MAX_VALUE)
                .addComponent(removebutton, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33))
        );
        userbuttonpanelLayout.setVerticalGroup(
            userbuttonpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(userbuttonpanelLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(removebutton, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(61, Short.MAX_VALUE))
        );

        buttonLayeredPane.add(userbuttonpanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 257, 106));

        agendabuttonpanel.setOpaque(false);

        agendaremovebutton.setBackground(new java.awt.Color(255, 102, 102));
        agendaremovebutton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        agendaremovebutton.setText("Remove");
        agendaremovebutton.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        agendaremovebutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agendaremovebuttonActionPerformed(evt);
            }
        });

        agendaaddbutton.setBackground(new java.awt.Color(153, 255, 153));
        agendaaddbutton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        agendaaddbutton.setText("Add");
        agendaaddbutton.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        agendaaddbutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agendaaddbuttonActionPerformed(evt);
            }
        });

        agendaupdatebutton.setBackground(new java.awt.Color(255, 255, 153));
        agendaupdatebutton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        agendaupdatebutton.setText("Update");
        agendaupdatebutton.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        agendaupdatebutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agendaupdatebuttonActionPerformed(evt);
            }
        });

        agendarefreshbutton.setBackground(new java.awt.Color(153, 255, 204));
        agendarefreshbutton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        agendarefreshbutton.setText("Refresh");
        agendarefreshbutton.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        agendarefreshbutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agendarefreshbuttonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout agendabuttonpanelLayout = new javax.swing.GroupLayout(agendabuttonpanel);
        agendabuttonpanel.setLayout(agendabuttonpanelLayout);
        agendabuttonpanelLayout.setHorizontalGroup(
            agendabuttonpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(agendabuttonpanelLayout.createSequentialGroup()
                .addGroup(agendabuttonpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(agendabuttonpanelLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(agendaaddbutton, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(agendaupdatebutton, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(agendaremovebutton, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(agendabuttonpanelLayout.createSequentialGroup()
                        .addGap(64, 64, 64)
                        .addComponent(agendarefreshbutton, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(13, Short.MAX_VALUE))
        );
        agendabuttonpanelLayout.setVerticalGroup(
            agendabuttonpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(agendabuttonpanelLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(agendabuttonpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(agendaaddbutton, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(agendabuttonpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(agendaupdatebutton, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(agendaremovebutton, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(agendarefreshbutton, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        buttonLayeredPane.add(agendabuttonpanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 257, 106));

        sgsbuttonpanel.setOpaque(false);

        sgsremovebutton.setBackground(new java.awt.Color(255, 102, 102));
        sgsremovebutton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        sgsremovebutton.setText("Remove");
        sgsremovebutton.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        sgsremovebutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sgsremovebuttonActionPerformed(evt);
            }
        });

        sgsaddbutton.setBackground(new java.awt.Color(153, 255, 153));
        sgsaddbutton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        sgsaddbutton.setText("Add");
        sgsaddbutton.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        sgsaddbutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sgsaddbuttonActionPerformed(evt);
            }
        });

        sgsupdatebutton.setBackground(new java.awt.Color(255, 255, 153));
        sgsupdatebutton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        sgsupdatebutton.setText("Update");
        sgsupdatebutton.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        sgsupdatebutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sgsupdatebuttonActionPerformed(evt);
            }
        });

        sgsrefreshbutton.setBackground(new java.awt.Color(153, 255, 204));
        sgsrefreshbutton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        sgsrefreshbutton.setText("Refresh");
        sgsrefreshbutton.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        sgsrefreshbutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sgsrefreshbuttonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout sgsbuttonpanelLayout = new javax.swing.GroupLayout(sgsbuttonpanel);
        sgsbuttonpanel.setLayout(sgsbuttonpanelLayout);
        sgsbuttonpanelLayout.setHorizontalGroup(
            sgsbuttonpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, sgsbuttonpanelLayout.createSequentialGroup()
                .addContainerGap(9, Short.MAX_VALUE)
                .addComponent(sgsaddbutton, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(sgsupdatebutton, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(sgsremovebutton, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14))
            .addGroup(sgsbuttonpanelLayout.createSequentialGroup()
                .addGap(65, 65, 65)
                .addComponent(sgsrefreshbutton, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        sgsbuttonpanelLayout.setVerticalGroup(
            sgsbuttonpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sgsbuttonpanelLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(sgsbuttonpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(sgsremovebutton, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sgsupdatebutton, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sgsaddbutton, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(sgsrefreshbutton, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        buttonLayeredPane.add(sgsbuttonpanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 257, 106));

        locbuttonpanel.setOpaque(false);

        locaddbutton.setBackground(new java.awt.Color(153, 255, 153));
        locaddbutton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        locaddbutton.setText("Add");
        locaddbutton.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        locaddbutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                locaddbuttonActionPerformed(evt);
            }
        });

        locupdatebutton.setBackground(new java.awt.Color(255, 255, 153));
        locupdatebutton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        locupdatebutton.setText("Update");
        locupdatebutton.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        locupdatebutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                locupdatebuttonActionPerformed(evt);
            }
        });

        locremovebutton.setBackground(new java.awt.Color(255, 102, 102));
        locremovebutton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        locremovebutton.setText("Remove");
        locremovebutton.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        locremovebutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                locremovebuttonActionPerformed(evt);
            }
        });

        locrefreshbutton.setBackground(new java.awt.Color(153, 255, 204));
        locrefreshbutton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        locrefreshbutton.setText("Refresh");
        locrefreshbutton.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        locrefreshbutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                locrefreshbuttonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout locbuttonpanelLayout = new javax.swing.GroupLayout(locbuttonpanel);
        locbuttonpanel.setLayout(locbuttonpanelLayout);
        locbuttonpanelLayout.setHorizontalGroup(
            locbuttonpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 257, Short.MAX_VALUE)
            .addGroup(locbuttonpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(locbuttonpanelLayout.createSequentialGroup()
                    .addGap(11, 11, 11)
                    .addGroup(locbuttonpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, locbuttonpanelLayout.createSequentialGroup()
                            .addComponent(locaddbutton, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(locupdatebutton, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(locremovebutton, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(locbuttonpanelLayout.createSequentialGroup()
                            .addGap(56, 56, 56)
                            .addComponent(locrefreshbutton, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 52, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addContainerGap(12, Short.MAX_VALUE)))
        );
        locbuttonpanelLayout.setVerticalGroup(
            locbuttonpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 106, Short.MAX_VALUE)
            .addGroup(locbuttonpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(locbuttonpanelLayout.createSequentialGroup()
                    .addGap(19, 19, 19)
                    .addGroup(locbuttonpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(locremovebutton, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(locupdatebutton, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(locaddbutton, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(18, 18, 18)
                    .addComponent(locrefreshbutton, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(19, Short.MAX_VALUE)))
        );

        buttonLayeredPane.add(locbuttonpanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 257, 106));

        subbuttonpanel.setOpaque(false);

        subaddbutton.setBackground(new java.awt.Color(153, 255, 153));
        subaddbutton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        subaddbutton.setText("Add");
        subaddbutton.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        subaddbutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subaddbuttonActionPerformed(evt);
            }
        });

        subupdatebutton.setBackground(new java.awt.Color(255, 255, 153));
        subupdatebutton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        subupdatebutton.setText("Update");
        subupdatebutton.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        subupdatebutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subupdatebuttonActionPerformed(evt);
            }
        });

        subremovebutton.setBackground(new java.awt.Color(255, 102, 102));
        subremovebutton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        subremovebutton.setText("Remove");
        subremovebutton.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        subremovebutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subremovebuttonActionPerformed(evt);
            }
        });

        subrefreshbutton.setBackground(new java.awt.Color(153, 255, 204));
        subrefreshbutton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        subrefreshbutton.setText("Refresh");
        subrefreshbutton.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        subrefreshbutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subrefreshbuttonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout subbuttonpanelLayout = new javax.swing.GroupLayout(subbuttonpanel);
        subbuttonpanel.setLayout(subbuttonpanelLayout);
        subbuttonpanelLayout.setHorizontalGroup(
            subbuttonpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, subbuttonpanelLayout.createSequentialGroup()
                .addContainerGap(9, Short.MAX_VALUE)
                .addComponent(subaddbutton, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(subupdatebutton, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(subremovebutton, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14))
            .addGroup(subbuttonpanelLayout.createSequentialGroup()
                .addGap(65, 65, 65)
                .addComponent(subrefreshbutton, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        subbuttonpanelLayout.setVerticalGroup(
            subbuttonpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(subbuttonpanelLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(subbuttonpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(subremovebutton, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(subupdatebutton, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(subaddbutton, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(subrefreshbutton, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        buttonLayeredPane.add(subbuttonpanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 257, 106));

        javax.swing.GroupLayout tablesortPanelLayout = new javax.swing.GroupLayout(tablesortPanel);
        tablesortPanel.setLayout(tablesortPanelLayout);
        tablesortPanelLayout.setHorizontalGroup(
            tablesortPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tablesortPanelLayout.createSequentialGroup()
                .addContainerGap(10, Short.MAX_VALUE)
                .addGroup(tablesortPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(buttonLayeredPane)
                    .addGroup(tablesortPanelLayout.createSequentialGroup()
                        .addGroup(tablesortPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbshowtable, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lbsortby, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(tablesortPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(showtable, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(sortby, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        tablesortPanelLayout.setVerticalGroup(
            tablesortPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tablesortPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tablesortPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(showtable, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbshowtable))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(tablesortPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(sortby, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbsortby))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(buttonLayeredPane)
                .addContainerGap())
        );

        otherOptionPanel.setOpaque(false);

        txt.setEditable(false);
        txt.setForeground(new java.awt.Color(133, 133, 133));
        txt.setText("▫ The SGS ID corresponds to the SGS ID in the SGS table. ▫ The SGS ID can be nulled by leaving its text field blank. ▫ Note that you cannot add or edit a student but you can remove them.");

        javax.swing.GroupLayout otherOptionPanelLayout = new javax.swing.GroupLayout(otherOptionPanel);
        otherOptionPanel.setLayout(otherOptionPanelLayout);
        otherOptionPanelLayout.setHorizontalGroup(
            otherOptionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(otherOptionPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txt, javax.swing.GroupLayout.DEFAULT_SIZE, 303, Short.MAX_VALUE)
                .addContainerGap())
        );
        otherOptionPanelLayout.setVerticalGroup(
            otherOptionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(otherOptionPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txt, javax.swing.GroupLayout.DEFAULT_SIZE, 174, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(editLayeredPane)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tableLayeredPane)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(otherOptionPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(tablesortPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(editLayeredPane)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(tableLayeredPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tablesortPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(otherOptionPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents
    // <editor-fold defaultstate="collapsed" desc="Button Codes">  
    private void removebuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removebuttonActionPerformed
        popup pop = new popup();
        if(showtable.getSelectedIndex() == 0){
            if(q == -1){
                notselectedpopup(pop);
            } else {
                pop.txt.setText("Decide carefully whether to remove this student. Removing this student means you're kicking him/her on your school or just deleting a record of a student. This cannot be undone.");
                pop.title.setText("Remove the selected student?");
                pop.eventOK(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        buttonstudedit();
                        GlassPanePopup.closePopupLast();
                    }
                });
                GlassPanePopup.showPopup(pop);
            }
        } else if (showtable.getSelectedIndex() == 1){
            if(q == -1){
                notselectedpopup(pop);
            } else {
                pop.txt.setText("Decide carefully whether to remove this teacher. Removing this teacher means you're kicking him/her on your school or just deleting a record of a teacher. This cannot be undone.");
                pop.title.setText("Remove the selected teacher?");
                pop.eventOK(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        buttonteaedit();
                        GlassPanePopup.closePopupLast();
                    }
                });
                GlassPanePopup.showPopup(pop);
            }
        } else if(showtable.getSelectedIndex() == 2){
            if(q == -1){
                notselectedpopup(pop);
            } else {
                pop.txt.setText("Decide carefully whether to remove this administrator. Removing this administrator means you're kicking him/her on your school or just deleting a record of a administrator. This cannot be undone.");
                pop.title.setText("Remove the selected administrator?");
                pop.eventOK(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        buttonadminedit();
                        GlassPanePopup.closePopupLast();
                    }
                });
                GlassPanePopup.showPopup(pop);
            }
        }
    }//GEN-LAST:event_removebuttonActionPerformed
    private void agendaremovebuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agendaremovebuttonActionPerformed
        popup pop = new popup();
        if(q == -1){
            notselectedpopup(pop);
        } else {
            pop.txt.setText("Just reminding you if you ever change your mind. You cannot turn off this feature because I was programmed this way");
            pop.title.setText("Remove the selected event?");
            pop.eventOK(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    buttonagendaremove();
                    GlassPanePopup.closePopupLast();
                }
            });
            GlassPanePopup.showPopup(pop);
        }
    }//GEN-LAST:event_agendaremovebuttonActionPerformed
    private void agendaaddbuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agendaaddbuttonActionPerformed
        popup pop = new popup();
        if(txtagendaname.getText().trim().equals("") || txtagendaid.getText().trim().equals("") || txtagendadesc.getText().trim().equals("")
                || txtagendadatemonth.getSelectedIndex() == -1 || txtagendadateday.getSelectedIndex() == -1 || txtagendarepeat.getSelectedIndex() == -1){
            notfilledpopup(pop);
        } else {
            buttonagendaadd();
            pop.txt.setText("You just added a new event with no worries.");
            pop.title.setText("Added event successfully!");
            pop.eventOK(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    GlassPanePopup.closePopupLast();
                }
            });
            GlassPanePopup.showPopup(pop);
        }
    }//GEN-LAST:event_agendaaddbuttonActionPerformed
    private void agendaupdatebuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agendaupdatebuttonActionPerformed
        popup pop = new popup();
        if(txtagendaname.getText().trim().equals("") || txtagendaid.getText().trim().equals("") || txtagendadesc.getText().trim().equals("")
                || txtagendadatemonth.getSelectedIndex() == -1 || txtagendadateday.getSelectedIndex() == -1 || txtagendarepeat.getSelectedIndex() == -1){
            notfilledpopup(pop);
        } else {
            buttonagendaupdate();
        }
    }//GEN-LAST:event_agendaupdatebuttonActionPerformed
    private void agendarefreshbuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agendarefreshbuttonActionPerformed
        hideall();
        agendatablepanel.setVisible(true);
        editagenda.setVisible(true);
        agendabuttonpanel.setVisible(true);
        defaultAgenda();
        sortAgenda();
    }//GEN-LAST:event_agendarefreshbuttonActionPerformed
    private void sgsremovebuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sgsremovebuttonActionPerformed
        popup pop = new popup();
        if(q == -1){
            notselectedpopup(pop);
        } else {
            pop.txt.setText("Just reminding you if you ever change your mind. You cannot turn off this feature because I was programmed this way");
            pop.title.setText("Remove the selected sgs?");
            pop.eventOK(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    buttonsgsremove();
                    GlassPanePopup.closePopupLast();
                }
            });
            GlassPanePopup.showPopup(pop);
        }
    }//GEN-LAST:event_sgsremovebuttonActionPerformed
    private void sgsaddbuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sgsaddbuttonActionPerformed
        popup pop = new popup();
        if(txtsgsstrand.getText().trim().equals("") || txtsgsid.getText().trim().equals("") || txtsgsgrade.getText().trim().equals("")
                || txtsgssection.getText().trim().equals("")){
            notfilledpopup(pop);
        } else {
            buttonsgsadd();
            pop.txt.setText("You just added a new SGS with no worries.");
            pop.title.setText("Added SGS successfully!");
            pop.eventOK(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    GlassPanePopup.closePopupLast();
                }
            });
            GlassPanePopup.showPopup(pop);
        }
    }//GEN-LAST:event_sgsaddbuttonActionPerformed
    private void sgsupdatebuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sgsupdatebuttonActionPerformed
        popup pop = new popup();
        if(txtsgsstrand.getText().trim().equals("") || txtsgsid.getText().trim().equals("") || txtsgsgrade.getText().trim().equals("")
                || txtsgssection.getText().trim().equals("")){
            notfilledpopup(pop);
        } else {
            buttonsgsupdate();
        }
    }//GEN-LAST:event_sgsupdatebuttonActionPerformed
    private void sgsrefreshbuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sgsrefreshbuttonActionPerformed
        hideall();
        sgstablepanel.setVisible(true);
        editsgs.setVisible(true);
        sgsbuttonpanel.setVisible(true);
        defaultSgs();
        sortSgs();
    }//GEN-LAST:event_sgsrefreshbuttonActionPerformed
    private void locaddbuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_locaddbuttonActionPerformed
        popup pop = new popup();
        if(txtlocname.getText().trim().equals("") || txtlocid.getText().trim().equals("") || txtlocroom.getText().trim().equals("")){
            notfilledpopup(pop);
        } else {
            buttonlocadd();
            pop.txt.setText("You just added a new location with no worries.");
            pop.title.setText("Added location successfully!");
            pop.eventOK(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    GlassPanePopup.closePopupLast();
                }
            });
            GlassPanePopup.showPopup(pop);
        }
    }//GEN-LAST:event_locaddbuttonActionPerformed
    private void locupdatebuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_locupdatebuttonActionPerformed
        popup pop = new popup();
        if(txtlocname.getText().trim().equals("") || txtlocid.getText().trim().equals("") || txtlocroom.getText().trim().equals("")){
            notfilledpopup(pop);
        } else {
            buttonlocupdate();
        }
    }//GEN-LAST:event_locupdatebuttonActionPerformed
    private void locremovebuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_locremovebuttonActionPerformed
        popup pop = new popup();
        if(q == -1){
            notselectedpopup(pop);
        } else {
            pop.txt.setText("Just reminding you if you ever change your mind. You cannot turn off this feature because I was programmed this way");
            pop.title.setText("Remove the selected location?");
            pop.eventOK(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    buttonlocremove();
                    GlassPanePopup.closePopupLast();
                }
            });
            GlassPanePopup.showPopup(pop);
        }
    }//GEN-LAST:event_locremovebuttonActionPerformed
    private void locrefreshbuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_locrefreshbuttonActionPerformed
        hideall();
        loctablepanel.setVisible(true);
        editloc.setVisible(true);
        locbuttonpanel.setVisible(true);
        defaultLocation();
        sortLocation();
    }//GEN-LAST:event_locrefreshbuttonActionPerformed
    private void subaddbuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subaddbuttonActionPerformed
        popup pop = new popup();
        if(txtsubname.getText().trim().equals("") || txtsubid.getText().trim().equals("") || txtsubsem.getText().trim().equals("")
                || txtsubdesc.getText().trim().equals("") || txtsubhour1.getText().trim().equals("") || txtsubmin1.getText().trim().equals("")
                || txtsubhour2.getText().trim().equals("") || txtsubmin2.getText().trim().equals("") || txtsubday1.getSelectedIndex() == -1){
            notfilledpopup(pop);
        } else {
            buttonsubadd();
            pop.txt.setText("You just added a new subject with no worries.");
            pop.title.setText("Added subject successfully!");
            pop.eventOK(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    GlassPanePopup.closePopupLast();
                }
            });
            GlassPanePopup.showPopup(pop);
        }
    }//GEN-LAST:event_subaddbuttonActionPerformed
    private void subupdatebuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subupdatebuttonActionPerformed
        popup pop = new popup();
        if(txtsubname.getText().trim().equals("") || txtsubid.getText().trim().equals("") || txtsubsem.getText().trim().equals("")
                || txtsubdesc.getText().trim().equals("") || txtsubhour1.getText().trim().equals("") || txtsubmin1.getText().trim().equals("")
                || txtsubhour2.getText().trim().equals("") || txtsubmin2.getText().trim().equals("") || txtsubday1.getSelectedIndex() == -1){
            notfilledpopup(pop);
        } else {
            buttonsubupdate();
        }
    }//GEN-LAST:event_subupdatebuttonActionPerformed
    private void subremovebuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subremovebuttonActionPerformed
        popup pop = new popup();
        if(q == -1){
            notselectedpopup(pop);
        } else {
            pop.txt.setText("Just reminding you if you ever change your mind. You cannot turn off this feature because I was programmed this way");
            pop.title.setText("Remove the selected subject?");
            pop.eventOK(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    buttonsubremove();
                    GlassPanePopup.closePopupLast();
                }
            });
            GlassPanePopup.showPopup(pop);
        }
    }//GEN-LAST:event_subremovebuttonActionPerformed
    private void subrefreshbuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subrefreshbuttonActionPerformed
        hideall();
        subtablepanel.setVisible(true);
        editsub.setVisible(true);
        subbuttonpanel.setVisible(true);
        defaultSubject();
        sortSubject();
    }//GEN-LAST:event_subrefreshbuttonActionPerformed

    private void buttonstudedit(){
        try {
            String id = String.valueOf(studtable.getValueAt(q, 0));
            String query = "DELETE FROM message WHERE send_stud=? OR rec_stud=?";
            String finalquery = "DELETE FROM student WHERE stud_id=?";
            PreparedStatement ps = dbconn.prepareStatement(query);
            ps.setString(1, id);
            ps.setString(2, id);
            ps.executeUpdate();
            
            PreparedStatement ps1 = dbconn.prepareStatement(finalquery);
            ps1.setString(1, id);
            ps1.executeUpdate();
            
            hideall();
            studtablepanel.setVisible(true);
            editstud.setVisible(true);
            userbuttonpanel.setVisible(true);
            defaultStudent();
            sortStudent();
            ps.close();
            ps1.close();
        } catch (SQLException ex) {
            Logger.getLogger(Admin_Form.class.getName()).log(Level.SEVERE, null, ex);
            databaseErrorMessage();
        }
    }
    private void buttonteaedit(){
        try {
            String id = String.valueOf(teatable.getValueAt(q, 0));
            String query = "DELETE FROM message WHERE send_tea=? OR rec_tea=?";
            String subquery = "UPDATE subject SET tea_id=NULL WHERE tea_id=?";
            String sgsquery = "UPDATE sgs SET teacher_adviser=NULL WHERE teacher_adviser=?";
            String finalquery = "DELETE FROM teacher WHERE tea_id=?";
            
            PreparedStatement ps = dbconn.prepareStatement(query);
            ps.setString(1, id);
            ps.setString(2, id);
            ps.executeUpdate();
            
            PreparedStatement ps1 = dbconn.prepareStatement(subquery);
            ps1.setString(1, id);
            ps1.executeUpdate();
            
            PreparedStatement ps2 = dbconn.prepareStatement(sgsquery);
            ps2.setString(1, id);
            ps2.executeUpdate();
            
            PreparedStatement ps3 = dbconn.prepareStatement(finalquery);
            ps3.setString(1, id);
            ps3.executeUpdate();
            
            hideall();
            teatablepanel.setVisible(true);
            edittea.setVisible(true);
            userbuttonpanel.setVisible(true);
            defaultTeacher();
            sortTeacher();
            ps.close();
            ps1.close();
            ps2.close();
            ps3.close();
        } catch (SQLException ex) {
            Logger.getLogger(Admin_Form.class.getName()).log(Level.SEVERE, null, ex);
            databaseErrorMessage();
        }
    }
    private void buttonadminedit(){
        try {
            String id = String.valueOf(admintable.getValueAt(q, 0));
            String query = "DELETE FROM message WHERE send_admin=? OR rec_admin=?";
            String finalquery = "DELETE FROM admin WHERE admin_id=?";
            PreparedStatement ps = dbconn.prepareStatement(query);
            ps.setString(1, id);
            ps.setString(2, id);
            ps.executeUpdate();
            
            PreparedStatement ps1 = dbconn.prepareStatement(finalquery);
            ps1.setString(1, id);
            ps1.executeUpdate();
            
            hideall();
            admintablepanel.setVisible(true);
            editadmin.setVisible(true);
            userbuttonpanel.setVisible(true);
            defaultAdmin();
            sortAdmin();
            ps.close();
            ps1.close();
        } catch (SQLException ex) {
            Logger.getLogger(Admin_Form.class.getName()).log(Level.SEVERE, null, ex);
            databaseErrorMessage();
        }
    }
    private void buttonagendaadd(){
        try {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date());
            String id = txtagendaid.getText();
            String name = txtagendaname.getText();
            String desc = txtagendadesc.getText();
            String repeat = String.valueOf(txtagendarepeat.getSelectedItem());
            String year = String.valueOf(calendar.get(Calendar.YEAR));
            String day = convertSingleDigit(Integer.parseInt(String.valueOf(txtagendadateday.getSelectedItem())));
            String month = convertSingleDigit(txtagendadatemonth.getSelectedIndex() + 1);
            String date = year + "-" + month + "-" + day;
            
            String query = "INSERT INTO agenda(agenda_id,name,description,date,repeatable) VALUES (?,?,?,?,?)";
            PreparedStatement ps = dbconn.prepareStatement(query);
            ps.setString(1, id);
            ps.setString(2, name);
            ps.setString(3, desc);
            ps.setString(4, date);
            ps.setString(5, repeat);
            ps.executeUpdate();
            
            hideall();
            agendatablepanel.setVisible(true);
            editagenda.setVisible(true);
            agendabuttonpanel.setVisible(true);
            defaultAgenda();
            sortAgenda();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(Admin_Form.class.getName()).log(Level.SEVERE, null, ex);
            databaseErrorMessage();
        }
    }
    private void buttonagendaremove(){
        try {
            String id = String.valueOf(agendatable.getValueAt(q, 0));
            String query = "DELETE FROM agenda WHERE agenda_id=?";
            PreparedStatement ps = dbconn.prepareStatement(query);
            ps.setString(1, id);
            ps.executeUpdate();
            
            hideall();
            agendatablepanel.setVisible(true);
            editagenda.setVisible(true);
            agendabuttonpanel.setVisible(true);
            defaultAgenda();
            sortAgenda();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(Admin_Form.class.getName()).log(Level.SEVERE, null, ex);
            databaseErrorMessage();
        }
    }
    private void buttonagendaupdate(){
        try {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date());
            String oldid = String.valueOf(agendatable.getValueAt(q, 0));
            String id = txtagendaid.getText();
            String name = txtagendaname.getText();
            String desc = txtagendadesc.getText();
            String repeat = String.valueOf(txtagendarepeat.getSelectedItem());
            String year = String.valueOf(calendar.get(Calendar.YEAR));
            String day = convertSingleDigit(Integer.parseInt(String.valueOf(txtagendadateday.getSelectedItem())));
            String month = convertSingleDigit(txtagendadatemonth.getSelectedIndex() + 1);
            String date = year + "-" + month + "-" + day;
            
            String query = "UPDATE agenda SET agenda_id=?, name=?, description=?, date=?, repeatable=? WHERE agenda_id=?";
            PreparedStatement ps = dbconn.prepareStatement(query);
            ps.setString(1, id);
            ps.setString(2, name);
            ps.setString(3, desc);
            ps.setString(4, date);
            ps.setString(5, repeat);
            ps.setString(6, oldid);
            ps.executeUpdate();
            
            hideall();
            agendatablepanel.setVisible(true);
            editagenda.setVisible(true);
            agendabuttonpanel.setVisible(true);
            defaultAgenda();
            sortAgenda();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(Admin_Form.class.getName()).log(Level.SEVERE, null, ex);
            databaseErrorMessage();
        }
    }
    private void buttonsgsadd(){
        try {
            String id = txtsgsid.getText();
            String section = txtsgssection.getText();
            String grade = txtsgsgrade.getText();
            String strand = txtsgsstrand.getText();
            String tea_id = txtsgsteaid.getText().trim();

            String query = "INSERT INTO sgs(sgs_id,strand,grade,section,teacher_adviser) VALUES (?,?,?,?,?)";
            PreparedStatement ps = dbconn.prepareStatement(query);
            ps.setString(1, id);
            ps.setString(2, strand);
            ps.setString(3, grade);
            ps.setString(4, section);
            if (tea_id.equals("")) {
                ps.setNull(5, Types.INTEGER);
            } else {
                ps.setString(5, tea_id);
            }
            ps.executeUpdate();

            hideall();
            sgstablepanel.setVisible(true);
            editsgs.setVisible(true);
            sgsbuttonpanel.setVisible(true);
            defaultSgs();
            sortSgs();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(Admin_Form.class.getName()).log(Level.SEVERE, null, ex);
            databaseErrorMessage();
        }

    }
    private void buttonsgsupdate(){
        try {
            String oldid = String.valueOf(sgstable.getValueAt(q, 0));
            String id = txtsgsid.getText();
            String section = txtsgssection.getText();
            String grade = txtsgsgrade.getText();
            String strand = txtsgsstrand.getText();
            String tea_id = txtsgsteaid.getText().trim();

            String query = "UPDATE sgs SET sgs_id=?, strand=?, grade=?, section=?, teacher_adviser=? WHERE sgs_id=?";
            PreparedStatement ps = dbconn.prepareStatement(query);
            ps.setString(1, id);
            ps.setString(2, strand);
            ps.setString(3, grade);
            ps.setString(4, section);
            if (tea_id.equals("")) {
                ps.setNull(5, Types.INTEGER);
            } else {
                ps.setString(5, tea_id);
            }
            ps.setString(6, oldid);
            ps.executeUpdate();

            hideall();
            sgstablepanel.setVisible(true);
            editsgs.setVisible(true);
            sgsbuttonpanel.setVisible(true);
            defaultSgs();
            sortSgs();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(Admin_Form.class.getName()).log(Level.SEVERE, null, ex);
            databaseErrorMessage();
        }
    }
    private void buttonsgsremove(){
        try {
            String id = String.valueOf(sgstable.getValueAt(q, 0));
            
            String query = "UPDATE student SET sgs_id=? WHERE sgs_id=?";
            String semiquery = "UPDATE subject SET sgs_id=? WHERE sgs_id=?";
            String finalquery = "DELETE FROM sgs WHERE sgs_id=?";
            
            PreparedStatement ps = dbconn.prepareStatement(query);
            ps.setNull(1, Types.INTEGER);
            ps.setString(2, id);
            ps.executeUpdate();
            
            PreparedStatement ps1 = dbconn.prepareStatement(semiquery);
            ps1.setNull(1, Types.INTEGER);
            ps1.setString(2, id);
            ps1.executeUpdate();
            
            PreparedStatement ps2 = dbconn.prepareStatement(finalquery);
            ps2.setString(1, id);
            ps2.executeUpdate();

            hideall();
            sgstablepanel.setVisible(true);
            editsgs.setVisible(true);
            sgsbuttonpanel.setVisible(true);
            defaultSgs();
            sortSgs();
            ps.close();
            ps1.close();
            ps2.close();
        } catch (SQLException ex) {
            Logger.getLogger(Admin_Form.class.getName()).log(Level.SEVERE, null, ex);
            databaseErrorMessage();
        }
    }
    private void buttonlocadd(){
        try {
            String id = txtlocid.getText();
            String dep = txtlocname.getText();
            String room = txtlocroom.getText();
            String desc = txtlocdesc.getText().trim();

            String query = "INSERT INTO location(loc_id,dep,room,room_desc) VALUES (?,?,?,?)";
            PreparedStatement ps = dbconn.prepareStatement(query);
            ps.setString(1, id);
            ps.setString(2, dep);
            ps.setString(3, room);
            if (desc.equals("")) {
                ps.setNull(4, Types.VARCHAR);
            } else {
                ps.setString(4, desc);
            }
            ps.executeUpdate();

            hideall();
            loctablepanel.setVisible(true);
            editloc.setVisible(true);
            locbuttonpanel.setVisible(true);
            defaultLocation();
            sortLocation();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(Admin_Form.class.getName()).log(Level.SEVERE, null, ex);
            databaseErrorMessage();
        }
    }
    private void buttonlocupdate(){
        try {
            String oldid = String.valueOf(loctable.getValueAt(q, 0));
            String id = txtlocid.getText();
            String dep = txtlocname.getText();
            String room = txtlocroom.getText();
            String desc = txtlocdesc.getText().trim();

            String query = "UPDATE location SET loc_id=?, dep=?, room=?, room_desc=? WHERE loc_id=?";
            PreparedStatement ps = dbconn.prepareStatement(query);
            ps.setString(1, id);
            ps.setString(2, dep);
            ps.setString(3, room);
            if (desc.equals("")) {
                ps.setNull(4, Types.VARCHAR);
            } else {
                ps.setString(4, desc);
            }
            ps.setString(5, oldid);
            ps.executeUpdate();

            hideall();
            loctablepanel.setVisible(true);
            editloc.setVisible(true);
            locbuttonpanel.setVisible(true);
            defaultLocation();
            sortLocation();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(Admin_Form.class.getName()).log(Level.SEVERE, null, ex);
            databaseErrorMessage();
        }
    }
    private void buttonlocremove(){
        try {
            String id = String.valueOf(loctable.getValueAt(q, 0));

            String query = "UPDATE subject SET loc_id=? WHERE loc_id=?";
            String finalquery = "DELETE FROM location WHERE loc_id=?";
            
            PreparedStatement ps = dbconn.prepareStatement(query);
            ps.setNull(1, Types.INTEGER);
            ps.setString(2, id);
            ps.executeUpdate();
            
            PreparedStatement ps1 = dbconn.prepareStatement(finalquery);
            ps1.setString(1, id);
            ps1.executeUpdate();

            hideall();
            loctablepanel.setVisible(true);
            editloc.setVisible(true);
            locbuttonpanel.setVisible(true);
            defaultLocation();
            sortLocation();
            ps.close();
            ps1.close();
        } catch (SQLException ex) {
            Logger.getLogger(Admin_Form.class.getName()).log(Level.SEVERE, null, ex);
            databaseErrorMessage();
        }
    }
    
    private void buttonsubadd(){
        try {
            String id = txtsubid.getText();
            String name = txtsubname.getText();
            String sem = txtsubsem.getText();
            String desc = txtsubdesc.getText();
            String day1 = String.valueOf(txtsubday1.getSelectedItem());
            String day2 = String.valueOf(txtsubday2.getSelectedItem()).trim();
            String hour1 = txtsubhour1.getText();
            String min1 = txtsubmin1.getText();
            String hour2 = txtsubhour2.getText();
            String min2 = txtsubmin2.getText();
            String locid = txtsublocid.getText().trim();
            String sgsid = txtsubsgsid.getText().trim();
            String teaid = txtsubteaid.getText().trim();
            
            String query = "INSERT INTO `subject` (`sub_id`, `name`, `sem`, `desc`, `day1`, `day2`, `hour1`, `min1`, `hour2`, `min2`, `loc_id`, `sgs_id`, `tea_id`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement ps = dbconn.prepareStatement(query);
            ps.setString(1, id);
            ps.setString(2, name);
            ps.setString(3, sem);
            ps.setString(4, desc);
            ps.setString(5, day1);
            if (txtsubday2.getSelectedIndex() == -1 || day2.equals("<none>") || day2.equals("") || day2 == null) {
                ps.setNull(6, Types.VARCHAR);
            } else {
                ps.setString(6, day2);
            }
            ps.setString(7, hour1);
            ps.setString(8, min1);
            ps.setString(9, hour2);
            ps.setString(10, min2);
            if (locid.equals("")) {
                ps.setNull(11, Types.INTEGER);
            } else {
                ps.setString(11, locid);
            }
            if (sgsid.equals("")) {
                ps.setNull(12, Types.INTEGER);
            } else {
                ps.setString(12, sgsid);
            }
            if (teaid.equals("")) {
                ps.setNull(13, Types.INTEGER);
            } else {
                ps.setString(13, teaid);
            }
            ps.executeUpdate();

            hideall();
            subtablepanel.setVisible(true);
            editsub.setVisible(true);
            subbuttonpanel.setVisible(true);
            defaultSubject();
            sortSubject();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(Admin_Form.class.getName()).log(Level.SEVERE, null, ex);
            databaseErrorMessage();
        }
    }
    private void buttonsubupdate(){
        try {
            String oldid = String.valueOf(subtable.getValueAt(q, 0));
            String id = txtsubid.getText();
            String name = txtsubname.getText();
            String sem = txtsubsem.getText();
            String desc = txtsubdesc.getText();
            String day1 = String.valueOf(txtsubday1.getSelectedItem());
            String day2 = String.valueOf(txtsubday2.getSelectedItem()).trim();
            String hour1 = txtsubhour1.getText();
            String min1 = txtsubmin1.getText();
            String hour2 = txtsubhour2.getText();
            String min2 = txtsubmin2.getText();
            String locid = txtsublocid.getText().trim();
            String sgsid = txtsubsgsid.getText().trim();
            String teaid = txtsubteaid.getText().trim();
            
            String query = "UPDATE `subject` SET `sub_id`=?, `name`=?, `sem`=?, `desc`=?, `day1`=?, `day2`=?, `hour1`=?, `min1`=?, `hour2`=?, `min2`=?, `loc_id`=?, `sgs_id`=?, `tea_id`=? WHERE `sub_id`=?";
            PreparedStatement ps = dbconn.prepareStatement(query);
            ps.setString(1, id);
            ps.setString(2, name);
            ps.setString(3, sem);
            ps.setString(4, desc);
            ps.setString(5, day1);
            if (txtsubday2.getSelectedIndex() == -1 || day2.equals("<none>") || day2.equals("") || day2 == null) {
                ps.setNull(6, Types.VARCHAR);
            } else {
                ps.setString(6, day2);
            }
            ps.setString(7, hour1);
            ps.setString(8, min1);
            ps.setString(9, hour2);
            ps.setString(10, min2);
            if (locid.equals("")) {
                ps.setNull(11, Types.INTEGER);
            } else {
                ps.setString(11, locid);
            }
            if (sgsid.equals("")) {
                ps.setNull(12, Types.INTEGER);
            } else {
                ps.setString(12, sgsid);
            }
            if (teaid.equals("")) {
                ps.setNull(13, Types.INTEGER);
            } else {
                ps.setString(13, teaid);
            }
            ps.setString(14, oldid);
            ps.executeUpdate();

            hideall();
            subtablepanel.setVisible(true);
            editsub.setVisible(true);
            subbuttonpanel.setVisible(true);
            defaultSubject();
            sortSubject();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(Admin_Form.class.getName()).log(Level.SEVERE, null, ex);
            databaseErrorMessage();
        }
    }
    private void buttonsubremove(){
        try {
            String id = String.valueOf(subtable.getValueAt(q, 0));

            String finalquery = "DELETE FROM subject WHERE sub_id=?";
            
            PreparedStatement ps1 = dbconn.prepareStatement(finalquery);
            ps1.setString(1, id);
            ps1.executeUpdate();

            hideall();
            subtablepanel.setVisible(true);
            editsub.setVisible(true);
            subbuttonpanel.setVisible(true);
            defaultSubject();
            sortSubject();
            ps1.close();
        } catch (SQLException ex) {
            Logger.getLogger(Admin_Form.class.getName()).log(Level.SEVERE, null, ex);
            databaseErrorMessage();
        }
    }
    // </editor-fold> 
    // <editor-fold defaultstate="collapsed" desc="Components Code">
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable admintable;
    public javax.swing.JPanel admintablepanel;
    private javax.swing.JScrollPane admintablescroll;
    private csms.database.swing.Button agendaaddbutton;
    private javax.swing.JPanel agendabuttonpanel;
    private csms.database.swing.Button agendarefreshbutton;
    private csms.database.swing.Button agendaremovebutton;
    private javax.swing.JTable agendatable;
    public javax.swing.JPanel agendatablepanel;
    private javax.swing.JScrollPane agendatablescroll;
    private csms.database.swing.Button agendaupdatebutton;
    private javax.swing.JLayeredPane buttonLayeredPane;
    private javax.swing.JLayeredPane editLayeredPane;
    public javax.swing.JPanel editadmin;
    public javax.swing.JPanel editagenda;
    public javax.swing.JPanel editloc;
    public javax.swing.JPanel editsgs;
    public javax.swing.JPanel editstud;
    public javax.swing.JPanel editsub;
    public javax.swing.JPanel edittea;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JLabel labelbottom;
    private javax.swing.JLabel labelbottom1;
    private javax.swing.JLabel labelbottom2;
    private javax.swing.JLabel lbemail;
    private javax.swing.JLabel lbemail1;
    private javax.swing.JLabel lbemail2;
    private javax.swing.JLabel lbemail3;
    private javax.swing.JLabel lbemail4;
    private javax.swing.JLabel lbemail5;
    private javax.swing.JLabel lbemail6;
    private javax.swing.JLabel lbfirst;
    private javax.swing.JLabel lbfirst1;
    private javax.swing.JLabel lbfirst2;
    private javax.swing.JLabel lbfirst3;
    private javax.swing.JLabel lbfirst4;
    private javax.swing.JLabel lbfirst5;
    private javax.swing.JLabel lbfirst6;
    private javax.swing.JLabel lblast;
    private javax.swing.JLabel lblast1;
    private javax.swing.JLabel lblast2;
    private javax.swing.JLabel lblast3;
    private javax.swing.JLabel lblast4;
    private javax.swing.JLabel lblast5;
    private javax.swing.JLabel lblast6;
    private javax.swing.JLabel lbnum;
    private javax.swing.JLabel lbnum1;
    private javax.swing.JLabel lbnum10;
    private javax.swing.JLabel lbnum11;
    private javax.swing.JLabel lbnum12;
    private javax.swing.JLabel lbnum13;
    private javax.swing.JLabel lbnum14;
    private javax.swing.JLabel lbnum15;
    private javax.swing.JLabel lbnum17;
    private javax.swing.JLabel lbnum2;
    private javax.swing.JLabel lbnum3;
    private javax.swing.JLabel lbnum4;
    private javax.swing.JLabel lbnum5;
    private javax.swing.JLabel lbnum6;
    private javax.swing.JLabel lbnum7;
    private javax.swing.JLabel lbnum8;
    private javax.swing.JLabel lbnum9;
    private javax.swing.JLabel lbsgs;
    private javax.swing.JLabel lbshowtable;
    private javax.swing.JLabel lbsortby;
    private javax.swing.JLabel lbstudid;
    private javax.swing.JLabel lbteaid;
    private javax.swing.JLabel lbteaid1;
    private javax.swing.JLabel lbteaid2;
    private javax.swing.JLabel lbteaid3;
    private javax.swing.JLabel lbteaid4;
    private javax.swing.JLabel lbteaid5;
    private javax.swing.JLabel lbtitle;
    private javax.swing.JLabel lbtitle1;
    private javax.swing.JLabel lbtitle2;
    private javax.swing.JLabel lbtitle3;
    private javax.swing.JLabel lbtitle4;
    private javax.swing.JLabel lbtitle5;
    private javax.swing.JLabel lbtitle6;
    private csms.database.swing.Button locaddbutton;
    private javax.swing.JPanel locbuttonpanel;
    private csms.database.swing.Button locrefreshbutton;
    private csms.database.swing.Button locremovebutton;
    private javax.swing.JTable loctable;
    public javax.swing.JPanel loctablepanel;
    private javax.swing.JScrollPane loctablescroll;
    private csms.database.swing.Button locupdatebutton;
    private javax.swing.JPanel otherOptionPanel;
    private csms.database.swing.Button removebutton;
    private csms.database.swing.Button sgsaddbutton;
    private javax.swing.JPanel sgsbuttonpanel;
    private csms.database.swing.Button sgsrefreshbutton;
    private csms.database.swing.Button sgsremovebutton;
    private javax.swing.JTable sgstable;
    public javax.swing.JPanel sgstablepanel;
    private javax.swing.JScrollPane sgstablescroll;
    private csms.database.swing.Button sgsupdatebutton;
    public csms.database.swing.Choice2 showtable;
    public csms.database.swing.Choice2 sortby;
    private javax.swing.JTable studtable;
    public javax.swing.JPanel studtablepanel;
    private javax.swing.JScrollPane studtablescroll;
    private csms.database.swing.Button subaddbutton;
    private javax.swing.JPanel subbuttonpanel;
    private csms.database.swing.Button subrefreshbutton;
    private csms.database.swing.Button subremovebutton;
    private javax.swing.JTable subtable;
    public javax.swing.JPanel subtablepanel;
    private javax.swing.JScrollPane subtablescroll;
    private csms.database.swing.Button subupdatebutton;
    private javax.swing.JLayeredPane tableLayeredPane;
    private javax.swing.JPanel tablesortPanel;
    private javax.swing.JTable teatable;
    public javax.swing.JPanel teatablepanel;
    private javax.swing.JScrollPane teatablescroll;
    public javax.swing.JTextPane txt;
    private javax.swing.JTextField txtadminemail;
    private javax.swing.JTextField txtadminfirst;
    private javax.swing.JTextField txtadminid;
    private javax.swing.JTextField txtadminjob;
    private javax.swing.JTextArea txtadminjobdesc;
    private javax.swing.JTextField txtadminjobloc;
    private javax.swing.JTextField txtadminlast;
    private javax.swing.JTextField txtadminnum;
    private csms.database.swing.Choice2 txtagendadateday;
    private csms.database.swing.Choice2 txtagendadatemonth;
    private javax.swing.JTextArea txtagendadesc;
    private javax.swing.JTextField txtagendaid;
    private javax.swing.JTextField txtagendaname;
    private csms.database.swing.Choice2 txtagendarepeat;
    private javax.swing.JTextField txtlocdesc;
    private javax.swing.JTextField txtlocid;
    private javax.swing.JTextField txtlocname;
    private javax.swing.JTextField txtlocroom;
    private javax.swing.JTextField txtsgsgrade;
    private javax.swing.JTextField txtsgsid;
    private javax.swing.JTextField txtsgssection;
    private javax.swing.JTextField txtsgsstrand;
    private javax.swing.JTextField txtsgsteaid;
    private javax.swing.JTextField txtstudemail;
    private javax.swing.JTextField txtstudfirst;
    private javax.swing.JTextField txtstudid;
    private javax.swing.JTextField txtstudlast;
    private javax.swing.JTextField txtstudnum;
    private javax.swing.JTextField txtstudsgs;
    private csms.database.swing.Choice2 txtsubday1;
    private csms.database.swing.Choice2 txtsubday2;
    private javax.swing.JTextArea txtsubdesc;
    private javax.swing.JTextField txtsubhour1;
    private javax.swing.JTextField txtsubhour2;
    private javax.swing.JTextField txtsubid;
    private javax.swing.JTextField txtsublocid;
    private javax.swing.JTextField txtsubmin1;
    private javax.swing.JTextField txtsubmin2;
    private javax.swing.JTextField txtsubname;
    private javax.swing.JTextField txtsubsem;
    private javax.swing.JTextField txtsubsgsid;
    private javax.swing.JTextField txtsubteaid;
    private javax.swing.JTextField txtteaemail;
    private javax.swing.JTextField txtteafirst;
    private javax.swing.JTextField txtteaid;
    private javax.swing.JTextField txttealast;
    private javax.swing.JTextField txtteanum;
    private javax.swing.JPanel userbuttonpanel;
    // End of variables declaration//GEN-END:variables
    // </editor-fold> 
    // <editor-fold defaultstate="collapsed" desc="Private Void Codes">  
    private void defaultStudent(){
        removebutton.setText("Remove the selected student?");
        try {
            Statement st = dbconn.createStatement();
            DefaultTableModel dt = (DefaultTableModel) studtable.getModel();
            dt.setRowCount(0);
            ResultSet rs = st.executeQuery("SELECT * FROM student");
            while (rs.next()) {
                Object o[] = {rs.getInt("stud_id"), rs.getString("first_name"), rs.getString("last_name"), rs.getString("email"), rs.getString("contact_no"), rs.getInt("sgs_id")};
                dt.addRow(o);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Admin_Form.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void defaultTeacher(){
        removebutton.setText("Remove the selected teacher?");
        try {
            Statement st = dbconn.createStatement();
            DefaultTableModel dt = (DefaultTableModel) teatable.getModel();
            dt.setRowCount(0);
            ResultSet rs = st.executeQuery("SELECT * FROM teacher");
            while (rs.next()) {
                Object o[] = {rs.getInt("tea_id"), rs.getString("first_name"), rs.getString("last_name"), rs.getString("email"), rs.getString("contact_no")};
                dt.addRow(o);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Admin_Form.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void defaultAdmin(){
        removebutton.setText("Remove the selected admin?");
        try {
            Statement st = dbconn.createStatement();
            DefaultTableModel dt = (DefaultTableModel) admintable.getModel();
            dt.setRowCount(0);
            ResultSet rs = st.executeQuery("SELECT * FROM admin");
            while (rs.next()) {
                Object o[]= {rs.getInt("admin_id"), rs.getString("first_name"), rs.getString("last_name"), rs.getString("email"), rs.getString("contact_no"), rs.getString("job"), rs.getString("job_location"), rs.getString("job_info")};
                dt.addRow(o);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Admin_Form.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void defaultSgs(){
        try {
            Statement st = dbconn.createStatement();
            DefaultTableModel dt = (DefaultTableModel) sgstable.getModel();
            dt.setRowCount(0);
            ResultSet rs = st.executeQuery("SELECT * FROM sgs");
            while (rs.next()) {
                String adviser = rs.getString("teacher_adviser");
                if(adviser == null){
                    adviser = "NULL";
                }
                Object o[]= {rs.getInt("sgs_id"), rs.getString("strand"), rs.getString("grade"), rs.getString("section"), adviser};
                dt.addRow(o);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Admin_Form.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void defaultAgenda(){
        try {
            Statement st = dbconn.createStatement();
            DefaultTableModel dt = (DefaultTableModel) agendatable.getModel();
            dt.setRowCount(0);
            ResultSet rs = st.executeQuery("SELECT * FROM agenda");
            while (rs.next()) {
                String dateformat = df.format(rs.getDate("date"));
                Object o[]= {rs.getInt("agenda_id"), rs.getString("name"), rs.getString("description"), dateformat, rs.getString("repeatable")};
                dt.addRow(o);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Admin_Form.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void defaultLocation(){
        try {
            Statement st = dbconn.createStatement();
            DefaultTableModel dt = (DefaultTableModel) loctable.getModel();
            dt.setRowCount(0);
            ResultSet rs = st.executeQuery("SELECT * FROM location");
            while (rs.next()) {
                String descr = rs.getString("room_desc");
                if(descr == null){
                    descr = "NULL";
                }
                Object o[]= {rs.getInt("loc_id"), rs.getString("dep"), rs.getInt("room"), descr};
                dt.addRow(o);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Admin_Form.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void defaultSubject(){
        try {
            Statement st = dbconn.createStatement();
            DefaultTableModel dt = (DefaultTableModel) subtable.getModel();
            dt.setRowCount(0);
            ResultSet rs = st.executeQuery("SELECT * FROM subject");
            while (rs.next()) {
                int subid = rs.getInt("sub_id");
                String subname = rs.getString("name");
                int subsem = rs.getInt("sem");
                String subdesc = rs.getString("desc");
                String subday1 = rs.getString("day1");
                String subday2 = rs.getString("day2");
                int subhour1 = rs.getInt("hour1");
                int submin1 = rs.getInt("min1");
                int subhour2 = rs.getInt("hour2");
                int submin2 = rs.getInt("min2");
                int subloc = rs.getInt("loc_id");
                int subsgs = rs.getInt("sgs_id");
                int subtea = rs.getInt("tea_id");
                Object o[]= {subid,subname,subsem,subdesc,subday1,subday2,subhour1,submin1,subhour2,submin2,subloc,subsgs,subtea};
                dt.addRow(o);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Admin_Form.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void sortStudent(){
        sortby.removeAllItems();
        String[] sortchoice = {"ID", "First Name", "Last Name", "SGS ID"};
        sortby.setModel(new DefaultComboBoxModel<>(sortchoice));
        sortby.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                q=-1;
                txtstudid.setText("");
                txtstudfirst.setText("");
                txtstudlast.setText("");
                txtstudnum.setText("");
                txtstudsgs.setText("");
                txtstudemail.setText("");
                try {
                    Statement st = dbconn.createStatement();
                    DefaultTableModel dt = (DefaultTableModel) studtable.getModel();
                    dt.setRowCount(0);
                    ResultSet rs;
                    rs = switch (sortby.getSelectedIndex()) {
                        case 3 -> st.executeQuery("SELECT * FROM student ORDER BY sgs_id");
                        case 1 -> st.executeQuery("SELECT * FROM student ORDER BY first_name");
                        case 2 -> st.executeQuery("SELECT * FROM student ORDER BY last_name");
                        default -> st.executeQuery("SELECT * FROM student ORDER BY stud_id");
                    };
                    while (rs.next()){
                        Object o[]= {rs.getInt("stud_id"), rs.getString("first_name"), rs.getString("last_name"), rs.getString("email"), rs.getString("contact_no"), rs.getInt("sgs_id")};
                        dt.addRow(o);
                    }
                    rs.close();
                    st.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Admin_Form.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
    private void sortTeacher(){
        sortby.removeAllItems();
        String[] sortchoice = {"ID", "First Name", "Last Name"};
        sortby.setModel(new DefaultComboBoxModel<>(sortchoice));
        sortby.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                q=-1;
                txtteaid.setText("");
                txtteafirst.setText("");
                txttealast.setText("");
                txtteaemail.setText("");
                txtteanum.setText("");
                try {
                    Statement st = dbconn.createStatement();
                    DefaultTableModel dt = (DefaultTableModel) teatable.getModel();
                    dt.setRowCount(0);
                    ResultSet rs;
                    rs = switch (sortby.getSelectedIndex()) {
                        case 1 -> st.executeQuery("SELECT * FROM teacher ORDER BY first_name");
                        case 2 -> st.executeQuery("SELECT * FROM teacher ORDER BY last_name");
                        default -> st.executeQuery("SELECT * FROM teacher ORDER BY tea_id");
                    };
                    while (rs.next()){
                        Object o[]= {rs.getInt("tea_id"), rs.getString("first_name"), rs.getString("last_name"), rs.getString("email"), rs.getString("contact_no")};
                        dt.addRow(o);
                    }
                    rs.close();
                    st.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Admin_Form.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
    private void sortAdmin(){
        sortby.removeAllItems();
        String[] sortchoice = {"ID", "First Name", "Last Name", "Job"};
        sortby.setModel(new DefaultComboBoxModel<>(sortchoice));
        sortby.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                q=-1;
                txtadminid.setText("");
                txtadminfirst.setText("");
                txtadminlast.setText("");
                txtadminemail.setText("");
                txtadminnum.setText("");
                txtadminjob.setText("");
                txtadminjobloc.setText("");
                txtadminjobdesc.setText("");
                try {
                    Statement st = dbconn.createStatement();
                    DefaultTableModel dt = (DefaultTableModel) admintable.getModel();
                    dt.setRowCount(0);
                    ResultSet rs;
                    rs = switch (sortby.getSelectedIndex()) {
                        case 1 -> st.executeQuery("SELECT * FROM admin ORDER BY first_name");
                        case 2 -> st.executeQuery("SELECT * FROM admin ORDER BY last_name");
                        case 3 -> st.executeQuery("SELECT * FROM admin ORDER BY job");
                        default -> st.executeQuery("SELECT * FROM admin ORDER BY admin_id");
                    };
                    while (rs.next()){
                        Object o[]= {rs.getInt("admin_id"), rs.getString("first_name"), rs.getString("last_name"), rs.getString("email"), rs.getString("contact_no"), rs.getString("job"), rs.getString("job_location"), rs.getString("job_info")};
                        dt.addRow(o);
                    }
                    rs.close();
                    st.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Admin_Form.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
    private void sortAgenda(){
        sortby.removeAllItems();
        String[] sortchoice = {"Agenda ID", "Agenda Name", "Date"};
        sortby.setModel(new DefaultComboBoxModel<>(sortchoice));
        sortby.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                q=-1;
                txtagendaid.setText("");
                txtagendaname.setText("");
                txtagendadesc.setText("");
                txtagendarepeat.setSelectedItem("");
                txtagendadatemonth.setSelectedIndex(-1);
                txtagendadateday.setSelectedItem(null);
                try {
                    Statement st = dbconn.createStatement();
                    DefaultTableModel dt = (DefaultTableModel) agendatable.getModel();
                    dt.setRowCount(0);
                    ResultSet rs;
                    rs = switch (sortby.getSelectedIndex()) {
                        case 1 -> st.executeQuery("SELECT * FROM agenda ORDER BY name");
                        case 2 -> st.executeQuery("SELECT * FROM agenda ORDER BY date");
                        default -> st.executeQuery("SELECT * FROM agenda ORDER BY agenda_id");
                    };
                    while (rs.next()){
                        String dateformat = df.format(rs.getDate("date"));
                        Object o[] = {rs.getInt("agenda_id"), rs.getString("name"), rs.getString("description"), dateformat, rs.getString("repeatable")};
                        dt.addRow(o);
                    }
                    rs.close();
                    st.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Admin_Form.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
    private void sortLocation(){
        sortby.removeAllItems();
        String[] sortchoice = {"Location ID", "Department Name", "Room Number"};
        sortby.setModel(new DefaultComboBoxModel<>(sortchoice));
        sortby.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtlocid.setText("");
                txtlocname.setText("");
                txtlocroom.setText("");
                txtlocdesc.setText("");
                q=-1;
                try {
                    Statement st = dbconn.createStatement();
                    DefaultTableModel dt = (DefaultTableModel) loctable.getModel();
                    dt.setRowCount(0);
                    ResultSet rs;
                    rs = switch (sortby.getSelectedIndex()) {
                        case 1 -> st.executeQuery("SELECT * FROM location ORDER BY dep");
                        case 2 -> st.executeQuery("SELECT * FROM location ORDER BY room,dep");
                        default -> st.executeQuery("SELECT * FROM location ORDER BY loc_id");
                    };
                    while (rs.next()){
                        String descr = rs.getString("room_desc");
                        if (descr == null) {
                            descr = "NULL";
                        }
                        Object o[] = {rs.getInt("loc_id"), rs.getString("dep"), rs.getInt("room"), descr};
                        dt.addRow(o);
                    }
                    rs.close();
                    st.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Admin_Form.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
    private void sortSgs(){
        sortby.removeAllItems();
        String[] sortchoice = {"SGS ID", "Strand", "Grade"};
        sortby.setModel(new DefaultComboBoxModel<>(sortchoice));
        sortby.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtsgsid.setText("");
                txtsgsstrand.setText("");
                txtsgsgrade.setText("");
                txtsgssection.setText("");
                txtsgsteaid.setText("");
                q = -1;
                try {
                    Statement st = dbconn.createStatement();
                    DefaultTableModel dt = (DefaultTableModel) sgstable.getModel();
                    dt.setRowCount(0);
                    ResultSet rs;
                    rs = switch (sortby.getSelectedIndex()) {
                        case 1 -> st.executeQuery("SELECT * FROM sgs ORDER BY strand,sgs_id");
                        case 2 -> st.executeQuery("SELECT * FROM sgs ORDER BY grade,strand");
                        default -> st.executeQuery("SELECT * FROM sgs ORDER BY sgs_id");
                    };
                    while (rs.next()){
                        String adviser = rs.getString("teacher_adviser");
                        if (adviser == null) {
                            adviser = "NULL";
                        }
                        Object o[] = {rs.getInt("sgs_id"), rs.getString("strand"), rs.getString("grade"), rs.getString("section"), adviser};
                        dt.addRow(o);
                    }
                    rs.close();
                    st.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Admin_Form.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
    private void sortSubject(){
        sortby.removeAllItems();
        String[] sortchoice = {"ID", "Subject", "Semester","Location ID", "SGS ID"};
        sortby.setModel(new DefaultComboBoxModel<>(sortchoice));
        sortby.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                q = -1;
                txtsubid.setText("");
                txtsubname.setText("");
                txtsubsem.setText("");
                txtsubdesc.setText("");
                txtsubday1.setSelectedIndex(-1);
                txtsubday2.setSelectedIndex(-1);
                txtsubhour1.setText("");
                txtsubmin1.setText("");
                txtsubhour2.setText("");
                txtsubmin2.setText("");
                txtsublocid.setText("");
                txtsubsgsid.setText("");
                txtsubteaid.setText("");
                try {
                    Statement st = dbconn.createStatement();
                    DefaultTableModel dt = (DefaultTableModel) subtable.getModel();
                    dt.setRowCount(0);
                    ResultSet rs;
                    rs = switch (sortby.getSelectedIndex()) {
                        case 1 -> st.executeQuery("SELECT * FROM subject ORDER BY name, sem");
                        case 2 -> st.executeQuery("SELECT * FROM subject ORDER BY sem, name");
                        case 3 -> st.executeQuery("SELECT * FROM subject ORDER BY loc_id, name, sem");
                        case 4 -> st.executeQuery("SELECT * FROM subject ORDER BY sgs_id, name, sem");
                        default -> st.executeQuery("SELECT * FROM subject ORDER BY sub_id");
                    };
                    while (rs.next()){
                        int subid = rs.getInt("sub_id");
                        String subname = rs.getString("name");
                        int subsem = rs.getInt("sem");
                        String subdesc = rs.getString("desc");
                        String subday1 = rs.getString("day1");
                        String subday2 = rs.getString("day2");
                        int subhour1 = rs.getInt("hour1");
                        int submin1 = rs.getInt("min1");
                        int subhour2 = rs.getInt("hour2");
                        int submin2 = rs.getInt("min2");
                        int subloc = rs.getInt("loc_id");
                        int subsgs = rs.getInt("sgs_id");
                        int subtea = rs.getInt("tea_id");
                        Object o[] = {subid, subname, subsem, subdesc, subday1, subday2, subhour1, submin1, subhour2, submin2, subloc, subsgs, subtea};
                        dt.addRow(o);
                    }
                    rs.close();
                    st.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Admin_Form.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
    
    private void hideall(){
        editstud.setVisible(false);
        edittea.setVisible(false);
        editadmin.setVisible(false);
        editagenda.setVisible(false);
        editloc.setVisible(false);
        editsub.setVisible(false);
        editsgs.setVisible(false);
        teatablepanel.setVisible(false);
        admintablepanel.setVisible(false);
        loctablepanel.setVisible(false);
        subtablepanel.setVisible(false);
        studtablepanel.setVisible(false);
        sgstablepanel.setVisible(false);
        agendatablepanel.setVisible(false);
        userbuttonpanel.setVisible(false);
        agendabuttonpanel.setVisible(false);
        sgsbuttonpanel.setVisible(false);
        locbuttonpanel.setVisible(false);
        subbuttonpanel.setVisible(false);
        q=-1;
        
        txtstudid.setText("");
        txtstudfirst.setText("");
        txtstudlast.setText("");
        txtstudnum.setText("");
        txtstudsgs.setText("");
        txtstudemail.setText("");
        
        txtteaid.setText("");
        txtteafirst.setText("");
        txttealast.setText("");
        txtteaemail.setText("");
        txtteanum.setText("");
        
        txtadminid.setText("");
        txtadminfirst.setText("");
        txtadminlast.setText("");
        txtadminemail.setText("");
        txtadminnum.setText("");
        txtadminjob.setText("");
        txtadminjobloc.setText("");
        txtadminjobdesc.setText("");
        
        txtagendaid.setText("");
        txtagendaname.setText("");
        txtagendadesc.setText("");
        txtagendarepeat.setSelectedIndex(-1);
        txtagendadatemonth.setSelectedIndex(-1);
        txtagendadateday.setSelectedItem(null);
        
        txtlocid.setText("");
        txtlocname.setText("");
        txtlocroom.setText("");
        txtlocdesc.setText("");
        
        txtsgsid.setText("");
        txtsgsstrand.setText("");
        txtsgsgrade.setText("");
        txtsgssection.setText("");
        txtsgsteaid.setText("");
        
        txtsubid.setText("");
        txtsubname.setText("");
        txtsubsem.setText("");
        txtsubdesc.setText("");
        txtsubday1.setSelectedIndex(-1);
        txtsubday2.setSelectedIndex(0);
        txtsubhour1.setText("");
        txtsubmin1.setText("");
        txtsubhour2.setText("");
        txtsubmin2.setText("");
        txtsublocid.setText("");
        txtsubsgsid.setText("");
        txtsubteaid.setText("");
    }
    private void initUsers(){
        studtable.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                q = studtable.getSelectedRow();
                if(q != -1){
                    String id = String.valueOf(studtable.getValueAt(q, 0));
                    String first = String.valueOf(studtable.getValueAt(q, 1));
                    String last = String.valueOf(studtable.getValueAt(q, 2));
                    String email = String.valueOf(studtable.getValueAt(q, 3));
                    String contact = String.valueOf(studtable.getValueAt(q, 4));
                    String sgsid = String.valueOf(studtable.getValueAt(q, 5));
                    txtstudid.setText(id);
                    txtstudfirst.setText(first);
                    txtstudlast.setText(last);
                    txtstudemail.setText(email);
                    txtstudnum.setText(contact);
                    txtstudsgs.setText(sgsid);
                }
            }
        });
        teatable.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                q = teatable.getSelectedRow();
                if(q != -1){
                    String id = String.valueOf(teatable.getValueAt(q, 0));
                    String first = String.valueOf(teatable.getValueAt(q, 1));
                    String last = String.valueOf(teatable.getValueAt(q, 2));
                    String email = String.valueOf(teatable.getValueAt(q, 3));
                    String contact = String.valueOf(teatable.getValueAt(q, 4));
                    txtteaid.setText(id);
                    txtteafirst.setText(first);
                    txttealast.setText(last);
                    txtteaemail.setText(email);
                    txtteanum.setText(contact);
                }
            }
        });
        admintable.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                q = admintable.getSelectedRow();
                if(q != -1){
                    String id = String.valueOf(admintable.getValueAt(q, 0));
                    String first = String.valueOf(admintable.getValueAt(q, 1));
                    String last = String.valueOf(admintable.getValueAt(q, 2));
                    String email = String.valueOf(admintable.getValueAt(q, 3));
                    String contact = String.valueOf(admintable.getValueAt(q, 4));
                    String job = String.valueOf(admintable.getValueAt(q, 5));
                    String jobloc = String.valueOf(admintable.getValueAt(q, 6));
                    String jobdesc = String.valueOf(admintable.getValueAt(q, 7));
                    txtadminid.setText(id);
                    txtadminfirst.setText(first);
                    txtadminlast.setText(last);
                    txtadminemail.setText(email);
                    txtadminnum.setText(contact);
                    txtadminjob.setText(job);
                    txtadminjobloc.setText(jobloc);
                    txtadminjobdesc.setText(jobdesc);
                }
            }
        });
    }
    private String selectdayDate(String agendaid){
        try {
            String day = null;
            Statement st = dbconn.createStatement();
            ResultSet rs = st.executeQuery("SELECT DAY(date) FROM agenda WHERE agenda_id=" + agendaid);
            if (rs.next()){
                day = rs.getString("DAY(date)");
            }
            return day;
        } catch (SQLException ex) {
            Logger.getLogger(Admin_Form.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    private int selectMonthDate(String agendaid){
        try {
            int month = -1;
            Statement st = dbconn.createStatement();
            ResultSet rs = st.executeQuery("SELECT MONTH(date) FROM agenda WHERE agenda_id=" + agendaid);
            if (rs.next()){
                month = rs.getInt("MONTH(date)");
            }
            return month;
        } catch (SQLException ex) {
            Logger.getLogger(Admin_Form.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }
    }
    private void initAgenda(){
        agendatable.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                q = agendatable.getSelectedRow();
                if(q != -1){
                    String id = String.valueOf(agendatable.getValueAt(q, 0));
                    String name = String.valueOf(agendatable.getValueAt(q, 1));
                    String desc = String.valueOf(agendatable.getValueAt(q, 2));
                    String repeat = String.valueOf(agendatable.getValueAt(q, 4));
                    String day = selectdayDate(id);
                    int month = selectMonthDate(id)-1;
                    txtagendaid.setText(id);
                    txtagendaname.setText(name);
                    txtagendadesc.setText(desc);
                    txtagendarepeat.setSelectedItem(repeat);
                    txtagendadatemonth.setSelectedIndex(month);
                    txtagendadateday.setSelectedItem(day);
                }
            }
        });
    }
    private void initLocation(){
        loctable.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                q = loctable.getSelectedRow();
                if(q != -1){
                    String id = String.valueOf(loctable.getValueAt(q, 0));
                    String name = String.valueOf(loctable.getValueAt(q, 1));
                    String room = String.valueOf(loctable.getValueAt(q, 2));
                    String desc = String.valueOf(loctable.getValueAt(q, 3));
                    txtlocid.setText(id);
                    txtlocname.setText(name);
                    txtlocroom.setText(room);
                    txtlocdesc.setText(desc);
                }
            }
        });
    }
    private void initSgs(){
        sgstable.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                q = sgstable.getSelectedRow();
                if(q != -1){
                    String id = String.valueOf(sgstable.getValueAt(q, 0));
                    String strand = String.valueOf(sgstable.getValueAt(q, 1));
                    String grade = String.valueOf(sgstable.getValueAt(q, 2));
                    String section = String.valueOf(sgstable.getValueAt(q, 3));
                    String tea_id = String.valueOf(sgstable.getValueAt(q, 4));
                    txtsgsid.setText(id);
                    txtsgsstrand.setText(strand);
                    txtsgsgrade.setText(grade);
                    txtsgssection.setText(section);
                    txtsgsteaid.setText(tea_id);
                }
            }
        });
    }
    private void initSubject(){
        subtable.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                q = subtable.getSelectedRow();
                if(q != -1){
                    String id = String.valueOf(subtable.getValueAt(q, 0));
                    String name = String.valueOf(subtable.getValueAt(q, 1));
                    String semester = String.valueOf(subtable.getValueAt(q, 2));
                    String desc = String.valueOf(subtable.getValueAt(q, 3));
                    String day1 = String.valueOf(subtable.getValueAt(q, 4));
                    String day2 = String.valueOf(subtable.getValueAt(q, 5));
                    String hour1 = String.valueOf(subtable.getValueAt(q, 6));
                    String min1 = String.valueOf(subtable.getValueAt(q, 7));
                    String hour2 = String.valueOf(subtable.getValueAt(q, 8));
                    String min2 = String.valueOf(subtable.getValueAt(q, 9));
                    String locid = String.valueOf(subtable.getValueAt(q, 10));
                    String sgsid = String.valueOf(subtable.getValueAt(q, 11));
                    String teaid = String.valueOf(subtable.getValueAt(q, 12));
                    txtsubid.setText(id);
                    txtsubname.setText(name);
                    txtsubsem.setText(semester);
                    txtsubdesc.setText(desc);
                    txtsubday1.setSelectedItem(day1);
                    txtsubday2.setSelectedIndex(-1);
                    if(!day2.isEmpty() || !day2.equals("") || day2 != null) {
                        txtsubday2.setSelectedItem(day2);
                    } else {
                        txtsubday2.setSelectedIndex(-1);
                    }
                    txtsubhour1.setText(hour1);
                    txtsubmin1.setText(min1);
                    txtsubhour2.setText(hour2);
                    txtsubmin2.setText(min2);
                    txtsublocid.setText(locid);
                    txtsubsgsid.setText(sgsid);
                    txtsubteaid.setText(teaid);
                }
            }
        });
    }
    private String convertSingleDigit(int num){
        if (num < 10){
                return "0" + String.valueOf(num);
            } else {
                return String.valueOf(num);
            }
    }
    private void notselectedpopup(popup pop){
        pop.txt.setText("Please select a data from the table before proceeding. Note that you cannot proceed unless you select a data from the table.");
        pop.title.setText("No data is selected");
        pop.eventOK(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GlassPanePopup.closePopupLast();
            }
        });
        GlassPanePopup.showPopup(pop);
    }
    private void notfilledpopup(popup pop){
        pop.txt.setText("Who doesn't fill up their form properly?");
        pop.title.setText("Fill up the form properly.");
        pop.eventOK(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GlassPanePopup.closePopupLast();
            }
        });
        GlassPanePopup.showPopup(pop);
    }
    private void databaseErrorMessage(){
        popup pop = new popup();
        pop.txt.setText("If this prompt shows, you have incorrectly inputted values that doesn't match on the attributes (eg. Putting letters instead of numbers in ID textfield). Please refer to the Guideline below center. The last message dialog is an error.");
        pop.title.setText("You have inputted incorrect values in the form.");
        pop.eventOK(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GlassPanePopup.closePopupLast();
            }
        });
        GlassPanePopup.showPopup(pop);
    }
    // </editor-fold> 
}