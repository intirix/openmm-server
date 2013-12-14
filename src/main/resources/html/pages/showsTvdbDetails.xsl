<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="2.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:ext="http://exslt.org/common">




	<xsl:template match="/">
		<view>
			<SinglePage>
				<Content>
					<img style="max-width:100%; min-width:100%;">
						<xsl:attribute name="src">/openmm<xsl:apply-templates select="/showsTvdbDetailsBean/banner/node()"/></xsl:attribute>
					</img>
					<h1 style="text-align: center;"><xsl:apply-templates select="/showsTvdbDetailsBean/name/node()"/></h1>
					<p><xsl:apply-templates select="/showsTvdbDetailsBean/description/node()"/></p>
					
					<form method="post">
						<input type="hidden" name="action" value="TVDBImport"/>
						<input type="hidden" name="tvdbId">
							<xsl:attribute name="value"><xsl:apply-templates select="/showsTvdbDetailsBean/id"/></xsl:attribute>
						</input>
						<input type="hidden" name="id">
							<xsl:attribute name="value"><xsl:apply-templates select="/showsTvdbDetailsBean/id"/></xsl:attribute>
						</input>
						<input type="submit" value="Import"/>
					</form>
					
					<h2>Seasons</h2>
					<ListView>
						<xsl:for-each select="/showsTvdbDetailsBean/seasons/tvdbSeasonInfoBean">
							<ListViewItem>
								<xsl:attribute name="href">showsTvdbSeasonDetails.html?sid=<xsl:apply-templates select="/showsTvdbDetailsBean/id"/>&amp;season=<xsl:apply-templates select="seasonNumber/node()"/></xsl:attribute>
								<xsl:apply-templates select="name/node()"/>
							</ListViewItem>
						</xsl:for-each>
					</ListView>
				</Content>
			</SinglePage>
		</view>
	</xsl:template>

</xsl:stylesheet>