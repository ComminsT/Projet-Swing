package vue;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import dao.BienDAO;
import dao.ComptabiliteDAO;
import dao.LocataireDAO;
import entite.Agent;
import entite.Bien;
import entite.Comptabilite;
import entite.Locataire;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JEditorPane;
import java.awt.Toolkit;

public class Vue_ComptaList {

	private JFrame frmListeDesFactures;
	private JTable tableComptabiliteEnCours;
	private Agent agent;
	private JTextField txtSearch;
	private DefaultTableModel model;
	private DefaultTableModel model2;
	private JTable tableComptabilitePayee;
	private Vector originalTableModel;
	private Vector originalTableModel2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Vue_ComptaList window = new Vue_ComptaList();
					window.frmListeDesFactures.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Vue_ComptaList() {
		initialize();
	}

	public Vue_ComptaList(Agent agent) {
		this.agent = agent;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		frmListeDesFactures = new JFrame();
		frmListeDesFactures.setTitle("Liste des factures");
		frmListeDesFactures.setBounds(100, 100, 981, 630);
		frmListeDesFactures.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmListeDesFactures.setLocationRelativeTo(null);
		frmListeDesFactures.getContentPane().setLayout(null);

		txtSearch = new JTextField();

		txtSearch.setBounds(654, 75, 301, 20);
		frmListeDesFactures.getContentPane().add(txtSearch);
		txtSearch.setColumns(10);

		JTabbedPane tabbedPane_1 = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane_1.setBounds(10, 106, 945, 474);
		frmListeDesFactures.getContentPane().add(tabbedPane_1);

		JScrollPane scrollPane = new JScrollPane();
		tabbedPane_1.addTab("Facture en attente", null, scrollPane, null);

		ComptabiliteDAO comptabiliteDAO = new ComptabiliteDAO();
		ArrayList<Comptabilite> comptabilites = comptabiliteDAO.getAllByIdAgentNOTPAID(agent.getId());
		String columns[] = { "ID", "Date-due", "Montant d??", "Locataire", "Bien" };
		String data[][] = new String[comptabilites.size()][columns.length];
		int i = 0;
		for (Comptabilite c : comptabilites) {
			LocataireDAO locataireDAO = new LocataireDAO();
			Locataire locataire = locataireDAO.getByIdComptabilite(c.getId());
			BienDAO bienDAO = new BienDAO();
			Bien bien = bienDAO.getByIdComptabilite(c.getId());
			String[] date = c.getDatedue().split("-");
			data[i][0] = c.getId() + "";
			data[i][1] = date[2] + "/" + date[1] + "/" + date[0];
			data[i][2] = c.getMontantdu() + " ???";
			data[i][3] = locataire + "";
			data[i][4] = bien + "";
			i++;
		}
		model = new DefaultTableModel(data, columns);

		tableComptabiliteEnCours = new JTable(model);
		scrollPane.setViewportView(tableComptabiliteEnCours);
		tableComptabiliteEnCours.setAutoCreateRowSorter(true);
		originalTableModel = (Vector) ((DefaultTableModel) tableComptabiliteEnCours.getModel()).getDataVector().clone();
		JScrollPane scrollPane_1 = new JScrollPane();
		tabbedPane_1.addTab("Facture pay??e", null, scrollPane_1, null);
		ArrayList<Comptabilite> comptabilites2 = comptabiliteDAO.getAllByIdAgentPAID(agent.getId());
		String data2[][] = new String[comptabilites2.size()][columns.length];
		i = 0;
		for (Comptabilite c : comptabilites2) {
			LocataireDAO locataireDAO = new LocataireDAO();
			Locataire locataire = locataireDAO.getByIdComptabilite(c.getId());
			BienDAO bienDAO = new BienDAO();
			Bien bien = bienDAO.getByIdComptabilite(c.getId());
			String[] date = c.getDatedue().split("-");
			data2[i][0] = c.getId() + "";
			data2[i][1] = date[2] + "/" + date[1] + "/" + date[0];
			data2[i][2] = c.getMontantdu() + " ???";
			data2[i][3] = locataire + "";
			data2[i][4] = bien + "";
			i++;
		}
		model2 = new DefaultTableModel(data2, columns);
		tableComptabilitePayee = new JTable(model2);
		scrollPane_1.setViewportView(tableComptabilitePayee);
		originalTableModel2 = (Vector) ((DefaultTableModel) tableComptabilitePayee.getModel()).getDataVector().clone();

		JLabel btnNewTenant = new JLabel("Nouvelle facture");
		btnNewTenant.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frmListeDesFactures.dispose();
				new Vue_CreationCompta(agent).getFrame().setVisible(true);
			}
		});
		btnNewTenant.setOpaque(false);
		btnNewTenant.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewTenant.setIcon(new ImageIcon(Vue_LocatairesList.class.getResource("/img/peopleAdd.png")));
		btnNewTenant.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnNewTenant.setHorizontalTextPosition(SwingConstants.CENTER);
		btnNewTenant.setHorizontalAlignment(SwingConstants.CENTER);
		btnNewTenant.setBorder(null);
		btnNewTenant.setBackground(Color.LIGHT_GRAY);
		btnNewTenant.setBounds(107, 24, 105, 70);
		frmListeDesFactures.getContentPane().add(btnNewTenant);

		JLabel btnRetour = new JLabel("Retour");
		btnRetour.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frmListeDesFactures.dispose();
				new Vue_AccueilAgent(agent).getFrame().setVisible(true);
			}
		});
		btnRetour.setIcon(new ImageIcon(Vue_ComptaList.class.getResource("/img/back.png")));
		btnRetour.setOpaque(false);
		btnRetour.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnRetour.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnRetour.setHorizontalTextPosition(SwingConstants.CENTER);
		btnRetour.setHorizontalAlignment(SwingConstants.CENTER);
		btnRetour.setBorder(null);
		btnRetour.setBackground(Color.LIGHT_GRAY);
		btnRetour.setBounds(10, 25, 48, 68);
		frmListeDesFactures.getContentPane().add(btnRetour);

		JLabel btnModifier = new JLabel("Modifier");
		btnModifier.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (scrollPane.isVisible()) {
					if (tableComptabiliteEnCours.getSelectedRow() != -1) {
						int row = tableComptabiliteEnCours
								.convertRowIndexToModel(tableComptabiliteEnCours.getSelectedRow());
						int selectedId = Integer.parseInt(model.getValueAt(row, 0).toString());
						ComptabiliteDAO comptabiliteDAO = new ComptabiliteDAO();
						Comptabilite comptabilite = comptabiliteDAO.getById(selectedId);
						frmListeDesFactures.dispose();
						new Vue_ComptaModif(comptabilite, agent).getFrame().setVisible(true);
					} else {
						JOptionPane.showMessageDialog(null, "Veuillez choisir une ligne");
					}
				} else if (scrollPane_1.isVisible()) {
					if (tableComptabilitePayee.getSelectedRow() != -1) {
						JOptionPane.showMessageDialog(null,
								"Cette facture est d??j?? r??gl??e, vous ne pouvez plus la modifier");
					} else {
						JOptionPane.showMessageDialog(null, "Veuillez choisir une ligne");
					}
				}
			}
		});
		btnModifier.setIcon(new ImageIcon(Vue_ComptaList.class.getResource("/img/modify.png")));
		btnModifier.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnModifier.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnModifier.setHorizontalTextPosition(SwingConstants.CENTER);
		btnModifier.setHorizontalAlignment(SwingConstants.CENTER);
		btnModifier.setBorder(null);
		btnModifier.setBackground(Color.LIGHT_GRAY);
		btnModifier.setBounds(224, 24, 54, 70);
		frmListeDesFactures.getContentPane().add(btnModifier);
		btnModifier.setOpaque(false);

		JLabel btnDetails = new JLabel("D??tails");
		btnDetails.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (scrollPane.isVisible()) {
					if (tableComptabiliteEnCours.getSelectedRow() != -1) {
						int row = tableComptabiliteEnCours
								.convertRowIndexToModel(tableComptabiliteEnCours.getSelectedRow());
						int selectedId = Integer.parseInt(model.getValueAt(row, 0).toString());
						ComptabiliteDAO comptabiliteDAO = new ComptabiliteDAO();
						Comptabilite comptabilite = comptabiliteDAO.getById(selectedId);
						frmListeDesFactures.dispose();
						new Vue_ComptaDetails(comptabilite, agent).getFrame().setVisible(true);

					} else {
						JOptionPane.showMessageDialog(null, "Veuillez choisir une ligne");
					}

				} else if (scrollPane_1.isVisible()) {
					if (tableComptabilitePayee.getSelectedRow() != -1) {
						int row = tableComptabilitePayee
								.convertRowIndexToModel(tableComptabilitePayee.getSelectedRow());
						int selectedId = Integer.parseInt(model2.getValueAt(row, 0).toString());
						ComptabiliteDAO comptabiliteDAO = new ComptabiliteDAO();
						Comptabilite comptabilite = comptabiliteDAO.getById(selectedId);
						frmListeDesFactures.dispose();
						new Vue_ComptaDetails(comptabilite, agent).getFrame().setVisible(true);
					} else {
						JOptionPane.showMessageDialog(null, "Veuillez choisir une ligne");
					}

				}
			}
		});
		btnDetails.setIcon(new ImageIcon(Vue_ComptaList.class.getResource("/img/details.png")));
		btnDetails.setOpaque(false);
		btnDetails.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnDetails.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnDetails.setHorizontalTextPosition(SwingConstants.CENTER);
		btnDetails.setHorizontalAlignment(SwingConstants.CENTER);
		btnDetails.setBorder(null);
		btnDetails.setBackground(Color.LIGHT_GRAY);
		btnDetails.setBounds(290, 24, 50, 70);
		frmListeDesFactures.getContentPane().add(btnDetails);

		JButton btnSearch = new JButton("");
		btnSearch.setOpaque(false);
		btnSearch.setBorder(null);
		btnSearch.setBackground(Color.WHITE);
		btnSearch.setIcon(new ImageIcon(Vue_ComptaList.class.getResource("/img/search20.png")));
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String keyword = txtSearch.getText();
				DefaultTableModel currtableModel = (DefaultTableModel) tableComptabiliteEnCours.getModel();
				DefaultTableModel currtableModel2 = (DefaultTableModel) tableComptabilitePayee.getModel();
				currtableModel.setRowCount(0);
				currtableModel2.setRowCount(0);
				for (Object rows : originalTableModel) {
					Vector rowVector = (Vector) rows;
					for (Object column : rowVector) {
						if (column.toString().contains(keyword)) {
							currtableModel.addRow(rowVector);
							break;
						}
					}

				}
				for (Object rows : originalTableModel2) {
					Vector rowVector = (Vector) rows;
					for (Object column : rowVector) {
						if (column.toString().contains(keyword)) {
							currtableModel2.addRow(rowVector);
							break;
						}
					}

				}
				tableComptabilitePayee.setModel(currtableModel2);
				tableComptabiliteEnCours.setModel(currtableModel);

			}
		});
		btnSearch.setHorizontalAlignment(SwingConstants.RIGHT);
		btnSearch.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnSearch.setBounds(632, 75, 20, 20);
		frmListeDesFactures.getContentPane().add(btnSearch);

		txtSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnSearch.doClick();

			}
		});

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(Vue_LocatairesList.class.getResource("/img/accueil_bg.jpeg")));
		lblNewLabel.setBounds(-26, -19, 1023, 636);
		frmListeDesFactures.getContentPane().add(lblNewLabel);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 100, 945, 480);
		frmListeDesFactures.getContentPane().add(tabbedPane);

		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(Vue_AccueilAgent.class.getResource("/img/accueil_bg.jpeg")));
		lblNewLabel_2.setBounds(-16, 0, 1000, 591);
		frmListeDesFactures.getContentPane().add(lblNewLabel_2);
	}

	public JFrame getFrame() {
		return frmListeDesFactures;
	}

	public void setFrame(JFrame frame) {
		this.frmListeDesFactures = frame;
	}
}
