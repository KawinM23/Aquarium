package manager;

public class GameManager {
	private static int WIDTH = (int) (640*1.5);
	private static int HEIGHT = (int) (480*1.5);
	private static int BOTTOMHEIGHT = HEIGHT - 30;
	
	private static int FRAMERATE = 50;
	
	private static boolean LOG = true;
	
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
	public static boolean isLOG() {
		return LOG;
	}
	
	
	
	
	
}
