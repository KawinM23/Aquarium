package game;

import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;

import model.Tank;
import properties.Hunger;
import properties.Position;
import model.Fish;
import model.Food;
import model.Goldfish;

public class Main {
	public static void main(String[] args) {
		Timer timer = new Timer();
		Timer tankTimer = new Timer("Tank Timer");
		Tank myTank = new Tank();
		
		Goldfish Goldfish1 = new Goldfish("Goldfish1", new Position(0,0),new Hunger());
		Goldfish1.getHunger().setHungry(true);
		Goldfish1.setMoving(false);
		Goldfish Goldfish2 = new Goldfish("Goldfish2", new Position(1,1),new Hunger());
		
		
		Food food1 = new Food(new Position(10,10));
		
		myTank.getFishList().add(Goldfish1);
		myTank.getFoodList().add(food1);
		//myTank.getFishList().add(Goldfish2);
		
		System.out.println(myTank.toString());
		
		TimerTask addFish = new TimerTask(){
			public void run() {
				Goldfish Goldfish3 = new Goldfish("Goldfish3", new Position(-1,-1),new Hunger());
				myTank.getFishList().add(Goldfish3);
			}
		};
		
		TimerTask check = new TimerTask(){
			public void run() {
				System.out.println(System.currentTimeMillis() + myTank.toString());
			}
		};
		
		TimerTask run = new TimerTask(){
			public void run() {
				for(Fish ef : myTank.getFishList()) {
					ef.CheckHunger(myTank);
				}
			}
		};
		//Goldfish1.Move();
		System.out.println(myTank.toString());
		
		tankTimer.schedule(run, 10L, 20L);
	    timer.scheduleAtFixedRate(check, 10L, 20L);
	    timer.schedule(addFish, 3000L);
	    
	    
		
	
		
	}
}
