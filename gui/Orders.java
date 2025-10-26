package gui;

import models.*;

import javax.swing.*;

import org.w3c.dom.events.MouseEvent;

import gui.Counter;
import gui.Kitchen;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Orders {
    public JPanel panel = new JPanel();
    public JPanel orderPanel = new JPanel();
    public JPanel moneyPanel = new JPanel();
    private JPanel messagePanel = new JPanel();
    private JTabbedPane jTabbedPane;
    private Kitchen kitchenPanel;
    private Counter counterPanel;
    private Fridge fridgePanel;
    private ArrayList<Order> orders;
    private Map<Integer, JLabel> orderTimeLabels;
    public Map<Integer, OrderButton> orderButtons;
    private Map<Order, ArrayList<JLabel>> orderLabelMap = new HashMap<>();
    private Bank bank;
    public JLabel balanceLabel;
    private Clock clock;

    public Orders(ArrayList<Order> orders, JTabbedPane tabbedPane, Kitchen kitchenPanel, Counter counterPanel, Fridge fridgePanel, Bank bank, Clock clock) {
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(new Color(245, 245, 245));

        orderPanel.setLayout(new BoxLayout(orderPanel, BoxLayout.Y_AXIS));
        orderPanel.setBackground(new Color(250, 250, 250));

        moneyPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 5));
        moneyPanel.setBackground(new Color(235, 235, 235));
        moneyPanel.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, new Color(210, 210, 210)));


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
        balanceLabel = new JLabel(String.format("$: %.2f", bank.getBalance()));
        balanceLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        orderPanel.setMinimumSize(new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE));
        moneyPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));

        messagePanel.setLayout(new BoxLayout(messagePanel, BoxLayout.Y_AXIS));
        messagePanel.setBackground(new Color(245, 245, 245));
        
        moneyPanel.add(balanceLabel);
        panel.add(orderPanel);
        panel.add(moneyPanel);
        panel.add(messagePanel);
    }

    public void removeOrder(Order order) {
        int orderNumber = order.getOrderNumber();
        orders.remove(order);

        OrderButton button = orderButtons.remove(orderNumber);
        SwingUtilities.invokeLater(() -> {
            orderPanel.remove(button);
            orderPanel.revalidate();
            orderPanel.repaint();
        });

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

        ArrayList<JLabel> labels = new ArrayList<>();

        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        content.setOpaque(false);
        content.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

        JLabel timeleftLabel = new JLabel(String.valueOf(timeLeft));
        timeleftLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        
        content.add(timeleftLabel); 
        labels.add(timeleftLabel);
        JLabel seasoningLabel = new JLabel(order.getRamen().getSeasoning());
        seasoningLabel.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        content.add(seasoningLabel);
        labels.add(seasoningLabel);
        for (String topping : order.getRamen().getToppings()) {
            JLabel toppingLabel = new JLabel("+ " + topping);
            toppingLabel.setFont(new Font("Segoe UI", Font.PLAIN, 13));
            content.add(toppingLabel);
            labels.add(toppingLabel);
        }
        if (order.getDrink() != null) {
            JLabel drinkLabel = new JLabel("+ " + order.getDrink().getName());
            drinkLabel.setFont(new Font("Segoe UI", Font.PLAIN, 13));
            content.add(drinkLabel);
            labels.add(drinkLabel);
        }

        orderLabelMap.put(order, labels);

        OrderButton button = new OrderButton();
        button.setLayout(new BorderLayout());
        button.setBackground(Color.WHITE);
        button.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(220, 220, 220), 1, true),
            BorderFactory.createEmptyBorder(8, 12, 8, 12)
        ));

        button.setFont(new Font("Segoe UI", Font.BOLD, 13));
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setOpaque(true);

        button.add(content, BorderLayout.CENTER);

        button.setAlignmentX(Component.LEFT_ALIGNMENT);
        button.setMaximumSize(new Dimension(Integer.MAX_VALUE, button.getPreferredSize().height));
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int tabIndex = jTabbedPane.getSelectedIndex();
                int orderNumber = order.getOrderNumber();
                switch (tabIndex) {
                    case 0:
                    break;
                    case 1:
                        Stove stove = kitchenPanel.getStove();
                        Burner[] burners = stove.getBurners();
                        Ramen selectedRamen = burners[kitchenPanel.getSelectedBurnerIndex()].getRamen();
                        if (selectedRamen.matches(order.getRamen())) {
                            button.setUserRamen(selectedRamen);
                            if (button.isComplete()) {
                                rightSubmit(clock, bank, balanceLabel, orderNumber, order);
                            }
                            updateOrderDisplay(order, button.hasRamen(), button.hasDrink());
                        } else {
                            wrongSubmit(clock, bank, balanceLabel, orderNumber, order);
                        }
                        burners[kitchenPanel.getSelectedBurnerIndex()].reset();
                        kitchenPanel.updateBurnerImages();
                        kitchenPanel.focusPanel();
                        break;
                    case 2:
                        Drink selectedDrink = fridgePanel.getSelectedDrink();
                        button.setUserDrink(selectedDrink);
                        if (selectedDrink.getName().equals(order.getDrink().getName())) {
                            button.setUserDrink(selectedDrink);
                            if (button.isComplete()) {
                                rightSubmit(clock, bank, balanceLabel, orderNumber, order);
                            } else {
                                updateOrderDisplay(order, button.hasRamen(), button.hasDrink());
                            }
                        } else {
                            wrongSubmit(clock, bank, balanceLabel, orderNumber, order);
                        }
                        fridgePanel.focusPanel();
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

    public void updateOrderDisplay(Order order, boolean ramenDone,  boolean drinkDone) {
        ArrayList<JLabel> labels = orderLabelMap.get(order);

        int index = 1;

        for (int i = 0; i < order.getRamen().getToppings().size() + 1 && index < labels.size(); i++) {
            JLabel label = labels.get(index++);
            String text = label.getText().replaceFirst("^[✓+]\\s", "");
            label.setText((ramenDone ? "✓ " : "+ ") + text);
        }

        if (order.getDrink() != null && index < labels.size()) {
            JLabel label = labels.get(index + 0);
            String text = label.getText().replaceFirst("^[✓+]\\s", "");
            label.setText((drinkDone ? "✓ " : "+ ") + text);
        }
        panel.revalidate();
        panel.repaint();
    }

    public void wrongSubmit(Clock clock, Bank bank, JLabel balanceLabel, int orderNumber, Order order) {
        int amount = 10;
        clock.deleteEvent(orderNumber);
        bank.removeAmount(amount);
        balanceLabel.setText(String.format("$: %.2f", bank.getBalance()));
        moneyPanel.revalidate();
        moneyPanel.repaint();
        removeOrder(order);
        counterPanel.removeClient(order.getOrderNumber());
        String message = String.format("Wrong Submit -$%d", amount);
        addMessage(message, "wrong");
    }

    public void rightSubmit(Clock clock, Bank bank, JLabel balanceLabel, int orderNumber, Order order) {
        long timeLeft = clock.getTimeEnd(orderNumber) - clock.getTime();
        double[] payArray = bank.calculatePay(order, timeLeft, clock.getTime());
        double pay = payArray[0];
        double timeMultiplier = payArray[1];
        double complexityMultiplier = payArray[2];
        double survivalMultiplier = payArray[3];
        clock.deleteEvent(orderNumber);
        bank.addAmount(pay);
        balanceLabel.setText(String.format("$: %.2f", bank.getBalance()));
        moneyPanel.revalidate();
        moneyPanel.repaint();
        removeOrder(order);
        counterPanel.removeClient(order.getOrderNumber());
        String message = String.format("Correct Submit +$%.2f", pay);
        addMessage(message, "right");
        message = String.format("Time Multiplier *%.2f", timeMultiplier);
        addMessage(message, "right");
        message = String.format("Complexity Multiplier *%.2f", complexityMultiplier);
        addMessage(message, "right");
        message = String.format("Survival Multiplier *%.2f", survivalMultiplier);
        addMessage(message, "right");
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

    public void addMessage(String s, String type) {
        JLabel outputLabel = new JLabel(s);
        switch (type) {
            case "normal":
                outputLabel.setFont(new Font("Arial", Font.PLAIN, 16));
                break;
            case "right":
                outputLabel.setFont(new Font("Arial", Font.PLAIN, 16));
                outputLabel.setForeground(Color.decode("#107f30"));
                break;
            case "wrong":
                outputLabel.setFont(new Font("Arial", Font.PLAIN, 16));
                outputLabel.setForeground(Color.RED);
                break;
        }
        messagePanel.add(outputLabel, 0);
        Timer timer = new Timer(3000, e -> {
            messagePanel.remove(outputLabel);
        });
        timer.start();
        messagePanel.revalidate();
        messagePanel.repaint();
    }
}

