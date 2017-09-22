$(document).ready(function() {
	$("#SubmitEdit").click(function(e) {
		e.preventDefault();
		var inputID = $("#edit-modal-person-id").val();
		var inputName = $("#edit-modal-person-name").val();
		$.ajax({
			type : "POST",
			url : "/ValidateEditPerson",
			dataType : "text",
			data : JSON.stringify({
				"id" : inputID,
				"name" : inputName
			}),
			contentType : "application/json",
			success : function(result) {
				if ("SUCCESS" == $.trim(result)) {
					$("#EditForm").submit();
					alert("SUBBMITED");
				} else {
					alert(result);
				}
			},
			error : function(result) {
				alert(result);
			}
		});
	});
});