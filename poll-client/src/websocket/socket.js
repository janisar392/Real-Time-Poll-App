import SockJS from "sockjs-client";
import { Client } from "@stomp/stompjs";

const connectSocket = (pollId, onMessage) => {

  const socket = new SockJS("http://localhost:8080/ws");

  const stompClient = new Client({
    webSocketFactory: () => socket,
    reconnectDelay: 5000,

    onConnect: () => {
      console.log("✅ WebSocket connected");

      stompClient.subscribe(`/topic/poll/${pollId}`, (message) => {
        const data = JSON.parse(message.body);
        onMessage(data);
      });
    },

    onStompError: (frame) => {
      console.error("Broker error:", frame.headers["message"]);
    }
  });

  stompClient.activate();

  return stompClient;
};

export default connectSocket;
