package com.find.similarity.service;

import com.find.similarity.model.Matches;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;

@Service
public class MatchServiceImpl implements MatchService {
    //Find the matching words and calculate frequency
    public Matches findMatches(MultipartFile file, String word, Boolean isFreq, Boolean isSimilar) throws IOException {
        int frequency = 0;
        HashSet<String> similarWords = new HashSet<>();
        Matches matches = new Matches();
        InputStream is = null;
        Scanner sc2 = null;
        Scanner s2 = null;
        try {
            is = file.getInputStream();
            if (is != null) {
                sc2 = new Scanner(new InputStreamReader(is));
            }
            while (sc2!= null && sc2.hasNextLine()) {
                s2 = new Scanner(sc2.nextLine());
                while (s2!= null && s2.hasNext()) {
                    String s = s2.next();
                    if (Boolean.TRUE.equals(isSimilar) && calculateLevenshteinDist(s, word) == 1) {
                        similarWords.add(s);
                    }
                    //Distance 0 means 2 words have a exact match
                    if (Boolean.TRUE.equals(isFreq) && calculateLevenshteinDist(s, word) == 0) {
                        frequency++;
                    }
                }
            }
        } catch (Exception e) {
            e.getMessage();
        } finally {
            is.close();
            sc2.close();
            s2.close();
        }

        matches.setFrequency(String.valueOf(frequency));
        matches.setSimilarWords(similarWords);

        return matches;
    }

    //calculate Levenshtein Distance
    private int calculateLevenshteinDist(String str, String word) {

        int[][] distanceMatrix = new int[str.length() + 1][word.length() + 1];

        for (int i = 0; i <= str.length(); i++) {
            for (int j = 0; j <= word.length(); j++) {
                if (i == 0) {
                    distanceMatrix[i][j] = j;
                } else if (j == 0) {
                    distanceMatrix[i][j] = i;
                } else {
                    distanceMatrix[i][j] = minimumEdits(
                            distanceMatrix[i - 1][j - 1]+ replacementCount(str.charAt(i - 1), word.charAt(j - 1)), // replace
                            distanceMatrix[i - 1][j] + 1, // delete
                            distanceMatrix[i][j - 1] + 1); // insert
                }

            }
        }
        
        return distanceMatrix[str.length()][word.length()];
    }

    private int replacementCount(char c1, char c2) {
        return c1 == c2 ? 0 : 1;
    }

    private int minimumEdits(int... numbers) {
        return Arrays.stream(numbers).min().orElse(
                Integer.MAX_VALUE);
    }
}
