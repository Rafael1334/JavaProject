public class ConteInvestimento extends Conta{

    private double depositoMinimo;
    private double montanteMinimo;

    public ConteInvestimento(Cliente donoConta, int numero, double saldoInicial, double depositoMinimo, double montanteMinimo) {
        super(donoConta, numero, saldoInicial);
        this.depositoMinimo = depositoMinimo;
        this.montanteMinimo = montanteMinimo;
    }

    @Override
    public boolean deposita(double valor){
        if(valor >= depositoMinimo){
            return super.deposita(valor);
        }
        return false;
    }

    @Override
    public boolean saca(double valor){
        if(valor >= montanteMinimo){
            return super.saca(valor);
        }
        return false;
    }

    @Override
    public void remunera(){
        saldoTotal = saldoTotal * 0.02;
    }

}
