$(function() {
	var webSocket;
	var username;

	var chatLog = document.getElementById("chatLog");

//	document.getElementById("joinButton").addEventListener("touchend", join, false);
//	document.getElementById("leaveButton").addEventListener("touchend", leave, false);
//	document.getElementById("sendButton").addEventListener("touchend", sendMessage, false);
	document.getElementById("joinButton").addEventListener("click", join, false);
	document.getElementById("leaveButton").addEventListener("click", leave, false);
	document.getElementById("sendButton").addEventListener("click", sendMessage, false);
	
	var usernameField = document.getElementById("username");
	var messageField = document.getElementById("message");
	
	// start web socket handshake and attach event listeners to the newly created web socket
	function join() {
		if(!webSocket && usernameField.value) {
			username = usernameField.value;
			webSocket = new WebSocket("ws://localhost/ChatWebSocketServer/ChatWebSocketServlet");
			webSocket.onopen = startNegotiation;
			webSocket.onmessage = onmessage;
			webSocket.onerror = onerror;
			webSocket.onclose = onclose;
		}
	}

	function leave() {
		// notify all users that user is leaving the chat room
		webSocket.send(username + ":::" + "just left the chat room");
		close();
		document.getElementById('leaveButton').classList.add('is-disabled');
		document.getElementById('joinButton').classList.remove('is-disabled');
		chatLog.innerHTML = "";
	}

	function sendMessage() {
		if(messageField.value) {
			// send message to the socket channel
			webSocket.send(username + ":::" + message.value);
		}
	}

	function startNegotiation() {
		console.log("Connection opened");
		// start the negotiation of the chosen username
		webSocket.send("negotiateUsername" + ":::" + username);
	};

	// manage incoming message
	function onmessage(event) {
		console.log("New message: " + event.data);
		// username negotiation message
		if(event.data.indexOf("negotiateUsername") === 0) {
			if(event.data == "negotiateUsername:::OK") {
				webSocket.send(username + ":::" + "joined the chat room");
				
				document.getElementById('joinButton').classList.add('is-disabled');
				document.getElementById('sendButton').classList.remove('is-disabled');
				document.getElementById('leaveButton').classList.remove('is-disabled');
			} else {
				chatLog.append("USERNAME ALREADY TAKEN<br/>");
			}
		} else {
			// standard message from another user
			var user = event.data.split(":::")[0];
			var msg = event.data.split(":::")[1];
			console.log(msg);
			if(!(user == username)) {
				chatLog.innerHTML += '<div class="my"> <p class="name">' + user + '</p>' + msg + '</div>';
			} else {
				chatLog.innerHTML += '<div class="other"> <p class="name">' + user + '</p>' + msg + '</div>';
			}
			chatLog.scrollTop = chatLog.scrollHeight;
		}
	};

	function onclose(event) {
		console.log("Connection closed");
		// free the webSocket variable
		webSocket = null;
		// empty the chat log
		chatLog.html("");
		document.getElementById('sendButton').classList.add('is-disabled');
		document.getElementById('joinButton').classList.remove('is-disabled'); 
	}
 
	function error(event) { 
		console.log("Error");
		// on error, we close the web socket 
		close();
	};

	function close(event) {
		webSocket.close();
	};
});

