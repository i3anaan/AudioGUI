import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;
import javafx.scene.paint.Color;

public class AudioButton extends BorderPane implements Runnable,
		EventHandler<ActionEvent> {

	private int count;
	private MediaPlayer player;
	private Media media;

	private Label countIndicator;
	private Button titleButton;

	public AudioButton(String filename, String buttonName) {
		player = new MediaPlayer(new Media(filename));
		this.count = 0;
		countIndicator = new Label(count + "");
		titleButton = new Button(buttonName);
		titleButton.setOnAction(this);
		titleButton.setTextFill(Color.BLACK);
		
		player.setOnEndOfMedia(this);
		player.setOnStopped(this);

		this.setCenter(titleButton);
		this.setBottom(countIndicator);

	}

	public void play() {
		Status status = player.getStatus();
		if (status == Status.UNKNOWN || status == Status.HALTED) {
			// don't do anything in these states
			return;
		}
		if (status == Status.PAUSED || status == Status.READY
				|| status == Status.STOPPED) {
			player.play();
			count++;
			countIndicator.setText(count + "");
			titleButton.setTextFill(Color.RED);
		} else {
			player.stop();
		}
	}

	@Override
	public void handle(ActionEvent arg0) {
		play();
	}

	@Override
	public void run() {
		//Media completed.
		player.stop();
		titleButton.setTextFill(Color.BLACK);
	}
}
