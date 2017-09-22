$(document).ready(function() {
	$(".buttonEdit").click(function(e) {
		e.preventDefault();
		var id = $(this).attr('data-id');
		$.ajax({
			type : "POST",
			url : "/GetOnePhoneNumber",
			data : 'id=' + id,
			datatype : "text",
			success : function(result) {
				$('#edit-modal-phone-number-id').val(result.id);
				$('#edit-modal-phone-number-number').val(result.number);
			},
			error : function() {
				alert("ERROR");
			}
		});
	});
});