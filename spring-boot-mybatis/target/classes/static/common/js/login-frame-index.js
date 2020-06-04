var $,tab,skyconsWeather;
var ctx = $("#ctx").val();
var username = $(".userName").html();
;!function(){
	
	var form = layui.form,
		layer = parent.layer === undefined ? layui.layer : parent.layer,
		element = layui.element;
		$ = layui.jquery;
		tab = layui.bodyTab({
			openTabNum : "7",  //最大可打开窗口数量
		});

	//更换皮肤
	function skins(){
		var skin = window.sessionStorage.getItem("skin");
		if(skin){  //如果更换过皮肤
			if(window.sessionStorage.getItem("skinValue") != "自定义"){
				$("body").addClass(window.sessionStorage.getItem("skin"));
			}else{
				$(".layui-layout-admin .layui-header").css("background-color",skin.split(',')[0]);
				$(".layui-bg-black").css("background-color",skin.split(',')[1]);
				$(".hideMenu").css("background-color",skin.split(',')[2]);
			}
		}
	}
	skins();
	$(".changeSkin").click(function(){
		var index = layer.open({
					title : "更换皮肤",
					area : ["310px","280px"],
					type : "1",
					offset: '250px',
					content : '<div class="skins_box">'+
								'<form class="layui-form">'+
									'<div class="layui-form-item">'+
										'<input type="radio" name="skin" value="默认" title="默认" lay-filter="default" checked="">'+
										'<input type="radio" name="skin" value="橙色" title="橙色" lay-filter="orange">'+
										'<input type="radio" name="skin" value="蓝色" title="蓝色" lay-filter="blue">'+
										'<input type="radio" name="skin" value="自定义" title="自定义" lay-filter="custom">'+
										'<div class="skinCustom">'+
											'<input type="text" class="layui-input topColor" name="topSkin" placeholder="顶部颜色" />'+
											'<input type="text" class="layui-input leftColor" name="leftSkin" placeholder="左侧颜色" />'+
											'<input type="text" class="layui-input menuColor" name="btnSkin" placeholder="顶部菜单按钮" />'+
										'</div>'+
									'</div>'+
									'<div class="layui-form-item skinBtn">'+
										'<a href="javascript:;" class="layui-btn layui-btn-small layui-btn-normal" lay-submit="" lay-filter="changeSkin">确定更换</a>'+
										'<a href="javascript:;" class="layui-btn layui-btn-small layui-btn-primary" lay-submit="" lay-filter="noChangeSkin">我再想想</a>'+
									'</div>'+
								'</form>'+
							'</div>',
					success : function(index, layero){
						var skin = window.sessionStorage.getItem("skin");
						if(window.sessionStorage.getItem("skinValue")){
							$(".skins_box input[value="+window.sessionStorage.getItem("skinValue")+"]").attr("checked","checked");
						};
						if($(".skins_box input[value=自定义]").attr("checked")){
							$(".skinCustom").css("visibility","inherit");
							$(".topColor").val(skin.split(',')[0]);
							$(".leftColor").val(skin.split(',')[1]);
							$(".menuColor").val(skin.split(',')[2]);
						};
						form.render();
						$(".skins_box").removeClass("layui-hide");
						$(".skins_box .layui-form-radio").on("click",function(){
							var skinColor;
							if($(this).find("span").text() == "橙色"){
								skinColor = "orange";
							}else if($(this).find("span").text() == "蓝色"){
								skinColor = "blue";
							}else if($(this).find("span").text() == "默认"){
								skinColor = "";
							}
							if($(this).find("span").text() != "自定义"){
								$(".topColor,.leftColor,.menuColor").val('');
								$("body").removeAttr("class").addClass("main_body "+skinColor+"");
								$(".skinCustom").removeAttr("style");
								$(".layui-bg-black,.hideMenu,.layui-layout-admin .layui-header").removeAttr("style");
							}else{
								$(".skinCustom").css("visibility","inherit");
							}
						})
						var skinStr,skinColor;
						$(".topColor").blur(function(){
							$(".layui-layout-admin .layui-header").css("background-color",$(this).val());
						})
						$(".leftColor").blur(function(){
							$(".layui-bg-black").css("background-color",$(this).val());
						})
						$(".menuColor").blur(function(){
							$(".hideMenu").css("background-color",$(this).val());
						})
		
						form.on("submit(changeSkin)",function(data){
							if(data.field.skin != "自定义"){
								if(data.field.skin == "橙色"){
									skinColor = "orange";
								}else if(data.field.skin == "蓝色"){
									skinColor = "blue";
								}else if(data.field.skin == "默认"){
									skinColor = "";
								}
								window.sessionStorage.setItem("skin",skinColor);
							}else{
								skinStr = $(".topColor").val()+','+$(".leftColor").val()+','+$(".menuColor").val();
								window.sessionStorage.setItem("skin",skinStr);
								$("body").removeAttr("class").addClass("main_body");
							}
							window.sessionStorage.setItem("skinValue",data.field.skin);
							layer.closeAll("page");
						});
						form.on("submit(noChangeSkin)",function(){
							$("body").removeAttr("class").addClass("main_body "+window.sessionStorage.getItem("skin")+"");
							$(".layui-bg-black,.hideMenu,.layui-layout-admin .layui-header").removeAttr("style");
							skins();
							layer.closeAll("page");
						});
					},
					cancel : function(){
						$("body").removeAttr("class").addClass("main_body "+window.sessionStorage.getItem("skin")+"");
						$(".layui-bg-black,.hideMenu,.layui-layout-admin .layui-header").removeAttr("style");
						skins();
					}
				});
	})

	//退出
	$(".signOut").click(function(){
		window.sessionStorage.removeItem("menu");
		menu = [];
		window.sessionStorage.removeItem("curmenu");
	})

	//隐藏左侧导航
	$(".hideMenu").click(function(){
		$(".layui-layout-admin").toggleClass("showMenu");
		//渲染顶部窗口
		tab.tabMove();
	})
	//渲染左侧菜单
	tab.render();
	//锁屏
	function lockPage(){
		var index	=   layer.open({
						title : false,
						type : 1,
						offset: '250px',
						area : ["360px","210px"],
						content : '	<div class="admin-header-lock" id="lock-box">'+
						'<div class="admin-header-lock-img"><img src="/common/images/face.jpg"/></div>'+
										'<div class="admin-header-lock-name" id="lockUserName">'+username+'</div>'+
										'<div class="input_btn">'+
											'<input type="password" class="admin-header-lock-input layui-input" autocomplete="off" placeholder="请输入密码解锁.." name="password" id="lockPwd" />'+
											'<button class="layui-btn" id="unlock">解锁</button>'+
										'</div>'+
										'<p>请输入“登录密码”，否则不会解锁成功哦！！！</p>'+
									'</div>',
						closeBtn : 0,
						shade : 0.9
					});
					$(".admin-header-lock-input").focus();
	}
	$(".lockcms").on("click",function(){
		window.sessionStorage.setItem("lockcms",true);
		lockPage();
	})
	// 判断是否显示锁屏
	if(window.sessionStorage.getItem("lockcms") == "true"){
		lockPage();
	}
	// 解锁
	$("body").on("click","#unlock",function(){
		var index;
		if($(this).siblings(".admin-header-lock-input").val() == ''){
			index = layer.msg("请输入解锁密码！");
			$(this).siblings(".admin-header-lock-input").focus();
			layer.style(index, {
	   			width: 'auto',
	   			top:'220px',
	   			});
		}else{
			$.post("/login/validatePassWord.do", {"password":$("#lockPwd").val()}, function(json) {
    			if(json=='error'){
    				index = layer.msg("服务器开小差了,请稍后重试!");
    				$(this).siblings(".admin-header-lock-input").val('').focus();
    			}else  if(json!='success'){
    				index = layer.msg("密码错误，请重新输入！");
    				$(this).siblings(".admin-header-lock-input").val('').focus();
    			}else if(json=='success'){
    				window.sessionStorage.setItem("lockcms",false);
    				$(this).siblings(".admin-header-lock-input").val('');
    				layer.closeAll("page");
    			}
    			layer.style(index, {
    	   			width: 'auto',
    	   			top:'320px',
    	   			});
			});
		}
		
	});
	$(document).on('keydown', function(event) {
		if(event.keyCode == 13) {
			$("#unlock").click();
		}
	});

	//手机设备的简单适配
	var treeMobile = $('.site-tree-mobile'),
		shadeMobile = $('.site-mobile-shade')

	treeMobile.on('click', function(){
		$('body').addClass('site-mobile');
	});

	shadeMobile.on('click', function(){
		$('body').removeClass('site-mobile');
	});

	// 添加新窗口
	$("body").on("click",".layui-nav .layui-nav-item a",function(){
		//如果不存在子级
		if($(this).siblings().length == 0){
			addTab($(this));
			$('body').removeClass('site-mobile');  //移动端点击菜单关闭菜单层
		}
		$(this).parent("li").siblings().removeClass("layui-nav-itemed");
		//打开新网页  删掉新网页那个图标栏目
		$("#top_tabs").find("i").removeClass("icon-zhanghu")
	})

	//公告层
	function showNotice(){
			layer.open({
		        type: 1,
		        title: ['系统公告(必看)',"color:orange"],
		        closeBtn: false,
		        area : 'auto',
		        scrollbar :false,
		        offset: '90px',
		        shade: 0.8,
		        id: '系统公告',
		        anim: 1,
		        isOutAnim :true,
		        btn: '我知道了',
		        fixed :true,
		        move :false,
		        content: '<div style="padding:15px 20px; text-align:justify; line-height: 22px; text-indent:2em;border-bottom:1px solid #e2e2e2;"><p>大家好,平台代码页面做了轻微调整:<br/>(1)融合修改密码功能到logincontroller中,删除原先修改密码的单独模块;<br/>(2)调整了主题样式使用不正常的bug;<br/>(3)修改了弹窗高分辨率下不居中问题;<br/>(4)修改了锁屏密码与用户密码不一致问题;<br/>(5)加入了异常页面:/login/error.jsp;<font style="color:red"><br/>(6)页面的绝对路径在数据库中现在只要改为/xx/xx.do就可以,不用再加ctx,但是要注意的是:大家必须把所有sysrole表中的url全加上路径,否则点击默认跳转/sinobm,也就是登录界面;<br/>(7)希望大家把各自模块的js文件全放入common/js文件夹下,js名字可以改成模块名-xx.js;<br/>css文件全放入common/css文件夹下,名称为模块名-xx.css;<br/>image文件全放入common/images文件夹下以方便管理及代码的美观性。</font><br/><span style="padding:15px 20px;">如有问题请及时沟通!</span></p></div>',
		          btnAlign: 'c', //按钮居中
		        success: function(layero){
					var btn = layero.find('.layui-layer-btn');
					
					$("#系统公告").css("height","auto");
					btn.on("click",function(){
						window.sessionStorage.setItem("showNotice","true");
					})
					if($(window).width() > 432){  //如果页面宽度不足以显示顶部“系统公告”按钮，则不提示
						btn.on("click",function(){
							layer.tips('系统公告躲在了这里', '#showNotice', {
								tips: 4
							});
						})
					}
		        }
		    });
	}
	//判断是否处于锁屏状态(如果关闭以后则未关闭浏览器之前不再显示)
	if(window.sessionStorage.getItem("lockcms") != "true" && window.sessionStorage.getItem("showNotice") != "true"){
		showNotice();
	}
	$(".showNotice").on("click",function(){
		showNotice();
	})

	//刷新后还原打开的窗口
	if(window.sessionStorage.getItem("menu") != null){
		menu = JSON.parse(window.sessionStorage.getItem("menu"));
		curmenu = window.sessionStorage.getItem("curmenu");
		var openTitle = '';
		for(var i=0;i<menu.length;i++){
			openTitle = '';
			if(menu[i].icon){
				if(menu[i].icon.split("-")[0] == 'icon'){
					//openTitle += '<i class="iconfont '+menu[i].icon+'"></i>';
				}else{
					//openTitle += '<i class="layui-icon">'+menu[i].icon+'</i>';
				}
			}
			openTitle += '<cite>'+menu[i].title+'</cite>';
			openTitle += '<i class="layui-icon layui-unselect layui-tab-close" data-id="'+menu[i].layId+'">&#x1006;</i>';
			element.tabAdd("bodyTab",{
				title : openTitle,
		        content :"<iframe src='"+menu[i].href+"' data-id='"+menu[i].layId+"'></frame>",
		        id : menu[i].layId
			})
			//定位到刷新前的窗口
			if(curmenu != "undefined"){
				if(curmenu == '' || curmenu == "null"){  //定位到后台首页
					element.tabChange("bodyTab",'');
				}else if(JSON.parse(curmenu).title == menu[i].title){  //定位到刷新前的页面
					element.tabChange("bodyTab",menu[i].layId);
				}
			}else{
				element.tabChange("bodyTab",menu[menu.length-1].layId);
			}
		}
		//渲染顶部窗口
		tab.tabMove();
	}

	//刷新当前
	$(".refresh").on("click",function(){  //此处添加禁止连续点击刷新一是为了降低服务器压力，另外一个就是为了防止超快点击造成chrome本身的一些js文件的报错(不过貌似这个问题还是存在，不过概率小了很多)
		if($(this).hasClass("refreshThis")){
			$(this).removeClass("refreshThis");
			$(".clildFrame .layui-tab-item.layui-show").find("iframe")[0].contentWindow.location.reload(true);
			setTimeout(function(){
				$(".refresh").addClass("refreshThis");
			},2000)
		}else{
			layer.msg("您点击的速度超过了服务器的响应速度，还是等两秒再刷新吧！");
		}
	})

	//关闭其他
	$(".closePageOther").on("click",function(){
		if($("#top_tabs li").length>2 && $("#top_tabs li.layui-this cite").text()!="后台首页"){
			var menu = JSON.parse(window.sessionStorage.getItem("menu"));
			$("#top_tabs li").each(function(){
				if($(this).attr("lay-id") != '' && !$(this).hasClass("layui-this")){
					element.tabDelete("bodyTab",$(this).attr("lay-id")).init();
					//此处将当前窗口重新获取放入session，避免一个个删除来回循环造成的不必要工作量
					for(var i=0;i<menu.length;i++){
						if($("#top_tabs li.layui-this cite").text() == menu[i].title){
							menu.splice(0,menu.length,menu[i]);
							window.sessionStorage.setItem("menu",JSON.stringify(menu));
						}
					}
				}
			})
		}else if($("#top_tabs li.layui-this cite").text()=="后台首页" && $("#top_tabs li").length>1){
			$("#top_tabs li").each(function(){
				if($(this).attr("lay-id") != '' && !$(this).hasClass("layui-this")){
					element.tabDelete("bodyTab",$(this).attr("lay-id")).init();
					window.sessionStorage.removeItem("menu");
					menu = [];
					window.sessionStorage.removeItem("curmenu");
				}
			})
		}else{
			layer.msg("没有可以关闭的窗口了@_@");
		}
		//渲染顶部窗口
		tab.tabMove();
	})
	//关闭全部
	$(".closePageAll").on("click",function(){
		if($("#top_tabs li").length > 1){
			$("#top_tabs li").each(function(){
				if($(this).attr("lay-id") != ''){
					element.tabDelete("bodyTab",$(this).attr("lay-id")).init();
					window.sessionStorage.removeItem("menu");
					menu = [];
					window.sessionStorage.removeItem("curmenu");
				}
			})
		}else{
			layer.msg("没有可以关闭的窗口了@_@");
		}
		//渲染顶部窗口
		tab.tabMove();
	})
}();

//打开新窗口
function addTab(_this){
	tab.tabAdd(_this);
}
