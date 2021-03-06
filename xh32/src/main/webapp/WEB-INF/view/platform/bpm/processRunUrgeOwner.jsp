<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
<%@include file="/commons/include/get.jsp"%>
<title><spr:message code="processRun.urgeOwner.title"/></title>

<script type="text/javascript" src="${ctx}/js/ckeditor/ckeditor.js"></script>
<script type="text/javascript" src="${ctx}/js/ckeditor/ckeditor_sysTemp.js"></script>
<script type="text/javascript" src="${ctx}/js/hotent/CustomValid.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery/jquery.form.js"></script>

<style type="text/css">
	label{
		cursor:pointer;
	}
</style>
<script type="text/javascript">
	$(function(){
		
		function showResponse(responseText)  { 
			var obj=new com.hotent.form.ResultMessage(responseText);
			if(obj.isSuccess()){//成功
				$.ligerDialog.success(obj.getMessage(),$lang.tip.msg,function(){
					window.returnValue="TRUE";   
					window.close();
				});					
		    }else{//失败
		    	$.ligerDialog.err($lang.tip.msg,$lang.save.failure,obj.getMessage());
		    }
		};
		
		var options={};
		if(showResponse){
			options.success=showResponse;
		}
	
		$('#urgeForm').ajaxForm({success:showResponse}); 
		
		$("a.urge").click(function() {
			$('#urgeForm').submit();
		});
	
	});
</script>
</head>
<body>
	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label"><spr:message code="processRun.urgeOwner.title"/></span>
			</div>
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group">
						<a class="link urge"><span></span><spr:message code="menu.button.urge"/></a>
					</div>
					<div class="l-bar-separator"></div>
					<div class="group">
						<a class="link del" onclick="window.close()"><span></span><spr:message code="menu.button.close"/></a>
					</div>
				</div>
			</div>			
		</div>
		<div class="panel-body">
			<form  id="urgeForm" method="post" action="urgeSubmit.ht" >
				<input type="hidden" name="actInstId" value="${actInstId}" />
				<input type="hidden" name="processSubject" value="${processSubject}">
				<table cellpadding="1" cellspacing="1" class="table-detail">
					<tr>
						<th width="120"><spr:message code="message.reminder"/>:</th>
						<td>
							<label><input type="checkbox"  checked="checked" name="messgeType" value="3" /><spr:message code="message.inner"/></label>
							<label><input type="checkbox" id="mail" name="messgeType" checked="checked"  value="1" /><spr:message code="message.mail"/></label>
							<label><input type="checkbox" id="short" name="messgeType"  value="2" /><spr:message code="message.sms"/></label>
						</td>
					</tr>
					<tr>
						<th><spr:message code="message.copyEdit.title"/>:</th>
						<td>
							<textarea rows="4" cols="50" style="width:300px" id="opinion" name="opinion" validate="{required:true,maxLength:1000}" maxLength="1000"></textarea>
						</td>
					</tr>
				</table>
			</form>
		</div>
	</div>
</body>
</html>


