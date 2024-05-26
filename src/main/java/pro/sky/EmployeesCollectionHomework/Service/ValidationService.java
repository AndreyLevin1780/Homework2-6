package pro.sky.EmployeesCollectionHomework.Service;

import org.apache.commons.lang3.StringUtils;
import pro.sky.EmployeesCollectionHomework.Exceptions.ValidationFailedException;

public class ValidationService {

    public String validateName(String name) {

        if (!StringUtils.isAlpha(name)) {
            throw new ValidationFailedException();
        }
        return StringUtils.capitalize(name.toLowerCase());
    }

}
