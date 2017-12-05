import socket

host = "127.0.0.1"
port = 1234
buf = 1024
calistir = (host,port)

s = socket.socket(socket.AF_INET,socket.SOCK_STREAM)
s.connect(calistir)

while True:
	gonder = input(">> ")
	s.send(gonder.encode())

	if gonder == "q":
		print("Baglanti Kapatildi..")
		break


s.close()

