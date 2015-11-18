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

	<form class="ui form" method="post" action="../save">
		<c:if test="${sejour.numSej != null}">
			<input type="hidden" name="numSej" value="${sejour.numSej}">
		</c:if>
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
			{{#ifCond numCli ../Current}}
			<option value="{{numCli}}" selected>{{nomCli}}</option>
			{{else}}
			<option value="{{numCli}}">{{nomCli}}</option>
			{{/ifCond}}		
		{{/Client.Client}}
	</select>
</script>

<script id="emplacement-template" type="text/x-handlebars-template">
	<select name="emplacement" id="emplacement">	
		{{#Emplacement.Emplacement}}		
			{{#ifCond numEmpl ../Current}}
			<option value="{{numEmpl}}" selected>{{numEmpl}}</option>
			{{else}}
			<option value="{{numEmpl}}">{{numEmpl}}</option>
			{{/ifCond}}		
		{{/Emplacement.Emplacement}}
	</select>
</script>

<script type="text/javascript">
	$(document).ready(function() {
		var currentClient = ${sejour.client.numCli};
		var source = $("#entry-template").html();
		var template = Handlebars.compile(source);
		var clients = ${clients};
		var context = {
			'Client' : clients,
			'Current' : currentClient,
		};
		$("#content-clients").html(template(context));

		var currentEmplacement = ${sejour.emplacement.numEmpl};
		var source = $("#emplacement-template").html();
		var template = Handlebars.compile(source);
		var emplacement = ${emplacements};		
		var context = {
			'Emplacement' : emplacement,
			'Current' : currentEmplacement,
		};
		$("#content-emplacements").html(template(context));


		
	});
</script>

<%@include file="../footer.jsp"%>