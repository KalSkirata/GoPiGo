#!/usr/bin/env python

from gopigo import *
import sys

def move(data):
	if data[:3]=="fwd":
		fwd()
	elif data[:3]=="bwd":
		bwd()
	elif data[:4]=="righ":
		right()
	elif data[:4]=="left":
		left()
	elif data[:4]=="stop":
		stop()		
	elif data[:2]=="up":
		increase_speed()
	elif data[:4]=="down":
		decrease_speed()
