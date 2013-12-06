<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="2.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:ext="http://exslt.org/common">




	<xsl:template match="/">
		<view>
			<SinglePage>
				<Content>
					<h1>${ui.movies.menu.heading}</h1>
					<ListView>
						<xsl:if test="moviesBean/hasRTKey = 'true'">
						<ListViewItem href="/html/moviesRTSearch.html">${ui.movies.menu.rt}
						</ListViewItem>
						</xsl:if>
						<ListViewItem href="/html/moviesPrefixList.html">${ui.movies.menu.list}
						</ListViewItem>
					</ListView>
				</Content>
			</SinglePage>
		</view>
	</xsl:template>

</xsl:stylesheet>