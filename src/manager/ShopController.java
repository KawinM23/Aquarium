package manager;

import java.util.ArrayList;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import manager.base.Gun;
import manager.base.Level;
import manager.base.Trophy;
import model.Food;
import model.base.Fish;
import model.base.Unit;
import model.fish.Beetlemuncher;
import model.fish.Breeder;
import model.fish.Carnivore;
import model.fish.Guppy;
import model.fish.Guppycruncher;
import model.fish.Starcatcher;
import model.fish.Ultravore;

public class ShopController {
	// TODO get money from Money class
	static final int SHOP_HEIGHT = 101;
	static Stage stage;
	static final String MENU_IMAGE_PATH = ClassLoader.getSystemResource("menubar3.jpg").toString();
	static final String GUPPY_IMAGE_PATH = ClassLoader.getSystemResource("Guppy.png").toString();
	static int[] prices;
	static final Image guppyImage = new Image(GUPPY_IMAGE_PATH);
	static Image menuImage = new Image(MENU_IMAGE_PATH);
	static Image[] images;
	static String[] imagePaths;
	static Unit[] shopItems;
	static Button button1 = new Button("1");
	static Button button2 = new Button("2");
	static Button button3 = new Button("3");
	static Button button4 = new Button("4");
	static Button button5 = new Button("5");
	static Button button6 = new Button("6");
	static Button button7 = new Button("7");
	static Button button8 = new Button("8");
	static Button[] buttonList = { button1, button2, button3, button4, button5, button6, button7, button8 };

	// { XOfTopLeft, YOfTopLeft, XOfBottomRight, YOfBottomRight, borderRadius,
	// FontSize }
	private static final double[][] BUTTON_DETAIL = { { 22.0, 3.0, 72.0, 46.0, 100.0, 20.0 },
			{ 91.0, 3.0, 141.0, 46.0, 80.0, 20.0 }, { 148.0, 3.0, 198.0, 46.0, 80.0, 20.0 },
			{ 221.0, 3.0, 271.0, 46.0, 100.0, 20.0 }, { 295.0, 3.0, 344.0, 46.0, 80.0, 20.0 },
			{ 367.0, 3.0, 417.0, 46.0, 80.0, 20.0 }, { 440.0, 3.0, 490.0, 46.0, 80.0, 20.0 },
			{ 532.0, 3.0, 617.0, 25.0, 80.0, 20.0 } };

	// TODO This one is a testing method for calling in Level as parameter
	public static void setShopDetail(Level level) {
		shopItems = level.getShopItem();
		// TODO Get details from Level and LevelManager instead
		// TODO NULL POINTER of getshop
		int foodTypePrice = 200;
		int maxFoodPrice = 300;
		Image foodTypeImage = Food.getStaticImage(2);
		Image gunImage = Gun.getGunImage(2);
		int weaponPrice = 1000;
		if (!level.isFoodUpgradable()) {
			foodTypePrice = 0;
			maxFoodPrice = 0;
			foodTypeImage = null;
		}
		if (!level.isWeaponUpgradable()) {
			gunImage = null;
			weaponPrice = 0;
		}
		prices = new int[] { getUnitPrice(level.getShopItem()[0]), foodTypePrice, maxFoodPrice,
				getUnitPrice(level.getShopItem()[1]), getUnitPrice(level.getShopItem()[2]), weaponPrice,
				level.getGoalPrice() };
		// TODO Get Gun Images Later (Right now is null) + Get Goal Egg Image
		images = new Image[] { getUnitImage(level.getShopItem()[0]), foodTypeImage, null,
				getUnitImage(level.getShopItem()[1]), getUnitImage(level.getShopItem()[2]), gunImage,
				Trophy.getGoalImage(1) };
		setStartingButtonsVisibility(level);
	}

	// TODO Add All button
	public static void setAllButtons(AnchorPane anchorpane) {
		// Clear Previous Buttons
		clearShop(anchorpane);

		// Add in new Buttons
		for (Button eachButton : buttonList) {
			setButtonProperty(anchorpane, eachButton);
		}
	}

	public static void setButtonProperty(AnchorPane anchorpane, Button button) {
		int buttonNumber = Integer.parseInt(button.getText());
		button.setPrefSize((BUTTON_DETAIL[buttonNumber - 1][2] - BUTTON_DETAIL[buttonNumber - 1][0]) * 1.5,
				(BUTTON_DETAIL[buttonNumber - 1][3] - BUTTON_DETAIL[buttonNumber - 1][1]) * 1.5);
		button.setStyle("-fx-background-radius: " + BUTTON_DETAIL[buttonNumber - 1][4] + "px;"
				+ "-fx-border-color: transparent;" + "-fx-background-color: transparent;" + "-fx-text-fill: null");
//		button.setStyle("-fx-background-radius: " + BUTTON_DETAIL[buttonNumber][4] + "px;");
//			button.setBorder(null);
//			button.setBackground(null);
		button.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {

			}
		});

		AnchorPane.setTopAnchor(button, BUTTON_DETAIL[buttonNumber - 1][1] * 1.5);
		AnchorPane.setLeftAnchor(button, BUTTON_DETAIL[buttonNumber - 1][0] * 1.5);

		button.hoverProperty().addListener((event) -> {

		});

		button.setOnMouseEntered(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent mouseEvent) {

			}
		});
		button.setOnMouseExited(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent mouseEvent) {

			}
		});
		button.setOnMousePressed(new EventHandler<MouseEvent>() {
			// TODO Set handlers to be in sync with Unit ShopItem
			@Override
			public void handle(MouseEvent mouseEvent) {
				if (!PlayerController.isPause()) {

					switch (buttonNumber) {
					// Guppy Fish
					case 1:
						if (PlayerController.buy(prices[0])) {
							if (shopItems[0] instanceof Breeder) {
								TankManager.addNewFish(new Breeder("Breeder", 0, 0));
							} else if (shopItems[0] instanceof Guppy) {
								TankManager.addNewFish(new Guppy("Guppy", 0, 0));
							}
							SoundManager.playClickSound();
						}
						break;
					// Food Type
					case 2:
						// If enough money AND Food Level not max
						if (PlayerController.buy(prices[1]) && PlayerController.getFoodLevel() <= 2 && prices[1] != 0) {
							PlayerController.setFoodLevel(PlayerController.getFoodLevel() + 1);
							if (PlayerController.getFoodLevel() == 3) {
								prices[1] = -1;
								button.setVisible(false);
							}
							SoundManager.playClickSound();
							Thread thread = new Thread(() -> {
								try {
									images[1] = Food.getStaticImage(PlayerController.getFoodLevel() + 1);
									Platform.runLater(new Runnable() {
										@Override
										public void run() {
											// TODO Auto-generated method stub

										}
									});
								} catch (Exception e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}

							});
							thread.start();
						}
						break;
					// Food Capacity
					case 3:
						if (prices[2] == 0) {
							button.setVisible(false);
						}
						if (PlayerController.buy(prices[2]) && PlayerController.getFoodLevel() <= 9) {
							if (button.isVisible())
								SoundManager.playClickSound();
							PlayerController.setMaxFood(PlayerController.getMaxFood() + 1);
							if (PlayerController.getMaxFood() == 10) {
								prices[2] = -1;
								button.setVisible(false);
							}
						}
						break;
					// Special 1
					case 4:
						if (PlayerController.buy(prices[3])) {
							SoundManager.playClickSound();
							// If it is a potion
							if (shopItems[1] instanceof Food) {
								PlayerController.setPotion(true);
							}
							// If it is a carnivore
							else if (shopItems[1] instanceof Carnivore) {
								Carnivore fish = new Carnivore("Carnivore", 0, 0);
								TankManager.addNewFish(fish);
							}
							// If it is a Guppy Crusher
							else if (shopItems[1] instanceof Guppycruncher) {
								Guppycruncher fish = new Guppycruncher("Cruncher", 0, 0);
								TankManager.addNewFish(fish);
							}
						}
						break;
					// Special 2
					case 5:
						if (PlayerController.buy(prices[4])) {
							SoundManager.playClickSound();
							if (shopItems[2] instanceof Starcatcher) {
								Starcatcher fish = new Starcatcher("Starcatcher", 0, 0);
								TankManager.addNewFish(fish);
							} else if (shopItems[2] instanceof Beetlemuncher) {
								Beetlemuncher fish = new Beetlemuncher("Muncher", 0, 0);
								TankManager.addNewFish(fish);
							} else if (shopItems[2] instanceof Ultravore) {
								Ultravore fish = new Ultravore("Ultravore", 0, 0);
								TankManager.addNewFish(fish);
							}
						}
						break;
					// Gun
					case 6:
						if (PlayerController.buy(prices[5])) {
							SoundManager.playClickSound();
							PlayerController.setGunLevel(PlayerController.getGunLevel() + 1);
							images[5] = Gun.getGunImage(PlayerController.getGunLevel() + 1);
							// TODO Change image to new gun images
							// If MAXED
							if (PlayerController.getGunLevel() == 10) {
								prices[5] = -1;
								button.setVisible(false);
							}

						}
						break;
					// Goal
					case 7:
						if (PlayerController.buy(prices[6])) {
							SoundManager.playClickSound();
							PlayerController.buyGoal();
							images[6] = Trophy.getGoalImage(PlayerController.getGoal() + 1);
						}
						break;
					// Menu
					case 8:
						SoundManager.playClickSound();
//					SceneController.changeScene("menu");
//					SoundManager.playMainMenuBgm();
						PlayerController.togglePause();
						break;
					default:

					}
				}

			}
		});
		button.setOnMouseReleased(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent mouseEvent) {

			}

		});

		anchorpane.getChildren().addAll(button);
	}

	// TODO Draw Shop Ui
	public static void drawShop(GraphicsContext gc) {
		// Draw the UI Bar

		DrawManager.drawImageFixSize(gc, menuImage, 0, 0, GameManager.getWIDTH(), SHOP_HEIGHT);

		// Draw the images
		for (int i = 0; i < 7; i++) {
			// Draw Images

			if (prices[i] != 0 && prices[i] != -1) {
				DrawManager.drawOval(gc, (int) (BUTTON_DETAIL[i][0] * 1.5), (int) (BUTTON_DETAIL[i][1] * 1.5),
						(int) (getButtonWidth(i + 1) * 1.5 + 1), (int) (getButtonHeight(i + 1) * 1.5 + 5));
			}

			DrawManager.drawImageFixSize(gc, images[i], (int) (BUTTON_DETAIL[i][0] * 1.5),
					(int) (BUTTON_DETAIL[i][1] * 1.5), getButtonWidth(i + 1) * 1.5, getButtonHeight(i + 1) * 1.5);

			// Draw Prices
			String priceText = "" + prices[i];
			int relay = priceText.length() * 3;

			if ((PlayerController.getGunLevel() == 10 || prices[5] == 0) && i == 5) {
				priceText = "";
			}

			if (prices[i] == -1) {
				priceText = "MAX";
				relay += 10;
			}
			if (shopItems[1] == null && i == 3)
				priceText = "";
			if (shopItems[2] == null && i == 4)
				priceText = "";
			if (prices[1] == 0 && i == 1)
				priceText = "";
			if (prices[2] == 0 && i == 2)
				priceText = "";

			DrawManager.drawText(gc, priceText, 18,
					(int) ((BUTTON_DETAIL[i][0] + getButtonWidth(i + 1) / 2 - relay) * 1.5),
					(int) ((BUTTON_DETAIL[i][3] * 1.5) + 19),
					(int) ((BUTTON_DETAIL[i][2] - BUTTON_DETAIL[i][0]) * 1.5));
		}

		// Draw Max Food Number
		String maxFoodString = "" + (PlayerController.getMaxFood() + 1);
		if (prices[2] == 0) {
			maxFoodString = "";
		}
		if (PlayerController.getMaxFood() == 10) {
			maxFoodString = "";
		}
		DrawManager.drawText(gc, maxFoodString, 40, (int) (BUTTON_DETAIL[2][0] * 1.5 + 25),
				(int) (BUTTON_DETAIL[2][3] * 1.5 - 15), (int) (((BUTTON_DETAIL[2][2] - BUTTON_DETAIL[2][0])) * 1.5));

		// Draw current money
		DrawManager.drawText(gc, "" + PlayerController.getMoney(), 24,
				(int) ((BUTTON_DETAIL[7][0] + getButtonWidth(7 + 1) / 2 - getDigit(PlayerController.getMoney()) * 5)
						* 1.5 + 10),
				(int) ((BUTTON_DETAIL[7][3] * 1.5) + 45), (int) ((BUTTON_DETAIL[7][2] - BUTTON_DETAIL[7][0]) * 1.5));

	}

	public static int getUnitPrice(Unit unit) {
		if (unit == null)
			return 0;
		else if (unit instanceof Fish) {
			return ((Fish) unit).getPrice();
		} else if (unit instanceof Food) {
			// 1 = Normal Food
			if (((Food) unit).getFoodType() == 1) {
				return 200;
			}
			// 2 = Green Potion
			else if (((Food) unit).getFoodType() == 2) {
				return 250;
			}
		}
		return 0;
	}

	// TODO Get image from units
	public static Image getUnitImage(Unit unit) {
		if (unit == null)
			return null;
		else if (unit instanceof Fish) {
			return ((Fish) unit).getImage();
		} else if (unit instanceof Food) {
			// 1 = Normal Food
			if (((Food) unit).getFoodType() == 1) {
				return ((Food) unit).getImage(((Food) unit).getFoodLevel());
			}
			// 2 = Green Potion
			else if (((Food) unit).getFoodType() == 2) {
				return Food.getPotionImage();
			}
		}
		return null;
	}

	public static void setStartingButtonsVisibility(Level level) {
		// Guppy
		buttonList[0].setVisible(true);
		// Food Type, Max Food
		if (level.isFoodUpgradable()) {
			buttonList[1].setVisible(true);
			buttonList[2].setVisible(true);
		} else if (!level.isFoodUpgradable()) {
			buttonList[1].setVisible(false);
			buttonList[2].setVisible(false);
		}
		// Special 1
		if (level.getShopItem()[1] != null) {
			buttonList[3].setVisible(true);
		} else
			buttonList[3].setVisible(false);

		// Special 2
		if (level.getShopItem()[2] != null) {
			buttonList[4].setVisible(true);
		} else
			buttonList[4].setVisible(false);

		// Gun
		if (level.isWeaponUpgradable()) {
			buttonList[5].setVisible(true);
		} else
			buttonList[5].setVisible(false);

		// Goal
		buttonList[6].setVisible(true);
		// Menu
		buttonList[7].setVisible(true);
	}

	public static void clearShop(AnchorPane anchorpane) {
		ArrayList<Node> toBeRemovedNodes = new ArrayList<Node>();
		for (Node node : anchorpane.getChildren()) {
			if (node instanceof Button) {
				try {
					if (Integer.parseInt(((Button) node).getText()) >= 1
							&& Integer.parseInt(((Button) node).getText()) <= 8) {
						toBeRemovedNodes.add(node);
					}
				} catch (Exception e) {

				}

			}
		}
		for (Node node : toBeRemovedNodes) {
			anchorpane.getChildren().remove(node);
		}
	}

	public void setPrice(int ButtonNumber, int newPrice) {
		prices[ButtonNumber - 1] = newPrice;
	}

	private static int getButtonWidth(int buttonNumber) {
		return (int) (BUTTON_DETAIL[buttonNumber - 1][2] - BUTTON_DETAIL[buttonNumber - 1][0]);
	}

	private static int getButtonHeight(int buttonNumber) {
		return (int) (BUTTON_DETAIL[buttonNumber - 1][3] - BUTTON_DETAIL[buttonNumber - 1][1]);
	}

	private static int getDigit(int n) {
		String str = String.valueOf(n);
		return str.length();
	}

	public Button getButton(int buttonNumber) {
		return buttonList[buttonNumber - 1];
	}

}
