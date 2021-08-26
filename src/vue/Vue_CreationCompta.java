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
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private JFrame frame;
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
		return frame;
	}
	public void setFrame(JFrame frame) {
		this.frame = frame;
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
Database.Connect();
		frame = new JFrame();
		frame.setBounds(100, 100, 534, 503);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);

		JButton btnSuivant = new JButton("Suivant");

		btnSuivant.setBounds(403, 11, 113, 53);
		frame.getContentPane().add(btnSuivant);

		separator = new JSeparator();
		separator.setForeground(Color.LIGHT_GRAY);
		separator.setBounds(0, 82, frame.getWidth(), 2);
		frame.getContentPane().add(separator);
		
		JLabel lblNewLabel = new JLabel("Nouvelle facture");
		lblNewLabel.setBounds(197, 11, 113, 53);
		frame.getContentPane().add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBounds(35, 96, 376, 306);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Sélectionnez la date ou la facture doit être payée :");
		lblNewLabel_1.setBounds(0, 47, 376, 14);
		panel.add(lblNewLabel_1);
		
		JCalendar calendar = new JCalendar();
		calendar.setBounds(0, 72, 302, 177);
		panel.add(calendar);
		Date date=new Date();
		calendar.setMinSelectableDate(date);
		
		JLabel lblNewLabel_1_1 = new JLabel("Entrez le montant dû :");
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
		
		JLabel lblNewLabel_2 = new JLabel("€");
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
		
		JLabel lblNewLabel_1_2 = new JLabel("Entrez la catégorie de la facture :");
		lblNewLabel_1_2.setBounds(0, 0, 376, 14);
		panel.add(lblNewLabel_1_2);
		
		txtCategorie = new JTextField();
		txtCategorie.setBounds(2, 15, 187, 20);
		panel.add(txtCategorie);
		txtCategorie.setText("Loyer");
		txtCategorie.setColumns(10);
		
		JButton btnRetour = new JButton("Retour");
		btnRetour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				new Vue_ComptaList(agent).getFrame().setVisible(true);
			}
		});
		btnRetour.setBounds(11, 11, 113, 53);
		frame.getContentPane().add(btnRetour);
		
		
		

		btnSuivant.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(txtCategorie.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Veuillez remplir le champ catégorie");
				}else if(txtEuros.getText().equals("")){
					JOptionPane.showMessageDialog(null, "Veuillez remplir le champ montant dû");
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
					frame.dispose();
					new Vue_CreationCompta2(compta,agent).getFrame().setVisible(true);
					
				}
				
				
				
				}
			
		});

	}
}
