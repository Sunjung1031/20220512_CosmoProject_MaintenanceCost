<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Sub 리스트</title>
<style type="text/css">
body {
	font-family: 'Open Sans', sans-serif;
}

.container {
	width: 85%;
	margin: auto;
}

#tr_title td {
	font-size: 12px;
}

table {
	width: 100%;
	border: none;
	margin-top: 50px;
}

td {
	border: none;
	color: #8C8C8C;
	text-align: center;
	height: 35px;
	padding: 0;
}

.td_title {
	color: #747474;
	text-align: center;
	height: 35px;
	font-weight: bold;
	border-bottom: 1px solid gray;
}

.no_a {
	color: #8C8C8C;
}

.no_a:hover {
	text-decoration-line: none;
}

.head_text {
	font-weight: bold;
	font-size: 25px;
	border-bottom: 2px solid gray;
	color: black;
	text-align: left;
}

.trTarget :nth-child(1) {
	width: 15%;
}

.trTarget :nth-child(3) {
	width: 15%;
}

.trTarget :nth-child(4) {
	width: 20%;
}

.trTarget :nth-child(2) {
	width: 40%;
}

#tr_page_a {
	padding-top: 15px;
}

.page_a {
	margin: 0 10px;
	text-decoration-line: none;
	color: #8C8C8C;
}

.page_a:hover {
	margin: 0 10px;
	text-decoration-line: none;
	color: #5d5d5d;
}

#btn_div {
	float: right;
	margin-top: 30px;
}

.input_btn {
	background-color: #353535;
	color: #EAEAEA;
	padding: auto 30px;
	border-radius: 2px;
	border: none;
}

.input_btn:hover {
	background-color: #8C8C8C;
	color: #EAEAEA;
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

.td_title {
	color: #747474;
	text-align: center;
	height: 35px;
	font-weight: bold;
	border-bottom: 1px solid gray;
}
</style>
<title>Insert title here</title>
</head>
<script type="text/javascript">
	
<%if (request.getAttribute("DeleteResult") != null) {
	boolean DeleteResult = (boolean) request.getAttribute("DeleteResult");
	if (DeleteResult == true) {%>
	alert("削除成功")
<%} else if (DeleteResult == false) {%>
	alert("削除失敗")
<%}
}%>
	function autoInsert(num, uri) {
		alert(uri);
		location.href = 'subList.do?autoInsert=yes&num=' + num + "&" + uri;
	}
	function deleteConfirm(no) {
		if (confirm(no + "号室を削除しますか。") == true) {
			location.href = 'subDelete.do?no=' + no;
		} else {
			return;
		}
	}
</script>
<body>
	<div class="container">
		<form action="subList.do?uri=main" method="post">
			<table border='1'>
				<tr>
					<td class="head_text" colspan="17">管理費情報</td>
				</tr>
				<tr>
					<td colspan="22" height="50px"><input type="text"
						name="search" style="width: 40%; height: 24px; margin: 30px 0;"
						value="${ search.search}" /> <input class="input_btn"
						type="submit" value="検索" style="width: 70px; height: 30px;" /></td>
				</tr>
				<tr id="tr_title">
					<td class="td_title">号室</td>
					<td class="td_title">商店名</td>
					<td class="td_title">個人水道代</td>
					<td class="td_title">公共水道代</td>
					<td class="td_title">基本電気代</td>
					<td class="td_title">個人電気代</td>
					<td class="td_title">公共電気代</td>
					<td class="td_title">光熱費</td>
					<td class="td_title">掃除費用</td>
					<td class="td_title">エレベーター維持費</td>
					<td class="td_title">エスカレーター維持費</td>
					<td class="td_title">維持費</td>
					<td class="td_title">保険料</td>
					<td class="td_title">人件費</td>
					<td class="td_title">領収証確認</td>
				</tr>
				<c:forEach var="sub" items="${sub }">
					<tr>

						<td>${sub.no }</td>

						<td>${sub.unitName }</td>
						<%-- <td>${sub.size }</td> --%>
						<td>${sub.water_ind }</td>
						<td>${sub.com_water }</td>
						<td>${sub.ele_basic }</td>
						<td>${sub.ele_ind }</td>
						<td>${sub.com_ele }</td>
						<td>${sub.com_heat }</td>
						<td>${sub.com_clean }</td>
						<td>${sub.com_ev }</td>
						<td>${sub.com_es }</td>
						<td>${sub.com_maintain }</td>
						<td>${sub.com_insur }</td>
						<td>${sub.com_labor }</td>
						<%-- <td>${sub.hire }</td>
					<td>${sub.lease }</td> --%>
						<td><input class="submit_btn"
							style="width: 40px; height: 25px; font-size: 10px;" type="button"
							value="確認" onclick="location.href = 'receipt.do?no=${sub.no }'" /></td>
					</tr>
				</c:forEach>
				<tr>
					<td id="tr_page_a" colspan="22"><c:if
							test="${paging.total/paging.limit<=5 }">
							<c:forEach var="currentPage" begin="${paging.startPage }"
								end="${paging.total/paging.limit}">
								<a class="page_a"
									href="subList.do?uri=main&currentPage=${ currentPage}&limit=${paging.limit }">${currentPage }</a>
							</c:forEach>
						</c:if> <c:if test="${paging.endPage>5 }">
							<a class="page_a"
								href="subList.do?uri=main&startPage=${ paging.startPage-5}&limit=${paging.limit }&currentPage=${ paging.startPage -1}">
								前へ</a>
						</c:if> <c:if test="${paging.total/paging.limit>5 }">
							<c:forEach var="currentPage" begin="${paging.startPage }"
								end="${ paging.startPage +4 }">
								<a class="page_a"
									href="subList.do?uri=main&currentPage=${ currentPage}&limit=${paging.limit }&startPage=${ paging.startPage}">${currentPage }</a>
							</c:forEach>
							<a class="page_a"
								href="subList.do?uri=main&startPage=${ paging.endPage+1}&limit=${paging.limit }&currentPage=${ paging.endPage +1}">次へ</a>
						</c:if></td>
				</tr>
			</table>
		</form>
		<div id="btn_div">
			<button class="submit_btn" type="button"
				onclick="location.href='subList.do'" style="width: 150px;">水道代、電気代情報</button>
			<button class="submit_btn" type="button"
				onclick="location.href='<%=request.getContextPath()%>/unit/list.do'">戻る</button>
		</div>
	</div>
</body>
</html>