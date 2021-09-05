package vue;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

public class Test {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Test window = new Test();
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
	public Test() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 904, 511);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 868, 450);
		frame.getContentPane().add(panel);

		JPanel pnl = new JPanel(new BorderLayout());
		frame.setContentPane(pnl);
		pnl.setSize(400, 250);
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		dataset.addValue(120000.0, "Produit 1", "2000");
		dataset.addValue(550000.0, "Produit 1", "2001");
		dataset.addValue(180000.0, "Produit 1", "2002");
		dataset.addValue(270000.0, "Produit 2", "2000");
		dataset.addValue(600000.0, "Produit 2", "2001");
		dataset.addValue(230000.0, "Produit 2", "2002");
		dataset.addValue(90000.0, "Produit 3", "2000");
		dataset.addValue(450000.0, "Produit 3", "2001");
		dataset.addValue(170000.0, "Produit 3", "2002");
		JFreeChart barChart = ChartFactory.createBarChart("Evolution des ventes", "", "Unité vendue", dataset,
				PlotOrientation.VERTICAL, true, true, false);
		ChartPanel cPanel = new ChartPanel(barChart);
		pnl.add(cPanel);

	}
}
