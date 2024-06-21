public class Conta implements Conta1{
    public Cliente donoConta;
    public int numero;
    public double saldoTotal;

    public Conta(Cliente donoConta, int numero, double saldoInicial){
        this.donoConta = donoConta;
        this.numero = numero;
        this.saldoTotal = saldoInicial;
    }

    @Override
    public Cliente getDonoConta() {
        return donoConta;
    }

    @Override
    public int getNumero() {
        return numero;
    }

    @Override
    public double getSaldoTotal() {
        return saldoTotal;
    }

    @Override
    public void remunera() {
    }

    @Override
    public boolean deposita(double valor) {
        if(valor > 0){
            saldoTotal += valor;
            return true;
        }
        return false;
    }

    @Override
    public boolean saca(double valor){
        if(valor > 0){
            saldoTotal -= valor;
            return true;
        }
        return false;
    }
}
