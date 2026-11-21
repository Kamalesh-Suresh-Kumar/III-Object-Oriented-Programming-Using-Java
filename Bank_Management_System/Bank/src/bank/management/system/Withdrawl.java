package bank.management.system;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
@SuppressWarnings("serial")
public class Withdrawl extends JFrame implements ActionListener 
{
	String pin;
	TextField textField;
	JButton b1, b2;
	Withdrawl(String pin) 
	{
		this.pin = pin;
		ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/atm2.png"));
		Image i2 = i1.getImage().getScaledInstance(1550, 830, Image.SCALE_DEFAULT);
		ImageIcon i3 = new ImageIcon(i2);
		JLabel l3 = new JLabel(i3);
		l3.setBounds(0, 0, 1550, 830);
		add(l3);
		JLabel label1 = new JLabel("ENTER AMOUNT YOU WANT TO WITHDRAW");
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
		b1 = new JButton("WITHDRAW");
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
				if (amount.equals("")) 
				{
					JOptionPane.showMessageDialog(null, "Please enter the amount you want to withdraw");
					return;
				}
				Connection conn = Database.connect();
				String balanceQuery = "SELECT balance FROM account WHERE pin = ?";
				PreparedStatement psBalance = conn.prepareStatement(balanceQuery);
				psBalance.setString(1, pin);
				ResultSet rs = psBalance.executeQuery();
				if (rs.next()) 
				{
					double balance = rs.getDouble("balance");
					double withdrawAmount = Double.parseDouble(amount);
					if (withdrawAmount > balance) 
					{
						JOptionPane.showMessageDialog(null, "Insufficient Balance");
					} 
					else
					{
						String updateQuery = "UPDATE account SET balance = balance - ? WHERE pin = ?";
						PreparedStatement psUpdate = conn.prepareStatement(updateQuery);
						psUpdate.setDouble(1, withdrawAmount);
						psUpdate.setString(2, pin);
						psUpdate.executeUpdate();
						String logQuery = "INSERT INTO transaction_log (pin, date, type, amount) VALUES (?, NOW(), ?, ?)";
						PreparedStatement psLog = conn.prepareStatement(logQuery);
						psLog.setString(1, pin);
						psLog.setString(2, "Withdraw");
						psLog.setDouble(3, withdrawAmount);
						psLog.executeUpdate();
						JOptionPane.showMessageDialog(null, "Rs. " + amount + " Withdrawn Successfully");
					}
				} 
				else 
				{
					JOptionPane.showMessageDialog(null, "Account not found!");
				}
				rs.close();
				conn.close();
				setVisible(false);
				new Main_Class(pin);
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
		new Withdrawl("");
	}
}