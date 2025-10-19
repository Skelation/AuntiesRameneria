package gui;

import models.Ramen;
import models.Stove;

import java.awt.*;
import javax.swing.*;

public class Kitchen {
    public JPanel panel = new JPanel(new GridBagLayout());
    private GridBagConstraints gbc = new GridBagConstraints();

    private JPanel stovePanel = new JPanel(new GridBagLayout());
    private JPanel burnersPanel = new JPanel();
    private JPanel ingredientsPanel = new JPanel();

    String[] seasonings = {"Water", "Noodles", "Carbonara", "Hot Chicken", "2x Spicy",
            "3x Spicy", "Cheese", "Habanero Lime"};
    String[] toppings = {"Shiitake", "Pork loin", "Fried eggs", "KaraAge chicken",
                "Katsu chicken", "Gyoza", "Spring onions"};

    public Kitchen(Stove stove) {
        burnersPanel.setOpaque(false);
        stovePanel.setBackground(Color.GRAY);
        gbc.fill = GridBagConstraints.BOTH;
        
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.weighty = 0.98;
        stovePanel.setBackground(Color.blue);

        //Stove Panel
        for (int i = 0; i < stove.getBurners().length; i++) {
            ImageIcon icon = new ImageIcon("Assets/BowlRamen.png");
            Image image = icon.getImage();
            image = image.getScaledInstance(300, 300, Image.SCALE_DEFAULT);
            icon = new ImageIcon(image);
            JToggleButton button = new JToggleButton(icon);
            button.setPreferredSize(new Dimension(300, 300));
            stovePanel.add(button);
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
        }

        panel.add(stovePanel, gbc);
    
        //Ingredients panel
        gbc.gridy = 1;
        gbc.weightx = 1;
        gbc.weighty = 0.02;
        ingredientsPanel.setPreferredSize(new Dimension(100, 300));

        ingredientsPanel.setLayout(new BoxLayout(ingredientsPanel, BoxLayout.Y_AXIS));
        JPanel seasoningsPanel = new JPanel();
        JPanel toppingsPanel = new JPanel();
        ingredientsPanel.add(seasoningsPanel);
        ingredientsPanel.add(toppingsPanel);
        
        //First row seasonings
        

        for (int i = 0; i < seasonings.length; i++) {
            ImageIcon icon = new ImageIcon();
            String pathToImage = getSeasoningImage(seasonings[i]);
            
            icon = new ImageIcon(pathToImage);
            Image image = icon.getImage();
            image = image.getScaledInstance(120, 120, Image.SCALE_DEFAULT);
            icon = new ImageIcon(image);
            JToggleButton button = new JToggleButton(icon);
            button.setPreferredSize(new Dimension(120, 120));
            seasoningsPanel.add(button);
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
        }
        

        //Second row Toppings
        for (int i = 0; i < toppings.length; i++) {
            ImageIcon icon = new ImageIcon();

            String pathToImage = getToppingImage(toppings[i]);
            icon = new ImageIcon(pathToImage);
            Image image = icon.getImage();
            image = image.getScaledInstance(120, 120, Image.SCALE_DEFAULT);
            icon = new ImageIcon(image);
            JToggleButton button = new JToggleButton(icon);
            button.setPreferredSize(new Dimension(120, 120));
            toppingsPanel.add(button);
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
            panel.add(ingredientsPanel, gbc);
        }
    }

    private String getSeasoningImage(String s) {
        switch (s) {
            case ("2x Spicy"):
                return "Assets/2xSpicy.png";
            case ("Hot Chicken"):
                return "Assets/Chicken.png";
            case ("Carbonara"):
                return "Assets/Carbonara.png";
            case ( "3x Spicy"):
                return "Assets/3xSpicy.png";
            case ("Cheese"):
                return "Assets/Cheese.png";
            case ( "Habanero Lime"):
                return "Assets/HabaneroLime.png";
            case ("Water"):
                return "Assets/Water.png";
            case ("Noodles"):
                return "Assets/Noodles.png";
            default:
                return "Assets/Noodles.png";
        }
    }

    private String getToppingImage(String s) {
        switch (s) {
            case ("Shiitake"):
                return "Assets/ShiitakeMFULL.png";
            case ( "Pork loin"):
                return "Assets/PorkLoin.png";
            case ( "Fried eggs"):
                return "Assets/Egg.png";
            case ( "KaraAge chicken"):
                return "Assets/KaraAge.png";
            case ( "Katsu chicken"):
                return "Assets/Katsu.png";
            case ("Gyoza"):
                return "Assets/Gyoza.png";
            case ( "Spring onions"):
                return "Assets/SpringOnion.png";
            default:
                return "Assets/SpringOnion.png";
        }
    }
}
