package vue;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

import com.toedter.calendar.JCalendar;

import dao.BienDAO;
import dao.ContratlDAO;
import dao.HistoriqueDAO;
import entite.Agent;
import entite.Bien;
import entite.Checker;
import entite.Contratl;
import entite.Database;
import entite.Historique;

import javax.swing.ImageIcon;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Vue_ContratFin {

	private JFrame frmFinDeContrat;
	private Agent agent;
	private Contratl contrat;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Vue_ContratFin window = new Vue_ContratFin();
					window.frmFinDeContrat.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Vue_ContratFin() {
		initialize();
	}
	public Vue_ContratFin(Contratl contrat, Agent agent) {
		this.contrat=contrat;
		this.agent=agent;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmFinDeContrat = new JFrame();
		frmFinDeContrat.setTitle("Fin de contrat");
		frmFinDeContrat.setBounds(100, 100, 534, 411);
		frmFinDeContrat.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmFinDeContrat.getContentPane().setLayout(null);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(Color.LIGHT_GRAY);
		separator.setBounds(0, 91, 535, 2);
		frmFinDeContrat.getContentPane().add(separator);
		
		JLabel lblNewLabel = new JLabel("Fin de contrat");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(220, 30, 77, 16);
		frmFinDeContrat.getContentPane().add(lblNewLabel);
		
		JCalendar calendar = new JCalendar();
		calendar.setBounds(105, 125, 275, 225);
		frmFinDeContrat.getContentPane().add(calendar);
		String date=contrat.getDate();
		try {
			Date date1=new SimpleDateFormat("yyyy-MM-dd").parse(date);
			calendar.setMinSelectableDate(date1);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		
		
		 
		JLabel lblNewLabel_1 = new JLabel("S??lectionnez la date de fin du contrat");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(104, 101, 276, 14);
		frmFinDeContrat.getContentPane().add(lblNewLabel_1);
		
		JLabel btnNewButton = new JLabel("Retour");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frmFinDeContrat.dispose();
				new Vue_ContratList(agent).getFrame().setVisible(true);
			}
		});
		btnNewButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton.setHorizontalTextPosition(SwingConstants.CENTER);
		btnNewButton.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnNewButton.setIcon(new ImageIcon(Vue_ContratFin.class.getResource("/img/back.png")));
		btnNewButton.setBounds(11, 11, 48, 68);
		frmFinDeContrat.getContentPane().add(btnNewButton);
		
		JLabel btnValider = new JLabel("Valider");
		btnValider.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Database.Connect();
				String mois = String.valueOf(calendar.getMonthChooser().getMonth() + 1);
				String jour = String.valueOf(calendar.getDayChooser().getDay());
				String annee = String.valueOf(calendar.getYearChooser().getYear());
				String date = annee + "-" + mois + "-" + jour;
				contrat.setDatefin(date);
				BienDAO bienDAO = new BienDAO();
				Bien bien= bienDAO.getById(contrat.getId_bien());
				bien.setStatut("En recherche de locataire");
				bienDAO.save(bien);
				ContratlDAO contratDAO = new ContratlDAO();
				contratDAO.save(contrat);
				HistoriqueDAO historiqueDAO = new HistoriqueDAO();
				Historique historique = new Historique();
				historique.setDate(Checker.getDateTime());
				historique.setId_agent(agent.getId());
				historique.setAction("Fin de contrat ID : "+contrat.getId());
				historiqueDAO.save(historique);	
				
				frmFinDeContrat.dispose();
				new Vue_ContratList(agent).getFrame().setVisible(true);
			
			}
		});
		btnValider.setHorizontalTextPosition(SwingConstants.CENTER);
		btnValider.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnValider.setIcon(new ImageIcon(Vue_ContratFin.class.getResource("/img/valider.png")));
		btnValider.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnValider.setBounds(458, 11, 48, 68);
		frmFinDeContrat.getContentPane().add(btnValider);
		
		JLabel lblBG = new JLabel("");
		lblBG.setOpaque(true);
		lblBG.setIcon(new ImageIcon(Vue_AccueilAgent.class.getResource("/img/accueil_bg.jpeg")));
		lblBG.setBounds(-300, 0, 1000, 591);
		frmFinDeContrat.getContentPane().add(lblBG);
	}

	public JFrame getFrame() {
		return frmFinDeContrat;
	}

	public void setFrame(JFrame frame) {
		this.frmFinDeContrat = frame;
	}
}
