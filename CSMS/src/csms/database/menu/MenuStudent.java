package csms.database.menu;

import csms.database.properties.SystemProperties;
import csms.database.swing.MenuButton;
import csms.database.theme.SystemTheme;
import csms.databaseconnection.DBConnection;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Area;
import java.awt.geom.RoundRectangle2D;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import net.miginfocom.swing.MigLayout;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTarget;
import org.jdesktop.animation.timing.TimingTargetAdapter;
import org.jdesktop.animation.timing.interpolation.PropertySetter;

public class MenuStudent extends javax.swing.JPanel {
    
    public ImageIcon getFormat() {
        return format;
    }

    public void setFormat(ImageIcon format) {
        this.format = format;
    }
    
    public int getSelectedLocation() {
        return selectedLocation;
    }

    public void setSelectedLocation(int selectedLocation) {
        this.selectedLocation = selectedLocation;
        repaint();
    }

    public void addEventMenu(EventMenu event) {
        this.events.add(event);
    }
    
    private int selectedIndex = 0;
    private Animator animator;
    private TimingTarget target;
    private int selectedLocation = 156; //151
    private int targetLocation;
    private List<EventMenu> events = new ArrayList<>();
    Connection dbconn = DBConnection.openConnection();
    private ImageIcon format;

    public MenuStudent() {
        initComponents();
        setOpaque(false);
        setBackground(Color.WHITE);
        menu.setLayout(new MigLayout("fillx, wrap, inset 0", "[fill]", "[fill, 36!]0[fill, 36!]"));
        initMenu();
        initName();
    }
    
    private void initName(){
        SystemProperties pro = new SystemProperties();
        pro.loadFromFile();
        position.setText(pro.getPosition());
        try {
            Statement stmt = dbconn.createStatement();
            if ("Student".equals(pro.getPosition())) {
                ResultSet rs = stmt.executeQuery("SELECT first_name, last_name, img_avatar FROM student WHERE stud_id=" + pro.getId());
                while (rs.next()) {
                    String first = rs.getString("first_name");
                    String last = rs.getString("last_name");
                    String name1 = (first + " " + last);
                    name.setText(name1);
                    byte[] imagedata = rs.getBytes("img_avatar");
                    if (imagedata != null) {
                        setFormat(new ImageIcon(imagedata));
                        imageAvatar.setImage(getFormat());
                    } else {
                        setFormat(new ImageIcon(getClass().getResource("/csms/database/icon/avatarimg.png")));
                        imageAvatar.setImage(new ImageIcon(getClass().getResource("/csms/database/icon/avatarimg.png")));
                    }
                }
                rs.close();
            } else if ("Teacher".equals(pro.getPosition())) {
                ResultSet rs = stmt.executeQuery("SELECT first_name, last_name, img_avatar FROM teacher WHERE tea_id=" + pro.getId());
                while (rs.next()) {
                    String first = rs.getString("first_name");
                    String last = rs.getString("last_name");
                    String name1 = (first + " " + last);
                    name.setText(name1);
                    byte[] imagedata = rs.getBytes("img_avatar");
                    if (imagedata != null) {
                        setFormat(new ImageIcon(imagedata));
                        imageAvatar.setImage(getFormat());
                    } else {
                        setFormat(new ImageIcon(getClass().getResource("/csms/database/icon/avatarimg.png")));
                        imageAvatar.setImage(new ImageIcon(getClass().getResource("/csms/database/icon/avatarimg.png")));
                    }
                }
            rs.close();
            } else if("Administrator".equals(pro.getPosition())){
                ResultSet rs = stmt.executeQuery("SELECT first_name, last_name, img_avatar FROM admin WHERE admin_id=" + pro.getId());
                while (rs.next()) {
                    String first = rs.getString("first_name");
                    String last = rs.getString("last_name");
                    String name1 = (first + " " + last);
                    name.setText(name1);
                    byte[] imagedata = rs.getBytes("img_avatar");
                    if (imagedata != null) {
                        setFormat(new ImageIcon(imagedata));
                        imageAvatar.setImage(getFormat());
                    } else {
                        setFormat(new ImageIcon(getClass().getResource("/csms/database/icon/avatarimg.png")));
                        imageAvatar.setImage(new ImageIcon(getClass().getResource("/csms/database/icon/avatarimg.png")));
                    }
                }
                rs.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void initMenu() {
        addMenu("Home", "1", 0);
        addMenu("Calendar", "2", 1);
        addMenu("Timetable", "3", 2);
        addMenu("Subject", "4", 3);
        addMenu("Teacher", "5", 4);
        addMenu("Agenda", "2", 5);
        addMenu("Announcement", "7", 6);
        addMenu("Room", "4", 7);
        addMenu("Message", "8", 8);
        addMenu("Setting", "9", 9);
        //addMenu(" ", "10", 9);
        //  add more menu here
        menu.repaint();
        menu.revalidate();
        setSelectedMenu(0);
        animator = new Animator(300);
        animator.addTarget(new TimingTargetAdapter() {
            @Override
            public void begin() {
                clearSelected();
            }

            @Override
            public void end() {
                setSelectedMenu(selectedIndex);
                runEvent();
            }
        });
        animator.setDeceleration(.5f);
        animator.setAcceleration(.5f);
        animator.setResolution(0);
    }
    
    private void addMenu(String menuName, String icon, int index) {
        MenuButton m = new MenuButton();
        m.setIcoName(icon);
        m.setIcon(new ImageIcon(getClass().getResource("/csms/database/icon/" + icon + ".png")));
        m.setFont(m.getFont().deriveFont(Font.BOLD, 12));
        m.setForeground(new Color(127, 127, 127));
        m.setHorizontalAlignment(JButton.LEFT);
        m.setText("  " + menuName);
        m.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (index != selectedIndex) {
                    if (animator.isRunning()) {
                        animator.stop();
                    }
                    int y = m.getY() + menu.getY();
                    targetLocation = y;
                    selectedIndex = index;
                    animator.removeTarget(target);
                    target = new PropertySetter(MenuStudent.this, "selectedLocation", selectedLocation, targetLocation);
                    animator.addTarget(target);
                    animator.start();
                }
            }
        });
        menu.add(m);
    }
    
    @Override
    protected void paintComponent(Graphics grphcs) {
        Graphics2D g2 = (Graphics2D) grphcs.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        int y = selectedLocation;
        g2.setColor(SystemTheme.mainColor);
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
    
    private void clearSelected() {
        for (Component com : menu.getComponents()) {
            if (com instanceof MenuButton) {
                MenuButton c = (MenuButton) com;
                c.setForeground(new Color(127, 127, 127));
                c.setEffectColor(new Color(173, 173, 173));
                if (!c.getIcoName().contains("_s")) {
                    c.setIcon(new ImageIcon(getClass().getResource("/csms/database/icon/" + c.getIcoName() + ".png")));
                }
            }
        }
    }

    public void setSelectedMenu(int index) {
        MenuButton cmd = (MenuButton) menu.getComponent(index);
        cmd.setForeground(Color.WHITE);
        cmd.setEffectColor(Color.WHITE);
        cmd.setIcon(new ImageIcon(getClass().getResource("/csms/database/icon/" + cmd.getIcoName() + "_s.png")));
    }

    private void runEvent() {
        for (EventMenu event : events) {
            event.selectedMenu(selectedIndex);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        imageAvatar = new csms.database.theme.ImageAvatar();
        name = new javax.swing.JLabel();
        position = new javax.swing.JLabel();
        menu = new javax.swing.JPanel();

        imageAvatar.setImage(new javax.swing.ImageIcon(getClass().getResource("/csms/database/icon/avatarimg.png"))); // NOI18N

        name.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        name.setForeground(new java.awt.Color(117, 117, 117));
        name.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        name.setText("Monthafer Barani");

        position.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        position.setForeground(new java.awt.Color(154, 154, 154));
        position.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        position.setText("Administrator");

        menu.setOpaque(false);

        javax.swing.GroupLayout menuLayout = new javax.swing.GroupLayout(menu);
        menu.setLayout(menuLayout);
        menuLayout.setHorizontalGroup(
            menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        menuLayout.setVerticalGroup(
            menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 502, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(imageAvatar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(name, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(position, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(menu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(imageAvatar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(name)
                .addGap(1, 1, 1)
                .addComponent(position)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(menu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private csms.database.theme.ImageAvatar imageAvatar;
    private javax.swing.JPanel menu;
    private javax.swing.JLabel name;
    private javax.swing.JLabel position;
    // End of variables declaration//GEN-END:variables
}
