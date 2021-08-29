package vue;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

import com.toedter.calendar.JYearChooser;

import dao.BienDAO;
import dao.ProprietaireDAO;
import entite.Agent;
import entite.Bien;
import entite.Database;
import entite.Proprietaire;
import javax.swing.JLayeredPane;
import java.awt.CardLayout;
import javax.swing.JPanel;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

public class Vue_BienModif {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					Vue_BienModif window = new Vue_BienModif();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private JFrame frame;
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
	private Bien bien;
	private int photoDisplayed = 1;

	/**
	 * Create the application.
	 */
	public Vue_BienModif() {
		initialize();

	}

	public Vue_BienModif(Bien bien, Agent agent) {
		this.agent = agent;
		this.bien = bien;

		initialize();
	}

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		Database.Connect();
		frame = new JFrame();
		frame.setBounds(100, 100, 981, 630);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);

		JButton btnNewButton = new JButton("Confirmer");
		btnNewButton.setHorizontalTextPosition(SwingConstants.CENTER);
		btnNewButton.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnNewButton.setIcon(new ImageIcon(Vue_BienModif.class.getResource("/img/valider.png")));

		btnNewButton.setBounds(852, 11, 113, 68);
		frame.getContentPane().add(btnNewButton);

		JLabel lblNewLabel = new JLabel("Nom :");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(45, 91, 73, 14);
		frame.getContentPane().add(lblNewLabel);

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
		txtNom.setText(bien.getNom());
		txtNom.setColumns(10);
		txtNom.setBounds(128, 90, 296, 20);
		frame.getContentPane().add(txtNom);

		separator = new JSeparator();
		separator.setForeground(Color.LIGHT_GRAY);
		separator.setBounds(0, 82, frame.getWidth(), 2);
		frame.getContentPane().add(separator);

		lblPays = new JLabel("Pays :");
		lblPays.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPays.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblPays.setBounds(45, 120, 73, 17);
		frame.getContentPane().add(lblPays);

		JComboBox<String> comboboxPays = new JComboBox<>();
		comboboxPays.setModel(new DefaultComboBoxModel<>(new String[] { "Allemagne", "Autriche", "Belgique", "Bulgarie",
				"Chypre", "Croatie", "Danemark", "Espagne", "Estonie", "Finlande", "France", "Grèce", "Hongrie",
				"Irlande", "Italie", "Lettonie", "Lituanie", "Luxembourg", "Malte", "Pays-Bas", "Pologne", "Portugal",
				"Roumanie", "Slovaquie", "Slovénie", "Suède", "Tchéquie" }));
		comboboxPays.setBounds(128, 121, 153, 22);
		frame.getContentPane().add(comboboxPays);
		comboboxPays.setSelectedItem(bien.getPays());

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
		txtAdresse.setText(bien.getAdresse());
		txtAdresse.setForeground(Color.BLACK);
		txtAdresse.setColumns(10);
		txtAdresse.setBounds(128, 151, 296, 20);
		frame.getContentPane().add(txtAdresse);

		JLabel lblVille = new JLabel("Adresse :");
		lblVille.setHorizontalAlignment(SwingConstants.RIGHT);
		lblVille.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblVille.setBounds(10, 150, 108, 17);
		frame.getContentPane().add(lblVille);

		JLabel lblVille_2 = new JLabel("Ville :");
		lblVille_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblVille_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblVille_2.setBounds(45, 178, 73, 17);
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
		txtVille.setText(bien.getVille());
		txtVille.setForeground(Color.BLACK);
		txtVille.setColumns(10);
		txtVille.setBounds(128, 175, 181, 20);
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
					txtCodePostale.setText("Code Postal");
					txtCodePostale.setForeground(Color.LIGHT_GRAY);
				}
			}
		});
		txtCodePostale.setText(bien.getCp());
		txtCodePostale.setForeground(Color.BLACK);
		txtCodePostale.setColumns(10);
		txtCodePostale.setBounds(128, 204, 105, 20);
		frame.getContentPane().add(txtCodePostale);

		lblVille_1 = new JLabel("Année de construction :");
		lblVille_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblVille_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblVille_1.setBounds(10, 235, 167, 17);
		frame.getContentPane().add(lblVille_1);

		JLabel lblNewLabel_4_1 = new JLabel("Type :");
		lblNewLabel_4_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_4_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_4_1.setBounds(62, 263, 56, 20);
		frame.getContentPane().add(lblNewLabel_4_1);

		JComboBox<String> comboboxType = new JComboBox<String>();
		comboboxType.setModel(new DefaultComboBoxModel<String>(new String[] { "T1", "T2", "T2 bis", "T3", "T3 bis",
				"T4", "T3 T4", "T5", "T6", "Duplex", "Triplex", "Souplex", "Loft" }));
		comboboxType.setBounds(128, 264, 119, 22);
		frame.getContentPane().add(comboboxType);
		comboboxType.setSelectedItem(bien.getType());

		JLabel lblNewLabel_4_1_1 = new JLabel("Statut :");
		lblNewLabel_4_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_4_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_4_1_1.setBounds(62, 293, 56, 20);
		frame.getContentPane().add(lblNewLabel_4_1_1);

		JComboBox<String> comboboxStatut = new JComboBox<String>();
		comboboxStatut.setModel(new DefaultComboBoxModel<String>(
				new String[] { "En location", "En recherche de locataire", "Inactif" }));
		comboboxStatut.setBounds(128, 294, 167, 22);
		frame.getContentPane().add(comboboxStatut);
		comboboxStatut.setSelectedItem(bien.getStatut());

		JYearChooser yearChooser = new JYearChooser();
		yearChooser.setBounds(187, 232, 48, 20);
		frame.getContentPane().add(yearChooser);
		yearChooser.setMaximum(2021);
		yearChooser.setYear(bien.getAnnee());

		JLabel lblNewLabel_4_1_2 = new JLabel("Valeur :");
		lblNewLabel_4_1_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_4_1_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_4_1_2.setBounds(62, 324, 56, 20);
		frame.getContentPane().add(lblNewLabel_4_1_2);
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
				if (txtValeur.getText().equals("€")) {
					txtValeur.setText("");
					txtValeur.setForeground(Color.black);
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (txtValeur.getText().equals("")) {
					txtValeur.setText("€");
					txtValeur.setForeground(Color.LIGHT_GRAY);
				}
			}
		});
		txtValeur.setText(String.valueOf(bien.getValeur()) + " €");
		txtValeur.setForeground(Color.BLACK);
		txtValeur.setColumns(10);
		txtValeur.setBounds(128, 326, 105, 20);
		frame.getContentPane().add(txtValeur);

		JLabel lblNewLabel_4_1_2_1 = new JLabel("Surface :");
		lblNewLabel_4_1_2_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_4_1_2_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_4_1_2_1.setBounds(45, 355, 73, 20);
		frame.getContentPane().add(lblNewLabel_4_1_2_1);

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
				if (txtSurface.getText().equals("m²")) {
					txtSurface.setText("");
					txtSurface.setForeground(Color.black);
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (txtSurface.getText().equals("")) {
					txtSurface.setText("m²");
					txtSurface.setForeground(Color.LIGHT_GRAY);
				}
			}
		});
		txtSurface.setText(String.valueOf(bien.getSurface()) + " m²");
		txtSurface.setForeground(Color.BLACK);
		txtSurface.setColumns(10);
		txtSurface.setBounds(128, 357, 100, 20);
		frame.getContentPane().add(txtSurface);

		JLabel lblPropritaire = new JLabel("Propriétaire :");
		lblPropritaire.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPropritaire.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblPropritaire.setBounds(18, 386, 100, 14);
		frame.getContentPane().add(lblPropritaire);

		ProprietaireDAO proprietaireDAO = new ProprietaireDAO();
		ArrayList<Proprietaire> proprietaires = proprietaireDAO.getAll();
		Proprietaire proprietaire = proprietaireDAO.getById(bien.getId_proprietaire());
		JComboBox<Proprietaire> comboboxProprietaire = new JComboBox<Proprietaire>();
		int i = 0;
		int memo = 0;
		for (Proprietaire p : proprietaires) {
			comboboxProprietaire.addItem(p);
			if (p.getId() == proprietaire.getId()) {
				memo = i;
			}
			i++;
		}
		comboboxProprietaire.setBounds(128, 384, 296, 22);
		frame.getContentPane().add(comboboxProprietaire);
		comboboxProprietaire.setSelectedIndex(memo);

		JLabel lblVille_2_1 = new JLabel("Code Postale :");
		lblVille_2_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblVille_2_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblVille_2_1.setBounds(5, 205, 113, 17);
		frame.getContentPane().add(lblVille_2_1);

		JLabel lblNewLabel_1 = new JLabel("Modification biens immobiliers");
		lblNewLabel_1.setBounds(291, 28, 296, 34);
		frame.getContentPane().add(lblNewLabel_1);

		JButton btnRetour = new JButton("Retour");
		btnRetour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				new Vue_BiensList(agent).getFrame().setVisible(true);
			}
		});
		btnRetour.setBounds(10, 11, 113, 69);
		frame.getContentPane().add(btnRetour);

		JButton btnAjouterDesPhotos = new JButton("Ajouter des photos");
		btnAjouterDesPhotos.setFont(new Font("Dialog", Font.BOLD, 10));
		btnAjouterDesPhotos.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnAjouterDesPhotos.setHorizontalTextPosition(SwingConstants.CENTER);
		btnAjouterDesPhotos.setIcon(new ImageIcon(Vue_BienModif.class.getResource("/img/photo.png")));
		btnAjouterDesPhotos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG & PNG", "jpg", "png");
				Path pathtoimg_appart = Paths
						.get("C:\\Users\\Seria\\OneDrive\\CDA\\Java\\projet SWING\\img_appart\\" + bien.getId() + "\\");
				File pathtoimg_appart_file = new File(
						"C:\\Users\\Seria\\OneDrive\\CDA\\Java\\projet SWING\\img_appart\\" + bien.getId());
				System.out.println(pathtoimg_appart);
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setFileFilter(filter);
				fileChooser.setCurrentDirectory(new File("c:"));
				fileChooser.setMultiSelectionEnabled(true);
				int response = fileChooser.showSaveDialog(null);
				int fileCount = pathtoimg_appart_file.list().length;
				System.out.println(fileCount);

				if (response == JFileChooser.APPROVE_OPTION) {
					int filesselected = fileChooser.getSelectedFiles().length;
					for (int i = 0; i < filesselected; i++) {
						File file = new File(fileChooser.getSelectedFiles()[i].getAbsolutePath());
						Path pathtofile = Paths.get(file.toString());
						String fileName = file.toString();
						int index = fileName.lastIndexOf('.');
						System.out.println(fileName.substring(index + 1));
						System.out.println(file);

						try {
							Files.copy(pathtofile,
									pathtoimg_appart.resolve(i + fileCount + 1 + "." + fileName.substring(index + 1)),
									REPLACE_EXISTING);
						} catch (IOException e1) {
							e1.printStackTrace();
						}

					}

				}
			}
		});
		btnAjouterDesPhotos.setBounds(133, 11, 148, 68);
		frame.getContentPane().add(btnAjouterDesPhotos);

		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBounds(434, 90, 531, 500);
		frame.getContentPane().add(layeredPane);
		layeredPane.setLayout(new CardLayout(0, 0));
		JPanel panel = new JPanel();
		layeredPane.add(panel, "name_463069160670000");
		panel.setLayout(null);

		JButton btnSuivant = new JButton("Suivant");

		btnSuivant.setBounds(442, 241, 89, 23);
		panel.add(btnSuivant);

		JButton btnPrecedent = new JButton("Precedent");

		btnPrecedent.setBounds(0, 241, 89, 23);
		panel.add(btnPrecedent);

		JLabel lblNewLabel_2 = new JLabel("");
		

		lblNewLabel_2.setBounds(0, 0, 531, 500);
		panel.add(lblNewLabel_2);

		File pathtoimg_appart_file = new File(
				"C:\\Users\\Seria\\OneDrive\\CDA\\Java\\projet SWING\\img_appart\\" + bien.getId());
		File[] paths = pathtoimg_appart_file.listFiles();
		ArrayList<String> imgpaths = new ArrayList<String>();
		for (File f : paths) {
			imgpaths.add(f.getAbsolutePath());
		}
		int fileCount = pathtoimg_appart_file.list().length;
		btnPrecedent.setVisible(false);
		btnSuivant.setVisible(false);
		if (fileCount > 0) {
			btnPrecedent.setVisible(true);
			btnSuivant.setVisible(true);
			lblNewLabel_2.setIcon(new ImageIcon(new ImageIcon(imgpaths.get(photoDisplayed - 1)).getImage()
					.getScaledInstance(531, 500, Image.SCALE_DEFAULT)));

		} else {
			lblNewLabel_2.setIcon(new ImageIcon(
					new ImageIcon(Vue_BienModif.class.getResource("/img/no_pics.jpg"))
							.getImage().getScaledInstance(470, 414, Image.SCALE_DEFAULT)));

		}
		btnSuivant.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (photoDisplayed != fileCount) {
					photoDisplayed++;
					lblNewLabel_2.setIcon(new ImageIcon(new ImageIcon(imgpaths.get(photoDisplayed - 1)).getImage()
							.getScaledInstance(531, 500, Image.SCALE_DEFAULT)));
				} else {
					photoDisplayed = 1;
					lblNewLabel_2.setIcon(new ImageIcon(new ImageIcon(imgpaths.get(photoDisplayed - 1)).getImage()
							.getScaledInstance(531, 500, Image.SCALE_DEFAULT)));
				}

			}
		});

		btnPrecedent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (photoDisplayed == 1) {
					photoDisplayed = fileCount;
					lblNewLabel_2.setIcon(new ImageIcon(new ImageIcon(imgpaths.get(photoDisplayed - 1)).getImage()
							.getScaledInstance(531, 500, Image.SCALE_DEFAULT)));
				} else {
					photoDisplayed--;
					lblNewLabel_2.setIcon(new ImageIcon(new ImageIcon(imgpaths.get(photoDisplayed - 1)).getImage()
							.getScaledInstance(531, 500, Image.SCALE_DEFAULT)));
				}

			}
		});

		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Database.Connect();
				String nom = txtNom.getText();
				String adresse = txtAdresse.getText();
				String ville = txtVille.getText();
				String cp = txtCodePostale.getText();
				String pays = comboboxPays.getSelectedItem().toString();
				String type = comboboxType.getSelectedItem().toString();
				String strannee = String.valueOf(yearChooser.getValue());
				String strsurface = txtSurface.getText().substring(0, txtSurface.getText().length() - 2);
				String strvaleur = txtValeur.getText().substring(0, txtValeur.getText().length() - 1);
				int id_proprietaire = comboboxProprietaire.getItemAt(comboboxProprietaire.getSelectedIndex()).getId();
				int id_agent = agent.getId();
				String statut = comboboxStatut.getSelectedItem().toString();

				if (nom.equals("Nom")) {
					txtNom.setForeground(Color.red);
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
				} else if (strvaleur.equals("€")) {
					txtValeur.setForeground(Color.red);
					JOptionPane.showMessageDialog(null, "Veuillez remplir tous les champs");
				} else if (strsurface.equals("m²")) {
					txtSurface.setForeground(Color.red);
					JOptionPane.showMessageDialog(null, "Veuillez remplir tous les champs");
				} else {
					Database.Connect();
					BienDAO bienDAO = new BienDAO();
					bien.setNom(nom);
					bien.setAdresse(adresse);
					bien.setCp(cp);
					bien.setVille(ville);
					bien.setPays(pays);
					bien.setType(type);
					double valeur = Double.parseDouble(strvaleur);
					bien.setValeur(valeur);
					double surface = Double.parseDouble(strsurface);
					bien.setSurface(surface);
					bien.setStatut(statut);
					bien.setId_agent(id_agent);
					bien.setId_proprietaire(id_proprietaire);
					int annee = Integer.parseInt(strannee);
					bien.setAnnee(annee);
					bienDAO.save(bien);
					frame.dispose();
					new Vue_BiensList(agent).getFrame().setVisible(true);
				}
			}
		});

	}
}
