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

public class Login extends JFrame{
	//private JLabel item1;
	private JLabel item1;
	public void select_user() {
		item1=new JLabel("Welcome to Library!");
		item1.setBounds(120, 0, 150,40);
	JFrame f=new JFrame("User Select");
	//item1=new JLabel("Welcome to Library!");
	//add(item1);
    JButton b1=new JButton("Librarian");
    JButton b2=new JButton("Member");
    b1.setBounds(50,100,95,30);
    b2.setBounds(200,100,95,30);
    f.add(item1);
    f.add(b1);  
    f.add(b2);
    f.setSize(400,400);  
    f.setLayout(null);  
    f.setVisible(true);
    b1.addActionListener(new ActionListener() {
    	public void actionPerformed(ActionEvent e) {
    		librarian();
    		f.dispose();
    	}
    
    	
    });
    b2.addActionListener(new ActionListener() {
    	public void actionPerformed(ActionEvent e) {
    		member();
    		f.dispose();
    	}
    
    	
    });
	}
	public void librarian() {
		
		JFrame f1=new JFrame("Librarian login");//creating instance of JFrame  
	    JLabel l1,l2;  
	    l1=new JLabel("Username");  //Create label Username
	    l1.setBounds(30,15, 100,30); //x axis, y axis, width, height 
	     
	    l2=new JLabel("Password");  //Create label Password
	    l2.setBounds(30,50, 100,30);    
	     
	    JTextField F_user = new JTextField(); //Create text field for username
	    F_user.setBounds(110, 15, 200, 30);
	         
	    JPasswordField F_pass=new JPasswordField(); //Create text field for password
	    F_pass.setBounds(110, 50, 200, 30);
	       
	    JButton login_but=new JButton("Login");//creating instance of JButton for Login Button
	    login_but.setBounds(130,90,80,25);//Dimensions for button
	    login_but.addActionListener(new ActionListener(){
	    	public void actionPerformed(ActionEvent e) {
	    		String username = F_user.getText();
	    		char password []= F_pass.getPassword();
	    		
	    		if(username.equals("")) {
	    			JOptionPane.showMessageDialog(null, "Please enter usename !!");
	    		}
	    		else if(String.valueOf(password).equals("")) {
	    			JOptionPane.showMessageDialog(null, "Please enter password !!");
	    		}
	    		else {
	    			Connection connection = connect();
	    			try {
	    				Statement smt = connection.createStatement();
	    				smt.executeUpdate("use library");
	    				String st = ("SELECT * FROM LIBRARIAN WHERE lib_name = '"+username+"' AND lib_password='"+String.valueOf(password)+"'");
	    				ResultSet rs = smt.executeQuery(st);
	    				if(rs.next()==false) { //Move pointer below
	    	                  //System.out.print("No user");  
//	    					System.out.println(rs.getString(1)+"\n");
//	    					System.out.println(rs.getString(2)+"\n");
	    					
	    	                  JOptionPane.showMessageDialog(null,"Wrong Username/Password!"); //Display Message
	    	 
	    	              }
	    				else {
	    					
	    					rs.beforeFirst();
	    					while(rs.next()) {
	    						String l_id = rs.getString("lib_id");
	    						Librarian L = new Librarian();
		    					L.librarian_menu(l_id,smt);
	    					}
	    					f1.dispose();
	    					
	    				}
	    			}
	    			catch(Exception ex) {
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
	    f1.setSize(400,180);//400 width and 500 height  
	    f1.setLayout(null);
	    f1.setVisible(true);
	}
	public void member() {
		JFrame f1=new JFrame("Member login");//creating instance of JFrame  
	    JLabel l1,l2;  
	    l1=new JLabel("Username");  //Create label Username
	    l1.setBounds(30,15, 100,30); //x axis, y axis, width, height 
	     
	    l2=new JLabel("Password");  //Create label Password
	    l2.setBounds(30,50, 100,30);    
	     
	    JTextField F_user = new JTextField(); //Create text field for username
	    F_user.setBounds(110, 15, 200, 30);
	         
	    JPasswordField F_pass=new JPasswordField(); //Create text field for password
	    F_pass.setBounds(110, 50, 200, 30);
	       
	    JButton login_but=new JButton("Login");//creating instance of JButton for Login Button
	    login_but.setBounds(130,90,80,25);//Dimensions for button
	    login_but.addActionListener(new ActionListener(){
	    	public void actionPerformed(ActionEvent e) {
	    		String username = F_user.getText();
	    		char password [] = F_pass.getPassword();
	    		
	    		if(username.equals("")) {
	    			JOptionPane.showMessageDialog(null, "Please enter usename !!");
	    		}
	    		else if(String.valueOf(password).equals("")) {
	    			JOptionPane.showMessageDialog(null, "Please enter password !!");
	    		}
	    		else {
	    			Connection connection = connect();
	    			try {
	    				Statement smt = connection.createStatement();
	    				smt.executeUpdate("use library");
	    				String st = ("SELECT * FROM member WHERE m_name = '"+username+"' AND m_password='"+String.valueOf(password)+"'");
	    				ResultSet rs = smt.executeQuery(st);
//    				while(rs.next()) {
//	    					System.out.println(rs.getString(1)+"\n");
//	    					System.out.println(rs.getString(2)+"\n");
//	    				}
	    				if(rs.next()==false) { //Move pointer below
	    	                  //System.out.print("No user");  
//	    					System.out.println(rs.getString(1)+"\n");
//	    					System.out.println(rs.getString(2)+"\n");
	    					
	    	                  JOptionPane.showMessageDialog(null,"Wrong Username/Password!"); //Display Message
	    	 
	    	              }
	    				else {
	    					
	    					rs.beforeFirst();
	    					Member M = new Member();
	    					M.Member_menu();
	    					f1.dispose();
	    					
	    				}
	    			}
	    			catch(Exception ex) {
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
	    f1.setSize(400,180);//400 width and 500 height  
	    f1.setLayout(null);
	    f1.setVisible(true);
	}
	public static Connection connect()
	{
	try {
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library","root", "Divyang@03");
	        return con;
	 } 
	 catch (Exception ex) {
	        ex.printStackTrace();
	 }
	return null;
	}
}


