function AddResourceDialog(conf)
{
	if(!conf){
		$.ligerDialog.error($lang_form.addResourceDialog.argument,$lang.tip.error);
		return;
	}
	var addUrl=conf.addUrl;
	if(!addUrl){
		$.ligerDialog.error($lang_form.addResourceDialog.url,$lang.tip.error);
		return;
	}
	
	var url=__ctx + "/platform/system/resources/addResource.ht";
	
	
	var dialogWidth=900;
	var dialogHeight=600;
	
	conf=$.extend({},{dialogWidth:dialogWidth ,dialogHeight:dialogHeight ,help:0,status:0,scroll:0,center:1},conf);

	var winArgs="dialogWidth="+conf.dialogWidth+"px;dialogHeight="+conf.dialogHeight
		+"px;help=" + conf.help +";status=" + conf.status +";scroll=" + conf.scroll +";center=" +conf.center;
	url=url.getNewUrl();
	var params={
		addUrl:addUrl	
	};
	
	if(conf.name){
		params.name=conf.name;
	}
	
	var rtn=window.showModalDialog(url,params,winArgs);
	if(rtn!=undefined){
		if(conf.callback){
			conf.callback.call(this,rtn);
		}
	}
}