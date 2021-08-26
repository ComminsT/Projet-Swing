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

import dao.LocataireDAO;
import entite.Checker;
import entite.Database;
import entite.Locataire;
import com.toedter.calendar.JCalendar;

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

	/**
	 * Create the application.
	 */
	public Vue_CreationLocataire() {
		initialize();

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		frame = new JFrame();
		frame.setBounds(100, 100, 534, 693);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);

		JButton btnNewButton = new JButton("Confirmer");

		btnNewButton.setBounds(390, 586, 113, 53);
		frame.getContentPane().add(btnNewButton);

		JLabel lblNewLabel = new JLabel("Nom");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(115, 96, 46, 14);
		frame.getContentPane().add(lblNewLabel);

		txtPrenom = new JTextField();
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
		txtPrenom.setBounds(115, 121, 134, 20);
		frame.getContentPane().add(txtPrenom);
		txtPrenom.setColumns(10);

		txtNom = new JTextField();
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
		txtNom.setBounds(277, 121, 134, 20);
		frame.getContentPane().add(txtNom);

		separator = new JSeparator();
		separator.setForeground(Color.LIGHT_GRAY);
		separator.setBounds(0, 82, frame.getWidth(), 2);
		frame.getContentPane().add(separator);

		JLabel lblMail = new JLabel("Mail");
		lblMail.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblMail.setBounds(115, 152, 46, 14);
		frame.getContentPane().add(lblMail);

		txtAdresseMail = new JTextField();
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
		txtAdresseMail.setBounds(115, 177, 134, 20);
		frame.getContentPane().add(txtAdresseMail);

		JLabel lblNewLabel_1_1 = new JLabel("@");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_1.setBounds(255, 177, 13, 17);
		frame.getContentPane().add(lblNewLabel_1_1);

		txtDomaine = new JTextField();
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
		txtDomaine.setBounds(277, 177, 134, 20);
		frame.getContentPane().add(txtDomaine);

		lblMailError = new JLabel("Adresse mail invalide ou déjà enregistrée");
		lblMailError.setForeground(Color.RED);
		lblMailError.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblMailError.setBounds(115, 195, 252, 13);
		frame.getContentPane().add(lblMailError);

		lblPays = new JLabel("Pays");
		lblPays.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblPays.setBounds(115, 219, 32, 17);
		frame.getContentPane().add(lblPays);

		JComboBox<String> comboboxPays = new JComboBox<>();
		comboboxPays.setModel(new DefaultComboBoxModel<>(new String[] { "Allemagne", "Autriche", "Belgique", "Bulgarie",
				"Chypre", "Croatie", "Danemark", "Espagne", "Estonie", "Finlande", "France", "Grèce", "Hongrie",
				"Irlande", "Italie", "Lettonie", "Lituanie", "Luxembourg", "Malte", "Pays-Bas", "Pologne", "Portugal",
				"Roumanie", "Slovaquie", "Slovénie", "Suède", "Tchéquie" }));
		comboboxPays.setBounds(115, 246, 153, 22);
		frame.getContentPane().add(comboboxPays);

		txtAdresse = new JTextField();
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
		txtAdresse.setBounds(115, 307, 296, 20);
		frame.getContentPane().add(txtAdresse);

		JLabel lblVille = new JLabel("Adresse");
		lblVille.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblVille.setBounds(115, 279, 55, 17);
		frame.getContentPane().add(lblVille);

		JLabel lblVille_2 = new JLabel("Ville");
		lblVille_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblVille_2.setBounds(115, 338, 27, 17);
		frame.getContentPane().add(lblVille_2);

		txtVille = new JTextField();
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
		txtVille.setBounds(115, 366, 181, 20);
		frame.getContentPane().add(txtVille);

		txtCodePostale = new JTextField();
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
				if (txtCodePostale.getText().equals("Code Postale")) {
					txtCodePostale.setText("");
					txtCodePostale.setForeground(Color.black);
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (txtCodePostale.getText().equals("")) {
					txtCodePostale.setText("Code Postale");
					txtCodePostale.setForeground(Color.LIGHT_GRAY);
				}
			}
		});
		txtCodePostale.setText("Code Postale");
		txtCodePostale.setForeground(Color.LIGHT_GRAY);
		txtCodePostale.setColumns(10);
		txtCodePostale.setBounds(306, 366, 105, 20);
		frame.getContentPane().add(txtCodePostale);

		lblVille_1 = new JLabel("Date de naissance");
		lblVille_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblVille_1.setBounds(24, 397, 123, 17);
		frame.getContentPane().add(lblVille_1);

		JComboBox<String> comboboxidentifiant = new JComboBox<>();
		comboboxidentifiant.setModel(new DefaultComboBoxModel<>(new String[] { "AT +43", "BE +32", "BG +359", "CY +357",
				"CZ +420", "DE +49", "DK +45", "EE +372", "EL +30", "ES +34", "FI +358", "FR +33", "GI +350", "HR +385",
				"HU +36", "IE +353", "IS +354", "IT +39", "LI +423", "LT +370", "LUX +352", "LV +371", "MT +356",
				"NL +31", "NO +47", "PL +48", "PT +351", "RO +40", "SE +46", "SI +386", "SK +421", "UK+44" }));
		comboboxidentifiant.setBounds(239, 421, 73, 22);
		frame.getContentPane().add(comboboxidentifiant);

		txtNumero = new JTextField();
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
		txtNumero.setBounds(315, 421, 139, 22);
		frame.getContentPane().add(txtNumero);

		JLabel lblNewLabel_4 = new JLabel("Numéro de telephone");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_4.setBounds(239, 397, 192, 14);
		frame.getContentPane().add(lblNewLabel_4);

		JLabel lblNewLabel_4_1 = new JLabel("Situation");
		lblNewLabel_4_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_4_1.setBounds(239, 454, 100, 14);
		frame.getContentPane().add(lblNewLabel_4_1);

		JComboBox<String> comboboxSituation = new JComboBox<String>();
		comboboxSituation.setModel(new DefaultComboBoxModel<String>(new String[] { "Etudiant", "Employé CDI",
				"Employé CDD", "Indépendant", "Retraité", "En recherche d'emploi", "Autre" }));
		comboboxSituation.setBounds(239, 479, 100, 22);
		frame.getContentPane().add(comboboxSituation);

		JLabel lblNewLabel_4_1_1 = new JLabel("Statut");
		lblNewLabel_4_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_4_1_1.setBounds(349, 454, 100, 14);
		frame.getContentPane().add(lblNewLabel_4_1_1);

		JComboBox<String> comboboxStatut = new JComboBox<String>();
		comboboxStatut.setModel(
				new DefaultComboBoxModel<String>(new String[] { "Locataire", "Ex-Locataire", "Expulsé", "Autre" }));
		comboboxStatut.setBounds(349, 479, 100, 22);
		frame.getContentPane().add(comboboxStatut);

		JCalendar calendar = new JCalendar();
		calendar.setBounds(24, 421, 205, 153);
		frame.getContentPane().add(calendar);

		JLabel lblNewLabel_1 = new JLabel("Numéro de téléphone déjà enregistré ou invalide");
		lblNewLabel_1.setForeground(Color.RED);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblNewLabel_1.setBounds(239, 442, 240, 14);
		frame.getContentPane().add(lblNewLabel_1);

		lblMailError.setVisible(false);

		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
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
				} else if (cp.equals("Code Postale")) {
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
					locataireDAO.save(locataire);

					int input = JOptionPane.showConfirmDialog(null, "Le nouveau locataire a bien été enregistré.\n"
							+ "Souhaitez vous lui attribuer un logement immédiatement ?");
					if (input == 0) {
						// Fenêtre création de contratl
					} else {
						System.out.println("Retour vueListeProprietaire");
						// fenêtre VueListeLocataire
					}
				}
			}
		});

	}

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}
}
