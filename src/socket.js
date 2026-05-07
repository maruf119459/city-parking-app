import { io } from "socket.io-client";
export const socket = io("https://city-parking-backend.onrender.com", {
  transports: ["websocket", "polling"],
  reconnection: true,
  reconnectionAttempts: 5,
  reconnectionDelay: 2000,
  timeout: 10000,
  withCredentials: true
});
