// Import libraries
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class investmentCalculator extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        // Create pane and set padding
        GridPane gp = new GridPane();
        gp.setHgap(5);
        gp.setVgap(5);

        // Initialize labels and textfield
        Label lblInvestment = new Label("Investment Amount:");
        TextField txtInvestment = new TextField();
        txtInvestment.setPromptText("Enter Investment Amount");
        gp.add(lblInvestment, 0, 0);
        gp.add(txtInvestment, 1, 0);

        Label lblYears = new Label("Number Of Years:");
        TextField txtYears = new TextField();
        txtYears.setPromptText("Enter Number of Years");
        gp.add(lblYears, 0, 1);
        gp.add(txtYears, 1, 1);

        Label lblRate = new Label("Annual Interest Rate:");
        TextField txtRate = new TextField();
        txtRate.setPromptText("Enter Annual Interest Rate");
        gp.add(lblRate, 0, 2);
        gp.add(txtRate, 1, 2);

        Label lblFutureValue = new Label("Future Value: ");
        TextField txtFutureValue = new TextField();
        gp.add(lblFutureValue, 0, 3);
        gp.add(txtFutureValue, 1, 3);

        // Initialize button to calculate result
        Button btCalculate = new Button("Calculate");
        gp.add(btCalculate, 1, 4);

        // Format pane and nodes
        gp.setAlignment(Pos.CENTER);
        txtInvestment.setAlignment(Pos.BOTTOM_RIGHT);
        txtYears.setAlignment(Pos.BOTTOM_RIGHT);
        txtRate.setAlignment(Pos.BOTTOM_RIGHT);
        txtFutureValue.setAlignment(Pos.BOTTOM_RIGHT);
        txtFutureValue.setEditable(false);
        GridPane.setHalignment(btCalculate, HPos.RIGHT);
        gp.setPadding(new Insets(10,10,10,10));

        // Create Event Handler for button
        btCalculate.setOnAction(event -> futureValue(txtInvestment, txtYears, txtRate, txtFutureValue));

        // Create Scene
        Scene scene = new Scene(gp);
        primaryStage.setTitle("Exercise_15_05"); // Set the title
        primaryStage.setScene(scene); // Place scene in stage
        primaryStage.show(); // Display stage
    }

    // Event Handler will run this method to calculate the future interest
    private void futureValue(TextField txtInvestment, TextField txtYears, TextField txtRate, TextField txtFutureValue) {
        double amount = Double.parseDouble(txtInvestment.getText());
        int years = Integer.parseInt(txtYears.getText());
        double rate = Double.parseDouble(txtRate.getText()) / 1200;
        txtFutureValue.setText(String.format("$%.2f", (amount * Math.pow(1 + rate, years * 12))));
    }
}
