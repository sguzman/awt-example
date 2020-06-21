package org.github.sguzman.awtexample;

// Java program to move a mouse from the initial
// location to a specified location
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class robomouse extends Frame implements ActionListener {
    // Frame
    static JFrame f;

    // textField
    static TextField x, y;
    static TextField locationX;
    static TextField locationY;

    static TextField locationRelativeX;
    static TextField locationRelativeY;

    // default constructor
    robomouse()
    {
    }

    // main function
    public static void main(String args[])
    {
        // object of class
        robomouse rm = new robomouse();

        // create a frame
        f = new JFrame("robomouse");

        // set the frame to close on exit
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // create textfield
        x = new TextField(7);
        y = new TextField(7);

        locationX = new TextField(4);
        locationY = new TextField(4);

        locationRelativeX = new TextField(4);
        locationRelativeY = new TextField(4);

        // create a button
        Button b = new Button("OK");

        // add actionListener
        b.addActionListener(rm);

        // create a panel
        Panel p = new Panel();

        // add items to panel
        p.add(x);
        p.add(y);
        p.add(b);

        p.add(locationX);
        p.add(locationY);

        p.add(locationRelativeX);
        p.add(locationRelativeY);

        f.add(p);

        Runnable task = () -> {
            while (true) {
                try {
                    Point pp = MouseInfo.getPointerInfo().getLocation();
                    locationX.setText(pp.x + "");
                    locationY.setText(pp.y + "");

                    Point ppp = f.getMousePosition();
                    if (ppp != null) {
                        locationRelativeX.setText(ppp.x + "");
                        locationRelativeY.setText(ppp.y + "");
                    }
                } catch (HeadlessException ignored) {
                }
            }
        };
        new Thread(task).start();

        // setsize of frame
        f.setSize(300, 300);
        f.show();
    }

    // if button is pressed
    public void actionPerformed(ActionEvent e)
    {
        try {
            Robot r = new Robot();
            int xi1, yi1, xi, yi;

            // get initial loction
            Point p = MouseInfo.getPointerInfo().getLocation();
            xi = p.x;
            yi = p.y;

            // get x and y points
            xi1 = Integer.parseInt(x.getText());
            yi1 = Integer.parseInt(y.getText());
            int i = xi, j = yi;

            // slowly move the mouse to detined location
            while (i != xi1 || j != yi1) {
                // move the mouse to the other point
                r.mouseMove(i, j);

                if (i < xi1)
                    i++;
                if (j < yi1)
                    j++;

                if (i > xi1)
                    i--;
                if (j > yi1)
                    j--;

                // wait
                Thread.sleep(30);
            }
        }
        catch (Exception evt) {
            System.err.println(evt.getMessage());
        }
    }
}