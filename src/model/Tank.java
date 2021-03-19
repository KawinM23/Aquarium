package model;

import java.util.ArrayList;

public class Tank {
	private ArrayList<Fish> fishList;
	private ArrayList<Coin> coinList;
	private ArrayList<Food> foodList;
	private ArrayList<Monster> monsterList;
	
	public Tank() {
		this.setFishList(new ArrayList<Fish>());
		this.setCoinList(new ArrayList<Coin>());
		this.setFoodList(new ArrayList<Food>());
		this.setMonsterList(new ArrayList<Monster>());
	}
	
	
	
	
	
	
	public ArrayList<Fish> getFishList() {
		return fishList;
	}
	public void setFishList(ArrayList<Fish> fishList) {
		this.fishList = fishList;
	}
	public ArrayList<Coin> getCoinList() {
		return coinList;
	}
	public void setCoinList(ArrayList<Coin> coinList) {
		this.coinList = coinList;
	}
	public ArrayList<Food> getFoodList() {
		return foodList;
	}
	public void setFoodList(ArrayList<Food> foodList) {
		this.foodList = foodList;
	}

	public ArrayList<Monster> getMonsterList() {
		return monsterList;
	}
	public void setMonsterList(ArrayList<Monster> monsterList) {
		this.monsterList = monsterList;
	}
	
	public String toString() {
		
		return fishList.toString();
	}
	
	
}
