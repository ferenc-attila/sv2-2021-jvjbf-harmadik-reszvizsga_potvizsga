package streams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ApartmentRental {

    private List<Apartment> apartments = new ArrayList<>();

    public void addApartment(Apartment apartment) {
        validateApartment(apartment);
        apartments.add(apartment);
    }

    public List<Apartment> findApartmentByLocation(String location) {
        validateApartments();
        return apartments.stream()
                .filter(apartment -> location.equals(apartment.getLocation()))
                .toList();
    }

    public List<Apartment> findApartmentByExtras(String... extras) {
        return apartments.stream()
                .filter(apartment -> apartment.getExtras().containsAll(Arrays.stream(extras).toList()))
                .toList();
    }

    public boolean isThereApartmentWithBathroomType(BathRoomType bathRoomType) {
        validateApartments();
        return apartments.stream()
                .anyMatch(apartment -> apartment.getBathRoomType() == bathRoomType);
    }

    public List<Integer> findApartmentsSize() {
        validateApartments();
        return apartments.stream()
                .map(Apartment::getSize)
                .toList();
    }

    private void validateApartment(Apartment apartment) {
        if (apartment == null) {
            throw new IllegalArgumentException("Apartment cannot be null!");
        }
    }

    private void validateApartments() {
        if (apartments.isEmpty()) {
            throw new IllegalArgumentException("Empty list!");
        }
    }
}
