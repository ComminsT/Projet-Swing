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
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import dao.LocataireDAO;
import entite.Agent;
import entite.Locataire;

public class Vue_LocatairesList {

	private JFrame frame;
	private JTable tableLocataires;
	private Agent agent;
	private JTextField txtSearch;
	private DefaultTableModel model;
	private Vector originalTableModel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Vue_LocatairesList window = new Vue_LocatairesList();
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
	public Vue_LocatairesList() {
		initialize();
	}

	public Vue_LocatairesList(Agent agent) {
		this.agent = agent;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		frame = new JFrame();
		frame.setBounds(100, 100, 981, 630);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(null);

		txtSearch = new JTextField();

		txtSearch.setBounds(654, 75, 301, 20);
		frame.getContentPane().add(txtSearch);
		txtSearch.setColumns(10);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 106, 945, 474);
		frame.getContentPane().add(scrollPane);

		LocataireDAO locataireDAO = new LocataireDAO();
		ArrayList<Locataire> locataires = locataireDAO.getAllFromAgent(agent.getId());
		String columns[] = { "ID", "Nom", "Prenom", "Telephone", "Statut", "Situation" };
		String data[][] = new String[locataires.size()][columns.length];
		int i = 0;
		for (Locataire l : locataires) {
			data[i][0] = l.getId() + "";
			data[i][1] = l.getNom();
			data[i][2] = l.getPrenom();
			data[i][3] = l.getTel();
			data[i][4] = l.getStatut();
			data[i][5] = l.getSituation();
			i++;
		}
		model = new DefaultTableModel(data, columns);
		tableLocataires = new JTable(model);
		originalTableModel = (Vector) ((DefaultTableModel) tableLocataires.getModel()).getDataVector().clone();
		scrollPane.setViewportView(tableLocataires);
		tableLocataires.setAutoCreateRowSorter(true);

		JButton btnNewTenant = new JButton("Nouveau locataire");
		btnNewTenant.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewTenant.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				new Vue_CreationLocataire(agent).getFrame().setVisible(true);
			}
		});
		btnNewTenant.setIcon(new ImageIcon(Vue_LocatairesList.class.getResource("/img/peopleAdd.png")));
		btnNewTenant.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnNewTenant.setHorizontalTextPosition(SwingConstants.CENTER);
		btnNewTenant.setHorizontalAlignment(SwingConstants.CENTER);
		btnNewTenant.setBorder(new LineBorder(new Color(0, 0, 0)));
		btnNewTenant.setBackground(Color.LIGHT_GRAY);
		btnNewTenant.setBounds(141, 11, 121, 84);
		frame.getContentPane().add(btnNewTenant);
		btnNewTenant.setOpaque(false);

		JButton btnRetour = new JButton("Retour");
		btnRetour.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnRetour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				new Vue_AccueilAgent(agent).getFrame().setVisible(true);
			}
		});
		btnRetour.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnRetour.setHorizontalTextPosition(SwingConstants.CENTER);
		btnRetour.setHorizontalAlignment(SwingConstants.CENTER);
		btnRetour.setBorder(new LineBorder(new Color(0, 0, 0)));
		btnRetour.setBackground(Color.LIGHT_GRAY);
		btnRetour.setBounds(10, 11, 121, 84);
		frame.getContentPane().add(btnRetour);
		btnRetour.setOpaque(false);

		JButton btnModifier = new JButton("Modifier");
		btnModifier.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnModifier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (tableLocataires.getSelectedRow() != -1) {
					int row = tableLocataires.convertRowIndexToModel(tableLocataires.getSelectedRow());
					int selectedId = Integer.parseInt(model.getValueAt(row, 0).toString());
					LocataireDAO locataireDAO = new LocataireDAO();
					Locataire locataire = locataireDAO.getById(selectedId);
					frame.dispose();
					new Vue_LocataireModif(locataire, agent).getFrame().setVisible(true);

				} else {
					JOptionPane.showMessageDialog(null, "Veuillez choisir une ligne");
				}

			}
		});
		btnModifier.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnModifier.setHorizontalTextPosition(SwingConstants.CENTER);
		btnModifier.setHorizontalAlignment(SwingConstants.CENTER);
		btnModifier.setBorder(new LineBorder(new Color(0, 0, 0)));
		btnModifier.setBackground(Color.LIGHT_GRAY);
		btnModifier.setBounds(272, 11, 121, 84);
		frame.getContentPane().add(btnModifier);
		btnModifier.setOpaque(false);

		JButton btnDetails = new JButton("Détails");
		btnDetails.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnDetails.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (tableLocataires.getSelectedRow() != -1) {
					int row = tableLocataires.convertRowIndexToModel(tableLocataires.getSelectedRow());
					int selectedId = Integer.parseInt(model.getValueAt(row, 0).toString());
					LocataireDAO locataireDAO = new LocataireDAO();
					Locataire locataire = locataireDAO.getById(selectedId);
					frame.dispose();
					new Vue_LocataireDetails(locataire, agent).getFrame().setVisible(true);

				} else {
					JOptionPane.showMessageDialog(null, "Veuillez choisir une ligne");
				}
			}

		});
		btnDetails.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnDetails.setHorizontalTextPosition(SwingConstants.CENTER);
		btnDetails.setHorizontalAlignment(SwingConstants.CENTER);
		btnDetails.setBorder(new LineBorder(new Color(0, 0, 0)));
		btnDetails.setBackground(Color.LIGHT_GRAY);
		btnDetails.setBounds(403, 11, 121, 84);
		frame.getContentPane().add(btnDetails);
		btnDetails.setOpaque(false);

		JButton btnSearch = new JButton("Recherche : ");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String keyword = txtSearch.getText();
				DefaultTableModel currtableModel = (DefaultTableModel) tableLocataires.getModel();
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
				tableLocataires.setModel(currtableModel);

			}
		});
		btnSearch.setHorizontalAlignment(SwingConstants.RIGHT);
		btnSearch.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnSearch.setBounds(534, 75, 110, 20);
		frame.getContentPane().add(btnSearch);
		btnSearch.setOpaque(false);

		txtSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnSearch.doClick();

			}
		});

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(Vue_LocatairesList.class.getResource("/img/accueil_bg.jpeg")));
		lblNewLabel.setBounds(-26, -19, 1023, 636);
		frame.getContentPane().add(lblNewLabel);
	}

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}
}
