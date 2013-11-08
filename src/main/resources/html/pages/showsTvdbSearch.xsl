<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="2.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:ext="http://exslt.org/common">




	<xsl:template match="/">
		<view>
			<SinglePage>
				<Content>
					<h1>${ui.shows.tvdbSearch.heading}</h1>
					<form method="post" data-transition="slide">
						<input type="hidden" name="action" value="Empty"/>
						<label for="tvdbSearchBox">${ui.shows.tvdbSearch.searchLabel}</label>
						<input type="search" name="query" id="tvdbSearchBox" value="">
							<xsl:attribute name="placeholder">${ui.shows.tvdbSearch.searchPlaceholder}</xsl:attribute>
						</input>
						<input type="submit">
							<xsl:attribute name="value">${ui.shows.tvdbSearch.searchButton}</xsl:attribute>
						</input>
					</form>
					
					<xsl:if test="//results[@length>0]">
						<ListView>
							<xsl:for-each select="//tvdbShowSearchResultBean">
								<ListViewItem>
									<xsl:attribute name="href">showsTvdbDetails.html?id=<xsl:apply-templates select="id/node()"/></xsl:attribute>
									<xsl:apply-templates select="displayName/node()"/>
									( <xsl:apply-templates select="lang/node()"/> ) -
									<xsl:apply-templates select="firstAirDate/node()"/>
								</ListViewItem>
							</xsl:for-each>
						</ListView>
					</xsl:if>
				</Content>
			</SinglePage>
		</view>
	</xsl:template>

</xsl:stylesheet>