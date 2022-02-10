<div class="col-sm-2" style="height: 100vh; background-color: #1111;">
	<!-- Default dropend button -->
	<div class="row m-4">
		<div class="card card-body" style="color: #00407b">
			<div>
				<i class="fas fa-user-circle" style="margin-right: 10px;"></i> ${ user.getNomUtilisateur() }
			</div>
			<hr>
			<div>
				<form action="logout.path" method="post">
					<button type="submit" class="btn btn-sm btn-outline-secondary w-100">
						<i class="fas fa-power-off" style="color: red;"></i> Quitter
					</button>
				</form>
			</div>
		</div>
	</div>
	<nav class="nav flex-column m-4">
		<a class="nav-link" href="agenda.path"><i class="fas fa-calendar"
			style="margin-right: 10px"></i> Evennements</a>
		<!--a class="nav-link" href="event.path"><i class="fas fa-plus" style="margin-right: 10px"></i> Ajouter</a-->
		<a class="nav-link" href="category.path"><i class="fas fa-plus"
			style="margin-right: 10px"></i> Category</a>
	</nav>
</div>