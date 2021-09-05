package vue;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;

import dao.BienDAO;
import entite.Agent;
import entite.Bien;
import entite.Visite;

public class Vue_VisiteDetails {

	private JFrame frame;
	private Agent agent;
	private Visite visite;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Vue_VisiteDetails window = new Vue_VisiteDetails();
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
	public Vue_VisiteDetails() {
		initialize();
	}
	public Vue_VisiteDetails(Visite visite,Agent agent) {
		this.visite=visite;
		this.agent=agent;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 436, 419);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		JSeparator separator = new JSeparator();
		separator.setForeground(Color.LIGHT_GRAY);
		separator.setBounds(0, 88, 420, 2);
		frame.getContentPane().add(separator);
		
		JLabel lblNewLabel = new JLabel("Détails de la visite");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(159, 30, 102, 16);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Id : ");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setBounds(94, 102, 23, 15);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Date :");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_1.setBounds(81, 129, 36, 15);
		frame.getContentPane().add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Heure :");
		lblNewLabel_1_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_1_1.setBounds(74, 156, 43, 15);
		frame.getContentPane().add(lblNewLabel_1_1_1);
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("Nom du visiteur :");
		lblNewLabel_1_1_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_1_1_1.setBounds(10, 183, 107, 15);
		frame.getContentPane().add(lblNewLabel_1_1_1_1);
		
		JLabel lblNewLabel_1_1_1_1_1 = new JLabel("Remarques :");
		lblNewLabel_1_1_1_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_1_1_1_1.setBounds(41, 236, 76, 15);
		frame.getContentPane().add(lblNewLabel_1_1_1_1_1);
		
		JTextPane txtRemarques = new JTextPane();
		txtRemarques.setEditable(false);
		txtRemarques.setBounds(10, 260, 400, 108);
		frame.getContentPane().add(txtRemarques);
		
		JLabel lblId = new JLabel("New label");
		lblId.setBounds(128, 102, 55, 16);
		frame.getContentPane().add(lblId);
		
		JLabel lblDate = new JLabel("New label");
		lblDate.setBounds(128, 129, 255, 16);
		frame.getContentPane().add(lblDate);
		
		JLabel lblHeure = new JLabel("New label");
		lblHeure.setBounds(128, 156, 90, 16);
		frame.getContentPane().add(lblHeure);
		
		JLabel lblNom = new JLabel("New label");
		lblNom.setBounds(128, 183, 151, 16);
		frame.getContentPane().add(lblNom);
	
		JLabel lblNewLabel_1_1_1_1_2 = new JLabel("Bien concerné :");
		lblNewLabel_1_1_1_1_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_1_1_1_2.setBounds(24, 210, 93, 15);
		frame.getContentPane().add(lblNewLabel_1_1_1_1_2);
		
		JLabel lblBien = new JLabel((String) null);
		lblBien.setBounds(128, 209, 253, 16);
		frame.getContentPane().add(lblBien);
		BienDAO bienDAO = new BienDAO();
		Bien bien = bienDAO.getById(visite.getId_bien());
		String[] date=visite.getDate().split("-");
		lblNom.setText(visite.getNom());
		lblHeure.setText(visite.getHeure());
		lblDate.setText(date[2]+" / "+date[1]+" / "+date[0]);
		lblId.setText(String.valueOf(visite.getId()));
		lblBien.setText(bien+"");
		if(visite.getRemarque()==null) {
			txtRemarques.setText("Pas de remarque pour l'instant");
		}else {
			txtRemarques.setText(visite.getRemarque());
		}
		
		
		
		
	}

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}
}
