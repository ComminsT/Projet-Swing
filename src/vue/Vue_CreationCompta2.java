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
import dao.LocataireDAO;
import entite.Agent;
import entite.Bien;
import entite.Comptabilite;
import entite.Contratl;
import entite.Database;
import entite.Locataire;

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
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private JFrame frame;
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
		return frame;
	}
	public void setFrame(JFrame frame) {
		this.frame = frame;
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
Database.Connect();
		frame = new JFrame();
		frame.setBounds(100, 100, 534, 503);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);

		JButton btnSuivant = new JButton("Suivant");

		btnSuivant.setBounds(403, 11, 113, 53);
		frame.getContentPane().add(btnSuivant);

		separator = new JSeparator();
		separator.setForeground(Color.LIGHT_GRAY);
		separator.setBounds(0, 82, frame.getWidth(), 2);
		frame.getContentPane().add(separator);
		
		JLabel lblNewLabel = new JLabel("Nouvelle facture");
		lblNewLabel.setBounds(197, 11, 113, 53);
		frame.getContentPane().add(lblNewLabel);
		Date date=new Date();
		
		JButton btnRetour = new JButton("Retour");
		btnRetour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				new Vue_CreationCompta(agent).getFrame().setVisible(true);
			}
		});
		btnRetour.setBounds(11, 11, 113, 53);
		frame.getContentPane().add(btnRetour);
		
		JLabel lblNewLabel_1 = new JLabel("Sélectionnez le contrat correspondant :");
		lblNewLabel_1.setBounds(10, 91, 236, 27);
		frame.getContentPane().add(lblNewLabel_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(11, 131, 505, 330);
		frame.getContentPane().add(scrollPane);
		
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
		
		btnSuivant.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
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
					frame.dispose();
					new Vue_ComptaList(agent).getFrame().setVisible(true);	
				}
				
				
				
				}
			
		});

	}
}
