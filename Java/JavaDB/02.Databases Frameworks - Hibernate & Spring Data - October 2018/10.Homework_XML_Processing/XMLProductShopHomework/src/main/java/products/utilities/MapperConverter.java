package products.utilities;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import products.io.Writer;

import java.util.ArrayList;
import java.util.List;

@Component
public class MapperConverter {

    private ModelMapper modelMapper;
    private final Writer writer;

    @Autowired
    public MapperConverter(Writer writer) {
        this.writer = writer;
        this.modelMapper = new ModelMapper();
    }

    public <S, D> D convertOne(S source, Class<D> destinationClass) {
        return this.modelMapper.map(source, destinationClass);
    }

    public <S, D> void convertOne(S source, D destination) {
        this.modelMapper.map(source, destination);
    }

    @SuppressWarnings("unchecked")
    public <S, D> List<D> convertList(Iterable<S> sources, Class<D> destinationClass) throws IllegalAccessException, InstantiationException {
        List<D> destinations = new ArrayList<>();

        for (S source : sources) {
            destinations.add(this.convertOne(source, destinationClass));
        }
        return destinations;
    }
}