package vue;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

import dao.GarantDAO;
import entite.Agent;
import entite.Checker;
import entite.Database;
import entite.Garant;

public class Vue_CreationGarant {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					Vue_CreationGarant window = new Vue_CreationGarant();
					window.frmAjoutDunNouveau.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private JFrame frmAjoutDunNouveau;
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
	private JTextField txtNumero;
	private Agent agent;

	/**
	 * Create the application.
	 */
	public Vue_CreationGarant() {
		initialize();
	}
	public Vue_CreationGarant(Agent agent) {
		this.agent=agent;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		frmAjoutDunNouveau = new JFrame();
		frmAjoutDunNouveau.setTitle("Ajout d'un nouveau garant");
		frmAjoutDunNouveau.setBounds(100, 100, 436, 595);
		frmAjoutDunNouveau.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		frmAjoutDunNouveau.getContentPane().setLayout(null);
		frmAjoutDunNouveau.setResizable(false);
		frmAjoutDunNouveau.setLocationRelativeTo(null);

		JLabel btnNewButton = new JLabel("Confirmer");
		btnNewButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		btnNewButton.setHorizontalTextPosition(SwingConstants.CENTER);
		btnNewButton.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnNewButton.setIcon(new ImageIcon(Vue_CreationGarant.class.getResource("/img/valider.png")));

		btnNewButton.setBounds(361, 11, 57, 68);
		frmAjoutDunNouveau.getContentPane().add(btnNewButton);

		JLabel lblNewLabel = new JLabel("Nom");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(10, 95, 46, 14);
		frmAjoutDunNouveau.getContentPane().add(lblNewLabel);

		txtPrenom = new JTextField();
		txtPrenom.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if (txtPrenom.getText().equals("Pr??nom")) {
					txtPrenom.setText("");
					txtPrenom.setForeground(Color.black);
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (txtPrenom.getText().equals("")) {
					txtPrenom.setText("Pr??nom");
					txtPrenom.setForeground(Color.LIGHT_GRAY);
				}
			}
		});
		txtPrenom.setForeground(Color.LIGHT_GRAY);
		txtPrenom.setText("Pr??nom");
		txtPrenom.setBounds(10, 120, 134, 20);
		frmAjoutDunNouveau.getContentPane().add(txtPrenom);
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
		txtNom.setBounds(172, 120, 134, 20);
		frmAjoutDunNouveau.getContentPane().add(txtNom);

		separator = new JSeparator();
		separator.setForeground(Color.LIGHT_GRAY);
		separator.setBounds(0, 82, frmAjoutDunNouveau.getWidth(), 2);
		frmAjoutDunNouveau.getContentPane().add(separator);

		JLabel lblMail = new JLabel("Mail");
		lblMail.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblMail.setBounds(10, 151, 46, 14);
		frmAjoutDunNouveau.getContentPane().add(lblMail);

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
		txtAdresseMail.setBounds(10, 176, 134, 20);
		frmAjoutDunNouveau.getContentPane().add(txtAdresseMail);

		JLabel lblNewLabel_1_1 = new JLabel("@");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_1.setBounds(150, 176, 13, 17);
		frmAjoutDunNouveau.getContentPane().add(lblNewLabel_1_1);

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
		txtDomaine.setBounds(172, 176, 134, 20);
		frmAjoutDunNouveau.getContentPane().add(txtDomaine);

		lblMailError = new JLabel("Adresse mail invalide ou d??j?? enregistr??e");
		lblMailError.setForeground(Color.RED);
		lblMailError.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblMailError.setBounds(10, 194, 252, 13);
		frmAjoutDunNouveau.getContentPane().add(lblMailError);

		lblPays = new JLabel("Pays");
		lblPays.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblPays.setBounds(10, 218, 32, 17);
		frmAjoutDunNouveau.getContentPane().add(lblPays);

		JComboBox<String> comboboxPays = new JComboBox<>();
		comboboxPays.setModel(new DefaultComboBoxModel<>(new String[] { "Allemagne", "Autriche", "Belgique", "Bulgarie",
				"Chypre", "Croatie", "Danemark", "Espagne", "Estonie", "Finlande", "France", "Gr??ce", "Hongrie",
				"Irlande", "Italie", "Lettonie", "Lituanie", "Luxembourg", "Malte", "Pays-Bas", "Pologne", "Portugal",
				"Roumanie", "Slovaquie", "Slov??nie", "Su??de", "Tch??quie" }));
		comboboxPays.setBounds(10, 245, 153, 22);
		frmAjoutDunNouveau.getContentPane().add(comboboxPays);

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
		txtAdresse.setBounds(10, 306, 296, 20);
		frmAjoutDunNouveau.getContentPane().add(txtAdresse);

		JLabel lblVille = new JLabel("Adresse");
		lblVille.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblVille.setBounds(10, 278, 55, 17);
		frmAjoutDunNouveau.getContentPane().add(lblVille);

		JLabel lblVille_2 = new JLabel("Ville");
		lblVille_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblVille_2.setBounds(10, 337, 29, 17);
		frmAjoutDunNouveau.getContentPane().add(lblVille_2);

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
		txtVille.setBounds(10, 365, 181, 20);
		frmAjoutDunNouveau.getContentPane().add(txtVille);

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
		txtCodePostale.setBounds(201, 365, 105, 20);
		frmAjoutDunNouveau.getContentPane().add(txtCodePostale);

		JComboBox<String> comboboxidentifiant = new JComboBox<>();
		comboboxidentifiant.setModel(new DefaultComboBoxModel<>(new String[] { "AT +43", "BE +32", "BG +359", "CY +357",
				"CZ +420", "DE +49", "DK +45", "EE +372", "EL +30", "ES +34", "FI +358", "FR +33", "GI +350", "HR +385",
				"HU +36", "IE +353", "IS +354", "IT +39", "LI +423", "LT +370", "LUX +352", "LV +371", "MT +356",
				"NL +31", "NO +47", "PL +48", "PT +351", "RO +40", "SE +46", "SI +386", "SK +421", "UK+44" }));
		comboboxidentifiant.setBounds(10, 421, 118, 27);
		frmAjoutDunNouveau.getContentPane().add(comboboxidentifiant);

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
		txtNumero.setBounds(140, 422, 139, 22);
		frmAjoutDunNouveau.getContentPane().add(txtNumero);

		JLabel lblNewLabel_4 = new JLabel("Num??ro de t??l??phone");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_4.setBounds(10, 396, 192, 14);
		frmAjoutDunNouveau.getContentPane().add(lblNewLabel_4);
		
		JLabel lblNewLabel_2 = new JLabel("Ajout d'un nouveau garant");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(141, 30, 147, 16);
		frmAjoutDunNouveau.getContentPane().add(lblNewLabel_2);

		lblMailError.setVisible(false);
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
				String mail = txtAdresseMail.getText() + "@" + txtDomaine.getText();

				if (prenom.equals("Pr??nom")) {
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
				} else {
					GarantDAO garantDAO = new GarantDAO();
					Garant garant = new Garant();
					
					garant.setAdresse(adresse);
					garant.setCp(cp);
					garant.setMail(mail);
					garant.setNom(nom);
					garant.setPrenom(prenom);
					garant.setTel(tel);
					garant.setVille(ville);
					garant.setPays(pays);
					garantDAO.saveWithoutBien(garant);
					ArrayList<Garant>garants = garantDAO.getAll();
					garant=garants.get(garants.size()-1);
					JOptionPane.showMessageDialog(null, "Le nouveau garant a bien ??t?? enregistr??.\n");
					frmAjoutDunNouveau.dispose();
					Vue_ContratConfirmation.addToList(garant.toString(),garant);
					
				}
			
			}
		});
		JLabel lblBG = new JLabel("");
		lblBG.setOpaque(true);
		lblBG.setIcon(new ImageIcon(Vue_AccueilAgent.class.getResource("/img/accueil_bg.jpeg")));
		lblBG.setBounds(-16, 0, 1000, 591);
		frmAjoutDunNouveau.getContentPane().add(lblBG);

	}

	public JFrame getFrame() {
		return frmAjoutDunNouveau;
	}

	public void setFrame(JFrame frame) {
		this.frmAjoutDunNouveau = frame;
	}
}
