package vue;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

import entite.Agent;
import entite.Proprietaire;

public class Vue_ProprietaireDetails {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					Vue_ProprietaireDetails window = new Vue_ProprietaireDetails();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private JFrame frame;
	private JSeparator separator;
	private JLabel txtAdresseMail;
	private JLabel txtNom;
	private JLayeredPane layeredPane;

	private JLabel txtPrenom;
	private JLabel lblPays;
	private JLabel txtAdresse;
	private JLabel txtVille;
	private JLabel txtCodePostale;
	private JLabel lblVille_1;
	private Agent agent;
	private Proprietaire proprietaire;

	/**
	 * Create the application.
	 */
	public Vue_ProprietaireDetails() {
		initialize();

	}

	public Vue_ProprietaireDetails(Proprietaire proprietaire, Agent agent) {
		this.proprietaire = proprietaire;
		this.agent = agent;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		frame = new JFrame();
		frame.setBounds(100, 100, 981, 630);
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);

		JSeparator separator_1 = new JSeparator();
		separator_1.setOrientation(SwingConstants.VERTICAL);
		separator_1.setBounds(502, 82, 2, 519);
		frame.getContentPane().add(separator_1);

		JButton btnNewButton = new JButton("Imprimer");

		btnNewButton.setBounds(852, 7, 113, 53);
		frame.getContentPane().add(btnNewButton);

		separator = new JSeparator();
		separator.setForeground(Color.LIGHT_GRAY);
		separator.setBounds(0, 82, frame.getWidth(), 2);
		frame.getContentPane().add(separator);

		JLabel lblNewLabel_2 = new JLabel("Détails propriétaire");
		lblNewLabel_2.setBounds(277, 26, 293, 14);
		frame.getContentPane().add(lblNewLabel_2);

		JButton btnAnnuler = new JButton("Retour");
		btnAnnuler.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				new Vue_ProprietairesList(agent).getFrame().setVisible(true);
			}
		});
		btnAnnuler.setBounds(24, 7, 113, 53);
		frame.getContentPane().add(btnAnnuler);
		String[] birth = proprietaire.getNaissance().split("-");

		JButton btnInformations = new JButton("Informations personnel");

		btnInformations.setBounds(0, 82, 492, 86);
		frame.getContentPane().add(btnInformations);

		JButton btnContrats = new JButton("Contrats");
		btnContrats.setBounds(0, 179, 492, 86);
		frame.getContentPane().add(btnContrats);

		layeredPane = new JLayeredPane();
		layeredPane.setBounds(502, 82, 479, 519);
		frame.getContentPane().add(layeredPane);
		layeredPane.setLayout(new CardLayout(0, 0));

		JPanel panelInfos = new JPanel();
		layeredPane.add(panelInfos, "name_429148076291500");
		panelInfos.setLayout(null);

		JLabel lblNewLabel = new JLabel("Nom :");
		lblNewLabel.setBounds(10, 11, 103, 14);
		panelInfos.add(lblNewLabel);
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));

		txtPrenom = new JLabel();
		txtPrenom.setBounds(123, 42, 134, 20);
		panelInfos.add(txtPrenom);
		txtPrenom.setForeground(Color.BLACK);
		txtPrenom.setText(proprietaire.getPrenom());

		txtNom = new JLabel();
		txtNom.setBounds(123, 11, 134, 20);
		panelInfos.add(txtNom);
		txtNom.setForeground(Color.BLACK);
		txtNom.setText(proprietaire.getNom());

		JLabel lblMail = new JLabel("Mail :");
		lblMail.setBounds(24, 67, 89, 14);
		panelInfos.add(lblMail);
		lblMail.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMail.setFont(new Font("Tahoma", Font.BOLD, 14));

		txtAdresseMail = new JLabel();
		txtAdresseMail.setBounds(123, 67, 134, 20);
		panelInfos.add(txtAdresseMail);

		txtAdresseMail.setText(proprietaire.getMail());
		txtAdresseMail.setForeground(Color.BLACK);

		lblPays = new JLabel("Pays :");
		lblPays.setBounds(24, 92, 89, 17);
		panelInfos.add(lblPays);
		lblPays.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPays.setFont(new Font("Tahoma", Font.BOLD, 14));

		txtAdresse = new JLabel();
		txtAdresse.setBounds(123, 121, 226, 20);
		panelInfos.add(txtAdresse);
		txtAdresse.setText(proprietaire.getAdresse());
		txtAdresse.setForeground(Color.BLACK);

		JLabel lblVille = new JLabel("Adresse :");
		lblVille.setBounds(24, 120, 89, 17);
		panelInfos.add(lblVille);
		lblVille.setHorizontalAlignment(SwingConstants.RIGHT);
		lblVille.setFont(new Font("Tahoma", Font.BOLD, 14));

		JLabel lblVille_2 = new JLabel("Ville :");
		lblVille_2.setBounds(24, 148, 89, 17);
		panelInfos.add(lblVille_2);
		lblVille_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblVille_2.setFont(new Font("Tahoma", Font.BOLD, 14));

		txtVille = new JLabel();
		txtVille.setBounds(123, 149, 181, 20);
		panelInfos.add(txtVille);
		txtVille.setText(proprietaire.getVille());
		txtVille.setForeground(Color.BLACK);

		txtCodePostale = new JLabel();
		txtCodePostale.setBounds(123, 176, 105, 20);
		panelInfos.add(txtCodePostale);
		txtCodePostale.setText(proprietaire.getCp());
		txtCodePostale.setForeground(Color.BLACK);

		lblVille_1 = new JLabel("Date de naissance :");
		lblVille_1.setBounds(10, 226, 141, 17);
		panelInfos.add(lblVille_1);
		lblVille_1.setFont(new Font("Tahoma", Font.BOLD, 14));

		JLabel lblNewLabel_4 = new JLabel("Telephone :");
		lblNewLabel_4.setBounds(10, 201, 103, 14);
		panelInfos.add(lblNewLabel_4);
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 14));

		JLabel lblPrnom = new JLabel("Prénom :");
		lblPrnom.setBounds(10, 42, 103, 14);
		panelInfos.add(lblPrnom);
		lblPrnom.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPrnom.setFont(new Font("Tahoma", Font.BOLD, 14));

		JLabel txtPays = new JLabel("New label");
		txtPays.setBounds(123, 95, 181, 14);
		panelInfos.add(txtPays);
		txtPays.setText(proprietaire.getPays());


		JLabel txtTelephone = new JLabel("New label");
		txtTelephone.setBounds(123, 203, 89, 14);
		panelInfos.add(txtTelephone);
		txtTelephone.setText(proprietaire.getTel());

		JLabel txtDateOfBirth = new JLabel("New label");
		txtDateOfBirth.setBounds(150, 229, 181, 14);
		panelInfos.add(txtDateOfBirth);
		txtDateOfBirth.setText(birth[2] + "/" + birth[1] + "/" + birth[0]);

		JLabel lblVille_2_1 = new JLabel("Code postal :");
		lblVille_2_1.setBounds(0, 176, 113, 17);
		panelInfos.add(lblVille_2_1);
		lblVille_2_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblVille_2_1.setFont(new Font("Tahoma", Font.BOLD, 14));

		JPanel panelHistorique = new JPanel();
		layeredPane.add(panelHistorique, "name_429172689935300");
		panelHistorique.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("panelHistorique");
		lblNewLabel_1.setBounds(20, 21, 121, 14);
		panelHistorique.add(lblNewLabel_1);

		JPanel panelContrats = new JPanel();
		layeredPane.add(panelContrats, "name_429176002358400");
		panelContrats.setLayout(null);

		JLabel lblNewLabel_3 = new JLabel("panelContrats");
		lblNewLabel_3.setBounds(10, 11, 104, 14);
		panelContrats.add(lblNewLabel_3);

		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				printRecord(frame);
								
				
			}

		});

		btnInformations.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchPanels(panelInfos);
			}

		});
		btnContrats.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchPanels(panelContrats);
			}
		});
	}

	public void switchPanels(JPanel panel) {
		layeredPane.removeAll();
		layeredPane.add(panel);
		layeredPane.repaint();
		layeredPane.revalidate();
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

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}
}