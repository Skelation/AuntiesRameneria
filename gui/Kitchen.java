package gui;

import models.*;

import java.awt.*;
import java.awt.RenderingHints.Key;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.*;

public class Kitchen implements KeyListener {
    private Stove stove;
    private Clock clock;
    private JToggleButton[] burnerButtons;
    public JPanel panel = new JPanel(new GridBagLayout());
    private GridBagConstraints gbc = new GridBagConstraints();
    private Map<Integer, JLabel> orderTimeLabels = new HashMap<>();

    private JPanel stovePanel = new JPanel(new GridBagLayout());
    private JPanel burnersPanel = new JPanel();
    private JPanel ingredientsPanel = new JPanel();

    String[] seasonings = {"Water", "Noodles", "Carbonara", "Hot Chicken", "2x Spicy",
            "3x Spicy", "Cheese", "Habanero Lime"};
    String[] toppings = {"Shiitake", "PorkLoin", "FriedEggs", "KaraAge",
                "Katsu", "Gyoza", "SpringOnion"};

    private String[] seasoningImages = {"2xSpicy.png", "Chicken.png", "Carbonara.png", "3xSpicy.png", 
        "Cheese.png", "HabaneroLime.png", "Water.png", "Noodles.png"};

    private String[] toppingImages = {"ShiitakeMFULL.png", "PorkLoin.png", "FriedEggs.png", "KaraAge.png", 
        "Katsu.png", "Gyoza.png", "SpringOnion.png"};

    private String[] burnerLowImages = {"BowlRamen.png", "BowlRawRamen.png", "BowlWater.png", "EmptyBowl.png"};

    public String[] burnerHighImages = {"BowlRamen.png", "BowlRawRamen.png", "BowlWater.png", "EmptyBowl.png",
        "JustShiitake.png", "JustPorkLoin.png", "JustFriedEggs.png", "JustGyoza.png", "JustSpringOnion.png", "EggAndGyoza.png",
        "FriedEggsAndPorkLoin.png", "FriedEggsAndShiitake.png", "FriedEggsAndSpringOnion.png", "PorkLoinAndGyoza.png", "ShiitakeAndGyoza.png", "ShiitakeAndPorkLoin.png", 
        "SpringOnionAndGyoza.png", "SpringOnionAndPorkLoin.png", "SpringOnionAndShiitake.png"
    };



    private int selectedBurnerIndex = 0;
    private JToggleButton[] seasoningButtons;
    private JToggleButton[] toppingButtons;

    private Map<String, ImageIcon> imageCache = new HashMap<>();
    private Map<String, ImageIcon> scaledImageCache = new HashMap<>();
    
    private static String ASSETS_PATH = "Assets/";

    private void preloadImages() {
                for (String imageName : seasoningImages) {
            loadAndCacheImage(imageName);
        }
        for (String imageName : toppingImages) {
            loadAndCacheImage(imageName);
        }
        // for (String imageName : burnerLowImages) {
        //     loadAndCacheImage(imageName);
        // }
        for (String imageName : burnerHighImages) {
            loadAndCacheImage(imageName);
        }
    }
    
    private void loadAndCacheImage(String imageName) {
        String fullPath = ASSETS_PATH + imageName;
        ImageIcon originalIcon = new ImageIcon(fullPath);
        
        imageCache.put(imageName, originalIcon);

        Image scaledImage = originalIcon.getImage().getScaledInstance(120, 120, Image.SCALE_DEFAULT);
        scaledImageCache.put(imageName, new ImageIcon(scaledImage));
    }

    private ImageIcon getCachedImage(String imageName, int size) {
        if (size == 120) {
            return scaledImageCache.get(imageName);
        } else if (size == 300) {
            String cacheKey = imageName + "_" + size;
            if (!scaledImageCache.containsKey(cacheKey)) {
                ImageIcon original = imageCache.get(imageName);
                if (original != null) {
                    Image scaledImage = original.getImage().getScaledInstance(size, size, Image.SCALE_SMOOTH);
                    scaledImageCache.put(cacheKey, new ImageIcon(scaledImage));
                }
            }
            return scaledImageCache.get(cacheKey);
        }
        return imageCache.get(imageName);
    }


    public Kitchen(Stove stove, Clock clock) {
        this.stove = stove;
        this.clock = clock;
        this.burnerButtons = new JToggleButton[stove.getBurners().length];
        this.seasoningButtons = new JToggleButton[seasonings.length];
        this.toppingButtons = new JToggleButton[toppings.length];
        burnersPanel.setOpaque(false);
        stovePanel.setBackground(Color.GRAY);
        gbc.fill = GridBagConstraints.BOTH;
        
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.weighty = 0.98;
        stovePanel.setBackground(Color.blue);

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

        preloadImages();

        //Stove Panel
        for (int i = 0; i < stove.getBurners().length; i++) {
            ImageIcon icon = new ImageIcon("Assets/EmptyBowl.png");
            Image image = icon.getImage();
            image = image.getScaledInstance(300, 300, Image.SCALE_DEFAULT);
            icon = new ImageIcon(image);
            JToggleButton button = new JToggleButton(icon);
            button.setPreferredSize(new Dimension(300, 300));
            button.setBorder(BorderFactory.createEmptyBorder());
            button.setContentAreaFilled(false);
            long timeLeft = clock.getTimeCookEnd(i) - clock.getTime();
            JLabel timeLeftLabel = new JLabel(String.valueOf(timeLeft));
            burnerButtons[i] = button;

            orderTimeLabels.put(i, timeLeftLabel);
            JPanel burnerPanel = new JPanel(new BorderLayout());
            burnerPanel.setOpaque(false);
            burnerPanel.add(timeLeftLabel, BorderLayout.NORTH);
            burnerPanel.add(button, BorderLayout.SOUTH);
            stovePanel.add(burnerPanel);
        }
        updateBurnerImages();
        updateBurnerSelection();
        panel.add(stovePanel, gbc);
    
        //Ingredients panel
        gbc.gridy = 1;
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
            JToggleButton button = createIngredientButton(pathToImage);
            seasoningsPanel.add(button);

            seasoningButtons[i] = button;
            
            final int seasoningIndex = i;
            button.addActionListener(e -> {
                Ramen ramen = stove.getBurners()[selectedBurnerIndex].getRamen();
                switch (seasoningIndex) {
                    case (0):
                        ramen.addWater();
                        break;
                    case (1):
                        ramen.addNoodle();
                        break;
                    case (2):
                        ramen.addSeasoning("Carbonara");
                        break;
                    case (3):
                        ramen.addSeasoning("Hot Chicken");
                        break;
                    case (4):
                        ramen.addSeasoning("2x Spicy");
                        break;
                    case (5):
                        ramen.addSeasoning("3x Spicy");
                        break;
                    case (6):
                        ramen.addSeasoning("Cheese");
                        break;
                    case (7):
                        ramen.addSeasoning("Habanero Lime");
                        break;
                    default:
                        System.out.println("Not handled yet");
                        break;
                }
                updateBurnerImages();
                focusPanel();
            });

            panel.add(ingredientsPanel, gbc);
        }

        //Second row Toppings
        for (int i = 0; i < toppings.length; i++) {
            String pathToImage = getToppingImage(toppings[i]);
            JToggleButton button = createIngredientButton(pathToImage);
            toppingButtons[i] = button;
            toppingsPanel.add(button);

            final int toppingIndex = i;
            button.addActionListener(e -> {
                Ramen ramen = stove.getBurners()[selectedBurnerIndex].getRamen();
                if (ramen.getToppings().size() < 2) {
                    switch (toppingIndex) {
                        case (0):
                            ramen.addTopping("Shiitake");
                            break;
                        case (1):
                            ramen.addTopping("PorkLoin");
                            break;
                        case (2):
                            ramen.addTopping("FriedEggs");
                            break;
                        case (3):
                            ramen.addTopping("KaraAge");
                            break;
                        case (4):
                            ramen.addTopping("Katsu");
                            break;
                        case (5):
                            ramen.addTopping("Gyoza");
                            break;
                        case (6):
                            ramen.addTopping("SpringOnion");
                            break;
                        default:
                            System.out.println("Not handled yet");
                            break;
                    }
                    updateBurnerImages();
                    focusPanel();
                }
            });

            panel.add(ingredientsPanel, gbc);
        }
    }

    private void updateBurnerSelection() {
        for (int i = 0; i < burnerButtons.length; i++) {
            if (burnerButtons[i] != null) {
                if (i == selectedBurnerIndex) {
                    burnerButtons[i].setBorder(BorderFactory.createLineBorder(Color.GREEN, 3));
                    burnerButtons[i].setBorderPainted(true);
                } else {
                    burnerButtons[i].setBorderPainted(false);
                }
            }
        }
    }

    public void updateBurnerImages() {
        for (int i = 0 ; i < burnerButtons.length; i++) {
            Ramen ramen = stove.getBurners()[i].getRamen();
            String state = ramen.getState(burnerHighImages);
            String imageName = state.replace(ASSETS_PATH, "");
            ImageIcon icon = getCachedImage(imageName, 300);
            burnerButtons[i].setIcon(icon);
        }
    }


    public void updateKitchenTimers() {
        SwingUtilities.invokeLater(() -> {
            for (Map.Entry<Integer, JLabel> entry : orderTimeLabels.entrySet()) {
                int burnerNumber = entry.getKey();
                JLabel label = entry.getValue();
                long timeLeft = clock.getTimeCookEnd(burnerNumber) - clock.getTime();

                if (timeLeft < 0) timeLeft = 0;
                if (timeLeft == 0) {
                    label.setText("<Enter> to cook");
                } else {
                    label.setText(timeLeft + "s");
                }
            }
        });
    }

    private JToggleButton createIngredientButton(String pathToImage) {
        ImageIcon icon = new ImageIcon();
        icon = new ImageIcon(pathToImage);
        Image image = icon.getImage();
        image = image.getScaledInstance(120, 120, Image.SCALE_DEFAULT);
        icon = new ImageIcon(image);
        JToggleButton button = new JToggleButton(icon);
        button.setPreferredSize(new Dimension(120, 120));
        button.setBorder(BorderFactory.createEmptyBorder());
        button.setContentAreaFilled(false);
        return button;

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
            case ("PorkLoin"):
                return "Assets/PorkLoinH.png";
            case ("FriedEggs"):
                return "Assets/FriedEggsH.png";
            case ("KaraAge"):
                return "Assets/KaraAge.png";
            case ("Katsu"):
                return "Assets/Katsu.png";
            case ("Gyoza"):
                return "Assets/GyozaH.png";
            case ("SpringOnion"):
                return "Assets/SpringOnionH.png";
            default:
                return "Assets/SpringOnionH.png";
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                if (selectedBurnerIndex > 0) {
                    selectedBurnerIndex--;
                    updateBurnerSelection();
                }
            break;
            case KeyEvent.VK_RIGHT:
                if (selectedBurnerIndex < burnerButtons.length - 1) {
                    selectedBurnerIndex++;
                    updateBurnerSelection();
                }
            break;
            case KeyEvent.VK_ENTER:
                if (stove.getBurners()[selectedBurnerIndex].getRamen().isReadyToCook()) {
                    clock.eventTimes.put(clock.getTime() + 5, String.format("TimeDoneCooking %d", selectedBurnerIndex));
                }
                break;
            case KeyEvent.VK_DELETE:
                Burner selectedBurner = stove.getBurners()[selectedBurnerIndex];
                selectedBurner.reset();
                updateBurnerImages();
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

    public int getSelectedBurnerIndex() {
        return selectedBurnerIndex;
    }
    
    public void focusPanel() {
        SwingUtilities.invokeLater(() -> {
            if (!panel.hasFocus()) {
                panel.requestFocusInWindow();
            }
        });
    }

    public Stove getStove() {
        return this.stove; 
    }

    public void setStove(Stove stove) {
        this.stove = stove;
    }
}
