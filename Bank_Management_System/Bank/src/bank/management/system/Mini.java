package bank.management.system;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
@SuppressWarnings("serial")
public class Mini extends JFrame implements ActionListener
{
	String pin;
	JButton button;
	Mini(String pin) 
	{
		this.pin = pin;
		getContentPane().setBackground(new Color(255, 204, 204));
		setSize(400, 600);
		setLocation(20, 20);
		setLayout(null);
		JLabel label2 = new JLabel("SPK Bank");
		label2.setFont(new Font("System", Font.BOLD, 15));
		label2.setBounds(150, 20, 200, 20);
		add(label2);
		JLabel label3 = new JLabel();
		label3.setBounds(20, 80, 300, 20);
		add(label3);
		JLabel label4 = new JLabel();
		label4.setBounds(20, 500, 300, 20);
		add(label4);
		try (Connection conn = Database.connect();
		PreparedStatement ps = conn.prepareStatement("SELECT card_number FROM login WHERE pin = ?")) 
		{
			ps.setString(1, pin);
			ResultSet resultSet = ps.executeQuery();
			if (resultSet.next()) 
			{
				String cardNumber = resultSet.getString("card_number");
				label3.setText("Card Number: " + cardNumber.substring(0, 4) + "XXXXXXXX" + cardNumber.substring(12));
			} 
			else 
			{
				JOptionPane.showMessageDialog(null, "No card number found for PIN: "  + pin);
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		JLabel label1 = new JLabel();
		label1.setVerticalAlignment(SwingConstants.TOP);
		label1.setHorizontalAlignment(SwingConstants.LEFT);
		label1.setFont(new Font("System", Font.PLAIN, 14));
		JScrollPane scrollPane = new JScrollPane(label1);
		scrollPane.setBounds(20, 140, 350, 300);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		add(scrollPane);
		try (Connection conn = Database.connect();
		PreparedStatement ps = conn.prepareStatement("SELECT date, type, amount FROM transaction_log WHERE pin = ? ORDER BY date")) 
		{
			ps.setString(1, pin);
			ResultSet resultSet = ps.executeQuery();
			double balance = 0;
			StringBuilder transactions = new StringBuilder("<html>");
			while (resultSet.next()) 
			{
				String type = resultSet.getString("type");
				double amount = resultSet.getDouble("amount");
				transactions.append(resultSet.getDate("date")).append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;")
				.append(type).append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;")
				.append(amount).append("<br><br>");
				if (type.equalsIgnoreCase("Deposit")) 
				{
					balance += amount;
				} 
				else if (type.equalsIgnoreCase("Withdraw") || type.equalsIgnoreCase("Withdrawal")) 
				{
					balance -= amount;
				}
			}
			transactions.append("</html>");
			label1.setText(transactions.toString());
			label4.setText("Your Total Balance is Rs " + balance);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		button = new JButton("Exit");
		button.setBounds(20, 520, 100, 25);
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
		new Mini("");
	}
}