import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class DietDataEntryPanel extends JPanel {

    private JComboBox<String> mealComboBox;
    private JTextField dateOfMeal;
    private JButton submitButton;
    private List<JPanel> ingredientPanels = new ArrayList<>();
    private JPanel centralContainer;


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

        dateOfMeal = new JTextField(20);
        JPanel domPanel = createFieldPanel("Date of Meal (YYYY-MM-DD):", dateOfMeal);
        centralContainer.add(domPanel);

        String[] mealOptions = {"Breakfast", "Lunch", "Dinner", "Snack"};
        mealComboBox = new JComboBox<>(mealOptions);
        JPanel mealPanel = createFieldPanel("Type:", mealComboBox);
        centralContainer.add(mealPanel);

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
        add(buttonPanel, BorderLayout.SOUTH);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<IngredientData> ingredients = new ArrayList<>();
                for (JPanel panel : ingredientPanels) {
                    Component[] components = panel.getComponents();
                    JTextField ingredientField = (JTextField) components[1];
                    JTextField amountField = (JTextField) components[3];
                    String ingredient = ingredientField.getText();
                    String amount = amountField.getText();
                    ingredients.add(new IngredientData(ingredient, amount));
                }

                int i = 1;
                try {

                    LocalDate parsedDateOfMeal = LocalDate.parse(dateOfMeal.getText());
                    Launcher.setDateOfBirth(parsedDateOfMeal);   

                    String type = (String) mealComboBox.getSelectedItem();
                    Launcher.setMealType(type);

                    Launcher.setDietData(ingredients);
        
                } catch (DateTimeParseException ex) {
                    JOptionPane.showMessageDialog(DietDataEntryPanel.this, 
                        "Invalid date format. Please enter the date in YYYY-MM-DD format.", 
                        "Date Format Error", JOptionPane.ERROR_MESSAGE);
                        i = 2;

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(DietDataEntryPanel.this, 
                        "Invalid number format in height or weight fields.", 
                        "Number Format Error", JOptionPane.ERROR_MESSAGE);
                        i = 2;
                }

                if (i == 1) {
                JOptionPane.showMessageDialog(null, "Diet Data Submitted Succesfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                cardLayout.show(cards, "MainMenu");
                resetForm();
                }
            }
        });
    }

    private void addIngredientPanel() {
        JPanel panel = createCombinedFieldPanel("Ingredient:", new JTextField(20), "Amount (g):", new JTextField(20));
        ingredientPanels.add(panel);
        centralContainer.add(panel); // Add to centralContainer
        revalidate(); // Refresh the layout
        repaint(); // Redraw the panel
    }

    private void removeIngredientPanel() {
        if (!ingredientPanels.isEmpty()) {
            JPanel panel = ingredientPanels.remove(ingredientPanels.size() - 1);
            centralContainer.remove(panel); // Remove from centralContainer
            revalidate(); // Refresh the layout
            repaint(); // Redraw the panel
        }
    }

    public void resetForm() {
        dateOfMeal.setText(""); // Clear the date field
        mealComboBox.setSelectedIndex(0); // Reset the meal type combo box
    
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
    
        // Set constraints for the label
        gbc.gridx = 0; // Column
        gbc.gridy = 0; // Row
        gbc.gridwidth = 1; // Number of columns spanned by the component
        gbc.anchor = GridBagConstraints.CENTER; // Center alignment
        panel.add(new JLabel(labelText), gbc);
    
        // Set constraints for the input component
        gbc.gridy = 1; // Move to the next row
        gbc.insets = new Insets(5, 0, 5, 0); // Top padding
        panel.add(component, gbc);
    
        panel.setBackground(Color.LIGHT_GRAY);
        return panel;
    }

    private JPanel createCombinedFieldPanel(String label1Text, JComponent component1, String label2Text, JComponent component2) {
        JPanel combinedPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        combinedPanel.setBackground(Color.LIGHT_GRAY);
    
        // Constraints for the first label
        gbc.gridx = 0; // Column
        gbc.gridy = 0; // Row
        gbc.insets = new Insets(5, 0, 0, 0); // Padding
        combinedPanel.add(new JLabel(label1Text), gbc);
    
        // Constraints for the first component
        gbc.gridy = 1; // Next row
        combinedPanel.add(component1, gbc);
    
        // Constraints for the second label
        gbc.gridx = 1; // Next column
        gbc.gridy = 0; // Reset to first row
        combinedPanel.add(new JLabel(label2Text), gbc);
    
        // Constraints for the second component
        gbc.gridy = 1; // Next row
        combinedPanel.add(component2, gbc);
    
        return combinedPanel;
    }
}
