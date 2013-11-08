<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="2.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:ext="http://exslt.org/common">




	<xsl:template match="/">
		<view>
			<SinglePage>
				<Content>
					<ListView>
						<ListViewItem href="/html/adminFolders.html">${ui.admin.menu.folders}
						</ListViewItem>
						<ListViewItem href="/html/adminUsers.html">${ui.admin.menu.users}
						</ListViewItem>
						<ListViewItem href="/html/adminServer.html">${ui.admin.menu.server}
						</ListViewItem>
					</ListView>
				</Content>
			</SinglePage>
		</view>
	</xsl:template>



</xsl:stylesheet>