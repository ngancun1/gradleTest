$(document).ready(function(){
	$(".buttonEdit").click(function(e){
		e.preventDefault();
		var id = $(this).attr('data-id');
		$.ajax({
			type: "POST",
			url : "/GetOnePerson",
			data : 'id=' + id,
			datatype : "text",
			success : function(result){
				$('#edit-modal-person-id').val(result.id);
				$('#edit-modal-person-name').val(result.name);
				$('#PhoneNumberEdit').prop('th:attr','data-id='+id);
			},
			error : function(){
				alert("ERROR");
			}
		});
	});
});
