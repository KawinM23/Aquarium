package manager;

import java.util.ArrayList;

import model.base.Fish;
import model.base.Monster;
import model.base.Unit;

public class Level {
	// Level,StartMoney,StartFish,ShopDetail,InvasionDetail
	private String mode;
	private int[] id;

	private int startingMoney;
	private ArrayList<Fish> startingFish;

	private Unit[] shopItem;
	private int maxWeaponUpgrade;
	private int goalPrice;

	private ArrayList<ArrayList<Monster>> invasionMonster;
	private int[] invasionTime;

	public Level(String mode, int tankId, int levelId) {
		super();
		setMode(mode);
		setId(tankId, levelId);
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public int[] getId() {
		return id;
	}

	public void setId(int tank, int level) {
		this.id = new int[2];
		id[0] = tank;
		id[1] = level;
	}

	public int getStartingMoney() {
		return startingMoney;
	}

	public void setStartingMoney(int startingMoney) {
		this.startingMoney = startingMoney;
	}

	public ArrayList<Fish> getStartingFish() {
		return startingFish;
	}

	public void setStartingFish(ArrayList<Fish> startingFish) {
		this.startingFish = startingFish;
	}

	public Unit[] getShopItem() {
		return shopItem;
	}

	public void setShopItem(Unit[] shopItem) {
		this.shopItem = shopItem;
	}

	public ArrayList<ArrayList<Monster>> getInvasionMonster() {
		return invasionMonster;
	}

	public void setInvasionMonster(ArrayList<ArrayList<Monster>> invasionMonster) {
		this.invasionMonster = invasionMonster;
	}

	public int[] getInvasionTime() {
		return invasionTime;
	}

	public void setInvasionTime(int[] invasionTime) {
		this.invasionTime = invasionTime;
	}

	public int getGoalPrice() {
		return goalPrice;
	}

	public void setGoalPrice(int goalPrice) {
		this.goalPrice = goalPrice;
	}

	public int getMaxWeaponUpgrade() {
		return maxWeaponUpgrade;
	}

	public void setMaxWeaponUpgrade(int maxWeaponUpgrade) {
		this.maxWeaponUpgrade = maxWeaponUpgrade;
	}
}
