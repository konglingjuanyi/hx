<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<%@include file="/commons/include/get.jsp" %>
<title><spr:message code="sysLogSwitch"/><spr:message code="title.manageList"/></title>
<f:js pre="js/lang/view/platform/system" ></f:js>
	<style type="text/css">
		.inputChange{
			background-color: #BBAAAA!important;
		}
		
		#logSwitchManagement td,#logSwitchManagement th{
				cursor: pointer;
				border-bottom: 1px solid #CCCCCC;
				border-left: 1px solid #CCCCCC;
				overflow: hidden;
				padding: 7px;
		}
		
		[name=logSwitchTr]:hover{
				background: #D4D4D4;
		}
		
		.edit-label{
			margin: 5px;
			padding: 5px;
		}
	</style>
	<script type="text/javascript">
		$(function(){
			$("[name=logSwitchTr]").click(function(){
				var _this = $(this);
				var isSelf =  _this.next().hasClass("logSwitchEditTr");
				saveData();
				if(!isSelf){
					var tr = $("#logSwitchEditTr").clone().removeAttr("id");
					var value = $("[name=status]",_this).val();
					var memo = $("[name=memo]",_this).text();
					$("[name=status]:[value="+value+"]",tr).attr("checked","checked");
					$("[name=memo]",tr).val(memo);
					tr.insertAfter(_this);
				}
			});
		});
		function saveData(){
			$("#logSwitchManagement tr.logSwitchEditTr").each(function(){
				var _this = $(this);
				var prev=_this.prev();
				var memo = $("[name=memo]",_this).val();
				var status = $("[name=status]:checked",_this).val();
				var memoSpan = $("[name=memo]",prev);
				var statusSpan = $("[name=statusSpan]",prev);
				var statusInput = $("[name=status]",prev);
				memoSpan.text(memo);
				statusInput.val(status);
				if(status=="1"){
					statusSpan.text($lang_system.sysLogSwitch.management.text_msg_status_open);
				}else{
					statusSpan.text($lang_system.sysLogSwitch.management.text_msg_status_close);
				}
			
				var id = $("[name=id]",prev).val();
				var model = $("[name=model]",prev).val();
				var url = __ctx+"/platform/system/sysLogSwitch/save.ht";
				var json = {
					id:id,
					model:model,
					status:status,
					memo:memo
				};
				
				var param = {
						json:JSON2.stringify(json)
				};
				$.post(url,param,function(){
					
				});
				_this.remove();
			});
		};
	</script>
</head>
<body>
	<div class="panel">
	<div class="hide-panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label"><spr:message code="sysLogSwitch"/><spr:message code="title.manageList"/></span>
			</div>
		</div>
		</div>
		<div class="panel-body">
			<table id="logSwitchManagement" class="table-grid" >
				<thead>
					<tr>
						<th><spr:message code="sysLogSwitch.model"/></th>
						<th><spr:message code="sysLogSwitch.memo"/></th>
						<th><spr:message code="sysLogSwitch.status"/></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${sysLogSwitchList}" var="item" varStatus="status">
						<tr name="logSwitchTr"   >
							<td >
								<span>${item.model}</span>
								<input type="hidden" name="id" value="${item.id}"/>
								<input type="hidden" name="model" value="${item.model}"/>
							</td>
							<td>
								<span name="memo">${item.memo}</span>
							</td>
							<td>
								<%--
								<input type="hidden" name="status" value="${item.status}" ivalue="${item.status}">
								<label><input type="radio" class="logSwitch_status" value="0" name="status_${status.index}" <c:if test="${item.status ne 1}">checked="checked" </c:if> />关闭</label>
								<label><input type="radio" class="logSwitch_status" value="1" name="status_${status.index}" <c:if test="${item.status eq 1}">checked="checked" </c:if> />开启</label>
								 --%>
								<span>
									<input type="hidden" name="status" value="${item.status}">
									<span name="statusSpan">
										<c:choose>
											<c:when test="${item.status eq 1 }"> <spr:message code="sysLogSwitch.status.open"/></c:when>
											<c:otherwise><spr:message code="sysLogSwitch.status.close"/></c:otherwise>
										</c:choose>
									</span>
								</span>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div><!-- end of panel-body -->				
	</div> <!-- end of panel -->
	
	<div style="display: none;">
		<div id="logSwitchEditDiv">
			<table id="logSwitchEditTable">
				<tr id="logSwitchEditTr" class="logSwitchEditTr">
					<td colspan="4">
						<div style="height:60px;width:100%;background:#EEE;padding:0 0 18px 0;">
							<div style="padding: 5px">
								<span class="edit-label"><spr:message code="sysLogSwitch.status"/>:</span>
								<span>
									<label><input type="radio" class="logSwitch_status" value="1" name="status" /><spr:message code="sysLogSwitch.status.open"/></label>
									<label><input type="radio" class="logSwitch_status" value="0" name="status" /><spr:message code="sysLogSwitch.status.close"/></label>
								</span>
							</div>
							<div style="padding: 5px">
								<span class="edit-label"><spr:message code="sysLogSwitch.memo"/>:</span>
								<span>
									<input name="memo" class="inputText" style="width: 300px"/>
								</span>
							</div>
						</div>
					</td>
				</tr>
			</table>
		</div>
	</div>
</body>
</html>


