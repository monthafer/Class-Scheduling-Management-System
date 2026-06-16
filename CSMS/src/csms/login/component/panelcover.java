package csms.login.component;

import csms.database.properties.SystemProperties;
import csms.login.swing.ButtonOutLine;
import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import javax.swing.JLabel;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author Monthafer Barani
 */
public class panelcover extends javax.swing.JPanel {

    private final DecimalFormat df = new DecimalFormat("##0.###", DecimalFormatSymbols.getInstance(Locale.US));
    private ActionListener event;
    private ActionListener event1;
    private MigLayout layout;
    private JLabel title;
    private JLabel description;
    private JLabel description1;
    private ButtonOutLine button;
    private ButtonOutLine buttonexit;
    SystemProperties pro = new SystemProperties();
    
    private boolean isLogin;
    
    public panelcover() {
        initComponents();
        setOpaque(false);
        layout = new MigLayout("wrap, fill", "[center]", "push[]25[]3[]25[]10[]push");
        setLayout(layout);
        init();
    }

    private void init() {
        
        title = new JLabel("Welcome to CSMS!");
        title.setFont(new Font("century gothic", 1, 30));
        title.setForeground(new Color(20,20,20));
        add(title);
        
        description = new JLabel("Ready to join our journey?");
        description.setFont(new Font("century gothic", 0, 12));
        description.setForeground(Color.BLACK);
        add(description);
        
        description1 = new JLabel("Give us your deets and let's get started!");
        description1.setFont(new Font("century gothic", 0, 12));
        description1.setForeground(Color.BLACK);
        add(description1);
        
        button = new ButtonOutLine();
        button.setFont(new Font("century gothic", 0, 12));
        button.setBackground(Color.BLACK);
        button.setForeground(Color.BLACK);
        button.setText("SIGN IN?");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                event.actionPerformed(ae);
            }
        });
        add(button, "w 60%, h 40");
        
        buttonexit = new ButtonOutLine();
        buttonexit.setFont(new Font("century gothic", 0, 12));
        buttonexit.setBackground(Color.RED.darker()); //new Color(231, 180, 14)
        buttonexit.setForeground(Color.BLACK);
        buttonexit.setText("EXIT");
        buttonexit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                event1.actionPerformed(ae);
            }
        });
        add(buttonexit, "w 30%, h 30");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 301, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents
    
    @Override
    protected void paintComponent(Graphics grphcs){
        pro.loadFromFile();
        Graphics2D g2 = (Graphics2D) grphcs;
        GradientPaint gra = new GradientPaint(0, 0, pro.getColor() , 0, getHeight(), pro.getColor().brighter());
        // First Color: new Color(200, 53, 53) second Color: new Color(113, 41, 41)
        g2.setPaint(pro.getColor().brighter());
        g2.fillRect (0, 0, getWidth(), getHeight());
        RoundRectangle2D roundRect = new RoundRectangle2D.Float(
            0, 0, getWidth(), getHeight(), 100, 100);
        g2.setClip(roundRect);
        super.paintComponent(grphcs);
    }
    
    public void addEvent(ActionListener event){
        this.event = event;
    }
    
    public void addEventexit(ActionListener event1){
        this.event1 = event1;
    }
    
    public void registerLeft(double v) {
        v = Double.valueOf(df.format(v));
        login(false);
        layout.setComponentConstraints(title, "pad 0 -" + v + "% 0 0");
        layout.setComponentConstraints(description, "pad 0 -" + v + "% 0 0");
        layout.setComponentConstraints(description1, "pad 0 -" + v + "% 0 0");
    }

    public void registerRight(double v) {
        v = Double.valueOf(df.format(v));
        login(false);
        layout.setComponentConstraints(title, "pad 0 -" + v + "% 0 0");
        layout.setComponentConstraints(description, "pad 0 -" + v + "% 0 0");
        layout.setComponentConstraints(description1, "pad 0 -" + v + "% 0 0");
    }

    public void loginLeft(double v) {
        v = Double.valueOf(df.format(v));
        login(true);
        layout.setComponentConstraints(title, "pad 0 " + v + "% 0 " + v + "%");
        layout.setComponentConstraints(description, "pad 0 " + v + "% 0 " + v + "%");
        layout.setComponentConstraints(description1, "pad 0 " + v + "% 0 " + v + "%");
    }

    public void loginRight(double v) {
        v = Double.valueOf(df.format(v));
        login(true);
        layout.setComponentConstraints(title, "pad 0 " + v + "% 0 " + v + "%");
        layout.setComponentConstraints(description, "pad 0 " + v + "% 0 " + v + "%");
        layout.setComponentConstraints(description1, "pad 0 " + v + "% 0 " + v + "%");
    }
    
    public void login(boolean login) {
        if (this.isLogin != login) {
            if (login) {
                title.setText("Oh, Welcome Back!");
                description.setText("Keep connected with us by logging in");
                description1.setText("using your personal information.");
                button.setText("SIGN UP?");
            } else {
                title.setText("Welcome to CSMS!");
                description.setText("Ready to join our journey?");
                description1.setText("Give us your deets and let's get started!");
                button.setText("SIGN IN?");
            }
            this.isLogin = login;
        }
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
