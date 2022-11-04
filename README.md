# Similarity-Finder
Similar words finder with frontend prototype in react

# This application will find the simiarity between a words in a file and a given word using Levenshtein distance calculation algorithm.
# If the Levenshtein distance = 0, word will be a exact match with the comparing word in the document.
# Words are considered as similar only if Levenshtein distance = 1.
# Frequency of exacty similar words and a list of words considered as simialr (without exact matches) will be returned.

# Technologies used
## Java
## Spring Boot
## React

## API Description

# This is a POST request with attahced file and other data as described.

# URL - http://localhost:8080/match/upload
# Insert following data as form-data using Postman.

## file - attach the file
## word - Word to be matched against the file
## frequency - Enter true If you need the frequency count
## similarWords - Enter true If you need the similar words count

# Assumption - These form data will be correctly set by front end. true false values will be set using a check box,radio button or dropdown selection. So, any input other that true and false are not considered for fields frequency,similarWords.

# Time spent to backend - Approximately 6 hours

# Future enhancements
## Better input handling, with restrictions on the file size to be attached.
## Better customized exception handling.
## Performance enhancements.
## Unit testing.
## We can let the user decide how much similarity between words they want to find and according to the user input we can consider the Levenshtein > 1 also.

# Front end
## A simple prototype in React is done. It is just a simple form with no UI styles and the output will be shown as a alert.

# Future enhancements in Front end
## Better User friendly UI with styles
## Output should be shown in a better way (for now it's only a alert)
## Betetr exception handling and testing

# Time spent for UI - less than 2 hours.

