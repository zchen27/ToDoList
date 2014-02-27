import javax.swing.JPanel;

public abstract class AbstractNewActionItemBar extends JPanel {
	
	AbstractStateInformation information;
	
	abstract void NewActionItemBar(); // Constructor	
	public abstract void update (boolean enterPressed);
	public abstract void draw();
	
}
