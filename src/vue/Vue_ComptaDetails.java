package vue;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JCalendar;

import dao.BienDAO;
import dao.ComptabiliteDAO;
import dao.LocataireDAO;
import entite.Agent;
import entite.Bien;
import entite.Comptabilite;
import entite.Locataire;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.JSeparator;

public class Vue_ComptaDetails {

	private JFrame frame;
	private Agent agent;
	private DefaultTableModel model;
	private Comptabilite compta;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Vue_ComptaDetails window = new Vue_ComptaDetails();
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
	public Vue_ComptaDetails() {
		initialize();
	}

	public Vue_ComptaDetails(Comptabilite compta, Agent agent) {
		this.compta = compta;
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

		ComptabiliteDAO comptabiliteDAO = new ComptabiliteDAO();
		ArrayList<Comptabilite> comptabilites = comptabiliteDAO.getAllByIdAgentNOTPAID(agent.getId());
		String columns[] = { "ID", "Date-due", "Montant dû", "Locataire", "Bien" };
		String data[][] = new String[comptabilites.size()][columns.length];
		int i = 0;
		for (Comptabilite c : comptabilites) {
			LocataireDAO locataireDAO = new LocataireDAO();
			Locataire locataire = locataireDAO.getByIdComptabilite(c.getId());
			BienDAO bienDAO = new BienDAO();
			Bien bien = bienDAO.getByIdComptabilite(c.getId());
			data[i][0] = c.getId() + "";
			data[i][1] = c.getDatedue();
			data[i][2] = c.getMontantdu() + "";
			data[i][3] = locataire + "";
			data[i][4] = bien + "";
			i++;
		}
		model = new DefaultTableModel(data, columns);

		JButton btnRetour = new JButton("Retour");
		btnRetour.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnRetour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				new Vue_ComptaList(agent).getFrame().setVisible(true);
			}
		});
		btnRetour.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnRetour.setHorizontalTextPosition(SwingConstants.CENTER);
		btnRetour.setHorizontalAlignment(SwingConstants.CENTER);
		btnRetour.setBorder(new LineBorder(new Color(0, 0, 0)));
		btnRetour.setBackground(Color.LIGHT_GRAY);
		btnRetour.setBounds(10, 11, 121, 84);
		frame.getContentPane().add(btnRetour);
		btnRetour.setOpaque(false);

		JLabel lblNewLabel = new JLabel("Type de facture :");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setBounds(25, 139, 104, 25);
		frame.getContentPane().add(lblNewLabel);

		JLabel lblDateD = new JLabel("Date dû :");
		lblDateD.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDateD.setBounds(66, 289, 64, 25);
		frame.getContentPane().add(lblDateD);

		JLabel lblMontantD = new JLabel("Montant dû :");
		lblMontantD.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMontantD.setBounds(25, 169, 104, 25);
		frame.getContentPane().add(lblMontantD);

		JLabel lblMontantPay = new JLabel("Montant payé :");
		lblMontantPay.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMontantPay.setBounds(25, 199, 104, 25);
		frame.getContentPane().add(lblMontantPay);

		JLabel lblDatePaye = new JLabel("Date payé :");
		lblDatePaye.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDatePaye.setBounds(56, 319, 73, 25);
		frame.getContentPane().add(lblDatePaye);
		lblDatePaye.setVisible(false);

		JButton btnConfirmer = new JButton("Imprimer");
		btnConfirmer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnConfirmer.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnConfirmer.setOpaque(false);
		btnConfirmer.setHorizontalTextPosition(SwingConstants.CENTER);
		btnConfirmer.setHorizontalAlignment(SwingConstants.CENTER);
		btnConfirmer.setBorder(new LineBorder(new Color(0, 0, 0)));
		btnConfirmer.setBackground(Color.LIGHT_GRAY);
		btnConfirmer.setBounds(834, 11, 121, 84);
		frame.getContentPane().add(btnConfirmer);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 118, 965, 2);
		frame.getContentPane().add(separator);
		
		
		JLabel lblBienConcern = new JLabel("Bien concerné :");
		lblBienConcern.setHorizontalAlignment(SwingConstants.RIGHT);
		lblBienConcern.setBounds(25, 229, 104, 25);
		frame.getContentPane().add(lblBienConcern);
		
		JLabel lblLocataireConcern = new JLabel("Locataire concerné :");
		lblLocataireConcern.setHorizontalAlignment(SwingConstants.RIGHT);
		lblLocataireConcern.setBounds(10, 260, 121, 25);
		frame.getContentPane().add(lblLocataireConcern);
		
		JLabel txtType = new JLabel("New label");
		txtType.setBounds(145, 145, 195, 14);
		frame.getContentPane().add(txtType);
		
		JLabel txtMontantDu = new JLabel("New label");
		txtMontantDu.setBounds(145, 175, 195, 14);
		frame.getContentPane().add(txtMontantDu);
		
		JLabel txtMontantPaye = new JLabel("New label");
		txtMontantPaye.setBounds(145, 205, 195, 14);
		frame.getContentPane().add(txtMontantPaye);
		
		JLabel txtBien = new JLabel("New label");
		txtBien.setBounds(145, 235, 195, 14);
		frame.getContentPane().add(txtBien);
		
		JLabel txtLocataire = new JLabel("New label");
		txtLocataire.setBounds(145, 265, 195, 14);
		frame.getContentPane().add(txtLocataire);
		
		JLabel txtDateDu = new JLabel("New label");
		txtDateDu.setBounds(145, 295, 195, 14);
		frame.getContentPane().add(txtDateDu);
		
		JLabel txtDatePaye = new JLabel("New label");
		txtDatePaye.setBounds(145, 325, 195, 14);
		frame.getContentPane().add(txtDatePaye);
		
		LocataireDAO locataireDAO = new LocataireDAO();
		Locataire locataire = locataireDAO.getByIdComptabilite(compta.getId());
		BienDAO bienDAO = new BienDAO();
		Bien bien = bienDAO.getByIdComptabilite(compta.getId()); 
		String[] datedu=compta.getDatedue().split("-");
		if(compta.getDatepaye()!=null) {
			String[] datepaye=compta.getDatepaye().split("-");
			txtDatePaye.setText(datepaye[2]+"/"+datepaye[1]+"/"+datepaye[0]);
			lblDatePaye.setVisible(true);
			txtDatePaye.setVisible(true);
		}
		
		txtType.setText(compta.getCategorie());
		txtMontantDu.setText(compta.getMontantdu()+" €");
		txtMontantPaye.setText(compta.getMontantpaye()+"  €");
		txtBien.setText(bien+"");
		txtLocataire.setText(locataire+"");
		txtDateDu.setText(datedu[2]+"/"+datedu[1]+"/"+datedu[0]);
		txtDatePaye.setVisible(false);
	
		
	}

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}
}
