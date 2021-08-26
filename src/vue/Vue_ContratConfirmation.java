package vue;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

import com.toedter.calendar.JCalendar;

import dao.AssuranceDAO;
import dao.BienDAO;
import dao.ContratlDAO;
import dao.GarantDAO;
import dao.LocataireDAO;
import entite.Agent;
import entite.Assurance;
import entite.Bien;
import entite.Contratl;
import entite.Database;
import entite.Garant;
import entite.Locataire;

public class Vue_ContratConfirmation {

	private JFrame frame;
	private Agent agent;
	private Locataire locataire;
	private Bien bien;
	private Contratl contrat;
	private static List listgarant;
	private static List listassurance;
	private static ArrayList<Garant> garants = new ArrayList<Garant>();
	private static ArrayList<Assurance> assurances = new ArrayList<Assurance>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Vue_ContratConfirmation window = new Vue_ContratConfirmation();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * 
	 * @param agent
	 * @param locataire
	 * @param bien
	 */
	public Vue_ContratConfirmation() {
		initialize();
	}

	public Vue_ContratConfirmation(Bien bien, Locataire locataire, Agent agent) {
		this.bien = bien;
		this.locataire = locataire;
		this.agent = agent;

		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		contrat = new Contratl();
		contrat.setId_locataire(locataire.getId());
		contrat.setId_bien(bien.getId());
		frame = new JFrame();
		frame.setBounds(100, 100, 527, 826);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JSeparator separator = new JSeparator();
		separator.setForeground(Color.LIGHT_GRAY);
		separator.setBounds(0, 88, 420, 2);
		frame.getContentPane().add(separator);
		JCalendar calendar = new JCalendar();
		calendar.setBounds(41, 586, 232, 154);
		frame.getContentPane().add(calendar);

		JButton btnNewButton = new JButton("Retour");
		btnNewButton.setBounds(10, 11, 116, 66);
		frame.getContentPane().add(btnNewButton);

		JLabel lblNewLabel = new JLabel("Locataire :");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setBounds(10, 101, 65, 25);
		frame.getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBounds(85, 106, 150, 14);
		frame.getContentPane().add(lblNewLabel_1);
		lblNewLabel_1.setText(locataire.toString());

		JLabel lblBien = new JLabel("Bien :");
		lblBien.setHorizontalAlignment(SwingConstants.RIGHT);
		lblBien.setBounds(10, 137, 65, 25);
		frame.getContentPane().add(lblBien);

		JLabel lblNewLabel_1_1 = new JLabel((String) null);
		lblNewLabel_1_1.setBounds(85, 142, 150, 14);
		frame.getContentPane().add(lblNewLabel_1_1);
		lblNewLabel_1_1.setText(bien.toString());

		JButton btnAjouterDesGarants = new JButton("Ajouter des garants");
		btnAjouterDesGarants.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Vue_CreationGarant().getFrame().setVisible(true);
			}
		});
		btnAjouterDesGarants.setBounds(85, 167, 188, 25);
		frame.getContentPane().add(btnAjouterDesGarants);

		JButton btnAjouterDesAssurances = new JButton("Ajouter des assurances");
		btnAjouterDesAssurances.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Vue_CreationAssurance().getFrame().setVisible(true);

			}
		});
		btnAjouterDesAssurances.setBounds(85, 358, 188, 25);
		frame.getContentPane().add(btnAjouterDesAssurances);

		JLabel lblNewLabel_2 = new JLabel("Garants :");
		lblNewLabel_2.setBounds(29, 173, 46, 14);
		frame.getContentPane().add(lblNewLabel_2);

		JLabel lblNewLabel_2_1 = new JLabel("Assurances :");
		lblNewLabel_2_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2_1.setBounds(0, 363, 75, 14);
		frame.getContentPane().add(lblNewLabel_2_1);

		listgarant = new List();
		listgarant.setBounds(85, 198, 188, 154);
		frame.getContentPane().add(listgarant);

		listassurance = new List();
		listassurance.setBounds(85, 389, 188, 154);
		frame.getContentPane().add(listassurance);

		JButton btnNewButton_1 = new JButton("Confirmer");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Database.Connect();
				String mois = String.valueOf(calendar.getMonthChooser().getMonth() + 1);
				String jour = String.valueOf(calendar.getDayChooser().getDay());
				String annee = String.valueOf(calendar.getYearChooser().getYear());
				String date = annee + "-" + mois + "-" + jour;
				contrat.setDate(date);
				LocataireDAO locataireDAO = new LocataireDAO();
				locataire.setStatut("Locataire");
				locataireDAO.save(locataire);
				BienDAO bienDAO = new BienDAO();
				bien.setStatut("En location");
				bienDAO.save(bien);
				AssuranceDAO assuranceDAO = new AssuranceDAO();
				GarantDAO garantDAO = new GarantDAO();
				ContratlDAO contratDAO = new ContratlDAO();
				contratDAO.save(contrat);
				ArrayList<Contratl> contrats = contratDAO.getAll();
				Contratl contratsaved = contrats.get(contrats.size() - 1);
				for (Garant g : garants) {
					g.setId_contratl(contratsaved.getId());
					garantDAO.save(g);
				}
				for (Assurance a : assurances) {
					a.setId_contratl(contratsaved.getId());
					assuranceDAO.save(a);
				}
				frame.dispose();
				new Vue_ContratList(agent).getFrame().setVisible(true);
				

			}
		});
		btnNewButton_1.setBounds(348, 707, 135, 69);
		frame.getContentPane().add(btnNewButton_1);
		
		
		
		JLabel lblNewLabel_2_1_1 = new JLabel("Date d'activation du contrat :");
		lblNewLabel_2_1_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_2_1_1.setBounds(10, 561, 264, 14);
		frame.getContentPane().add(lblNewLabel_2_1_1);

	}

	public static void addToAssurance(String type, Assurance assurance) {
		listassurance.add(type);
		assurances.add(assurance);

	}

	public static void addToList(String nom, Garant garant) {
		listgarant.add(nom);
		garants.add(garant);
	}

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}
}
