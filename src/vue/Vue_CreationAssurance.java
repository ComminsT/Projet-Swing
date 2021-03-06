package vue;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;

import dao.AssuranceDAO;
import entite.Assurance;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class Vue_CreationAssurance {

	private JFrame frmAjouterUneAssurance;
	private JTextField txtType;
	private JTextField txtContrat;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Vue_CreationAssurance window = new Vue_CreationAssurance();
					window.frmAjouterUneAssurance.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Vue_CreationAssurance() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmAjouterUneAssurance = new JFrame();
		frmAjouterUneAssurance.setTitle("Ajout d'une assurance");
		frmAjouterUneAssurance.setBounds(100, 100, 534, 294);
		frmAjouterUneAssurance.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmAjouterUneAssurance.getContentPane().setLayout(null);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(Color.LIGHT_GRAY);
		separator.setBounds(0, 88, 518, 2);
		frmAjouterUneAssurance.getContentPane().add(separator);
		
		JLabel lblNewLabel = new JLabel("Ajout d'une assurance");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(195, 30, 127, 16);
		frmAjouterUneAssurance.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Type d'assurance :");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setBounds(28, 116, 126, 14);
		frmAjouterUneAssurance.getContentPane().add(lblNewLabel_1);
		
		txtType = new JTextField();
		txtType.setText("Assurance Habitation\r\n");
		txtType.setBounds(179, 113, 144, 20);
		frmAjouterUneAssurance.getContentPane().add(txtType);
		txtType.setColumns(10);
		
		JLabel lblNewLabel_1_1 = new JLabel("Num??ro de contrat :");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_1.setBounds(10, 141, 144, 14);
		frmAjouterUneAssurance.getContentPane().add(lblNewLabel_1_1);
		
		txtContrat = new JTextField();
		txtContrat.setColumns(10);
		txtContrat.setBounds(178, 138, 144, 20);
		frmAjouterUneAssurance.getContentPane().add(txtContrat);
		
		JLabel btnNewButton = new JLabel("Confirmer");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(txtType.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Veuillez remplir tous les champs");
				}else if (txtContrat.getText().equals("")){
					JOptionPane.showMessageDialog(null, "Veuillez remplir tous les champs");
				}else {
					Assurance assurance = new Assurance();
					assurance.setNumero(txtContrat.getText());
					assurance.setType(txtType.getText());
//					assurance.setId_contratl(null);
//					AssuranceDAO assuranceDAO = new AssuranceDAO();
//					assuranceDAO.save(assurance);
//					ArrayList<Assurance> assurances = assuranceDAO.getAll();
//					assurance = assurances.get(assurances.size() - 1);
					JOptionPane.showMessageDialog(null, "L'assurance a bien ??t?? cr????e");
					frmAjouterUneAssurance.dispose();
					Vue_ContratConfirmation.addToAssurance(assurance.getType(),assurance);
					}
				
			}
		});
		btnNewButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton.setHorizontalTextPosition(SwingConstants.CENTER);
		btnNewButton.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnNewButton.setIcon(new ImageIcon(Vue_CreationAssurance.class.getResource("/img/valider.png")));
		btnNewButton.setBounds(450, 11, 57, 68);
		frmAjouterUneAssurance.getContentPane().add(btnNewButton);
		JLabel lblBG = new JLabel("");
		lblBG.setOpaque(true);
		lblBG.setIcon(new ImageIcon(Vue_AccueilAgent.class.getResource("/img/accueil_bg.jpeg")));
		lblBG.setBounds(-300, -20, 1000, 591);
		frmAjouterUneAssurance.getContentPane().add(lblBG);
	}

	public JFrame getFrame() {
		return frmAjouterUneAssurance;
	}

	public void setFrame(JFrame frame) {
		this.frmAjouterUneAssurance = frame;
	}
}
