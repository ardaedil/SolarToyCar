import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class SolarToyCarVisualization extends Application {

    private SolarToyCar myCar;
    private Label infoLabel;
    private Rectangle carShape;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        myCar = new SolarToyCar();

        VBox root = new VBox(10);

        StackPane carPane = new StackPane();
        carShape = new Rectangle(30, 20, Color.BLUE); // Initial size and color of the car
        carPane.getChildren().add(carShape);

        infoLabel = new Label("Initializing...");
        root.getChildren().addAll(carPane, infoLabel);

        Scene scene = new Scene(root, 400, 200);

        primaryStage.setTitle("Solar Toy Car Visualization");
        primaryStage.setScene(scene);
        primaryStage.show();

        // Run the toy car actions with animation
        runToyCarActions();
    }

    private void runToyCarActions() {
        performAction("Initial Battery Level: " + myCar.getBattery(), 0);

        myCar.turn(Direction.RIGHT, 45);
        performAction("Turn right by 45 degrees", 45);

        myCar.forward(30.0);
        performAction("Move forward by 30.0 meters", 75);

        myCar.turnWithSolarEnergy(20.0);
        performAction("Turn with solar energy by 20.0 degrees", 95);

        myCar.moveWithSolarEnergy(100.0);
        performAction("Move with solar energy by 100.0 meters", 195);

        myCar.chargeBattery();
        performAction("Charge the battery", 195);

        performAction("Final Battery Level: " + myCar.getBattery(), 195);
    }

    private void performAction(String action, double distance) {
        // Animate the car moving horizontally
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(1.5),
                        event -> {
                            infoLabel.setText(action);
                            animateCar(distance);
                        })
        );
        timeline.play();
    }

    private void animateCar(double distance) {
        // Animate the car's movement
        Timeline carAnimation = new Timeline(
                new KeyFrame(Duration.seconds(1), event -> {
                    carShape.setTranslateX(carShape.getTranslateX() + distance);
                })
        );
        carAnimation.play();
    }
}


