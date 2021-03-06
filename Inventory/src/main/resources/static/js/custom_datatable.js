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
	return dTables(table, url, columns, columnDefs, true);
}

/* custom datatable */
function customFixedDatatableNotServerSide(table, url, columns, columnDefs){
	return dTables(table, url, columns, columnDefs, false);
}

function dTables(table, url, columns, columnDefs, serverSide){
	return table.DataTable({
		responsive : true,
		searching: false, 
		paging: false,
		bInfo: false,
		ajax: {
			url: url
		},
		processing: serverSide,
        serverSide: serverSide,
        columns: columns,
        columnDefs: columnDefs
	});
}

/* custom datatable */
function customFixedDatatableWithGroup(table, url, columns, columnDefs, params) {
	var table = table.DataTable({
			ajax: {
				url: url,
				data: params
			},
	      columnDefs: columnDefs,
	      columns: columns,
		  searching: false, 
		  paging: true,
		  bInfo: false,
	      processing: true,
          serverSide: true,
	      rowGroup: {
	            //startRender: null,
	    	  startRender: function ( rows, group ) {
	                var salaryAvg = rows
	                    .data()
	                    .pluck(1)
	                    .reduce( function (a, b) {
	                        return a + b.replace(/[^\d]/g, '')*1;
	                    }, 0) / rows.count();
	                salaryAvg = $.fn.dataTable.render.number(',', '.', 0, '$').display( salaryAvg );
	 
	                var ageAvg = rows
	                    .data()
	                    .pluck(2)
	                    .reduce( function (a, b) {
	                        return a + b*1;
	                    }, 0) / rows.count();
	 
	                return $('<tr style="background: #e4e4e4;"/>')
	                    .append( '<td colspan="'+columns.length+'">'+group+'</td>' )
	                    //.append( '<td>'+ageAvg.toFixed(0)+'</td>' )
//	                    .append( '<td/>' )
	                    //.append( '<td>'+salaryAvg+'</td>' );
	            }, 
	    	  dataSrc: 0
	       },
	       //'rowsGroup': [0],
	   }); 
	return table;
}