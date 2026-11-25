package org.example.service;

import org.example.model.VocabularyWord;
import org.example.repo.VocabularyRepo;
import org.springframework.stereotype.*;

import java.util.List;
import java.util.Optional;

@Service
public class VocabularyService {

    private final VocabularyRepo vocabularyRepo;

    //Constructor
    public VocabularyService(VocabularyRepo vocabularyRepo) {
        this.vocabularyRepo = vocabularyRepo;
    }

    //Get all the vocabs
    public List<VocabularyWord> getAllWords(){
        return vocabularyRepo.findAll();
    }

    //Get all the vocabs for a specific language
    public List<VocabularyWord> getWordsByLanguage(String language){
        return vocabularyRepo.findByLanguage(language.trim());
    }

    public Optional<VocabularyWord> getWordById(Long id){
        return vocabularyRepo.findById(id);
    }

    public VocabularyWord saveWord(VocabularyWord word){
        // Business logic / transformation: normalize language to lowercase
        if (word.getLanguage() != null) {
            word.setLanguage(word.getLanguage().toLowerCase().trim());
        }
        return vocabularyRepo.save(word);
    }

    public void deleteWord(Long id) {
        vocabularyRepo.deleteById(id);
    }
}
