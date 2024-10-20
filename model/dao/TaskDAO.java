package model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Task;

/**
 * TaskDAOクラスは、データベースとの接続およびタスクデータのCRUD操作（作成、読み取り、更新、削除）を
 * 担当します。データベースにはPostgreSQLを使用し、JDBCを利用して接続を行います。
 */
public class TaskDAO {
    // データベース接続に必要な情報を定義
    private static final String URL = "jdbc:postgresql://localhost:5432/todo_app"; // PostgreSQLデータベースのURL
    private static final String USER = "postgres"; // データベースユーザー名
    private static final String PASSWORD = "your_password"; // データベースのパスワード

    /**
     * データベースへの接続を取得します。
     * 
     * @return Connection データベース接続オブジェクト
     * @throws SQLException データベースへの接続が失敗した場合にスローされます
     */
    public Connection getConnection() throws SQLException {
        // DriverManagerを使用してデータベースに接続
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    /**
     * 新しいタスクをデータベースに追加します。
     * 
     * @param task 追加するTaskオブジェクト（タスクの説明と状態を含む）
     */
    public void addTask(Task task) {
        // タスクを追加するSQL文を定義。?はプレースホルダとして使用される。
        String sql = "INSERT INTO tasks (description, status) VALUES (?, ?)";
        // try-with-resources構文を使用して、リソース（接続とPreparedStatement）を自動的に閉じる
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            // プレースホルダにタスクの説明と状態をセット
            pstmt.setString(1, task.getDescription());
            pstmt.setBoolean(2, task.isStatus());
            // SQL文を実行して、タスクをデータベースに追加
            pstmt.executeUpdate();
        } catch (SQLException e) {
            // SQL実行中にエラーが発生した場合、スタックトレースを出力
            e.printStackTrace();
        }
    }

    /**
     * データベースからすべてのタスクを取得します。
     * 
     * @return List<Task> データベースに保存されているすべてのTaskオブジェクトのリスト
     */
    public List<Task> getAllTasks() {
        List<Task> tasks = new ArrayList<>(); // タスクを格納するリストを生成
        String sql = "SELECT * FROM tasks Order by id"; // タスクを取得するSQL文
        // try-with-resources構文を使用して、リソースを自動的に閉じる
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            // 結果セット（ResultSet）からタスクデータを1行ずつ読み取る
            while (rs.next()) {
                // ResultSetからデータを取得し、Taskオブジェクトを生成
                Task task = new Task(
                    rs.getInt("id"),
                    rs.getString("description"),
                    rs.getBoolean("status")
                );
                // 生成したTaskオブジェクトをリストに追加
                tasks.add(task);
            }
        } catch (SQLException e) {
            // SQL実行中にエラーが発生した場合、スタックトレースを出力
            e.printStackTrace();
        }
        // 取得したタスクのリストを返す
        return tasks;
    }

    /**
     * 指定されたIDのタスクの完了状態を更新します。
     * 
     * @param id 更新するタスクのID
     * @param status 新しい完了状態（true: 完了, false: 未完了）
     */
    public void updateTaskStatus(int id, boolean status) {
        // タスクの状態を更新するSQL文を定義。?はプレースホルダとして使用される。
        String sql = "UPDATE tasks SET status = ? WHERE id = ?";
        // try-with-resources構文を使用して、リソースを自動的に閉じる
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            // プレースホルダに完了状態とタスクのIDをセット
            pstmt.setBoolean(1, status);
            pstmt.setInt(2, id);
            // SQL文を実行して、指定されたIDのタスクの状態を更新
            pstmt.executeUpdate();
        } catch (SQLException e) {
            // SQL実行中にエラーが発生した場合、スタックトレースを出力
            e.printStackTrace();
        }
    }

    /**
     * 指定されたIDのタスクをデータベースから削除します。
     * 
     * @param id 削除するタスクのID
     */
    public void deleteTask(int id) {
        // タスクを削除するSQL文を定義。?はプレースホルダとして使用される。
        String sql = "DELETE FROM tasks WHERE id = ?";
        // try-with-resources構文を使用して、リソースを自動的に閉じる
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            // プレースホルダにタスクのIDをセット
            pstmt.setInt(1, id);
            // SQL文を実行して、指定されたIDのタスクを削除
            pstmt.executeUpdate();
        } catch (SQLException e) {
            // SQL実行中にエラーが発生した場合、スタックトレースを出力
            e.printStackTrace();
        }
    }
}
