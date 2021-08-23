package vue;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import dao.LocataireDAO;
import entite.Agent;
import entite.Locataire;

public class Vue_LocatairesList {

	private JFrame frame;
	private JTable tableLocataires;
	private Agent agent;
	private JTextField txtSearch;
	private DefaultTableModel model;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Vue_LocatairesList window = new Vue_LocatairesList();
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
	public Vue_LocatairesList() {
		initialize();
	}
	public Vue_LocatairesList(Agent agent) {
		this.agent=agent;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frame = new JFrame();
		frame.setBounds(100, 100, 981, 630);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(null);
		
		txtSearch = new JTextField();
		
		txtSearch.setBounds(654, 75, 301, 20);
		frame.getContentPane().add(txtSearch);
		txtSearch.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 106, 945, 485);
		frame.getContentPane().add(scrollPane);
		
		
		LocataireDAO locataireDAO=new LocataireDAO();
		Locataire locataire = new Locataire();
		ArrayList<Locataire>locataires=locataireDAO.getAllByIdAgent(agent.getId());
		String columns[]= {"ID","Nom","Prenom","Telephone","Statut","Situation"};
		String data[][]=new String[locataires.size()][columns.length];
		int i=0;
		for(Locataire l : locataires) {
			data[i][0]=l.getId()+"";
			data[i][1]=l.getNom();
			data[i][2]=l.getPrenom();
			data[i][3]=l.getTel();
			data[i][4]=l.getStatut();
			data[i][5]=l.getSituation();
			i++;
		}
		model=new DefaultTableModel(data,columns);
		tableLocataires = new JTable(model);
		scrollPane.setViewportView(tableLocataires);
		tableLocataires.setAutoCreateRowSorter(true);
		
		
		JButton btnNewTenant = new JButton("Nouveau locataire");
		btnNewTenant.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Vue_CreationLocataire().getFrame().setVisible(true);
			}
		});
		btnNewTenant.setIcon(new ImageIcon(Vue_LocatairesList.class.getResource("/img/peopleAdd.png")));
		btnNewTenant.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				frame.setCursor(Cursor.HAND_CURSOR);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				frame.setCursor(Cursor.DEFAULT_CURSOR);
			}
		});
		btnNewTenant.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnNewTenant.setHorizontalTextPosition(SwingConstants.CENTER);
		btnNewTenant.setHorizontalAlignment(SwingConstants.CENTER);
		btnNewTenant.setBorder(new LineBorder(new Color(0, 0, 0)));
		btnNewTenant.setBackground(Color.LIGHT_GRAY);
		btnNewTenant.setBounds(141, 11, 121, 84);
		frame.getContentPane().add(btnNewTenant);
		
		JButton btnRetour = new JButton("Retour");
		btnRetour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				new Vue_AccueilAgent(agent).getFrame().setVisible(true);
			}
		});
		btnRetour.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnRetour.setHorizontalTextPosition(SwingConstants.CENTER);
		btnRetour.setHorizontalAlignment(SwingConstants.CENTER);
		btnRetour.setBorder(new LineBorder(new Color(0, 0, 0)));
		btnRetour.setBackground(Color.LIGHT_GRAY);
		btnRetour.setBounds(10, 11, 121, 84);
		frame.getContentPane().add(btnRetour);
		
		JButton btnModifier = new JButton("Modifier");
		btnModifier.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnModifier.setHorizontalTextPosition(SwingConstants.CENTER);
		btnModifier.setHorizontalAlignment(SwingConstants.CENTER);
		btnModifier.setBorder(new LineBorder(new Color(0, 0, 0)));
		btnModifier.setBackground(Color.LIGHT_GRAY);
		btnModifier.setBounds(272, 11, 121, 84);
		frame.getContentPane().add(btnModifier);
		
		JButton btnDetails = new JButton("Détails");
		btnDetails.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnDetails.setHorizontalTextPosition(SwingConstants.CENTER);
		btnDetails.setHorizontalAlignment(SwingConstants.CENTER);
		btnDetails.setBorder(new LineBorder(new Color(0, 0, 0)));
		btnDetails.setBackground(Color.LIGHT_GRAY);
		btnDetails.setBounds(403, 11, 121, 84);
		frame.getContentPane().add(btnDetails);
		
		JButton btnSearch = new JButton("Recherche : ");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<Locataire>locataires = new ArrayList<Locataire>();
				String data[][]= new String[locataires.size()]
				
			}
		});
		btnSearch.setHorizontalAlignment(SwingConstants.RIGHT);
		btnSearch.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnSearch.setBounds(551, 75, 93, 20);
		frame.getContentPane().add(btnSearch);
		
		txtSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnSearch.doClick();
				
			}
		});
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(Vue_LocatairesList.class.getResource("/img/accueil_bg.jpeg")));
		lblNewLabel.setBounds(-26, -19, 1023, 636);
		frame.getContentPane().add(lblNewLabel);
	}

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}
}
