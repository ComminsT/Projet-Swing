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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;

public class Vue_LocatairesList {

	private JFrame frmListeDesLocataires;
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
					window.frmListeDesLocataires.setVisible(true);
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

		frmListeDesLocataires = new JFrame();
		frmListeDesLocataires.setTitle("Liste des locataires");
		frmListeDesLocataires.setBounds(100, 100, 981, 630);
		frmListeDesLocataires.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmListeDesLocataires.setLocationRelativeTo(null);
		frmListeDesLocataires.getContentPane().setLayout(null);

		txtSearch = new JTextField();

		txtSearch.setBounds(654, 74, 301, 20);
		frmListeDesLocataires.getContentPane().add(txtSearch);
		txtSearch.setColumns(10);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 106, 945, 474);
		frmListeDesLocataires.getContentPane().add(scrollPane);

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
		model = new DefaultTableModel(data, columns);
		tableLocataires = new JTable(model);
		originalTableModel = (Vector) ((DefaultTableModel) tableLocataires.getModel()).getDataVector().clone();
		scrollPane.setViewportView(tableLocataires);
		tableLocataires.setAutoCreateRowSorter(true);
		
		JPanel panel = new JPanel();
		panel.setOpaque(false);
		panel.setBounds(10, 24, 396, 71);
		frmListeDesLocataires.getContentPane().add(panel);
		panel.setLayout(null);
		
				JLabel btnNewTenant = new JLabel("Nouveau locataire");
				btnNewTenant.setBounds(100, 0, 115, 70);
				panel.add(btnNewTenant);
				btnNewTenant.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {

						frmListeDesLocataires.dispose();
						new Vue_CreationLocataire(agent).getFrame().setVisible(true);

					}
				});
		btnNewTenant.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewTenant.setIcon(new ImageIcon(Vue_LocatairesList.class.getResource("/img/peopleAdd.png")));
		btnNewTenant.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnNewTenant.setHorizontalTextPosition(SwingConstants.CENTER);
		btnNewTenant.setHorizontalAlignment(SwingConstants.CENTER);
		btnNewTenant.setBorder(null);
		btnNewTenant.setBackground(Color.LIGHT_GRAY);
		btnNewTenant.setOpaque(false);

		JLabel btnRetour = new JLabel("Retour");
		btnRetour.setBounds(25, 1, 50, 70);
		panel.add(btnRetour);
		btnRetour.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frmListeDesLocataires.dispose();
				new Vue_AccueilAgent(agent).getFrame().setVisible(true);
			}
		});
		btnRetour.setIcon(new ImageIcon(Vue_LocatairesList.class.getResource("/img/back.png")));
		btnRetour.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnRetour.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnRetour.setHorizontalTextPosition(SwingConstants.CENTER);
		btnRetour.setHorizontalAlignment(SwingConstants.CENTER);
		btnRetour.setBorder(null);
		btnRetour.setBackground(Color.LIGHT_GRAY);
		btnRetour.setOpaque(false);

		JLabel btnModifier = new JLabel("Modifier");
		btnModifier.setBounds(240, 0, 54, 70);
		panel.add(btnModifier);
		
		btnModifier.setIcon(new ImageIcon(Vue_LocatairesList.class.getResource("/img/modify.png")));
		btnModifier.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnModifier.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnModifier.setHorizontalTextPosition(SwingConstants.CENTER);
		btnModifier.setHorizontalAlignment(SwingConstants.CENTER);
		btnModifier.setBorder(null);
		btnModifier.setBackground(Color.LIGHT_GRAY);
		btnModifier.setOpaque(false);
		
				JLabel btnDetails = new JLabel("Détails");
				btnDetails.setBounds(319, 0, 50, 70);
				panel.add(btnDetails);
				
				btnDetails.setIcon(new ImageIcon(Vue_LocatairesList.class.getResource("/img/details.png")));
				btnDetails.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				btnDetails.setVerticalTextPosition(SwingConstants.BOTTOM);
				btnDetails.setHorizontalTextPosition(SwingConstants.CENTER);
				btnDetails.setHorizontalAlignment(SwingConstants.CENTER);
				btnDetails.setBorder(null);
				btnDetails.setBackground(Color.LIGHT_GRAY);
				btnDetails.setOpaque(false);
				btnDetails.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {

						if (tableLocataires.getSelectedRow() != -1) {
							int row = tableLocataires.convertRowIndexToModel(tableLocataires.getSelectedRow());
							int selectedId = Integer.parseInt(model.getValueAt(row, 0).toString());
							LocataireDAO locataireDAO = new LocataireDAO();
							Locataire locataire = locataireDAO.getById(selectedId);
							frmListeDesLocataires.dispose();
							new Vue_LocataireDetails(locataire, agent).getFrame().setVisible(true);

						} else {
							JOptionPane.showMessageDialog(null, "Veuillez choisir une ligne");
						}
					
					}
				});
		btnModifier.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				if (tableLocataires.getSelectedRow() != -1) {
					int row = tableLocataires.convertRowIndexToModel(tableLocataires.getSelectedRow());
					int selectedId = Integer.parseInt(model.getValueAt(row, 0).toString());
					LocataireDAO locataireDAO = new LocataireDAO();
					Locataire locataire = locataireDAO.getById(selectedId);
					frmListeDesLocataires.dispose();
					new Vue_LocataireModif(locataire, agent).getFrame().setVisible(true);

				} else {
					JOptionPane.showMessageDialog(null, "Veuillez choisir une ligne");
				}

			
			}
		});

		JButton btnSearch = new JButton("");
		btnSearch.setRequestFocusEnabled(false);
		btnSearch.setOpaque(false);
		btnSearch.setBackground(Color.WHITE);
		btnSearch.setBorder(null);
		btnSearch.setIcon(new ImageIcon(Vue_LocatairesList.class.getResource("/img/search20.png")));
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
		btnSearch.setBounds(621, 74, 21, 21);
		frmListeDesLocataires.getContentPane().add(btnSearch);

		txtSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnSearch.doClick();

			}
		});
		txtSearch.requestFocus(true);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(Vue_LocatairesList.class.getResource("/img/accueil_bg.jpeg")));
		lblNewLabel.setBounds(-26, -19, 1023, 636);
		frmListeDesLocataires.getContentPane().add(lblNewLabel);
	}

	public JFrame getFrame() {
		return frmListeDesLocataires;
	}

	public void setFrame(JFrame frame) {
		this.frmListeDesLocataires = frame;
	}
}
