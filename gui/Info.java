package gui;

import java.awt.*;

import javax.swing.*;

public class Info {
    public JPanel panel = new JPanel(new GridBagLayout());


    public Info() {
        // gbc.anchor = GridBagConstraints.LINE_START;
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        // gbc.weighty = 1;

        panel.setBackground(Color.black);
        panel.setFocusable(true);

        Image image = (new ImageIcon("Assets/AuntiesRameneriaLogo.jpg")).getImage();
        image = image.getScaledInstance(500, 500, Image.SCALE_DEFAULT);
        JLabel logo = new JLabel(new ImageIcon(image));

        JLabel l1 = new JLabel("Welcome to Auntie's Rameneria!!!");
        l1.setFont(new Font("Montserrat", Font.BOLD, 40));
        l1.setForeground(Color.white);

        JLabel l2 = new JLabel("Instructions:");
        l2.setFont(new Font("Montserrat", Font.BOLD, 35));
        l2.setForeground(Color.white);

        JLabel l3 = new JLabel("1. The game has 3 scenes — Counter, Kitchen, and Drinks.");
        l3.setFont(new Font("Montserrat", Font.PLAIN, 25));
        l3.setForeground(Color.white);

        JLabel l4 = new JLabel("2. The goal is to make Ramen that satisfies the customer's orders.");
        l4.setFont(new Font("Montserrat", Font.PLAIN, 25));
        l4.setForeground(Color.white);

        JLabel l5 = new JLabel("3. In the Kitchen scene, pick the bowl using arrow keys.");
        l5.setFont(new Font("Montserrat", Font.PLAIN, 25));
        l5.setForeground(Color.white);

        JLabel l6 = new JLabel("Add water, noodles and seasoning by clicking them from the ingredients in the bottom.");
        l6.setFont(new Font("Montserrat", Font.PLAIN, 25));
        l6.setForeground(Color.white);

        JLabel l7 = new JLabel("4. Hit enter to cook the noodles. Then, use the ingredients panel again to add the required toppings to the ramen.");
        l7.setFont(new Font("Montserrat", Font.PLAIN, 25));
        l7.setForeground(Color.white);

        JLabel l8 = new JLabel("5. While the ramen is selected, click on the order from the order panel to add to it.");
        l8.setFont(new Font("Montserrat", Font.PLAIN, 25));
        l8.setForeground(Color.white);

        JLabel l9 = new JLabel("5. If the order contains drinks, do the same from the drinks tab.");
        l9.setFont(new Font("Montserrat", Font.PLAIN, 25));
        l9.setForeground(Color.white);

        JLabel l10 = new JLabel("6 The goal is to not go bankrupt! Enjoy the game!.");
        l10.setFont(new Font("Montserrat", Font.PLAIN, 25));
        l10.setForeground(Color.white);

        panel.add(logo, gbc);
        panel.add(l1, gbc);
        panel.add(l2, gbc);
        panel.add(l3, gbc);
        panel.add(l4, gbc);
        panel.add(l5, gbc);
        panel.add(l6, gbc);
        panel.add(l7, gbc);
        panel.add(l8, gbc);
        panel.add(l9, gbc);
        panel.add(l10, gbc);

        focusPanel();
    }

    public void focusPanel() {
        SwingUtilities.invokeLater(() -> {
            if (!panel.hasFocus()) {
                panel.requestFocusInWindow();
            }
        });
    }
}
