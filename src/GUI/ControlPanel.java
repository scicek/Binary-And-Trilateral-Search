package GUI;

import Logic.Searcher;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.*;

/***************************************************************************************
 * Written by: Simon Cicek                                                             *
 * Last changed: 2012-03-27                                                            *
 *                                                                                     *
 * The class defining the controlpanel and setting up/starting the main functionality. * 
 ***************************************************************************************/

public class ControlPanel extends JPanel implements ActionListener
{
    // Components
    JLabel algorithm = new JLabel("Search Algorithm:");
    JComboBox algo;
    JComboBox elements;
    JLabel index = new JLabel("Index Of Element: N/A");
    JLabel nrComp = new JLabel("Number Of Compares: 0");
    JLabel nrElements = new JLabel("Number Of Elements:");
    JButton search = new JButton("Search");
    JButton init = new JButton("Initialize");
    JLabel valueL = new JLabel("Search For:");
    JTextField value = new JTextField("");    
    int[] list = null;
    
    public ControlPanel ()
    {
        String[] algorithms = {"Binary Search - Iterative","Binary Search - Recursive",
                               "Trilateral Search - Iterative", "Trilateral Search - Recursive"};
        String[] e = {"20000","10000","1000","100","10"};
        algo = new JComboBox(algorithms);
        elements = new JComboBox(e);
        value.setPreferredSize(new Dimension(100,25));
        search.setEnabled(false);
        
        // Position the components
        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        
        c.anchor = GridBagConstraints.WEST;
        c.gridx= 0;
        c.gridy= 0;
        this.add(algorithm,c);
        c.gridy= 1;
        this.add(algo,c);
        c.insets = new Insets(10,0,0,0);
        
        c.gridy = 2;
        this.add(nrElements,c);
        c.insets = new Insets(0,0,0,0);
        c.gridy = 3;
        this.add(elements,c);
        
        c.insets = new Insets(10,0,0,0);
        c.gridy = 4;
        this.add(init,c);
        c.gridy = 5;
        this.add(valueL,c);
        c.gridy = 6;
        c.insets = new Insets(0,0,0,0);
        this.add(value,c);
        c.gridy = 7;
        c.insets = new Insets(10,0,0,0);
        this.add(search,c);
        
        c.gridy = 8;
        this.add(index,c);
        
        c.gridy = 9;
        this.add(nrComp,c);
        this.setBorder(BorderFactory.createRaisedBevelBorder());
        
        // Add actionlisteners
        init.addActionListener(this);
        search.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) 
    {
        if(e.getSource() == init)
        {
            // Initialize the program
            list = initArray(Integer.parseInt(elements.getSelectedItem().toString()));
            search.setEnabled(true);
        }
        else
        {
            init.setEnabled(false);
            try
            {
                // Start the search
                int[] i = startSearching(list, Integer.parseInt(value.getText()));
                index.setText("Index Of Element: " + i[0]);
                nrComp.setText("Number Of Compares: " + i[1]);
            }
            catch(Exception ex)
            {
                // Alert the user of the error
                JOptionPane.showMessageDialog(null, "Enter a whole number", "Error!", JOptionPane.ERROR_MESSAGE);
                return;
            }
            init.setEnabled(true);
        }
    }
    
    public int[] initArray (int n)
    {
        // Initialize the array to search through
        int next;
        int[] list = new int[n];
        Random r = new Random();
        list[0] = r.nextInt(5);
        
        for(int i = 1; i < n; i++)
        {
            next = r.nextInt(10*i);
            // Ensure we get a sorted array with random numbers
            while(next < list[i-1])
                next = r.nextInt(10*i);
            list[i] = next;
        }
        return list;
    }
    
    public int[] startSearching(int[] list, int i)
    {
        // Default case: element not found and 0 searches
        int[] p = new int[2];
        p[0] = -1;
        p[1] = 0;
        
        // Start the search using the algorithm defined by the user
        if(algo.getSelectedIndex() == 0)
            return Searcher.iterativeBinarySearch(list, i,0,list.length-1);
        else if(algo.getSelectedIndex() == 1)
            return Searcher.recursiveBinarySearch(list, i);
        else if(algo.getSelectedIndex() == 2)
            return Searcher.iterativeTrilateralSearch(list, i,0,list.length-1);
        else if(algo.getSelectedIndex() == 3)
            return Searcher.recursiveTrilateralSearch(list, i);
        return p;
    }
}