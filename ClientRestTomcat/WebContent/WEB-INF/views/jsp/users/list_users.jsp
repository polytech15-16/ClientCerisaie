<%@include file="../header.jsp"%>

<div id="content-placeholder"></div>

<script id="entry-template" type="text/x-handlebars-template">
	<div class="ui main text container">
    	<h1>Liste des utilisateurs</h1>
			{{#each Client}}
				<div class="ui raised very padded text container segment">
  					<h2 class="ui header">{{nomCli}}</h2>
  					<p>Numéro : {{numCli}}</p>
  					<p>Adresse : {{adrRueCli}} {{cpCli}} {{villeCli}}</p>
					<p>Pièce du client : {{pieceCli}}|{{numPieceCli}}</p>
					<p>
						<a href="user/{{numCli}}"><button class="ui inverted blue button">Afficher</button></a>
						<a href="user/{{numCli}}/edit"><button class="ui inverted green button">Modifier</button></a>
						<form method="post" action="user/delete">
							<input type="hidden" name="numCli" value="{{numCli}}">
							<input class="ui inverted red button" type="submit" value="Supprimer">
						</form>				
					</p>
				</div>
			{{/each}}
	</div>
</script>

<script type="text/javascript">
	$(document).ready(function() {
		var source = $("#entry-template").html();
		var template = Handlebars.compile(source);
		var context = ${users};
		console.log(context);
		$("#content-placeholder").html(template(context));
	});
</script>


<%@include file="../footer.jsp"%>