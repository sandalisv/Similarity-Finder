
package com.find.similarity.controller;

import com.find.similarity.exception.InvalidInputException;
import com.find.similarity.model.Matches;
import com.find.similarity.service.MatchService;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/match")
public class Controller {

    @Autowired
    MatchService service;

    @PostMapping(value = "/upload")
    public ResponseEntity<Matches> uploadFile(@RequestPart("file") MultipartFile file, @RequestPart("word") String word, @RequestPart(value = "frequency") String frequency, @RequestPart(value = "similarWords") String similarWords) throws InvalidInputException {
        String extension;
        Boolean isFreq = Boolean.valueOf(frequency);
        Boolean isSimilar = Boolean.valueOf(similarWords);
        Matches matches;
        try {

            if (file == null) {
                throw new InvalidInputException("Please upload a file");
            } else {
                extension = FilenameUtils.getExtension((file.getOriginalFilename()));
                if (extension.equals("txt")) {
                    matches = service.findMatches(file, word, isFreq, isSimilar);
                } else throw new InvalidInputException("Please upload a file with extension txt");
            }
            if (file.isEmpty()) {
                throw new InvalidInputException("File is empty");
            } else if (word == null || word.isEmpty()) {
                throw new InvalidInputException("Please enter a word to compare");
            }

        } catch (Exception e) {
            throw new InvalidInputException(e.getMessage());
        }
        return new ResponseEntity<>(matches, HttpStatus.OK);
    }

}
