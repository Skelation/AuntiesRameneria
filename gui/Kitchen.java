package gui;

import java.awt.*;
import javax.swing.*;

public class Kitchen {
    public JPanel panel = new JPanel(new GridBagLayout());
    private GridBagConstraints gbc = new GridBagConstraints();

    private JPanel stovePanel = new JPanel();
    private JPanel ingredientsPanel = new JPanel();

    public Kitchen() {
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = 0;
        

        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.weighty = 0.8;
        stovePanel.setBackground(Color.blue);
        panel.add(stovePanel, gbc);
    
        gbc.gridy = 1;
        gbc.weightx = 1;
        gbc.weighty = 0.2;
        ingredientsPanel.setBackground(Color.gray);
        panel.add(ingredientsPanel, gbc);
    }
}
