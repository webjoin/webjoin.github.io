<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>GB tool by Elijah</title>
<!-- Bootstrap -->
    <link href="${pageContext.request.contextPath }/lib/bootstrap-3.3.4/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath }/lib/bootstrap-3.3.4/prettify.css"  rel="stylesheet" >
    <link href="${pageContext.request.contextPath }/lib/bootstrap-3.3.4/dist/css/bootstrap-multiselect.css" rel="stylesheet">
	<link href="${pageContext.request.contextPath }/lib/tree.css" rel="stylesheet">
	
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="http://cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="http://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
  </head>
  <body>

  <!-- <button type="button" class="btn btn-lg btn-success">Success</button> -->
	<br>
	<header style='display: ${isInit?"none":"blank"}'>
		<div class="input-group input-group-lg">
		  <span class="input-group-addon" id="sizing-addon3">请输入工程src目录</span>
		  <input type="text" class="form-control" placeholder="请输入工程src目录" aria-describedby="sizing-addon3" id='src_addr' value="/Users/Elijah/Learning/git-repo${pageContext.request.contextPath }/src/main/java/com/viewt">
		</div>
		<div class="input-group input-group-lg">
		  <span class="input-group-addon" id="sizing-addon3">数据源配置</span>
		  <input type="text" class="form-control" placeholder="IP" aria-describedby="sizing-addon3" id='ip' value = '127.0.0.1'>
		  <input type="text" class="form-control" placeholder="用户名" aria-describedby="sizing-addon3" id='username' value = 'root'>
		  <input type="text" class="form-control" placeholder="密码" aria-describedby="sizing-addon3" id='passcode' value = '123456'>
		  	<div class="row">
			  <div class="col-lg-6">
			    <div class="input-group">
			      <span class="input-group-addon">
			        <input type="radio" aria-label="..." name = "radio" value="oracle">&nbsp;&nbsp;Oracle
			      </span>
			      <span class="input-group-addon">
			        <input type="radio" aria-label="..." name = "radio" value="mysql" checked="checked">&nbsp;&nbsp;Mysql
			      </span>
			    </div><!-- /input-group -->
			  </div><!-- /.col-lg-6 -->
			</div><!-- /.row -->
			<input type="text" class="form-control" placeholder="Driver" aria-describedby="sizing-addon3" id='driver' value='com.mysql.jdbc.Driver'>
			<input type="text" class="form-control" placeholder="SID" aria-describedby="sizing-addon3" id = 'sid' value='openfire'>
			<button type="button" class="btn btn-lg btn-success" onclick="TyFunction.initBD()">提交</button>
		  	<div class="alert alert-danger" role="alert" id="error_tips" style="display: none;">
			  <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
			  <strong>Warning!</strong>
			</div>
		</div>
	</header>
	
	
	<!-- <script src='http://runjs.cn/gist/xtte94ls/all'></script> -->
	<br>
	
	<br>
	
	
	<div>
		<div class="tree well" >
		    <ul>
		        <li>
		            <span><i class="glyphicon glyphicon-folder-open"></i> Root</span> <a href=""></a>
		            <ul><!-- 存放子节点 -->
		            </ul>
		        </li>
		    </ul>
		</div>
		<div class='left-well'>
		<div class="btn-group">
			<select id="multiselect-boot" multiple="multiple">
				<c:forEach var="tb" items="${tables}" varStatus="status">
				 	<%-- ${varStatus.index}: --%>
				 	<option value="${ tb}">${ tb}:${status.index}</option>
				</c:forEach>
			    
			</select>
			<button id="example-destroy-button" class="btn btn-danger">${i18n.generate}</button>
			</div>
			
		</div>
   </div>

	<div class="menu">
	    <ul>
	        <li><a href="#">新建实体类</a></li>
	        <li><a href="#">删除</a></li>
	        <li><a href="#">查看</a></li>
	    </ul>
	</div>
	<!-- <span class="glyphicon glyphicon-search" aria-hidden="true"></span> -->
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="${pageContext.request.contextPath }/lib/jquery-1.11.2.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="${pageContext.request.contextPath }/lib/bootstrap-3.3.4/dist/js/bootstrap.min.js"></script>
    
    <script src="${pageContext.request.contextPath }/lib/bootstrap-3.3.4/prettify.js" ></script>
    <script src="${pageContext.request.contextPath }/lib/bootstrap-3.3.4/dist/js/bootstrap-multiselect.js" ></script>
	

    <script type="text/javascript">
    	var isInit = ${isInit};
    	var TyFunction = {
    			cxt : "${pageContext.request.contextPath }"
    	};
    	
    </script>
    <script type="text/javascript" src="${pageContext.request.contextPath }/lib/tyfunction.js"></script>
    <script type="text/javascript">
                $(document).ready(function() {
                    $('#multiselect-boot').multiselect({
                        enableFiltering: true,
                        filterBehavior: 'value'
                    });
                     
                });
     </script>
  </body>
</html>