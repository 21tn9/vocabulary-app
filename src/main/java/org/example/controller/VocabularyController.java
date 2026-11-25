package org.example.controller;

import org.example.model.VocabularyWord;
import org.example.service.VocabularyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
 * =========================================================================
 * CONCEPT 1: @CrossOrigin(origins = "*")
 * =========================================================================
 * Handles CORS (Cross-Origin Resource Sharing) security restrictions.
 * By default, web browsers block frontend apps (like a React or mobile app
 * running on port 3000) from talking to a backend server running on a
 * different port (port 8080) for security reasons.
 * Setting origins = "*" tells the backend to accept requests from anywhere.
 */
@RestController
@RequestMapping("/api/vocabulary")
@CrossOrigin(origins = "*") // Allows your future React/React Native app to connect easily
public class VocabularyController {
    private final VocabularyService vocabularyService;

    /*
     * =========================================================================
     * CONCEPT 2: @Autowired (Dependency Injection)
     * =========================================================================
     * Tells Spring Boot: "Find the instance of this service that you are
     * already managing in memory, and automatically plug it in here."
     * This keeps your code clean and avoids you having to manually write
     * something like 'VocabularyService service = new VocabularyService();'.
     */
    @Autowired
    public VocabularyController(VocabularyService vocabularyService) {
        this.vocabularyService = vocabularyService;
    }

    //All CRUD Methods that are in the service should be in the controller
    //Get all words
    @GetMapping
    public List<VocabularyWord> getAllWords(@RequestParam(required=false) String language){
        if(language != null && !language.isEmpty()) {
            return vocabularyService.getWordsByLanguage(language);
        }
        return vocabularyService.getAllWords();
    }

    /*
     * =========================================================================
     * CONCEPT 3 & 4: getWordById & ResponseEntity Breakdown
     * =========================================================================
     * - ResponseEntity: Represents the *entire* HTTP response (status code, headers,
     *   and body). We use it here so we can explicitly return a 404 Not Found error
     *   code if a word ID doesn't exist, rather than blindly returning a 200 OK.
     *
     * - @PathVariable Long id: Extracts the dynamic variable from the URL path
     *   (e.g., if the user visits /api/vocabulary/3, Spring grabs 3 as the 'id').
     *
     * - Optional<VocabularyWord>: A safe container that either holds the word
     *   or is empty (if the ID wasn't found in the database).
     *
     * - .map(ResponseEntity::ok): If the container HAS a word inside it, wrap it
     *   in a ResponseEntity with a 200 OK status code.
     *
     * - .orElse(...): If the container is EMPTY (ID doesn't exist), fall back to
     *   sending back a clean 404 Not Found response.
     */
    @GetMapping("/{id}")
    public ResponseEntity<VocabularyWord> getWordById(@PathVariable Long id){
        return vocabularyService.getWordById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST: Create a new vocabulary word
    @PostMapping
    public VocabularyWord createWord(@RequestBody VocabularyWord word) {
        return vocabularyService.saveWord(word);
    }

    // DELETE: Remove a word by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWord(@PathVariable Long id) {
        vocabularyService.deleteWord(id);
        return ResponseEntity.ok().build();
    }
}
