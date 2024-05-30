package SSChallenge.AmazonWordCloud.controlador;

import SSChallenge.AmazonWordCloud.evento.Evento;
import SSChallenge.AmazonWordCloud.modelo.Palabra;
import SSChallenge.AmazonWordCloud.servicio.palabra.PalabraServicio;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

@Component
public class MainControlador implements Initializable{
    private final PalabraServicio palabraServicio;
    @FXML
    private TableView<Palabra> tablaPalabras;
    @FXML
    private TableColumn<Palabra, String> columnaPalabra;
    @FXML
    private TableColumn<Palabra, Integer> columnaApariciones;

    private final ObservableList<Palabra> palabrasList = FXCollections.observableArrayList();

    public MainControlador(PalabraServicio palabraServicio) {

        this.palabraServicio = palabraServicio;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tablaPalabras.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        tablaPalabras.getSortOrder().add(columnaApariciones);
        configurarColumnas();
        listarPalabras();
    }

    public void listarPalabras() {
        palabrasList.clear();
        palabrasList.addAll(palabraServicio.listarPalabras());
        tablaPalabras.setItems(palabrasList);
        tablaPalabras.getSortOrder().add(columnaApariciones);
        tablaPalabras.sort();
    }

    @EventListener({Evento.class})
    public void listarPalabrasEvento() {
        Platform.runLater(this::listarPalabras);
    }

    private void configurarColumnas() {
        columnaPalabra.setCellValueFactory(new PropertyValueFactory<>("palabra"));
        columnaApariciones.setCellValueFactory(new PropertyValueFactory<>("cantApariciones"));
        columnaApariciones.setSortType(TableColumn.SortType.DESCENDING);
    }

}
