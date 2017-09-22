$(document).ready(function() {
	$("#PhoneNumberEdit").click(function(e) {
		e.preventDefault();
		var id = $('#edit-modal-person-id').val();
		$('#RedirectPersonID').val(id);
		$('#RedirectForm').submit();
	});
});