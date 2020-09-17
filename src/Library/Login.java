package Library;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import com.formdev.flatlaf.FlatDarkLaf;
//import java.awt.Image;

import java.awt.*;

public class Login extends JFrame {
	// private JLabel item1;
	private JLabel item1;

	public void select_user() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			UIManager.setLookAndFeel(new FlatDarkLaf());
		} catch (Exception e) {

		}

		item1 = new JLabel("Welcome to Library!");
		item1.setFont(new Font("Helvetica Neue", Font.BOLD, 54));
		item1.setBounds(500, 200, 600, 80);
		item1.setForeground(Color.white);
		JFrame f = new JFrame("User Select");
		JButton b1 = new JButton("Librarian");

		b1.setFont(new Font("Arial", Font.PLAIN, 25));
		b1.setForeground(Color.white);
		JButton b2 = new JButton("Member");
		b2.setFont(new Font("Arial", Font.PLAIN, 25));
		b2.setForeground(Color.white);
		f.add(item1);
		f.add(b1);
		f.add(b2);
		b1.setBounds(450, 400, 200, 50);
		b2.setBounds(850, 400, 200, 50);
		f.setSize(380, 200);
		f.setExtendedState(JFrame.MAXIMIZED_BOTH);
		// f.setSize(1650,1080);
		f.setLocationRelativeTo(null);
		f.setLayout(null);
		f.setVisible(true);
		f.getContentPane().setBackground(Color.ORANGE);

//   f.setLayout(new BorderLayout());
//   ImageIcon img = new ImageIcon("images/test2.png");
//   f.setContentPane(new JLabel(img));
//   f.setLayout(new FlowLayout());

		b1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				librarian();
				f.dispose();
			}

		});
		b2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Member();
				f.dispose();
			}

		});
	}

	public void librarian() {

		JFrame f1 = new JFrame("Librarian login");// creating instance of JFrame
		JLabel l1, l2;
		l1 = new JLabel("Username"); // Create label Username
		l1.setBounds(30, 15, 100, 30); // x axis, y axis, width, height

		l2 = new JLabel("Password"); // Create label Password
		l2.setBounds(30, 50, 100, 30);

		JTextField F_user = new JTextField(); // Create text field for username
		F_user.setBounds(110, 15, 200, 30);

		JPasswordField F_pass = new JPasswordField(); // Create text field for password
		F_pass.setBounds(110, 50, 200, 30);

		JButton login_but = new JButton("Login");// creating instance of JButton for Login Button
		login_but.setBounds(130, 90, 80, 25);// Dimensions for button
		login_but.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username = F_user.getText();
				char password[] = F_pass.getPassword();

				if (username.equals("")) {
					JOptionPane.showMessageDialog(null, "Please enter usename !!");
				} else if (String.valueOf(password).equals("")) {
					JOptionPane.showMessageDialog(null, "Please enter password !!");
				} else {
					Connection connection = connect();
					try {
						Statement smt = connection.createStatement();
						smt.executeUpdate("use library");
						String st = ("SELECT * FROM LIBRARIAN WHERE lib_name = '" + username + "' AND lib_password='"
								+ String.valueOf(password) + "'");
						ResultSet rs = smt.executeQuery(st);
						if (rs.next() == false) { // Move pointer below
							// System.out.print("No user");
//	    					System.out.println(rs.getString(1)+"\n");
//	    					System.out.println(rs.getString(2)+"\n");

							JOptionPane.showMessageDialog(null, "Wrong Username/Password!"); // Display Message

						} else {

							rs.beforeFirst();
							while (rs.next()) {
								String l_id = rs.getString("lib_id");
								Librarian L = new Librarian();
								L.librarian_menu(l_id, smt, connection);
							}
							f1.dispose();

						}
						rs.close();
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}

			}
		});

		f1.add(l1);
		f1.add(l2);
		f1.add(F_user);
		f1.add(F_pass);
		f1.add(login_but);
		f1.setSize(400, 180);
		f1.setLocationRelativeTo(null);
		f1.setLayout(null);
		f1.setVisible(true);
//	    f1.getContentPane().setBackground(Color.DARKSLATEBLUE);

	}

	public void Member() {
		JFrame F = new JFrame("Register & Login");

		JButton reg_but = new JButton("Register");// creating instance of JButton for Login Button
		reg_but.setBounds(100, 112, 200, 25);// Dimensions for button

		JButton login_but = new JButton("Login");// creating instance of JButton for Login Button
		login_but.setBounds(100, 212, 200, 25);// Dimensions for button

		reg_but.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				F.dispose();

				Register R = new Register();
				R.register();
			}
		});

		login_but.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				F.dispose();
				member();
			}
		});
		F.add(reg_but);
		F.add(login_but);
		F.setSize(400, 400);
		F.setLocationRelativeTo(null);
		F.setLayout(null);
		F.setVisible(true);
	}

	public void member() {
		JFrame f1 = new JFrame("Member login");// creating instance of JFrame
		JLabel l1, l2;
		l1 = new JLabel("Username"); // Create label Username
		l1.setBounds(30, 15, 100, 30); // x axis, y axis, width, height

		l2 = new JLabel("Password"); // Create label Password
		l2.setBounds(30, 50, 100, 30);

		JTextField F_user = new JTextField(); // Create text field for username
		F_user.setBounds(110, 15, 200, 30);

		JPasswordField F_pass = new JPasswordField(); // Create text field for password
		F_pass.setBounds(110, 50, 200, 30);

		JButton login_but = new JButton("Login");// creating instance of JButton for Login Button
		login_but.setBounds(130, 90, 80, 25);// Dimensions for button
		login_but.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username = F_user.getText();
				char password[] = F_pass.getPassword();

				if (username.equals("")) {
					JOptionPane.showMessageDialog(null, "Please enter usename !!");
				} else if (String.valueOf(password).equals("")) {
					JOptionPane.showMessageDialog(null, "Please enter password !!");
				} else {
					Connection connection = connect1();
					try {
						Statement smt = connection.createStatement();
						smt.executeUpdate("use library");
						String st = ("SELECT * FROM member WHERE m_name = '" + username + "' AND m_password='"
								+ String.valueOf(password) + "'");
						ResultSet rs = smt.executeQuery(st);
						if (rs.next() == false) { // Move pointer below
							JOptionPane.showMessageDialog(null, "Wrong Username/Password!"); // Display Message

						} else {
							rs.beforeFirst();
							while (rs.next()) {

								String m_id = rs.getString("m_id");
								Member M = new Member();
								M.Member_menu(m_id, smt, connection);
							}
							f1.dispose();
//	    					connection.close();
						}
						rs.close();
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}
			}
		});
		f1.add(l1);
		f1.add(l2);
		f1.add(F_user);
		f1.add(F_pass);
		f1.add(login_but);
		f1.setSize(400, 180);
		f1.setLocationRelativeTo(null);
		f1.setLayout(null);
		f1.setVisible(true);
	}

	public static Connection connect() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "Divyang@03");
			return con;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	public static Connection connect1() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/library?noAccessToProcedureBodies=true", "lib_member", "member123");
			return con;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

}
