package vue;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.util.ArrayList;

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
import entite.Agent;
import entite.Bien;
import entite.Proprietaire;
import javax.swing.ImageIcon;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Vue_ProprietaireDetails {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					Vue_ProprietaireDetails window = new Vue_ProprietaireDetails();
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
	private Agent agent;
	private Proprietaire proprietaire;
	private JTable table;

	/**
	 * Create the application.
	 */
	public Vue_ProprietaireDetails() {
		initialize();

	}

	public Vue_ProprietaireDetails(Proprietaire proprietaire, Agent agent) {
		this.proprietaire = proprietaire;
		this.agent = agent;
		initialize();
	}
	public Vue_ProprietaireDetails(Proprietaire proprietaire) {
		this.proprietaire = proprietaire;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		frame = new JFrame();
		frame.setBounds(100, 100, 981, 630);
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);

		JLabel btnNewButton = new JLabel("Imprimer");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				printRecord(frame);
			}
		});
		btnNewButton.setHorizontalTextPosition(SwingConstants.CENTER);
		btnNewButton.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnNewButton.setIcon(new ImageIcon(Vue_ProprietaireDetails.class.getResource("/img/print.png")));

		btnNewButton.setBounds(911, 11, 52, 68);
		frame.getContentPane().add(btnNewButton);

		separator = new JSeparator();
		separator.setForeground(Color.LIGHT_GRAY);
		separator.setBounds(0, 82, frame.getWidth(), 2);
		frame.getContentPane().add(separator);

		JLabel lblNewLabel_2 = new JLabel("Détails propriétaire");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(427, 30, 121, 16);
		frame.getContentPane().add(lblNewLabel_2);

		JLabel btnAnnuler = new JLabel("Retour");
		btnAnnuler.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.dispose();
				new Vue_ProprietairesList(agent).getFrame().setVisible(true);
			}
		});
		btnAnnuler.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAnnuler.setHorizontalTextPosition(SwingConstants.CENTER);
		btnAnnuler.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnAnnuler.setIcon(new ImageIcon(Vue_ProprietaireDetails.class.getResource("/img/back.png")));
		btnAnnuler.setBounds(11, 11, 48, 68);
		frame.getContentPane().add(btnAnnuler);
		String[] birth = proprietaire.getNaissance().split("-");
		
		JPanel panel = new JPanel();
		panel.setOpaque(false);
		panel.setBounds(0, 82, 270, 264);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		JLabel btnInformations = new JLabel("Informations personnelles");
		btnInformations.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		btnInformations.setIcon(new ImageIcon(Vue_ProprietaireDetails.class.getResource("/img/personal.png")));
		btnInformations.setOpaque(false);
		btnInformations.setBorder(null);
		btnInformations.setBounds(0, 58, 193, 40);
		panel.add(btnInformations);
		
				JLabel btnContrats = new JLabel("Contrats");
				btnContrats.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				
				btnContrats.setOpaque(false);
				btnContrats.setIcon(new ImageIcon(Vue_ProprietaireDetails.class.getResource("/img/bien.png")));
				btnContrats.setBorder(null);
				btnContrats.setBounds(0, 156, 101, 48);
				panel.add(btnContrats);

		layeredPane = new JLayeredPane();
		layeredPane.setBounds(282, 90, 660, 488);
		frame.getContentPane().add(layeredPane);
		layeredPane.setLayout(new CardLayout(0, 0));

		JPanel panelInfos = new JPanel();
		layeredPane.add(panelInfos, "name_429148076291500");
		panelInfos.setLayout(null);
		panelInfos.setBackground(new Color(255,255,255,100));

		JLabel lblNewLabel = new JLabel("Nom :");
		lblNewLabel.setBounds(10, 10, 141, 17);
		panelInfos.add(lblNewLabel);
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));

		txtPrenom = new JLabel();
		txtPrenom.setBounds(163, 64, 202, 17);
		panelInfos.add(txtPrenom);
		txtPrenom.setForeground(Color.BLACK);
		txtPrenom.setText(proprietaire.getPrenom());

		txtNom = new JLabel();
		txtNom.setBounds(163, 10, 163, 17);
		panelInfos.add(txtNom);
		txtNom.setForeground(Color.BLACK);
		txtNom.setText(proprietaire.getNom());

		JLabel lblMail = new JLabel("Mail :");
		lblMail.setBounds(10, 118, 141, 17);
		panelInfos.add(lblMail);
		lblMail.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMail.setFont(new Font("Tahoma", Font.BOLD, 14));

		txtAdresseMail = new JLabel();
		txtAdresseMail.setBounds(163, 118, 202, 17);
		panelInfos.add(txtAdresseMail);

		txtAdresseMail.setText(proprietaire.getMail());
		txtAdresseMail.setForeground(Color.BLACK);

		lblPays = new JLabel("Pays :");
		lblPays.setBounds(10, 172, 141, 17);
		panelInfos.add(lblPays);
		lblPays.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPays.setFont(new Font("Tahoma", Font.BOLD, 14));

		txtAdresse = new JLabel();
		txtAdresse.setBounds(163, 226, 226, 17);
		panelInfos.add(txtAdresse);
		txtAdresse.setText(proprietaire.getAdresse());
		txtAdresse.setForeground(Color.BLACK);

		JLabel lblVille = new JLabel("Adresse :");
		lblVille.setBounds(10, 226, 141, 17);
		panelInfos.add(lblVille);
		lblVille.setHorizontalAlignment(SwingConstants.RIGHT);
		lblVille.setFont(new Font("Tahoma", Font.BOLD, 14));

		JLabel lblVille_2 = new JLabel("Ville :");
		lblVille_2.setBounds(10, 280, 141, 17);
		panelInfos.add(lblVille_2);
		lblVille_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblVille_2.setFont(new Font("Tahoma", Font.BOLD, 14));

		txtVille = new JLabel();
		txtVille.setBounds(163, 280, 181, 17);
		panelInfos.add(txtVille);
		txtVille.setText(proprietaire.getVille());
		txtVille.setForeground(Color.BLACK);

		txtCodePostale = new JLabel();
		txtCodePostale.setBounds(163, 334, 105, 17);
		panelInfos.add(txtCodePostale);
		txtCodePostale.setText(proprietaire.getCp());
		txtCodePostale.setForeground(Color.BLACK);

		lblVille_1 = new JLabel("Date de naissance :");
		lblVille_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblVille_1.setBounds(10, 442, 141, 17);
		panelInfos.add(lblVille_1);
		lblVille_1.setFont(new Font("Tahoma", Font.BOLD, 14));

		JLabel lblNewLabel_4 = new JLabel("Téléphone :");
		lblNewLabel_4.setBounds(10, 388, 141, 17);
		panelInfos.add(lblNewLabel_4);
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 14));

		JLabel lblPrnom = new JLabel("Prénom :");
		lblPrnom.setBounds(10, 64, 141, 17);
		panelInfos.add(lblPrnom);
		lblPrnom.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPrnom.setFont(new Font("Tahoma", Font.BOLD, 14));

		JLabel txtPays = new JLabel("New label");
		txtPays.setBounds(163, 172, 181, 17);
		panelInfos.add(txtPays);
		txtPays.setText(proprietaire.getPays());

		JLabel txtTelephone = new JLabel("New label");
		txtTelephone.setBounds(163, 388, 89, 17);
		panelInfos.add(txtTelephone);
		txtTelephone.setText(proprietaire.getTel());

		JLabel txtDateOfBirth = new JLabel("New label");
		txtDateOfBirth.setBounds(163, 443, 326, 17);
		panelInfos.add(txtDateOfBirth);
		txtDateOfBirth.setText(birth[2] + "/" + birth[1] + "/" + birth[0]);

		JLabel lblVille_2_1 = new JLabel("Code postal :");
		lblVille_2_1.setBounds(10, 334, 141, 17);
		panelInfos.add(lblVille_2_1);
		lblVille_2_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblVille_2_1.setFont(new Font("Tahoma", Font.BOLD, 14));

		JScrollPane scrollPane = new JScrollPane();
		layeredPane.add(scrollPane, "name_102472273351875");

		BienDAO bienDAO = new BienDAO();
		ArrayList<Bien> biens = bienDAO.getAllByProprietaireId(proprietaire.getId());
		String[] columns = { "ID", "Nom", "Ville" };
		String[][] data = new String[biens.size()][columns.length];
		int i = 0;
		for (Bien b : biens) {
			data[i][0] = b.getId() + "";
			data[i][1] = b.getNom();
			data[i][2] = b.getVille();
			i++;
		}
		DefaultTableModel model = new DefaultTableModel(data, columns);
		table = new JTable(model);
		scrollPane.setViewportView(table);
		JLabel lblBG = new JLabel("");
		lblBG.setOpaque(true);
		lblBG.setIcon(new ImageIcon(Vue_AccueilAgent.class.getResource("/img/accueil_bg.jpeg")));
		lblBG.setBounds(-16, 0, 1014, 601);
		frame.getContentPane().add(lblBG);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(269, 82, 1, 519);
		frame.getContentPane().add(separator_1);
		btnInformations.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				switchPanels(panelInfos);
			}
		});
		btnContrats.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				switchScrollPane(scrollPane);
			}
		});
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