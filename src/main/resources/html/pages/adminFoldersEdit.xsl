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
								select="/adminFoldersEditBean/folder/id" /></xsl:attribute>
						</input>
						<input type="hidden" id="editFolderAction" name="action"
							value="UpdateFolder" />
						<label for="editFolderLocalPath">${ui.admin.folders.editFolderLocal.pathLabel}
						</label>
						<input type="text" name="path" id="editFolderLocalPath">
							<xsl:attribute name="value"><xsl:apply-templates
								select="/adminFoldersEditBean/folder/url" /></xsl:attribute>
						</input>
						<label for="editFolderLocalMountPoint">${ui.admin.folders.editFolderLocal.mountPointLabel}
						</label>
						<input type="text" name="mountpoint" id="editFolderLocalMountPoint">
							<xsl:attribute name="value"><xsl:apply-templates
								select="/adminFoldersEditBean/folder/mountPoint" /></xsl:attribute>
						</input>
						<input type="submit"
							onclick="document.getElementById( 'editFolderAction' ).value = 'UpdateFolder'; return true;">
							<xsl:attribute name="value">${ui.admin.folders.editFolderLocal.updateButton}</xsl:attribute>
						</input>
						<input type="button"
							onclick="document.getElementById( 'editFolderAction' ).value = 'DeleteFolder'; document.getElementById( 'editFolderForm' ).submit(); return false;">
							<xsl:attribute name="value">${ui.admin.folders.editFolderLocal.deleteButton}</xsl:attribute>
						</input>
					</form>
				</Content>
			</SinglePage>
		</view>
	</xsl:template>



</xsl:stylesheet>