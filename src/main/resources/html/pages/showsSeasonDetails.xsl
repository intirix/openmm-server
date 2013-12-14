<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="2.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:ext="http://exslt.org/common">




	<xsl:template match="/">
		<view>
			<SinglePage>
				<Content>
					<img style="max-width:100%; min-width:100%;">
						<xsl:attribute name="src">/openmm<xsl:apply-templates select="/showsSeasonDetailsBean/bannerPath/node()"/></xsl:attribute>
					</img>
					<h1 style="text-align: center;"><xsl:apply-templates select="/showsSeasonDetailsBean/season/name/node()"/></h1>

					
					<h2>Episodes</h2>
					<ListView>
						<xsl:for-each select="//episodes/episode">
							<ListViewItem>
								<xsl:attribute name="href">showsEpisodeDetails.html?epid=<xsl:apply-templates select="id/node()"/></xsl:attribute>
								<img>
									<xsl:attribute name="src">/openmm<xsl:apply-templates select="screenshotPath/node()"/></xsl:attribute>
								</img>
								<h1><xsl:apply-templates select="epNum/node()"/>) <xsl:apply-templates select="name/node()"/></h1>
								<p><xsl:apply-templates select="description/node()"/></p>
							</ListViewItem>
						</xsl:for-each>
					</ListView>
				</Content>
			</SinglePage>
		</view>
	</xsl:template>

</xsl:stylesheet>