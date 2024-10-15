<%@ page contentType="text/html; charset=utf-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title></title>
<link rel="stylesheet" href="../css/uploadView.css">
</head>
<body>
	<div id="container-header">
		<a href="/spring/">
			<img id="mang" alt="망그러진곰" src="../image/mangom3.png">
		</a>
	</div>
	<div id="container">
		<div id="edit-header">이미지 상세보기</div>
		<form name="uploadView" id="uploadView">
			<input type="hidden" id="seq" value="${ uploadDTO.seq }">
			<table>
				<tr>
					<td rowspan="3">
						<img alt="${ uploadDTO.imageName }" src="https://kr.object.ncloudstorage.com/bitcamp-9th-bucket-147/storage/${ uploadDTO.imageFileName }" width = "120" height="110">
					</td>
					<td>
						번호 : ${ uploadDTO.seq }
					</td>
				</tr>
				<tr>
					<td>
						상품명 : ${ uploadDTO.imageName }
					</td>
				</tr>
				<tr>
					<td>
						파일명 : ${ uploadDTO.imageOriginalFileName }
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<pre>${ uploadDTO.imageContent }</pre>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<input type="button" id="updateBtn" value="수정하기" onclick="location.href='/spring/file/updateForm?seq=${ uploadDTO.seq }'">
						<input type="button" id="deleteBtn" value="삭제하기">
						<input type="button" value="뒤로" onclick="history.go(-1);">
					</td>
				</tr>
			</table>
		</form>
	</div>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
<script type="text/javascript" src="../js/uploadView.js"></script>
</body>
</html>