package org.example.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

//Create a Table of an Entity and the attributes to put it in a database.
@Entity
@Getter
@Setter
@Table(name="vocabulary_words")
public class VocabularyWord {

    //Defines a Primary key
    @Id
    private Long id;


    @Column(nullable= false)
    public String language;

    @Column(nullable=false)
    public String actualWriting;

    public String reading;

    @Column(nullable=false)
    private String typeOfWords;

    // Translations
    private String englishTranslation;
    private String germanTranslation;

    //Example sentences
    private String exampleSentenceTarget;
    private String exampleSentenceEnglish;
    private String exampleSentenceGerman;
}
