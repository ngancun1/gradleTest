<script>
$(".PhoneNumberEdit").click(function(e1){
  			  				e1.preventDefault();
  			  				alert("hmm");
  			  				$.ajax({
  			  					type: "GET",
  			  					url : "/toEditPhoneNumber",
  			  					data : 'id='+id,
  			  					datatype : "text",
  			  					error : function(){
  			  						alert("ERROR");
  			  					}
  			  				});
  			  			});
</script>