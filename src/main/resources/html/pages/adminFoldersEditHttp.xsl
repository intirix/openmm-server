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
						<NavBarItem href="adminFoldersAdd.html" class="ui-btn-active"
							data-transition="slide">${ui.admin.folders.topNav.addFolder}
						</NavBarItem>
					</NavBar>
				</Header>
				<Messages>
					<xsl:apply-templates select="//actionMessage" />
				</Messages>
				<Content>

					<form method="post" id="editFolderForm">
						<input type="hidden" name="id">
							<xsl:attribute name="value"><xsl:apply-templates
								select="//folder/id" /></xsl:attribute>
						</input>
						<input type="hidden" id="editFolderAction" name="action"
							value="UpdateHttpFolder" />
							
						<label for="editFolderHttpUrl">${ui.admin.folders.editFolderHttp.urlLabel}
						</label>
						<input type="text" name="url" id="editFolderHttpUrl">
							<xsl:attribute name="value"><xsl:apply-templates
								select="//folder/url" /></xsl:attribute>
						</input>
						
						<label for="editFolderHttpUsername">${ui.admin.folders.editFolderHttp.usernameLabel}
						</label>
						<input type="text" name="username" id="editFolderHttpUsername">
							<xsl:attribute name="value"><xsl:apply-templates
								select="//folder/username" /></xsl:attribute>
						</input>
						
						<label for="editFolderHttpPassword">${ui.admin.folders.editFolderHttp.passwordLabel}
						</label>
						<input type="password" name="password" id="editFolderHttpPassword">
							<xsl:attribute name="value"><xsl:apply-templates
								select="//folder/password" /></xsl:attribute>
						</input>
						
						<label for="editFolderHttpMountPoint">${ui.admin.folders.editFolderHttp.mountPointLabel}
						</label>
						<input type="text" name="mountpoint" id="editFolderHttpMountPoint">
							<xsl:attribute name="value"><xsl:apply-templates
								select="//folder/mountPoint" /></xsl:attribute>
						</input>
						<input type="submit"
							onclick="document.getElementById( 'editFolderAction' ).value = 'UpdateHttpFolder'; return true;">
							<xsl:attribute name="value">${ui.admin.folders.editFolderHttp.updateButton}</xsl:attribute>
						</input>
						<input type="button"
							onclick="document.getElementById( 'editFolderAction' ).value = 'DeleteFolder'; document.getElementById( 'editFolderForm' ).submit(); return false;">
							<xsl:attribute name="value">${ui.admin.folders.editFolderHttp.deleteButton}</xsl:attribute>
						</input>
					</form>
				</Content>
			</SinglePage>
		</view>
	</xsl:template>



</xsl:stylesheet>