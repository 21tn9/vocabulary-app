package org.example.repo;

import org.example.model.VocabularyWord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.*;

import java.util.List;

//Parameters are <EntityType, Primary Key Type>-.
//To define which table this repo is responsible for
@Repository
public interface VocabularyRepo extends JpaRepository<VocabularyWord, Long>{

    // Spring Boot automatically implements this query method based on its name.
    // It filters your vocabulary words by language
    List<VocabularyWord> findByLanguage(String language);
}
