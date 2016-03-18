package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;

public class AddNewVendor extends JFrame {

	private JPanel contentPane;
	private JTextField VendorNameField;
	private JTextField AddressField;
	private JTextField CityField;
	private JTextField StateField;
	private JTextField WebsiteField;
	private JLabel lblOtherInfo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddNewVendor frame = new AddNewVendor();
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
	public AddNewVendor() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(AddNewVendor.class.getResource("/gui/11970932001393807721BenBois_Vinyl_records.svg.hi.png")));
		setTitle("Add A New Vendor");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblvendorName = new JLabel("*Vendor Name:");
		lblvendorName.setBounds(10, 11, 90, 14);
		contentPane.add(lblvendorName);
		
		VendorNameField = new JTextField();
		VendorNameField.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblvendorName.setLabelFor(VendorNameField);
		VendorNameField.setBounds(110, 8, 190, 20);
		contentPane.add(VendorNameField);
		VendorNameField.setColumns(10);
		
		JLabel lbladress = new JLabel("Adress:");
		lbladress.setBounds(10, 36, 90, 14);
		contentPane.add(lbladress);
		
		AddressField = new JTextField();
		AddressField.setBorder(new LineBorder(new Color(0, 0, 0)));
		lbladress.setLabelFor(AddressField);
		AddressField.setBounds(110, 33, 190, 20);
		contentPane.add(AddressField);
		AddressField.setColumns(10);
		
		JLabel lblCity = new JLabel("City:");
		lblCity.setBounds(10, 61, 90, 14);
		contentPane.add(lblCity);
		
		CityField = new JTextField();
		CityField.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblCity.setLabelFor(CityField);
		CityField.setBounds(110, 58, 190, 20);
		contentPane.add(CityField);
		CityField.setColumns(10);
		
		JLabel lblState = new JLabel("State: ");
		lblState.setBounds(10, 86, 90, 14);
		contentPane.add(lblState);
		
		StateField = new JTextField();
		lblState.setLabelFor(StateField);
		StateField.setBorder(new LineBorder(new Color(0, 0, 0)));
		StateField.setBounds(110, 83, 90, 20);
		contentPane.add(StateField);
		StateField.setColumns(10);
		
		JLabel lblWebsite = new JLabel("Website:");
		lblWebsite.setBounds(10, 111, 90, 14);
		contentPane.add(lblWebsite);
		
		WebsiteField = new JTextField();
		lblWebsite.setLabelFor(WebsiteField);
		WebsiteField.setBorder(new LineBorder(new Color(0, 0, 0)));
		WebsiteField.setBounds(110, 108, 190, 20);
		contentPane.add(WebsiteField);
		WebsiteField.setColumns(10);
		
		lblOtherInfo = new JLabel("Other Info:");
		lblOtherInfo.setBounds(10, 136, 90, 14);
		contentPane.add(lblOtherInfo);
		
		JTextPane OtherInfoPane = new JTextPane();
		lblOtherInfo.setLabelFor(OtherInfoPane);
		OtherInfoPane.setBorder(new LineBorder(new Color(0, 0, 0)));
		OtherInfoPane.setBounds(110, 139, 190, 83);
		contentPane.add(OtherInfoPane);
	}

}
