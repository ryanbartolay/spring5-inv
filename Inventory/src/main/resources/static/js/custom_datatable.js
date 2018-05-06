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