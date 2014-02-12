<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="2.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:ext="http://exslt.org/common">




	<xsl:template match="/">
		<view>
			<SinglePage>
				<Content>
					
					<h2>Users</h2>
					<ListView>
						<ListViewItem>
							<xsl:attribute name="href">adminUsersAdd.html</xsl:attribute>
							Add User
						</ListViewItem>
						<ListViewDivider>Users</ListViewDivider>
							
						<xsl:for-each select="//userInfoBean">
							<ListViewItem>
								<xsl:attribute name="href">adminUsersMenu.html?userId=<xsl:apply-templates select="userId"/></xsl:attribute>
								<xsl:apply-templates select="username/node()"/>
							</ListViewItem>
						</xsl:for-each>
					</ListView>
				</Content>
			</SinglePage>
		</view>
	</xsl:template>

</xsl:stylesheet>