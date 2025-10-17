package gui;

import models.*;

import java.awt.*;
import java.util.ArrayList;

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
            JPanel content = new JPanel();
            content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
            content.add(new JLabel(orders[i].getRamen().getSeasoning()));

            ArrayList<String> toppings = orders[i].getRamen().getToppings();
            for (String topping : toppings) {
                content.add(new JLabel("+ " + topping));
            }

            JButton button = new JButton();
            button.setLayout(new BorderLayout());
            button.add(content, BorderLayout.CENTER);
            panel.add(button, gbc);
        }
        gbc.weightx = 1;
        gbc.weighty = 1;
        panel.add(new JLabel(" "), gbc);
    }
}
