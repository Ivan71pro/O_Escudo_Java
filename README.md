# 🛡️ O Escudo - Gerenciador de Remoção de Conteúdo Digital

O **O Escudo** é um software desenvolvido em **Java** para automatizar a organização e o relatório de solicitações de remoção de conteúdos digitais (como endereços expostos no Google Maps ou links ofensivos).

Este projeto faz parte da minha jornada de transição de carreira e estudos em **Análise e Desenvolvimento de Sistemas (ADS)** na Estácio.

## 🚀 Funcionalidades

* **Cadastro Interativo:** Entrada de dados via console (teclado) para Nome, Link e Data.
* **Validação de Dados:** O sistema garante que os links comecem com `http` e que as datas sigam o padrão de 10 caracteres.
* **Organização Automática:** Criação automática de pastas de relatórios (`/Relatorios`).
* **Exportação para Excel:** Geração de arquivos `.csv` com carimbo de data e hora (timestamp) para evitar sobrescrever dados anteriores.

## 🛠️ Tecnologias Utilizadas

* **Linguagem:** Java 17+
* **IDE:** IntelliJ IDEA
* **Conceitos de POO:** Encapsulamento, Construtores e Listas (`ArrayList`).
* **Persistência:** Manipulação de arquivos com `FileWriter` e `PrintWriter`.

## 📂 Como Executar

1. Clone o repositório.
2. Abra o projeto no IntelliJ IDEA.
3. Execute a classe `OEscudoApp.java`.
4. Siga as instruções no console para cadastrar os links.
5. Verifique a pasta `/Relatorios` para encontrar sua planilha pronta para o Excel.

## 📈 Evolução do Projeto

Este projeto nasceu do desejo de criar uma ferramenta útil para o serviço "O Escudo". Começou como um simples script de console e evoluiu para um sistema com validações e persistência de dados.

---
*"Se eu não DESISTIR o que ACONTECE?"* 🚀