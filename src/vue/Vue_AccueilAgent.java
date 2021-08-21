package vue;

import java.awt.EventQueue;

import javax.swing.JFrame;

import entite.Agent;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Cursor;

import javax.swing.border.LineBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class Vue_AccueilAgent {

	private JFrame frame;
	private Agent agent;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Vue_AccueilAgent window = new Vue_AccueilAgent();
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
	public Vue_AccueilAgent() {
		initialize();
	}
	
	public Vue_AccueilAgent(Agent agent) {
		this.agent=agent;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 981, 630);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		
		JButton lblNewLabel = new JButton("Locataires");
		lblNewLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				frame.setCursor(Cursor.HAND_CURSOR);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				frame.setCursor(Cursor.DEFAULT_CURSOR);
			}
		});
		lblNewLabel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		lblNewLabel.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblNewLabel.setBackground(Color.LIGHT_GRAY);
		lblNewLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewLabel.setVerticalTextPosition(SwingConstants.BOTTOM);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\Seria\\OneDrive\\CDA\\ExerciceSwingBinomeCRUD\\src\\images\\client.png"));
		lblNewLabel.setBounds(20, 6, 121, 112);
		frame.getContentPane().add(lblNewLabel);
		
		JButton btnProprietaires = new JButton("Proprietaires");
		btnProprietaires.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnProprietaires.setHorizontalTextPosition(SwingConstants.CENTER);
		btnProprietaires.setHorizontalAlignment(SwingConstants.CENTER);
		btnProprietaires.setBorder(new LineBorder(new Color(0, 0, 0)));
		btnProprietaires.setBackground(Color.LIGHT_GRAY);
		btnProprietaires.setBounds(153, 6, 121, 112);
		frame.getContentPane().add(btnProprietaires);
		
		JButton btnBiens = new JButton("Biens");
		btnBiens.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnBiens.setHorizontalTextPosition(SwingConstants.CENTER);
		btnBiens.setHorizontalAlignment(SwingConstants.CENTER);
		btnBiens.setBorder(new LineBorder(new Color(0, 0, 0)));
		btnBiens.setBackground(Color.LIGHT_GRAY);
		btnBiens.setBounds(286, 6, 121, 112);
		frame.getContentPane().add(btnBiens);
		
		JButton btnVisite = new JButton("Visite");
		btnVisite.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnVisite.setHorizontalTextPosition(SwingConstants.CENTER);
		btnVisite.setHorizontalAlignment(SwingConstants.CENTER);
		btnVisite.setBorder(new LineBorder(new Color(0, 0, 0)));
		btnVisite.setBackground(Color.LIGHT_GRAY);
		btnVisite.setBounds(419, 6, 121, 112);
		frame.getContentPane().add(btnVisite);
		
		JButton btnEspacePersonnel = new JButton("Comptabilité");
		btnEspacePersonnel.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnEspacePersonnel.setHorizontalTextPosition(SwingConstants.CENTER);
		btnEspacePersonnel.setHorizontalAlignment(SwingConstants.CENTER);
		btnEspacePersonnel.setBorder(new LineBorder(new Color(0, 0, 0)));
		btnEspacePersonnel.setBackground(Color.LIGHT_GRAY);
		btnEspacePersonnel.setBounds(552, 6, 121, 112);
		frame.getContentPane().add(btnEspacePersonnel);
		
		JButton btnEspacePersonnelle = new JButton("Espace personnelle");
		btnEspacePersonnelle.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnEspacePersonnelle.setHorizontalTextPosition(SwingConstants.CENTER);
		btnEspacePersonnelle.setHorizontalAlignment(SwingConstants.CENTER);
		btnEspacePersonnelle.setBorder(new LineBorder(new Color(0, 0, 0)));
		btnEspacePersonnelle.setBackground(Color.LIGHT_GRAY);
		btnEspacePersonnelle.setBounds(685, 6, 121, 112);
		frame.getContentPane().add(btnEspacePersonnelle);
		
		JButton btnDconnexion = new JButton("Déconnexion");
		btnDconnexion.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnDconnexion.setHorizontalTextPosition(SwingConstants.CENTER);
		btnDconnexion.setHorizontalAlignment(SwingConstants.CENTER);
		btnDconnexion.setBorder(new LineBorder(new Color(0, 0, 0)));
		btnDconnexion.setBackground(Color.LIGHT_GRAY);
		btnDconnexion.setBounds(818, 6, 121, 112);
		frame.getContentPane().add(btnDconnexion);
		
		JLabel lblNewLabel_1 = new JLabel("Bienvenue "+agent.getPrenom());
		lblNewLabel_1.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblNewLabel_1.setBounds(20, 130, 254, 58);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon("C:\\Users\\Seria\\OneDrive\\Bureau fixe\\AdobeStock_130518605c.jpeg"));
		lblNewLabel_2.setBounds(-16, 0, 1000, 591);
		frame.getContentPane().add(lblNewLabel_2);
	}

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}
}
