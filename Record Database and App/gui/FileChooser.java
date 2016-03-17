package gui;


import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

import java.awt.Toolkit;
import java.io.File;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FileChooser extends AddNewAlbum {

	private JPanel contentPane;
	public static File file;
	public static JFileChooser fc;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FileChooser frame1 = new FileChooser();
					frame1.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FileChooser() {
		setTitle("Choose File From");
		setIconImage(Toolkit.getDefaultToolkit().getImage(FileChooser.class.getResource("/gui/11970932001393807721BenBois_Vinyl_records.svg.hi.png")));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 309, 120);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		fc = new JFileChooser();
		
		
		
		JButton btnBrowseFiles = new JButton("Browse Files");
		btnBrowseFiles.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int returnVal = fc.showOpenDialog(FileChooser.this);
				if (returnVal == JFileChooser.APPROVE_OPTION) 
				{
		            file = fc.getSelectedFile();
		            
		            ImageIcon imageIcon = new ImageIcon(""+file);
					Image image = imageIcon.getImage();
					Image newimg = image.getScaledInstance(ArtWorkLBWidth, ArtWorkLBHeight,  Image.SCALE_SMOOTH);
					imageIcon = new ImageIcon(newimg);
					
					AddNewAlbum.ArtWork.setIcon(imageIcon);
				}
				
			}
		});
		btnBrowseFiles.setBounds(10, 11, 120, 38);
		contentPane.add(btnBrowseFiles);
		
		JButton btnFromInternet = new JButton("From Internet");
		btnFromInternet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		btnFromInternet.setBounds(163, 11, 120, 38);
		contentPane.add(btnFromInternet);
	}
}
