// imports
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import java.util.Random;

public class ThreeCards extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        // grid pane
        GridPane pane = new GridPane();
        pane.setAlignment(Pos.CENTER);
        pane.setPadding(new Insets(10,10,10,10));
        pane.setVgap(5);
        pane.setHgap(5);

        // random cards
        int r2 = 0;
        for(int i = 0; i < 3; i++) {
            Random rand = new Random();
            int r = rand.nextInt(54);
            r += 1; //random card 1 -54
            if(r2 == r) {    //no duplicates
                i--;
                continue;
            }
            r2 = r;
            Image c = new Image("Cards/" + r + ".png");
            ImageView imageView = new ImageView(c);

            pane.add(imageView, i, 0);
        }

        // scene
        Scene scene = new Scene(pane);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Part 1");
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
