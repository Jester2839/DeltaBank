package accounts;

// typ uctu, ktery si u prevodu mezi dvema ucty uctuje vlastni poplatek
public interface TransferFeePoint {

    public double calculateTransferFee(double amount);

}
