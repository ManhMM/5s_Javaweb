<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Tin tức game </title>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    
    <link rel="stylesheet" href="<c:url value='/template/web/lib/bootstrap/css/bootstrap.min.css'/>">
    <!-- End Bootstrap CSS -->

    <!-- Custom Fonts -->
    <link href="<c:url value='/template/web/css/fontawesome-all.min.css'/>" rel="stylesheet" type="text/css">
    <link rel="stylesheet" type="text/css" href="<c:url value='/template/web/lib/chart/all.min.css'/>" />
    <link rel="stylesheet" href="<c:url value='/template/web/lib/slick/css/slick.css'/>">
    <link rel="stylesheet" href="<c:url value='/template/web/lib/slick/css/slick-theme.css'/>">
    <link rel="stylesheet" href="<c:url value='/template/web/css/style.css'/>">

   	<script async defer crossorigin="anonymous" src="https://connect.facebook.net/vi_VN/sdk.js#xfbml=1&version=v10.0&appId=620736825090744&autoLogAppEvents=1" nonce="inrtqwDK"></script>

</head>
<style>
	figure.image img{
	    width: 100%;
    	height: auto;
	}
	p img{
	    width: 100%;
    	height: auto;
	}
</style>
<body>
  
  <%@ include file="./_header.jsp" %>

    <section class="mt-5">
        <div class="container">
            <div class="row">
                 <div class="col-8">
                    <div class="row">
                        <div class="col-12">
                            <h2 class="mb-0">
                                <a href="#" class="text-dark nav-link pl-0">${ article.title }</a>
                            </h2>
                            <div class="mb-3">
                                <span class="ml-0"><i class="fas fa-certificate"></i> ${ article.category.name }</span>
                                <span class="ml-3"><i class="far fa-clock"></i> ${ article.createDate }</span>
                                <span class="ml-3"><i class="fas fa-eye"></i> ${ article.view }</span>
                            </div>
                            <div class="fb-like" data-href="http://localhost:8080/CustomerManager/bai-viet?id=${article.id}" data-width="" data-layout="standard" data-action="like" data-size="small" data-share="true"></div>
                            <strong class="mt-4 mb-4 d-block">${ article.shortDescription }</strong>
                   <%--          <a href="#" class="nav-link p-0">
                                <img src="<c:url value='/template/photo/'/>${article.photo }" alt="${ article.title }" class="img img-fluid" width="100%">
                            </a> --%>
                            <div>
                                ${ article.content }
                            </div>
                        </div>
                    </div>
                   <div id="fb-root"></div>
                   <div class="fb-comments" data-href="http://localhost:8080/CustomerManager/bai-viet?id=${article.id}" data-lazy="true" data-numposts="5"></div>
                	<div class="row mt-5">
                	     <div class="col-12">
                        	<h3 class="text-uppercase">Có thể bạn quan tâm</h3>
                            <ul id="suggestions" class="list-group list-group-flush">
<%--                            	<c:forEach var="suggestion" items="${suggestionList}">--%>
<%--	                                <li class="list-group-item pl-0">--%>
<%--	                                    <div class="row">--%>
<%--	                                        <div class="col-3">--%>
<%--	                                            <a href="${pageContext.request.contextPath}/bai-viet?id=${suggestion.id}" class="nav-link p-0">--%>
<%--	                                                <img src="<c:url value='/template/photo/'/>${suggestion.photo }" alt="#" class="img img-fluid rounded">--%>
<%--	                                            </a>--%>
<%--	                                        </div>--%>
<%--	                                        <div class="col-9">--%>
<%--	                                            <h4 class="mb-0">--%>
<%--	                                                <a href="${pageContext.request.contextPath}/bai-viet?id=${suggestion.id}" class="text-dark nav-link p-0">${suggestion.title}</a>--%>
<%--	                                            </h4>--%>
<%--	                                            <div>--%>
<%--	                                                <span class="ml-3"><i class="fas fa-certificate"></i> ${ suggestion.category.name }</span>--%>
<%--	                                                <span class="ml-3"><i class="far fa-clock"></i> ${ suggestion.createDate }</span>--%>
<%--	                                                <span class="ml-3"><i class="fas fa-eye"></i> ${ suggestion.view }</span>--%>
<%--	                                            </div>--%>
<%--	                                        </div>--%>
<%--	                                    </div>--%>
<%--	                                </li>--%>
<%--         						</c:forEach>--%>
                            </ul>
                        </div>
                	</div>
                </div>
                <div class="col-4">
                    <div class="row">
                        <div class="col-12">
                            <h3>MỚI NÓNG</h3>
                            <ul class="list-group list-group-flush">
                            	<c:forEach items="${findTopByCreateDate}" var="articleByCreateDate">
	                                <li class="list-group-item pl-0">
	                                    <div class="row">
	                                        <div class="col-4">
	                                            <a href="#" class="nav-link p-0">
	                                                <img src="<c:url value='/template/photo/'/>${articleByCreateDate.photo }" alt="#" class="img img-fluid rounded">
	                                            </a>
	                                        </div>
	                                        <div class="col-8">
	                                            <h6 class="mb-0">
	                                                <a href="#" class="text-dark nav-link p-0">${articleByCreateDate.title}</a>
	                                            </h6>
	                                        </div>
	                                    </div>
	                                </li>
                               	</c:forEach>
                            </ul>
                        </div>
                        <div class="col-12 mb-3">
                            <div class="card">
                                <div class="card-body">
                                    <iframe height="720" src="http://www.tygia.com/api.php?cols=1&title=1&chart=1&gold=1&rate=1&expand=1&color=1D4C75&nganhang=VIETCOM&ngoaite=usd,jpy,chf,eur,gbp,aud&fontsize=80&change=0&css=%23gold_tb%7Bdisplay%3Anone%7D" frameborder="0"></iframe>
                                </div>
                            </div>
                        </div>
                        <c:forEach items="${listPromotion}" var="promotion">
	                        <div class="col-12 mb-3">
	                            <div class="card">
	                                <div class="card-body">
	                                    <a href="${promotion.url}">
	                                        <img src="<c:url value='/template/photo/'/>${promotion.photo }" class="img img-fluid" width="100%">
	                                    </a>
	                                </div>
	                            </div>
	                        </div>
                        </c:forEach>                       
                    </div>
                </div>
            </div>
        </div>
    </section>
    <section class="mt-4 mb-4">
        <div class="container">
            <div class="text-center page-info-lite page-info">
                <div class="row">
                    <div class="col-4">
                        <div class="iconbox-wrap">
                            <div class="iconbox">
                                <div class="iconbox-wrap-icon">
                                    <i class="far fa-file-alt"></i>
                                </div>
                                <div class="iconbox-wrap-content">
                                    <h2 class="mb-0">8</h2>
                                    <div class="iconbox-wrap-text">Việc làm</div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-4">
                        <div class="iconbox-wrap">
                            <div class="iconbox">
                                <div class="iconbox-wrap-icon">
                                    <i class="fas fa-users"></i>
                                </div>
                                <div class="iconbox-wrap-content">
                                    <h2 class="mb-0">16</h2>
                                    <div class="iconbox-wrap-text">Người dùng</div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-4">
                        <div class="iconbox-wrap">
                            <div class="iconbox">
                                <div class="iconbox-wrap-icon">
                                    <i class="fas fa-map"></i>
                                </div>
                                <div class="iconbox-wrap-content">
                                    <h2 class="mb-0">91+</h2>
                                    <div class="iconbox-wrap-text">Địa điểm</div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>

  <footer class="bg-dark text-white text-center text-lg-start">
      <!-- Grid container -->
      <div class="container p-4">
          <!--Grid row-->
          <div class="row">
              <!--Grid column-->
              <div class="col-lg-6 col-md-12 mb-4 mb-md-0">
                  <img src="<c:url value='/template/web/img/logo1.png'/>" alt="logo" class="img img-fluid" width="300px">
              </div>
              <!--Grid column-->

              <!--Grid column-->
              <div class="text-left col-lg-6 col-md-12 mb-4 mb-md-0">
                  <h5 class="text-uppercase">Về chúng tôi</h5>

                  <p>
                      Giấy phép số 2411/GP-TTĐT do Sở Thông tin và Truyền thông Hà Nội cấp ngày 31 tháng 07 năm 2021.
                      Chịu trách nhiệm nội dung: 5S <br>
                      Email:bmanh837@gmail.com
                  </p>
              </div>
              <!--Grid column-->
          </div>
          <!--Grid row-->
      </div>
      <!-- Grid container -->

      <!-- Copyright -->
      <div class="text-center p-3" style="background-color: rgba(0, 0, 0, 0.2);">
          Game được thực hiện bởi:
          <a class="" href="https://mdbootstrap.com/">5S Group</a>
      </div>
      <!-- Copyright -->
  </footer>



    <script src="<c:url value='/template/web/lib/jquery/jquery-3.3.1.slim.min.js'/>"></script>
    <script>
        window.jQuery || document.write('<script src="/docs/4.0/assets/js/vendor/jquery-slim.min.js"><\/script>')
    </script>
    <script src="<c:url value='/template/web/lib/popper/popper.min.js'/>"></script>
    <script src="<c:url value='/template/web/lib/bootstrap/js/bootstrap.min.js'/>"></script>
<%--    <script type="text/javascript" src="<c:url value='/template/web/lib/chart/shieldui-all.min.js'/>"></script>--%>
    <script src="<c:url value='/template/web/lib/slick/js/slick.min.js'/>"></script>
    <script src="<c:url value='/template/web/js/chart.js'/>"></script>
    <script src="<c:url value='/template/web/js/style.js'/>"></script>
    <script async src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script type="text/javascript">
        var id = ${article.id};
        var data = {'ID':id};
        $(document).ready(function(){
                $.ajax({
                    async: true,
                    crossDomain: true,
                    url: 'http://192.168.1.5:33/',
                    type: 'POST',
                    contentType:'application/json',
                    headers:{
                        'Content-Type':'application/json',
                        'action':'predict'
                    },
                    data: JSON.stringify(data),
                    dataType: 'json',
                    success: function(data,jqXhr, textStatus){
                        // alert(data)
                        //console.log(data);
                        const data1 = JSON.parse(data);
                        for (var i=0; i<data1.length; i++) {
                            var row = $(
                                '<li class="list-group-item pl-0">'+
                                    '<div class="row">'+
                                        '<div class="col-3">'+
                                            '<a href="/BaoMoi/bai-viet?id='+
                                data1[i].ID+
                                '" class="nav-link p-0">'+
                                                '<img src="/BaoMoi/template/photo/'+
                                data1[i].Photo +
                                '" alt="#" class="img img-fluid rounded">'+
                                            '</a>'+
                                        '</div>'+
                                        '<div class="col-9">'+
                                            '<h4 class="mb-0">'+
                                                '<a href="/BaoMoi/bai-viet?id='+
                                data1[i].ID+
                            '" class="text-dark nav-link p-0">'+
                                data1[i].Title+
                            '</a>'+
                                            '</h4>'+
                                            '<div>'+
                                                '<span class="ml-3"><i class="fas fa-certificate"></i> Chính trị</span>'+
                                                '<span class="ml-3"><i class="far fa-clock"></i> 2021-04-13</span>'+
                                                '<span class="ml-3"><i class="fas fa-eye"></i> 76</span>'+
                                            '</div>'+
                                        '</div>'+
                                    '</div>'+
                                '</li>'
                            );
                            $('#suggestions').append(row);
                        }

                    },
                    error: function( jqXhr, textStatus, errorThrown ){
                        console.log( errorThrown );
                    }
            });
        });
    </script>
</body>

</html>