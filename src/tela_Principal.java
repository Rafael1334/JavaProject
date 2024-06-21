import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

public class tela_Principal extends JFrame {

    private JFrame telaPrincipal = new JFrame("Tela Principal");
    private JFrame telaCadastrar = new JFrame("Tela Cadastrar");
    private JFrame telaAtualizar = new JFrame("Tela Atualizar");
    private JFrame telaTipoDeConta = new JFrame("Tela Tipo de Conta");
    private JFrame telaSaqueEDeposito = new JFrame("Tela Saque e Deposito");
    private JTextField txtfPesquisar = new JTextField();
    private JLabel lblPesquisa = new JLabel("Pesquisar e Cadastrar Clientes");
    private JButton btnCadastrar = new JButton("CADASTRAR");
    private JButton btnExcluir = new JButton("EXCLUIR");
    private JButton btnAtualizar = new JButton("ATUALIZAR");
    private JButton btnPesquisar = new JButton("PESQUISAR");
    private JButton btnGerenciarConta = new JButton("TIPO DE CONTA");
    private JButton btnOperacaoConta = new JButton("SAQUE/DEPOSITO");
    private JTable tabelaClientes;
    private DefaultTableModel modeloTabela;
    private ArrayList<Cliente> listaClientes = new ArrayList<>();
    private double saldoAtual;
    private JLabel lblSaldoAtual;

    public tela_Principal() {
        telaPrincipal.setSize(1080, 675);
        telaPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        telaPrincipal.setLocationRelativeTo(null);

        JPanel contentPane = new JPanel(null);

        lblPesquisa.setFont(new Font("Arial", Font.BOLD, 36));
        lblPesquisa.setBounds(250, 40, 600, 50);
        contentPane.add(lblPesquisa);

        txtfPesquisar.setBounds(50, 120, 420, 30);
        contentPane.add(txtfPesquisar);

        btnPesquisar.setBounds(480, 120, 110, 30);
        contentPane.add(btnPesquisar);

        btnCadastrar.setBounds(50, 160, 110, 30);
        contentPane.add(btnCadastrar);

        btnAtualizar.setBounds(170, 160, 110, 30);
        contentPane.add(btnAtualizar);

        btnExcluir.setBounds(890, 160, 110, 30);
        contentPane.add(btnExcluir);

        btnGerenciarConta.setBounds(50, 600, 200, 30);
        contentPane.add(btnGerenciarConta);

        btnOperacaoConta.setBounds(270, 600, 200, 30);
        contentPane.add(btnOperacaoConta);

        modeloTabela = new DefaultTableModel();
        modeloTabela.addColumn("Nome");
        modeloTabela.addColumn("Sobrenome");
        modeloTabela.addColumn("CPF");
        modeloTabela.addColumn("RG");
        modeloTabela.addColumn("Endereço");
        tabelaClientes = new JTable(modeloTabela);
        JScrollPane sp = new JScrollPane(tabelaClientes);
        sp.setBounds(50, 200, 980, 400);
        contentPane.add(sp);

        btnCadastrar.addActionListener(e -> adicionarCliente());
        btnAtualizar.addActionListener(e -> atualizarCliente());
        btnExcluir.addActionListener(e -> excluirCliente());
        btnPesquisar.addActionListener(e -> pesquisarCliente());
        btnGerenciarConta.addActionListener(e -> gerenciarContaCliente());
        btnOperacaoConta.addActionListener(e -> operacaoContaCliente());

        tabelaClientes.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int linha = tabelaClientes.getSelectedRow();
            }
        });

        telaPrincipal.setContentPane(contentPane);
        telaPrincipal.setVisible(true);
    }

    private void adicionarCliente() {
        telaCadastrar.setLayout(null);
        telaCadastrar.setSize(1080, 675);

        JLabel lblCadastrar = new JLabel("Cadastrar Cliente");
        lblCadastrar.setFont(new Font("Arial", Font.BOLD, 36));
        lblCadastrar.setBounds(400, 40, 500, 50);
        telaCadastrar.add(lblCadastrar);

        JLabel lblNome = new JLabel("Nome:");
        lblNome.setFont(new Font("Arial", Font.BOLD, 18));
        lblNome.setBounds(50, 120, 60, 30);
        telaCadastrar.add(lblNome);
        JTextField txtfNome = new JTextField();
        txtfNome.setBounds(150, 120, 420, 30);
        telaCadastrar.add(txtfNome);

        JLabel lblSobrenome = new JLabel("Sobrenome:");
        lblSobrenome.setFont(new Font("Arial", Font.BOLD, 18));
        lblSobrenome.setBounds(50, 170, 120, 30);
        telaCadastrar.add(lblSobrenome);
        JTextField txtfSobrenome = new JTextField();
        txtfSobrenome.setBounds(180, 170, 390, 30);
        telaCadastrar.add(txtfSobrenome);

        JLabel lblCpf = new JLabel("CPF:");
        lblCpf.setFont(new Font("Arial", Font.BOLD, 18));
        lblCpf.setBounds(50, 220, 60, 30);
        telaCadastrar.add(lblCpf);
        JTextField txtfCpf = new JTextField();
        txtfCpf.setBounds(120, 220, 200, 30);
        telaCadastrar.add(txtfCpf);

        JLabel lblRg = new JLabel("RG:");
        lblRg.setFont(new Font("Arial", Font.BOLD, 18));
        lblRg.setBounds(350, 220, 40, 30);
        telaCadastrar.add(lblRg);
        JTextField txtfRg = new JTextField();
        txtfRg.setBounds(390, 220, 180, 30);
        telaCadastrar.add(txtfRg);

        JLabel lblEndereco = new JLabel("Endereço:");
        lblEndereco.setFont(new Font("Arial", Font.BOLD, 18));
        lblEndereco.setBounds(50, 270, 100, 30);
        telaCadastrar.add(lblEndereco);
        JTextField txtfEndereco = new JTextField();
        txtfEndereco.setBounds(150, 270, 420, 30);
        telaCadastrar.add(txtfEndereco);

        JButton btnSalvar = new JButton("Salvar");
        btnSalvar.setBounds(50, 350, 100, 30);

        btnSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nome = txtfNome.getText().trim();
                String sobrenome = txtfSobrenome.getText().trim();
                String cpf = txtfCpf.getText().trim();
                String rg = txtfRg.getText().trim();
                String endereco = txtfEndereco.getText().trim();

                if (nome.isEmpty() || sobrenome.isEmpty() || cpf.isEmpty() || rg.isEmpty() || endereco.isEmpty()) {
                    JOptionPane.showMessageDialog(telaCadastrar, "Preencha todos os campos", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                Cliente cliente = new Cliente(nome, sobrenome, cpf, rg, endereco);
                listaClientes.add(cliente);

                atualizarTabelaClientes();

                txtfNome.setText("");
                txtfSobrenome.setText("");
                txtfCpf.setText("");
                txtfRg.setText("");
                txtfEndereco.setText("");

                telaCadastrar.dispose();
            }
        });
        telaCadastrar.add(btnSalvar);

        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.setBounds(180, 350, 100, 30);
        btnVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                telaCadastrar.dispose();
            }
        });
        telaCadastrar.add(btnVoltar);

        telaCadastrar.setVisible(true);
    }


    private void atualizarCliente() {
        int linha = tabelaClientes.getSelectedRow();
        if (linha > -1) {
            Cliente cliente = listaClientes.get(linha);

            telaAtualizar.setLayout(null);
            telaAtualizar.setSize(1080, 675);

            JLabel lblAtualizar = new JLabel("Atualizar Cliente");
            lblAtualizar.setFont(new Font("Arial", Font.BOLD, 36));
            lblAtualizar.setBounds(400, 40, 500, 50);
            telaAtualizar.add(lblAtualizar);

            JLabel lblNome = new JLabel("Nome:");
            lblNome.setFont(new Font("Arial", Font.BOLD, 18));
            lblNome.setBounds(50, 120, 60, 30);
            telaAtualizar.add(lblNome);
            JTextField txtfNome = new JTextField(cliente.getNome());
            txtfNome.setBounds(150, 120, 420, 30);
            telaAtualizar.add(txtfNome);

            JLabel lblSobrenome = new JLabel("Sobrenome:");
            lblSobrenome.setFont(new Font("Arial", Font.BOLD, 18));
            lblSobrenome.setBounds(50, 170, 120, 30);
            telaAtualizar.add(lblSobrenome);
            JTextField txtfSobrenome = new JTextField(cliente.getSobrenome());
            txtfSobrenome.setBounds(180, 170, 390, 30);
            telaAtualizar.add(txtfSobrenome);

            JLabel lblCpf = new JLabel("CPF:");
            lblCpf.setFont(new Font("Arial", Font.BOLD, 18));
            lblCpf.setBounds(50, 220, 60, 30);
            telaAtualizar.add(lblCpf);
            JTextField txtfCpf = new JTextField(cliente.getCpf());
            txtfCpf.setBounds(120, 220, 200, 30);
            txtfCpf.setEditable(false);
            telaAtualizar.add(txtfCpf);

            JLabel lblRg = new JLabel("RG:");
            lblRg.setFont(new Font("Arial", Font.BOLD, 18));
            lblRg.setBounds(350, 220, 40, 30);
            telaAtualizar.add(lblRg);
            JTextField txtfRg = new JTextField(cliente.getRg());
            txtfRg.setBounds(390, 220, 180, 30);
            txtfRg.setEditable(false);
            telaAtualizar.add(txtfRg);

            JLabel lblEndereco = new JLabel("Endereço:");
            lblEndereco.setFont(new Font("Arial", Font.BOLD, 18));
            lblEndereco.setBounds(50, 270, 100, 30);
            telaAtualizar.add(lblEndereco);
            JTextField txtfEndereco = new JTextField(cliente.getEndereco());
            txtfEndereco.setBounds(150, 270, 420, 30);
            telaAtualizar.add(txtfEndereco);

            JButton btnSalvar = new JButton("Salvar");
            btnSalvar.setBounds(50, 350, 100, 30);
            btnSalvar.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String nome = txtfNome.getText().trim();
                    String sobrenome = txtfSobrenome.getText().trim();
                    String endereco = txtfEndereco.getText().trim();

                    if (nome.isEmpty() || sobrenome.isEmpty() || endereco.isEmpty()) {
                        JOptionPane.showMessageDialog(telaAtualizar, "Preencha todos os campos", "Erro", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    cliente.setNome(nome);
                    cliente.setSobrenome(sobrenome);
                    cliente.setEndereco(endereco);

                    atualizarTabelaClientes();

                    telaAtualizar.dispose();
                }
            });
            telaAtualizar.add(btnSalvar);

            JButton btnVoltar = new JButton("Voltar");
            btnVoltar.setBounds(180, 350, 100, 30);
            btnVoltar.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    telaAtualizar.dispose();
                }
            });
            telaAtualizar.add(btnVoltar);

            telaAtualizar.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(telaPrincipal, "Selecione um cliente para atualizar.", "Atualizar Cliente", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void excluirCliente() {
        int linha = tabelaClientes.getSelectedRow();
        if (linha > -1) {
            int msgDelletar = JOptionPane.showConfirmDialog(null, "Deseja deletar o cliente selecionado?", "Clientes", JOptionPane.YES_NO_OPTION);
            if (msgDelletar == 0) {
                listaClientes.remove(linha);
                atualizarTabelaClientes();
            }
        } else {
            JOptionPane.showMessageDialog(telaPrincipal, "Selecione um cliente para excluir.", "Excluir Cliente", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void pesquisarCliente() {
        String pesquisa = txtfPesquisar.getText().trim().toLowerCase();

        if (pesquisa.isEmpty()) {
            atualizarTabelaClientes();
            return;
        }

        List<Cliente> clientesFiltrados = listaClientes.stream().filter(cliente -> cliente.getNome().toLowerCase().contains(pesquisa) || cliente.getSobrenome().toLowerCase().contains(pesquisa) || cliente.getCpf().toLowerCase().contains(pesquisa) || cliente.getRg().toLowerCase().contains(pesquisa) || cliente.getEndereco().toLowerCase().contains(pesquisa)).collect(Collectors.toList());

        modeloTabela.setRowCount(0);
        for (Cliente cliente : clientesFiltrados) {
            Object[] rowData = {cliente.getNome(), cliente.getSobrenome(), cliente.getCpf(), cliente.getRg(), cliente.getEndereco()};
            modeloTabela.addRow(rowData);
        }

    }

    private void gerenciarContaCliente() {
        int linha = tabelaClientes.getSelectedRow();
        if (linha > -1) {
            Cliente cliente = listaClientes.get(linha);
            JFrame telaTipoDeConta = new JFrame();
            telaTipoDeConta.setLayout(null);
            telaTipoDeConta.setSize(1080, 675);
            telaTipoDeConta.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            telaTipoDeConta.setLocationRelativeTo(null);

            JLabel lblTipoConta = new JLabel("Tipo De Conta");
            lblTipoConta.setFont(new Font("Arial", Font.BOLD, 36));
            lblTipoConta.setBounds(350, 40, 500, 50);
            telaTipoDeConta.add(lblTipoConta);

            JLabel lblNome = new JLabel("Nome:");
            lblNome.setFont(new Font("Arial", Font.BOLD, 18));
            lblNome.setBounds(50, 120, 60, 30);
            telaTipoDeConta.add(lblNome);
            JTextField txtfNome = new JTextField(cliente.getNome());
            txtfNome.setBounds(150, 120, 420, 30);
            txtfNome.setEditable(false);
            telaTipoDeConta.add(txtfNome);

            JLabel lblSobrenome = new JLabel("Sobrenome:");
            lblSobrenome.setFont(new Font("Arial", Font.BOLD, 18));
            lblSobrenome.setBounds(50, 170, 120, 30);
            telaTipoDeConta.add(lblSobrenome);
            JTextField txtfSobrenome = new JTextField(cliente.getSobrenome());
            txtfSobrenome.setBounds(180, 170, 390, 30);
            txtfSobrenome.setEditable(false);
            telaTipoDeConta.add(txtfSobrenome);

            JLabel lblCpf = new JLabel("CPF:");
            lblCpf.setFont(new Font("Arial", Font.BOLD, 18));
            lblCpf.setBounds(50, 220, 60, 30);
            telaTipoDeConta.add(lblCpf);
            JTextField txtfCpf = new JTextField(cliente.getCpf());
            txtfCpf.setBounds(120, 220, 200, 30);
            txtfCpf.setEditable(false);
            telaTipoDeConta.add(txtfCpf);

            String[] tiposDeConta = {"Conta Corrente", "Conta Investimento"};
            JComboBox<String> cbxContaCorrente = new JComboBox<>(tiposDeConta);
            cbxContaCorrente.setBounds(50, 270, 200, 30);
            telaTipoDeConta.add(cbxContaCorrente);

            JLabel lblSaldoInicial = new JLabel("Saldo Inicial");
            lblSaldoInicial.setBounds(50, 320, 120, 30);
            telaTipoDeConta.add(lblSaldoInicial);
            JTextField txtfSaldoInicial = new JTextField();
            txtfSaldoInicial.setBounds(170, 320, 200, 30);
            telaTipoDeConta.add(txtfSaldoInicial);

            JLabel lblLimite = new JLabel("Limite");
            lblLimite.setBounds(50, 370, 70, 30);
            telaTipoDeConta.add(lblLimite);
            JTextField txtfLimite = new JTextField();
            txtfLimite.setBounds(170, 370, 150, 30);
            telaTipoDeConta.add(txtfLimite);

            JLabel lblDepositoMinimo = new JLabel("Deposito Minimo");
            lblDepositoMinimo.setBounds(50, 420, 120, 30);
            telaTipoDeConta.add(lblDepositoMinimo);
            JTextField txtfDepositoMinimo = new JTextField();
            txtfDepositoMinimo.setBounds(170, 420, 150, 30);
            telaTipoDeConta.add(txtfDepositoMinimo);

            JLabel lblMontanteMinimo = new JLabel("Montante Minimo");
            lblMontanteMinimo.setBounds(50, 470, 120, 30);
            telaTipoDeConta.add(lblMontanteMinimo);
            JTextField txtfMontanteMinimo = new JTextField();
            txtfMontanteMinimo.setBounds(170, 470, 150, 30);
            telaTipoDeConta.add(txtfMontanteMinimo);

            cbxContaCorrente.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String tipoSelecionado = (String) cbxContaCorrente.getSelectedItem();
                    if (Objects.equals(tipoSelecionado, "Conta Corrente")) {
                        txtfLimite.setEditable(true);
                        lblLimite.setVisible(true);
                        txtfLimite.setVisible(true);
                        lblMontanteMinimo.setVisible(false);
                        txtfMontanteMinimo.setVisible(false);
                        txtfMontanteMinimo.setEditable(false);
                        lblDepositoMinimo.setVisible(false);
                        txtfDepositoMinimo.setVisible(false);
                        txtfDepositoMinimo.setEditable(false);
                    } else {
                        txtfLimite.setEditable(false);
                        lblLimite.setVisible(false);
                        txtfLimite.setVisible(false);
                        lblMontanteMinimo.setVisible(true);
                        txtfMontanteMinimo.setVisible(true);
                        txtfMontanteMinimo.setEditable(true);
                        lblDepositoMinimo.setVisible(true);
                        txtfDepositoMinimo.setVisible(true);
                        txtfDepositoMinimo.setEditable(true);
                    }
                }
            });

            JButton btnSalvar = new JButton("Salvar");
            btnSalvar.setBounds(750, 550, 120, 40);
            telaTipoDeConta.add(btnSalvar);

            JButton btnVoltar = new JButton("Voltar");
            btnVoltar.setBounds(900, 550, 120, 40);
            telaTipoDeConta.add(btnVoltar);

            btnSalvar.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (txtfSaldoInicial.getText().isEmpty() || (txtfLimite.isVisible() && txtfLimite.getText().isEmpty()) || (txtfDepositoMinimo.isVisible() && txtfDepositoMinimo.getText().isEmpty()) || (txtfMontanteMinimo.isVisible() && txtfMontanteMinimo.getText().isEmpty())) {
                        JOptionPane.showMessageDialog(telaTipoDeConta, "Por favor, preencha todos os campos obrigatórios.", "Erro", JOptionPane.ERROR_MESSAGE);
                    } else {
                        String tipoContaSelecionado = (String) cbxContaCorrente.getSelectedItem();

                        JOptionPane.showMessageDialog(telaTipoDeConta, "Dados salvos com sucesso!", "Salvar", JOptionPane.INFORMATION_MESSAGE);

                        txtfSaldoInicial.setText("");
                        txtfLimite.setText("");
                        txtfDepositoMinimo.setText("");
                        txtfMontanteMinimo.setText("");
                    }
                }
            });

            btnVoltar.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    telaTipoDeConta.dispose();
                }
            });

            telaTipoDeConta.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(telaPrincipal, "Selecione um cliente para selecionar o seu tipo de conta (CONTA CORRENTE OU CONTA INVESTIMENTO).", "Tipo de Conta do Cliente", JOptionPane.WARNING_MESSAGE);
        }
    }


    private void operacaoContaCliente() {
        int linha = tabelaClientes.getSelectedRow();
        if (linha > -1) {
            String nomeCliente = (String) tabelaClientes.getValueAt(linha, 0);
            String tipoConta = (String) tabelaClientes.getValueAt(linha, 2);

            telaSaqueEDeposito = new JFrame("Saque e Depósito");
            telaSaqueEDeposito.setLayout(null);
            telaSaqueEDeposito.setSize(800, 600);
            telaSaqueEDeposito.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            telaSaqueEDeposito.setLocationRelativeTo(null);

            JLabel lblSaqueDeposito = new JLabel("Saque e Depósito - Cliente: " + nomeCliente);
            lblSaqueDeposito.setFont(new Font("Arial", Font.BOLD, 24));
            lblSaqueDeposito.setBounds(50, 40, 600, 30);
            telaSaqueEDeposito.add(lblSaqueDeposito);

            lblSaldoAtual = new JLabel("Saldo Atual: R$ " + saldoAtual);
            lblSaldoAtual.setFont(new Font("Arial", Font.BOLD, 18));
            lblSaldoAtual.setBounds(50, 100, 300, 30);
            telaSaqueEDeposito.add(lblSaldoAtual);

            JLabel lblValorOperacao = new JLabel("Valor:");
            lblValorOperacao.setFont(new Font("Arial", Font.BOLD, 18));
            lblValorOperacao.setBounds(50, 160, 60, 30);
            telaSaqueEDeposito.add(lblValorOperacao);

            JTextField txtfValorOperacao = new JTextField();
            txtfValorOperacao.setBounds(120, 160, 150, 30);
            telaSaqueEDeposito.add(txtfValorOperacao);

            JButton btnSaque = new JButton("Saque");
            btnSaque.setBounds(50, 220, 100, 30);
            btnSaque.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String valorTexto = txtfValorOperacao.getText().trim();
                    if (!valorTexto.isEmpty()) {
                        try {
                            double valor = Double.parseDouble(valorTexto);
                            if (valor <= saldoAtual) {
                                saldoAtual -= valor;
                                atualizarSaldo();
                                JOptionPane.showMessageDialog(telaSaqueEDeposito, "Saque de R$ " + valor + " realizado com sucesso.", "Saque", JOptionPane.INFORMATION_MESSAGE);
                            } else {
                                JOptionPane.showMessageDialog(telaSaqueEDeposito, "Saldo insuficiente para o saque.", "Erro", JOptionPane.ERROR_MESSAGE);
                            }
                        } catch (NumberFormatException ex) {
                            JOptionPane.showMessageDialog(telaSaqueEDeposito, "Valor inválido para saque.", "Erro", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(telaSaqueEDeposito, "Informe o valor para saque.", "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                }
            });
            telaSaqueEDeposito.add(btnSaque);

            JButton btnDeposito = new JButton("Depósito");
            btnDeposito.setBounds(180, 220, 100, 30);
            btnDeposito.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String valorTexto = txtfValorOperacao.getText().trim();
                    if (!valorTexto.isEmpty()) {
                        try {
                            double valor = Double.parseDouble(valorTexto);
                            saldoAtual += valor;
                            atualizarSaldo();
                            JOptionPane.showMessageDialog(telaSaqueEDeposito, "Depósito de R$ " + valor + " realizado com sucesso.", "Depósito", JOptionPane.INFORMATION_MESSAGE);
                        } catch (NumberFormatException ex) {
                            JOptionPane.showMessageDialog(telaSaqueEDeposito, "Valor inválido para depósito.", "Erro", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(telaSaqueEDeposito, "Informe o valor para depósito.", "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                }
            });
            telaSaqueEDeposito.add(btnDeposito);

            JButton btnSair = new JButton("Sair");
            btnSair.setBounds(300, 220, 100, 30);
            btnSair.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    telaSaqueEDeposito.dispose();
                }
            });
            telaSaqueEDeposito.add(btnSair);

            telaSaqueEDeposito.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(telaPrincipal, "Selecione um cliente para realizar operações de conta.", "Seleção de Cliente", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void atualizarTabelaClientes() {
        modeloTabela.setRowCount(0);
        for (Cliente cliente : listaClientes) {
            Object[] rowData = {cliente.getNome(), cliente.getSobrenome(), cliente.getCpf(), cliente.getRg(), cliente.getEndereco()};
            modeloTabela.addRow(rowData);
        }
    }

    private void atualizarSaldo() {
        lblSaldoAtual.setText("Saldo Atual: R$ " + saldoAtual);
    }

    public static void main(String[] args) {
        new tela_Principal();
    }

}