<div class="col-sm-10 py-4"
	style="height: 100vh; background-color: #fff;">
	<div class="col-lg-10 mx-auto">
		<div class="row">
			<div class="col-sm-6">
				<div class="container-fluid card card-cody shadow-sm">
					<div class="row py-4">
						<h5 class="mb-4">Ajout catégorie</h5>
						<c:if test="${not empty message }">
							<div class="alert alert-success mb-4">${ message }</div>
						</c:if>
						<div class="row">
							<form action="category.path" method="post">
								<div class="form-group mb-4">
									<input type="text" class="form-control" name="nom"
										placeholder="Nom de la catégorie" required>
								</div>
								<div align="right">
									<button type="submit" class="btn btn-primary">
										<i class="fas fa-save"></i> Enregistrer
									</button>
								</div>
						</div>
						</form>
					</div>
				</div>
			</div>
			<div class="col-sm-6 py-4 card card-cody shadow-sm">
				<h5 class="mb-4">Catégories</h5>
				<ul class="list-group py-1">
					<c:forEach items="${categories}" var="p">
						<li
							class="list-group-item d-flex justify-content-between align-items-center">
							${p.getNom()} <span class="rounded-pill"><a
								href="category.path?idCategory=${p.getIdCategory()}"
								onclick="return confirm('Voulez-vous vraiment supprimer cette information ?')"><i
									class="fas fa-trash" style="color: red"></i></a></span>
						</li>
					</c:forEach>
				</ul>
			</div>
		</div>
	</div>
</div>