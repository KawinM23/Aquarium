package manager;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import model.Food;
import model.base.Fish;
import model.base.Unit;
import model.fish.Beetlemuncher;
import model.fish.Carnivore;
import model.fish.Guppy;
import model.fish.Guppycruncher;
import model.fish.Starcatcher;
import model.fish.Ultravore;
import model.money.Beetle;
import template.pause1;

public class ShopController {
	// TODO get money from Money class
	static final int shopHeight = 113;
	static Stage stage;
	static final String MENU_IMAGE_PATH = ClassLoader.getSystemResource("menubar.jpg").toString();
	static final String GUPPY_IMAGE_PATH = ClassLoader.getSystemResource("Guppy.png").toString();
	static int[] prices;
	private static int foodLevelPrice = 300;
	private static int foodCountPrice = 200;
	private static int weaponUpgradePrice = 1000;
	static final Image guppyImage = new Image(GUPPY_IMAGE_PATH);
	static final Image menuImage = new Image(MENU_IMAGE_PATH);
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
	private static final double[][] buttonDetail = { { 22.0, 3.0, 72.0, 46.0, 100.0, 20.0 },
			{ 91.0, 3.0, 141.0, 46.0, 80.0, 20.0 }, { 148.0, 3.0, 198.0, 46.0, 80.0, 20.0 },
			{ 221.0, 3.0, 271.0, 46.0, 100.0, 20.0 }, { 295.0, 3.0, 344.0, 46.0, 80.0, 20.0 },
			{ 367.0, 3.0, 417.0, 46.0, 80.0, 20.0 }, { 440.0, 3.0, 490.0, 46.0, 80.0, 20.0 },
			{ 532.0, 3.0, 617.0, 25.0, 80.0, 20.0 } };

	// TODO This one is a testing method for calling in Level as parameter
	public static void setShopDetail(Level level) {
		shopItems = level.getShopItem();
		// TODO Get details from Level and LevelManager instead
		prices = new int[] { getUnitPrice(level.getShopItem()[0]), 200, 300, getUnitPrice(level.getShopItem()[1]),
				getUnitPrice(level.getShopItem()[2]), 1000, level.getGoalPrice() };
		// TODO Get Gun Images Later (Right now is null) + Get Goal Egg Image
		images = new Image[] { getUnitImage(level.getShopItem()[0]), Food.getStaticImage(1), null,
				getUnitImage(level.getShopItem()[1]), getUnitImage(level.getShopItem()[2]), null, null };
	}

	public static void setShopDetail(int tankNumber) {
		// TODO Get details from Level and LevelManager instead
		switch (tankNumber) {
		case 0:
			// TODO Prices ; Prices of {Button1, Button4, Button5, Button7}
			// Fish Fish2 Fish3 Goal
			prices = new int[] { 100, 1000, 1000, 2000 };
			images = new Image[] { guppyImage, guppyImage, guppyImage, guppyImage, guppyImage, guppyImage, guppyImage,
					guppyImage };
			break;
		case 1:
			break;
		case 2:
			prices = new int[] { 0, 0, 0, 0, 0, 0, 0, 0 };
			break;
		case 3:
			prices = new int[] { 0, 0, 0, 0, 0, 0, 0, 0 };
			break;
		default:
		}
	}

	// TODO Add All button
	public static void setAllButtons(AnchorPane anchorpane) {
		for (Button eachButton : buttonList) {
			setButtonProperty(anchorpane, eachButton);
		}
	}

	public static void setButtonProperty(AnchorPane anchorpane, Button button) {
		int buttonNumber = Integer.parseInt(button.getText());
		button.setPrefSize((buttonDetail[buttonNumber - 1][2] - buttonDetail[buttonNumber - 1][0]) * 1.5,
				(buttonDetail[buttonNumber - 1][3] - buttonDetail[buttonNumber - 1][1]) * 1.5);
		button.setStyle("-fx-background-radius: " + buttonDetail[buttonNumber - 1][4] + "px;"
				+ "-fx-border-color: transparent;" + "-fx-background-color: transparent;" + "-fx-text-fill: null");
//		button.setStyle("-fx-background-radius: " + buttonDetail[buttonNumber][4] + "px;");
//			button.setBorder(null);
//			button.setBackground(null);
		button.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {

			}
		});

		AnchorPane.setTopAnchor(button, buttonDetail[buttonNumber - 1][1] * 1.5);
		AnchorPane.setLeftAnchor(button, buttonDetail[buttonNumber - 1][0] * 1.5);

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
					SoundManager.playClickSound();

					switch (buttonNumber) {
					// Guppy Fish
					case 1:
						if (PlayerController.buy(prices[0])) {
							TankManager.addNewFish(new Guppy("Guppy", 0, 0));
						}
						break;
					// Food Type
					case 2:
						if (PlayerController.buy(prices[1])) {
							PlayerController.setFoodLevel(PlayerController.getFoodLevel() + 1);

							Thread thread = new Thread(() -> {
								try {
									images[1] = Food.getStaticImage(PlayerController.getFoodLevel());
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
							System.out.println(PlayerController.getFoodLevel());
						}
						break;
					// Food Capacity
					case 3:
						if (PlayerController.buy(prices[2])) {
							PlayerController.setMaxFood(PlayerController.getMaxFood() + 1);
							System.out.println(PlayerController.getMaxFood());
						}
						break;
					// Special 1
					case 4:
						if (PlayerController.buy(prices[3])) {
							// If it is a potion
							if (shopItems[1] instanceof Food) {
								Food food = new Food("Food", 0, 0, PlayerController.getFoodLevel());
								TankManager.addFood(food);
								System.out.println(TankManager.getFoodList());
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
							PlayerController.setGunLevel(PlayerController.getGunLevel() + 1);
							// TODO Change image to new gun images
						}
						break;
					// Goal
					case 7:
						if (PlayerController.buy(prices[6])) {
							PlayerController.buyGoal();
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

		DrawManager.drawImageFixSize(gc, menuImage, 0, 0, GameManager.getWIDTH(), shopHeight);
		// Draw the images
		for (int i = 0; i < 7; i++) {
			// Draw Images
			DrawManager.drawImageFixSize(gc, images[i], (int) (buttonDetail[i][0] * 1.5),
					(int) (buttonDetail[i][1] * 1.5), getButtonWidth(i + 1) * 1.5, getButtonHeight(i + 1) * 1.5);
			// Draw Prices
			DrawManager.drawText(gc, "" + prices[i], 18,
					(int) ((buttonDetail[i][0] + getButtonWidth(i + 1) / 2 - getDigit(prices[i]) * 3) * 1.5),
					(int) ((buttonDetail[i][3] * 1.5) + 16), (int) ((buttonDetail[i][2] - buttonDetail[i][0]) * 1.5));
		}
		// Draw current money
		DrawManager.drawText(gc, "" + PlayerController.getMoney(), 24,
				(int) ((buttonDetail[7][0] + getButtonWidth(7 + 1) / 2 - getDigit(PlayerController.getMoney()) * 5)
						* 1.5 + 10),
				(int) ((buttonDetail[7][3] * 1.5) + 45), (int) ((buttonDetail[7][2] - buttonDetail[7][0]) * 1.5));

	}

	public static int getUnitPrice(Unit unit) {
		if (unit instanceof Fish) {
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
		if (unit instanceof Fish) {
			return ((Fish) unit).getImage();
		} else if (unit instanceof Food) {
			// 1 = Normal Food
			if (((Food) unit).getFoodType() == 1) {
				return ((Food) unit).getImage(((Food) unit).getFoodLevel());
			}
			// 2 = Green Potion
			else if (((Food) unit).getFoodType() == 2) {
				return ((Food) unit).getImage(((Food) unit).getFoodLevel());
			}
		}
		return null;
	}

	public void setPrice(int ButtonNumber, int newPrice) {
		this.prices[ButtonNumber - 1] = newPrice;
	}

	private static int getButtonWidth(int buttonNumber) {
		return (int) (buttonDetail[buttonNumber - 1][2] - buttonDetail[buttonNumber - 1][0]);
	}

	private static int getButtonHeight(int buttonNumber) {
		return (int) (buttonDetail[buttonNumber - 1][3] - buttonDetail[buttonNumber - 1][1]);
	}

	private static int getDigit(int n) {
		String str = String.valueOf(n);
		return str.length();
	}

	public static int getFoodLevelPrice() {
		return foodLevelPrice;
	}

	public static int getFoodCountPrice() {
		return foodCountPrice;
	}

	public static int getWeaponUpgradePrice() {
		return weaponUpgradePrice;
	}

	public Button getButton(int buttonNumber) {
		return buttonList[buttonNumber - 1];
	}

}
