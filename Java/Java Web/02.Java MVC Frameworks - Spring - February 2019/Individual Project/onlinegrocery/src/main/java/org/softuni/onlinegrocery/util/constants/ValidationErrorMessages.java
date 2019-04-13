package org.softuni.onlinegrocery.util.constants;

public final class ValidationErrorMessages {

    private ValidationErrorMessages() {
    }

    public static final String ERROR = "error";
    public static final String PASSWORDS_DOES_NOT_MATCH_ERROR_MSG = "Passwords doesnt match.";

    /** User Models (DTO) error messages **/


    /** Car Models (DTO) error messages **/
    public static final String CAR_MODEL_EMPTY_FIELD_ERROR_MSG = "Car model can't be empty.";
    public static final String CAR_MODEL_MAX_LENGTH_ERROR_MSG = "Car model should be max 25 characters long.";
    public static final String CAR_EMPTY_MANUFACTURE_DATE_ERROR_MSG = "Car manufacture date can't be empty.";
    public static final String CAR_EMPTY_REGISTRATION_NUMBER_ERROR_MSG = "Car registration number can't be empty.";
    public static final String CAR_REGISTRATION_NUMBER_MAX_LENGTH_ERROR_MSG = "Car registration number should be max 15 characters long.";
    public static final String CAR_HP_EMPTY_FIELD_ERROR_MSG = "Car horse power (HP) can't be empty.";
    public static final String CAR_HP_TOLERABLE_LIMITS_ERROR_MSG = "Car horse power (HP) should be between 50 and 1300.";
    public static final String CAR_ENGINE_VOLUME_EMPTY_FIELD_ERROR_MSG = "Car engine volume can't be empty.";
    public static final String CAR_ENGINE_VOLUME_TOLERABLE_LIMITS_ERROR_MSG = "Car engine volume should be between 50 and 1300.";


    /** News Models (DTO) error messages **/
    public static final String NEWS_TITLE_EMPTY_FIELD_ERROR_MSG = "Title can't be empty.";
    public static final String NEWS_SUB_TITLE_EMPTY_FIELD_ERROR_MSG = "Sub title can't be empty.";
    public static final String NEWS_SUB_TITLE_TOO_LONG_ERROR_MSG = "Sub title can't be more than 255 characters long.";
    public static final String NEWS_TITLE_MAX_LENGTH_ERROR_MSG = "Title should be max 40 characters long.";
    public static final String NEWS_DESCRIPTION_EMPTY_FIELD_ERROR_MSG = "News description can't be empty.";
    public static final String NEWS_SOURCE_EMPTY_FIELD_ERROR_MSG = "News source can't be empty.";
    public static final String NEWS_CATEGORY_EMPTY_FIELD_ERROR_MSG = "News category can't be empty.";
    public static final String NEWS_IMAGE_FILE_EMPTY_FIELD_ERROR_MSG = "Image can't be empty.";
    public static final String NEWS_IMAGE_EMPTY_FIELD_ERROR_MSG = "Image can't be empty.";

    /** Event Models (DTO) error messages **/
    public static final String EVENT_TITLE_EMPTY_FIELD_ERROR_MSG = "Event name can't be empty.";

    public static final String EVENT_TITLE_MAX_LENGTH_ERROR_MSG = "Event name should be max 40 characters long.";
    public static final String EVENT_DESCRIPTION_EMPTY_FIELD_ERROR_MSG = "Event description can't be empty.";
    public static final String EVENT_ORGANIZER_EMPTY_FIELD_ERROR_MSG = "Event organizer can't be empty.";
    public static final String EVENT_LOCATION_EMPTY_FIELD_ERROR_MSG = "Event location can't be empty.";
    public static final String EVENT_DATE_EMPTY_FIELD_ERROR_MSG = "Event date can't be empty.";
    public static final String EVENT_FEE_EMPTY_FIELD_ERROR_MSG = "Event fee can't be empty. Should be at least 0.";
    public static final String EVENT_MEMBERSHIP_FEE_EMPTY_FIELD_ERROR_MSG = "Event membership fee can't be empty. Should be at least 0";
    public static final String EVENT_FEE_DESCRIPTION_EMPTY_FIELD_ERROR_MSG = "Event fee description can't be empty.";
    public static final String EVENT_DISCIPLINES_EMPTY_FIELD_ERROR_MSG = "Event disciplines can't be empty. Should choice at least one.";
    public static final String EVENT_IMAGE_EMPTY_FILE_ERROR_MSG = "Event image can't be empty. Please upload an image.";



    //MY
    public static final String CATEGORY_NAME_EMPTY_FIELD_ERROR_MSG = "Category name can't be empty.";
    public static final String CATEGORY_NAME_MAX_LENGTH_ERROR_MSG = "Category name should be max 20 characters long.";

    public static final String PRODUCT_NAME_EMPTY_FIELD_ERROR_MSG = "Product name can't be empty.";
    public static final String PRODUCT_NAME_LENGTH = "Product name should be between 3 and 20 symbols!";
    public static final String PRODUCT_DESCRIPTION_MAX_LENGTH_ERROR_MSG = "Product description should be max 50 characters long.";
    public static final String PRODUCT_DESCRIPTION_EMPTY_FIELD_ERROR_MSG = "Product description can't be empty.";
    public static final String PRODUCT_PRICE_EMPTY_FIELD_ERROR_MSG = "Product price can't be empty. Should be at least 0.";
    public static final String PRODUCT_IMAGE_EMPTY_FIELD_ERROR_MSG = "Upload product image.";
    public static final String PRODUCT_CATEGORIES_EMPTY_FIELD_ERROR_MSG = "Select at least one category.";

}
