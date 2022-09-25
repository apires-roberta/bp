package betterplace.betterplacebcd.data.enums;

import org.springframework.stereotype.Component;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.stream.Stream;

@Component
@Converter(autoApply = true)
public class TipoCampanhaConverter implements AttributeConverter<TipoCampanha, Integer> {
    @Override
    public Integer convertToDatabaseColumn(TipoCampanha tipoCampanha) {
        return tipoCampanha == null ? null : tipoCampanha.getIdTipoCampanha();
    }
    @Override
    public TipoCampanha convertToEntityAttribute(Integer idTipoCampanha) {
        return idTipoCampanha == null ? null : Stream.of(TipoCampanha.values())
                                                     .filter(tipo -> tipo.getIdTipoCampanha() == idTipoCampanha)
                                                     .findFirst()
                                                     .orElseThrow(IllegalArgumentException::new);
    }
}