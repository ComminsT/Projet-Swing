package vue;

import java.awt.BasicStroke;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Cursor;
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
import java.util.Collections;

import javax.swing.BorderFactory;
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

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import dao.ContratlDAO;
import dao.HistoriqueDAO;
import dao.LocataireDAO;
import dao.ProprietaireDAO;
import entite.Agent;
import entite.Historique;
import entite.Locataire;
import entite.Proprietaire;

public class Vue_AgentDetailsA {

	private JFrame frmDtailDeLagent;
	private Agent agent;
	private JSeparator separator;
	private JLabel btnImprimer;
	private JLabel textField;
	private JLabel textField_4;
	private JLabel textField_5;
	private JLayeredPane layeredPane;
	private JTable table_Clients;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Vue_AgentDetailsA window = new Vue_AgentDetailsA();
					window.frmDtailDeLagent.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Vue_AgentDetailsA() {
		initialize();
	}

	public Vue_AgentDetailsA(Agent agent) {
		this.agent = agent;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmDtailDeLagent = new JFrame();
		frmDtailDeLagent.setTitle("Informations personnelles");
		frmDtailDeLagent.setBounds(100, 100, 973, 600);
		frmDtailDeLagent.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmDtailDeLagent.setLocationRelativeTo(null);
		separator = new JSeparator();
		separator.setBackground(Color.GRAY);
		separator.setForeground(Color.GRAY);
		separator.setBounds(0, 82, 973, 2);
		frmDtailDeLagent.getContentPane().add(separator);
		frmDtailDeLagent.getContentPane().setLayout(null);
		

		JLabel btnRetour = new JLabel("Retour");
		btnRetour.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnRetour.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frmDtailDeLagent.dispose();
				new Vue_AccueilAgent(agent).getFrame().setVisible(true);
			}
		});
		btnRetour.setIcon(new ImageIcon(Vue_AgentDetails.class.getResource("/img/back.png")));
		btnRetour.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnRetour.setOpaque(false);
		btnRetour.setHorizontalTextPosition(SwingConstants.CENTER);
		btnRetour.setBounds(11, 11, 48, 68);
		frmDtailDeLagent.getContentPane().add(btnRetour);

		btnImprimer = new JLabel("Imprimer");
		btnImprimer.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnImprimer.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				printRecord(frmDtailDeLagent);
			}
		});
		btnImprimer.setIcon(new ImageIcon(Vue_AgentDetails.class.getResource("/img/print.png")));
		btnImprimer.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnImprimer.setOpaque(false);
		btnImprimer.setHorizontalTextPosition(SwingConstants.CENTER);
		btnImprimer.setHorizontalAlignment(SwingConstants.CENTER);
		btnImprimer.setBounds(895, 11, 52, 68);
		frmDtailDeLagent.getContentPane().add(btnImprimer);

		layeredPane = new JLayeredPane();
		layeredPane.setBounds(169, 96, 762, 450);
		frmDtailDeLagent.getContentPane().add(layeredPane);
		layeredPane.setLayout(new CardLayout(0, 0));

		JPanel panel_infos = new JPanel();
		layeredPane.add(panel_infos, "name_53660680559042");
		panel_infos.setLayout(null);
		panel_infos.setBackground(new Color(255,255,255,100));

		textField = new JLabel();
		textField.setText((String) null);
		textField.setForeground(Color.BLACK);
		textField.setBounds(180, 34, 172, 20);
		panel_infos.add(textField);
		textField.setText(agent.getNom());

		JLabel lblNewLabel = new JLabel("Nom :");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(7, 36, 42, 17);
		panel_infos.add(lblNewLabel);

		JLabel lblMail = new JLabel("Mail :");
		lblMail.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblMail.setBounds(7, 139, 35, 17);
		panel_infos.add(lblMail);

		JLabel lblVille = new JLabel("Adresse :");
		lblVille.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblVille.setBounds(7, 189, 64, 17);
		panel_infos.add(lblVille);

		textField_4 = new JLabel();
		textField_4.setText((String) null);
		textField_4.setForeground(Color.BLACK);
		textField_4.setBounds(180, 187, 284, 20);
		panel_infos.add(textField_4);
		textField_4.setText(agent.getAdresse());

		JLabel lblVille_2 = new JLabel("Ville :");
		lblVille_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblVille_2.setBounds(7, 242, 36, 17);
		panel_infos.add(lblVille_2);

		textField_5 = new JLabel();
		textField_5.setText((String) null);
		textField_5.setForeground(Color.BLACK);
		textField_5.setBounds(180, 240, 229, 20);
		panel_infos.add(textField_5);
		textField_5.setText(agent.getCp() + " " + agent.getVille());

		JLabel textField_2 = new JLabel();
		textField_2.setText((String) null);
		textField_2.setForeground(Color.BLACK);
		textField_2.setBounds(180, 137, 284, 20);
		panel_infos.add(textField_2);
		textField_2.setText(agent.getMail());

		JLabel lblVille_2_1 = new JLabel("Date d'entrée :");
		lblVille_2_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblVille_2_1.setBounds(7, 295, 221, 17);
		panel_infos.add(lblVille_2_1);
		String[] date = agent.getDateentree().split("-");

		JLabel textField_4_1 = new JLabel();
		textField_4_1.setText((String) null);
		textField_4_1.setForeground(Color.BLACK);
		textField_4_1.setBounds(180, 293, 262, 20);
		panel_infos.add(textField_4_1);
		textField_4_1.setText(date[2] + "/" + date[1] + "/" + date[0]);

		JLabel lblPrenom = new JLabel("Prénom :");
		lblPrenom.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblPrenom.setBounds(7, 86, 63, 17);
		panel_infos.add(lblPrenom);

		JLabel textField_1 = new JLabel();
		textField_1.setText((String) null);
		textField_1.setForeground(Color.BLACK);
		textField_1.setBounds(180, 84, 149, 20);
		panel_infos.add(textField_1);
		textField_1.setText(agent.getPrenom());

		JLabel lblNewLabel_4 = new JLabel("Numéro de téléphone :");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_4.setBounds(7, 348, 159, 17);
		panel_infos.add(lblNewLabel_4);

		JLabel textField_3 = new JLabel();
		textField_3.setText((String) null);
		textField_3.setForeground(Color.BLACK);
		textField_3.setBounds(180, 346, 246, 20);
		panel_infos.add(textField_3);
		textField_3.setText(agent.getTel());

		JLabel lblNewLabel_4_1 = new JLabel("Statut :");
		lblNewLabel_4_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_4_1.setBounds(7, 398, 53, 17);
		panel_infos.add(lblNewLabel_4_1);

		JLabel textField_3_1 = new JLabel();
		textField_3_1.setText((String) null);
		textField_3_1.setForeground(Color.BLACK);
		textField_3_1.setBounds(180, 396, 246, 20);
		panel_infos.add(textField_3_1);
		textField_3_1.setText(agent.getStatut());
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
		
		ContratlDAO contratDAO = new ContratlDAO();
		ArrayList<Integer> result=contratDAO.getcontratCountByAgentId(agent.getId());
		System.out.println(result);
		var series = new XYSeries("Propriétaire");
		series.add(1, result.get(0));
		series.add(2, result.get(1));
		series.add(3, result.get(2));
		series.add(4, result.get(3));
		series.add(5, result.get(4));
		series.add(6, result.get(5));
		series.add(7, result.get(6));
		series.add(8, result.get(7));
		series.add(9, result.get(8));
		series.add(10, result.get(9));
		series.add(11, result.get(10));
		series.add(12, result.get(11));
		var dataset = new XYSeriesCollection();
		dataset.addSeries(series);
		
		int ymin = Collections.min(result);
		int ymax=Collections.max(result);
		JFreeChart chart = ChartFactory.createXYLineChart("Indice de performance","Mois","Quantité",dataset,PlotOrientation.VERTICAL,false,true,false);
		
		XYPlot plot = chart.getXYPlot();
		var renderer = new XYLineAndShapeRenderer();
		renderer.setSeriesPaint(0, Color.RED);
		renderer.setSeriesStroke(0, new BasicStroke(2.0f));
		plot.setRenderer(renderer);
		plot.setBackgroundPaint(Color.white);
		NumberAxis yAxis = (NumberAxis) plot.getRangeAxis();
		yAxis.setLowerBound(ymin);
		yAxis.setUpperBound(ymax+1);
		yAxis.setTickUnit(new NumberTickUnit(1));
		
		plot.setRangeGridlinesVisible(true);
		plot.setRangeGridlinePaint(Color.BLACK);

		plot.setDomainGridlinesVisible(true);
		plot.setDomainGridlinePaint(Color.BLACK);

		chart.setTitle(new TextTitle("Nombre de contrats en cours",
						new Font("Serif", java.awt.Font.BOLD, 18)));
		JPanel panel_Charts = new JPanel();
	        ChartPanel chartPanel = new ChartPanel(chart,762,450,700,400,700,400,false,true,true,true,false,false);
	        chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
	        chartPanel.setBackground(Color.white);
	        panel_Charts. add(chartPanel);
		
		
		table_Clients.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int row = table_Clients.convertRowIndexToModel(table_Clients.getSelectedRow());
				int selectedId = Integer.parseInt(model2.getValueAt(row, 0).toString());
				if(table_Clients.getValueAt(row, 2).equals("Locataire")) {
					Locataire locataire = locataireDAO.getById(selectedId);
					frmDtailDeLagent.dispose();
					new Vue_LocataireDetails(locataire,agent).getFrame().setVisible(true);
				}else {
					Proprietaire proprietaire = proprietaireDAO.getById(selectedId);
					frmDtailDeLagent.dispose();
					new Vue_ProprietaireDetails(proprietaire,agent).getFrame().setVisible(true);
					
				}
			}
		});

		JPanel panel = new JPanel();
		panel.setOpaque(false);
		panel.setBounds(12, 117, 145, 461);
		frmDtailDeLagent.getContentPane().add(panel);
		panel.setLayout(null);

		JButton btnNewButton = new JButton("Informations");
		btnNewButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton.setOpaque(false);

		btnNewButton.setBounds(12, 81, 116, 40);
		btnNewButton.setIcon(new ImageIcon(Vue_AgentDetails.class.getResource("/img/personal.png")));
		btnNewButton.setBorder(null);
		btnNewButton.setBorderPainted(false);
		btnNewButton.setBackground(Color.WHITE);
		panel.add(btnNewButton);

		JButton btnPerformance = new JButton("Performance");
		btnPerformance.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				switchPanels(panel_Charts);
			}
		});
		btnPerformance.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnPerformance.setOpaque(false);
		btnPerformance.setBounds(12, 202, 127, 48);
		btnPerformance.setIcon(new ImageIcon(Vue_AgentDetails.class.getResource("/img/performance.png")));
		btnPerformance.setBorderPainted(false);
		btnPerformance.setBorder(null);
		btnPerformance.setBackground(Color.WHITE);
		panel.add(btnPerformance);

		JButton btnClients = new JButton("Clients");
		btnClients.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnClients.setOpaque(false);

		btnClients.setIcon(new ImageIcon(Vue_AgentDetails.class.getResource("/img/clients.png")));
		btnClients.setBorderPainted(false);
		btnClients.setBorder(null);
		btnClients.setBackground(Color.WHITE);
		btnClients.setBounds(12, 331, 91, 48);
		panel.add(btnClients);

		JLabel lblBG = new JLabel("");
		lblBG.setOpaque(true);
		lblBG.setIcon(new ImageIcon(Vue_AccueilAgent.class.getResource("/img/accueil_bg.jpeg")));
		lblBG.setBounds(-27, 0, 1000, 591);
		frmDtailDeLagent.getContentPane().add(lblBG);
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				switchPanels(panel_infos);
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
		return frmDtailDeLagent;
	}

	public void setFrame(JFrame frame) {
		this.frmDtailDeLagent = frame;
	}
	
}
