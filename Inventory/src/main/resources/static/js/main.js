function GET(url, callback){
	return ajax("GET", url, "", callback)
}
function GETJSON(url, callback){
	return ajax("GET", url, "", function(data){
		callback(JSON.parse(getDecode(data)));
	});
}
function DELETE(url, data, callback){
	return ajax("DELETE", url, data, callback)
}
function POST(url, data, callback){
	return ajax("POST", url, data, callback)
}
function PUT(url, data, callback){
	return ajax("PUT", url, data, callback)
}
function ajax(type, url, data, callback){
	var strReturn;
	if(typeof callback == 'function'){
		$.ajax({
			type: type,
		    url: url,
		    contentType : "application/json",
			data: JSON.stringify(data),
			success: function(data, status, xhr) {
				if(typeof callback == 'function'){
					callback(data)
				}
		    },
		    error: errorHandler // see function errorHandler
		});
	}else if(callback == true){
		$.ajax({
			type: type,
			url: url,
		    contentType : "application/json",
			data: JSON.stringify(data),
			async:false,
			success : function(data) {
				strReturn = data;
			},
			error: errorHandler // see function errorHandler
		});
	}

	return strReturn;
}

function errorHandler(data){
	if(data.status==500){
		return {
			status : "error",
			message:"Internal server error has occured, all transactions has been rollback. If problem still persist pleae contact your IT admin."
		}
	}else if(data.status==401){
		location.href = JSON.parse(data.responseText).url;
	}else if(data.status==403){
		closeAllConfirmModal();
		toastMessage({
			status : "error",
			message : "Access Denied. You don't access this action!"
		})
	}
}

function getDecode(str){
	return decodeURIComponent(str).replace(/%20/g," ");
}