package vue;

import java.awt.EventQueue;

import javax.swing.JFrame;

import entite.Agent;
import entite.Proprietaire;

public class Vue_ProprietaireDetails {

	private JFrame frame;
	private Proprietaire proprietaire;
	private Agent agent;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Vue_ProprietaireDetails window = new Vue_ProprietaireDetails();
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
	public Vue_ProprietaireDetails() {
		initialize();
	}
	public Vue_ProprietaireDetails(Proprietaire proprietaire, Agent agent) {
		this.proprietaire = proprietaire;
		this.agent=agent;
		
	}
	

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

}
