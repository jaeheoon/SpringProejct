$(function(){
	/*$('#deleteBtn').click(function(event){
		let id = $('#id').val();
		let page = $('#page').val();
		location.href='/spring/file/deleteForm?id='+id+'&page='+page;
	});*/
	
	// 삭제 버튼 클릭 시
	$('#deleteBtn').click(function(event) {
		let selectedImages = [];
		selectedImages.push($('#seq').val());
    	$.ajax({
            url: '/spring/file/delete', // 실제 삭제를 처리할 URL
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify({ seqs: selectedImages }), // seq를 JSON으로 전송
            success: function(data) {
	            if (data === 'ok') {
	                alert("삭제가 완료되었습니다.");
	                location.href = '/spring/file/list'; // 삭제 후 목록 페이지로 이동
	            }
            },
            error: function() {
                alert("삭제 요청이 실패했습니다.");
            }
        });
	});
});

