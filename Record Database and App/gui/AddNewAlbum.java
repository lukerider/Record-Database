package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JTextField;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import java.awt.Dimension;

public class AddNewAlbum extends JFrame {

	private JPanel contentPane;
	private JTextField ArtistField;
	private JTextField AlbumField;
	private JTextField CATNumField;
	private JTextField ReleaseYearField;
	private JTextField ReleaseCountryField;
	private JTextField MediaCoinditionField;
	private JTextField SleeveField;
	private JTextField PurchasedFromField;
	private JTextField PurchasedPriceField;
	private JTable table;
	
	//Manually Added
	private int songNumber = 10;
	protected final int ArtWorkLBWidth = 392, ArtWorkLBHeight = 345, ArtWorkLBX = 10;
	protected int ArtWorkLBY = 11;
	protected static JLabel ArtWork;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable() 
		{
			public void run()
			{
				try 
				{
					AddNewAlbum frame = new AddNewAlbum();
					frame.setVisible(true);
					frame.setResizable(false);
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AddNewAlbum() 
	{
		setTitle("Add New Album");
		setIconImage(Toolkit.getDefaultToolkit().getImage(AddNewAlbum.class.getResource("/gui/11970932001393807721BenBois_Vinyl_records.svg.hi.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 465, 488);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane, BorderLayout.CENTER);
		
		JPanel GeneralInfo = new JPanel();
		tabbedPane.addTab("General Info", null, GeneralInfo, null);
		GeneralInfo.setLayout(null);
		
		JPanel DonePanel = new JPanel();
		DonePanel.setBounds(0, 391, 434, 20);
		GeneralInfo.add(DonePanel);
		DonePanel.setLayout(null);
		
		JLabel required = new JLabel("*Required");
		required.setForeground(Color.BLUE);
		required.setBounds(10, 0, 61, 14);
		DonePanel.add(required);
		
		JButton btnNewButton = new JButton("Done");
		btnNewButton.setBounds(332, 0, 89, 19);
		DonePanel.add(btnNewButton);
		
		JScrollPane TrackListTable = new JScrollPane();
		TrackListTable.setBorder(new LineBorder(Color.BLACK));
		TrackListTable.setBounds(10, 265, 409, 102);
		GeneralInfo.add(TrackListTable);
		
		String[] columnHeadders = {"Track Number", "Track Name", "Track Artist", "Track Length"};
		final DefaultTableModel tm = new DefaultTableModel(columnHeadders, 10);
		table = new JTable(tm);
		//sets track number column for first 10 rows
		for(int step=0; step<songNumber;step++)
		{
			table.setValueAt((""+(step+1)),step,0);
		}	
		TrackListTable.setViewportView(table);
		final Object[] blank = new Object[columnHeadders.length];
		JButton AddSong = new JButton("Add Song");
		AddSong.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				tm.addRow(blank);
				table.setValueAt((""+(songNumber+1)), songNumber,0);
				songNumber++;
			}
		});
		AddSong.setBounds(332, 367, 89, 20);
		GeneralInfo.add(AddSong);
		
		
		
		
		
		JLabel lblartist = new JLabel("*Artist:");
		lblartist.setBounds(10, 11, 90, 14);
		GeneralInfo.add(lblartist);
		
		ArtistField = new JTextField();
		ArtistField.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblartist.setLabelFor(ArtistField);
		ArtistField.setBounds(130, 8, 86, 20);
		GeneralInfo.add(ArtistField);
		ArtistField.setColumns(10);
		
		JLabel lblalbum = new JLabel("*Album:");
		lblalbum.setBounds(10, 36, 90, 14);
		GeneralInfo.add(lblalbum);
		
		AlbumField = new JTextField();
		AlbumField.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblalbum.setLabelFor(AlbumField);
		AlbumField.setBounds(130, 33, 86, 20);
		GeneralInfo.add(AlbumField);
		AlbumField.setColumns(10);
		
		JLabel lblcatNumber = new JLabel("*CAT Number:");
		lblcatNumber.setBounds(10, 61, 104, 14);
		GeneralInfo.add(lblcatNumber);
		
		CATNumField = new JTextField();
		CATNumField.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblcatNumber.setLabelFor(CATNumField);
		CATNumField.setBounds(130, 58, 86, 20);
		GeneralInfo.add(CATNumField);
		CATNumField.setColumns(10);
		
		JLabel lblReleaseYear = new JLabel("Release Year:");
		lblReleaseYear.setBounds(10, 86, 104, 14);
		GeneralInfo.add(lblReleaseYear);
		
		ReleaseYearField = new JTextField();
		ReleaseYearField.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblReleaseYear.setLabelFor(ReleaseYearField);
		ReleaseYearField.setBounds(130, 83, 86, 20);
		GeneralInfo.add(ReleaseYearField);
		ReleaseYearField.setColumns(10);
		
		JLabel lblReleaseCountry = new JLabel("Release Country:");
		lblReleaseCountry.setBounds(10, 111, 104, 14);
		GeneralInfo.add(lblReleaseCountry);
		
		ReleaseCountryField = new JTextField();
		ReleaseCountryField.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblReleaseCountry.setLabelFor(ReleaseCountryField);
		ReleaseCountryField.setBounds(130, 108, 86, 20);
		GeneralInfo.add(ReleaseCountryField);
		ReleaseCountryField.setColumns(10);
		
		JLabel lblmediaCondition = new JLabel("*Media Condition:");
		lblmediaCondition.setBounds(10, 136, 104, 14);
		GeneralInfo.add(lblmediaCondition);
		
		MediaCoinditionField = new JTextField();
		MediaCoinditionField.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblmediaCondition.setLabelFor(MediaCoinditionField);
		MediaCoinditionField.setBounds(130, 133, 86, 20);
		GeneralInfo.add(MediaCoinditionField);
		MediaCoinditionField.setColumns(10);
		
		JLabel lblsleeveCondition = new JLabel("*Sleeve Condition:");
		lblsleeveCondition.setBounds(10, 161, 104, 14);
		GeneralInfo.add(lblsleeveCondition);
		
		SleeveField = new JTextField();
		SleeveField.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblsleeveCondition.setLabelFor(SleeveField);
		SleeveField.setBounds(130, 158, 86, 20);
		GeneralInfo.add(SleeveField);
		SleeveField.setColumns(10);
		
		JLabel lblpurchasedFrom = new JLabel("*Purchased From:");
		lblpurchasedFrom.setBounds(226, 11, 104, 14);
		GeneralInfo.add(lblpurchasedFrom);
		
		PurchasedFromField = new JTextField();
		PurchasedFromField.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblpurchasedFrom.setLabelFor(PurchasedFromField);
		PurchasedFromField.setBounds(333, 8, 86, 20);
		GeneralInfo.add(PurchasedFromField);
		PurchasedFromField.setColumns(10);
		
		JLabel lblpurchasePrice = new JLabel("*Purchase Price:");
		lblpurchasePrice.setBounds(226, 36, 104, 14);
		GeneralInfo.add(lblpurchasePrice);
		
		PurchasedPriceField = new JTextField();
		PurchasedPriceField.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblpurchasePrice.setLabelFor(PurchasedPriceField);
		PurchasedPriceField.setText("$");
		PurchasedPriceField.setBounds(333, 33, 86, 20);
		GeneralInfo.add(PurchasedPriceField);
		PurchasedPriceField.setColumns(10);
		
		JLabel lblOtherInfo = new JLabel("Other Info:");
		lblOtherInfo.setBounds(10, 186, 90, 14);
		GeneralInfo.add(lblOtherInfo);
		
		JTextPane textPane = new JTextPane();
		textPane.setBorder(new LineBorder(new Color(0, 0, 0)));
		textPane.setBounds(130, 190, 212, 64);
		GeneralInfo.add(textPane);	
		
		JPanel AlbumArtwork = new JPanel();
		tabbedPane.addTab("Album Artwork", null, AlbumArtwork, null);
		AlbumArtwork.setLayout(null);
		
		
		final JPanel ArtworkPanel = new JPanel();
		ArtworkPanel.setBounds(10, 11, 392, (345+100));
		ArtworkPanel.setLayout(null);
		
		final JScrollPane ArtworkScrollPane = new JScrollPane(ArtworkPanel,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		ArtworkScrollPane.setBounds(10, 11, 414, 369);
		AlbumArtwork.add(ArtworkScrollPane);
		
				
		JPanel DonePanel_2 = new JPanel();
		DonePanel_2.setBounds(0, 391, 434, 20);
		AlbumArtwork.add(DonePanel_2);
		DonePanel_2.setLayout(null);
		
		JButton btnAddArtwork = new JButton("Add Artwork");
		btnAddArtwork.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{	
				FileChooser.main(null);
			
				ArtWork = new JLabel();
				ArtWork.setBounds(ArtWorkLBX, ArtWorkLBY , ArtWorkLBWidth, ArtWorkLBHeight);
				ArtworkPanel.add(ArtWork);
				ArtWorkLBY += ArtWorkLBHeight + 5;
				ArtworkPanel.setPreferredSize(new Dimension(ArtWorkLBWidth, ArtWorkLBY));
				ArtworkPanel.updateUI();
			}
		});
		
		JButton btnDone = new JButton("Done");
		btnDone.setBounds(332, 0, 89, 19);
		DonePanel_2.add(btnDone);
		btnAddArtwork.setBounds(191, 0, 119, 19);
		DonePanel_2.add(btnAddArtwork);
	}
}
