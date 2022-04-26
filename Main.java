/**
     * MD Amir Hamja
     * Sqv036
     * UTSA CS 3443 
     * spring 2022
     */



package notepad.app;

import java.awt.EventQueue
import static javax.swing.JOptionPane.showMessageDialog;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JTextPane;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Button;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime;
import java.awt.List;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextArea;


public class Main {
//Main
	private JFrame frmNotepad;
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");  
		LocalDateTime now = LocalDateTime.now(); 
         static DefaultListModel<String> notesData = new DefaultListModel<>();  
         JList addedNotes = new JList(notesData);
         int selected=-1;
         boolean select=false;
        
		
	
	public void storeToDB(String dataString) {
		
	}
	public static void main(String[] args) {
		 
		 
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
					window.frmNotepad.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Main() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmNotepad = new JFrame();
		frmNotepad.getContentPane().setFont(new Font("Dialog", Font.PLAIN, 17));
		frmNotepad.setTitle("Notepad");
		frmNotepad.getContentPane().setBackground(Color.DARK_GRAY);
		frmNotepad.setBounds(100, 100, 700, 700);
		frmNotepad.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmNotepad.getContentPane().setLayout(null);
		
		
		JLabel lblNotepad = new JLabel("NOTEPAD");
		lblNotepad.setFont(new Font("Dialog", Font.BOLD, 30));
		lblNotepad.setForeground(Color.WHITE);
		lblNotepad.setBounds(237, 39, 179, 54);
		frmNotepad.getContentPane().add(lblNotepad);
		
		JTextArea notes = new JTextArea();
		notes.setText("Text goes here....");
		notes.setBounds(56, 130, 238, 301);
		frmNotepad.getContentPane().add(notes);
		
		JLabel lblNotesFor = new JLabel("NOTES FOR:");
		lblNotesFor.setForeground(Color.WHITE);
		lblNotesFor.setBounds(406, 115, 129, 15);
		frmNotepad.getContentPane().add(lblNotesFor);
		
		Button button = new Button("ADD NOTES");
		button.setBackground(Color.DARK_GRAY);
		button.setForeground(Color.WHITE);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(notes.getText().toString().isBlank()) { 
					showMessageDialog(null, "Please add some text");
				}
				else{
					notesData.addElement(notes.getText());
					
				}
				
				
				
			}
		});
		button.setBounds(110, 467, 136, 41);
		frmNotepad.getContentPane().add(button);
		
		JLabel date = new JLabel(dtf.format(now));
		date.setForeground(Color.WHITE);
		date.setBounds(484, 115, 129, 15);
		frmNotepad.getContentPane().add(date);
		
		Button button_1 = new Button("REMOVE NOTE");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(addedNotes.getSelectedIndex()< 0)  
					showMessageDialog(null, "Please select a note");					
				
				else {
					if(notesData.isEmpty()) {}
					
					notesData.remove(selected);
					
				}
				
			}
		});
		button_1.setForeground(Color.WHITE);
		button_1.setBackground(Color.DARK_GRAY);
		button_1.setBounds(462, 467, 151, 41);
		frmNotepad.getContentPane().add(button_1);
		addedNotes.setSelectedIndex(0);
		addedNotes.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				 selected=addedNotes.getSelectedIndex();
				
				 
			}
		});
	
		
		addedNotes.setBounds(363, 130, 275, 301);
		frmNotepad.getContentPane().add(addedNotes);
		
		
		
	
	}
}
