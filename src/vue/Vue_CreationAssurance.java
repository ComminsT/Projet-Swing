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

public class Vue_CreationAssurance {

	private JFrame frame;
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
	public Vue_CreationAssurance() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 534, 310);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(Color.LIGHT_GRAY);
		separator.setBounds(0, 88, 518, 2);
		frame.getContentPane().add(separator);
		
		JLabel lblNewLabel = new JLabel("Ajout Assurance");
		lblNewLabel.setBounds(185, 26, 137, 51);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Type d'assurance :");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setBounds(28, 116, 126, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		txtType = new JTextField();
		txtType.setText("Assurance Habitation\r\n");
		txtType.setBounds(179, 113, 144, 20);
		frame.getContentPane().add(txtType);
		txtType.setColumns(10);
		
		JLabel lblNewLabel_1_1 = new JLabel("Numéro de contrat :");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_1.setBounds(10, 141, 144, 14);
		frame.getContentPane().add(lblNewLabel_1_1);
		
		txtContrat = new JTextField();
		txtContrat.setColumns(10);
		txtContrat.setBounds(178, 138, 144, 20);
		frame.getContentPane().add(txtContrat);
		
		JButton btnNewButton = new JButton("Confirmer");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(txtType.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Veuillez remplir tous les champs");
				}else if (txtContrat.getText().equals("")){
					JOptionPane.showMessageDialog(null, "Veuillez remplir tous les champs");
				}else {
					Assurance assurance = new Assurance();
					assurance.setNumero(txtContrat.getText());
					assurance.setType(txtType.getText());
					JOptionPane.showMessageDialog(null, "L'assurance a bien été créée");
					frame.dispose();
					Vue_ContratConfirmation.addToAssurance(assurance.getType(),assurance);
					}
				}
		});
		btnNewButton.setBounds(357, 201, 151, 59);
		frame.getContentPane().add(btnNewButton);
	}

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}
}