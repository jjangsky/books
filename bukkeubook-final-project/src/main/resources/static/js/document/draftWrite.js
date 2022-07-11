/**
 * 
 */

let user = "hyejun";
$("#writer").val(user);
let date = $("#date").val();
let writer = $("#writer").val();
let dept = $("#dept").val();

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

	$("#date2").text(todayString);
});

$("#title").keyup(function() {
	let title = $("#title").val();
	// console.log(title);
	$("#title2").text(title);
})

$('.description').summernote({
	height: 200,
	width: 350,
	toolbar: [
		// 표만들기
		['table', ['table']],
		// 글머리 기호, 번호매기기, 문단정렬
		['para', ['ul', 'ol', 'paragraph']],
		// 코드보기, 확대해서보기, 도움말
		['view', ['codeview', 'fullscreen', 'help']]
	],
	callbacks: {
		onKeyup: function(e) {
			setTimeout(function() {
				$("#result").html($('.description').val());
			}, 200);
		}
	}
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
	let account1 = $("#account1").val();
	let account2 = $("#account2").val();
	let account3 = $("#account3").val();

	if (account1 == "") {
		alert("최소 결재라인을 지정해주세요!")
	} else if (account2 == "" && account3 == "") {
		let con = confirm("결재라인이 1단계로 종료됩니다. 맞으시면 확인을 눌러주세요.");
		if (con) {
			$("#selacc1").text(account1);
			$("#selacc2").text(account2);
			$("#selacc3").text(account3);
		}
	} else if (account3 == "") {
		let con = confirm("결재라인이 2단계로 종료됩니다. 맞으시면 확인을 눌러주세요.");
		if (con) {
			$("#selacc1").text(account1);
			$("#selacc2").text(account2);
			$("#selacc3").text(account3);
		}
	} else {
		let con = confirm("결재라인이 3단계로 종료됩니다. 맞으시면 확인을 눌러주세요.");
		if (con) {
			$("#selacc1").text(account1);
			$("#selacc2").text(account2);
			$("#selacc3").text(account3);
		}
	}
}
