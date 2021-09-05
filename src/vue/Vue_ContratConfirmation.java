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
import dao.HistoriqueDAO;
import dao.LocataireDAO;
import entite.Agent;
import entite.Assurance;
import entite.Bien;
import entite.Checker;
import entite.Contratl;
import entite.Database;
import entite.Garant;
import entite.Historique;
import entite.Locataire;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Cursor;
import javax.swing.JPanel;

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
		frame.setBounds(100, 100, 981, 620);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JSeparator separator = new JSeparator();
		separator.setForeground(Color.LIGHT_GRAY);
		separator.setBounds(0, 91, 965, 2);
		frame.getContentPane().add(separator);
		
		JPanel panel = new JPanel();
		panel.setOpaque(false);
		panel.setBounds(298, 105, 232, 182);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		JCalendar calendar = new JCalendar();
		calendar.setBounds(0, 28, 232, 154);
		panel.add(calendar);
		
		
		
		JLabel lblNewLabel_2_1_1 = new JLabel("Date d'activation du contrat :");
		lblNewLabel_2_1_1.setBounds(0, 0, 161, 16);
		panel.add(lblNewLabel_2_1_1);
		lblNewLabel_2_1_1.setHorizontalAlignment(SwingConstants.LEFT);

		JLabel btnNewButton = new JLabel("Retour");
		btnNewButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.dispose();
				new Vue_ContratBienSelect(locataire,agent).getFrame().setVisible(true);	
			
			}
		});
		btnNewButton.setHorizontalTextPosition(SwingConstants.CENTER);
		btnNewButton.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnNewButton.setIcon(new ImageIcon(Vue_ContratConfirmation.class.getResource("/img/back.png")));
		btnNewButton.setBounds(10, 11, 48, 68);
		frame.getContentPane().add(btnNewButton);

		JLabel lblNewLabel = new JLabel("Locataire :");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setBounds(17, 100, 65, 25);
		frame.getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBounds(92, 105, 150, 14);
		frame.getContentPane().add(lblNewLabel_1);
		lblNewLabel_1.setText(locataire.toString());

		JLabel lblBien = new JLabel("Bien :");
		lblBien.setHorizontalAlignment(SwingConstants.RIGHT);
		lblBien.setBounds(17, 136, 65, 25);
		frame.getContentPane().add(lblBien);

		JLabel lblNewLabel_1_1 = new JLabel((String) null);
		lblNewLabel_1_1.setBounds(92, 141, 150, 14);
		frame.getContentPane().add(lblNewLabel_1_1);
		lblNewLabel_1_1.setText(bien.toString());

		JButton btnAjouterDesGarants = new JButton("Ajouter des garants");
		btnAjouterDesGarants.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAjouterDesGarants.setBorder(null);
		btnAjouterDesGarants.setBackground(new Color(255,255,255,100));
		btnAjouterDesGarants.setIcon(new ImageIcon(Vue_ContratConfirmation.class.getResource("/img/garant20.png")));
		btnAjouterDesGarants.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Vue_CreationGarant().getFrame().setVisible(true);
			}
		});
		btnAjouterDesGarants.setBounds(95, 168, 194, 30);
		frame.getContentPane().add(btnAjouterDesGarants);

		JButton btnAjouterDesAssurances = new JButton("Ajouter des assurances");
		btnAjouterDesAssurances.setBackground(new Color(255,255,255,100));
		btnAjouterDesAssurances.setBorder(null);
		btnAjouterDesAssurances.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAjouterDesAssurances.setIcon(new ImageIcon(Vue_ContratConfirmation.class.getResource("/img/assurance20.png")));
		btnAjouterDesAssurances.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Vue_CreationAssurance().getFrame().setVisible(true);

			}
		});
		btnAjouterDesAssurances.setBounds(95, 358, 194, 30);
		frame.getContentPane().add(btnAjouterDesAssurances);

		JLabel lblNewLabel_2 = new JLabel("Garants :");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2.setBounds(26, 173, 56, 16);
		frame.getContentPane().add(lblNewLabel_2);

		JLabel lblNewLabel_2_1 = new JLabel("Assurances :");
		lblNewLabel_2_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2_1.setBounds(2, 362, 80, 16);
		frame.getContentPane().add(lblNewLabel_2_1);

		listgarant = new List();
		listgarant.setBounds(92, 200, 188, 154);
		frame.getContentPane().add(listgarant);

		listassurance = new List();
		listassurance.setBounds(94, 390, 188, 154);
		frame.getContentPane().add(listassurance);

		JLabel btnNewButton_1 = new JLabel("Confirmer");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

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
				ArrayList<Contratl> newContrat=contratDAO.getAllByIdAgentNOTFINISHED(agent.getId());
				Contratl newC=newContrat.get(newContrat.size()-1);
				HistoriqueDAO historiqueDAO = new HistoriqueDAO();
				Historique historique = new Historique();
				historique.setDate(Checker.getDateTime());
				historique.setId_agent(agent.getId());
				historique.setAction("Création contrat immobilier id : "+newC);
				historiqueDAO.save(historique);	
				
				frame.dispose();
				new Vue_ContratList(agent).getFrame().setVisible(true);
				

			
			}
		});
		btnNewButton_1.setHorizontalTextPosition(SwingConstants.CENTER);
		btnNewButton_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton_1.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnNewButton_1.setIcon(new ImageIcon(Vue_ContratConfirmation.class.getResource("/img/valider.png")));
		btnNewButton_1.setBounds(890, 11, 63, 68);
		frame.getContentPane().add(btnNewButton_1);
		JLabel lblBG = new JLabel("");
		lblBG.setOpaque(true);
		lblBG.setIcon(new ImageIcon(Vue_AccueilAgent.class.getResource("/img/accueil_bg.jpeg")));
		lblBG.setBounds(-16, 0, 1000, 591);
		frame.getContentPane().add(lblBG);
		
		

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
