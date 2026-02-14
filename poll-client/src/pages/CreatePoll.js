import React, { useState } from "react";
import { createPoll } from "../api/pollApi";
import "../styles/createPoll.css";

function CreatePoll({ onPollCreated }) {
  const [question, setQuestion] = useState("");
  const [options, setOptions] = useState(["", ""]);
  const [success, setSuccess] = useState(false);

  const handleOptionChange = (index, value) => {
    const newOptions = [...options];
    newOptions[index] = value;
    setOptions(newOptions);
  };

  const addOption = () => setOptions([...options, ""]);

  const handleSubmit = async () => {
    try {
      const res = await createPoll({ question, options });
      setSuccess(true);
      onPollCreated(res.data.id);
    } catch {
      alert("Error creating poll");
    }
  };

  return (
    <div className="container">
      <h2>✨ Create Poll</h2>
      
      <div className="create-poll-form">
        <div className="question-section">
          <label>Question</label>
          <input
            className="question-input" /* Added this class */
            placeholder="e.g., What's your favorite programming language?"
            value={question}
            onChange={(e) => setQuestion(e.target.value)}
          />
        </div>

        <div className="options-section">
          <label>Options</label>
          {options.map((opt, i) => (
            <div key={i} className="option-row">
              <span className="option-number">{i + 1}</span>
              <input
                placeholder={`Option ${i + 1}`}
                value={opt}
                onChange={(e) => handleOptionChange(i, e.target.value)}
              />
            </div>
          ))}
        </div>

        <div className="button-group">
          <button className="add-btn" onClick={addOption}>
            ➕ Add Option
          </button>
        </div>
        
        <button className="submit-btn" onClick={handleSubmit}>
          🚀 Create Poll
        </button>
      </div>

      {success && <p className="success">✓ Poll created successfully! 🎉</p>}
    </div>
  );
}

export default CreatePoll;