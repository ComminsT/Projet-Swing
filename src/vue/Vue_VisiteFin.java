package vue;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;

import dao.BienDAO;
import dao.VisiteDAO;
import entite.Agent;
import entite.Bien;
import entite.Visite;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Cursor;

public class Vue_VisiteFin {

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
					Vue_VisiteFin window = new Vue_VisiteFin();
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
	public Vue_VisiteFin() {
		initialize();
	}
	public Vue_VisiteFin(Visite visite,Agent agent) {
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
		
		JLabel lblNewLabel = new JLabel("Finaliser la visite");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(163, 30, 94, 16);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Id : ");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setBounds(49, 101, 68, 16);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Date :");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_1.setBounds(49, 128, 68, 16);
		frame.getContentPane().add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Heure :");
		lblNewLabel_1_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_1_1.setBounds(49, 153, 68, 16);
		frame.getContentPane().add(lblNewLabel_1_1_1);
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("Nom du visiteur :");
		lblNewLabel_1_1_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_1_1_1.setBounds(-2, 180, 119, 16);
		frame.getContentPane().add(lblNewLabel_1_1_1_1);
		
		JLabel lblNewLabel_1_1_1_1_1 = new JLabel("Remarques :");
		lblNewLabel_1_1_1_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_1_1_1_1.setBounds(-2, 233, 119, 16);
		frame.getContentPane().add(lblNewLabel_1_1_1_1_1);
		
		JTextPane txtRemarques = new JTextPane();
		txtRemarques.setBounds(10, 260, 400, 108);
		frame.getContentPane().add(txtRemarques);
		
		JLabel lblId = new JLabel("New label");
		lblId.setBounds(129, 102, 8, 15);
		frame.getContentPane().add(lblId);
		
		JLabel lblDate = new JLabel("New label");
		lblDate.setBounds(129, 129, 246, 15);
		frame.getContentPane().add(lblDate);
		
		JLabel lblHeure = new JLabel("New label");
		lblHeure.setBounds(129, 154, 72, 15);
		frame.getContentPane().add(lblHeure);
		
		JLabel lblNom = new JLabel("New label");
		lblNom.setBounds(129, 181, 72, 15);
		frame.getContentPane().add(lblNom);
	
		JLabel lblNewLabel_1_1_1_1_2 = new JLabel("Bien concerné :");
		lblNewLabel_1_1_1_1_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_1_1_1_2.setBounds(-2, 205, 119, 16);
		frame.getContentPane().add(lblNewLabel_1_1_1_1_2);
		
		JLabel lblBien = new JLabel((String) null);
		lblBien.setBounds(129, 206, 24, 15);
		frame.getContentPane().add(lblBien);
		BienDAO bienDAO = new BienDAO();
		Bien bien = bienDAO.getById(visite.getId_bien());
		String[] date=visite.getDate().split("-");
		lblNom.setText(visite.getNom());
		lblHeure.setText(visite.getHeure());
		lblDate.setText(date[2]+" / "+date[1]+" / "+date[0]);
		lblId.setText(String.valueOf(visite.getId()));
		lblBien.setText(bien+"");
		
		JLabel btnValider = new JLabel("Valider");
		btnValider.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnValider.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				visite.setRemarque(txtRemarques.getText());
                VisiteDAO visiteDAO = new VisiteDAO();
                visiteDAO.save(visite);
                frame.dispose();
                new Vue_VisitesList(agent).getFrame().setVisible(true);
			}
		});
		btnValider.setHorizontalTextPosition(SwingConstants.CENTER);
		btnValider.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnValider.setIcon(new ImageIcon(Vue_VisiteFin.class.getResource("/img/valider.png")));
		btnValider.setBounds(360, 11, 48, 68);
		frame.getContentPane().add(btnValider);
		
		JLabel btnRetour = new JLabel("Retour");
		btnRetour.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnRetour.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.dispose();
				new Vue_VisitesList(agent).getFrame().setVisible(true);
			}
		});
		btnRetour.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnRetour.setHorizontalTextPosition(SwingConstants.CENTER);
		btnRetour.setIcon(new ImageIcon(Vue_VisiteFin.class.getResource("/img/back.png")));
		btnRetour.setBounds(11, 11, 48, 68);
		frame.getContentPane().add(btnRetour);
		if(visite.getRemarque()==null) {
			txtRemarques.setText("Pas de remarque pour l'instant");
		}else {
			txtRemarques.setText(visite.getRemarque());
		}
		
		JLabel lblBG = new JLabel("");
		lblBG.setOpaque(true);
		lblBG.setIcon(new ImageIcon(Vue_AccueilAgent.class.getResource("/img/accueil_bg.jpeg")));
		lblBG.setBounds(-16, 0, 1000, 591);
		frame.getContentPane().add(lblBG);
		
		
		
		
	}

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}
}
