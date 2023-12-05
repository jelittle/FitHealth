import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import Controller.ExerciseLogic.ExerciseLogic;
import Controller.ExerciseLogic.IExerciseLogic;


public class ExerciseDataEntryPanel extends JPanel {

    private IExerciseLogic exerciseLogic = new ExerciseLogic();

    private JComboBox<String> typeComboBox;
    private JComboBox<String> intensityComboBox;
    private JTextField dateOfExerciseField;
    private JTextField durationField;
    private JButton submitButton;
    private JButton backButton;

    int userId = Launcher.userId;

    public ExerciseDataEntryPanel(CardLayout cardLayout, JPanel cards) {

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.LIGHT_GRAY);

        add(Box.createVerticalGlue());

        JLabel headerLabel = new JLabel("Enter your exercise data below:");
        headerLabel.setFont(new Font("Arial", Font.BOLD, 16));
        headerLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        JPanel headerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        headerPanel.setBackground(Color.LIGHT_GRAY);
        headerPanel.add(headerLabel);
        add(headerPanel);

        dateOfExerciseField = new JTextField(20);
        JPanel dobPanel = createFieldPanel("Date of Exercise (YYYY-MM-DD):", dateOfExerciseField);
        addCenteredPanel(dobPanel);

        durationField = new JTextField(20);
        JPanel durationPanel = createFieldPanel("Duration (min):", durationField);
        addCenteredPanel(durationPanel);

        String[] typeOptions = {"Walking", "Running", "Biking", "Swimming", "Weightlifting"};
        typeComboBox = new JComboBox<>(typeOptions);
        JPanel typePanel = createFieldPanel("Type:", typeComboBox);
        addCenteredPanel(typePanel);

        String[] intensityOptions = {"Low", "Medium", "High", "Very High"};
        intensityComboBox = new JComboBox<>(intensityOptions);
        JPanel intensityPanel = createFieldPanel("Intensity:", intensityComboBox);
        addCenteredPanel(intensityPanel);

        submitButton = new JButton("Submit");
        backButton = new JButton("Back");

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBackground(Color.LIGHT_GRAY);

        buttonPanel.add(submitButton);
        buttonPanel.add(backButton);

        add(buttonPanel);
        
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int i = 1;
                try {
                    LocalDate date = LocalDate.parse(dateOfExerciseField.getText());
                    ArrayList<Integer> dateTime = new ArrayList<>(5);
                    dateTime.add(date.getYear());
                    dateTime.add(date.getMonthValue());
                    dateTime.add(date.getDayOfMonth());
                    dateTime.add(0); // Hour
                    dateTime.add(0); // Minute
                    Launcher.setDateOfExercise(date);

                    Double duration = Double.parseDouble(durationField.getText());
                    Launcher.setExerciseDuration(duration);
        
                    String type = (String) typeComboBox.getSelectedItem();
                    Launcher.setExerciseType(type);

                    String intensity = (String) intensityComboBox.getSelectedItem();
                    Launcher.setExerciseIntensity(intensity);

                    exerciseLogic.addExerciseLog(dateTime, dateTime, type, intensity, userId);
        
                } catch (DateTimeParseException ex) {
                    JOptionPane.showMessageDialog(ExerciseDataEntryPanel.this, 
                        "Invalid date format. Please enter the date in YYYY-MM-DD format.", 
                        "Date Format Error", JOptionPane.ERROR_MESSAGE);
                        i = 2;

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(ExerciseDataEntryPanel.this, 
                        "Invalid number format in duration field.", 
                        "Number Format Error", JOptionPane.ERROR_MESSAGE);
                        i = 2;

                } catch (Exception ex) {

                    System.out.println("Error with addExerciseLog()");
                    
                    ex.printStackTrace();

                }
            

                if (i == 1) {
                
                JOptionPane.showMessageDialog(null, "Exercise Info Submitted Succesfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                cardLayout.show(cards, "MainMenu");
                resetForm();
                }
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cards, "MainMenu");
            }    
        });
           

        add(Box.createVerticalGlue());
    }

    public void resetForm() {
        dateOfExerciseField.setText(""); // Clear the date field
        durationField.setText("");
        typeComboBox.setSelectedIndex(0);
        intensityComboBox.setSelectedIndex(0); // Reset the meal type combo box
    
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

    private void addCenteredPanel(JPanel panel) {
        JPanel container = new JPanel(new FlowLayout(FlowLayout.CENTER));
        container.add(panel);
        container.setBackground(Color.LIGHT_GRAY);
        add(container);
    }
}