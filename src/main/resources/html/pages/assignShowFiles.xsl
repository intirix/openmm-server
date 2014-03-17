<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="2.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:ext="http://exslt.org/common">




	<xsl:template match="/">
		<view>
			<SinglePage>
				<Header>
					<style>
						@media (max-width: 600px) {
							.my-breakpoint .col1,
							.my-breakpoint .col2 {
								width: 100%;
								float: none;
							}
						}
						@media (min-width: 600px) {
							.my-breakpoint .col1,
							.my-breakpoint .col2 {
								width: 49%;
							}
							.my-breakpoint .col1 {
								float: left;
							}
							.my-breakpoint .col2 {
								float: right;
							}
						}
					</style>
				</Header>
				<Messages>
					<xsl:apply-templates select="//actionMessage"/>
				</Messages>
				<Content>
				
					<form id="assignShowFileForm" method="post">
						<input type="hidden" name="action" value="AssignShowFile"/>
						<input id="assignShowFileName" type="hidden" name="filename" value=""/>
					</form>
				
					<form id="assignShowDirectoryForm" method="post">
						<input type="hidden" name="action" value="AssignShowDirectory"/>
					</form>
				
					<h2>Assign Files to a Show Episode</h2>
					<div class="my-breakpoint">
						<div class="col1">
							<div data-role="collapsible-set">
								<div data-role="collapsible">
									<xsl:choose>
										<xsl:when test="/assignShowFilesBean/showId/node() >= 0">
											<h2><xsl:apply-templates select="/assignShowFilesBean/shows/show[id=/assignShowFilesBean/showId]/displayName/node()" /></h2>
										</xsl:when>
										<xsl:otherwise>
											<h2>Select Show</h2>
										</xsl:otherwise>
									</xsl:choose>
									<ListView>
										<xsl:for-each select="//show">
											<ListViewItem>
												<xsl:attribute name="href">?show=<xsl:apply-templates select="id/node()" />&amp;path=<xsl:apply-templates select="/assignShowFilesBean/path/node()" /></xsl:attribute>
												<xsl:choose>
													<xsl:when test="/assignShowFilesBean/showId/node() = id/node()">
														<xsl:attribute name="data-icon">info</xsl:attribute>
													</xsl:when>
													<xsl:otherwise>
														<xsl:attribute name="data-icon">false</xsl:attribute>
													</xsl:otherwise>
												</xsl:choose>
												<xsl:apply-templates select="displayName/node()" />
											</ListViewItem>
										</xsl:for-each>
									</ListView>
								</div>
							</div>

							<xsl:if test="//seasons[@length > 0]">
								<div data-role="collapsible-set">
									<div data-role="collapsible">
										<xsl:choose>
											<xsl:when test="/assignShowFilesBean/seasonNumber/node() >= 0">
												<h2><xsl:apply-templates select="/assignShowFilesBean/seasons/season[number=/assignShowFilesBean/seasonNumber]/name/node()" /></h2>
											</xsl:when>
											<xsl:otherwise>
												<h2>Select Season</h2>
											</xsl:otherwise>
										</xsl:choose>
										<ListView>
											<xsl:for-each select="//season">
												<ListViewItem>
													<xsl:attribute name="href">?show=<xsl:apply-templates select="/assignShowFilesBean/showId/node()" />&amp;season=<xsl:apply-templates select="number/node()" />&amp;path=<xsl:apply-templates select="/assignShowFilesBean/path/node()" /></xsl:attribute>
													<xsl:choose>
														<xsl:when test="/assignShowFilesBean/seasonNumber/node() = number/node()">
															<xsl:attribute name="data-icon">info</xsl:attribute>
														</xsl:when>
														<xsl:otherwise>
															<xsl:attribute name="data-icon">false</xsl:attribute>
														</xsl:otherwise>
													</xsl:choose>
													<xsl:apply-templates select="name/node()" />
												</ListViewItem>
											</xsl:for-each>
										</ListView>
									</div>
								</div>
							</xsl:if>
							
							<xsl:if test="//episodes[@length > 0]">
								<div data-role="collapsible-set">
									<div data-role="collapsible">
										<xsl:choose>
											<xsl:when test="/assignShowFilesBean/epNum/node() >= 0">
												<h2><xsl:apply-templates select="/assignShowFilesBean/epNum/node()" />) <xsl:apply-templates select="/assignShowFilesBean/episodes/episode[epNum=/assignShowFilesBean/epNum]/name/node()" /></h2>
											</xsl:when>
											<xsl:otherwise>
												<h2>Select Episode</h2>
											</xsl:otherwise>
										</xsl:choose>
										<ListView>
											<xsl:for-each select="//episodes/episode">
												<ListViewItem>
													<xsl:attribute name="href">?show=<xsl:apply-templates select="/assignShowFilesBean/showId/node()" />&amp;season=<xsl:apply-templates select="/assignShowFilesBean/seasonNumber/node()" />&amp;ep=<xsl:apply-templates select="epNum/node()" />&amp;path=<xsl:apply-templates select="/assignShowFilesBean/path/node()" /></xsl:attribute>
													<xsl:choose>
														<xsl:when test="/assignShowFilesBean/epNum/node() = epNum/node()">
															<xsl:attribute name="data-icon">info</xsl:attribute>
														</xsl:when>
														<xsl:otherwise>
															<xsl:attribute name="data-icon">false</xsl:attribute>
														</xsl:otherwise>
													</xsl:choose>
													<xsl:apply-templates select="epNum/node()" />) <xsl:apply-templates select="name/node()" />
												</ListViewItem>
											</xsl:for-each>
										</ListView>
									</div>
								</div>
							</xsl:if>
							
							<xsl:if test="/assignShowFilesBean/selected/node() = 'true'">
								<h3><xsl:apply-templates select="/assignShowFilesBean/shows/show[id=/assignShowFilesBean/showId]/displayName/node()" /></h3>
								<h4>
									<xsl:apply-templates select="/assignShowFilesBean/seasonNumber/node()" />x<xsl:apply-templates select="/assignShowFilesBean/epNum/node()" />
									- <xsl:apply-templates select="/assignShowFilesBean/episodes/episode[epNum=/assignShowFilesBean/epNum]/name/node()" />
								</h4>
								<p><xsl:apply-templates select="/assignShowFilesBean/episodes/episode[epNum=/assignShowFilesBean/epNum]/description/node()" /></p>
							</xsl:if>
							

						</div>
						<div class="col2">
							<h2><xsl:apply-templates select="/assignShowFilesBean/path/node()" /></h2>
							<ListView>
								<xsl:if test="string-length(//parentPath) &gt; 1">
									<ListViewItem><xsl:attribute name="href">?show=<xsl:apply-templates select="/assignShowFilesBean/showId/node()" />&amp;season=<xsl:apply-templates select="/assignShowFilesBean/seasonNumber/node()" />&amp;ep=<xsl:apply-templates select="/assignShowFilesBean/epNum/node()" />&amp;path=<xsl:apply-templates select="/assignShowFilesBean/parentPath" />/</xsl:attribute>Parent Folder</ListViewItem>
								</xsl:if>
								<xsl:if test="string-length(//parentPath) = 1">
									<ListViewItem><xsl:attribute name="href">?show=<xsl:apply-templates select="/assignShowFilesBean/showId/node()" />&amp;season=<xsl:apply-templates select="/assignShowFilesBean/seasonNumber/node()" />&amp;ep=<xsl:apply-templates select="/assignShowFilesBean/epNum/node()" />&amp;path=<xsl:apply-templates select="/assignShowFilesBean/parentPath" /></xsl:attribute>Parent Folder</ListViewItem>
								</xsl:if>
								<xsl:for-each select="//folders/string">
									<ListViewItem>
										<xsl:attribute name="href">?show=<xsl:apply-templates select="/assignShowFilesBean/showId/node()" />&amp;season=<xsl:apply-templates select="/assignShowFilesBean/seasonNumber/node()" />&amp;ep=<xsl:apply-templates select="/assignShowFilesBean/epNum/node()" />&amp;path=<xsl:apply-templates select="/assignShowFilesBean/path" /><xsl:apply-templates select="node()" />/</xsl:attribute>
										<xsl:apply-templates select="node()" />/
									</ListViewItem>
								</xsl:for-each>
								
								<!--  only display the assign all button if the user selected a show -->
								<xsl:if test="/assignShowFilesBean/showId/node() >= 0">
									<ListViewDivider>Assign Files</ListViewDivider>
									<ListViewItem href="#" onclick="document.getElementById('assignShowDirectoryForm').submit();return false;">
										Assign All Files
									</ListViewItem>
									<!-- only display the files if an episode is selected -->
									<xsl:if test="/assignShowFilesBean/selected/node() = 'true'">
										<xsl:if test="//files[@length>0]">
											<xsl:for-each select="//files/string">
												<ListViewItem href="#" onclick="document.getElementById( 'assignShowFileName' ).value = this.textContent;document.getElementById('assignShowFileForm').submit();return false;">
													<xsl:apply-templates select="node()" />
												</ListViewItem>
											</xsl:for-each>
										</xsl:if>
									</xsl:if>
								</xsl:if>
								
							</ListView>
						
						</div>
					</div>
				</Content>
			</SinglePage>
		</view>
	</xsl:template>
	
</xsl:stylesheet>