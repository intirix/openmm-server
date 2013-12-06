<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="2.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:ext="http://exslt.org/common">




	<xsl:template match="/">
		<view>
			<SinglePage>
				<Content>
					
					<h2>Movies</h2>
					<ListView>
						<xsl:for-each select="//moviePrefixCounts">
							<ListViewItem>
								<xsl:attribute name="href">moviesList.html?prefix=<xsl:apply-templates select="prefix"/></xsl:attribute>
								<xsl:apply-templates select="prefix/node()"/>
								<span class="ui-li-count"><xsl:apply-templates select="numMoviesAvailable/node()"/>/<xsl:apply-templates select="numMovies/node()"/></span>
							</ListViewItem>
						</xsl:for-each>
					</ListView>
				</Content>
			</SinglePage>
		</view>
	</xsl:template>

</xsl:stylesheet>