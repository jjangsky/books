$(document).ready(function() {

	$(function() {
		$.ajax({
			url: "/document/dept",
			success: function(data) {
				/*console.log(data[0].name);
				console.log(data);
				console.table(data);*/

				const $deptCode = $("#deptCode");
				const $deptCode2 = $("#deptCode2");
				const $deptCode3 = $("#deptCode3");
				const $deptCode4 = $("#deptCode4");
				const $deptCode5 = $("#deptCode5");
				const $deptCode6 = $("#deptCode6");

				for (let index in data) {
					$deptCode.append($("<option>").val(data[index].deptCode).text(data[index].deptName));
					$deptCode2.append($("<option>").val(data[index].deptCode).text(data[index].deptName));
					$deptCode3.append($("<option>").val(data[index].deptCode).text(data[index].deptName));
					$deptCode4.append($("<option>").val(data[index].deptCode).text(data[index].deptName));
					$deptCode5.append($("<option>").val(data[index].deptCode).text(data[index].deptName));
					$deptCode6.append($("<option>").val(data[index].deptCode).text(data[index].deptName));
				}
			},
			error: function(error) {
				console.log(error);
			}
		});
	});

	$("#deptCode").change(function() {
		let deptValue = $(this).val();
		console.log($(this).val());

		$.ajax({
			url: "/document/emp/"+deptValue,
			success: function(data) {
				console.log(data[0].empName);
				console.log(data);
				console.table(data);
	
				const $empList = $("#empList");
				
				$empList.text("");
	
				for (let index in data) {
					$empList.append($("<option>").val(data[index].empNo).text(data[index].empName));
				}
			},
			error: function(error) {
				console.log(error);
			}
		});
	});
	$("#deptCode2").change(function() {
		let deptValue = $(this).val();
		console.log($(this).val());

		$.ajax({
			url: "/document/emp/"+deptValue,
			success: function(data) {
				console.log(data[0].empName);
				console.log(data);
				console.table(data);
	
				const $empList = $("#empList2");
				
				$empList.text("");
	
				for (let index in data) {
					$empList.append($("<option>").val(data[index].empNo).text(data[index].empName));
				}
			},
			error: function(error) {
				console.log(error);
			}
		});
	});
	$("#deptCode3").change(function() {
		let deptValue = $(this).val();
		console.log($(this).val());

		$.ajax({
			url: "/document/emp/"+deptValue,
			success: function(data) {
				console.log(data[0].empName);
				console.log(data);
				console.table(data);
	
				const $empList = $("#empList3");
				
				$empList.text("");
	
				for (let index in data) {
					$empList.append($("<option>").val(data[index].empNo).text(data[index].empName));
				}
			},
			error: function(error) {
				console.log(error);
			}
		});
	});
	$("#deptCode4").change(function() {
		let deptValue = $(this).val();
		console.log($(this).val());

		$.ajax({
			url: "/document/emp/"+deptValue,
			success: function(data) {
				console.log(data[0].empName);
				console.log(data);
				console.table(data);
	
				const $empList = $("#empList4");
				
				$empList.text("");
	
				for (let index in data) {
					$empList.append($("<option>").val(data[index].empNo).text(data[index].empName));
				}
			},
			error: function(error) {
				console.log(error);
			}
		});
	});
	$("#deptCode5").change(function() {
		let deptValue = $(this).val();
		console.log($(this).val());

		$.ajax({
			url: "/document/emp/"+deptValue,
			success: function(data) {
				console.log(data[0].empName);
				console.log(data);
				console.table(data);
	
				const $empList = $("#empList5");
				
				$empList.text("");
	
				for (let index in data) {
					$empList.append($("<option>").val(data[index].empNo).text(data[index].empName));
				}
			},
			error: function(error) {
				console.log(error);
			}
		});
	});
	$("#deptCode6").change(function() {
		let deptValue = $(this).val();
		console.log($(this).val());

		$.ajax({
			url: "/document/emp/"+deptValue,
			success: function(data) {
				console.log(data[0].empName);
				console.log(data);
				console.table(data);
	
				const $empList = $("#empList6");
				
				$empList.text("");
	
				for (let index in data) {
					$empList.append($("<option>").val(data[index].empNo).text(data[index].empName));
				}
			},
			error: function(error) {
				console.log(error);
			}
		});
	});

	function sendData() {
		if ($("#title").val().length < 1 || $("#title").val() == "" || $("#title").val() == "  ") {
			alert("문서의 제목을 입력해주세요.");
		} else if ($("#title").val().length < 5 || $("#title").val().length < 5) {
			alert("문서의 제목을 5글자 이상 입력해주세요.");
		}

		console.log($(".note-editable").text());
		if ($(".note-editable").text().length < 1 || $(".note-editable").text() == "" || $(".note-editable").text() == "  ") {
			alert("문서의 내용을 입력해주세요.");
		} else if ($(".note-editable").text().length < 10) {
			alert("문서의 내용을 10글자 이상 입력해주세요.");
		}

		else {
			let sendDraft = $(".draft").html();
			console.log(sendDraft);
			$("#draftcnt").val(sendDraft);
		}

	}

	function selectacc() {
		let account1 = $("#empList").val();
		let account2 = $("#empList2").val();
		let account3 = $("#empList3").val();

		if (empList == "") {
			alert("최소 결재라인을 지정해주세요!")
		} else if (empList2 == "" && empList3 == "") {
			let con = confirm("결재라인이 1단계로 종료됩니다. 맞으시면 확인을 눌러주세요.");
			if (con) {
				$("#selacc1").text(empList);
				$("#selacc2").text(empList2);
				$("#selacc3").text(empList3);
			}
		} else if (empList3 == "") {
			let con = confirm("결재라인이 2단계로 종료됩니다. 맞으시면 확인을 눌러주세요.");
			if (con) {
				$("#selacc1").text(empList);
				$("#selacc2").text(empList2);
				$("#selacc3").text(empList3);
			}
		} else {
			let con = confirm("결재라인이 3단계로 종료됩니다. 맞으시면 확인을 눌러주세요.");
			if (con) {
				$("#selacc1").text(empList);
				$("#selacc2").text(empList2);
				$("#selacc3").text(empList3);
			}
		}
	}
})

