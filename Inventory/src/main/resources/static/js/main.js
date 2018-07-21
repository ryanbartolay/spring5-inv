$(document).on("submit", "form", function(e){
	e.preventDefault();
});

function GET(url, callback, error_callback = null){
//	return ajax("GET", url, "", function(data) {
//	callback(decodeAPIResponse(data));
//	});

	$.get( {
		url: url,
		dataType: "text"
	}, function(data) {
		callback(decodeAPIResponse(data));
	})
	.fail(function(error) {
		if(typeof error_callback == 'function'){
			error_callback(error);
		}
	});
}
function DELETE(url, callback){
	return ajax("DELETE", url, "", function(data) {
		callback(decodeAPIResponse(data));
	})
}
function POST(url, data, callback){
//	return ajax("POST", url, data, callback)
	$.ajax({
		type: "POST",
		url: url,
		data: data,

		success: function(data, status) {
			console.log(data);
			callback(decodeAPIResponse(data));
		}
	});
}
function PUT(url, data, callback){
	return ajax("PUT", url, data, callback)
}
function decodeAPIResponse(data) {
	try {
		return JSON.parse(getDecode(data));
	} catch (e) {
		return data;
	}

}

function ajax(type, url, data, callback){

	var strReturn;
	if(typeof callback == 'function'){

		$.ajax({
			type: type,
			url: url,
			contentType : "text", // all requests are expected to be url encoded
			data: JSON.stringify(data),
			success: function(data, status, xhr) {
				if(typeof callback == 'function'){
					callback(decodeAPIResponse(data));
				}
			},
			error: errorHandler // see function errorHandler
		});
	}else if(callback == true){
		$.ajax({
			type: type,
			url: url,
			contentType : "text",
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

function displayErrors(data, div) {
	var index;
	var errors = data.errors;
	var localized_message = data.localizedMessage;
	
	var html = "<div class=\"alert alert-danger\">";
	if(errors != null) {
		html += 
		"<ul>";
		for (index = 0; index < errors.length; ++index) {
			html += "<li>" +errors[index]+ "</li>";
		}
		html += "</ul>";

		div.html(html);
	}
	
	if(localized_message != null) {
		html += localized_message;
	}
	
	html += "</div>";
	div.html(html);
}

/**
 * Form inside Modal
 * @param modal
 * @param successCallback
 * @returns
 */
function formModalAsync(modal, successCallback = null) {
	
	$('.modal').on('hidden.bs.modal', function () {
		var $target = $(this).find("#errors");
		$target.empty()
		$(this).find("#submit").removeAttr("disabled");
	})
	
	$('form[data-async]').on('submit', function(event) {
		event.preventDefault();
		var $form = $(this);
		var $target = $($form.attr('data-target'));
		var $divError = $($form.find("#errors"));
		var $btnSubmit = $($form.find("#submit"));
		var $btnSubmitText = $btnSubmit.text();

		$btnSubmit.attr("disabled", "disabled");
		$btnSubmit.html("Processing...");

		$.ajax({
			type: $form.attr('method'),
			url: $form.attr('action'),
			data: $form.serialize(),

			success: function(data, status) {
				data = JSON.parse(decodeURIComponent(data));
				if(data.status == "OK"){
					$target.html(data);
					modal.modal("toggle");
					var obj = decodeAPIResponse(data);

					showToast(obj);

					// calling success callback
					if(successCallback != null) {
						successCallback();
					}

					$divError.html("");
					$form[0].reset();
					$btnSubmit.removeAttr("disabled");
					$btnSubmit.html($btnSubmitText);
				}else{
					var index;
					var errors = data.errors;
	
					var html = "<div class=\"alert alert-danger\">" +
					"<ul>";
					for (index = 0; index < errors.length; ++index) {
						html += "<li>" +errors[index].split("%20").join(" ")+ "</li>";
					}
					html += "</ul></div>";
					$divError.html(html);
					$btnSubmit.removeAttr("disabled");
					$btnSubmit.html($btnSubmitText);
				}
			},
			error: function(xhr, ajaxOptions, thrownError) {
				var index;
				var errors = xhr.responseJSON.errors;

				var html = "<div class=\"alert alert-danger\">" +
				"<ul>";
				for (index = 0; index < errors.length; ++index) {
					html += "<li>" +errors[index]+ "</li>";
				}
				html += "</ul></div>";

				$divError.html(html);
				$btnSubmit.removeAttr("disabled");
				$btnSubmit.html($btnSubmitText);
			}
		});
	});
}

function showToast(data) {
	if(data.status == 'OK') {
		toast("success", data.localizedMessage.split("%20").join(" "));
	} else if(data.status == 'ACCEPTED') { 
		toast("info", data.localizedMessage.split("%20").join(" "));
	} else {
		toast("error", data.localizedMessage.split("%20").join(" "));
	}
}

function toast(icon, message) {

	var heading = { success: "Success", error: "Error", info: "Information", warn: "Warning"};

	$.toast({
		heading: heading[icon],
		text: message,
		icon: icon, // success, info, warn, error
		position: 'top-right',
		hideAfter: false, // auto hides after some seconds
	});

}

function errorHandler(data){
	console.log(data);
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

function singledatetimepicker(container){
	container.daterangepicker({
		singleDatePicker: true,
		locale: {
			format: 'YYYY-MM-DD HH:mm:ss'
		},
		timePicker: true,
		timePicker24Hour: true,
	});
}

function singledatepicker(container){
	container.daterangepicker({
		singleDatePicker: true,
		locale: {
			format: 'YYYY-MM-DD'
		}
	});
}

function getParameterByName(name, url) {
    if (!url) url = window.location.href;
    name = name.replace(/[\[\]]/g, "\\$&");
    var regex = new RegExp("[?&]" + name + "(=([^&#]*)|&|#|$)"),
        results = regex.exec(url);
    if (!results) return null;
    if (!results[2]) return '';
    return decodeURIComponent(results[2].replace(/\+/g, " "));
}

function round2DecimalPlaces(value) {
	return parseFloat(Math.round(value * 100) / 100).toFixed(2);
}
