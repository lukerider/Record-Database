package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class AddNewArtist extends JFrame {

	private JPanel contentPane;
	private JTextField NametextField;
	private JTextField YearFormedtextField;
	private JTextField YearDisbandtextField;
	private JTextField FromtextField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddNewArtist frame = new AddNewArtist();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AddNewArtist() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(AddNewArtist.class.getResource("/gui/11970932001393807721BenBois_Vinyl_records.svg.hi.png")));
		setTitle("Add New Artist");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 325, 176);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblName = new JLabel("Name:");
		lblName.setBounds(10, 11, 83, 14);
		contentPane.add(lblName);
		
		NametextField = new JTextField();
		lblName.setLabelFor(NametextField);
		NametextField.setBounds(103, 8, 155, 20);
		contentPane.add(NametextField);
		NametextField.setColumns(10);
		
		JLabel lblYearFormed = new JLabel("Year Formed:");
		lblYearFormed.setBounds(10, 36, 83, 14);
		contentPane.add(lblYearFormed);
		
		YearFormedtextField = new JTextField();
		lblYearFormed.setLabelFor(YearFormedtextField);
		YearFormedtextField.setBounds(103, 33, 86, 20);
		contentPane.add(YearFormedtextField);
		YearFormedtextField.setColumns(10);
		
		JLabel lblYearDisband = new JLabel("Year Disband:");
		lblYearDisband.setBounds(10, 61, 83, 14);
		contentPane.add(lblYearDisband);
		
		YearDisbandtextField = new JTextField();
		lblYearDisband.setLabelFor(YearDisbandtextField);
		YearDisbandtextField.setBounds(103, 58, 86, 20);
		contentPane.add(YearDisbandtextField);
		YearDisbandtextField.setColumns(10);
		
		JLabel lblFrom = new JLabel("From:");
		lblFrom.setBounds(10, 86, 83, 14);
		contentPane.add(lblFrom);
		
		FromtextField = new JTextField();
		lblFrom.setLabelFor(FromtextField);
		FromtextField.setBounds(103, 83, 155, 20);
		contentPane.add(FromtextField);
		FromtextField.setColumns(10);
	}
}
