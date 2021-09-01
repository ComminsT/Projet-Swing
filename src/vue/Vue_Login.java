package vue;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import dao.AdminDAO;
import dao.AgentDAO;
import entite.Admin;
import entite.Agent;
import entite.Database;

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
		frame.setBounds(100, 100, 981, 620);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);

		JPanel panel = new JPanel();
		panel.setOpaque(false);
		panel.setBounds(407, 241, 167, 110);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Identifiant");
		lblNewLabel.setBounds(0, 3, 86, 14);
		panel.add(lblNewLabel);

		JLabel lblMotDePasse = new JLabel("Mot de passe");
		lblMotDePasse.setBounds(0, 43, 86, 14);
		panel.add(lblMotDePasse);

		// bouton valider
		JButton btnsubmit = new JButton("Valider");
		btnsubmit.setBounds(78, 83, 89, 23);
		panel.add(btnsubmit);

		// champ mdp
		tmdp = new JPasswordField();
		tmdp.setBounds(0, 60, 167, 20);
		panel.add(tmdp);
		tmdp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnsubmit.doClick();
			}
		});
		tmdp.setColumns(10);

		// champ identifiant
		tidentifiant = new JTextField();
		tidentifiant.setBounds(0, 20, 167, 20);
		panel.add(tidentifiant);
		tidentifiant.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnsubmit.doClick();
			}
		});
		tidentifiant.setColumns(10);
		btnsubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Database.Connect();
				String identifiant = tidentifiant.getText();
				String mdp = tmdp.getText();
				
				AgentDAO agentDAO = new AgentDAO();
				ArrayList<Agent> agents = agentDAO.getByIdentifiant(identifiant, mdp);
				AdminDAO adminDAO = new AdminDAO();
				ArrayList<Admin> admins=adminDAO.getByIdentifiant(identifiant, mdp);
				if (agents.size() > 0) {
					Agent agent = agents.get(0);
					new Vue_AccueilAgent(agent).getFrame().setVisible(true);
					frame.dispose();
				} else if(admins.size()>0){
					Admin admin = admins.get(0);
					new Vue_AccueilAdmin().getFrame().setVisible(true);
				}else {
					JOptionPane.showMessageDialog(null, "Identifiant ou mot de passe non valide");	
				}
				

			}
		});
		JLabel lblBG = new JLabel("");
		lblBG.setOpaque(true);
		lblBG.setIcon(new ImageIcon(Vue_AccueilAgent.class.getResource("/img/accueil_bg.jpeg")));
		lblBG.setBounds(-16, 0, 1000, 591);
		frame.getContentPane().add(lblBG);

	}

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}
}
