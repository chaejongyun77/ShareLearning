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
            font-size:22px;
            border-bottom : 1px solid #C58917;


        }
        .btn.btn-outline-primary.me-2{
            color : white; !important;
            border: 1px solid white;

        }
        .nav-link.px-2{
            color : white; !important;
        }
       #nav{
            background: #C58917;
           border-radius: 8px;
        }
    </style>
</head>
<body>
<div class="navbar-container"> <!-- 컨테이너 추가 -->

    <header id="nav" class="d-flex flex-wrap align-items-center justify-content-center justify-content-md-between py-3 mb-4 border-bottom">
        <div class="col-md-3 mb-2 mb-md-0">
            <a href="/" class="d-inline-flex link-body-emphasis text-decoration-none">
                <svg class="bi" width="40" height="32" role="img" aria-label="Bootstrap"><use xlink:href="#bootstrap"></use></svg>
            </a>
        </div>

        <ul class="nav col-12 col-md-auto mb-2 justify-content-center mb-md-0">

            <li><a href="#" class="nav-link px-2" >마이페이지</a></li>
            <li><a href="#" class="nav-link px-2">오늘의 학습</a></li>
            <li><a href="#" class="nav-link px-2">나의 학습</a></li>
            <li><a href="#" class="nav-link px-2">공유학습</a></li>
        </ul>

        <div class="col-md-3 text-end">
            <c:choose>
            <c:when test="${sessionScope.memberDTO !=null}">
                <button type="button" class="btn btn-outline-primary me-2" onclick="location.href='/login/logout'">로그아웃</button>
            </c:when>
                <c:otherwise>
            <button type="button" class="btn btn-outline-primary me-2" onclick="location.href='/login/login'" >로그인</button>
                </c:otherwise>
            </c:choose>
        </div>
    </header>
    <%--<ul class="nav nav-pills nav-justified">
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

    </ul>--%>
</div>

</body>
</html>