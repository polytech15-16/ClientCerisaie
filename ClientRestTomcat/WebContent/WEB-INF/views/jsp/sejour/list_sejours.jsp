<%@include file="../header.jsp"%>

<div id="content-placeholder"></div>

<script id="entry-template" type="text/x-handlebars-template">
	<div class="ui main text container">
    	<h1>Liste des séjours</h1>
			{{#each Sejour}}
				<div class="ui raised very padded text container segment">
  					<h2 class="ui header">Numéro : {{numSej}}</h2>
					<p>
						<i class="calendar icon"></i>Du {{formatDate dateDebSej "short"}}
						au {{formatDate dateFinSej "short"}}					
						<i class="users icon"></i>{{nbPersonnes}}
					</p>
  					<p>Client : {{client.nomCli}}</p>  					
					<p>
						<a href="sejour/{{numSej}}"><button class="ui inverted blue button">Afficher</button></a>
						<a href="sejour/{{numSej}}/edit"><button class="ui inverted green button">Modifier</button></a>
						<a href="sejour/{{numSej}}?output=pdf">
							<div class="ui animated button right floated">
								<div class="visible content">Facture</div>
								<div class="hidden content">
									<i class="file pdf outline icon"></i>
								</div>
							</div>
						</a>
						<form method="post" action="sejour/delete">
							<input type="hidden" name="numSej" value="{{numSej}}">
							<input class="ui inverted red button" type="submit" value="Supprimer">
						</form>				
					</p>
				</div>
			{{/each}}
	</div>
</script>

<script type="text/javascript">
	var DateFormats = {
		short : "DD/MM/YYYY",
		long : "DD/MM/YYYY à HH:mm"
	};

	$(document).ready(function() {
		var source = $("#entry-template").html();
		var template = Handlebars.compile(source);
		var context = ${sejours};
		console.log(context);
		$("#content-placeholder").html(template(context));
	});
</script>


<%@include file="../footer.jsp"%>