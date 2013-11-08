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
						<input type="hidden" name="action" value="AddLocalFolder" />
						<label for="addFolderLocalPath">${ui.admin.folders.addFolderLocal.pathLabel}
						</label>
						<input type="text" name="path" id="addFolderLocalPath"
							value="" />
						<label for="addFolderLocalMountPoint">${ui.admin.folders.addFolderLocal.mountPointLabel}
						</label>
						<input type="text" name="mountpoint" id="addFolderLocalMountPoint"
							value="" />
						<input type="submit">
							<xsl:attribute name="value">${ui.admin.folders.addFolderLocal.addButton}</xsl:attribute>
						</input>
					</form>
				</Content>
			</SinglePage>
		</view>
	</xsl:template>



</xsl:stylesheet>