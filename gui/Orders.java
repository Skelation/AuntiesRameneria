package gui;

import models.*;
import javax.swing.*;
import java.awt.*;

public class Orders {
    public JPanel panel = new JPanel();

    public Orders(Order[] orders) {
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.GRAY);

        panel.add(Box.createVerticalStrut(5));
        for (int i = 0; i < orders.length; i++) {
            addOrder(orders[i]);
        }
    }

    public void addOrder(Order order) {
        JPanel content = new JPanel();
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        content.add(new JLabel(order.getRamen().getSeasoning()));
        for (String topping : order.getRamen().getToppings()) {
            content.add(new JLabel("+ " + topping));
        }

        JButton button = new JButton();
        button.setLayout(new BorderLayout());
        button.add(content, BorderLayout.CENTER);

        button.setAlignmentX(Component.LEFT_ALIGNMENT);
        button.setMaximumSize(new Dimension(Integer.MAX_VALUE, button.getPreferredSize().height));

        panel.add(button);
        panel.add(Box.createVerticalStrut(5));

        panel.revalidate();
        panel.repaint();
    }
}

