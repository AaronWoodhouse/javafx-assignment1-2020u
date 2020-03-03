// Import libraries
import javafx.application.Application;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class letterHistogram extends Application {
    // Initialize pane and essential nodes
    Pane pane = new Pane();
    TextField txtFile = new TextField();
    VBox vBox = new VBox();

    @Override
    public void start(Stage primaryStage) {
        // Initialize the user prompt
        Label label = new Label("Filename:", txtFile);
        label.setContentDisplay(ContentDisplay.RIGHT);
        txtFile.setPrefColumnCount(20);

        // Initialize button
        Button viewBtn = new Button("View");

        // Add all of the nodes to an HBox
        HBox hBox = new HBox(label, viewBtn);

        // Add HBox to the main VBox
        vBox.getChildren().addAll(pane, hBox);

        // Create scene
        Scene scene = new Scene(vBox);
        primaryStage.setScene(scene);
        primaryStage.setTitle("HistogramOfLetters");
        primaryStage.sizeToScene();

        // Show histogram when button is pressed
        viewBtn.setOnAction(e -> {
            updateHistogram();
            vBox.setTranslateY(10);

            // Resize pane for graph
            primaryStage.sizeToScene();
            primaryStage.setTitle("HistogramOfLetters");
        });

        primaryStage.show();
    }

    // Add values to graph
    private void updateHistogram() {
        Histogram graph = new Histogram(txtFile.getText());
        pane.getChildren().add(graph);
    }

    // Creates histogram using set values for width and height
    private static class Histogram extends Pane {
        GridPane pane;
        double w = 250;
        double h = 250;

        // Creates enough bars to handle every letter of the alphabet
        private char[] chars = new char[26];
        private int[] counts = new int[26];
        private Rectangle[] rectBars = new Rectangle[26];
        private File fileObj;

        // Constructs a histogram
        public Histogram(String filename) {
            this.fileObj = new File(filename.trim());
            setWidth(w);
            setHeight(h);
            readFile();
            drawHistogram();
        }

        // Sets the file as the data to be displayed in the histogram
        private void readFile() {
            Scanner scanner;
            StringBuilder s = new StringBuilder();

            // Scans file for data
            try {
                scanner = new Scanner(fileObj);

                while (scanner.hasNextLine()) {
                    s.append(scanner.nextLine());
                }
            } catch (IOException ignored) {}

            s = new StringBuilder(s.toString().toUpperCase());

            // Counts occurrences of each letter
            for (int i = 0; i < s.length(); i++) {
                char character = s.charAt(i);

                if (Character.isLetter(character)) {
                    counts[character - 'A']++;
                }
            }
        }

        // Gets the total amount of each letter
        private double getTotalCount() {
            double total = 0;

            for (int count : counts) {
                total += count;
            }

            return total;
        }

        // Draws histogram
        private void drawHistogram() {
            pane = new GridPane();
            double barW = w / chars.length;
            double total = getTotalCount();

            for (int i = 0; i < counts.length; i++) {
                chars[i] = (char) ('A' + i);
                double percentage = counts[i] / total;
                double barH = h * percentage;

                rectBars[i] = new Rectangle(barW, barH);
                rectBars[i].setStyle("-fx-fill: white; -fx-stroke: black; -fx-stroke-width: 1;");

                Label label = new Label(chars[i] + "", rectBars[i]);
                label.setContentDisplay(ContentDisplay.TOP);

                pane.add(label, i, 0);

                GridPane.setValignment(label, VPos.BASELINE);
            }

            getChildren().addAll(pane);
        }

        public int[] getCounts() {
            return counts;
        }

        public void setCounts(int[] counts) {
            this.counts = counts;
            drawHistogram();
        }
    }
}