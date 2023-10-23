DROP TABLE IF EXISTS dogs;

CREATE TABLE dogs (
  id int unsigned AUTO_INCREMENT,
  name VARCHAR(20) NOT NULL,
  dogSex VARCHAR(20)NOT NULL,
  age VARCHAR(20)NOT NULL,
  dogBreed VARCHAR(20)NOT NULL,
  region VARCHAR(20)NOT NULL,
  PRIMARY KEY(id)
);

INSERT INTO dogs (name, dogSex, age, dogBreed, region) VALUES ("シロ", "オス", "1歳", "フレンチ・ブルドック", "東北");
INSERT INTO dogs (name, dogSex, age, dogBreed, region) VALUES ("豆きち", "オス", "3-4ヶ月", "柴犬", "関東");
INSERT INTO dogs (name, dogSex, age, dogBreed, region) VALUES ("チョコ", "メス", "2歳", "トイプードル", "関東");
INSERT INTO dogs (name, dogSex, age, dogBreed, region) VALUES ("ナナ", "メス", "1歳", "ミックス犬", "関西");
INSERT INTO dogs (name, dogSex, age, dogBreed, region) VALUES ("ポテト", "オス", "2歳", "チワワ", "関東");
