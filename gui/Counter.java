package gui;

import java.awt.*;
import java.util.Random;

import javax.swing.*;
import models.*;

public class Counter {
    public JPanel panel = new JPanel(new GridBagLayout());
    private JPanel topPanel = new JPanel(new BorderLayout()) {
        private Image backgroundImage = new ImageIcon("Assets/resto.jpg").getImage();

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    };
    private JPanel bottomPanel = new JPanel();

    private JPanel SpacerPanel = new JPanel();
    private JPanel clientsPanel = new JPanel();

    private GridBagConstraints gbc = new GridBagConstraints();

    public Counter(Order[] orders) {
        bottomPanel.setBackground(Color.decode("#964B00"));
        SpacerPanel.setOpaque(false);
        clientsPanel.setOpaque(false);

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
            int n = r.nextInt(0, 2);
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
            image = image.getScaledInstance(500, 500, Image.SCALE_DEFAULT);
            icon = new ImageIcon(image);

            JToggleButton button = new JToggleButton(icon);
            button.setPreferredSize(new Dimension(500, 500));
            panel.add(button);
            button.setBorder(BorderFactory.createEmptyBorder());
            button.setContentAreaFilled(false);

            button.addItemListener(e -> {
                if (button.isSelected()) {
                    button.setBorder(BorderFactory.createLineBorder(Color.GREEN, 3));
                    button.setBorderPainted(true);
                } else {
                    button.setBorderPainted(false);
                }
            });
            clientsPanel.add(button);
        }
    }
}
