<%@include file="../header.jsp"%>

<div id="content-placeholder"></div>

<script id="entry-template" type="text/x-handlebars-template">
<div class="ui main text container">
	<div class="column">
		<h1>Informations du s�jour :</h1>
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
					{{else}} 
						Il n'y a pas d'emplacement. 
					{{/if}}
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
		</div>
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
		var context = ${sejour};
		console.log(context);
		$("#content-placeholder").html(template(context));
	});
</script>

<%@include file="../footer.jsp"%>