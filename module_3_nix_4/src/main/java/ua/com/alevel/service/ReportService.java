package ua.com.alevel.service;

import au.com.bytecode.opencsv.CSVWriter;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import ua.com.alevel.model.Account;
import ua.com.alevel.model.Operation;
import ua.com.alevel.repository.OperationRepository;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ReportService {

    OperationRepository operationRepository;

    public void doReport(Account account, String from, String to) {

        List<Operation> operationList = operationRepository.findOperationBetweenDates(account, from, to);

        long saldo = getSaldo(operationList);
        long sumOfIncomesOperation = getGeneralIncomeSum(operationList);
        String csv = "statement.csv";

        try (CSVWriter writer = new CSVWriter(new FileWriter(csv))) {

            String[] header = "Account_Id,Amount,Timestamp,SumOfIncome,Saldo".split(",");

            writer.writeNext(header);

            for (Operation operation : operationList) {
                String[] data = (account.getId() + ","
                        + operation.getAmount() + ","
                        + operation.getTimestamp() + ","
                        + sumOfIncomesOperation + "," + saldo).split(",");
                writer.writeNext(data);
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
            throw new RuntimeException("Failed to create statement.", e);
        }
    }

    private long getSaldo(List<Operation> operationList) {
        return operationList
                .stream()
                .map(Operation::getAmount)
                .mapToLong(Integer::intValue)
                .sum();
    }

    private long getGeneralIncomeSum(List<Operation> operationList) {
        return operationList
                .stream()
                .mapToLong(Operation::getAmount)
                .filter(amount -> amount > 0)
                .sum();
    }

}
