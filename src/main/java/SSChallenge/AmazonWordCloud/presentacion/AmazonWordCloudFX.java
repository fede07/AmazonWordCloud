package SSChallenge.AmazonWordCloud.presentacion;

import SSChallenge.AmazonWordCloud.AmazonWordCloudApplication;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

public class AmazonWordCloudFX extends Application {

    private ConfigurableApplicationContext context;

    public void init(){
        this.context = new SpringApplicationBuilder(AmazonWordCloudApplication.class).run();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        FXMLLoader loader = new FXMLLoader(AmazonWordCloudApplication.class.getResource("/templates/MainScene.fxml"));
        loader.setControllerFactory(context::getBean);
        Scene scene = new Scene(loader.load());
        primaryStage.setScene(scene);
        primaryStage.setTitle("Amazon Word Cloud");
        primaryStage.show();
    }

    @Override
    public void stop(){
        context.close();
        Platform.exit();
    }
}
