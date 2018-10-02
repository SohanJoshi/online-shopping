<%@ include file="../shared/flows-header.jsp"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>

<div class="container">

	<div class="col-md-12">

		<div class="panel panel-primary">

			<div class="my-4 panel-heading">

				<h4>User Registration - Personal Information</h4>

			</div>

			<div class="panel-division">

				<!-- Form Elements -->

				<sf:form 
					id="registerForm"
					class="form-horizontal" 
					modelAttribute="user" 
					method="POST">

					<div class="form-group">

						<label class="control-label col-md-4" for="firstName">Enter
							First Name : </label>

						<div class="col-md-8">

							<sf:input type="text" path="firstName" id="firstName"
								placeholder="First Name" class="form-control" />
							<sf:errors path="firstName" cssClass="help-block" element="em" />
						</div>

					</div>
					
					<div class="form-group">

						<label class="control-label col-md-4" for="lastName">Enter
							Last Name : </label>

						<div class="col-md-8">

							<sf:input type="text" path="lastName" id="lastName"
								placeholder="Last Name" class="form-control" />
							<sf:errors path="lastName" cssClass="help-block" element="em" />
						</div>

	
					
					</div>
					
					<div class="form-group">

						<label class="control-label col-md-4" for="email">Enter
							Email : </label>

						<div class="col-md-8">

							<sf:input type="email" path="email" id="email"
								placeholder="abc@xyz.com" class="form-control" />
							<sf:errors path="email" cssClass="help-block" element="em" />
						</div>

					</div>
					
					<div class="form-group">

						<label class="control-label col-md-4" for="contactNumber">Enter
							Contact Number : </label>

						<div class="col-md-8">

							<sf:input type="text" path="contactNumber" id="contactNumber"
								placeholder="+XX-XXX-XXX-XXXX" class="form-control" />
							<sf:errors path="contactNumber" cssClass="help-block" element="em" />
						</div>

					</div>

					<div class="form-group">

						<label class="control-label col-md-4" for="password">Enter
							Password : </label>

						<div class="col-md-8">

							<sf:input type="password" path="password" id="password"
								placeholder="********" class="form-control" />
							<sf:errors path="password" cssClass="help-block" element="em" />
						</div>

					</div>
					
					<div class="form-group">

						<label class="control-label col-md-4" for="confirmPassword">Re-Enter
							Password : </label>

						<div class="col-md-8">

							<sf:input type="password" path="confirmPassword" id="confirmPassword"
								placeholder="********" class="form-control" />
							<sf:errors path="confirmPassword" cssClass="help-block" element="em" />
						</div>

					</div>

					<div class="form-group">

					<!-- Radio button for Role  -->
					<label class="control-label col-md-4" for="role">Select
							Role : </label>

						<div class="col-md-8">
							<label class="radio-inline">
								<sf:radiobutton path="role" value="USER" checked="checked"/> User
							</label>
							<label class="radio-inline">
								<sf:radiobutton path="role" value="SUPPLIER" /> Supplier
							</label>
						</div>

					</div>
					
					
					<div class="form-group">

						<div class="col-md-12">

							<button type="submit" class="btn btn-primary" 
								name="_eventId_billing">
								
								Next - 	Billing <i class="fas fa-arrow-right"></i>
								
							</button>

						</div>

					</div>


				</sf:form>

			</div>


		</div>

	</div>

</div>

<%@ include file="../shared/flows-footer.jsp"%>