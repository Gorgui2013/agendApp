<div class="col-sm-10 py-4 p-4"
	style="height: 100vh; background-color: #fff;">
	<div class="row">
		<div class="col-sm-6">
			<div class="container-fluid card card-cody shadow-sm">
				<div class="row py-4">
					<h5 class="mb-4">
						<c:choose>
							<c:when test="${event.getIdEvent() != 0}">Modification</c:when>
							<c:when test="${event.getIdEvent() == 0}">Ajout</c:when>
							<c:otherwise>Ajout</c:otherwise>
						</c:choose>
						evennement
					</h5>
					<c:if test="${not empty message }">
						<div class="alert alert-success mb-4">${ message }</div>
					</c:if>
					<div class="row">
						<c:choose>
							<c:when test="${event.getIdEvent() != 0}">
								<form action="event.path?editEvent=${event.getIdEvent()}"
									method="post">
							</c:when>
							<c:when test="${event.getIdEvent() == 0}">
								<form action="event.path" method="post">
							</c:when>
							<c:otherwise>
								<form action="event.path" method="post">
							</c:otherwise>
						</c:choose>
						<div class="form-group mb-4">
							<input type="text" class="form-control" name="nom"
								placeholder="Nom de l'évennement" required
								value="${event.getNom()}">
						</div>
						<div class="row">
							<div class="col-sm">
								<div class="form-group mb-4">
									<input type="text" class="form-control" name="dateHeureDebut"
										placeholder="Date et heure de début"
										onfocus="(this.type='datetime-local')"
										onblur="(this.type='text')" required
										value="${event.getDateHeureDebut()}">
								</div>
							</div>
							<div class="col-sm">
								<div class="form-group mb-4">
									<input type="text" class="form-control" name="dateHeureFin"
										placeholder="Date et heure de fin"
										onfocus="(this.type='datetime-local')"
										onblur="(this.type='text')" required
										value="${event.getDateHeureFin()}">
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-sm">
								<div class="form-group mb-4">
									<input type="text" class="form-control" name="lieu"
										placeholder="Lieu" required value="${event.getLieu()}">
								</div>
							</div>
							<div class="col-sm">
								<div class="form-group mb-4">
									<select name="category" class="form-control"
										placeholder="Categorie" required>
										<c:forEach items="${categories}" var="p">
											<option value="${p.getIdCategory()}">${p.getNom()}</option>
										</c:forEach>
									</select>
								</div>
							</div>
							<div class="col-sm">
								<div class="form-group mb-4">
									<select name="priorite" class="form-control"
										placeholder="Priorité" required>
										<option value="1">!</option>
										<option value="2">!!</option>
										<option value="3">!!!</option>
									</select>
								</div>
							</div>
						</div>
						<!-- <div class="form-group mb-4">
      <select name="status" class="form-control">
        <option value="false"></option>
        <option value="true"></option>
      </select>
    </div> -->
						<div align="right">
							<c:choose>
								<c:when test="${event.getIdEvent() != 0}">
									<a class="btn btn-danger"
										href="event.path?deleteEvent=${event.getIdEvent()}"
										onclick="return confirm('Voulez-vous vraiment supprimer cette information ?')">
										<i class="fas fa-trash"></i> Supprimer
									</a>
									<button type="submit" class="btn btn-primary">
										<i class="fas fa-save"></i> Enregistrer
									</button>
								</c:when>
								<c:when test="${event.getIdEvent() == 0}">
									<button type="submit" class="btn btn-primary">
										<i class="fas fa-save"></i> Enregistrer
									</button>
								</c:when>
								<c:otherwise>
									<button type="submit" class="btn btn-primary">
										<i class="fas fa-save"></i> Enregistrer
									</button>
								</c:otherwise>
							</c:choose>
						</div>
					</div>
					</form>
				</div>
			</div>
		</div>
		<c:if test="${event.getIdEvent() != 0}">
			<div class="col-sm-3">
				<div class="container-fluid py-4 card card-cody shadow-sm">
					<h5 class="mb-4">
						Participants
					</h5>
					<ul class="list-group">
						<c:forEach items="${users}" var="u">
							<li
								class="list-group-item d-flex justify-content-between align-items-center"><i
								class="fas fa-user" style="margin-right: 2px;"></i> ${ u.getNomUtilisateur() }
								<span class="badge bg-danger rounded-pill"><a
									href="event.path?editEvent=${event.getIdEvent()}&removeUser=${u.getIdUser()}"
									onclick="return confirm('Voulez-vous vraiment supprimer cette utilisateur ?')"><i
										class="fas fa-minus" style="color: #eee"></i></a></span></li>
						</c:forEach>
					</ul>
				</div>
			</div>
			<div class="col-sm-3">
				<div class="container-fluid py-4 card card-cody shadow-sm">
					<h5 class="mb-4">
						Non Participants
					</h5>
					<ul class="list-group">
						<c:forEach items="${noUsers}" var="n">
							<li
								class="list-group-item d-flex justify-content-between align-items-center"><i
								class="fas fa-user" style="margin-right: 2px;"></i> ${ n.getNomUtilisateur() }
								<span class="badge bg-success rounded-pill"><a
									href="event.path?editEvent=${event.getIdEvent()}&addUser=${n.getIdUser()}"
									onclick="return confirm('Voulez-vous vraiment ajouter cette utilisateur ?')"><i
										class="fas fa-plus" style="color: #eee"></i></a></span></li>
						</c:forEach>
					</ul>
				</div>
			</div>
		</c:if>
	</div>
</div>