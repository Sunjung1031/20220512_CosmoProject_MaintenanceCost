<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>정보삭제</title>
</head>
<body>
	<form action="delete.do?no=${unit.no}" method="post">
	削除しますか？
		<input type="submit" value="はい">
		<button type="button"  onclick="location='./list.do'">いいえ</button>
	</form>
</body>
</html>