package gui;

import models.*;
import javax.swing.*;

import gui.Counter;
import gui.Kitchen;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Orders {
    public JPanel panel = new JPanel();
    public JPanel orderPanel = new JPanel();
    public JPanel moneyPanel = new JPanel();
    private JTabbedPane jTabbedPane;
    private Kitchen kitchenPanel;
    private Counter counterPanel;
    private Fridge fridgePanel;
    private ArrayList<Order> orders;
    private Map<Integer, JLabel> orderTimeLabels;
    public Map<Integer, JButton> orderButtons;
    private Bank bank;
    public JLabel balanceLabel;
    private Clock clock;

    public Orders(ArrayList<Order> orders, JTabbedPane tabbedPane, Kitchen kitchenPanel, Counter counterPanel, Fridge fridgePanel, Bank bank, Clock clock) {
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        orderPanel.setLayout(new BoxLayout(orderPanel, BoxLayout.Y_AXIS));
        orderPanel.setBackground(Color.GRAY);
        this.bank = bank;
        this.clock = clock;
        this.orderTimeLabels = new HashMap<>();

        orderPanel.add(Box.createVerticalStrut(5));
        for (int i = 0; i < orders.size(); i++) {
            addOrder(orders.get(i), bank);
        }
        jTabbedPane = tabbedPane;
        this.kitchenPanel = kitchenPanel;
        this.counterPanel = counterPanel;
        this.fridgePanel = fridgePanel;
        this.orders = orders;
        this.orderButtons = new HashMap<>();
        balanceLabel = new JLabel();
        balanceLabel.setText(String.valueOf(bank.getBalance()));
        
        moneyPanel.add(balanceLabel);
        panel.add(orderPanel);
        panel.add(moneyPanel);
    }

    public void removeOrder(Order order) {
        int orderNumber = order.getOrderNumber();
        orders.remove(order);

        JButton button = orderButtons.remove(orderNumber);
        SwingUtilities.invokeLater(() -> {
            orderPanel.remove(button);
            orderPanel.revalidate();
            orderPanel.repaint();
        });

        System.out.println("Order removed " + orderNumber);
    }

    public void removeOrder(int orderNumber) {
        for (int i = 0; i < orders.size(); i++) {
            Order order = orders.get(i);
            if (order.getOrderNumber() == orderNumber) {
                removeOrder(orders.get(i));
            }
        }
    }

    public void addOrder(Order order, Bank bank) {
        JPanel content = new JPanel();
        long timeEndOrder = clock.getTimeEnd(order.getOrderNumber());
        long timeLeft = timeEndOrder - clock.getTime();
        JLabel timeleftLabel = new JLabel(String.valueOf(timeLeft));

        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        content.add(timeleftLabel); 
        content.add(new JLabel(order.getRamen().getSeasoning()));
        for (String topping : order.getRamen().getToppings()) {
            content.add(new JLabel("+ " + topping));
        }

        JButton button = new JButton();
        button.setLayout(new BorderLayout());
        button.add(content, BorderLayout.CENTER);

        button.setAlignmentX(Component.LEFT_ALIGNMENT);
        button.setMaximumSize(new Dimension(Integer.MAX_VALUE, button.getPreferredSize().height));
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int tabIndex = jTabbedPane.getSelectedIndex();
                switch (tabIndex) {
                    case 0:
                    System.out.println("Interacting with counter");
                    break;
                    case 1:
                    Stove stove = kitchenPanel.getStove();
                    Burner[] burners = stove.getBurners();
                    Ramen selectedRamen = burners[kitchenPanel.getSelectedBurnerIndex()].getRamen();
                    if (selectedRamen.matches(order.getRamen())) {
                        int orderNumber = order.getOrderNumber();
                        clock.deleteEvent(orderNumber);
                        System.out.println("Same Order");
                        bank.addAmount(10);
                        balanceLabel.setText(String.valueOf(bank.getBalance()));
                        moneyPanel.revalidate();
                        moneyPanel.repaint();
                        removeOrder(order);
                        counterPanel.removeClient(order.getOrderNumber());
                    } else {
                        System.out.printf("Order Ramen:\n%s \n\nSelectedRamen: \n%s", order.getRamen().getDescription(), selectedRamen.getDescription());
                        System.out.println("Different");
                        bank.removeAmount(10);
                        balanceLabel.setText(String.valueOf(bank.getBalance()));
                        moneyPanel.revalidate();
                        moneyPanel.repaint();
                        removeOrder(order);
                        counterPanel.removeClient(order.getOrderNumber());
                    }
                    burners[kitchenPanel.getSelectedBurnerIndex()].reset();
                    kitchenPanel.updateBurnerImages();
                    kitchenPanel.focusPanel();
                    default:
                    break;
                }
            }
        });

        orderPanel.add(button);
        orderPanel.add(Box.createVerticalStrut(5));
        orderButtons.put(order.getOrderNumber(), button);
        orderTimeLabels.put(order.getOrderNumber(), timeleftLabel);

        panel.revalidate();
        panel.repaint();
    }

    public void updateButtonTimers() {
        SwingUtilities.invokeLater(() -> {
            for (Map.Entry<Integer, JLabel> entry : orderTimeLabels.entrySet()) {
                int orderNumber = entry.getKey();
                JLabel label = entry.getValue();
                long timeLeft = clock.getTimeEnd(orderNumber) - clock.getTime();

                if (timeLeft < 0) timeLeft = 0;
                label.setText("Time left: " + timeLeft + "s");
            }
        });
    }

}

