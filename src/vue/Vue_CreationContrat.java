package vue;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import dao.BienDAO;
import dao.LocataireDAO;
import entite.Agent;
import entite.Bien;
import entite.Locataire;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class Vue_CreationContrat {

	private JFrame frame;
	private Agent agent;
	private DefaultTableModel modelLocataire;
	private JTable table_locataire;
	private JTextField txtSearch;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Vue_CreationContrat window = new Vue_CreationContrat();
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
	public Vue_CreationContrat() {
		initialize();
	}

	public Vue_CreationContrat(Agent agent) {
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
				new Vue_ContratList(agent).getFrame().setVisible(true);
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

		JLabel lblNewLabel_1 = new JLabel("Sélectionnez un locataire ");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(0, 88, 420, 31);
		frame.getContentPane().add(lblNewLabel_1);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 142, 400, 330);
		frame.getContentPane().add(scrollPane);

		table_locataire = new JTable();

		LocataireDAO locataireDAO = new LocataireDAO();
		ArrayList<Locataire> locataires = locataireDAO.getAllByIdAgent(agent.getId());
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
		modelLocataire = new DefaultTableModel(data, columns);
		table_locataire.setModel(modelLocataire);
		scrollPane.setViewportView(table_locataire);

		txtSearch = new JTextField();

		txtSearch.setColumns(10);
		txtSearch.setBounds(109, 120, 301, 20);
		frame.getContentPane().add(txtSearch);

		JButton btnSearch = new JButton("Recherche");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				LocataireDAO locataireDAO = new LocataireDAO();
				ArrayList<Locataire> locataires = locataireDAO.getByKeywordsAndByIdAgent(txtSearch.getText(),
						agent.getId());
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

			}
		});

		modelLocataire = new DefaultTableModel(data, columns);
		table_locataire.setModel(modelLocataire);

		txtSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnSearch.doClick();
			}
		});

		btnSearch.setBounds(10, 119, 89, 23);
		frame.getContentPane().add(btnSearch);

		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (table_locataire.getSelectedRow() != -1) {
					int row = table_locataire.convertRowIndexToModel(table_locataire.getSelectedRow());
					int selectedId = Integer.parseInt(modelLocataire.getValueAt(row, 0).toString());
					LocataireDAO locataireDAO = new LocataireDAO();
					Locataire locataire = locataireDAO.getById(selectedId);
					frame.dispose();
					new Vue_ContratBienSelect(locataire, agent).getFrame().setVisible(true);
				} else {
					JOptionPane.showMessageDialog(null, "Veuillez choisir une ligne");
				}

			}
		});
	}
}
