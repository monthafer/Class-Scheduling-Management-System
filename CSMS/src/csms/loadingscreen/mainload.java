package csms.loadingscreen;

import csms.database.mainViewer;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

/**
 *
 * @author Monthafer Barani
 */
public class mainload extends javax.swing.JDialog {

    static Timer fadeinonetimer; //txtline
    static Timer fadeintwotimer; //firstline and secondline
    static Timer fadeouttimer;
    static Timer delaytimer;
    static Timer delaytimer2;
    
    public mainload(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setBackground(new Color(0,0,0,0));
        bg.setBackground(new Color(0,0,0,0));
        
        txtline.setForeground(new Color(0,0,0,0));
        firstline.setForeground(new Color(0,0,0,0));
        secondline.setForeground(new Color(0,0,0,0));
        
        delayanimout();
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bg = new javax.swing.JPanel();
        progressbar = new csms.loadingscreen.progressbar();
        csms1 = new javax.swing.JLabel();
        csms = new javax.swing.JLabel();
        txtline = new javax.swing.JLabel();
        secondline = new javax.swing.JLabel();
        firstline = new javax.swing.JLabel();
        loadimage = new javax.swing.JLabel();
        loadbg = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        bg.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        bg.add(progressbar, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 310, 140, 10));

        csms1.setBackground(new java.awt.Color(0, 0, 0));
        csms1.setFont(new java.awt.Font("Bradley Hand ITC", 1, 12)); // NOI18N
        csms1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        csms1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/csms/loadingscreen/smalcal.gif"))); // NOI18N
        csms1.setCursor(new java.awt.Cursor(java.awt.Cursor.WAIT_CURSOR));
        bg.add(csms1, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 330, 30, 40));
        csms1.getAccessibleContext().setAccessibleName("");

        csms.setBackground(new java.awt.Color(0, 0, 0));
        csms.setFont(new java.awt.Font("Bradley Hand ITC", 1, 12)); // NOI18N
        csms.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        csms.setText("please wait...");
        csms.setCursor(new java.awt.Cursor(java.awt.Cursor.WAIT_CURSOR));
        bg.add(csms, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 340, 80, 20));

        txtline.setBackground(new java.awt.Color(0, 0, 0));
        txtline.setFont(new java.awt.Font("Bradley Hand ITC", 1, 12)); // NOI18N
        txtline.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtline.setText(" ");
        txtline.setCursor(new java.awt.Cursor(java.awt.Cursor.WAIT_CURSOR));
        bg.add(txtline, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 160, 280, 20));

        secondline.setBackground(new java.awt.Color(0, 0, 0));
        secondline.setFont(new java.awt.Font("Bradley Hand ITC", 1, 12)); // NOI18N
        secondline.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        secondline.setText("my brain's not loading...");
        secondline.setCursor(new java.awt.Cursor(java.awt.Cursor.WAIT_CURSOR));
        bg.add(secondline, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 170, 280, 20));

        firstline.setBackground(new java.awt.Color(0, 0, 0));
        firstline.setFont(new java.awt.Font("Bradley Hand ITC", 1, 12)); // NOI18N
        firstline.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        firstline.setText("I wish I had a refresh button for myself for when");
        firstline.setCursor(new java.awt.Cursor(java.awt.Cursor.WAIT_CURSOR));
        bg.add(firstline, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 150, 280, 20));

        loadimage.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        loadimage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/csms/loadingscreen/loadimage.gif"))); // NOI18N
        loadimage.setCursor(new java.awt.Cursor(java.awt.Cursor.WAIT_CURSOR));
        bg.add(loadimage, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 190, 140, -1));

        loadbg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/csms/loadingscreen/loadbgwhite.png"))); // NOI18N
        loadbg.setCursor(new java.awt.Cursor(java.awt.Cursor.WAIT_CURSOR));
        bg.add(loadbg, new org.netbeans.lib.awtextra.AbsoluteConstraints(-4, 0, 710, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    doTask("Blah", 10);
                    doTask("Blah", 20);
                    doTask("Blah", 40);
                    doTask("Blah", 50);
                    doTask("Blah", 70);
                    doTask("Blah", 90);
                    doTask("Blah", 100);
                    dispose();
                    
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();
    }//GEN-LAST:event_formWindowOpened

    private void doTask(String taskName, int progress) throws Exception {
        Thread.sleep(1000);
        progressbar.setValue(progress);
    }
    /**
     * @param args the command line arguments
     */
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
            java.util.logging.Logger.getLogger(mainload.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(mainload.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(mainload.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(mainload.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                //new mainload().setVisible(true);
                mainload dialog = new mainload(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        mainViewer dash = new mainViewer();
                        dash.setVisible(true);
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bg;
    private javax.swing.JLabel csms;
    private javax.swing.JLabel csms1;
    private javax.swing.JLabel firstline;
    private javax.swing.JLabel loadbg;
    private javax.swing.JLabel loadimage;
    private csms.loadingscreen.progressbar progressbar;
    private javax.swing.JLabel secondline;
    private javax.swing.JLabel txtline;
    // End of variables declaration//GEN-END:variables

    //one liner text desc
    private void fadeinanim1(){
        fadeinonetimer = new Timer(1, new ActionListener() {
            int fadeinone = 0;
            @Override
            public void actionPerformed(ActionEvent e){
                txtline.setForeground(new Color(0,0,0,fadeinone++));
                firstline.setForeground(new Color(0,0,0,fadeinone++));
                secondline.setForeground(new Color(0,0,0,fadeinone++));
                if (fadeinone > 250){
                    fadeinone = 255;
                    fadeinonetimer.stop();
                    delayanimin();
                }
            }
        });
        fadeinonetimer.setRepeats(true);
        fadeinonetimer.start();
        //delayanim();
        //fadeoutanim();
    }
    
    //two liner text desc
    private void fadeinanim2(){
        fadeintwotimer = new Timer(1, new ActionListener() {
            int fadeintwo = 0;
            @Override
            public void actionPerformed(ActionEvent e){
                firstline.setForeground(new Color(0,0,0,fadeintwo++));
                secondline.setForeground(new Color(0,0,0,fadeintwo++));
                if (fadeintwo>250){
                    fadeintwo = 255;
                    fadeintwotimer.stop();
                    delayanimin();
                }
            }
        });
        fadeintwotimer.setRepeats(true);
        fadeintwotimer.start();
    }
    
    //fade out
    private void fadeoutanim(){
        fadeouttimer = new Timer(1, new ActionListener() {
            int fadeout = 255;
            @Override
            public void actionPerformed(ActionEvent e){
                firstline.setForeground(new Color(0,0,0,fadeout--));
                secondline.setForeground(new Color(0,0,0,fadeout--));
                txtline.setForeground(new Color(0,0,0,fadeout--));
                if (fadeout<5){
                    firstline.setForeground(new Color(0,0,0,0));
                    secondline.setForeground(new Color(0,0,0,0));
                    txtline.setForeground(new Color(0,0,0,0));
                    fadeouttimer.stop();
                    delayanimout();
                }
            }
        });
        fadeouttimer.setRepeats(true);
        fadeouttimer.start();
    }
    
    private void delayanimin(){
        delaytimer = new Timer(1, new ActionListener() {
            int delay = 0;
            @Override
            public void actionPerformed(ActionEvent e){
                delay++;
                if (delay==50){
                    delaytimer.stop();
                    fadeoutanim();
                }
            }
        });
        delaytimer.setRepeats(true);
        delaytimer.start();
    }
    
    private void delayanimout(){
        delaytimer2 = new Timer(1, new ActionListener() {
            int delay2 = 0;
            @Override
            public void actionPerformed(ActionEvent e){
                delay2++;
                if (delay2==50){
                    delaytimer2.stop();
                    int a = (int)(Math.random()*(5-1+1)+1);
                    randomtxt(a);
                }
            }
        });
        delaytimer2.setRepeats(true);
        delaytimer2.start();
        
    }
    
    private void randomtxt(int a){
        switch (a) {
            case 1:
                txtline.setText("Sending Virtual Hug...");
                firstline.setText(" ");
                secondline.setText(" ");
                fadeinanim1();
                break;
            case 2:
                txtline.setText(" ");
                firstline.setText("Did you know that these texts appear in a");
                secondline.setText("randomly way?");
                fadeinanim1();
                break;
            case 3:
                txtline.setText(" ");
                firstline.setText("I wish I had a refresh button for myself for when");
                secondline.setText("my brain's not loading...");
                fadeinanim1();
                break;
            case 4:
                txtline.setText(" ");
                firstline.setText("CSMS stands for 'Class Scheduling Management");
                secondline.setText("System'");
                fadeinanim1();
                break;
            case 5:
                txtline.setText(" ");
                firstline.setText("CSMS stands for 'Class Scheduling Management");
                secondline.setText("System'");
                fadeinanim1();
                break;
            default:
                break;
        }
    }
    
}
