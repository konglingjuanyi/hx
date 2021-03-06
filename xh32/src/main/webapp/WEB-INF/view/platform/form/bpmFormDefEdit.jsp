<%--
	time:2011-11-16 16:34:16
--%>
<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
<%@include file="/commons/include/form.jsp"%>
<f:js pre="js/lang/view/platform/form" ></f:js>
<title><spr:message code="operator.edit"/><spr:message code="bpmFormDef.title"/></title>
<f:link href="tree/zTreeStyle.css"></f:link>
<f:link href="tab/tab.css"></f:link>
<script type="text/javascript" src="${ctx}/js/lg/plugins/ligerLayout.js"></script>
<script type="text/javascript" src="${ctx}/js/tree/jquery.ztree.core.min.js"></script>
<script type="text/javascript" src="${ctx}/servlet/ValidJs?form=bpmFormDef"></script>
<script type="text/javascript" src="${ctx}/js/lg/plugins/ligerWindow.js"></script>
<script type="text/javascript" src="${ctx}/js/lg/plugins/ligerTab.js"></script>
<script type="text/javascript" src="${ctx}/js/hotent/platform/system/SysDialog.js"></script>
<script type="text/javascript" src="${ctx}/js/hotent/platform/form/FormDef.js"></script>
<script type="text/javascript" src="${ctx}/js/hotent/platform/form/PageTab.js"></script>
<script type="text/javascript" src="${ctx}/js/hotent/platform/form/FormContainer.js"></script>
<script type="text/javascript" src="${ctx}/js/hotent/platform/form/CommonDialog.js"></script>
<!-- ueditor -->
<script type="text/javascript" charset="utf-8" src="${ctx}/js/ueditor2/editor_config.js"></script>
<script type="text/javascript" charset="utf-8" src="${ctx}/js/ueditor2/editor_api.js"></script>
<style type="text/css">
	.panel-body{
		overflow:hidden;
	}
</style>
<script type="text/javascript">
	var locale="${locale}";
	var tabTitle="${bpmFormDef.tabTitle}";
	var formKey=${bpmFormDef.formKey};
	var tableId=${bpmFormDef.tableId};
	var tab;
	
	function showRequest(formData, jqForm, options) {
		return true;
	}
	
	$(function() {
		//验证代码
		valid(showRequest, showResponse);		
		$("a.save").click(save);		
		var winHeight = $(window).height()-120;
		$("#frmDefLayout").ligerLayout({leftWidth : 200,height:winHeight,onHeightChanged:function(layoutHeight, diffHeight, middleHeight){
		}});
		var height = $(".l-layout-center").height();
        $("#colstree").height(height-120);
     
		
		tab = $("#tab").ligerGetTabManager();
		FormDef.getEditor({
			height:240,
			width:227,
			lang:locale
		});
		editor.addListener('ready',function(){
			initTab();
		});
		//ueditor渲染textarea
		editor.render("html");
		editor.tableId = tableId;
		//获取字段显示到左边的字段树中
		getAllFields();
		$("#btnPreView").click(function(){
			preview();
		});
	
		
   		window.onbeforeunload = function() {				 											
   		  return $lang.tip.sure;
   	 	};
   	 	if(height)
	 		$('body').height(height);
	});
	
	//预览
	function preview(){
		saveChange();
		window.onbeforeunload  =  null ;
		var objForm = formContainer.getResult();
		var frm=new com.hotent.form.Form();
		frm.creatForm("bpmPreview",__ctx+"/platform/form/bpmFormHandler/edit.ht");
		frm.addFormEl("name",$("#subject").val());
		frm.addFormEl("title",objForm.title);
		frm.addFormEl("html",objForm.form);
		frm.addFormEl("comment",$("#formDesc").val());
		frm.addFormEl("tableId",tableId);
		frm.setTarget("_blank");
		frm.setMethod("post");
		frm.submit();
		frm.clearFormEl();
	};
	
	
	//保存表单数据。
	function save() {
		if (FormDef.isSourceMode) {
			$.ligerDialog.warn($lang_form.bpmFormDef.edit.sourceModeSave,$lang.tip.msg);
			return;
		}
		saveChange();
		var rtn = $("#bpmFormDefForm").valid();
		if (!rtn)
			return;
		//syncOpinion();
		var data = {};
		var arr = $('#bpmFormDefForm').serializeArray();
		$.each(arr, function(i, d) {
			data[d.name] = d.value;
		});

		//保存当前tab的数据。
		var idx = tabControl.getCurrentIndex() - 1;
		saveTabChange(idx);
		var objForm = formContainer.getResult();

		data['tabTitle'] = objForm.title;
		
		data['html'] = objForm.form;
		
        while(data['html'].indexOf('？')!=-1){
        	data['html']=data['html'].replace('？','');
       }
		$.post('save.ht', {
			data : JSON2.stringify(data),
			tableName : $('#tableName').val()
		}, FormDef.showResponse);
	}

	function getAllFields() {
		FormDef.getFieldsByTableId(tableId);
	}

	//tab控件
	var tabControl;
	//存储数据控件。
	var formContainer;
	//添加tab页面
	function addCallBack() {
		var curPage = tabControl.getCurrentIndex();
		var str = $lang_form.bpmFormDef.edit.newPage;
		var idx = curPage - 1;
		formContainer.insertForm(str, "", idx);
		saveTabChange(idx-1,idx);		
	}
	//切换tab之前，返回false即中止切换
	function beforeActive(prePage) {
		if (FormDef.isSourceMode) {
			$.ligerDialog.warn($lang_form.bpmFormDef.edit.sourceModeActive,$lang.tip.msg);
			return 0;
		}
		return 1;
	}
	//点击激活tab时执行。
	function activeCallBack(prePage, currentPage) {
		if (prePage == currentPage)
			return;
		//保存上一个数据。
		saveTabChange(prePage - 1, currentPage - 1);
	}
	//根据索引设置数据
	function setDataByIndex(idx) {
		if (idx == undefined)
			return;
		var obj = formContainer.getForm(idx);
		editor.setContent(obj.form);
		$("b", tabControl.currentTab).text(obj.title);
	}
	//在删除页面之前的事件，返回false即中止删除操作
	function beforeDell(curPage) {
		if (FormDef.isSourceMode) {
			$.ligerDialog.warn($lang_form.bpmFormDef.edit.sourceModeDel,$lang.tip.msg);
			return 0;
		}
		return 1;
	}
	//点击删除时回调执行。
	function delCallBack(curPage) {
		formContainer.removeForm(curPage - 1);
		var tabPage = tabControl.getCurrentIndex();
		setDataByIndex(tabPage - 1);
	}
	//文本返回
	function txtCallBack() {
		var curPage = tabControl.getCurrentIndex();
		var idx = curPage - 1;
		var title = tabControl.currentTab.text();
		//设置标题
		formContainer.setFormTitle(title, idx);
	}
	//tab切换时保存数据
	function saveTabChange(index, curIndex){
		var data = editor.getContent();
		formContainer.setFormHtml(data, index);
		setDataByIndex(curIndex);
	}
	//表单或者标题发生变化是保存数据。
	function saveChange() {
		var index = tabControl.getCurrentIndex() - 1;
		var title = tabControl.currentTab.text();
		var data = editor.getContent();
		formContainer.setForm(title, data, index);
	}
	//初始化TAB
	function initTab() {
		var formData = editor.getContent();
		if (tabTitle == "")
			tabTitle = $lang_form.bpmFormDef.edit.page1;
		formContainer = new FormContainer();
		var aryTitle = tabTitle.split(formContainer.splitor);
		var aryForm = formData.split(formContainer.splitor);
		var aryLen = aryTitle.length;
		//初始化
		formContainer.init(tabTitle, formData);

		tabControl = new PageTab("pageTabContainer", aryLen, {
			addCallBack : addCallBack,
			beforeActive : beforeActive,
			activeCallBack : activeCallBack,
			beforeDell : beforeDell,
			delCallBack : delCallBack,
			txtCallBack : txtCallBack
		});
		tabControl.init(aryTitle);
		if (aryLen > 1) {
			editor.setContent(aryForm[0]);
		};		
	};
</script>
</head>
<body style="overflow:hidden">
	<div>
		<div class="tbar-title">
			<span class="tbar-label"><spr:message code="operator.edit"/><spr:message code="bpmFormDef.title"/></span>
		</div>
		<div class="panel-toolbar">
			<div class="toolBar">
				<div class="group">
					<a class="link save" id="dataFormSave" href="#"><span></span><spr:message code="menu.button.save"/></a>
				</div>
				<div class="l-bar-separator"></div>
				<div class="group">
					<a class="link preview" id="btnPreView" href="javascript:;"><span></span><spr:message code="menu.button.preview"/></a>
				</div>
				<div class="group">
					<a class="link  del" href="javascript:window.onbeforeunload = null;window.close()"><span></span><spr:message code="menu.button.close"/></a>
				</div>
			</div>
		</div>
	</div>
	<div  class="panel-body">
		<form id="bpmFormDefForm" method="post">
			<input id="formDefId" type="hidden" name="formDefId" value="${bpmFormDef.formDefId}" /> 
			<input id="tableId" type="hidden" name="tableId" value="${bpmFormDef.tableId}" />
			<input id="categoryId" type="hidden" name="categoryId" value="${bpmFormDef.categoryId}" /> 
			<input  type="hidden" name="formKey" value="${bpmFormDef.formKey}" /> 
			<input  type="hidden" name="isDefault" value="${bpmFormDef.isDefault}" /> 
			<input  type="hidden" name="versionNo" value="${bpmFormDef.versionNo}" /> 
			<input  type="hidden" name="isPublished" value="${bpmFormDef.isPublished}" /> 
			<input  type="hidden" name="publishedBy" value="${bpmFormDef.publishedBy}" /> 
			<input  type="hidden" name="publishTime" value="<fmt:formatDate value="${bpmFormDef.publishTime}" pattern="yyyy-MM-dd HH:mm:ss"/>" /> 
			<input  type="hidden" id="templatesId" name="templatesId" value="${bpmFormDef.templatesId}"/> 
			<div class="panel-nav">
				<table cellpadding="0" cellspacing="0" border="0"  class="table-detail">
					<tr>
						<th width="80"><spr:message code="bpmFormDef.subject"/>:&nbsp;</th>
						<td style="padding:4px;"><input id="subject" type="text" name="subject" value="${bpmFormDef.subject}" class="inputText" style="width:86%" /></td>
						<th width="80"><spr:message code="bpmFormDef.formDesc"/>:&nbsp;</th>
						<td style="padding:4px;"><input id="formDesc" type="text" name="formDesc" value="${bpmFormDef.formDesc}" class="inputText" style="width:86%"/></td>
					</tr>
				</table>
			</div>
		</form>
		<div id="tab" class="panel-nav">
			<div id="frmDefLayout">
				<div position="left" title="<spr:message code="bpmFormDef.tableField"/>" style="overflow: auto;">
					<div class="tree-toolbar tree-title">
						<span class="toolBar">
							<div class="group">
								<a class="link reload" onclick="getAllFields()"><spr:message code="menu.button.refresh"/></a>
							</div>
						</span>
					</div>
					<ul id="colstree" class="ztree"></ul>
				</div>
				<div id="editor" position="center"  style="overflow:hidden;height:100%;">
					<textarea id="html" name="html">${fn:escapeXml(bpmFormDef.html) }</textarea>
					<div id="pageTabContainer"></div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>

