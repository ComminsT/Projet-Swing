package vue;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
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
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

import dao.BienDAO;
import dao.ComptabiliteDAO;
import dao.ContratlDAO;
import entite.Agent;
import entite.Bien;
import entite.Comptabilite;
import entite.Contratl;
import entite.Locataire;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Vue_LocataireDetails {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					Vue_LocataireDetails window = new Vue_LocataireDetails();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private JFrame frame;
	private JSeparator separator;
	private JLabel txtAdresseMail;
	private JLabel txtNom;
	private JLayeredPane layeredPane;
	private JLabel txtPrenom;
	private JLabel lblPays;
	private JLabel txtAdresse;
	private JLabel txtVille;
	private JLabel txtCodePostale;
	private JLabel lblVille_1;
	private Locataire locataire;
	private Agent agent;
	private JTable table;
	private JTable table_1;

	/**
	 * Create the application.
	 */
	public Vue_LocataireDetails() {
		initialize();

	}

	public Vue_LocataireDetails(Locataire locataire, Agent agent) {
		this.locataire = locataire;
		this.agent = agent;
		initialize();
	}
	public Vue_LocataireDetails(Locataire locataire) {
		this.locataire = locataire;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		frame = new JFrame();
		frame.setBounds(100, 100, 981, 620);
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);

		JSeparator separator_1 = new JSeparator();
		separator_1.setOrientation(SwingConstants.VERTICAL);
		separator_1.setBounds(502, 82, 2, 519);
		frame.getContentPane().add(separator_1);

		JLabel btnNewButton = new JLabel("Imprimer");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				printRecord(frame);
			}
		});
		btnNewButton.setHorizontalTextPosition(SwingConstants.CENTER);
		btnNewButton.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnNewButton.setIcon(new ImageIcon(Vue_LocataireDetails.class.getResource("/img/print.png")));

		btnNewButton.setBounds(908, 7, 57, 68);
		frame.getContentPane().add(btnNewButton);

		separator = new JSeparator();
		separator.setForeground(Color.LIGHT_GRAY);
		separator.setBounds(0, 82, frame.getWidth(), 2);
		frame.getContentPane().add(separator);

		JLabel lblNewLabel_2 = new JLabel("Modifications des informations du locataire");
		lblNewLabel_2.setBounds(277, 26, 275, 16);
		frame.getContentPane().add(lblNewLabel_2);

		JLabel btnAnnuler = new JLabel("Retour");
		btnAnnuler.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.dispose();
				new Vue_LocatairesList(agent).getFrame().setVisible(true);

			}
		});
		btnAnnuler.setHorizontalTextPosition(SwingConstants.CENTER);
		btnAnnuler.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnAnnuler.setIcon(new ImageIcon(Vue_LocataireDetails.class.getResource("/img/back.png")));
		btnAnnuler.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAnnuler.setBounds(24, 7, 48, 68);
		frame.getContentPane().add(btnAnnuler);
		String[] birth = locataire.getNaissance().split("-");

		JLabel btnInformations = new JLabel("Informations personnel");
		
		btnInformations.setBackground(Color.WHITE);
		btnInformations.setBorder(null);
		btnInformations.setIcon(new ImageIcon(Vue_LocataireDetails.class.getResource("/img/personal.png")));

		btnInformations.setBounds(0, 82, 191, 40);
		frame.getContentPane().add(btnInformations);

		JLabel btnHistorique = new JLabel("Historique de paiement");

		btnHistorique.setBackground(Color.WHITE);
		btnHistorique.setBorder(null);
		btnHistorique.setIcon(new ImageIcon(Vue_LocataireDetails.class.getResource("/img/compta.png")));

		btnHistorique.setBounds(0, 146, 199, 48);
		frame.getContentPane().add(btnHistorique);

		JLabel btnContrats = new JLabel("Contrats");

		btnContrats.setBackground(Color.WHITE);
		btnContrats.setBorder(null);
		btnContrats.setIcon(new ImageIcon(Vue_LocataireDetails.class.getResource("/img/contract.png")));
		btnContrats.setBounds(0, 218, 115, 60);
		frame.getContentPane().add(btnContrats);

		layeredPane = new JLayeredPane();
		layeredPane.setBounds(236, 82, 745, 519);
		frame.getContentPane().add(layeredPane);
		layeredPane.setLayout(new CardLayout(0, 0));

		JPanel panelInfos = new JPanel();
		panelInfos.setOpaque(false);
		layeredPane.add(panelInfos, "name_429148076291500");
		panelInfos.setLayout(null);

		JLabel lblNewLabel = new JLabel("Nom :");
		lblNewLabel.setBounds(10, 11, 103, 14);
		panelInfos.add(lblNewLabel);
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));

		txtPrenom = new JLabel();
		txtPrenom.setBounds(123, 42, 134, 20);
		panelInfos.add(txtPrenom);
		txtPrenom.setForeground(Color.BLACK);
		txtPrenom.setText(locataire.getPrenom());

		txtNom = new JLabel();
		txtNom.setBounds(123, 11, 134, 20);
		panelInfos.add(txtNom);
		txtNom.setForeground(Color.BLACK);
		txtNom.setText(locataire.getNom());

		JLabel lblMail = new JLabel("Mail :");
		lblMail.setBounds(24, 67, 89, 14);
		panelInfos.add(lblMail);
		lblMail.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMail.setFont(new Font("Tahoma", Font.BOLD, 14));

		txtAdresseMail = new JLabel();
		txtAdresseMail.setBounds(123, 67, 134, 20);
		panelInfos.add(txtAdresseMail);

		txtAdresseMail.setText(locataire.getMail());
		txtAdresseMail.setForeground(Color.BLACK);

		lblPays = new JLabel("Pays :");
		lblPays.setBounds(24, 92, 89, 17);
		panelInfos.add(lblPays);
		lblPays.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPays.setFont(new Font("Tahoma", Font.BOLD, 14));

		txtAdresse = new JLabel();
		txtAdresse.setBounds(123, 121, 226, 20);
		panelInfos.add(txtAdresse);
		txtAdresse.setText(locataire.getAdresse());
		txtAdresse.setForeground(Color.BLACK);

		JLabel lblVille = new JLabel("Adresse :");
		lblVille.setBounds(24, 120, 89, 17);
		panelInfos.add(lblVille);
		lblVille.setHorizontalAlignment(SwingConstants.RIGHT);
		lblVille.setFont(new Font("Tahoma", Font.BOLD, 14));

		JLabel lblVille_2 = new JLabel("Ville :");
		lblVille_2.setBounds(24, 148, 89, 17);
		panelInfos.add(lblVille_2);
		lblVille_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblVille_2.setFont(new Font("Tahoma", Font.BOLD, 14));

		txtVille = new JLabel();
		txtVille.setBounds(123, 149, 181, 20);
		panelInfos.add(txtVille);
		txtVille.setText(locataire.getVille());
		txtVille.setForeground(Color.BLACK);

		txtCodePostale = new JLabel();
		txtCodePostale.setBounds(123, 176, 105, 20);
		panelInfos.add(txtCodePostale);
		txtCodePostale.setText(locataire.getCp());
		txtCodePostale.setForeground(Color.BLACK);

		lblVille_1 = new JLabel("Date de naissance :");
		lblVille_1.setBounds(10, 279, 141, 17);
		panelInfos.add(lblVille_1);
		lblVille_1.setFont(new Font("Tahoma", Font.BOLD, 14));

		JLabel lblNewLabel_4 = new JLabel("Telephone :");
		lblNewLabel_4.setBounds(10, 254, 103, 14);
		panelInfos.add(lblNewLabel_4);
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 14));

		JLabel lblNewLabel_4_1 = new JLabel("Situation :");
		lblNewLabel_4_1.setBounds(13, 204, 100, 14);
		panelInfos.add(lblNewLabel_4_1);
		lblNewLabel_4_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_4_1.setFont(new Font("Tahoma", Font.BOLD, 14));

		JLabel lblNewLabel_4_1_1 = new JLabel("Statut :");
		lblNewLabel_4_1_1.setBounds(23, 229, 90, 14);
		panelInfos.add(lblNewLabel_4_1_1);
		lblNewLabel_4_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_4_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));

		JLabel lblPrnom = new JLabel("Prénom :");
		lblPrnom.setBounds(10, 42, 103, 14);
		panelInfos.add(lblPrnom);
		lblPrnom.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPrnom.setFont(new Font("Tahoma", Font.BOLD, 14));

		JLabel txtPays = new JLabel("New label");
		txtPays.setBounds(123, 95, 181, 14);
		panelInfos.add(txtPays);
		txtPays.setText(locataire.getPays());

		JLabel txtSituation = new JLabel("New label");
		txtSituation.setBounds(123, 207, 105, 14);
		panelInfos.add(txtSituation);
		txtSituation.setText(locataire.getSituation());

		JLabel txtStatut = new JLabel("New label");
		txtStatut.setBounds(123, 229, 105, 14);
		panelInfos.add(txtStatut);
		txtStatut.setText(locataire.getStatut());

		JLabel txtTelephone = new JLabel("New label");
		txtTelephone.setBounds(123, 256, 89, 14);
		panelInfos.add(txtTelephone);
		txtTelephone.setText(locataire.getTel());

		JLabel txtDateOfBirth = new JLabel("New label");
		txtDateOfBirth.setBounds(150, 282, 181, 14);
		panelInfos.add(txtDateOfBirth);
		txtDateOfBirth.setText(birth[2] + "/" + birth[1] + "/" + birth[0]);

		JLabel lblVille_2_1 = new JLabel("Code postal :");
		lblVille_2_1.setBounds(0, 176, 113, 17);
		panelInfos.add(lblVille_2_1);
		lblVille_2_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblVille_2_1.setFont(new Font("Tahoma", Font.BOLD, 14));

		JScrollPane scrollPane = new JScrollPane();
		layeredPane.add(scrollPane, "name_68207472195916");

		ComptabiliteDAO comptaDAO = new ComptabiliteDAO();
		ArrayList<Comptabilite> comptas = comptaDAO.getAllByLocataireId(locataire.getId());

		String[] columns = { "ID", "Date dû", "Date payé" };
		String[][] data = new String[comptas.size()][columns.length];
		int i = 0;
		for (Comptabilite c : comptas) {
			data[i][0] = c.getId() + "";
			data[i][1] = c.getDatedue();
			data[i][2] = c.getDatepaye();
			i++;
		}
		DefaultTableModel model = new DefaultTableModel(data, columns);
		table = new JTable(model);
		scrollPane.setViewportView(table);

		JScrollPane scrollPane_1 = new JScrollPane();
		layeredPane.add(scrollPane_1, "name_100872588406875");
		ContratlDAO contratDAO = new ContratlDAO();
		ArrayList<Contratl> contrats = contratDAO.getAllByLocataireId(locataire.getId());
		String[] columns2 = { "ID", "Bien concerné", "Date début", "Date fin" };
		String[][] data2 = new String[contrats.size()][columns2.length];
		i = 0;
		for (Contratl c : contrats) {
			String datefin = "";
			BienDAO bienDAO = new BienDAO();
			Bien bien = bienDAO.getById(c.getId_bien());
			if (c.getDatefin() != null) {
				datefin = c.getDatefin() + "";
			} else {
				datefin = "Non terminé";
			}
			data2[i][0] = c.getId() + "";
			data2[i][1] = bien.toString();
			data2[i][2] = c.getDate();
			data2[i][3] = datefin;
			i++;

		}
		DefaultTableModel model2 = new DefaultTableModel(data2, columns2);

		table_1 = new JTable(model2);
		scrollPane_1.setViewportView(table_1);
		btnContrats.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				switchScrollPane(scrollPane_1);
			}
		});
		btnHistorique.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				switchScrollPane(scrollPane);
			}
		});
		btnInformations.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				switchPanels(panelInfos);
			}
		});
		JLabel lblBG = new JLabel("");
		lblBG.setOpaque(true);
		lblBG.setIcon(new ImageIcon(Vue_AccueilAgent.class.getResource("/img/accueil_bg.jpeg")));
		lblBG.setBounds(-16, 0, 1000, 591);
		frame.getContentPane().add(lblBG);
	}

	public void switchPanels(JPanel panel) {
		layeredPane.removeAll();
		layeredPane.add(panel);
		layeredPane.repaint();
		layeredPane.revalidate();
	}

	public void switchScrollPane(JScrollPane panel) {
		layeredPane.removeAll();
		layeredPane.add(panel);
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

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}
}