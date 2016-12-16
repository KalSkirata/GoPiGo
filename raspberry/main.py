#!/usr/bin/env python

import db
import sensor

database = db.connect_db()
cur = db.get_cursor(database)
db.reset_table(cur)
db.insert_data(database, cur, sensor.measure())
db.display_db(cur)
db.close_db(database)
