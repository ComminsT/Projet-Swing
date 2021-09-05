package vue;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
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

import com.toedter.calendar.JCalendar;

import dao.HistoriqueDAO;
import dao.LocataireDAO;
import entite.Agent;
import entite.Checker;
import entite.Database;
import entite.Historique;
import entite.Locataire;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.awt.Cursor;
import javax.swing.JPanel;

public class Vue_CreationLocataire {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					Vue_CreationLocataire window = new Vue_CreationLocataire();
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
	private JLabel lblPays;
	private JTextField txtAdresse;
	private JTextField txtVille;
	private JTextField txtCodePostale;
	private JLabel lblVille_1;
	private JTextField txtNumero;
	private Agent agent;

	/**
	 * Create the application.
	 */
	public Vue_CreationLocataire() {
		initialize();
	}

	public Vue_CreationLocataire(Agent agent) {
		this.agent = agent;
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
		
		JLabel lblNewLabel_2 = new JLabel("Ajout nouveau locataire");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2.setBounds(421, 30, 133, 16);
		frame.getContentPane().add(lblNewLabel_2);

		JLabel btnNewButton = new JLabel("Confirmer");

		btnNewButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton.setHorizontalTextPosition(SwingConstants.CENTER);
		btnNewButton.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnNewButton.setIcon(new ImageIcon(Vue_CreationLocataire.class.getResource("/img/valider.png")));

		btnNewButton.setBounds(913, 11, 57, 68);
		frame.getContentPane().add(btnNewButton);

		JPanel panel_8 = new JPanel();
		panel_8.setOpaque(false);
		panel_8.setBounds(24, 95, 296, 485);
		frame.getContentPane().add(panel_8);
		panel_8.setLayout(null);

		JPanel panel = new JPanel();
		panel.setOpaque(false);
		panel.setBounds(0, 0, 296, 45);
		panel_8.add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Nom");
		lblNewLabel.setBounds(0, 0, 46, 14);
		panel.add(lblNewLabel);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));

		txtPrenom = new JTextField();
		txtPrenom.setBounds(0, 25, 134, 20);
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
		txtNom.setBounds(162, 25, 134, 20);
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

		JPanel panel_1 = new JPanel();
		panel_1.setOpaque(false);
		panel_1.setBounds(0, 53, 296, 56);
		panel_8.add(panel_1);
		panel_1.setLayout(null);

		JLabel lblMail = new JLabel("Mail");
		lblMail.setBounds(0, 0, 46, 14);
		panel_1.add(lblMail);
		lblMail.setFont(new Font("Tahoma", Font.BOLD, 14));

		txtAdresseMail = new JTextField();
		txtAdresseMail.setBounds(0, 25, 134, 20);
		panel_1.add(txtAdresseMail);
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
		lblNewLabel_1_1.setBounds(140, 25, 13, 17);
		panel_1.add(lblNewLabel_1_1);
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));

		txtDomaine = new JTextField();
		txtDomaine.setBounds(162, 25, 134, 20);
		panel_1.add(txtDomaine);
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
		lblMailError.setBounds(0, 43, 252, 13);
		panel_1.add(lblMailError);
		lblMailError.setForeground(Color.RED);
		lblMailError.setFont(new Font("Tahoma", Font.PLAIN, 10));

		JPanel panel_2 = new JPanel();
		panel_2.setOpaque(false);
		panel_2.setBounds(0, 117, 153, 49);
		panel_8.add(panel_2);
		panel_2.setLayout(null);

		lblPays = new JLabel("Pays");
		lblPays.setBounds(0, 0, 32, 17);
		panel_2.add(lblPays);
		lblPays.setFont(new Font("Tahoma", Font.BOLD, 14));

		JComboBox<String> comboboxPays = new JComboBox<>();
		comboboxPays.setBounds(0, 27, 153, 22);
		panel_2.add(comboboxPays);
		comboboxPays.setModel(new DefaultComboBoxModel<>(new String[] { "Allemagne", "Autriche", "Belgique", "Bulgarie",
				"Chypre", "Croatie", "Danemark", "Espagne", "Estonie", "Finlande", "France", "Grèce", "Hongrie",
				"Irlande", "Italie", "Lettonie", "Lituanie", "Luxembourg", "Malte", "Pays-Bas", "Pologne", "Portugal",
				"Roumanie", "Slovaquie", "Slovénie", "Suède", "Tchéquie" }));

		JPanel panel_3 = new JPanel();
		panel_3.setOpaque(false);
		panel_3.setBounds(0, 174, 296, 48);
		panel_8.add(panel_3);
		panel_3.setLayout(null);

		txtAdresse = new JTextField();
		txtAdresse.setBounds(0, 28, 296, 20);
		panel_3.add(txtAdresse);
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
		lblVille.setBounds(0, 0, 55, 17);
		panel_3.add(lblVille);
		lblVille.setFont(new Font("Tahoma", Font.BOLD, 14));

		JPanel panel_4 = new JPanel();
		panel_4.setOpaque(false);
		panel_4.setBounds(0, 230, 296, 48);
		panel_8.add(panel_4);
		panel_4.setLayout(null);

		JLabel lblVille_2 = new JLabel("Ville");
		lblVille_2.setBounds(0, 0, 27, 17);
		panel_4.add(lblVille_2);
		lblVille_2.setFont(new Font("Tahoma", Font.BOLD, 14));

		txtVille = new JTextField();
		txtVille.setBounds(0, 28, 181, 20);
		panel_4.add(txtVille);
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
		txtCodePostale.setBounds(191, 28, 105, 20);
		panel_4.add(txtCodePostale);
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

		JPanel panel_6 = new JPanel();
		panel_6.setOpaque(false);
		panel_6.setBounds(0, 286, 240, 59);
		panel_8.add(panel_6);
		panel_6.setLayout(null);

		JComboBox<String> comboboxidentifiant = new JComboBox<>();
		comboboxidentifiant.setBounds(0, 24, 73, 22);
		panel_6.add(comboboxidentifiant);
		comboboxidentifiant.setModel(new DefaultComboBoxModel<>(new String[] { "AT +43", "BE +32", "BG +359", "CY +357",
				"CZ +420", "DE +49", "DK +45", "EE +372", "EL +30", "ES +34", "FI +358", "FR +33", "GI +350", "HR +385",
				"HU +36", "IE +353", "IS +354", "IT +39", "LI +423", "LT +370", "LUX +352", "LV +371", "MT +356",
				"NL +31", "NO +47", "PL +48", "PT +351", "RO +40", "SE +46", "SI +386", "SK +421", "UK+44" }));

		txtNumero = new JTextField();
		txtNumero.setBounds(76, 24, 139, 22);
		panel_6.add(txtNumero);
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
		lblNewLabel_4.setBounds(0, 0, 192, 14);
		panel_6.add(lblNewLabel_4);
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 14));

		JLabel lblNewLabel_1 = new JLabel("Numéro de téléphone déjà enregistré ou invalide");
		lblNewLabel_1.setBounds(0, 45, 240, 14);
		panel_6.add(lblNewLabel_1);
		lblNewLabel_1.setForeground(Color.RED);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 10));

		JPanel panel_7 = new JPanel();
		panel_7.setOpaque(false);
		panel_7.setBounds(0, 353, 210, 47);
		panel_8.add(panel_7);
		panel_7.setLayout(null);

		JLabel lblNewLabel_4_1 = new JLabel("Situation");
		lblNewLabel_4_1.setBounds(0, 0, 100, 14);
		panel_7.add(lblNewLabel_4_1);
		lblNewLabel_4_1.setFont(new Font("Tahoma", Font.BOLD, 14));

		JComboBox<String> comboboxSituation = new JComboBox<String>();
		comboboxSituation.setBounds(0, 25, 100, 22);
		panel_7.add(comboboxSituation);
		comboboxSituation.setModel(new DefaultComboBoxModel<String>(new String[] { "Etudiant", "Employé CDI",
				"Employé CDD", "Indépendant", "Retraité", "En recherche d'emploi", "Autre" }));

		JLabel lblNewLabel_4_1_1 = new JLabel("Statut");
		lblNewLabel_4_1_1.setBounds(110, 0, 100, 14);
		panel_7.add(lblNewLabel_4_1_1);
		lblNewLabel_4_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));

		JComboBox<String> comboboxStatut = new JComboBox<String>();
		comboboxStatut.setBounds(110, 25, 100, 22);
		panel_7.add(comboboxStatut);
		comboboxStatut.setModel(
				new DefaultComboBoxModel<String>(new String[] { "Locataire", "Ex-Locataire", "Expulsé", "Autre" }));

		lblMailError.setVisible(false);

		separator = new JSeparator();
		separator.setForeground(Color.LIGHT_GRAY);
		separator.setBounds(0, 82, frame.getWidth(), 2);
		frame.getContentPane().add(separator);

		JPanel panel_5 = new JPanel();
		panel_5.setOpaque(false);
		panel_5.setBounds(365, 93, 219, 177);
		frame.getContentPane().add(panel_5);
		panel_5.setLayout(null);

		lblVille_1 = new JLabel("Date de naissance");
		lblVille_1.setBounds(14, 0, 125, 17);
		panel_5.add(lblVille_1);
		lblVille_1.setFont(new Font("Tahoma", Font.BOLD, 14));

		JCalendar calendar = new JCalendar();
		calendar.setBounds(0, 24, 219, 153);
		panel_5.add(calendar);

		JLabel btnRetour = new JLabel("Retour");
		btnRetour.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnRetour.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.dispose();
				new Vue_LocatairesList(agent).getFrame().setVisible(true);

			}
		});
		btnRetour.setHorizontalTextPosition(SwingConstants.CENTER);
		btnRetour.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnRetour.setIcon(new ImageIcon(Vue_CreationLocataire.class.getResource("/img/back.png")));
		btnRetour.setBounds(11, 11, 48, 68);
		frame.getContentPane().add(btnRetour);
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				Database.Connect();
				lblMailError.setVisible(false);
				String nom = txtNom.getText();
				String prenom = txtPrenom.getText();
				String adresse = txtAdresse.getText();
				String ville = txtVille.getText();
				String cp = txtCodePostale.getText();
				String pays = comboboxPays.getSelectedItem().toString();
				String tel = Checker
						.phonenumber(comboboxidentifiant.getSelectedItem().toString() + txtNumero.getText());
				String mois = String.valueOf(calendar.getMonthChooser().getMonth() + 1);
				String jour = String.valueOf(calendar.getDayChooser().getDay());
				String annee = String.valueOf(calendar.getYearChooser().getYear());
				String datedenaissance = annee + "-" + mois + "-" + jour;
				String mail = txtAdresseMail.getText() + "@" + txtDomaine.getText();
				String situation = comboboxSituation.getSelectedItem().toString();
				String statut = comboboxStatut.getSelectedItem().toString();

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
				} else if (!Checker.mailcheckerl(mail)) {
					lblMailError.setVisible(true);
					JOptionPane.showMessageDialog(null, "Adresse mail invalide");
				} else if (!Checker.phonecheckerl(tel)) {

				} else {
					LocataireDAO locataireDAO = new LocataireDAO();
					Locataire locataire = new Locataire();
					locataire.setAdresse(adresse);
					locataire.setCp(cp);
					locataire.setMail(mail);
					locataire.setNaissance(datedenaissance);
					locataire.setNom(nom);
					locataire.setPrenom(prenom);
					locataire.setTel(tel);
					locataire.setVille(ville);
					locataire.setPays(pays);
					locataire.setSituation(situation);
					locataire.setStatut(statut);
					locataire.setId_agent(agent.getId());
					locataireDAO.save(locataire);
					ArrayList<Locataire>locataires = locataireDAO.getAll();
					locataire=locataires.get(locataires.size()-1);
					 HistoriqueDAO historiqueDAO = new HistoriqueDAO();
						Historique historique = new Historique();
						historique.setDate(Checker.getDateTime());
						historique.setId_agent(agent.getId());
						historique.setAction("Ajout locataire id : "+ locataire.getId());
						historiqueDAO.save(historique);	

					int input = JOptionPane.showConfirmDialog(null, "Le nouveau locataire a bien été enregistré.\n"
							+ "Souhaitez vous lui attribuer un logement immédiatement ?");
					if (input == 0) {
						frame.dispose();
						new Vue_CreationContrat(agent).getFrame().setVisible(true);
					} else {
						frame.dispose();
						new Vue_LocatairesList(agent).getFrame().setVisible(true);
					}
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
