<div class="container">

	<div class="row">

		<!-- Would be to display sidebar -->
		<div class="col-md-3">
			<%@include file="./shared/sidebar.jsp"%>
		</div>
		<!-- to display actual products-->
		<div class="col-md-9">

			<!-- Added breadcrum component -->
			<div class="row">

				<div class="col-lg-12">

					<ol class="breadcrumb">
						<li class="breadcrumb-item"><a href="${contextRoot}/home">Home</a></li>
						<li class="breadcrumb-item">Products</li>
						<li class="breadcrumb-item active">${product.name}</li>
					</ol>
				</div>


			</div>
		</div>
	</div>




	<div class="row">

		<!-- Display the product image -->
		<div class="col-xs-12 col-sm-4">
			<div class="thumnail">
				<img src="${images}/${product.code}.jpg" class="img img-responsive">
			</div>
		</div>

		<!-- Display the product description -->
		<div class="col-xs-12 col-sm-8">
			<h3>${product.name }</h3>
			<hr />

			<p>${product.description }</p>
			<hr />

			<h4>
				Price : <strong> &#8377; ${product.unitPrice } /- </strong>
			</h4>
			<hr />

			<c:choose>
				<c:when test="${product.quantity < 1}">

					<div style="color: red">Out Of Stock</div>

				</c:when>
				<c:otherwise>

					<h6>Quantity Available : ${product.quantity }</h6>

				</c:otherwise>
			</c:choose>

			<hr />

			<c:choose>
				<c:when test="${product.quantity < 1}">

			<a href="javascript:void(0)"
				class="btn bts-disabled"> <i class="fas fa-shopping-cart"></i>
				<strike>Add to cart</strike>
			</a> 
				</c:when>
				<c:otherwise>

					<a href="${contextRoot}/cart/add/${product.id }/product"
						class="btn bts-success"> <i class="fas fa-shopping-cart"></i>
							Add to cart
					</a> 
				</c:otherwise>
			</c:choose>

			<a href="${contextRoot}/show/all/products" class="btn bts-warning">
				<i class="fas fa-arrow-left"></i> Back
			</a>

		</div>

	</div>
</div>