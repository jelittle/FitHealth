import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import Controller.DietLogic.DietLogic;
import Controller.DietLogic.IDietLogic;
import Controller.ExerciseLogic.ExerciseLogic;
import Controller.ExerciseLogic.IExerciseLogic;

public class VisualizeFL extends JPanel{

    private JPanel centralContainer;
    private JPanel southContainer;
    private JButton submitButton;
    private JButton backButton;
    private JTextField endDate;

    static int fatLoss;
    static IExerciseLogic exerciseLogic = new ExerciseLogic();

    public int protein = 0; //Set with calculated values
    public int carbs = 0; //Set with calculated values

    int userId = Launcher.userId;

    public VisualizeFL(CardLayout cardLayout, JPanel cards) {


        setLayout(new BorderLayout());
        setBackground(Color.LIGHT_GRAY);

        centralContainer = new JPanel();
        centralContainer.setLayout(new BoxLayout(centralContainer, BoxLayout.Y_AXIS));
        centralContainer.setBackground(Color.LIGHT_GRAY);
        add(centralContainer, BorderLayout.NORTH);

        JLabel headerLabel = new JLabel("Fat Loss Visualization");
        headerLabel.setFont(new Font("Arial", Font.BOLD, 16));

        JPanel headerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        headerPanel.setBackground(Color.LIGHT_GRAY);
        headerPanel.add(headerLabel);
        centralContainer.add(headerPanel);

        JPanel textFieldContainer = new JPanel();
        textFieldContainer.setLayout(new FlowLayout(FlowLayout.CENTER));
        textFieldContainer.setBackground(Color.LIGHT_GRAY);

        // Create text fields
        endDate = new JTextField(20);
        JPanel endPanel = createFieldPanel("Future Date (YYYY-MM-DD):", endDate);
        centralContainer.add(endPanel);

        // Add text fields to the container
        textFieldContainer.add(endPanel);

        // Add the container to the central container
        centralContainer.add(textFieldContainer);

        southContainer = new JPanel();
        southContainer.setLayout(new BoxLayout(southContainer, BoxLayout.Y_AXIS));
        southContainer.setBackground(Color.LIGHT_GRAY);
        add(southContainer, BorderLayout.SOUTH);

        submitButton = new JButton("Submit");
        backButton = new JButton("Back");
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBackground(Color.LIGHT_GRAY);
        
        buttonPanel.add(submitButton);
        buttonPanel.add(backButton);

        southContainer.add(buttonPanel);
        add(southContainer, BorderLayout.SOUTH);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int i = 1;
                try {
                    LocalDate parsedEndDate = LocalDate.parse(endDate.getText());
                    ArrayList<Integer> endDateTime = new ArrayList<>(5);
                    endDateTime.add(parsedEndDate.getYear());
                    endDateTime.add(parsedEndDate.getMonthValue());
                    endDateTime.add(parsedEndDate.getDayOfMonth());
                    endDateTime.add(0); // Hour
                    endDateTime.add(0); // Minute

                    fatLoss = exerciseLogic.predictFatLoss(userId, endDateTime);

                    JLabel answer = new JLabel("Your Fat Loss Prediction by " + endDate.getText() + "is: " + fatLoss);
                    answer.setFont(new Font("Arial", Font.BOLD, 20));
                    centralContainer.add(answer);
                    
                    
        
                } catch (DateTimeParseException ex) {
                    JOptionPane.showMessageDialog(VisualizeFL.this, 
                        "Invalid date format. Please enter the date in YYYY-MM-DD format.", 
                        "Date Format Error", JOptionPane.ERROR_MESSAGE);
                        i = 2;
                }

                catch (Exception e1) {

                    System.out.println("Error with predictFatLoss()");
                    
                    e1.printStackTrace();
                    
                }

                if (i == 1) {
                    // JOptionPane.showMessageDialog(null, "TODO: Display Visualization", "Success", JOptionPane.INFORMATION_MESSAGE);
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

    }

    public void resetForm() {
        endDate.setText(""); 
    
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
    
}
