import socket

IP = "52.29.7.52"
UDP_PORT = 28979
MESSAGE = "HELLO"

sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM) # UDP
sock.sendto(bytes(MESSAGE, "utf-8"), (IP, 80))