public class ContaCorrente extends Conta{

    private double limite;

    public ContaCorrente(Cliente donoConta, int numero, double saldoInicial, double limite) {
        super(donoConta, numero, saldoInicial);
        this.limite = limite;
    }

    @Override
    public boolean saca(double valor) {
        if (valor > 0 && saldoTotal + limite >= valor) {
            saldoTotal -= valor;
            return true;
        }
        return false;
    }

    @Override
    public void remunera(){
        saldoTotal += saldoTotal * 0.01;
    }
}
