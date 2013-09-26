package Logic;

import GUI.Frame;
import javax.swing.UIManager;

/***************************************************************************************
 * Written by: Simon Cicek                                                             *
 * Last changed: 2012-03-27                                                            *
 ***************************************************************************************/

public class Main 
{
    public static void main(String[] args) 
    {
        // Change the look of the program
        try
        {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch (Exception e) 
        {
            e.printStackTrace();
        }
        Frame f = new Frame();
    }
}
