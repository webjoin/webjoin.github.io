

$(function () {
//		TyFunction.generateHtml();
		
	document.oncontextmenu = function(event) {
		//event.returnValue=false;
		return false;
	}
	document.onselectstart = function(event) {
		//event.returnValue=false;
		return false;
	}
	document.onmousedown = function(event) {
		if(3 == event.which){
			var tag=event.srcElement||event.target
			if(tag.localName != 'i' && tag.localName != 'span'){
				return false;
			}else{
				var className = '';
				if(tag.localName == 'i'){
					className = tag.className ;
				}else{//span
					className = tag.firstChild.className ;
				}
				if(className.indexOf('glyphicon glyphicon-') == -1 ){
					return ;
				}
				
			}
			console.log("left"+event.clientX +"--"+ event.clientY+"--"+event.screenY+'--'+document.body.scrollTop);
			var y = document.documentElement.scrollTop + event.clientY;
			var x = document.documentElement.scrollLeft + event.clientX;
			var css = {
				left:x+'px',
				top:y+'px',
				display:'block'
			};
			$(".menu").css(css);
		}else if(1 == event.which){
			console.log("left"+event.clientX +"--"+ event.clientY+"--"+event.screenY+'--'+document.body.clientHeight);
			//alert("left")
			var css = {
					display:'none'
			};
			$(".menu").css(css);
			
		}
	}
	
	if(isInit){
		TyFunction.queryFiles();
	}
});


//查找工程源目录下文件
TyFunction.initBD = function(){
	var src_addr = $('#src_addr').val();
	var ip = $('#ip').val();
	var username = $("#username").val();
	var passcode = $("#passcode").val();
	var SQLDialect = $(':checked[name=radio]').val();
	var driver = $('#driver').val();
	var sid = $('#sid').val();
	$.ajax({
	   type: "GET",
	   dataType:'json',
	   url: this.cxt+"/index.html",
	   data: {
		   op:'initDB',
		   src_addr:src_addr,
		   ip:ip,
		   username:username,
		   passcode:passcode,
		   SQLDialect:SQLDialect,
		   driver:driver,
		   sid:sid
	   },
	   success: function(rs){
	     //alert( "Data Saved: " + msg );
		  // eval('var rs = '+msg);
		   if(rs.success || rs.success == 'true' ){ //DB is ready
			   TyFunction.queryFiles();
//			   $("#error_tips").hide('fast')
//			   TyFunction.data = rs.msg;
//			   TyFunction.generateHtml();
//			   TyFunction.queryInit();
		   }else{
			   $("#error_tips").show('fast').append(rs.msg)
		   }
	   },
	   error:function (XMLHttpRequest, textStatus, errorThrown) {
		   console.log(textStatus+'---');
	   }
	});
};

TyFunction.queryFiles = function(){
	$.ajax({
		   type: "GET",
		   dataType:'json',
		   url: this.cxt+"/index.html",
		   data: {
			   op:'loadFiles'
		   },
		   success: function(rs){
		     //alert( "Data Saved: " + msg );
//			   eval('var rs = '+msg);
			   if(rs.success || rs.success == 'true'){ //DB is ready
				   TyFunction.data = rs.msg;
				   TyFunction.generateHtml();
				   TyFunction.queryInit();
				   $("#error_tips").hide('fast')
				   $("header").hide('fast')
			   }else{
				   $("#error_tips").show('fast').append(rs.msg)
			   }
		   }
		});
}

TyFunction.queryInit = function(){
	$('.tree li:has(ul)').addClass('parent_li').find(' > span').attr('title', 'Collapse this branch');
    $('.tree li.parent_li > span').on('click', function (e) {
        var children = $(this).parent('li.parent_li').find(' > ul > li');
        if (children.is(":visible")) {
            children.hide('fast');
            $(this).attr('title', 'Expand this branch').find(' > i').addClass('glyphicon').addClass('glyphicon-plus-sign').removeClass('glyphicon-minus-sign');
        } else {
            children.show('fast');
            $(this).attr('title', 'Collapse this branch').find(' > i').addClass('glyphicon').addClass('glyphicon-minus-sign').removeClass('glyphicon-plus-sign');
        }
        //e.stopPropagation();
    });
    
//    $('.path-class').click(function(){
//    	
//    	//alert($(this).attr("path"));
//    	$('.path-class').css("background-color","#EEE");
//    	$(this).css("background-color","#449D44");
//    });
    $('li > span').click(function(){
    	//alert($(this).attr("path"));
    	$('li > span').css("background-color","#EEE");
    	$(this).css("background-color","#449D44");
    });
    $('.tree li.parent_li > span').click();
    $($('.tree li.parent_li > span').get(0)).click();
    $($('.tree li.parent_li > span').get(1)).click();
}
TyFunction.data = {"subDirs":[{"subDirs":[],"name":"/Users/Elijah/Learning/git-repo/Jerusalem/src/main/java/com/viewt/.DS_Store"},{"subDirs":[{"subDirs":[],"name":"/Users/Elijah/Learning/git-repo/Jerusalem/src/main/java/com/viewt/btrace/Helloworld.java"},{"subDirs":[],"name":"/Users/Elijah/Learning/git-repo/Jerusalem/src/main/java/com/viewt/btrace/TraceHelloWorld.java"}],"name":"/Users/Elijah/Learning/git-repo/Jerusalem/src/main/java/com/viewt/btrace"},{"subDirs":[{"subDirs":[],"name":"/Users/Elijah/Learning/git-repo/Jerusalem/src/main/java/com/viewt/container/ListFeatures.java"}],"name":"/Users/Elijah/Learning/git-repo/Jerusalem/src/main/java/com/viewt/container"},{"subDirs":[{"subDirs":[],"name":"/Users/Elijah/Learning/git-repo/Jerusalem/src/main/java/com/viewt/file/FileUtils.java"}],"name":"/Users/Elijah/Learning/git-repo/Jerusalem/src/main/java/com/viewt/file"},{"subDirs":[{"subDirs":[{"subDirs":[],"name":"/Users/Elijah/Learning/git-repo/Jerusalem/src/main/java/com/viewt/gb/file/FileBean.java"}],"name":"/Users/Elijah/Learning/git-repo/Jerusalem/src/main/java/com/viewt/gb/file"},{"subDirs":[{"subDirs":[],"name":"/Users/Elijah/Learning/git-repo/Jerusalem/src/main/java/com/viewt/gb/servlet/OperateResource.java"}],"name":"/Users/Elijah/Learning/git-repo/Jerusalem/src/main/java/com/viewt/gb/servlet"}],"name":"/Users/Elijah/Learning/git-repo/Jerusalem/src/main/java/com/viewt/gb"},{"subDirs":[{"subDirs":[],"name":"/Users/Elijah/Learning/git-repo/Jerusalem/src/main/java/com/viewt/hazelcast/hazelcast.xml"},{"subDirs":[],"name":"/Users/Elijah/Learning/git-repo/Jerusalem/src/main/java/com/viewt/hazelcast/Hazelcast1Client.java"},{"subDirs":[],"name":"/Users/Elijah/Learning/git-repo/Jerusalem/src/main/java/com/viewt/hazelcast/Hazelcast1Server.java"}],"name":"/Users/Elijah/Learning/git-repo/Jerusalem/src/main/java/com/viewt/hazelcast"},{"subDirs":[{"subDirs":[{"subDirs":[],"name":"/Users/Elijah/Learning/git-repo/Jerusalem/src/main/java/com/viewt/kafka/another/ConSumerTest.java"},{"subDirs":[],"name":"/Users/Elijah/Learning/git-repo/Jerusalem/src/main/java/com/viewt/kafka/another/ProducerTest.java"}],"name":"/Users/Elijah/Learning/git-repo/Jerusalem/src/main/java/com/viewt/kafka/another"},{"subDirs":[],"name":"/Users/Elijah/Learning/git-repo/Jerusalem/src/main/java/com/viewt/kafka/ConsumerDemo.java"},{"subDirs":[],"name":"/Users/Elijah/Learning/git-repo/Jerusalem/src/main/java/com/viewt/kafka/KafkaConsumer.java"},{"subDirs":[],"name":"/Users/Elijah/Learning/git-repo/Jerusalem/src/main/java/com/viewt/kafka/KafkaProducer.java"},{"subDirs":[],"name":"/Users/Elijah/Learning/git-repo/Jerusalem/src/main/java/com/viewt/kafka/PartitionerDemo.java"},{"subDirs":[],"name":"/Users/Elijah/Learning/git-repo/Jerusalem/src/main/java/com/viewt/kafka/ProducerDemo.java"}],"name":"/Users/Elijah/Learning/git-repo/Jerusalem/src/main/java/com/viewt/kafka"},{"subDirs":[{"subDirs":[],"name":"/Users/Elijah/Learning/git-repo/Jerusalem/src/main/java/com/viewt/log/LogUtil.java"}],"name":"/Users/Elijah/Learning/git-repo/Jerusalem/src/main/java/com/viewt/log"},{"subDirs":[{"subDirs":[{"subDirs":[],"name":"/Users/Elijah/Learning/git-repo/Jerusalem/src/main/java/com/viewt/netty/bio/TimeServer.java"}],"name":"/Users/Elijah/Learning/git-repo/Jerusalem/src/main/java/com/viewt/netty/bio"}],"name":"/Users/Elijah/Learning/git-repo/Jerusalem/src/main/java/com/viewt/netty"},{"subDirs":[{"subDirs":[],"name":"/Users/Elijah/Learning/git-repo/Jerusalem/src/main/java/com/viewt/ping/Ping.java"}],"name":"/Users/Elijah/Learning/git-repo/Jerusalem/src/main/java/com/viewt/ping"},{"subDirs":[{"subDirs":[],"name":"/Users/Elijah/Learning/git-repo/Jerusalem/src/main/java/com/viewt/redis/RedisCluster.java"},{"subDirs":[],"name":"/Users/Elijah/Learning/git-repo/Jerusalem/src/main/java/com/viewt/redis/RedisHelloworld.java"}],"name":"/Users/Elijah/Learning/git-repo/Jerusalem/src/main/java/com/viewt/redis"},{"subDirs":[{"subDirs":[],"name":"/Users/Elijah/Learning/git-repo/Jerusalem/src/main/java/com/viewt/string/StringIndexPractice.java"}],"name":"/Users/Elijah/Learning/git-repo/Jerusalem/src/main/java/com/viewt/string"},{"subDirs":[{"subDirs":[],"name":"/Users/Elijah/Learning/git-repo/Jerusalem/src/main/java/com/viewt/websocket/HelloWebSocket.java"},{"subDirs":[],"name":"/Users/Elijah/Learning/git-repo/Jerusalem/src/main/java/com/viewt/websocket/MucWebSocket.java"}],"name":"/Users/Elijah/Learning/git-repo/Jerusalem/src/main/java/com/viewt/websocket"},{"subDirs":[{"subDirs":[{"subDirs":[],"name":"/Users/Elijah/Learning/git-repo/Jerusalem/src/main/java/com/viewt/xmpp/chat/P2pChat.java"},{"subDirs":[],"name":"/Users/Elijah/Learning/git-repo/Jerusalem/src/main/java/com/viewt/xmpp/chat/P2pChatB.java"}],"name":"/Users/Elijah/Learning/git-repo/Jerusalem/src/main/java/com/viewt/xmpp/chat"},{"subDirs":[{"subDirs":[],"name":"/Users/Elijah/Learning/git-repo/Jerusalem/src/main/java/com/viewt/xmpp/form/Bootstrap.java"}],"name":"/Users/Elijah/Learning/git-repo/Jerusalem/src/main/java/com/viewt/xmpp/form"},{"subDirs":[{"subDirs":[],"name":"/Users/Elijah/Learning/git-repo/Jerusalem/src/main/java/com/viewt/xmpp/muc/MucChatUserA.java"},{"subDirs":[],"name":"/Users/Elijah/Learning/git-repo/Jerusalem/src/main/java/com/viewt/xmpp/muc/UccCustomer.java"}],"name":"/Users/Elijah/Learning/git-repo/Jerusalem/src/main/java/com/viewt/xmpp/muc"},{"subDirs":[{"subDirs":[],"name":"/Users/Elijah/Learning/git-repo/Jerusalem/src/main/java/com/viewt/xmpp/test/FirstTestLauncher.java"},{"subDirs":[],"name":"/Users/Elijah/Learning/git-repo/Jerusalem/src/main/java/com/viewt/xmpp/test/JabberSmackAPI.java"},{"subDirs":[],"name":"/Users/Elijah/Learning/git-repo/Jerusalem/src/main/java/com/viewt/xmpp/test/WorkgroupQueue.java"}],"name":"/Users/Elijah/Learning/git-repo/Jerusalem/src/main/java/com/viewt/xmpp/test"}],"name":"/Users/Elijah/Learning/git-repo/Jerusalem/src/main/java/com/viewt/xmpp"}],"name":"/Users/Elijah/Learning/git-repo/Jerusalem/src/main/java/com/viewt"};
TyFunction.generateHtml = function(){
	//$(".tree").children('ul').children('li').children('ul').html()
	var data = this.data;
	
	var leaf = '<li><span class="path-class" path="${path}" ><i class="glyphicon glyphicon-leaf" ></i>${name}</span></li>';
//	var child = '<li>'
//				+'<span><i class="glyphicon glyphicon-minus-sign"></i>${name}</span>'
//				+	'<a href="">Goes somewhere</a>';
//				+	'<ul><li><span><i class="glyphicon glyphicon-leaf"></i>${childname}</span><a href="">Goes somewhere</a></li>';
//				+'</ul>';
//			+'</li>';
	
	//$(".tree").children('ul').children('li').children('ul').html(leaf+child);
	var arr = [];
	function parseJson(data){
		var nameVal = data.name.substring(data.name.lastIndexOf('/')+1);
		var len = data.subDirs.length ;
		if(len > 0){
			//存在子节点
			arr.push("<li>");
				arr.push('<span><i class="glyphicon glyphicon-minus-sign"></i>${name}</span>'.replace('${name}',nameVal));
				//arr.push('<a href="">Goes somewhere</a>');
				arr.push('<ul>');
			for(var i = 0 ; i < len ; i++){
				parseJson(data.subDirs[i]);
			}
			arr.push('</ul>');
			arr.push('</li>');
			
		}else{ //没有子节点
			var leafVal = leaf.replace("${name}",nameVal).replace("${path}",data.name);
			arr.push(leafVal);	
		}
		
	}
	parseJson(this.data);
	//console.log(arr.join(''))
	$(".tree").children('ul').children('li').children('ul').html(arr.join(''));
}
