/**
 * 
 */
package com.hotent.base.core.soap.type;

import java.lang.reflect.Field;
import java.util.Iterator;

import javax.xml.soap.SOAPElement;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.w3c.dom.NodeList;

import com.hotent.base.core.soap.SoapConst;
import com.hotent.base.core.util.BeanUtils;

/**
 * @author wwz
 * 
 */
public class BeanSoapType extends BaseSoapType {

	private static Log logger = LogFactory.getLog(BaseSoapType.class);

	@Override
	public Class<?>[] getBeanTypes() {
		return new Class[] { Object.class };
	}

	@Override
	public String[] getSoapTypes() {
		return new String[] { "anyType" };
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	void setCurrentValue(SOAPElement element, Object obj, Class<?> klass) {
		Class<?> myKlass = obj.getClass();
		if (myKlass != null)
			klass = myKlass;
		// 通过反射设置obj的值
		for (Field field : klass.getDeclaredFields()) {
			field.setAccessible(true);
			Class fieldType = field.getType();// 获取字段类型
			String fieldName = field.getName();// 字段名
			fieldName = fieldName.replace(SoapConst.CGLIB_PREFIX, "");
			NodeList fieldNodeList = element.getElementsByTagName(fieldName);
			if (fieldNodeList == null || fieldNodeList.getLength() < 1) // webservice没有该字段,不需要拷贝
				continue;
			try {
				Object objValue = field.get(obj);
				SOAPElement targetElement = (SOAPElement) fieldNodeList.item(0);
				if (BeanUtils.isEmpty(objValue)) {
					boolean hasChild = targetElement.hasChildNodes();
					// 如果节点既没有子节点 也没有值 则移除该节点（否则调用时 会报错）
					if (!hasChild) {
						targetElement.detachNode();
					}
					continue;
				}
				SoapTypes.getTypeByBean(fieldType).setValue(targetElement,
						objValue, fieldType);
			} catch (Exception e) {
				// 设置失败,跳过.
				logger.warn("字段[" + fieldName + "]设置失败.", e);
				continue;
			}
		}
		Iterator<SOAPElement> it = element.getChildElements();
		while (it.hasNext()) {
			SOAPElement child = it.next();
			if (child.hasChildNodes())
				continue;
			String content = child.getTextContent();
			if (StringUtils.isEmpty(content))
				child.detachNode();
		}
	}

	@SuppressWarnings("rawtypes")
	@Override
	Object convertCurrent(Class<?> klass, SOAPElement element) {
		Object bean;
		try {
			bean = klass.newInstance();
		} catch (Exception e) {
			logger.error("类别[" + klass + "]无法实例化.", e);
			return null;
		}

		for (Field field : klass.getDeclaredFields()) {
			field.setAccessible(true);
			Class fieldType = field.getType();// 获取字段类型
			String fieldName = field.getName();// 字段名
			NodeList fieldNodeList = element.getElementsByTagName(fieldName);
			if (fieldNodeList == null || fieldNodeList.getLength() < 1) {// webservice没有该字段,不需要拷贝
				continue;
			}
			try {
				Object obj = SoapTypes.getTypeByBean(fieldType).convertToBean(
						fieldType, element);
				field.set(bean, obj);
			} catch (Exception e) {
				// 设置失败,跳过.
				logger.warn("字段[" + fieldName + "]设置失败.", e);
				continue;
			}
		}

		return bean;
	}

}
