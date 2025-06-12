import java.sql.*;
import java.util.Scanner;

public class conexao {
    private static final String HOST = "cdzeliseos-javaprojetosenai.f.aivencloud.com";
    private static final int PORTA = 21730;
    private static final String BANCO = "defaultdb";
    private static final String USER = "avnadmin";
    private static final String PASSWORD = "AVNS_GYLK_3Tt6FLt1cBwmui";
    private static final String URL = "jdbc:mysql://" + HOST + ":" + PORTA + "/" + BANCO + "?ssl-mode=REQUIRED";

    private static Connection conn;
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        try {
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Conexão realizada com sucesso!");

            boolean running = true;
            while (running) {
                System.out.println("\nEscolha a tabela para CRUD:");
                System.out.println("1 - CAVALEIRO");
                System.out.println("2 - MESTRE");
                System.out.println("3 - TREINAMENTO");
                System.out.println("4 - AULA");
                System.out.println("5 - EVOLUCAO");
                System.out.println("0 - Sair");
                int opc = Integer.parseInt(sc.nextLine());

                switch (opc) {
                    case 1 -> crudCavaleiro();
                    case 2 -> crudMestre();
                    case 3 -> crudTreinamento();
                    case 4 -> crudAula();
                    case 5 -> crudEvolucao();
                    case 0 -> running = false;
                    default -> System.out.println("Opção inválida!");
                }
            }

            conn.close();
            System.out.println("Conexão encerrada.");

        } catch (SQLException e) {
            System.out.println("Erro ao conectar ao banco de dados: " + e.getMessage());
        }
    }

    // ---------- CRUD CAVALEIRO ----------
    private static void crudCavaleiro() throws SQLException {
        System.out.println("\nCRUD CAVALEIRO");
        System.out.println("1 - Inserir");
        System.out.println("2 - Listar");
        System.out.println("3 - Atualizar");
        System.out.println("4 - Deletar");
        int opc = Integer.parseInt(sc.nextLine());

        switch (opc) {
            case 1 -> inserirCavaleiro();
            case 2 -> listarCavaleiros();
            case 3 -> atualizarCavaleiro();
            case 4 -> deletarCavaleiro();
            default -> System.out.println("Opção inválida.");
        }
    }

    private static void inserirCavaleiro() throws SQLException {
        System.out.print("Nome: ");
        String nome = sc.nextLine();
        System.out.print("Armadura: ");
        String armadura = sc.nextLine();
        System.out.print("Nível: ");
        int nivel = Integer.parseInt(sc.nextLine());

        String sql = "INSERT INTO Cavaleiro(nome, armadura, nivel) VALUES (?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, nome);
        ps.setString(2, armadura);
        ps.setInt(3, nivel);
        int r = ps.executeUpdate();
        System.out.println(r > 0 ? "Cavaleiro inserido com sucesso." : "Erro ao inserir cavaleiro.");
    }

    private static void listarCavaleiros() throws SQLException {
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM Cavaleiro");
        while (rs.next()) {
            System.out.printf("ID: %d | Nome: %s | Armadura: %s | Nível: %d%n",
                    rs.getInt("id"), rs.getString("nome"), rs.getString("armadura"), rs.getInt("nivel"));
        }
    }

    private static void atualizarCavaleiro() throws SQLException {
        System.out.print("ID do cavaleiro a atualizar: ");
        int id = Integer.parseInt(sc.nextLine());
        System.out.print("Novo nome: ");
        String nome = sc.nextLine();
        System.out.print("Nova armadura: ");
        String armadura = sc.nextLine();
        System.out.print("Novo nível: ");
        int nivel = Integer.parseInt(sc.nextLine());

        String sql = "UPDATE Cavaleiro SET nome = ?, armadura = ?, nivel = ? WHERE id = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, nome);
        ps.setString(2, armadura);
        ps.setInt(3, nivel);
        ps.setInt(4, id);
        int r = ps.executeUpdate();
        System.out.println(r > 0 ? "Cavaleiro atualizado." : "Erro na atualização.");
    }

    private static void deletarCavaleiro() throws SQLException {
        System.out.print("ID do cavaleiro a deletar: ");
        int id = Integer.parseInt(sc.nextLine());
        String sql = "DELETE FROM Cavaleiro WHERE id = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, id);
        int r = ps.executeUpdate();
        System.out.println(r > 0 ? "Cavaleiro deletado." : "Erro na exclusão.");
    }

    // ---------- CRUD MESTRE ----------
    private static void crudMestre() throws SQLException {
        System.out.println("\nCRUD MESTRE");
        System.out.println("1 - Inserir");
        System.out.println("2 - Listar");
        System.out.println("3 - Atualizar");
        System.out.println("4 - Deletar");
        int opc = Integer.parseInt(sc.nextLine());

        switch (opc) {
            case 1 -> inserirMestre();
            case 2 -> listarMestres();
            case 3 -> atualizarMestre();
            case 4 -> deletarMestre();
            default -> System.out.println("Opção inválida.");
        }
    }

    private static void inserirMestre() throws SQLException {
        System.out.print("Nome: ");
        String nome = sc.nextLine();
        System.out.print("Templo: ");
        String templo = sc.nextLine();

        String sql = "INSERT INTO Mestre(nome, templo) VALUES (?, ?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, nome);
        ps.setString(2, templo);
        int r = ps.executeUpdate();
        System.out.println(r > 0 ? "Mestre inserido com sucesso." : "Erro ao inserir mestre.");
    }

    private static void listarMestres() throws SQLException {
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM Mestre");
        while (rs.next()) {
            System.out.printf("ID: %d | Nome: %s | Templo: %s%n",
                    rs.getInt("id"), rs.getString("nome"), rs.getString("templo"));
        }
    }

    private static void atualizarMestre() throws SQLException {
        System.out.print("ID do mestre a atualizar: ");
        int id = Integer.parseInt(sc.nextLine());
        System.out.print("Novo nome: ");
        String nome = sc.nextLine();
        System.out.print("Novo templo: ");
        String templo = sc.nextLine();

        String sql = "UPDATE Mestre SET nome = ?, templo = ? WHERE id = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, nome);
        ps.setString(2, templo);
        ps.setInt(3, id);
        int r = ps.executeUpdate();
        System.out.println(r > 0 ? "Mestre atualizado." : "Erro na atualização.");
    }

    private static void deletarMestre() throws SQLException {
        System.out.print("ID do mestre a deletar: ");
        int id = Integer.parseInt(sc.nextLine());
        String sql = "DELETE FROM Mestre WHERE id = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, id);
        int r = ps.executeUpdate();
        System.out.println(r > 0 ? "Mestre deletado." : "Erro na exclusão.");
    }

    // ---------- CRUD TREINAMENTO ----------
    private static void crudTreinamento() throws SQLException {
        System.out.println("\nCRUD TREINAMENTO");
        System.out.println("1 - Inserir");
        System.out.println("2 - Listar");
        System.out.println("3 - Atualizar");
        System.out.println("4 - Deletar");
        int opc = Integer.parseInt(sc.nextLine());

        switch (opc) {
            case 1 -> inserirTreinamento();
            case 2 -> listarTreinamentos();
            case 3 -> atualizarTreinamento();
            case 4 -> deletarTreinamento();
            default -> System.out.println("Opção inválida.");
        }
    }

    private static void inserirTreinamento() throws SQLException {
        System.out.print("Descrição: ");
        String descricao = sc.nextLine();
        System.out.print("Local: ");
        String local = sc.nextLine();
        System.out.print("Data (YYYY-MM-DD): ");
        String data = sc.nextLine();

        String sql = "INSERT INTO Treinamento(descricao, local, data) VALUES (?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, descricao);
        ps.setString(2, local);
        ps.setDate(3, Date.valueOf(data));
        int r = ps.executeUpdate();
        System.out.println(r > 0 ? "Treinamento inserido com sucesso." : "Erro ao inserir treinamento.");
    }

    private static void listarTreinamentos() throws SQLException {
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM Treinamento");
        while (rs.next()) {
            System.out.printf("ID: %d | Descrição: %s | Local: %s | Data: %s%n",
                    rs.getInt("id"), rs.getString("descricao"), rs.getString("local"), rs.getDate("data").toString());
        }
    }

    private static void atualizarTreinamento() throws SQLException {
        System.out.print("ID do treinamento a atualizar: ");
        int id = Integer.parseInt(sc.nextLine());
        System.out.print("Nova descrição: ");
        String descricao = sc.nextLine();
        System.out.print("Novo local: ");
        String local = sc.nextLine();
        System.out.print("Nova data (YYYY-MM-DD): ");
        String data = sc.nextLine();

        String sql = "UPDATE Treinamento SET descricao = ?, local = ?, data = ? WHERE id = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, descricao);
        ps.setString(2, local);
                ps.setString(2, local);
        ps.setDate(3, Date.valueOf(data));
        ps.setInt(4, id);
        int r = ps.executeUpdate();
        System.out.println(r > 0 ? "Treinamento atualizado." : "Erro na atualização.");
    }

    private static void deletarTreinamento() throws SQLException {
        System.out.print("ID do treinamento a deletar: ");
        int id = Integer.parseInt(sc.nextLine());
        String sql = "DELETE FROM Treinamento WHERE id = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, id);
        int r = ps.executeUpdate();
        System.out.println(r > 0 ? "Treinamento deletado." : "Erro na exclusão.");
    }

    // ---------- CRUD AULA ----------
    private static void crudAula() throws SQLException {
        System.out.println("\nCRUD AULA");
        System.out.println("1 - Inserir");
        System.out.println("2 - Listar");
        System.out.println("3 - Atualizar");
        System.out.println("4 - Deletar");
        int opc = Integer.parseInt(sc.nextLine());

        switch (opc) {
            case 1 -> inserirAula();
            case 2 -> listarAulas();
            case 3 -> atualizarAula();
            case 4 -> deletarAula();
            default -> System.out.println("Opção inválida.");
        }
    }

    private static void inserirAula() throws SQLException {
        System.out.print("Nome da aula: ");
        String nome = sc.nextLine();
        System.out.print("ID do mestre: ");
        int mestre_id = Integer.parseInt(sc.nextLine());
        System.out.print("ID do treinamento: ");
        int treinamento_id = Integer.parseInt(sc.nextLine());

        String sql = "INSERT INTO Aula(nome, mestre_id, treinamento_id) VALUES (?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, nome);
        ps.setInt(2, mestre_id);
        ps.setInt(3, treinamento_id);
        int r = ps.executeUpdate();
        System.out.println(r > 0 ? "Aula inserida com sucesso." : "Erro ao inserir aula.");
    }

    private static void listarAulas() throws SQLException {
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM Aula");
        while (rs.next()) {
            System.out.printf("ID: %d | Nome: %s | Mestre ID: %d | Treinamento ID: %d%n",
                    rs.getInt("id"), rs.getString("nome"), rs.getInt("mestre_id"), rs.getInt("treinamento_id"));
        }
    }

    private static void atualizarAula() throws SQLException {
        System.out.print("ID da aula a atualizar: ");
        int id = Integer.parseInt(sc.nextLine());
        System.out.print("Novo nome da aula: ");
        String nome = sc.nextLine();
        System.out.print("Novo ID do mestre: ");
        int mestre_id = Integer.parseInt(sc.nextLine());
        System.out.print("Novo ID do treinamento: ");
        int treinamento_id = Integer.parseInt(sc.nextLine());

        String sql = "UPDATE Aula SET nome = ?, mestre_id = ?, treinamento_id = ? WHERE id = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, nome);
        ps.setInt(2, mestre_id);
        ps.setInt(3, treinamento_id);
        ps.setInt(4, id);
        int r = ps.executeUpdate();
        System.out.println(r > 0 ? "Aula atualizada." : "Erro na atualização.");
    }

    private static void deletarAula() throws SQLException {
        System.out.print("ID da aula a deletar: ");
        int id = Integer.parseInt(sc.nextLine());
        String sql = "DELETE FROM Aula WHERE id = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, id);
        int r = ps.executeUpdate();
        System.out.println(r > 0 ? "Aula deletada." : "Erro na exclusão.");
    }

    // ---------- CRUD EVOLUCAO ----------
    private static void crudEvolucao() throws SQLException {
        System.out.println("\nCRUD EVOLUCAO");
        System.out.println("1 - Inserir");
        System.out.println("2 - Listar");
        System.out.println("3 - Atualizar");
        System.out.println("4 - Deletar");
        int opc = Integer.parseInt(sc.nextLine());

        switch (opc) {
            case 1 -> inserirEvolucao();
            case 2 -> listarEvolucoes();
            case 3 -> atualizarEvolucao();
            case 4 -> deletarEvolucao();
            default -> System.out.println("Opção inválida.");
        }
    }

    private static void inserirEvolucao() throws SQLException {
        System.out.print("Descrição da evolução: ");
        String descricao = sc.nextLine();
        System.out.print("ID do cavaleiro: ");
        int cavaleiro_id = Integer.parseInt(sc.nextLine());
        System.out.print("ID da aula: ");
        int aula_id = Integer.parseInt(sc.nextLine());

        String sql = "INSERT INTO Evolucao(descricao, cavaleiro_id, aula_id) VALUES (?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, descricao);
        ps.setInt(2, cavaleiro_id);
        ps.setInt(3, aula_id);
        int r = ps.executeUpdate();
        System.out.println(r > 0 ? "Evolução inserida com sucesso." : "Erro ao inserir evolução.");
    }

    private static void listarEvolucoes() throws SQLException {
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM Evolucao");
        while (rs.next()) {
            System.out.printf("ID: %d | Descrição: %s | Cavaleiro ID: %d | Aula ID: %d%n",
                    rs.getInt("id"), rs.getString("descricao"), rs.getInt("cavaleiro_id"), rs.getInt("aula_id"));
        }
    }

    private static void atualizarEvolucao() throws SQLException {
        System.out.print("ID da evolução a atualizar: ");
        int id = Integer.parseInt(sc.nextLine());
        System.out.print("Nova descrição da evolução: ");
        String descricao = sc.nextLine();
        System.out.print("Novo ID do cavaleiro: ");
        int cavaleiro_id = Integer.parseInt(sc.nextLine());
        System.out.print("Novo ID da aula: ");
        int aula_id = Integer.parseInt(sc.nextLine());

        String sql = "UPDATE Evolucao SET descricao = ?, cavaleiro_id = ?, aula_id = ? WHERE id = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, descricao);
        ps.setInt(2, cavaleiro_id);
        ps.setInt(3, aula_id);
        ps.setInt(4, id);
        int r = ps.executeUpdate();
        System.out.println(r > 0 ? "Evolução atualizada." : "Erro na atualização.");
    }

    private static void deletarEvolucao() throws SQLException {
        System.out.print("ID da evolução a deletar: ");
        int id = Integer.parseInt(sc.nextLine());
        String sql = "DELETE FROM Evolucao WHERE id = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, id);
        int r = ps.executeUpdate();
        System.out.println(r > 0 ? "Evolução deletada." : "Erro na exclusão.");
    }
}
