import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;


// add border to text area
// make window non-resizable below a certain size


public class CommentWindow extends JFrame implements ActionListener{
	private JPanel contentPane;
	private String eventComment;
	private JButton commit,delete;
	private JTextArea txtField;
	private int eventIndexNum;
	private JScrollPane scrollPane;
	CommentWindow(MainScreen mainScreen,String title,int eventIndex){
		super(title);
		eventIndexNum=eventIndex;

		eventComment= mainScreen.si.getEventList().get(eventIndexNum).getComment();

		contentPane= new JPanel();
		contentPane.setLayout(new BoxLayout(contentPane,BoxLayout.PAGE_AXIS));
		contentPane.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

		txtField = new JTextArea();
		txtField.setLineWrap(true);
		txtField.setText(eventComment);
		txtField.setBorder(BorderFactory.createLineBorder(Color.black));
		scrollPane= new JScrollPane();
		scrollPane.setViewportView(txtField);
		scrollPane.setHorizontalScrollBarPolicy(scrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setVerticalScrollBarPolicy(scrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		contentPane.add(scrollPane);

		commit = new JButton("Commit");
		commit.setActionCommand("commit");
		commit.addActionListener(this);
		commit.setAlignmentX(TextArea.CENTER_ALIGNMENT);
		contentPane.add(commit);

		delete = new JButton("Delete");
		delete.setActionCommand("delete");
		delete.addActionListener(this);
		delete.setAlignmentX(TextArea.CENTER_ALIGNMENT);
		contentPane.add(delete);

		this.setContentPane(contentPane);
		this.pack();
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		Dimension dim = this.getToolkit().getScreenSize();  
		int screenWidth = dim.width;  
		int screenHeight = dim.height;  
		int frameWidth = screenWidth/3;  
		int frameHeight = screenHeight/3;  
		this.setSize(frameWidth,frameHeight);  
		this.setLocation((screenWidth-frameWidth)/2,(screenHeight-frameHeight)/2);
		this.setVisible(true);

	}
	public void actionPerformed(ActionEvent e){
		String eventName = e.getActionCommand();
		if(eventName.equals("commit")){
			commit(txtField.getText());
		}else if(eventName.equals("delete")){
			delete();
		}
	}
	public void commit(String comment){
		eventComment = comment;
		MainScreen.si.getEventList().get(eventIndexNum).setComment(eventComment);
		this.dispose();
	}
	public void delete(){
		eventComment = "";
		MainScreen.si.getEventList().get(eventIndexNum).setComment(eventComment);
		this.dispose();
	}
}
