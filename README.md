# dbeliseos
Teste de conexão banco de dados feito através do Senai Lauro de Freitas.

# Como se conectar com o banco de dados:

Para executar esse programa, você precisará ter o Java Development Kit (JDK) instalado no seu computador e um ambiente de desenvolvimento integrado (IDE) como o Eclipse, IntelliJ IDEA ou NetBeans. Além disso, você precisará ter o driver JDBC do MySQL adicionado ao seu projeto.

Aqui estão os passos para executar o programa:

1. Instale o JDK: Certifique-se de que você tem o JDK instalado no seu computador. Você pode baixá-lo no site oficial da Oracle.
2. Crie um projeto Java: Abra sua IDE e crie um novo projeto Java.
3. Adicione o driver JDBC do MySQL: Baixe o driver JDBC do MySQL no site oficial do MySQL e adicione-o ao seu projeto ou adicione o driver JDBC do mySQL disponibilizado no próprio repositório. Isso pode variar dependendo da sua IDE.
4. Copie o código: Copie o código fornecido e cole-o em uma classe Java no seu projeto.
5. Configure as credenciais do banco de dados: Certifique-se de que as credenciais do banco de dados (host, porta, nome do banco, usuário e senha) estejam corretas.
6. Execute o programa: Execute o programa clicando no botão de execução da sua IDE ou usando o comando java no terminal.

Se tudo estiver configurado corretamente, o programa criará as tabelas e inserirá os dados no banco de dados MySQL.

## Observações:

- Certifique-se de que o banco de dados MySQL esteja configurado para aceitar conexões remotas e que as credenciais sejam válidas.
- Se você estiver usando uma versão mais recente do MySQL, pode ser necessário ajustar a string de conexão para incluir parâmetros adicionais de segurança.
- Lembre-se de que a senha do banco de dados está hardcoded no código, o que não é recomendado para produção. Em um ambiente de produção, você deve usar uma forma segura de armazenar e gerenciar credenciais.

# Conexão com o Banco de Dados no Aiven

1. Crie uma conta no Aiven: Se você ainda não tiver uma conta no Aiven, crie uma conta gratuita no site oficial do Aiven.
2. Crie um serviço de banco de dados: No painel de controle do Aiven, crie um novo serviço de banco de dados MySQL.
3. Configure as credenciais: Anote as credenciais do banco de dados, incluindo o host, porta, nome do banco, usuário e senha.
4. Baixe o certificado SSL: Baixe o certificado SSL do Aiven para garantir a conexão segura com o banco de dados.

# Configurando o IntelliJ

1. Instale o IntelliJ: Se você ainda não tiver o IntelliJ instalado, baixe e instale a versão Community ou Ultimate.
2. Crie um novo projeto: Abra o IntelliJ e crie um novo projeto Java.
3. Adicione o driver JDBC do MySQL: Adicione o driver JDBC do MySQL ao seu projeto. Você pode fazer isso adicionando a dependência ao arquivo pom.xml (se você estiver usando Maven) ou baixando o driver manualmente.
4. Configure as credenciais do banco de dados: No código, configure as credenciais do banco de dados com os valores obtidos no Aiven.

# Código de Conexão
O código de conexão com o banco de dados é o seguinte:

        String host = "cdzeliseos-javaprojetosenai.f.aivencloud.com";
        int porta = 21730;
        String nomeDoBanco = "defaultdb";
        String usuario = "avnadmin";
        String senha = "AVNS_GYLK_3Tt6FLt1cBwmui";
        String url = "jdbc:mysql://" + host + ":" + porta + "/" + nomeDoBanco + "?ssl-mode=REQUIRED";

