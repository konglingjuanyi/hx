package com.hotent.platform.model.form;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.hotent.core.model.BaseModel;
import com.hotent.core.util.BeanUtils;
import com.hotent.core.util.ContextUtil;
import com.hotent.core.util.StringPool;
import com.hotent.core.util.StringUtil;
import com.hotent.platform.model.system.SysLanguage;
import com.hotent.platform.model.util.FieldPool;

/**
 * 对象功能:bpm_form_field Model对象 开发公司:广州宏天软件有限公司 开发人员:xwy 创建时间:2012-02-06 15:49:21
 */
@XmlRootElement(name = "field")
@XmlAccessorType(XmlAccessType.NONE)
public class BpmFormField extends BaseModel implements Cloneable {
	public static short VALUE_FROM_FORM = 0;
	public static short VALUE_FROM_SCRIPT_SHOW = 1;
	public static short VALUE_FROM_SCRIPT_HIDDEN = 2;
	public static short VALUE_FROM_IDENTITY = 3;

	/**
	 * 标记删除：是
	 */
	public final static int IS_DELETE_Y=1;
	/**
	 * 标记删除：否
	 */
	public final static int IS_DELETE_N=0;
	
	/** 条件值来源类型，来自表单输入 */
	public static short COND_TYPE_FORM = 0;
	/** 条件值来源类型，来自script */
	public static short COND_TYPE_SCRIPT = 1;
	/** 条件值来源类型，来自固定值*/
	public static short COND_TYPE_FIX = 2;

	/** 隐藏字段 +ID。 */
	public static final String FIELD_HIDDEN = "ID";


	
	/** 隐藏*/
	public static final short HIDDEN = 1;
	/** 非隐藏*/
	public static final short NO_HIDDEN = 0;	
		
	public static String ElmName = "field";
	
	
	
	public static final String DATATYPE_VARCHAR="varchar";
	public static final String DATATYPE_CLOB="clob";
	public static final String DATATYPE_DATE="date";
	public static final String DATATYPE_NUMBER="number";

	// fieldId
	@XmlAttribute
	protected Long fieldId = 0L;
	// tableId
	@XmlAttribute
	protected Long tableId = 0L;
	// 字段名称
	@XmlAttribute
	protected String fieldName = "";
	// 字段类型
	@XmlAttribute
	protected String fieldType = "";
	// 是否必填
	@XmlAttribute
	protected Short isRequired=0;
	// 是否列表显示
	@XmlAttribute
	protected Short isList=1;
	// 是否查询显示
	@XmlAttribute
	protected Short isQuery=1;
	// 说明
	@XmlAttribute
	protected String fieldDesc = "";
	// 字符长度，仅针对字符类型
	@XmlAttribute
	protected Integer charLen=0;
	// 整数长度，仅针对数字类型
	@XmlAttribute
	protected Integer intLen=0;
	// 小数长度，仅针对数字类型
	@XmlAttribute
	protected Integer decimalLen=0;
	// 数据字典的类别，仅针对数据字典类型
	@XmlAttribute
	protected String dictType = "";
	// 是否删除
	@XmlAttribute
	protected Short isDeleted = 0;
	// 验证规则
	@XmlAttribute
	protected String validRule = "";
	// 字段原名
	@XmlAttribute
	protected String originalName = "";
	// 排列顺序
	@XmlAttribute
	protected Integer sn=0;
	// 值来源 0-表单 1-脚本
	@XmlAttribute
	protected Short valueFrom = 0;
	// 脚本
	@XmlAttribute
	protected String script = "";
	// 控件类型
	@XmlAttribute
	protected Short controlType=0;
	// 是否隐藏域
	@XmlAttribute
	protected Short isHidden = 0;
	// 是否流程变量
	@XmlAttribute
	protected Short isFlowVar = 0;
	// 流水号别名
	@XmlAttribute
	protected String identity = "";
	// 下拉框
	@XmlAttribute
	protected String options = "";
	// 控件属性
	@XmlAttribute
	protected String ctlProperty = "";
	// 新添加的列
	@XmlAttribute
	protected boolean isAdded = false;

	// 支持手机客户端显示
	@XmlAttribute
	protected Short isAllowMobile = 0;
	// 文字为日期格式
	@XmlAttribute
	protected Short isDateString = 0;
	// 文字取当前日期
	@XmlAttribute
	protected Short isCurrentDateStr = 0;
	// 控件样式
	@XmlAttribute
	protected String style;
	//流程引用
	@XmlAttribute
	protected Short isReference;
	

	// 非数据库字段 日期格式
	@XmlAttribute
	protected String datefmt;
	
	/**
	 * 选择器ID脚本。
	 */
	@XmlAttribute
	protected String scriptID="";

	// 该字段是否主键
	// 1为主键
//	protected Integer isPk = 0;
	@XmlAttribute
	protected short type;
	
	//web印章验证    1为支持，0为不用 
	@XmlAttribute
	protected Short	isWebSign=0;
	//数字类型是否显示千分位
	@XmlAttribute
	protected Short isShowComdify;
	//数字类型显示货币符号
	@XmlAttribute
	protected String coinValue;
	
	public void setFieldId(Long fieldId) {
		this.fieldId = fieldId;
	}

	/**
	 * 返回 fieldId
	 * 
	 * @return
	 */
	public Long getFieldId() {
		return fieldId;
	}

	public void setTableId(Long tableId) {
		this.tableId = tableId;
	}

	public Short getIsAllowMobile() {
		return isAllowMobile;
	}

	public void setIsAllowMobile(Short isAllowMobile) {
		this.isAllowMobile = isAllowMobile;
	}
	
	public Short getIsWebSign()
	{
		return isWebSign;
	}

	public void setIsWebSign(Short isWebSign)
	{
		this.isWebSign = isWebSign;
	}

	/**
	 * 返回 tableId
	 * 
	 * @return
	 */
	public Long getTableId() {
		return tableId;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	/**
	 * 返回 字段名称
	 * 
	 * @return
	 */
	public String getFieldName() {
		return fieldName;
	}

	public void setFieldType(String fieldType) {
		this.fieldType = fieldType;
	}

	/**
	 * 返回 字段类型
	 * 
	 * @return
	 */
	public String getFieldType() {
		return fieldType;
	}

	public void setIsRequired(Short isRequired) {
		this.isRequired = isRequired;
	}

	/**
	 * 返回 是否必填
	 * 
	 * @return
	 */
	public Short getIsRequired() {
		return isRequired;
	}

	public void setIsList(Short isList) {
		this.isList = isList;
	}

	/**
	 * 返回 是否列表显示
	 * 
	 * @return
	 */
	public Short getIsList() {
		return isList;
	}

	public void setIsQuery(Short isQuery) {
		this.isQuery = isQuery;
	}

	/**
	 * 返回 是否查询显示
	 * 
	 * @return
	 */
	public Short getIsQuery() {
		return isQuery;
	}

	public void setFieldDesc(String fieldDesc) {
		this.fieldDesc = fieldDesc;
	}

	/**
	 * 返回 说明
	 * 
	 * @return
	 */
	public String getFieldDesc() {
		return fieldDesc;
	}

	public void setCharLen(Integer charLen) {
		this.charLen = charLen;
	}

	/**
	 * 返回 字符长度，仅针对字符类型
	 * 
	 * @return
	 */
	public Integer getCharLen() {
		return charLen;
	}

	public void setIntLen(Integer intLen) {
		this.intLen = intLen;
	}

	/**
	 * 返回 整数长度，仅针对数字类型
	 * 
	 * @return
	 */
	public Integer getIntLen() {
		return intLen;
	}

	public void setDecimalLen(Integer decimalLen) {
		this.decimalLen = decimalLen;
	}

	/**
	 * 返回 小数长度，仅针对数字类型
	 * 
	 * @return
	 */
	public Integer getDecimalLen() {
		return decimalLen;
	}

	public void setDictType(String dictType) {
		this.dictType = dictType;
	}

	/**
	 * 返回 数据字典的类别，仅针对数据字典类型
	 * 
	 * @return
	 */
	public String getDictType() {
		return dictType;
	}

	public void setIsDeleted(Short isDeleted) {
		this.isDeleted = isDeleted;
	}

	/**
	 * 返回 是否删除
	 * 
	 * @return
	 */
	public Short getIsDeleted() {
		return isDeleted;
	}

	public void setValidRule(String validRule) {
		this.validRule = validRule;
	}

	/**
	 * 返回 验证规则
	 * 
	 * @return
	 */
	public String getValidRule() {
		return validRule;
	}

	public void setOriginalName(String originalName) {
		this.originalName = originalName;
	}

	/**
	 * 返回 字段原名
	 * 
	 * @return
	 */
	public String getOriginalName() {
		return originalName;
	}

	public void setSn(Integer sn) {
		this.sn = sn;
	}

	/**
	 * 返回 排列顺序
	 * 
	 * @return
	 */
	public Integer getSn() {
		return sn;
	}

	public void setValueFrom(Short valueFrom) {
		this.valueFrom = valueFrom;
	}

	/**
	 * 返回 值来源 0-表单 1-脚本
	 * 
	 * @return
	 */
	public Short getValueFrom() {
		return valueFrom;
	}

	public void setScript(String script) {
		this.script = script;
	}

	/**
	 * 返回 脚本
	 * 
	 * @return
	 */
	public String getScript() {
		return script;
	}

	public void setControlType(Short controlType) {
		this.controlType = controlType;
	}

	/**
	 * 返回 控件类型
	 * 
	 * @return
	 */
	public Short getControlType() {
		return controlType;
	}

	public Short getIsHidden() {
		return isHidden;
	}

	public void setIsHidden(Short isHidden) {
		this.isHidden = isHidden;
	}

	public Short getIsFlowVar() {
		return isFlowVar;
	}

	public void setIsFlowVar(Short isFlowVar) {
		this.isFlowVar = isFlowVar;
	}

	public String getIdentity() {
		return identity;
	}

	public void setIdentity(String identity) {
		this.identity = identity;
	}


	public String getOptions() {
		return this.options;
	}

	public void setOptions(String options) {
		this.options = options;
	}
	

	/**
	 * 获取json对象。
	 * @return
	 */
	public String getJsonOptions(){
		if(StringUtil.isEmpty(this.options))
			return "";
		String lanType = ContextUtil.getLocale().toString();
		JSONArray jarray = new JSONArray();
		if(StringUtil.isJson(this.options)){
			JSONArray jArray = JSONArray.fromObject(this.options);
			for(Object obj : jArray){
				JSONObject jObject = (JSONObject)obj;
				String key = jObject.getString("key");
				String jsonValue = jObject.getString("value");
				if(StringUtil.isJSONArray(jsonValue)){
					String value = "";
					String defVal = "";
					JSONArray valAry = jObject.getJSONArray("value");
					for(Object valObj : valAry){
						JSONObject valJObject = (JSONObject)valObj;
						String type = valJObject.getString("lantype");
						if(lanType.equals(type)){
							value = valJObject.getString("lanres");
						}
						if(type.equals(SysLanguage.SIMPLIFIED_CHINESE)){
							defVal = valJObject.getString("lanres");
						}
					}
					//未获取到对应语言版本的资源时  获取简体中文的资源
					if(StringUtil.isEmpty(value)){
						value = defVal;
					}
					jarray.add(new JSONObject().accumulate("key", key).accumulate("value", value));
				}
				else{//兼容3.2
					jarray.add(new JSONObject().accumulate("key", key).accumulate("value", jsonValue));
				}
				
			}
		}
		else{
			String[] ary = this.options.split("\n");
			for(String opt : ary){
				jarray.add(new JSONObject().accumulate("key", opt).accumulate("value", opt));
			}
		}
		String reStr = jarray.toString();
		reStr = reStr.replaceAll("\"", "'");
		return reStr;
	}

	/**
	 * 选项构建成hashmap对象。
	 * @return
	 */
	public Map<String,Object> getAryOptions() {
		Map<String,Object> map = new LinkedHashMap<String, Object>();
		if(StringUtil.isEmpty(this.options))
			return map;
		String lanType = ContextUtil.getLocale().toString();
		if(StringUtil.isJson(this.options)){
			JSONArray jArray = JSONArray.fromObject(this.options);
			for(Object obj : jArray){
				JSONObject jObject = (JSONObject)obj;
				String key = jObject.getString("key");
				String jsonValue = jObject.getString("value");
				if(StringUtil.isJSONArray(jsonValue)){
					String value = "";
					String defVal = "";
					JSONArray valAry = jObject.getJSONArray("value");
					for(Object valObj : valAry){
						JSONObject valJObject = (JSONObject)valObj;
						String type = valJObject.getString("lantype");
						if(lanType.equals(type)){
							value = valJObject.getString("lanres");
						}
						if(type.equals(SysLanguage.SIMPLIFIED_CHINESE)){
							defVal = valJObject.getString("lanres");
						}
					}
					//未获取到对应语言版本的资源时  获取简体中文的资源
					if(StringUtil.isEmpty(value)){
						value = defVal;
					}
					map.put(key,value);
				}
				else{//兼容3.2
					map.put(key,jsonValue);
				}
			}
		}
		else{
			String[] ary = this.options.split("\n");
			for(String opt : ary){
				map.put(opt, opt);
			}
		}
		return map;
	}
	
	public boolean isAdded() {
		return isAdded;
	}

	public void setAdded(boolean isAdded) {
		this.isAdded = isAdded;
	}

	public Short getIsDateString() {
		return isDateString;
	}

	public void setIsDateString(Short isDateString) {
		this.isDateString = isDateString;
	}

	public Short getIsCurrentDateStr() {
		return isCurrentDateStr;
	}

	public void setIsCurrentDateStr(Short isCurrentDateStr) {
		this.isCurrentDateStr = isCurrentDateStr;
	}

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	/**
	 * 获取控件属性
	 * 
	 * @return
	 */
	public String getCtlProperty() {
		return ctlProperty;
	}

	public void setCtlProperty(String ctlProperty) {
		this.ctlProperty = ctlProperty;
	}

	/**
	 * 将控件属性转换成map输出。
	 * 
	 * @return
	 */
	public Map<String, String> getPropertyMap() {
		Map<String, String> map = new HashMap<String, String>();
		if (StringUtil.isEmpty(this.ctlProperty)) {
			return map;
		}
		try {
			JSONObject jsonObject = JSONObject.fromObject(this.ctlProperty);
			Iterator it = jsonObject.keys();
			while (it.hasNext()) {
				String key = it.next().toString();
				String value = jsonObject.getString(key);
				map.put(key, value);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return map;
	}

	public String getFieldTypeDisplay() {
		if ("varchar".equals(this.fieldType)) {
			return "字符串,varchar(" + this.charLen + ")";
		} else if ("number".equals(this.fieldType)) {
			if(BeanUtils.isEmpty(this.decimalLen)){
				this.decimalLen =0;
			}		
			if (this.decimalLen == 0) {
				return "数字,number(" + this.intLen + ")";
			}
			return "数字,number(" + this.intLen + "," + this.decimalLen + ")";
		} else if ("date".equals(this.fieldType)) {
			return "日期,date";
		} else if ("clob".equals(this.fieldType)) {
			return "大文本";
		}
		return "字符串";
	}

	public Short getIsReference() {
		return isReference;
	}

	public void setIsReference(Short isReference) {
		this.isReference = isReference;
	}

	

	public String getDatefmt() {
		datefmt = StringPool.DATE_FORMAT_DATE;
		if(StringUtils.isNotEmpty(ctlProperty)){
			try {
				JSONObject json = JSONObject.fromObject(ctlProperty);
				Object format = json.get("format");
				if(BeanUtils.isNotEmpty(format))
					datefmt= (String) format;
			} catch (Exception e) {
			}
		}
		return datefmt;
	}

	public void setDatefmt(String datefmt) {
		this.datefmt = datefmt;
	}

	public short getType() {
		return type;
	}

	public void setType(short type) {
		this.type = type;
	}

	public String getScriptID() {
		return scriptID;
	}

	public void setScriptID(String scriptID) {
		this.scriptID = scriptID;
	}


	public Short getIsShowComdify() {
		return isShowComdify;
	}

	public void setIsShowComdify(Short isShowComdify) {
		this.isShowComdify = isShowComdify;
	}

	public String getCoinValue() {
		return coinValue;
	}

	public void setCoinValue(String coinValue) {
		this.coinValue = coinValue;
	}

	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) {
		if (!(object instanceof BpmFormField)) {
			return false;
		}
		BpmFormField rhs = (BpmFormField) object;
		return this.fieldName.equals(rhs.getFieldName());

	}

	public Object clone() {
		BpmFormField obj = null;
		try {
			obj = (BpmFormField) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return obj;
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973)
				.append(this.fieldName).toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this).append("fieldName", this.fieldName)
				.toString();
	}
	
	
	/**
	 * 是否选择器的隐藏字段
	 * @return
	 */
	public boolean isExecutorSelectorHidden(){
		if(BeanUtils.isEmpty(controlType)) 	return false;
		if (controlType.shortValue() == FieldPool.SELECTOR_USER_SINGLE
				|| controlType.shortValue() == FieldPool.SELECTOR_USER_MULTI
				|| controlType.shortValue() == FieldPool.SELECTOR_ORG_SINGLE
				|| controlType.shortValue() == FieldPool.SELECTOR_ORG_MULTI
				|| controlType.shortValue() == FieldPool.SELECTOR_ROLE_SINGLE
				|| controlType.shortValue() == FieldPool.SELECTOR_ROLE_MULTI
				|| controlType.shortValue() == FieldPool.SELECTOR_POSITION_SINGLE
				|| controlType.shortValue() == FieldPool.SELECTOR_POSITION_MULTI) {
			if(isHidden.shortValue()==  BpmFormField.HIDDEN)
				return true;
		}
		return false;
	}

}