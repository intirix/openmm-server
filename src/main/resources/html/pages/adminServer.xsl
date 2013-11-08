<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="2.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

	<xsl:template match="/">
		<view>
			<SinglePage>
				<Messages>
					<xsl:apply-templates select="//actionMessage" />
				</Messages>
				<Content>

					<form method="post" id="adminServerForm">
						<input type="hidden" name="action" value="UpdateServerConfig" />
						<label for="updateServerHttpPort">${ui.admin.server.updateServer.httpPortLabel}
						</label>
						<input type="text" name="httpPort" id="updateServerHttpPort">
							<xsl:attribute name="value"><xsl:apply-templates
								select="//httpPort" /></xsl:attribute>
						</input>
						<label for="updateServerTvdbKey">${ui.admin.server.updateServer.tvdbKeyLabel}
						</label>
						<input type="text" name="tvdbKey" id="updateServerTvdbKey">
							<xsl:attribute name="value"><xsl:apply-templates
								select="//tvdbKey" /></xsl:attribute>
						</input>
						<input type="submit">
							<xsl:attribute name="value">${ui.admin.server.updateServer.updateButton}</xsl:attribute>
						</input>
					</form>
				</Content>
			</SinglePage>
		</view>
	</xsl:template>



</xsl:stylesheet>