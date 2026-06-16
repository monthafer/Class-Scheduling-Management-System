package csms.login.model;

import csms.databaseconnection.DBConnection;
import csms.login.component.fullreg;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.*;

public class ServiceUser {
    
    Connection dbconn = DBConnection.openConnection();
    private fullreg reg;
    
    public void insertStudentUser(ModelUser user) throws SQLException, FileNotFoundException{
        String contact = (String) reg.getContact();
        String address = (String) reg.getAddress();
        String fb = "";
        if(reg.getFb() == null || reg.getFb().equals("")){
            fb = "null";
        } else {
            fb = (String) reg.getFb();
        }
        String bio = (String)reg.getBio();
        String dir = (String) reg.getDir();
        int sgs = (Integer) reg.getStudGrade();
        File f = new File(dir);
        InputStream is = new FileInputStream(f);
        
        PreparedStatement st = (PreparedStatement) 
                dbconn.prepareStatement("INSERT INTO student(first_name,last_name,username,email,password,contact_no,address,facebook,student_desc,img_avatar,sgs_id) VALUES (?,?,?,?,?,?,?,?,?,?,?)");

        st.setString(1, user.getFirstName());
        st.setString(2, user.getLastName());
        st.setString(3, user.getUserName());
        st.setString(4, user.getEmail());
        st.setString(5, user.getPassword());
        st.setString(6, contact);
        st.setString(7, address);
        st.setString(8, fb);
        st.setString(9, bio);
        st.setBlob(10, is);
        st.setInt(11, sgs);

        ResultSet ress = st.getGeneratedKeys();
        ress.first();
        ress.close();
        st.close();
    }
    
    public void insertAdminUser(ModelUser user) throws SQLException, FileNotFoundException{
        String contact = (String) reg.getContact();
        String fb = "";
        if(reg.getFb() == null || reg.getFb().equals("")){
            fb = "null";
        } else {
            fb = (String) reg.getFb();
        }
        String bio = (String)reg.getBio();
        String dir = (String) reg.getDir();
        String job = (String) reg.getAdminJob();
        String jobInfo = (String) reg.getAdminJobInfo();
        String jobLoc = (String) reg.getAdminJobLoc();
        File f = new File(dir);
        InputStream is = new FileInputStream(f);
        
        PreparedStatement st = (PreparedStatement) 
                dbconn.prepareStatement("INSERT INTO admin(first_name,last_name,username,email,password,contact_no,fbAcc,bio,img_avatar,job,job_location,job_info) VALUES (?,?,?,?,?,?,?,?,?,?,?)");

        st.setString(1, user.getFirstName());
        st.setString(2, user.getLastName());
        st.setString(3, user.getUserName());
        st.setString(4, user.getEmail());
        st.setString(5, user.getPassword());
        st.setString(6, contact);
        st.setString(7, fb);
        st.setString(8, bio);
        st.setBlob(9, is);
        st.setString(10, job);
        st.setString(11, jobLoc);
        st.setString(12, jobInfo);

        ResultSet ress = st.getGeneratedKeys();
        ress.first();
        ress.close();
        st.close();
    }
    
    public void insertTeacherUser(ModelUser user) throws SQLException, FileNotFoundException{
        String contact = (String) reg.getContact();
        String address = (String) reg.getAddress();
        String fb = "";
        if(reg.getFb() == null || reg.getFb().equals("")){
            fb = "null";
        } else {
            fb = (String)reg.getFb();
        }
        String bio = (String)reg.getBio();
        String dir = (String) reg.getDir();
        String consultH = (String) reg.getConsultHours();
        String consultD = (String) reg.getConsultDays();
        File f = new File(dir);
        InputStream is = new FileInputStream(f);
        
        PreparedStatement st = (PreparedStatement) 
                dbconn.prepareStatement("INSERT INTO teacher(first_name,last_name,username,email,password,contact_no,address,facebook,tea_desc,img_avatar,consult_hours,consult_days) VALUES (?,?,?,?,?,?,?,?,?,?,?)");

        st.setString(1, user.getFirstName());
        st.setString(2, user.getLastName());
        st.setString(3, user.getUserName());
        st.setString(4, user.getEmail());
        st.setString(5, user.getPassword());
        st.setString(6, contact);
        st.setString(7, address);
        st.setString(8, fb);
        st.setString(9, bio);
        st.setBlob(10, is);
        st.setString(11, consultH);
        st.setString(12, consultD);

        ResultSet ress = st.getGeneratedKeys();
        ress.first();
        ress.close();
        st.close();
    }
    
    
    
    public boolean checkDuplicateUser (String user) throws SQLException {
        boolean duplicate = false;
        PreparedStatement p = (PreparedStatement)
                dbconn.prepareStatement("SELECT stud_id FROM student WHERE username=? limit 1");
        p.setString(1, user);
        ResultSet r = p.executeQuery();
        if(r.first()){
            duplicate = true;
        }
        r.close();
        p.close();
        return duplicate;
    }
    
    public boolean checkDuplicateEmail (String user) throws SQLException {
        boolean duplicate = false;
        PreparedStatement p = (PreparedStatement)
                dbconn.prepareStatement("SELECT stud_id FROM student WHERE email=? limit 1");
        p.setString(1, user);
        ResultSet r = p.executeQuery();
        if(r.first()){
            duplicate = true;
        }
        r.close();
        p.close();
        return duplicate;
    }
    
}
