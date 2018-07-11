package p04Telephony;

public class Phone implements Browseable, Callable {

    @Override
    public void calling(String number) {
        if (!isValidNumber(number)){
            throw new IllegalArgumentException("Invalid number!");
        }
        System.out.println("Calling... " + number);
    }

    @Override
    public void browsing(String url) {
        if (!isValidWebsite(url)){
            throw new IllegalArgumentException("Invalid URL!");
        }
        System.out.println("Browsing: " + url + "!");
    }

    private boolean isValidNumber(String number) {
        for (int i = 0; i < number.length(); i++) {
            if (!Character.isDigit(number.charAt(i))) {
                return false;
            }
        }

        return true;
    }

    private boolean isValidWebsite(String website) {
        for (int i = 0; i < website.length(); i++) {
            if (Character.isDigit(website.charAt(i))) {
                return false;
            }
        }

        return true;
    }
}
