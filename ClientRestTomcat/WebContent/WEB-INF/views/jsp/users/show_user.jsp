<%@include file="../header.jsp"%>

<div id="content-placeholder"></div>

<script id="entry-template" type="text/x-handlebars-template">
<div class="ui main text container">
	<div class="ui two column doubling stackable grid container">
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
		<div class="column">
			<h1>Liste des séjours :</h1>
			<div class="ui cards">
				{{#each sejours}}
				<div class="card">
					<div class="content">
						<div class="header">Numéro : {{numSej}}</div>
						<div class="meta">
							<p><i class="calendar icon"></i>Du {{formatDate dateDebSej "short"}} au {{formatDate dateFinSej "short"}}</p>
							<p><i class="users icon"></i>{{nbPersonnes}}</p>
						</div>
						<div class="description">
							<h4>Listes des activités</h4>
							<ul>
								{{#activites}}
									<li><a href="">Numéro : {{idActivite}} <p><i class="calendar icon"></i>{{formatDate dateJour "long"}}</p></a></li> 
								{{/activites}}
							</ul>
						</div>
					</div>
				</div>
				{{/each}}
			</div>
		</div>
	</div>
	</script>

	<script type="text/javascript">
	var DateFormats = {
		       short: "DD/MM/YYYY",
		       long: "DD/MM/YYYY à HH:mm"
		};
	
		$(document).ready(function() {
			var source = $("#entry-template").html();
			var template = Handlebars.compile(source);
			var context = ${user};
			console.log(context);
			$("#content-placeholder").html(template(context));
		});
	</script>

	<%@include file="../footer.jsp"%>