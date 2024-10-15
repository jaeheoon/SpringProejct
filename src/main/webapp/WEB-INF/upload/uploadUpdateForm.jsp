<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="../css/uploadUpdate.css">
</head>
<body>
	<div id="container-header">
		<a href="/spring/">
			<img id="mang" alt="망그러진곰" src="../image/mangom3.png">
		</a>
	</div>
	<div id="container">
		<div id="edit-header">파일 업로드</div>
		<form id="uploadUpdate">
			<input type="hidden" name="seq" value="${ uploadDTO.seq }">
			
			<table>
				<tr>
					<th>상품명</th>
					<td>
						<input type="text" id="imageName" name="imageName" size="35" value="${ uploadDTO.imageName }">
					</td>
				</tr>
				<tr>
					<th>상품설명</th>
					<td>
						<textarea id="imageContent" name="imageContent" rows="10" cols="50">${ uploadDTO.imageContent }</textarea>
					</td>
				</tr>
				<!-- 한 번에 한 개 또는 여러 개(드래그)를 선택 => 서버로 갈 데이터를 List로 받는다 -->
				<tr>
					<td colspan="2" align="left">
						<img id="camera" alt="카메라" src="../image/camera.png" height="70" width="70">
					</td>
				</tr>
				<tr>
					<td colspan="2" align="left">
						<span id="showImg">
							<img alt="${ uploadDTO.imageName }" src="https://kr.object.ncloudstorage.com/bitcamp-9th-bucket-147/storage/${ uploadDTO.imageFileName }" width="70" height="70">
						</span>
						<input type="file" id="img" name="img" style="visibility: hidden;">
					</td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<input type="button" id="updateBtn" value="수정하기">
						<input type="reset" value="취소">
						<input type="button" value="뒤로" onclick="history.go(-1);">
					</td>
				</tr>
			</table>
		</form>
	</div>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
<script type="text/javascript" src="../js/uploadUpdate.js"></script>
</body>
</html>