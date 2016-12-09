#!/usr/bin/env python

import socket

s = socket.socket()
host = ''
port = 12345
s.bind((host, port))

s.listen(5)
while True:
  client, addr = s.accept()
  print ('Got connection from',addr)
  client.send('Thank you for connecting')
  data = client.recv(1024)
  if data != "":
	print "data =", data

client.close()
