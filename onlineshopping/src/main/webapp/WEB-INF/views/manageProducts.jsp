<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>


<div class="container">

	<div class="row" style="text-align: left">
	
	<c:if test="${not empty message }">
		<div class="col-md-12">
				
				<div class="alert alert-success alert-dismissible">
					
					<button type="button" class="close" data-dismiss="alert">&times;</button>
				
					${message}
				
				</div>
		
		</div>
	</c:if>

		<div class="col-md-12">

			<div class="panel panel-primary">

				<div class="my-4 panel-heading">

					<h4>Product Management</h4>

				</div>

				<div class="panel-division">

					<!-- Form Elements -->
					
					<sf:form class="form-horizontal" modelAttribute="product"
							action="${contextRoot}/manage/products"
							method="POST"
							enctype="multipart/form-data"
							>

						<div class="form-group">

							<label class="control-label col-md-4" for="name" >Enter Product Name : </label>

							<div class="col-md-8">

								<sf:input type="text" path="name" id="name" placeholder="Product Name" class="form-control" /> 
								<sf:errors path="name" cssClass="help-block" element="em"/>
							</div>

						</div>

						<div class="form-group">

							<label class="control-label col-md-4" for="brand" >Enter Brand Name : </label>

							<div class="col-md-8">

								<sf:input type="text" path="brand" id="brand" placeholder="Brand Name" class="form-control" />
								<sf:errors path="brand" cssClass="help-block" element="em"/>
							</div>

						</div>

						<div class="form-group">

							<label class="control-label col-md-4" for="description" >Product Description </label>

							<div class="col-md-8">

								<sf:textarea type="text" path="description" id="description" class="form-control" /> 
								<sf:errors path="description" cssClass="help-block" element="em"/>
							</div>

						</div>

						<div class="form-group">

							<label class="control-label col-md-4" for="unitPrice" >Enter Unit Price : </label>

							<div class="col-md-8">

								<sf:input type="number" path="unitPrice" id="unitPrice" placeholder="Unit Price in &#8377; " class="form-control" /> 
								<sf:errors path="unitPrice" cssClass="help-block" element="em"/>
							</div>

						</div>

						<div class="form-group">

							<label class="control-label col-md-4" for="quantity" >Quantity Available : </label>

							<div class="col-md-8">

								<sf:input type="number" path="quantity" id="quantity" placeholder="Quantity Available" class="form-control" /> 
								
							</div>

						</div>


						<div class="form-group">

							<label class="control-label col-md-4" for="file" > Select an Image File : </label>

							<div class="col-md-8">

								<sf:input type="file" path="file" id="file" class="form-control" /> 
								
								<sf:errors path="file" cssClass="help-block" element="em" />
								
							</div>

						</div>

						<div class="form-group">

							<label class="control-label col-md-4" for="category" >Product Category : </label>

							<div class="col-md-8">

								<sf:select id="categoryId" path="categoryId" class="form-control"
										items="${categories }"
										itemLabel="name"
										itemValue="id"
								/>

							</div>

						</div>
						
						<c:if test="${product.id == 0}">
							<div class="col-md-8 text-right">
							<br />
							<button type="button" data-toggle="modal" data-target="#myCategoryModal" class="btn btn-warning btn-sm">Add Category</button>
							</div>
						</c:if>

						<!-- Hidden Fields for Product -->
						
						<sf:input type="hidden" path="id" />
						<sf:input type="hidden" path="code" />
						<sf:input type="hidden" path="active" />
						<sf:input type="hidden" path="supplierId" />
						<sf:input type="hidden" path="purchases" />
						<sf:input type="hidden" path="views" />
												
						<div class="form-group">

							<div class="col-md-12">

								<input type="submit" name="submit" id="submit" value="Submit" class="btn btn-primary" />

							</div>

						</div>


					</sf:form>

				</div>

			</div>

		</div>

	</div>
	
	<div class="row">
	
		<div class="col-xs-12">
			<h3>Available Products</h3>
			<hr/>
		</div>
		
		<div class="col-xs-12">
			<div style="overflow:auto">
			
				<!-- Product Table for Admin -->
				<table id="adminProductsTable" class="table table-striped table-bordered">
				
					<thead>
						<tr>
							<td>Id</td>
							<td>&#160;</td>
							<td>Brand</td>
							<td>Name</td>
							<td>Quantity</td>
							<td>Unit Price</td>
							<td>Active</td>
							<td>Edit</td>
						</tr>
					</thead>

					<tfoot>
						<tr>
							<td>Id</td>
							<td>&#160;</td>
							<td>Name</td>
							<td>Brand</td>
							<td>Quantity</td>
							<td>Unit Price</td>
							<td>Active</td>
							<td>Edit</td>
						</tr>
					</tfoot>
				</table>
			
			</div>
		</div>
	
	</div>
	
	<div class="modal fade" id="myCategoryModal" role="dialog" tab-index="-1" >
		<div class="modal-dialog" role="document" >
			<div class="modal-content" >
				<!-- Modal Header -->
				<div class="modal-header" >
					<button type="button" class="close" data-dismiss="modal">
						<span>&times;</span>
					</button>
					<h4>Add new Category</h4>
				</div>
				<div class="modal-body">
					<!-- Category Form -->
					<sf:form id="categoryForm" modelAttribute="category" action="${contextRoot }/manage/category" 
					method="POST" class="form-horizontal">
					
					<div class="form-group" >
						<label for="category_name" class="control-label col-md-4" >Category Name</label>
						<div class="col-md-8" >
							<sf:input type="text" path="name" id="category_name" class="form-control" />
						</div>
					</div>
					
					<div class="form-group" >
						<label for="category_description" class="control-label col-md-4" >Category Description</label>
						<div class="col-md-8" >
							<sf:textarea cols="" rows="5" path="description" id="category_description" class="form-control" />
						</div>
					</div>
					
					<div class="form-group" >
						<div class="col-md-offset-4 col-md-8" >
							<input type="submit" value ="Add Category" class="btn btn-warning" />
						</div>
					</div>

					</sf:form>
				</div>
			</div>
		</div>
	
	</div> 
	
	
</div>