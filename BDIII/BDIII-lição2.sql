CREATE DATABASE dbempresa;

CREATE TABLE dbempresa.cliente ( 
cod_cli INT(3) NOT NULL AUTO_INCREMENT,
nome VARCHAR(30) NOT NULL,
endereco VARCHAR(201) NOT NULL, 
cidade VARCHAR(20) NOT NULL, 
telefone VARCHAR(9) NOT NULL, 
PRIMARY KEY (Cod_Cli)
);

CREATE TABLE dbempresa.departamento (
cod_depto INT NOT NULL AUTO_INCREMENT,
nome VARCHAR(20),
ramal INT(2),
PRIMARY KEY (cod_depto)
);

CREATE TABLE dbempresa.funcionario (
cod_func INT NOT NULL AUTO_INCREMENT,
nome VARCHAR(20),
data_admissao DATE,
cargo VARCHAR(20),
salario REAL,
cod_depto INT,
PRIMARY KEY (cod_func),
FOREIGN KEY (cod_depto) REFERENCES departamento(cod_depto)
);

CREATE TABLE dbempresa.venda (
cod_venda INT NOT NULL AUTO_INCREMENT,
data DATE NOT NULL,
cod_cli INT,
cod_depto INT,
PRIMARY KEY (cod_venda),
FOREIGN KEY (cod_cli) REFERENCES cliente(cod_cli),
FOREIGN KEY (cod_depto) REFERENCES funcionario(cod_depto)
); 

CREATE TABLE dbempresa.categoria (
cod_cat INT NOT NULL AUTO_INCREMENT,
descricao VARCHAR(200),
PRIMARY KEY (cod_cat)
);

CREATE TABLE dbempresa.produto (
cod_prod INT NOT NULL AUTO_INCREMENT,
descricao VARCHAR(200),
preco REAL NOT NULL,
estoque VARCHAR(200),
cod_cat INT,
PRIMARY KEY (cod_prod),
FOREIGN KEY (cod_cat) REFERENCES categoria(cod_cat)
);

CREATE TABLE dbempresa.itens (
qtde INT,
cod_venda INT,
cod_prod INT,
FOREIGN KEY (cod_venda) REFERENCES venda(cod_venda),
FOREIGN KEY (cod_prod) REFERENCES produto(cod_prod)
);

USE dbempresa
GO
CREATE VIEW vwFuncionario AS
SELECT cod_func,
       nome,
       data_admissao
FROM funcionario;
GO

CREATE VIEW vwProduto AS
SELECT cod_prod,
       descricao,
       preco
FROM prooduto;