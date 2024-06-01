package SSChallenge.AmazonWordCloud.service.word;

import SSChallenge.AmazonWordCloud.model.Word;
import SSChallenge.AmazonWordCloud.repository.WordRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WordService implements IWordService {

    private final WordRepository wordRepository;

    public WordService(WordRepository wordRepository) {
        this.wordRepository = wordRepository;
    }

    /**
     * Busca una palabra en la base de datos.
     * @param word Palabra a buscar.
     * @return  Palabra o Null si no la encontro
     */
    @Override
    public Word findWord(String word) {
        return wordRepository.findById(word).orElse(null);
    }

    /**
     * Guarda una palabra en la base de datos. Si ya existe, actualiza la cantidad de ocurrencias.
     * @param word Palabra a guardar.
     */

    @Override
    public void saveWord(Word word) {
        Word wordExist = findWord(word.getWord());
        if(wordExist != null){
            int occurences = wordExist.getOccurences();
            wordExist.setOccurences(occurences+1);
            wordRepository.save(wordExist);
        }else {
            wordRepository.save(word);
        }
    }

    /**
     * Lista las palabras en la base de datos.
     * @return Lista de palabras.
     */
    @Override
    public List<Word> listWords() {
        return wordRepository.findAll();
    }
}
