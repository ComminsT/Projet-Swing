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
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import dao.BienDAO;
import dao.ContratlDAO;
import dao.LocataireDAO;
import entite.Agent;
import entite.Bien;
import entite.Contratl;
import entite.Locataire;
import javax.swing.table.TableModel;

public class Vue_ContratList {

	private JFrame frame;
	private Agent agent;
	private DefaultTableModel model;
	private JTable tableContratEnCours;
	private JTable tableContratFini;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Vue_ContratList window = new Vue_ContratList();
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
	public Vue_ContratList() {
		initialize();
	}

	public Vue_ContratList(Agent agent) {
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

		ContratlDAO contratlDAO = new ContratlDAO();

		ArrayList<Contratl> contrats = contratlDAO.getAllByIdAgentNOTFINISHED(agent.getId());
		String columns[] = { "ID", "Nom du bien", "Nom du locataire", "Debut du contrat" };
		String data[][] = new String[contrats.size()][columns.length];
		int i = 0;
		for (Contratl p : contrats) {
			BienDAO bienDAO = new BienDAO();
			LocataireDAO locataireDAO = new LocataireDAO();
			Bien bien = bienDAO.getById(p.getId_bien());
			Locataire locataire = locataireDAO.getById(p.getId_locataire());
			data[i][0] = p.getId() + "";
			data[i][1] = bien.toString();
			data[i][2] = locataire.toString();
			data[i][3] = p.getDate();
			i++;
		}
		model = new DefaultTableModel(data, columns);

		JButton btnNewContract = new JButton("Nouveau Contrat");
		btnNewContract.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewContract.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				new Vue_CreationContrat(agent).getFrame().setVisible(true);
			}
		});

		JButton btnDetails = new JButton("Détails");

		btnDetails.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnDetails.setOpaque(false);
		btnDetails.setHorizontalTextPosition(SwingConstants.CENTER);
		btnDetails.setHorizontalAlignment(SwingConstants.CENTER);
		btnDetails.setBorder(new LineBorder(new Color(0, 0, 0)));
		btnDetails.setBackground(Color.LIGHT_GRAY);
		btnDetails.setBounds(403, 11, 121, 84);
		frame.getContentPane().add(btnDetails);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(20, 130, 872, 433);
		frame.getContentPane().add(tabbedPane);

		JScrollPane scrollPane = new JScrollPane();
		tabbedPane.addTab("Contrat en cours", null, scrollPane, null);
		tableContratEnCours = new JTable(model);
		tableContratEnCours.setAutoCreateRowSorter(true);
		scrollPane.setViewportView(tableContratEnCours);

		JScrollPane scrollPane_1 = new JScrollPane();
		tabbedPane.addTab("Contrat terminé", null, scrollPane_1, null);

		ArrayList<Contratl> contrats2 = contratlDAO.getAllByIdAgentFINISHED(agent.getId());
		String data2[][] = new String[contrats2.size()][columns.length];
		int i2 = 0;
		for (Contratl p : contrats2) {
			BienDAO bienDAO = new BienDAO();
			LocataireDAO locataireDAO = new LocataireDAO();
			Bien bien = bienDAO.getById(p.getId_bien());
			Locataire locataire = locataireDAO.getById(p.getId_locataire());
			data2[i2][0] = p.getId() + "";
			data2[i2][1] = bien.toString();
			data2[i2][2] = locataire.toString();
			data2[i2][3] = p.getDate();
			i2++;
		}
		DefaultTableModel model2 = new DefaultTableModel(data2, columns);
		tableContratFini = new JTable(model2);
		scrollPane_1.setViewportView(tableContratFini);

		btnNewContract.setIcon(new ImageIcon(Vue_LocatairesList.class.getResource("/img/peopleAdd.png")));
		btnNewContract.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnNewContract.setHorizontalTextPosition(SwingConstants.CENTER);
		btnNewContract.setHorizontalAlignment(SwingConstants.CENTER);
		btnNewContract.setBorder(new LineBorder(new Color(0, 0, 0)));
		btnNewContract.setBackground(Color.LIGHT_GRAY);
		btnNewContract.setBounds(141, 11, 121, 84);
		frame.getContentPane().add(btnNewContract);
		btnNewContract.setOpaque(false);

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

		JButton btnFin = new JButton("Fin de contrat");
		btnFin.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnFin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (tableContratEnCours.getSelectedRow() != -1) {
					int row = tableContratEnCours.convertRowIndexToModel(tableContratEnCours.getSelectedRow());
					int selectedId = Integer.parseInt(model.getValueAt(row, 0).toString());
					ContratlDAO contratDAO = new ContratlDAO();
					Contratl contrat = contratDAO.getById(selectedId);
					frame.dispose();
					new Vue_ContratFin(contrat, agent).getFrame().setVisible(true);
				} else if (tableContratFini.getSelectedRow() != -1) {
					JOptionPane.showMessageDialog(null, "Ce contrat est déjà terminé");
				} else {
					JOptionPane.showMessageDialog(null, "Veuillez choisir une ligne");
				}

			}
		});
		btnFin.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnFin.setHorizontalTextPosition(SwingConstants.CENTER);
		btnFin.setHorizontalAlignment(SwingConstants.CENTER);
		btnFin.setBorder(new LineBorder(new Color(0, 0, 0)));
		btnFin.setBackground(Color.LIGHT_GRAY);
		btnFin.setBounds(272, 11, 121, 84);
		frame.getContentPane().add(btnFin);
		btnFin.setOpaque(false);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(Vue_LocatairesList.class.getResource("/img/accueil_bg.jpeg")));
		lblNewLabel.setBounds(-26, -19, 1023, 636);
		frame.getContentPane().add(lblNewLabel);

		btnDetails.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (tableContratEnCours.getSelectedRow() != -1) {
					int row = tableContratEnCours.convertRowIndexToModel(tableContratEnCours.getSelectedRow());
					int selectedId = Integer.parseInt(model.getValueAt(row, 0).toString());
					ContratlDAO contratDAO = new ContratlDAO();
					Contratl contrat = contratDAO.getById(selectedId);
					new Vue_ContratDetails(contrat, agent).getFrame().setVisible(true);
				} else if (tableContratFini.getSelectedRow() != -1) {
					int row = tableContratFini.convertRowIndexToModel(tableContratFini.getSelectedRow());
					int selectedId = Integer.parseInt(model2.getValueAt(row, 0).toString());
					ContratlDAO contratDAO = new ContratlDAO();
					Contratl contrat = contratDAO.getById(selectedId);
					new Vue_ContratDetails(contrat, agent).getFrame().setVisible(true);
				} else {
					JOptionPane.showMessageDialog(null, "Veuillez choisir une ligne");
				}

			}
		});
	}

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}
}
