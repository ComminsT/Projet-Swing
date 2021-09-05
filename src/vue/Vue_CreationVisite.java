package vue;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JCalendar;

import dao.BienDAO;
import dao.HistoriqueDAO;
import dao.VisiteDAO;
import entite.Agent;
import entite.Bien;
import entite.Checker;
import entite.Database;
import entite.Historique;
import entite.Visite;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Vue_CreationVisite {

	private JFrame frmAjoutDuneNouvelle;
	private Agent agent;
	private JTextField txtNom;
	private JTable table_biens;
	private DefaultTableModel model;
	private JTextField txtSearch;
	private Vector originalTableModel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Vue_CreationVisite window = new Vue_CreationVisite();
					window.frmAjoutDuneNouvelle.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Vue_CreationVisite() {
		initialize();
	}

	public Vue_CreationVisite(Agent agent) {
		this.agent = agent;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmAjoutDuneNouvelle = new JFrame();
		frmAjoutDuneNouvelle.setTitle("Ajout d'une nouvelle visite");
		frmAjoutDuneNouvelle.setBounds(100, 100, 436, 617);
		frmAjoutDuneNouvelle.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmAjoutDuneNouvelle.getContentPane().setLayout(null);
		JSeparator separator = new JSeparator();
		separator.setForeground(Color.LIGHT_GRAY);
		separator.setBounds(0, 88, 420, 2);
		frmAjoutDuneNouvelle.getContentPane().add(separator);
		frmAjoutDuneNouvelle.setLocationRelativeTo(null);

		JLabel lblNewLabel = new JLabel("Ajout d'une nouvelle visite");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(127, 30, 165, 16);
		frmAjoutDuneNouvelle.getContentPane().add(lblNewLabel);

		Date date = new Date();
		JCalendar calendar = new JCalendar();
		calendar.setBounds(29, 126, 309, 159);
		frmAjoutDuneNouvelle.getContentPane().add(calendar);
		calendar.setMinSelectableDate(date);
		JLabel lblNewLabel_1 = new JLabel("Sélectionnez la date de la visite");
		lblNewLabel_1.setBounds(29, 101, 196, 15);
		frmAjoutDuneNouvelle.getContentPane().add(lblNewLabel_1);

		JLabel lblNewLabel_1_1 = new JLabel("Insérez le nom du visiteur :");
		lblNewLabel_1_1.setBounds(29, 345, 177, 14);
		frmAjoutDuneNouvelle.getContentPane().add(lblNewLabel_1_1);

		txtNom = new JTextField();
		txtNom.setBounds(29, 359, 309, 20);
		frmAjoutDuneNouvelle.getContentPane().add(txtNom);
		txtNom.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Sélectionnez l'heure de la visite");
		lblNewLabel_2.setBounds(29, 296, 309, 14);
		frmAjoutDuneNouvelle.getContentPane().add(lblNewLabel_2);

		JComboBox comboboxHeure = new JComboBox();
		comboboxHeure.setModel(new DefaultComboBoxModel(
				new String[] { "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19" }));
		comboboxHeure.setBounds(29, 314, 51, 20);
		frmAjoutDuneNouvelle.getContentPane().add(comboboxHeure);

		JLabel lblNewLabel_3 = new JLabel("Heure");
		lblNewLabel_3.setBounds(90, 314, 91, 21);
		frmAjoutDuneNouvelle.getContentPane().add(lblNewLabel_3);

		JComboBox comboboxMinute = new JComboBox();
		comboboxMinute.setModel(new DefaultComboBoxModel(
				new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14",
						"15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30",
						"31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46",
						"47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
		comboboxMinute.setBounds(191, 314, 51, 20);
		frmAjoutDuneNouvelle.getContentPane().add(comboboxMinute);

		JLabel lblNewLabel_3_1 = new JLabel("Minutes");
		lblNewLabel_3_1.setBounds(252, 315, 86, 21);
		frmAjoutDuneNouvelle.getContentPane().add(lblNewLabel_3_1);

		JLabel lblNewLabel_2_1 = new JLabel("Sélectionnez le bien visité");
		lblNewLabel_2_1.setBounds(10, 406, 215, 14);
		frmAjoutDuneNouvelle.getContentPane().add(lblNewLabel_2_1);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 450, 400, 116);
		frmAjoutDuneNouvelle.getContentPane().add(scrollPane);

		BienDAO bienDAO = new BienDAO();
		ArrayList<Bien> biens = bienDAO.getAllByIdAgent(agent.getId());
		String columns[] = { "ID", "Nom", "Ville" };
		String data[][] = new String[biens.size()][columns.length];
		int i = 0;
		for (Bien b : biens) {
			data[i][0] = b.getId() + "";
			data[i][1] = b.getNom();
			data[i][2] = b.getVille();

			i++;
		}
		model = new DefaultTableModel(data, columns);

		table_biens = new JTable(model);
		originalTableModel = (Vector) ((DefaultTableModel) table_biens.getModel()).getDataVector().clone();
		scrollPane.setViewportView(table_biens);

		JLabel btnNewButton = new JLabel("Confirmer");
		btnNewButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		btnNewButton.setHorizontalTextPosition(SwingConstants.CENTER);
		btnNewButton.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnNewButton.setIcon(new ImageIcon(Vue_CreationVisite.class.getResource("/img/valider.png")));
		btnNewButton.setBounds(351, 11, 57, 68);
		frmAjoutDuneNouvelle.getContentPane().add(btnNewButton);

		JButton btnSearch = new JButton("");
		btnSearch.setBorder(null);
		btnSearch.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnSearch.setOpaque(false);
		btnSearch.setBackground(Color.WHITE);
		btnSearch.setIcon(new ImageIcon(Vue_CreationVisite.class.getResource("/img/search20.png")));
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String keyword = txtSearch.getText();
				DefaultTableModel currtableModel = (DefaultTableModel) table_biens.getModel();
				currtableModel.setRowCount(0);
				for (Object rows : originalTableModel) {
					Vector rowVector = (Vector) rows;
					for (Object column : rowVector) {
						if (column.toString().contains(keyword)) {
							currtableModel.addRow(rowVector);
							break;
						}
					}

				}
				table_biens.setModel(currtableModel);

			}
		});
		btnSearch.setBounds(108, 430, 20, 20);
		frmAjoutDuneNouvelle.getContentPane().add(btnSearch);

		txtSearch = new JTextField();
		txtSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnSearch.doClick();

			}
		});
		txtSearch.setColumns(10);
		txtSearch.setBounds(129, 430, 281, 20);
		frmAjoutDuneNouvelle.getContentPane().add(txtSearch);

		JLabel btnRetour = new JLabel("Retour");
		btnRetour.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frmAjoutDuneNouvelle.dispose();
				new Vue_VisitesList(agent).getFrame().setVisible(true);
			
			}
		});
		btnRetour.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnRetour.setHorizontalTextPosition(SwingConstants.CENTER);
		btnRetour.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnRetour.setIcon(new ImageIcon(Vue_CreationVisite.class.getResource("/img/back.png")));
		btnRetour.setBounds(11, 11, 48, 68);
		frmAjoutDuneNouvelle.getContentPane().add(btnRetour);
		
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				Database.Connect();
				if (txtNom.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Veuillez remplir le nom du visiteur");
				} else if (table_biens.getSelectedRow() == -1) {
					JOptionPane.showMessageDialog(null, "Veuillez choisir un bien");
				} else {
					int row = table_biens.convertRowIndexToModel(table_biens.getSelectedRow());
					int selectedId = Integer.parseInt(model.getValueAt(row, 0).toString());
					BienDAO bienDAO = new BienDAO();
					Bien bien = bienDAO.getById(selectedId);
					String mois = String.valueOf(calendar.getMonthChooser().getMonth() + 1);
					String jour = String.valueOf(calendar.getDayChooser().getDay());
					String annee = String.valueOf(calendar.getYearChooser().getYear());
					String date = annee + "-" + mois + "-" + jour;
					String heure = comboboxHeure.getSelectedItem() + ":" + comboboxMinute.getSelectedItem();
					Visite visite = new Visite();

					visite.setDate(date);
					visite.setHeure(heure);
					visite.setId_bien(bien.getId());
					visite.setNom(txtNom.getText());
					VisiteDAO visiteDAO = new VisiteDAO();
					visiteDAO.save(visite);
					ArrayList<Visite>visites = visiteDAO.getAll();
					visite=visites.get(visites.size()-1);
					 HistoriqueDAO historiqueDAO = new HistoriqueDAO();
						Historique historique = new Historique();
						historique.setDate(Checker.getDateTime());
						historique.setId_agent(agent.getId());
						historique.setAction("Ajout visite id : "+visite.getId());
						historiqueDAO.save(historique);	

					frmAjoutDuneNouvelle.dispose();
					new Vue_VisitesList(agent).getFrame().setVisible(true);

				}
			
			}
		});
		JLabel lblBG = new JLabel("");
		lblBG.setOpaque(true);
		lblBG.setIcon(new ImageIcon(Vue_AccueilAgent.class.getResource("/img/accueil_bg.jpeg")));
		lblBG.setBounds(-16, 0, 1000, 591);
		frmAjoutDuneNouvelle.getContentPane().add(lblBG);

	}

	public JFrame getFrame() {
		return frmAjoutDuneNouvelle;
	}

	public void setFrame(JFrame frame) {
		this.frmAjoutDuneNouvelle = frame;
	}
}
