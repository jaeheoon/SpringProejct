$(function(){
	// 유효성 검사 함수
	function validateForm() {
		// 오류 메시지 초기화 및 숨김
		$('#nameDiv').html('');
		$('#idDiv').html('');
		$('#pwdDiv').html('');
		
		var isValid = true;
		
		// 비밀번호 입력 검사
		if ($('#pwd').val() === ''){
		   $('#pwdDiv').html('비밀번호를 입력해주세요'); 
		   $('#pwd').focus();
		   isValid = false;
		}
		
		// 아이디 입력 검사
		if ($('#id').val() === ''){
		   $('#idDiv').html('아이디를 입력해주세요');
		   $('#id').focus();
		   isValid = false;
		}
		
		// 이름 입력 검사
		if ($('#name').val() === ''){
		   $('#nameDiv').html('이름을 입력해주세요'); // 오류 메시지 표시
		   $('#name').focus();
		   isValid = false;
		}
		
		return isValid;
	}
	
	// 수정 버튼 클릭 시 유효성 검사
	$('#updateBtn').click(function(event){
		if (!validateForm()) {
			event.preventDefault(); // 폼 제출 방지
		} else {
			$.ajax({
				url: '/spring/user/update',
				type: 'POST',
				data: $('#updateForm').serialize(),
				success: function() {
					alert('회원정보 수정 완료');
					location.href = '/spring/user/list?page='+$('#page').val();
				},
				error: function(e) {
					console.log(e);
				}
			});
		}
	});
	
	$('#deleteBtn').click(function(event){
		let id = $('#id').val();
		let page = $('#page').val();
		location.href='/spring/user/deleteForm?id='+id+'&page='+page;
	});
	
	// 삭제 버튼 클릭 시
	/*$('#deleteBtn').click(function(event){
		let pwd = prompt('비밀번호를 입력하세요');
		let id = $('#id').val();
		if(pwd == null) {
			alert('비밀번호를 입력해주세요');
			return;
		}
		$.ajax({
			url: '/spring/user/delete',
			type: 'POST',
			data: { id: id,
					pwd: pwd },
			success: function(data) {
				if(data == 'true') {
					alert('회원 탈퇴에 성공하였습니다');
					location.href='/spring/user/list';
				} else alert('회원 탈퇴에 실패하였습니다');
				
			},
			error: function(e) {
				console.log(e);
			}
		});
	});*/

	// 초기화 버튼 클릭 시 입력 필드 및 오류 메시지 초기화
	$('#resetBtn').click(function(){
	    $('#nameDiv').html('');
	    $('#idDiv').html('');
	    $('#pwdDiv').html('');
	    $('#updateForm')[0].reset();
	});
});

