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

/* custom datatable */
function customFixedDatatableWithGroup(table, url, columns, columnDefs) {
	var table = table.DataTable({
	      'ajax': url,
	      'columnDefs': columnDefs,
	      "columns": columns,
	      //responsive : true,
		  searching: false, 
		  paging: true,
		  bInfo: false,
	      processing: true,
          serverSide: true,
	      //rowGroup: true,
	      //rowsGroup: [0],
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
	                    .append( '<td colspan="4">'+group+'</td>' )
	                    //.append( '<td>'+ageAvg.toFixed(0)+'</td>' )
//	                    .append( '<td/>' )
	                    //.append( '<td>'+salaryAvg+'</td>' );
	            }, 
	    	  dataSrc: 0
	       },
	       'rowsGroup': [0],
	   }); 
}