package Library;
import javax.swing.*;

import net.proteanit.sql.DbUtils;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import java.awt.FlowLayout;


public class Member{
	public void Member_menu(String m_id,Statement smt) {
		JFrame f =new JFrame("Member Functions");
		JButton add_but = new JButton("Search Book");
		add_but.setBounds(20,20,120,25);
		add_but.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				book_search(smt);
			}
		});
		
		JButton search_but= new JButton("Issue Book");
		search_but.setBounds(150,20,120,25);
		search_but.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login L = new Login();
				Connection connection = L.connect();
				try {
				CallableStatement cstmt = connection.prepareCall("{? = call check_user_inBorrows(?)}");
				cstmt.registerOutParameter(1,java.sql.Types.INTEGER);
				cstmt.setString(2, m_id);
				cstmt.execute();
				if(cstmt.getInt(1) == 0) {
					issue_book(m_id,smt);
				}
				else {
					JOptionPane.showMessageDialog(null,"Return the book first!!");
				}
				
				}
				catch(Exception E) {
					
				}
			}
		});
		
		JButton up_aut_but= new JButton("Return Book");
		up_aut_but.setBounds(280,20,120,25);
		up_aut_but.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
		JButton up_pub_but= new JButton("Search Author");
		up_pub_but.setBounds(410,20,140,25);
		up_pub_but.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				author_search(smt);
			}
		});
		
		JButton delete_but= new JButton("Search Publisher ");
		delete_but.setBounds(25,60,150,25);
		delete_but.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				publisher_search(smt);
			}
		});
		
		JButton up_book_but= new JButton("Update User Info");
		up_book_but.setBounds(200,60,150,25);
		up_book_but.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null,"Update successful!");
			}
		});
		JButton dis_userinfo_but= new JButton("Display user info");
		dis_userinfo_but.setBounds(375,60,150,25);
		dis_userinfo_but.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null,"Update successful!");
			}
		});
		
		
			
		
		f.add(add_but);
		f.add(search_but);
		f.add(up_aut_but);
		f.add(up_pub_but);
		f.add(delete_but);
		f.add(up_book_but);
		f.add(dis_userinfo_but);
		f.setSize(600,200);//400 width and 500 height  
	    f.setLayout(null);//using no layout managers  
	    f.setVisible(true);
	}
	// method to accept input for books
	public static void book_search(Statement smt) {
		JFrame F1 = new JFrame("Search the Book");
		
		JLabel J1;
		
		J1 = new JLabel("Enter Book Name ");
		J1.setBounds(100,15,200,30);
		
		JTextField B_name = new JTextField();
		B_name.setBounds(100, 50, 200, 30);
	
		JButton searchbutton=new JButton("Search Books");//creating instance of JButton for Login Button
	    searchbutton.setBounds(100,85,200,30);
	    
	    searchbutton.addActionListener(new ActionListener(){
	    	public void actionPerformed(ActionEvent e) {
	    		String b_name = B_name.getText();
	    		String sql = "select b_id,b_name,genre,aisle from books where b_name like '%"+b_name+"%'";
	    		display_results(smt,sql,"Books Available");
	    		F1.dispose();
	    	}
	    });
	    	
	    
	    F1.add(J1);
	    F1.add(B_name);
	    F1.add(searchbutton);
	    F1.setSize(400,200);
	    F1.setLayout(null);
	    F1.setVisible(true);
	}
	
	public static void author_search(Statement smt) {
		JFrame F1 = new JFrame("Search the Author");
		
		JLabel J1;
		
		J1 = new JLabel("Enter Author Name ");
		J1.setBounds(100,15,200,30);
		
		JTextField A_name = new JTextField();
		A_name.setBounds(100, 50, 200, 30);
	
		JButton searchbutton=new JButton("Search Author");//creating instance of JButton for Login Button
	    searchbutton.setBounds(100,85,200,30);
	    
	    searchbutton.addActionListener(new ActionListener(){
	    	public void actionPerformed(ActionEvent e) {
	    		String a_Name = A_name.getText();
	    		String sql = "select * from author where a_name = '"+a_Name+"'";
	    		display_results(smt,sql,"Authors Available");
	    		F1.dispose();
	    	}
	    });
	    	
	    
	    F1.add(J1);
	    F1.add(A_name);
	    F1.add(searchbutton);
	    F1.setSize(400,200);
	    F1.setLayout(null);
	    F1.setVisible(true);
	}
	
	public static void publisher_search(Statement smt) {
		JFrame F1 = new JFrame("Search the Publisher");
		
		JLabel J1;
		
		J1 = new JLabel("Enter Publisher Name ");
		J1.setBounds(100,15,200,30);
		
		JTextField P_name = new JTextField();
		P_name.setBounds(100, 50, 200, 30);
	
		JButton searchbutton=new JButton("Search Publisher");//creating instance of JButton for Login Button
	    searchbutton.setBounds(100,85,200,30);
	    
	    searchbutton.addActionListener(new ActionListener(){
	    	public void actionPerformed(ActionEvent e) {
	    		String p_Name = P_name.getText();
	    		String sql = "select * from publisher where p_name = '"+p_Name+"'";
	    		display_results(smt,sql,"Publishers List");
	    		F1.dispose();
	    	}
	    	
	    });
	    	
	    
	    F1.add(J1);
	    F1.add(P_name);
	    F1.add(searchbutton);
	    F1.setSize(400,200);
	    F1.setLayout(null);
	    F1.setVisible(true);
	}

	
	
	private static void display_results(Statement smt,String sql,String s) {
		JFrame J = new JFrame(s);
		try {
			ResultSet rs = smt.executeQuery(sql);
			JTable book_list = new JTable();
			book_list.setModel(DbUtils.resultSetToTableModel(rs));
			JScrollPane scrollPane = new JScrollPane(book_list);
			J.add(scrollPane);
			J.setSize(800,400);
			J.setVisible(true);
		}
		catch(Exception e) {
			JOptionPane.showMessageDialog(null,e);
		}
	}
	
	public static void issue_book(String m_id , Statement smt) {
JFrame F1 = new JFrame("Issue the Book");
		
		JLabel J1;
		
		J1 = new JLabel("Enter Book ID");
		J1.setBounds(100,15,200,30);
		
		JTextField B_ID = new JTextField();
		B_ID.setBounds(100, 50, 200, 30);
	
		JButton issuebutton =new JButton("ISSUE");//creating instance of JButton for Login Button
	    issuebutton.setBounds(100,85,200,30);
	    
	    issuebutton.addActionListener(new ActionListener(){
	    	public void actionPerformed(ActionEvent e) {
	    		String b_id = B_ID.getText();
	    		int B_id = Integer.parseInt(b_id);
	    		int M_id = Integer.parseInt(m_id);
	    		try {
	    			String sql = "insert into borrows values("+B_id +","+M_id+",curdate() , DATE_ADD(curdate(),INTERVAL 14 DAY) , 0)";
	    			smt.executeUpdate(sql);
	    			F1.dispose();
	    			JOptionPane.showMessageDialog(null, "Book Issued Sucessfully!!");
	    		}
	    		catch(Exception e1) {
	    			JOptionPane.showMessageDialog(null,e1);
	    		}
	    	}
	    	
	    });
	    
	    F1.add(J1);
	    F1.add(B_ID);
	    F1.add(issuebutton);
	    F1.setSize(400,400);
	    F1.setLayout(null);
	    F1.setVisible(true);

	}

}
