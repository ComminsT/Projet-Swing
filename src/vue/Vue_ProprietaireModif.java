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
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

import com.toedter.calendar.JCalendar;

import dao.HistoriqueDAO;
import dao.ProprietaireDAO;
import entite.Agent;
import entite.Checker;
import entite.Database;
import entite.Historique;
import entite.Proprietaire;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Cursor;

public class Vue_ProprietaireModif {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					Vue_ProprietaireModif window = new Vue_ProprietaireModif();
					window.frmModificationDesInformations.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private JFrame frmModificationDesInformations;
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
	private Proprietaire proprietaire;
	private Agent agent;

	/**
	 * Create the application.
	 */
	public Vue_ProprietaireModif() {
		initialize();

	}

	public Vue_ProprietaireModif(Proprietaire proprietaire, Agent agent) {
		this.proprietaire = proprietaire;
		this.agent = agent;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		frmModificationDesInformations = new JFrame();
		frmModificationDesInformations.setTitle("Modification des informations du propri??taire");
		frmModificationDesInformations.setBounds(100, 100, 973, 630);
		frmModificationDesInformations.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		frmModificationDesInformations.getContentPane().setLayout(null);
		frmModificationDesInformations.setResizable(false);
		frmModificationDesInformations.setLocationRelativeTo(null);

		JLabel btnNewButton = new JLabel("Confirmer");
		btnNewButton.setHorizontalTextPosition(SwingConstants.CENTER);
		btnNewButton.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnNewButton.setHorizontalAlignment(SwingConstants.CENTER);
		btnNewButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		btnNewButton.setIcon(new ImageIcon(Vue_ProprietaireModif.class.getResource("/img/valider.png")));

		btnNewButton.setBounds(895, 11, 57, 68);
		frmModificationDesInformations.getContentPane().add(btnNewButton);

		JLabel lblNewLabel = new JLabel("Nom :");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(10, 95, 103, 14);
		frmModificationDesInformations.getContentPane().add(lblNewLabel);

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
		txtPrenom.setForeground(Color.BLACK);
		txtPrenom.setText(proprietaire.getPrenom());
		txtPrenom.setBounds(123, 126, 134, 20);
		frmModificationDesInformations.getContentPane().add(txtPrenom);
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
		txtNom.setForeground(Color.BLACK);
		txtNom.setText(proprietaire.getNom());
		txtNom.setColumns(10);
		txtNom.setBounds(123, 95, 134, 20);
		frmModificationDesInformations.getContentPane().add(txtNom);

		separator = new JSeparator();
		separator.setForeground(Color.LIGHT_GRAY);
		separator.setBounds(0, 82, frmModificationDesInformations.getWidth(), 2);
		frmModificationDesInformations.getContentPane().add(separator);

		JLabel lblMail = new JLabel("Mail :");
		lblMail.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMail.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblMail.setBounds(24, 151, 89, 14);
		frmModificationDesInformations.getContentPane().add(lblMail);

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
		String[] mail = proprietaire.getMail().split("@");
		txtAdresseMail.setText(mail[0]);
		txtAdresseMail.setForeground(Color.BLACK);
		txtAdresseMail.setColumns(10);
		txtAdresseMail.setBounds(123, 151, 134, 20);
		frmModificationDesInformations.getContentPane().add(txtAdresseMail);

		JLabel lblNewLabel_1_1 = new JLabel("@");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_1.setBounds(260, 151, 13, 17);
		frmModificationDesInformations.getContentPane().add(lblNewLabel_1_1);

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
		txtDomaine.setText(mail[1]);
		txtDomaine.setForeground(Color.BLACK);
		txtDomaine.setColumns(10);
		txtDomaine.setBounds(279, 151, 134, 20);
		frmModificationDesInformations.getContentPane().add(txtDomaine);

		lblMailError = new JLabel("Adresse mail invalide ou d??j?? enregistr??e");
		lblMailError.setForeground(Color.RED);
		lblMailError.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblMailError.setBounds(123, 175, 252, 13);
		frmModificationDesInformations.getContentPane().add(lblMailError);

		lblPays = new JLabel("Pays :");
		lblPays.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPays.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblPays.setBounds(24, 199, 89, 17);
		frmModificationDesInformations.getContentPane().add(lblPays);

		JComboBox<String> comboboxPays = new JComboBox<>();
		comboboxPays.setModel(new DefaultComboBoxModel<>(new String[] { "Allemagne", "Autriche", "Belgique", "Bulgarie",
				"Chypre", "Croatie", "Danemark", "Espagne", "Estonie", "Finlande", "France", "Gr??ce", "Hongrie",
				"Irlande", "Italie", "Lettonie", "Lituanie", "Luxembourg", "Malte", "Pays-Bas", "Pologne", "Portugal",
				"Roumanie", "Slovaquie", "Slov??nie", "Su??de", "Tch??quie" }));
		comboboxPays.setBounds(123, 199, 153, 22);
		frmModificationDesInformations.getContentPane().add(comboboxPays);
		comboboxPays.setSelectedItem(proprietaire.getPays());

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
		txtAdresse.setText(proprietaire.getAdresse());
		txtAdresse.setForeground(Color.BLACK);
		txtAdresse.setColumns(10);
		txtAdresse.setBounds(123, 228, 296, 20);
		frmModificationDesInformations.getContentPane().add(txtAdresse);

		JLabel lblVille = new JLabel("Adresse :");
		lblVille.setHorizontalAlignment(SwingConstants.RIGHT);
		lblVille.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblVille.setBounds(24, 227, 89, 17);
		frmModificationDesInformations.getContentPane().add(lblVille);

		JLabel lblVille_2 = new JLabel("Ville :");
		lblVille_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblVille_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblVille_2.setBounds(24, 255, 89, 17);
		frmModificationDesInformations.getContentPane().add(lblVille_2);

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
		txtVille.setText(proprietaire.getVille());
		txtVille.setForeground(Color.BLACK);
		txtVille.setColumns(10);
		txtVille.setBounds(123, 256, 181, 20);
		frmModificationDesInformations.getContentPane().add(txtVille);

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
		txtCodePostale.setText(proprietaire.getCp());
		txtCodePostale.setForeground(Color.BLACK);
		txtCodePostale.setColumns(10);
		txtCodePostale.setBounds(123, 283, 105, 20);
		frmModificationDesInformations.getContentPane().add(txtCodePostale);

		lblVille_1 = new JLabel("Date de naissance");
		lblVille_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblVille_1.setBounds(10, 372, 125, 17);
		frmModificationDesInformations.getContentPane().add(lblVille_1);
		String phone2 = proprietaire.getTel().substring(proprietaire.getTel().length()-9,proprietaire.getTel().length());
		String phoneid=proprietaire.getTel().substring(0,proprietaire.getTel().length()-9);
		JComboBox<String> comboboxidentifiant = new JComboBox<>();
		comboboxidentifiant.setModel(new DefaultComboBoxModel<>(new String []  { "AT +43", "BE +32", "BG +359", "CY +357",
				"CZ +420", "DE +49", "DK +45", "EE +372", "EL +30", "ES +34", "FI +358", "FR +33", "GI +350", "HR +385",
				"HU +36", "IE +353", "IS +354", "IT +39", "LI +423", "LT +370", "LUX +352", "LV +371", "MT +356",
				"NL +31", "NO +47", "PL +48", "PT +351", "RO +40", "SE +46", "SI +386", "SK +421", "UK+44" }));
		comboboxidentifiant.setBounds(10, 333, 73, 22);
		String[] id=new String[] {"AT +43", "BE +32", "BG +359", "CY +357",
				"CZ +420", "DE +49", "DK +45", "EE +372", "EL +30", "ES +34", "FI +358", "FR +33", "GI +350", "HR +385",
				"HU +36", "IE +353", "IS +354", "IT +39", "LI +423", "LT +370", "LUX +352", "LV +371", "MT +356",
				"NL +31", "NO +47", "PL +48", "PT +351", "RO +40", "SE +46", "SI +386", "SK +421", "UK+44"	
		};
		frmModificationDesInformations.getContentPane().add(comboboxidentifiant);
		int i=0;
		int memo=-1;
		for(String str: id ) {
			if(str.contains(phoneid)) {		
				memo=i;
			}
			i++;
		}
		comboboxidentifiant.setSelectedIndex(memo);

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
		txtNumero.setText(phone2);
		txtNumero.setForeground(Color.BLACK);
		txtNumero.setColumns(10);
		txtNumero.setBounds(93, 333, 139, 22);
		frmModificationDesInformations.getContentPane().add(txtNumero);

		JLabel lblNewLabel_4 = new JLabel("Num??ro de t??l??phone :");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_4.setBounds(10, 314, 170, 14);
		frmModificationDesInformations.getContentPane().add(lblNewLabel_4);

		

		String[]date=proprietaire.getNaissance().split("-");
		int annee=Integer.parseInt(date[0]);
		int mois=Integer.parseInt(date[1]);
		int jour=Integer.parseInt(date[2]);
		
		JCalendar calendar = new JCalendar();
		calendar.setBounds(10, 401, 294, 165);
		frmModificationDesInformations.getContentPane().add(calendar);
		calendar.getYearChooser().setYear(annee);
		calendar.getMonthChooser().setMonth(mois-1);
		calendar.getDayChooser().setDay(jour);
		

		JLabel lblPhoneError = new JLabel("Num??ro de t??l??phone d??j?? enregistr?? ou invalide");
		lblPhoneError.setForeground(Color.RED);
		lblPhoneError.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblPhoneError.setBounds(10, 358, 240, 14);
		frmModificationDesInformations.getContentPane().add(lblPhoneError);

		JLabel lblNewLabel_2 = new JLabel("Modifications des informations du propri??taire");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(352, 30, 262, 16);
		frmModificationDesInformations.getContentPane().add(lblNewLabel_2);

		JLabel lblPrnom = new JLabel("Pr??nom :");
		lblPrnom.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPrnom.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblPrnom.setBounds(10, 126, 103, 14);
		frmModificationDesInformations.getContentPane().add(lblPrnom);

		JLabel lblVille_2_1 = new JLabel("Code postal :");
		lblVille_2_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblVille_2_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblVille_2_1.setBounds(0, 283, 113, 17);
		frmModificationDesInformations.getContentPane().add(lblVille_2_1);

		JLabel btnAnnuler = new JLabel("Annuler");
		btnAnnuler.setHorizontalTextPosition(SwingConstants.CENTER);
		btnAnnuler.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnAnnuler.setToolTipText("");
		btnAnnuler.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAnnuler.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frmModificationDesInformations.dispose();
				new Vue_ProprietairesList(agent).getFrame().setVisible(true);
			}
		});
		btnAnnuler.setIcon(new ImageIcon(Vue_ProprietaireModif.class.getResource("/img/back.png")));
		btnAnnuler.setBounds(11, 11, 48, 68);
		frmModificationDesInformations.getContentPane().add(btnAnnuler);

		lblMailError.setVisible(false);
		lblPhoneError.setVisible(false);
		JLabel lblbackground = new JLabel("");
		lblbackground.setIcon(new ImageIcon(Vue_LocatairesList.class.getResource("/img/accueil_bg.jpeg")));
		lblbackground.setBounds(-26, -19, 1023, 636);
		frmModificationDesInformations.getContentPane().add(lblbackground);
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
				String pays = comboboxPays.getSelectedItem().toString();
				String tel = Checker
						.phonenumber(comboboxidentifiant.getSelectedItem().toString() + txtNumero.getText());
				String mois = String.valueOf(calendar.getMonthChooser().getMonth() + 1);
				String jour = String.valueOf(calendar.getDayChooser().getDay());
				String annee = String.valueOf(calendar.getYearChooser().getYear());
				String datedenaissance = annee + "-" + mois + "-" + jour;
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
				} else if (!Checker.mailmodifierp(mail,proprietaire)) {
					lblMailError.setVisible(true);
					JOptionPane.showMessageDialog(null, "Adresse mail invalide");
				} else if (!Checker.phonemodifierp(tel,proprietaire)) {
					lblPhoneError.setVisible(true);
					JOptionPane.showMessageDialog(null, "Num??ro de telephone invalide ou d??j?? utilis??");
				} else {
					ProprietaireDAO proprietaireDAO = new ProprietaireDAO();
					proprietaire.setAdresse(adresse);
					proprietaire.setCp(cp);
					proprietaire.setMail(mail);
					proprietaire.setNaissance(datedenaissance);
					proprietaire.setNom(nom);
					proprietaire.setPrenom(prenom);
					proprietaire.setTel(tel);
					proprietaire.setVille(ville);
					proprietaire.setPays(pays);
					proprietaireDAO.save(proprietaire);
					 HistoriqueDAO historiqueDAO = new HistoriqueDAO();
						Historique historique = new Historique();
						historique.setDate(Checker.getDateTime());
						historique.setId_agent(agent.getId());
						historique.setAction("Modification du proprietaire id : "+proprietaire.getId());
						historiqueDAO.save(historique);	
						frmModificationDesInformations.dispose();
						new Vue_ProprietairesList(agent).getFrame().setVisible(true);
				}
				
				
			}
		});
	}

	public JFrame getFrame() {
		return frmModificationDesInformations;
	}

	public void setFrame(JFrame frame) {
		this.frmModificationDesInformations = frame;
	}
}
