package vue;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

import dao.BienDAO;
import dao.ComptabiliteDAO;
import dao.ContratlDAO;
import dao.HistoriqueDAO;
import dao.LocataireDAO;
import entite.Agent;
import entite.Bien;
import entite.Checker;
import entite.Comptabilite;
import entite.Contratl;
import entite.Database;
import entite.Historique;
import entite.Locataire;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Vue_CreationCompta2 {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					Vue_CreationCompta2 window = new Vue_CreationCompta2();
					window.frmAjoutDuneNouvelle.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private JFrame frmAjoutDuneNouvelle;
	private JSeparator separator;
	private Agent agent;
	private Comptabilite compta;
	private JTable table;
	private DefaultTableModel model;

	/**
	 * Create the application.
	 */
	public Vue_CreationCompta2() {
		initialize();

	}
	public Vue_CreationCompta2(Comptabilite compta, Agent agent) {
		this.compta=compta;
		this.agent=agent;
		initialize();
	}

	public JFrame getFrame() {
		return frmAjoutDuneNouvelle;
	}
	public void setFrame(JFrame frame) {
		this.frmAjoutDuneNouvelle = frame;
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
Database.Connect();
		frmAjoutDuneNouvelle = new JFrame();
		frmAjoutDuneNouvelle.setTitle("Ajout d'une nouvelle facture");
		frmAjoutDuneNouvelle.setBounds(100, 100, 534, 503);
		frmAjoutDuneNouvelle.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frmAjoutDuneNouvelle.getContentPane().setLayout(null);
		frmAjoutDuneNouvelle.setResizable(false);
		frmAjoutDuneNouvelle.setLocationRelativeTo(null);

		JLabel btnSuivant = new JLabel("Suivant");
		
		btnSuivant.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnSuivant.setHorizontalTextPosition(SwingConstants.CENTER);
		btnSuivant.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnSuivant.setIcon(new ImageIcon(Vue_CreationCompta2.class.getResource("/img/next.png")));

		btnSuivant.setBounds(469, 11, 48, 68);
		frmAjoutDuneNouvelle.getContentPane().add(btnSuivant);

		separator = new JSeparator();
		separator.setForeground(Color.LIGHT_GRAY);
		separator.setBounds(0, 82, frmAjoutDuneNouvelle.getWidth(), 2);
		frmAjoutDuneNouvelle.getContentPane().add(separator);
		
		JLabel lblNewLabel = new JLabel("Ajout d'une nouvelle facture");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(218, 30, 92, 16);
		frmAjoutDuneNouvelle.getContentPane().add(lblNewLabel);
		Date date=new Date();
		
		JLabel btnRetour = new JLabel("Retour");
		btnRetour.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				frmAjoutDuneNouvelle.dispose();
				new Vue_CreationCompta(agent).getFrame().setVisible(true);
			
			}
		});
		btnRetour.setHorizontalTextPosition(SwingConstants.CENTER);
		btnRetour.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnRetour.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnRetour.setIcon(new ImageIcon(Vue_CreationCompta2.class.getResource("/img/back.png")));
		btnRetour.setBounds(11, 11, 48, 68);
		frmAjoutDuneNouvelle.getContentPane().add(btnRetour);
		
		JLabel lblNewLabel_1 = new JLabel("Sélectionnez le contrat correspondant :");
		lblNewLabel_1.setBounds(10, 91, 236, 27);
		frmAjoutDuneNouvelle.getContentPane().add(lblNewLabel_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(11, 131, 505, 330);
		frmAjoutDuneNouvelle.getContentPane().add(scrollPane);
		
		ContratlDAO contratlDAO = new ContratlDAO();
		ArrayList<Contratl> contrats = contratlDAO.getAllByIdAgentNOTFINISHED(agent.getId());
		String columns[] = { "ID", "Nom du bien", "Nom du locataire" };
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
			i++;
		}
		model = new DefaultTableModel(data, columns);
		table = new JTable(model);
		scrollPane.setViewportView(table);
		btnSuivant.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(table.getSelectedRow()==-1) {
					JOptionPane.showMessageDialog(null, "Veuillez sélectionnez un contrat de la liste");
				}else {
					Database.Connect();
					int row = table.convertRowIndexToModel(table.getSelectedRow());
					int selectedId = Integer.parseInt(model.getValueAt(row, 0).toString());
					ContratlDAO contratDAO = new ContratlDAO();
					Contratl contrat = contratDAO.getById(selectedId);
					compta.setId_contratl(contrat.getId());
					compta.setDatepaye(null);
					compta.setMontantpaye(0.0);
					ComptabiliteDAO comptaDAO = new ComptabiliteDAO();
					comptaDAO.save(compta);
					ArrayList<Comptabilite>comp=comptaDAO.getAll();
					compta=comp.get(comp.size()-1);
					 HistoriqueDAO historiqueDAO = new HistoriqueDAO();
						Historique historique = new Historique();
						historique.setDate(Checker.getDateTime());
						historique.setId_agent(agent.getId());
						historique.setAction("Creation facture id : "+compta.getId());
						historiqueDAO.save(historique);
					frmAjoutDuneNouvelle.dispose();
					new Vue_ComptaList(agent).getFrame().setVisible(true);	
				}
				
				
				
				
			}
		});
		JLabel lblBG = new JLabel("");
		lblBG.setOpaque(true);
		lblBG.setIcon(new ImageIcon(Vue_AccueilAgent.class.getResource("/img/accueil_bg.jpeg")));
		lblBG.setBounds(-16, 0, 1000, 591);
		frmAjoutDuneNouvelle.getContentPane().add(lblBG);

	}
}
