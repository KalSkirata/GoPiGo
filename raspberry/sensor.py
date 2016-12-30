#!/usr/bin/env python
# ! Attach Ultrasonic sensor to A1 Port.
from gopigo import *
import time

def measure():
	return us_dist(15)

def security(limit):
	if us_dist(15)<limit:
		print "Stop"
		stop()
