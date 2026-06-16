package csms.login;

import csms.database.mainAdmin;
import csms.database.mainFaculty;
import java.sql.Connection;
import csms.database.mainViewer;
import csms.database.properties.SystemProperties;
import csms.databaseconnection.DBConnection;
import csms.login.component.Message;
import csms.login.component.PanelLoading;
import csms.login.component.PanelLoginandRegister;
import csms.login.component.fullreg;
import csms.login.component.panelcover;
import csms.login.model.ModelUser;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLayeredPane;
import net.miginfocom.swing.MigLayout;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTarget;
import org.jdesktop.animation.timing.TimingTargetAdapter;
import javax.swing.Timer;

/**
 *
 * @author Monthafer Barani
 */
public class main extends javax.swing.JFrame {

    public int getLginid() {
        return lginid;
    }

    private final DecimalFormat df = new DecimalFormat("##0.###", DecimalFormatSymbols.getInstance(Locale.US));
    private MigLayout layout;
    private panelcover cover;
    private PanelLoading loading;
    private fullreg reg;
    private PanelLoginandRegister loginAndRegister;
    private boolean isLogin;
    private final double addSize = 30;
    private final double coverSize = 40;
    private final double loginSize = 60;
    static Timer loadingtimer;
    static Timer delaytimer;
    static int y = 0;
    Connection dbconn = DBConnection.openConnection();
    private int lgin;
    private int lginid;

    public String first1, last1;

    public main() {
        initComponents();
        setShape(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 100, 100));
        init();
        try {
            BufferedImage image = ImageIO.read(getClass().getResourceAsStream("/csms/database/icon/logocsms.png"));
            Image icon = Toolkit.getDefaultToolkit().createImage(image.getSource());
            setIconImage(icon);
        } catch (IOException ex) {
            Logger.getLogger(Continue.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //Components after the public main
    private void init() {
        layout = new MigLayout("fill, insets 0");
        cover = new panelcover();
        loading = new PanelLoading();
        ActionListener eventRegister = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                register();
            }
        };
        ActionListener eventLogin = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                login();
            }
        };
        reg = new fullreg();
        loginAndRegister = new PanelLoginandRegister(eventRegister, eventLogin);
        TimingTarget target = new TimingTargetAdapter() {
            @Override
            public void timingEvent(float fraction) {
                double fractionCover;
                double fractionLogin;
                double size = coverSize;
                if (fraction <= 0.5f) {
                    size += fraction * addSize;
                } else {
                    size += addSize - fraction * addSize;
                }
                if (isLogin) {
                    fractionCover = 1f - fraction;
                    fractionLogin = fraction;
                    if (fraction >= 0.5f) {
                        cover.registerRight(fractionCover * 100);
                    } else {
                        cover.loginRight(fractionLogin * 100);
                    }
                } else {
                    fractionCover = fraction;
                    fractionLogin = 1f - fraction;
                    if (fraction <= 0.5f) {
                        cover.registerLeft(fraction * 100);
                    } else {
                        cover.loginLeft((1f - fraction) * 100);
                    }
                }
                if (fraction >= 0.5f) {
                    loginAndRegister.showRegister(isLogin);
                }
                fractionCover = Double.valueOf(df.format(fractionCover));
                fractionLogin = Double.valueOf(df.format(fractionLogin));
                layout.setComponentConstraints(cover, "width " + size + "%, pos " + fractionCover + "al 0 n 100%");
                layout.setComponentConstraints(loginAndRegister, "width " + loginSize + "%, pos " + fractionLogin + "al 0 n 100%");
                bg.revalidate();
            }
            @Override
            public void end() {
                isLogin = !isLogin;
            }
        };
        Animator animator = new Animator(800, target);
        animator.setAcceleration(0.5f);
        animator.setDeceleration(0.5f);
        animator.setResolution(0);
        bg.setLayout(layout);
        bg.setLayer(loading, JLayeredPane.POPUP_LAYER);
        bg.setLayer(reg, JLayeredPane.POPUP_LAYER);
        bg.add(loading, "pos 0 0 100% 100%");
        bg.add(reg, "pos 0 0 100% 100%");
        bg.add(cover, "width " + coverSize + "%, pos " + (isLogin ? "1al" : "0al") + " 0 n 100%");
        bg.add(loginAndRegister, "width " + loginSize + "%, pos " + (isLogin ? "0al" : "1al") + " 0 n 100%");
        cover.addEvent(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (!animator.isRunning()) {
                    animator.start();
                }
            }
        });
        cover.addEventexit(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                System.exit(0);
            }
        });
        reg.addEventButtonNext(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                ModelUser user = loginAndRegister.getUser();
                if (user.getPosition().equals("Student")) {
                    if (reg.getContact().isEmpty() || reg.getContact().equals("") || reg.getAddress().isEmpty() || reg.getAddress().equals("")
                            || reg.getBio().isEmpty() || reg.getBio().equals("")) {
                        showMessage(Message.MessageType.ERROR, "Please fill up the form properly!");
                    } else {
                        lgin = 1;
                        regStudent(user);
                    }
                } else if (user.getPosition().equals("Administrator")) {
                    if (reg.getContact().isEmpty() || reg.getContact().equals("") || reg.getAdminJob().isEmpty() || reg.getAdminJob().equals("")
                            || reg.getBio().isEmpty() || reg.getBio().equals("") || reg.getAdminJobLoc().isEmpty() || reg.getAdminJobLoc().equals("")
                            || reg.getAdminJobInfo().isEmpty() || reg.getAdminJobInfo().equals("")) {
                        showMessage(Message.MessageType.ERROR, "Please fill up the form properly!");
                    } else {
                        lgin = 2;
                        regAdmin(user);
                    }
                } else if (user.getPosition().equals("Teacher")) {
                    if (reg.getContact().isEmpty() || reg.getContact().equals("") || reg.getAddress().isEmpty() || reg.getAddress().equals("") || reg.getBio().isEmpty()
                            || reg.getBio().equals("") || reg.getConsultDays().isEmpty() || reg.getConsultDays().equals("")) {
                        showMessage(Message.MessageType.ERROR, "Please fill up the form properly!");
                    } else {
                        lgin = 3;
                        regTeacher(user);
                    }
                }
            }
        });
    }

    private void regTeacher(ModelUser user) {
        try {
            String contact = (String) reg.getContact();
            String address = (String) reg.getAddress();
            String fb = (String) reg.getFb();
            String bio = (String) reg.getBio();
            String dir = (String) reg.getDir();
            String consultH = (String) reg.getConsultHours();
            String consultD = (String) reg.getConsultDays();
            int adv = (Integer) reg.getAdvisoryClass();
            File f;
            InputStream is;

            PreparedStatement st = (PreparedStatement) dbconn.prepareStatement("INSERT INTO teacher(first_name,last_name,username,email,password,contact_no,address,facebook,tea_desc,img_avatar,consult_hours,consult_days) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)");

            st.setString(1, user.getFirstName());
            st.setString(2, user.getLastName());
            st.setString(3, user.getUserName());
            st.setString(4, user.getEmail());
            st.setString(5, user.getPassword());
            st.setString(6, contact);
            st.setString(7, address);
            if (fb.isEmpty() || fb.equals("") || fb == null) {
                st.setNull(8, java.sql.Types.VARCHAR);
            } else {
                st.setString(8, fb);
            }
            st.setString(9, bio);
            if (dir == null) {
                st.setNull(10, java.sql.Types.BLOB);
            } else {
                f = new File(dir);
                is = new FileInputStream(f);
                st.setBlob(10, is);
            }
            st.setString(11, consultH);
            st.setString(12, consultD);

            st.executeUpdate();
            st.close();
            reg.setVisible(false);

            if (adv != -1) {
                PreparedStatement st1 = (PreparedStatement) dbconn.prepareStatement("SELECT tea_id FROM teacher WHERE username=?");
                st1.setString(1, user.getUserName());
                ResultSet rset = st1.executeQuery();
                while (rset.next()) {
                    int teaId = rset.getInt("tea_id");
                    lginid = rset.getInt("tea_id");
                    PreparedStatement st2 = (PreparedStatement) dbconn.prepareStatement("UPDATE sgs SET teacher_adviser=? WHERE sgs_id=?");
                    st2.setInt(1, teaId);
                    st2.setInt(2, adv);
                    st2.executeUpdate();
                }
                rset.close();
            }

            Statement stmt = dbconn.createStatement();
            ResultSet rs2 = stmt.executeQuery("SELECT tea_id FROM teacher WHERE username='" + user.getUserName() + "'");
            if (rs2.next()) {
                lginid = rs2.getInt("tea_id");
            }
            SystemProperties pro = new SystemProperties();
            String pos = "Teacher";

            pro.save("id", String.valueOf(lginid));
            pro.save("position", pos);

            showMessage(Message.MessageType.SUCCESS, "Teacher Registered!");
            delaytimer = new Timer(2000, new ActionListener() {
                int delay = 0;
                @Override
                public void actionPerformed(ActionEvent e) {
                    delay++;
                    if (delay == 1) {
                        dispose();
                        new csms.loadingscreen.mainload(null, true).setVisible(true);
                        new mainFaculty().setVisible(true);
                    }
                }
            });
            delaytimer.setRepeats(true);
            delaytimer.start();

        } catch (FileNotFoundException | SQLException e) {
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, e);
            showMessage(Message.MessageType.ERROR, "Database connection is not available for today");
        }
    }

    private void regStudent(ModelUser user) {
        try {
            String contact = (String) reg.getContact();
            String address = (String) reg.getAddress();
            String fb = (String) reg.getFb();
            String bio = (String) reg.getBio();
            String dir = (String) reg.getDir();
            int sgs = (Integer) reg.getStudGrade();
            File f;
            InputStream is;

            PreparedStatement st = (PreparedStatement) dbconn.prepareStatement("INSERT INTO student(first_name,last_name,username,email,password,contact_no,address,facebook,student_desc,img_avatar,sgs_id) VALUES (?,?,?,?,?,?,?,?,?,?,?)");

            st.setString(1, user.getFirstName());
            st.setString(2, user.getLastName());
            st.setString(3, user.getUserName());
            st.setString(4, user.getEmail());
            st.setString(5, user.getPassword());
            st.setString(6, contact);
            st.setString(7, address);
            if (fb.isEmpty() || fb.equals("") || fb == null) {
                st.setNull(8, java.sql.Types.VARCHAR);
            } else {
                st.setString(8, fb);
            }
            st.setString(9, bio);
            if (dir == null) {
                st.setNull(10, java.sql.Types.BLOB);
            } else {
                f = new File(dir);
                is = new FileInputStream(f);
                st.setBlob(10, is);
            }
            st.setInt(11, sgs);

            st.executeUpdate();

            String sgs1 = "";
            PreparedStatement st1 = (PreparedStatement) dbconn.prepareStatement("SELECT stud_id, sgs_id FROM student WHERE username=?");
            st1.setString(1, user.getUserName());
            ResultSet rset = st1.executeQuery();
            while (rset.next()) {
                lginid = rset.getInt("stud_id");
                sgs1 = String.valueOf(rset.getInt("sgs_id"));
            }
            rset.close();
            st.close();

            SystemProperties pro = new SystemProperties();
            String pos = "Student";

            pro.save("id", String.valueOf(lginid));
            pro.save("position", pos);
            pro.save("sgs", sgs1);
            reg.setVisible(false);

            showMessage(Message.MessageType.SUCCESS, "Student Registered!");
            delaytimer = new Timer(2000, new ActionListener() {
                int delay = 0;

                @Override
                public void actionPerformed(ActionEvent e) {
                    delay++;
                    if (delay == 1) {
                        dispose();
                        new csms.loadingscreen.mainload(null, true).setVisible(true);
                        new mainViewer().setVisible(true);
                    }
                }
            });
            delaytimer.setRepeats(true);
            delaytimer.start();
        } catch (FileNotFoundException | SQLException e) {
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, e);
            showMessage(Message.MessageType.ERROR, "Database connection is not available for today");
        }
    }

    private void regAdmin(ModelUser user) {
        try {
            String contact = (String) reg.getContact();
            String fb = (String) reg.getFb();
            String bio = (String) reg.getBio();
            String dir = (String) reg.getDir();
            String job = (String) reg.getAdminJob();
            String jobInfo = (String) reg.getAdminJobInfo();
            String jobLoc = (String) reg.getAdminJobLoc();
            File f;
            InputStream is;

            PreparedStatement st = (PreparedStatement) dbconn.prepareStatement("INSERT INTO admin(first_name,last_name,username,email,password,contact_no,fbAcc,bio,img_avatar,job,job_location,job_info) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)");

            st.setString(1, user.getFirstName());
            st.setString(2, user.getLastName());
            st.setString(3, user.getUserName());
            st.setString(4, user.getEmail());
            st.setString(5, user.getPassword());
            st.setString(6, contact);
            if (fb.isEmpty() || fb.equals("") || fb == null) {
                st.setNull(7, java.sql.Types.VARCHAR);
            } else {
                st.setString(7, fb);
            }
            st.setString(8, bio);
            if (dir == null) {
                st.setNull(9, java.sql.Types.BLOB);
            } else {
                f = new File(dir);
                is = new FileInputStream(f);
                st.setBlob(9, is);
            }
            st.setString(10, job);
            st.setString(11, jobLoc);
            st.setString(12, jobInfo);

            st.executeUpdate();

            PreparedStatement st1 = (PreparedStatement) dbconn.prepareStatement("SELECT admin_id FROM admin WHERE username=?");
            st1.setString(1, user.getUserName());
            ResultSet rset = st1.executeQuery();
            while (rset.next()) {
                lginid = rset.getInt("admin_id");
            }

            SystemProperties pro = new SystemProperties();
            String pos = "Administrator";

            pro.save("id", String.valueOf(lginid));
            pro.save("position", pos);

            rset.close();
            st.close();
            reg.setVisible(false);

            showMessage(Message.MessageType.SUCCESS, "Administrator Registered!");
            delaytimer = new Timer(2000, new ActionListener() {
                int delay = 0;

                @Override
                public void actionPerformed(ActionEvent e) {
                    delay++;
                    if (delay == 1) {
                        dispose();
                        new csms.loadingscreen.mainload(null, true).setVisible(true);
                        new mainAdmin().setVisible(true);
                    }
                }
            });
            delaytimer.setRepeats(true);
            delaytimer.start();
        } catch (FileNotFoundException | SQLException e) {
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, e);
            showMessage(Message.MessageType.ERROR, "Database connection is not available for today");
        }
    }

    //register action performed
    private void register() {
        ModelUser user = loginAndRegister.getUser();
        int userRep = 0;
        int emailRep = 0;

        // check if all required fields are filled up
        if (user.getFirstName().isEmpty() || user.getLastName().isEmpty() || user.getPassword().isEmpty()
                || user.getRetype().isEmpty() || user.getUserName().isEmpty()
                || (!user.getPosition().equals("Teacher") && !user.getPosition().equals("Student")
                && !user.getPosition().equals("Administrator"))) {
            showMessage(Message.MessageType.ERROR, "Fill up the form properly!");

            // check if inputs have 50 or less characters
        } else if (user.getFirstName().length() > 50 || user.getLastName().length() > 50
                || user.getPassword().length() > 50 || user.getUserName().length() > 50) {
            showMessage(Message.MessageType.ERROR, "Input 50 or less characters!");

            // check if password is at least 8 characters long
        } else if (user.getPassword().length() < 8) {
            showMessage(Message.MessageType.ERROR, "The Password should be 8 or more characters!");

            // check if password and retype match
        } else if (!user.getPassword().equals(user.getRetype())) {
            showMessage(Message.MessageType.ERROR, "The password doesn't match!");

        } else {
            try {
                Statement stmt = dbconn.createStatement();

                // execute queries depending on user position
                if (user.getPosition().equals("Student")) {
                    ResultSet rs1 = stmt.executeQuery("SELECT stud_id FROM student WHERE username='" + user.getUserName() + "'");
                    while (rs1.next()) {
                        userRep = 1;
                    }
                    rs1.close();
                    ResultSet rs2 = stmt.executeQuery("SELECT stud_id FROM student WHERE email='" + user.getEmail() + "'");
                    while (rs2.next()) {
                        emailRep = 1;
                    }
                    rs2.close();

                } else if (user.getPosition().equals("Teacher")) {
                    ResultSet rs1 = stmt.executeQuery("SELECT tea_id FROM teacher WHERE username='" + user.getUserName() + "'");
                    while (rs1.next()) {
                        userRep = 1;
                    }
                    rs1.close();
                    ResultSet rs2 = stmt.executeQuery("SELECT tea_id FROM teacher WHERE email='" + user.getEmail() + "'");
                    while (rs2.next()) {
                        emailRep = 1;
                    }
                    rs2.close();

                } else if (user.getPosition().equals("Administrator")) {
                    ResultSet rs1 = stmt.executeQuery("SELECT admin_id FROM admin WHERE username='" + user.getUserName() + "'");
                    while (rs1.next()) {
                        userRep = 1;
                    }
                    rs1.close();
                    ResultSet rs2 = stmt.executeQuery("SELECT admin_id FROM admin WHERE email='" + user.getEmail() + "'");
                    while (rs2.next()) {
                        emailRep = 1;
                    }
                    rs2.close();

                }
                stmt.close();

            } catch (SQLException ex) {
                Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
            }

            // show error messages if username or email already exists
            if (userRep == 1) {
                showMessage(Message.MessageType.ERROR, "Username already taken!");
            } else if (emailRep == 1) {
                showMessage(Message.MessageType.ERROR, "Email already taken!");
            } else {
                loaddash(user); // load dashboard if user is successfully registered
            }
        }
    }

    private void loaddash(ModelUser user) {
        loading.setVisible(true);
        loadingtimer = new Timer(2000, new ActionListener() {
            int delay = 0;

            @Override
            public void actionPerformed(ActionEvent e) {
                delay++;
                if (delay == 1) {
                    reg.setVisible(true);
                    loading.setVisible(false);
                    if (user.getPosition().equals("Student")) {
                        reg.student();
                    } else if (user.getPosition().equals("Teacher")) {
                        reg.teacher();
                    } else if (user.getPosition().equals("Administrator")) {
                        reg.administrator();
                    }
                }
            }
        });
        loadingtimer.setRepeats(true);
        loadingtimer.start();
    }

    //login action performed
    private void login() {
        ModelUser user = loginAndRegister.getUser();
        lgin = 0;
        ImageIcon format = null;
        SystemProperties pro = new SystemProperties();

        try {
            Statement stmt = dbconn.createStatement();
            ResultSet rs4 = stmt.executeQuery("SELECT stud_id, first_name, last_name, img_avatar, sgs_id FROM student WHERE username='" + user.getUserName() + "' AND password='" + user.getPassword() + "'");
            if (rs4.next()) {
                lgin = 1;
                lginid = rs4.getInt("stud_id");
                first1 = rs4.getString("first_name");
                last1 = rs4.getString("last_name");
                String sgs = String.valueOf(rs4.getInt("sgs_id"));

                pro.save("id", String.valueOf(lginid));
                pro.save("position", "Student");
                pro.save("sgs", sgs);
            }
            rs4.close();
            ResultSet rs2 = stmt.executeQuery("SELECT tea_id, first_name, last_name, img_avatar FROM teacher WHERE username='" + user.getUserName() + "' AND password='" + user.getPassword() + "'");
            while (rs2.next()) {
                lgin = 2;
                lginid = rs2.getInt("tea_id");
                first1 = rs2.getString("first_name");
                last1 = rs2.getString("last_name");

                pro.save("id", lginid + "");
                pro.save("position", "Teacher");
            }
            rs2.close();
            ResultSet rs3 = stmt.executeQuery("SELECT admin_id, first_name, last_name, img_avatar FROM admin WHERE username='" + user.getUserName() + "' AND password='" + user.getPassword() + "'");
            if (rs3.next()) {
                lgin = 3;
                lginid = rs3.getInt("admin_id");
                first1 = rs3.getString("first_name");
                last1 = rs3.getString("last_name");

                pro.save("id", lginid + "");
                pro.save("position", "Administrator");
            }
            rs3.close();
        } catch (SQLException e) {
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, e);
            showMessage(Message.MessageType.ERROR, "There appears to be an issue with the database server.");
        }
        if (user.getUserName().isEmpty() || user.getPassword().isEmpty()) {
            showMessage(Message.MessageType.ERROR, "Please fill up the form properly");
        } else if (lgin == 0) {
            showMessage(Message.MessageType.ERROR, "Incorrect username or password.");
        } else {
            y++;
            if (y == 1) {
                delaytimer = new Timer(2000, new ActionListener() {
                    int delay = 0;

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        delay++;
                        if (delay == 1) {
                            dispose();
                            pro.loadFromFile();
                            if (pro.getPosition().equals("Student")) {
                                new csms.loadingscreen.mainload(null, true).setVisible(true);
                                new mainViewer().setVisible(true);
                                y = 0;
                            } else if (pro.getPosition().equals("Teacher")) {
                                new csms.loadingscreen.mainload(null, true).setVisible(true);
                                new mainFaculty().setVisible(true);
                                y = 0;
                            } else {
                                new csms.loadingscreen.mainload(null, true).setVisible(true);
                                new mainAdmin().setVisible(true);
                                y = 0;
                            }
                        }
                    }
                });
                delaytimer.setRepeats(true);
                delaytimer.start();
                showMessage(Message.MessageType.SUCCESS, "Login Successful!");
            } else {
                showMessage(Message.MessageType.ERROR, "You have Logged in!");
            }
        }
    }

    //pop-up message
    private void showMessage(Message.MessageType messageType, String message) {
        Message ms = new Message();
        ms.showMessage(messageType, message);
        TimingTarget target = new TimingTargetAdapter() {
            @Override
            public void begin() {
                if (!ms.isShow()) {
                    bg.add(ms, "pos 0.5al -30", 0);
                    ms.setVisible(true);
                    bg.repaint();
                }
            }

            @Override
            public void timingEvent(float fraction) {
                float f;
                if (ms.isShow()) {
                    f = 40 * (1f - fraction);
                } else {
                    f = 40 * fraction;
                }
                layout.setComponentConstraints(ms, "pos 0.5al " + (int) (f - 30));
                bg.repaint();
                bg.revalidate();
            }

            @Override
            public void end() {
                if (ms.isShow()) {
                    bg.remove(ms);
                    bg.repaint();
                    bg.revalidate();
                } else {
                    ms.setShow(true);
                }
            }
        };
        Animator animator = new Animator(300, target);
        animator.setResolution(0);
        animator.setAcceleration(0.5f);
        animator.setDeceleration(0.5f);
        animator.start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                    animator.start();
                } catch (InterruptedException e) {
                    System.err.println(e);
                }
            }
        }).start();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bg = new javax.swing.JLayeredPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        bg.setBackground(new java.awt.Color(255, 255, 255));
        bg.setOpaque(true);

        javax.swing.GroupLayout bgLayout = new javax.swing.GroupLayout(bg);
        bg.setLayout(bgLayout);
        bgLayout.setHorizontalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 900, Short.MAX_VALUE)
        );
        bgLayout.setVerticalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 600, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new main().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLayeredPane bg;
    // End of variables declaration//GEN-END:variables
}
