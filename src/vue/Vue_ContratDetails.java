package vue;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import dao.AssuranceDAO;
import dao.BienDAO;
import dao.GarantDAO;
import dao.LocataireDAO;
import entite.Agent;
import entite.Assurance;
import entite.Bien;
import entite.Contratl;
import entite.Garant;
import entite.Locataire;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Vue_ContratDetails {

	private JFrame frame;
	private Contratl contrat;
	private Agent agent;
	private JTable table;
	private JTable table_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Vue_ContratDetails window = new Vue_ContratDetails();
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
	public Vue_ContratDetails() {
		initialize();
	}
	public Vue_ContratDetails(Contratl contrat, Agent agent) {
		this.contrat=contrat;
		this.agent=agent;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 524, 475);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(Color.LIGHT_GRAY);
		separator.setBounds(0, 88, 508, 2);
		frame.getContentPane().add(separator);
		
		JLabel lblNewLabel = new JLabel("Détails");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(234, 30, 39, 16);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel btnNewButton = new JLabel("Imprimer");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				 printRecord(frame);
			}
		});
		btnNewButton.setHorizontalTextPosition(SwingConstants.CENTER);
		btnNewButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnNewButton.setIcon(new ImageIcon(Vue_ContratDetails.class.getResource("/img/print.png")));
		btnNewButton.setBounds(444, 11, 52, 68);
		frame.getContentPane().add(btnNewButton);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 101, 488, 324);
		frame.getContentPane().add(tabbedPane);
		
		JPanel panel_info = new JPanel();
		tabbedPane.addTab("Informations", null, panel_info, null);
		panel_info.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Id du contrat :");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setBounds(46, 11, 94, 14);
		panel_info.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Locataire :");
		lblNewLabel_1_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_1_1.setBounds(3, 61, 139, 14);
		panel_info.add(lblNewLabel_1_1_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Début du contrat :");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_1.setBounds(3, 83, 139, 14);
		panel_info.add(lblNewLabel_1_1);
		
		JLabel lbl_Fin = new JLabel("Date de fin de contrat :");
		lbl_Fin.setHorizontalAlignment(SwingConstants.RIGHT);
		lbl_Fin.setBounds(-41, 108, 183, 14);
		panel_info.add(lbl_Fin);
		
		JLabel lblId = new JLabel("New label");
		lblId.setBounds(150, 11, 192, 14);
		panel_info.add(lblId);
		
		JLabel lblLocataire = new JLabel("New label");
		lblLocataire.setBounds(152, 61, 205, 14);
		panel_info.add(lblLocataire);
		
		JLabel lblDebut = new JLabel("New label");
		lblDebut.setBounds(152, 83, 205, 14);
		panel_info.add(lblDebut);
		
		JLabel lblFin = new JLabel("New label");
		lblFin.setBounds(152, 108, 156, 14);
		panel_info.add(lblFin);
		
		JPanel panel_garants = new JPanel();
		tabbedPane.addTab("Garants", null, panel_garants, null);
		panel_garants.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 463, 274);
		panel_garants.add(scrollPane);
		
		GarantDAO garantDAO = new GarantDAO();
		ArrayList<Garant>garants=garantDAO.getAllByContratId(contrat.getId());
		String columns[]= {"ID","Nom","Prenom","Adresse","Code postal","Ville","Pays","Téléphone","Mail"};
		String data[][]=new String[garants.size()][columns.length];
		int i=0;
		for(Garant g : garants) {
			data[i][0]=g.getId()+"";
			data[i][1]=g.getNom();
			data[i][2]=g.getPrenom();
			data[i][3]=g.getAdresse();
			data[i][4]=g.getCp();
			data[i][5]=g.getVille();
			data[i][6]=g.getPays();
			data[i][7]=g.getTel();
			data[i][8]=g.getMail();
			i++;
		}
		DefaultTableModel model = new DefaultTableModel(data,columns);
		table = new JTable(model);
		scrollPane.setViewportView(table);
		
		JPanel panel_assurance = new JPanel();
		tabbedPane.addTab("Assurances", null, panel_assurance, null);
		panel_assurance.setLayout(null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 11, 463, 274);
		panel_assurance.add(scrollPane_1);
		
		AssuranceDAO assuranceDAO = new AssuranceDAO();
		ArrayList<Assurance>assurances=assuranceDAO.getByIdContrat(contrat.getId());
		String columns2[]= {"ID","Type","Numéro de contrat"};
		String data2[][]=new String[assurances.size()][columns2.length];
		int i2=0;
		for(Assurance a : assurances) {
			data2[i2][0]=a.getId()+"";
			data2[i2][1]=a.getType();
			data2[i2][2]=a.getNumero();
			i2++;
		}
		DefaultTableModel model2=new DefaultTableModel(data2,columns2);
		
		table_1 = new JTable(model2);
		scrollPane_1.setViewportView(table_1);
		
		LocataireDAO locataireDAO = new LocataireDAO();
		Locataire locataire = locataireDAO.getById(contrat.getId_locataire());
		BienDAO bienDAO = new BienDAO();
		Bien bien=bienDAO.getById(contrat.getId_bien());
		
		
		if(contrat.getDatefin()==null) {
			lblFin.setVisible(false);
			lbl_Fin.setVisible(false);
		}
		
		JLabel lblNewLabel_1_2 = new JLabel("Bien concerné :");
		lblNewLabel_1_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_2.setBounds(46, 36, 94, 14);
		panel_info.add(lblNewLabel_1_2);
		
		JLabel lblId_1 = new JLabel("0");
		lblId_1.setBounds(150, 36, 192, 14);
		panel_info.add(lblId_1);
		
		lblId.setText(contrat.getId()+"");
		lblLocataire.setText(locataire+"");
		lblDebut.setText(contrat.getDate());
		lblFin.setText(contrat.getDatefin());
		lblId_1.setText(bien+"");
		JLabel lblBG = new JLabel("");
		lblBG.setOpaque(true);
		lblBG.setIcon(new ImageIcon(Vue_AccueilAgent.class.getResource("/img/accueil_bg.jpeg")));
		lblBG.setBounds(-16, 0, 1000, 591);
		frame.getContentPane().add(lblBG);
		
		
		
		
		
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
