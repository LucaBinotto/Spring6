<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset-UTF-8" language="java"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!doctype html>
<html lang="en">
<%@ include file="common/head.jspf"%>

<body>

	<%@ include file="common/navbar.jspf"%>


	<section class="content-main">

		<div class="content-header">
			<h2 class="content-title">Lista Prodotti</h2>
			<div>
				<a href="/articoli/grid" class="btn btn-success"> <i
					class="material-icons md-grid_on"></i> Grid View
				</a> <a href="#" class="btn btn-primary newart"> <i
					class="material-icons md-plus"></i> Nuovo
				</a>
			</div>
		</div>

		<div class="card mb-4">

			<!-- Inizio Header -->
			<header class="card-header">
				<div class="row gx-3">
					<div class="col-lg-4 col-md-6 me-auto">
						<form:form class="form-inline my-2 my-lg-0" id="search"
							role="search" method="GET" action="/alphashop/articoli/search">

							<input type="text" id="filtroInput" onClick="this.select();"
								name="filtro" value="${filtro}" placeholder="Cerca..."
								class="form-control">
						</form:form>
					</div>
					<div class="col-lg-2 col-6 col-md-3">
						<select class="form-select">
							<option>Status</option>
							<option>Attivo</option>
							<option>Eliminato</option>
							<option>Tutti</option>
						</select>
					</div>
					<div class="col-lg-2 col-6 col-md-3">
						<select class="form-select">
							<option>Cetegorie</option>
							<option>Alimentari</option>
							<option>Chimico</option>
							<option>Freschi</option>
						</select>
					</div>
					<div class="col-lg-2 col-6 col-md-3">
						<select class="form-select">
							<option>Mostra 20</option>
							<option>Mostra 30</option>
							<option>Mostra 40</option>
						</select>
					</div>
				</div>
			</header>
			<!-- Fine Header -->

			<!-- Inizio Body -->
			<div class="card-body">
				<div class="table-responsive">
					<table class="table table-hover">
						<thead>
							<tr>
								<th scope="col">CodArt</th>
								<th scope="col">Descrizione</th>
								<th scope="col">Um</th>
								<th scope="col">Pezzi</th>
								<th scope="col">Peso</th>
								<th scope="col">Prezzo</th>
								<th scope="col">Tipo</th>
								<th scope="col">Data</th>
								<th scope="col">Status</th>
								<th scope="col" class="text-end">Azione</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${articoli}" var="article">
								<tr>
									<td class="tbl-string">${article.codart}</td>
									<td class="tbl-string">${article.descrizione}</td>
									<td class="tbl-string">${article.um}</td>
									<td class="tbl-string">${article.pzcart}</td>
									<td class="tbl-string">${article.pesonetto}</td>
									<td class="tbl-string"><fmt:formatNumber
											value="${article.prezzo}" type="currency" currencyCode="USD" /></td>

									<td class="tbl-string infoBadge"><span
										class="badge rounded-pill text-bg-primary"> Normale </span></td>

									<td class="tbl-string">${article.datacreazione}</td>
									<td class="tbl-string infoBadge"><span
										class="badge rounded-pill text-bg-primary">
											${article.idstatoart} </span></td>


									<td class="text-end">
										<button class="btn btn-light text-danger">Elimina</button> <!-- DropDown Menu -->
										<div class="dropdown">
											<a href="#" data-bs-toggle="dropdown" class="btn btn-light">
												<i class="material-icons md-more_horiz"></i>
											</a>
											<ul class="dropdown-menu">
												<li><a class="dropdown-item" href="#">Dettaglio</a></li>
												<li><a class="dropdown-item" href="#">Info</a></li>
												<li><a class="dropdown-item" href="#">Modifica</a></li>
											</ul>
										</div>
									</td>

								</tr>
							</c:forEach>

						</tbody>
					</table>

					<c:if test="${notFound}">
						<div class="alert alert-danger" role="alert">Articolo/i non
							presente/i in anagrafica</div>
					</c:if>

					<!-- Paginazione -->
					<nav class="float-end mt-3" aria-label="Page navigation">
						<ul class="pagination">

							<!-- Tasto Next Disabilitato -->
							<c:if test="${PageNum <= 1}">
								<li class="page-item disabled"><a class="page-link"
									href="#" aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
										<span class="sr-only">Precedente</span>
								</a></li>
							</c:if>

							<spring:url
								value="/articoli/cerca/parametri;paging=${PageNum},0,-1?filtro=${filtro}&selected=${RecPage}"
								var="urlPrevious" />

							<!-- Tasto Next Abilitato -->
							<c:if test="${PageNum > 1}">
								<li class="page-item"><a class="page-link"
									href="${urlPrevious}" aria-label="Previous"> <span
										aria-hidden="true">&laquo;</span> <span class="sr-only">Precedente</span>
								</a></li>
							</c:if>

							<c:forEach items="${Pages}" var="Pagine">

								<spring:url
									value="/articoli/cerca/parametri;paging=${Pagine.pageNum},0,0?filtro=${filtro}&selected=${RecPage}"
									var="urlPage" />

								<c:choose>
									<c:when test="${Pagine.isSelected}">
										<li class="page-item active"><a class="page-link"
											href="${urlPage}"> ${Pagine.pageNum} </a></li>
									</c:when>
									<c:otherwise>
										<li class="page-item"><a class="page-link"
											href="${urlPage}"> ${Pagine.pageNum} </a></li>
									</c:otherwise>
								</c:choose>
							</c:forEach>

							<spring:url
								value="/articoli/cerca/parametri;paging=${PageNum},0,1?filtro=${filtro}&selected=${RecPage}"
								var="urlNext" />

							<!-- Tasto Previous -->
							<li class="page-item"><a class="page-link" href="${urlNext}"
								aria-label="Next"> <span aria-hidden="true">&raquo;</span> <span
									class="sr-only">Successivo</span>
							</a></li>
						</ul>
					</nav>
				</div>

			</div>
		</div>
		<!-- Fine Body -->

	</section>

	<script>
		document.getElementById("filtroInput").addEventListener("click",
				function() {
					this.select();
				});
	</script>
	<%@ include file="common/foot.jspf"%>
</body>
</html>