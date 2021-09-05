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
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;

import dao.AdminDAO;
import dao.AgentDAO;
import entite.Admin;
import entite.Agent;
import entite.Database;
import java.awt.Color;
import javax.swing.SwingConstants;

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
		String className = getLookAndFeelClassName("Metal");
		try {
			UIManager.setLookAndFeel(className);
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InstantiationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (UnsupportedLookAndFeelException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		frame.setLocationRelativeTo(null);

		JPanel panel = new JPanel();
		panel.setOpaque(false);
		panel.setBounds(296, 180, 372, 232);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Identifiant");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(143, 31, 86, 14);
		panel.add(lblNewLabel);

		JLabel lblMotDePasse = new JLabel("Mot de passe");
		lblMotDePasse.setHorizontalAlignment(SwingConstants.CENTER);
		lblMotDePasse.setBounds(143, 97, 86, 14);
		panel.add(lblMotDePasse);

		// bouton valider
		JButton btnsubmit = new JButton("Connexion");
		btnsubmit.setHorizontalTextPosition(SwingConstants.CENTER);
		btnsubmit.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnsubmit.setIcon(new ImageIcon(Vue_Login.class.getResource("/img/connexion.png")));
		btnsubmit.setBackground(Color.WHITE);
		btnsubmit.setBorder(null);
		btnsubmit.setOpaque(false);
		btnsubmit.setBounds(152, 151, 67, 67);
		panel.add(btnsubmit);

		// champ mdp
		tmdp = new JPasswordField();
		tmdp.setBounds(64, 120, 244, 19);
		panel.add(tmdp);
		tmdp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnsubmit.doClick();
			}
		});
		tmdp.setColumns(10);

		// champ identifiant
		tidentifiant = new JTextField();
		tidentifiant.setBounds(64, 50, 244, 20);
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
				ArrayList<Admin> admins = adminDAO.getByIdentifiant(identifiant, mdp);
				if (agents.size() > 0) {
					Agent agent = agents.get(0);
					new Vue_AccueilAgent(agent).getFrame().setVisible(true);
					frame.dispose();
				} else if (admins.size() > 0) {
					frame.dispose();
					new Vue_AccueilAdmin().getFrame().setVisible(true);
				} else {
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

	public static String getLookAndFeelClassName(String nameSnippet) {
		LookAndFeelInfo[] plafs = UIManager.getInstalledLookAndFeels();
		for (LookAndFeelInfo info : plafs) {
			if (info.getName().contains(nameSnippet)) {
				return info.getClassName();
			}
		}
		return null;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}
}
