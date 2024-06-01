package SSChallenge.AmazonWordCloud.presentation;

import SSChallenge.AmazonWordCloud.AmazonWordCloudApplication;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.IOException;

public class AmazonWordCloudFX extends Application {

    private ConfigurableApplicationContext context;

    public void init(){
        this.context = new SpringApplicationBuilder(AmazonWordCloudApplication.class).run();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Scene scene = loadScene();
        configureStage(primaryStage, scene);
    }

    /**
     * Carga la escena principal.
     * @return Escena cargada.
     * @throws IOException Error de carga.
     */
    private Scene loadScene() throws IOException {
        FXMLLoader loader = new FXMLLoader(AmazonWordCloudApplication.class.getResource("/templates/MainScene.fxml"));
        loader.setControllerFactory(context::getBean);
        return new Scene(loader.load());
    }

    /**
     * Configura la ventana para su ejecucion.
     * @param primaryStage Stage principal.
     * @param scene Escena principal.
     */
    private static void configureStage(Stage primaryStage, Scene scene) {
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
