package vue;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

import dao.ProprietaireDAO;
import entite.Agent;
import entite.Bien;
import entite.Database;
import entite.Proprietaire;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTable;

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
	private JTable table;
	private JTable table_1;

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
		frmModificationDeBien.setTitle("Modification de bien immobilier");
		frmModificationDeBien.setBounds(100, 100, 981, 630);
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
				
				btnVisites.setBounds(10, 382, 123, 86);
				frmModificationDeBien.getContentPane().add(btnVisites);
				btnContrats.setBounds(10, 285, 123, 86);
				frmModificationDeBien.getContentPane().add(btnContrats);

		JButton btnNewButton = new JButton("Imprimer");
		btnNewButton.setOpaque(false);
		btnNewButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton.setHorizontalTextPosition(SwingConstants.CENTER);
		btnNewButton.setIcon(new ImageIcon(Vue_BienDetails.class.getResource("/img/print.png")));
		btnNewButton.setVerticalTextPosition(SwingConstants.BOTTOM);

		btnNewButton.setBounds(852, 11, 113, 68);
		frmModificationDeBien.getContentPane().add(btnNewButton);

		separator = new JSeparator();
		separator.setForeground(Color.LIGHT_GRAY);
		separator.setBounds(0, 82, frmModificationDeBien.getWidth(), 2);
		frmModificationDeBien.getContentPane().add(separator);

		JLabel lblNewLabel_1 = new JLabel("Modification biens immobilier");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(291, 28, 214, 34);
		frmModificationDeBien.getContentPane().add(lblNewLabel_1);

		JButton btnRetour = new JButton("Retour");
		btnRetour.setOpaque(false);
		btnRetour.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnRetour.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnRetour.setHorizontalTextPosition(SwingConstants.CENTER);
		btnRetour.setIcon(new ImageIcon(Vue_BienDetails.class.getResource("/img/back.png")));
		btnRetour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmModificationDeBien.dispose();
				new Vue_BiensList(agent).getFrame().setVisible(true);
			}
		});
		btnRetour.setBounds(10, 11, 113, 69);
		frmModificationDeBien.getContentPane().add(btnRetour);

		layeredPane = new JLayeredPane();
		layeredPane.setOpaque(true);
		layeredPane.setBounds(133, 90, 765, 500);
		frmModificationDeBien.getContentPane().add(layeredPane);
		layeredPane.setLayout(new CardLayout(0, 0));
		JPanel panel_photos = new JPanel();
		layeredPane.add(panel_photos, "name_463069160670000");
		panel_photos.setLayout(null);
		panel_photos.setVisible(false);

		JButton btnSuivant = new JButton("Suivant");
		btnSuivant.setOpaque(false);
		btnSuivant.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		btnSuivant.setBounds(666, 241, 89, 23);
		panel_photos.add(btnSuivant);

		JButton btnPrecedent = new JButton("Precedent");
		btnPrecedent.setOpaque(false);
		btnPrecedent.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		btnPrecedent.setBounds(0, 241, 89, 23);
		panel_photos.add(btnPrecedent);

		JLabel lblNewLabel_2 = new JLabel("");

		lblNewLabel_2.setBounds(0, 0, 531, 500);
		panel_photos.add(lblNewLabel_2);

		JPanel panel_info = new JPanel();
		layeredPane.add(panel_info, "name_528310932692899");
		panel_info.setLayout(null);

		JLabel txtPays = new JLabel();
		txtPays.setForeground(Color.BLACK);
		txtPays.setBounds(133, 42, 296, 20);
		panel_info.add(txtPays);
		txtPays.setText(bien.getPays());

		JLabel lblNewLabel = new JLabel("Nom :");
		lblNewLabel.setBounds(50, 12, 73, 14);
		panel_info.add(lblNewLabel);
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));

		txtNom = new JLabel();
		txtNom.setBounds(133, 11, 296, 20);
		panel_info.add(txtNom);
		txtNom.setForeground(Color.BLACK);
		txtNom.setText(bien.getNom());

		lblPays = new JLabel("Pays :");
		lblPays.setBounds(50, 41, 73, 17);
		panel_info.add(lblPays);
		lblPays.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPays.setFont(new Font("Tahoma", Font.BOLD, 14));

		txtAdresse = new JLabel();
		txtAdresse.setBounds(133, 72, 296, 20);
		panel_info.add(txtAdresse);
		txtAdresse.setText(bien.getAdresse());
		txtAdresse.setForeground(Color.BLACK);

		JLabel lblVille = new JLabel("Adresse :");
		lblVille.setBounds(15, 71, 108, 17);
		panel_info.add(lblVille);
		lblVille.setHorizontalAlignment(SwingConstants.RIGHT);
		lblVille.setFont(new Font("Tahoma", Font.BOLD, 14));

		JLabel lblVille_2 = new JLabel("Ville :");
		lblVille_2.setBounds(50, 99, 73, 17);
		panel_info.add(lblVille_2);
		lblVille_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblVille_2.setFont(new Font("Tahoma", Font.BOLD, 14));

		txtVille = new JLabel();
		txtVille.setBounds(133, 96, 181, 20);
		panel_info.add(txtVille);
		txtVille.setText(bien.getVille());
		txtVille.setForeground(Color.BLACK);

		txtCodePostale = new JLabel();
		txtCodePostale.setBounds(133, 125, 105, 20);
		panel_info.add(txtCodePostale);
		txtCodePostale.setText(bien.getCp());
		txtCodePostale.setForeground(Color.BLACK);

		lblVille_1 = new JLabel("Année de construction :");
		lblVille_1.setBounds(15, 156, 167, 17);
		panel_info.add(lblVille_1);
		lblVille_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblVille_1.setFont(new Font("Tahoma", Font.BOLD, 14));

		JLabel lblNewLabel_4_1 = new JLabel("Type :");
		lblNewLabel_4_1.setBounds(67, 184, 56, 20);
		panel_info.add(lblNewLabel_4_1);
		lblNewLabel_4_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_4_1.setFont(new Font("Tahoma", Font.BOLD, 14));

		JLabel txtType = new JLabel();
		txtType.setBounds(133, 185, 119, 22);
		panel_info.add(txtType);
		txtType.setText(bien.getType());

		JLabel lblNewLabel_4_1_1 = new JLabel("Statut :");
		lblNewLabel_4_1_1.setBounds(67, 214, 56, 20);
		panel_info.add(lblNewLabel_4_1_1);
		lblNewLabel_4_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_4_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));

		JLabel lblStatut = new JLabel();
		lblStatut.setBounds(133, 215, 167, 22);
		panel_info.add(lblStatut);
		lblStatut.setText(bien.getStatut());

		JLabel lblNewLabel_4_1_2 = new JLabel("Valeur :");
		lblNewLabel_4_1_2.setBounds(67, 245, 56, 20);
		panel_info.add(lblNewLabel_4_1_2);
		lblNewLabel_4_1_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_4_1_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		// Impossible de mettre un point pour faire un double
		txtValeur = new JLabel();
		txtValeur.setBounds(133, 247, 105, 20);
		panel_info.add(txtValeur);
		txtValeur.setText(String.valueOf(bien.getValeur()) + " €");
		txtValeur.setForeground(Color.BLACK);

		JLabel lblNewLabel_4_1_2_1 = new JLabel("Surface :");
		lblNewLabel_4_1_2_1.setBounds(50, 276, 73, 20);
		panel_info.add(lblNewLabel_4_1_2_1);
		lblNewLabel_4_1_2_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_4_1_2_1.setFont(new Font("Tahoma", Font.BOLD, 14));

		txtSurface = new JLabel();
		txtSurface.setBounds(133, 278, 100, 20);
		panel_info.add(txtSurface);
		txtSurface.setText(String.valueOf(bien.getSurface()) + " m²");
		txtSurface.setForeground(Color.BLACK);

		JLabel lblPropritaire = new JLabel("Propriétaire :");
		lblPropritaire.setBounds(23, 307, 100, 14);
		panel_info.add(lblPropritaire);
		lblPropritaire.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPropritaire.setFont(new Font("Tahoma", Font.BOLD, 14));
		JLabel lblProprietaire = new JLabel();
		lblProprietaire.setBounds(133, 305, 296, 22);
		panel_info.add(lblProprietaire);
		ProprietaireDAO proprietaireDAO = new ProprietaireDAO();
		Proprietaire proprietaire = proprietaireDAO.getById(bien.getId_proprietaire());
		lblProprietaire.setText(proprietaire.toString());

		JLabel lblVille_2_1 = new JLabel("Code Postal :");
		lblVille_2_1.setBounds(10, 126, 113, 17);
		panel_info.add(lblVille_2_1);
		lblVille_2_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblVille_2_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		JLabel lblAnnee = new JLabel("");
		lblAnnee.setBounds(192, 159, 73, 14);
		panel_info.add(lblAnnee);
		lblAnnee.setText(String.valueOf(bien.getAnnee()));
		
		JScrollPane scroll_Contrats = new JScrollPane();
		layeredPane.add(scroll_Contrats, "name_989719240449800");
		
		table = new JTable();
		scroll_Contrats.setViewportView(table);
		
		JScrollPane scroll_Visites = new JScrollPane();
		layeredPane.add(scroll_Visites, "name_989722156527600");
		
		table_1 = new JTable();
		scroll_Visites.setViewportView(table_1);
		JLabel btnInfos = new JLabel("Informations");
		btnInfos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				switchPanels(panel_info);
			}
		});
		btnInfos.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnInfos.setBorder(null);
		btnInfos.setOpaque(false);
		btnInfos.setIcon(new ImageIcon(Vue_BienDetails.class.getResource("/img/personal.png")));
		btnInfos.setBounds(10, 95, 123, 86);
		frmModificationDeBien.getContentPane().add(btnInfos);

		JLabel btnPhotos = new JLabel("Photos");
		btnPhotos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				switchPanels(panel_photos);
			}
		});
		btnPhotos.setOpaque(false);
		btnPhotos.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnPhotos.setBorder(null);
		btnPhotos.setIcon(new ImageIcon(Vue_BienDetails.class.getResource("/img/photos.png")));
		btnPhotos.setBounds(10, 188, 123, 86);
		frmModificationDeBien.getContentPane().add(btnPhotos);
		JLabel lblNewLabel_21 = new JLabel("");
		lblNewLabel_21.setOpaque(true);
		lblNewLabel_21.setIcon(new ImageIcon(Vue_AccueilAgent.class.getResource("/img/accueil_bg.jpeg")));
		lblNewLabel_21.setBounds(-16, 0, 1000, 591);
		frmModificationDeBien.getContentPane().add(lblNewLabel_21);

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
					.getScaledInstance(832, 500, Image.SCALE_DEFAULT)));

		} else {
			lblNewLabel_2.setIcon(new ImageIcon(new ImageIcon(Vue_BienModif.class.getResource("/img/no_pics.jpg"))
					.getImage().getScaledInstance(832, 414, Image.SCALE_DEFAULT)));

		}
		btnSuivant.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
		layeredPane.setBackground(new Color(0,0,0,0));

		btnPrecedent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
		
		

		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				printRecord(frmModificationDeBien);
				
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
                if(pageIndex > 0){
                    return Printable.NO_SUCH_PAGE;
                }
                
                // Make 2D Graphics to map content
                Graphics2D graphics2D = (Graphics2D)graphics;
                // Set Graphics Translations
                // A Little Correction here Multiplication was not working so I replaced with addition
                graphics2D.translate(pageFormat.getImageableX()+10, pageFormat.getImageableY()+10);
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
	        if(returningResult){
	            // Use try catch exeption for failure
	            try{
	                // Now call print method inside printerJob to print
	                printerJob.print();
	            }catch (PrinterException printerException){
	                JOptionPane.showMessageDialog(frametoprint, "Print Error: " + printerException.getMessage());
	            }
	        }
		
	}
}
