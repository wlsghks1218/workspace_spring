<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<link rel="shortcut icon" href="#">
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>회원가입</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
        }
        .container {
            max-width: 400px;
            margin: 50px auto;
            padding: 20px;
            background: white;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        h2 {
            text-align: center;
            margin-bottom: 20px;
        }
        .form-group {
            margin-bottom: 15px;
        }
        .form-group label {
            display: block;
            margin-bottom: 5px;
        }
        .form-group input {
            width: 95%;
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 4px;
        }
        .btn {
            width: 100%;
            padding: 10px;
            background-color: #5cb85c;
            border: none;
            color: white;
            border-radius: 4px;
            cursor: pointer;
            font-size: 16px;
        }
        .btn:hover {
            background-color: #4cae4c;
        }
        .footer {
            text-align: center;
            margin-top: 20px;
        }
    </style>
</head>
<body>

<div class="container">
    <h2>회원가입</h2>
    <form method="post">
        <div class="form-group">
            <label for="id">ID</label>
            <input type="text" id="id" name="userId" required>
        </div>
        <div class="form-group">
            <label for="password">비밀번호</label>
            <input type="password" id="password" name="userPw" required>
        </div>
        <div class="form-group">
            <label for="confirm_password">비밀번호 확인</label>
            <input type="password" id="confirm_password" name="confirmUserPw" required>
        </div>
        <div class="form-group">
            <label for="nickname">닉네임</label>
            <input type="text" id="nickname" name="userName" required>
        </div>
        <button type="submit" id="submitBtn">가입하기</button>
    </form>
    <div class="footer">
        <p>이미 계정이 있으신가요? <a href="/customLogin">로그인</a></p>
        <input type="button" value="뒤로가기" onclick="backToHistory()">
    </div>
</div>
</body>
<script type="text/javascript" src="/resources/js/join.js"></script>
</html>