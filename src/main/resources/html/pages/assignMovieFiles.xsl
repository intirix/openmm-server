<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="2.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:ext="http://exslt.org/common"
	xmlns:fn="http://www.w3.org/2005/xpath-functions">

	<xsl:output indent="yes"/>



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
				
					<form id="assignMovieFileForm" method="post">
						<input type="hidden" name="action" value="AssignMovieFile"/>
						<input type="hidden" name="movieId">
							<xsl:attribute name="value"><xsl:apply-templates select="/assignMovieFilesBean/movieId/node()" /></xsl:attribute>
						</input>
						<input type="hidden" name="path">
							<xsl:attribute name="value"><xsl:apply-templates select="/assignMovieFilesBean/path/node()" /></xsl:attribute>
						</input>
						<input id="assignMovieFileName" type="hidden" name="filename" value=""/>
					</form>
				
					<h2>${ui.movies.assignFile.heading}</h2>
					<div class="my-breakpoint">
						<div class="col1">
							<div id="assignMovieFilesPrefixGroup" data-role="collapsible-set">
								<div data-role="collapsible">
									<xsl:choose>
										<xsl:when test="/assignMovieFilesBean/prefix/text()[string-length() >= 1]">
											<h2><xsl:apply-templates select="/assignMovieFilesBean/prefix/node()" /></h2>
										</xsl:when>
										<xsl:otherwise>
											<h2>Select Letter</h2>
										</xsl:otherwise>
									</xsl:choose>
									<ListView>
										<xsl:for-each select="//moviePrefixCounts">
											<ListViewItem>
												<xsl:attribute name="href">?prefix=<xsl:apply-templates select="prefix/node()" />&amp;path=<xsl:apply-templates select="/assignMovieFilesBean/path/node()" /></xsl:attribute>
												<xsl:choose>
													<xsl:when test="/assignMovieFilesBean/prefix/node() = prefix/node()">
														<xsl:attribute name="data-icon">info</xsl:attribute>
													</xsl:when>
													<xsl:otherwise>
														<xsl:attribute name="data-icon">false</xsl:attribute>
													</xsl:otherwise>
												</xsl:choose>
												<xsl:apply-templates select="prefix/node()" />
											</ListViewItem>
										</xsl:for-each>
									</ListView>
								</div>
							</div>

							
							<xsl:if test="//movies[@length > 0]">
								<div id="assignMovieFilesMovieGroup" data-role="collapsible-set">
									<div data-role="collapsible">
										<xsl:choose>
											<xsl:when test="/assignMovieFilesBean/movieId/node() >= 0">
												<h2><xsl:apply-templates select="/assignMovieFilesBean/movie/displayName/node()" /></h2>
											</xsl:when>
											<xsl:otherwise>
												<h2>Select Movie</h2>
											</xsl:otherwise>
										</xsl:choose>
										<ListView>
											<xsl:for-each select="//movies/movieDetails">
												<ListViewItem>
													<xsl:attribute name="href">?prefix=<xsl:apply-templates select="/assignMovieFilesBean/prefix/node()" />&amp;movieId=<xsl:apply-templates select="movie/id/node()" />&amp;path=<xsl:apply-templates select="/assignMovieFilesBean/path/node()" /></xsl:attribute>
													<xsl:choose>
														<xsl:when test="/assignMovieFilesBean/movieId/node() = movie/id/node()">
															<xsl:attribute name="data-icon">info</xsl:attribute>
														</xsl:when>
														<xsl:otherwise>
															<xsl:attribute name="data-icon">false</xsl:attribute>
														</xsl:otherwise>
													</xsl:choose>
													<xsl:apply-templates select="movie/displayName/node()" />
												</ListViewItem>
											</xsl:for-each>
										</ListView>
									</div>
								</div>
							</xsl:if>
							
							<xsl:if test="/assignMovieFilesBean/selected/node() = 'true'">
								<h3><xsl:apply-templates select="/assignMovieFilesBean/movie/displayName/node()" /></h3>
								<p><xsl:apply-templates select="/assignMovieFilesBean/movie/description/node()" /></p>
							</xsl:if>
							

						</div>
						<div class="col2">
							<h2><xsl:apply-templates select="/assignMovieFilesBean/path/node()" /></h2>
							<ListView>
								<xsl:if test="string-length(//parentPath) &gt; 1">
									<ListViewItem><xsl:attribute name="href">?movieId=<xsl:apply-templates select="/assignMovieFilesBean/movieId/node()" />&amp;prefix=<xsl:apply-templates select="/assignMovieFilesBean/prefix" />&amp;path=<xsl:apply-templates select="/assignMovieFilesBean/parentPath" />/</xsl:attribute>Parent Folder</ListViewItem>
								</xsl:if>
								<xsl:if test="string-length(//parentPath) = 1">
									<ListViewItem><xsl:attribute name="href">?movieId=<xsl:apply-templates select="/assignMovieFilesBean/movieId/node()" />&amp;prefix=<xsl:apply-templates select="/assignMovieFilesBean/prefix" />&amp;path=<xsl:apply-templates select="/assignMovieFilesBean/parentPath" /></xsl:attribute>Parent Folder</ListViewItem>
								</xsl:if>
								<xsl:for-each select="//folders/string">
									<ListViewItem>
										<xsl:attribute name="href">?movieId=<xsl:apply-templates select="/assignMovieFilesBean/movieId/node()" />&amp;prefix=<xsl:apply-templates select="/assignMovieFilesBean/prefix" />&amp;path=<xsl:apply-templates select="/assignMovieFilesBean/path" /><xsl:apply-templates select="node()" />/</xsl:attribute>
										<xsl:apply-templates select="node()" />/
									</ListViewItem>
								</xsl:for-each>
								
								<!-- only display the files if a movie is selected -->
								<xsl:if test="/assignMovieFilesBean/selected/node() = 'true'">
									<xsl:if test="//files[@length>0]">
										<ListViewDivider>Assign File</ListViewDivider>
										<xsl:for-each select="//files/string">
											<ListViewItem href="#" onclick="document.getElementById( 'assignMovieFileName' ).value = this.textContent;document.getElementById('assignMovieFileForm').submit();return false;">
												<xsl:apply-templates select="node()" />
											</ListViewItem>
										</xsl:for-each>
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