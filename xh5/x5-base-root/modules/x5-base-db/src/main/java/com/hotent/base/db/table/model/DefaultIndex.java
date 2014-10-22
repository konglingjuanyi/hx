package com.hotent.base.db.table.model;

import java.util.List;

import com.hotent.base.api.db.model.Index;

/**
 * 默认的索引对象
 * 
 * <pre>
 * 构建组：x5-base-db
 * 作者：hugh zhuang
 * 邮箱:zhuangxh@jee-soft.cn
 * 日期:2014-01-25-上午11:35:40
 * 版权：广州宏天软件有限公司版权所有
 * </pre>
 * 
 */
public class DefaultIndex implements Index {

	/**
	 * 索引表名
	 */
	private String tableName;
	/**
	 * 索引类型
	 */
	private String tableType;
	/**
	 * 索引名
	 */
	private String indexName;
	/**
	 * 索引类型
	 */
	private String indexType;
	private String indexStatus;
	/**
	 * 索引字段
	 */
	private List<String> columnList;
	private boolean unique;
	private String indexDdl;
	private String indexComment;
	private boolean pkIndex;

	/**
	 * @return the tableName
	 */
	public String getTableName() {
		return tableName;
	}

	/**
	 * @param tableName
	 *            the tableName to set
	 */
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	/**
	 * @return the tableType
	 */
	public String getTableType() {
		return tableType;
	}

	/**
	 * @param tableType
	 *            the tableType to set
	 */
	public void setTableType(String tableType) {
		this.tableType = tableType;
	}

	/**
	 * @return the indexName
	 */
	public String getIndexName() {
		return indexName;
	}

	/**
	 * @param indexName
	 *            the indexName to set
	 */
	public void setIndexName(String indexName) {
		this.indexName = indexName;
	}

	/**
	 * @return the indexType
	 */
	public String getIndexType() {
		return indexType;
	}

	/**
	 * @param indexType
	 *            the indexType to set
	 */
	public void setIndexType(String indexType) {
		this.indexType = indexType;
	}

	/**
	 * @return the indexStatus
	 */
	public String getIndexStatus() {
		return indexStatus;
	}

	/**
	 * @param indexStatus
	 *            the indexStatus to set
	 */
	public void setIndexStatus(String indexStatus) {
		this.indexStatus = indexStatus;
	}

	/**
	 * @return the columnList
	 */
	public List<String> getColumnList() {
		return columnList;
	}

	/**
	 * @param columnList
	 *            the columnList to set
	 */
	public void setColumnList(List<String> columnList) {
		this.columnList = columnList;
	}

	/**
	 * @return the unique
	 */
	public boolean isUnique() {
		return unique;
	}

	/**
	 * @param unique
	 *            the unique to set
	 */
	public void setUnique(boolean unique) {
		this.unique = unique;
	}

	/**
	 * @return the indexDdl
	 */
	public String getIndexDdl() {
		return indexDdl;
	}

	/**
	 * @param indexDdl
	 *            the indexDdl to set
	 */
	public void setIndexDdl(String indexDdl) {
		this.indexDdl = indexDdl;
	}

	/**
	 * @return the indexComment
	 */
	public String getIndexComment() {
		return indexComment;
	}

	/**
	 * @param indexComment
	 *            the indexComment to set
	 */
	public void setIndexComment(String indexComment) {
		this.indexComment = indexComment;
	}

	/**
	 * @return the pkIndex
	 */
	public boolean isPkIndex() {
		return pkIndex;
	}

	/**
	 * @param pkIndex
	 *            the pkIndex to set
	 */
	public void setPkIndex(boolean pkIndex) {
		this.pkIndex = pkIndex;
	}

}