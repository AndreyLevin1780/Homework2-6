package pro.sky.EmployeesCollectionHomework.Service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import pro.sky.EmployeesCollectionHomework.Exceptions.ValidationFailedException;

@Service
public class ValidationService {

    public String validateName(String name) {

        if (!StringUtils.isAlpha(name)) {
            throw new ValidationFailedException();
        }
        return StringUtils.capitalize(name.toLowerCase());
    }

}
