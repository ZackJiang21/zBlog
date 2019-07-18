define(function(require, exports, module) {
	var $ = require("../../3rd/jquery-3.2.1");

	$(function() {
		$("#submit").click(function() {
			$.ajax({
				type:"post",
				url:"/auth",
				contentType: "application/json;charset=UTF-8",
				async:true,
				data: JSON.stringify({
					name: $("#usr").val(),
					value: $("#pwd").val()
				}),
				success: success,
				error: showError
			});
		});
	});
	
	function showError() {
		$("#error").show().delay(3000).hide(300);
	}

	function success(data) {
		window.location.href = "/view/admin/console.html";
	}

});