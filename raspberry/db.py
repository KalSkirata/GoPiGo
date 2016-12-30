#!/usr/bin/env python

import MySQLdb

def connect_db():
	db = MySQLdb.connect("localhost", "root", "root", "db")
	return db

def get_cursor(db):
	curs=db.cursor()
	return curs

def close_db(db):
        db.close()

def display_db(curs):
	curs.execute("SELECT * FROM sensor")
	print "\n id  measure"
	print "--------------"

	for reading in curs.fetchall():
		print str(str(reading[0])+"  "+str(reading[1]))

def insert_data(db, curs, measure):
	try:
		curs.execute("""INSERT INTO sensor (measure) VALUES (%s)""", measure)
		db.commit()
	except:
		print("Insert in DB failed, the database is being rolled back")
		db.rollback()

def reset_table(curs):
	curs.execute("truncate sensor")
