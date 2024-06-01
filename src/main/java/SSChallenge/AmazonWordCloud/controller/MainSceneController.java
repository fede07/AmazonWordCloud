package SSChallenge.AmazonWordCloud.controller;

import SSChallenge.AmazonWordCloud.event.Event;
import SSChallenge.AmazonWordCloud.model.Word;
import SSChallenge.AmazonWordCloud.service.word.WordService;
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
public class MainSceneController implements Initializable{

    private final WordService wordService;
    @FXML
    private TableView<Word> tableWords;
    @FXML
    private TableColumn<Word, String> columnWord;
    @FXML
    private TableColumn<Word, Integer> columnOccurrences;

    private final ObservableList<Word> wordsList = FXCollections.observableArrayList();

    public MainSceneController(WordService wordService) {

        this.wordService = wordService;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        configureTable();
        configureColumns();
        listWords();
    }

    /**
    Configura la tabla de palabras.
    Seleccion simple.
    Setea orden por columna "Cantidad de Apariciones".
     */
    private void configureTable() {
        tableWords.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        tableWords.getSortOrder().add(columnOccurrences);
    }

    /**
     * Obtiene la lista de palabras de la base de datos y la muestra en la tabla.
     */
    public void listWords() {
        wordsList.clear();
        wordsList.addAll(wordService.listWords());
        tableWords.setItems(wordsList);
        tableWords.getSortOrder().add(columnOccurrences);
        tableWords.sort();
    }

    /**
     * Cuando llega un request, despues de que se proceso, refresca la lista de palabras
     */
    @EventListener({Event.class})
    public void listWordsEvent() {
        Platform.runLater(this::listWords);
    }

    /**
     * Configura las columnas de la tabla para que use los get de la clase Palabra
     */
    private void configureColumns() {
        columnWord.setCellValueFactory(new PropertyValueFactory<>("word"));
        columnOccurrences.setCellValueFactory(new PropertyValueFactory<>("occurences"));
        columnOccurrences.setSortType(TableColumn.SortType.DESCENDING);
    }

}
