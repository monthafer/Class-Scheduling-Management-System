package csms.login.component;

import csms.database.properties.SystemProperties;
import csms.login.model.ModelUser;
import csms.login.swing.Button;
import csms.login.swing.Choice;
import csms.login.swing.MyPasswordField;
import csms.login.swing.MyTextField;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author Monthafer Barani
 */
public class PanelLoginandRegister extends javax.swing.JLayeredPane {


    public ModelUser getUser() {
        return user;
    }
    
    private ModelUser user;
    SystemProperties pro = new SystemProperties();
    
    public PanelLoginandRegister(){
        
    }
    
    public PanelLoginandRegister(ActionListener eventRegister, ActionListener eventLogin) {
        initComponents();
        pro.loadFromFile();
        initRegister(eventRegister);
        initLogin(eventLogin);
        login.setVisible(false);
        register.setVisible(true);
    }

    //register page
    private void initRegister(ActionListener eventRegister) {
        txtFirst = new MyTextField();
        txtLast = new MyTextField();
        txtEmail = new MyTextField();
        txtRegUser = new MyTextField();
        txtRegPass = new MyPasswordField();
        MyPasswordField txtConfirm = new MyPasswordField();
        Button cmd = new Button();
        
        register.setLayout(new MigLayout("wrap", "push[center]push", "push[]25[]10[]10[]10[]10[]10[]10[]25[]push"));
        JLabel label = new JLabel("Create Account");
        label.setFont(new Font("sanserif", 1, 30));
        label.setForeground(pro.getColor());//new Color(7, 175, 90)
        register.add(label);
        
        
        txtFirst.setPrefixIcon(new ImageIcon(getClass().getResource("/csms/login/icon/user.png")));
        txtFirst.setHint("First name");
        register.add(txtFirst, "w 60%");
        txtFirst.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                if(evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_DOWN){
                    txtLast.grabFocus();
                }
            }
        });
        
        txtLast.setPrefixIcon(new ImageIcon(getClass().getResource("/csms/login/icon/user.png")));
        txtLast.setHint("Last name");
        register.add(txtLast, "w 60%");
        txtLast.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                if(evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_DOWN){
                    txtEmail.grabFocus();
                }
                else if(evt.getKeyCode() == KeyEvent.VK_UP){
                    txtFirst.grabFocus();
                }
            }
        });
        
        txtEmail.setPrefixIcon(new ImageIcon(getClass().getResource("/csms/login/icon/email.png")));
        txtEmail.setHint("Email Address");
        register.add(txtEmail, "w 60%");
        txtEmail.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                if(evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_DOWN){
                    txtRegUser.grabFocus();
                }
                else if(evt.getKeyCode() == KeyEvent.VK_UP){
                    txtLast.grabFocus();
                }
            }
        });
        
        txtPosition = new Choice();
        txtPosition.setHint("Position");
        String[] positionchoice = {"Student", "Teacher", "Administrator"};
        txtPosition.setModel(new javax.swing.DefaultComboBoxModel<>(positionchoice));
        txtPosition.setSelectedItem(null);
        txtPosition.setPrefixIcon(new ImageIcon(getClass().getResource("/csms/login/icon/position.png")));
        txtPosition.setForeground(Color.decode("#7A8C8D"));
        register.add(txtPosition, "w 60%, h 10");
        
        txtRegUser.setPrefixIcon(new ImageIcon(getClass().getResource("/csms/login/icon/mail.png")));
        txtRegUser.setHint("Username");
        register.add(txtRegUser, "w 60%");
        txtRegUser.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                if(evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_DOWN){
                    txtRegPass.grabFocus();
                }
                else if(evt.getKeyCode() == KeyEvent.VK_UP){
                    txtEmail.grabFocus();
                }
            }
        });
        
        txtRegPass.setPrefixIcon(new ImageIcon(getClass().getResource("/csms/login/icon/pass.png")));
        txtRegPass.setHint("Password");
        register.add(txtRegPass, "w 60%");
        txtRegPass.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                if(evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_DOWN){
                    txtConfirm.grabFocus();
                }
                else if(evt.getKeyCode() == KeyEvent.VK_UP){
                    txtRegUser.grabFocus();
                }
            }
        });
        
        txtConfirm.setPrefixIcon(new ImageIcon(getClass().getResource("/csms/login/icon/pass.png")));
        txtConfirm.setHint("Re-type your Password");
        register.add(txtConfirm, "w 60%");
        txtConfirm.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                if(evt.getKeyCode() == KeyEvent.VK_UP){
                    txtRegPass.grabFocus();
                }
            }
        });
        
        cmd.setBackground(pro.getColor());//new Color(7, 175, 90)
        cmd.setForeground(new Color(250, 250, 250));
        cmd.addActionListener(eventRegister);
        cmd.setText("SIGN UP");
        register.add(cmd, "w 40%, h 40");
        cmd.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                String firstName = txtFirst.getText().trim();
                String lastName = txtLast.getText().trim();
                String email = txtEmail.getText().trim();
                String position = String.valueOf(txtPosition.getSelectedItem());
                String userName = txtRegUser.getText().trim();
                String password = String.valueOf(txtRegPass.getPassword());
                String retype = String.valueOf(txtConfirm.getPassword());
                user = new ModelUser(0, firstName, lastName, email, position, userName, password, retype);
            }
        });
    }
    
    //login page
    private void initLogin(ActionListener eventLogin){
        MyTextField txtEmail = new MyTextField();
        MyPasswordField txtPass = new MyPasswordField();
        
        login.setLayout(new MigLayout("wrap", "push[center]push", "push[]25[]10[]10[]25[]push"));
        JLabel label = new JLabel("Sign In");
        label.setFont(new Font("sansserif", 1, 30));
        label.setForeground(pro.getColor()); // new Color(7, 175, 90)
        login.add(label);
        
        txtEmail.setPrefixIcon(new ImageIcon(getClass().getResource("/csms/login/icon/mail.png")));
        txtEmail.setHint("Username");
        login.add(txtEmail, "w 60%");
        txtEmail.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                if(evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_DOWN){
                    txtPass.grabFocus();
                }
            }
        });
        
        txtPass.setPrefixIcon(new ImageIcon(getClass().getResource("/csms/login/icon/pass.png")));
        txtPass.setHint("Password");
        login.add(txtPass, "w 60%");
        txtPass.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                if(evt.getKeyCode() == KeyEvent.VK_UP){
                    txtEmail.grabFocus();
                }
            }
        });
        
        JButton cmdForget = new JButton("Forgot your password ?");
        cmdForget.setForeground(new Color(100, 100, 100));
        cmdForget.setFont(new Font("sansserif", 1, 12));
        cmdForget.setContentAreaFilled(false);
        cmdForget.setCursor(new Cursor(Cursor.HAND_CURSOR));
        login.add(cmdForget);
        
        Button cmd = new Button();
        cmd.setBackground(pro.getColor()); // new Color(7, 175, 90)
        cmd.setForeground(new Color(250, 250, 250));
        cmd.setText("SIGN IN");
        cmd.addActionListener(eventLogin);
        login.add(cmd, "w 40%, h 40");
        cmd.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                String userName = txtEmail.getText().trim();
                String password = String.valueOf(txtPass.getPassword());
                user = new ModelUser(userName, password);
            }
        });
    }
    
    public String getLRPosition(){
        return (String) txtPosition.getSelectedItem();
    }
    
    public void showRegister(boolean show) {
        if (show) {
            register.setVisible(true);
            login.setVisible(false);
        } else {
            register.setVisible(false);
            login.setVisible(true);
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        login = new javax.swing.JPanel();
        register = new javax.swing.JPanel();

        setLayout(new java.awt.CardLayout());

        login.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout loginLayout = new javax.swing.GroupLayout(login);
        login.setLayout(loginLayout);
        loginLayout.setHorizontalGroup(
            loginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 301, Short.MAX_VALUE)
        );
        loginLayout.setVerticalGroup(
            loginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        add(login, "card3");

        register.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout registerLayout = new javax.swing.GroupLayout(register);
        register.setLayout(registerLayout);
        registerLayout.setHorizontalGroup(
            registerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 301, Short.MAX_VALUE)
        );
        registerLayout.setVerticalGroup(
            registerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        add(register, "card2");
    }// </editor-fold>//GEN-END:initComponents
    
    public MyTextField txtFirst;
    public MyTextField txtLast;
    public MyTextField txtEmail;
    public MyTextField txtRegUser;
    public MyPasswordField txtRegPass;
    public Choice txtPosition;
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel login;
    private javax.swing.JPanel register;
    // End of variables declaration//GEN-END:variables
}
