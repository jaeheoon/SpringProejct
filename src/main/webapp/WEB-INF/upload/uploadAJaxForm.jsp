<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="../css/upload.css">
</head>
<body>
	<div id="container-header">
		<a href="/spring/">
			<img id="mang" alt="망그러진곰" src="../image/mangom3.png">
		</a>
	</div>
	<div id="container">
		<div id="edit-header">파일 업로드</div>
		<form id="uploadAJaxForm">
			<table>
				<tr>
					<th>상품명</th>
					<td>
						<input type="text" id="imageName" name="imageName" size="35">
					</td>
				</tr>
				<tr>
					<th>상품설명</th>
					<td>
						<textarea id="imageContent" name="imageContent" rows="10" cols="50"></textarea>
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
						<span id="showImg">이미지 미리보기</span>
						<input type="file" id="img" name="img[]" multiple="multiple" style="visibility: hidden;">
					</td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<input type="button" id="uploadBtn" value="이미지 업로드">
						<input type="reset" value="취소">
						<input type="button" value="뒤로" onclick="history.go(-1);">
					</td>
				</tr>
			</table>
		</form>
	</div>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
<script type="text/javascript" src="../js/upload.js"></script>
</body>
</html>