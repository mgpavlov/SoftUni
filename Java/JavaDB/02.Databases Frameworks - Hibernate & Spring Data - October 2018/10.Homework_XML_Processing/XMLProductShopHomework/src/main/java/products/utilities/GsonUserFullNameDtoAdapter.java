package products.utilities;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import products.dto.views.user.UserNameViewDto;

import java.lang.reflect.Type;

public class GsonUserFullNameDtoAdapter implements JsonSerializer<UserNameViewDto> {

    @Override
    public JsonElement serialize(UserNameViewDto userNameViewDto, Type type, JsonSerializationContext jsonSerializationContext) {
        String fullName = userNameViewDto.getFirstName() + " " + userNameViewDto.getLastName();
        return new JsonPrimitive(fullName);
    }
}