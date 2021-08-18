package vue;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.FlowLayout;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.GridLayout;
import java.awt.Font;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JSeparator;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JSpinner;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CreationProprietaire {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreationProprietaire window = new CreationProprietaire();
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
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JLabel lblPays;
	private JTextField txtAdresse;
	private JTextField txtVille;
	private JTextField txtCodePostale;
	private JLabel lblVille_1;
	private JTextField txtNumero;

	/**
	 * Create the application.
	 */
	public CreationProprietaire() {
		initialize();

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 347, 747);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);

		JButton btnNewButton = new JButton("Confirmer");

		btnNewButton.setBounds(194, 599, 112, 23);
		frame.getContentPane().add(btnNewButton);

		JLabel lblNewLabel = new JLabel("Nom");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(10, 139, 46, 14);
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
		txtPrenom.setBounds(10, 164, 100, 20);
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
		txtNom.setBounds(132, 164, 100, 20);
		frame.getContentPane().add(txtNom);

		separator = new JSeparator();
		separator.setForeground(Color.LIGHT_GRAY);
		separator.setBounds(0, 126, frame.getWidth(), 2);
		frame.getContentPane().add(separator);

		JLabel lblMail = new JLabel("Mail");
		lblMail.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblMail.setBounds(10, 195, 46, 14);
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
		txtAdresseMail.setBounds(10, 220, 134, 20);
		frame.getContentPane().add(txtAdresseMail);

		JLabel lblNewLabel_1_1 = new JLabel("@");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_1.setBounds(150, 220, 13, 17);
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
		txtDomaine.setBounds(172, 220, 134, 20);
		frame.getContentPane().add(txtDomaine);

		lblNewLabel_1 = new JLabel("Nom de domaine invalide");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblNewLabel_1.setForeground(Color.RED);
		lblNewLabel_1.setBounds(176, 239, 118, 14);
		frame.getContentPane().add(lblNewLabel_1);
		lblNewLabel_1.setVisible(false);

		lblNewLabel_2 = new JLabel("Adresse mail déjà enregistré");
		lblNewLabel_2.setForeground(Color.RED);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblNewLabel_2.setBounds(10, 239, 124, 13);
		frame.getContentPane().add(lblNewLabel_2);

		lblPays = new JLabel("Pays");
		lblPays.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblPays.setBounds(10, 263, 32, 17);
		frame.getContentPane().add(lblPays);

		JComboBox comboboxPays = new JComboBox();
		comboboxPays.setModel(new DefaultComboBoxModel(new String[] { "Allemagne", "Autriche", "Belgique", "Bulgarie",
				"Chypre", "Croatie", "Danemark", "Espagne", "Estonie", "Finlande", "France", "Grèce", "Hongrie",
				"Irlande", "Italie", "Lettonie", "Lituanie", "Luxembourg", "Malte", "Pays-Bas", "Pologne", "Portugal",
				"Roumanie", "Slovaquie", "Slovénie", "Suède", "Tchéquie" }));
		comboboxPays.setBounds(10, 290, 153, 22);
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
		txtAdresse.setBounds(10, 351, 296, 20);
		frame.getContentPane().add(txtAdresse);

		JLabel lblVille = new JLabel("Adresse");
		lblVille.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblVille.setBounds(10, 323, 55, 17);
		frame.getContentPane().add(lblVille);

		JLabel lblVille_2 = new JLabel("Ville");
		lblVille_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblVille_2.setBounds(10, 382, 27, 17);
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
		txtVille.setBounds(10, 410, 181, 20);
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
		txtCodePostale.setBounds(201, 410, 105, 20);
		frame.getContentPane().add(txtCodePostale);

		lblVille_1 = new JLabel("Date de naissance");
		lblVille_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblVille_1.setBounds(10, 441, 123, 17);
		frame.getContentPane().add(lblVille_1);

		JComboBox comboboxJour = new JComboBox();
		comboboxJour.setModel(new DefaultComboBoxModel(
				new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16",
						"17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));
		comboboxJour.setBounds(10, 469, 46, 22);
		frame.getContentPane().add(comboboxJour);

		JComboBox comboboxMois = new JComboBox();
		comboboxMois.setModel(new DefaultComboBoxModel(new String[] { "Janvier", "Février", "Mars", "Avril", "Mai",
				"Juin", "Juillet", "Août", "Septembre", "Octobre", "Novembre", "Décembre" }));
		comboboxMois.setBounds(66, 469, 97, 22);
		frame.getContentPane().add(comboboxMois);

		JComboBox comboboxAnnee = new JComboBox();
		comboboxAnnee.setModel(new DefaultComboBoxModel(new String[] { "2003", "2002", "2001", "2000", "1999", "1998",
				"1997", "1996", "1995", "1994", "1993", "1992", "1991", "1990", "1989", "1988", "1987", "1986", "1985",
				"1984", "1983", "1982", "1981", "1980", "1979", "1978", "1977", "1976", "1975", "1974", "1973", "1972",
				"1971", "1970", "1969", "1968", "1967", "1966", "1965", "1964", "1963", "1962", "1961", "1960", "1959",
				"1958", "1957", "1956", "1955", "1954", "1953", "1952", "1951", "1950", "1949", "1948", "1947", "1946",
				"1945", "1944", "1943", "1942", "1941", "1940", "1939", "1938", "1937", "1936", "1935", "1934", "1933",
				"1932", "1931", "1930", "1929", "1928", "1927", "1926", "1925", "1924", "1923", "1922", "1921", "1920",
				"1919", "1918", "1917", "1916", "1915", "1914", "1913", "1912", "1911", "1910", "1909", "1908", "1907",
				"1906", "1905", "1904", "1903", "1902", "1901", "1900" }));
		comboboxAnnee.setBounds(172, 469, 60, 22);
		frame.getContentPane().add(comboboxAnnee);

		JLabel lblNewLabel_3 = new JLabel("Date de naissance non valide");
		lblNewLabel_3.setForeground(Color.RED);
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblNewLabel_3.setBounds(10, 491, 181, 14);
		lblNewLabel_3.setVisible(false);
		frame.getContentPane().add(lblNewLabel_3);

		JComboBox comboboxidentifiant = new JComboBox();
		comboboxidentifiant.setModel(new DefaultComboBoxModel(new String[] {"AT +43", "BE +32", "BG +359", "CY +357", "CZ +420", "DE +49", "DK +45", "EE +372", "EL +30", "ES +34", "FI +358", "FR +33", "GI +350", "HR +385", "HU +36", "IE +353", "IS +354", "IT +39", "LI +423", "LT +370", "LUX +352", "LV +371", "MT +356", "NL +31", "NO +47", "PL +48", "PT +351", "RO +40", "SE +46", "SI +386", "SK +421", "UK+44"}));
		comboboxidentifiant.setBounds(10, 540, 73, 22);
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
		txtNumero.setBounds(86, 540, 116, 22);
		frame.getContentPane().add(txtNumero);

		JLabel lblNewLabel_4 = new JLabel("Numéro de telephone");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_4.setBounds(10, 516, 192, 14);
		frame.getContentPane().add(lblNewLabel_4);

		lblNewLabel_2.setVisible(false);

		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nom = txtNom.getText();
				String prenom = txtPrenom.getText();
				String adresse = txtAdresse.getText();
				String ville = txtVille.getText();
				String cp = txtCodePostale.getText();
				String pays = comboboxPays.getItemAt(comboboxPays.getSelectedIndex()).toString();
				String tel = comboboxidentifiant.getSelectedItem().toString()+txtNumero;
				String datedenaissance=comboboxJour.getSelectedItem().toString()+"/"+comboboxMois.getSelectedItem().toString()+"/"+comboboxAnnee.getSelectedItem().toString();
				String mail=txtAdresseMail.getText()+"@"+txtDomaine.getText();
			}
		});

	}
}
