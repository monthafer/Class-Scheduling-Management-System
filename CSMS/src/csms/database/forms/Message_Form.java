package csms.database.forms;

import csms.database.component.Form;
import csms.database.message.component.ChatBox;
import csms.database.message.component.avatarOnPanel;
import csms.database.message.component.nameOnPanel;
import csms.database.message.model.ModelMessage;
import csms.database.message.swing.ChatEvent;
import csms.database.properties.SystemProperties;
import csms.database.scrollbar.ScrollBarCustom;
import csms.databaseconnection.DBConnection;
import java.sql.*;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.Timer;
import javax.swing.border.LineBorder;
import net.miginfocom.swing.MigLayout;

public class Message_Form extends Form {

    SystemProperties pro = new SystemProperties();
    SimpleDateFormat df= new SimpleDateFormat("yyyy/MM/dd, hh:mmaa");
    Connection dbconn = DBConnection.openConnection();
    String position;
    String id;
    String username, userid, userPosition;
    ImageIcon userAvatar;
    
    
    public Message_Form() {
        initComponents();
        initUser();
        init();
        initSortSgs();
        initUpdate();
        initChat();
        initSort();
    }
    
    private void init(){
        scroll.setOpaque(false);
        scroll.setBackground(new Color(0, 0, 0, 0));
        scroll.getViewport().setOpaque(false);
        scroll.setBorder(null);
        scroll.setViewportBorder(null);
        scroll.setHorizontalScrollBar(new ScrollBarCustom());
        
        scroll1.setOpaque(false);
        scroll1.setBackground(new Color(0, 0, 0, 0));
        scroll1.getViewport().setOpaque(false);
        scroll1.setBorder(new LineBorder(Color.BLACK, 1, true));
        scroll1.setViewportBorder(null);
        scroll1.setVerticalScrollBar(new ScrollBarCustom());
        
        namePanel.setOpaque(false);
        namePanel.setBackground(new Color(0,0,0,0));
        
        avatarPanel.setOpaque(false);
        avatarPanel.setBackground(new Color(0,0,0,0));
        
        avatarPanel.setLayout(new MigLayout());
        namePanel.setLayout(new MigLayout("wrap"));
        chatArea.setBorder(new LineBorder(Color.BLACK, 1, true));
        background1.setBorder(new LineBorder(new Color(0, 0, 0), 3, true));
    }
    
    private void initUpdate(){
        try {
            Statement stmt = dbconn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM teacher");
            while (rs.next()) {
                String name = rs.getString("first_name") + " " + rs.getString("last_name");
                String firstname = rs.getString("first_name");
                String id1 = String.valueOf(rs.getInt("tea_id"));
                byte[] imagedata = rs.getBytes("img_avatar");
                ImageIcon format;
                if (imagedata != null) {
                    format = new ImageIcon(imagedata);
                } else {
                    format = new ImageIcon(getClass().getResource("/csms/database/icon/avatarimg.png"));
                }
                
                avatarOnPanel avatar = new avatarOnPanel();
                avatar.avatar.setImage(format);
                avatar.namefull.setText(name);
                avatar.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        chatArea.clearChatBox();
                        chatArea.setTitle(name);
                        position = "Teacher";
                        id = id1;
                        chatHistoryTeacher(firstname, format);
                    }
                });
                nameOnPanel namepanel = new nameOnPanel();
                namepanel.avatar.setImage(format);
                namepanel.namefull.setText(name);
                namepanel.positionfull.setText("Teacher");
                namepanel.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        chatArea.clearChatBox();
                        chatArea.setTitle(name);
                        position = "Teacher";
                        id = id1;
                        chatHistoryTeacher(firstname, format);
                    }
                });
                avatarPanel.add(avatar);
                namePanel.add(namepanel);
            }
            rs.close();
            ResultSet rs1 = stmt.executeQuery("SELECT * FROM student");
            while (rs1.next()) {
                String name = rs1.getString("first_name") + " " + rs1.getString("last_name");
                String id1 = String.valueOf(rs1.getInt("stud_id"));
                String firstname = rs1.getString("first_name");
                byte[] imagedata = rs1.getBytes("img_avatar");
                ImageIcon format;
                if (imagedata != null) {
                    format = new ImageIcon(imagedata);
                } else {
                    format = new ImageIcon(getClass().getResource("/csms/database/icon/avatarimg.png"));
                }
                
                avatarOnPanel avatar = new avatarOnPanel();
                avatar.avatar.setImage(format);
                avatar.namefull.setText(name);
                avatar.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        chatArea.clearChatBox();
                        chatArea.setTitle(name);
                        position = "Student";
                        id = id1;
                        chatHistoryStudent(firstname, format);
                    }
                });
                nameOnPanel namepanel = new nameOnPanel();
                namepanel.avatar.setImage(format);
                namepanel.namefull.setText(name);
                namepanel.positionfull.setText("Student");
                namepanel.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        chatArea.clearChatBox();
                        chatArea.setTitle(name);
                        position = "Student";
                        id = id1;
                        chatHistoryStudent(firstname, format);
                    }
                });
                avatarPanel.add(avatar);
                namePanel.add(namepanel);
            }
            rs1.close();
            ResultSet rs2 = stmt.executeQuery("SELECT * FROM admin");
            while (rs2.next()) {
                String name = rs2.getString("first_name") + " " + rs2.getString("last_name");
                String id1 = rs2.getString("admin_id");
                String firstname = rs2.getString("first_name");
                byte[] imagedata = rs2.getBytes("img_avatar");
                ImageIcon format;
                if (imagedata != null) {
                    format = new ImageIcon(imagedata);
                } else {
                    format = new ImageIcon(getClass().getResource("/csms/database/icon/avatarimg.png"));
                }
                
                avatarOnPanel avatar = new avatarOnPanel();
                avatar.avatar.setImage(format);
                avatar.namefull.setText(name);
                avatar.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        chatArea.clearChatBox();
                        chatArea.setTitle(name);
                        position = "Administrator";
                        id = id1;
                        chatHistoryAdmin(firstname, format);
                    }
                });
                nameOnPanel namepanel = new nameOnPanel();
                namepanel.avatar.setImage(format);
                namepanel.namefull.setText(name);
                namepanel.positionfull.setText("Administrator");
                namepanel.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        chatArea.clearChatBox();
                        chatArea.setTitle(name);
                        position = "Administrator";
                        id = id1;
                        chatHistoryAdmin(firstname, format);
                    }
                });
                avatarPanel.add(avatar);
                namePanel.add(namepanel);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Message_Form.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void initChat(){
        chatArea.setTitle("Monthafer Barani");
        chatArea.addChatEvent(new ChatEvent(){
            @Override
            public void mousePressedSendButton(ActionEvent evt){
                String date = df.format(new Date());
                String message = chatArea.getText().trim();
                if(!message.equals("")){
                    chatArea.addChatBox(new ModelMessage(userAvatar, username, date, message), ChatBox.BoxType.RIGHT);
                    chatArea.clearTextAndGrabFocus();
                    sendMessage(message);
                }
            }
            
            @Override
            public void mousePressedFileButton(ActionEvent evt) {
            }
            
            @Override
            public void keyTyped(KeyEvent evt) {
            }
        });
    }
    
    private void sendMessage(String message){
        pro.loadFromFile();
        try {
            if(pro.getPosition().equals("Student")){
                if (position.equals("Student")){
                    PreparedStatement ps = (PreparedStatement) 
                            dbconn.prepareStatement("INSERT INTO message(send_stud,rec_stud,message) VALUES (?,?,?)");
                    ps.setString(1, userid);
                    ps.setString(2, id);
                    ps.setString(3, message);
                    ps.executeUpdate();
                } else if(position.equals("Teacher")){
                    PreparedStatement ps = (PreparedStatement) 
                            dbconn.prepareStatement("INSERT INTO message(send_stud,rec_tea,message) VALUES (?,?,?)");
                    ps.setString(1, userid);
                    ps.setString(2, id);
                    ps.setString(3, message);
                    ps.executeUpdate();
                } else{
                    PreparedStatement ps = (PreparedStatement) 
                            dbconn.prepareStatement("INSERT INTO message(send_stud,rec_admin,message) VALUES (?,?,?)");
                    ps.setString(1, userid);
                    ps.setString(2, id);
                    ps.setString(3, message);
                    ps.executeUpdate();
                }
            } else if(pro.getPosition().equals("Teacher")){
                if (position.equals("Student")){
                    PreparedStatement ps = (PreparedStatement) 
                            dbconn.prepareStatement("INSERT INTO message(send_tea,rec_stud,message) VALUES (?,?,?)");
                    ps.setString(1, userid);
                    ps.setString(2, id);
                    ps.setString(3, message);
                    ps.executeUpdate();
                } else if(position.equals("Teacher")){
                    PreparedStatement ps = (PreparedStatement) 
                            dbconn.prepareStatement("INSERT INTO message(send_tea,rec_tea,message) VALUES (?,?,?)");
                    ps.setString(1, userid);
                    ps.setString(2, id);
                    ps.setString(3, message);
                    ps.executeUpdate();
                } else{
                    PreparedStatement ps = (PreparedStatement) 
                            dbconn.prepareStatement("INSERT INTO message(send_tea,rec_admin,message) VALUES (?,?,?)");
                    ps.setString(1, userid);
                    ps.setString(2, id);
                    ps.setString(3, message);
                    ps.executeUpdate();
                }
            } else{
                if (position.equals("Student")){
                    PreparedStatement ps = (PreparedStatement) 
                            dbconn.prepareStatement("INSERT INTO message(send_admin,rec_stud,message) VALUES (?,?,?)");
                    ps.setString(1, userid);
                    ps.setString(2, id);
                    ps.setString(3, message);
                    ps.executeUpdate();
                } else if(position.equals("Teacher")){
                    PreparedStatement ps = (PreparedStatement) 
                            dbconn.prepareStatement("INSERT INTO message(send_admin,rec_tea,message) VALUES (?,?,?)");
                    ps.setString(1, userid);
                    ps.setString(2, id);
                    ps.setString(3, message);
                    ps.executeUpdate();
                } else{
                    PreparedStatement ps = (PreparedStatement) 
                            dbconn.prepareStatement("INSERT INTO message(send_admin,rec_admin,message) VALUES (?,?,?)");
                    ps.setString(1, userid);
                    ps.setString(2, id);
                    ps.setString(3, message);
                    ps.executeUpdate();
                }
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Message_Form.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void initUser(){
        try {
            pro.loadFromFile();
            userid = pro.getId();
            userPosition = pro.getPosition();
            Statement state = dbconn.createStatement();
            if (pro.getPosition().equals("Student")) {
                ResultSet reset = state.executeQuery("SELECT * FROM student WHERE stud_id=" + pro.getId());
                reset.next();
                username = reset.getString("first_name");
                byte[] imagedata = reset.getBytes("img_avatar");
                if (imagedata != null) {
                    userAvatar = new ImageIcon(imagedata);
                } else {
                    userAvatar = new ImageIcon(getClass().getResource("/csms/database/icon/avatarimg.png"));
                }
            } else if (pro.getPosition().equals("Teacher")) {
                ResultSet reset = state.executeQuery("SELECT * FROM teacher WHERE tea_id=" + pro.getId());
                reset.next();
                username = reset.getString("first_name");
                byte[] imagedata = reset.getBytes("img_avatar");
                if (imagedata != null) {
                    userAvatar = new ImageIcon(imagedata);
                } else {
                    userAvatar = new ImageIcon(getClass().getResource("/csms/database/icon/avatarimg.png"));
                }
            } else if (pro.getPosition().equals("Administrator")) {
                ResultSet reset = state.executeQuery("SELECT * FROM admin WHERE admin_id=" + pro.getId());
                reset.next();
                username = reset.getString("first_name");
                byte[] imagedata = reset.getBytes("img_avatar");
                if (imagedata != null) {
                    userAvatar = new ImageIcon(imagedata);
                } else {
                    userAvatar = new ImageIcon(getClass().getResource("/csms/database/icon/avatarimg.png"));
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Message_Form.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void chatHistoryTeacher(String firstname, ImageIcon format){
        try {
            Statement stm = dbconn.createStatement();
            pro.loadFromFile();
            ResultSet rs;
            if (pro.getPosition().equals("Student")){
                rs = stm.executeQuery("SELECT * FROM message WHERE (send_tea=" + id + " AND rec_stud=" + userid + ") OR (send_stud=" + userid + " AND rec_tea=" + id + ")");
            } else if (pro.getPosition().equals("Teacher")){
                rs = stm.executeQuery("SELECT * FROM message WHERE (send_tea=" + id + " AND rec_tea=" + userid + ") OR (send_tea=" + userid + " AND rec_tea=" + id + ")");
            } else {
                rs = stm.executeQuery("SELECT * FROM message WHERE (send_tea=" + id + " AND rec_admin=" + userid + ") OR (send_admin=" + userid + " AND rec_tea=" + id + ")");
            }
            while(rs.next()){
                String check = String.valueOf(rs.getInt("send_tea"));
                String check2 = String.valueOf(rs.getInt("rec_tea"));
                if(check.equals(id)){
                    String mes = rs.getString("message");
                    String datetime = df.format(rs.getTimestamp("time"));
                    chatArea.addChatBox(new ModelMessage(format, firstname, datetime, mes), ChatBox.BoxType.LEFT);
                } else if(check2.equals(id)){
                    String mes = rs.getString("message");
                    String datetime = df.format(rs.getTimestamp("time"));
                    chatArea.addChatBox(new ModelMessage(userAvatar, username, datetime, mes), ChatBox.BoxType.RIGHT);
                }
            }
            rs.close();
            stm.close();
        } catch (SQLException ex) {
            Logger.getLogger(Message_Form.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void chatHistoryStudent(String firstname, ImageIcon format){
        try {
            Statement stm = dbconn.createStatement();
            ResultSet rs;
            if (pro.getPosition().equals("Student")){
                rs = stm.executeQuery("SELECT * FROM message WHERE (send_stud=" + id + " AND rec_stud=" + userid + ") OR (send_stud=" + userid + " AND rec_stud=" + id + ")");
            } else if (pro.getPosition().equals("Teacher")){
                rs = stm.executeQuery("SELECT * FROM message WHERE (send_stud=" + id + " AND rec_tea=" + userid + ") OR (send_tea=" + userid + " AND rec_stud=" + id + ")");
            } else {
                rs = stm.executeQuery("SELECT * FROM message WHERE (send_stud=" + id + " AND rec_admin=" + userid + ") OR (send_admin=" + userid + " AND rec_stud=" + id + ")");
            }
            while(rs.next()){
                String check = String.valueOf(rs.getInt("send_stud"));
                String check2 = String.valueOf(rs.getInt("rec_stud"));
                if(check.equals(id)){
                    String mes = rs.getString("message");
                    String datetime = df.format(rs.getTimestamp("time"));
                    chatArea.addChatBox(new ModelMessage(format, firstname, datetime, mes), ChatBox.BoxType.LEFT);
                } else if(check2.equals(id)){
                    String mes = rs.getString("message");
                    String datetime = df.format(rs.getTimestamp("time"));
                    chatArea.addChatBox(new ModelMessage(userAvatar, username, datetime, mes), ChatBox.BoxType.RIGHT);
                }
            }
            rs.close();
            stm.close();
        } catch (SQLException ex) {
            Logger.getLogger(Message_Form.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void chatHistoryAdmin(String firstname, ImageIcon format){
        try {
            Statement stm = dbconn.createStatement();
            ResultSet rs;
            if (pro.getPosition().equals("Student")){
                rs = stm.executeQuery("SELECT * FROM message WHERE (send_admin=" + id + " AND rec_stud=" + userid + ") OR (send_stud=" + userid + " AND rec_admin=" + id + ")");
            } else if (pro.getPosition().equals("Teacher")){
                rs = stm.executeQuery("SELECT * FROM message WHERE (send_admin=" + id + " AND rec_tea=" + userid + ") OR (send_tea=" + userid + " AND rec_admin=" + id + ")");
            } else {
                rs = stm.executeQuery("SELECT * FROM message WHERE (send_admin=" + id + " AND rec_admin=" + userid + ") OR (send_admin=" + userid + " AND rec_admin=" + id + ")");
            }
            while(rs.next()){
                String check = String.valueOf(rs.getInt("send_admin"));
                String check2 = String.valueOf(rs.getInt("rec_admin"));
                if (check.equals(id)) {
                    String mes = rs.getString("message");
                    String datetime = df.format(rs.getTimestamp("time"));
                    chatArea.addChatBox(new ModelMessage(format, firstname, datetime, mes), ChatBox.BoxType.LEFT);
                } else if (check2.equals(id)) {
                    String mes = rs.getString("message");
                    String datetime = df.format(rs.getTimestamp("time"));
                    chatArea.addChatBox(new ModelMessage(userAvatar, username, datetime, mes), ChatBox.BoxType.RIGHT);
                }
            }
            rs.close();
            stm.close();
        } catch (SQLException ex) {
            Logger.getLogger(Message_Form.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void initSort() {
        sortChoice.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int s = sortChoice.getSelectedIndex();
                namePanel.removeAll();
                try {
                    if (s == 0) {
                        Statement stmt = dbconn.createStatement();
                        ResultSet rs = stmt.executeQuery("SELECT * FROM teacher");
                        while (rs.next()) {
                            String name = rs.getString("first_name") + " " + rs.getString("last_name");
                            String firstname = rs.getString("first_name");
                            String id1 = String.valueOf(rs.getInt("tea_id"));
                            byte[] imagedata = rs.getBytes("img_avatar");
                            ImageIcon format;
                            if (imagedata != null) {
                                format = new ImageIcon(imagedata);
                            } else {
                                format = new ImageIcon(getClass().getResource("/csms/database/icon/avatarimg.png"));
                            }
                            nameOnPanel namepanel = new nameOnPanel();
                            namepanel.avatar.setImage(format);
                            namepanel.namefull.setText(name);
                            namepanel.positionfull.setText("Teacher");
                            namepanel.addMouseListener(new java.awt.event.MouseAdapter() {
                                public void mouseClicked(java.awt.event.MouseEvent evt) {
                                    chatArea.clearChatBox();
                                    chatArea.setTitle(name);
                                    position = "Teacher";
                                    id = id1;
                                    chatHistoryTeacher(firstname, format);
                                }
                            });
                            namePanel.add(namepanel);
                        }
                        rs.close();
                        ResultSet rs1 = stmt.executeQuery("SELECT * FROM student");
                        while (rs1.next()) {
                            String name = rs1.getString("first_name") + " " + rs1.getString("last_name");
                            String id1 = String.valueOf(rs1.getInt("stud_id"));
                            String firstname = rs1.getString("first_name");
                            byte[] imagedata = rs1.getBytes("img_avatar");
                            ImageIcon format;
                            if (imagedata != null) {
                                format = new ImageIcon(imagedata);
                            } else {
                                format = new ImageIcon(getClass().getResource("/csms/database/icon/avatarimg.png"));
                            }
                            nameOnPanel namepanel = new nameOnPanel();
                            namepanel.avatar.setImage(format);
                            namepanel.namefull.setText(name);
                            namepanel.positionfull.setText("Student");
                            namepanel.addMouseListener(new java.awt.event.MouseAdapter() {
                                public void mouseClicked(java.awt.event.MouseEvent evt) {
                                    chatArea.clearChatBox();
                                    chatArea.setTitle(name);
                                    position = "Student";
                                    id = id1;
                                    chatHistoryStudent(firstname, format);
                                }
                            });
                            namePanel.add(namepanel);
                        }
                        rs1.close();
                        ResultSet rs2 = stmt.executeQuery("SELECT * FROM admin");
                        while (rs2.next()) {
                            String name = rs2.getString("first_name") + " " + rs2.getString("last_name");
                            String id1 = rs2.getString("admin_id");
                            String firstname = rs2.getString("first_name");
                            byte[] imagedata = rs2.getBytes("img_avatar");
                            ImageIcon format;
                            if (imagedata != null) {
                                format = new ImageIcon(imagedata);
                            } else {
                                format = new ImageIcon(getClass().getResource("/csms/database/icon/avatarimg.png"));
                            }
                            nameOnPanel namepanel = new nameOnPanel();
                            namepanel.avatar.setImage(format);
                            namepanel.namefull.setText(name);
                            namepanel.positionfull.setText("Administrator");
                            namepanel.addMouseListener(new java.awt.event.MouseAdapter() {
                                public void mouseClicked(java.awt.event.MouseEvent evt) {
                                    chatArea.clearChatBox();
                                    chatArea.setTitle(name);
                                    position = "Administrator";
                                    id = id1;
                                    chatHistoryAdmin(firstname, format);
                                }
                            });
                            namePanel.add(namepanel);
                        }
                        if(sortSgs.isVisible()){
                            sortSgs.setVisible(false);
                        }
                    } else if (s == 1) {
                        Statement stmt = dbconn.createStatement();
                        ResultSet rs1 = stmt.executeQuery("SELECT * FROM student ORDER BY first_name");
                        while (rs1.next()) {
                            String name = rs1.getString("first_name") + " " + rs1.getString("last_name");
                            String id1 = String.valueOf(rs1.getInt("stud_id"));
                            String firstname = rs1.getString("first_name");
                            byte[] imagedata = rs1.getBytes("img_avatar");
                            ImageIcon format;
                            if (imagedata != null) {
                                format = new ImageIcon(imagedata);
                            } else {
                                format = new ImageIcon(getClass().getResource("/csms/database/icon/avatarimg.png"));
                            }
                            nameOnPanel namepanel = new nameOnPanel();
                            namepanel.avatar.setImage(format);
                            namepanel.namefull.setText(name);
                            namepanel.positionfull.setText("Student");
                            namepanel.addMouseListener(new java.awt.event.MouseAdapter() {
                                public void mouseClicked(java.awt.event.MouseEvent evt) {
                                    chatArea.clearChatBox();
                                    chatArea.setTitle(name);
                                    position = "Student";
                                    id = id1;
                                    chatHistoryStudent(firstname, format);
                                }
                            });
                            namePanel.add(namepanel);
                        }
                        rs1.close();
                        stmt.close();
                        if(!sortSgs.isVisible()){
                            sortSgs.setVisible(true);
                        }
                    } else if (s == 2) {
                        Statement stmt = dbconn.createStatement();
                        ResultSet rs = stmt.executeQuery("SELECT * FROM teacher ORDER BY first_name");
                        while (rs.next()) {
                            String name = rs.getString("first_name") + " " + rs.getString("last_name");
                            String firstname = rs.getString("first_name");
                            String id1 = String.valueOf(rs.getInt("tea_id"));
                            byte[] imagedata = rs.getBytes("img_avatar");
                            ImageIcon format;
                            if (imagedata != null) {
                                format = new ImageIcon(imagedata);
                            } else {
                                format = new ImageIcon(getClass().getResource("/csms/database/icon/avatarimg.png"));
                            }
                            nameOnPanel namepanel = new nameOnPanel();
                            namepanel.avatar.setImage(format);
                            namepanel.namefull.setText(name);
                            namepanel.positionfull.setText("Teacher");
                            namepanel.addMouseListener(new java.awt.event.MouseAdapter() {
                                public void mouseClicked(java.awt.event.MouseEvent evt) {
                                    chatArea.clearChatBox();
                                    chatArea.setTitle(name);
                                    position = "Teacher";
                                    id = id1;
                                    chatHistoryTeacher(firstname, format);
                                }
                            });
                            namePanel.add(namepanel);
                        }
                        rs.close();
                        stmt.close();
                        if(sortSgs.isVisible()){
                            sortSgs.setVisible(false);
                        }
                    } else if (s == 3) {
                        Statement stmt = dbconn.createStatement();
                        ResultSet rs2 = stmt.executeQuery("SELECT * FROM admin ORDER BY first_name");
                        while (rs2.next()) {
                            String name = rs2.getString("first_name") + " " + rs2.getString("last_name");
                            String id1 = rs2.getString("admin_id");
                            String firstname = rs2.getString("first_name");
                            byte[] imagedata = rs2.getBytes("img_avatar");
                            ImageIcon format;
                            if (imagedata != null) {
                                format = new ImageIcon(imagedata);
                            } else {
                                format = new ImageIcon(getClass().getResource("/csms/database/icon/avatarimg.png"));
                            }
                            nameOnPanel namepanel = new nameOnPanel();
                            namepanel.avatar.setImage(format);
                            namepanel.namefull.setText(name);
                            namepanel.positionfull.setText("Administrator");
                            namepanel.addMouseListener(new java.awt.event.MouseAdapter() {
                                public void mouseClicked(java.awt.event.MouseEvent evt) {
                                    chatArea.clearChatBox();
                                    chatArea.setTitle(name);
                                    position = "Administrator";
                                    id = id1;
                                    chatHistoryAdmin(firstname, format);
                                }
                            });
                            namePanel.add(namepanel);
                        }
                        rs2.close();
                        stmt.close();
                        if(sortSgs.isVisible()){
                            sortSgs.setVisible(false);
                        }
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(Message_Form.class.getName()).log(Level.SEVERE, null, ex);
                }
                namePanel.repaint();
                namePanel.revalidate();
            }
        });
    }
    
    private void initSortSgs(){
        sortSgs.setHint("Sort by class");
        try {
            Statement st = dbconn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM sgs");
            while (rs.next()){
                String sgsChoice = rs.getString("strand") + " " + String.valueOf(rs.getInt("grade")) + "-" + rs.getString("section");
                sortSgs.addItem(sgsChoice);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Student_Form.class.getName()).log(Level.SEVERE, null, ex);
        }
        sortSgs.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                int p = sortSgs.getSelectedIndex();
                namePanel.removeAll();
                try {
                    Statement stmt = dbconn.createStatement();
                    ResultSet rs1 = stmt.executeQuery("SELECT * FROM student WHERE sgs_id=" + p + " ORDER BY first_name");
                    while (rs1.next()) {
                        String name = rs1.getString("first_name") + " " + rs1.getString("last_name");
                        String id1 = String.valueOf(rs1.getInt("stud_id"));
                        String firstname = rs1.getString("first_name");
                        byte[] imagedata = rs1.getBytes("img_avatar");
                        ImageIcon format;
                        if (imagedata != null) {
                            format = new ImageIcon(imagedata);
                        } else {
                            format = new ImageIcon(getClass().getResource("/csms/database/icon/avatarimg.png"));
                        }
                        nameOnPanel namepanel = new nameOnPanel();
                        namepanel.avatar.setImage(format);
                        namepanel.namefull.setText(name);
                        namepanel.positionfull.setText("Student");
                        namepanel.addMouseListener(new java.awt.event.MouseAdapter() {
                            public void mouseClicked(java.awt.event.MouseEvent evt) {
                                chatArea.clearChatBox();
                                chatArea.setTitle(name);
                                position = "Student";
                                id = id1;
                                chatHistoryStudent(firstname, format);
                            }
                        });
                        namePanel.add(namepanel);
                    }
                    rs1.close();
                    stmt.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Student_Form.class.getName()).log(Level.SEVERE, null, ex);
                }
                namePanel.repaint();
                namePanel.revalidate();
            }
        });
        sortSgs.setSelectedIndex(-1);
        sortSgs.setVisible(false);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        background1 = new csms.database.message.swing.Background();
        chatArea = new csms.database.message.component.ChatArea();
        jPanel1 = new javax.swing.JPanel();
        scroll = new javax.swing.JScrollPane();
        avatarPanel = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        scroll1 = new javax.swing.JScrollPane();
        namePanel = new javax.swing.JPanel();
        sortChoice = new csms.login.swing.Choice();
        txtSortBy = new javax.swing.JLabel();
        sortSgs = new csms.login.swing.Choice();

        javax.swing.GroupLayout background1Layout = new javax.swing.GroupLayout(background1);
        background1.setLayout(background1Layout);
        background1Layout.setHorizontalGroup(
            background1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, background1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(chatArea, javax.swing.GroupLayout.DEFAULT_SIZE, 309, Short.MAX_VALUE)
                .addContainerGap())
        );
        background1Layout.setVerticalGroup(
            background1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(background1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(chatArea, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel1.setOpaque(false);

        scroll.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        javax.swing.GroupLayout avatarPanelLayout = new javax.swing.GroupLayout(avatarPanel);
        avatarPanel.setLayout(avatarPanelLayout);
        avatarPanelLayout.setHorizontalGroup(
            avatarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 554, Short.MAX_VALUE)
        );
        avatarPanelLayout.setVerticalGroup(
            avatarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 152, Short.MAX_VALUE)
        );

        scroll.setViewportView(avatarPanel);

        jPanel2.setOpaque(false);

        scroll1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        javax.swing.GroupLayout namePanelLayout = new javax.swing.GroupLayout(namePanel);
        namePanel.setLayout(namePanelLayout);
        namePanelLayout.setHorizontalGroup(
            namePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 554, Short.MAX_VALUE)
        );
        namePanelLayout.setVerticalGroup(
            namePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 362, Short.MAX_VALUE)
        );

        scroll1.setViewportView(namePanel);

        sortChoice.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "<default>", "Student", "Teacher", "Adminstrator" }));

        txtSortBy.setForeground(new java.awt.Color(153, 153, 153));
        txtSortBy.setText("Sort by:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(scroll1, javax.swing.GroupLayout.DEFAULT_SIZE, 536, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(txtSortBy)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(sortChoice, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(43, 43, 43)
                        .addComponent(sortSgs, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(sortSgs, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(sortChoice, javax.swing.GroupLayout.PREFERRED_SIZE, 38, Short.MAX_VALUE)
                        .addComponent(txtSortBy, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scroll1, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scroll, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scroll)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(background1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(background1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 4, Short.MAX_VALUE)))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel avatarPanel;
    private csms.database.message.swing.Background background1;
    private csms.database.message.component.ChatArea chatArea;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel namePanel;
    private javax.swing.JScrollPane scroll;
    private javax.swing.JScrollPane scroll1;
    private csms.login.swing.Choice sortChoice;
    private csms.login.swing.Choice sortSgs;
    private javax.swing.JLabel txtSortBy;
    // End of variables declaration//GEN-END:variables
}
