<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true" pageEncoding="UTF-8" %><!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html lang="ko">
<head>

    <title>Include Header Example</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" >
    <style>
        .navbar-container{
            position: fixed;
            top: 0;
            left: 0;
            right: 0;
            max-width: 1024px;
            margin: 0 auto;
            width: 100%;

        }
    </style>
</head>
<body>
<div class="navbar-container"> <!-- 컨테이너 추가 -->
    <ul class="nav nav-pills nav-justified">
        <li class="nav-item">
            <a class="nav-link " aria-current="page" href="#">마이페이지</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="#">오늘의 학습</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="#">나의 학습</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" aria-disabled="true">공유학습</a>
        </li>
        <c:choose>
            <c:when test="${sessionScope.memberDTO !=null}">
                <li class="nav-item">
                    <a class="nav-link"  href="/login/logout" aria-disabled="true">로그아웃</a>
                </li>
            </c:when>
            <c:otherwise>
                <li class="nav-item">
                    <a class="nav-link"  href="/login/login" aria-disabled="true">로그인</a>
                </li>
            </c:otherwise>
        </c:choose>

    </ul>
</div>

</body>
</html>