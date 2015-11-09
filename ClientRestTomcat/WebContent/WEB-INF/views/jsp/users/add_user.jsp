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

	<form class="ui form" method="post" action="<c:if test="${edit == true}">../</c:if>save">
		<c:if test="${client.numCli != null}">
			<input type="hidden" name="numCli" value="${client.numCli}">
		</c:if>
		<h4 class="ui dividing header">Informations du client</h4>
		<div class="field">
			<label>Nom</label>
			<div class="one fields">
				<div
					class="field <c:if test="${erreur['nom'] != null}">error</c:if>">
					<input type="text" name="nom" placeholder="Nom"
						value="${client.nomCli}">
				</div>
			</div>
		</div>
		<div class="field">
			<label>Adresse</label>
			<div class="three fields">
				<div
					class="field <c:if test="${erreur['adresse'] != null}">error</c:if>">
					<input type="text" name="adresse" placeholder="Adresse"
						value="${client.adrRueCli}">
				</div>
				<div
					class="field <c:if test="${erreur['codePostal'] != null}">error</c:if>">
					<input type="text" name="codePostal" placeholder="Code postale"
						value="${client.cpCli}">
				</div>
				<div
					class="field <c:if test="${erreur['ville'] != null}">error</c:if>">
					<input type="text" name="ville" placeholder="Ville"
						value="${client.villeCli}">
				</div>
			</div>
		</div>

		<div class="field">
			<label>Pièce client</label>
			<div class="two fields">
				<div
					class="field <c:if test="${erreur['pieceCli'] != null}">error</c:if>">
					<input type="text" name="pieceCli" placeholder="Piece du client"
						value="${client.pieceCli}">
				</div>
				<div
					class="field <c:if test="${erreur['numPieceCli'] != null}">error</c:if>">
					<input type="text" name="numPieceCli"
						placeholder="Numéro de pièce du client"
						value="${client.numPieceCli}">
				</div>
			</div>
		</div>
		<input class="ui button" type="submit" value="Envoyer">
	</form>

</div>
<%@include file="../footer.jsp"%>