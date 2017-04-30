package clara.iksan.model;

import java.util.List;

/**
 * Created by dyhan on 2017. 4. 30..
 */
public class BankStatementWrapper {
    private List<BankStatement> bankStatements;

    public List<BankStatement> getBankStatements() {
        return bankStatements;
    }

    public void setBankStatements(List<BankStatement> bankStatements) {
        this.bankStatements = bankStatements;
    }
}
