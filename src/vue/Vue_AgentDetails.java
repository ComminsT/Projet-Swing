package vue;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
import javax.swing.table.DefaultTableModel;

import dao.HistoriqueDAO;
import dao.LocataireDAO;
import dao.ProprietaireDAO;
import entite.Agent;
import entite.Historique;
import entite.Locataire;
import entite.Proprietaire;

public class Vue_AgentDetails {

	private JFrame frame;
	private Agent agent;
	private JSeparator separator;
	private JLabel btnImprimer;
	private JLabel textField;
	private JLabel textField_4;
	private JLabel textField_5;
	private JLayeredPane layeredPane;
	private JTable table_Historique;
	private JTable table_Clients;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Vue_AgentDetails window = new Vue_AgentDetails();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Vue_AgentDetails() {
		initialize();
	}

	public Vue_AgentDetails(Agent agent) {
		this.agent = agent;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 973, 619);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		separator = new JSeparator();
		separator.setBackground(Color.GRAY);
		separator.setForeground(Color.GRAY);
		separator.setBounds(0, 82, 973, 2);
		frame.getContentPane().add(separator);
		frame.getContentPane().setLayout(null);

		JLabel btnRetour = new JLabel("Retour");
		btnRetour.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.dispose();
				new Vue_AccueilAdmin().getFrame().setVisible(true);
			}
		});
		btnRetour.setIcon(new ImageIcon(Vue_AgentDetails.class.getResource("/img/back.png")));
		btnRetour.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnRetour.setOpaque(false);
		btnRetour.setHorizontalTextPosition(SwingConstants.CENTER);
		btnRetour.setBounds(6, 6, 62, 68);
		frame.getContentPane().add(btnRetour);

		btnImprimer = new JLabel("Imprimer");
		btnImprimer.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				printRecord(frame);
			}
		});
		btnImprimer.setIcon(new ImageIcon(Vue_AgentDetails.class.getResource("/img/print.png")));
		btnImprimer.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnImprimer.setOpaque(false);
		btnImprimer.setHorizontalTextPosition(SwingConstants.CENTER);
		btnImprimer.setHorizontalAlignment(SwingConstants.CENTER);
		btnImprimer.setBounds(892, 6, 81, 68);
		frame.getContentPane().add(btnImprimer);

		layeredPane = new JLayeredPane();
		layeredPane.setBounds(169, 117, 774, 461);
		frame.getContentPane().add(layeredPane);
		layeredPane.setLayout(new CardLayout(0, 0));

		JPanel panel_infos = new JPanel();
		layeredPane.add(panel_infos, "name_53660680559042");
		panel_infos.setOpaque(false);
		panel_infos.setLayout(null);

		textField = new JLabel();
		textField.setText((String) null);
		textField.setForeground(Color.BLACK);
		textField.setBounds(6, 50, 172, 20);
		panel_infos.add(textField);
		textField.setText(agent.getNom());

		JLabel lblNewLabel = new JLabel("Nom");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(6, 18, 46, 14);
		panel_infos.add(lblNewLabel);

		JLabel lblMail = new JLabel("Mail");
		lblMail.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblMail.setBounds(6, 161, 46, 14);
		panel_infos.add(lblMail);

		JLabel lblVille = new JLabel("Adresse");
		lblVille.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblVille.setBounds(6, 231, 55, 17);
		panel_infos.add(lblVille);

		textField_4 = new JLabel();
		textField_4.setText((String) null);
		textField_4.setForeground(Color.BLACK);
		textField_4.setBounds(6, 266, 284, 20);
		panel_infos.add(textField_4);
		textField_4.setText(agent.getAdresse());

		JLabel lblVille_2 = new JLabel("Ville");
		lblVille_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblVille_2.setBounds(6, 304, 29, 17);
		panel_infos.add(lblVille_2);

		textField_5 = new JLabel();
		textField_5.setText((String) null);
		textField_5.setForeground(Color.BLACK);
		textField_5.setBounds(6, 339, 229, 20);
		panel_infos.add(textField_5);
		textField_5.setText(agent.getCp() + " " + agent.getVille());

		JLabel textField_2 = new JLabel();
		textField_2.setText((String) null);
		textField_2.setForeground(Color.BLACK);
		textField_2.setBounds(6, 193, 284, 20);
		panel_infos.add(textField_2);
		textField_2.setText(agent.getMail());

		JLabel lblVille_2_1 = new JLabel("Date d'entrée dans l'entreprise");
		lblVille_2_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblVille_2_1.setBounds(6, 377, 215, 17);
		panel_infos.add(lblVille_2_1);
		String[] date = agent.getDateentree().split("-");

		JLabel textField_4_1 = new JLabel();
		textField_4_1.setText((String) null);
		textField_4_1.setForeground(Color.BLACK);
		textField_4_1.setBounds(6, 412, 262, 20);
		panel_infos.add(textField_4_1);
		textField_4_1.setText(date[2] + "/" + date[1] + "/" + date[0]);

		JLabel lblPrenom = new JLabel("Prenom");
		lblPrenom.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblPrenom.setBounds(6, 88, 54, 17);
		panel_infos.add(lblPrenom);

		JLabel textField_1 = new JLabel();
		textField_1.setText((String) null);
		textField_1.setForeground(Color.BLACK);
		textField_1.setBounds(6, 123, 149, 20);
		panel_infos.add(textField_1);
		textField_1.setText(agent.getPrenom());

		JLabel lblNewLabel_4 = new JLabel("Numéro de telephone");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_4.setBounds(384, 18, 192, 14);
		panel_infos.add(lblNewLabel_4);

		JLabel textField_3 = new JLabel();
		textField_3.setText((String) null);
		textField_3.setForeground(Color.BLACK);
		textField_3.setBounds(384, 50, 246, 20);
		panel_infos.add(textField_3);
		textField_3.setText(agent.getTel());

		JLabel lblNewLabel_4_1 = new JLabel("Statut");
		lblNewLabel_4_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_4_1.setBounds(384, 88, 192, 14);
		panel_infos.add(lblNewLabel_4_1);

		JLabel textField_3_1 = new JLabel();
		textField_3_1.setText((String) null);
		textField_3_1.setForeground(Color.BLACK);
		textField_3_1.setBounds(384, 120, 246, 20);
		panel_infos.add(textField_3_1);
		textField_3_1.setText(agent.getStatut());

		JScrollPane scrollPane_Historique = new JScrollPane();
		layeredPane.add(scrollPane_Historique, "name_53670529992000");
		HistoriqueDAO historiqueDAO = new HistoriqueDAO();
		ArrayList<Historique> historique = historiqueDAO.getAllByAgentId(agent.getId());
		String[] columns = { "ID", "Action", "Date" };
		String[][] data = new String[historique.size()][columns.length];
		int i = 0;
		for (Historique h : historique) {
			data[i][0] = h.getId() + "";
			data[i][1] = h.getAction();
			data[i][2] = h.getDate();
			i++;
		}
		DefaultTableModel model = new DefaultTableModel(data, columns);

		table_Historique = new JTable(model);
		scrollPane_Historique.setViewportView(table_Historique);

		JScrollPane scrollPane_Clients = new JScrollPane();
		layeredPane.add(scrollPane_Clients, "name_53945579736125");
		LocataireDAO locataireDAO = new LocataireDAO();
		ArrayList<Locataire> locataires = locataireDAO.getAllFromAgent(agent.getId());
		ProprietaireDAO proprietaireDAO = new ProprietaireDAO();
		ArrayList<Proprietaire> proprietaires = proprietaireDAO.getAllByIdAgent(agent.getId());
		String[] columns2 = { "ID", "Type", "Nom" };
		String[][] data2 = new String[locataires.size() + proprietaires.size()][columns2.length];
		i = 0;
		for (Locataire l : locataires) {
			String[] type = l.getClass().toString().split("\\.");
			data2[i][0] = l.getId() + "";
			data2[i][1] = type[1];
			data2[i][2] = l.getNom() + " " + l.getPrenom();
			i++;
		}
		for (Proprietaire p : proprietaires) {
			String[] type = p.getClass().toString().split("\\.");
			data2[i][0] = p.getId() + "";
			data2[i][1] = type[1];
			data2[i][2] = p.getNom() + " " + p.getPrenom();
			i++;
		}
		DefaultTableModel model2 = new DefaultTableModel(data2, columns2);
		table_Clients = new JTable(model2);
		scrollPane_Clients.setViewportView(table_Clients);
		table_Clients.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int row = table_Clients.convertRowIndexToModel(table_Clients.getSelectedRow());
				int selectedId = Integer.parseInt(model2.getValueAt(row, 0).toString());
				if(table_Clients.getValueAt(row, 2).equals("Locataire")) {
					Locataire locataire = locataireDAO.getById(selectedId);
					frame.dispose();
					new Vue_LocataireDetails(locataire).getFrame().setVisible(true);
				}else {
					Proprietaire proprietaire = proprietaireDAO.getById(selectedId);
					frame.dispose();
					new Vue_ProprietaireDetails(proprietaire).getFrame().setVisible(true);
					
				}
			}
		});

		JPanel panel = new JPanel();
		panel.setOpaque(false);
		panel.setBounds(12, 117, 138, 461);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		JButton btnNewButton = new JButton("Informations");

		btnNewButton.setBounds(12, 55, 113, 40);
		btnNewButton.setIcon(new ImageIcon(Vue_AgentDetails.class.getResource("/img/personal.png")));
		btnNewButton.setBorder(null);
		btnNewButton.setBorderPainted(false);
		btnNewButton.setBackground(Color.WHITE);
		panel.add(btnNewButton);

		JButton btnHistorique = new JButton("Historique");

		btnHistorique.setBounds(15, 150, 108, 48);
		btnHistorique.setIcon(new ImageIcon(Vue_AgentDetails.class.getResource("/img/historique.png")));
		btnHistorique.setBorderPainted(false);
		btnHistorique.setBorder(null);
		btnHistorique.setBackground(Color.WHITE);
		panel.add(btnHistorique);

		JButton btnPerformance = new JButton("Performance");
		btnPerformance.setBounds(9, 253, 120, 48);
		btnPerformance.setIcon(new ImageIcon(Vue_AgentDetails.class.getResource("/img/performance.png")));
		btnPerformance.setBorderPainted(false);
		btnPerformance.setBorder(null);
		btnPerformance.setBackground(Color.WHITE);
		panel.add(btnPerformance);

		JButton btnClients = new JButton("Clients");

		btnClients.setIcon(new ImageIcon(Vue_AgentDetails.class.getResource("/img/clients.png")));
		btnClients.setBorderPainted(false);
		btnClients.setBorder(null);
		btnClients.setBackground(Color.WHITE);
		btnClients.setBounds(5, 356, 120, 48);
		panel.add(btnClients);

		JLabel lblBG = new JLabel("");
		lblBG.setOpaque(true);
		lblBG.setIcon(new ImageIcon(Vue_AccueilAgent.class.getResource("/img/accueil_bg.jpeg")));
		lblBG.setBounds(-27, 6, 1000, 591);
		frame.getContentPane().add(lblBG);
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				switchPanels(panel_infos);
			}
		});
		btnHistorique.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				switchScrollPane(scrollPane_Historique);
			}
		});
		btnClients.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				switchScrollPane(scrollPane_Clients);
			}
		});
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

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}
}
