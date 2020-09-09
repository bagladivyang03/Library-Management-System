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
import net.proteanit.sql.DbUtils;


public class Librarian {
	public void librarian_menu(String l_id , Statement smt) {
		JFrame f =new JFrame("Librarian Functions");
		JButton add_but = new JButton("Add Books ");
		add_but.setBounds(20,20,120,25);
		add_but.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				add_books(l_id,smt);
			}
		});
		
		JButton search_but= new JButton("Search User ");
		search_but.setBounds(150,20,120,25);
		search_but.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
		JButton up_books_but= new JButton("Update Aisle");
		up_books_but.setBounds(280,20,120,25);
		up_books_but.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				update_books_aisle(smt);
			}
		});
		
		JButton add_pub_but= new JButton("Add Publisher ");
		add_pub_but.setBounds(410,20,140,25);
		add_pub_but.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				add_publisher(smt);
			}
		});
		
		JButton delete_but= new JButton("Delete Books ");
		delete_but.setBounds(60,60,120,25);
		delete_but.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				delete_books(smt);
			}
		});
		
		JButton dis_book_but= new JButton("Display Books");
		dis_book_but.setBounds(190,60,120,25);
		dis_book_but.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				display_books(smt);
			}
		});
		
		JButton add_author_but= new JButton("Add Author");
		add_author_but.setBounds(330,60,120,25);
		add_author_but.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				add_author(smt);
			}
		});
		
		
			
		
		f.add(add_but);
		f.add(search_but);
		f.add(up_books_but);
		f.add(add_pub_but);
		f.add(delete_but);
		f.add(dis_book_but);
		f.add(add_author_but);
		f.setSize(600,200);//400 width and 500 height  
	    f.setLayout(null);//using no layout managers  
	    f.setVisible(true);
	}
	// method to accept input for books
	public static void add_books(String l_id , Statement smt) {
		JFrame f2=new JFrame("Enter Book Info");
		JLabel J1,J2,J3,J4,J5,J6;
		J1 = new JLabel("Book ID");
		J1.setBounds(30,15,100,30);
		
		J2 = new JLabel("Book Name");
		J2.setBounds(30,50,100,30);
		
		J3 = new JLabel("Author ID");
		J3.setBounds(30,85,100,30);
		
		J4 = new JLabel("Publisher ID");
		J4.setBounds(30,120,110,30);
		
		J5 = new JLabel("Genre");
		J5.setBounds(30,155,110,30);
		
		J6 = new JLabel("Aisle");
		J6.setBounds(30,190,110,30);
		
		JTextField B_ID = new JTextField();
		B_ID.setBounds(150, 15, 200, 30);
		
		JTextField B_Name = new JTextField();
		B_Name.setBounds(150, 50, 200, 30);
		
		JTextField B_author = new JTextField();
		B_author.setBounds(150, 85, 200, 30);
		
		JTextField B_publisher = new JTextField();
		B_publisher.setBounds(150, 120, 200, 30);
		
		JTextField B_genre = new JTextField();
		B_genre.setBounds(150, 155, 200, 30);
		
		JTextField B_aisle = new JTextField();
		B_aisle.setBounds(150, 190, 200, 30);
		
		
	    JButton addbooks_but=new JButton("Add Books");//creating instance of JButton for Login Button
	    addbooks_but.setBounds(170,225,100,25);
	    
	    addbooks_but.addActionListener(new ActionListener(){
	    	public void actionPerformed(ActionEvent e) {
	    		String b_id = B_ID.getText();
	    		String b_name = B_Name.getText();
	    		String b_author = B_author.getText();
	    		String b_publisher = B_publisher.getText();
	    		String b_genre = B_genre.getText();
	    		String b_aisle = B_aisle.getText();
	    		int B_id = Integer.parseInt(b_id);
	    		int L_id = Integer.parseInt(l_id);
	    		int A_id = Integer.parseInt(b_author);
	    		int P_id = Integer.parseInt(b_publisher);
	    		int B_aisle = Integer.parseInt(b_aisle);
	    		try {
	    		String sql = "insert into books values("+B_id+",'" +b_name+"','"+b_genre+"',"+B_aisle+","+L_id+","+A_id+","+P_id+")";
	    		smt.executeUpdate(sql);
	    		}
	    		catch(Exception ex) {
	    			ex.printStackTrace();
	    		}
	    		f2.dispose();
	    		JOptionPane.showMessageDialog(null, "Books added Successfully!!");
	    	}
	    });
	    
	    
	    f2.add(J1);
	    f2.add(J2);
	    f2.add(J3);
	    f2.add(J4);
	    f2.add(J5);
	    f2.add(J6);
	    f2.add(B_ID);
	    f2.add(B_Name);
	    f2.add(B_author);
	    f2.add(B_publisher);
	    f2.add(addbooks_but);
	    f2.add(B_genre);
	    f2.add(B_aisle);
	    f2.setSize(400,400);//400 width and 500 height  
	    f2.setLayout(null);
	    f2.setVisible(true);
	    
	    
		
	}
	
	public static void add_author(Statement smt) {
		JFrame f3 = new JFrame("Enter Author Details");
		JLabel J1,J2,J3;
		
		J1 = new JLabel("Author ID");
		J1.setBounds(30,15,100,30);
		
		J2 = new JLabel("Author Name");
		J2.setBounds(30,50,100,30);
		
		J3 = new JLabel("Author E-mail");
		J3.setBounds(30,85,100,30);
		
		JTextField A_ID = new JTextField();
		A_ID.setBounds(150, 15, 200, 30);
		
		JTextField A_name = new JTextField();
		A_name.setBounds(150, 50, 200, 30);

		JTextField A_email = new JTextField();
		A_email.setBounds(150, 85, 200, 30);
		
		JButton addauth_but=new JButton("Add Author");//creating instance of JButton for Login Button
	    addauth_but.setBounds(170,155,100,25);
	    
	    addauth_but.addActionListener(new ActionListener(){
	    	public void actionPerformed(ActionEvent e) {
	    		String A_id = A_ID.getText();
	    		String a_name = A_name.getText();
	    		String a_email = A_email.getText();
	    		int a_id = Integer.parseInt(A_id);
	    		try {
	    		String sql = "insert into author values("+a_id+",'" +a_name+"','"+a_email+"')";
	    		smt.executeUpdate(sql);
	    		}
	    		catch(Exception ex) {
	    			ex.printStackTrace();
	    		}
	    		f3.dispose();
	    		JOptionPane.showMessageDialog(null, "Author Details added Successfully!!");
	    	}
	    });
	    
	    
		
		f3.add(J1);
	    f3.add(J2);
	    f3.add(J3);
	    f3.add(A_ID);
	    f3.add(A_name);
	    f3.add(A_email);
	    f3.add(addauth_but);
	    f3.setSize(400,400);//400 width and 500 height  
	    f3.setLayout(null);
	    f3.setVisible(true);
	}
	
	public static void add_publisher(Statement smt) {
		JFrame f4 = new JFrame("Enter Publisher Details");
		JLabel J1,J2,J3;
		
		J1 = new JLabel("Publisher ID");
		J1.setBounds(30,15,100,30);
		
		J2 = new JLabel("Publisher Name");
		J2.setBounds(30,50,100,30);
		
		J3 = new JLabel("Publisher E-mail");
		J3.setBounds(30,85,100,30);
		
		JTextField P_ID = new JTextField();
		P_ID.setBounds(150, 15, 200, 30);
		
		JTextField P_name = new JTextField();
		P_name.setBounds(150, 50, 200, 30);

		JTextField P_email = new JTextField();
		P_email.setBounds(150, 85, 200, 30);
		
		JButton addpubl_but=new JButton("Add Publisher");//creating instance of JButton for Login Button
	    addpubl_but.setBounds(170,155,150,25);
	    
	    addpubl_but.addActionListener(new ActionListener(){
	    	public void actionPerformed(ActionEvent e) {
	    		String P_id = P_ID.getText();
	    		String p_name = P_name.getText();
	    		String p_email = P_email.getText();
	    		int p_id = Integer.parseInt(P_id);
	    		try {
	    		String sql = "insert into publisher values("+p_id+",'" +p_name+"','"+p_email+"')";
	    		smt.executeUpdate(sql);
	    		}
	    		catch(Exception ex) {
	    			ex.printStackTrace();
	    		}
	    		f4.dispose();
	    		JOptionPane.showMessageDialog(null, "Publisher Details added Successfully!!");
	    	}
	    });
	    
	    
		
		f4.add(J1);
	    f4.add(J2);
	    f4.add(J3);
	    f4.add(P_ID);
	    f4.add(P_name);
	    f4.add(P_email);
	    f4.add(addpubl_but);
	    f4.setSize(400,400);//400 width and 500 height  
	    f4.setLayout(null);
	    f4.setVisible(true);
	}
	
	public static void display_books(Statement smt) {
		JFrame f5 = new JFrame("Books Available");
		try {
			String sql = "select b_id,b_name,genre,aisle from books";
			ResultSet rs = smt.executeQuery(sql);
			JTable book_list = new JTable();
			book_list.setModel(DbUtils.resultSetToTableModel(rs));
			JScrollPane scrollPane = new JScrollPane(book_list);
			f5.add(scrollPane);
			f5.setSize(800,400);
			f5.setVisible(true);
			
		}
		catch(Exception ex) {
			JOptionPane.showMessageDialog(null,ex);
		}
	}
	public static void delete_books(Statement smt) {
		JFrame f6 = new JFrame("Enter Book ID for deleting book");
		JLabel J1;
		
		J1 = new JLabel("Book ID");
		J1.setBounds(30,15,100,30);
		
		JTextField B_ID = new JTextField();
		B_ID.setBounds(150, 15, 200, 30);
		
		JButton add_del_but=new JButton("Delete Book");//creating instance of JButton for Login Button
	    add_del_but.setBounds(170,50,150,25);
	    
		add_del_but.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String b_id = B_ID.getText();
				int B_id = Integer.parseInt(b_id);
				try {
					String sql = "delete from books where b_id = "+B_id;
					smt.executeUpdate(sql);
				}
				catch(Exception ex) {
					JOptionPane.showMessageDialog(null,ex);
				}
				f6.dispose();
				JOptionPane.showMessageDialog(null,"Delete successful!");
			}
		});
		f6.add(J1);
		f6.add(B_ID);
		f6.add(add_del_but);
		f6.setSize(400,200);//400 width and 500 height  
	    f6.setLayout(null);
	    f6.setVisible(true);	
	}
	
	public static void update_books_aisle(Statement smt) {
		JFrame f7 = new JFrame("Update the book aisle");
		JLabel J1,J2;
		J1 = new JLabel("Book ID");
		J1.setBounds(30,15,100,30);
		
		J2 = new JLabel("New Aisle");
		J2.setBounds(30,45,100,30);

		JTextField B_ID = new JTextField();
		B_ID.setBounds(150, 15, 200, 30);
		
		JTextField new_aisle = new JTextField();
		new_aisle.setBounds(150, 45, 200, 30);
		
		JButton up_aisle_but=new JButton("Update Book");//creating instance of JButton for Login Button
	    up_aisle_but.setBounds(170,80,200,30);
	    
	    up_aisle_but.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		String B_id = B_ID.getText();
	    		String New_aisle = new_aisle.getText();	    	
	    		try {
	    			String sql = "update books set aisle = "+Integer.parseInt(New_aisle) +" where b_id = "+Integer.parseInt(B_id);
	    			smt.executeUpdate(sql);
	    			
	    		}
	    		catch(Exception ex) {
	    			JOptionPane.showMessageDialog(null,ex);
	    		}
	    		f7.dispose();
	    		JOptionPane.showMessageDialog(null,"Aisle Updated successfully!");
	    		
	    	}
	    });
	    f7.add(J1);
	    f7.add(J2);
	    f7.add(B_ID);
	    f7.add(new_aisle);
	    f7.add(up_aisle_but);
		f7.setSize(400,300);//400 width and 500 height  
	    f7.setLayout(null);
	    f7.setVisible(true);
	}
		
	

}
