import React, { useState } from "react";
import axios from 'axios';
import FileUploader from "./Components/FileUploader";

const App = () => {
  const [selectedFile, setSelectedFile] = useState(null);
  const [word, setWord] = useState("");
  const [frequency, setFrequencyd] = useState("");
  const [similarWords, setSimilarWords] = useState("");

  const submitForm = () => {
    const formData = new FormData();

    formData.append("file", selectedFile);
    formData.append("word", word);
    formData.append("frequency", frequency);
    formData.append("similarWords", similarWords);

    axios
      .post("/proxy/match/upload", formData)
      .then((res) => {
        alert("Frequency is -" + res.data.frequency + " Simiar words are - " + res.data.similarWords);
      })
      .catch((err) => alert("File Upload Error"));
  };


  return (
    <div className="App">
      <form>
        <h1>Similar Words Finder</h1>

        Input word:
        <br />

        <input type="text" value={word}
          onChange={(e) => setWord(e.target.value)}
          placeholder="Input word to compare"
        />

        <br /><br />

        Do you want to see word frequency?
        <br />

        <input type="text" value={frequency}
          onChange={(e) => setFrequencyd(e.target.value)}
          placeholder="If yes, Enter true"
        />

        <br /><br />

        Do you want to see all matching words?
        <br />

        <input type="text" value={similarWords}
          onChange={(e) => setSimilarWords(e.target.value)}
          placeholder="If yes, Enter true"
        />

        <br /><br />

        <FileUploader
          onFileSelect={(file) => setSelectedFile(file)}
        />

        <br /><br />

        <button onClick={submitForm}>Submit</button>
        
      </form>
    </div>
  );
};

export default App;
