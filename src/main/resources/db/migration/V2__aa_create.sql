CREATE CACHED TABLE OPENMM_FOLDER_OPTIONS (
	FO_ID INTEGER GENERATED BY DEFAULT AS IDENTITY,
	FOLDER_ID INTEGER NOT NULL,
	K VARCHAR( 128 ) NOT NULL,
	V VARCHAR( 128 ) DEFAULT '' NOT NULL,
	PRIMARY KEY ( FO_ID ),
	CONSTRAINT OPENMM_FOLDER_OPTIONS_UN1 UNIQUE( FOLDER_ID, K ),
	FOREIGN KEY ( FOLDER_ID ) REFERENCES OPENMM_FOLDERS( FOLDER_ID )
);
