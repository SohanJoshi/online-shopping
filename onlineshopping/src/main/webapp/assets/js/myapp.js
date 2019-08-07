$(function(){
	//solving the active menu problem
	switch(menu){
	case 'Home' :
		$('#home').addClass('active');
		break;
	case 'About Us' :
		$('#about').addClass('active');
		break;
	case 'Contact Us' :
		$('#contact').addClass('active');
		break;
	case 'All Products':
		$('#listProducts').addClass('active');
		break;
	case 'Manage Products' :
		$('#manageProducts').addClass('active');
		break;
	default : 
		$('#listProducts').addClass('active');
		$('#a_'+menu).addClass('active');
		break;
	}
	
	// to tackle the csrf token
	
	var token = $('meta[name="_csrf"]').attr('content');
	var header = $('meta[name="_csrf_header"]').attr('content');
	
	if(token.length > 0 && header.length > 0) {
		$(document).ajaxSend(function(e, xhr, options) {
			xhr.setRequestHeader(header, token);
		});
	}
	
	// code for jQuery dataTable

	// create a dataset
	
	var $table = $('#productListTable');
	
	// execute the code below where we have the table 
	
	if($table.length){
		// console.log("Inside the table");
		
		var jsonUrl = '';
		
		if(window.categoryId == '')
			jsonUrl = window.contextRoot + '/json/data/all/products';
		else
			jsonUrl = window.contextRoot + '/json/data/category/' + window.categoryId + '/products'; 
		
		$table.DataTable({
			lengthMenu: [[3,5,10,-1], ['3 records','5 records','10 records', 'All']],
			pageLength: 5,
			ajax: {
				url: jsonUrl,
				dataSrc: ''
			},
			columns: [
						{
							data: 'code',
							mRender: function(data, type, row) {
								return '<img src="'+window.imagesPath+'/' + data + '.jpg" class="dataTableImage"/>';
							}
						},
						{
							data: 'name'
						},
						{
							data: 'brand'
						},
						{
							data: 'unitPrice',
							mRender: function(data, type, row) {
								return '&#8377; ' + data;
							}
							
						},
						{
							data: 'quantity',
							mRender: function (data, type, row) {
								if(data < 1)
									return "<span style='color:red'>Out of Stock</span>";
								else
									return data;
							}
						},
						{
							data: 'id',
							mRender: function(data, type, row) {
								
								var str = '';
								str += '<a href="' + window.contextRoot + '/show/'+ data +'/product" class="btn btn-primary"><i class="fas fa-eye"></i></a>';
								if(userRole == 'USER'){
									if(row.quantity < 1)
										str += '<a href="javascript:void(0)" class="btn btn-warning disabled"><i class="fas fa-shopping-cart"></i></a>';
									else
										str += '<a href="' + window.contextRoot + '/cart/add/'+ data +'/product" class="btn btn-success"><i class="fas fa-shopping-cart"></i></span></a>';
									
								} 
								else 
									str += '<a href="' + window.contextRoot + '/manage/'+ data +'/product" class="btn btn-warning"><i class="fas fa-pencil-alt"></i></span></a>';
								
								return str;
							}
						}
					]
		});
	}
	
	// Dismissing alert after 3 secs 
	var $alert = $('.alert');
	
	if($alert.length){
		setTimeout(function(){
			$alert.fadeOut('slow');
		},3000);
	}
	

	// ----------------------- Toggle Switch handler
	
	$('.switch input[type="checkbox"').on('change',function() {
		
		var checkbox = $(this);
		var checked = checkbox.prop('checked');
		var dMsg = (checked) ? "Do you want to activate the product ?":
			"Do you want to deactivate the product ?";
		var value = checkbox.prop('value');
		bootbox.confirm({
			size: 'medium',
			title: 'Product activation and deactivation',
			message: dMsg,
			callback: function(confirmed) {
				if(confirmed){
					console.log(value);
					bootbox.alert({
						size: 'medium',
						title: 'Information',
						message: 'You are going to perform action of product ' + value
					});
				}else {
					checkbox.prop('checked',!checked );
				}
			}
		})
		
	});

	
	// ----------- Data Table for Admin ---------

	// create a dataset
	
	var $adminProductsTable = $('#adminProductsTable');
	
	// execute the code below where we have the table 
	
	if($adminProductsTable.length){
		// console.log("Inside the table");
		
		var jsonUrl = window.contextRoot + '/json/data' +  '/admin/all/products';
		
		$adminProductsTable.DataTable({
			lengthMenu: [[10, 30, 50, -1], ['10 records','30 records','50 records', 'All']],
			pageLength: 5,
			ajax: {
				url: jsonUrl,
				dataSrc: ''
			},
			columns: [
				
						{
							data: 'id'
						},
						{
							data: 'code',
							mRender: function(data, type, row) {
								return '<img src="'+window.imagesPath+'/' + data + '.jpg" class="adminDataTableImg"/>';
							}
						},
						{
							data: 'name'
						},
						{
							data: 'brand'
						},
						{
							data: 'quantity',
							mRender: function (data, type, row) {
								if(data < 1)
									return "<span style='color:red'>Out of Stock</span>";
								else
									return data;
							}
						},
						{
							data: 'unitPrice',
							mRender: function(data, type, row) {
								return '&#8377; ' + data;
							}
							
						},
						{
							data: 'active',
							bSortable: false,
							mRender: function(data, type, row) {
								var str = '';
								
								str += '<label class="switch">';
								if(data){
									str += '<input type="checkbox" checked="checked" value="' + row.id +'"/>';
								} else {
									str += '<input type="checkbox" value="' + row.id +'"/>';
								}
								str += '<div class="slider"></div>';
								str += '</label>';
								
								return str;
							}
						},
						{
							data: 'id',
							bSortable: false,
							mRender: function (data, type, row) {
							
								var str = '';
								
								str += '<a href="'+window.contextRoot+'/manage/'+row.id+'/product" class="btn btn-warning">';
								str += '<span class="fas fa-pencil-alt"></span>';
								str += '</a>';
								
								return str;
							}
						}
					],
			initComplete: function() {
						var api = this.api();
						api.$('.switch input[type="checkbox"]').on('change',function() {
							var checkbox = $(this);
							var checked = checkbox.prop('checked');
							var dMsg = (checked) ? "Do you want to activate the product ?":
								"Do you want to deactivate the product ?";
							var value = checkbox.prop('value');
							bootbox.confirm({
								size: 'medium',
								title: 'Product activation and deactivation',
								message: dMsg,
								callback: function(confirmed) {
									if(confirmed){
										// console.log(value);
										
										var activationUrl = window.contextRoot + '/manage/products/' + value + '/activation';
										
										$.get(activationUrl, function(data) {
											bootbox.alert({
												size: 'medium',
												title: 'Information',
												message: data
											});
										});
									}else {
										checkbox.prop('checked',!checked );
									}
								}
							})
							
					});
				}
		});
	}
	
	// ----------------------------
	// Validation code for Category
	
	var $categoryForm = $('#categoryForm');
	
	if($categoryForm.length) {
		
		$categoryForm.validate({
			
			rules : {
				
				name : {
					
					required : true,
					minlength : 2
				},
				
				description : {
					required : true
				}
			},
			
			messages : {
				
				name : {
					
					required : "Please add the category name!",
					minlength : "The minimum length of the name should be 2."
				},
				
				description : {
					required : "Please add a description for the category!"
				}
			},
			errorElement : 'em',
			errorPlacement : function (error, element) {
				// add the class of help-block
				error.addClass('help-block');
				// add the error element
				error.insertAfter(element);
			}
		})
	}

	// ----------------------------
	// Validation code for Login Page
	
	var loginForm = $('#loginForm');
	
	if($loginForm.length) {
		
		$loginForm.validate({
			
			rules : {
				
				username : {
					
					required : true,
					email : true
				},
				
				passowrd : {
					required : true
				}
			},
			
			messages : {
				
				username : {
					
					required : "Please provide username !",
					email : "Please enter valid email address!"
				},
				
				passoword : {
					required : "Please enter the password !"
				}
			},
			errorElement : 'em',
			errorPlacement : function (error, element) {
				// add the class of help-block
				error.addClass('help-block');
				// add the error element
				error.insertAfter(element);
			}
		})
	}

	
});

