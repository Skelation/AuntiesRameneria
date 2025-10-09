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

    private JPanel Counter = new JPanel();
    private JPanel Kitchen = new JPanel();
    private JPanel Fridge = new JPanel();

    public Gui() {
        frame = new JFrame();
        frame.setTitle("Auntie's Rameneria");
        frame.setSize(600, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setVisible(true);

        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridy = 0;
        
        tabbedPane.add("Counter", Counter);
        tabbedPane.add("Kitchen", Kitchen);
        tabbedPane.add("Fridge", Fridge);
        gbc.gridx = 0;
        gbc.weightx = 0.8;
        gbc.weighty = 1;
        p.add(tabbedPane, gbc);

        orderPane.setSize(50, 100);
        orderPane.setBackground(Color.red);
        gbc.gridx = 1;
        gbc.weightx = 0.2;
        gbc.weighty = 1;
        p.add(orderPane, gbc);
        frame.add(p);
    }
}
