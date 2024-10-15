$(function(){
	// 유효성 검사 함수
	function validateForm() {
		// 오류 메시지 초기화 및 숨김
		$('#pwdDiv').html('');
		
		var isValid = true;
		
		// 비밀번호 입력 검사
		if ($('#pwd').val() === ''){
		   $('#pwdDiv').html('비밀번호를 입력해주세요'); 
		   $('#pwd').focus();
		   isValid = false;
		}
		
		return isValid;
	}
	
	// 삭제 버튼 클릭 시
	$('#deleteBtn').click(function(event){
		let id = $('#id').val();
		let pwd = $('#pwd').val();
		if(pwd == '') {
			alert('비밀번호를 입력해주세요');
			return;
		}
		//아이디 JSON으로 확인
		/*
		$.ajax({
			url: '/spring/user/getExistPwd',
			data: {id: $('#id').val()},
			dataType: 'json',
			success:function(data) {
				alert(JSON.stringfy(data));
			},
			error: function(e) {
				console.log(e);
			}
		});
		*/
		$.ajax({
			url: '/spring/user/delete',
			type: 'POST',
			data: { id: id,
					pwd: pwd },
			success: function(data) {
				if(data == 'true') {
					alert('회원 탈퇴에 성공하였습니다');
					location.href='/spring/user/list';
				} else {
					alert('회원 탈퇴에 실패하였습니다');
					$('#pwd').val('');
				}
				
			},
			error: function(e) {
				console.log(e);
			}
		});
	});

	// 초기화 버튼 클릭 시 입력 필드 및 오류 메시지 초기화
	$('#resetBtn').click(function(){
	    $('#pwdDiv').html('');
	    $('#updateForm')[0].reset();
	});
});