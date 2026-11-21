package bank.management.system;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
@SuppressWarnings("serial")
public class Signup2 extends JFrame implements ActionListener 
{
    JTextField textAdd, textCity, textPin, textState, textPan, textAadhar;
    JComboBox<String> comboBoxIncome, comboBoxEducation, comboBoxOccupation;
    JRadioButton r1, r2;
    JButton next, previous;
    String formno;
    public String formatText(String input) {
        // Split the input by spaces and special characters
        String[] words = input.split("(?<=\\W)|(?=\\W)|\\s+");
        StringBuilder formattedText = new StringBuilder();
        
        for (String word : words) {
            if (!word.isEmpty()) {
                // Capitalize the first letter and keep the rest as is
                formattedText.append(word.substring(0, 1).toUpperCase()).append(word.substring(1).toLowerCase());
            } else {
                formattedText.append(word); // Keep spaces or special characters intact
            }
        }
        
        return formattedText.toString();
    }
    public String formatAddress(String address) {
        StringBuilder result = new StringBuilder();
        boolean capitalizeNext = true;

        for (char c : address.toCharArray()) {
            if (Character.isLetter(c)) {
                result.append(capitalizeNext ? Character.toUpperCase(c) : Character.toLowerCase(c));
                capitalizeNext = false;
            } else if (Character.isDigit(c)) {
                result.append(c); // Keep digits as they are
                capitalizeNext = true; // Ensure next alphabet is capitalized
            } else {
                result.append(c); // Include special characters like ':' or ','
                capitalizeNext = true; // Treat special characters as word boundaries
            }
        }

        return result.toString();
    }
    Signup2(String formno) 
    {
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

        JLabel l1 = new JLabel("Page 2: Residential Details");
        l1.setFont(new Font("Raleway", Font.BOLD, 22));
        l1.setBounds(320, 40, 400, 50);
        add(l1);
        JLabel labelAdd = new JLabel("Address :");
        labelAdd.setFont(new Font("Raleway", Font.BOLD, 18));
        labelAdd.setBounds(100, 120, 200, 30);
        add(labelAdd);
        textAdd = new JTextField();
        textAdd.setFont(new Font("Raleway", Font.BOLD, 14));
        textAdd.setBounds(300, 120, 400, 30);
        add(textAdd);
        textAdd.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                String formattedAddress = formatAddress(textAdd.getText());
                textAdd.setText(formattedAddress);
            }
        });
        JLabel labelCity = new JLabel("City :");
        labelCity.setFont(new Font("Raleway", Font.BOLD, 18));
        labelCity.setBounds(100, 170, 200, 30);
        add(labelCity);
        textCity = new JTextField();
        textCity.setFont(new Font("Raleway", Font.BOLD, 14));
        textCity.setBounds(300, 170, 400, 30);
        add(textCity);
        textCity.addFocusListener(new FocusAdapter() {
        	@Override
            public void focusLost(FocusEvent e) {
                textCity.setText(formatText(textCity.getText()));
            }
        });
        JLabel labelPin = new JLabel("Pin Code :");
        labelPin.setFont(new Font("Raleway", Font.BOLD, 18));
        labelPin.setBounds(100, 220, 200, 30);
        add(labelPin);
        textPin = new JTextField();
        textPin.setFont(new Font("Raleway", Font.BOLD, 14));
        textPin.setBounds(300, 220, 400, 30);
        add(textPin);
        JLabel labelState = new JLabel("State :");
        labelState.setFont(new Font("Raleway", Font.BOLD, 18));
        labelState.setBounds(100, 270, 200, 30);
        add(labelState);
        textState = new JTextField();
        textState.setFont(new Font("Raleway", Font.BOLD, 14));
        textState.setBounds(300, 270, 400, 30);
        add(textState);
        textState.addFocusListener(new FocusAdapter() {
        	@Override
            public void focusLost(FocusEvent e) {
                textState.setText(formatText(textState.getText()));
            }
        });
        JLabel l3 = new JLabel("Income : ");
        l3.setFont(new Font("Raleway", Font.BOLD, 18));
        l3.setBounds(100, 320, 200, 30);
        add(l3);
        String[] incomeOptions = {"Select one", "<1,50,000", "<2,50,000", "5,00,000", "Upto 10,00,000", "Above 10,00,000"};
        comboBoxIncome = new JComboBox<>(incomeOptions);
        comboBoxIncome.setFont(new Font("Raleway", Font.BOLD, 14));
        comboBoxIncome.setBounds(300, 320, 400, 30);
        comboBoxIncome.setBackground(new Color(222, 255, 228));
        add(comboBoxIncome);
        JLabel l4 = new JLabel("Educational : ");
        l4.setFont(new Font("Raleway", Font.BOLD, 18));
        l4.setBounds(100, 370, 200, 30);
        add(l4);
        String[] educationOptions = {"Select one", "Alumna of School/Non-Graduate", "Graduate", "Post-Graduate", "Doctorate", "Others"};
        comboBoxEducation = new JComboBox<>(educationOptions);
        comboBoxEducation.setFont(new Font("Raleway", Font.BOLD, 14));
        comboBoxEducation.setBounds(300, 370, 400, 30);
        comboBoxEducation.setBackground(new Color(222, 255, 228));
        add(comboBoxEducation);
        JLabel l5 = new JLabel("Occupation : ");
        l5.setFont(new Font("Raleway", Font.BOLD, 18));
        l5.setBounds(100, 420, 200, 30);
        add(l5);
        String[] occupationOptions = {"Select one", "Salaried", "Self-Employed", "Business", "Student", "Retired", "Other"};
        comboBoxOccupation = new JComboBox<>(occupationOptions);
        comboBoxOccupation.setFont(new Font("Raleway", Font.BOLD, 14));
        comboBoxOccupation.setBounds(300, 420, 400, 30);
        comboBoxOccupation.setBackground(new Color(222, 255, 228));
        add(comboBoxOccupation);
        JLabel l6 = new JLabel("PAN Number : ");
        l6.setFont(new Font("Raleway", Font.BOLD, 18));
        l6.setBounds(100, 470, 200, 30);
        add(l6);
        textPan = new JTextField();
        textPan.setFont(new Font("Raleway", Font.BOLD, 14));
        textPan.setBounds(300, 470, 400, 30);
        add(textPan);
        JLabel l7 = new JLabel("Aadhar Number : ");
        l7.setFont(new Font("Raleway", Font.BOLD, 18));
        l7.setBounds(100, 520, 200, 30);
        add(l7);
        textAadhar = new JTextField();
        textAadhar.setFont(new Font("Raleway", Font.BOLD, 14));
        textAadhar.setBounds(300, 520, 400, 30);
        add(textAadhar);
        JLabel l8 = new JLabel("Senior Citizen : ");
        l8.setFont(new Font("Raleway", Font.BOLD, 18));
        l8.setBounds(100, 570, 200, 30);
        add(l8);
        r1 = new JRadioButton("Yes");
        r1.setFont(new Font("Raleway", Font.BOLD, 14));
        r1.setBounds(300, 570, 100, 30);
        r1.setBackground(new Color(102, 178, 178));
        add(r1);
        r2 = new JRadioButton("No");
        r2.setFont(new Font("Raleway", Font.BOLD, 14));
        r2.setBounds(400, 570, 100, 30);
        r2.setBackground(new Color(102, 178, 178));
        add(r2);
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(r1);
        buttonGroup.add(r2);
        JLabel l9 = new JLabel("Form No:" + formno);
        l9.setFont(new Font("Raleway", Font.BOLD, 14));
        l9.setBounds(700, 10, 100, 30);
        add(l9);
        if (formno != null && !formno.isEmpty()) {
            try {
                Connection conn = Database.connect();
                PreparedStatement ps = conn.prepareStatement("SELECT * FROM signup2 WHERE form_no = ?");
                ps.setString(1, formno);
                ResultSet rs = ps.executeQuery();

                if (rs.next()) {
                    textAdd.setText(rs.getString("address"));
                    textCity.setText(rs.getString("city"));
                    textPin.setText(rs.getString("pin_code"));
                    textState.setText(rs.getString("state"));
                    comboBoxIncome.setSelectedItem(rs.getString("income"));
                    comboBoxEducation.setSelectedItem(rs.getString("education"));
                    comboBoxOccupation.setSelectedItem(rs.getString("occupation"));
                    textPan.setText(rs.getString("pan"));
                    textAadhar.setText(rs.getString("aadhar"));
                    String seniorCitizen = rs.getString("senior_citizen");
                    if ("Yes".equals(seniorCitizen)) {
                        r1.setSelected(true);
                    } else {
                        r2.setSelected(true);
                    }
                }
                rs.close();
                ps.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        next = new JButton("Next");
        next.setFont(new Font("Raleway", Font.BOLD, 14));
        next.setBackground(Color.BLACK);
        next.setForeground(Color.WHITE);
        next.setBounds(570, 630, 100, 30);
        next.addActionListener(this);
        add(next);
        previous = new JButton("Previous");
        previous.setFont(new Font("Raleway", Font.BOLD, 14));
        previous.setBackground(Color.BLACK);
        previous.setForeground(Color.WHITE);
        previous.setBounds(450, 630, 100, 30);
        previous.addActionListener(this);
        add(previous);
        setLayout(null);
        setSize(850, 750);
        setLocation(450, 80);
        getContentPane().setBackground(new Color(102, 178, 178));
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
    public void actionPerformed(ActionEvent e) 
    {
        if (e.getSource() == next) 
        {
            if (textAdd.getText().equals("") || textCity.getText().equals("") || textPin.getText().equals("") ||
                textState.getText().equals("") || textPan.getText().equals("") || textAadhar.getText().equals("") ||
                comboBoxIncome.getSelectedIndex() == 0 || comboBoxEducation.getSelectedIndex() == 0 || comboBoxOccupation.getSelectedIndex() == 0) 
            {
                JOptionPane.showMessageDialog(null, "All fields are mandatory");
            } 
            else 
            {
                try 
                {
                    Connection conn = Database.connect();
                    
                    // Check if form_no already exists in the database
                    String checkQuery = "SELECT COUNT(*) FROM signup2 WHERE form_no = ?";
                    PreparedStatement checkStmt = conn.prepareStatement(checkQuery);
                    checkStmt.setString(1, formno);
                    ResultSet rs = checkStmt.executeQuery();
                    rs.next();
                    int count = rs.getInt(1);
                    rs.close();
                    checkStmt.close();

                    if (count > 0) {
                        // Update existing record
                        String updateQuery = "UPDATE signup2 SET address = ?, city = ?, pin_code = ?, state = ?, income = ?, education = ?, occupation = ?, pan = ?, aadhar = ?, senior_citizen = ? WHERE form_no = ?";
                        PreparedStatement updateStmt = conn.prepareStatement(updateQuery);
                        updateStmt.setString(1, textAdd.getText());
                        updateStmt.setString(2, textCity.getText());
                        updateStmt.setString(3, textPin.getText());
                        updateStmt.setString(4, textState.getText());
                        updateStmt.setString(5, (String) comboBoxIncome.getSelectedItem());
                        updateStmt.setString(6, (String) comboBoxEducation.getSelectedItem());
                        updateStmt.setString(7, (String) comboBoxOccupation.getSelectedItem());
                        updateStmt.setString(8, textPan.getText());
                        updateStmt.setString(9, textAadhar.getText());
                        updateStmt.setString(10, r1.isSelected() ? "Yes" : "No");
                        updateStmt.setString(11, formno);
                        updateStmt.executeUpdate();
                        updateStmt.close();
                    } else {
                        // Insert new record
                        String insertQuery = "INSERT INTO signup2 (form_no, address, city, pin_code, state, income, education, occupation, pan, aadhar, senior_citizen) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                        PreparedStatement insertStmt = conn.prepareStatement(insertQuery);
                        insertStmt.setString(1, formno);
                        insertStmt.setString(2, textAdd.getText());
                        insertStmt.setString(3, textCity.getText());
                        insertStmt.setString(4, textPin.getText());
                        insertStmt.setString(5, textState.getText());
                        insertStmt.setString(6, (String) comboBoxIncome.getSelectedItem());
                        insertStmt.setString(7, (String) comboBoxEducation.getSelectedItem());
                        insertStmt.setString(8, (String) comboBoxOccupation.getSelectedItem());
                        insertStmt.setString(9, textPan.getText());
                        insertStmt.setString(10, textAadhar.getText());
                        insertStmt.setString(11, r1.isSelected() ? "Yes" : "No");
                        insertStmt.executeUpdate();
                        insertStmt.close();
                    }

                    // Navigate to the next page
                    setVisible(false);
                    new Signup3(formno);

                } 
                catch (Exception ex) 
                {
                    ex.printStackTrace();
                }
            }
        } 
        else if (e.getSource() == previous)
        {
        	if (!Condition.doesFormNumberExist(formno)) {
        	try 
            {
                Connection conn = Database.connect();
	        	String insertQuery = "INSERT INTO signup2 (form_no, address, city, pin_code, state, income, education, occupation, pan, aadhar, senior_citizen) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	            PreparedStatement insertStmt = conn.prepareStatement(insertQuery);
	            insertStmt.setString(1, formno);
	            insertStmt.setString(2, textAdd.getText());
	            insertStmt.setString(3, textCity.getText());
	            insertStmt.setString(4, textPin.getText());
	            insertStmt.setString(5, textState.getText());
	            insertStmt.setString(6, (String) comboBoxIncome.getSelectedItem());
	            insertStmt.setString(7, (String) comboBoxEducation.getSelectedItem());
	            insertStmt.setString(8, (String) comboBoxOccupation.getSelectedItem());
	            insertStmt.setString(9, textPan.getText());
	            insertStmt.setString(10, textAadhar.getText());
	            insertStmt.setString(11, r1.isSelected() ? "Yes" : "No");
	            insertStmt.executeUpdate();
	            insertStmt.close();
	            if (Condition.doesFormNumberExist(formno)) {
	            setVisible(false);
	            new Signup(formno);
	            }
            }
        	catch (Exception ex) 
            {
                ex.printStackTrace();
            }
        }
        	}
    }

    public static void main(String[] args) 
    {
        new Signup2("");
    }
}