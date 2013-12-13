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
							<xsl:apply-templates select="/browseBean/folders" mode="folder"/>
						</xsl:if>
						<xsl:if test="//files[@length>0]">
							<ListViewDivider>Files</ListViewDivider>
							<xsl:apply-templates select="/browseBean/files" mode="file"/>
						</xsl:if>
					</ListView>
				</Content>
			</SinglePage>
		</view>
	</xsl:template>
	
	<xsl:template match="string" mode="folder">
		<ListViewItem>
			<xsl:attribute name="href">/openmm/html/browse.html?path=<xsl:apply-templates select="/browseBean/path" /><xsl:apply-templates select="node()" />/</xsl:attribute>
			<xsl:apply-templates select="node()" />/
		</ListViewItem>
	</xsl:template>

	<xsl:template match="string" mode="file">
		<ListViewItem rel="external">
			<xsl:attribute name="href">/download<xsl:apply-templates select="/browseBean/path" /><xsl:apply-templates select="node()" /></xsl:attribute>
			<xsl:apply-templates select="node()" />
		</ListViewItem>
	</xsl:template>

</xsl:stylesheet>