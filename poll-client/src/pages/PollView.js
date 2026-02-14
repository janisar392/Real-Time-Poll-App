import React, { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import { getPoll, votePoll } from "../api/pollApi";
import connectSocket from "../websocket/socket";
import "../styles/pollView.css";

function PollView() {

  const { id: pollId } = useParams();

  const [poll, setPoll] = useState(null);
  const [hasVoted, setHasVoted] = useState(false);

  useEffect(() => {

    loadPoll();

    const client = connectSocket(pollId, (updatedPoll) => {
      setPoll(updatedPoll);
    });

    return () => client.deactivate();

  }, [pollId]);

  const loadPoll = async () => {
    const res = await getPoll(pollId);
    setPoll(res.data);
  };

  const vote = async (optionId) => {
    try {
      const voterToken = localStorage.getItem("voterToken");
      await votePoll(pollId, { optionId, voterToken });
      setHasVoted(true);
    } catch (err) {
      alert(err.response?.data?.message || "Error voting");
      setHasVoted(true);
    }
  };

  const copyLink = () => {
    navigator.clipboard.writeText(window.location.href);
    alert("Link copied!");
  };

  if (!poll) return <div className="spinner">Loading...</div>;

  const totalVotes = poll.options.reduce((sum, o) => sum + o.votes, 0);

  return (
    <div className="container">

      <button className="copy-btn" onClick={copyLink}>Copy Poll Link</button>

      <h2>{poll.question}</h2>

      {poll.options.map((opt) => {

        const percent = totalVotes === 0 ? 0 : Math.round((opt.votes / totalVotes) * 100);

        return (
          <div key={opt.id} className="option-card">

            <button disabled={hasVoted} onClick={() => vote(opt.id)}>
              {opt.text} — {opt.votes} votes ({percent}%)
            </button>

            <div className="progress-bar">
              <div
                className="progress-fill"
                style={{ width: `${percent}%` }}
              />
            </div>

          </div>
        );
      })}

      {hasVoted && <p className="success">You have voted ✔</p>}

    </div>
  );
}

export default PollView;
