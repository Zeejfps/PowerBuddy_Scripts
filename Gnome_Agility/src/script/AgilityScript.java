package script;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.powerbuddy.api.input.Mouse;
import org.powerbuddy.api.methods.Camera;
import org.powerbuddy.api.methods.Skills;
import org.powerbuddy.api.util.Timer;
import org.powerbuddy.bot.event.Paintable;
import org.powerbuddy.script.Manifest;
import org.powerbuddy.script.Script;

import script.courses.GnomeCourse;
import script.courses.ObstacleCourse;
import script.obstacles.Obstacle;
import script.util.conditions.Condition;

/*
 * Description: Will complete an agility course of users choice.
 * @author: Zeejfps.
 * Version: 1.1
 */
@Manifest(author = "Zeejfps", name = "AIO Agility", description= "Completes the Agility course in the Gnome Stronghold.")
public class AgilityScript extends Script implements Paintable{

	private BufferedImage img;
	private String status = "Starting...";
	private int startExp = Skills.AGILITY.getExperience();
	private int startLvl = Skills.AGILITY.getCurrentLevel();
	private Timer t = new Timer(0);
	
	private static final ObstacleCourse course = new GnomeCourse();
	
//------------------------------------Main Script Stuff Below------------------------------------
	@Override
	public int loop() {
		
		
		status = "Checking...";
		for (Obstacle obstacle : course.getObstacles()) {
			if (obstacle.needsSolving()) {
				status = "Solving: " + obstacle.getName();
				obstacle.performSolve();
				status = "Checking...";
			}
		}
		
		
		//log(Menu.getMenuOptions().contains("Walk-on"));
		return 200;
	}

	@Override
	public void onEnd() {
		log("Thank you for using my Agility Script.");
	}

	@Override
	public void onStart() {
		
		try {
			img = ImageIO.read(this.getClass().getResource("paint.png"));
		} catch (IOException e) {
			log("Image file could not be found.");
		}
		
		Camera.setPitch(true);
		log("Script Started.");
	}
	
	@Override
	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
		
		//Paint Image Drawing...
		g2d.drawImage(img, 3, 249, null);
		
		int expGained = Skills.AGILITY.getExperience() - startExp;
		int curLvl = Skills.AGILITY.getCurrentLevel();
		int lvlGained = Skills.AGILITY.getCurrentLevel() - startLvl;
		int targetExp = Skills.getExperienceAt(Skills.AGILITY.getCurrentLevel() + 1);
		int expTillLv = targetExp - Skills.AGILITY.getExperience();
		
		//First Column
		g2d.drawString(calcExp(), 139, 310);
		g2d.drawString(String.format("%,d", expGained), 153, 328);
		
		//Second Column
		g2d.drawString(status, 247, 310);
		g2d.drawString(curLvl + " (" + lvlGained + ")", 248, 328);
		
		//Third Column
		g2d.drawString(t.toElapsedString(), 445, 310);
		g2d.drawString(String.format("%,d", expTillLv), 424, 328);
		
		//Paint Image Drawing....
		
		// ----------------Mouse Drawing ----------------
		int x1 = (int)(Mouse.getLocation().getX() - 5);
		int x2 = (int)(Mouse.getLocation().getX() + 5);
		
		int y1 = (int)(Mouse.getLocation().getY());
		int y2 = (int)(Mouse.getLocation().getY());

		g2d.drawLine(x1, y1, x2, y2);
		
		x1 = (int)(Mouse.getLocation().getX());
		x2 = (int)(Mouse.getLocation().getX());
		
		y1 = (int)(Mouse.getLocation().getY() - 5);
		y2 = (int)(Mouse.getLocation().getY() + 5);
		
		g2d.drawLine(x1, y1, x2, y2);
		//---------------End Mouse Drawing----------------		
		
	}
//------------------------------------Main Script Stuff Above------------------------------------
	//Needed for paint
	private String calcExp() {
		double expGained = Skills.AGILITY.getExperience() - startExp;
		double secondsPassed = t.getElapsed() / 1000.0;
		double xpPerSec = expGained / secondsPassed;
		double xpPerHour = xpPerSec * 3600;
		
		return "" + String.format("%,.0f", xpPerHour);
	}
	
	//Conditional Sleep Override
	public static boolean sleep(Condition c, int timeout) {
        for (int i = 0; i < timeout / 100 && !c.isValid(); i++) {
            sleep(100);
        }
        return c.isValid();
    }
	
	//Getter for ObstacleCourse
	public static ObstacleCourse getCourse() {
		return course;
	}
}//End CLASS GnomeAgility
