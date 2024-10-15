<%-- SpringProject/src/main/webapp/WEB-INF/user/deleteForm.jsp --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="../css/delete.css">
<title>회원탈퇴</title>
</head>
<body>
	<div id="container-header">
		<a href="/spring/">
			<img id="mang" alt="망그러진곰" src="../image/mangom3.png">
		</a>
	</div>
	<div id="container">
		<div id="delete-header">회원탈퇴</div>
		<form name="deleteForm" id="deleteForm">
			<input type="hidden" id="page" value="${page}"/>
			<input type="hidden" id="id" value="${userDTO.id}"/>
			<table>
				<thead></thead>
				<tbody>
				<tr>
				    <th class="label">비밀번호</th>
				    <td class="input">
				       <input type="password" name="pwd" id="pwd" placeholder="비밀번호를 입력하세요" />
				       <div id="pwdDiv"></div>
				    </td>
				</tr>
				</tbody>
				<tfoot></tfoot>
				<tr align="center">
			        <td colspan="2" height="20">
			            <input type="button" id="deleteBtn" value="회원탈퇴" />
			        </td>
			    </tr>
			</table>
		</form>
	</div>
	<div id="menuDiv">
		<input type="button" id="menuBtn" value="뒤로" onclick="history.go(-1);"/>
		<input type="button" id="menuBtn" value="회원목록" onclick="location.href='/spring/user/list?page=${page}'"/>
	</div>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.7.1.min.js"></script> 
<script type="text/javascript" src="../js/delete.js"></script>
</body>
</html>