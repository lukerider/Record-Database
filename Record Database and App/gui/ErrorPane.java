package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Toolkit;

public class ErrorPane extends JFrame {

	private JPanel contentPane;
	private JLabel label ;
	private JPanel panel;
	private String errorMessage;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ErrorPane frame = new ErrorPane();
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
	public ErrorPane() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(ErrorPane.class.getResource("/gui/11970932001393807721BenBois_Vinyl_records.svg.hi.png")));
		setTitle("Error");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 338, 149);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		label = new JLabel(errorMessage);
		label.setBounds(10, 11, 292, 78);
		panel.add(label);
		
		
	}
	public void setErrorMessage(String error)
	{
		errorMessage = error;
		label.setText(errorMessage);
		panel.updateUI();
	}	
}
