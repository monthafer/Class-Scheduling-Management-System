package csms.database.swing;

public class subjectpane extends javax.swing.JPanel {

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    private String id;

    public subjectpane() {
        initComponents();
    }
    
    public void updatetext(String subject, int hourstart, int minutestart, int hourend, int minuteend, String day1, String day2){
        if(day2.equals("null")){
            time.setText(convertTime(hourstart, minutestart) + " - " + convertTime(hourend, minuteend) + " | " + day1 + "s");
        } else {
            time.setText(convertTime(hourstart, minutestart) + " - " + convertTime(hourend, minuteend) + " | " + day1 + "s and " + day2 + "s");
        }
        this.subject.setText(subject);
    }
    
    private String convertTime(int hour, int minute){
        String min = null;
        if(minute == 0){
            min = "00";
        } else {
            min = String.valueOf(minute);
        }
        if(hour>12){
            return String.valueOf(hour - 12) + ":" + min + " PM";
        } else {
            return String.valueOf(hour) + ":" + min + " AM";
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        subject = new javax.swing.JLabel();
        time = new javax.swing.JLabel();

        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        setMaximumSize(new java.awt.Dimension(275, 54));
        setMinimumSize(new java.awt.Dimension(275, 54));
        setOpaque(false);

        subject.setFont(new java.awt.Font("Bahnschrift", 1, 18)); // NOI18N
        subject.setText("SUBJECT");

        time.setFont(new java.awt.Font("Bahnschrift", 0, 11)); // NOI18N
        time.setText("12:00 AM - 8:00 PM | Mondays and Wednesdays");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(subject)
                    .addComponent(time))
                .addContainerGap(31, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(subject)
                .addGap(1, 1, 1)
                .addComponent(time)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JLabel subject;
    public javax.swing.JLabel time;
    // End of variables declaration//GEN-END:variables
}
