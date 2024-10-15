$(function(){
	// 수정 버튼 클릭 시 유효성 검사
	$('#updateBtn').click(function(event){
		let formData = new FormData($('#uploadUpdate')[0]);
	
		$.ajax({
			url: '/spring/file/update',
			type: 'POST',
			enctype: 'multipart/form-data',
			processData: false,
			contentType: false,
			data: formData,
			success: function() {
				alert('수정 완료');
				location.href = '/spring/file/list';
			},
			error: function(e) {
				console.log(e);
			}
		});
	});
});

//이미지 미리보기
$('#camera').click(function(){
	$('#img').trigger('click'); //강제 이벤트 발생
});

$('#img').change(function(){
	$('#showImg').empty();
	
	for(var i=0; i<this.files.length; i++){
		readURL(this.files[i]);
	}
});

function readURL(file){
	var reader = new FileReader();
	
	reader.onload = function(e){
		var img = document.createElement('img');
		img.src = e.target.result;
		img.width = 70;
		img.height = 70;
		$('#showImg').append(img);
	}
	
	reader.readAsDataURL(file);
}