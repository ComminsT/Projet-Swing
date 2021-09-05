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
import java.io.File;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import com.toedter.calendar.JYearChooser;

import dao.BienDAO;
import dao.HistoriqueDAO;
import dao.ProprietaireDAO;
import entite.Agent;
import entite.Bien;
import entite.Checker;
import entite.Database;
import entite.Historique;
import entite.Proprietaire;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Vue_CreationBien {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					Vue_CreationBien window = new Vue_CreationBien();
					window.frmCrationDunBien.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private JFrame frmCrationDunBien;
	private JSeparator separator;
	private JTextField txtNom;
	private JLabel lblPays;
	private JTextField txtAdresse;
	private JTextField txtVille;
	private JTextField txtCodePostale;
	private JLabel lblVille_1;
	private JTextField txtValeur;
	private JTextField txtSurface;
	private Agent agent;

	/**
	 * Create the application.
	 */
	public Vue_CreationBien() {
		initialize();

	}
	public Vue_CreationBien(Agent agent) {
		this.agent=agent;
		initialize();
	}

	public JFrame getFrame() {
		return frmCrationDunBien;
	}
	public void setFrame(JFrame frame) {
		this.frmCrationDunBien = frame;
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
Database.Connect();
		frmCrationDunBien = new JFrame();
		frmCrationDunBien.setTitle("Création d'un bien immobilier");
		frmCrationDunBien.setBounds(100, 100, 981, 620);
		frmCrationDunBien.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frmCrationDunBien.getContentPane().setLayout(null);
		frmCrationDunBien.setResizable(false);
		frmCrationDunBien.setLocationRelativeTo(null);
		
		JLabel lblNewLabel_1 = new JLabel("Création d'un bien immobilier");
		lblNewLabel_1.setBounds(405, 30, 165, 16);
		frmCrationDunBien.getContentPane().add(lblNewLabel_1);

		JLabel btnNewButton = new JLabel("Confirmer");
		
		btnNewButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton.setHorizontalTextPosition(SwingConstants.CENTER);
		btnNewButton.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnNewButton.setIcon(new ImageIcon(Vue_CreationBien.class.getResource("/img/valider.png")));

		btnNewButton.setBounds(895, 11, 63, 67);
		frmCrationDunBien.getContentPane().add(btnNewButton);

		JLabel lblNewLabel = new JLabel("Nom du bien immobilier");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(115, 96, 210, 14);
		frmCrationDunBien.getContentPane().add(lblNewLabel);

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
		txtNom.setBounds(115, 121, 296, 20);
		frmCrationDunBien.getContentPane().add(txtNom);

		separator = new JSeparator();
		separator.setForeground(Color.LIGHT_GRAY);
		separator.setBounds(0, 82, frmCrationDunBien.getWidth(), 2);
		frmCrationDunBien.getContentPane().add(separator);

		lblPays = new JLabel("Pays");
		lblPays.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblPays.setBounds(115, 152, 32, 17);
		frmCrationDunBien.getContentPane().add(lblPays);

		JComboBox<String> comboboxPays = new JComboBox<>();
		comboboxPays.setModel(new DefaultComboBoxModel<>(new String[] { "Allemagne", "Autriche", "Belgique", "Bulgarie",
				"Chypre", "Croatie", "Danemark", "Espagne", "Estonie", "Finlande", "France", "Grèce", "Hongrie",
				"Irlande", "Italie", "Lettonie", "Lituanie", "Luxembourg", "Malte", "Pays-Bas", "Pologne", "Portugal",
				"Roumanie", "Slovaquie", "Slovénie", "Suède", "Tchéquie" }));
		comboboxPays.setBounds(115, 179, 153, 22);
		frmCrationDunBien.getContentPane().add(comboboxPays);

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
		txtAdresse.setBounds(115, 241, 296, 20);
		frmCrationDunBien.getContentPane().add(txtAdresse);

		JLabel lblVille = new JLabel("Adresse");
		lblVille.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblVille.setBounds(115, 213, 55, 17);
		frmCrationDunBien.getContentPane().add(lblVille);

		JLabel lblVille_2 = new JLabel("Ville");
		lblVille_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblVille_2.setBounds(115, 273, 29, 17);
		frmCrationDunBien.getContentPane().add(lblVille_2);

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
		txtVille.setBounds(115, 301, 181, 20);
		frmCrationDunBien.getContentPane().add(txtVille);

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
		txtCodePostale.setBounds(306, 301, 105, 20);
		frmCrationDunBien.getContentPane().add(txtCodePostale);

		lblVille_1 = new JLabel("Année de construction");
		lblVille_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblVille_1.setBounds(115, 333, 175, 17);
		frmCrationDunBien.getContentPane().add(lblVille_1);

		JLabel lblNewLabel_4_1 = new JLabel("Type");
		lblNewLabel_4_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_4_1.setBounds(115, 388, 100, 20);
		frmCrationDunBien.getContentPane().add(lblNewLabel_4_1);

		JComboBox<String> comboboxType = new JComboBox<String>();
		comboboxType.setModel(new DefaultComboBoxModel<String>(new String[] {"T1", "T2", "T2 bis", "T3", "T3 bis", "T4", "T3 T4", "T5", "T6", "Duplex", "Triplex", "Souplex", "Loft"}));
		comboboxType.setBounds(115, 413, 119, 22);
		frmCrationDunBien.getContentPane().add(comboboxType);

		JLabel lblNewLabel_4_1_1 = new JLabel("Statut");
		lblNewLabel_4_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_4_1_1.setBounds(244, 388, 100, 20);
		frmCrationDunBien.getContentPane().add(lblNewLabel_4_1_1);

		JComboBox<String> comboboxStatut = new JComboBox<String>();
		comboboxStatut.setModel(
				new DefaultComboBoxModel<String>(new String[] {"En location", "En recherche de locataire", "Inactif"}));
		comboboxStatut.setBounds(244, 413, 167, 22);
		frmCrationDunBien.getContentPane().add(comboboxStatut);
		
		JYearChooser yearChooser = new JYearChooser();
		yearChooser.setBounds(115, 357, 53, 19);
		frmCrationDunBien.getContentPane().add(yearChooser);
		yearChooser.setMaximum(2021);
		
		JLabel lblNewLabel_4_1_2 = new JLabel("Valeur");
		lblNewLabel_4_1_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_4_1_2.setBounds(115, 446, 100, 20);
		frmCrationDunBien.getContentPane().add(lblNewLabel_4_1_2);
		// Impossible de mettre un point pour faire un double
		txtValeur = new JTextField();
		txtValeur.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if ((!Character.isDigit(c)) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE)) {
					e.consume();
				
					}
				
			}
		});
		txtValeur.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if(txtValeur.getText().equals("€")) {
					txtValeur.setText("");
					txtValeur.setForeground(Color.black);
				}
			}
			@Override
			public void focusLost(FocusEvent e) {
				if(txtValeur.getText().equals("")) {
					txtValeur.setText("€");
					txtValeur.setForeground(Color.LIGHT_GRAY);
				}
			}
		});
		txtValeur.setText("€");
		txtValeur.setForeground(Color.LIGHT_GRAY);
		txtValeur.setColumns(10);
		txtValeur.setBounds(115, 477, 105, 20);
		frmCrationDunBien.getContentPane().add(txtValeur);
		
		JLabel lblNewLabel_4_1_2_1 = new JLabel("Surface");
		lblNewLabel_4_1_2_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_4_1_2_1.setBounds(225, 446, 100, 20);
		frmCrationDunBien.getContentPane().add(lblNewLabel_4_1_2_1);
		
		txtSurface = new JTextField();
		txtSurface.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if ((!Character.isDigit(c)) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE)) {
					e.consume();
					}
				
			}
		});
		txtSurface.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if(txtSurface.getText().equals("m²")) {
					txtSurface.setText("");
					txtSurface.setForeground(Color.black);
				}
			}
			@Override
			public void focusLost(FocusEvent e) {
				if(txtSurface.getText().equals("")) {
					txtSurface.setText("m²");
					txtSurface.setForeground(Color.LIGHT_GRAY);
				}
			}
		});
		txtSurface.setText("m²");
		txtSurface.setForeground(Color.LIGHT_GRAY);
		txtSurface.setColumns(10);
		txtSurface.setBounds(225, 477, 100, 20);
		frmCrationDunBien.getContentPane().add(txtSurface);
		
		JLabel lblPropritaire = new JLabel("Propriétaire");
		lblPropritaire.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblPropritaire.setBounds(115, 508, 100, 14);
		frmCrationDunBien.getContentPane().add(lblPropritaire);
		
		ProprietaireDAO proprietaireDAO= new ProprietaireDAO();
		ArrayList<Proprietaire>proprietaires=proprietaireDAO.getAll();
		
		JComboBox<Proprietaire> comboboxProprietaire = new JComboBox<Proprietaire>();
		for(Proprietaire p:proprietaires) {
			comboboxProprietaire.addItem(p);
		}
		comboboxProprietaire.setBounds(115, 533, 296, 22);
		frmCrationDunBien.getContentPane().add(comboboxProprietaire);
		
		JLabel btnRetour = new JLabel("Retour");
		btnRetour.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frmCrationDunBien.dispose();
				new Vue_BiensList(agent).getFrame().setVisible(true);
				
			}
		});
		btnRetour.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnRetour.setHorizontalTextPosition(SwingConstants.CENTER);
		btnRetour.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnRetour.setIcon(new ImageIcon(Vue_CreationBien.class.getResource("/img/back.png")));
		btnRetour.setBounds(11, 11, 48, 67);
		frmCrationDunBien.getContentPane().add(btnRetour);
		JLabel lblBG = new JLabel("");
		lblBG.setOpaque(true);
		lblBG.setIcon(new ImageIcon(Vue_AccueilAgent.class.getResource("/img/accueil_bg.jpeg")));
		lblBG.setBounds(-16, 0, 1000, 591);
		frmCrationDunBien.getContentPane().add(lblBG);
		
		JLabel label = new JLabel("New label");
		label.setBounds(328, 23, 55, 16);
		frmCrationDunBien.getContentPane().add(label);
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				Database.Connect();
				String nom = txtNom.getText();
				String adresse = txtAdresse.getText();
				String ville = txtVille.getText();
				String cp = txtCodePostale.getText();
				String pays = comboboxPays.getSelectedItem().toString();
				String type=comboboxType.getSelectedItem().toString();
				String strannee = String.valueOf(yearChooser.getValue());
				String strsurface=txtSurface.getText();
				String strvaleur=txtValeur.getText();
				int id_proprietaire=comboboxProprietaire.getItemAt(comboboxProprietaire.getSelectedIndex()).getId();
				int id_agent=agent.getId();
				String statut = comboboxStatut.getSelectedItem().toString();
				

				 if (nom.equals("Nom")) {
					txtNom.setForeground(Color.red);
					JOptionPane.showMessageDialog(null, "Veuillez remplir tous les champs");
				}  else if (adresse.equals("Adresse")) {
					txtAdresse.setForeground(Color.red);
					JOptionPane.showMessageDialog(null, "Veuillez remplir tous les champs");
				} else if (ville.equals("Ville")) {
					txtVille.setForeground(Color.red);
					JOptionPane.showMessageDialog(null, "Veuillez remplir tous les champs");
				} else if (cp.equals("Code Postal")) {
					txtCodePostale.setForeground(Color.red);
					JOptionPane.showMessageDialog(null, "Veuillez remplir tous les champs");
				} else if(strvaleur.equals("€")){
					txtValeur.setForeground(Color.red);
					JOptionPane.showMessageDialog(null, "Veuillez remplir tous les champs");
				}else if(strsurface.equals("m²")){
					txtSurface.setForeground(Color.red);
					JOptionPane.showMessageDialog(null, "Veuillez remplir tous les champs");
				}else{
					Database.Connect();
					BienDAO bienDAO=new BienDAO();
					Bien bien=new Bien();
					bien.setNom(nom);
					bien.setAdresse(adresse);
					bien.setCp(cp);
					bien.setVille(ville);
					bien.setPays(pays);
					bien.setType(type);
					double valeur=Double.parseDouble(strvaleur);
					bien.setValeur(valeur);
					double surface=Double.parseDouble(strsurface);
					bien.setSurface(surface);
					bien.setStatut(statut);
					bien.setId_agent(id_agent);
					bien.setId_proprietaire(id_proprietaire);
					int annee=Integer.parseInt(strannee);
				    bien.setAnnee(annee);
				    bienDAO.save(bien);
				    ArrayList<Bien>biens = bienDAO.getAll();
				    bien=biens.get(biens.size()-1);
				    
				    String c = System.getProperty("user.dir");
				    String folderName=String.valueOf(bien.getId());
				    String basePath=c+"\\img_appart";
				    String concat=basePath+"\\"+folderName;
				    File f1=new File(concat);
				    boolean bool = f1.mkdir();  
				      if(bool){  
				         System.out.println("Folder is created successfully");  
				      }else{  
				         System.out.println("Error Found!");  
				      }  
				      
				      
				      HistoriqueDAO historiqueDAO = new HistoriqueDAO();
						Historique historique = new Historique();
						historique.setDate(Checker.getDateTime());
						historique.setId_agent(agent.getId());
						historique.setAction("Ajout bien immobilier id : "+bien.getId());
						historiqueDAO.save(historique);	
	
					int input = JOptionPane.showConfirmDialog(null, "Le nouveau bien a bien été enregistré.\n"
							+ "Souhaitez vous lui attribuer des photos tout de suite?");
					if (input == 0) {
						frmCrationDunBien.dispose();
						new Vue_BienModif(bien,agent).getFrame().setVisible(true);
					} else {
						frmCrationDunBien.dispose();
						new Vue_BiensList(agent).getFrame().setVisible(true);
					}
				}
			
				
			}
		});

	}
}
