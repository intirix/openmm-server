<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="2.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:ext="http://exslt.org/common">




	<xsl:template match="/">
		<view>
			<SinglePage>
				<Content>
					
					<h2><xsl:apply-templates select="adminUsersMenuBean/user/username/node()"/></h2>
					<ListView>
						<ListViewItem>
							<xsl:attribute name="href">adminUsersResetPassword.html?userId=<xsl:apply-templates select="adminUsersMenuBean/user/userId/node()"/></xsl:attribute>
							Reset Password
						</ListViewItem>
					</ListView>
				</Content>
			</SinglePage>
		</view>
	</xsl:template>

</xsl:stylesheet>