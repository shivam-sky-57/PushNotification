# Push Notification Service

A real-time notification service built with Spring Boot, WebSockets (STOMP), and MongoDB. It allows users to subscribe to channels and receive instant push notifications whenever a new event or video is published.

## Tech Stack
- Java 21
- Spring Boot
- Spring WebSocket (STOMP + SockJS)
- MongoDB / MongoDB Atlas

## How It Works
1. A client connects via WebSocket at `/ws` and passes their `userId` in the STOMP connect headers.
2. The user subscribes to channels via REST API.
3. When a channel publishes an update, the backend queries MongoDB for that channel's subscribers and pushes the message to each user's private queue (`/user/queue/notifications`).
4. Notifications are saved in MongoDB so users can also retrieve their history later.

## Setup & Running

### 1. Configure MongoDB
Create a `.env` file in the project root (see `.env.example`):

```env
MONGODB_URI=mongodb+srv://<username>:<password>@cluster0.xxx.mongodb.net/pushNotificationDb?retryWrites=true&w=majority
```

*(Or point to local Mongo: `mongodb://localhost:27017/pushNotificationDb`)*

### 2. Start the App
Using the Maven wrapper:

**Windows:**
```powershell
.\mvnw.cmd spring-boot:run
```

**Linux / macOS:**
```bash
./mvnw spring-boot:run
```

The server runs on `http://localhost:8080`.

## Testing (Browser Dashboard)
Open `http://localhost:8080` in your browser. A built-in UI is provided for quick testing:

1. **Tab 1 (Subscriber):**
   - Enter `User ID: 101` -> click **Connect**.
   - Enter `Channel ID: 1` -> click **Subscribe**.
2. **Tab 2 (Publisher):**
   - Enter `Channel ID: 1`, enter a video title, and click **Publish Video Event**.
3. Tab 1 will receive the notification live on screen.

## REST API Reference

### Subscriptions
- `POST /api/subscriptions/subscribe`
  ```json
  { "userId": 101, "channelId": 1 }
  ```
- `POST /api/subscriptions/unsubscribe`
  ```json
  { "userId": 101, "channelId": 1 }
  ```
- `GET /api/subscriptions/channel/{channelId}` - Lists all user IDs subscribed to a channel

### Notifications
- `POST /api/notifications/publish`
  ```json
  { "channelId": 1, "videoTitle": "Spring Boot Tutorial" }
  ```
- `GET /api/notifications/user/{userId}` - Returns stored notification history for a user

## WebSocket Configuration
- **Connection URL**: `/ws` (SockJS enabled)
- **STOMP Connect Header**: `{ login: "<userId>" }`
- **User Queue**: `/user/queue/notifications`

