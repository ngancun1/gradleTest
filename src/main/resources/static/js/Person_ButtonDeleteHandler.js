$(document).ready(function() {
	$(".buttonDelete").click(function(e) {
		e.preventDefault();
		var id = $(this).data('id');
		$.ajax({
			type : "POST",
			url : "/GetOnePerson",
			data : 'id=' + id,
			datatype : "text",
			success : function(result) {
				$('#delete-modal-person-id').val(result.id);
				$('#delete-modal-person-name').val(result.name);
				$('#delete-modal-person-name').prop('readonly', 'readonly')
			},
			error : function() {
				alert("ERROR");
			}
		});
	});
});