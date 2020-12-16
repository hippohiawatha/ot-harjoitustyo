package slotmachine.ui;

import slotmachine.dao.DBService;
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
    
    private DBService slotService;
    
    private Scene loginScene, newUserScene, gameScene;
    
    private SlotLogic slot;
    private Image image;
    private User player;
    private Text wonText, accBalanceText, accBalanceTextBase;
    
    @Override
    public void start(Stage stage) throws Exception {
        
        slotService = new DBService(false);
        player = new User("");
        
        
        //Login scene
        VBox loginPane = new VBox(10);
        HBox inputPane = new HBox(10);
        Label loginLabel = new Label("username");
        TextField nameInput = new TextField();
        
        inputPane.getChildren().addAll(loginLabel, nameInput);
        Label loginMessage = new Label();
        
        Button loginButton = new Button("login");
        Button createButton = new Button("create new user");
        loginButton.setOnAction(event -> {
            String username = nameInput.getText();
            player = slotService.login(username);
            if(player != null) {
                accBalanceText.setText(accBalanceTextBase.getText() + String.valueOf(player.getMoney()));
                loginMessage.setText("");
                stage.setScene(gameScene);
                nameInput.setText("");
            } else {
                loginMessage.setText("User does not exist");
            }
        });
        
        createButton.setOnAction(event -> {
            nameInput.setText("");
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
   
            if (name.length() < 3 ) {
                userCreationMessage.setText("name is too short");
                userCreationMessage.setTextFill(Color.RED);              
            } else if (slotService.create(name)){
                userCreationMessage.setText("");                
                loginMessage.setText("new user created");
                loginMessage.setTextFill(Color.GREEN);
                stage.setScene(loginScene);      
            } else {
                userCreationMessage.setText("name already exists");
                userCreationMessage.setTextFill(Color.RED);        
            }
 
        });  
        
        newUserPane.getChildren().addAll(userCreationMessage, newNamePane, createNewUserButton); 
       
        newUserScene = new Scene(newUserPane, 300, 300);
        
        
        //game scene
        slot = new SlotLogic();
        
        wonText = new Text("Good luck in your gamble!");
        wonText.setFill(Color.WHITE);
        
        
        accBalanceTextBase = new Text("Account balance: ");
        accBalanceText = new Text();
        accBalanceText.setFill(Color.WHITE);
        
        BorderPane pane;
        pane = new BorderPane();
        pane.setStyle("-fx-background-color:#000080");
        
        
        //Grid setup
        GridPane slotGrid = new GridPane();
        slotGrid.setAlignment(Pos.CENTER);
        slotGrid.setVgap(15);
        slotGrid.setHgap(15);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                slotGrid.add(slot.imageHandler(3), j, i);
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
            slotGrid.getChildren().clear();
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    slotGrid.add(slot.imageHandler(slot.getValueSlot(i, j)), j, i);
                }
            }
            if (slot.won()) {
                player.payUp();
                accBalanceText.setText(accBalanceTextBase.getText() + String.valueOf(player.getMoney()));
                wonText.setText("You WON!");
            } else {
                player.lose();
                accBalanceText.setText(accBalanceTextBase.getText() + String.valueOf(player.getMoney()));
                wonText.setText("Lady Luck will be on your side yet!");
            }
        });
        
        //Build borderpane
        pane.setCenter(slotGrid);
        pane.setLeft(button);
        pane.setBottom(wonText);
        pane.setRight(accBalanceText);
        
        //Build scene
        gameScene = new Scene(pane, 700, 500);
        stage.setScene(loginScene);
        stage.show();
    }
    
    @Override
    public void stop() {
        slotService.updateBalance(player);
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    
}