CREATE CACHED TABLE OPENMM_FOLDERS (
	FOLDER_ID INTEGER GENERATED BY DEFAULT AS IDENTITY,
	AVAILABLE CHAR( 1 ) DEFAULT 'Y' NOT NULL,
	MOUNTPOINT VARCHAR( 128 ) DEFAULT '' NOT NULL,
	PATH VARCHAR( 512 ) NOT NULL,
	PRIMARY KEY ( FOLDER_ID ),
	CONSTRAINT OPENMM_FOLDERS_UN1 UNIQUE( PATH )
);

CREATE CACHED TABLE OPENMM_SHOWS (
	SHOW_ID INTEGER GENERATED BY DEFAULT AS IDENTITY,
	NAME VARCHAR( 64 ) NOT NULL,
	DISPLAY_NAME VARCHAR( 64 ) NOT NULL,
	TVDB_ID VARCHAR( 16 ),					-- optional TVDB show id
	TVDB_LANG VARCHAR( 4 ),					-- optional TVDB show id language
	IMDB_ID VARCHAR( 16 ),
	ZAP2IT_ID VARCHAR( 16 ),
	ACTIVE CHAR( 1 ) NOT NULL,				-- Y/N, are they actively making new episodes
	CONTENT_RATING VARCHAR( 8 ),
	BANNER_PATH VARCHAR( 128 ),
	DESCRIPTION VARCHAR( 255 ),
	PRIMARY KEY ( SHOW_ID )
);

CREATE CACHED TABLE OPENMM_SHOW_SEASONS (
	SEASON_ID INTEGER GENERATED BY DEFAULT AS IDENTITY,
	SHOW_ID INTEGER NOT NULL,
	NAME VARCHAR( 64 ) NOT NULL,
	NUMBER INTEGER DEFAULT 0 NOT NULL,
	PRIMARY KEY ( SEASON_ID ),
	FOREIGN KEY ( SHOW_ID ) REFERENCES OPENMM_SHOWS( SHOW_ID )
);

CREATE CACHED TABLE OPENMM_SHOW_EPISODES (
	EPISODE_ID INTEGER GENERATED BY DEFAULT AS IDENTITY,
	SEASON_ID INTEGER NOT NULL,
	TVDB_ID VARCHAR( 16 ),							-- optional TVDB episode id
	NAME VARCHAR( 128 ) NOT NULL,
	EPNUM INTEGER,
	DVD_EPNUM INTEGER,
	SCREENSHOT_PATH VARCHAR( 255 ),
	DESCRIPTION VARCHAR( 255 ),
	AIR_DATE VARCHAR( 16 ),
	RATING VARCHAR( 16 ),
	LAST_WATCHED DATE,
	WATCH_COUNT INTEGER DEFAULT 0 NOT NULL,
	PRIMARY KEY ( EPISODE_ID ),
	FOREIGN KEY ( SEASON_ID ) REFERENCES OPENMM_SHOW_SEASONS( SEASON_ID )
);

CREATE CACHED TABLE OPENMM_MOVIES (
	MOVIE_ID INTEGER GENERATED BY DEFAULT AS IDENTITY,
	NAME VARCHAR( 128 ) NOT NULL,
	IMDB_NUMBER VARCHAR( 16 ),
	TMDB_NUMBER VARCHAR( 16 ),
	YEAR INTEGER,
	LAST_WATCHED DATE,
	WATCH_COUNT INTEGER DEFAULT 0 NOT NULL,
	PRIMARY KEY ( MOVIE_ID )
);

CREATE CACHED TABLE OPENMM_LINKS (
	LINK_ID INTEGER GENERATED BY DEFAULT AS IDENTITY,
	AVAILABLE CHAR( 1 ) NOT NULL,							-- Y/N
	REF_TYPE_CODE CHAR( 1 ) NOT NULL,						-- I or E for internal or external
	EXT_SERVICE VARCHAR( 16 ),								-- name of the external service that hosts the content
	TYPE_CODE CHAR( 1 ) NOT NULL,							-- M or S for movie or show
	REF_ID INTEGER NOT NULL,								-- reference to the id from the other table
	URL VARCHAR( 128 ) NOT NULL,
	FILE_SIZE BIGINT,										-- size of the local file
	PRIMARY KEY ( LINK_ID )
);

-- Cache of web resources (Mostly images)
CREATE CACHED TABLE OPENMM_WEB_CACHE (
	CACHE_ID INTEGER GENERATED BY DEFAULT AS IDENTITY,
	HASH VARCHAR( 255 ) NOT NULL,					-- Hash of the url
	URL VARCHAR( 255 ) NOT NULL,					-- Url
	LAST_ACCESSED DATETIME,
	PRIMARY KEY( CACHE_ID ),
	CONSTRAINT OPENMM_WEB_CACHE_UN1 UNIQUE( HASH ),
	CONSTRAINT OPENMM_WEB_CACHE_UN2 UNIQUE( URL )
);