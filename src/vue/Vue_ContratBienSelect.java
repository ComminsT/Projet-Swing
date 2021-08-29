package vue;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import dao.BienDAO;
import entite.Agent;
import entite.Bien;
import entite.Locataire;

public class Vue_ContratBienSelect {

	private JFrame frame;
	private Agent agent;
	private DefaultTableModel modelbiens;
	private JTable table_proprietaire;
	private JTextField txtSearch;
	private Locataire locataire;
	private Vector originalTableModel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Vue_ContratBienSelect window = new Vue_ContratBienSelect();
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
	public Vue_ContratBienSelect() {
		initialize();
	}

	public Vue_ContratBienSelect(Locataire locataire, Agent agent) {
		this.locataire = locataire;
		this.agent = agent;
		initialize();
	}

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 436, 595);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JSeparator separator = new JSeparator();
		separator.setForeground(Color.LIGHT_GRAY);
		separator.setBounds(0, 88, 420, 2);
		frame.getContentPane().add(separator);

		JButton btnNewButton = new JButton("Retour");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				new Vue_CreationContrat(agent).getFrame().setVisible(true);
			}
		});
		btnNewButton.setBounds(10, 11, 109, 66);
		frame.getContentPane().add(btnNewButton);

		JButton btnNext = new JButton("Suivant");

		btnNext.setBounds(285, 479, 125, 66);
		frame.getContentPane().add(btnNext);

		JLabel lblNewLabel = new JLabel("Nouveau contrat de location");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(129, 11, 281, 66);
		frame.getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Sélectionnez un bien");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(0, 88, 420, 25);
		frame.getContentPane().add(lblNewLabel_1);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 142, 400, 330);
		frame.getContentPane().add(scrollPane);

		table_proprietaire = new JTable();

		txtSearch = new JTextField();

		txtSearch.setBounds(109, 121, 301, 20);
		frame.getContentPane().add(txtSearch);
		txtSearch.setColumns(10);

		BienDAO bienDAO = new BienDAO();

		ArrayList<Bien> biens = bienDAO.getAllByIdAgentLIBRE(agent.getId());
		String columnsBiens[] = { "ID", "Nom", "Ville", "Statut" };
		String dataBiens[][] = new String[biens.size()][columnsBiens.length];
		int a = 0;
		for (Bien b : biens) {
			dataBiens[a][0] = b.getId() + "";
			dataBiens[a][1] = b.getNom();
			dataBiens[a][2] = b.getVille();
			dataBiens[a][3] = b.getStatut();
			a++;
		}
		modelbiens = new DefaultTableModel(dataBiens, columnsBiens);
		table_proprietaire.setModel(modelbiens);
		originalTableModel = (Vector) ((DefaultTableModel) table_proprietaire.getModel()).getDataVector().clone();

		scrollPane.setViewportView(table_proprietaire);

		JButton btnSearch = new JButton("Recherche");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String keyword = txtSearch.getText();
				DefaultTableModel currtableModel = (DefaultTableModel) table_proprietaire.getModel();
				currtableModel.setRowCount(0);
				for (Object rows : originalTableModel) {
					Vector rowVector = (Vector) rows;
					for (Object column : rowVector) {
						if (column.toString().contains(keyword)) {
							currtableModel.addRow(rowVector);
							break;
						}
					}
					table_proprietaire.setModel(currtableModel);
				}

			}
		});

		btnSearch.setBounds(10, 120, 89, 23);
		frame.getContentPane().add(btnSearch);

		txtSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnSearch.doClick();
			}
		});

		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (table_proprietaire.getSelectedRow() != -1) {
					int row = table_proprietaire.convertRowIndexToModel(table_proprietaire.getSelectedRow());
					int selectedId = Integer.parseInt(modelbiens.getValueAt(row, 0).toString());
					BienDAO bienDAO = new BienDAO();
					Bien bien = bienDAO.getById(selectedId);
					frame.dispose();
					new Vue_ContratConfirmation(bien, locataire, agent).getFrame().setVisible(true);
				} else {
					JOptionPane.showMessageDialog(null, "Veuillez choisir une ligne");
				}

			}
		});
	}

}
