package sample;

import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.paint.Color;

import java.util.Random;

public class Main extends Application {

    int radius = 5;

    Line l1 = new Line();
    Line l2 = new Line();
    Line l3 = new Line();

    Text[] text = {new Text(), new Text(), new Text()};

    Circle circle2 = new Circle(100, 100, 45, Color.TRANSPARENT);

    @Override
    public void start(Stage primaryStage) {
        circle2.setStroke(Color.BLACK);

        //Random start values
        Random random = new Random();
        int bound = 300;
        int rand1 = random.nextInt(bound);
        int rand2 = random.nextInt(bound);
        int rand3 = random.nextInt(bound);
        int rand4 = random.nextInt(bound);
        int rand5 = random.nextInt(bound);
        int rand6 = random.nextInt(bound);

        // set all values initially
        double dxi = rand1 - circle2.getCenterX();
        double dyi = rand2 - circle2.getCenterY();

        double dxi2 = rand3 - circle2.getCenterX();
        double dyi2 = rand4 - circle2.getCenterY();

        double dxi3 = rand5 - circle2.getCenterX();
        double dyi3 = rand6 - circle2.getCenterY();

        double hypi = Math.sqrt(Math.pow(dxi,2) + Math.pow(dyi,2));
        double hypi2 = Math.sqrt(Math.pow(dxi2,2) + Math.pow(dyi2,2));
        double hypi3 = Math.sqrt(Math.pow(dxi3,2) + Math.pow(dyi3,2));

        double xi = dxi / hypi;
        double yi = dyi / hypi;

        double xi2 = dxi2 / hypi2;
        double yi2 = dyi2 / hypi2;

        double xi3 = dxi3 / hypi3;
        double yi3 = dyi3 / hypi3;


        Circle[] circle = {new Circle((xi * circle2.getRadius()) + circle2.getCenterX(), (yi * circle2.getRadius()) + circle2.getCenterY(), radius, Color.RED),
                           new Circle((xi2 * circle2.getRadius()) + circle2.getCenterX(), (yi2 * circle2.getRadius()) + circle2.getCenterY(), radius, Color.RED),
                           new Circle((xi3 * circle2.getRadius()) + circle2.getCenterX(), (yi3 * circle2.getRadius()) + circle2.getCenterY(), radius, Color.RED)};

        text[0].setX((0.7f * xi * circle2.getRadius()) + circle2.getCenterX() - 5);
        text[0].setY((0.7f * yi * circle2.getRadius()) + circle2.getCenterY() + 5);
        text[1].setX((0.7f * xi2 * circle2.getRadius()) + circle2.getCenterX() - 5);
        text[1].setY((0.7f * yi2 * circle2.getRadius()) + circle2.getCenterY() + 5);
        text[2].setX((0.7f * xi3 * circle2.getRadius()) + circle2.getCenterX() - 5);
        text[2].setY((0.7f * yi3 * circle2.getRadius()) + circle2.getCenterY() + 5);

        //(x * circle2.getRadius()) + circle2.getCenterX()
        //(y * circle2.getRadius()) + circle2.getCenterY()

        //setup pane
        Pane pane = new Pane();
        setLines(circle);
        pane.getChildren().addAll(circle2, l1, l2, l3, circle[0], circle[1], circle[2], text[0], text[1], text[2]);

        //setup scene
        Scene scene = new Scene(pane, 400, 250);
        primaryStage.setTitle("Part 3");
        primaryStage.setScene(scene);
        primaryStage.show();

        // mouse dragging
        circle[0].setOnMouseDragged(e -> {
            if (circle[0].contains(e.getX(), e.getY())) {

                double dx = e.getX() - circle2.getCenterX();
                double dy = e.getY() - circle2.getCenterY();
                double hyp = Math.sqrt(Math.pow(dx,2) + Math.pow(dy,2));
                double x = dx / hyp;
                double y = dy / hyp;

                // Recompute and display angles
                circle[0].setCenterX((x * circle2.getRadius()) + circle2.getCenterX());
                circle[0].setCenterY((y * circle2.getRadius()) + circle2.getCenterY());
                setLines(circle);

                text[0].setX((0.7f * x * circle2.getRadius()) + circle2.getCenterX() - 5);
                text[0].setY((0.7f * y * circle2.getRadius()) + circle2.getCenterY() + 5);
            }
        });

        circle[1].setOnMouseDragged(e -> {
            if (circle[1].contains(e.getX(), e.getY())) {

                double dx = e.getX() - circle2.getCenterX();
                double dy = e.getY() - circle2.getCenterY();
                double hyp = Math.sqrt(Math.pow(dx,2) + Math.pow(dy,2));
                double x = dx / hyp;
                double y = dy / hyp;

                // Recompute and display angles
                circle[1].setCenterX((x * circle2.getRadius()) + circle2.getCenterX());
                circle[1].setCenterY((y * circle2.getRadius()) + circle2.getCenterY());
                setLines(circle);

                text[1].setX((0.7f * x * circle2.getRadius()) + circle2.getCenterX() - 5);
                text[1].setY((0.7f * y * circle2.getRadius()) + circle2.getCenterY() + 5);
            }
        });

        circle[2].setOnMouseDragged(e -> {
            if (circle[2].contains(e.getX(), e.getY())) {

                double dx = e.getX() - circle2.getCenterX();
                double dy = e.getY() - circle2.getCenterY();
                double hyp = Math.sqrt(Math.pow(dx,2) + Math.pow(dy,2));
                double x = dx / hyp;
                double y = dy / hyp;

                // Recompute and display angles
                circle[2].setCenterX((x * circle2.getRadius()) + circle2.getCenterX());
                circle[2].setCenterY((y * circle2.getRadius()) + circle2.getCenterY());
                setLines(circle);

                text[2].setX((0.7f * x * circle2.getRadius()) + circle2.getCenterX() - 5);
                text[2].setY((0.7f * y * circle2.getRadius()) + circle2.getCenterY() + 5);
            }
        });
    }

    // lines between dots
    private void setLines(Circle[] circle) {
        l1.setStartX(circle[0].getCenterX());
        l1.setStartY(circle[0].getCenterY());
        l1.setEndX(circle[1].getCenterX());
        l1.setEndY(circle[1].getCenterY());

        l2.setStartX(circle[0].getCenterX());
        l2.setStartY(circle[0].getCenterY());
        l2.setEndX(circle[2].getCenterX());
        l2.setEndY(circle[2].getCenterY());

        l3.setStartX(circle[1].getCenterX());
        l3.setStartY(circle[1].getCenterY());
        l3.setEndX(circle[2].getCenterX());
        l3.setEndY(circle[2].getCenterY());

        // Compute angles
        double a = new Point2D(circle[2].getCenterX(), circle[2].getCenterY()).
                distance(circle[1].getCenterX(), circle[1].getCenterY());
        double b = new Point2D(circle[2].getCenterX(), circle[2].getCenterY()).
                distance(circle[0].getCenterX(), circle[0].getCenterY());
        double c = new Point2D(circle[1].getCenterX(), circle[1].getCenterY()).
                distance(circle[0].getCenterX(), circle[0].getCenterY());

        double[] angle = new double[3];
        angle[0] = Math.acos((a * a - b * b - c * c) / (-2 * b * c));
        angle[1] = Math.acos((b * b - a * a - c * c) / (-2 * a * c));
        angle[2] = Math.acos((c * c - b * b - a * a) / (-2 * a * b));

        //set text
        for (int i = 0; i < 3; i++) {
            text[i].setText(String.format("%.0f", Math.toDegrees(angle[i])));
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}