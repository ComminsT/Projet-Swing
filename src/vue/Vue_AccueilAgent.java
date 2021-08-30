package vue;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import dao.BienDAO;
import dao.VisiteDAO;
import entite.Agent;
import entite.Bien;
import entite.Visite;

public class Vue_AccueilAgent {

	private JFrame frame;
	private Agent agent;
	private JTable table;
	private Vector originalTableModel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Vue_AccueilAgent window = new Vue_AccueilAgent();
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
	public Vue_AccueilAgent() {
		initialize();
	}

	public Vue_AccueilAgent(Agent agent) {
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

		JButton btnLocataires = new JButton("Locataires");
		btnLocataires.setBorderPainted(false);
		btnLocataires.setOpaque(false);
		btnLocataires.setIcon(new ImageIcon(Vue_AccueilAgent.class.getResource("/img/locataires.png")));
		btnLocataires.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				frame.setCursor(Cursor.HAND_CURSOR);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				frame.setCursor(Cursor.DEFAULT_CURSOR);
			}
		});
		btnLocataires.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Vue_LocatairesList(agent).getFrame().setVisible(true);
				frame.dispose();

			}
		});

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(6, 198, 305, 133);
		frame.getContentPane().add(scrollPane);

		VisiteDAO visiteDAO = new VisiteDAO();
		ArrayList<Visite> visites = visiteDAO.getAllByIdAgentTODAY(agent.getId());
		String[] columns = { "Heure", "Visiteur", "Bien" };
		String[][] data = new String[visites.size()][columns.length];
		int i = 0;
		BienDAO bienDAO = new BienDAO();
		Bien bien = new Bien();
		for (Visite v : visites) {
			bien = bienDAO.getById(v.getId_bien());
			data[i][0] = v.getHeure();
			data[i][1] = v.getNom();
			data[i][2] = bien + "";
			i++;
		}

		DefaultTableModel model = new DefaultTableModel(data, columns);

		table = new JTable(model);
		table.setAutoCreateRowSorter(true);
		scrollPane.setViewportView(table);
		btnLocataires.setBorder(null);
		btnLocataires.setBackground(Color.LIGHT_GRAY);
		btnLocataires.setHorizontalTextPosition(SwingConstants.CENTER);
		btnLocataires.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnLocataires.setHorizontalAlignment(SwingConstants.CENTER);
		btnLocataires.setBounds(20, 21, 121, 84);
		frame.getContentPane().add(btnLocataires);
	



		JButton btnProprietaires = new JButton("Proprietaires");
		btnProprietaires.setBorderPainted(false);
		btnProprietaires.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				new Vue_ProprietairesList(agent).getFrame().setVisible(true);
			}
		});
		btnProprietaires.setIcon(new ImageIcon(Vue_AccueilAgent.class.getResource("/img/proprietaire.png")));
		btnProprietaires.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				frame.setCursor(Cursor.HAND_CURSOR);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				frame.setCursor(Cursor.DEFAULT_CURSOR);
			}
		});
		btnProprietaires.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnProprietaires.setHorizontalTextPosition(SwingConstants.CENTER);
		btnProprietaires.setHorizontalAlignment(SwingConstants.CENTER);
		btnProprietaires.setBorder(null);
		btnProprietaires.setBackground(Color.LIGHT_GRAY);
		btnProprietaires.setBounds(153, 21, 121, 84);
		frame.getContentPane().add(btnProprietaires);
		btnProprietaires.setOpaque(false);

		JButton btnBiens = new JButton("Biens");
		btnBiens.setBorderPainted(false);
		btnBiens.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Vue_BiensList(agent).getFrame().setVisible(true);
				frame.dispose();
			}
		});
		btnBiens.setIcon(new ImageIcon(Vue_AccueilAgent.class.getResource("/img/bien.png")));
		btnBiens.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				frame.setCursor(Cursor.HAND_CURSOR);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				frame.setCursor(Cursor.DEFAULT_CURSOR);
			}
		});
		btnBiens.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnBiens.setHorizontalTextPosition(SwingConstants.CENTER);
		btnBiens.setHorizontalAlignment(SwingConstants.CENTER);
		btnBiens.setBorder(null);
		btnBiens.setBackground(Color.LIGHT_GRAY);
		btnBiens.setBounds(286, 21, 121, 84);
		frame.getContentPane().add(btnBiens);
		btnBiens.setOpaque(false);

		JButton btnVisites = new JButton("Visites");
		btnVisites.setBorderPainted(false);
		btnVisites.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				new Vue_VisitesList(agent).getFrame().setVisible(true);
			}
		});
		btnVisites.setIcon(new ImageIcon(Vue_AccueilAgent.class.getResource("/img/visits.png")));
		btnVisites.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				frame.setCursor(Cursor.HAND_CURSOR);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				frame.setCursor(Cursor.DEFAULT_CURSOR);
			}
		});
		btnVisites.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnVisites.setHorizontalTextPosition(SwingConstants.CENTER);
		btnVisites.setHorizontalAlignment(SwingConstants.CENTER);
		btnVisites.setBorder(null);
		btnVisites.setBackground(Color.LIGHT_GRAY);
		btnVisites.setBounds(419, 21, 121, 84);
		frame.getContentPane().add(btnVisites);
		btnVisites.setOpaque(false);

		JButton btnComptabilite = new JButton("Comptabilité");
		btnComptabilite.setBorderPainted(false);
		btnComptabilite.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				new Vue_ComptaList(agent).getFrame().setVisible(true);
			}
		});
		btnComptabilite.setIcon(new ImageIcon(Vue_AccueilAgent.class.getResource("/img/compta.png")));
		btnComptabilite.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				frame.setCursor(Cursor.HAND_CURSOR);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				frame.setCursor(Cursor.DEFAULT_CURSOR);
			}
		});
		btnComptabilite.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnComptabilite.setHorizontalTextPosition(SwingConstants.CENTER);
		btnComptabilite.setHorizontalAlignment(SwingConstants.CENTER);
		btnComptabilite.setBorder(null);
		btnComptabilite.setBackground(Color.LIGHT_GRAY);
		btnComptabilite.setBounds(552, 21, 121, 84);
		frame.getContentPane().add(btnComptabilite);
		btnComptabilite.setOpaque(false);

		JButton btnEspacePersonnelle = new JButton("Espace personnel");
		btnEspacePersonnelle.setIcon(new ImageIcon(Vue_AccueilAgent.class.getResource("/img/personal.png")));
		btnEspacePersonnelle.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				frame.setCursor(Cursor.HAND_CURSOR);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				frame.setCursor(Cursor.DEFAULT_CURSOR);
			}
		});
		btnEspacePersonnelle.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnEspacePersonnelle.setHorizontalTextPosition(SwingConstants.CENTER);
		btnEspacePersonnelle.setHorizontalAlignment(SwingConstants.CENTER);
		btnEspacePersonnelle.setBorder(new LineBorder(new Color(0, 0, 0)));
		btnEspacePersonnelle.setBackground(Color.LIGHT_GRAY);
		btnEspacePersonnelle.setBounds(715, 501, 121, 84);
		frame.getContentPane().add(btnEspacePersonnelle);
		btnEspacePersonnelle.setOpaque(false);

		JButton btnDeconnexion = new JButton("Déconnexion");
		btnDeconnexion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				new Vue_Login().getFrame().setVisible(true);
			}
		});
		btnDeconnexion.setIcon(new ImageIcon(Vue_AccueilAgent.class.getResource("/img/logOut.png")));
		btnDeconnexion.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				frame.setCursor(Cursor.HAND_CURSOR);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				frame.setCursor(Cursor.DEFAULT_CURSOR);
			}
		});
		btnDeconnexion.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnDeconnexion.setHorizontalTextPosition(SwingConstants.CENTER);
		btnDeconnexion.setHorizontalAlignment(SwingConstants.CENTER);
		btnDeconnexion.setBorder(new LineBorder(new Color(0, 0, 0)));
		btnDeconnexion.setBackground(Color.LIGHT_GRAY);
		btnDeconnexion.setBounds(838, 501, 121, 84);
		frame.getContentPane().add(btnDeconnexion);
		btnDeconnexion.setOpaque(false);

		JLabel lblNewLabel_1 = new JLabel("Bienvenue " + agent.getPrenom());
		lblNewLabel_1.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblNewLabel_1.setBounds(20, 128, 254, 58);
		frame.getContentPane().add(lblNewLabel_1);

		JButton btnContrats = new JButton("Contrats");
		btnContrats.setBorderPainted(false);
		btnContrats.setIcon(new ImageIcon(Vue_AccueilAgent.class.getResource("/img/contract.png")));
		btnContrats.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnContrats.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				new Vue_ContratList(agent).getFrame().setVisible(true);

			}
		});

		btnContrats.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnContrats.setOpaque(false);
		btnContrats.setHorizontalTextPosition(SwingConstants.CENTER);
		btnContrats.setHorizontalAlignment(SwingConstants.CENTER);
		btnContrats.setBorder(null);
		btnContrats.setBackground(Color.LIGHT_GRAY);
		btnContrats.setBounds(685, 21, 121, 84);
		frame.getContentPane().add(btnContrats);

		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(Vue_AccueilAgent.class.getResource("/img/accueil_bg.jpeg")));
		lblNewLabel_2.setBounds(-16, 0, 1000, 591);
		frame.getContentPane().add(lblNewLabel_2);
	}

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}
}
