package interface_adaptors;

public class TransactionController {
    private final TransactionViewModel viewModel;

    public TransactionController(TransactionViewModel viewModel) {
        this.viewModel = viewModel;
    }

    public void selectOneTimeTransaction() {
        viewModel.selectOneTimeTransaction();
    }

    public void selectPeriodicTransaction() {
        viewModel.selectPeriodicTransaction();
    }
}
