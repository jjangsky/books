window.onload = function() {

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

				for (let index in data) {
					$deptCode.append($("<option>").val(data[index].deptCode).text(data[index].deptName));
					$deptCode2.append($("<option>").val(data[index].deptCode).text(data[index].deptName));
					$deptCode3.append($("<option>").val(data[index].deptCode).text(data[index].deptName));
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
			url: "/document/emp/" + deptValue,
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
			url: "/document/emp/" + deptValue,
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
			url: "/document/emp/" + deptValue,
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

	$(document).ready(function() {
		let now = new Date();
		// $("h6").text(now); //전체

		let year = now.getFullYear();//연도
		let month = now.getMonth() + 1;//월
		let date = now.getDate();//일

		let todayString = now.getFullYear() + "-";
		let todayMonth = now.getMonth() + 1;
		if (todayMonth < 10) {
			todayString += "0";
		}
		todayString += todayMonth + "-";
		let todayDate = now.getDate();
		if (todayDate < 10) {
			todayString += "0";
		}
		todayString += todayDate;

		$("#wrDate").val(todayString);
		$("#date").text(todayString);
	});

	$("#title").keyup(function() {
		let title = $("#title").text();
		// console.log(title.length);
		$("#title2").text(title);
	});
	
	$("#back").click(function(){
		Swal.fire({
                  title: '작성내용이 모두 사라집니다.',
                  text: "진행 하시겠습니까?",
                  icon: 'warning',
                  showCancelButton: true,
                  confirmButtonColor: '#c5bfbf',
                  cancelButtonColor: '#c5bfbf',
                  confirmButtonText: '승인',
                  cancelButtonText: '취소'
              }).then((result) => {
                  if (result.isConfirmed) {
                      window.history.back();
                  }
              });
	});
	
	$("#tempStore").click(function(){
		Swal.fire({
			title: '임시저장',
			text: "작성하신 문서를 임시저장 하시겠습니까?\n맞으시면 '확인'을 눌러주세요.",
			icon: 'question',
			showCancelButton: true,
			confirmButtonColor: '#c5bfbf',
			cancelButtonColor: '#c5bfbf',
			confirmButtonText: '승인',
			cancelButtonText: '취소'
		}).then((result) => {
			if (result.isConfirmed) {
				$("#docStatus").val("임시저장");
				let cnt = $("#writein").html();
				console.log(cnt)
				$("#cnttt").val(cnt);

				let sendDraft = $("#insertin").html();
				console.log(sendDraft);
				$("#draftcnt").val(sendDraft);
				$("#docTitle").val($("#title").text());
				$("#temp").submit();
				
			}
		})
	});

}
function sendData() {

	const cnts = document.querySelectorAll('table#write tbody tr #cnt');
	const amts = document.querySelectorAll('table#write tbody tr #amt');
	const memos = document.querySelectorAll('table#write tbody tr #memo');

	const resultcnts = document.querySelectorAll('table#inserttbl tbody tr #cnt');
	const resultamts = document.querySelectorAll('table#inserttbl tbody tr #amt');
	const resultmemos = document.querySelectorAll('table#inserttbl tbody tr #memo');

	// console.log(cnts);
	// console.log(amts);
	// console.log(memos);

	let totalamt = 0;

	for (let i = 0; i < amts.length; i++) {
		if (!(Number(amts[i].innerText) || amts[i].innerText === '')) {
			//alert('숫자 아닌거 있음');
			Swal.fire({
				icon: 'warning',
				title: '금액란 입력 오류',
				text: '금액란에 숫자만 입력해주세요!',
			})
			return;
		} else if (amts[0].innerText.length < 1) {
			console.log(amts[0].innerText);
			//alert('내용 한개이상 입력해야함');
			Swal.fire({
				icon: 'warning',
				title: '등록된 금액이 없습니다!',
				text: '내용을 한개이상 입력해주세요!',
			})
			return;
		} else {
			totalamt += Number(amts[i].innerText);
		}
	}

	for (let i = 0; i < cnts.length; i++) {
		if (cnts[0].innerText.length < 1) {
			console.log(cnts[0].innerText);
			//alert('내용없음 한개이상 입력해야함');
			Swal.fire({
				icon: 'warning',
				title: '등록된 내용이 없습니다!',
				text: '내용을 한개이상 입력해주세요!',
			})
			return;
		} else {
			resultamts[i].innerText = amts[i].innerText
			resultcnts[i].innerText = cnts[i].innerText
			resultmemos[i].innerText = memos[i].innerText

			// console.log(totalamt);

			document.getElementById("sumamt").innerText = totalamt;

		}
	}

}

function selectacc() {
	let account1 = $("#empList option:selected").text();
	let account2 = $("#empList2 option:selected").text();
	let account3 = $("#empList3 option:selected").text();

	if (account1 == "") {
		Swal.fire({
				icon: 'warning',
				title: '결재라인 없음',
				text: '최소 결재라인을 지정해주세요!'
			})
	} else if (account2 == "" && account3 == "") {
		Swal.fire({
			title: '결재라인이 1단계로 \n종료됩니다.',
			text: "맞으시면 확인을 눌러주세요.",
			icon: 'info',
			showCancelButton: true,
			confirmButtonColor: '#c5bfbf',
			cancelButtonColor: '#c5bfbf',
			confirmButtonText: '확인',
			cancelButtonText: '취소'
		}).then((result) => {
			if (result.isConfirmed) {
				$("#selacc1").text(account1);
				$("#selacc2").text(account2);
				$("#selacc3").text(account3);
			}
		})
	} else if (account3 == "") {
		Swal.fire({
			title: '결재라인이 2단계로 \n종료됩니다.',
			text: "맞으시면 확인을 눌러주세요.",
			icon: 'info',
			showCancelButton: true,
			confirmButtonColor: '#c5bfbf',
			cancelButtonColor: '#c5bfbf',
			confirmButtonText: '확인',
			cancelButtonText: '취소'
		}).then((result) => {
			if (result.isConfirmed) {
				$("#selacc1").text(account1);
				$("#selacc2").text(account2);
				$("#selacc3").text(account3);
			}
		})
	} else {
		Swal.fire({
			title: '결재라인이 3단계로 \n종료됩니다.',
			text: "맞으시면 확인을 눌러주세요.",
			icon: 'info',
			showCancelButton: true,
			confirmButtonColor: '#c5bfbf',
			cancelButtonColor: '#c5bfbf',
			confirmButtonText: '확인',
			cancelButtonText: '취소'
		}).then((result) => {
			if (result.isConfirmed) {
				$("#selacc1").text(account1);
				$("#selacc2").text(account2);
				$("#selacc3").text(account3);
			}
		})
	}
}

