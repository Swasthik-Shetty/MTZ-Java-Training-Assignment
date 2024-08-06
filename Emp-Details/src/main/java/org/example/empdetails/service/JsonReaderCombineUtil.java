package org.example.empdetails.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.empdetails.domain.Currency;
import org.example.empdetails.domain.Employee;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public final class JsonReaderCombineUtil {
    private JsonReaderCombineUtil() {
    }

    public static void combine() {

        ObjectMapper mapper = new ObjectMapper();
        try {
            // Read employee data
            InputStream empStream = JsonReaderCombineUtil.class.getClassLoader().getResourceAsStream("emp.json");
            List<Employee> employees = mapper.readValue(empStream, new TypeReference<>() {});

            // Read currency data
            InputStream currencyStream = JsonReaderCombineUtil.class.getClassLoader().getResourceAsStream("currency.json");
            List<Currency> currencies = mapper.readValue(currencyStream, new TypeReference<>() {});
            Map<String, Currency> currencyMap = currencies.stream().collect(Collectors.toMap(Currency::getCurrency, c -> c));

            // Format salaries
            for (Employee employee : employees) {
                Currency currency = currencyMap.get(employee.getCurrency());
                DecimalFormat format = new DecimalFormat("#,##0." + "0".repeat(currency.getDecimals()));
                employee.setFormattedSalary(currency.getSymbol() + format.format(employee.getSalary()));
            }

            // Write formatted data to emp_details.json
            mapper.writeValue(new File("Emp-Details/src/main/resources/emp_details.json"), employees);
        } catch (IOException e) {

            System.out.println("IOException found");
        }
    }


}
