import java.io.File;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class Main extends Application {

	@Override
	public void start(Stage stage) throws Exception {
		FlowPane flowPane = new FlowPane();

		addAudioButtons(flowPane);

		Scene scene = new Scene(flowPane);
		stage.setOnCloseRequest(event -> System.exit(0));
		stage.setScene(scene);
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

	public void addAudioButtons(FlowPane flowPane) {
		File dir = new File(System.getProperty("user.dir"));
		for (File file : dir.listFiles()) {
			if (file.isFile() && file.getName().endsWith(".mp3")) {
				String buttonName = file.getName();
				String filename = file.toURI().toString();

				flowPane.getChildren().add(new AudioButton(filename,buttonName));
			}
		}
	}
}
