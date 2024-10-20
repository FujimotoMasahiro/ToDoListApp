package main;

import java.util.Scanner;

import controller.TaskController;
import model.dao.TaskDAO;
import view.TaskView;

/**
 * メインクラスのTodoAppは、タスク管理アプリケーションのエントリーポイントです。
 * ユーザーの入力を受け付け、タスクの追加、表示、更新、削除を行います。
 * アプリケーションはコンソールベースで動作します。
 */
public class TodoApp {
    /**
     * アプリケーションのエントリーポイント。
     * ユーザーからの入力を基にタスク管理の操作を実行します。
     *
     * @param args コマンドライン引数（本アプリケーションでは使用しません）
     */
    public static void main(String[] args) {
        // タスク管理のデータベース操作を担当するDAOオブジェクトを生成
        TaskDAO taskDAO = new TaskDAO();
        // タスクの表示を担当するビューオブジェクトを生成
        TaskView taskView = new TaskView();
        // コントローラを生成し、DAOとビューを連携させる
        TaskController taskController = new TaskController(taskDAO, taskView);
        // ユーザー入力を処理するためのScannerオブジェクトを生成
        Scanner scanner = new Scanner(System.in);

        // アプリケーションの終了フラグ
        boolean exit = false;

        // ユーザーが終了を選択するまで繰り返し処理を実行
        while (!exit) {
            // ユーザーに操作の選択肢を表示
            System.out.println("\n1. タスク追加");
            System.out.println("2. タスク一覧表示");
            System.out.println("3. タスクの完了、未完了を更新する");
            System.out.println("4. タスク削除");
            System.out.println("5. 終了");
            System.out.print("選択してください: ");
            int choice = scanner.nextInt(); // ユーザーの選択を取得
            scanner.nextLine(); // 改行文字を消費して次の入力を受け付ける準備

            // ユーザーの選択に基づき処理を実行
            switch (choice) {
                case 1:
                    // タスクの追加を処理
                    System.out.print("タスクの説明を入力してください: ");
                    String description = scanner.nextLine(); // タスクの説明を取得
                    taskController.addTask(description); // コントローラを通じてタスクを追加
                    break;
                case 2:
                    // すべてのタスクを表示
                    taskController.showAllTasks(); // コントローラを通じてタスク一覧を表示
                    break;
                case 3:
                    // タスクの完了/未完了状態を更新
                    System.out.print("更新するタスクのIDを入力してください: ");
                    int id = scanner.nextInt(); // 更新するタスクのIDを取得
                    boolean status = false; // タスクの状態を格納する変数
                    boolean validInput = false; // 入力の有効性を判定するフラグ

                    // ユーザーが正しい入力を行うまで繰り返す
                    while (!validInput) {
                        System.out.print("完了済みの場合はtrue、未完了の場合はfalseを入力してください: ");
                        if (scanner.hasNextBoolean()) {
                            status = scanner.nextBoolean(); // 正しいboolean値を取得
                            validInput = true; // 入力が正しい場合はループを抜ける
                        } else {
                            System.out.println("無効な入力です。'true'または'false'を入力してください。");
                            scanner.next(); // 無効な入力を消費して再入力を促す
                        }
                    }
                    taskController.updateTaskStatus(id, status); // コントローラを通じてタスクの状態を更新
                    break;
                case 4:
                    // タスクの削除を処理
                    System.out.print("削除するタスクのIDを入力してください: ");
                    int deleteId = scanner.nextInt(); // 削除するタスクのIDを取得
                    taskController.deleteTask(deleteId); // コントローラを通じてタスクを削除
                    break;
                case 5:
                    // アプリケーションの終了処理
                    exit = true; // 終了フラグを設定
                    System.out.println("終了します。");
                    break;
                default:
                    // ユーザーが不正な選択を行った場合の処理
                    System.out.println("無効な選択です。もう一度選択してください。");
            }
        }

        // Scannerオブジェクトを閉じてリソースを解放
        scanner.close();
    }
}
