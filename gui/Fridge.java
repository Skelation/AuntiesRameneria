package gui;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import models.Drink;

import javax.swing.*;

public class Fridge implements KeyListener {
    public JPanel panel = new JPanel(new GridBagLayout());
    private GridBagConstraints gbc = new GridBagConstraints();
    private JToggleButton[] drinkButtons;
    private int selectedDrinkIndex = 0;

    String[] drinks = {"Soda", "Soju", "Kombucha", "Beer"};

    public Fridge() {
        panel.setBackground(Color.orange);
        drinkButtons = new JToggleButton[drinks.length];

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

        for (int i = 0; i < drinks.length; i++) {
            ImageIcon icon = new ImageIcon();
            if (drinks[i] == "Soda") {
                icon = new ImageIcon("Assets/Soda.png");
            } else if (drinks[i] == "Soju") {
                icon = new ImageIcon("Assets/Soju.png");
            } else if (drinks[i] == "Kombucha") {
                icon = new ImageIcon("Assets/HalloKombucha.png");
            } else if (drinks[i] == "Beer") {
                icon = new ImageIcon("Assets/Beer.png");
            } else {
                icon = new ImageIcon("Assets/Shiitake.png");
            }

            Image image = icon.getImage();
            image = image.getScaledInstance(300, 300, Image.SCALE_DEFAULT);
            icon = new ImageIcon(image);
            JToggleButton button = new JToggleButton(icon);
            button.setFocusable(false);
            button.setPreferredSize(new Dimension(300, 300));
            button.setBorder(BorderFactory.createEmptyBorder());
            button.setContentAreaFilled(false);

            drinkButtons[i] = button;
            panel.add(button);
        }
        updateDrinkSelection();
    }

    private void updateDrinkSelection() {
        for (int i = 0; i < drinkButtons.length; i++) {
            if (drinkButtons[i] != null) {
                if (i == selectedDrinkIndex) {
                    drinkButtons[i].setBorder(BorderFactory.createLineBorder(Color.GREEN, 3));
                    drinkButtons[i].setBorderPainted(true);
                } else {
                    drinkButtons[i].setBorderPainted(false);
                }
            }
        }
        focusPanel();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT:
            if (selectedDrinkIndex > 0) {
                selectedDrinkIndex--;
                updateDrinkSelection();
            }
            break;
            case KeyEvent.VK_RIGHT:
            if (selectedDrinkIndex< drinkButtons.length - 1) {
                selectedDrinkIndex++;
                updateDrinkSelection();
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

    public int getSelectedDrinkIndex() {
        return selectedDrinkIndex;
    }

    public Drink getSelectedDrink() {
        return new Drink().setName(drinks[selectedDrinkIndex]);
    }

    public void focusPanel() {
        SwingUtilities.invokeLater(() -> {
            if (!panel.hasFocus()) {
                panel.requestFocusInWindow();
            }
        });
    }

}
