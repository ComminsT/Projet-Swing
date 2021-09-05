package vue;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import dao.AgentDAO;
import entite.Agent;
import entite.Checker;
import entite.Database;

public class Vue_AccueilAdmin {

	private JFrame frame;
	private JTable tableAgent;
	private JTextField txtSearch;
	private DefaultTableModel model;
	private Vector originalTableModel;
	private Agent agent;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Vue_AccueilAdmin window = new Vue_AccueilAdmin();
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
	public Vue_AccueilAdmin() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		Database.Connect();
		System.out.println(System.getProperty("user.dir"));
		String chemin = System.getProperty("user.dir");
		System.out.println(chemin);
		Date date = new Date();
		String seconde=String.valueOf(date.getSeconds());
		String minute = String.valueOf(date.getMinutes());
		String heure=String.valueOf(date.getHours());
		
		String jour = "";
		if(date.getDate()<10) {
			jour="0"+date.getDate();
		}else {
			jour=date.getDate()+"";
		}
		
		String moisnbr="";
		if(date.getMonth()<10) {
			 moisnbr ="0"+(date.getMonth()+1);
		}else {
			moisnbr=date.getMonth()+1+"";
		}
		String mois=Checker.getMonthName();
		String annees=String.valueOf(date.getYear()+1900);
		String dateTimeSQL=annees+"-"+moisnbr+"-"+jour+" "+heure+":"+minute+":"+seconde;
		System.out.println(dateTimeSQL);

		frame = new JFrame();
		frame.setBounds(100, 100, 981, 630);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(null);

		txtSearch = new JTextField();

		txtSearch.setBounds(601, 75, 354, 20);
		frame.getContentPane().add(txtSearch);
		txtSearch.setColumns(10);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 106, 945, 474);
		frame.getContentPane().add(scrollPane);

		AgentDAO agentDAO = new AgentDAO();
		ArrayList<Agent> agents = agentDAO.getAll();
		String columns[] = { "ID", "Nom", "Prénom", "Téléphone", "Statut" };
		String data[][] = new String[agents.size()][columns.length];
		int i = 0;
		for (Agent a : agents) {
			data[i][0] = a.getId() + "";
			data[i][1] = a.getNom();
			data[i][2] = a.getPrenom();
			data[i][3] = a.getTel();
			data[i][4] = a.getStatut();
			i++;
		}
		model = new DefaultTableModel(data, columns);
		tableAgent = new JTable(model);
		originalTableModel = (Vector) ((DefaultTableModel) tableAgent.getModel()).getDataVector().clone();
		scrollPane.setViewportView(tableAgent);
		tableAgent.setAutoCreateRowSorter(true);
		

		JPanel panel = new JPanel();
		panel.setOpaque(false);
		panel.setBounds(10, 24, 550, 71);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		JLabel btnNewTenant = new JLabel("Nouvel agent");
		btnNewTenant.setBounds(124, 2, 74, 68);
		panel.add(btnNewTenant);
		btnNewTenant.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				frame.dispose();
				new Vue_CreationAgent().getFrame().setVisible(true);

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
		btnRetour.setBounds(10, 0, 50, 70);
		panel.add(btnRetour);
		btnRetour.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.dispose();
				new Vue_Login().getFrame().setVisible(true);
			}
		});
		btnRetour.setIcon(new ImageIcon(Vue_AccueilAdmin.class.getResource("/img/logOut.png")));
		btnRetour.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnRetour.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnRetour.setHorizontalTextPosition(SwingConstants.CENTER);
		btnRetour.setHorizontalAlignment(SwingConstants.CENTER);
		btnRetour.setBorder(null);
		btnRetour.setBackground(Color.LIGHT_GRAY);
		btnRetour.setOpaque(false);

		JLabel btnModifier = new JLabel("Modifier");
		btnModifier.setBounds(284, 2, 65, 66);
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
		btnDetails.setBounds(435, 2, 65, 66);
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

				if (tableAgent.getSelectedRow() != -1) {
					int row = tableAgent.convertRowIndexToModel(tableAgent.getSelectedRow());
					int selectedId = Integer.parseInt(model.getValueAt(row, 0).toString());
					AgentDAO agentDAO = new AgentDAO();
					Agent agent = agentDAO.getById(selectedId);
					frame.dispose();
					new Vue_AgentDetails(agent).getFrame().setVisible(true);

				} else {
					JOptionPane.showMessageDialog(null, "Veuillez choisir une ligne");
				}

			}
		});
		btnModifier.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				if (tableAgent.getSelectedRow() != -1) {
					int row = tableAgent.convertRowIndexToModel(tableAgent.getSelectedRow());
					int selectedId = Integer.parseInt(model.getValueAt(row, 0).toString());
					AgentDAO agentDAO = new AgentDAO();
					Agent agent = agentDAO.getById(selectedId);
					frame.dispose();
					new Vue_AgentModif(agent).getFrame().setVisible(true);

				} else {
					JOptionPane.showMessageDialog(null, "Veuillez choisir une ligne");
				}

			}
		});

		JButton btnSearch = new JButton("");
		btnSearch.setBackground(Color.WHITE);
		btnSearch.setBorder(null);
		btnSearch.setOpaque(false);
		btnSearch.setIcon(new ImageIcon(Vue_AccueilAdmin.class.getResource("/img/search20.png")));
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String keyword = txtSearch.getText();
				DefaultTableModel currtableModel = (DefaultTableModel) tableAgent.getModel();
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
				tableAgent.setModel(currtableModel);

			}
		});
		btnSearch.setHorizontalAlignment(SwingConstants.RIGHT);
		btnSearch.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnSearch.setBounds(570, 75, 21, 21);
		frame.getContentPane().add(btnSearch);

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
