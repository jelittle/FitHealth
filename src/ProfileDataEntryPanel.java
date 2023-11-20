import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;


public class ProfileDataEntryPanel extends JPanel {

    private JComboBox<String> sexComboBox;
    private JTextField dateOfBirthField;
    private JTextField heightField;
    private JTextField weightField;
    private JButton submitButton;

    public ProfileDataEntryPanel(CardLayout cardLayout, JPanel cards) {

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.LIGHT_GRAY);

        add(Box.createVerticalGlue());

        JLabel headerLabel = new JLabel("Enter your information below:");
        headerLabel.setFont(new Font("Arial", Font.BOLD, 16));
        headerLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        JPanel headerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        headerPanel.setBackground(Color.LIGHT_GRAY);
        headerPanel.add(headerLabel);
        add(headerPanel);

        String[] sexOptions = {"Male", "Female"};
        sexComboBox = new JComboBox<>(sexOptions);
        JPanel sexPanel = createFieldPanel("Sex:", sexComboBox);
        addCenteredPanel(sexPanel);

        dateOfBirthField = new JTextField(20);
        JPanel dobPanel = createFieldPanel("Date of Birth (YYYY-MM-DD):", dateOfBirthField);
        addCenteredPanel(dobPanel);

        heightField = new JTextField(20);
        JPanel heightPanel = createFieldPanel("Height (cm):", heightField);

        addCenteredPanel(heightPanel);

        weightField = new JTextField(20);
        JPanel weightPanel = createFieldPanel("Weight (kg):", weightField);
        addCenteredPanel(weightPanel);

        submitButton = new JButton("Submit");
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBackground(Color.LIGHT_GRAY);
        buttonPanel.add(submitButton);
        add(buttonPanel);
        
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int i = 1;
                try {
                    LocalDate dateOfBirth = LocalDate.parse(dateOfBirthField.getText());
                    Launcher.setDateOfBirth(dateOfBirth);   
        
                    String sex = (String) sexComboBox.getSelectedItem();
                    Launcher.setSex(sex);
        
                    Double height = Double.parseDouble(heightField.getText());
                    Launcher.setHeight(height);

                    Double weight = Double.parseDouble(weightField.getText());
                    Launcher.setWeight(weight);
        
                } catch (DateTimeParseException ex) {
                    JOptionPane.showMessageDialog(ProfileDataEntryPanel.this, 
                        "Invalid date format. Please enter the date in YYYY-MM-DD format.", 
                        "Date Format Error", JOptionPane.ERROR_MESSAGE);
                        i = 2;

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(ProfileDataEntryPanel.this, 
                        "Invalid number format in height or weight fields.", 
                        "Number Format Error", JOptionPane.ERROR_MESSAGE);
                        i = 2;
                }

                if (i == 1) {
                
                JOptionPane.showMessageDialog(null, "Profile Info Submitted Succesfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                cardLayout.show(cards, "MainFrameFitHealth");
                }
            }
        });
           

        add(Box.createVerticalGlue());
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

    private void addCenteredPanel(JPanel panel) {
        JPanel container = new JPanel(new FlowLayout(FlowLayout.CENTER));
        container.add(panel);
        container.setBackground(Color.LIGHT_GRAY);
        add(container);
    }
}
