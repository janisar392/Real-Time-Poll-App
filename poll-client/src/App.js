import React, { useEffect } from "react";
import { v4 as uuidv4 } from "uuid";
import { BrowserRouter as Router, Routes, Route, useNavigate } from "react-router-dom";
import CreatePoll from "./pages/CreatePoll";
import PollView from "./pages/PollView";
import "./styles/app.css";

function AppWrapper() {
  const navigate = useNavigate();

  useEffect(() => {
    if (!localStorage.getItem("voterToken")) {
      localStorage.setItem("voterToken", uuidv4());
    }
  }, []);

  const handlePollCreated = (id) => {
    navigate(`/poll/${id}`);
  };

  return (
    <div style={{ width: "100%", padding: "20px", minHeight: "100vh", display: "flex", flexDirection: "column" }}>

      {/* Header */}
      <div className="app-header">
        <h1 className="app-title">Real-Time Poll App</h1>
        <div className="app-subtitle">CREATE · VOTE · SEE RESULTS INSTANTLY</div>
      </div>

      {/* Main content */}
      <div style={{ flex: 1 }}>
        <Routes>
          <Route path="/" element={<CreatePoll onPollCreated={handlePollCreated} />} />
          <Route path="/poll/:id" element={<PollView />} />
        </Routes>
      </div>

  <footer style={{
  marginTop: "60px",
  padding: "20px 0",
  textAlign: "center",
  color: "#ffffffcc",
  fontSize: "14px",
  borderTop: "1px solid rgba(255,255,255,0.2)"
}}>
   <strong>Designed & developed By : Janisar Akhtar — Software Engineer</strong> 
</footer>


    </div>
  );
}

function App() {
  return (
    <Router>
      <AppWrapper />
    </Router>
  );
}

export default App;
