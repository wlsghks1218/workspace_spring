<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Scroll Menu Example</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
        }
        header {
            background: #333;
            color: #fff;
            padding: 10px;
            position: fixed;
            width: 100%;
            top: 0;
            left: 0;
            transition: background 0.3s;
            z-index: 1000;
        }
        header.scrolled {
            background: rgba(51, 51, 51, 0.8);
        }
        .content {
            margin-top: 60px;
            padding: 20px;
            height: 2000px; /* Example content height */
            background: linear-gradient(to bottom, #f0f0f0, #ccc);
        }
    </style>
</head>
<body>

<header id="header">
    <h1>Scroll Menu</h1>
</header>

<div class="content">
    <h2>Scroll Down to See the Effect</h2>
    <p>Content goes here...</p>
</div>

<script>
    window.addEventListener('scroll', function() {
        const header = document.getElementById('header');
        if (window.scrollY > 50) {
            header.classList.add('scrolled');
        } else {
            header.classList.remove('scrolled');
        }
    });
</script>

</body>
</html>