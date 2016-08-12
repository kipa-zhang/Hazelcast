package com.kipa.test.db;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DBHelper {

	private static String driverClassName;
	private static String url;
	private static String user;
	private static String password;
	protected Connection conn;
	protected PreparedStatement st;
	protected ResultSet rs;
	/*
	 * 初始化配置信息
	 */
	static {
		driverClassName = "com.mysql.jdbc.Driver";
		url = "jdbc:mysql://10.41.87.73:3306/cms";
		user = "root";
		password = "root";
	}

	/**
	 * 获取数据库连接对象
	 */
	public Connection getConnection() {
		try {
			Class.forName(driverClassName);
			conn = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return conn;
	}

	/**
	 * 操作完数据库后关闭资源
	 */
	public void close() {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (st != null) {
			try {
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 执行一条insert,update,delete等sql语句
	 */
	public int updateDB(String sql, Object... args) {
		int a = 0;

		this.getConnection();
		try {

			st = conn.prepareStatement(sql);
			if (args.length > 0) {
				for (int i = 1; i <= args.length; i++) {
					st.setObject(i, args[i - 1]);
				}
			}
			a = st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.close();
		}
		return a;
	}

	/**
	 * 执行insert语句，如果需要返回主键，可以使用这个方法
	 */
	public int insertReturnAutoId(String sql, Object... args) {
		int autoId = 0;

		this.getConnection();
		try {

			st = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			if (args!= null && args.length > 0) {
				for (int i = 1; i <= args.length; i++) {
					st.setObject(i, args[i - 1]);
				}
			}
			st.executeUpdate();

			rs = st.getGeneratedKeys();
			if (rs.next())
				autoId = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.close();
		}
		return autoId;
	}

	/**
	 * 根据id查找记录，最多也只会返回一行
	 * 
	 * @param sql
	 * @param id
	 * @return 一行数据封装在一个list对象
	 */
	public List<Object> findById(String sql, Object id) {
		List<Object> row = null;
		this.getConnection();

		try {
			st = conn.prepareStatement(sql);
			st.setObject(1, id);
			rs = st.executeQuery();

			ResultSetMetaData rmd = rs.getMetaData();
			int columnCount = rmd.getColumnCount();
			if (rs.next()) {
				row = new ArrayList<Object>();

				for (int i = 1; i <= columnCount; i++) {
					row.add(rs.getObject(i));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.close();
		}

		return row;
	}

	/**
	 * 可以返回多行的查询，每一行也是封装在一个list中
	 */
	public List<List<Object>> findObjects(String sql, Object... args) {
		List<List<Object>> rows = new ArrayList<List<Object>>();

		this.getConnection();

		try {
			st = conn.prepareStatement(sql);

			if (args.length > 0) {
				for (int i = 1; i <= args.length; i++) {
					st.setObject(i, args[i - 1]);
				}
			}

			rs = st.executeQuery();
			List<Object> row = null;

			ResultSetMetaData rmd = rs.getMetaData();
			int columnCount = rmd.getColumnCount();

			while (rs.next()) {
				row = new ArrayList<Object>();
				for (int i = 1; i <= columnCount; i++) {
					row.add(rs.getObject(i));
				}

				rows.add(row);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.close();
		}
		return rows;
	}

	/**
	 * 根据id查询，返回的是一个javaBean对象
	 * 
	 * @param <T>
	 */
	public <T> T findObjectById(String sql, Object id, Class<T> cls) {
		T t = null;
		this.getConnection();

		try {
			st = conn.prepareStatement(sql);
			st.setObject(1, id);
			rs = st.executeQuery();

			ResultSetMetaData rmd = rs.getMetaData();
			int columnCount = rmd.getColumnCount();
			if (rs.next()) {
				t = cls.newInstance();
				String curColName = null;

				for (int i = 1; i <= columnCount; i++) {
					curColName = rmd.getColumnName(i);
					Field field = cls.getDeclaredField(curColName);
					field.setAccessible(true);
					field.set(t, rs.getObject(i));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} finally {
			this.close();
		}

		return t;
	}

	/**
	 * 返回一个集合，要注意的集合中的元素是一个javaBean
	 * 
	 * @param <T>
	 */
	public <T> List<T> findObjectsByCondition(String sql, Class<T> cls,
			Object... args) {
		List<T> ts = new ArrayList<T>();
		this.getConnection();

		try {
			st = conn.prepareStatement(sql);
			if (args.length > 0) {
				for (int i = 1; i <= args.length; i++) {
					st.setObject(i, args[i - 1]);
				}
			}

			rs = st.executeQuery();

			ResultSetMetaData rmd = rs.getMetaData();
			int columnCount = rmd.getColumnCount();
			T t = null;
			while (rs.next()) {
				t = cls.newInstance();
				String curColName = null;

				for (int i = 1; i <= columnCount; i++) {
					curColName = rmd.getColumnName(i);
					Field field = cls.getDeclaredField(curColName);
					field.setAccessible(true);
					field.set(t, rs.getObject(i));
				}
				ts.add(t);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} finally {
			this.close();
		}

		return ts;
	}

}
