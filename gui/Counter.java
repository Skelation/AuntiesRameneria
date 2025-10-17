package gui;

import java.awt.*;
import java.util.Random;

import javax.swing.*;
import models.*;

public class Counter {
    public JPanel panel = new JPanel(new GridBagLayout());
    private JPanel topPanel = new JPanel(new BorderLayout());
    private JPanel bottomPanel = new JPanel();

    private JPanel SpacerPanel = new JPanel();
    private JPanel clientsPanel = new JPanel();

    private GridBagConstraints gbc = new GridBagConstraints();

    public Counter(Order[] orders) {
        bottomPanel.setBackground(Color.red);
        topPanel.setBackground(Color.blue);
        SpacerPanel.setBackground(Color.blue);
        clientsPanel.setBackground(Color.blue);

        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weighty = 0.7;
        gbc.weightx = 1.0;
        panel.add(topPanel, gbc);

        gbc.gridy = 1;
        gbc.weighty = 0.3;
        panel.add(bottomPanel, gbc);

        topPanel.add(SpacerPanel, BorderLayout.CENTER);
        topPanel.add(clientsPanel, BorderLayout.SOUTH);

        for (int i = 0; i < orders.length; i++) {
            ImageIcon icon = new ImageIcon();
            Random r = new Random();
            int n = r.nextInt(0, 3);
            switch (n) {
                case 0:
                    icon = new ImageIcon("Assets/FatMan.png");
                    break;
                case 1:
                    icon = new ImageIcon("Assets/Littlewoman.png");
                    break;
                case 2:
                    icon = new ImageIcon("Assets/SlimMan.png");
                    break;
                default:
                    icon = new ImageIcon("Assets/FatMan.png");
                    break;
            }
            Image image = icon.getImage();
            image = image.getScaledInstance(300, 300, Image.SCALE_DEFAULT);
            icon = new ImageIcon(image);
            JButton button = new JButton(icon);
            button.setPreferredSize(new Dimension(300, 300));
            clientsPanel.add(button);
        }
    }
}
