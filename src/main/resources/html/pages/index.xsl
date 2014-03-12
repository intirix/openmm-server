<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="2.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:ext="http://exslt.org/common">




	<xsl:template match="/">
		<view>
			<SinglePage>
				<Content>
					<ListView>
						<ListViewItem href="/openmm/html/browse.html?path=/"
							data-icon="grid">${ui.main.menu.browse}
						</ListViewItem>
						<ListViewItem href="/openmm/html/search.html">${ui.main.menu.search}
						</ListViewItem>
						<ListViewItem href="/openmm/html/shows.html">${ui.main.menu.shows}
						</ListViewItem>
						<ListViewItem href="/openmm/html/movies.html">${ui.main.menu.movies}
						</ListViewItem>
						<xsl:if test="indexBean/userInfo/admin/node() = 'true'">
							<ListViewItem href="/openmm/html/admin.html" data-icon="gear">${ui.main.menu.admin}
							</ListViewItem>
						</xsl:if>
					</ListView>
				</Content>
			</SinglePage>
		</view>
	</xsl:template>

</xsl:stylesheet>