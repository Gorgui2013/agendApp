<div class="col-sm-10 py-4 p-4"
	style="height: 100vh; background-color: #fff;">
	<div class="row">
		<div class="col-sm-8">
			<div class="container-fluid card card-cody shadow-sm">
				<div class="row py-4">
					<div class="col-sm-6">
						<h4>Calendrier</h4>
					</div>
					<div class="col-sm-6">
						<div class="row">
							<div class="col-sm-4 cell-nonil">
								<a href="agenda.path?month=${date.getMonth()-1}"><i
									class="fas fa-chevron-left"></i></a>
							</div>
							<div class="col-sm-4">${month }</div>
							<div class="col-sm-4 cell-nonil">
								<a href="agenda.path?month=${date.getMonth()+1}"><i
									class="fas fa-chevron-right"></i></a>
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<table class="table" style="text-align: center">
						<thead>
							<tr>
								<th>Lundi</th>
								<th>Mardi</th>
								<th>Mercredi</th>
								<th>Jeudi</th>
								<th>Vendredi</th>
								<th>Samedi</th>
								<th>Dimanche</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="i" begin="0" end="${days/7+1}" step="1">
								<tr>
									<c:forEach var="j" begin="0" end="6" step="1">
										<c:choose>
											<c:when test="${tab[i][j].getNumJour() == 0}">
												<td class="cell-nil"
													style="padding-top: 25px; padding-bottom: 25px">-</td>
											</c:when>
											<c:when test="${tab[i][j].getNumJour() != 0}">
												<td class="cell-nonil"
													style="padding-top: 25px; padding-bottom: 25px"><a
													class="position-relative"
													href="agenda.path?month=${date.getMonth()}&jour=${tab[i][j].getNumJour()}">${tab[i][j].getNumJour()}
														<c:if test="${tab[i][j].isHasEvent() == true}">
															<span
																class="position-absolute top-0 start-50 translate-middle badge rounded-pill bg-warning">.</span>
														</c:if>
												</a></td>
											</c:when>
											<c:otherwise>
												<td class="cell-nil"
													style="padding-top: 25px; padding-bottom: 25px">-</td>
											</c:otherwise>
										</c:choose>
									</c:forEach>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>
		<div class="col-sm-4 py-4 card card-cody shadow-sm">
			<div class="row mb-3">
				<div class="col-sm-6">
					<h4>Evennements</h4>
				</div>
				<div class="col-sm-6" align="right">
					<a class="nav-link" href="event.path"><i class="fas fa-plus"
						style="margin-right: 10px;"></i> Ajouter</a>
				</div>
			</div>
			<c:if test="${not empty message }">
				<div class="alert alert-success mb-4">${ message }</div>
			</c:if>
			<hr />
			<div class="container-fluid">
				<div class="list-group">
					<c:forEach items="${eventsDay}" var="p">
						<a href="event.path?editEvent=${p.getIdEvent()}"
							class="list-group-item list-group-item-action">
							<div class="d-flex w-100 justify-content-between">
								<h6 class="mb-1">${p.getNom()}</h6>
								<small> <c:choose>
										<c:when test="${p.getPriorite() == 1}">!</c:when>
										<c:when test="${p.getPriorite() == 2}">!!</c:when>
										<c:when test="${p.getPriorite() == 3}">!!!</c:when>
										<c:otherwise>priorité</c:otherwise>
									</c:choose>
								</small>
							</div>
							<p class="mb-1">${p.getLieu()}:
								${p.getHoursDebut()}h${p.getMinutesDebut()} -
								${p.getHoursFin()}h${p.getMinutesFin()}</p>
						</a>
					</c:forEach>
				</div>
			</div>
		</div>
	</div>
</div>