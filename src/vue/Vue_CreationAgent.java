package vue;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import dao.AgentDAO;
import entite.Agent;
import entite.Checker;
import entite.Database;
import com.cemiltokatli.passwordgenerate.Password;
import com.cemiltokatli.passwordgenerate.PasswordType;
import com.toedter.calendar.JCalendar;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;

public class Vue_CreationAgent {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					Vue_CreationAgent window = new Vue_CreationAgent();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private JFrame frame;
	private JSeparator separator;
	private JTextField txtAdresseMail;
	private JTextField txtDomaine;
	private JTextField txtNom;

	private JTextField txtPrenom;
	private JLabel lblMailError;
	private JTextField txtAdresse;
	private JTextField txtVille;
	private JTextField txtCodePostale;
	private JLabel lblVille_1;
	private JTextField txtNumero;
	private JPanel panel;
	private JPanel panel_1;
	private JLabel lblStatut;

	/**
	 * Create the application.
	 */
	public Vue_CreationAgent() {
		initialize();

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 981, 620);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);

		JLabel btnNewButton = new JLabel("Confirmer");

		btnNewButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton.setHorizontalTextPosition(SwingConstants.CENTER);
		btnNewButton.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnNewButton.setIcon(new ImageIcon(Vue_CreationAgent.class.getResource("/img/valider.png")));

		btnNewButton.setBounds(906, 10, 63, 67);
		frame.getContentPane().add(btnNewButton);

		panel = new JPanel();
		panel.setOpaque(false);
		panel.setBounds(47, 96, 296, 471);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Nom");
		lblNewLabel.setBounds(0, 17, 46, 14);
		panel.add(lblNewLabel);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));

		txtPrenom = new JTextField();
		txtPrenom.setBounds(0, 48, 134, 20);
		panel.add(txtPrenom);
		txtPrenom.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if (txtPrenom.getText().equals("Prénom")) {
					txtPrenom.setText("");
					txtPrenom.setForeground(Color.black);
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (txtPrenom.getText().equals("")) {
					txtPrenom.setText("Prénom");
					txtPrenom.setForeground(Color.LIGHT_GRAY);
				}
			}
		});
		txtPrenom.setForeground(Color.LIGHT_GRAY);
		txtPrenom.setText("Prénom");
		txtPrenom.setColumns(10);

		txtNom = new JTextField();
		txtNom.setBounds(168, 48, 116, 20);
		panel.add(txtNom);
		txtNom.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if (txtNom.getText().equals("Nom")) {
					txtNom.setText("");
					txtNom.setForeground(Color.black);
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (txtNom.getText().equals("")) {
					txtNom.setText("Nom");
					txtNom.setForeground(Color.LIGHT_GRAY);
				}
			}
		});
		txtNom.setForeground(Color.LIGHT_GRAY);
		txtNom.setText("Nom");
		txtNom.setColumns(10);

		JLabel lblMail = new JLabel("Mail");
		lblMail.setBounds(0, 85, 46, 14);
		panel.add(lblMail);
		lblMail.setFont(new Font("Tahoma", Font.BOLD, 14));

		txtAdresseMail = new JTextField();
		txtAdresseMail.setBounds(0, 116, 134, 20);
		panel.add(txtAdresseMail);
		txtAdresseMail.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if (txtAdresseMail.getText().equals("Adresse Mail")) {
					txtAdresseMail.setText("");
					txtAdresseMail.setForeground(Color.black);
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (txtAdresseMail.getText().equals("")) {
					txtAdresseMail.setText("Adresse Mail");
					txtAdresseMail.setForeground(Color.LIGHT_GRAY);
				}
			}
		});
		txtAdresseMail.setText("Adresse Mail");
		txtAdresseMail.setForeground(Color.LIGHT_GRAY);
		txtAdresseMail.setColumns(10);

		JLabel lblNewLabel_1_1 = new JLabel("@");
		lblNewLabel_1_1.setBounds(146, 116, 13, 17);
		panel.add(lblNewLabel_1_1);
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));

		txtDomaine = new JTextField();
		txtDomaine.setBounds(168, 116, 116, 20);
		panel.add(txtDomaine);
		txtDomaine.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if (txtDomaine.getText().equals("Domaine")) {
					txtDomaine.setText("");
					txtDomaine.setForeground(Color.black);
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (txtDomaine.getText().equals("")) {
					txtDomaine.setText("Domaine");
					txtDomaine.setForeground(Color.LIGHT_GRAY);
				}

			}

		});
		txtDomaine.setText("Domaine");
		txtDomaine.setForeground(Color.LIGHT_GRAY);
		txtDomaine.setColumns(10);

		lblMailError = new JLabel("Adresse mail invalide ou déjà enregistrée");
		lblMailError.setBounds(0, 137, 252, 13);
		panel.add(lblMailError);
		lblMailError.setForeground(Color.RED);
		lblMailError.setFont(new Font("Tahoma", Font.PLAIN, 10));

		txtAdresse = new JTextField();
		txtAdresse.setBounds(0, 217, 284, 20);
		panel.add(txtAdresse);
		txtAdresse.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if (txtAdresse.getText().equals("Adresse")) {
					txtAdresse.setText("");
					txtAdresse.setForeground(Color.black);
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (txtAdresse.getText().equals("")) {
					txtAdresse.setText("Adresse");
					txtAdresse.setForeground(Color.LIGHT_GRAY);
				}

			}
		});
		txtAdresse.setText("Adresse");
		txtAdresse.setForeground(Color.LIGHT_GRAY);
		txtAdresse.setColumns(10);

		JLabel lblVille = new JLabel("Adresse");
		lblVille.setBounds(0, 183, 55, 17);
		panel.add(lblVille);
		lblVille.setFont(new Font("Tahoma", Font.BOLD, 14));

		JLabel lblVille_2 = new JLabel("Ville");
		lblVille_2.setBounds(0, 254, 29, 17);
		panel.add(lblVille_2);
		lblVille_2.setFont(new Font("Tahoma", Font.BOLD, 14));

		txtVille = new JTextField();
		txtVille.setBounds(0, 288, 134, 20);
		panel.add(txtVille);
		txtVille.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if (txtVille.getText().equals("Ville")) {
					txtVille.setText("");
					txtVille.setForeground(Color.black);

				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (txtVille.getText().equals("")) {
					txtVille.setText("Ville");
					txtVille.setForeground(Color.LIGHT_GRAY);
				}
			}
		});
		txtVille.setText("Ville");
		txtVille.setForeground(Color.LIGHT_GRAY);
		txtVille.setColumns(10);

		txtCodePostale = new JTextField();
		txtCodePostale.setBounds(146, 288, 105, 20);
		panel.add(txtCodePostale);
		txtCodePostale.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE)) {
					e.consume();
				}
			}
		});
		txtCodePostale.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if (txtCodePostale.getText().equals("Code Postal")) {
					txtCodePostale.setText("");
					txtCodePostale.setForeground(Color.black);
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (txtCodePostale.getText().equals("")) {
					txtCodePostale.setText("Code Postal");
					txtCodePostale.setForeground(Color.LIGHT_GRAY);
				}
			}
		});
		txtCodePostale.setText("Code Postal");
		txtCodePostale.setForeground(Color.LIGHT_GRAY);
		txtCodePostale.setColumns(10);

		JComboBox<String> comboboxidentifiant = new JComboBox<>();
		comboboxidentifiant.setBounds(0, 356, 73, 22);
		panel.add(comboboxidentifiant);
		comboboxidentifiant.setModel(new DefaultComboBoxModel<>(new String[] { "AT +43", "BE +32", "BG +359", "CY +357",
				"CZ +420", "DE +49", "DK +45", "EE +372", "EL +30", "ES +34", "FI +358", "FR +33", "GI +350", "HR +385",
				"HU +36", "IE +353", "IS +354", "IT +39", "LI +423", "LT +370", "LUX +352", "LV +371", "MT +356",
				"NL +31", "NO +47", "PL +48", "PT +351", "RO +40", "SE +46", "SI +386", "SK +421", "UK+44" }));
		

		txtNumero = new JTextField();
		txtNumero.setBounds(85, 357, 199, 22);
		panel.add(txtNumero);
		txtNumero.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if (txtNumero.getText().equals("Numero")) {
					txtNumero.setText("");
					txtNumero.setForeground(Color.black);
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (txtNumero.getText().equals("")) {
					txtNumero.setText("Numero");
					txtNumero.setForeground(Color.LIGHT_GRAY);
				}
			}
		});
		txtNumero.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE)
						|| txtNumero.getText().length() > 8) {
					e.consume();
				}
			}

		});
		txtNumero.setText("Numero");
		txtNumero.setForeground(Color.LIGHT_GRAY);
		txtNumero.setColumns(10);

		JLabel lblNewLabel_4 = new JLabel("Numéro de telephone");
		lblNewLabel_4.setBounds(0, 325, 192, 14);
		panel.add(lblNewLabel_4);
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 14));

		JLabel lblPhoneError = new JLabel("Numéro de téléphone déjà enregistré ou invalide");
		lblPhoneError.setBounds(0, 381, 232, 14);
		panel.add(lblPhoneError);
		lblPhoneError.setForeground(Color.RED);
		lblPhoneError.setFont(new Font("Tahoma", Font.PLAIN, 10));
		
		lblStatut = new JLabel("Statut");
		lblStatut.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblStatut.setBounds(0, 436, 46, 14);
		panel.add(lblStatut);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"En poste", "Congé maladie", "Vacance", "Retraite"}));
		comboBox.setBounds(55, 432, 98, 24);
		panel.add(comboBox);
		lblPhoneError.setVisible(false);

		lblMailError.setVisible(false);

		separator = new JSeparator();
		separator.setForeground(Color.LIGHT_GRAY);
		separator.setBounds(0, 82, frame.getWidth(), 2);
		frame.getContentPane().add(separator);
		
		panel_1 = new JPanel();
		panel_1.setOpaque(false);
		panel_1.setBounds(380, 96, 296, 187);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);

		lblVille_1 = new JLabel("Date d'entrée dans l'agence");
		lblVille_1.setBounds(11, 0, 222, 17);
		panel_1.add(lblVille_1);
		lblVille_1.setFont(new Font("Tahoma", Font.BOLD, 14));

		JCalendar calendar = new JCalendar();
		calendar.setBounds(0, 28, 296, 159);
		panel_1.add(calendar);

		JLabel lblRetour = new JLabel("Retour");
		lblRetour.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.dispose();
			}
		});
		lblRetour.setIcon(new ImageIcon(Vue_CreationAgent.class.getResource("/img/back.png")));
		lblRetour.setVerticalTextPosition(SwingConstants.BOTTOM);
		lblRetour.setHorizontalTextPosition(SwingConstants.CENTER);
		lblRetour.setBounds(12, 10, 48, 67);
		frame.getContentPane().add(lblRetour);

		JLabel lblNewLabel_1 = new JLabel("Création de nouvel agent immobilier");
		lblNewLabel_1.setBounds(376, 31, 229, 15);
		frame.getContentPane().add(lblNewLabel_1);
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				Database.Connect();
				lblMailError.setVisible(false);
				lblPhoneError.setVisible(false);
				String nom = txtNom.getText();
				String prenom = txtPrenom.getText();
				String adresse = txtAdresse.getText();
				String ville = txtVille.getText();
				String cp = txtCodePostale.getText();
				String tel = Checker
						.phonenumber(comboboxidentifiant.getSelectedItem().toString() + txtNumero.getText());
				String mois = String.valueOf(calendar.getMonthChooser().getMonth() + 1);
				String jour = String.valueOf(calendar.getDayChooser().getDay());
				String annee = String.valueOf(calendar.getYearChooser().getYear());
				String date = annee + "-" + mois + "-" + jour;
				String mail = txtAdresseMail.getText() + "@" + txtDomaine.getText();

				if (prenom.equals("Prénom")) {
					txtPrenom.setForeground(Color.red);
					JOptionPane.showMessageDialog(null, "Veuillez remplir tous les champs");
				} else if (nom.equals("Nom")) {
					txtNom.setForeground(Color.red);
					JOptionPane.showMessageDialog(null, "Veuillez remplir tous les champs");
				} else if (txtAdresseMail.getText().equals("Adresse Mail")) {
					txtAdresseMail.setForeground(Color.red);
					JOptionPane.showMessageDialog(null, "Veuillez remplir tous les champs");
				} else if (txtDomaine.getText().equals("Domaine")) {
					txtDomaine.setForeground(Color.red);
					JOptionPane.showMessageDialog(null, "Veuillez remplir tous les champs");
				} else if (adresse.equals("Adresse")) {
					txtAdresse.setForeground(Color.red);
					JOptionPane.showMessageDialog(null, "Veuillez remplir tous les champs");
				} else if (ville.equals("Ville")) {
					txtVille.setForeground(Color.red);
					JOptionPane.showMessageDialog(null, "Veuillez remplir tous les champs");
				} else if (cp.equals("Code Postal")) {
					txtCodePostale.setForeground(Color.red);
					JOptionPane.showMessageDialog(null, "Veuillez remplir tous les champs");
				} else if (txtNumero.getText().equals("Numero")) {
					txtNumero.setForeground(Color.red);
					JOptionPane.showMessageDialog(null, "Veuillez remplir tous les champs");
				} else if (!Checker.mailcheckera(mail)) {
					lblMailError.setVisible(true);
					JOptionPane.showMessageDialog(null, "Adresse mail invalide");
				} else if (!Checker.phonecheckera(tel)) {
					lblPhoneError.setVisible(true);
					JOptionPane.showMessageDialog(null, "Numéro de téléphone déjà enregistré ou invalide");
				} else {
					String identifiant = nom.toLowerCase().substring(0, 3) + "-" + prenom.toLowerCase().substring(0, 3)
							+ Math.round(Math.random() * 100);
					Password password = Password.createPassword(PasswordType.ALPHANUMERIC, 5, 5);
					String mdp = password.generate();
					AgentDAO agentDAO = new AgentDAO();
					Agent agent = new Agent();
					agent.setStatut("Employé");
					agent.setAdresse(adresse);
					agent.setCp(cp);
					agent.setMail(mail);
					agent.setDateentree(date);
					agent.setNom(nom);
					agent.setPrenom(prenom);
					agent.setTel(tel);
					agent.setVille(ville);
					agent.setIdentifiant(identifiant);
					agent.setMdp(mdp);
					agentDAO.save(agent);
					JOptionPane.showMessageDialog(null, "Le nouvel agent immobilier a bien été enregistré.\n"
							+ "Identifiant : " + identifiant + "\n" + "MDP : " + mdp);

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
