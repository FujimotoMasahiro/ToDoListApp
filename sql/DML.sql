-- サンプルデータを5件挿入します。各行が1つのタスクを表します。
INSERT INTO tasks (description, status) VALUES
('買い物に行く', false), -- 買い物のタスク。未完了の状態
('読書をする', true), -- 読書のタスク。完了の状態
('ランニングをする', false), -- ランニングのタスク。未完了の状態
('部屋を掃除する', true), -- 部屋掃除のタスク。完了の状態
('友人と会う', false); -- 友人と会うタスク。未完了の状態
