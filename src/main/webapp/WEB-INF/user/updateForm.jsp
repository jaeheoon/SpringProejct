<%@ page contentType="text/html; charset=utf-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title></title>
<link rel="stylesheet" href="../css/update.css">
</head>
<body>
	<div id="container-header">
		<a href="/spring/">
			<img id="mang" alt="망그러진곰" src="../image/mangom3.png">
		</a>
	</div>
	<div id="container">
		<div id="edit-header">회원정보 수정</div>
		<form name="updateForm" id="updateForm">
			<input type="hidden" id="page" value="${ page }">
			<table>
				<tr>
					<th>이름</th>
					<td>
						<input type="text" name="name" id="name" value="${ userDTO.name }">
						<div id="nameDiv"></div>
					</td>
				</tr>
				<tr>
					<th>아이디</th>
					<td>
						<input type="text" name="id" id="id" value="${ userDTO.id }" readonly="readonly">
						<input type="hidden" id="check" value="">
						<div id="idDiv"></div>
					</td>
				</tr>
				<tr>
					<th>비밀번호</th>
					<td>
						<input type="password" name="pwd" id="pwd" value="${ userDTO.pwd }">
						<div id="pwdDiv"></div>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<input type="button" id="updateBtn" value="회원정보 수정">
						<input type="button" id="deleteBtn" value="회원정보 삭제">
						<input type="button" id="resetBtn" value="다시입력">
						<input type="button" value="뒤로" onclick="history.go(-1);">
					</td>
				</tr>
			</table>
		</form>
	</div>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
<script type="text/javascript" src="../js/update.js"></script>
</body>
</html>