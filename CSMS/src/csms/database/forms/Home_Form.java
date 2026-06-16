package csms.database.forms;

import csms.database.component.Form;
import csms.database.message.component.homeMessage;
import csms.database.properties.SystemProperties;
import csms.database.theme.ThemeColorChange;
import csms.databaseconnection.DBConnection;
import java.awt.Color;
import java.util.Calendar;
import java.util.Date;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.time.*;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import net.miginfocom.swing.MigLayout;


public class Home_Form extends Form {
    
    int second, minute, hour;
    Timer timer;
    Connection dbconn = DBConnection.openConnection();
    private int currentSubjectId = -1;
    private String currentSubjectName = "";
    private LocalDateTime currentSubjectEndTime;
    SystemProperties pro = new SystemProperties();
    
    public Home_Form() {
        initComponents();
        init();
        
        updateAgenda();
        unreadMessage();
        updateSchedule();
        startCountdown();
    }
    
    private void init(){
        //startTimer();
        if(ThemeColorChange.getInstance().getMode() == ThemeColorChange.Mode.DARK){
            txtHeader.setForeground(new Color(230,230,230));
            txtTimer.setForeground(new Color(230,230,230));
            txtTeacher.setForeground(new Color(230,230,230));
            txtTitle.setForeground(new Color(230,230,230));
            txtday.setForeground(new Color(230,230,230));
            txtdesc.setForeground(new Color(230,230,230));
            txtloc.setForeground(new Color(230,230,230));
            txttime.setForeground(new Color(230,230,230));
            lbTeacher.setForeground(new Color(230,230,230));
            lbday.setForeground(new Color(230,230,230));
            lbdesc.setForeground(new Color(230,230,230));
            lbloc.setForeground(new Color(230,230,230));
            lbtime.setForeground(new Color(230,230,230));
        } else{
            txtHeader.setForeground(new Color(80,80,80));
            txtTimer.setForeground(new Color(80,80,80));
            txtTeacher.setForeground(new Color(80,80,80));
            txtTitle.setForeground(new Color(80,80,80));
            txtday.setForeground(new Color(80,80,80));
            txtdesc.setForeground(new Color(80,80,80));
            txtloc.setForeground(new Color(80,80,80));
            txttime.setForeground(new Color(80,80,80));
            lbTeacher.setForeground(new Color(80,80,80));
            lbday.setForeground(new Color(80,80,80));
            lbdesc.setForeground(new Color(80,80,80));
            lbloc.setForeground(new Color(80,80,80));
            lbtime.setForeground(new Color(80,80,80));
        }
        unreadPanel.setLayout(new MigLayout("wrap"));
    }
    
    private void updateAgenda(){
        agendaPanel.setLayout(new MigLayout("width 288!, height 408!, wrap", "push[center]push")); //408, 288
        try {
            SimpleDateFormat sqldate = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat sql1 = new SimpleDateFormat("MMMM dd, yyyy");
            String datenow = sqldate.format(new Date());
            Statement state = dbconn.createStatement();
            ResultSet rst1 = state.executeQuery("SELECT * FROM `agenda` WHERE `date` = '" + datenow + "' LIMIT 5");
            if(rst1.next()){
                String aName = rst1.getString("name");
                String aDate = sqldate.format(rst1.getDate("date"));
                JLabel lbNow = new JLabel();
                JLabel txtName = new JLabel();
                JLabel txtDate = new JLabel();
                lbNow.setText("Today's Event:");
                txtName.setText(aName);
                txtDate.setText(sql1.format(rst1.getDate("date")));
                lbNow.setFont(new java.awt.Font("SansSerif", 0, 11));
                txtName.setFont(new java.awt.Font("SansSerif", 1, 15));
                txtDate.setFont(new java.awt.Font("SansSerif", 0, 11));
                agendaPanel.add(lbNow);
                agendaPanel.add(txtName, "w 80%, h 20");
                agendaPanel.add(txtDate, "w 80%, h 20");
            }
            JLabel lbNow = new JLabel();
            lbNow.setText("Upcoming Events:");
            lbNow.setFont(new java.awt.Font("SansSerif", 0, 11));
            agendaPanel.add(lbNow);
            rst1.close();
            ResultSet rst = state.executeQuery("SELECT * FROM `agenda` WHERE `date` > '" + datenow + "' LIMIT 5");
            while(rst.next()){
                String aName = rst.getString("name");
                String aDate = sqldate.format(rst.getDate("date"));
                
                JLabel txtName = new JLabel();
                JLabel txtDate = new JLabel();
                
                txtName.setText(aName);
                txtDate.setText(sql1.format(rst.getDate("date")));
                
                txtName.setFont(new java.awt.Font("SansSerif", 1, 15));
                txtDate.setFont(new java.awt.Font("SansSerif", 0, 11));
                
                agendaPanel.add(txtName, "w 80%, h 20");
                agendaPanel.add(txtDate, "w 80%, h 20");
            }
            rst.close();
            state.close();
        } catch (SQLException ex) {
            Logger.getLogger(Home_Form.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void startTimer(){
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.HOUR_OF_DAY, 23);
                calendar.set(Calendar.MINUTE, 45);
                calendar.set(Calendar.SECOND, 0);
                calendar.set(Calendar.MILLISECOND, 0);

                Date now = new Date();
                long diff = calendar.getTimeInMillis() - now.getTime();

                if (diff <= 0) {
                    timer.cancel();
                } else {
                    long hours = diff / (60 * 60 * 1000);
                    long minutes = (diff / (60 * 1000)) % 60;
                    long seconds = (diff / 1000) % 60;
                    txtTimer.setText(String.format("%02d : %02d : %02d", hours, minutes, seconds));
                }
            }
        }, 0, 1000);
    }

    private void updateSchedule() {
        try {
            // Get the current time
            LocalDateTime currentTime = LocalDateTime.now();
            pro.loadFromFile();

            // Find the next subject from the database
            PreparedStatement stmt
                    = dbconn.prepareStatement("SELECT * FROM subject WHERE ((day1 = ? AND (hour1 >= ? AND min1 <=?) OR hour2 >= ?)) AND sgs_id = ? ORDER BY hour1, min1 LIMIT 1");
            stmt.setString(1, currentTime.getDayOfWeek().name());
            stmt.setInt(2, currentTime.getHour());
            stmt.setInt(3, currentTime.getMinute());
            stmt.setInt(4, currentTime.getHour());
            stmt.setInt(5, Integer.parseInt(pro.getSgs()));
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int subjectId = rs.getInt("sub_id");
                String subjectName = rs.getString("name");
                int hourStart = rs.getInt("hour1");
                int minuteStart = rs.getInt("min1");
                int hourEnd = rs.getInt("hour2");
                int minuteEnd = rs.getInt("min2");
                int tea_id = rs.getInt("tea_id");
                int loc_id = rs.getInt("loc_id");
                String description = rs.getString("desc");

                Statement st = dbconn.createStatement();
                ResultSet rs1 = st.executeQuery("SELECT * FROM teacher WHERE tea_id=" + tea_id);
                String teacherName=null;
                if(rs1.next()){
                    teacherName = (rs1.getString("first_name") + " " + rs1.getString("last_name"));
                }
                rs1.close();

                ResultSet rs2 = st.executeQuery("SELECT * FROM location WHERE loc_id=" + loc_id);
                String locationName=null;
                if(rs2.next()){
                    locationName = rs2.getString("dep") + " " + rs2.getString("room");
                }
                rs2.close();
                st.close();

                LocalDateTime subjectStartTime = LocalDateTime.of(currentTime.toLocalDate(), LocalTime.of(hourStart, minuteStart));
                LocalDateTime subjectEndTime = LocalDateTime.of(currentTime.toLocalDate(), LocalTime.of(hourEnd, minuteEnd));

                // Update the labels
                txtTeacher.setVisible(true);
                txtTimer.setVisible(true);
                txttime.setVisible(true);
                txtday.setVisible(true);
                txtloc.setVisible(true);
                txtdesc.setVisible(true);
                lbTeacher.setVisible(true);
                lbtime.setVisible(true);
                lbday.setVisible(true);
                lbloc.setVisible(true);
                lbdesc.setVisible(true);

                txtTitle.setText(subjectName + " will end on:");
                txtTeacher.setText(teacherName);
                txttime.setText(formatTime(hourStart, minuteStart) + " - " + formatTime(hourEnd, minuteEnd));
                txtday.setText(rs.getString("day1") + (rs.getString("day2") != null ? ", " + rs.getString("day2") : ""));
                txtloc.setText(locationName);
                txtdesc.setText(description);

                if (currentSubjectId != subjectId) {
                    currentSubjectId = subjectId;
                    currentSubjectName = subjectName;
                    currentSubjectEndTime = subjectEndTime;
                    // Reset the timer
                    txtTimer.setText(formatDuration(Duration.between(currentTime, subjectStartTime)));
                }

                // Update the window title
                txtHeader.setText("Class Schedule - '" + subjectName + "'");

            } else {
                // No subjects are scheduled at this time
                txtTitle.setText("No class right now");

                txtTeacher.setVisible(false);
                txttime.setVisible(false);
                txtday.setVisible(false);
                txtloc.setVisible(false);
                txtdesc.setVisible(false);
                lbTeacher.setVisible(false);
                lbtime.setVisible(false);
                lbday.setVisible(false);
                lbloc.setVisible(false);
                lbdesc.setVisible(false);

                // Find the next subject from the database
                stmt = dbconn.prepareStatement("SELECT * FROM subject WHERE (day1 = ? AND hour1 > ? OR (day2 = ? AND hour1 >= ? AND min1 >= ?)) AND sgs_id=? ORDER BY hour1, min1 LIMIT 1");
                stmt.setString(1, currentTime.getDayOfWeek().name());
                stmt.setInt(2, currentTime.getHour());
                stmt.setString(3, currentTime.getDayOfWeek().name());
                stmt.setInt(4, currentTime.getHour());
                stmt.setInt(5, currentTime.getMinute());
                stmt.setInt(6, Integer.parseInt(pro.getSgs()));
                rs = stmt.executeQuery();

                if (rs.next()) {
                    String nextSubjectName = rs.getString("name");
                    int hourStart = rs.getInt("hour1");
                    int minuteStart = rs.getInt("min1");

                    LocalDateTime nextSubjectStartTime = LocalDateTime.of(currentTime.toLocalDate(), LocalTime.of(hourStart, minuteStart));

                    // Update the timer
                    currentSubjectEndTime = nextSubjectStartTime;
                    txtTimer.setText(formatDuration(Duration.between(currentTime, nextSubjectStartTime)));

                    // Update the window title
                    txtHeader.setText("Class Schedule - Next: '" + nextSubjectName + "'");

                } else {
                    // No subjects are scheduled today
                    txtHeader.setText("Class Schedule - No classes today");
                    txtTimer.setVisible(false);
                }
            }
            Calendar cal = Calendar.getInstance();
            cal.setTime(new Date());
            PreparedStatement stat = dbconn.prepareStatement("SELECT * FROM agenda WHERE MONTH(date)=? AND DAY(date)=? LIMIT 1");
            stat.setInt(1, (cal.get(Calendar.MONTH) + 1));
            stat.setInt(2, cal.get(Calendar.DATE));
            ResultSet sr = stat.executeQuery();
            if (sr.next()){
                if (sr.getString("repeatable").equals("no")) {
                    PreparedStatement stat1 = dbconn.prepareStatement("SELECT * FROM agenda WHERE MONTH(date)=? AND DAY(date)=? AND YEAR(date)=? LIMIT 1");
                    stat1.setInt(1, (cal.get(Calendar.MONTH) + 1));
                    stat1.setInt(2, cal.get(Calendar.DATE));
                    stat1.setInt(3, cal.get(Calendar.YEAR));
                    ResultSet sr1 = stat1.executeQuery();
                    if (sr1.next()) {
                        txtHeader.setText("Today is " + sr.getString("name") + "!");
                        txtTitle.setText("Go to Agenda tab to see detailed info for today's event/holiday.");
                        txtTeacher.setVisible(false);
                        txttime.setVisible(false);
                        txtTimer.setVisible(false);
                        txtday.setVisible(false);
                        txtloc.setVisible(false);
                        txtdesc.setVisible(false);
                        lbTeacher.setVisible(false);
                        lbtime.setVisible(false);
                        lbday.setVisible(false);
                        lbloc.setVisible(false);
                        lbdesc.setVisible(false);
                    }
                } else{
                    txtHeader.setText("It's " + sr.getString("name") + " today!");
                    txtTitle.setText("Go to Agenda tab to see detailed info for today's event/holiday.");
                    txtTeacher.setVisible(false);
                    txttime.setVisible(false);
                    txtTimer.setVisible(false);
                    txtday.setVisible(false);
                    txtloc.setVisible(false);
                    txtdesc.setVisible(false);
                    lbTeacher.setVisible(false);
                    lbtime.setVisible(false);
                    lbday.setVisible(false);
                    lbloc.setVisible(false);
                    lbdesc.setVisible(false);
                }
            }
            revalidate();
        } catch (SQLException ex) {
            Logger.getLogger(Home_Form.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void startCountdown() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                LocalDateTime currentTime = LocalDateTime.now();

                if (currentSubjectEndTime != null && currentTime.isAfter(currentSubjectEndTime)) {
                    // The current subject has ended
                    updateSchedule();

                } else {
                    // Update the timer
                    txtTimer.setText(formatDuration(Duration.between(currentTime, currentSubjectEndTime)));
                }
            }
        }, 0, 1000);
    }
    
    private String formatTime(int hour, int minute) {
        return String.format("%02d:%02d", hour, minute);
    }

    private String formatDuration(Duration duration) {
        long hours = duration.toHours();
        long minutes = duration.toMinutes() % 60;
        long seconds = duration.getSeconds() % 60;

        if (hours > 0) {
            return String.format("%d:%02d:%02d", hours, minutes, seconds);
        } else {
            return String.format("%02d:%02d", minutes, seconds);
        }
    }
    
    private void unreadMessage(){
        pro.loadFromFile();
        SimpleDateFormat df= new SimpleDateFormat("MMMM dd, yyyy  hh:mm aa"); // yyyy/MM/dd, hh:mmaa
        try {
            Statement st = dbconn.createStatement();
            ResultSet rs;
            if(pro.getPosition().equals("Student")){
                rs = st.executeQuery("SELECT * FROM message WHERE rec_stud=" + pro.getId() + " ORDER BY time DESC LIMIT 1");
            } else if(pro.getPosition().equals("Teacher")){
                rs = st.executeQuery("SELECT * FROM message WHERE rec_tea=" + pro.getId() + " ORDER BY time DESC LIMIT 1");
            } else{
                rs = st.executeQuery("SELECT * FROM message WHERE rec_admin=" + pro.getId() + " ORDER BY time DESC LIMIT 1");
            }
            
            if(rs.next()){
                String message = rs.getString("message");
                String stud = String.valueOf(rs.getInt("send_stud"));
                String tea = String.valueOf(rs.getInt("send_tea"));
                String admin = String.valueOf(rs.getInt("send_admin"));
                String date = df.format(rs.getTimestamp("time"));
                String sendUser = "";
                byte[] imagedata;
                ImageIcon format = null;
                
                if (stud != null || !stud.equals("") || !stud.equals("0")) {
                    Statement st1 = dbconn.createStatement();
                    ResultSet rs1 = st1.executeQuery("SELECT * FROM student WHERE stud_id=" + stud);
                    if (rs1.next()) {
                        sendUser = rs1.getString("first_name") + " " + rs1.getString("last_name");
                        imagedata = rs1.getBytes("img_avatar");
                        if (imagedata != null) {
                            format = new ImageIcon(imagedata);
                        } else {
                            format = new ImageIcon(getClass().getResource("/csms/database/icon/avatarimg.png"));
                        }
                    }
                    rs1.close();
                    st1.close();
                }
           
                if (tea != null && !tea.equals("") && !tea.equals("0")) {
                    Statement st1 = dbconn.createStatement();
                    ResultSet rs1 = st1.executeQuery("SELECT * FROM teacher WHERE tea_id=" + tea);
                    if (rs1.next()) {
                        sendUser = rs1.getString("first_name") + " " + rs1.getString("last_name");
                        imagedata = rs1.getBytes("img_avatar");
                        if (imagedata != null) {
                            format = new ImageIcon(imagedata);
                        } else {
                            format = new ImageIcon(getClass().getResource("/csms/database/icon/avatarimg.png"));
                        }
                    }
                    rs1.close();
                    st1.close();
                }
  
                if (admin != null || !admin.equals("") || !admin.equals("0")) {
                    Statement st1 = dbconn.createStatement();
                    ResultSet rs1 = st1.executeQuery("SELECT * FROM admin WHERE admin_id=" + admin);
                    if (rs1.next()) {
                        sendUser = rs1.getString("first_name") + " " + rs1.getString("last_name");
                        imagedata = rs1.getBytes("img_avatar");
                        if (imagedata != null) {
                            format = new ImageIcon(imagedata);
                        } else {
                            format = new ImageIcon(getClass().getResource("/csms/database/icon/avatarimg.png"));
                        }
                    }
                    rs1.close();
                    st1.close();
                }
                JLabel noMessage = new JLabel();
                noMessage.setText("Recent Messages:");
                noMessage.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                noMessage.setVerticalAlignment(javax.swing.SwingConstants.CENTER);
                
                unreadPanel.add(noMessage, "width 130");
                
                homeMessage mes = new homeMessage();
                mes.name.setText(sendUser);
                mes.Ddesc.setText(date);
                mes.Ddesc1.setText(message);
                mes.avatar.setImage(format);
                unreadPanel.add(mes);
                
            } else{
                JLabel noMessage = new JLabel();
                noMessage.setText("You currently don't have any messages.");
                noMessage.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                noMessage.setVerticalAlignment(javax.swing.SwingConstants.CENTER);
                
                unreadPanel.add(noMessage,"width 280, height 80");
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(Home_Form.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        unreadPanel = new csms.database.component.PlainPanelRound();
        agendaPanel = new csms.database.component.PlainPanelRound();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        txtTimer = new javax.swing.JLabel();
        txtTitle = new javax.swing.JLabel();
        lbTeacher = new javax.swing.JLabel();
        txtTeacher = new javax.swing.JLabel();
        lbtime = new javax.swing.JLabel();
        txtday = new javax.swing.JLabel();
        txttime = new javax.swing.JLabel();
        lbday = new javax.swing.JLabel();
        lbloc = new javax.swing.JLabel();
        txtloc = new javax.swing.JLabel();
        lbdesc = new javax.swing.JLabel();
        txtdesc = new javax.swing.JLabel();
        txtHeader = new javax.swing.JLabel();

        unreadPanel.setRoundBottomLeft(20);
        unreadPanel.setRoundBottomRight(5);
        unreadPanel.setRoundTopLeft(5);
        unreadPanel.setRoundTopRight(20);

        javax.swing.GroupLayout unreadPanelLayout = new javax.swing.GroupLayout(unreadPanel);
        unreadPanel.setLayout(unreadPanelLayout);
        unreadPanelLayout.setHorizontalGroup(
            unreadPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 288, Short.MAX_VALUE)
        );
        unreadPanelLayout.setVerticalGroup(
            unreadPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 94, Short.MAX_VALUE)
        );

        agendaPanel.setRoundBottomLeft(20);
        agendaPanel.setRoundBottomRight(5);
        agendaPanel.setRoundTopLeft(5);
        agendaPanel.setRoundTopRight(20);

        javax.swing.GroupLayout agendaPanelLayout = new javax.swing.GroupLayout(agendaPanel);
        agendaPanel.setLayout(agendaPanelLayout);
        agendaPanelLayout.setHorizontalGroup(
            agendaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        agendaPanelLayout.setVerticalGroup(
            agendaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanel1.setOpaque(false);

        jPanel2.setOpaque(false);

        txtTimer.setBackground(new java.awt.Color(80, 80, 80));
        txtTimer.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        txtTimer.setForeground(new java.awt.Color(80, 80, 80));
        txtTimer.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtTimer.setText("00");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtTimer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtTimer, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        txtTitle.setBackground(new java.awt.Color(80, 80, 80));
        txtTitle.setForeground(new java.awt.Color(80, 80, 80));
        txtTitle.setText("The next subject will be <next> and <current> will end on");

        lbTeacher.setForeground(new java.awt.Color(80, 80, 80));
        lbTeacher.setText("Subject Teacher:");

        txtTeacher.setForeground(new java.awt.Color(80, 80, 80));
        txtTeacher.setText("Jalipha Ampog");

        lbtime.setForeground(new java.awt.Color(80, 80, 80));
        lbtime.setText("Class Time:");

        txtday.setForeground(new java.awt.Color(80, 80, 80));
        txtday.setText("Every Wednesdays");

        txttime.setForeground(new java.awt.Color(80, 80, 80));
        txttime.setText("10:00am - 12:00 am");

        lbday.setForeground(new java.awt.Color(80, 80, 80));
        lbday.setText("Class Days:");

        lbloc.setForeground(new java.awt.Color(80, 80, 80));
        lbloc.setText("Class Location:");

        txtloc.setForeground(new java.awt.Color(80, 80, 80));
        txtloc.setText("PUC - Computer Room");

        lbdesc.setForeground(new java.awt.Color(80, 80, 80));
        lbdesc.setText("Class Description:");

        txtdesc.setForeground(new java.awt.Color(80, 80, 80));
        txtdesc.setText("Capstone Presentation");

        txtHeader.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtHeader.setText("jLabel2");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtHeader, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txtTitle)
                                .addGap(0, 247, Short.MAX_VALUE)))))
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lbTeacher)
                        .addGap(18, 18, 18)
                        .addComponent(txtTeacher))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lbtime)
                        .addGap(18, 18, 18)
                        .addComponent(txttime))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lbday)
                        .addGap(18, 18, 18)
                        .addComponent(txtday))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lbloc)
                        .addGap(18, 18, 18)
                        .addComponent(txtloc))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lbdesc)
                        .addGap(18, 18, 18)
                        .addComponent(txtdesc)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(18, Short.MAX_VALUE)
                .addComponent(txtHeader)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTitle)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbTeacher)
                    .addComponent(txtTeacher))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbtime)
                    .addComponent(txttime))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbday)
                    .addComponent(txtday))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbloc)
                    .addComponent(txtloc))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbdesc)
                    .addComponent(txtdesc))
                .addGap(20, 20, 20))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(unreadPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(agendaPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(207, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(unreadPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(agendaPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private csms.database.component.PlainPanelRound agendaPanel;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lbTeacher;
    private javax.swing.JLabel lbday;
    private javax.swing.JLabel lbdesc;
    private javax.swing.JLabel lbloc;
    private javax.swing.JLabel lbtime;
    private javax.swing.JLabel txtHeader;
    private javax.swing.JLabel txtTeacher;
    private javax.swing.JLabel txtTimer;
    private javax.swing.JLabel txtTitle;
    private javax.swing.JLabel txtday;
    private javax.swing.JLabel txtdesc;
    private javax.swing.JLabel txtloc;
    private javax.swing.JLabel txttime;
    private csms.database.component.PlainPanelRound unreadPanel;
    // End of variables declaration//GEN-END:variables
}
