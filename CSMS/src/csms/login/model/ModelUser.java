package csms.login.model;

public class ModelUser {

    public String getRetype() {
        return retype;
    }

    public void setRetype(String retype) {
        this.retype = retype;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    public ModelUser(String userName, String password){
        this.userName = userName;
        this.password = password;
    }
    
    public ModelUser(int userID, String firstName, String lastName, String email, String position, String userName, String password, String retype){
        this.userID = userID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.position = position;
        this.userName = userName;
        this.password = password;
        this.retype = retype;
    }
    
    public ModelUser(){
        
    }
    
    private String retype;
    private String position;
    private int userID;
    private String userName;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
}
