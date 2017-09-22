$(document).ready(function() {
	$("#SubmitEdit").click(function(e) {
		e.preventDefault();
		var inputID = $("#edit-modal-phone-number-id").val();
		var inputNumber = $("#edit-modal-phone-number-number").val();
		var inputPersonID = $("#edit-modal-person-id").val();
		$.ajax({
			type : "POST",
			url : "/ValidateEditPhoneNumber",
			dataType : "text",
			data : JSON.stringify({
				"id" : inputID,
				"number" : inputNumber,
				"personID" : inputPersonID
			}),
			contentType : "application/json",
			success : function(result) {
				if ("SUCCESS" == $.trim(result)) {
					$("#PhoneEditForm").submit();
					alert("SUBBMITED");
				} else {
					$("#errorMessage").text(result);
				}
			},
			error : function(result) {
				alert(result);
			}
		});
	});
});