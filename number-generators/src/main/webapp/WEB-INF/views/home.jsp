<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page session="false"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="Tomáš Pochobradský">
<link rel="icon" href="../../favicon.ico">

<title>Number generator</title>

<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css">

<!-- Optional theme -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap-theme.min.css">

</head>

<body>

	<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#navbar" aria-expanded="false"
					aria-controls="navbar">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="/generator">Generátor
					pseudo-náhodných čísel</a>
			</div>
			<div id="navbar" class="collapse navbar-collapse">
				<ul class="nav navbar-nav">
					<li><a href="/generator/documentation">Dokumentace</a></li>
					<li><a href="/generator/contact">Kontakt</a></li>
				</ul>
			</div>
			<!--/.nav-collapse -->
		</div>
	</nav>

	<div class="container">

		<div class="starter-template" style="margin-top: 80px">
			<h1>Generátor pseudo-náhodných čísel</h1>
			<p class="lead">
				Use this document as a way to quickly start any new project.<br>
				All you get is this text and a mostly barebones HTML document.
			</p>
			<form:form action="result" method="POST" commandName="generator" role="form">
				<div class="form-group">
					<label for="count">Count</label>
					<form:input type="text" path="count" id="count" class="form-control" required="required" placeholder="Vložte count"/>
					<label for="seed">Seed</label>
					<form:input type="text" path="seed" id="seed" class="form-control" required="required" placeholder="Vložte seed"/>
					<label for="alpha" >Alpha</label>
					<form:input type="text" path="alpha" id="alpha" class="form-control" required="required" placeholder="Vložte alpha"/>
				</div>
				<div class="form-group">
					<input class="btn btn-alfa btn-lg" type="submit" value="Generovat Java Random Gaussian" formaction="result?type=1"/>
					<input class="btn btn-alfa btn-lg" type="submit" value="Generovat Java Random Integer" formaction="result?type=2"/>
					<input class="btn btn-alfa btn-lg" type="submit" value="Generovat Java Random Double" formaction="result?type=3"/>
					
				</div>
			</form:form>
		</div>
		<h2 class="sub-header">Výsledky</h2>
		<div class="table-responsive" style="height: 300px; overflow:scroll;">
            <table class="table table-striped">
	            <tbody>
					<c:forEach var="result" items="${results}">
						  	  <tr><td>${result}</td></tr>
			        </c:forEach>
	        	</tbody>
        	</table>
		</div>
		
		<h2 class="sub-header">Testy</h2>
		
		<div class="table-responsive">
            <table class="table table-striped">
            <thead><tr><th width="140px">Test bodů růstu</th><th></th></tr></thead>
            	<tbody>
					<c:forEach var="o" items="${outputGP}">
						  	  <tr><td>${o.key}</td><td>${o.value}</td></tr>
			        </c:forEach>
	        	</tbody>
        	</table>
		</div>
		
		<div class="table-responsive">
            <table class="table table-striped">
            <thead><tr><th style="width: 140px;">Kolmogorovův-Smirnovův test R(0; 1)</th><th></th></tr></thead>
            	<tbody>
					<c:forEach var="o" items="${outputKSE}">
						  	  <tr><td>${o.key}</td><td>${o.value}</td></tr>
			        </c:forEach>
	        	</tbody>
        	</table>
		</div>
		<div class="table-responsive">
            <table class="table table-striped">
            <thead><tr><th style="width: 140px;">Test bodů zvratu</th><th></th></tr></thead>
            	<tbody>
					<c:forEach var="o" items="${outputTP}">
						  	  <tr><td>${o.key}</td><td>${o.value}</td></tr>
			        </c:forEach>
	        	</tbody>
        	</table>
		</div>
		<div class="table-responsive">
            <table class="table table-striped">
            <thead><tr><th style="width: 140px;">Test korelace (Spearmanův koeficient)</th><th></th></tr></thead>
            	<tbody>
					<c:forEach var="o" items="${outputSRO}">
						  	  <tr><td>${o.key}</td><td>${o.value}</td></tr>
			        </c:forEach>
	        	</tbody>
        	</table>
		</div>
		<div class="table-responsive">
            <table class="table table-striped">
            <thead><tr><th style="width: 140px;">Run test</th><th></th></tr></thead>
            	<tbody>
					<c:forEach var="o" items="${outputR}">
						  	  <tr><td>${o.key}</td><td>${o.value}</td></tr>
			        </c:forEach>
	        	</tbody>
        	</table>
		</div>
		
	
	</div>
	<!-- /.container -->


	<!-- Bootstrap core JavaScript
    ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
	<!-- Latest compiled and minified JavaScript -->
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>
</body>
</html>
