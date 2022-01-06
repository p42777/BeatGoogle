<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>GoogleSearch</title>
</head>
<body style = "background-image: url('https://i.imgur.com/6v4xBFE.jpg');background-size: cover;background-position:5% 5%;background-repeat:no-repeat;" >
 <div style="text-align:center;
   position: absolute;
   top: 55%;
   left: 29%;
   margin: -10px 0 0 -200px;"><br>
   <form action='${requestUri}' method='get'>
  <input type='text' name='keyword' placeholder = '請輸入關鍵字'style="width:300px;height:40px;font-size:16px;"/>
  <input type='submit' value='搜尋'style="width:120px;height:45px;font-size:16px;border-style: hidden;background-color:#B8B8FF" />
  </form>
  <form action = 'Main.jsp'>
  </form>
  </div>
</body>
</html>