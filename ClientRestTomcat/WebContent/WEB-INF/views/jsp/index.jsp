<%@include file="header.jsp"%>
<div class="ui main text container">
	<H1 class="ui header">Bienvenue sur la page d'accueil</H1>

	<h2>Que souhaitez-vous faire ?</h2>

	<div class="options">

		<ol class="ui list">
			<li value="*"><h3>Client:</h3>
				<ol>
					<li value=""><a class="item" href="${url}/user"><i
							class="sort content ascending icon"></i>Afficher les utilisateurs</a></li>
					<li value=""><a class="item" href="${url}/user/add"><i
							class="user icon"></i>Ajouter un utilisateur</a></li>
				</ol></li>
			<li value="*"><h3>Séjours:</h3>
				<ol>
					<li value=""><a class="item" href="${url}/sejour"><i class="sort content ascending icon"></i>Afficher
						les séjours</a></li>
					<li value=""><a class="item" href="${url}/sejour/add"><i class="tree icon"></i>Ajouter
						un séjour</a></li>
				</ol></li>
		</ol>
	</div>


</div>
<%@include file="footer.jsp"%>