import javax.swing.*;

import Controller.DietLogic.DietLogic;
import Controller.DietLogic.IDietLogic;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



public class DietDataEntryPanel extends JPanel {

    private IDietLogic dietLogic = new DietLogic();

    private JComboBox<String> mealComboBox;
    private JTextField dateOfMeal;
    private JTextField mealName;
    private JButton submitButton;
    private JButton backButton;
    private List<JPanel> ingredientPanels = new ArrayList<>();
    private JPanel centralContainer;

    HashMap<Integer, String> meal = new HashMap<>();
    public HashMap<String, Float> ingredient = new HashMap<>();
    int mealId;

    public List<LocalDate> dateOfMealList;


    public DietDataEntryPanel(CardLayout cardLayout, JPanel cards) {
        setLayout(new BorderLayout());
        // setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.LIGHT_GRAY);

        // add(Box.createVerticalGlue());

        // Central container for dynamic components
        centralContainer = new JPanel();
        centralContainer.setLayout(new BoxLayout(centralContainer, BoxLayout.Y_AXIS));
        centralContainer.setBackground(Color.LIGHT_GRAY);
        add(centralContainer, BorderLayout.NORTH); // Add centralContainer to center

        JLabel headerLabel = new JLabel("Enter your diet data below:");
        headerLabel.setFont(new Font("Arial", Font.BOLD, 16));
        // headerLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel headerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        headerPanel.setBackground(Color.LIGHT_GRAY);
        headerPanel.add(headerLabel);
        centralContainer.add(headerPanel);

        mealName = new JTextField(20);
        JPanel mealPanel = createFieldPanel("Meal Name:", mealName);
        centralContainer.add(mealPanel);

        dateOfMeal = new JTextField(20);
        JPanel domPanel = createFieldPanel("Date of Meal (YYYY-MM-DD):", dateOfMeal);
        centralContainer.add(domPanel);

        String[] mealOptions = {"Breakfast", "Lunch", "Dinner", "Snack"};
        mealComboBox = new JComboBox<>(mealOptions);
        JPanel mealTypePanel = createFieldPanel("Type:", mealComboBox);
        centralContainer.add(mealTypePanel);

        JButton addIngredientButton = new JButton("Add Ingredient");
        JButton removeIngredientButton = new JButton("Remove Ingredient");
        addIngredientButton.addActionListener(e -> addIngredientPanel());
        removeIngredientButton.addActionListener(e -> removeIngredientPanel());

        JPanel addRemoveButtonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        addRemoveButtonPanel.setBackground(Color.LIGHT_GRAY);
        addRemoveButtonPanel.add(addIngredientButton);
        addRemoveButtonPanel.add(removeIngredientButton);
        centralContainer.add(addRemoveButtonPanel);

        // JPanel removeIngredientPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        // removeIngredientPanel.setBackground(Color.LIGHT_GRAY);
        // removeIngredientPanel.add(removeIngredientButton);
        // addCenteredPanel(removeIngredientPanel);

        submitButton = new JButton("Submit");
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBackground(Color.LIGHT_GRAY);
        buttonPanel.add(submitButton);
        
        backButton = new JButton("Back");
        buttonPanel.add(backButton);

        add(buttonPanel, BorderLayout.SOUTH);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                List<IngredientData> ingredients = new ArrayList<>();
                for (JPanel panel : ingredientPanels) {
                    Component[] components = panel.getComponents();
        
                    JComboBox<String> ingredientComboBox = (JComboBox<String>) components[1];
                    String ingredient = (String) ingredientComboBox.getSelectedItem();
        
                    JTextField amountField = (JTextField) components[3];
                    String amount = amountField.getText();

                    if (ingredient.isEmpty() || amount.isEmpty()) {
                        JOptionPane.showMessageDialog(DietDataEntryPanel.this, 
                            "Please fill in all Amount fields.", 
                            "Empty Field Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
        
                    ingredients.add(new IngredientData(ingredient, amount));
                }

                if (ingredientPanels.isEmpty()) {
                    JOptionPane.showMessageDialog(DietDataEntryPanel.this,
                        "Please add at least one ingredient before submitting.",
                        "No Ingredients Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
        
                int i = 1;
                try {

                    LocalDate parsedDateOfMeal = LocalDate.parse(dateOfMeal.getText());
                    ArrayList<Integer> dateTime = new ArrayList<>(5);
                    dateTime.add(parsedDateOfMeal.getYear());
                    dateTime.add(parsedDateOfMeal.getMonthValue());
                    dateTime.add(parsedDateOfMeal.getDayOfMonth());
                    dateTime.add(0); // Hour
                    dateTime.add(0); // Minute

                    // dateOfMealList.add(parsedDateOfMeal);

                    String mealType = (String) mealComboBox.getSelectedItem();

                    int userId = Launcher.userId;

                
                    meal = dietLogic.addMeal(mealName.getText(), mealType, dateTime, userId);

                    for (Map.Entry<Integer, String> entry : meal.entrySet()) {
                        if (entry.getValue().equals(mealName.getText())) {
                            entry.getKey();
                            mealId = entry.getKey();
                        }
                    }

                    for (IngredientData ingredientData : ingredients) {
                        dietLogic.addIngredient(mealId, ingredientData.getIngredient(), Float.parseFloat(ingredientData.getAmount()));
                    }
                                         
                    JOptionPane.showMessageDialog(null, "Diet Data Submitted Successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    cardLayout.show(cards, "MainMenu");
                    resetForm();

                } catch (DateTimeParseException ex) {
                    JOptionPane.showMessageDialog(DietDataEntryPanel.this,
                        "Invalid date format. Please enter the date in YYYY-MM-DD format.",
                        "Date Format Error", JOptionPane.ERROR_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(DietDataEntryPanel.this,
                        "Error: " + ex.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);
                        ex.printStackTrace();
                }
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cards, "MainMenu");
            }    
        });
    }        

    private void addIngredientPanel() {
        JComboBox<String> ingredientComboBox = createIngredientComboBox();
    
        int textFieldColumns = 10; 
        JTextField amountField = new JTextField(textFieldColumns);
    
        Dimension comboBoxSize = ingredientComboBox.getPreferredSize();
        amountField.setPreferredSize(new Dimension(comboBoxSize.width, amountField.getPreferredSize().height));
    
        JPanel panel = createCombinedFieldPanel("Ingredient:", ingredientComboBox, "Amount (g):", amountField);
        ingredientPanels.add(panel);
        centralContainer.add(panel);
        revalidate();
        repaint();
    }

    private JComboBox<String> createIngredientComboBox() {
        String[] ingredients = {"Egg", "Cheese", "Milk", "Carrot", "Apple", "Chicken Breast", "Brown Rice", "Olive Oil", "Chocolate", "Water"};
        return new JComboBox<>(ingredients);
    }    

    private void removeIngredientPanel() {
        if (!ingredientPanels.isEmpty()) {
            JPanel panel = ingredientPanels.remove(ingredientPanels.size() - 1);
            centralContainer.remove(panel);
            revalidate(); // Refresh the layout
            repaint(); // Redraw the panel
        }
    }

    public void resetForm() {
        dateOfMeal.setText("");
        mealName.setText(""); 
        mealComboBox.setSelectedIndex(0);
    
        // Clear all ingredient panels
        for (JPanel panel : ingredientPanels) {
            centralContainer.remove(panel);
        }
        ingredientPanels.clear();
    
        // Refresh the layout
        revalidate();
        repaint();
    }
    
    private JPanel createFieldPanel(String labelText, JComponent component) {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
    
       
        gbc.gridx = 0; 
        gbc.gridy = 0; 
        gbc.gridwidth = 1; 
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(new JLabel(labelText), gbc);
    
        
        gbc.gridy = 1; 
        gbc.insets = new Insets(5, 0, 5, 0);
        panel.add(component, gbc);
    
        panel.setBackground(Color.LIGHT_GRAY);
        return panel;
    }

    private JPanel createCombinedFieldPanel(String label1Text, JComponent component1, String label2Text, JComponent component2) {
        JPanel combinedPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        combinedPanel.setBackground(Color.LIGHT_GRAY);
    
        
        gbc.gridx = 0; 
        gbc.gridy = 0; 
        gbc.insets = new Insets(5, 0, 0, 0);
        combinedPanel.add(new JLabel(label1Text), gbc);
    
        gbc.gridy = 1; 
        combinedPanel.add(component1, gbc);
    
        gbc.gridx = 1; 
        gbc.gridy = 0; 
        combinedPanel.add(new JLabel(label2Text), gbc);
    
        gbc.gridy = 1; 
        combinedPanel.add(component2, gbc);
    
        return combinedPanel;
    }
}
