package slotmachine.ui;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import slotmachine.domain.*;

public class Ui extends Application {
    
    private SlotMachineService slotService;
    
    private Scene loginScene, newUserScene, gameScene;
    
    private SlotLogic slot;
    private Image image;
    private User player;
    private Text wonText, accBalance, accBalanceBase;
    
    @Override
    public void start(Stage stage) throws Exception {
        
        slotService = new SlotMachineService();
        player = new User("");
        
        
        //Login scene
        VBox loginPane = new VBox(10);
        HBox inputPane = new HBox(10);
        Label loginLabel = new Label("username");
        TextField usernameInput = new TextField();
        
        inputPane.getChildren().addAll(loginLabel, usernameInput);
        Label loginMessage = new Label();
        
        Button loginButton = new Button("login");
        Button createButton = new Button("create new user");
        loginButton.setOnAction(event -> {
            String username = usernameInput.getText();
            player = slotService.login(username);
            if(player != null) {
                accBalance.setText(accBalanceBase.getText() + String.valueOf(player.getMoney()));
                loginMessage.setText("");
                stage.setScene(gameScene);
                usernameInput.setText("");
            } else {
                loginMessage.setText("User does not exist");
            }
        });
        
        createButton.setOnAction(event -> {
            usernameInput.setText("");
            stage.setScene(newUserScene);
        });
        
        loginPane.getChildren().addAll(loginMessage, inputPane, loginButton, createButton);
        
        loginScene = new Scene(loginPane, 300, 250);
        
        //create new user scene
        VBox newUserPane = new VBox(10);
        HBox newNamePane = new HBox(10);
        TextField newNameInput = new TextField();
        Label newNameLabel = new Label("name");
        newNameLabel.setPrefWidth(100);
        newNamePane.getChildren().addAll(newNameLabel, newNameInput);   
        
        Label userCreationMessage = new Label();
        
        Button createNewUserButton = new Button("create");

        createNewUserButton.setOnAction(event -> {
            String name = newNameInput.getText();
   
            if (name.length()==2 || name.length()<2 ) {
                userCreationMessage.setText("username or name too short");
                userCreationMessage.setTextFill(Color.RED);              
            } else if (slotService.create(name)){
                userCreationMessage.setText("");                
                loginMessage.setText("new user created");
                loginMessage.setTextFill(Color.GREEN);
                stage.setScene(loginScene);      
            } else {
                userCreationMessage.setText("username has to be unique");
                userCreationMessage.setTextFill(Color.RED);        
            }
 
        });  
        
        newUserPane.getChildren().addAll(userCreationMessage, newNamePane, createNewUserButton); 
       
        newUserScene = new Scene(newUserPane, 300, 250);
        
        
        //game scene
        slot = new SlotLogic();
        
        wonText = new Text("Good luck in your gamble!");
        wonText.setFill(Color.WHITE);
        
        
        accBalanceBase = new Text("Account balance: ");
        accBalance = new Text();
        accBalance.setFill(Color.WHITE);
        
        BorderPane pane;
        pane = new BorderPane();
        pane.setStyle("-fx-background-color:#000080");
        
        
        //Grid setup
        GridPane gPane = new GridPane();
        gPane.setAlignment(Pos.CENTER);
        gPane.setVgap(15);
        gPane.setHgap(15);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                gPane.add(imageHandler(3), j, i);
            }
        }
        
        
        //Button
        Image playImage = new Image("images/other/play.png", 100, 100, false, false);
        ImageView playView = new ImageView(playImage);
        
        Button button = new Button();
        button.setGraphic(playView);
        button.setStyle("-fx-background-color: #ffffff00");
        
        button.setOnMouseClicked((event) -> {
            slot.setValueSlots();
            gPane.getChildren().clear();
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    gPane.add(imageHandler(slot.getValueSlot(i, j)), j, i);
                }
            }
            try {
                checkIfWin();
            } catch (SQLException ex) {
                Logger.getLogger(Ui.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        //Build borderpane
        pane.setCenter(gPane);
        pane.setLeft(button);
        pane.setBottom(wonText);
        pane.setRight(accBalance);
        
        //Build scene
        gameScene = new Scene(pane, 700, 500);
        stage.setScene(loginScene);
        stage.show();
    }
    
    //Check win and pay
    public void checkIfWin() throws SQLException {
        //dbHandle();
        if (slot.getWin()) {
            player.payUp();
            //player = slotService.payUp(player, 5);
            accBalance.setText(accBalanceBase.getText() + String.valueOf(player.getMoney()));
            wonText.setText("You WON!");
        } else {
            player.lose();
            //player = slotService.taxLose(player, 1);
            accBalance.setText(accBalanceBase.getText() + String.valueOf(player.getMoney()));
            wonText.setText("Lady Luck will be on your side yet!");
        }   
    }
    
    //Assign images to grid values
    public ImageView imageHandler(int x) {
        switch (x) {
            case 1: 
                image = new Image("images/fruits/pineapple.png", 100, 100, false, false);
                break;
            case 2: 
                image = new Image("images/fruits/pear.png", 100, 100, false, false);
                break;
            case 3: 
                image = new Image("images/fruits/apple.png", 100, 100, false, false);
                break;
            case 4: 
                image = new Image("images/fruits/orange.png", 100, 100, false, false);
                break;
            case 5: 
                image = new Image("images/fruits/cherry.png", 100, 100, false, false);
                break;
            case 6: 
                image = new Image("images/fruits/strawberry.png", 100, 100, false, false);
                break;
            case 7: 
                image = new Image("images/fruits/watermelon.png", 100, 100, false, false);
                break;
        }
        return new ImageView(image);
    }
    
    @Override
    public void stop() {
        slotService.updateBalance(player);
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    
}