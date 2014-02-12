<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="2.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

	<xsl:template match="/">
		<view>
			<SinglePage>
				<Header>
					<h2>${ui.admin.users.add.heading}</h2>
				</Header>
				<Messages>
					<xsl:apply-templates select="//actionMessage"/>
				</Messages>
				<Content>

					<form method="post" data-transition="slide">
						<input type="hidden" name="action" value="AddUser" />
						
						<label for="adminUsersAddUsername">${ui.admin.users.add.usernameLabel}
						</label>
						<input type="text" name="username" id="adminUsersAddUsername" value="" />
						
						<label for="adminUsersAddDisplayName">${ui.admin.users.add.displayNameLabel}
						</label>
						<input type="text" name="displayName" id="adminUsersAddDisplayName" value="" />
						
						<label for="adminUsersAddPassword">${ui.admin.users.add.passwordLabel}
						</label>
						<input type="password" name="password" id="adminUsersAddPassword" value="" />
						
						<label for="adminUsersAddAdmin">${ui.admin.users.add.adminLabel}
						</label>
						<select name="admin">
							<option value="Y">${ui.admin.users.add.adminYes}</option>
							<option value="N">${ui.admin.users.add.adminNo}</option>
						</select>
						
						
						<input type="submit">
							<xsl:attribute name="value">${ui.admin.users.add.addButton}</xsl:attribute>
						</input>
					</form>
				</Content>
			</SinglePage>
		</view>
	</xsl:template>



</xsl:stylesheet>