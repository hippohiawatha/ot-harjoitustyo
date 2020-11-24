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
import slotmachine.domain.SlotLogic;

public class Ui extends Application {
    
    private SlotLogic slot;
    private Text text;
    private Image image;
    
    @Override
    public void start(Stage stage) throws Exception{
        
        slot = new SlotLogic();
        text = new Text("Good luck in your gamble!");
        text.setFill(Color.WHITE);
        
        BorderPane pane;
        pane = new BorderPane();
        pane.setStyle("-fx-background-color:#000080");
        
        
        //Grid setup
        GridPane gPane = new GridPane();
        gPane.setAlignment(Pos.CENTER);
        gPane.setVgap(15);
        gPane.setHgap(15);
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                gPane.add(imageHandler(3), j, i);
            }
        }
        
        
        //Button
        Button button = new Button("SPIN");
        button.setOnMouseClicked((event) -> {
            slot.setValueSlots();
            gPane.getChildren().clear();
            for(int i = 0; i < 3; i++) {
                 for(int j = 0; j < 3; j++) {
                    gPane.add(imageHandler(slot.getValueSlots(i, j)), j, i);
                }
            }
            if(slot.getWin()) text.setText("You WON!");
            else text.setText("Lady Luck will be on your side yet!");   
        });
        
        //Build borderpane
        pane.setCenter(gPane);
        pane.setLeft(button);
        pane.setBottom(text);
        
        //Build scene
        Scene scene = new Scene(pane, 400, 400);
        stage.setScene(scene);
        stage.show();
    }
    
    //Assign images to grid values
    public ImageView imageHandler(int x){
        switch(x){
            case 1: image = new Image("images/pineapple.png",100,100,false,false); break;
            case 2: image = new Image("images/pear.png",100,100,false,false); break;
            case 3: image = new Image("images/apple.png",100,100,false,false); break;
        }
        return new ImageView(image);
    }
    
    public static void main(String[] args){
        launch(args);
    }
    
}