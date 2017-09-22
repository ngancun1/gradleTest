$(document).ready(function() {
	$("#SubmitInsert").click(function(e) {
		e.preventDefault();
		console.log("SAFSAFSAF");
		$.ajax({
			type : "POST",
			url : "/ValidateEditPhoneNumber",
			dataType : "text",
			data : JSON.stringify({
				"id" : $("#insert-modal-phone-number-id").val(),
				"number" : $("#insert-modal-phone-number-number").val(),
				"personID" : $("#insert-modal-person-id").val()
			}),
			contentType : "application/json",
			success : function(result) {
				console.log(result);
				if ("SUCCESS" == $.trim(result)) {
					$("#InsertPhoneNumberForm").submit();
					alert("SUBBMITED");
				} else {
					$("#insertErrorMessage").text(result);
				}
			},
			error : function(result) {
				alert(result);
			}
		});
	});
});