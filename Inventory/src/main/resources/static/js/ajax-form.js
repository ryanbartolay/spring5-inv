function formModal(modal, successCallback = null) {
	$('form[data-async]').on('submit', function(event) {
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
                
                toast("success", data.localizedMessage);
            	
                // calling success callback
                if(successCallback != null) {
                	successCallback();
                }
                
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

        event.preventDefault();
    });
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