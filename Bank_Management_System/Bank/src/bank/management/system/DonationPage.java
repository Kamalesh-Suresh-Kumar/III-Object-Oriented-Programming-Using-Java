package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

@SuppressWarnings("serial")
public class DonationPage extends JFrame implements ActionListener {
    JComboBox<String> organizationBox;
    JTextField amountField;
    JButton donateButton, backButton;
    String cardNumber;

    DonationPage(String cardNumber) {
        this.cardNumber = cardNumber;
        setTitle("Donation to Charity");

        JLabel header = new JLabel("Donate to Charity");
        header.setFont(new Font("Arial", Font.BOLD, 24));
        header.setBounds(200, 30, 400, 30);
        add(header);

        JLabel organizationLabel = new JLabel("Select Organization:");
        organizationLabel.setFont(new Font("Arial", Font.BOLD, 16));
        organizationLabel.setBounds(100, 100, 200, 30);
        add(organizationLabel);

        String[] organizations = {"Red Cross", "UNICEF", "WWF", "Save the Children", "Other"};
        organizationBox = new JComboBox<>(organizations);
        organizationBox.setFont(new Font("Arial", Font.PLAIN, 14));
        organizationBox.setBounds(300, 100, 200, 30);
        add(organizationBox);

        JLabel amountLabel = new JLabel("Donation Amount:");
        amountLabel.setFont(new Font("Arial", Font.BOLD, 16));
        amountLabel.setBounds(100, 150, 200, 30);
        add(amountLabel);

        amountField = new JTextField();
        amountField.setFont(new Font("Arial", Font.PLAIN, 14));
        amountField.setBounds(300, 150, 200, 30);
        add(amountField);

        donateButton = new JButton("Donate");
        donateButton.setFont(new Font("Arial", Font.BOLD, 14));
        donateButton.setForeground(Color.WHITE);
        donateButton.setBackground(Color.BLACK);
        donateButton.setBounds(200, 250, 100, 30);
        donateButton.addActionListener(this);
        add(donateButton);

        backButton = new JButton("Back");
        backButton.setFont(new Font("Arial", Font.BOLD, 14));
        backButton.setForeground(Color.WHITE);
        backButton.setBackground(Color.BLACK);
        backButton.setBounds(350, 250, 100, 30);
        backButton.addActionListener(this);
        add(backButton);

        setLayout(null);
        setSize(600, 400);
        setLocation(500, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(new Color(240, 248, 255));
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == donateButton) {
            String organization = (String) organizationBox.getSelectedItem();
            String amountText = amountField.getText();

            if (organization.isEmpty() || amountText.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please fill in all fields.");
                return;
            }

            try {
                double amount = Double.parseDouble(amountText);

                if (amount <= 0) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid donation amount.");
                    return;
                }

                // Insert the donation into the database
                Connection conn = Database.connect();
                String query = "INSERT INTO donations (donor_card_number, organization_name, donation_amount) VALUES (?, ?, ?)";
                PreparedStatement ps = conn.prepareStatement(query);
                ps.setString(1, cardNumber);
                ps.setString(2, organization);
                ps.setDouble(3, amount);
                ps.executeUpdate();

                JOptionPane.showMessageDialog(null, "Thank you for your generous donation!");
                ps.close();
                conn.close();

                setVisible(false);
                new Main_Class(cardNumber);  // Redirect back to the main menu
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Please enter a valid numeric amount.");
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error processing your donation. Please try again.");
            }
        } else if (e.getSource() == backButton) {
            setVisible(false);
            new Main_Class(cardNumber);  // Redirect back to the main menu
        }
    }

    public static void main(String[] args) {
        new DonationPage("");  // Example card number
    }
}
