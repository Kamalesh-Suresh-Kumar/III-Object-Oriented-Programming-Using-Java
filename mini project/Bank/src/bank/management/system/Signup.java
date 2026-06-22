package bank.management.system;

import com.toedter.calendar.JDateChooser;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.sql.*;
import java.util.*;
import java.util.Date;

@SuppressWarnings("serial")
public class Signup extends JFrame implements ActionListener 
{
    JRadioButton r1, r2, r3;
    JButton next, previous, uploadPhotoButton;
    JTextField textName, textFname, textEmail;
    JDateChooser dateChooser;
    JLabel photoLabel;
    File userPhotoFile;
    String first, userName, userFname, userEmail, userGender, savedPhotoPath = null;
    static String formno;

    Signup(String formno) 
    {
        super("APPLICATION FORM");
        this.first = formno;
        JLabel bankLabel = new JLabel("SPK Bank");
        bankLabel.setFont(new Font("Raleway", Font.BOLD, 30));
        bankLabel.setBounds(360, 20, 300, 40);
        add(bankLabel);
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/bank.png"));
        Image i2 = i1.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(100, 5, 100, 100);
        add(image);
        JLabel label1 = new JLabel("APPLICATION FORM NO: " + first);
        label1.setBounds(250, 40, 700, 63);
        label1.setFont(new Font("Raleway", Font.BOLD, 27));
        add(label1);

        JLabel label2 = new JLabel("Page 1: Personal Details");
        label2.setFont(new Font("Raleway", Font.BOLD, 22));
        label2.setBounds(320, 70, 500, 50);
        add(label2);
        JLabel labelName = new JLabel("Name:");
        labelName.setFont(new Font("Raleway", Font.BOLD, 20));
        labelName.setBounds(100, 120, 100, 30);
        add(labelName);
        textName = new JTextField();
        textName.setFont(new Font("Raleway", Font.BOLD, 14));
        textName.setBounds(300, 120, 400, 30);
        add(textName);
        textName.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                textName.setText(textName.getText().toUpperCase());
            }
        });
        JLabel labelfName = new JLabel("Father's Name:");
        labelfName.setFont(new Font("Raleway", Font.BOLD, 20));
        labelfName.setBounds(100, 170, 200, 30);
        add(labelfName);
        
        textFname = new JTextField();
        textFname.setFont(new Font("Raleway", Font.BOLD, 14));
        textFname.setBounds(300, 170, 400, 30);
        add(textFname);
        textFname.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                textFname.setText(textFname.getText().toUpperCase());
            }
        });
        JLabel DOB = new JLabel("Date of Birth:");
        DOB.setFont(new Font("Raleway", Font.BOLD, 20));
        DOB.setBounds(100, 220, 200, 30);
        add(DOB);
        dateChooser = new JDateChooser();
        dateChooser.setForeground(new Color(105, 105, 105));
        dateChooser.setBounds(300, 220, 400, 30);
        add(dateChooser);

        JLabel labelG = new JLabel("Gender:");
        labelG.setFont(new Font("Raleway", Font.BOLD, 20));
        labelG.setBounds(100, 270, 200, 30);
        add(labelG);
        r1 = new JRadioButton("Male");
        r1.setFont(new Font("Raleway", Font.BOLD, 14));
        r1.setBounds(300, 270, 60, 30);
        r1.setBackground(new Color(102, 178, 178));
        add(r1);
        r2 = new JRadioButton("Female");
        r2.setFont(new Font("Raleway", Font.BOLD, 14));
        r2.setBounds(450, 270, 90, 30);
        r2.setBackground(new Color(102, 178, 178));
        add(r2);
        r3 = new JRadioButton("Other");
        r3.setFont(new Font("Raleway", Font.BOLD, 14));
        r3.setBounds(600, 270, 90, 30);
        r3.setBackground(new Color(102, 178, 178));
        add(r3);
        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(r1);
        genderGroup.add(r2);
        genderGroup.add(r3);

        JLabel labelEmail = new JLabel("Email Address:");
        labelEmail.setFont(new Font("Raleway", Font.BOLD, 20));
        labelEmail.setBounds(100, 320, 200, 30);
        add(labelEmail);
        textEmail = new JTextField();
        textEmail.setFont(new Font("Raleway", Font.BOLD, 14));
        textEmail.setBounds(300, 320, 400, 30);
        add(textEmail);
        textEmail.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                textEmail.setText(textEmail.getText().toLowerCase());
            }
        });

        JLabel labelPhoto = new JLabel("Upload Photo:");
        labelPhoto.setFont(new Font("Raleway", Font.BOLD, 20));
        labelPhoto.setBounds(100, 370, 200, 30);
        add(labelPhoto);
        photoLabel = new JLabel();
        photoLabel.setBounds(300, 370, 100, 100);
        photoLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        add(photoLabel);
        uploadPhotoButton = new JButton("Upload");
        uploadPhotoButton.setBounds(420, 400, 100, 30);
        uploadPhotoButton.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) 
            {
                try 
                {
                    userPhotoFile = fileChooser.getSelectedFile();
                    ImageIcon photoIcon = new ImageIcon(new ImageIcon(userPhotoFile.getPath()).getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH));
                    photoLabel.setIcon(photoIcon);
                } 
                catch (Exception ex) 
                {
                    ex.printStackTrace();
                }
            }
        });
        add(uploadPhotoButton);

        if (formno != null && !formno.isEmpty()) {
            try {
                Connection conn = Database.connect();
                PreparedStatement ps = conn.prepareStatement("SELECT * FROM signup WHERE form_no = ?");
                ps.setString(1, formno);
                ResultSet rs = ps.executeQuery();

                if (rs.next()) {
                    textName.setText(rs.getString("name"));
                    textFname.setText(rs.getString("fname"));
                    textEmail.setText(rs.getString("email"));
                    dateChooser.setDate(rs.getDate("dob"));

                    String gender = rs.getString("gender");
                    if ("Male".equals(gender)) {
                        r1.setSelected(true);
                    } else if ("Female".equals(gender)) {
                        r2.setSelected(true);
                    } else {
                        r3.setSelected(true);
                    }

                    savedPhotoPath = rs.getString("photo_path");
                    if (savedPhotoPath != null) {
                        ImageIcon photoIcon = new ImageIcon(new ImageIcon(savedPhotoPath).getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH));
                        photoLabel.setIcon(photoIcon);
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
        next.setBounds(620, 500, 100, 30);
        next.setBackground(Color.BLACK);
        next.setForeground(Color.WHITE);
        next.addActionListener(this);
        add(next);

        previous = new JButton("Previous");
        previous.setFont(new Font("Raleway", Font.BOLD, 14));
        previous.setBackground(Color.BLACK);
        previous.setForeground(Color.WHITE);
        previous.setBounds(450, 500, 100, 30);
        previous.addActionListener(this);
        add(previous);

        setLayout(null);
        setSize(850, 600);
        setLocation(350, 100);
        getContentPane().setBackground(new Color(102, 178, 178));
        setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) 
    {
        if (e.getSource() == next) {
            userName = textName.getText();
            userFname = textFname.getText();
            userEmail = textEmail.getText();

            userEmail = userEmail.toLowerCase();

            userGender = r1.isSelected() ? "Male" : r2.isSelected() ? "Female" : "Other";

            if (userName.isEmpty() || userFname.isEmpty() || userEmail.isEmpty() || dateChooser.getDate() == null) {
                JOptionPane.showMessageDialog(null, "All fields are mandatory");
                return;
            }
            Date today = new Date();
            Calendar cal = Calendar.getInstance();
            cal.setTime(today);
            cal.add(Calendar.DATE, -31);
            java.util.Date validDate = cal.getTime();

            if (dateChooser.getDate().after(validDate)) {
                JOptionPane.showMessageDialog(null, "Invalid Age: with this Date of Birth account cannot be created since AGE is less than 31 days ago.");
                return;
            }

            String photoPath = (userPhotoFile != null) ? userPhotoFile.getPath() : savedPhotoPath;

            if (photoPath == null) {
                JOptionPane.showMessageDialog(null, "Please upload a photo");
                return;
            }

            try {
                Connection conn = Database.connect();
                PreparedStatement ps = conn.prepareStatement(
                    "INSERT INTO signup (form_no, name, fname, dob, gender, email, photo_path) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?) " +
                    "ON DUPLICATE KEY UPDATE name = VALUES(name), fname = VALUES(fname), dob = VALUES(dob), gender = VALUES(gender), email = VALUES(email), photo_path = VALUES(photo_path)"
                );
                ps.setString(1, first);
                ps.setString(2, userName);
                ps.setString(3, userFname);
                ps.setDate(4, new java.sql.Date(dateChooser.getDate().getTime()));
                ps.setString(5, userGender);
                ps.setString(6, userEmail);
                ps.setString(7, photoPath);
                ps.executeUpdate();
                ps.close();

                setVisible(false);
                new Signup2(first);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else if (e.getSource() == previous) {
            setVisible(false);
            new Login();
        }
    }


    public static void main(String[] args) {
        new Signup(formno);
    }
}

