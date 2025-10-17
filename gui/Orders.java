package gui;

import models.*;

import java.awt.*;
import javax.swing.*;

public class Orders {
    public JPanel panel = new JPanel(new GridBagLayout());
    private GridBagConstraints gbc = new GridBagConstraints();
    public Orders(Order[] orders) {
        gbc.fill = GridBagConstraints.HORIZONTAL;

        panel.setBackground(Color.red);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 0.0;
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.insets = new Insets(5, 0, 5, 0);
        for (int i = 0; i < orders.length; i++) {
            gbc.gridy = i;
            JButton button = new JButton("Order " + (i + 1));
            button.setPreferredSize(new Dimension(220, 40));
            gbc.gridy = i;
            panel.add(button, gbc);
        }
        gbc.weightx = 1;
        gbc.weighty = 1;
        panel.add(new JLabel(" "), gbc);
    }
}
