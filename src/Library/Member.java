package Library;
import javax.swing.*;
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
	public void Member_menu() {
		JFrame f =new JFrame("Member Functions");
		JButton add_but = new JButton("Search Book");
		add_but.setBounds(20,20,120,25);
		add_but.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null,"Added sucessfully!");
			}
		});
		
		JButton search_but= new JButton("Issue Book");
		search_but.setBounds(150,20,120,25);
		search_but.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null,"Search successful!");
			}
		});
		
		JButton up_aut_but= new JButton("Return Book");
		up_aut_but.setBounds(280,20,120,25);
		up_aut_but.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null,"Update(author) successful!");
			}
		});
		
		JButton up_pub_but= new JButton("Search Author");
		up_pub_but.setBounds(410,20,140,25);
		up_pub_but.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null,"Update(publisher) successful!");
			}
		});
		
		JButton delete_but= new JButton("Search Publisher ");
		delete_but.setBounds(25,60,150,25);
		delete_but.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null,"Delete successful!");
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
	

}
