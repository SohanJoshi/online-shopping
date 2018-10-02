<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ include file="../shared/flows-header.jsp"%>

<div class="container">

	<div class="row">

		<div class="col-md-9 col-md-offset-3">

			<div class="panel panel-primary">

				<div class="panel-heading">
					<h4>Sign Up - Address</h4>
				</div>

				<div class="panel-body">

					<sf:form 
						method="POST" class="form-horizontal" id="billingForm"
						modelAttribute="billing">

						<div class="form-group">

							<label class="control-label col-md-4" for="addressLineOne">Enter
								Address Line 1 : </label>

							<div class="col-md-8">

								<sf:input type="text" path="addressLineOne" id="addressLineOne"
									placeholder="Address Line 1" class="form-control" />
								<sf:errors path="addressLineOne" cssClass="help-block" element="em" />
							</div>

						</div>
						
						<div class="form-group">

							<label class="control-label col-md-4" for="addressLineTwo">Enter
								Address Line 2 : </label>

							<div class="col-md-8">

								<sf:input type="text" path="addressLineTwo" id="addressLineTwo"
									placeholder="Address Line 2" class="form-control" />
								<sf:errors path="addressLineTwo" cssClass="help-block" element="em" />
							</div>

						</div>
						
						<div class="form-group">

							<label class="control-label col-md-4" for="city">Enter
								City : </label>

							<div class="col-md-8">

								<sf:input type="text" path="city" id="city"
									placeholder="City" class="form-control" />
								<sf:errors path="city" cssClass="help-block" element="em" />
							</div>

						</div>						

						<div class="form-group">

							<label class="control-label col-md-4" for="state">Enter
								State : </label>

							<div class="col-md-8">

								<sf:input type="text" path="state" id="state"
									placeholder="State" class="form-control" />
								<sf:errors path="state" cssClass="help-block" element="em" />
							</div>

						</div>						
						<div class="form-group">

							<label class="control-label col-md-4" for="country">Enter
								Country : </label>

							<div class="col-md-8">

								<sf:input type="text" path="country" id="country"
									placeholder="Country" class="form-control" />
								<sf:errors path="country" cssClass="help-block" element="em" />
							</div>

						</div>						
						<div class="form-group">

							<label class="control-label col-md-4" for="postalCode">Enter
								Postal Code : </label>

							<div class="col-md-8">

								<sf:input type="text" path="postalCode" id="postalCode"
									placeholder="XXXXXX" class="form-control" />
								<sf:errors path="postalCode" cssClass="help-block" element="em" />
							</div>

						</div>						

					<div class="form-group">

						<div class="col-md-12">
						
							<!-- Submit button for Sign-up Personal  -->
							<button type="submit" class="btn btn-secondary" 
								name="_eventId_personal">
								
								<i class="fas fa-arrow-left"></i> Previous - Personal 
								
							</button>
							
							<!-- Submit button for Sign-up Confirm -->
							<button type="submit" class="btn btn-primary" 
								name="_eventId_confirm">
								
								Next - 	Confirm <i class="fas fa-arrow-right"></i>
								
							</button>		

						</div>

					</div>
					</sf:form>

				</div>

			</div>

		</div>

	</div>

</div>

<%@ include file="../shared/flows-footer.jsp"%>