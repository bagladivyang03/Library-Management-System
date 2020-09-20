package Library;

import javax.swing.*;
import net.proteanit.sql.DbUtils;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.*;

public class Member {
	public void Member_menu(String m_id, Statement smt, Connection con) {
		JFrame f = new JFrame("Member Functions");
		JButton add_but = new JButton("Search Book");
		add_but.setBounds(50, 20, 150, 25);
		add_but.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				book_search(smt);
			}
		});

		JButton search_but = new JButton("Issue Book");
		search_but.setBounds(50, 70, 150, 25);
		search_but.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login L = new Login();
				Connection connection = L.connect1();
				try {
					CallableStatement cstmt = connection.prepareCall("{? = call check_user_inBorrows(?)}");
					cstmt.registerOutParameter(1, java.sql.Types.INTEGER);
					cstmt.setString(2, m_id);
					cstmt.execute();
					if (cstmt.getInt(1) == 0) {
						issue_book(m_id, smt);
					} else {
						JOptionPane.showMessageDialog(null, "Return the book first!!");
					}
					connection.close();

				} catch (Exception E) {

				}
			}
		});

		JButton up_aut_but = new JButton("Return Book");
		up_aut_but.setBounds(50, 120, 150, 25);
		up_aut_but.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				return_book(m_id, smt);
			}
		});

		JButton up_pub_but = new JButton("Search Author");
		up_pub_but.setBounds(50, 170, 150, 25);
		up_pub_but.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				author_search(smt);
			}
		});

		JButton delete_but = new JButton("Search Publisher ");
		delete_but.setBounds(300, 20, 150, 25);
		delete_but.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				publisher_search(smt);
			}
		});

		JButton up_book_but = new JButton("Update User Info");
		up_book_but.setBounds(300, 70, 150, 25);
		up_book_but.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				update_info(m_id, smt);
			}
		});
		JButton dis_userinfo_but = new JButton("Display user info");
		dis_userinfo_but.setBounds(300, 120, 150, 25);
		dis_userinfo_but.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				display_info(m_id, smt);
			}
		});

		JButton logout_but = new JButton("Logout");
		logout_but.setBounds(175, 230, 200, 40);
		logout_but.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				f.dispose();
				Login L = new Login();
				L.select_user();
				try {
					smt.close();
					con.close();
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, e1);
				}

			}
		});

		JButton view_issued_book_but = new JButton("View Issued Book");
		view_issued_book_but.setBounds(300, 170, 150, 25);
		view_issued_book_but.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				view_book(m_id, smt);

			}
		});

		f.add(add_but);
		f.add(search_but);
		f.add(up_aut_but);
		f.add(up_pub_but);
		f.add(delete_but);
		f.add(up_book_but);
		f.add(dis_userinfo_but);
		f.add(view_issued_book_but);
		f.add(logout_but);
		f.setSize(500, 350);// 400 width and 500 height
		f.setLocationRelativeTo(null);
		f.setLayout(null);// using no layout managers
		f.setVisible(true);
	}

	// method to accept input for books
	public static void book_search(Statement smt) {
		JFrame F1 = new JFrame("Search the Book");

		JLabel J1;

		J1 = new JLabel("Enter Book Name ");
		J1.setBounds(100, 15, 200, 30);

		JTextField B_name = new JTextField();
		B_name.setBounds(100, 50, 200, 30);

		JButton searchbutton = new JButton("Search Books");// creating instance of JButton for Login Button
		searchbutton.setBounds(100, 85, 200, 30);

		searchbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String b_name = B_name.getText();
				if (b_name.equals("")) {
					JOptionPane.showMessageDialog(null, "Please enter the missing fields");
				} else {
					String sql = "select b_id,b_name,genre,aisle from books where b_name like '%" + b_name + "%'";
					display_results(smt, sql, "Books Available");
					F1.dispose();
				}
			}
		});

		F1.add(J1);
		F1.add(B_name);
		F1.add(searchbutton);
		F1.setSize(400, 200);
		F1.setLocationRelativeTo(null);
		F1.setLayout(null);
		F1.setVisible(true);
	}

	public static void author_search(Statement smt) {
		JFrame F1 = new JFrame("Search the Author");

		JLabel J1;

		J1 = new JLabel("Enter Author Name ");
		J1.setBounds(100, 15, 200, 30);

		JTextField A_name = new JTextField();
		A_name.setBounds(100, 50, 200, 30);

		JButton searchbutton = new JButton("Search Author");// creating instance of JButton for Login Button
		searchbutton.setBounds(100, 85, 200, 30);

		searchbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String a_Name = A_name.getText();
				if (a_Name.equals("")) {
					JOptionPane.showMessageDialog(null, "Please enter the missing fields");
				} else {
					String sql = "select * from author where a_name = '" + a_Name + "'";
					display_results(smt, sql, "Authors Available");
					F1.dispose();
				}
			}
		});

		F1.add(J1);
		F1.add(A_name);
		F1.add(searchbutton);
		F1.setSize(400, 200);
		F1.setLocationRelativeTo(null);
		F1.setLayout(null);
		F1.setVisible(true);
	}

	public static void publisher_search(Statement smt) {
		JFrame F1 = new JFrame("Search the Publisher");

		JLabel J1;

		J1 = new JLabel("Enter Publisher Name ");
		J1.setBounds(100, 15, 200, 30);

		JTextField P_name = new JTextField();
		P_name.setBounds(100, 50, 200, 30);

		JButton searchbutton = new JButton("Search Publisher");// creating instance of JButton for Login Button
		searchbutton.setBounds(100, 85, 200, 30);

		searchbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String p_Name = P_name.getText();
				if (p_Name.equals("")) {
					JOptionPane.showMessageDialog(null, "Please enter the missing fields");
				} else {
					String sql = "select * from publisher where p_name = '" + p_Name + "'";
					display_results(smt, sql, "Publishers List");
					F1.dispose();
				}
			}

		});

		F1.add(J1);
		F1.add(P_name);
		F1.add(searchbutton);
		F1.setSize(400, 200);
		F1.setLocationRelativeTo(null);
		F1.setLayout(null);
		F1.setVisible(true);
	}

	private static void display_results(Statement smt, String sql, String s) {
		JFrame J = new JFrame(s);
		try {
			ResultSet rs = smt.executeQuery(sql);
			JTable book_list = new JTable();
			book_list.setModel(DbUtils.resultSetToTableModel(rs));
			JScrollPane scrollPane = new JScrollPane(book_list);
			J.add(scrollPane);
			J.setSize(800, 400);
			J.setLocationRelativeTo(null);
			J.setVisible(true);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}

	public static void issue_book(String m_id, Statement smt) {
		JFrame F1 = new JFrame("Issue the Book");

		JLabel J1;

		J1 = new JLabel("Enter Book ID");
		J1.setBounds(100, 15, 200, 30);

		JTextField B_ID = new JTextField();
		B_ID.setBounds(100, 50, 200, 30);

		JButton issuebutton = new JButton("ISSUE");// creating instance of JButton for Login Button
		issuebutton.setBounds(100, 85, 200, 30);

		issuebutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String b_id = B_ID.getText();
				if (b_id.equals("")) {
					JOptionPane.showMessageDialog(null, "Please enter the missing fields");
				} else {
					String sql = "select b_id from borrows where b_id = " + b_id;
					try {
						ResultSet rs = smt.executeQuery(sql);
						if (rs.next()) {
							JOptionPane.showMessageDialog(null, "Book issued Already!!");
						} else {
							int B_id = Integer.parseInt(b_id);
							int M_id = Integer.parseInt(m_id);
							try {
								String sql1 = "insert into borrows values(" + B_id + "," + M_id
										+ ",curdate() , DATE_ADD(curdate(),INTERVAL 14 DAY) , 0)";
								smt.executeUpdate(sql1);
								F1.dispose();
								JOptionPane.showMessageDialog(null, "Book Issued Sucessfully!!");
							} catch (Exception e1) {
								JOptionPane.showMessageDialog(null, "Book Doesn't Exists !!");
							}
						}
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, e1);
					}

				}
			}

		});

		F1.add(J1);
		F1.add(B_ID);
		F1.add(issuebutton);
		F1.setSize(400, 200);
		F1.setLayout(null);
		F1.setLocationRelativeTo(null);
		F1.setVisible(true);
	}

	public static void return_book(String M_id, Statement smt) {
		int m_id = Integer.parseInt(M_id);
		try {
			String sql3 = "select m_id from borrows where m_id =" + m_id;
			ResultSet rs1 = smt.executeQuery(sql3);
			if (rs1.next()) {
				try {
					Login L = new Login();
					Connection con = L.connect1();
					String sql = "{call calc_penalty(?)}";
					CallableStatement stmt = con.prepareCall(sql);
					stmt.setInt(1, Integer.parseInt(M_id));
					stmt.execute();
					String sql1 = "select penalty from borrows where m_id = " + M_id;
					ResultSet rs = smt.executeQuery(sql1);
					while (rs.next()) {
						String Penalty = rs.getString(1);
						int pen = Integer.parseInt(Penalty);
						if (pen > 0) {
							JOptionPane.showMessageDialog(null,
									"Pay penalty :" + pen + "Rs.!! Contact to Librarian :/");
						} else {
							try {
								Statement smt1 = con.createStatement();
								String sql2 = "delete from borrows where m_id = " + M_id;
								smt1.executeUpdate(sql2);
							} catch (Exception E2) {
								JOptionPane.showMessageDialog(null, E2);
							}

							JOptionPane.showMessageDialog(null, "Return Successfull!!");
						}
					}
					con.close();
				} catch (Exception E) {
					JOptionPane.showMessageDialog(null, E);
				}
			} else {
				JOptionPane.showMessageDialog(null, "No book issued or no return left!!");
			}
		} catch (Exception E) {
			JOptionPane.showMessageDialog(null, E);
		}

	}

	public void display_info(String M_id, Statement smt) {
		JFrame f5 = new JFrame("Member Information");
		int M_ID = Integer.parseInt(M_id);
		try {
			String sql = "select m_id,m_name,m_email,contact_info ,street,city,zipcode from member where m_id =" + M_ID;
			ResultSet rs = smt.executeQuery(sql);
			JTable mem_info = new JTable();
			mem_info.setModel(DbUtils.resultSetToTableModel(rs));
			JScrollPane scrollPane = new JScrollPane(mem_info);
			f5.add(scrollPane);
			f5.setSize(800, 100);
			f5.setLocationRelativeTo(null);
			f5.setVisible(true);
		} catch (Exception E) {
			JOptionPane.showMessageDialog(null, E);
		}
	}

	public void update_info(String m_id, Statement smt) {
		JFrame F = new JFrame("Update the information");

		JLabel J1, J2;

		J1 = new JLabel("Mail ID");
		J1.setBounds(40, 20, 200, 30);

		JTextField Mail_ID = new JTextField();
		Mail_ID.setBounds(120, 20, 200, 30);

		JButton mailbutton = new JButton("UpdateEmail");// creating instance of JButton for Login Button
		mailbutton.setBounds(120, 70, 200, 30);

		mailbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String mail_id = Mail_ID.getText();
				if (mail_id.equals("")) {
					JOptionPane.showMessageDialog(null, "Please enter Mail ID !!");
				} else {
					try {

						int M_id = Integer.parseInt(m_id);
						String sql = "update member set m_email = '" + mail_id + "' where m_id = " + M_id;
						smt.executeUpdate(sql);
						JOptionPane.showMessageDialog(null, "Email ID updated Successfully!!");
						F.dispose();
					} catch (Exception E) {
						JOptionPane.showMessageDialog(null, E);
					}
				}

			}

		});

		J2 = new JLabel("Contact Info");
		J2.setBounds(40, 120, 200, 30);

		JTextField contact_info = new JTextField();
		contact_info.setBounds(120, 120, 200, 30);

		JButton contactbutton = new JButton("Update Contact Info");// creating instance of JButton for Login Button
		contactbutton.setBounds(120, 170, 200, 30);

		contactbutton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				String Contact_info = contact_info.getText();
				if (Contact_info.equals("")) {
					JOptionPane.showMessageDialog(null, "Pls Enter contact Info");
				} else {
					try {

						int M_id = Integer.parseInt(m_id);
						String sql = "update member set contact_info = '" + Contact_info + "' where m_id = " + M_id;
						smt.executeUpdate(sql);
						JOptionPane.showMessageDialog(null, "Contact Info Updated Sucesssfully !!");
						F.dispose();
					} catch (Exception E) {
						JOptionPane.showMessageDialog(null, E);
					}
				}

			}

		});

		F.add(J1);
		F.add(Mail_ID);
		F.add(mailbutton);
		F.add(J2);
		F.add(contact_info);
		F.add(contactbutton);
		F.setSize(400, 300);
		F.setLocationRelativeTo(null);
		F.setLayout(null);
		F.setVisible(true);
	}

	public void view_book(String m_id, Statement smt) {
		try {
			String sql = "select b_id from borrows where m_id=" + m_id;
			ResultSet rs = smt.executeQuery(sql);
			if (rs.next()) {
				int M_id = Integer.parseInt(m_id);
				JFrame f5 = new JFrame("Issued Book Info");
				String sql1 = "select Bo.b_id,b.b_name,Bo.borrow_date,Bo.return_date \r\n"
						+ "from borrows Bo , books b\r\n" + "where Bo.b_id = b.b_id and Bo.m_id =" + M_id;
				ResultSet rs1 = smt.executeQuery(sql1);
				JTable mem_info = new JTable();
				mem_info.setModel(DbUtils.resultSetToTableModel(rs1));
				JScrollPane scrollPane = new JScrollPane(mem_info);
				f5.add(scrollPane);
				f5.setSize(800, 100);
				f5.setLocationRelativeTo(null);
				f5.setVisible(true);
			} else {
				JOptionPane.showMessageDialog(null, "You haven't issued any book yet.");
			}
		} catch (Exception E) {
			JOptionPane.showMessageDialog(null, E);
		}
	}
}
