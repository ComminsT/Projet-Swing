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

import dao.LocataireDAO;
import entite.Agent;
import entite.Locataire;
import javax.swing.ImageIcon;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Vue_CreationContrat {

	private JFrame frmAjoutDunNouveau;
	private Agent agent;
	private DefaultTableModel modelLocataire;
	private JTable table;
	private JTextField txtSearch;
	private Vector originalTableModel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Vue_CreationContrat window = new Vue_CreationContrat();
					window.frmAjoutDunNouveau.setVisible(true);
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
		return frmAjoutDunNouveau;
	}

	public void setFrame(JFrame frame) {
		this.frmAjoutDunNouveau = frame;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmAjoutDunNouveau = new JFrame();
		frmAjoutDunNouveau.setTitle("Ajout d'un nouveau contrat de location");
		frmAjoutDunNouveau.setBounds(100, 100, 436, 595);
		frmAjoutDunNouveau.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmAjoutDunNouveau.getContentPane().setLayout(null);
		frmAjoutDunNouveau.setLocationRelativeTo(null);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(10, 88, 420, 2);
		frmAjoutDunNouveau.getContentPane().add(separator_1);

		JLabel btnNewButton = new JLabel("Retour");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				frmAjoutDunNouveau.dispose();
				new Vue_ContratList(agent).getFrame().setVisible(true);
			
			}
		});
		btnNewButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton.setHorizontalTextPosition(SwingConstants.CENTER);
		btnNewButton.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnNewButton.setIcon(new ImageIcon(Vue_CreationContrat.class.getResource("/img/back.png")));
		btnNewButton.setBounds(11, 11, 48, 68);
		frmAjoutDunNouveau.getContentPane().add(btnNewButton);

		JLabel btnNext = new JLabel("Suivant");
		btnNext.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		btnNext.setHorizontalTextPosition(SwingConstants.CENTER);
		btnNext.setIcon(new ImageIcon(Vue_CreationContrat.class.getResource("/img/next.png")));
		btnNext.setVerticalTextPosition(SwingConstants.BOTTOM);

		btnNext.setBounds(361, 11, 48, 66);
		frmAjoutDunNouveau.getContentPane().add(btnNext);

		JLabel lblNewLabel = new JLabel("Ajout d'un nouveau contrat de location");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(102, 30, 216, 16);
		frmAjoutDunNouveau.getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Sélectionnez un locataire ");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(0, 88, 420, 31);
		frmAjoutDunNouveau.getContentPane().add(lblNewLabel_1);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 142, 400, 330);
		frmAjoutDunNouveau.getContentPane().add(scrollPane);

		table = new JTable();

		LocataireDAO locataireDAO = new LocataireDAO();
		ArrayList<Locataire> locataires = locataireDAO.getAllFromAgent(agent.getId());
		String columns[] = { "ID", "Nom", "Prénom", "Téléphone", "Statut", "Situation" };
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
		table.setModel(modelLocataire);
		scrollPane.setViewportView(table);
		originalTableModel = (Vector) ((DefaultTableModel) table.getModel()).getDataVector().clone();

		txtSearch = new JTextField();

		txtSearch.setColumns(10);
		txtSearch.setBounds(129, 120, 281, 20);
		frmAjoutDunNouveau.getContentPane().add(txtSearch);

		JButton btnSearch = new JButton("");
		btnSearch.setBorder(null);
		btnSearch.setBackground(Color.WHITE);
		btnSearch.setOpaque(false);
		btnSearch.setIcon(new ImageIcon(Vue_CreationContrat.class.getResource("/img/search20.png")));
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String keyword = txtSearch.getText();
				DefaultTableModel currtableModel = (DefaultTableModel) table.getModel();
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
				table.setModel(currtableModel);
			}
		});

		modelLocataire = new DefaultTableModel(data, columns);
		table.setModel(modelLocataire);

		txtSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnSearch.doClick();
			}
		});

		btnSearch.setBounds(92, 120, 35, 20);
		frmAjoutDunNouveau.getContentPane().add(btnSearch);
		btnNext.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (table.getSelectedRow() != -1) {
					int row = table.convertRowIndexToModel(table.getSelectedRow());
					int selectedId = Integer.parseInt(modelLocataire.getValueAt(row, 0).toString());
					LocataireDAO locataireDAO = new LocataireDAO();
					Locataire locataire = locataireDAO.getById(selectedId);
					frmAjoutDunNouveau.dispose();
					new Vue_ContratBienSelect(locataire, agent).getFrame().setVisible(true);
				} else {
					JOptionPane.showMessageDialog(null, "Veuillez choisir une ligne");
				}

			
			}
		});
		JLabel lblBG = new JLabel("");
		lblBG.setOpaque(true);
		lblBG.setIcon(new ImageIcon(Vue_AccueilAgent.class.getResource("/img/accueil_bg.jpeg")));
		lblBG.setBounds(-16, 0, 1000, 591);
		frmAjoutDunNouveau.getContentPane().add(lblBG);
	}
}
