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

import dao.ContratlDAO;
import entite.Agent;
import entite.Contratl;
import entite.Database;

public class Vue_ContratFin {

	private JFrame frame;
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
		frame = new JFrame();
		frame.setBounds(100, 100, 534, 411);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(Color.LIGHT_GRAY);
		separator.setBounds(0, 88, 518, 2);
		frame.getContentPane().add(separator);
		
		JLabel lblNewLabel = new JLabel("Fin de contrat");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(167, 11, 185, 51);
		frame.getContentPane().add(lblNewLabel);
		
		JCalendar calendar = new JCalendar();
		calendar.setBounds(105, 125, 275, 225);
		frame.getContentPane().add(calendar);
		String date=contrat.getDate();
		try {
			Date date1=new SimpleDateFormat("yyyy-MM-dd").parse(date);
			calendar.setMinSelectableDate(date1);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		
		
		 
		JLabel lblNewLabel_1 = new JLabel("Sélectionnez la date de fin du contrat");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(104, 101, 276, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		JButton btnNewButton = new JButton("Retour");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				new Vue_ContratList(agent).getFrame().setVisible(true);
			}
		});
		btnNewButton.setBounds(0, 11, 145, 69);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnValider = new JButton("Valider");
		btnValider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Database.Connect();
				String mois = String.valueOf(calendar.getMonthChooser().getMonth() + 1);
				String jour = String.valueOf(calendar.getDayChooser().getDay());
				String annee = String.valueOf(calendar.getYearChooser().getYear());
				String date = annee + "-" + mois + "-" + jour;
				contrat.setDatefin(date);
				ContratlDAO contratDAO = new ContratlDAO();
				contratDAO.save(contrat);
				frame.dispose();
				new Vue_ContratList(agent).getFrame().setVisible(true);
			}
		});
		btnValider.setBounds(362, 11, 145, 69);
		frame.getContentPane().add(btnValider);
	}

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}
}
