package manager;

public class GameManager {
	private static int WIDTH = (int) (640*1.5);
	private static int HEIGHT = (int) (480*1.5);
	private static int BOTTOMHEIGHT = HEIGHT - 20;
	
	private static int FRAMERATE = 50;
	
	
	public static int getWIDTH() {
		return WIDTH;
	}
	public static int getHEIGHT() {
		return HEIGHT;
	}
	
	public static int getBOTTOMHEIGHT() {
		return BOTTOMHEIGHT;
	}
	public static int getFRAMERATE() {
		return FRAMERATE;
	}
	
	
	
}
