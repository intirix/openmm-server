<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="2.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:ext="http://exslt.org/common">




	<xsl:template match="/">
		<view>
			<SinglePage>
				<Header>
					<style>
						@media (min-width: 600px) {
						#blockA {
						width: 50%;
						display: inline;
						float: left;
						}
						#blockB {
						width: 50%;
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
								select="//banner/node()" /></xsl:attribute>
						</img>
					</div>
					<div id="blockB">
						<h1 style="text-align: center;">
							<xsl:apply-templates select="//name/node()" />
						</h1>
						<div>
							<div style="width: 50%; display: inline; float: left;">
								<div>Season: <xsl:apply-templates select="//seasonNumber/node()" /></div>
								<div>Rating: <xsl:apply-templates select="//rating/node()" /></div>
							</div>
							<div style="width: 50%; display: inline; float: left;">
								<div>Episode: <xsl:apply-templates select="//episodeNumber/node()" /></div>
								<div>First Air Date: <xsl:apply-templates select="//firstAired/node()" /></div>
							</div>
						</div>
						<div>
						<p style="padding-top: 2em;">
							<xsl:apply-templates select="//description/node()" />
						</p>
						</div>
					</div>

				</Content>
			</SinglePage>
		</view>
	</xsl:template>

</xsl:stylesheet>