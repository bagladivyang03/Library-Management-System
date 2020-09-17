package Library;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Register {
	public static void register() {
		JFrame F = new JFrame("Register");
		JLabel J1, J2, J3, J4, J5, J6, J7, J8;

		J1 = new JLabel("Member ID");
		J1.setBounds(30, 15, 200, 30);

		J2 = new JLabel("Member Name");
		J2.setBounds(30, 50, 200, 30);

		J3 = new JLabel("Member Email");
		J3.setBounds(30, 85, 200, 30);

		J4 = new JLabel("Contact Info");
		J4.setBounds(30, 120, 200, 30);

		J5 = new JLabel("Street");
		J5.setBounds(30, 155, 200, 30);

		J6 = new JLabel("City");
		J6.setBounds(30, 190, 200, 30);

		J7 = new JLabel("Zip Code");
		J7.setBounds(30, 225, 200, 30);

		J8 = new JLabel("Enter Password");
		J8.setBounds(30, 260, 200, 30);

		JTextField M_ID = new JTextField();
		M_ID.setBounds(130, 15, 200, 30);

		JTextField M_Name = new JTextField();
		M_Name.setBounds(130, 50, 200, 30);

		JTextField M_email = new JTextField();
		M_email.setBounds(130, 85, 200, 30);

		JTextField M_contact_info = new JTextField();
		M_contact_info.setBounds(130, 120, 200, 30);

		JTextField M_street = new JTextField();
		M_street.setBounds(130, 155, 200, 30);

		JTextField M_city = new JTextField();
		M_city.setBounds(130, 190, 200, 30);

		JTextField M_Zipcode = new JTextField();
		M_Zipcode.setBounds(130, 225, 200, 30);

		JPasswordField M_password = new JPasswordField();
		M_password.setBounds(130, 260, 200, 30);

		JButton registerbutton = new JButton("Register");// creating instance of JButton for Login Button
		registerbutton.setBounds(130, 305, 200, 30);

		registerbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String M_id = M_ID.getText();
				String M_name = M_Name.getText();
				String M_Email = M_email.getText();
				char M_pass[] = M_password.getPassword();
				String M_info = M_contact_info.getText();
				String M_Street = M_street.getText();
				String M_City = M_city.getText();
				String M_zip = M_Zipcode.getText();
				if (M_name.equals("") || M_Email.equals("") || M_pass.equals("") || M_info.equals("")
						|| M_Street.equals("") || M_City.equals("") || M_zip.equals("")) {
					JOptionPane.showMessageDialog(null, "Please enter the missing fields");
				} else {

					String sql1 = "select count(*) from member";
					try {
						Login l = new Login();
						Connection con = l.connect1();
						Statement smt = con.createStatement();
						ResultSet rs = smt.executeQuery(sql1);
						if (rs.next()) {
							int count = Integer.parseInt(rs.getString(1));
							int M_Id = count + 1;
							try {
//	    		    			Login l = new Login();
//	    		    			Connection con = l.connect1();
								String sql = "insert into member values(" + M_Id + ",'" + M_name + "','" + M_Email
										+ "','" + String.valueOf(M_pass) + "','" + M_info + "','" + M_Street + "','"
										+ M_City + "','" + M_zip + "')";
								smt.executeUpdate(sql);
								F.dispose();
								JOptionPane.showMessageDialog(null, "Registration Successfull!!");
							} catch (Exception e1) {
								JOptionPane.showMessageDialog(null, "Member with these credentials already exists !!");
							}
						}
						con.close();
					} catch (Exception E) {
						JOptionPane.showMessageDialog(null, E);
					}
				}
			}

		});
//		F.add(J1);
		F.add(J2);
		F.add(J3);
		F.add(J4);
		F.add(J5);
		F.add(J6);
		F.add(J7);
		F.add(J8);
//		F.add(M_ID);
		F.add(M_Name);
		F.add(M_email);
		F.add(M_contact_info);
		F.add(M_street);
		F.add(M_city);
		F.add(M_Zipcode);
		F.add(M_password);
		F.add(registerbutton);
		F.setSize(450, 400);
		F.setLocationRelativeTo(null);
		F.setLayout(null);
		F.setVisible(true);

	}
}
