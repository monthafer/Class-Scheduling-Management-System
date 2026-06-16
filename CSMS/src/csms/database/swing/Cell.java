package csms.database.swing;

import java.awt.Color;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JLabel;

public class Cell extends JButton{
    
    private Date date;
    private Boolean title;
    
    public Cell() {
        setContentAreaFilled(false);
        setBorder(null);
        setHorizontalAlignment(JLabel.CENTER);
    }
    
    public void asTitle(){
        title=true;
    }
    
    public Boolean isTitle() {
        return title;
    }
    
    public void currentMonth (boolean act){
        if (act) {
            setForeground(new Color(68,68,68));
        } else {
            setForeground(new Color(169,169,169));
        }
    }
}
