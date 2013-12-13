<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:ext="http://exslt.org/common">
	
	<xsl:param name="passes"/>

	<xsl:template match="/" mode="pass1">
	</xsl:template>

	<xsl:variable name="output1">
		<Page>
		<div data-role="page" id="topPage" data-wrapper="true">
			<div data-role="header">
				<a href="#" data-icon="arrow-l" data-rel="back" data-transition="slide">Back
				</a>
				<h1>${ui.title}</h1>
				<a href="/openmm/html/index.html" data-icon="home" data-transition="slide">Home
				</a>
			</div>
			<xsl:apply-templates select="/" mode="pass1" />
		</div>
		</Page>
	</xsl:variable>

	<xsl:template match="actionMessage" mode="pass1">
		<xsl:if test="string-length(.) > 0">
		<h2 class="actionMessage"><xsl:apply-templates select="node()" /></h2>
		</xsl:if>
	</xsl:template>

	<xsl:template match="ListView" mode="pass2">
		<ul data-role="listview" data-inset="true">
			<xsl:apply-templates select="@*|node()" mode="pass2" />
		</ul>
	</xsl:template>

	<xsl:template match="ListViewItem" mode="pass2">
		<li>
			<xsl:attribute name="data-icon">
   				<xsl:value-of select="@data-icon" />
  			</xsl:attribute>
			<a data-transition="slide">
				<xsl:apply-templates select="@*|node()" mode="pass2" />
			</a>
		</li>
	</xsl:template>


	<xsl:template match="NavBar" mode="pass2">
		<div data-role="navbar">
	    	<ul>
				<xsl:apply-templates select="@*|node()" mode="pass2" />
    		</ul>
		</div>
	</xsl:template>

	<xsl:template match="NavBarItem" mode="pass2">
		<li>
			<a href="#">
				<xsl:apply-templates select="@*|node()" mode="pass2" />
			</a>
		</li>
	</xsl:template>
	<!-- default template, matches everything -->
	<xsl:template match="@*|node()" mode="pass2">
		<xsl:copy>
			<xsl:apply-templates select="@*|node()" mode="pass2" />
		</xsl:copy>
	</xsl:template>

	<xsl:template match="/">
		<xsl:if test="$passes = 2">
		<html>
			<head>
				<meta charset="utf-8" />
				<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
				<meta name="apple-mobile-web-app-capable" content="yes" />
				<meta name="apple-mobile-web-app-status-bar-style" content="black" />
				<link rel="stylesheet" href="/openmm/static/jquery.mobile-1.1.0.min.css" />
				<script src="/openmm/staticlib/jquery/1.8.2/jquery.min.js"></script>
				<script src="/openmm/staticlib/jquery-mobile/1.3.0/jquery.mobile.min.js"></script>
				<!-- <script src="/openmm/static/jquery.mobile-1.1.0.multiview.js"></script>-->
				<script type="text/javascript">
					$(document).bind("mobileinit", function(){
					$.mobile.defaultPageTransition = 'slide';
					});
				</script>
				<title>OpenMM</title>
			</head>
			<body>
				<!--the node-set extension function turns the first pass back into a 
					node set -->
				<xsl:apply-templates select="ext:node-set($output1)"
					mode="pass2" />
			</body>
		</html>
		</xsl:if>
		<xsl:if test="$passes = 1">
			<Page>
			<xsl:apply-templates select="/" mode="pass1" />
			</Page>
		</xsl:if>
	</xsl:template>
</xsl:stylesheet>