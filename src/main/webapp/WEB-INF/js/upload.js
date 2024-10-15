/* 이미지 업로드 */
$(function(){
	$('#uploadBtn').on('click', function(){
		let formData = new FormData($('#uploadAJaxForm')[0]);
	
		$.ajax({
			type: 'post',
			enctype: 'multipart/form-data',
			//processData: 기본적으로 Query String으로 변환해서 보내진다, 파일 전송시에는 반드시 false로 해야함
			processData: false,
			//contentType: 파일 전송시에는 'multipart/form-data'로 전송이 될 수 있도록 false로 설정한다
			contentType: false,
			url: '/spring/file/upload',
			data: formData,
			success: function(){
				alert('이미지 등록 완료');
				location.href="/spring/file/list";
			},
			error: function(e){
				console.log(e);
			}
		});
	})
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

/*
FileReader 란?
FileReader는 type이 file인 input 태그 또는 API 요청과 같은 인터페이스를 통해 
File 또는 Blob 객체를 편리하게 처리할수있는 방법을 제공하는 객체이며
abort, load, error와 같은 이벤트에서 발생한 프로세스를 처리하는데 주로 사용되며,
File 또는 Blob 객체를 읽어서 result 속성에 저장한다.

FileReader도 비동기로 동작한다.

FileReader.onload()
load 이벤트의 핸들러. 이 이벤트는 읽기 동작이 성공적으로 완료되었을 때마다 발생한다.
*/