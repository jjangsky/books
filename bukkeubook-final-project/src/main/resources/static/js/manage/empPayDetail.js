window.onload = function() {
	

	let salNo = $("#salNo").val();
	$("#delete").click(function() {
		Swal.fire({
			title: '급여내역 삭제',
			text: "삭제 하시겠습니까?",
			icon: 'warning',
			showCancelButton: true,
			confirmButtonColor: '#c5bfbf',
			cancelButtonColor: '#c5bfbf',
			confirmButtonText: '확인',
			cancelButtonText: '취소'
		}).then((result) => {
			if (result.isConfirmed) {
				location.href = "/empPay/paydelete/" + salNo
				
			}
		})
	});

	$("#backBtn").click(function() {
		window.history.back();
	});

}


