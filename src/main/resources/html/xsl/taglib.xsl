<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:ext="http://exslt.org/common">
	
	<xsl:output indent="yes"/>
	
	<xsl:template match="SinglePage">
		<div data-role="page" id="topPage" data-wrapper="true">
			<div data-role="header">
				<a href="#" data-icon="arrow-l" data-rel="back" data-transition="slide">Back
				</a>
				<h1>${ui.title}</h1>
				<a href="/openmm/html/index.html" data-icon="home" data-transition="slide">Home
				</a>
			</div>
			<xsl:apply-templates select="Header"/>
			<div class="messages">
				<xsl:apply-templates select="Messages"/>
			</div>
			<xsl:apply-templates select="Content"/>
		</div>
	</xsl:template>


	<xsl:template match="ListView">
		<ul data-role="listview" data-inset="true">
			<xsl:apply-templates select="@*|node()"/>
		</ul>
	</xsl:template>
	
	<xsl:template match="Header">
		<xsl:apply-templates select="node()"/>
	</xsl:template>

	
	<xsl:template match="Content">
		<xsl:apply-templates select="node()"/>
	</xsl:template>

	
	<xsl:template match="Messages">
		<xsl:apply-templates select="node()"/>
	</xsl:template>

	
	<xsl:template match="view">
		<xsl:apply-templates select="node()"/>
	</xsl:template>

	<xsl:template match="ListViewItem">
		<li>
			<xsl:attribute name="data-icon">
   				<xsl:value-of select="@data-icon" />
  			</xsl:attribute>
			<a data-transition="slide">
				<xsl:apply-templates select="@*|node()"/>
			</a>
		</li>
	</xsl:template>

	<xsl:template match="ListViewDivider">
		<li data-role="list-divider"><xsl:apply-templates select="@*|node()"/></li>
	</xsl:template>
	


	<xsl:template match="NavBar">
		<div data-role="navbar">
	    	<ul>
				<xsl:apply-templates select="@*|node()"/>
    		</ul>
		</div>
	</xsl:template>

	<xsl:template match="NavBarItem">
		<li>
			<a href="#">
				<xsl:apply-templates select="@*|node()"/>
			</a>
		</li>
	</xsl:template>


		
	
	<!-- default template, matches everything -->
	<xsl:template match="@*|node()">
		<xsl:copy>
			<xsl:apply-templates select="@*|node()"/>
		</xsl:copy>
	</xsl:template>

	<xsl:template match="/">
		<html>
			<head>
				<meta charset="utf-8" />
				<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
				<meta name="apple-mobile-web-app-capable" content="yes" />
				<meta name="apple-mobile-web-app-status-bar-style" content="black" />
				<link rel="stylesheet" href="/openmm/staticlib/jquery-mobile/1.3.0/jquery.mobile.min.css" />
				<link rel="stylesheet" href="/openmm/static/openmm.css" />
				<script src="/openmm/staticlib/jquery/1.8.2/jquery.min.js"></script>
				<script src="/openmm/staticlib/jquery-mobile/1.3.0/jquery.mobile.min.js"></script>
				<!-- <script src="/openmm/static/jquery.mobile-1.1.0.multiview.js"></script>-->
				<script src="/static/openmm.js"></script>
				<script type="text/javascript">
					$(document).bind("mobileinit", function(){
					$.mobile.defaultPageTransition = 'slide';
					});
				</script>
				<title>OpenMM</title>
			</head>
			<body>
				<xsl:apply-templates select="node()"/>
			</body>
		</html>
	</xsl:template>
</xsl:stylesheet>