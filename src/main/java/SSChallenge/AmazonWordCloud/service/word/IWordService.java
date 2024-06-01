package SSChallenge.AmazonWordCloud.service.word;

import SSChallenge.AmazonWordCloud.model.Word;

import java.util.List;

public interface IWordService {
    Word findWord(String word);
    void saveWord(Word word);
    List<Word> listWords();
}
