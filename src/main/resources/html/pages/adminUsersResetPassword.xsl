<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="2.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

	<xsl:template match="/">
		<view>
			<SinglePage>
				<Header>
					<h2>${ui.admin.users.resetPassword.heading}</h2>
					<h2><xsl:apply-templates select="adminUsersResetPasswordBean/user/username/node()"/></h2>
				</Header>
				<Messages>
					<xsl:apply-templates select="//actionMessage"/>
				</Messages>
				<Content>

					<form method="post" data-transition="slide">
						<input type="hidden" name="action" value="ResetPassword" />
						<input type="hidden" name="userId">
							<xsl:attribute name="value"><xsl:apply-templates select="adminUsersResetPasswordBean/user/userId/node()"/></xsl:attribute>
						</input>
												
						<label for="adminUsersResetPasswordPassword">${ui.admin.users.resetPassword.passwordLabel}
						</label>
						<input type="password" name="password" id="adminUsersResetPasswordPassword" value="" />

						
						
						<input type="submit">
							<xsl:attribute name="value">${ui.admin.users.resetPassword.resetButton}</xsl:attribute>
						</input>
					</form>
				</Content>
			</SinglePage>
		</view>
	</xsl:template>



</xsl:stylesheet>