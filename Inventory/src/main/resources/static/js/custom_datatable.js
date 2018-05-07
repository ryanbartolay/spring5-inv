/* custom datatable */
function customDatatable(table, url, columns, columnDefs) {
	
	return table.DataTable({
		responsive : true,
		ajax: {
			url: url
		},
		processing: true,
        serverSide: true,
		length: 5,
		start: 0,
        columns: columns,
        columnDefs: columnDefs
	});
	
}

/* custom datatable */
function customFixedDatatable(table, url, columns, columnDefs) {
	
	return table.DataTable({
		responsive : true,
		searching: false, 
		paging: false,
		bInfo: false,
		ajax: {
			url: url
		},
		processing: true,
        serverSide: true,
        columns: columns,
        columnDefs: columnDefs
	});
	
}