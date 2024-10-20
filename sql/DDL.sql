CREATE TABLE tasks (
    id SERIAL PRIMARY KEY, -- 自動で増加する一意のIDを持つカラム
    description TEXT NOT NULL, -- タスクの説明を保存するカラム
    status BOOLEAN DEFAULT FALSE -- タスクの完了状態を保存するカラム。デフォルトは未完了（false）
);
