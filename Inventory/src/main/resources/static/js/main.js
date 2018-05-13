$(document).on("submit", "form", function(e){
	e.preventDefault();
});

function GET(url, callback){
	return ajax("GET", url, "", function(data) {
		callback(decodeAPIResponse(data));
	});
}
function DELETE(url, callback){
	return ajax("DELETE", url, "", function(data) {
		callback(decodeAPIResponse(data));
	})
}
function POST(url, data, callback){
	return ajax("POST", url, data, callback)
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

/**
 * Form inside Modal
 * @param modal
 * @param successCallback
 * @returns
 */
function formModalAsync(modal, successCallback = null) {
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
                $target.html(data);
                modal.modal("toggle");
                var obj = decodeAPIResponse(data);
                
                console.log(obj.status);
                console.log(obj.status == 'ACCEPTED');
                
                showToast(obj);
            	
                // calling success callback
                if(successCallback != null) {
                	successCallback();
                }
                
                $divError.html("");
                $form[0].reset();
                $btnSubmit.removeAttr("disabled");
            	$btnSubmit.html($btnSubmitText);
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
		toast("success", data.localizedMessage);
	} else if(data.status == 'ACCEPTED') { 
		toast("info", data.localizedMessage);
	} else {
		toast("error", data.localizedMessage);
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
