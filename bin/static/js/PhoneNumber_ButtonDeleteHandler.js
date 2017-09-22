$(document).ready(function() {
	$(".buttonDelete").click(function(e) {
		e.preventDefault();
		var id = $(this).attr('data-id');
		$.ajax({
			type : "POST",
			url : "/GetOnePhoneNumber",
			data : 'id=' + id,
			datatype : "text",
			success : function(result) {
				$('#delete-modal-phone-number-id').val(
						result.id);
				$('#delete-modal-phone-number-number').val(
						result.number);
				$('#delete-modal-phone-number-number').prop(
						'readonly', 'readonly');
			},
			error : function() {
				alert("ERROR");
			}
		});
	});
});