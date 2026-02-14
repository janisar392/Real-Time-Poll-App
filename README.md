📊 Real-Time Poll App

A full-stack real-time polling application that allows users to create polls, share links, vote, and view live results instantly. Built with a modern event-driven architecture using WebSockets for real-time updates and MongoDB for persistence.

🚀 Live Demo

👉 Frontend: [Add Netlify URL here]
👉 Backend API: [Add Render URL here]

🧠 Problem Statement

This application enables users to:

Create polls with multiple options

Share polls via link

Vote in real time

View live results without refreshing

Prevent duplicate or unfair voting

🏗️ Architecture Overview
Frontend (React)
   ↓ REST API
Backend (Spring Boot)
   ↓
MongoDB Atlas (Persistence)
   ↓
WebSocket Broker (Real-time updates)

🛠️ Tech Stack
Frontend

React

Axios

SockJS

STOMP WebSocket client

UUID

CSS

Backend

Spring Boot

Spring WebSocket (STOMP)

Spring Data MongoDB

Lombok

Database

MongoDB Atlas

Deployment

Netlify (Frontend)

Render (Backend)

✨ Features
📝 Poll Creation

Create poll with question and multiple options

Input validation enforced

Generates shareable link

🔗 Shareable Links

Poll accessible via URL

Anyone with link can vote

⚡ Real-Time Updates

Vote counts update instantly

No page refresh required

WebSocket broadcasting

🛡️ Anti-Abuse Mechanisms

Token-based voting (UUID stored in browser)

IP-based duplicate vote prevention

📈 Live Results

Percentage bars

Total vote counts

Automatic UI refresh

🎨 User Experience

Loading spinner

Success messages

Copy link button

Clean responsive UI

🛡️ Fairness / Anti-Abuse Mechanisms

This application implements two mechanisms to prevent duplicate or unfair voting:

1️⃣ Browser Token (UUID)
A unique token is generated and stored in localStorage to identify a user session and prevent multiple votes from the same browser.

2️⃣ IP Address Tracking
The backend checks the client IP address to prevent multiple votes from the same network source.

These combined checks ensure voting integrity.

⚠️ Edge Cases Handled

Empty question validation

Less than two options prevented

Invalid poll ID handling

Invalid option selection handling

Duplicate vote detection

API error responses

Loading states during data fetch

Network error handling

📦 Data Persistence

All polls and votes are stored in MongoDB Atlas.
Results remain available after refresh or server restart.

🔄 Real-Time Workflow

User submits vote

Backend validates vote

Vote stored in database

Backend broadcasts update via WebSocket

All clients receive update

UI updates instantly

🧩 API Endpoints
Create Poll
POST /api/polls

Get Poll
GET /api/polls/{id}

Vote
POST /api/polls/{id}/vote

Results
GET /api/polls/{id}/results

💻 Local Setup
Backend
cd poll-server
mvn clean install
mvn spring-boot:run


Configure MongoDB connection in application.properties.

Frontend
cd poll-client
npm install
npm start

📊 Known Limitations

IP detection may be inaccurate behind proxies or NAT

No authentication system (anonymous voting)

Token stored in browser can be cleared

No rate limiting implemented

Poll owner controls not implemented

No poll expiration feature

🔮 Future Improvements

Add authentication

Add poll expiration

Rate limiting

Admin dashboard

Better analytics

Mobile optimization

Docker deployment

Redis caching

Horizontal scaling

📸 Screenshots

(Add screenshots here before submission)

🧪 Testing

Test scenarios covered:

Create poll

Share link

Vote once

Duplicate vote blocked

Real-time updates across tabs

Page refresh persistence

📚 Learning Outcomes

This project demonstrates:

Full-stack development

Real-time system design

REST API design

WebSocket communication

Database schema design

Error handling

UX considerations

Production architecture concepts

👨‍💻 Author

Janisar Akhtar

📄 License

This project is created for educational and assessment purposes.
