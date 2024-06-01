package SSChallenge.AmazonWordCloud;

import SSChallenge.AmazonWordCloud.presentation.AmazonWordCloudFX;
import javafx.application.Application;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AmazonWordCloudApplication implements CommandLineRunner {

    public static void main(String[] args) {
		Application.launch(AmazonWordCloudFX.class, args);
	}

	@Override
	public void run(String... args){
	}

}
