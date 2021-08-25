package vue;

import java.awt.EventQueue;
import java.io.File;

import javax.swing.JFrame;
import javax.swing.JButton;

public class Vue_SaveFile {

	private JFrame frame;
	private final JButton btnNewButton = new JButton("Save");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Vue_SaveFile window = new Vue_SaveFile();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Vue_SaveFile() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		btnNewButton.setBounds(131, 96, 120, 31);
		frame.getContentPane().add(btnNewButton);
		
		 String folderName="5";
		    String basePath="C:\\Users\\Seria\\OneDrive\\CDA\\Java\\projet SWING";
		    String concat=basePath+"\\"+folderName;
		    File f1=new File(concat);
		    boolean bool = f1.mkdir();  
		      if(bool){  
		         System.out.println("Folder is created successfully");  
		      }else{  
		         System.out.println("Error Found!");  
		      }  
		
		
	}

}
