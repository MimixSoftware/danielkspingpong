package com.danielkspingpong.danielkspingpong;

import com.danielkspingpong.danielkspingpong.controller.GameController;
import com.danielkspingpong.danielkspingpong.model.*;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.*;
import java.net.URL;
import java.util.Optional;

/**
 * The main Game class which initialises all game entities and handles core functionality.
 */
public class Game extends Application implements Serializable
{
    /**
     * The name of the settings file.
     */
    private static final String SETTINGS_FILENAME = "settings.ser";
    /**
     * The width of the stage.
     */
    private static int width = 800;
    /**
     * The height of the stage.
     */
    private static int height = 600;
    /**
     * A point is currently being played.
     */
    private boolean pointActive;
    /**
     * The current game has ended.
     */
    private boolean gameEnded;
    /**
     * The current game has been paused.
     */
    private boolean gamePaused;
    /**
     * The amount of times the ball has collided with either player in the current point.
     */
    private int pointBallBounces;
    /**
     * The interface for settings.
     */
    private SettingsInterface settings;
    /**
     * The interface for the ball.
     */
    private BallInterface ball;
    /**
     * The interface for player 1.
     */
    private PlayerInterface player1;
    /**
     * The interface for player 2.
     */
    private PlayerInterface player2;
    /**
     * The game logic controller.
     */
    private GameController gameController;

    /**
     * Serves as an entry-point of the application and sets the stage icon, initialises game entities and shows the main menu.
     * @param stage The stage that will be used.
     */
    @Override
    public void start(Stage stage)
    {
        URL iconUrl = this.getClass().getResource("Icon.png");
        Image icon = new Image(iconUrl.toExternalForm());
        stage.getIcons().add(icon);

        //Initialisation of Game Entities

        settings = new Settings();
        settings = settings.loadSettings(SETTINGS_FILENAME);
        stage.setOnCloseRequest(e -> {
            settings.saveSettings(SETTINGS_FILENAME);
        });

        ball = new Ball(width / 2.0, height / 2.0, settings.getInitialBallXSpeed(), settings.getInitialBallYSpeed());
        player1 = new Player(settings.getPlayer1DefaultName(), 60, height / 2.0, 0);
        player2 = new Player(settings.getPlayer2DefaultName(), width - 60 - settings.getPlayerWidth(), height / 2.0, 0);
        gameController = new GameController(settings, ball, player1, player2);

        showMenu(stage);
    }

    /**
     * Renders the main menu by creating all the components and displaying them in the menu scene.
     * @param stage The stage that will be used.
     */
    private void showMenu(Stage stage)
    {
        stage.setTitle("Menu - Daniel K's Ping Pong");

        //Logo
        URL imageUrl = this.getClass().getResource("Controls.png");
        Image image = new Image(imageUrl.toExternalForm());
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(146);
        imageView.setFitWidth(499);

        //Saving/Loading
        Text savingLoadingText = new Text("Shift+S - Save Game      Shift+L - Load Game     Shift+Y - Save to Database      Shift+N - Load from Database");
        savingLoadingText.setFont(Font.font("Arial", 24));

        Separator separator1 = new Separator();

        // Main Menu
        Button startButton = new Button("Start Game");
        startButton.setOnAction(e -> startGame(stage));
        startButton.setPrefSize(120, 40);

        Button changePlayerOneNameButton = new Button("Change Player 1 Name");
        changePlayerOneNameButton.setOnAction(e -> changePlayer1Name());

        Button changePlayerTwoNameButton = new Button("Change Player 2 Name");
        changePlayerTwoNameButton.setOnAction(e -> changePlayer2Name());

        Button setBallSpeedButton = new Button("Set Ball Speed");
        setBallSpeedButton.setOnAction(e -> changeBallSpeed());

        Button adjustRacketDimensionsButton = new Button("Adjust Racket Dimensions");
        adjustRacketDimensionsButton.setOnAction(e -> changeRacketDimensions());

        Button setEndScoreButton = new Button("Set End Score");
        setEndScoreButton.setOnAction(e -> changeEndScore());

        Spinner<Integer> ballSpeedIncreaseSpinner = new Spinner<>();
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 20, settings.getBallSpeedIncreaseSetting());
        ballSpeedIncreaseSpinner.setValueFactory(valueFactory);
        HBox ballSpeedIncreaseSpinnerBox = new HBox(new Label("Ball Speed increases after "), ballSpeedIncreaseSpinner, new Label(" bounces (0 = speed stays constant)."));
        ballSpeedIncreaseSpinnerBox.setAlignment(Pos.CENTER);
        ballSpeedIncreaseSpinner.valueProperty().addListener((obs, oldValue, newValue) -> settings.setBallSpeedIncreaseSetting(newValue));

        ComboBox<String> player2ComputerControlledDropdown = new ComboBox<>();
        player2ComputerControlledDropdown.getItems().addAll("Player 2 is not Computer Controlled", "Player 2 is Computer Controlled");
        player2ComputerControlledDropdown.setValue(settings.getPlayer2ComputerControlled() ? "Player 2 is Computer Controlled":  "Player 2 is not Computer Controlled"); // Default value
        player2ComputerControlledDropdown.setOnAction(e -> updatePlayer2ComputerControlled(player2ComputerControlledDropdown.getValue()));

        Separator separator2 = new Separator();

        Button exitButton = new Button("Save & Exit");
        exitButton.setOnAction(e ->
        {
            settings.saveSettings(SETTINGS_FILENAME);
            stage.close();
        });

        VBox menuLayout = new VBox(20);
        menuLayout.getChildren().addAll(imageView, savingLoadingText, separator1, startButton, changePlayerOneNameButton, changePlayerTwoNameButton, setBallSpeedButton, adjustRacketDimensionsButton, setEndScoreButton, ballSpeedIncreaseSpinnerBox, player2ComputerControlledDropdown,  separator2, exitButton);
        menuLayout.setAlignment(Pos.CENTER);

        Scene menuScene = new Scene(menuLayout, width, height);

        stage.setScene(menuScene);
        stage.setMaximized(true);
        stage.show();
    }

    /**
     * Prepares the stage for a new game by adding listeners for various events such as window size changes and
     * key presses. A timeline is created, which will continuously call the refreshGame() function.
     * @param stage The stage that will be used.
     */
    private void startGame(Stage stage)
    {
        stage.setTitle("Daniel K's Ping Pong");

        Canvas canvas = new Canvas(width, height);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        //Reset the game
        resetGame();

        Timeline tl = new Timeline(new KeyFrame(Duration.millis(10), e -> refreshGame(gc, stage, canvas)));
        tl.setCycleCount(Timeline.INDEFINITE);

        //canvas.setOnMouseMoved(e -> player1.setYPosition(e.getY()));

        stage.widthProperty().addListener((obs, oldWidth, newWidth) ->
        {
            width = newWidth.intValue();
            player1.setXPosition(60);
            player2.setXPosition(width - 60 - settings.getPlayerWidth());
            canvas.setWidth(width);
        });
        stage.heightProperty().addListener((obs, oldHeight, newHeight) ->
        {
            height = newHeight.intValue();
            canvas.setHeight(height);
        });

        Scene scene = new Scene(new StackPane(canvas));
        stage.setScene(scene);
        gameController.listenForControls(scene);
        scene.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
            if (event.isShiftDown())
            {
                if (event.getCode() == KeyCode.S)
                {
                    gamePaused = true;
                    SerializationManager.getInstance().saveGame(this);
                }
                else if (event.getCode() == KeyCode.L)
                {
                    gamePaused = true;
                    Game loadedGame = SerializationManager.getInstance().loadGame();
                    loadGame(loadedGame, scene);
                }
                else if (event.getCode() == KeyCode.Y)
                {
                    gamePaused = true;
                    GameDAO.saveGame(this);
                }
                else if (event.getCode() == KeyCode.N)
                {
                    gamePaused = true;
                    GameDAO.loadGame(this);
                    ball.setXPosition(0);
                    ball.setYPosition(0);
                    ball.resetSpeed();
                }
            }
            else if (event.getCode() == KeyCode.P)
            {
                gamePaused = true;
            }
            else if (event.getCode() == KeyCode.R)
            {
                resetGame();
            }
        });
        stage.show();
        tl.play();
    }

    /**
     * Handles the process of changing the name of player 1.
     */
    private void changePlayer1Name()
    {
        TextInputDialog dialog = new TextInputDialog(player1.getName());
        dialog.setTitle("Change Player 1 Name");
        dialog.setHeaderText(null);

        dialog.setContentText("Enter the new name for " + player1.getName() + ": ");
        Optional<String> result = dialog.showAndWait();
        result.ifPresent(name ->
        {
            if (!name.isBlank())
            {
                player1.setName(name);
                settings.setPlayer1DefaultName(name);
            }
            else
            {
                alert("Input must be a non-empty string!");
            }
        });
    }

    /**
     * Handles the process of changing the name of player 2.
     */
    private void changePlayer2Name()
    {
        TextInputDialog dialog = new TextInputDialog(player2.getName());
        dialog.setTitle("Change Player 2 Name");
        dialog.setHeaderText(null);

        dialog.setContentText("Enter the new name for " + player2.getName() + ": ");
        Optional<String> result = dialog.showAndWait();
        result.ifPresent(name ->
        {
            if (!name.isBlank())
            {
                player2.setName(name);
                settings.setPlayer2DefaultName(name);
            }
            else
            {
                alert("Input must be a non-empty string!");
            }
        });
    }

    /**
     * Handles the process of changing the starting speed of the ball.
     */
    private void changeBallSpeed()
    {
        TextInputDialog dialog = new TextInputDialog(String.valueOf(ball.getInitialXSpeed()));
        dialog.setTitle("Change Ball Speed");
        dialog.setHeaderText(null);

        dialog.setContentText("Enter the new ball speed (1-100): ");
        Optional<String> result = dialog.showAndWait();
        result.ifPresent(speed ->
        {
            if (speed.matches("-?\\d+(\\.\\d+)?") && Integer.parseInt(speed) >= 1)
            {
                int newSpeed = Integer.parseInt(speed);
                settings.setInitialBallXSpeed(newSpeed);
                settings.setInitialBallYSpeed(newSpeed);
                ball.setInitialXSpeed(settings.getInitialBallXSpeed());
                ball.setInitialYSpeed(settings.getInitialBallYSpeed());
            }
            else
            {
                alert("Input must be an integer greater than 1!");
            }
        });
    }

    /**
     * Handles the process of changing the width and height of the player paddle.
     */
    private void changeRacketDimensions()
    {
        TextInputDialog heightDialog = new TextInputDialog(String.valueOf(settings.getPlayerHeight()));
        heightDialog.setTitle("Change Racket Dimensions");
        heightDialog.setHeaderText(null);

        heightDialog.setContentText("Enter the new height for the rackets:");
        Optional<String> heightResult = heightDialog.showAndWait();
        heightResult.ifPresent(height -> {
            if (height.matches("-?\\d+(\\.\\d+)?") && Integer.parseInt(height) >= 1)
            {
                settings.setPlayerHeight(Integer.parseInt(height));
            }
            else
            {
                alert("Input must be an integer greater than 1!");
            }
        });

        TextInputDialog widthDialog = new TextInputDialog(String.valueOf(settings.getPlayerWidth()));
        widthDialog.setTitle("Adjust Racket Dimensions");
        widthDialog.setHeaderText(null);

        widthDialog.setContentText("Enter the new width for the rackets:");
        Optional<String> widthResult = widthDialog.showAndWait();
        widthResult.ifPresent(width -> {
            if (width.matches("-?\\d+(\\.\\d+)?") && Integer.parseInt(width) >= 1)
            {
                settings.setPlayerWidth(Integer.parseInt(width));
            }
            else
            {
                alert("Input must be an integer greater than 1!");
            }
        });
    }

    /**
     * Handles the process of changing the score that will result in the victory of either player.
     */
    private void changeEndScore()
    {
        TextInputDialog dialog = new TextInputDialog(String.valueOf(settings.getEndScore()));
        dialog.setTitle("Set End Score");
        dialog.setHeaderText(null);

        dialog.setContentText("Enter the new end score (0 = no end score):");
        Optional<String> result = dialog.showAndWait();
        result.ifPresent(newEndScore -> {
            if (newEndScore.matches("-?\\d+(\\.\\d+)?") && Integer.parseInt(newEndScore) >= 0)
            {
                settings.setEndScore(Integer.parseInt(newEndScore));
            }
            else
            {
                alert("Input must be an integer!");
            }
        });
    }

    /**
     * Handles the process of changing whether player 2 is computer controlled or not.
     * @param setting The value of the player2ComputerControlledDropdown control from the main menu.
     */
    private void updatePlayer2ComputerControlled(String setting)
    {
        if (setting.equals("Player 2 is Computer Controlled"))
        {
            settings.setPlayer2ComputerControlled(true);
        }
        else
        {
            settings.setPlayer2ComputerControlled(false);
        }
    }

    /**
     * Displays an error alert box.
     * @param message The message to be displayed in the alert.
     */
    private void alert(String message)
    {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);

        alert.setContentText(message);
        alert.showAndWait();
    }

    /**
     * Updates/refreshes the game and all its entities.
     *
     * @param gc     The graphics context.
     * @param stage  The stage.
     * @param canvas The canvas.
     */
    public void refreshGame(GraphicsContext gc, Stage stage, Canvas canvas)
    {
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, width, height);
        gc.setFill(Color.WHITE);
        gc.setFont(Font.font(25));

        if(pointActive && !gamePaused) //A point is currently being played.
        {
            gameController.updatePlayerAndBallPositions(height);

            if (settings.getPlayer2ComputerControlled()) //If Player 2 is AI controlled
                gameController.computerControlPlayer2(width);

            gameController.renderBall(gc);
        }
        else
        {
            if (gameEnded) //The current game has ended.
            {
                gc.setStroke(Color.WHITE);
                gc.setTextAlign(TextAlignment.CENTER);
                if (player1.getScore() == settings.getEndScore())
                {
                    gc.strokeText(player1.getName() + " won!", width / 2.0, height / 2.0);
                }
                else
                {
                    gc.strokeText(player2.getName() + " won!", width / 2.0, height / 2.0);
                }

                canvas.setOnMouseClicked(e -> showMenu(stage));
            }
            else //The game is active, waiting to start point.
            {
                gc.setStroke(Color.WHITE);
                gc.setTextAlign(TextAlignment.CENTER);
                if (gamePaused)
                {
                    gc.strokeText("Click to Unpause", width / 2.0, height / 2.0);
                }
                else
                {
                    gc.strokeText("Click to Start", width / 2.0, height / 2.0);
                    ball.setRandomTrajectory();
                }

                canvas.setOnMouseClicked(e -> {
                    pointActive = true;
                    gamePaused = false;
                });
            }
        }

        gameController.checkForEdgeCollision(height);

        if (ball.getXPosition() < player1.getXPosition() - settings.getPlayerWidth())
        {
            handleGoalFrom(player2, gc);
        }
        if (ball.getXPosition() > player2.getXPosition() + settings.getPlayerWidth())
        {
            handleGoalFrom(player1, gc);
        }

        if (gameController.checkForPaddleCollision())
        {
            pointBallBounces++;
            if (settings.getBallSpeedIncreaseSetting() != 0 && pointBallBounces % settings.getBallSpeedIncreaseSetting() == 0)
            {
                ball.incrementXSpeed();
                ball.incrementYSpeed();
            }
        }

        gc.fillText(player1.getName() + ": " + player1.getScore() + "\t\t\t\t\t\t" + player2.getName() + ": " + player2.getScore(), width / 2.0, 100);
        gameController.renderPlayers(gc);
    }

    /**
     * Handles a goal from a player.
     * @param player The player that scored.
     * @param gc The graphics context.
     */
    public void handleGoalFrom(PlayerInterface player, GraphicsContext gc)
    {
        if (!gameEnded && pointActive)
        {
            player.score();
            resetPoint();
        }
        pointActive = false;
        if (settings.getEndScore() != 0 && player.getScore() >= settings.getEndScore())
        {
            gc.setFill(Color.WHITE);
            gameEnded = true;
        }
    }

    /**
     * Resets the game.
     */
    private void resetGame()
    {
        pointActive = false;
        gameEnded = false;
        gamePaused = false;
        pointBallBounces = 0;

        player1.setScore(0);
        player2.setScore(0);
        resetPoint();
    }

    /**
     * Resets the ball and paddle positions.
     */
    private void resetPoint()
    {
        pointBallBounces = 0;
        ball.resetSpeed();

        player1.setYPosition(height / 2.0);
        player2.setYPosition(height / 2.0);

        ball.setXPosition(width / 2.0);
        ball.setYPosition(height / 2.0);
    }

    /**
     * Loads entities from the specified Game.
     * @param game The Game to be loaded.
     * @param scene The current Scene.
     */
    private void loadGame(Game game, Scene scene)
    {
        if (game != null)
        {
            this.ball = game.ball;
            this.player1 = game.player1;
            this.player2 = game.player2;
            this.gameController = game.gameController;
            gameController.listenForControls(scene);
        }
    }

    public Object[] getDataForDatabase()
    {
        String player1Name = player1.getName();
        String player2Name = player2.getName();
        int player1Score = player1.getScore();
        int player2Score = player2.getScore();
        int endScore = settings.getEndScore();
        return new Object[] { player1Name, player2Name, player1Score, player2Score, endScore };
    }

    public void setDataFromDatabase(String player1Name, String player2Name, int player1Score, int player2Score, int endScore)
    {
        player1.setName(player1Name);
        player2.setName(player2Name);
        player1.setScore(player1Score);
        player2.setScore(player2Score);
        settings.setEndScore(endScore);
    }

    /**
     * The main function.
     * @param args Arguments passed into the main.
     */
    public static void main(String[] args)
    {
        launch();
    }
}