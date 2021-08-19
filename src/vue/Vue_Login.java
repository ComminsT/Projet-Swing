package vue;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Vue_Login {

	private JFrame frame;
	private JTextField tmdp;
	private JTextField tidentifiant;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Vue_Login window = new Vue_Login();
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
	public Vue_Login() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 289, 162);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("Identifiant");
		lblNewLabel.setBounds(31, 11, 86, 14);
		frame.getContentPane().add(lblNewLabel);

		JLabel lblMotDePasse = new JLabel("Mot de passe");
		lblMotDePasse.setBounds(31, 46, 86, 14);
		frame.getContentPane().add(lblMotDePasse);

		// bouton valider
		JButton btnsubmit = new JButton("Valider");
		btnsubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String identifiant = tidentifiant.getText();
				String mdp = tmdp.getText();

			}
		});
		btnsubmit.setBounds(31, 90, 89, 23);
		frame.getContentPane().add(btnsubmit);

		// champ mdp
		tmdp = new JTextField();
		tmdp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnsubmit.doClick();
			}
		});
		tmdp.setBounds(31, 59, 167, 20);
		frame.getContentPane().add(tmdp);
		tmdp.setColumns(10);

		// champ identifiant
		tidentifiant = new JTextField();
		tidentifiant.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnsubmit.doClick();
			}
		});
		tidentifiant.setBounds(31, 23, 167, 20);
		frame.getContentPane().add(tidentifiant);
		tidentifiant.setColumns(10);

	}

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}
}
