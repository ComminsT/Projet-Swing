package entite;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.List;

public class testtestT {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					testtestT window = new testtestT();
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
	public testtestT() {
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
		
		List list = new List();
		list.setBounds(10, 10, 249, 200);
		frame.getContentPane().add(list);
		list.add("test");
		int nbr=list.getItemCount();
		System.out.println(nbr);
	}

}
