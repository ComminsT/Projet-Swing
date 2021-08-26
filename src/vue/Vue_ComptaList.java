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
import dao.ComptabiliteDAO;
import dao.LocataireDAO;
import entite.Agent;
import entite.Bien;
import entite.Comptabilite;
import entite.Locataire;
import javax.swing.JTabbedPane;

public class Vue_ComptaList {

	private JFrame frame;
	private JTable tableComptabiliteEnCours;
	private Agent agent;
	private JTextField txtSearch;
	private DefaultTableModel model;
	private DefaultTableModel model2;
	private JTable tableComptabilitePayee;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Vue_ComptaList window = new Vue_ComptaList();
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

		frame = new JFrame();
		frame.setBounds(100, 100, 981, 630);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(null);

		txtSearch = new JTextField();
		txtSearch.setEnabled(false);

		txtSearch.setBounds(654, 75, 301, 20);
		frame.getContentPane().add(txtSearch);
		txtSearch.setColumns(10);
		
		JTabbedPane tabbedPane_1 = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane_1.setBounds(10, 106, 945, 474);
		frame.getContentPane().add(tabbedPane_1);

		JScrollPane scrollPane = new JScrollPane();
		tabbedPane_1.addTab("Facture en attente", null, scrollPane, null);
		
		
		ComptabiliteDAO comptabiliteDAO = new ComptabiliteDAO();
		ArrayList<Comptabilite> comptabilites = comptabiliteDAO.getAllByIdAgentNOTPAID(agent.getId());
		String columns[] = { "ID", "Date-due", "Montant dû", "Locataire", "Bien" };
		String data[][] = new String[comptabilites.size()][columns.length];
		int i = 0;
		for (Comptabilite c : comptabilites) {
			LocataireDAO locataireDAO = new LocataireDAO();
			Locataire locataire = locataireDAO.getByIdComptabilite(c.getId());
			BienDAO bienDAO = new BienDAO();
			Bien bien = bienDAO.getByIdComptabilite(c.getId()); 
			String[]date = c.getDatedue().split("-");
			data[i][0] = c.getId() + "";
			data[i][1] = date[2]+"/"+date[1]+"/"+date[0];
			data[i][2] = c.getMontantdu()+" €";
			data[i][3] = locataire+"";
			data[i][4] = bien+"";
			i++;
		}
		model = new DefaultTableModel(data, columns);
		
		tableComptabiliteEnCours = new JTable(model);
		scrollPane.setViewportView(tableComptabiliteEnCours);
		tableComptabiliteEnCours.setAutoCreateRowSorter(true);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		tabbedPane_1.addTab("Facture payée", null, scrollPane_1, null);
		ArrayList<Comptabilite> comptabilites2 = comptabiliteDAO.getAllByIdAgentPAID(agent.getId());
		String data2[][] = new String[comptabilites2.size()][columns.length];
		 i = 0;
		for (Comptabilite c : comptabilites2) {
			LocataireDAO locataireDAO = new LocataireDAO();
			Locataire locataire = locataireDAO.getByIdComptabilite(c.getId());
			BienDAO bienDAO = new BienDAO();
			Bien bien = bienDAO.getByIdComptabilite(c.getId()); 
			String[]date = c.getDatedue().split("-");
			data2[i][0] = c.getId() + "";
			data2[i][1] = date[2]+"/"+date[1]+"/"+date[0];
			data2[i][2] = c.getMontantdu()+" €";
			data2[i][3] = locataire+"";
			data2[i][4] = bien+"";
			i++;
		}
		model2=new DefaultTableModel(data2,columns);
		tableComptabilitePayee = new JTable(model2);
		scrollPane_1.setViewportView(tableComptabilitePayee);

		JButton btnNewTenant = new JButton("Nouvelle facture");
		btnNewTenant.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewTenant.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				new Vue_CreationCompta(agent).getFrame().setVisible(true);
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
				if (tableComptabiliteEnCours.getSelectedRow() != -1) {
					int row = tableComptabiliteEnCours.convertRowIndexToModel(tableComptabiliteEnCours.getSelectedRow());
					int selectedId = Integer.parseInt(model.getValueAt(row, 0).toString());
					ComptabiliteDAO comptabiliteDAO = new ComptabiliteDAO();
					Comptabilite comptabilite = comptabiliteDAO.getById(selectedId);
					frame.dispose();
					new Vue_ComptaModif(comptabilite,agent).getFrame().setVisible(true);

				} else if(tableComptabilitePayee.getSelectedRow() != -1) {
					JOptionPane.showMessageDialog(null, "Cette facture est déjà réglée, vous ne pouvez plus la modifier");
				}else {
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
				if (tableComptabiliteEnCours.getSelectedRow() != -1) {
					int row = tableComptabiliteEnCours.convertRowIndexToModel(tableComptabiliteEnCours.getSelectedRow());
					int selectedId = Integer.parseInt(model.getValueAt(row, 0).toString());
					ComptabiliteDAO comptabiliteDAO = new ComptabiliteDAO();
					Comptabilite comptabilite = comptabiliteDAO.getById(selectedId);
					frame.dispose();
					new Vue_ComptaDetails(comptabilite,agent).getFrame().setVisible(true);

				} else if(tableComptabilitePayee.getSelectedRow()!=-1) {
					int row = tableComptabilitePayee.convertRowIndexToModel(tableComptabilitePayee.getSelectedRow());
					int selectedId = Integer.parseInt(model2.getValueAt(row, 0).toString());
					ComptabiliteDAO comptabiliteDAO = new ComptabiliteDAO();
					Comptabilite comptabilite = comptabiliteDAO.getById(selectedId);
					frame.dispose();
					new Vue_ComptaDetails(comptabilite,agent).getFrame().setVisible(true);
				}else {
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
		btnSearch.setEnabled(false);
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<Comptabilite> comptabilites = comptabiliteDAO.getByKeywordsAndByIdAgentNOTPAID(txtSearch.getText(),
						agent.getId());
				ArrayList<Comptabilite> comptabilites2 = comptabiliteDAO.getByKeywordsAndByIdAgentPAID(txtSearch.getText(),
						agent.getId());
				
				String data[][] = new String[comptabilites.size()][columns.length];
				String data2[][] = new String[comptabilites2.size()][columns.length];
				int i = 0;
				for (Comptabilite c : comptabilites) {
					LocataireDAO locataireDAO = new LocataireDAO();
					Locataire locataire = locataireDAO.getByIdComptabilite(c.getId());
					BienDAO bienDAO = new BienDAO();
					Bien bien = bienDAO.getByIdComptabilite(c.getId()); 
					data[i][0] = c.getId() + "";
					data[i][1] = c.getDatedue();
					data[i][2] = c.getMontantdu()+"";
					data[i][3] = locataire+"";
					data[i][4] = bien+"";
					i++;
				}
				i=0;
				for (Comptabilite c : comptabilites2) {
					LocataireDAO locataireDAO = new LocataireDAO();
					Locataire locataire = locataireDAO.getByIdComptabilite(c.getId());
					BienDAO bienDAO = new BienDAO();
					Bien bien = bienDAO.getByIdComptabilite(c.getId()); 
					data2[i][0] = c.getId() + "";
					data2[i][1] = c.getDatedue();
					data2[i][2] = c.getMontantdu()+"";
					data2[i][3] = locataire+"";
					data2[i][4] = bien+"";
					i++;
				}
				model2=new DefaultTableModel(data2,columns);
				tableComptabilitePayee.setModel(model2);
				
				
				model = new DefaultTableModel(data, columns);
				tableComptabiliteEnCours.setModel(model);

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
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 100, 945, 480);
		frame.getContentPane().add(tabbedPane);
	}

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}
}
