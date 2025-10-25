package gui;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.swing.*;
import models.*;

public class Counter implements KeyListener {
    public JPanel panel = new JPanel(new GridBagLayout());
    private JPanel topPanel = new JPanel(new BorderLayout()) {
        private Image backgroundImage = new ImageIcon("Assets/resto.jpg").getImage();

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    };


    private Map<Integer, JButton> clientButtons;
    private List<JButton> clientList = new ArrayList<>();
    private int selectedClientIndex = 0;

    private JPanel bottomPanel = new JPanel();

    private JPanel SpacerPanel = new JPanel();
    private JPanel clientsPanel = new JPanel();

    private GridBagConstraints gbc = new GridBagConstraints();

    public Counter(ArrayList<Order> orders) {
        clientButtons = new HashMap<>();

        panel.setFocusable(true);
        panel.addKeyListener(this);

        // Ensures that keylistener focuses for the panel we are on
        panel.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent event) {
                panel.requestFocusInWindow();
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent event) {}
            public void ancestorMoved(javax.swing.event.AncestorEvent event) {}
        });

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

        for (int i = 0; i < orders.size(); i++) {
            addClient(orders.get(i).getOrderNumber());
        }
        updateClientSelection();
    }

    public void removeClient(int orderNumber) {
        JButton button = clientButtons.remove(orderNumber);
        button.setFocusable(false);
        if (button != null) {
            clientList.remove(button);
            clientsPanel.remove(button);
        }

        if (selectedClientIndex >= clientList.size()) {
            selectedClientIndex = Math.max(0, clientList.size() - 1);
        }

        panel.revalidate();
        panel.repaint();
        updateClientSelection();
    }

    public void addClient(int orderNumber) {
        ImageIcon icon = new ImageIcon();
        Random r = new Random();
        int n = r.nextInt(0, 1);
        switch (n) {
            case 0:
            icon = new ImageIcon("Assets/C1Happy.png");
            break;
            case 1:
            icon = new ImageIcon("Assets/C2Happy.png");
            break;
            // case 2:
            // icon = new ImageIcon("Assets/SlimMan.png");
            // break;
            default:
            icon = new ImageIcon("Assets/FatMan.png");
            break;
        }
        Image image = icon.getImage();
        image = image.getScaledInstance(400, 400, Image.SCALE_DEFAULT);
        icon = new ImageIcon(image);

        JButton button = new JButton(icon);
        button.setPreferredSize(new Dimension(400, 400));
        panel.add(button);
        button.setBorder(BorderFactory.createEmptyBorder());
        button.setContentAreaFilled(false);

        clientButtons.put(orderNumber, button);
        clientList.add(button);
        clientsPanel.add(button);

        clientsPanel.revalidate();
        clientsPanel.repaint();
        updateClientSelection();
    }

    private void updateClientSelection() {
        for (int i = 0; i < clientList.size(); i++) {
            JButton button = clientList.get(i);
            if (i == selectedClientIndex) {
                button.setBorder(BorderFactory.createLineBorder(Color.GREEN, 3));
                button.setBorderPainted(true);
            } else {
                button.setBorderPainted(false);
            }
        }
        focusPanel();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT:
            if (selectedClientIndex > 0) {
                selectedClientIndex--;
                updateClientSelection();
            }
            break;
            case KeyEvent.VK_RIGHT:
            if (selectedClientIndex< clientList.size() - 1) {
                selectedClientIndex++;
                updateClientSelection();
            }
            break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        //Useless but needed
    }

    @Override
    public void keyTyped(KeyEvent e) {
        //Useless but needed
    }

    public int getSelectedClientIndex() {
        return selectedClientIndex;
    }

    public void focusPanel() {
        SwingUtilities.invokeLater(() -> {
            if (!panel.hasFocus()) {
                panel.requestFocusInWindow();
            }
        });
    }
}
