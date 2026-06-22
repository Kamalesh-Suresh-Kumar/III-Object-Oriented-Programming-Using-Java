package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

@SuppressWarnings("serial")
public class Pin extends JFrame implements ActionListener {

    JButton b1, b2;
    JPasswordField p1, p2;
    String pin;

    Pin(String pin) {
        this.pin = pin;
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/atm2.png"));
        Image i2 = i1.getImage().getScaledInstance(1550, 830, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel l3 = new JLabel(i3);
        l3.setBounds(0, 0, 1550, 830);
        add(l3);

        JLabel label1 = new JLabel("CHANGING YOUR PIN");
        label1.setForeground(Color.WHITE);
        label1.setFont(new Font("System", Font.BOLD, 16));
        label1.setBounds(430, 180, 400, 35);
        l3.add(label1);

        JLabel label2 = new JLabel("New PIN: ");
        label2.setForeground(Color.WHITE);
        label2.setFont(new Font("System", Font.BOLD, 16));
        label2.setBounds(430, 220, 150, 35);
        l3.add(label2);

        p1 = new JPasswordField();
        p1.setBackground(new Color(65, 125, 128));
        p1.setForeground(Color.WHITE);
        p1.setBounds(600, 220, 180, 25);
        p1.setFont(new Font("Raleway", Font.BOLD, 22));
        l3.add(p1);

        JLabel label3 = new JLabel("Re-Enter New PIN: ");
        label3.setForeground(Color.WHITE);
        label3.setFont(new Font("System", Font.BOLD, 16));
        label3.setBounds(430, 250, 400, 35);
        l3.add(label3);

        p2 = new JPasswordField();
        p2.setBackground(new Color(65, 125, 128));
        p2.setForeground(Color.WHITE);
        p2.setBounds(600, 255, 180, 25);
        p2.setFont(new Font("Raleway", Font.BOLD, 22));
        l3.add(p2);

        b1 = new JButton("CHANGE");
        b1.setBounds(700, 362, 150, 35);
        b1.setBackground(new Color(65, 125, 128));
        b1.setForeground(Color.WHITE);
        b1.addActionListener(this);
        l3.add(b1);

        b2 = new JButton("BACK");
        b2.setBounds(700, 406, 150, 35);
        b2.setBackground(new Color(65, 125, 128));
        b2.setForeground(Color.WHITE);
        b2.addActionListener(this);
        l3.add(b2);

        setSize(1550, 1080);
        setLayout(null);
        setLocation(0, 0);
        setVisible(true);
    }

    @SuppressWarnings("deprecation")
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            String pin1 = p1.getText();
            String pin2 = p2.getText();

            // PIN length validation
            if (pin1.length() != 4 || pin2.length() != 4) {
                JOptionPane.showMessageDialog(null, "PIN must be 4 digits.");
                return;
            }

            // Check if the entered PINs match
            if (!pin1.equals(pin2)) {
                JOptionPane.showMessageDialog(null, "Entered PIN does not match");
                return;
            }

            if (e.getSource() == b1) {
                if (pin1.isEmpty() || pin2.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please fill both fields");
                    return;
                }

                Connection conn = Database.connect();

                // Update the PIN in the signup3, transaction_log, and account tables
                String updateSignup3Query = "UPDATE signup3 SET pin = ? WHERE pin = ?";
                PreparedStatement psSignup3 = conn.prepareStatement(updateSignup3Query);
                psSignup3.setString(1, pin1);
                psSignup3.setString(2, pin);
                int signup3Rows = psSignup3.executeUpdate();

                String updateTransactionLogQuery = "UPDATE transaction_log SET pin = ? WHERE pin = ?";
                PreparedStatement psTransactionLog = conn.prepareStatement(updateTransactionLogQuery);
                psTransactionLog.setString(1, pin1);
                psTransactionLog.setString(2, pin);
                int transactionLogRows = psTransactionLog.executeUpdate();

                String updateAccountQuery = "UPDATE account SET pin = ? WHERE pin = ?";
                PreparedStatement psAccount = conn.prepareStatement(updateAccountQuery);
                psAccount.setString(1, pin1);
                psAccount.setString(2, pin);
                int accountRows = psAccount.executeUpdate();

                // Check if any of the updates were successful
                if (signup3Rows > 0 || transactionLogRows > 0 || accountRows > 0) {
                    JOptionPane.showMessageDialog(null, "PIN changed successfully");
                    setVisible(false);
                    new Main_Class(pin1);  // Navigate to the main page with the new PIN
                } else {
                    JOptionPane.showMessageDialog(null, "PIN change failed. Account or Login not found.");
                }

                psSignup3.close();
                psTransactionLog.close();
                psAccount.close();
                conn.close();
            } else if (e.getSource() == b2) {
                setVisible(false);
                new Main_Class(pin);  // Navigate back to the main page with the old PIN
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Pin("");  // Launch the Pin change screen
    }
}
