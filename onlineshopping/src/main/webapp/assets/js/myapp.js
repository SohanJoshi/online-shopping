$(function(){
	//solving the active menu problem
	switch(menu){
	
	case 'About Us' :
		$('#about').addClass('active');
		break;
	case 'Contact Us' :
		$('#contact').addClass('active');
		break;
	case 'All Products':
		$('#listProducts').addClass('active');
		break;
	case 'Home' :
		$('#home').addClass('active');
		break;
	default : 
		$('#listProducts').addClass('active');
		$('#a_'+menu).addClass('active');
		break;
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
								if(row.quantity < 1)
									str += '<a href="javascript:void(0)" class="btn btn-warning disabled"><i class="fas fa-shopping-cart"></i></a>';
								else
									str += '<a href="' + window.contextRoot + '/cart/add/'+ data +'/product" class="btn btn-warning"><i class="fas fa-shopping-cart"></i></span></a>';
								
								return str;
							}
						}
					]
		});
	}
	
});