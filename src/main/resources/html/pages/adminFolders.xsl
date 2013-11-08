<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="2.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

	<xsl:template match="/">
		<view>
			<SinglePage>
				<Header>
					<NavBar>
						<NavBarItem href="adminFolders.html" class="ui-btn-active" data-transition="slide">${ui.admin.folders.topNav.listFolders}
						</NavBarItem>
						<NavBarItem href="adminFoldersAdd.html" data-transition="slide">${ui.admin.folders.topNav.addFolder}
						</NavBarItem>
					</NavBar>
				</Header>
				<Content>
					<xsl:apply-templates select="node()"/>
				</Content>
			</SinglePage>
		</view>
	</xsl:template>

	<xsl:template match="folders[@length=0]">
		<h1>No Folders</h1>
	</xsl:template>

	<xsl:template match="folders[@length>0]">
		<h2>Select Folder</h2>
		<ListView>
			<xsl:apply-templates select="rootFolder"/>
		</ListView>
	</xsl:template>

	<xsl:template match="rootFolder">
		<ListViewItem href="adminFoldersEdit.html?id=">
			<xsl:attribute name="href">adminFoldersEdit.html?id=<xsl:value-of select="id/node()"/></xsl:attribute>
			<xsl:apply-templates select="url/node()"/>
		</ListViewItem>
	</xsl:template>

</xsl:stylesheet>