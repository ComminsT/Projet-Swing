package vue;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JCalendar;

import dao.BienDAO;
import dao.ComptabiliteDAO;
import dao.HistoriqueDAO;
import dao.LocataireDAO;
import entite.Agent;
import entite.Bien;
import entite.Checker;
import entite.Comptabilite;
import entite.Database;
import entite.Historique;
import entite.Locataire;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Vue_ComptaModif {

	private JFrame frmModificationDeLa;
	private Agent agent;
	private DefaultTableModel model;
	private Comptabilite compta;
	private JTextField txtType;
	private JTextField txtEuros;
	private JTextField txtEurop;
	private JTextField txtCents;
	private JTextField txtCentsp;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Vue_ComptaModif window = new Vue_ComptaModif();
					window.frmModificationDeLa.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Vue_ComptaModif() {
		initialize();
	}

	public Vue_ComptaModif(Comptabilite compta, Agent agent) {
		this.compta = compta;
		this.agent = agent;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		frmModificationDeLa = new JFrame();
		frmModificationDeLa.setTitle("Modification de la facture");
		frmModificationDeLa.setBounds(100, 100, 981, 630);
		frmModificationDeLa.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmModificationDeLa.setLocationRelativeTo(null);
		frmModificationDeLa.getContentPane().setLayout(null);

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

		JLabel btnRetour = new JLabel("Retour");
		btnRetour.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frmModificationDeLa.dispose();
				new Vue_ComptaList(agent).getFrame().setVisible(true);
			}
		});
		
		JLabel lblNewLabel_3 = new JLabel("Modification de la facture");
		lblNewLabel_3.setBounds(411, 30, 143, 16);
		frmModificationDeLa.getContentPane().add(lblNewLabel_3);
		btnRetour.setIcon(new ImageIcon(Vue_ComptaModif.class.getResource("/img/back.png")));
		btnRetour.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnRetour.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnRetour.setHorizontalTextPosition(SwingConstants.CENTER);
		btnRetour.setHorizontalAlignment(SwingConstants.CENTER);
		btnRetour.setBorder(new LineBorder(new Color(0, 0, 0)));
		btnRetour.setBackground(Color.LIGHT_GRAY);
		btnRetour.setBounds(10, 11, 50, 69);
		frmModificationDeLa.getContentPane().add(btnRetour);
		btnRetour.setOpaque(false);

		JLabel lblNewLabel = new JLabel("Type de facture :");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setBounds(26, 132, 121, 25);
		frmModificationDeLa.getContentPane().add(lblNewLabel);

		JLabel lblDateD = new JLabel("Date due :");
		lblDateD.setHorizontalAlignment(SwingConstants.CENTER);
		lblDateD.setBounds(92, 277, 104, 25);
		frmModificationDeLa.getContentPane().add(lblDateD);

		JLabel lblMontantD = new JLabel("Montant dû :");
		lblMontantD.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMontantD.setBounds(0, 154, 104, 25);
		frmModificationDeLa.getContentPane().add(lblMontantD);

		JLabel lblMontantPay = new JLabel("Montant payé :");
		lblMontantPay.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMontantPay.setBounds(12, 177, 104, 25);
		frmModificationDeLa.getContentPane().add(lblMontantPay);

		JLabel lblDatePay = new JLabel("Date payée :");
		lblDatePay.setHorizontalAlignment(SwingConstants.CENTER);
		lblDatePay.setBounds(490, 277, 73, 25);
		frmModificationDeLa.getContentPane().add(lblDatePay);

		txtType = new JTextField();
		txtType.setBounds(174, 134, 157, 20);
		frmModificationDeLa.getContentPane().add(txtType);
		txtType.setColumns(10);

		txtEuros = new JTextField();
		txtEuros.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE)) {
					e.consume();
				}
			}
		});
		txtEuros.setColumns(10);
		txtEuros.setBounds(121, 156, 157, 20);
		frmModificationDeLa.getContentPane().add(txtEuros);

		txtEurop = new JTextField();
		txtEurop.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE)) {
					e.consume();
				}
			}

		});
		txtEurop.setColumns(10);
		txtEurop.setBounds(121, 179, 157, 20);
		frmModificationDeLa.getContentPane().add(txtEurop);
		JCalendar calendar = new JCalendar();
		try {
			Date date1=new SimpleDateFormat("yyyy-MM-dd").parse(compta.getDatedue());
			calendar.setDate(date1);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}  
		
		
		

		calendar.setBounds(10, 313, 302, 198);
		frmModificationDeLa.getContentPane().add(calendar);

		JCalendar calendar_1 = new JCalendar();
		calendar_1.setBounds(368, 313, 302, 198);
		frmModificationDeLa.getContentPane().add(calendar_1);

		JLabel btnConfirmer = new JLabel("Confirmer");
		btnConfirmer.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				if (txtType.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Veuillez remplir le champ catégorie");
				} else if (txtEuros.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Veuillez remplir le champ montant dû");
				} else {
					Database.Connect();
					compta.setCategorie(txtType.getText());
					if (txtEurop.getText() != null) {
						String mois = String.valueOf(calendar_1.getMonthChooser().getMonth() + 1);
						String jour = String.valueOf(calendar_1.getDayChooser().getDay());
						String annee = String.valueOf(calendar_1.getYearChooser().getYear());
						String date = annee + "-" + mois + "-" + jour;
						compta.setDatepaye(date);
						String montantpaye2 = txtEurop.getText();
						if (txtCentsp.getText() != null) {
							montantpaye2 += "." + txtCentsp.getText();
						}
						Double montantpaye = Double.parseDouble(montantpaye2);
						compta.setMontantpaye(montantpaye);
					}
					String mois = String.valueOf(calendar.getMonthChooser().getMonth() + 1);
					String jour = String.valueOf(calendar.getDayChooser().getDay());
					String annee = String.valueOf(calendar.getYearChooser().getYear());
					String date = annee + "-" + mois + "-" + jour;
					compta.setDatedue(date);
					String montantdue = txtEuros.getText();
					if (txtCents.getText() != null) {
						montantdue += "." + txtCents.getText();
					}
					Double dbmontantdue = Double.parseDouble(montantdue);
					compta.setMontantdu(dbmontantdue);
					ComptabiliteDAO comptaDAO = new ComptabiliteDAO();
					comptaDAO.save(compta);
					HistoriqueDAO historiqueDAO = new HistoriqueDAO();
					Historique historique = new Historique();
					historique.setDate(Checker.getDateTime());
					historique.setId_agent(agent.getId());
					historique.setAction("Modification de la facture : "+compta);
					historiqueDAO.save(historique);	
					frmModificationDeLa.dispose();
					new Vue_ComptaList(agent).getFrame().setVisible(true);
				}

			
			}
		});
		btnConfirmer.setIcon(new ImageIcon(Vue_ComptaModif.class.getResource("/img/valider.png")));

		btnConfirmer.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnConfirmer.setOpaque(false);
		btnConfirmer.setHorizontalTextPosition(SwingConstants.CENTER);
		btnConfirmer.setHorizontalAlignment(SwingConstants.CENTER);
		btnConfirmer.setBorder(new LineBorder(new Color(0, 0, 0)));
		btnConfirmer.setBackground(Color.LIGHT_GRAY);
		btnConfirmer.setBounds(895, 11, 65, 69);
		frmModificationDeLa.getContentPane().add(btnConfirmer);

		txtCents = new JTextField();
		txtCents.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE)) {
					e.consume();
				}
			}

		});
		txtCents.setColumns(10);
		txtCents.setBounds(289, 156, 157, 20);
		frmModificationDeLa.getContentPane().add(txtCents);

		txtCentsp = new JTextField();
		txtCentsp.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE)) {
					e.consume();
				}
			}
		});
		txtCentsp.setColumns(10);
		txtCentsp.setBounds(290, 179, 157, 20);
		frmModificationDeLa.getContentPane().add(txtCentsp);

		JLabel lblNewLabel_1 = new JLabel(".");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(281, 157, 3, 16);
		frmModificationDeLa.getContentPane().add(lblNewLabel_1);

		JLabel lblNewLabel_1_1 = new JLabel(".");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1.setBounds(282, 179, 3, 16);
		frmModificationDeLa.getContentPane().add(lblNewLabel_1_1);

		JLabel lblNewLabel_2 = new JLabel("€");
		lblNewLabel_2.setBounds(452, 158, 38, 16);
		frmModificationDeLa.getContentPane().add(lblNewLabel_2);

		JLabel lblNewLabel_2_1 = new JLabel("€");
		lblNewLabel_2_1.setBounds(453, 181, 32, 16);
		frmModificationDeLa.getContentPane().add(lblNewLabel_2_1);

		JSeparator separator = new JSeparator();
		separator.setBounds(0, 118, 965, 2);
		frmModificationDeLa.getContentPane().add(separator);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(0, 280, 965, 2);
		frmModificationDeLa.getContentPane().add(separator_1);

		txtType.setText(compta.getCategorie());

		Double montantdu = compta.getMontantdu();
		String strmontantdu = String.valueOf(montantdu);
		String[] strmontantdusorted = strmontantdu.split("\\.");
		txtEuros.setText(strmontantdusorted[0]);
		txtCents.setText(strmontantdusorted[1]);
		JLabel lblBG = new JLabel("");
		lblBG.setOpaque(true);
		lblBG.setIcon(new ImageIcon(Vue_AccueilAgent.class.getResource("/img/accueil_bg.jpeg")));
		lblBG.setBounds(-16, 0, 1000, 591);
		frmModificationDeLa.getContentPane().add(lblBG);

		if (compta.getMontantpaye() != null) {
			Double montantpaye = compta.getMontantpaye();
			String strmontantpaye = String.valueOf(montantpaye);
			String[] strmontapayesorted = strmontantpaye.split("\\.");
			txtEurop.setText(strmontapayesorted[0]);
			txtCentsp.setText(strmontapayesorted[1]);
		}
	}

	public JFrame getFrame() {
		return frmModificationDeLa;
	}

	public void setFrame(JFrame frame) {
		this.frmModificationDeLa = frame;
	}
}
