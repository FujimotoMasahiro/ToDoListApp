package controller;

import java.util.List;

import model.Task;
import model.dao.TaskDAO;
import view.TaskView;

/**
 * TaskControllerクラスは、モデル（TaskDAO）とビュー（TaskView）を仲介し、
 * アプリケーションのビジネスロジックを処理する役割を持ちます。
 * このクラスを通じて、タスクの追加、表示、更新、削除を実行します。
 */
public class TaskController {
    private TaskDAO taskDAO; // データベースとやり取りを行うオブジェクト
    private TaskView taskView; // ユーザーにデータを表示するためのオブジェクト

    /**
     * TaskControllerのコンストラクタ。
     * データベース操作と表示操作を担うオブジェクトを受け取ります。
     *
     * @param taskDAO データベース操作を担当するTaskDAOオブジェクト
     * @param taskView 表示操作を担当するTaskViewオブジェクト
     */
    public TaskController(TaskDAO taskDAO, TaskView taskView) {
        this.taskDAO = taskDAO;
        this.taskView = taskView;
    }

    /**
     * 新しいタスクを追加します。
     * 入力された説明を持つ未完了のタスクをデータベースに保存し、
     * タスクが追加されたメッセージを表示します。
     *
     * @param description タスクの説明（内容）
     */
    public void addTask(String description) {
        // 新しいタスクを作成。idは0で、データベース側で自動的に割り当てられます。
        Task task = new Task(0, description, false);
        // TaskDAOを使用してデータベースにタスクを追加します。
        taskDAO.addTask(task);
        // タスク追加のメッセージをビューに表示します。
        taskView.displayMessage("タスクが追加されました。");
    }

    /**
     * すべてのタスクを表示します。
     * データベースからすべてのタスクを取得し、ビューに表示します。
     */
    public void showAllTasks() {
        // TaskDAOを使用して、すべてのタスクを取得します。
        List<Task> tasks = taskDAO.getAllTasks();
        // TaskViewを使用して、取得したタスクをユーザーに表示します。
        taskView.displayTasks(tasks);
    }

    /**
     * 指定されたIDのタスクの状態を更新します。
     * タスクの完了状況（true: 完了、false: 未完了）を変更し、
     * 状態が更新されたメッセージを表示します。
     *
     * @param id 更新するタスクのID
     * @param status 新しい完了状況（true: 完了、false: 未完了）
     */
    public void updateTaskStatus(int id, boolean status) {
        // TaskDAOを使用して、指定されたIDのタスクの状態を更新します。
        taskDAO.updateTaskStatus(id, status);
        // タスクの状態が更新されたことをビューに表示します。
        taskView.displayMessage("タスクの状態が更新されました。");
    }

    /**
     * 指定されたIDのタスクを削除します。
     * データベースから該当するタスクを削除し、
     * 削除されたメッセージを表示します。
     *
     * @param id 削除するタスクのID
     */
    public void deleteTask(int id) {
        // TaskDAOを使用して、指定されたIDのタスクをデータベースから削除します。
        taskDAO.deleteTask(id);
        // タスクが削除されたことをビューに表示します。
        taskView.displayMessage("タスクが削除されました。");
    }
}
