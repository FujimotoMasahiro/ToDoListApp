package model;

/**
 * Taskクラスは、タスクを表現するモデルクラスです。
 * 各タスクにはID、説明、完了状態が含まれます。
 * このクラスを通じてタスクのデータを管理します。
 */
public class Task {
    // タスクのIDを表すフィールド。データベースで一意に識別される。
    private int id;
    // タスクの説明を表すフィールド。タスクの内容が格納される。
    private String description;
    // タスクの完了状態を表すフィールド。trueならタスクは完了、falseなら未完了。
    private boolean status;

    /**
     * コンストラクタ。
     * 指定されたID、説明、完了状態を持つ新しいTaskオブジェクトを作成します。
     * 
     * @param id タスクの一意のID
     * @param description タスクの説明
     * @param status タスクの完了状態（true: 完了、false: 未完了）
     */
    public Task(int id, String description, boolean status) {
        this.id = id;
        this.description = description;
        this.status = status;
    }

    /**
     * タスクのIDを取得します。
     * 
     * @return タスクのID
     */
    public int getId() {
        return id;
    }

    /**
     * タスクのIDを設定します。
     * 
     * @param id タスクに設定する新しいID
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * タスクの説明を取得します。
     * 
     * @return タスクの説明
     */
    public String getDescription() {
        return description;
    }

    /**
     * タスクの説明を設定します。
     * 
     * @param description タスクに設定する新しい説明
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * タスクの完了状態を取得します。
     * 
     * @return タスクが完了している場合はtrue、未完了の場合はfalse
     */
    public boolean isStatus() {
        return status;
    }

    /**
     * タスクの完了状態を設定します。
     * 
     * @param status タスクに設定する新しい完了状態（true: 完了、false: 未完了）
     */
    public void setStatus(boolean status) {
        this.status = status;
    }

    /**
     * タスクの情報を文字列として返します。
     * タスクのID、説明、完了状態をわかりやすい形式で表示します。
     * 
     * @return タスクのID、説明、完了状態を含む文字列
     */
    @Override
    public String toString() {
        // 状態がtrueなら「完了」、falseなら「未完了」と表示する
        return "ID: " + id + ", Description: " + description + ", Status: " + (status ? "完了" : "未完了");
    }
}
