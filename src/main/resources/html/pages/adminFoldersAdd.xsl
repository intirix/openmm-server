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
				<Content>
					<h2>${ui.admin.folders.addFolderList.heading}</h2>
					<ListView>
						<ListViewItem href="adminFoldersAddLocal.html">${ui.folder.types.local}
						</ListViewItem>
						<ListViewItem href="adminFoldersAddHttp.html">${ui.folder.types.http}
						</ListViewItem>
					</ListView>
				</Content>
			</SinglePage>
		</view>
	</xsl:template>



</xsl:stylesheet>