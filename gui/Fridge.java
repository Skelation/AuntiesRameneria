package gui;

import java.awt.*;
import javax.swing.*;

public class Fridge {
    public JPanel panel = new JPanel(new GridBagLayout());
    private GridBagConstraints gbc = new GridBagConstraints();

    String[] drinks = {"Soda", "Soju", "Kombucha", "Beer"};

    public Fridge() {
        panel.setBackground(Color.orange);
        ButtonGroup group = new ButtonGroup();

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
            button.setPreferredSize(new Dimension(300, 300));
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
            group.add(button);
            panel.add(button);
        }
    }
}
