package vue;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;

import dao.AgentDAO;
import entite.Agent;
import entite.Database;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import java.awt.Font;
import java.awt.Color;

public class Vue_Login {

	private JFrame frame;
	private JPasswordField tmdp;
	private JTextField tidentifiant;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Vue_Login window = new Vue_Login();
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
	public Vue_Login() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		frame = new JFrame();
		frame.setBounds(100, 100, 240, 177);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);

		JLabel lblNewLabel = new JLabel("Identifiant");
		lblNewLabel.setBounds(31, 11, 86, 14);
		frame.getContentPane().add(lblNewLabel);

		JLabel lblMotDePasse = new JLabel("Mot de passe");
		lblMotDePasse.setBounds(31, 54, 86, 14);
		frame.getContentPane().add(lblMotDePasse);

		// bouton valider
		JButton btnsubmit = new JButton("Valider");
		btnsubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Database.Connect();
				String identifiant = tidentifiant.getText();
				String mdp = tmdp.getText();
				AgentDAO agentDAO = new AgentDAO();
				ArrayList<Agent> agents = agentDAO.getByIdentifiant(identifiant, mdp);
				if (agents.size() > 0) {
					Agent agent = agents.get(0);
					new Vue_AccueilAgent(agent).getFrame().setVisible(true);
					frame.dispose();
				} else {
					JOptionPane.showMessageDialog(null, "Identifiant ou mot de passe non valide");
				}

			}
		});
		btnsubmit.setBounds(109, 98, 89, 23);
		frame.getContentPane().add(btnsubmit);

		// champ mdp
		tmdp = new JPasswordField();
		tmdp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnsubmit.doClick();
			}
		});
		tmdp.setBounds(31, 67, 167, 20);
		frame.getContentPane().add(tmdp);
		tmdp.setColumns(10);

		// champ identifiant
		tidentifiant = new JTextField();
		tidentifiant.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnsubmit.doClick();
			}
		});
		tidentifiant.setBounds(31, 23, 167, 20);
		frame.getContentPane().add(tidentifiant);
		tidentifiant.setColumns(10);

	}

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}
}
