package vue;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

import com.toedter.calendar.JCalendar;

import entite.Agent;
import entite.Comptabilite;
import entite.Database;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Vue_CreationCompta {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					Vue_CreationCompta window = new Vue_CreationCompta();
					window.frmAjoutDuneNouvelle.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private JFrame frmAjoutDuneNouvelle;
	private JSeparator separator;
	private Agent agent;
	private JTextField txtEuros;
	private JTextField txtCents;
	private JTextField txtCategorie;

	/**
	 * Create the application.
	 */
	public Vue_CreationCompta() {
		initialize();

	}
	public Vue_CreationCompta(Agent agent) {
		this.agent=agent;
		initialize();
	}

	public JFrame getFrame() {
		return frmAjoutDuneNouvelle;
	}
	public void setFrame(JFrame frame) {
		this.frmAjoutDuneNouvelle = frame;
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
Database.Connect();
		frmAjoutDuneNouvelle = new JFrame();
		frmAjoutDuneNouvelle.setTitle("Ajout d'une nouvelle facture");
		frmAjoutDuneNouvelle.setBounds(100, 100, 534, 503);
		frmAjoutDuneNouvelle.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frmAjoutDuneNouvelle.getContentPane().setLayout(null);
		frmAjoutDuneNouvelle.setResizable(false);
		frmAjoutDuneNouvelle.setLocationRelativeTo(null);

		JLabel btnSuivant = new JLabel("Suivant");
		
		btnSuivant.setHorizontalTextPosition(SwingConstants.CENTER);
		btnSuivant.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnSuivant.setIcon(new ImageIcon(Vue_CreationCompta.class.getResource("/img/next.png")));
		btnSuivant.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		btnSuivant.setBounds(469, 11, 48, 68);
		frmAjoutDuneNouvelle.getContentPane().add(btnSuivant);

		separator = new JSeparator();
		separator.setForeground(Color.LIGHT_GRAY);
		separator.setBounds(0, 82, frmAjoutDuneNouvelle.getWidth(), 2);
		frmAjoutDuneNouvelle.getContentPane().add(separator);
		
		JLabel lblNewLabel = new JLabel("Ajout d'une nouvelle facture");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(185, 30, 157, 16);
		frmAjoutDuneNouvelle.getContentPane().add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setOpaque(false);
		panel.setBounds(35, 96, 376, 306);
		frmAjoutDuneNouvelle.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("S??lectionnez la date o?? la facture doit ??tre pay??e :");
		lblNewLabel_1.setBounds(0, 47, 376, 14);
		panel.add(lblNewLabel_1);
		
		JCalendar calendar = new JCalendar();
		calendar.setBounds(0, 72, 302, 177);
		panel.add(calendar);
		Date date=new Date();
		calendar.setMinSelectableDate(date);
		
		JLabel lblNewLabel_1_1 = new JLabel("Entrez le montant d?? :");
		lblNewLabel_1_1.setBounds(0, 261, 302, 14);
		panel.add(lblNewLabel_1_1);
		
		txtEuros = new JTextField();
		txtEuros.setBounds(0, 286, 114, 20);
		panel.add(txtEuros);
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
		
		JLabel lblNewLabel_2 = new JLabel("???");
		lblNewLabel_2.setBounds(244, 288, 23, 16);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_2_1 = new JLabel(".");
		lblNewLabel_2_1.setBounds(112, 288, 13, 16);
		panel.add(lblNewLabel_2_1);
		lblNewLabel_2_1.setHorizontalAlignment(SwingConstants.CENTER);
		
		txtCents = new JTextField();
		txtCents.setBounds(124, 286, 114, 20);
		panel.add(txtCents);
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
		
		JLabel lblNewLabel_1_2 = new JLabel("Entrez la cat??gorie de la facture :");
		lblNewLabel_1_2.setBounds(0, 0, 376, 14);
		panel.add(lblNewLabel_1_2);
		
		txtCategorie = new JTextField();
		txtCategorie.setBounds(2, 15, 187, 20);
		panel.add(txtCategorie);
		txtCategorie.setText("Loyer");
		txtCategorie.setColumns(10);
		
		JLabel btnRetour = new JLabel("Retour");
		btnRetour.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frmAjoutDuneNouvelle.dispose();
				new Vue_ComptaList(agent).getFrame().setVisible(true);
			
			}
		});
		btnRetour.setHorizontalTextPosition(SwingConstants.CENTER);
		btnRetour.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnRetour.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnRetour.setIcon(new ImageIcon(Vue_CreationCompta.class.getResource("/img/back.png")));
		btnRetour.setBounds(11, 11, 48, 68);
		frmAjoutDuneNouvelle.getContentPane().add(btnRetour);
		btnSuivant.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				if(txtCategorie.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Veuillez remplir le champ cat??gorie");
				}else if(txtEuros.getText().equals("")){
					JOptionPane.showMessageDialog(null, "Veuillez remplir le champ montant d??");
				}else {
					Comptabilite compta = new Comptabilite();
					compta.setCategorie(txtCategorie.getText());
					String mois = String.valueOf(calendar.getMonthChooser().getMonth() + 1);
					String jour = String.valueOf(calendar.getDayChooser().getDay());
					String annee = String.valueOf(calendar.getYearChooser().getYear());
					String date = annee + "-" + mois + "-" + jour;
					compta.setDatedue(date);
					String montant=txtEuros.getText()+"."+txtCents.getText();
					Double montantdu=Double.parseDouble(montant);
					compta.setMontantdu(montantdu);
					frmAjoutDuneNouvelle.dispose();
					new Vue_CreationCompta2(compta,agent).getFrame().setVisible(true);
					
				}
				
				
				
				
			}
		});
		JLabel lblBG = new JLabel("");
		lblBG.setOpaque(true);
		lblBG.setIcon(new ImageIcon(Vue_AccueilAgent.class.getResource("/img/accueil_bg.jpeg")));
		lblBG.setBounds(-16, 0, 1000, 591);
		frmAjoutDuneNouvelle.getContentPane().add(lblBG);

	}
}
