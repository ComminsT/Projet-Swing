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
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import dao.VisiteDAO;
import entite.Agent;
import entite.Visite;
import javax.swing.JPanel;

public class Vue_VisitesList {

	private JFrame frame;
	private JTextField txtSearch;
	private JTable table_VisiteEnCours;
	private Agent agent;
	private DefaultTableModel model;
	private DefaultTableModel model2;
	private JTable table_VisitesFinis;
	private Vector originalTableModel;
	private Vector originalTableModel2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Vue_VisitesList window = new Vue_VisitesList();
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
	public Vue_VisitesList() {
		initialize();
	}

	public Vue_VisitesList(Agent agent) {
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
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		
		JPanel panel = new JPanel();
		panel.setOpaque(false);
		panel.setBounds(10, 19, 731, 75);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JButton btnModification = new JButton("Modification");
		btnModification.setBounds(608, 0, 66, 66);
		panel.add(btnModification);
		btnModification.setVerticalAlignment(SwingConstants.BOTTOM);
		btnModification.setIcon(new ImageIcon(Vue_VisitesList.class.getResource("/img/modify.png")));
		btnModification.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		btnModification.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnModification.setOpaque(false);
		btnModification.setHorizontalTextPosition(SwingConstants.CENTER);
		btnModification.setHorizontalAlignment(SwingConstants.CENTER);
		btnModification.setBorder(null);
		btnModification.setBackground(Color.LIGHT_GRAY);
		
				JButton btnRetour = new JButton("Retour");
				btnRetour.setBounds(55, 0, 48, 66);
				panel.add(btnRetour);
				btnRetour.setIcon(new ImageIcon(Vue_VisitesList.class.getResource("/img/back.png")));
				btnRetour.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				btnRetour.setOpaque(false);
				btnRetour.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						frame.dispose();
						new Vue_AccueilAgent(agent).getFrame().setVisible(true);
					}
				});
				btnRetour.setVerticalTextPosition(SwingConstants.BOTTOM);
				btnRetour.setHorizontalTextPosition(SwingConstants.CENTER);
				btnRetour.setHorizontalAlignment(SwingConstants.CENTER);
				btnRetour.setBorder(null);
				btnRetour.setBackground(Color.LIGHT_GRAY);
				
						JButton btnNouvelleVisite = new JButton("Nouvelle visite");
						btnNouvelleVisite.setBounds(158, 0, 77, 66);
						panel.add(btnNouvelleVisite);
						btnNouvelleVisite.setIcon(new ImageIcon(Vue_VisitesList.class.getResource("/img/calendar.png")));
						btnNouvelleVisite.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
						btnNouvelleVisite.setOpaque(false);
						btnNouvelleVisite.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								frame.dispose();
								new Vue_CreationVisite(agent).getFrame().setVisible(true);
							}
						});
						btnNouvelleVisite.setVerticalTextPosition(SwingConstants.BOTTOM);
						btnNouvelleVisite.setHorizontalTextPosition(SwingConstants.CENTER);
						btnNouvelleVisite.setHorizontalAlignment(SwingConstants.CENTER);
						btnNouvelleVisite.setBorder(null);
						btnNouvelleVisite.setBackground(Color.LIGHT_GRAY);
						
								JButton btnTerminerUneVisite = new JButton("Terminer");
								btnTerminerUneVisite.setBounds(290, 0, 49, 66);
								panel.add(btnTerminerUneVisite);
								btnTerminerUneVisite.setIcon(new ImageIcon(Vue_VisitesList.class.getResource("/img/terminer.png")));
								btnTerminerUneVisite.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
								btnTerminerUneVisite.setOpaque(false);
								btnTerminerUneVisite.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent e) {
										if (table_VisiteEnCours.getSelectedRow() != -1) {
											int row = table_VisiteEnCours.convertRowIndexToModel(table_VisiteEnCours.getSelectedRow());
											int selectedId = Integer.parseInt(model.getValueAt(row, 0).toString());
											VisiteDAO visiteDAO = new VisiteDAO();
											Visite visite = visiteDAO.getById(selectedId);
											frame.dispose();
											new Vue_VisiteFin(visite, agent).getFrame().setVisible(true);
										} else if (table_VisitesFinis.getSelectedRow() != -1) {
											JOptionPane.showMessageDialog(null, "Cette visite est déjà terminée");
										} else {
											JOptionPane.showMessageDialog(null, "Veuillez choisir une ligne");
										}

									}
								});
								btnTerminerUneVisite.setVerticalTextPosition(SwingConstants.BOTTOM);
								btnTerminerUneVisite.setHorizontalTextPosition(SwingConstants.CENTER);
								btnTerminerUneVisite.setHorizontalAlignment(SwingConstants.CENTER);
								btnTerminerUneVisite.setBorder(null);
								btnTerminerUneVisite.setBackground(Color.LIGHT_GRAY);
								
										JButton btnDetails = new JButton("Détails");
										btnDetails.setBounds(394, 0, 48, 66);
										panel.add(btnDetails);
										btnDetails.setIcon(new ImageIcon(Vue_VisitesList.class.getResource("/img/search.png")));
										btnDetails.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
										btnDetails.setOpaque(false);
										btnDetails.addActionListener(new ActionListener() {
											public void actionPerformed(ActionEvent e) {
												if (table_VisiteEnCours.getSelectedRow() != -1) {
													int row = table_VisiteEnCours.convertRowIndexToModel(table_VisiteEnCours.getSelectedRow());
													int selectedId = Integer.parseInt(model.getValueAt(row, 0).toString());
													VisiteDAO visiteDAO = new VisiteDAO();
													Visite visite = visiteDAO.getById(selectedId);
													new Vue_VisiteDetails(visite, agent).getFrame().setVisible(true);
												} else if (table_VisitesFinis.getSelectedRow() != -1) {
													int row = table_VisitesFinis.convertRowIndexToModel(table_VisitesFinis.getSelectedRow());
													int selectedId = Integer.parseInt(model2.getValueAt(row, 0).toString());
													VisiteDAO visiteDAO = new VisiteDAO();
													Visite visite = visiteDAO.getById(selectedId);
													new Vue_VisiteDetails(visite, agent).getFrame().setVisible(true);
												} else {
													JOptionPane.showMessageDialog(null, "Veuillez choisir une ligne");
												}
											}
										});
										btnDetails.setVerticalTextPosition(SwingConstants.BOTTOM);
										btnDetails.setHorizontalTextPosition(SwingConstants.CENTER);
										btnDetails.setHorizontalAlignment(SwingConstants.CENTER);
										btnDetails.setBorder(null);
										btnDetails.setBackground(Color.LIGHT_GRAY);
										
												JButton btnSupprimer = new JButton("Supprimer");
												btnSupprimer.setBounds(497, 0, 56, 66);
												panel.add(btnSupprimer);
												btnSupprimer.setIcon(new ImageIcon(Vue_VisitesList.class.getResource("/img/abort.png")));
												btnSupprimer.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
												btnSupprimer.setOpaque(false);
												btnSupprimer.addActionListener(new ActionListener() {
													public void actionPerformed(ActionEvent e) {
														if (table_VisiteEnCours.getSelectedRow() != -1) {
															int row = table_VisiteEnCours.convertRowIndexToModel(table_VisiteEnCours.getSelectedRow());
															int selectedId = Integer.parseInt(model.getValueAt(row, 0).toString());
															VisiteDAO visiteDAO = new VisiteDAO();
															Visite visite = visiteDAO.getById(selectedId);
															int input = JOptionPane.showConfirmDialog(null,
																	"Êtes-vous sur de vouloir supprimer cette visite ?");
															if (input == 0) {
																visiteDAO.deleteById(visite.getId());
																model.removeRow(row);
															}
														} else if (table_VisitesFinis.getSelectedRow() != -1) {
															int row = table_VisitesFinis.convertRowIndexToModel(table_VisitesFinis.getSelectedRow());
															int selectedId = Integer.parseInt(model2.getValueAt(row, 0).toString());
															VisiteDAO visiteDAO = new VisiteDAO();
															Visite visite = visiteDAO.getById(selectedId);
															int input = JOptionPane.showConfirmDialog(null,
																	"Êtes-vous sur de vouloir supprimer cette visite ?");
															if (input == 0) {
																visiteDAO.deleteById(visite.getId());
																model.removeRow(row);
															}
														} else {
															JOptionPane.showMessageDialog(null, "Veuillez choisir une ligne");
														}

													}
												});
												btnSupprimer.setVerticalTextPosition(SwingConstants.BOTTOM);
												btnSupprimer.setHorizontalTextPosition(SwingConstants.CENTER);
												btnSupprimer.setHorizontalAlignment(SwingConstants.CENTER);
												btnSupprimer.setBorder(null);
												btnSupprimer.setBackground(Color.LIGHT_GRAY);
		btnModification.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = table_VisiteEnCours.convertRowIndexToModel(table_VisiteEnCours.getSelectedRow());
				int selectedId = Integer.parseInt(model.getValueAt(row, 0).toString());
				VisiteDAO visiteDAO = new VisiteDAO();
				Visite visite = visiteDAO.getById(selectedId);
			frame.dispose();
			new Vue_VisiteModif(visite,agent).getFrame().setVisible(true);
			}
		});

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 106, 945, 474);
		frame.getContentPane().add(tabbedPane);

		JScrollPane scrollPane = new JScrollPane();
		tabbedPane.addTab("Visite en préparation", null, scrollPane, null);

		JScrollPane scrollPane_1 = new JScrollPane();
		tabbedPane.addTab("Visite terminées", null, scrollPane_1, null);

		JButton btnSearch = new JButton("Recherche");
		btnSearch.setOpaque(false);
		btnSearch.setBorder(null);
		btnSearch.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnSearch.setBounds(823, 69, 105, 20);
		frame.getContentPane().add(btnSearch);

		txtSearch = new JTextField();
		txtSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnSearch.doClick();
			}
		});
		txtSearch.setColumns(10);
		txtSearch.setBounds(784, 95, 171, 20);
		frame.getContentPane().add(txtSearch);
		VisiteDAO visiteDAO = new VisiteDAO();
		ArrayList<Visite> visites = visiteDAO.getAllByIdAgent(agent.getId());
		String columns[] = { "ID", "Nom", "Date", "Heure" };
		String data[][] = new String[visites.size()][columns.length];
		int i = 0;
		for (Visite v : visites) {
			String[] date = v.getDate().split("-");
			data[i][0] = v.getId() + "";
			data[i][1] = v.getNom();
			data[i][2] = date[2] + " / " + date[1] + " / " + date[0];
			data[i][3] = v.getHeure();
			i++;
		}
		model = new DefaultTableModel(data, columns);
		table_VisiteEnCours = new JTable(model);
		originalTableModel = (Vector) ((DefaultTableModel) table_VisiteEnCours.getModel()).getDataVector().clone();
		scrollPane.setViewportView(table_VisiteEnCours);

		ArrayList<Visite> visites2 = visiteDAO.getAllByIdAgentFINIS(agent.getId());
		String data2[][] = new String[visites2.size()][columns.length];
		int i2 = 0;
		for (Visite v : visites2) {
			String[] date = v.getDate().split("-");
			data2[i2][0] = v.getId() + "";
			data2[i2][1] = v.getNom();
			data2[i2][2] = date[2] + " / " + date[1] + " / " + date[0];
			data2[i2][3] = v.getHeure();
			i2++;
		}
		model2 = new DefaultTableModel(data2, columns);
		table_VisitesFinis = new JTable(model2);
		originalTableModel2 = (Vector) ((DefaultTableModel) table_VisitesFinis.getModel()).getDataVector().clone();

		scrollPane_1.setViewportView(table_VisitesFinis);
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(Vue_AccueilAgent.class.getResource("/img/accueil_bg.jpeg")));
		lblNewLabel_2.setBounds(-16, 0, 1000, 591);
		frame.getContentPane().add(lblNewLabel_2);

		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String keyword = txtSearch.getText();
				DefaultTableModel currtableModel = (DefaultTableModel) table_VisiteEnCours.getModel();
				DefaultTableModel currtableModel2 = (DefaultTableModel) table_VisitesFinis.getModel();
				currtableModel.setRowCount(0);
				currtableModel2.setRowCount(0);
				for (Object rows : originalTableModel) {
					Vector rowVector = (Vector) rows;
					for (Object column : rowVector) {
						if (column.toString().contains(keyword)) {
							currtableModel.addRow(rowVector);
							break;
						}
					}

				}
				
				for (Object rows : originalTableModel2) {
					Vector rowVector = (Vector) rows;
					for (Object column : rowVector) {
						if (column.toString().contains(keyword)) {
							currtableModel2.addRow(rowVector);
							break;
						}
					}

				}
				table_VisiteEnCours.setModel(currtableModel);
				table_VisitesFinis.setModel(currtableModel2);

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
