package vue;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import dao.BienDAO;
import dao.LocataireDAO;
import entite.Agent;
import entite.Bien;
import entite.Comptabilite;
import entite.Locataire;

public class Vue_ComptaDetails {

	private JFrame frmDtailDeLa;
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
					window.frmDtailDeLa.setVisible(true);
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
		frmDtailDeLa = new JFrame();
		frmDtailDeLa.setTitle("Détail de la facture");
		frmDtailDeLa.setBounds(100, 100, 981, 619);
		frmDtailDeLa.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmDtailDeLa.setLocationRelativeTo(null);
		frmDtailDeLa.getContentPane().setLayout(null);
		JLabel btnRetour = new JLabel("Retour");
		btnRetour.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frmDtailDeLa.dispose();
				new Vue_ComptaList(agent).getFrame().setVisible(true);
			}
		});
				
				JLabel lblNewLabel_1 = new JLabel("Détail de la facture");
				lblNewLabel_1.setBounds(429, 30, 106, 16);
				frmDtailDeLa.getContentPane().add(lblNewLabel_1);
		
				JSeparator separator = new JSeparator();
				separator.setBounds(0, 116, 1000, 4);
				frmDtailDeLa.getContentPane().add(separator);
		btnRetour.setIcon(new ImageIcon(Vue_ComptaDetails.class.getResource("/img/back.png")));
		btnRetour.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnRetour.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnRetour.setHorizontalTextPosition(SwingConstants.CENTER);
		btnRetour.setHorizontalAlignment(SwingConstants.CENTER);
		btnRetour.setBorder(null);
		btnRetour.setBackground(Color.LIGHT_GRAY);
		btnRetour.setBounds(10, 11, 50, 68);
		frmDtailDeLa.getContentPane().add(btnRetour);
		btnRetour.setOpaque(false);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 143, 330, 348);
		frmDtailDeLa.getContentPane().add(panel);
		panel.setLayout(null);
		panel.setBackground(new Color(255,255,255,100));

		
		

		JLabel btnConfirmer = new JLabel("Imprimer");
		btnConfirmer.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				printRecord(frmDtailDeLa);
			}
		});
		btnConfirmer.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnConfirmer.setIcon(new ImageIcon(Vue_ComptaDetails.class.getResource("/img/print.png")));
		btnConfirmer.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnConfirmer.setOpaque(false);
		btnConfirmer.setHorizontalTextPosition(SwingConstants.CENTER);
		btnConfirmer.setHorizontalAlignment(SwingConstants.CENTER);
		btnConfirmer.setBorder(null);
		btnConfirmer.setBackground(Color.LIGHT_GRAY);
		btnConfirmer.setBounds(895, 11, 59, 70);
		frmDtailDeLa.getContentPane().add(btnConfirmer);

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

		JLabel lblDatePaye = new JLabel("Date payée :");
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
		frmDtailDeLa.getContentPane().add(lblBG);
	}

	public JFrame getFrame() {
		return frmDtailDeLa;
	}

	public void setFrame(JFrame frame) {
		this.frmDtailDeLa = frame;
	}
	private void printRecord(JFrame frametoprint) {
		PrinterJob printerJob = PrinterJob.getPrinterJob();
		printerJob.setJobName("Print Record");
		printerJob.setPrintable(new Printable() {
            @Override
            public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
                // Check If No Printable Content
                if(pageIndex > 0){
                    return Printable.NO_SUCH_PAGE;
                }
                
                // Make 2D Graphics to map content
                Graphics2D graphics2D = (Graphics2D)graphics;
                // Set Graphics Translations
                // A Little Correction here Multiplication was not working so I replaced with addition
                graphics2D.translate(pageFormat.getImageableX()+10, pageFormat.getImageableY()+10);
                // This is a page scale. Default should be 0.3 I am using 0.5
                graphics2D.scale(0.5, 0.5);
                
                // Now paint panel as graphics2D
                frametoprint.paint(graphics2D);
                
                // return if page exists
                return Printable.PAGE_EXISTS;
            }
        });
		 boolean returningResult = printerJob.printDialog();
	        // check if dialog is showing
	        if(returningResult){
	            // Use try catch exeption for failure
	            try{
	                // Now call print method inside printerJob to print
	                printerJob.print();
	            }catch (PrinterException printerException){
	                JOptionPane.showMessageDialog(frametoprint, "Print Error: " + printerException.getMessage());
	            }
	        }
		
	}
}
