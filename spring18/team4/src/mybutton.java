import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;



    class mybutton extends JButton 
    {

        private Color hoverBackgroundColor;
        private Color pressedBackgroundColor;

        public mybutton() {
            this(null);
            hoverBackgroundColor=Color.pink;
            pressedBackgroundColor=Color.green;

        }

        public mybutton(String text) {
            super(text);
            super.setContentAreaFilled(false);
        }

        @Override
        protected void paintComponent(Graphics g) {
            if (getModel().isPressed()) {
                g.setColor(pressedBackgroundColor);
            } else if (getModel().isRollover()) {
                g.setColor(hoverBackgroundColor);
            } else {
                g.setColor(getBackground());
            }
            g.fillRect(0, 0, getWidth(), getHeight());
            super.paintComponent(g);
        }

        @Override
        public void setContentAreaFilled(boolean b) {
        }

        public Color getHoverBackgroundColor() {
            return hoverBackgroundColor;
        }

        public void setHoverBackgroundColor(Color hoverBackgroundColor) {
            this.hoverBackgroundColor = hoverBackgroundColor;
        }

        public Color getPressedBackgroundColor() {
            return pressedBackgroundColor;
        }

        public void setPressedBackgroundColor(Color pressedBackgroundColor) {
            this.pressedBackgroundColor = pressedBackgroundColor;
        }
    

    protected void createAndShowGUI() {
        JFrame frame = new JFrame("Test button");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        final mybutton btnSave = new mybutton("Save");
        btnSave.setForeground(new Color(0, 135, 200).brighter());
        btnSave.setHorizontalTextPosition(SwingConstants.CENTER);
        btnSave.setBorder(null);
        btnSave.setBackground(new Color(3, 59, 90));
        btnSave.setHoverBackgroundColor(new Color(3, 59, 90).brighter());
        btnSave.setPressedBackgroundColor(Color.PINK);
        frame.add(btnSave);
        frame.setSize(200, 200);
        frame.setVisible(true);
    }

    }

