import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class conexao {
    public static void main(String[] args) {
        String host = "cdzeliseos-javaprojetosenai.f.aivencloud.com";
        int porta = 21730;
        String nomeDoBanco = "defaultdb";
        String usuario = "avnadmin";
        String senha = "AVNS_GYLK_3Tt6FLt1cBwmui";
        String url = "jdbc:mysql://" + host + ":" + porta + "/" + nomeDoBanco + "?ssl-mode=REQUIRED";

        try {
            Connection conn = DriverManager.getConnection(url, usuario, senha);
            Statement stmt = conn.createStatement();


            String[] queries = {
                    "CREATE TABLE IF NOT EXISTS Cavaleiro(id INT AUTO_INCREMENT PRIMARY KEY, nome VARCHAR(100) NOT NULL, armadura VARCHAR(50), nivel INT)",
                    "CREATE TABLE IF NOT EXISTS Mestre(id INT AUTO_INCREMENT PRIMARY KEY, nome VARCHAR(100) NOT NULL, templo VARCHAR(100))",
                    "CREATE TABLE IF NOT EXISTS Treinamento(id INT AUTO_INCREMENT PRIMARY KEY, descricao TEXT, local VARCHAR(100), data DATE)",
                    "CREATE TABLE IF NOT EXISTS Aula(id INT AUTO_INCREMENT PRIMARY KEY, cavaleiro_id INT, mestre_id INT, treinamento_id INT, resultado VARCHAR(100), FOREIGN KEY (cavaleiro_id) REFERENCES Cavaleiro(id), FOREIGN KEY (mestre_id) REFERENCES Mestre(id), FOREIGN KEY (treinamento_id) REFERENCES Treinamento(id))",
                    "CREATE TABLE IF NOT EXISTS Evolucao(id INT AUTO_INCREMENT PRIMARY KEY, cavaleiro_id INT, nivel_antigo INT, nivel_novo INT, data DATE, FOREIGN KEY (cavaleiro_id) REFERENCES Cavaleiro(id))"
            };

            for (String query : queries) {
                stmt.executeUpdate(query);
            }


            String[] inserts = {
                    "INSERT INTO Cavaleiro(nome, armadura, nivel) VALUES ('Seiya', 'Pégaso', 1)",
                    "INSERT INTO Mestre(nome, templo) VALUES ('Mestre Ancião', 'Cinco Picos de Rozan')",
                    "INSERT INTO Treinamento(descricao, local, data) VALUES ('Treino de Cosmo', 'Santuário', '2025-06-01')",
                    "INSERT INTO Aula(cavaleiro_id, mestre_id, treinamento_id, resultado) VALUES (1, 1, 1, 'Aprovado')",
                    "INSERT INTO Evolucao(cavaleiro_id, nivel_antigo, nivel_novo, data) VALUES (1, 1, 2, '2025-06-02')"
            };

            for (String insert : inserts) {
                stmt.executeUpdate(insert);
            }

            System.out.println("Tabelas criadas e dados inseridos com sucesso!");

            conn.close();
        } catch (SQLException e) {
            System.out.println("Erro ao conectar ao banco de dados: " + e.getMessage());
        }
    }
}
