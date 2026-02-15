import axios from "axios";

// const BASE_URL = "http://localhost:8080/api/polls";
const BASE_URL = "https://real-time-poll-app-9z96.onrender.com/api/polls";

export const createPoll = (data) => axios.post(BASE_URL, data);

export const getPoll = (id) => axios.get(`${BASE_URL}/${id}`);

export const votePoll = (id, data) => axios.post(`${BASE_URL}/${id}/vote`, data);

export const getResults = (id) => axios.get(`${BASE_URL}/${id}/results`);
