package csms.database.component;

public class SubjectDes {

    public String getLocationdes() {
        return locationdes;
    }

    /**
     * @param locationdes the locationdes to set
     */
    public void setLocationdes(String locationdes) {
        this.locationdes = locationdes;
    }

    public String getSubjectdes() {
        return subjectdes;
    }

    public void setSubjectdes(String subjectdes) {
        this.subjectdes = subjectdes;
    }

    public String getTeacherdes() {
        return teacherdes;
    }

    public void setTeacherdes(String teacherdes) {
        this.teacherdes = teacherdes;
    }

    public int getSubjectID() {
        return subjectID;
    }

    public void setSubjectID(int subjectID) {
        this.subjectID = subjectID;
    }

    public String getDaydes() {
        return daydes;
    }

    public void setDaydes(String daydes) {
        this.daydes = daydes;
    }

    public String getTimedes() {
        return timedes;
    }

    public void setTimedes(String timedes) {
        this.timedes = timedes;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    private String locationdes;
    
    public SubjectDes(String userName, String password){
        
    }
    
    public SubjectDes(int subjectID, String subjectdes, String teacherdes, String daydes, String timedes){
        this.subjectID = subjectID;
        this.subjectdes = subjectdes;
        this.teacherdes = teacherdes;
        this.daydes = daydes;
        this.timedes = timedes;
        this.locationdes = locationdes;
    }
    
    public SubjectDes(){
        
    }
    
    private int subjectID;
    private String subjectdes;
    private String teacherdes;
    private String daydes;
    private String timedes;
    private String description;
}
