package bank.management.system;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
@SuppressWarnings("serial")
public class Deposit extends JFrame implements ActionListener 
{
	String pin;
	TextField textField;
	JButton b1, b2;
	Deposit(String pin)
	{
		this.pin = pin;
		ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/atm2.png"));
		Image i2 = i1.getImage().getScaledInstance(1550, 830, Image.SCALE_DEFAULT);
		ImageIcon i3 = new ImageIcon(i2);
		JLabel l3 = new JLabel(i3);
		l3.setBounds(0, 0, 1550, 830);
		add(l3);
		JLabel label1 = new JLabel("ENTER AMOUNT YOU WANT TO DEPOSIT");
		label1.setForeground(Color.WHITE);
		label1.setFont(new Font("System", Font.BOLD, 16));
		label1.setBounds(460, 180, 400, 35);
		l3.add(label1);
		textField = new TextField();
		textField.setBackground(new Color(65, 125, 128));
		textField.setForeground(Color.WHITE);
		textField.setBounds(460, 230, 320, 25);
		textField.setFont(new Font("Raleway", Font.BOLD, 22));
		l3.add(textField);
		b1 = new JButton("DEPOSIT");
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
		setLayout(null);
		setSize(1550, 1080);
		setLocation(0, 0);
		setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		try 
		{
			String amount = textField.getText();
			if (e.getSource() == b1) 
			{
				if (amount.isEmpty() || Double.parseDouble(amount) <= 0) 
				{
					JOptionPane.showMessageDialog(null, "Please enter some amount to deposit into your account");
				} 
				else 
				{
					Connection conn = Database.connect();
					String checkAccountQuery = "SELECT * FROM account WHERE pin = ?";
					PreparedStatement psCheck = conn.prepareStatement(checkAccountQuery);
					psCheck.setString(1, pin);
					ResultSet rs = psCheck.executeQuery();
					if (rs.next()) 
					{
						String updateQuery = "UPDATE account SET balance = balance + ? WHERE pin = ?";
						PreparedStatement psUpdate = conn.prepareStatement(updateQuery);
						psUpdate.setDouble(1, Double.parseDouble(amount));
						psUpdate.setString(2, pin);
						psUpdate.executeUpdate();
						String insertQuery = "INSERT INTO transaction_log (pin, type, amount) VALUES (?, ?, ?)";
						PreparedStatement psInsert = conn.prepareStatement(insertQuery);
						psInsert.setString(1, pin);
						psInsert.setString(2, "Deposit");
						psInsert.setDouble(3, Double.parseDouble(amount));
						psInsert.executeUpdate();
						JOptionPane.showMessageDialog(null, "Rs. " + amount + " Deposited Successfully");
						psInsert.close();
						psUpdate.close();
					} 
					else 
					{
						JOptionPane.showMessageDialog(null, "Account not found for PIN: " + pin);
					}
					rs.close();
					psCheck.close();
					conn.close();
					setVisible(false);
					new Main_Class(pin);
				}
			} 
			else if (e.getSource() == b2) 
			{
				setVisible(false);
				new Main_Class(pin);
			}
		} 
		catch (Exception ex) 
		{
			ex.printStackTrace();
		}
	}
	public static void main(String[] args) 
	{
			new Deposit("");
	}
}