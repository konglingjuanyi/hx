package com.hotent.base.db.table.impl.sqlserver;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang.StringUtils;
import org.springframework.jdbc.core.RowMapper;

import com.hotent.base.api.Page;
import com.hotent.base.api.db.model.Column;
import com.hotent.base.api.db.model.Table;
import com.hotent.base.core.util.BeanUtils;
import com.hotent.base.db.jdbc.JdbcHelper;
import com.hotent.base.db.table.BaseTableMeta;
import com.hotent.base.db.table.colmap.SQLServerColumnMap;
import com.hotent.base.db.table.model.DefaultTable;
import com.hotent.base.db.table.util.SQLConst;

/**
 * SQLServer 表元数据的实现类
 * 
 * <pre>
 * 构建组：x5-base-db
 * 作者：hugh zhuang
 * 邮箱:zhuangxh@jee-soft.cn
 * 日期:2014-01-25-上午11:35:40
 * 版权：广州宏天软件有限公司版权所有
 * </pre>
 * 
 * 
 */
public class SQLServerTableMeta extends BaseTableMeta {

	/**
	 * 取得主键
	 */
	private String sqlPk = "SELECT c.COLUMN_NAME COLUMN_NAME FROM INFORMATION_SCHEMA.TABLE_CONSTRAINTS pk ,INFORMATION_SCHEMA.KEY_COLUMN_USAGE c "
			+ "WHERE 	pk.TABLE_NAME LIKE '%s' "
			+ "and	CONSTRAINT_TYPE = 'PRIMARY KEY' "
			+ "and	c.TABLE_NAME = pk.TABLE_NAME "
			+ "and	c.CONSTRAINT_NAME = pk.CONSTRAINT_NAME ";

	// private String sqlTable="SELECT name f"

	/**
	 * 取得注释
	 */
	private String sqlTableComment = "select cast(b.value as varchar) comment from sys.tables a, sys.extended_properties b where a.type='U' and a.object_id=b.major_id and b.minor_id=0 and a.name='%s'";

	/**
	 * 取得列表
	 */
	private String SQL_GET_COLUMNS = "SELECT B.NAME TABLE_NAME,A.NAME NAME, C.NAME TYPENAME, A.MAX_LENGTH LENGTH, A.IS_NULLABLE IS_NULLABLE,A.PRECISION PRECISION,A.SCALE SCALE, "
			+ "  ( "
			+ " 		SELECT COUNT(*) "
			+ " 		FROM  "
			+ " 		SYS.IDENTITY_COLUMNS  "
			+ " 		WHERE SYS.IDENTITY_COLUMNS.OBJECT_ID = A.OBJECT_ID AND A.COLUMN_ID = SYS.IDENTITY_COLUMNS.COLUMN_ID"
			+ "	) AS AUTOGEN, "
			+ " 	( "
			+ " 		SELECT CAST(VALUE AS VARCHAR) "
			+ " 		FROM SYS.EXTENDED_PROPERTIES  "
			+ " 		WHERE SYS.EXTENDED_PROPERTIES.MAJOR_ID = A.OBJECT_ID AND SYS.EXTENDED_PROPERTIES.MINOR_ID = A.COLUMN_ID "
			+ " 	) AS DESCRIPTION, "
			+ " 	( "
			+ " 		SELECT COUNT(*) "
			+ " 		FROM INFORMATION_SCHEMA.TABLE_CONSTRAINTS pk ,INFORMATION_SCHEMA.KEY_COLUMN_USAGE kcu "
			+ " 		WHERE 	pk.TABLE_NAME = B.NAME "
			+ " 			 AND	CONSTRAINT_TYPE = 'PRIMARY KEY'  "
			+ " 			 AND	KCU.TABLE_NAME = PK.TABLE_NAME  "
			+ " 			 AND	KCU.CONSTRAINT_NAME = PK.CONSTRAINT_NAME "
			+ " 			 AND 	KCU.COLUMN_NAME =A.NAME "
			+ " 	) AS IS_PK "
			+ " FROM SYS.COLUMNS A, SYS.TABLES B, SYS.TYPES C  "
			+ " WHERE A.OBJECT_ID = B.OBJECT_ID AND A.SYSTEM_TYPE_ID=C.SYSTEM_TYPE_ID AND B.NAME='%s' "
			+ " 		AND C.NAME<>'SYSNAME' " + "		ORDER BY A.COLUMN_ID ";

	private String SQL_GET_COLUMNS_BATCH = "SELECT B.NAME TABLE_NAME,A.NAME NAME, C.NAME TYPENAME, A.MAX_LENGTH LENGTH, A.IS_NULLABLE IS_NULLABLE,A.PRECISION PRECISION,A.SCALE SCALE, "
			+ " ( "
			+ " 	SELECT COUNT(*) "
			+ " 	FROM  "
			+ " 	SYS.IDENTITY_COLUMNS  "
			+ " WHERE SYS.IDENTITY_COLUMNS.OBJECT_ID = A.OBJECT_ID AND A.COLUMN_ID = SYS.IDENTITY_COLUMNS.COLUMN_ID) AS AUTOGEN, "
			+ " 	( "
			+ " 			SELECT CAST(VALUE AS VARCHAR) "
			+ " 			FROM SYS.EXTENDED_PROPERTIES  "
			+ " 		WHERE SYS.EXTENDED_PROPERTIES.MAJOR_ID = A.OBJECT_ID AND SYS.EXTENDED_PROPERTIES.MINOR_ID = A.COLUMN_ID "
			+ " 	) AS DESCRIPTION, "
			+ " 	( "
			+ " 		SELECT COUNT(*) "
			+ " 		FROM INFORMATION_SCHEMA.TABLE_CONSTRAINTS pk ,INFORMATION_SCHEMA.KEY_COLUMN_USAGE kcu "
			+ " 		WHERE 	pk.TABLE_NAME = B.NAME "
			+ " 			 AND	CONSTRAINT_TYPE = 'PRIMARY KEY'  "
			+ " 			 AND	KCU.TABLE_NAME = PK.TABLE_NAME  "
			+ " 			 AND	KCU.CONSTRAINT_NAME = PK.CONSTRAINT_NAME "
			+ " 			 AND 	KCU.COLUMN_NAME =A.NAME "
			+ " 	) AS IS_PK "
			+ " FROM SYS.COLUMNS A, SYS.TABLES B, SYS.TYPES C  "
			+ " WHERE A.OBJECT_ID = B.OBJECT_ID AND A.SYSTEM_TYPE_ID=C.SYSTEM_TYPE_ID "
			+ " 		AND C.NAME<>'SYSNAME' ";

	/**
	 * 取得数据库所有表
	 */
	private String sqlAllTables = "select a.name name, cast(b.value as varchar) comment from sys.tables a, sys.extended_properties b where a.type='U' and a.object_id=b.major_id and b.minor_id=0";

	/**
	 * 获取表对象
	 */
	@Override
	public Table getTableByName(String tableName) {
		Table model = getTable(tableName);
		// 获取列对象
		List<Column> columnList = getColumnsByTableName(tableName);
		model.setColumnList(columnList);
		return model;
	}

	/**
	 * 根据表名获取主键列名
	 * 
	 * @param tableName
	 * @return
	 */
	@SuppressWarnings({ "unused", "unchecked" })
	private String getPkColumn(String tableName) {
		// jdbcHelper.setCurrentDb(currentDb);
		jdbcHelper = JdbcHelper.getInstance();
		String sql = String.format(sqlPk, tableName);
		Object rtn = jdbcHelper.queryForObject(sql, null,
				new RowMapper<String>() {
					@Override
					public String mapRow(ResultSet rs, int row)
							throws SQLException {
						return rs.getString("COLUMN_NAME");
					}
				});
		if (rtn == null)
			return "";

		return rtn.toString();
	}

	/**
	 * 根据表名获取tableModel。
	 * 
	 * @param tableName
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private Table getTable(final String tableName) {

		// jdbcHelper.setCurrentDb(currentDb);
		jdbcHelper = JdbcHelper.getInstance();
		String sql = String.format(sqlTableComment, tableName);
		Table tableModel = (Table) jdbcHelper.queryForObject(sql, null,
				new RowMapper<Table>() {

					@Override
					public Table mapRow(ResultSet rs, int row)
							throws SQLException {
						Table tableModel = new DefaultTable();
						tableModel.setName(tableName);
						tableModel.setComment(rs.getString("comment"));
						return tableModel;
					}
				});
		if (BeanUtils.isEmpty(tableModel))
			tableModel = new DefaultTable();

		tableModel.setName(tableName);

		return tableModel;
	}

	/**
	 * 根据表名查询列表，如果表名为空则去系统所有的数据库表。
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Map<String, String> getTablesByName(String tableName) {
		String sql = sqlAllTables;
		if (StringUtils.isNotEmpty(tableName))
			sql += " and  lower(a.name) like '%" + tableName.toLowerCase()
					+ "%'";
		// jdbcHelper.setCurrentDb(currentDb);
		jdbcHelper = JdbcHelper.getInstance();
		Map parameter = new HashMap();
		List list = jdbcHelper.queryForList(sql, parameter,
				new RowMapper<Map<String, String>>() {
					@Override
					public Map<String, String> mapRow(ResultSet rs, int row)
							throws SQLException {
						String tableName = rs.getString("name");
						String comments = rs.getString("comment");
						Map<String, String> map = new HashMap<String, String>();
						map.put("name", tableName);
						map.put("comments", comments);
						return map;
					}
				});
		Map<String, String> map = new LinkedHashMap<String, String>();
		for (int i = 0; i < list.size(); i++) {
			Map<String, String> tmp = (Map<String, String>) list.get(i);
			String name = tmp.get("name");
			String comments = tmp.get("comments");
			map.put(name, comments);
		}

		return map;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Map<String, String> getTablesByName(List<String> names) {
		StringBuffer sb = new StringBuffer();
		for (String name : names) {
			sb.append("'");
			sb.append(name);
			sb.append("',");
		}
		sb.deleteCharAt(sb.length() - 1);
		String sql = sqlAllTables + " and  a.name in ("
				+ sb.toString().toLowerCase() + ")";

		// jdbcHelper.setCurrentDb(currentDb);

		jdbcHelper = JdbcHelper.getInstance();
		Map parameter = new HashMap();
		List list = jdbcHelper.queryForList(sql, parameter,
				new RowMapper<Map<String, String>>() {
					@Override
					public Map<String, String> mapRow(ResultSet rs, int row)
							throws SQLException {
						String tableName = rs.getString("name");
						String comments = rs.getString("comment");
						Map<String, String> map = new HashMap<String, String>();
						map.put("name", tableName);
						map.put("comments", comments);
						return map;
					}
				});
		Map<String, String> map = new LinkedHashMap<String, String>();
		for (int i = 0; i < list.size(); i++) {
			Map<String, String> tmp = (Map<String, String>) list.get(i);
			String name = tmp.get("name");
			String comments = tmp.get("comments");
			map.put(name, comments);
		}

		return map;
	}

	@Override
	public List<Table> getTablesByName(String tableName, Page page)
			throws Exception {
		String sql = sqlAllTables;
		if (StringUtils.isNotEmpty(tableName))
			sql += " AND  LOWER(name) LIKE '%" + tableName.toLowerCase() + "%'";
		RowMapper<Table> rowMapper = new RowMapper<Table>() {
			@Override
			public Table mapRow(ResultSet rs, int row) throws SQLException {
				Table tableModel = new DefaultTable();
				tableModel.setName(rs.getString("NAME"));
				tableModel.setComment(rs.getString("COMMENT"));
				return tableModel;
			}
		};
		List<Table> tableModels = getForList(sql, page, rowMapper,
				SQLConst.DB_SQLSERVER);
		// 获取列对象
		List<String> tableNames = new ArrayList<String>();
		// get all table names
		for (Table model : tableModels) {
			tableNames.add(model.getName());
		}
		// batch get table columns
		Map<String, List<Column>> tableColumnsMap = getColumnsByTableName(tableNames);
		// extract table columns from paraTypeMap by table name;
		for (Entry<String, List<Column>> entry : tableColumnsMap.entrySet()) {
			// set Table's columns
			for (Table model : tableModels) {
				if (model.getName().equalsIgnoreCase(entry.getKey())) {
					model.setColumnList(entry.getValue());
				}
			}
		}

		return tableModels;
	}

	/**
	 * 根据表名获取列
	 * 
	 * @param tableName
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private List<Column> getColumnsByTableName(String tableName) {
		String sql = String.format(SQL_GET_COLUMNS, tableName);
		// jdbcHelper.setCurrentDb(currentDb);
		jdbcHelper = JdbcHelper.getInstance();
		Map map = new HashMap();
		List<Column> list = jdbcHelper.queryForList(sql, map,
				new SQLServerColumnMap());
		for (Column model : list) {
			model.setTableName(tableName);
		}
		return list;
	}

	/**
	 * 根据表名获取列。此方法使用批量查询方式。
	 * 
	 * @param tableName
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private Map<String, List<Column>> getColumnsByTableName(
			List<String> tableNames) {
		String sql = SQL_GET_COLUMNS_BATCH;
		Map<String, List<Column>> map = new HashMap<String, List<Column>>();
		if (tableNames != null && tableNames.size() == 0) {
			return map;
		} else {
			StringBuffer buf = new StringBuffer();
			for (String str : tableNames) {
				buf.append("'" + str + "',");
			}
			buf.deleteCharAt(buf.length() - 1);
			sql += " AND B.NAME IN (" + buf.toString() + ") ";
		}
		// jdbcHelper.setCurrentDb(currentDb);
		jdbcHelper = JdbcHelper.getInstance();
		List<Column> columnModels = jdbcHelper.queryForList(sql,
				new HashMap<String, Object>(), new SQLServerColumnMap());

		for (Column columnModel : columnModels) {
			String tableName = columnModel.getTableName();
			if (map.containsKey(tableName)) {
				map.get(tableName).add(columnModel);
			} else {
				List<Column> cols = new ArrayList<Column>();
				cols.add(columnModel);
				map.put(tableName, cols);
			}
		}
		return map;
	}
}
