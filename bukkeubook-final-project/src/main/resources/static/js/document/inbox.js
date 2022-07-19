window.onload = function(){
	
	let docNo = $("#docNo").val();
	$(function(){
		$.ajax({
			url:"/document/checkButton/" + docNo,
			success: function(data){
				console.log(data);
				
				let docStatus = data[0];
				//console.log(docStatus);
				if(docStatus == "반려" || docStatus == "승인"){
					Swal.fire({
						icon: 'info',
						title: '결재 완료',
						text: '결재가 완료된 문서입니다.'
					})
					$("#apply").hide();
					$("#refuse").hide();
				}else{
					if(docStatus == "0" || docStatus == "1"){
						Swal.fire({
							icon: 'info',
							title: '결재 요망',
							text: '결재를 진행해주세요.'
						})
						$("#apply").show();
						$("#refuse").show();
					} else if (docStatus == "2" || docStatus == "3"){
						let isPossible = data[1];
						
						if(isPossible == "가능"){
							Swal.fire({
								icon: 'info',
								title: '결재 요망',
								text: '결재를 진행해주세요.'
							})
							$("#apply").show();
							$("#refuse").show();
						} else {
							Swal.fire({
								icon: 'info',
								title: '전결 미확인',
								text: '이전 결재자의 확인이 필요합니다.'
							})
							$("#apply").hide();
							$("#refuse").hide();
						}
					}
				}
			},
			error: function(error){
				console.log(error);
			}
		});
	});
	
	$("#back").click(function() {
				window.history.back();
	})
	
	$("#apply").click(function() {
		Swal.fire({
			title: '결재승인',
			text: "진행 하시겠습니까?",
			icon: 'warning',
			showCancelButton: true,
			confirmButtonColor: '#c5bfbf',
			cancelButtonColor: '#c5bfbf',
			confirmButtonText: '확인',
			cancelButtonText: '취소'
		}).then((result) => {
			if (result.isConfirmed) {
				console.log("dddd");
				$("#appStatus").val('승인');
				console.log($("#appStatus").val());
				$("#submitReport").submit();
				$("#app").submit();
			}
		})
	})
	
	$("#refuse").click(function() {
		Swal.fire({
			title: '결재반려',
			text: "진행 하시겠습니까?",
			icon: 'warning',
			showCancelButton: true,
			confirmButtonColor: '#c5bfbf',
			cancelButtonColor: '#c5bfbf',
			confirmButtonText: '확인',
			cancelButtonText: '취소'
		}).then((result) => {
			if (result.isConfirmed) {
				$("#appStatus").val('반려');
				$("#app").submit();
			}
		})
	})
	
	
}