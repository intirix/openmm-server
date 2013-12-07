<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="2.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:ext="http://exslt.org/common">




	<xsl:template match="/">
		<view>
			<SinglePage>
				<Content>
					
					<h2>Movies</h2>
					<ListView>
						<xsl:for-each select="//movieDetails">
							<ListViewItem>
								<xsl:attribute name="href">moviesDetails.html?movieId=<xsl:apply-templates select="movie/id"/></xsl:attribute>
								<xsl:apply-templates select="movie/displayName/node()"/>
								<xsl:if test="available/node() = 'false'">
								<span class="ui-li-count">Unavailable</span>
								</xsl:if>
							</ListViewItem>
						</xsl:for-each>
					</ListView>
				</Content>
			</SinglePage>
		</view>
	</xsl:template>

</xsl:stylesheet>