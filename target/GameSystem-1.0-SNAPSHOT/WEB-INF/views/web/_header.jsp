<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<style>
	figure.image img{
	    width: 100%;
    	height: auto;
	}
</style>
<body>

    <div class="bg-light">
        <div class="container admin__panel">
            <!-- <div> -->
            <div class="row">
                <div class="col-sm-12 col-md-12 col-lg-12 col-xl-12 px-0">
                    <div class="admin__header bg-light">
                        <nav class="navbar navbar-expand-md navbar-light bg-light px-4">
                            <a class="navbar-brand mr-5" href="#1">
                                <img src="<c:url value='/template/web/img/logo1.png'/>" alt="logo" class="img img-fluid" width="100px">
                            </a>
                            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarTogglerDemo02" aria-controls="navbarTogglerDemo02" aria-expanded="false" aria-label="Toggle navigation">
                                <span class="navbar-toggler-icon"></span>
                            </button>
                            <div class="collapse navbar-collapse" id="navbarTogglerDemo02">
                                <ul class="navbar-nav ml-auto align-items-center">
                                    <c:if test="${sessionScope.account == null}">
	                                    <li class="nav-item mr-2">
	                                        <a class="nav-link" href="${pageContext.request.contextPath}/login">
	                                            <i class="fas fa-user"></i>
	                                            <span>Đăng nhập</span>
	                                        </a>
	                                    </li>
                                    </c:if>
                                     <c:if test="${sessionScope.account != null}">
	                                    <li class="nav-item mr-2">
	                                    	<i class="fas fa-user"></i>
	                                       	<span>${sessionScope.account.fullname}</span>
	                                    </li>   
	                                     	<li class="nav-item mr-2">
                                        <a class="nav-link" href="${pageContext.request.contextPath}/logout">
                                            <i class="fas fa-user"></i>
                                            <span>Đăng xuất</span>
                                        </a>
                                    </li>   
                                    </c:if>    
                                                           
                                </ul>
                            </div>
                        </nav>
                    </div>
                </div>
            </div>
        </div>
    </div>
    
    <section class="bg-dark text-center text-white">
        <div class="container">
            <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                  <span class="navbar-toggler-icon"></span>
                </button>

                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav mr-auto">
                        <li class="nav-item active">
                            <a class="nav-link text-white" href="${pageContext.request.contextPath}/home">Trang chủ <span class="sr-only">(current)</span></a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link text-white" href="${pageContext.request.contextPath}/tin-nong">Tin game nóng</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link text-white" href="${pageContext.request.contextPath}/tin-moi">Tin game trong ngày</a>
                        </li>
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle text-white" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                Thể loại
                            </a>
                            <div class="dropdown-menu" aria-labelledby="navbarDropdown">
	                            <c:forEach items="${listCategory}" var="category">
	                                <a class="dropdown-item" href="${pageContext.request.contextPath}/the-loai?id=${category.id}">${category.name}</a>
	                          	</c:forEach>
                            </div>
                        </li>
                    </ul>
                    <form class="form-inline my-2 my-lg-0" action="${pageContext.request.contextPath}/tim-tin" method="POST">
                        <input class="form-control mr-sm-2" name="search" type="search" placeholder="Nhập nội dung tìm kiếm" aria-label="Search">
                        <button class="btn btn-outline-light my-2 my-sm-0" type="submit">Search</button>
                    </form>
                </div>
            </nav>
        </div>
    </section>