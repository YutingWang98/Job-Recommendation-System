package db;

import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.Connection;

public class MySQLTableCreation {
	// Run this as Java application to reset the database.
	public static void main(String[] args) {
		try {
			// Step 1 Connect to MySQL.创建连接
			System.out.println("Connecting to " + MySQLDBUtil.URL);
			// 14，15是关键
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			Connection conn = DriverManager.getConnection(MySQLDBUtil.URL);

			if (conn == null) {
				return;
			}

			// Step 2 Drop tables in case they exist. //注意顺序，先删除有foreign
			// key指向别人的table，再删除被指向的
			Statement statement = conn.createStatement();
			String sql = "DROP TABLE IF EXISTS keywords";
			statement.executeUpdate(sql); // 删除操作，executeUpdate()；如果是读操作，用executeQuery（）

			sql = "DROP TABLE IF EXISTS history";
			statement.executeUpdate(sql); // 可以重复利用

			sql = "DROP TABLE IF EXISTS items";
			statement.executeUpdate(sql);

			sql = "DROP TABLE IF EXISTS users";
			statement.executeUpdate(sql);

			// Step 3 Create new tables
			sql = "CREATE TABLE items (" + "item_id VARCHAR(255) NOT NULL," // string 是VARCHAR(255)，最多255个字符组成的string
					+ "name VARCHAR(255)," + "address VARCHAR(255)," + "image_url VARCHAR(255)," + "url VARCHAR(255),"
					+ "PRIMARY KEY (item_id)" + ")";
			statement.executeUpdate(sql); // 创建

			sql = "CREATE TABLE users (" + "user_id VARCHAR(255) NOT NULL," + "password VARCHAR(255) NOT NULL,"
					+ "first_name VARCHAR(255)," + "last_name VARCHAR(255)," + "PRIMARY KEY (user_id)" + ")";
			statement.executeUpdate(sql);

			sql = "CREATE TABLE keywords (" + "item_id VARCHAR(255) NOT NULL," + "keyword VARCHAR(255) NOT NULL,"
					+ "PRIMARY KEY (item_id, keyword)," + "FOREIGN KEY (item_id) REFERENCES items(item_id)" // foreign
																											// key
					+ ")";
			statement.executeUpdate(sql);

			sql = "CREATE TABLE history (" + "user_id VARCHAR(255) NOT NULL," + "item_id VARCHAR(255) NOT NULL,"
					+ "last_favor_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP," // 不能为null，要设置默认值
					+ "PRIMARY KEY (user_id, item_id)," + "FOREIGN KEY (user_id) REFERENCES users(user_id),"
					+ "FOREIGN KEY (item_id) REFERENCES items(item_id)" + ")";
			statement.executeUpdate(sql);

			// Step 4: insert fake user 1111/3229c1097c00d497a0fd282d586be050
			sql = "INSERT INTO users VALUES('1111', '3229c1097c00d497a0fd282d586be050', 'John', 'Smith')";
			statement.executeUpdate(sql);

			conn.close(); // close 是个比较好的习惯
			System.out.println("Import done successfully");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
