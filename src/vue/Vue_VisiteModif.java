package vue;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Cursor;

public class Vue_VisiteModif {

	private JFrame frame;
	private Agent agent;
	private JTextField txtNom;
	private JTable table_biens;
	private DefaultTableModel model;
	private JTextField txtSearch;
	private Vector originalTableModel;
	private Visite visite;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Vue_VisiteModif window = new Vue_VisiteModif();
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
	public Vue_VisiteModif() {
		initialize();
	}

	public Vue_VisiteModif(Visite visite, Agent agent) {
		this.visite = visite;
		this.agent = agent;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 436, 620);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		JSeparator separator = new JSeparator();
		separator.setForeground(Color.LIGHT_GRAY);
		separator.setBounds(0, 88, 420, 2);
		frame.getContentPane().add(separator);
		frame.setLocationRelativeTo(null);

		JLabel lblNewLabel = new JLabel("Modification visite");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(159, 30, 102, 16);
		frame.getContentPane().add(lblNewLabel);
		Date date1 = null;
		try {
			date1 = new SimpleDateFormat("yyyy-MM-dd").parse(visite.getDate());
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Date date = new Date();
		JCalendar calendar = new JCalendar();
		calendar.setBounds(29, 126, 309, 159);
		frame.getContentPane().add(calendar);

		calendar.setDate(date1);
		calendar.setMinSelectableDate(date);
		JLabel lblNewLabel_1 = new JLabel(" Date de la visite");
		lblNewLabel_1.setBounds(29, 101, 177, 14);
		frame.getContentPane().add(lblNewLabel_1);

		JLabel lblNewLabel_1_1 = new JLabel("Nom du visiteur :");
		lblNewLabel_1_1.setBounds(29, 345, 177, 14);
		frame.getContentPane().add(lblNewLabel_1_1);

		txtNom = new JTextField();
		txtNom.setBounds(29, 359, 309, 20);
		frame.getContentPane().add(txtNom);
		txtNom.setColumns(10);
		txtNom.setText(visite.getNom());

		JLabel lblNewLabel_2 = new JLabel("Heure de la visite");
		lblNewLabel_2.setBounds(29, 296, 309, 14);
		frame.getContentPane().add(lblNewLabel_2);

		JComboBox comboboxHeure = new JComboBox();
		comboboxHeure.setModel(new DefaultComboBoxModel(
				new String[] { "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19" }));
		comboboxHeure.setBounds(29, 314, 72, 27);
		frame.getContentPane().add(comboboxHeure);
		String[] heure = visite.getHeure().split(":");

		comboboxHeure.setSelectedItem(heure[0]);

		JLabel lblNewLabel_3 = new JLabel("Heure");
		lblNewLabel_3.setBounds(104, 314, 91, 27);
		frame.getContentPane().add(lblNewLabel_3);

		JComboBox comboboxMinute = new JComboBox();
		comboboxMinute.setModel(new DefaultComboBoxModel(
				new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14",
						"15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30",
						"31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46",
						"47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
		comboboxMinute.setBounds(168, 314, 72, 27);
		frame.getContentPane().add(comboboxMinute);
		comboboxMinute.setSelectedItem(heure[1]);

		JLabel lblNewLabel_3_1 = new JLabel("Minutes");
		lblNewLabel_3_1.setBounds(245, 314, 86, 27);
		frame.getContentPane().add(lblNewLabel_3_1);

		JLabel lblNewLabel_2_1 = new JLabel("Sélectionnez le bien visité");
		lblNewLabel_2_1.setBounds(29, 390, 215, 14);
		frame.getContentPane().add(lblNewLabel_2_1);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 450, 400, 130);
		frame.getContentPane().add(scrollPane);

		BienDAO bienDAO = new BienDAO();
		ArrayList<Bien> biens = bienDAO.getAllByIdAgent(agent.getId());
		String columns[] = { "ID", "Nom", "Ville" };
		String data[][] = new String[biens.size()][columns.length];
		int i = 0;
		int memo = 0;
		for (Bien b : biens) {
			data[i][0] = b.getId() + "";
			data[i][1] = b.getNom();
			data[i][2] = b.getVille();
			if (b.getId() == visite.getId_bien()) {
				memo = i;
			}
			i++;
		}
		model = new DefaultTableModel(data, columns);

		table_biens = new JTable(model);
		originalTableModel = (Vector) ((DefaultTableModel) table_biens.getModel()).getDataVector().clone();
		scrollPane.setViewportView(table_biens);
		table_biens.setRowSelectionInterval(memo, memo);

		JLabel btnNewButton = new JLabel("Confirmer");
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
					visite.setDate(date);
					visite.setHeure(heure);
					visite.setId_bien(bien.getId());
					visite.setNom(txtNom.getText());
					VisiteDAO visiteDAO = new VisiteDAO();
					visiteDAO.save(visite);
					 HistoriqueDAO historiqueDAO = new HistoriqueDAO();
						Historique historique = new Historique();
						historique.setDate(Checker.getDateTime());
						historique.setId_agent(agent.getId());
						historique.setAction("Modification de la visite id : "+visite.getId());
						historiqueDAO.save(historique);	

					frame.dispose();
					new Vue_VisitesList(agent).getFrame().setVisible(true);

				}
			}
		});
		btnNewButton.setHorizontalTextPosition(SwingConstants.CENTER);
		btnNewButton.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnNewButton.setIcon(new ImageIcon(Vue_VisiteModif.class.getResource("/img/valider.png")));
		btnNewButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton.setBounds(351, 11, 57, 68);
		frame.getContentPane().add(btnNewButton);

		JButton btnSearch = new JButton("Recherche");
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
		btnSearch.setBounds(10, 428, 107, 20);
		frame.getContentPane().add(btnSearch);

		txtSearch = new JTextField();
		txtSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnSearch.doClick();

			}
		});
		txtSearch.setColumns(10);
		txtSearch.setBounds(129, 430, 281, 20);
		frame.getContentPane().add(txtSearch);

		JLabel btnRetour = new JLabel("Retour");
		btnRetour.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnRetour.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				frame.dispose();
				new Vue_VisitesList(agent).getFrame().setVisible(true);

			}
		});
		btnRetour.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnRetour.setHorizontalTextPosition(SwingConstants.CENTER);
		btnRetour.setIcon(new ImageIcon(Vue_VisiteModif.class.getResource("/img/back.png")));
		btnRetour.setBounds(11, 11, 48, 68);
		frame.getContentPane().add(btnRetour);
		JLabel lblBG = new JLabel("");
		lblBG.setOpaque(true);
		lblBG.setIcon(new ImageIcon(Vue_AccueilAgent.class.getResource("/img/accueil_bg.jpeg")));
		lblBG.setBounds(-16, 0, 1000, 591);
		frame.getContentPane().add(lblBG);

	}

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}
}
