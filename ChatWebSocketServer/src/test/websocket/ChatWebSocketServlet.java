package test.websocket;

import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import javax.servlet.http.HttpServletRequest;
import org.eclipse.jetty.websocket.WebSocket;
import org.eclipse.jetty.websocket.WebSocketServlet;

public class ChatWebSocketServlet extends WebSocketServlet {

	private final Set<ChatWebSocket> webSockets = new CopyOnWriteArraySet<ChatWebSocket>();

	// HTTP HANDSHAKE
	public WebSocket doWebSocketConnect(HttpServletRequest request,
			String protocol) {
		System.out.println(request.getRemoteAddr());
		return new ChatWebSocket();
	}

	private class ChatWebSocket implements WebSocket.OnTextMessage {

		// stores the web socket channel
		private Connection connection;
		
		// stores the username of the connected client
		private String username;

		// OPEN WEB SOCKET
		public void onOpen(Connection connection) {
			// store the opened connection
			this.connection = connection;
			// add ChatWebSocket in the global list of ChatWebSocket instances
			webSockets.add(this);
		}

		// MESSAGE RECEIVED ON THE WEB SOCKET
		public void onMessage(String data) {
			System.out.println(data);
			try {
				if(data.startsWith("negotiateUsername")) {
					// username negotiation
					boolean found = false;
					int i = 0;
					ChatWebSocket currentWebSocket;
					while(!found && i<webSockets.size()) {
						currentWebSocket = (ChatWebSocket) webSockets.toArray()[i];
						if(data.split(":::")[1].equals(currentWebSocket.getUsername())) {
							found = true;
						}
						i++;
					}
					if(!found) {
						this.username = data.split(":::")[1];
						this.connection.sendMessage("negotiateUsername:::OK");
					} else {
						this.connection.sendMessage("negotiateUsername:::NO");
					}
				} else {
					// loop for each instance of ChatWebSocket to send the message to each client WebSockets
					for (ChatWebSocket webSocket : webSockets) {
						// send a message to each client WebSocket
						webSocket.connection.sendMessage(data);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				// close the ChatWebSocket client side
				this.connection.close();
			}

		}

		// CLOSE WEB SOCKET
		public void onClose(int closeCode, String message) {
			webSockets.remove(this);
		}

		public String getUsername() {
			return username;
		}
	}
}
