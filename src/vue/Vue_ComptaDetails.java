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
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JPanel;

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
		frame.setBounds(100, 100, 981, 619);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(null);
		JLabel btnRetour = new JLabel("Retour");
		btnRetour.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.dispose();
				new Vue_ComptaList(agent).getFrame().setVisible(true);
			}
		});
		
				JSeparator separator = new JSeparator();
				separator.setBounds(0, 116, 1000, 4);
				frame.getContentPane().add(separator);
		btnRetour.setIcon(new ImageIcon(Vue_ComptaDetails.class.getResource("/img/back.png")));
		btnRetour.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnRetour.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnRetour.setHorizontalTextPosition(SwingConstants.CENTER);
		btnRetour.setHorizontalAlignment(SwingConstants.CENTER);
		btnRetour.setBorder(null);
		btnRetour.setBackground(Color.LIGHT_GRAY);
		btnRetour.setBounds(10, 11, 50, 68);
		frame.getContentPane().add(btnRetour);
		btnRetour.setOpaque(false);
		
		JPanel panel = new JPanel();
		panel.setOpaque(false);
		panel.setBounds(10, 143, 330, 348);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		
		

		JLabel btnConfirmer = new JLabel("Imprimer");
		btnConfirmer.setIcon(new ImageIcon(Vue_ComptaDetails.class.getResource("/img/print.png")));
		btnConfirmer.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnConfirmer.setOpaque(false);
		btnConfirmer.setHorizontalTextPosition(SwingConstants.CENTER);
		btnConfirmer.setHorizontalAlignment(SwingConstants.CENTER);
		btnConfirmer.setBorder(null);
		btnConfirmer.setBackground(Color.LIGHT_GRAY);
		btnConfirmer.setBounds(914, 10, 59, 70);
		frame.getContentPane().add(btnConfirmer);

		LocataireDAO locataireDAO = new LocataireDAO();
		Locataire locataire = locataireDAO.getByIdComptabilite(compta.getId());
		BienDAO bienDAO = new BienDAO();
		Bien bien = bienDAO.getByIdComptabilite(compta.getId());
		String[] datedu = compta.getDatedue().split("-");
		
		JLabel lblNewLabel = new JLabel("Type de facture :");
		lblNewLabel.setBounds(21, 29, 105, 16);
		panel.add(lblNewLabel);
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);

		JLabel lblDateD = new JLabel("Date dû :");
		lblDateD.setBounds(69, 254, 57, 16);
		panel.add(lblDateD);
		lblDateD.setHorizontalAlignment(SwingConstants.RIGHT);

		JLabel lblMontantD = new JLabel("Montant dû :");
		lblMontantD.setBounds(46, 74, 80, 16);
		panel.add(lblMontantD);
		lblMontantD.setHorizontalAlignment(SwingConstants.RIGHT);

		JLabel lblMontantPay = new JLabel("Montant payé :");
		lblMontantPay.setBounds(33, 119, 93, 16);
		panel.add(lblMontantPay);
		lblMontantPay.setHorizontalAlignment(SwingConstants.RIGHT);

		JLabel lblDatePaye = new JLabel("Date payé :");
		lblDatePaye.setBounds(56, 299, 70, 16);
		panel.add(lblDatePaye);
		lblDatePaye.setHorizontalAlignment(SwingConstants.RIGHT);
		
				JLabel lblBienConcern = new JLabel("Bien concerné :");
				lblBienConcern.setBounds(31, 164, 95, 16);
				panel.add(lblBienConcern);
				lblBienConcern.setHorizontalAlignment(SwingConstants.RIGHT);
				
						JLabel lblLocataireConcern = new JLabel("Locataire concerné :");
						lblLocataireConcern.setBounds(0, 209, 126, 16);
						panel.add(lblLocataireConcern);
						lblLocataireConcern.setHorizontalAlignment(SwingConstants.RIGHT);
						
								JLabel txtType = new JLabel("New label");
								txtType.setBounds(135, 31, 195, 14);
								panel.add(txtType);
								
										txtType.setText(compta.getCategorie());
										
												JLabel txtMontantDu = new JLabel("New label");
												txtMontantDu.setBounds(135, 76, 195, 14);
												panel.add(txtMontantDu);
												txtMontantDu.setText(compta.getMontantdu() + " €");
												
														JLabel txtMontantPaye = new JLabel("New label");
														txtMontantPaye.setBounds(135, 121, 195, 14);
														panel.add(txtMontantPaye);
														txtMontantPaye.setText(compta.getMontantpaye() + "  €");
														
																JLabel txtBien = new JLabel("New label");
																txtBien.setBounds(135, 166, 195, 14);
																panel.add(txtBien);
																txtBien.setText(bien + "");
																
																		JLabel txtLocataire = new JLabel("New label");
																		txtLocataire.setBounds(135, 211, 195, 14);
																		panel.add(txtLocataire);
																		txtLocataire.setText(locataire + "");
																		
																				JLabel txtDateDu = new JLabel("New label");
																				txtDateDu.setBounds(135, 256, 195, 14);
																				panel.add(txtDateDu);
																				txtDateDu.setText(datedu[2] + "/" + datedu[1] + "/" + datedu[0]);
																				
																						JLabel txtDatePaye = new JLabel("New label");
																						txtDatePaye.setBounds(135, 301, 195, 14);
																						panel.add(txtDatePaye);
																						lblDatePaye.setVisible(false);
																						txtDatePaye.setVisible(false);
																						if (compta.getDatepaye() != null) {
																							String[] datepaye = compta.getDatepaye().split("-");
																							txtDatePaye.setText(datepaye[2] + "/" + datepaye[1] + "/" + datepaye[0]);
																							lblDatePaye.setVisible(true);
																							txtDatePaye.setVisible(true);
																						}

		JLabel lblBG = new JLabel("");
		lblBG.setHorizontalTextPosition(SwingConstants.CENTER);
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
