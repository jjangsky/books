window.onload = function() {
		$(function() {
		$.ajax({
			url: "/empPay/dept",
			success: function(data) {
				/*console.log(data[0].name);
				console.log(data);
				console.table(data);*/

				const $deptCode = $("#deptCode");

				for (let index in data) {
					$deptCode.append($("<option>").val(data[index].deptCode).text(data[index].deptName));
				}
			},
			error: function(error) {
				console.log(error);
			}
		});
	});
	
	$(function(){
		//let empNo = $("#empNo1").val();
		$.ajax({
			url:"/empPay/empInfo/",
			success: function(data){
				console.log(data.empName);
				console.log(data.deptName);
				console.log(data.docNo);
				console.log(data);
				
				$("#writer2").text(data.empName);
				$("#dept2").text(data.deptName);
				$("#docnumber").text(data.docNo);
			},
			error: function(error){
				console.log(error);
			}
		});
	});
	
	$("#clear").click(function(){
		$("#account1").val("");
		$("#acco1").val("");
		$("#deptName1").val("");
		$("#account2").val("");
		$("#acco2").val("");
		$("#deptName2").val("");
		$("#account3").val("");
		$("#acco3").val("");
		$("#deptName3").val("");
		$("#stepNo").val("");
		$("#step").text("");
	})

	$("#empList").change(function(){
		let appro = $(this).val();
		let account = $("#empList option:selected").text();
		let deptName = $("#deptCode option:selected").text();
		console.log(appro);
		console.log(account);
		console.log(deptName);
		
		let approver1 = deptName + "<br>" + account
		$("#account1").val(appro);
		$("#acco1").val(account);
		$("#deptName1").val(deptName);
		$("#selacc1").html(approver1);
	});
	
	







