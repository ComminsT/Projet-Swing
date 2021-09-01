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

import dao.ProprietaireDAO;
import entite.Agent;
import entite.Proprietaire;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Vue_ProprietairesList {

	private JFrame frame;
	private JTable tableProprietaire;
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
					Vue_ProprietairesList window = new Vue_ProprietairesList();
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
	public Vue_ProprietairesList() {
		initialize();
	}

	public Vue_ProprietairesList(Agent agent) {
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

		ProprietaireDAO proprietaireDAO = new ProprietaireDAO();

		ArrayList<Proprietaire> proprietaires = proprietaireDAO.getAllByIdAgent(agent.getId());
		String columns[] = { "ID", "Nom", "Prenom", "Telephone" };
		String data[][] = new String[proprietaires.size()][columns.length];
		int i = 0;
		for (Proprietaire p : proprietaires) {
			data[i][0] = p.getId() + "";
			data[i][1] = p.getNom();
			data[i][2] = p.getPrenom();
			data[i][3] = p.getTel();
			i++;
		}
		model = new DefaultTableModel(data, columns);
		tableProprietaire = new JTable(model);
		originalTableModel = (Vector) ((DefaultTableModel) tableProprietaire.getModel()).getDataVector().clone();
		scrollPane.setViewportView(tableProprietaire);
		tableProprietaire.setAutoCreateRowSorter(true);

		JLabel btnNewLandlord = new JLabel("Nouveau propriétaire");
		btnNewLandlord.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewLandlord.setIcon(new ImageIcon(Vue_LocatairesList.class.getResource("/img/peopleAdd.png")));
		btnNewLandlord.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnNewLandlord.setHorizontalTextPosition(SwingConstants.CENTER);
		btnNewLandlord.setHorizontalAlignment(SwingConstants.CENTER);
		btnNewLandlord.setBorder(new LineBorder(new Color(0, 0, 0)));
		btnNewLandlord.setBackground(Color.LIGHT_GRAY);
		btnNewLandlord.setBounds(141, 11, 117, 69);
		frame.getContentPane().add(btnNewLandlord);
		btnNewLandlord.setOpaque(false);

		JLabel btnRetour = new JLabel("Retour");
		btnRetour.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		btnRetour.setIcon(new ImageIcon(Vue_ProprietairesList.class.getResource("/img/back.png")));
		btnRetour.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnRetour.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnRetour.setHorizontalTextPosition(SwingConstants.CENTER);
		btnRetour.setHorizontalAlignment(SwingConstants.CENTER);
		btnRetour.setBorder(new LineBorder(new Color(0, 0, 0)));
		btnRetour.setBackground(Color.LIGHT_GRAY);
		btnRetour.setBounds(10, 11, 51, 69);
		frame.getContentPane().add(btnRetour);
		btnRetour.setOpaque(false);

		JLabel btnModifier = new JLabel("Modifier");
		btnModifier.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		btnModifier.setIcon(new ImageIcon(Vue_ProprietairesList.class.getResource("/img/modify.png")));
		btnModifier.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnModifier.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnModifier.setHorizontalTextPosition(SwingConstants.CENTER);
		btnModifier.setHorizontalAlignment(SwingConstants.CENTER);
		btnModifier.setBorder(new LineBorder(new Color(0, 0, 0)));
		btnModifier.setBackground(Color.LIGHT_GRAY);
		btnModifier.setBounds(272, 11, 51, 69);
		frame.getContentPane().add(btnModifier);
		btnModifier.setOpaque(false);

		JLabel btnDetails = new JLabel("Détails");
		btnDetails.setIcon(new ImageIcon(Vue_ProprietairesList.class.getResource("/img/details.png")));
		btnDetails.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnDetails.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnDetails.setHorizontalTextPosition(SwingConstants.CENTER);
		btnDetails.setHorizontalAlignment(SwingConstants.CENTER);
		btnDetails.setBorder(new LineBorder(new Color(0, 0, 0)));
		btnDetails.setBackground(Color.LIGHT_GRAY);
		btnDetails.setBounds(403, 11, 51, 69);
		frame.getContentPane().add(btnDetails);
		btnDetails.setOpaque(false);

		JButton btnSearch = new JButton("Recherche : ");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String keyword = txtSearch.getText();
				DefaultTableModel currtableModel = (DefaultTableModel) tableProprietaire.getModel();
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
				tableProprietaire.setModel(currtableModel);

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
		lblNewLabel.setBorder(null);
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
