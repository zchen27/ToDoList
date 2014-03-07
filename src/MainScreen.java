import java.awt.Container;
import java.awt.Point;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;


public class MainScreen extends AbstractMainWindow{
	public static AbstractStateInformation si;
	private boolean leftClick;
	private boolean rightClick;
	private Point mousePoint;
	private boolean enterPress;
	private boolean running;
	private ArrayList<JComponent> components=new ArrayList<JComponent>();
	private JPanel contentPane;
	private AbstractMenuBar menuBar;
	private AbstractEventPanel eventPanel;
	MainScreen(){
		super("To Do List");
		contentPane=new JPanel();
		setSize(1200,800);
		setLocation(50,50);
		setResizable(false);
		addMouseListener(this);
		addKeyListener(this);
		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane,BoxLayout.Y_AXIS));
		eventPanel=new EventPanel(this);
		contentPane.add(eventPanel);
		components.add(new EventPanel(this));
		//pack();
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		run();
		
		
	}
	public void mouseClicked(MouseEvent arg0) {
		if(arg0.equals(MouseEvent.BUTTON1)){
			leftClick=true;
		}
		if(arg0.equals(MouseEvent.BUTTON2)){
			rightClick=true;
		}
		mousePoint=arg0.getPoint();
	}
	public void mouseEntered(MouseEvent arg0) {}
	public void mouseExited(MouseEvent arg0) {}
	public void mousePressed(MouseEvent arg0) {}
	public void mouseReleased(MouseEvent arg0) {}
	public void keyPressed(KeyEvent arg0) {
		if(arg0.equals(KeyEvent.VK_ENTER)){
			enterPress=true;
		}
	}
	public void keyReleased(KeyEvent arg0) {}
	public void keyTyped(KeyEvent arg0) {}
	public void run() {
		running=true;
		while(running){
			update();
			load();
			draw();
		}
	}
	public void load() {
		//load(si.lastBackup());
	}
	
	public void load(String fileAddress) {
		//si.FileBackup.loadBackup(fileAddress);
	}
	public void update() {
		for(int i=0;i<components.size();i++){
			((EventPanel) components.get(i)).update(mousePoint,rightClick,leftClick);
		}
	}

	public void draw() {
		for(int i=0;i<components.size();i++){
			((EventPanel) components.get(i)).draw();
		}
	}
	public void close() {
		
	}
	public void addComponent(JComponent c) {
		
	}

	public void removeComponent(JComponent c) {
		
	}
	public static void main(String[] args){
		new MainScreen();
	}

}
