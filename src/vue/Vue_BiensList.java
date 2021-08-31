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

import dao.BienDAO;
import entite.Agent;
import entite.Bien;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Vue_BiensList {

	private JFrame frame;
	private JTable tableBiens;
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

		txtSearch.setBounds(769, 75, 186, 20);
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
		originalTableModel = (Vector) ((DefaultTableModel) tableBiens.getModel()).getDataVector().clone();

		JLabel btnNewLandlord = new JLabel("Nouveau Bien");
		btnNewLandlord.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.dispose();
				new Vue_CreationBien(agent).getFrame().setVisible(true);
			}
		});
		btnNewLandlord.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewLandlord.setIcon(new ImageIcon(Vue_LocatairesList.class.getResource("/img/peopleAdd.png")));
		btnNewLandlord.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnNewLandlord.setHorizontalTextPosition(SwingConstants.CENTER);
		btnNewLandlord.setHorizontalAlignment(SwingConstants.CENTER);
		btnNewLandlord.setBorder(null);
		btnNewLandlord.setBackground(Color.LIGHT_GRAY);
		btnNewLandlord.setBounds(126, 17, 103, 84);
		frame.getContentPane().add(btnNewLandlord);
		btnNewLandlord.setOpaque(false);

		JLabel btnRetour = new JLabel("Retour");
		btnRetour.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.dispose();
				new Vue_AccueilAgent(agent).getFrame().setVisible(true);
			}
		});
		btnRetour.setIcon(new ImageIcon(Vue_BiensList.class.getResource("/img/back.png")));
		btnRetour.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnRetour.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnRetour.setHorizontalTextPosition(SwingConstants.CENTER);
		btnRetour.setHorizontalAlignment(SwingConstants.CENTER);
		btnRetour.setBorder(null);
		btnRetour.setBackground(Color.LIGHT_GRAY);
		btnRetour.setBounds(6, 25, 68, 68);
		frame.getContentPane().add(btnRetour);
		btnRetour.setOpaque(false);

		JLabel btnModifier = new JLabel("Modifier");
		btnModifier.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (tableBiens.getSelectedRow() != -1) {
					int row = tableBiens.convertRowIndexToModel(tableBiens.getSelectedRow());
					int selectedId = Integer.parseInt(model.getValueAt(row, 0).toString());
					BienDAO bienDAO = new BienDAO();
					Bien bien = bienDAO.getById(selectedId);
					frame.dispose();
					new Vue_BienModif(bien, agent).getFrame().setVisible(true);

				} else {
					JOptionPane.showMessageDialog(null, "Veuillez choisir une ligne");
				}

			
			}
		});
		btnModifier.setIcon(new ImageIcon(Vue_BiensList.class.getResource("/img/modify.png")));
		btnModifier.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnModifier.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnModifier.setHorizontalTextPosition(SwingConstants.CENTER);
		btnModifier.setHorizontalAlignment(SwingConstants.CENTER);
		btnModifier.setBorder(null);
		btnModifier.setBackground(Color.LIGHT_GRAY);
		btnModifier.setBounds(272, 25, 103, 70);
		frame.getContentPane().add(btnModifier);
		btnModifier.setOpaque(false);

		JLabel btnDetails = new JLabel("Détails");
		btnDetails.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (tableBiens.getSelectedRow() != -1) {
					int row = tableBiens.convertRowIndexToModel(tableBiens.getSelectedRow());
					int selectedId = Integer.parseInt(model.getValueAt(row, 0).toString());
					BienDAO bienDAO = new BienDAO();
					Bien bien = bienDAO.getById(selectedId);
					frame.dispose();
					new Vue_BienDetails(bien, agent).getFrame().setVisible(true);

				} else {
					JOptionPane.showMessageDialog(null, "Veuillez choisir une ligne");
				}
			
			}
		});
		btnDetails.setIcon(new ImageIcon(Vue_BiensList.class.getResource("/img/details.png")));
		btnDetails.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnDetails.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnDetails.setHorizontalTextPosition(SwingConstants.CENTER);
		btnDetails.setHorizontalAlignment(SwingConstants.CENTER);
		btnDetails.setBorder(null);
		btnDetails.setBackground(Color.LIGHT_GRAY);
		btnDetails.setBounds(403, 25, 121, 70);
		frame.getContentPane().add(btnDetails);
		btnDetails.setOpaque(false);

		JButton btnSearch = new JButton("Recherche : ");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String keyword = txtSearch.getText();
				DefaultTableModel currtableModel = (DefaultTableModel) tableBiens.getModel();
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
				tableBiens.setModel(currtableModel);


			}
		});
		
		
		btnSearch.setHorizontalAlignment(SwingConstants.RIGHT);
		btnSearch.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnSearch.setBounds(665, 75, 94, 20);
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
