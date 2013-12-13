<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="2.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:ext="http://exslt.org/common">




	<xsl:template match="/">
		<view>
			<SinglePage>
				<Content>
					<div><xsl:apply-templates
								select="/browseBean/path" /></div>
					<ListView>
						<xsl:if test="string-length(/browseBean/parentPath) &gt; 1">
							<ListViewItem><xsl:attribute name="href">/openmm/html/browse.html?path=<xsl:apply-templates select="/browseBean/parentPath" />/</xsl:attribute>Parent Folder</ListViewItem>
						</xsl:if>
						<xsl:if test="string-length(/browseBean/parentPath) = 1">
							<ListViewItem><xsl:attribute name="href">/openmm/html/browse.html?path=<xsl:apply-templates select="/browseBean/parentPath" /></xsl:attribute>Parent Folder</ListViewItem>
						</xsl:if>
						<xsl:if test="//folders[@length>0]">
							<ListViewDivider>Folders</ListViewDivider>
							<xsl:for-each select="/browseBean/folders/folderEntry">
								<ListViewItem>
									<xsl:attribute name="href">/openmm/html/browse.html?path=<xsl:apply-templates select="/browseBean/path" /><xsl:apply-templates select="encodedName/node()" />/</xsl:attribute>
									<xsl:apply-templates select="name/node()" />/
								</ListViewItem>
							</xsl:for-each>
						</xsl:if>
						<xsl:if test="//files[@length>0]">
							<li class="ui-body ui-body-b">
								<fieldset class="ui-grid-a">
									<div class="ui-block-a"><a data-role="button">
										<xsl:attribute name="href">/openmm/html/assignShowFiles.html?path=<xsl:apply-templates select="/browseBean/path" /></xsl:attribute>
										Assign Shows</a></div>
									<div class="ui-block-b"><a data-role="button">
										<xsl:attribute name="href">/openmm/html/assignMovieFiles.html?path=<xsl:apply-templates select="/browseBean/path" /></xsl:attribute>
										Assign Movies</a></div>
								</fieldset>
							</li>
							<ListViewDivider>Files</ListViewDivider>
							<xsl:for-each select="/browseBean/files/fileEntry">
								<ListViewItem rel="external">
									<xsl:attribute name="href">/download<xsl:apply-templates select="/browseBean/path" />/<xsl:apply-templates select="encodedName/node()" /></xsl:attribute>
									<xsl:apply-templates select="name/node()" />
								</ListViewItem>
							</xsl:for-each>
						</xsl:if>
					</ListView>
				</Content>
			</SinglePage>
		</view>
	</xsl:template>
	
</xsl:stylesheet>