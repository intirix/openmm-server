<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="2.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:ext="http://exslt.org/common">




	<xsl:template match="/">
		<view>
			<SinglePage>
				<Content>
					<h1>${ui.shows.menu.heading}</h1>
					<ListView>
						<xsl:if test="showsBean/hasTVDBKey = 'true'">
						<ListViewItem href="/openmm/html/showsTvdbSearch.html">${ui.shows.menu.tvdb}
						</ListViewItem>
						</xsl:if>
						<ListViewItem href="/openmm/html/showsList.html">${ui.shows.menu.list}
						</ListViewItem>
					</ListView>
				</Content>
			</SinglePage>
		</view>
	</xsl:template>

</xsl:stylesheet>