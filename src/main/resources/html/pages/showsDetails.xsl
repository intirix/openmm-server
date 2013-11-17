<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="2.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:ext="http://exslt.org/common">




	<xsl:template match="/">
		<view>
			<SinglePage>
				<Content>
					<img style="max-width:100%; min-width:100%;">
						<xsl:attribute name="src"><xsl:apply-templates select="/showsDetailsBean/show/bannerPath/node()"/></xsl:attribute>
					</img>
					<h1 style="text-align: center;"><xsl:apply-templates select="/showsDetailsBean/show/displayName/node()"/></h1>
					<p><b><xsl:apply-templates select="/showsDetailsBean/show/contentRating/node()"/></b></p>
					<p><xsl:apply-templates select="/showsDetailsBean/show/description/node()"/></p>

					
					<h2>Seasons</h2>
					<ListView>
						<xsl:for-each select="//seasonDetails">
							<ListViewItem>
								<xsl:attribute name="href">showsSeasonDetails.html?sid=<xsl:apply-templates select="/showsDetailsBean/show/id"/>&amp;season=<xsl:apply-templates select="season/number/node()"/></xsl:attribute>
								<xsl:apply-templates select="season/name/node()"/>
								<span class="ui-li-count"><xsl:apply-templates select="numEpisodesAvailable/node()"/>/<xsl:apply-templates select="numEpisodes/node()"/></span>
							</ListViewItem>
						</xsl:for-each>
					</ListView>
				</Content>
			</SinglePage>
		</view>
	</xsl:template>

</xsl:stylesheet>