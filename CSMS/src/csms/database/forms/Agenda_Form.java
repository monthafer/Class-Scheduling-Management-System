package csms.database.forms;

import csms.database.component.Form;
import csms.database.menu.Menu;
import csms.database.agenda.swing.MenuButton;
import csms.database.agenda.swing.panelEvent;
import csms.database.properties.SystemProperties;
import csms.database.scrollbar.ScrollBarCustom;
import csms.database.theme.SystemTheme;
import csms.database.theme.ThemeColorChange;
import csms.databaseconnection.DBConnection;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Area;
import java.awt.geom.RoundRectangle2D;
import javax.swing.JButton;
import net.miginfocom.swing.MigLayout;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTarget;
import org.jdesktop.animation.timing.TimingTargetAdapter;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.border.LineBorder;

public class Agenda_Form extends Form {

    private Animator animator;
    private TimingTarget target;
    private int selectedLocation = 156;
    private int selectedIndex = -1;
    SimpleDateFormat df = new SimpleDateFormat("MMMM dd, yyyy");
    Connection dbconn = DBConnection.openConnection();
    SystemProperties pro = new SystemProperties();
    
    int jan = 0;
    int feb = 0;
    int mar = 0;
    int apr = 0;
    int may = 0;
    int jun = 0;
    int jul = 0;
    int aug = 0;
    int sep = 0;
    int oct = 0;
    int nov = 0;
    int dec = 0;
    int i = 0;
    int x = 0;
    
    public int getSelectedLocation() {
        return selectedLocation;
    }

    public void setSelectedLocation(int selectedLocation) {
        this.selectedLocation = selectedLocation;
        repaint();
    }
    
    public Agenda_Form() {
        initComponents();
        init();
        initDark();
        initMonth();
        initMenu();
    }
    
    private void init(){
        pro.loadFromFile();
        scroll.setOpaque(false);
        scroll.setBackground(new Color(0, 0, 0, 0));
        scroll.getViewport().setOpaque(false);
        scroll.setBorder(null);
        scroll.setViewportBorder(null);
        scroll.setVerticalScrollBar(new ScrollBarCustom()); //216, 467
        
        scroll1.setOpaque(false);
        scroll1.setBackground(new Color(0, 0, 0, 0));
        scroll1.getViewport().setOpaque(false);
        scroll1.setBorder(null);
        scroll1.setViewportBorder(null);
        
        scroll2.setOpaque(false);
        scroll2.setBackground(new Color(0, 0, 0, 0));
        scroll2.getViewport().setOpaque(false);
        scroll2.setBorder(null);
        scroll2.setViewportBorder(null);
        
        edit.setVisible(false);
        if(!pro.getPosition().equals("Student")){
            edit.setVisible(true);
        }
        
        agendaDesc.setOpaque(false);
        agendaDesc.setBackground(new Color(0, 0, 0, 0));
        editDesc.setOpaque(false);
        editDesc.setBackground(new Color(0, 0, 0, 0));
        descPanel.setVisible(false);
        editPanel.setVisible(false);
        descPanel.setBackground(SystemTheme.mainColor);
        descPanel.setBackground(SystemTheme.mainColor);
        editPanel.setBackground(SystemTheme.mainColor);
        editName.setBackground(SystemTheme.mainColor);
        
        monthPanel.setLayout(new MigLayout("fillx, wrap, inset 0", "[fill]", "[fill, 36!]0[fill, 36!]"));
        eventPanel.setLayout(new MigLayout("width 216!, height 467!, wrap")); //, "[fill]", "[fill, 36!]0[fill, 36!]"
    }
    
    private void initDark(){
        if(ThemeColorChange.getInstance().getMode() == ThemeColorChange.Mode.DARK){
            monthPanel.setBackground(SystemTheme.mainColor);
            eventPanel.setBackground(SystemTheme.mainColor);
            desc1.setForeground(new Color(200,200,200));
            desc2.setForeground(new Color(200,200,200));
            
            eventPanel.setBorder(new LineBorder(new Color(255, 255, 255), 3, true));
            monthPanel.setBorder(new LineBorder(new Color(255, 255, 255), 3, true));
            descPanel.setBorder(new LineBorder(new Color(255, 255, 255), 3, true));
            editPanel.setBorder(new LineBorder(new Color(255, 255, 255), 3, true));
        } else{
            monthPanel.setBackground(SystemTheme.mainColor);
            eventPanel.setBackground(SystemTheme.mainColor);
            desc1.setForeground(new Color(50,50,50));
            desc2.setForeground(new Color(50,50,50));
            
            eventPanel.setBorder(new LineBorder(new Color(0, 0, 0), 3, true));
            monthPanel.setBorder(new LineBorder(new Color(0, 0, 0), 3, true));
            descPanel.setBorder(new LineBorder(new Color(0, 0, 0), 3, true));
            editPanel.setBorder(new LineBorder(new Color(0, 0, 0), 3, true));
        }
    }
    
    private void initMonth(){
        try {
            Statement stmt = dbconn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM agenda ORDER BY date");
            while (rs.next()){
                Date date = rs.getDate("date");
                int agid = rs.getInt("agenda_id");
                Calendar cal = Calendar.getInstance();
                cal.setTime(date);
                monthNum(cal,rs);
                animation();
                String name = rs.getString("name");
                String date2 = df.format(rs.getDate("date"));
                String desc = rs.getString("description");
                panelEvent panel = new panelEvent();
                panel.agendaName.setText(name);
                panel.agendaDate.setText(date2);
                panel.dot.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (!descPanel.isVisible()) {
                            descPanel.setVisible(true);
                        }
                        agendaName.setText(name);
                        agendaDesc.setText(desc);
                        agendaDate.setText(date2);
                        x = agid;
                    }
                });
                eventPanel.add(panel, "w 100%");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void initMenu() {
        addMenu("January", jan, 0);
        addMenu("February", feb, 1);
        addMenu("March", mar, 2);
        addMenu("April", apr, 3);
        addMenu("May", may, 4);
        addMenu("June", jun, 5);
        addMenu("July", jul, 6);
        addMenu("August", aug, 7);
        addMenu("September", sep, 8);
        addMenu("October", oct, 9);
        addMenu("November", nov, 10);
        addMenu("December", dec, 11);
        
        if(!pro.getPosition().equals("Student")){
            desc1.setVisible(false);
            desc2.setVisible(false);
        }
    }
    
    private void monthNum(Calendar cal, ResultSet rs){
        switch (cal.get(Calendar.MONTH)) {
            case 0 -> jan++;
            case 1 -> feb++;
            case 2 -> mar++;
            case 3 -> apr++;
            case 4 -> may++;
            case 5 -> jun++;
            case 6 -> jul++;
            case 7 -> aug++;
            case 8 -> sep++;
            case 9 -> oct++;
            case 10 -> nov++;
            case 11 -> dec++;
            default -> {}
        }
    }
    @Override
    protected void paintComponent(Graphics grphcs) {
        Graphics2D g2 = (Graphics2D) grphcs.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        int y = selectedLocation;
        g2.setColor(new Color(0,0,0,0));
        g2.fill(createShape(y));
        g2.dispose();
        super.paintComponent(grphcs);
    }
    
    private Shape createShape(int y) {
        int width = getWidth() - 12;
        int r = 20;
        Area area = new Area(new RoundRectangle2D.Float(6, y, width, 35, r, r));
        area.add(new Area(new RoundRectangle2D.Float(width - r + 6, y, r, r, 5, 5)));
        area.add(new Area(new RoundRectangle2D.Float(6, y + 35 - r, r, r, 5, 5)));
        return area;
    }
    
    private void addMenu(String menuName, int num, int index){
        MenuButton m = new MenuButton();
        m.setFont(new java.awt.Font("SansSerif", 0, 12));
        m.setText(menuName + " (" + String.valueOf(num) + ")");
        m.setForeground(new Color(50,50,50));
        m.setBg(new Color(0,0,0,70));
        m.setHorizontalAlignment(JButton.CENTER);
        m.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (index != selectedIndex) {
                    if (animator.isRunning()) {
                        animator.stop();
                    }
                    selectedIndex = index;
                    animator.removeTarget(target);
                    animator.addTarget(target);
                    animator.start();
                    //updatePanel(index);
                }
            }
        });
        monthPanel.add(m);
    }
    
    private void updatePanel(int index){
        eventPanel.removeAll();
        try {
            Statement stmt = dbconn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM agenda WHERE MONTH(date)=" + String.valueOf(index+1));
            while (rs.next()){
                String name = rs.getString("name");
                String date = df.format(rs.getDate("date"));
                int agid = rs.getInt("agenda_id");
                String desc = rs.getString("description");
                panelEvent panel = new panelEvent();
                panel.agendaName.setText(name);
                panel.agendaDate.setText(date);
                panel.dot.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (!descPanel.isVisible()){
                            descPanel.setVisible(true);
                        }
                        agendaName.setText(name);
                        agendaDesc.setText(desc);
                        agendaDate.setText(date);
                        x = agid;
                    }
                });
                eventPanel.add(panel, "w 100%");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void animation(){
        animator = new Animator(300);
        animator.addTarget(new TimingTargetAdapter() {
            @Override
            public void begin() {
                clearSelected();
            }

            @Override
            public void end() {
                setSelectedMenu(selectedIndex);
                updatePanel(selectedIndex);
                eventPanel.repaint();
                eventPanel.revalidate();
            }
        });
        animator.setDeceleration(.5f);
        animator.setAcceleration(.5f);
        animator.setResolution(0);
    }
    
    public void setSelectedMenu(int index) {
        MenuButton cmd = (MenuButton) monthPanel.getComponent(index);
        cmd.setForeground(Color.WHITE);
        cmd.setEffectColor(Color.WHITE);
    }
    
    private void clearSelected() {
        for (Component com : monthPanel.getComponents()) {
            if (com instanceof MenuButton) {
                MenuButton c = (MenuButton) com;
                c.setForeground(new Color(50,50,50));
                c.setEffectColor(new Color(173, 173, 173));
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        monthPanel = new javax.swing.JPanel();
        titleMenu2 = new csms.database.agenda.swing.titleMenu();
        scroll = new javax.swing.JScrollPane();
        eventPanel = new javax.swing.JPanel();
        descPanel = new javax.swing.JPanel();
        agendaName = new javax.swing.JLabel();
        scroll1 = new javax.swing.JScrollPane();
        agendaDesc = new javax.swing.JTextArea();
        lbdate = new javax.swing.JLabel();
        agendaDate = new javax.swing.JLabel();
        edit = new csms.database.swing.Button();
        editPanel = new javax.swing.JPanel();
        lbdate1 = new javax.swing.JLabel();
        editName = new javax.swing.JTextField();
        scroll2 = new javax.swing.JScrollPane();
        editDesc = new javax.swing.JTextArea();
        desc2 = new javax.swing.JLabel();
        desc1 = new javax.swing.JLabel();

        monthPanel.setBackground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout monthPanelLayout = new javax.swing.GroupLayout(monthPanel);
        monthPanel.setLayout(monthPanelLayout);
        monthPanelLayout.setHorizontalGroup(
            monthPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        monthPanelLayout.setVerticalGroup(
            monthPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        scroll.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        eventPanel.setBackground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout eventPanelLayout = new javax.swing.GroupLayout(eventPanel);
        eventPanel.setLayout(eventPanelLayout);
        eventPanelLayout.setHorizontalGroup(
            eventPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 216, Short.MAX_VALUE)
        );
        eventPanelLayout.setVerticalGroup(
            eventPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 467, Short.MAX_VALUE)
        );

        scroll.setViewportView(eventPanel);

        agendaName.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        agendaName.setForeground(new java.awt.Color(51, 51, 51));
        agendaName.setText("jLabel1");

        scroll1.setBackground(new java.awt.Color(255, 255, 255));
        scroll1.setBorder(null);
        scroll1.setFocusable(false);
        scroll1.setOpaque(false);

        agendaDesc.setEditable(false);
        agendaDesc.setBackground(new java.awt.Color(255, 255, 255));
        agendaDesc.setColumns(20);
        agendaDesc.setLineWrap(true);
        agendaDesc.setRows(5);
        agendaDesc.setBorder(null);
        agendaDesc.setFocusable(false);
        scroll1.setViewportView(agendaDesc);

        lbdate.setForeground(new java.awt.Color(51, 51, 51));
        lbdate.setText("Event Date:");

        agendaDate.setForeground(new java.awt.Color(51, 51, 51));
        agendaDate.setText("jLabel3");

        edit.setText("Edit");
        edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout descPanelLayout = new javax.swing.GroupLayout(descPanel);
        descPanel.setLayout(descPanelLayout);
        descPanelLayout.setHorizontalGroup(
            descPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(descPanelLayout.createSequentialGroup()
                .addGroup(descPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, descPanelLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(edit, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(descPanelLayout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addGroup(descPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(agendaName)
                            .addGroup(descPanelLayout.createSequentialGroup()
                                .addComponent(lbdate)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(agendaDate)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, descPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(scroll1, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
        );
        descPanelLayout.setVerticalGroup(
            descPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(descPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(agendaName)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scroll1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(descPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbdate)
                    .addComponent(agendaDate))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(edit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(8, Short.MAX_VALUE))
        );

        lbdate1.setBackground(new java.awt.Color(102, 102, 102));
        lbdate1.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        lbdate1.setForeground(new java.awt.Color(51, 51, 51));
        lbdate1.setText("*If you want to add an event or delete this event, go to 'Admin' tab.");

        editName.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        editName.setForeground(new java.awt.Color(51, 51, 51));
        editName.setText("jTextField1");
        editName.setBorder(null);

        editDesc.setColumns(20);
        editDesc.setRows(5);
        scroll2.setViewportView(editDesc);

        javax.swing.GroupLayout editPanelLayout = new javax.swing.GroupLayout(editPanel);
        editPanel.setLayout(editPanelLayout);
        editPanelLayout.setHorizontalGroup(
            editPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(editPanelLayout.createSequentialGroup()
                .addGroup(editPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(editPanelLayout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(editName, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(editPanelLayout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(scroll2, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(editPanelLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(lbdate1)))
                .addContainerGap(24, Short.MAX_VALUE))
        );
        editPanelLayout.setVerticalGroup(
            editPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(editPanelLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(editName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(scroll2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13)
                .addComponent(lbdate1)
                .addContainerGap(8, Short.MAX_VALUE))
        );

        desc2.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        desc2.setForeground(new java.awt.Color(51, 51, 51));
        desc2.setText("*If you wish to add or edit event, submit a message on the admin.");

        desc1.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        desc1.setForeground(new java.awt.Color(51, 51, 51));
        desc1.setText("*You don't have authority to add or edit an event.");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(titleMenu2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(monthPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scroll, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(descPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(editPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(desc2)
                            .addComponent(desc1))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(titleMenu2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(monthPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(scroll)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(descPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(editPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(desc1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(desc2)
                        .addGap(20, 20, 20)))
                .addContainerGap(39, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void editActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editActionPerformed
        if (i%2 == 0) {
            edit.setText("Done");
            agendaDesc.setEditable(true);
            editPanel.setVisible(true);
            editName.setText(agendaName.getText());
            editDesc.setText(agendaDesc.getText());
            editName.grabFocus();
            i++;
        } else {
            if(editName.getText().length() > 20){
                lbdate1.setText("*The number of characters in the Event title should be less that 20.");
                lbdate1.setForeground(new Color(150,0,0));
            } else {
                edit.setText("Edit");
                editPanel.setVisible(false);
                editAgenda();
                eventPanel.removeAll();
                clearSelected();
                initMonth();
                eventPanel.revalidate();
                lbdate1.setText("*If you want to add an event or delete this event, go to 'Admin' tab.");
                lbdate1.setForeground(new Color(51,51,51));
                i++;
            }
        }
    }//GEN-LAST:event_editActionPerformed

    private void editAgenda(){
        try {
            PreparedStatement ps = (PreparedStatement) dbconn.prepareStatement("UPDATE agenda SET name=?, description=? WHERE agenda_id=?");
            ps.setString(1, editName.getText().trim());
            ps.setString(2, editDesc.getText().trim());
            ps.setInt(3, x);
            ps.executeUpdate();
            agendaName.setText(editName.getText().trim());
            agendaDesc.setText(editDesc.getText().trim());
        } catch (SQLException ex) {
            Logger.getLogger(Agenda_Form.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel agendaDate;
    public javax.swing.JTextArea agendaDesc;
    private javax.swing.JLabel agendaName;
    private javax.swing.JLabel desc1;
    private javax.swing.JLabel desc2;
    private javax.swing.JPanel descPanel;
    private csms.database.swing.Button edit;
    private javax.swing.JTextArea editDesc;
    private javax.swing.JTextField editName;
    private javax.swing.JPanel editPanel;
    private javax.swing.JPanel eventPanel;
    private javax.swing.JLabel lbdate;
    private javax.swing.JLabel lbdate1;
    private javax.swing.JPanel monthPanel;
    private javax.swing.JScrollPane scroll;
    private javax.swing.JScrollPane scroll1;
    private javax.swing.JScrollPane scroll2;
    private csms.database.agenda.swing.titleMenu titleMenu2;
    // End of variables declaration//GEN-END:variables
}
