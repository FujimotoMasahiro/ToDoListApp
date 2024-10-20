package view;

import java.util.List;

import model.Task;

/**
 * TaskViewクラスは、タスクの情報をユーザーに表示する役割を持ちます。
 * このクラスはコンソールにメッセージやタスク一覧を表示します。
 */
public class TaskView {

    /**
     * タスクのリストを表示します。
     * 各タスクのID、説明、完了状態を表示します。
     *
     * @param tasks 表示するタスクのリスト
     */
    public void displayTasks(List<Task> tasks) {
        // タスク一覧の見出しを表示
        System.out.println("=== Task List ===");
        // リスト内の各タスクを1行ずつ表示
        for (Task task : tasks) {
            // taskオブジェクトのtoStringメソッドを利用して、タスクの詳細を表示
            System.out.println(task);
        }
    }

    /**
     * メッセージを表示します。
     * タスクの追加や更新、削除が成功したことをユーザーに伝えるために使用します。
     *
     * @param message 表示するメッセージ
     */
    public void displayMessage(String message) {
        // 指定されたメッセージをコンソールに表示
        System.out.println(message);
    }
}
