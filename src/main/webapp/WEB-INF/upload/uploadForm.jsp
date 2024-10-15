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
		<form method="post" enctype="multipart/form-data" action="/spring/file/upload">
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
				<%-- <tr>
					<td colspan="2" align="left">
						<input type="file" name="img">
					</td>
				</tr>
				다중 업로드할 때는 name 속성의 이름이 같아야한다 => 서버로 갈 데이터를 배열로 받는다
				<tr>
					<td colspan="2" align="left">
						<input type="file" name="img">
					</td>
				</tr> --%>
				<!-- 한 번에 한 개 또는 여러 개(드래그)를 선택 => 서버로 갈 데이터를 List로 받는다 -->
				<tr>
					<td colspan="2" align="left">
						<input type="file" name="img[]" multiple="multiple">
					</td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<input type="submit" value="이미지 업로드">
						<input type="reset" value="취소">
						<input type="button" value="뒤로" onclick="history.go(-1);">
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>