<%@include file="../header.jsp"%>

<div class="ui main text container">
	<div id="content-placeholder"></div>
	<div id="content-sejours"></div>
</div>


<script id="entry-template" type="text/x-handlebars-template">
<div class="ui one column container">
	<div class="column">
		<h1>Informations du client :</h1>
		<div class="ui card">
			<div class="image">
				<img src="${url}/resources/images/kristy.png">
			</div>
			<div class="content">
				<a class="header">{{numCli}}. {{nomCli}}</a>
				<div class="meta">
					<span class="date">{{adrRueCli}}</span> <span class="date">{{cpCli}}
						{{villeCli}}</span>
				</div>
				<div class="description"></div>
			</div>
			<div class="extra content">
				<a> <i class="user icon"></i> {{pieceCli}} {{numPieceCli}}
				</a>
			</div>
		</div>
	</div>
</div>
</script>

<script id="sejours-template" type="text/x-handlebars-template">
<div class="container space">
	<h1>Liste des s�jours :</h1>
	<div class="ui cards">
		{{#if Sejour}} 
		{{#Sejour}}
		<div class="card">
			<div class="content">
				<div class="header">Num�ro : {{numSej}}</div>
				<div class="meta">
					<p>
						<i class="calendar icon"></i>Du {{formatDate dateDebSej "short"}}
						au {{formatDate dateFinSej "short"}}
					</p>
					<p>
						<i class="users icon"></i>{{nbPersonnes}}
					</p>
				</div>
				<div class="description">
					<h4>Listes des activit�s</h4>
					{{#if activites}}
					<ul>
						{{#activites}}
						<li><a href="">Num�ro : {{idActivite}}
								<p>
									<i class="calendar icon"></i>{{formatDate dateJour "long"}}
								</p>
						</a></li> {{/activites}}
					</ul>
					{{else}} Il n'y a pas d'activit�. {{/if}}

					<h4>Emplacement :</h4>
					{{#if emplacement}}
					<ul>
						<li>Num�ro : {{emplacement.numEmpl}}</li>
						<li>Nombre de personnes max : {{emplacement.nbPersMaxEmpl}}</li>
						<li>Surface : {{emplacement.surfaceEmpl}}</li>
						<li>Type d'emplacement :
							{{emplacement.typeEmplacement.codeTypeE}} -
							{{emplacement.typeEmplacement.libtypepl}}</li>
						<li>Prix : {{emplacement.typeEmplacement.tariftypepl}}<i
							class="eur icon"></i></li>
					</ul>
					{{else}} Il n'y a pas d'emplacement. {{/if}} <a
						href="../sejour/{{numSej}}">
						<div class="ui animated button left floated">
							<div class="visible content">Afficher</div>
							<div class="hidden content">
								<i class="file outline icon"></i>
							</div>
						</div>
					</a>

				</div>
				<a href="../sejour/{{numSej}}?output=pdf">
					<div class="ui animated button right floated">
						<div class="visible content">Facture</div>
						<div class="hidden content">
							<i class="file pdf outline icon"></i>
						</div>
					</div>
				</a>
			</div>
		</div>
		{{/Sejour}} 
		{{else}}
		<div class="card">
			<div class="content">
				<h3>Ce client n'a pas de s�jours.</h3>
			</div>
		</div>
		{{/if}}
	</div>
</div>
</script>

<script type="text/javascript">
	var DateFormats = {
		short : "DD/MM/YYYY",
		long : "DD/MM/YYYY � HH:mm"
	};

	$(document).ready(function() {
		var source = $("#entry-template").html();
		var template = Handlebars.compile(source);
		var context = ${user};
		console.log(context);
		$("#content-placeholder").html(template(context));

		var source = $("#sejours-template").html();
		var template = Handlebars.compile(source);
		var context = ${sejours};
		console.log(context);
		$("#content-sejours").html(template(context));
	});
</script>

<%@include file="../footer.jsp"%>