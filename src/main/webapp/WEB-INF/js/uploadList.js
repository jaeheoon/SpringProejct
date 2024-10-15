$(function(){
    $('#all_check').change(function(){
        let isCheck = $(this).is(':checked');
        $('.img_check').prop('checked', isCheck);
    });
    $('.img_check').change(function(){
        let allChecked = $('.img_check').length === $('.img_check:checked').length;
        $('#all_check').prop('checked', allChecked);
    });
    
    $('#deleteBtn').click(function(){
    	let selectedImages = [];
        $('.img_check:checked').each(function() {
            selectedImages.push($(this).attr('id'));
        });

        // 선택된 체크박스가 있는지 확인
        if (selectedImages.length === 0) {
            alert("선택된 항목이 없습니다.");
            return;
        }

        // 서버로 전송할 데이터
        $.ajax({
            url: '/spring/file/delete', // 실제 파일 삭제를 처리할 서버의 URL
            type: 'POST',
            data: JSON.stringify({ seqs: selectedImages }),
            contentType: 'application/json',
            success: function(response) {
                // 삭제 성공 시, 화면에서 해당 항목들을 제거
                $('.img_check:checked').each(function() {
                    $(this).closest('tr').remove(); // 체크된 행을 삭제
                });
                alert("삭제가 완료되었습니다.");
            },
            error: function(e) {
                alert("삭제 중 오류가 발생했습니다." + e);
            }
        });
    });
});