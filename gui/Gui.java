package gui;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.*;

public class Gui {
    private JFrame frame;
    private JPanel p = new JPanel(new GridBagLayout());
    private GridBagConstraints gbc = new GridBagConstraints();
    public JPanel orderPane = new JPanel();
    public JTabbedPane tabbedPane = new JTabbedPane();

    private JPanel Counter = new gui.Counter().panel;
    private JPanel Kitchen = new gui.Kitchen().panel;
    private JPanel Fridge = new gui.Fridge().panel;//TODO Fridge panel
    private JPanel Orders = new gui.Orders().panel;

    public Gui() {
        frame = new JFrame();
        frame.setTitle("Auntie's Rameneria");
        frame.setSize(1920, 1080);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setVisible(true);
        frame.setResizable(false);

        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridy = 0;
        
        tabbedPane.add("Counter", Counter);
        tabbedPane.add("Kitchen", Kitchen);
        tabbedPane.add("Fridge", Fridge);
        gbc.gridx = 0;
        gbc.weightx = 0.9;
        gbc.weighty = 1;
    
        p.add(tabbedPane, gbc);

        gbc.gridx = 1;
        gbc.weightx = 0.1;
        gbc.weighty = 1;
        p.add(Orders, gbc);
        frame.add(p);
    }
}
