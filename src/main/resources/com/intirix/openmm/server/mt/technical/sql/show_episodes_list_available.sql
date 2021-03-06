SELECT OPENMM_SHOW_EPISODES.EPISODE_ID,
	(
		SELECT COUNT( * ) FROM OPENMM_LINKS
			WHERE OPENMM_LINKS.REF_ID = EPISODE_ID
				AND OPENMM_LINKS.TYPE_CODE = 'S'
				AND OPENMM_LINKS.AVAILABLE = 'Y'
				AND OPENMM_LINKS.REF_TYPE_CODE = 'I'
	) NUM_LINK_INT,
	(
		SELECT COUNT( * ) FROM OPENMM_LINKS
			WHERE OPENMM_LINKS.REF_ID = EPISODE_ID
				AND OPENMM_LINKS.TYPE_CODE = 'S'
				AND OPENMM_LINKS.AVAILABLE = 'Y'
				AND OPENMM_LINKS.REF_TYPE_CODE = 'E'
	) NUM_LINK_EXT
	FROM OPENMM_SHOWS
	INNER JOIN OPENMM_SHOW_SEASONS ON ( OPENMM_SHOWS.SHOW_ID = OPENMM_SHOW_SEASONS.SHOW_ID) 
	INNER JOIN OPENMM_SHOW_EPISODES ON ( OPENMM_SHOW_SEASONS.SEASON_ID = OPENMM_SHOW_EPISODES.SEASON_ID )
	WHERE OPENMM_SHOW_SEASONS.SEASON_ID = ?
	ORDER BY OPENMM_SHOW_EPISODES.EPNUM
--	LEFT OUTER JOIN OPENMM_LINKS ON ( OPENMM_SHOW_EPISODES.EPISODE_ID = OPENMM_LINKS.REF_ID )
--	WHERE OPENMM_SHOWS.SHOW_ID = ? AND OPENMM_SHOW_SEASONS.NUMBER = ?
--		AND TYPE_CODE = 'S' AND AVAILABLE = 'Y'
--	GROUP BY EPISODE_ID