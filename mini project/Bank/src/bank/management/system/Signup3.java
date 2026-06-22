package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Random;

@SuppressWarnings("serial")
public class Signup3 extends JFrame implements ActionListener {
    JRadioButton r1, r2, r3, r4;
    JCheckBox c1, c2, c3, c4, c5, c6, c7;
    JButton s, c, previous;
    String formno;

    // Static variables for card number and PIN that will be stored and used across instances
    static String cardNumber;
    static String pin;

    Signup3(String formno) {
        super("APPLICATION FORM");
        this.formno = formno;
        
        JLabel bankLabel = new JLabel("SPK Bank");
        bankLabel.setFont(new Font("Raleway", Font.BOLD, 32));
        bankLabel.setBounds(360, 20, 200, 40);  
        add(bankLabel);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/bank.png"));
        Image i2 = i1.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(200, 5, 100, 100);
        add(image);

        JLabel l1 = new JLabel("Page 3: Account Details");
        l1.setFont(new Font("Raleway", Font.BOLD, 22));
        l1.setBounds(320, 40, 400, 50);
        add(l1);

        JLabel l3 = new JLabel("Account Type:");
        l3.setFont(new Font("Raleway", Font.BOLD, 18));
        l3.setBounds(100, 140, 200, 30);
        add(l3);

        r1 = new JRadioButton("Saving Account");
        r2 = new JRadioButton("Fixed Deposit Account");
        r3 = new JRadioButton("Current Account");
        r4 = new JRadioButton("Recurring Deposit Account");
        ButtonGroup accountGroup = new ButtonGroup();
        accountGroup.add(r1);
        accountGroup.add(r2);
        accountGroup.add(r3);
        accountGroup.add(r4);
        r1.setFont(new Font("Raleway", Font.BOLD, 16));
        r2.setFont(new Font("Raleway", Font.BOLD, 16));
        r3.setFont(new Font("Raleway", Font.BOLD, 16));
        r4.setFont(new Font("Raleway", Font.BOLD, 16));
        r1.setBounds(100, 180, 150, 30);
        r2.setBounds(350, 180, 300, 30);
        r3.setBounds(100, 220, 250, 30);
        r4.setBounds(350, 220, 250, 30);
        r1.setBackground(new Color(102, 178, 178));
        r2.setBackground(new Color(102, 178, 178));
        r3.setBackground(new Color(102, 178, 178));
        r4.setBackground(new Color(102, 178, 178));
        add(r1);
        add(r2);
        add(r3);
        add(r4);

        JLabel l4 = new JLabel("Card Number:");
        l4.setFont(new Font("Raleway", Font.BOLD, 18));
        l4.setBounds(100, 300, 200, 30);
        add(l4);

        JLabel l5 = new JLabel("(Your 16-digit Card Number)");
        l5.setFont(new Font("Raleway", Font.BOLD, 12));
        l5.setBounds(100, 330, 200, 20);
        add(l5);

        JLabel l6 = new JLabel("1409-XXXX-XXXX-XXXX"); 
        l6.setFont(new Font("Raleway",Font.BOLD,18)); 
        l6.setBounds(330,300,250,30); 
        add(l6);

        JLabel l7 = new JLabel("(It would appear on ATM card/cheque book and statements)");
        l7.setFont(new Font("Raleway", Font.BOLD, 12));
        l7.setBounds(330, 330, 500, 20);
        add(l7);

        JLabel l8 = new JLabel("PIN:");
        l8.setFont(new Font("Raleway", Font.BOLD, 18));
        l8.setBounds(100, 370, 200, 30);
        add(l8);

        JLabel l9 = new JLabel("XXXX");   // Display stored pin
        l9.setFont(new Font("Raleway", Font.BOLD, 18));
        l9.setBounds(330, 370, 200, 30);
        add(l9);

        JLabel l10 = new JLabel("(4-digit Password)");
        l10.setFont(new Font("Raleway", Font.BOLD, 12));
        l10.setBounds(100, 400, 200, 20);
        add(l10);

        JLabel l11 = new JLabel("Services Required:");
        l11.setFont(new Font("Raleway", Font.BOLD, 18));
        l11.setBounds(100, 450, 200, 30);
        add(l11);

        c1 = new JCheckBox("ATM CARD");
        c2 = new JCheckBox("Internet Banking");
        c3 = new JCheckBox("Mobile Banking");
        c4 = new JCheckBox("EMAIL Alerts");
        c5 = new JCheckBox("Cheque Book");
        c6 = new JCheckBox("E-Statement");
        c7 = new JCheckBox("I hereby declare that the above details are correct to the best of my knowledge.");
        c1.setBounds(100, 500, 200, 30);
        c2.setBounds(350, 500, 200, 30);
        c3.setBounds(100, 550, 200, 30);
        c4.setBounds(350, 550, 200, 30);
        c5.setBounds(100, 600, 200, 30);
        c6.setBounds(350, 600, 200, 30);
        c7.setBounds(100, 650, 600, 30);
        c1.setBackground(new Color(102, 178, 178));
        c2.setBackground(new Color(102, 178, 178));
        c3.setBackground(new Color(102, 178, 178));
        c4.setBackground(new Color(102, 178, 178));
        c5.setBackground(new Color(102, 178, 178));
        c6.setBackground(new Color(102, 178, 178));
        c7.setBackground(new Color(102, 178, 178));
        add(c1);
        add(c2);
        add(c3);
        add(c4);
        add(c5);
        add(c6);
        add(c7);

        JLabel l = new JLabel("Form No:" + formno);
        l.setFont(new Font("Raleway", Font.BOLD, 14));
        l.setBounds(700, 10, 100, 30);
        add(l);

        s = new JButton("Submit");
        s.setFont(new Font("Raleway", Font.BOLD, 14));
        s.setBackground(Color.BLACK);
        s.setForeground(Color.WHITE);
        s.setBounds(370, 720, 100, 30);
        s.addActionListener(this);
        add(s);

        c = new JButton("Cancel");
        c.setFont(new Font("Raleway", Font.BOLD, 14));
        c.setBackground(Color.BLACK);
        c.setForeground(Color.WHITE);
        c.setBounds(130, 720, 100, 30);
        c.addActionListener(this);
        add(c);

        previous = new JButton("Previous");
        previous.setFont(new Font("Raleway", Font.BOLD, 14));
        previous.setBackground(Color.BLACK);
        previous.setForeground(Color.WHITE);
        previous.setBounds(250, 720, 100, 30);
        previous.addActionListener(this);
        add(previous);

        getContentPane().setBackground(new Color(102, 178, 178));
        setSize(850, 800);
        setLocation(400, 20);
        setLayout(null);
        setVisible(true);
        
    }
    public class Condition {

        // Static method to check if the form number exists in the database
        public static boolean doesFormNumberExist(String formno) {
            boolean exists = false;

            try {
                Connection conn = Database.connect(); // Assumes Database.connect() returns a valid connection
                String query = "SELECT COUNT(*) FROM signup3 WHERE form_no = ?";
                PreparedStatement ps = conn.prepareStatement(query);
                ps.setString(1, formno);

                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    exists = rs.getInt(1) > 0; // If the count is greater than 0, formno exists
                }
                rs.close();
                ps.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

            return exists;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    	if (e.getSource() == s) {
    		if (!Condition.doesFormNumberExist(formno)) {
            try {
                if (!r1.isSelected() && !r2.isSelected() && !r3.isSelected() && !r4.isSelected()) {
                    JOptionPane.showMessageDialog(null, "Please select an account type");
                    return;
                }
                if (!c1.isSelected() && !c2.isSelected() && !c3.isSelected() && !c4.isSelected() && !c5.isSelected() && !c6.isSelected()) {
                    JOptionPane.showMessageDialog(null, "Please select at least one service");
                    return;
                }
                if (!c7.isSelected()) {
                    JOptionPane.showMessageDialog(null, "Please accept the declaration.");
                    return;
                }

                // Generate card number and PIN
                Random ran = new Random(); 
                long cardNumber = (ran.nextLong() % 90000000L) + 1409963000000000L; 
                String cardNo = "" + Math.abs(cardNumber); 
                String pin = "" + Math.abs((ran.nextLong() % 9000L) + 1000L);
                
                String accountType = r1.isSelected() ? "Saving Account"
                        : r2.isSelected() ? "Fixed Deposit Account"
                        : r3.isSelected() ? "Current Account"
                        : "Recurring Deposit Account";

                String facilities = (c1.isSelected() ? "ATM Card " : "") +
                                    (c2.isSelected() ? "Internet Banking " : "") +
                                    (c3.isSelected() ? "Mobile Banking " : "") +
                                    (c4.isSelected() ? "Email Alerts " : "") +
                                    (c5.isSelected() ? "Cheque Book " : "") +
                                    (c6.isSelected() ? "E-Statement " : "");

                Connection conn = Database.connect();
                

                // Insert into `signup3`
                String query = "INSERT INTO signup3 (form_no, account_type, card_number, pin, facilities) VALUES (?, ?, ?, ?, ?)";
                PreparedStatement ps = conn.prepareStatement(query);
                ps.setString(1, formno);
                ps.setString(2, accountType);
                ps.setString(3, cardNo);  // Insert the generated card_number
                ps.setString(4, pin);
                ps.setString(5, facilities);
                ps.executeUpdate();
                
             // Insert into `login`
                String loginQuery = "INSERT INTO login (card_number, pin, account_type, facilities) VALUES (?, ?, ?, ?)";
                PreparedStatement psLogin = conn.prepareStatement(loginQuery);
                psLogin.setString(1, cardNo);  // Ensure card_number is inserted
                psLogin.setString(2, pin);
                psLogin.setString(3, accountType);
                psLogin.setString(4, facilities);
                psLogin.executeUpdate();

                JOptionPane.showMessageDialog(null, "Account Created Successfully!\nCard Number: " + cardNumber + "\nPIN: " + pin);
                setVisible(false);
                new Deposit(pin);

            } catch (Exception ex) {
                ex.printStackTrace();
            }
    		}
        } else if (e.getSource() == c) {
            try {
                Connection conn = Database.connect();
                // Delete data from all three tables
                PreparedStatement psSignup3 = conn.prepareStatement("DELETE FROM signup3 WHERE form_no = ?");
                psSignup3.setString(1, formno);
                psSignup3.executeUpdate();

                PreparedStatement psSignup2 = conn.prepareStatement("DELETE FROM signup2 WHERE form_no = ?");
                psSignup2.setString(1, formno);
                psSignup2.executeUpdate();

                PreparedStatement psSignup = conn.prepareStatement("DELETE FROM signup WHERE form_no = ?");
                psSignup.setString(1, formno);
                psSignup.executeUpdate();
                setVisible(false);
                new Login();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else if (e.getSource() == previous) {
            try {
                // Save the account type and facilities before going back
                /*String accountType = r1.isSelected() ? "Saving Account"
                        : r2.isSelected() ? "Fixed Deposit Account"
                        : r3.isSelected() ? "Current Account"
                        : "Recurring Deposit Account";
                String facilities = (c1.isSelected() ? "ATM Card " : "") +
                                    (c2.isSelected() ? "Internet Banking " : "") +
                                    (c3.isSelected() ? "Mobile Banking " : "") +
                                    (c4.isSelected() ? "Email Alerts " : "") +
                                    (c5.isSelected() ? "Cheque Book " : "") +
                                    (c6.isSelected() ? "E-Statement " : "");

                Connection conn = Database.connect();
                String upsertQuery = "INSERT INTO signup3 (form_no, account_type, facilities) VALUES (?, ?, ?) " +
                                     "ON DUPLICATE KEY UPDATE account_type = VALUES(account_type), facilities = VALUES(facilities)";
                PreparedStatement ps = conn.prepareStatement(upsertQuery);
                ps.setString(1, formno);
                ps.setString(2, accountType);
                ps.setString(3, facilities);
                ps.executeUpdate();
                ps.close();*/
            	
            	String accountType = r1.isSelected() ? "Saving Account"
                        : r2.isSelected() ? "Fixed Deposit Account"
                        : r3.isSelected() ? "Current Account"
                        : "Recurring Deposit Account";

                String facilities = (c1.isSelected() ? "ATM Card " : "") +
                                    (c2.isSelected() ? "Internet Banking " : "") +
                                    (c3.isSelected() ? "Mobile Banking " : "") +
                                    (c4.isSelected() ? "Email Alerts " : "") +
                                    (c5.isSelected() ? "Cheque Book " : "") +
                                    (c6.isSelected() ? "E-Statement " : "");
                
            	Connection conn = Database.connect();
                

            	String upsertQuery = "INSERT INTO signup3 (form_no, account_type, facilities) VALUES (?, ?, ?) " +
                        			  "ON DUPLICATE KEY UPDATE account_type = VALUES(account_type), facilities = VALUES(facilities)";
            	PreparedStatement ps = conn.prepareStatement(upsertQuery);
            	ps.setString(1, formno);
            	ps.setString(2, accountType);
            	ps.setString(3, facilities);
            	ps.executeUpdate();


                // Navigate to the previous page
                setVisible(false);
                new Signup2(formno);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new Signup3("");
    }
}
