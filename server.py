import socket
import threading

# Global score list with client names
scores = {}

def handle_client(client_socket):
    global scores
    try:
        while True:
            # Receive score and client name from client
            data = client_socket.recv(1024)
            if not data:
                break
            score, client_name = data.decode().split(":")
            score = int(score)
            # Update score list with client name
            scores[client_name] = score
            # Send updated score list back to client
            client_socket.sendall(str(scores).encode())
    finally:
        client_socket.close()

def main():
    global scores
    # Initialize server
    server = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    server.bind(('localhost', 9999))
    server.listen(5)
    print("Server started. Listening on port 9999...")
    try:
        while True:
            # Accept incoming connections
            client_socket, addr = server.accept()
            print("Accepted connection from", addr)
            # Start a new thread to handle the client
            client_handler = threading.Thread(target=handle_client, args=(client_socket,))
            client_handler.start()
    except KeyboardInterrupt:
        print("Shutting down server...")
        server.close()

if __name__ == "__main__":
    main()
