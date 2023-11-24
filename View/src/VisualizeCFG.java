import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class VisualizeCFG extends JPanel{

    private JPanel centralContainer;
    private JPanel southContainer;
    private JButton submitButton;
    private JButton backButton;

    public VisualizeCFG(CardLayout cardLayout, JPanel cards) {
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

                JOptionPane.showMessageDialog(null, "TODO: Display Visualization", "Success", JOptionPane.INFORMATION_MESSAGE);
                resetForm();
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
