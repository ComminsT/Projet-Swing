package vue;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

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

import dao.BienDAO;
import dao.ProprietaireDAO;
import entite.Agent;
import entite.Bien;
import entite.Proprietaire;

public class Vue_BiensList {

	private JFrame frame;
	private JTable tableBiens;
	private Agent agent;
	private JTextField txtSearch;
	private DefaultTableModel model;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Vue_BiensList window = new Vue_BiensList();
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
	public Vue_BiensList() {
		initialize();
	}

	public Vue_BiensList(Agent agent) {
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

		BienDAO bienDAO = new BienDAO();

		ArrayList<Bien> biens = bienDAO.getAllByIdAgent(agent.getId());
		String columns[] = { "ID", "Nom", "Ville", "Statut" };
		String data[][] = new String[biens.size()][columns.length];
		int i = 0;
		for (Bien b : biens) {
			data[i][0] = b.getId() + "";
			data[i][1] = b.getNom();
			data[i][2] = b.getVille();
			data[i][3] = b.getStatut();
			i++;
		}
		model = new DefaultTableModel(data, columns);
		tableBiens = new JTable(model);
		scrollPane.setViewportView(tableBiens);
		tableBiens.setAutoCreateRowSorter(true);

		JButton btnNewLandlord = new JButton("Nouveau Bien");
		btnNewLandlord.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewLandlord.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Vue_CreationBien(agent).getFrame().setVisible(true);
			}
		});
		btnNewLandlord.setIcon(new ImageIcon(Vue_LocatairesList.class.getResource("/img/peopleAdd.png")));
		btnNewLandlord.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnNewLandlord.setHorizontalTextPosition(SwingConstants.CENTER);
		btnNewLandlord.setHorizontalAlignment(SwingConstants.CENTER);
		btnNewLandlord.setBorder(new LineBorder(new Color(0, 0, 0)));
		btnNewLandlord.setBackground(Color.LIGHT_GRAY);
		btnNewLandlord.setBounds(141, 11, 121, 84);
		frame.getContentPane().add(btnNewLandlord);
		btnNewLandlord.setOpaque(false);

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
				if (tableBiens.getSelectedRow() != -1) {
					int row = tableBiens.convertRowIndexToModel(tableBiens.getSelectedRow());
					int selectedId = Integer.parseInt(model.getValueAt(row, 0).toString());
					BienDAO bienDAO= new BienDAO();
					Bien bien = bienDAO.getById(selectedId);
					frame.dispose();
					new Vue_BienModif(bien, agent).getFrame().setVisible(true);

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
				if (tableBiens.getSelectedRow() != -1) {
					int row = tableBiens.convertRowIndexToModel(tableBiens.getSelectedRow());
					int selectedId = Integer.parseInt(model.getValueAt(row, 0).toString());
					BienDAO bienDAO= new BienDAO();
					Bien bien = bienDAO.getById(selectedId);
					frame.dispose();
					//new Vue_BienDetails(bien, agent).getFrame().setVisible(true);

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
				ArrayList<Bien> biens = bienDAO.getByKeywordsAndByIdAgent(txtSearch.getText(),
						agent.getId());
				String data[][] = new String[biens.size()][columns.length];
				int i = 0;
				for (Bien b : biens) {
					data[i][0] = b.getId() + "";
					data[i][1] = b.getNom();
					data[i][2] = b.getVille();
					data[i][3] = b.getStatut();
					i++;
				}
				model = new DefaultTableModel(data, columns);
				tableBiens.setModel(model);

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
