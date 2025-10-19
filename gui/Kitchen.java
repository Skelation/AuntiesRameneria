package gui;

import java.awt.*;
import javax.swing.*;

public class Kitchen {
    public JPanel panel = new JPanel(new GridBagLayout());
    private GridBagConstraints gbc = new GridBagConstraints();

    private JPanel stovePanel = new JPanel();
    private JPanel ingredientsPanel = new JPanel();

    String[] seasonings = {"Water", "Noodles", "Carbonara", "Hot Chicken", "2x Spicy",
            "3x Spicy", "Cheese", "Habanero Lime"};
    String[] toppings = {"Shiitake", "Pork loin", "Fried eggs", "KaraAge chicken",
                "Katsu chicken", "Gyoza", "Spring onions"};

    public Kitchen() {
        gbc.fill = GridBagConstraints.BOTH;
        
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.weighty = 0.98;
        stovePanel.setBackground(Color.blue);
        panel.add(stovePanel, gbc);
    
        gbc.gridy = 1;
        gbc.weightx = 1;
        gbc.weighty = 0.02;
        ingredientsPanel.setPreferredSize(new Dimension(100, 300));

        ingredientsPanel.setLayout(new BoxLayout(ingredientsPanel, BoxLayout.Y_AXIS));
        JPanel seasoningsPanel = new JPanel();
        JPanel toppingsPanel = new JPanel();
        ingredientsPanel.add(seasoningsPanel);
        ingredientsPanel.add(toppingsPanel);

        for (int i = 0; i < seasonings.length; i++) {
            ImageIcon icon = new ImageIcon();
            if (seasonings[i] == "2x Spicy") {
                icon = new ImageIcon("Assets/2xSpicy.png");
            } else if (seasonings[i] == "Hot Chicken") {
                icon = new ImageIcon("Assets/Chicken.png");
            } else if (seasonings[i] == "Carbonara") {
                icon = new ImageIcon("Assets/Carbonara.png");
            } else if (seasonings[i] == "3x Spicy") {
                icon = new ImageIcon("Assets/3xSpicy.png");
            } else if (seasonings[i] == "Cheese") {
                icon = new ImageIcon("Assets/Cheese.png");
            } else if (seasonings[i] == "Habanero Lime") {
                icon = new ImageIcon("Assets/HabaneroLime.png");
            } else if (seasonings[i] == "Water") {
                icon = new ImageIcon("Assets/Water.png");
            } else if (seasonings[i] == "Noodles") {
                icon = new ImageIcon("Assets/Noodles.png");
            } else {
                icon = new ImageIcon("Assets/Cheese.png");
            }
            Image image = icon.getImage();
            image = image.getScaledInstance(120, 120, Image.SCALE_DEFAULT);
            icon = new ImageIcon(image);
            JButton button = new JButton(icon);
            button.setPreferredSize(new Dimension(120, 120));
            seasoningsPanel.add(button);
            button.setBorder(BorderFactory.createEmptyBorder());
            button.setContentAreaFilled(false);
        }

        for (int i = 0; i < toppings.length; i++) {
            ImageIcon icon = new ImageIcon();
            if (toppings[i] == "Shiitake") {
                icon = new ImageIcon("Assets/ShiitakeMFULL.png");
            } else if (toppings[i] == "Pork loin") {
                icon = new ImageIcon("Assets/PorkLoin.png");
            } else if (toppings[i] == "Fried eggs") {
                icon = new ImageIcon("Assets/Egg.png");
            } else if (toppings[i] == "KaraAge chicken") {
                icon = new ImageIcon("Assets/KaraAge.png");
            } else if (toppings[i] == "Katsu chicken") {
                icon = new ImageIcon("Assets/Katsu.png");
            } else if (toppings[i] == "Gyoza") {
                icon = new ImageIcon("Assets/Gyoza.png");
            } else if (toppings[i] == "Spring onions") {
                icon = new ImageIcon("Assets/SpringOnion.png");
            } else {
                icon = new ImageIcon("Assets/Shiitake.png");
            }
            Image image = icon.getImage();
            image = image.getScaledInstance(120, 120, Image.SCALE_DEFAULT);
            icon = new ImageIcon(image);
            JButton button = new JButton(icon);
            button.setPreferredSize(new Dimension(120, 120));
            toppingsPanel.add(button);
            button.setBorder(BorderFactory.createEmptyBorder());
            button.setContentAreaFilled(false);
        }

        ingredientsPanel.setBackground(Color.gray);
        panel.add(ingredientsPanel, gbc);
    }
}
