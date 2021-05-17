package manager;

import java.util.ArrayList;

import model.base.Fish;
import model.base.Unit;
import model.fish.Guppy;

public class LevelManager {
	//TODO
	//Store Each Level Information
	//Level,StartMoney,StartFish,ShopDetail,InvasionDetail
	
	private Level level1_1 = new Level("Adventure",1,1);
	
	public void loadLevel1_1() {
		level1_1.setStartingMoney(200);
		
		ArrayList<Fish> startingFishList = new ArrayList<Fish>();
		startingFishList.add(new Guppy("Guppy", 0, 0));
		startingFishList.add(new Guppy("Guppy", 0, 0));
		level1_1.setStartingFish(startingFishList);
		
		Unit[] shopItem = new Unit[7];
		shopItem[0] = new Guppy("Guppy",0,0);
		shopItem[1] = null;
		shopItem[2] = null;
		shopItem[3] = null;
		shopItem[4] = null;
		shopItem[5] = null;
		shopItem[6] = null; //Goal
	}
}
