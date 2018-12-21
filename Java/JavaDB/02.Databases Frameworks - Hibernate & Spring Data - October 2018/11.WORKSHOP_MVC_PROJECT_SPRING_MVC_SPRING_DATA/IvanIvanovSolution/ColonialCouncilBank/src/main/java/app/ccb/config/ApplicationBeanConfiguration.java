package app.ccb.config;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
public class ApplicationBeanConfiguration {

//    @Bean
//    public FileUtil fileUtil() {
//        // TODO : Implement Me
////        return new FileUtilImpl();
//        return null;
//    }

//    @Bean
//    public ValidationUtil validationUtil() {
//        // TODO : Implement Me
//        //        return new ValidationUtilImpl();
//        return null;
//    }

    @Bean
    @Primary
    public Gson gson() {
        return new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .setDateFormat("yyyy-MM-dd")
                .registerTypeAdapter(LocalDate.class, (JsonDeserializer<LocalDate>) (json, type, jsonDeserializationContext) -> {
                    DateTimeFormatter dateformatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    LocalDate ld = LocalDate.parse(json.getAsJsonPrimitive().getAsString(), dateformatter);
                    return ld;
                })
                .create();

    }

    @Bean
    public ModelMapper modelMapper(ModelMapperConfig config) {
        ModelMapper mapper = new ModelMapper();
        config.configure(mapper);
        return mapper;
    }
}
