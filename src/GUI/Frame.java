package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JFrame;

/***************************************************************************************
 * Written by: Simon Cicek                                                             *
 * Last changed: 2012-03-27                                                            *
 ***************************************************************************************/

public class Frame extends JFrame
{
    public ControlPanel cp = new ControlPanel();
    
    public Frame()
    {
        // Frame properties
        this.setLayout(new BorderLayout());
        this.add(cp);
        this.setTitle("Simon Cicek - Search");
        this.setBackground(Color.yellow);
	this.setSize(210,380);
	this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
	this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
