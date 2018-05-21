package core.com.ui.swing.button;


import java.awt.Color;
import java.awt.event.MouseEvent;
import javax.swing.JButton;

// ComUiButton.java
// Copyright Kyle Dreger 2012
// To make JButtons suck less
// https://gist.github.com/4646029

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;

public class ComUiButton extends JButton implements MouseListener { 
    JLabel label = new JLabel();
    private Color bg = null;
    private Color bgh = null;
    //--------------------------------------------------------------------------
    public ComUiButton() {
        super();
        this.setDefaults();
        this.bgh = Color.BLUE;
        addMouseListener(this);
    }
    //--------------------------------------------------------------------------
    @Override
    protected void paintComponent(Graphics g) {
        if(bg != null){
            setContentAreaFilled(false);
            setFocusPainted(false);
            final Graphics2D g2 = (Graphics2D) g.create();
            g2.setPaint(bg);
            g2.fillRect(0, 0, getWidth(), getHeight());
            g2.dispose();
        }

        super.paintComponent(g);
    }
    //--------------------------------------------------------------------------
    @Override
    public void setBackground(Color color){
        this.bg = color;
    }
    //--------------------------------------------------------------------------
    private void setDefaults() {
        this.setRolloverEnabled(true);
        this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }
    //--------------------------------------------------------------------------
    public void mouseEntered(MouseEvent e) {
        if (e.getSource() == this && this.bgh != null) {
            this.setBackground(this.bgh);
        }
    }
    //--------------------------------------------------------------------------
    public void mouseExited(MouseEvent e) {
        if (e.getSource() == this) {
            this.setBackground(this.bg);
        }
    }
    //--------------------------------------------------------------------------
    @Override
    public void mouseClicked(MouseEvent e) { }
    //--------------------------------------------------------------------------
    @Override
    public void mousePressed(MouseEvent e) { }
    //--------------------------------------------------------------------------
    @Override
    public void mouseReleased(MouseEvent e) { }
    //--------------------------------------------------------------------------
}
    