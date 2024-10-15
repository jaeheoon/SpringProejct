<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="../css/uploadList.css">
</head>
<body>
	<div id="container-header">
		<a href="/spring/">
			<img id="mang" alt="망그러진곰" src="../image/mangom3.png">
		</a>
	</div>
	<div id="container">
		<div id="edit-header">이미지 리스트</div>
        <table>
            <thead>
                <tr>
                	<th><input type="checkbox" name="all_check" id="all_check" value="Y"></th>
                    <th class="seq">이미지 번호</th>
                    <th class="name">상품명</th>
                    <th class="imgTh">이미지</th>
                </tr>
            </thead>
            <tbody>
                <!-- 목록 -->
                <c:forEach var="img" items="${ list }">
               		<tr>
               			 <td><input type="checkbox" id="${img.seq}" class="img_check" value="이미지"></td>
	                     <td>${ img.seq }</td>
	                     <td><a href="/spring/file/uploadView?seq=${img.seq}">${ img.imageName }</a></td>
	                     <%-- <td><img src="/spring/storage/${ img.imageOriginalFileName }" width = "120" height="110"></td> --%>
	                     <!-- Object Storage -->
	                     <td>
	                     	<a href="/spring/file/uploadView?seq=${img.seq}"><img src="https://kr.object.ncloudstorage.com/bitcamp-9th-bucket-147/storage/${ img.imageFileName }" width = "120" height="110"></a>
                     	</td>
                	</tr>
                </c:forEach>
            </tbody>
        </table>
   	</div><!-- //div#container -->
    <div id="tfBtn">
   		<input type="button" id="deleteBtn" value="삭제하기">
    </div>
    <div id="paging">
    	
    </div>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
<script type="text/javascript" src="../js/uploadList.js"></script>
</body>
</html>