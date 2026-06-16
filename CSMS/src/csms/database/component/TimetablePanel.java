package csms.database.component;

import csms.database.theme.SystemTheme;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;

public class TimetablePanel extends javax.swing.JPanel {

    public TimetablePanel() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        subject = new javax.swing.JLabel();
        subtime = new javax.swing.JLabel();
        subtea = new javax.swing.JLabel();
        subsgs = new javax.swing.JLabel();

        setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        subject.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        subject.setText("ORACLE");
        add(subject, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 5, -1, -1));

        subtime.setFont(new java.awt.Font("Century Gothic", 0, 10)); // NOI18N
        subtime.setText("9:00 AM - 10:00 AM");
        add(subtime, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 25, -1, -1));

        subtea.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        subtea.setText("Jalipha Ampog");
        add(subtea, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 50, -1, -1));

        subsgs.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        subsgs.setText("TVL-ICT 12-1");
        add(subsgs, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 70, -1, -1));
    }// </editor-fold>//GEN-END:initComponents

    @Override
    protected void paintComponent(Graphics grphcs) {
        Graphics2D g2 = (Graphics2D) grphcs.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(SystemTheme.mainColor);
        Area area = new Area(createRoundTopLeft());
        area.intersect(new Area(createRoundTopRight()));
        area.intersect(new Area(createRoundBottomLeft()));
        area.intersect(new Area(createRoundBottomRight()));
        g2.fill(area);
        g2.dispose();
        super.paintComponent(grphcs);
    }

    private Shape createRoundTopLeft() {
        int width = getWidth();
        int height = getHeight();
        int roundX = Math.min(width, 15);
        int roundY = Math.min(height, 15);
        Area area = new Area(new RoundRectangle2D.Double(0, 0, width, height, roundX, roundY));
        area.add(new Area(new Rectangle2D.Double(roundX / 2, 0, width - roundX / 2, height)));
        area.add(new Area(new Rectangle2D.Double(0, roundY / 2, width, height - roundY / 2)));
        return area;
    }
    private Shape createRoundTopRight() {
        int width = getWidth();
        int height = getHeight();
        int roundX = Math.min(width, 15);
        int roundY = Math.min(height, 15);
        Area area = new Area(new RoundRectangle2D.Double(0, 0, width, height, roundX, roundY));
        area.add(new Area(new Rectangle2D.Double(0, 0, width - roundX / 2, height)));
        area.add(new Area(new Rectangle2D.Double(0, roundY / 2, width, height - roundY / 2)));
        return area;
    }
    private Shape createRoundBottomLeft() {
        int width = getWidth();
        int height = getHeight();
        int roundX = Math.min(width, 15);
        int roundY = Math.min(height, 15);
        Area area = new Area(new RoundRectangle2D.Double(0, 0, width, height, roundX, roundY));
        area.add(new Area(new Rectangle2D.Double(roundX / 2, 0, width - roundX / 2, height)));
        area.add(new Area(new Rectangle2D.Double(0, 0, width, height - roundY / 2)));
        return area;
    }
    private Shape createRoundBottomRight() {
        int width = getWidth();
        int height = getHeight();
        int roundX = Math.min(width, 15);
        int roundY = Math.min(height, 15);
        Area area = new Area(new RoundRectangle2D.Double(0, 0, width, height, roundX, roundY));
        area.add(new Area(new Rectangle2D.Double(0, 0, width - roundX / 2, height)));
        area.add(new Area(new Rectangle2D.Double(0, 0, width, height - roundY / 2)));
        return area;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel subject;
    private javax.swing.JLabel subsgs;
    private javax.swing.JLabel subtea;
    private javax.swing.JLabel subtime;
    // End of variables declaration//GEN-END:variables
}
