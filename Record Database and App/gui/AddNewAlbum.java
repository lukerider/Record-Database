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

import Models.Albums;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import java.awt.Dimension;
import java.io.File;
import java.util.ArrayList;
import java.sql.*;



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
	private JTable TrackListTable;
	
	//Manually Added
	private int songNumber = 10;
	protected final int ArtWorkLBWidth = 392, ArtWorkLBHeight = 345, ArtWorkLBX = 10;
	protected int ArtWorkLBY = 11;
	protected static JLabel ArtWork;
	private JTextField PurchasedDateField;
	private JTable ArtistTable;
	public static ArrayList<File> ArtworkFiles = new ArrayList<File>();
	//Remember: File paths may be obtained by the too string method. 
	

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
	/*
	 * This method gathers all attributes for an Album and creates and Album Object 
	 */
	public Albums getAtributesForAlbum()
	{
		String albumName = "'"+AlbumField.getText()+"'";
		String artistName = ""+ArtistField.getText();
		int releaseYear = Integer.parseInt(ReleaseYearField.getText());
		String catilogNum = "'"+CATNumField.getText()+"'";
		String releaseCountry = "'"+ReleaseCountryField.getText()+"'";
		
		Albums temp = new Albums(albumName,artistName,releaseYear,releaseCountry,catilogNum);
		return temp;
	}
		
	
	
	
	
	
	

	/**
	 * Create the frame.
	 */
	public AddNewAlbum() 
	{
		setTitle("Add New Album");
		setIconImage(Toolkit.getDefaultToolkit().getImage(AddNewAlbum.class.getResource("/gui/11970932001393807721BenBois_Vinyl_records.svg.hi.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 465, 642);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane, BorderLayout.CENTER);
		
		JPanel GeneralInfo = new JPanel();
		GeneralInfo.setName("GeneralInfo");
		tabbedPane.addTab("General Info", null, GeneralInfo, null);
		GeneralInfo.setLayout(null);
		
		JPanel DonePanel = new JPanel();
		DonePanel.setBounds(0, 545, 434, 20);
		GeneralInfo.add(DonePanel);
		DonePanel.setLayout(null);
		
		JLabel required = new JLabel("*Required");
		required.setForeground(Color.BLUE);
		required.setBounds(10, 0, 61, 14);
		DonePanel.add(required);
		
		JButton btnNewButton = new JButton("Done");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try 
				{
					/***********************************Set Up******************************************/
					Class.forName("org.sqlite.JDBC");
					Statement stmt = null;
					ResultSet rs = null;
					String sql = null;
					/**********************************Connect to Database******************************/
					Connection c = DriverManager.getConnection("jdbc:sqlite:Record.db");
					c.setAutoCommit(false);
					/*****************************Collect & Submit Data*********************************/
					//Album
					Albums album = getAtributesForAlbum();
					//Submit Album
						//sql = ;
						//stmt.executeUpdate(sql);
					//Vendor
					
					//Version
					
					
					//Artist
					
					//People
					
					//Artwork				
					
					/***********************************Close Connection********************************/
					stmt.close();
					c.close();					
				} 
				catch (Exception e)
				{
					gui.ErrorPane error = new gui.ErrorPane();
					error.setErrorMessage("Submit Was Unsuccessful");
					error.main(null);
				}	
			}
		});
		btnNewButton.setBounds(332, 0, 89, 19);
		DonePanel.add(btnNewButton);
		
		JScrollPane TrackListScrollPanel = new JScrollPane();
		TrackListScrollPanel.setBorder(new LineBorder(Color.BLACK));
		TrackListScrollPanel.setBounds(10, 420, 409, 102);
		GeneralInfo.add(TrackListScrollPanel);
		
		
		String[] SongColumnHeadders = {"Track Number", "Track Name", "Track Artist", "Track Length"};
		final DefaultTableModel songtm = new DefaultTableModel(SongColumnHeadders, 10);
		TrackListTable = new JTable(songtm);
		//sets track number column for first 10 rows
		for(int step=0; step<songNumber;step++)
		{
			TrackListTable.setValueAt((""+(step+1)),step,0);
		}	
		final Object[] songBlank = new Object[SongColumnHeadders.length];
			
		TrackListScrollPanel.setViewportView(TrackListTable);
		JButton AddSong = new JButton("Add Song");
		AddSong.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				songtm.addRow(songBlank);
				TrackListTable.setValueAt((""+(songNumber+1)), songNumber,0);
				songNumber++;
			}
		});
		
		JLabel lblSongs = new JLabel("Songs:");
		lblSongs.setLabelFor(TrackListScrollPanel);
		lblSongs.setBounds(10, 405, 46, 14);
		GeneralInfo.add(lblSongs);
		AddSong.setBounds(330, 522, 89, 20);
		GeneralInfo.add(AddSong);
		
		JScrollPane PeopleScrollPanel = new JScrollPane();
		PeopleScrollPanel.setBorder(new LineBorder(Color.BLACK));
		PeopleScrollPanel.setBounds(10, 282, 409, 102);
		GeneralInfo.add(PeopleScrollPanel);
		
		String[] peopleColumnHeadders = {"*First Name", "*Last Name", "*Instrument"};
		final DefaultTableModel peopletm = new DefaultTableModel(peopleColumnHeadders, 5);
		ArtistTable = new JTable(peopletm);	
		PeopleScrollPanel.setViewportView(ArtistTable);
		
		final Object[] peopleblank = new Object[peopleColumnHeadders.length];
		JButton btnAddPerson = new JButton("Add Person");
		btnAddPerson.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				peopletm.addRow(peopleblank);
			}
		});
		btnAddPerson.setBounds(315, 386, 104, 20);
		GeneralInfo.add(btnAddPerson);
		
		JLabel lblContributingPeople = new JLabel("Contributing People:");
		lblContributingPeople.setLabelFor(PeopleScrollPanel);
		lblContributingPeople.setBounds(10, 265, 120, 14);
		GeneralInfo.add(lblContributingPeople);
		
		
		
		
		
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
		lblcatNumber.setBounds(10, 86, 104, 14);
		GeneralInfo.add(lblcatNumber);
		
		CATNumField = new JTextField();
		CATNumField.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblcatNumber.setLabelFor(CATNumField);
		CATNumField.setBounds(130, 83, 86, 20);
		GeneralInfo.add(CATNumField);
		CATNumField.setColumns(10);
		
		JLabel lblReleaseYear = new JLabel("*Release Year:");
		lblReleaseYear.setBounds(10, 61, 104, 14);
		GeneralInfo.add(lblReleaseYear);
		
		ReleaseYearField = new JTextField();
		ReleaseYearField.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblReleaseYear.setLabelFor(ReleaseYearField);
		ReleaseYearField.setBounds(130, 58, 86, 20);
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
		
		JLabel lblpurchasedDate = new JLabel("*Purchased Date:");
		lblpurchasedDate.setBounds(226, 61, 104, 14);
		GeneralInfo.add(lblpurchasedDate);
		lblpurchasedDate.setLabelFor(PurchasedDateField);
		
		PurchasedDateField = new JTextField();
		PurchasedDateField.setBorder(new LineBorder(Color.BLACK));
		PurchasedDateField.setBounds(332, 58, 86, 20);
		GeneralInfo.add(PurchasedDateField);
		PurchasedDateField.setColumns(10);
		
		JLabel lblOtherInfo = new JLabel("Other Info:");
		lblOtherInfo.setBounds(10, 186, 90, 14);
		GeneralInfo.add(lblOtherInfo);
		
		JTextPane textPane = new JTextPane();
		textPane.setBorder(new LineBorder(new Color(0, 0, 0)));
		textPane.setBounds(130, 190, 212, 64);
		GeneralInfo.add(textPane);
		
		
		
		JPanel AlbumArtwork = new JPanel();
		AlbumArtwork.setName("AlbumArtwork");
		tabbedPane.addTab("Album Artwork", null, AlbumArtwork, null);
		AlbumArtwork.setLayout(null);
		
		
		final JPanel ArtworkPanel = new JPanel();
		ArtworkPanel.setBounds(10, 11, 392, (345+100));
		ArtworkPanel.setLayout(null);
		
		final JScrollPane ArtworkScrollPane = new JScrollPane(ArtworkPanel,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		ArtworkScrollPane.setBounds(10, 11, 414, 523);
		AlbumArtwork.add(ArtworkScrollPane);
		
				
		JPanel DonePanel_2 = new JPanel();
		DonePanel_2.setBounds(0, 545, 434, 20);
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
