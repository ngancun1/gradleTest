$(document).ready(function() {
	$("#SubmitInsert").click(function(e) {
		e.preventDefault();
		$.ajax({
			type : "POST",
			url : "/ValidateEditPerson",
			data : JSON.stringify({
				"id" : 0,
				"name" : $("#insert-modal-person-name").val()
			}),
			contentType : "application/json",
			dataType : "text",
			success : function(result) {
				if ("SUCCESS" == $.trim(result)) {
					$("#InsertForm").submit();
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