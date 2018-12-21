package cars.utilities;

import cars.io.Writer;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MapperConverter {

    private ModelMapper modelMapper;
    private final Writer writer;

    @Autowired
    public MapperConverter(Writer writer) {
        this.writer = writer;
        this.modelMapper = new ModelMapper();
    }

    public <S, D> D convert(S source, Class<D> destinationClass) {
        return this.modelMapper.map(source, destinationClass);
    }

    public <S, D> void convert(S source, D destination) {
        this.modelMapper.map(source, destination);
    }
}