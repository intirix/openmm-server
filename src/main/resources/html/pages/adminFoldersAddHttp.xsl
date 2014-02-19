<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="2.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

	<xsl:template match="/">
		<view>
			<SinglePage>
				<Header>

					<NavBar>
						<NavBarItem href="adminFolders.html" data-transition="slide">${ui.admin.folders.topNav.listFolders}
						</NavBarItem>
						<NavBarItem href="adminFoldersAdd.html" class="ui-btn-active" data-transition="slide">${ui.admin.folders.topNav.addFolder}
						</NavBarItem>
					</NavBar>
				</Header>
				<Messages>
					<xsl:apply-templates select="//actionMessage"/>
				</Messages>
				<Content>

					<form method="post" data-transition="slide">
						<input type="hidden" name="action" value="AddHttpFolder" />

						<label for="addFolderHttpUrl">${ui.admin.folders.addFolderHttp.urlLabel}
						</label>
						<input type="text" name="url" id="addFolderHttpUrl"
							value="" />

						<label for="addFolderHttpUsername">${ui.admin.folders.addFolderHttp.usernameLabel}
						</label>
						<input type="text" name="username" id="addFolderHttpUsername"
							value="" />

						<label for="addFolderHttpPassword">${ui.admin.folders.addFolderHttp.passwordLabel}
						</label>
						<input type="password" name="password" id="addFolderHttpPassword"
							value="" />

						<label for="addFolderHttpMountPoint">${ui.admin.folders.addFolderHttp.mountPointLabel}
						</label>
						<input type="text" name="mountpoint" id="addFolderHttpMountPoint"
							value="" />
						<input type="submit">
							<xsl:attribute name="value">${ui.admin.folders.addFolderHttp.addButton}</xsl:attribute>
						</input>
					</form>
				</Content>
			</SinglePage>
		</view>
	</xsl:template>



</xsl:stylesheet>