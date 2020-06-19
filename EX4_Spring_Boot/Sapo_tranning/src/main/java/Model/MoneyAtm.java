package Model;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;

@Component
@ConfigurationProperties(prefix = "money")
@Validated
public class MoneyAtm {
    BigDecimal moneyAtm;

    public BigDecimal getMoneyAtm() {
        return moneyAtm;
    }

    public void setMoneyAtm(BigDecimal moneyAtm) {
        this.moneyAtm = moneyAtm;
    }
}
