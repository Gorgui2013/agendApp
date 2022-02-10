<%@ include file="base_begin.jsp"%>
<%@ page session="false"%>
<div class="col-lg-4 mx-auto" style="margin-top: 7em">
	<ul class="nav nav-pills nav-justified mb-3" id="pills-tab" role="tablist">
		<li class="nav-item" role="presentation">
			<button class="nav-link active" id="connexion-tab"
				data-bs-toggle="pill" data-bs-target="#connexion" type="button"
				role="tab" aria-controls="connexion" aria-selected="true">Se
				connecter</button>
		</li>
		<li class="nav-item" role="presentation">
			<button class="nav-link" id="inscription-tab" data-bs-toggle="pill"
				data-bs-target="#inscription" type="button" role="tab"
				aria-controls="inscription" aria-selected="false">S'inscrire</button>
		</li>
	</ul>
	<div class="tab-content" id="pills-tabContent">
		<div class="tab-pane fade show active rounded-3 card card-body" id="connexion" role="tabpanel"
			aria-labelledby="connexion-tab">
			<div align="center" style="margin-bottom: 1em">
				<h3>Connexion</h3>
				<c:if test="${not empty message }">
					<div class="alert alert-danger">${ message }</div>
				</c:if>
			</div>
			<form action="agenda.path" method="post">
				<div class="form-group mb-3">
					<input type="text" class="form-control" name="nomUtilisateur"
						placeholder="Nom d'utilisateur" required>
				</div>
				<div class="form-group mb-3">
					<input type="password" class="form-control" name="motDePasse"
						placeholder="Mot de passe" required>
				</div>
				<div align="center">
					<button type="submit" class="btn btn-primary">Connexion</button>
				</div>
			</form>
		</div>
		<div class="tab-pane fade rounded-3 card card-body" id="inscription" role="tabpanel"
			aria-labelledby="inscription-tab">
			<div align="center" style="margin-bottom: 1em">
				<h3>Inscription</h3>
				<c:if test="${not empty message }">
					<div class="alert alert-danger">${ message }</div>
				</c:if>
			</div>
			<form action="register.path" method="post">
				<div class="form-group mb-3">
					<input type="text" class="form-control" name="nom"
						placeholder="Votre nom" required>
				</div>
				<div class="form-group mb-3">
					<input type="text" class="form-control" name="prenom"
						placeholder="Votre prénom" required>
				</div>
				<div class="form-group mb-3">
					<input type="text" class="form-control" name="nomUtilisateur"
						placeholder="Nom d'utilisateur" required>
				</div>
				<div class="form-group mb-3">
					<input type="password" class="form-control" name="motDePasse"
						placeholder="Mot de passe" required>
				</div>
				<div align="center">
					<button type="submit" class="btn btn-primary">Créer un compte</button>
				</div>
			</form>
		</div>
	</div>
</div>
<%@ include file="base_end.jsp"%>