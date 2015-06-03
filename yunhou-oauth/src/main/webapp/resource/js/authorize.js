$(document).ready(function(){
    $("#login_submit").click(function(){
    	$.ajax({
			url: window.basepath + 'authorize/login',
			type : 'post',
			dataType : 'json',
			data :{
				app_key : $("#app_key").val(),
				response_type : $("#response_type").val(),
				redirect_uri : $("#redirect_uri").val(),
				state : $("#state").val(),
				view : $("#view").val(),
				username : $("#username").val(),
				password : $("#password").val()
			}
		}).done(function(data, status, xhr) {
				if (data.error == '' || data.error == null) {//成功
					$("#sessionId").val(data.code);
					$("#login_span").hide();
					$("#authorize_span").show();
				} else {
					$("#error").html(data.error_description);
				}
			}).fail(function(xhr, status, error) {
				$("#error").html(error);
			});
    });
	$("#login_authorize").click(function(){
		var url = window.basepath+"authorize/oauth?"
		url+="appkey="+$("#app_key").val();
		url+="&sessionId="+$("#sessionId").val();
		url+="&authorize="+true;
		url+="&redirect_uri="+$("#redirect_uri").val();
		window.location.href= url;
	});
	$("#login_cancel").click(function(){
		var url = window.basepath+"authorize/oauth?"
		url+="appkey="+$("#app_key").val();
		url+="&sessionId="+$("#sessionId").val();
		url+="&authorize="+false;
		url+="&redirect_uri="+$("#redirect_uri").val();
		window.location.href= url;
	});
    
});