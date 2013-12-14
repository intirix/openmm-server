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
						width: 30%;
						display:
						inline;
						float: left;
						}
						#blockB {
						width: 70%;
						display: inline;
						float:
						right;
						}
						}
					</style>
				</Header>
				<Messages>
					<xsl:apply-templates select="//actionMessage" />
				</Messages>
				<Content>
					<div id="blockA">
						<img style="max-width:100%; min-width:100%;">
							<xsl:attribute name="src">/openmm<xsl:apply-templates
								select="/moviesRTDetailsBean/banner/node()" /></xsl:attribute>
						</img>
					</div>
					<div id="blockB">
						<h1 style="text-align: center;">
							<xsl:apply-templates select="/moviesRTDetailsBean/name/node()" />
						</h1>
						<p>MPAA Rating:
							<xsl:apply-templates select="/moviesRTDetailsBean/mpaaRating/node()" />
						</p>
						<p>
							<xsl:apply-templates select="/moviesRTDetailsBean/description/node()" />
						</p>

						<form method="post">
							<input type="hidden" name="action" value="RTImport" />
							<input type="hidden" name="rtId">
								<xsl:attribute name="value"><xsl:apply-templates
									select="/moviesRTDetailsBean/id" /></xsl:attribute>
							</input>
							<input type="hidden" name="id">
								<xsl:attribute name="value"><xsl:apply-templates
									select="/moviesRTDetailsBean/id" /></xsl:attribute>
							</input>
							<input type="submit" value="Import" />
						</form>
					</div>
				</Content>
			</SinglePage>
		</view>
	</xsl:template>

</xsl:stylesheet>