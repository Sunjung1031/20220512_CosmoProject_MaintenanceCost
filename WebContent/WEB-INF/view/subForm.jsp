<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<%
String readOnly = "readOnly";
if (request.getAttribute("readOnly") != null && (Boolean) request.getAttribute("readOnly") == false) {
	readOnly = "";
}
%>
<html>
<head>
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Open+Sans:ital,wght@0,300;0,400;0,500;0,600;0,700;1,300;1,400;1,500;1,600&display=swap"
	rel="stylesheet">
<meta charset="UTF-8">
<title>수도세, 전기세 페이지</title>
<style>
body {
	font-family: 'Open Sans', sans-serif;
}

input {
	border: solid 1px #D5D5D5;
	border-radius: 2px;
	height: 20px;
	float: right;
	margin: 0 5px
}

input:focus {
	background-color: #E8F0FE;
	outline: none;
}

.container {
	width: 50%;
	margin: auto;
}

#main_div {
	border-bottom: solid 2px gray;
	margin-bottom: 20px;
	margin-top: 50px;
}

#main_text {
	font-weight: bold;
	font-size: 25px;
}

.text_div {
	margin: 10px;
	padding: 5px;
	border-bottom: solid 1px #EAEAEA;
}

.text_id {
	display: inline-block;
	width: 200px;
	font-weight: 400;
	font-size: 14px;
	color: #8C8C8C;
}

.text_div input {
	text-align: right;
	float: right;
}

#btn_div {
	float: right;
	margin-top: 30px;
}

.submit_btn {
	background-color: #353535;
	color: #EAEAEA;
	padding: auto 30px;
	border-radius: 2px;
	border: none;
	width: 100px;
	height: 30px;
	font-size: 15px;
	font-weight: 400;
}

.submit_btn:hover {
	background-color: #8C8C8C;
	color: #EAEAEA;
}
.text_read {
   border: solid 1px #D5D5D5;
   border-radius: 2px;
   height: 20px;
   background-color: #E8F0FE;
   float:right;
   text-align: right;
}
</style>
<%
if (request.getAttribute("SubSuccess") != null) {
	String SubSuccess = (String) request.getAttribute("SubSuccess");
	if (SubSuccess.equals("insert")) {
%>
<script type="text/javascript">
alert( ${subReq.no } + "号室追加完了");
</script>
<%
} else if (SubSuccess.equals("update")) {
%>
<script type="text/javascript">
alert( ${sub.no } + "号室修正完了");
</script>
<%
}
}
%>
<%
if (request.getAttribute("NumExist") != null) {
%>
<script type="text/javascript">
alert( ${subReq.no } + "号室があります。");
</script>
<%
}
%>


</head>
<body>
	<c:if test="${errors.ele_ind  || errors.water_ind  ||errors.ele_basic ||errors.ele_ind  }">
		<script type="text/javascript">
			alert("空欄なしに入力してください。");
		</script>
	</c:if>
	<input type="hidden" value="${ ctxPath = pageContext.request.contextPath }" />

	<div class="container">
		<div id=main_div>
			<span id="main_text">水道代, 電気代</span>
		</div>
		<form method="post" action="subRead.do">
			<div class="text_div">
				<span class="text_id">号室</span>
				<span>
					<input  class="text_read"  type="text" name="no" value="${subReq.no }号" <%=readOnly%> />
				</span>
			</div>

			<div class="text_div">
				<span class="text_id">水道代</span>
				<span>
					<input   class="text_read"  type="text" name="water_ind" value="${subReq.water_ind }" <%=readOnly%> />
				</span>
			</div>

			<div class="text_div">
				<span class="text_id">基本電気代</span>
				<span>
					<input class="text_read"   type="text" name="ele_basic" value="${subReq.ele_basic }" <%=readOnly%> />
				</span>
			</div>

			<div class="text_div">
				<span class="text_id">個人電気代</span>
				<span>
					<input  class="text_read"  type="text" name="ele_ind" value="${subReq.ele_ind }" <%=readOnly%> />
				</span>
			</div>
			<div id="btn_div">
				<input class="submit_btn" type="button" value="戻る" onclick="location.href = 'subList.do'" />
				<%
				if (readOnly.equals("readOnly")) {
				%>
				<input class="submit_btn" type="button" value="修正" onclick="location.href = 'subModify.do?no=${subReq.no }'" />
				<%
				} else {
				%>
				<input class="submit_btn" type="submit" value="登録" />
				<%
				}
				%>

			</div>
		</form>
	</div>
</body>
</html>