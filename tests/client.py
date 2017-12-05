from socketIO_client_nexus import SocketIO, LoggingNamespace

def on_bbb_response(*args):
    print('on_bbb_response', args)

def dinle_message(*args):
    print(args)
    
def dinle_other(*args):
    print(args)
    
socket = SocketIO('http://akilli-ev.herokuapp.com/', 80)

socket.emit("ahmet", "Emre")
socket.emit("Message", "Selam")
# socket.on("Message", dinle_message)
# socket.on("Other Title", dinle_other)
socket.wait()
