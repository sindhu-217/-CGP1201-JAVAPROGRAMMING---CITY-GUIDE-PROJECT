import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.Map;

public class CityGuideAWT {

    // Map to store cities and their restaurants with cuisine types
    private static Map<String, HashMap<String, String>> cityData = new HashMap<>();

    public static void main(String[] args) {
        // Adding initial predefined city data
        addInitialCityData();

        // Create the AWT frame
        Frame frame = new Frame("City Guide");
        frame.setSize(600, 600);
        frame.setLayout(new FlowLayout());

        // Components for the GUI
        Label cityLabel = new Label("City:");
        TextField cityField = new TextField(20);
        Button displayButton = new Button("Display City");
        Button addRestaurantButton = new Button("Add Restaurant");
        Button findCityButton = new Button("Find City");
        Button exitButton = new Button("Exit");
        TextArea outputArea = new TextArea(20, 50);
        outputArea.setEditable(false);

        // Add components to the frame
        frame.add(cityLabel);
        frame.add(cityField);
        frame.add(displayButton);
        frame.add(addRestaurantButton);
        frame.add(findCityButton);
        frame.add(exitButton);
        frame.add(outputArea);

        // Action for displaying city data
        displayButton.addActionListener(e -> {
            String city = cityField.getText().trim();
            if (!city.isEmpty()) {
                String result = displayCity(city);
                outputArea.setText(result);
            } else {
                outputArea.setText("Please enter a city name.");
            }
        });

        // Action for adding a restaurant to a city
        addRestaurantButton.addActionListener(e -> {
            Frame addRestaurantFrame = new Frame("Add Restaurant");
            addRestaurantFrame.setSize(400, 300);
            addRestaurantFrame.setLayout(new GridLayout(4, 2));

            Label cityNameLabel = new Label("City:");
            TextField cityNameField = new TextField(20);
            Label restaurantNameLabel = new Label("Restaurant:");
            TextField restaurantNameField = new TextField(20);
            Label cuisineLabel = new Label("Cuisine:");
            TextField cuisineField = new TextField(20);
            Button submitButton = new Button("Submit");

            addRestaurantFrame.add(cityNameLabel);
            addRestaurantFrame.add(cityNameField);
            addRestaurantFrame.add(restaurantNameLabel);
            addRestaurantFrame.add(restaurantNameField);
            addRestaurantFrame.add(cuisineLabel);
            addRestaurantFrame.add(cuisineField);
            addRestaurantFrame.add(submitButton);

            // Submit button action
            submitButton.addActionListener(event -> {
                String city = cityNameField.getText().trim();
                String restaurant = restaurantNameField.getText().trim();
                String cuisine = cuisineField.getText().trim();

                if (!city.isEmpty() && !restaurant.isEmpty() && !cuisine.isEmpty()) {
                    addRestaurant(city, restaurant, cuisine);
                    outputArea.setText("Added " + restaurant + " (" + cuisine + ") to " + city + ".");
                    addRestaurantFrame.dispose();
                } else {
                    outputArea.setText("All fields are required.");
                }
            });

            addRestaurantFrame.setVisible(true);
        });

        // Action for finding a city
        findCityButton.addActionListener(e -> {
            String city = cityField.getText().trim();
            if (!city.isEmpty()) {
                String result = findCity(city);
                outputArea.setText(result);
            } else {
                outputArea.setText("Please enter a city name.");
            }
        });

        // Exit button action
        exitButton.addActionListener(e -> System.exit(0));

        // Close the frame properly
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        // Make the frame visible
        frame.setVisible(true);
    }

    // Method to display restaurants and cuisine types in a city
    public static String displayCity(String city) {
        if (cityData.containsKey(city)) {
            StringBuilder result = new StringBuilder("Restaurants in " + city + ":\n");
            HashMap<String, String> restaurants = cityData.get(city);
            for (Map.Entry<String, String> entry : restaurants.entrySet()) {
                result.append("- ").append(entry.getKey()).append(" (Cuisine: ").append(entry.getValue()).append(")\n");
            }
            return result.toString();
        } else {
            return "City not found in the guide.";
        }
    }

    // Method to add a restaurant to a city
    public static void addRestaurant(String city, String restaurant, String cuisine) {
        cityData.putIfAbsent(city, new HashMap<>());
        cityData.get(city).put(restaurant, cuisine);
    }

    // Method to find a city
    public static String findCity(String city) {
        if (cityData.containsKey(city)) {
            return "City " + city + " found in the guide.";
        } else {
            return "City " + city + " not found.";
        }
    }

    // Method to add initial predefined cities (Tamil Nadu cities) and their
    // restaurants
    public static void addInitialCityData() {
        HashMap<String, String> chennaiRestaurants = new HashMap<>();
        chennaiRestaurants.put("Saravana Bhavan", "South Indian");
        chennaiRestaurants.put("Anjappar", "Chettinad");
        chennaiRestaurants.put("The Raintree", "Multi-Cuisine");
        chennaiRestaurants.put("Peshawri", "North Indian");
        chennaiRestaurants.put("Murugan Idli Shop", "South Indian");
        chennaiRestaurants.put("Barbeque Nation", "Grill");
        chennaiRestaurants.put("Dakshin", "Authentic South Indian");
        chennaiRestaurants.put("Cream Centre", "Vegetarian");
        chennaiRestaurants.put("Absolute Barbecue", "Grill & Buffet");
        chennaiRestaurants.put("Sandy's Chocolate Laboratory", "Desserts");
        cityData.put("Chennai", chennaiRestaurants);

        HashMap<String, String> coimbatoreRestaurants = new HashMap<>();
        coimbatoreRestaurants.put("Sree Annapoorna", "South Indian");
        coimbatoreRestaurants.put("Hari Bhavanam", "South Indian");
        coimbatoreRestaurants.put("The Bombay Brasserie", "Indian");
        coimbatoreRestaurants.put("Shree Sangeetha", "South Indian");
        coimbatoreRestaurants.put("Almora", "North Indian");
        coimbatoreRestaurants.put("On The Go", "Continental");
        coimbatoreRestaurants.put("Geetha Cafe", "Snacks and Drinks");
        coimbatoreRestaurants.put("Dindigul Thalappakatti", "Biriyani");
        coimbatoreRestaurants.put("Kove", "Fusion Indian");
        coimbatoreRestaurants.put("Latitude Restaurant", "Multi-Cuisine");
        cityData.put("Coimbatore", coimbatoreRestaurants);

        HashMap<String, String> maduraiRestaurants = new HashMap<>();
        maduraiRestaurants.put("Murugan Idli Shop", "South Indian");
        maduraiRestaurants.put("Chidambaram", "Chettinad");
        maduraiRestaurants.put("Kumar Mess", "South Indian");
        maduraiRestaurants.put("Sree Saravana Bhavan", "South Indian");
        maduraiRestaurants.put("The Bell Hotel", "Multi-Cuisine");
        maduraiRestaurants.put("Muniyandi Vilas", "Local Chettinad");
        maduraiRestaurants.put("Temple City", "Vegetarian");
        maduraiRestaurants.put("Meenakshi Bhavan", "Authentic South Indian");
        maduraiRestaurants.put("Zaitoon", "Arabian Cuisine");
        maduraiRestaurants.put("Buhari", "Biriyani");
        cityData.put("Madurai", maduraiRestaurants);

        HashMap<String, String> trichyRestaurants = new HashMap<>();
        trichyRestaurants.put("Sree Annapoorna", "South Indian");
        trichyRestaurants.put("Karaikudi Chettinad", "Chettinad");
        trichyRestaurants.put("Hotel Canara", "South Indian");
        trichyRestaurants.put("Shree Sangeetha", "South Indian");
        trichyRestaurants.put("Banana Leaf", "South Indian");
        trichyRestaurants.put("The Residency", "Fine Dining");
        trichyRestaurants.put("Rasika", "North Indian");
        trichyRestaurants.put("Ebony", "Buffet");
        trichyRestaurants.put("Café Coffee Day", "Cafe");
        trichyRestaurants.put("Thalappakatti Biriyani", "Biriyani");
        cityData.put("Trichy", trichyRestaurants);
    }
}
