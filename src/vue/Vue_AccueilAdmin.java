package vue;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Vue_AccueilAdmin {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Vue_AccueilAdmin window = new Vue_AccueilAdmin();
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
	public Vue_AccueilAdmin() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frame = new JFrame();
		frame.setBounds(100, 100, 981, 630);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Agents Immobiliers");
		lblNewLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.dispose();
				
			}
		});
		lblNewLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblNewLabel.setVerticalTextPosition(SwingConstants.BOTTOM);
		lblNewLabel.setIcon(new ImageIcon(Vue_AccueilAdmin.class.getResource("/img/agents.png")));
		lblNewLabel.setBounds(6, 6, 123, 68);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblAjouterUnAgent = new JLabel("Ajouter un agent immobilier");
		lblAjouterUnAgent.setIcon(new ImageIcon(Vue_AccueilAdmin.class.getResource("/img/peopleAdd.png")));
		lblAjouterUnAgent.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		lblAjouterUnAgent.setVerticalTextPosition(SwingConstants.BOTTOM);
		lblAjouterUnAgent.setHorizontalTextPosition(SwingConstants.CENTER);
		lblAjouterUnAgent.setBounds(141, 6, 177, 68);
		frame.getContentPane().add(lblAjouterUnAgent);
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(Vue_AccueilAgent.class.getResource("/img/accueil_bg.jpeg")));
		lblNewLabel_2.setBounds(0, 0, 981, 602);
		frame.getContentPane().add(lblNewLabel_2);
	}

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}
}
