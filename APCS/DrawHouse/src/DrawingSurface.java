import java.util.ArrayList;

import processing.core.PApplet;

public class DrawingSurface extends PApplet {

	private House house;
	private Person person1;
	private Person person2;
	private float[] intersections;
	private float randomX;
	private float randomY;

	public DrawingSurface() {
		house = new House();
		person1 = new Person(70, 0, 36, 193, 127);
		person2 = new Person(230, 0, 0, 0, 255);
		randomX = random(-50, 50);
		randomY = random(-50, 50);
	}

	public void setup() {
		stroke(255);
		background(255);
	}

	public void draw() {
		boolean s1Intersects = false, s2Intersects = false; // if swords of persons 1 and 2 intersect other sword or other person's torso
		background(255);
		house.drawHouse(this);
		person1.drawPerson(this);
		person2.drawPerson(this);
		stroke(0, 0, 0);
		strokeWeight(1.f);
		if (person1.health > 0 && person2.health > 0) { // so long as the persons are alive
			fill(0);
			text("Objective: Kill the person with the blue sword!", 20, 10);
			text("Use arrow keys to move people,", 10, 25);
			text("u/d to scale house,", 45, 40);
			text("upper/lower case Rs Gs Bs and 0 for house color.", 45, 55);
			text("Hover mouse to move swords and click mouse to attack!", 10, 70);
			fill(255, 100, 50);
			textSize(14);
			text(person1.health, 35 + person1.getX(), 380);
			text(person2.health, 35 + person2.getX(), 380);
			
			// create new Line objects as swords
			Line s1 = new Line(70 + person1.getX(), 280 + person1.getY(), mouseX, mouseY);
			Line s2 = new Line(30 + person2.getX(), 280 + person2.getY(), mouseX + randomX, mouseY + randomY);
			strokeWeight(2.f);
			stroke(36, 193, 127);
			// create new Line objects as the limit to each sword's horizontal reach based on person's position
			Line e1 = new Line(160 + person1.getX(), 0, 160 + person1.getX(), 400);
			Line e2 = new Line(person2.getX() - 60, 0, person2.getX() - 60, 400);
			
			// person1's sword limitations
			if (s1.intersects(e1) != null) { // if sword is touching its limit line
				s1Intersects = true;
				person1.reachSword(this, s1.intersects(e1)[0], s1.intersects(e1)[1]); // have the sword reach only to the limit line
			} else if (mouseX > 0 && mouseY > 0){ // if cursor on screen
				person1.reachSword(this, mouseX, mouseY);
			} else {
				person1.reachSword(this, 70, 230);
			}
			
			// person2's sword limitations
			if (s2.intersects(e2) != null) {			
				s2Intersects = true;
				person2.reachSword(this, s2.intersects(e2)[0], s2.intersects(e2)[1]);
			} else if (mouseX > 0 && mouseY > 0){
				person2.reachSword(this, person2.getX(), 230 + person2.getY());
			} else {
				person2.reachSword(this, 250, 230);
			}


			if (keyPressed) {
				// move person in cardinal directions
				if (keyCode == 37) {
					person1.movePerson(-5, 0);
					person2.movePerson((int) random(-3, -1), 0);
				}
				if (keyCode == 39) {
					person1.movePerson(5, 0);
					person2.movePerson((int) random(1, 3), 0);
				}

				// scale house up and down
				if (key == 'u' || key == 'U') {
					house.scaleHouse(1.05f);
				}
				if (key == 'd' || key == 'D') {
					house.scaleHouse(0.95f);
				}

				// shift rgb values up and down
				if (key == 'r') {
					house.changeColor(-5, 0, 0);
				}
				if (key == 'R') {
					house.changeColor(5, 0, 0);
				}
				if (key == 'g') {
					house.changeColor(0, -5, 0);
				}
				if (key == 'G') {
					house.changeColor(0, 5, 0);
				}
				if (key == 'b') {
					house.changeColor(0, 0, -5);
				}
				if (key == 'B') {
					house.changeColor(0, 0, 5);
				}
				if (key == '0') {
					house.changeColor(0, 0, 0);
				}
			}
			if (mousePressed) {
				stroke(200, 100, 50);
				if (person1.sword.intersects(person2.sword) != null) {
					strokeWeight(1.5f);
					// draw plus sign at intersection point
					line(person1.sword.intersects(person2.sword)[0], person1.sword.intersects(person2.sword)[1], person1.sword.intersects(person2.sword)[0] + 5, person1.sword.intersects(person2.sword)[1] + 0);
					line(person1.sword.intersects(person2.sword)[0], person1.sword.intersects(person2.sword)[1], person1.sword.intersects(person2.sword)[0] - 5, person1.sword.intersects(person2.sword)[1] - 0);
					line(person1.sword.intersects(person2.sword)[0], person1.sword.intersects(person2.sword)[1], person1.sword.intersects(person2.sword)[0] + 0, person1.sword.intersects(person2.sword)[1] - 5);
					line(person1.sword.intersects(person2.sword)[0], person1.sword.intersects(person2.sword)[1], person1.sword.intersects(person2.sword)[0] - 0, person1.sword.intersects(person2.sword)[1] + 5);
					person2.health++; // increment defender's health	
				}
				if (person2.sword.intersects(person1.sword) != null) {
					strokeWeight(1.5f);
					// draw plus sign at intersection point
					line(person2.sword.intersects(person1.sword)[0], person2.sword.intersects(person1.sword)[1], person2.sword.intersects(person1.sword)[0] + 5, person2.sword.intersects(person1.sword)[1] + 0);
					line(person2.sword.intersects(person1.sword)[0], person2.sword.intersects(person1.sword)[1], person2.sword.intersects(person1.sword)[0] - 5, person2.sword.intersects(person1.sword)[1] - 0);
					line(person2.sword.intersects(person1.sword)[0], person2.sword.intersects(person1.sword)[1], person2.sword.intersects(person1.sword)[0] + 0, person2.sword.intersects(person1.sword)[1] - 5);
					line(person2.sword.intersects(person1.sword)[0], person2.sword.intersects(person1.sword)[1], person2.sword.intersects(person1.sword)[0] - 0, person2.sword.intersects(person1.sword)[1] + 5);
					person1.health++; // increment defender's health
				}
				stroke(255, 0, 0);
				if (person1.sword.intersects(person2.torso) != null) {
					ellipse(person1.sword.intersects(person2.torso)[0], person1.sword.intersects(person2.torso)[1], 10, 10); // draw wound
					person2.health--; // decrement victim's health
				}
				if (person2.sword.intersects(person1.torso) != null) {
					ellipse(person2.sword.intersects(person1.torso)[0], person2.sword.intersects(person1.torso)[1], 10, 10); // draw wound
					person1.health--; // decrement victim's health
				}
			}
		} 
		// when either person dies
		else if (person1.health <= 0) {
			fill(255, 100, 50);
			textSize(28);
			text("Person 2 has won!", 75, 50);
		} else if (person2.health <= 0) {
			fill(255, 100, 50);
			textSize(28);
			text("Person 1 has won!", 75, 50);
		}
	}
}
