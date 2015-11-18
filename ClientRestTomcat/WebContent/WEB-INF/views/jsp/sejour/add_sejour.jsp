<%@include file="../header.jsp"%>
<div class="ui main text container">
	<c:if test="${erreur != null}">
		<div class="ui warning message">
			<div class="header">Des erreurs sont apparus</div>
			<ul class="list">
				<c:forEach items="${erreur}" var="err">
					<li>${err.value}</li>
				</c:forEach>
			</ul>
		</div>
	</c:if>

	<form class="ui form" method="post" action="save">
		<h4 class="ui dividing header">Informations du séjour</h4>
		<div class="two fields">
			<div class="field">
				<label>Date de début</label>
				<div
					class="field <c:if test="${erreur['dateDebSej'] != null}">error</c:if>">
					<input type="date" id="dateDebSej" name="dateDebSej"
						value="<fmt:formatDate value='${sejour.dateDebSej}' pattern='yyyy-MM-dd'/>">
				</div>
			</div>
			<div class="field">
				<label>Date de fin</label>
				<div
					class="field <c:if test="${erreur['dateFinSej'] != null}">error</c:if>">
					<input type="date" id="dateFinSej" name="dateFinSej"
						value="<fmt:formatDate value='${sejour.dateFinSej}' pattern='yyyy-MM-dd'/>">
				</div>
			</div>
		</div>
		<div class="three fields">
			<div class="field">
				<label>Nombre de personnes</label>
				<div
					class="field <c:if test="${erreur['nbPersonnes'] != null}">error</c:if>">
					<input type="number" name="nbPersonnes"
						placeholder="Nombre de personnes" value="${sejour.nbPersonnes}">
				</div>
			</div>
		</div>

		<div class="two fields">
			<div class="field">
				<label>Client</label>
				<div
					class="<c:if test="${erreur['client'] != null}">error</c:if>">
					<div id="content-clients"></div>
				</div>
			</div>
			<div class="field">
				<label>Emplacement</label>
				<div
					class="<c:if test="${erreur['emplacement'] != null}">error</c:if>">
					<div id="content-emplacements"></div>
				</div>
			</div>
		</div>
		<input class="ui button" type="submit" value="Envoyer">
	</form>
</div>

<script id="entry-template" type="text/x-handlebars-template">
	<select name="client" id="client">	
		{{#Client.Client}}		
			<option value="{{numCli}}">{{nomCli}}</option>
		{{/Client.Client}}
	</select>
</script>

<script id="emplacement-template" type="text/x-handlebars-template">
	<select name="emplacement" id="emplacement">	
		{{#Emplacement.Emplacement}}		
			<option value="{{numEmpl}}">{{numEmpl}}</option>
		{{/Emplacement.Emplacement}}
	</select>
</script>

<script type="text/javascript">
	$(document).ready(function() {
		var source = $("#entry-template").html();
		var template = Handlebars.compile(source);
		var clients = ${clients};
		var context = {
			'Client' : clients,
		};
		$("#content-clients").html(template(context));

		var source = $("#emplacement-template").html();
		var template = Handlebars.compile(source);
		var emplacement = ${emplacements};		
		var context = {
			'Emplacement' : emplacement,			
		};
		$("#content-emplacements").html(template(context));		
	});
</script>

<%@include file="../footer.jsp"%>