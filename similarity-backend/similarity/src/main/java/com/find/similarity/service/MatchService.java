package com.find.similarity.service;

import com.find.similarity.model.Matches;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface MatchService {
     Matches findMatches(MultipartFile file, String word, Boolean isFreq, Boolean isSimilar) throws IOException;
}
