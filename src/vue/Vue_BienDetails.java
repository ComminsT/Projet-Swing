package vue;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

import dao.ContratlDAO;
import dao.LocataireDAO;
import dao.ProprietaireDAO;
import dao.VisiteDAO;
import entite.Agent;
import entite.Bien;
import entite.Contratl;
import entite.Database;
import entite.Locataire;
import entite.Proprietaire;
import entite.Visite;

public class Vue_BienDetails {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					Vue_BienDetails window = new Vue_BienDetails();
					window.frmModificationDeBien.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private JFrame frmModificationDeBien;
	private JSeparator separator;
	private JLabel txtNom;
	private JLabel lblPays;
	private JLabel txtAdresse;
	private JLabel txtVille;
	private JLabel txtCodePostale;
	private JLabel lblVille_1;
	private JLabel txtValeur;
	private JLabel txtSurface;
	private Agent agent;
	private Bien bien;
	private int photoDisplayed = 1;
	private JLayeredPane layeredPane;
	private JTable table_Contrats;
	private JTable table_Visites;

	/**
	 * Create the application.
	 */
	public Vue_BienDetails() {
		initialize();

	}

	public Vue_BienDetails(Bien bien, Agent agent) {
		this.agent = agent;
		this.bien = bien;

		initialize();
	}

	public JFrame getFrame() {
		return frmModificationDeBien;
	}

	public void setFrame(JFrame frame) {
		this.frmModificationDeBien = frame;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		Database.Connect();
		frmModificationDeBien = new JFrame();
		frmModificationDeBien.setTitle("D??tails du bien immobilier");
		frmModificationDeBien.setBounds(100, 100, 981, 619);
		frmModificationDeBien.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frmModificationDeBien.getContentPane().setLayout(null);
		frmModificationDeBien.setResizable(false);
		frmModificationDeBien.setLocationRelativeTo(null);

		JLabel btnContrats = new JLabel("Contrats");

		btnContrats.setOpaque(false);
		btnContrats.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnContrats.setBorder(null);
		btnContrats.setIcon(new ImageIcon(Vue_BienDetails.class.getResource("/img/contract.png")));

		JLabel btnVisites = new JLabel("Visites");

		btnVisites.setOpaque(false);
		btnVisites.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnVisites.setBorder(null);
		btnVisites.setIcon(new ImageIcon(Vue_BienDetails.class.getResource("/img/visits.png")));

		btnVisites.setBounds(0, 476, 86, 40);
		frmModificationDeBien.getContentPane().add(btnVisites);
		btnContrats.setBounds(0, 343, 106, 48);
		frmModificationDeBien.getContentPane().add(btnContrats);

		JLabel btnImprimer = new JLabel("Imprimer");
		btnImprimer.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				printRecord(frmModificationDeBien);
			}
		});
		btnImprimer.setHorizontalAlignment(SwingConstants.CENTER);
		btnImprimer.setOpaque(false);
		btnImprimer.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnImprimer.setHorizontalTextPosition(SwingConstants.CENTER);
		btnImprimer.setIcon(new ImageIcon(Vue_BienDetails.class.getResource("/img/print.png")));
		btnImprimer.setVerticalTextPosition(SwingConstants.BOTTOM);

		btnImprimer.setBounds(895, 11, 52, 68);
		frmModificationDeBien.getContentPane().add(btnImprimer);

		separator = new JSeparator();
		separator.setForeground(Color.LIGHT_GRAY);
		separator.setBounds(0, 82, frmModificationDeBien.getWidth(), 2);
		frmModificationDeBien.getContentPane().add(separator);

		JLabel lblNewLabel_1 = new JLabel("D??tail du bien immobilier");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(404, 30, 166, 16);
		frmModificationDeBien.getContentPane().add(lblNewLabel_1);

		JLabel btnRetour = new JLabel("Retour");
		btnRetour.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frmModificationDeBien.dispose();
				new Vue_BiensList(agent).getFrame().setVisible(true);
			}
		});
		btnRetour.setOpaque(false);
		btnRetour.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnRetour.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnRetour.setHorizontalTextPosition(SwingConstants.CENTER);
		btnRetour.setIcon(new ImageIcon(Vue_BienDetails.class.getResource("/img/back.png")));
		btnRetour.setBounds(11, 11, 48, 68);
		frmModificationDeBien.getContentPane().add(btnRetour);

		layeredPane = new JLayeredPane();
		layeredPane.setBounds(133, 91, 765, 487);
		frmModificationDeBien.getContentPane().add(layeredPane);
		layeredPane.setLayout(new CardLayout(0, 0));
		JPanel panel_photos = new JPanel();
		layeredPane.add(panel_photos, "name_463069160670000");
		panel_photos.setLayout(null);

		JLabel btnSuivant = new JLabel("Suivant");
		btnSuivant.setVisible(false);

		btnSuivant.setHorizontalTextPosition(SwingConstants.CENTER);
		btnSuivant.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnSuivant.setIcon(new ImageIcon(Vue_BienDetails.class.getResource("/img/next.png")));
		btnSuivant.setOpaque(false);
		btnSuivant.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		btnSuivant.setBounds(700, 224, 65, 69);
		panel_photos.add(btnSuivant);

		JLabel btnPrecedent = new JLabel("Precedent");
		btnPrecedent.setVisible(false);

		btnPrecedent.setHorizontalTextPosition(SwingConstants.CENTER);
		btnPrecedent.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnPrecedent.setIcon(new ImageIcon(Vue_BienDetails.class.getResource("/img/back.png")));
		btnPrecedent.setOpaque(false);
		btnPrecedent.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		btnPrecedent.setBounds(12, 224, 65, 69);
		panel_photos.add(btnPrecedent);

		JLabel lblNewLabel_2 = new JLabel("");

		lblNewLabel_2.setBounds(0, 0, 765, 500);
		panel_photos.add(lblNewLabel_2);

		JPanel panel_info = new JPanel();
		layeredPane.add(panel_info, "name_528310932692899");
		panel_info.setLayout(null);
		panel_info.setBackground(new Color(255,255,255,100));

		JLabel txtPays = new JLabel();
		txtPays.setForeground(Color.BLACK);
		txtPays.setBounds(199, 64, 296, 20);
		panel_info.add(txtPays);
		txtPays.setText(bien.getPays());

		JLabel lblNewLabel = new JLabel("Nom :");
		lblNewLabel.setBounds(106, 24, 73, 14);
		panel_info.add(lblNewLabel);
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));

		txtNom = new JLabel();
		txtNom.setBounds(199, 22, 296, 20);
		panel_info.add(txtNom);
		txtNom.setForeground(Color.BLACK);
		txtNom.setText(bien.getNom());

		lblPays = new JLabel("Pays :");
		lblPays.setBounds(106, 62, 73, 17);
		panel_info.add(lblPays);
		lblPays.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPays.setFont(new Font("Tahoma", Font.BOLD, 14));

		txtAdresse = new JLabel();
		txtAdresse.setBounds(199, 106, 296, 20);
		panel_info.add(txtAdresse);
		txtAdresse.setText(bien.getAdresse());
		txtAdresse.setForeground(Color.BLACK);

		JLabel lblVille = new JLabel("Adresse :");
		lblVille.setBounds(71, 103, 108, 17);
		panel_info.add(lblVille);
		lblVille.setHorizontalAlignment(SwingConstants.RIGHT);
		lblVille.setFont(new Font("Tahoma", Font.BOLD, 14));

		JLabel lblVille_2 = new JLabel("Ville :");
		lblVille_2.setBounds(106, 144, 73, 17);
		panel_info.add(lblVille_2);
		lblVille_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblVille_2.setFont(new Font("Tahoma", Font.BOLD, 14));

		txtVille = new JLabel();
		txtVille.setBounds(199, 148, 181, 20);
		panel_info.add(txtVille);
		txtVille.setText(bien.getVille());
		txtVille.setForeground(Color.BLACK);

		txtCodePostale = new JLabel();
		txtCodePostale.setBounds(199, 190, 105, 20);
		panel_info.add(txtCodePostale);
		txtCodePostale.setText(bien.getCp());
		txtCodePostale.setForeground(Color.BLACK);

		lblVille_1 = new JLabel("Ann??e de construction :");
		lblVille_1.setBounds(12, 226, 167, 17);
		panel_info.add(lblVille_1);
		lblVille_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblVille_1.setFont(new Font("Tahoma", Font.BOLD, 14));

		JLabel lblNewLabel_4_1 = new JLabel("Type :");
		lblNewLabel_4_1.setBounds(123, 267, 56, 20);
		panel_info.add(lblNewLabel_4_1);
		lblNewLabel_4_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_4_1.setFont(new Font("Tahoma", Font.BOLD, 14));

		JLabel txtType = new JLabel();
		txtType.setBounds(199, 268, 119, 22);
		panel_info.add(txtType);
		txtType.setText(bien.getType());

		JLabel lblNewLabel_4_1_1 = new JLabel("Statut :");
		lblNewLabel_4_1_1.setBounds(123, 311, 56, 20);
		panel_info.add(lblNewLabel_4_1_1);
		lblNewLabel_4_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_4_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));

		JLabel lblStatut = new JLabel();
		lblStatut.setBounds(199, 312, 167, 22);
		panel_info.add(lblStatut);
		lblStatut.setText(bien.getStatut());

		JLabel lblNewLabel_4_1_2 = new JLabel("Valeur :");
		lblNewLabel_4_1_2.setBounds(123, 355, 56, 20);
		panel_info.add(lblNewLabel_4_1_2);
		lblNewLabel_4_1_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_4_1_2.setFont(new Font("Tahoma", Font.BOLD, 14));

		
		txtValeur = new JLabel();
		txtValeur.setBounds(199, 356, 105, 20);
		panel_info.add(txtValeur);
		txtValeur.setText(String.valueOf(bien.getValeur()) + " ???");
		txtValeur.setForeground(Color.BLACK);

		JLabel lblNewLabel_4_1_2_1 = new JLabel("Surface :");
		lblNewLabel_4_1_2_1.setBounds(106, 399, 73, 20);
		panel_info.add(lblNewLabel_4_1_2_1);
		lblNewLabel_4_1_2_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_4_1_2_1.setFont(new Font("Tahoma", Font.BOLD, 14));

		txtSurface = new JLabel();
		txtSurface.setBounds(199, 398, 100, 20);
		panel_info.add(txtSurface);
		txtSurface.setText(String.valueOf(bien.getSurface()) + " m??");
		txtSurface.setForeground(Color.BLACK);

		JLabel lblPropritaire = new JLabel("Propri??taire :");
		lblPropritaire.setBounds(79, 443, 100, 14);
		panel_info.add(lblPropritaire);
		lblPropritaire.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPropritaire.setFont(new Font("Tahoma", Font.BOLD, 14));
		JLabel lblProprietaire = new JLabel();
		lblProprietaire.setBounds(199, 440, 296, 22);
		panel_info.add(lblProprietaire);
		ProprietaireDAO proprietaireDAO = new ProprietaireDAO();
		Proprietaire proprietaire = proprietaireDAO.getById(bien.getId_proprietaire());
		lblProprietaire.setText(proprietaire.toString());

		JLabel lblVille_2_1 = new JLabel("Code Postal :");
		lblVille_2_1.setBounds(66, 185, 113, 17);
		panel_info.add(lblVille_2_1);
		lblVille_2_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblVille_2_1.setFont(new Font("Tahoma", Font.BOLD, 14));

		JLabel lblAnnee = new JLabel("");
		lblAnnee.setBounds(199, 232, 73, 14);
		panel_info.add(lblAnnee);
		lblAnnee.setText(String.valueOf(bien.getAnnee()));

		JScrollPane scroll_Contrats = new JScrollPane();
		layeredPane.add(scroll_Contrats, "name_989719240449800");

		ContratlDAO contratDAO = new ContratlDAO();
		ArrayList<Contratl> contrats = contratDAO.getAllByBienId(bien.getId());
		String[] columns = { "ID", "Locataire", "Date de d??but", "Date de fin" };
		String[][] data2 = new String[contrats.size()][columns.length];
		int i = 0;
		for (Contratl c : contrats) {
			LocataireDAO locataireDAO = new LocataireDAO();
			Locataire locataire = locataireDAO.getById(c.getId_locataire());
			data2[i][0] = c.getId() + "";
			data2[i][1] = locataire.toString();
			data2[i][2] = c.getDate();
			data2[i][3] = c.getDatefin();
			i++;
		}
		DefaultTableModel model2 = new DefaultTableModel(data2, columns);

		table_Contrats = new JTable(model2);
		scroll_Contrats.setViewportView(table_Contrats);

		JScrollPane scroll_Visites = new JScrollPane();
		layeredPane.add(scroll_Visites, "name_989722156527600");

		VisiteDAO visiteDAO = new VisiteDAO();
		ArrayList<Visite> visites = visiteDAO.getAllByBienId(bien.getId());

		String[] column = { "ID", "Date", "Visiteur" };
		String[][] data = new String[visites.size()][column.length];
		i = 0;
		for (Visite v : visites) {
			data[i][0] = v.getId() + "";
			data[i][1] = v.getDate();
			data[i][2] = v.getNom();
			i++;
		}
		DefaultTableModel model1 = new DefaultTableModel(data, column);
		table_Visites = new JTable(model1);
		scroll_Visites.setViewportView(table_Visites);
		JLabel btnInfos = new JLabel("Informations");
		
		btnInfos.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnInfos.setBorder(null);
		btnInfos.setOpaque(false);
		btnInfos.setIcon(new ImageIcon(Vue_BienDetails.class.getResource("/img/personal.png")));
		btnInfos.setBounds(0, 85, 125, 40);
		frmModificationDeBien.getContentPane().add(btnInfos);

		JLabel btnPhotos = new JLabel("Photos");
		
		btnPhotos.setOpaque(false);
		btnPhotos.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnPhotos.setBorder(null);
		btnPhotos.setIcon(new ImageIcon(Vue_BienDetails.class.getResource("/img/photos.png")));
		btnPhotos.setBounds(0, 210, 95, 48);
		frmModificationDeBien.getContentPane().add(btnPhotos);
		JLabel lblBG = new JLabel("");
		lblBG.setOpaque(true);
		lblBG.setIcon(new ImageIcon(Vue_AccueilAgent.class.getResource("/img/accueil_bg.jpeg")));
		lblBG.setBounds(-16, 0, 1000, 591);
		frmModificationDeBien.getContentPane().add(lblBG);
		
		
		String dir = System.getProperty("user.dir");
		File pathtoimg_appart_file = new File(dir + "\\img_appart\\" + bien.getId());
		File[] paths = pathtoimg_appart_file.listFiles();
		ArrayList<String> imgpaths = new ArrayList<String>();
		for (File f : paths) {
			imgpaths.add(f.getAbsolutePath());
		}
		int fileCount = pathtoimg_appart_file.list().length;
		
		
		if (fileCount > 0) {
			btnPrecedent.setVisible(true);
			btnSuivant.setVisible(true);
			lblNewLabel_2.setIcon(new ImageIcon(new ImageIcon(imgpaths.get(photoDisplayed - 1)).getImage()
					.getScaledInstance(832, 500, Image.SCALE_DEFAULT)));

		} else {
			lblNewLabel_2.setIcon(new ImageIcon(new ImageIcon(Vue_BienModif.class.getResource("/img/no_pics.jpg"))
					.getImage().getScaledInstance(832, 414, Image.SCALE_DEFAULT)));

		}

		btnPrecedent.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (photoDisplayed == 1) {
					photoDisplayed = fileCount;
					lblNewLabel_2.setIcon(new ImageIcon(new ImageIcon(imgpaths.get(photoDisplayed - 1)).getImage()
							.getScaledInstance(832, 500, Image.SCALE_DEFAULT)));
				} else {
					photoDisplayed--;
					lblNewLabel_2.setIcon(new ImageIcon(new ImageIcon(imgpaths.get(photoDisplayed - 1)).getImage()
							.getScaledInstance(832, 500, Image.SCALE_DEFAULT)));
				}
			}
		});

		btnSuivant.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (photoDisplayed != fileCount) {
					photoDisplayed++;
					lblNewLabel_2.setIcon(new ImageIcon(new ImageIcon(imgpaths.get(photoDisplayed - 1)).getImage()
							.getScaledInstance(832, 500, Image.SCALE_DEFAULT)));
				} else {
					photoDisplayed = 1;
					lblNewLabel_2.setIcon(new ImageIcon(new ImageIcon(imgpaths.get(photoDisplayed - 1)).getImage()
							.getScaledInstance(832, 500, Image.SCALE_DEFAULT)));
				}
			}
		});
		layeredPane.setBackground(new Color(0, 0, 0, 0));

		btnVisites.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				switchScrollPane(scroll_Visites);
			}

		});

		btnContrats.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				switchScrollPane(scroll_Contrats);
			}
		});
		btnPhotos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				switchPanels(panel_photos);
			}
		});
		btnInfos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				switchPanels(panel_info);
			}
		});

	}

	public void switchPanels(JPanel panel) {
		layeredPane.removeAll();
		layeredPane.add(panel);
		layeredPane.repaint();
		layeredPane.revalidate();
	}

	public void switchScrollPane(JScrollPane jscrollpane) {
		layeredPane.removeAll();
		layeredPane.add(jscrollpane);
		layeredPane.repaint();
		layeredPane.revalidate();
	}

	private void printRecord(JFrame frametoprint) {
		PrinterJob printerJob = PrinterJob.getPrinterJob();
		printerJob.setJobName("Print Record");
		printerJob.setPrintable(new Printable() {
			@Override
			public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
				// Check If No Printable Content
				if (pageIndex > 0) {
					return Printable.NO_SUCH_PAGE;
				}

				// Make 2D Graphics to map content
				Graphics2D graphics2D = (Graphics2D) graphics;
				// Set Graphics Translations
				// A Little Correction here Multiplication was not working so I replaced with
				// addition
				graphics2D.translate(pageFormat.getImageableX() + 10, pageFormat.getImageableY() + 10);
				// This is a page scale. Default should be 0.3 I am using 0.5
				graphics2D.scale(0.5, 0.5);

				// Now paint panel as graphics2D
				frametoprint.paint(graphics2D);

				// return if page exists
				return Printable.PAGE_EXISTS;
			}
		});
		boolean returningResult = printerJob.printDialog();
		// check if dialog is showing
		if (returningResult) {
			// Use try catch exeption for failure
			try {
				// Now call print method inside printerJob to print
				printerJob.print();
			} catch (PrinterException printerException) {
				JOptionPane.showMessageDialog(frametoprint, "Print Error: " + printerException.getMessage());
			}
		}

	}
}
