<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="2.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:ext="http://exslt.org/common">




	<xsl:template match="/">
		<view>
			<SinglePage>
				<Content>
					<h1>${ui.shows.list.heading}</h1>

						<ListView>
								<ListViewItem href="showsAdd.html">
									${ui.shows.list.add}
								</ListViewItem>
							<xsl:for-each select="//show">
								<ListViewItem>
									<xsl:attribute name="href">showsDetails.html?id=<xsl:apply-templates select="id/node()"/></xsl:attribute>
									<xsl:apply-templates select="displayName/node()"/>
								</ListViewItem>
							</xsl:for-each>
						</ListView>

				</Content>
			</SinglePage>
		</view>
	</xsl:template>

</xsl:stylesheet>