<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="2.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:ext="http://exslt.org/common"
	xmlns:fn="http://www.w3.org/2005/xpath-functions">




	<xsl:template match="/">
		<view>
			<SinglePage>
				<Header>
					<style>
						@media (min-width: 600px) {
						#blockA {
						width: 30%;
						display: inline;
						float: left;
						}
						#blockB {
						width: 70%;
						display: inline;
						float: right;
						}
						}
					</style>
				</Header>
				<Content>
					<div id="blockA">
						<img style="max-width:100%; min-width:100%;">
							<xsl:attribute name="src">/openmm<xsl:apply-templates
								select="//movie/posterUrl/node()" /></xsl:attribute>
						</img>
					</div>
					<div id="blockB">
						<h1 style="text-align: center;">
							<xsl:apply-templates select="//movie/displayName/node()" />
						</h1>
						<div>
							<div style="width: 50%; display: inline; float: left;">
								<div>Release Date: <xsl:apply-templates select="//movie/releaseDate/node()" /></div>
								<div>Rating: <xsl:apply-templates select="//movie/rating/node()" /></div>
							</div>
							<div style="width: 50%; display: inline; float: left;">
								<div>MPAA Rating: <xsl:apply-templates select="//movie/mpaaRating/node()" /></div>
								<div>Runtime: <xsl:apply-templates select="//movie/runtime/node()" /></div>
							</div>
						</div>
						<div>
						<p style="padding-top: 2em;">
							<div>Genre: <xsl:apply-templates select="//movie/genre/node()" /></div>
							<br/>
							<div><xsl:apply-templates select="//description/node()" /></div>
						</p>
						</div>
						
						<center>
						<div data-role="controlgroup" data-type="horizontal">
							<a href="#" data-role="button">Update Movie</a>
							<a data-role="button">
								<xsl:attribute name="href">assignMovieFiles.html?movieId=<xsl:apply-templates select="//movie/id/node()" />&amp;prefix=<xsl:apply-templates select="//prefix/node()" />&amp;path=/</xsl:attribute>
								Assign Files
							</a>
						</div>
						</center>
					</div>

				</Content>
			</SinglePage>
		</view>
	</xsl:template>

</xsl:stylesheet>