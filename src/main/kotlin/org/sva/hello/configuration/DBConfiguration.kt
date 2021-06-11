package org.sva.hello.configuration

import org.springframework.context.annotation.Configuration
import org.springframework.r2dbc.core.DatabaseClient

@Configuration
class DBConfiguration(db: DatabaseClient) {
    init {
        val initDb = db.sql {
            """ CREATE TABLE IF NOT EXISTS profile (
                    id SERIAL PRIMARY KEY,
                    first_name varchar,
                    last_name varchar,
                    birth_date datetime
                );
                CREATE TABLE IF NOT EXISTS health_record(
                    id SERIAL PRIMARY KEY,
                    profile_id bigint NOT NULL,
                    temperature double,
                    blood_pressure double,
                    heart_rate double,
                    date date
                );
            """
        }
        initDb.then().subscribe()
    }
}