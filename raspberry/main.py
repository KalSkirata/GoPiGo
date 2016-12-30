#!/usr/bin/env python

import db
import sensor
import server
import move

database = db.connect_db()
cur = db.get_cursor(database)
db.reset_table(cur)
client=server.connect()
while True:
	db.insert_data(database, cur, sensor.measure())
	move.move(server.listen(client))
db.display_db(cur)
db.close_db(database)
