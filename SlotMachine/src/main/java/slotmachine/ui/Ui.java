package slotmachine.ui;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import slotmachine.domain.Player;
import slotmachine.domain.SlotLogic;

public class Ui extends Application {
    
    private SlotLogic slot;
    private Text text;
    private Image image;
    private Player player;
    private Text winnings;
    private String money;
    
    @Override
    public void start(Stage stage) throws Exception {
        
        slot = new SlotLogic();
        player = new Player();
        
        text = new Text("Good luck in your gamble!");
        text.setFill(Color.WHITE);
        
        winnings = new Text(String.valueOf(player.getMoney()));
        winnings.setFill(Color.WHITE);
        
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
            checkIfWin();
        });
        
        //Build borderpane
        pane.setCenter(gPane);
        pane.setLeft(button);
        pane.setBottom(text);
        pane.setRight(winnings);
        
        //Build scene
        Scene scene = new Scene(pane, 700, 500);
        stage.setScene(scene);
        stage.show();
    }
    
    //Check win and pay
    public void checkIfWin() {
        if (slot.getWin()) {
            player.payUp();
            winnings.setText(String.valueOf(player.getMoney()));
            text.setText("You WON!");
        } else {
            player.lose();
            winnings.setText(String.valueOf(player.getMoney()));
            text.setText("Lady Luck will be on your side yet!");
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
    
    public static void main(String[] args) {
        launch(args);
    }
    
}