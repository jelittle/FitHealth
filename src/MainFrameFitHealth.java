import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrameFitHealth extends JPanel{

    // private JPanel panel1; //For buttons?
    private JPanel panel2; //For left side of login screen
    private JPanel panel3; //For right side of login screen
    private JLabel imageLabel; //For icon
    private JLabel welcomeText; //For welcome text
    private JButton loginButton; // "Login" button
    private JButton editButton; // "Edit Profile Info" button

    public MainFrameFitHealth(CardLayout cardLayout, JPanel cards) {

        setLayout(new GridLayout(1, 2)); 

        // panel1 = new JPanel();
        // frame.add(panel1);

        panel2 = new JPanel();
        panel2.setBackground(Color.DARK_GRAY);
        panel2.setLayout(new BoxLayout(panel2, BoxLayout.Y_AXIS));
        add(panel2);

        ImageIcon imageIcon = new ImageIcon("icon.png"); // Load the image
        imageLabel = new JLabel(imageIcon);
        imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        welcomeText = new JLabel("Welcome to FitHealth");
        welcomeText.setFont(new Font("Arial", Font.BOLD, 24));
        welcomeText.setAlignmentX(Component.CENTER_ALIGNMENT);
        welcomeText.setForeground(Color.LIGHT_GRAY);
        
        panel2.add(Box.createVerticalGlue());
        panel2.add(imageLabel);
        panel2.add(welcomeText);
        panel2.add(Box.createVerticalGlue());

        panel3 = new JPanel();
        panel3.setBackground(Color.LIGHT_GRAY);
        panel3.setLayout(new BoxLayout(panel3, BoxLayout.Y_AXIS));
        add(panel3);

        loginButton = new JButton("Login");
        editButton = new JButton("Edit Profile Info");
        loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        editButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        loginButton.setMargin(new Insets(10, 20, 10, 20));
        editButton.setMargin(new Insets(10, 20, 10, 20));

        panel3.add(Box.createVerticalGlue());
        panel3.add(loginButton);
        panel3.add(Box.createRigidArea(new Dimension(0, 10)));
        panel3.add(editButton);
        panel3.add(Box.createVerticalGlue());

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Launcher.getSex() == null || Launcher.getDateOfBirth() == null || 
                    Launcher.getHeight() == null || Launcher.getWeight() == null) {
                    JOptionPane.showMessageDialog(MainFrameFitHealth.this, 
                        "Please set profile info first before logging in.", 
                        "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    cardLayout.show(cards, "MainMenu");
                }
            }
        });

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cards, "ProfileDataEntryPanel");
            }
        });

        setVisible(true);
    }
}
