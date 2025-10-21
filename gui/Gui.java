package gui;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.KeyEvent;

import models.*;

import javax.swing.*;

public class Gui {
    private JFrame frame;
    private JPanel p = new JPanel(new GridBagLayout());
    private GridBagConstraints gbc = new GridBagConstraints();
    public JPanel orderPane = new JPanel();
    public JTabbedPane tabbedPane = new JTabbedPane();

    private JPanel Kitchen;
    private Kitchen kitchenPanel;
    private JPanel Fridge = new gui.Fridge().panel;
    private JPanel Orders;
    public Orders ordersPanel;
    private JPanel Counter;
    public Counter counterPanel;

    public Gui(Order[] orders, Stove stove) {
        // Orders = new gui.Orders(orders).panel;
        ordersPanel = new Orders(orders);
        Orders = ordersPanel.panel;

        counterPanel = new Counter(orders);
        Counter = counterPanel.panel;

        kitchenPanel = new Kitchen(stove);
        Kitchen = kitchenPanel.panel;
       
        frame = new JFrame();
        frame.setTitle("Auntie's Rameneria");
        frame.setSize(1920, 1080);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setUndecorated(true);
        frame.setVisible(true);
        frame.setResizable(false);

        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridy = 0;
        
        tabbedPane.add("1 Counter", Counter);
        tabbedPane.add("2 Kitchen", Kitchen);
        tabbedPane.add("3 Fridge", Fridge);
        gbc.gridx = 0;
        gbc.weightx = 0.9;
        gbc.weighty = 1;
    
        p.add(tabbedPane, gbc);

        gbc.gridx = 1;
        gbc.weightx = 0.1;
        gbc.weighty = 1;
        p.add(Orders, gbc);
        frame.add(p);
 
        tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);
        tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);
        tabbedPane.setMnemonicAt(2, KeyEvent.VK_3);
    }
}
