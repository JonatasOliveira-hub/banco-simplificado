INSERT INTO USUARIO (nome_Completo, cpf_cnpj, email, senha, saldo)
VALUES 
('Loja Tech', '11111111000101', 'lojatech@exemplo.com', 'senha123', 1000.00),
('Loja Games', '22222222000102', 'lojagames@exemplo.com', 'senha123', 1500.00),
('Loja Roupas', '33333333000103', 'lojaroupas@exemplo.com', 'senha123', 800.00),
('Loja Pet', '44444444000104', 'lojapet@exemplo.com', 'senha123', 600.00),
('Loja Café', '55555555000105', 'lojacafe@exemplo.com', 'senha123', 1200.00),
('João Silva', '12345678900', 'joao@exemplo.com', 'senha123', 500.00),
('Maria Oliveira', '23456789011', 'maria@exemplo.com', 'senha123', 750.00),
('Carlos Souza', '34567890122', 'carlos@exemplo.com', 'senha123', 300.00),
('Ana Lima', '45678901233', 'ana@exemplo.com', 'senha123', 1200.00),
('Fernanda Costa', '56789012344', 'fernanda@exemplo.com', 'senha123', 950.00);

-- Inserir os IDs na tabela `Lojista`
INSERT INTO lojista (id) VALUES (1), (2), (3), (4), (5);

-- Inserir os IDs na tabela `usuario_comum`
INSERT INTO usuario_comum (id) VALUES (6), (7), (8), (9), (10);