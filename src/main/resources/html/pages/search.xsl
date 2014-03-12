<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="2.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:ext="http://exslt.org/common">




	<xsl:template match="/">
		<view>
			<SinglePage>
				<Content>
					<h1>${ui.search.heading}</h1>
					<form method="get" action="search.html" data-transition="slide">
						<input type="hidden" name="action" value="Empty"/>
						<label for="searchBox">${ui.search.searchLabel}</label>
						<input type="search" name="query" id="searchBox" value="">
							<xsl:attribute name="value"><xsl:apply-templates select="//query/node()"/></xsl:attribute>
							<xsl:attribute name="placeholder">${ui.search.searchPlaceholder}</xsl:attribute>
						</input>
						
						<label for="searchReindex">${ui.search.searchReindexLabel}</label>
						<FlipSwitch id="searchReindex" name="reindex" data-on-text="Yes" data-off-text="No"/>
						<input type="submit">
							<xsl:attribute name="value">${ui.search.searchButton}</xsl:attribute>
						</input>
					</form>
					
					<xsl:if test="//results[@length>0]">
						<ListView>
							<xsl:for-each select="//searchResult">
								<ListViewItem>
									<xsl:choose>
										<xsl:when test="type/node() = 'movie'">
											<xsl:attribute name="href">/openmm/html/moviesDetails.html?movieId=<xsl:apply-templates select="refId"/></xsl:attribute>
										</xsl:when>
										<xsl:when test="type/node() = 'show'">
											<xsl:attribute name="href">/openmm/html/showsDetails.html?id=<xsl:apply-templates select="refId"/></xsl:attribute>
										</xsl:when>
										<xsl:when test="type/node() = 'episode'">
											<xsl:attribute name="href">/openmm/html/showsEpisodeDetails.html?epid=<xsl:apply-templates select="refId"/></xsl:attribute>
										</xsl:when>
									</xsl:choose>
									
									<h1><xsl:apply-templates select="title/node()"/></h1>
									<p><xsl:apply-templates select="description/node()"/></p>
								</ListViewItem>
							</xsl:for-each>
						</ListView>
					</xsl:if>
				</Content>
			</SinglePage>
		</view>
	</xsl:template>

</xsl:stylesheet>