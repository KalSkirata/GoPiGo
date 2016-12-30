#!/usr/bin/env python

import socket

def connect():
	s = socket.socket()
	host = ''
	port = 12345
	s.bind((host, port))
	s.listen(5)
	client, addr = s.accept()
        print ('Got connection from',addr)
        client.send('Thank you for connecting')
	return client

def listen(client):
  	data = client.recv(1024)
  	if data != "":
		return data
