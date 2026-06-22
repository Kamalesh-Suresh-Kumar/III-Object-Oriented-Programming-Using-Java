package bank.management.system;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
@SuppressWarnings("serial")
public class BalanceEnquriy extends JFrame implements ActionListener 
{
	String pin;
	JButton button;
	BalanceEnquriy(String pin) 
	{
		this.pin = pin;
		getContentPane().setBackground(new Color(204, 255, 204));
		setSize(400, 300);
		setLocation(20, 20);
		setLayout(null);
		JLabel title = new JLabel("Balance Enquiry");
		title.setFont(new Font("System", Font.BOLD, 20));
		title.setBounds(120, 30, 200, 30);
		add(title);
		JLabel balanceLabel = new JLabel();
		balanceLabel.setFont(new Font("System", Font.BOLD, 16));
		balanceLabel.setBounds(50, 100, 300, 30);
		add(balanceLabel);
		try 
		{
			double balance = 0;
			Connection conn = Database.connect();
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM transaction_log WHERE pin = ? ORDER BY date");
			ps.setString(1, pin);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) 
			{
				String type = rs.getString("type");
				double amount = rs.getDouble("amount");
				if (type.equalsIgnoreCase("Deposit")) 
				{
					balance += amount;
				} 
				else if (type.equalsIgnoreCase("Withdraw") || type.equalsIgnoreCase("Withdrawal")) 
				{
					balance -= amount;
				}
			}
			balanceLabel.setText("Your Current Balance is: Rs " + balance);
			rs.close();
			ps.close();
			conn.close();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		button = new JButton("Exit");
		button.setBounds(150, 200, 100, 30);
		button.addActionListener(this);
		button.setBackground(Color.BLACK);
		button.setForeground(Color.WHITE);
		add(button);
		setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		setVisible(false);
	}
	public static void main(String[] args) 
	{
		new BalanceEnquriy("");
	}
}