import java.awt.Container;
import java.awt.Point;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;


public class MainScreen extends JFrame implements MouseListener, KeyListener{
	public static AbstractStateInformation si;
	private boolean leftClick;
	private boolean rightClick;
	private Point mousePoint;
	private boolean enterPress;
	private boolean running;
	private ArrayList<JComponent> components=new ArrayList<JComponent>();
	private JPanel contentPane;
	private AbstractMenuBar menuBar;
	private EventPanel eventPanel;
	
	MainScreen(){
		super("To Do List");
		setUp();
		si=new StateInformation();
		//addMenuBar();
		addEventPanel();
		//addNewActionItemBar();
		setVisible(true);
		
		//run();	
	}
	public EventPanel getEventPanel(){
		return eventPanel;
	}
	private void setUp(){
		contentPane=new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane,BoxLayout.Y_AXIS));
		setSize(1200,800);
		setLocation(50,50);
		setResizable(false);
		addMouseListener(this);
		addKeyListener(this);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	private void addMenuBar(){
		menuBar=new MenuBar(this);
	}
	private void addEventPanel(){
		eventPanel=new EventPanel(this);
		contentPane.add(eventPanel);
		repaint();
	}
	public void mouseClicked(MouseEvent arg0) {
		if(arg0.getButton()==MouseEvent.BUTTON1){
			leftClick=true;
		}
		if(arg0.getButton()==MouseEvent.BUTTON3){
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