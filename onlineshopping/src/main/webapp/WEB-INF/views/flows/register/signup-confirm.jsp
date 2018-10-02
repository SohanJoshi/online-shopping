<%@ include file="../shared/flows-header.jsp" %>
			
		<div class="row" style="padding-left:100px">
			
			<!-- Column to display personal details -->	
			<div class="card border-primary panel panel-primary">
				<div class="panel panel-primary" >
					<div class="card-header panel-heading">
						<h4>Personal Details</h4>
					</div>
					<div class="panel-body">
						<!-- Code to display the personal details -->
						<div class="text-center">
							<h4 class="card-title">${registerModel.user.firstName } ${registerModel.user.lastName}</h4>
							<h5 class="card-text">Email : ${registerModel.user.email}</h5>
							<h5 class="card-text">Contact No. : ${registerModel.user.contactNumber}</h5>
							<h5 class="card-text">Role : ${registerModel.user.role}</h5>
						</div>
					</div>
					<div class="panel-footer">
						<!-- Anchor to move to the edit of personal details -->
							<a href="${flowExecutionUrl}&_eventId_personal" class="btn btn-primary">
							<i class="fas fa-arrow-left"></i> Edit Personal Details</a>
					</div>
				</div>
			</div>
			
			<!-- Column to display billing details -->	
			<div class="col-sm-6">
				<div class="card border-primary panel panel-primary" >
					<div class="card-header panel-heading">
						<h4>Billing Details</h4>
					</div>
					<div class="panel-body">
						<!-- Code to display the Billing details -->
						<div class="text-center">
							<h5 class="card-text">${registerModel.billing.addressLineOne}</h5>
							<h5 class="card-text">${registerModel.billing.addressLineTwo}</h5>
							<h5 class="card-text">${registerModel.billing.city} - ${registerModel.billing.postalCode}</h5>
							<h5 class="card-text">${registerModel.billing.state} - ${registerModel.billing.country}</h5>
						</div>
						
					</div>
					<div class="panel-footer">
						<!-- Anchor to move to the edit of billing details -->
							<a href="${flowExecutionUrl}&_eventId_billing" class="btn btn-primary">
							<i class="fas fa-arrow-left"></i> Edit Billing Details</a>
					</div>
				</div>
			</div>
		
		</div>
		
		<!-- To provide the confirm button after displaying the details -->
		<div class="row" style="allign:center">
			<div class="col-sm-4 col-sm-offset-4">
				<div class="text-certer">
					<a href="${flowExecutionUrl}&_eventId_submit" class="btn btn-primary">Confirm</a>
				</div>
			</div>		
		</div>
			
<%@ include file="../shared/flows-footer.jsp" %>