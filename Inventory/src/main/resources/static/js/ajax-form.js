function formModal(modal) {
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
                $.toaster({ priority : 'success', title : 'Notice', message : data.localizedMessage});
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